/*
 * Copyright 2005-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.document;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.custom.DocumentCustomData;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocValue;
import org.kuali.coeus.common.permissions.impl.PermissionableKeys;
import org.kuali.coeus.propdev.impl.budget.ProposalBudgetStatusService;
import org.kuali.coeus.propdev.impl.state.ProposalStateService;
import org.kuali.coeus.sys.framework.auth.perm.Permissionable;
import org.kuali.coeus.sys.framework.auth.task.Task;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.workflow.KcDocumentRejectionService;
import org.kuali.kra.bo.RolePersons;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.BudgetParentDocument;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.infrastructure.TaskGroupName;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalService;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.kra.krms.KrmsRulesContext;
import org.kuali.kra.krms.service.impl.KcKrmsFactBuilderServiceHelper;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.document.authorization.ProposalTask;
import org.kuali.kra.proposaldevelopment.hierarchy.ProposalHierarchyException;
import org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.COMPONENT;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.NAMESPACE;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.KewApiServiceLocator;
import org.kuali.rice.kew.api.action.ActionTaken;
import org.kuali.rice.kew.api.action.WorkflowDocumentActionsService;
import org.kuali.rice.kew.framework.postprocessor.ActionTakenEvent;
import org.kuali.rice.kew.framework.postprocessor.DocumentRouteStatusChange;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.web.ui.ExtraButton;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;
import org.kuali.rice.krad.datadictionary.DataDictionary;
import org.kuali.rice.krad.datadictionary.DocumentEntry;
import org.kuali.rice.krad.document.Copyable;
import org.kuali.rice.krad.document.SessionDocument;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.workflow.DocumentInitiator;
import org.kuali.rice.krad.workflow.KualiDocumentXmlMaterializer;
import org.kuali.rice.krad.workflow.KualiTransactionalDocumentInformation;
import org.kuali.rice.krms.api.engine.Facts;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@NAMESPACE(namespace = Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT)
@COMPONENT(component = ParameterConstants.DOCUMENT_COMPONENT)
@Entity
@Table(name = "EPS_PROPOSAL_DOCUMENT")
public class ProposalDevelopmentDocument extends BudgetParentDocument<DevelopmentProposal> implements Copyable, SessionDocument, Permissionable, KrmsRulesContext {

    private static Log LOG = LogFactory.getLog(ProposalDevelopmentDocument.class);

    public static final String DOCUMENT_TYPE_CODE = "PRDV";

    private static final String KRA_EXTERNALIZABLE_IMAGES_URI_KEY = "kra.externalizable.images.url";

    private static final String RETURN_TO_PROPOSAL_ALT_TEXT = "return to proposal";

    private static final String RETURN_TO_PROPOSAL_METHOD_TO_CALL = "methodToCall.returnToProposal";

    private static final String HIERARCHY_CHILD_SPLITNODE_QUESTION = "isHierarchyChild";

    @Column(name = "PROPOSAL_DELETED")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean proposalDeleted = Boolean.FALSE;

    @OneToOne(mappedBy = "proposalDocument", cascade = CascadeType.ALL)
    private DevelopmentProposal developmentProposal;

    @OneToMany(orphanRemoval = true, cascade = { CascadeType.ALL })
    @JoinColumn(name = "PARENT_DOCUMENT_KEY", referencedColumnName = "DOCUMENT_NUMBER", insertable = false, updatable = false)
    private List<BudgetDocumentVersion> budgetDocumentVersions;

    @OneToMany(orphanRemoval = true, cascade = { CascadeType.ALL })
    @JoinColumn(name = "DOCUMENT_NUMBER", referencedColumnName = "DOCUMENT_NUMBER", insertable = true, updatable = true)
    private List<CustomAttributeDocValue> customDataList;

    /* Currently this property is just used for UI display.
 * If it becomes part of the domain, it should probably move to DevelopmentProposal.java
 */
    @Transient
    private String institutionalProposalNumber;

    @Transient
    private String saveXmlFolderName;

    @Transient
    private transient Boolean allowsNoteAttachments;

    public ProposalDevelopmentDocument() {
        super();
        DevelopmentProposal newProposal = new DevelopmentProposal();
        newProposal.setProposalDocument(this);
        developmentProposal = newProposal;
        budgetDocumentVersions = new ArrayList<BudgetDocumentVersion>();
        customDataList = new ArrayList<CustomAttributeDocValue>();
    }

    public DevelopmentProposal getDevelopmentProposal() {
        return developmentProposal;
    }

    public void setDevelopmentProposal(DevelopmentProposal proposal) {
        developmentProposal = proposal;
    }

    public String getInstitutionalProposalNumber() {
        return institutionalProposalNumber;
    }

    public void setInstitutionalProposalNumber(String institutionalProposalNumber) {
        this.institutionalProposalNumber = institutionalProposalNumber;
    }

    @Override
    public void initialize() {
        super.initialize();
        getDevelopmentProposal().initializeOwnedByUnitNumber();
    }

    @Override
    public void doRouteStatusChange(DocumentRouteStatusChange dto) {
        super.doRouteStatusChange(dto);
        String newStatus = dto.getNewRouteStatus();
        String oldStatus = dto.getOldRouteStatus();
        if (LOG.isDebugEnabled()) {
            LOG.debug(String.format("Route Status change for document %s from %s to %s", this.getDocumentNumber(), oldStatus, newStatus));
        }
        if (!isProposalDeleted()) {
            DevelopmentProposal bp = this.getDevelopmentProposal();
            ProposalHierarchyService hierarchyService = KcServiceLocator.getService(ProposalHierarchyService.class);
            LOG.info(String.format("Route status change for document %s - proposal number %s is moving from %s to %s", bp.getProposalDocument().getDocumentHeader().getDocumentNumber(), bp.getProposalNumber(), oldStatus, newStatus));
            if (bp.isParent()) {
                try {
                    hierarchyService.routeHierarchyChildren(this, dto);
                } catch (ProposalHierarchyException e) {
                    throw new RuntimeException("ProposalHierarchyException thrown while routing children.", e);
                }
            } else if (!bp.isInHierarchy()) {
                try {
                    hierarchyService.calculateAndSetProposalAppDocStatus(this, dto);
                } catch (ProposalHierarchyException pe) {
                    throw new RuntimeException(String.format("ProposalHierarchyException thrown while updating app doc status for document %s", getDocumentNumber()));
                }
            }
            bp.setProposalStateTypeCode(KcServiceLocator.getService(ProposalStateService.class).getProposalStateTypeCode(this, true, false));
        }
    }

    @Override
    public void doActionTaken(ActionTakenEvent event) {
        super.doActionTaken(event);
        ActionTaken actionTaken = event.getActionTaken();
        if (LOG.isDebugEnabled()) {
            LOG.debug(String.format("Action taken on document %s: event code %s, action taken is %s", getDocumentNumber(), event.getDocumentEventCode(), actionTaken.getActionTaken().getCode()));
        }
        if (!isProposalDeleted()) {
            ProposalHierarchyService hService = KcServiceLocator.getService(ProposalHierarchyService.class);
            KcDocumentRejectionService documentRejectionService = KcServiceLocator.getService(KcDocumentRejectionService.class);
            if (StringUtils.equals(KewApiConstants.ACTION_TAKEN_APPROVED_CD, actionTaken.getActionTaken().getCode())) {
                try {
                    if (documentRejectionService.isDocumentOnInitialNode(this)) {
                        DocumentRouteStatusChange dto = new DocumentRouteStatusChange(getDocumentHeader().getWorkflowDocument().getDocumentId(), getDocumentNumber(), KewApiConstants.ROUTE_HEADER_ENROUTE_CD, KewApiConstants.ROUTE_HEADER_ENROUTE_CD);
                        //DocumentRouteStatusChange.documentEventCode is always returned as rt_status_change 
                        //dto.setDocumentEventCode("REJECTED_APPROVED"); 
                        if (getDevelopmentProposal().isParent()) {
                            hService.routeHierarchyChildren(this, dto);
                            hService.calculateAndSetProposalAppDocStatus(this, dto);
                        }
                        if (!getDevelopmentProposal().isInHierarchy())
                            hService.calculateAndSetProposalAppDocStatus(this, dto);
                    }
                } catch (ProposalHierarchyException pe) {
                    throw new RuntimeException(String.format("ProposalHeierachyException encountered trying to re-submit rejected parent document:%s", getDocumentNumber()), pe);
                } catch (Exception we) {
                    throw new RuntimeException(String.format("Exception trying to re-submit rejected parent:%s", getDocumentNumber()), we);
                }
            }
            String pCode = getDevelopmentProposal().getProposalStateTypeCode();
            getDevelopmentProposal().setProposalStateTypeCode(KcServiceLocator.getService(ProposalStateService.class).getProposalStateTypeCode(this, false, documentRejectionService.isDocumentOnInitialNode(this)));
            if (!StringUtils.equals(pCode, getDevelopmentProposal().getProposalStateTypeCode())) {
                getDevelopmentProposal().refresh();
                KcServiceLocator.getService(BusinessObjectService.class).save(getDevelopmentProposal());
            }
            if (getDevelopmentProposal().isChild() && StringUtils.equals(KewApiConstants.ACTION_TAKEN_CANCELED_CD, actionTaken.getActionTaken().getCode())) {
                try {
                    hService.removeFromHierarchy(this.getDevelopmentProposal());
                } catch (ProposalHierarchyException e) {
                    throw new RuntimeException(String.format("COULD NOT REMOVE CHILD:%s", this.getDevelopmentProposal().getProposalNumber()));
                }
            }
            if (isLastSubmitterApprovalAction(event.getActionTaken()) && shouldAutogenerateInstitutionalProposal()) {
                InstitutionalProposalService institutionalProposalService = KcServiceLocator.getService(InstitutionalProposalService.class);
                String proposalNumber = institutionalProposalService.createInstitutionalProposal(this.getDevelopmentProposal(), this.getFinalBudgetForThisProposal());
                this.setInstitutionalProposalNumber(proposalNumber);
            }
        }
    }

    private boolean isLastSubmitterApprovalAction(ActionTaken actionTaken) {
        WorkflowDocumentActionsService workflowInfo = KewApiServiceLocator.getWorkflowDocumentActionsService();
        return actionTaken.getActionTaken().getCode().equals(KewApiConstants.ACTION_TAKEN_APPROVED_CD) && workflowInfo.isFinalApprover(actionTaken.getDocumentId(), actionTaken.getPrincipalId());
    }

    private boolean shouldAutogenerateInstitutionalProposal() {
        return getParameterService().getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, ParameterConstants.DOCUMENT_COMPONENT, KeyConstants.AUTOGENERATE_INSTITUTIONAL_PROPOSAL_PARAM);
    }

    protected ConfigurationService getKualiConfigurationService() {
        return CoreApiServiceLocator.getKualiConfigurationService();
    }

    protected ParameterService getParameterService() {
        return KcServiceLocator.getService(ParameterService.class);
    }

    protected DateTimeService getDateTimeService() {
        return KcServiceLocator.getService(DateTimeService.class);
    }

    public Budget getFinalBudgetForThisProposal() {
        BudgetDocumentVersion budgetDocumentVersion = this.getFinalBudgetVersion();
        if (budgetDocumentVersion != null) {
            return budgetDocumentVersion.getFinalBudget();
        }
        return null;
    }

    public String getFinalrateClassCode() {
        String retVal = "";
        Budget finalBudget = getFinalBudgetForThisProposal();
        if (finalBudget != null && finalBudget.getRateClass().getRateClassCode() != null) {
            retVal = finalBudget.getRateClass().getRateClassCode();
        }
        return retVal;
    }

    public String getDocumentTypeCode() {
        return DOCUMENT_TYPE_CODE;
    }

    /**
     * Wraps a document in an instance of KualiDocumentXmlMaterializer, that provides additional metadata for serialization
     */
    @Override
    public KualiDocumentXmlMaterializer wrapDocumentWithMetadataForXmlSerialization() {
        KualiTransactionalDocumentInformation transInfo = new KualiTransactionalDocumentInformation();
        DocumentInitiator initiator = new DocumentInitiator();
        String initiatorPrincipalId = getDocumentHeader().getWorkflowDocument().getDocument().getInitiatorPrincipalId();
        Person initiatorUser = KimApiServiceLocator.getPersonService().getPerson(initiatorPrincipalId);
        initiator.setPerson(initiatorUser);
        transInfo.setDocumentInitiator(initiator);
        PropDevDocumentXMLMaterializer xmlWrapper = new PropDevDocumentXMLMaterializer();
        xmlWrapper.setDocument(this);
        xmlWrapper.setKualiTransactionalDocumentInformation(transInfo);

        xmlWrapper.setRolepersons(getAllRolePersons());
        return xmlWrapper;
    }

    @Override
    public void prepareForSave() {
        super.prepareForSave();
        if (!isProposalDeleted()) {
            getDevelopmentProposal().updateS2sOpportunity();
            KcServiceLocator.getService(ProposalBudgetStatusService.class).saveBudgetFinalVersionStatus(this);
            if (getBudgetDocumentVersions() != null) {
                updateDocumentDescriptions(getBudgetDocumentVersions());
            }
        }
    }

    @Override
    public void processAfterRetrieve() {
        super.processAfterRetrieve();
        if (!isProposalDeleted()) {
            KcServiceLocator.getService(ProposalBudgetStatusService.class).loadBudgetStatus(this.getDevelopmentProposal());
            getDevelopmentProposal().updateProposalChangeHistory();
        }
    }

    public Boolean getAllowsNoteAttachments() {
        if (allowsNoteAttachments == null) {
            DataDictionary dataDictionary = KNSServiceLocator.getDataDictionaryService().getDataDictionary();
            DocumentEntry entry = (DocumentEntry) dataDictionary.getDocumentEntry(getClass().getName());
            allowsNoteAttachments = entry.getAllowsNoteAttachments();
        }
        return allowsNoteAttachments;
    }

    public void setAllowsNoteAttachments(boolean allowsNoteAttachments) {
        this.allowsNoteAttachments = allowsNoteAttachments;
    }

    @Override
    public List<String> getRoleNames() {
        List<String> roleNames = new ArrayList<String>();
        roleNames.add(RoleConstants.AGGREGATOR);
        roleNames.add(RoleConstants.BUDGET_CREATOR);
        roleNames.add(RoleConstants.NARRATIVE_WRITER);
        roleNames.add(RoleConstants.VIEWER);
        roleNames.add("approver");
        return roleNames;
    }

    @Override
    public String getDocumentNumberForPermission() {
        return getDevelopmentProposal().getProposalNumber();
    }

    @Override
    public String getDocumentKey() {
        return PermissionableKeys.PROPOSAL_KEY;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        managedLists.addAll(getDevelopmentProposal().buildListOfDeletionAwareLists());
        return managedLists;
    }

    /**
     * Sets the budgetDocumentVersions attribute value.
     * @param budgetDocumentVersions The budgetDocumentVersions to set.
     */
    public void setBudgetDocumentVersions(List<BudgetDocumentVersion> budgetDocumentVersions) {
        this.budgetDocumentVersions = budgetDocumentVersions;
    }

    /**
     * Gets the budgetDocumentVersions attribute. 
     * @return Returns the budgetDocumentVersions.
     */
    public List<BudgetDocumentVersion> getBudgetDocumentVersions() {
        return budgetDocumentVersions;
    }

    @Override
    public Task getParentAuthZTask(String taskName) {
        return new ProposalTask(taskName, this);
    }

    @Override
    public boolean isComplete() {
        return getDevelopmentProposal().isProposalComplete();
    }

    @Override
    public void saveBudgetFinalVersionStatus(BudgetDocument budgetDocument) {
        KcServiceLocator.getService(ProposalBudgetStatusService.class).saveBudgetFinalVersionStatus(this);
    }

    @Override
    public void processAfterRetrieveForBudget(BudgetDocument budgetDocument) {
        KcServiceLocator.getService(ProposalBudgetStatusService.class).loadBudgetStatusByProposalDocumentNumber(budgetDocument.getParentDocumentKey());
    }

    @Override
    public String getTaskGroupName() {
        return TaskGroupName.PROPOSAL_BUDGET;
    }

    @Override
    public ExtraButton configureReturnToParentTopButton() {
        ExtraButton returnToProposalButton = new ExtraButton();
        returnToProposalButton.setExtraButtonProperty(RETURN_TO_PROPOSAL_METHOD_TO_CALL);
        returnToProposalButton.setExtraButtonSource(buildExtraButtonSourceURI("tinybutton-retprop.gif"));
        returnToProposalButton.setExtraButtonAltText(RETURN_TO_PROPOSAL_ALT_TEXT);
        return returnToProposalButton;
    }

    /**
     * This method does what its name says
     * @param buttonFileName
     * @return
     */
    private String buildExtraButtonSourceURI(String buttonFileName) {
        return getKualiConfigurationService().getPropertyValueAsString(KRA_EXTERNALIZABLE_IMAGES_URI_KEY) + buttonFileName;
    }

    @Override
    public DevelopmentProposal getBudgetParent() {
        return getDevelopmentProposal();
    }

    @Override
    public String getDocumentBoNumber() {
        return getDevelopmentProposal().getProposalNumber();
    }

    @Override
    public Permissionable getBudgetPermissionable() {
        return new Permissionable() {

            public String getDocumentKey() {
                return PermissionableKeys.PROPOSAL_BUDGET_KEY;
            }

            public String getDocumentNumberForPermission() {
                return getDevelopmentProposal().getProposalNumber();
            }

            public List<String> getRoleNames() {
                List<String> roleNames = new ArrayList<String>();
                return roleNames;
            }

            public String getNamespace() {
                return Constants.MODULE_NAMESPACE_BUDGET;
            }

            public String getLeadUnitNumber() {
                return getDevelopmentProposal().getOwnedByUnitNumber();
            }

            public String getDocumentRoleTypeCode() {
                return RoleConstants.PROPOSAL_ROLE_TYPE;
            }

            public void populateAdditionalQualifiedRoleAttributes(Map<String, String> qualifiedRoleAttributes) {
            }
        };
    }

    @Override
    public boolean answerSplitNodeQuestion(String routeNodeName) {
        LOG.debug("Processing answerSplitNodeQuestion:" + routeNodeName);
        if (StringUtils.equals(HIERARCHY_CHILD_SPLITNODE_QUESTION, routeNodeName)) {
            return getDevelopmentProposal().isChild();
        }
        //defer to the super class. ResearchDocumentBase will throw the UnsupportedOperationException 
        //if no super class answers the question. 
        return super.answerSplitNodeQuestion(routeNodeName);
    }

    public String getNamespace() {
        return Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT;
    }

    public String getLeadUnitNumber() {
        return getDevelopmentProposal().getOwnedByUnitNumber();
    }

    public String getDocumentRoleTypeCode() {
        return RoleConstants.PROPOSAL_ROLE_TYPE;
    }

    public String getProposalBudgetFlag() {
        return "true";
    }

    /**
     * This method is to check whether rice async routing is ok now.   
     * Close to hack.  called by holdingpageaction
     * Different document type may have different routing set up, so each document type
     * can implement its own isProcessComplete
     * @return
     */
    public boolean isProcessComplete() {
        boolean isComplete = false;
        if (getDocumentHeader().hasWorkflowDocument()) {
            String docRouteStatus = getDocumentHeader().getWorkflowDocument().getStatus().getCode();
            if (KewApiConstants.ROUTE_HEADER_ENROUTE_CD.equals(docRouteStatus) || KewApiConstants.ROUTE_HEADER_PROCESSED_CD.equals(docRouteStatus) || KewApiConstants.ROUTE_HEADER_FINAL_CD.equals(docRouteStatus)) {
                isComplete = true;
            }
        }
        return isComplete;
    }

    public boolean isProposalDeleted() {
        return proposalDeleted;
    }

    public void setProposalDeleted(boolean proposalDeleted) {
        this.proposalDeleted = proposalDeleted;
    }

    public void refreshBudgetDocumentVersions() {
        final List<BudgetDocumentVersion> v = KcServiceLocator.getService(DataObjectService.class).findMatching(BudgetDocumentVersion.class,
                QueryByCriteria.Builder.andAttributes(Collections.singletonMap("parentDocumentKey",documentNumber)).build()).getResults();
        budgetDocumentVersions = v;
    }

    public void populateContextQualifiers(Map<String, String> qualifiers) {
        qualifiers.put("namespaceCode", Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT);
        qualifiers.put("name", KcKrmsConstants.ProposalDevelopment.PROPOSAL_DEVELOPMENT_CONTEXT);
    }

    public void addFacts(Facts.Builder factsBuilder) {
        KcKrmsFactBuilderServiceHelper fbService = KcServiceLocator.getService("proposalDevelopmentFactBuilderService");
        fbService.addFacts(factsBuilder, this);
    }

    public void populateAgendaQualifiers(Map<String, String> qualifiers) {
        qualifiers.put(KcKrmsConstants.UNIT_NUMBER, getLeadUnitNumber());
    }

    public void defaultDocumentDescription() {
        DevelopmentProposal proposal = getDevelopmentProposal();
        String desc = String.format("%s; Proposal No: %s; PI: %s; Sponsor: %s; Due Date: %s", proposal.getTitle() != null ? proposal.getTitle().substring(0, Math.min(proposal.getTitle().length(), 19)) : "null", proposal.getProposalNumber(), proposal.getPrincipalInvestigatorName(), proposal.getSponsorName(), proposal.getDeadlineDate() != null ? getDateTimeService().toDateString(proposal.getDeadlineDate()) : "null");
        getDocumentHeader().setDocumentDescription(desc);
    }

    @Override
    public List<? extends DocumentCustomData> getDocumentCustomData() {
        return getCustomDataList();
    }

    public List<CustomAttributeDocValue> getCustomDataList() {
        return customDataList;
    }

    public void setCustomDataList(List<CustomAttributeDocValue> customDataList) {
        this.customDataList = customDataList;
    }

    public String getSaveXmlFolderName() {
        return saveXmlFolderName;
    }

    public void setSaveXmlFolderName(String saveXmlFolderName) {
        this.saveXmlFolderName = saveXmlFolderName;
    }

    public boolean isDefaultDocumentDescription() {
        return getParameterService().getParameterValueAsBoolean(ProposalDevelopmentDocument.class, Constants.HIDE_AND_DEFAULT_PROP_DEV_DOC_DESC_PARAM);
    }

    @Override
    public String getDocumentTitle() {
        if (isDefaultDocumentDescription()) {
            return this.getDocumentHeader().getDocumentDescription();
        } else {
            return super.getDocumentTitle();
        }
    }

    public static class PropDevDocumentXMLMaterializer extends KualiDocumentXmlMaterializer{

        List<RolePersons> rolepersons;

        public List<RolePersons> getRolepersons() {
            return rolepersons;
        }

        public void setRolepersons(List<RolePersons> rolepersons) {
            this.rolepersons = rolepersons;
        }
    }
}

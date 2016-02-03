/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.propdev.impl.core;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.auth.perm.DocumentLevelPermissionable;
import org.kuali.coeus.common.framework.custom.DocumentCustomData;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocValue;
import org.kuali.coeus.common.permissions.impl.PermissionableKeys;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.propdev.impl.state.ProposalStateService;
import org.kuali.coeus.common.framework.auth.perm.Permissionable;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.workflow.KcDocumentRejectionService;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.bo.RolePersons;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetParentDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalService;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.coeus.common.framework.krms.KrmsRulesContext;
import org.kuali.coeus.common.impl.krms.KcKrmsFactBuilderServiceHelper;
import org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyException;
import org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyService;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.COMPONENT;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.NAMESPACE;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.KewApiServiceLocator;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.action.ActionTaken;
import org.kuali.rice.kew.api.action.ActionType;
import org.kuali.rice.kew.api.action.WorkflowDocumentActionsService;
import org.kuali.rice.kew.api.document.WorkflowDocumentService;
import org.kuali.rice.kew.framework.postprocessor.ActionTakenEvent;
import org.kuali.rice.kew.framework.postprocessor.DocumentRouteStatusChange;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.web.ui.ExtraButton;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;
import org.kuali.rice.krad.datadictionary.DocumentEntry;
import org.kuali.rice.krad.document.Copyable;
import org.kuali.rice.krad.document.SessionDocument;
import org.kuali.rice.krad.service.DataDictionaryService;
import org.kuali.rice.krad.service.DocumentHeaderService;
import org.kuali.rice.krad.util.NoteType;
import org.kuali.rice.krad.workflow.DocumentInitiator;
import org.kuali.rice.krad.workflow.KualiDocumentXmlMaterializer;
import org.kuali.rice.krad.workflow.KualiTransactionalDocumentInformation;
import org.kuali.rice.krms.api.engine.Facts;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@NAMESPACE(namespace = Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT)
@COMPONENT(component = ParameterConstants.DOCUMENT_COMPONENT)
@Entity
@Table(name = "EPS_PROPOSAL_DOCUMENT")
public class ProposalDevelopmentDocument extends BudgetParentDocument<DevelopmentProposal> implements Copyable, SessionDocument, DocumentLevelPermissionable, KrmsRulesContext, ProposalDevelopmentDocumentContract {

    private static Log LOG = LogFactory.getLog(ProposalDevelopmentDocument.class);

    public static final String DOCUMENT_TYPE_CODE = "PRDV";

    private static final String KRA_EXTERNALIZABLE_IMAGES_URI_KEY = "kra.externalizable.images.url";

    private static final String RETURN_TO_PROPOSAL_ALT_TEXT = "return to proposal";

    private static final String RETURN_TO_PROPOSAL_METHOD_TO_CALL = "methodToCall.returnToProposal";

    private static final String HIERARCHY_CHILD_SPLITNODE_QUESTION = "isHierarchyChild";

    @Transient
    private transient DocumentHeaderService documentHeaderService;

    @Transient
    private transient ProposalHierarchyService  proposalHierarchyService;
    
	@Transient
    private transient ProposalStateService proposalStateService;
	
	@Transient
    private transient KcDocumentRejectionService kcDocumentRejectionService;

    @Transient
    private transient WorkflowDocumentService workflowDocumentService;

    @Transient
	InstitutionalProposalService institutionalProposalService;
	
    @Transient
    private transient WorkflowDocumentActionsService  workflowDocumentActionsService;

    
    @Transient
    private transient DataDictionaryService dataDictionaryService  ;
    
    @Transient
    private transient  DataObjectService dataObjectService; 
    
    @Transient
    private transient  KcKrmsFactBuilderServiceHelper proposalDevelopmentFactBuilderService;
    
    
    @Column(name = "PROPOSAL_DELETED")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean proposalDeleted = Boolean.FALSE;

    @OneToOne(orphanRemoval = true, mappedBy = "proposalDocument", cascade = CascadeType.ALL)
    private DevelopmentProposal developmentProposal;

    @OneToMany(orphanRemoval = true, cascade = { CascadeType.ALL })
    @JoinColumn(name = "DOCUMENT_NUMBER", referencedColumnName = "DOCUMENT_NUMBER", insertable = true, updatable = true)
    private List<CustomAttributeDocValue> customDataList;

    /* Currently this property is just used for UI display.
 * If it becomes part of the domain, it should probably move to DevelopmentProposal.java
 */
    @Transient
    private String institutionalProposalNumber;

    @Transient
    private transient Boolean allowsNoteAttachments;
    
    @Transient
    private transient Boolean certifyViewOnly = false;

	public ProposalDevelopmentDocument() {
        super();
        DevelopmentProposal newProposal = new DevelopmentProposal();
        newProposal.setProposalDocument(this);
        developmentProposal = newProposal;
        customDataList = new ArrayList<>();
    }
    @Override
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

    protected ProposalHierarchyService getProposalHierarchyService() {
		if (proposalHierarchyService == null){
			proposalHierarchyService = KcServiceLocator.getService(ProposalHierarchyService.class);
		}
    	return proposalHierarchyService;
	}

	protected ProposalStateService getProposalStateService() {
		if (proposalStateService == null){
			proposalStateService = KcServiceLocator.getService(ProposalStateService.class);
		}
		return proposalStateService;
	}

	protected KcDocumentRejectionService getKcDocumentRejectionService() {
		if (kcDocumentRejectionService == null){
			kcDocumentRejectionService = KcServiceLocator.getService(KcDocumentRejectionService.class);
		}
		return kcDocumentRejectionService;
	}

    protected WorkflowDocumentService getWorkflowDocumentService() {
        if (workflowDocumentService == null){
            workflowDocumentService = KewApiServiceLocator.getWorkflowDocumentService();
        }
        return workflowDocumentService;
    }
	
	protected InstitutionalProposalService getInstitutionalProposalService () {
		if ( institutionalProposalService == null){
			institutionalProposalService = KcServiceLocator.getService(InstitutionalProposalService.class);
		}
		return institutionalProposalService;
	}
	
	protected WorkflowDocumentActionsService getWorkflowDocumentActionsService() {
		if (workflowDocumentActionsService == null){
			workflowDocumentActionsService = KewApiServiceLocator.getWorkflowDocumentActionsService();
		}
		return workflowDocumentActionsService;
	}

	protected DataDictionaryService getDataDictionaryService() {
		if (dataDictionaryService == null){
			dataDictionaryService = KNSServiceLocator.getDataDictionaryService();
		}
		return dataDictionaryService;
	}

	protected DataObjectService getDataObjectService() {
		if (dataObjectService == null){
			dataObjectService = KcServiceLocator.getService(DataObjectService.class);
		}
		return dataObjectService;
	}

	protected KcKrmsFactBuilderServiceHelper getProposalDevelopmentFactBuilderService() {
		if (proposalDevelopmentFactBuilderService == null){
			proposalDevelopmentFactBuilderService = KcServiceLocator.getService("proposalDevelopmentFactBuilderService");
		}
		return proposalDevelopmentFactBuilderService;
	}

    @Override
    public void doRouteStatusChange(DocumentRouteStatusChange dto) {

        executeAsLastActionUser( () -> {
            super.doRouteStatusChange(dto);
            String newStatus = dto.getNewRouteStatus();
            String oldStatus = dto.getOldRouteStatus();
            if (LOG.isDebugEnabled()) {
                LOG.debug(String.format("Route Status change for document %s from %s to %s", this.getDocumentNumber(), oldStatus, newStatus));
            }
            if (!isProposalDeleted()) {
                DevelopmentProposal bp = this.getDevelopmentProposal();
                LOG.info(String.format("Route status change for document %s - proposal number %s is moving from %s to %s", bp.getProposalDocument().getDocumentHeader().getDocumentNumber(), bp.getProposalNumber(), oldStatus, newStatus));
               if (!bp.isInHierarchy()) {
                    try {
                        getProposalHierarchyService().calculateAndSetProposalAppDocStatus(this, dto);
                    } catch (ProposalHierarchyException pe) {
                        throw new RuntimeException(String.format("ProposalHierarchyException thrown while updating app doc status for document %s", getDocumentNumber()));
                    }
                }
                bp.setProposalStateTypeCode(getProposalStateService().getProposalStateTypeCode(this, false));
                getDataObjectService().save(bp);
            }
            return null;
        });
    }

    @Override
    public void doActionTaken(ActionTakenEvent event) {
        executeAsLastActionUser( () -> {
            super.doActionTaken(event);
            ActionTaken actionTaken = event.getActionTaken();
            if (LOG.isDebugEnabled()) {
                LOG.debug(String.format("Action taken on document %s: event code %s, action taken is %s", getDocumentNumber(), event.getDocumentEventCode(), actionTaken.getActionTaken().getCode()));
            }
            if (!isProposalDeleted()) {
                if (StringUtils.equals(KewApiConstants.ACTION_TAKEN_APPROVED_CD, actionTaken.getActionTaken().getCode())) {
                    try {
                        if (getKcDocumentRejectionService().isDocumentOnInitialNode(this.getDocumentHeader().getWorkflowDocument())) {
                            DocumentRouteStatusChange dto = new DocumentRouteStatusChange(getDocumentHeader().getWorkflowDocument().getDocumentId(), getDocumentNumber(), KewApiConstants.ROUTE_HEADER_ENROUTE_CD, KewApiConstants.ROUTE_HEADER_ENROUTE_CD);
                            if (!getDevelopmentProposal().isChild()) {
                                getProposalHierarchyService().calculateAndSetProposalAppDocStatus(this, dto);
                            }
                        }
                    } catch (ProposalHierarchyException pe) {
                        throw new RuntimeException(String.format("ProposalHeierachyException encountered trying to re-submit rejected parent document:%s", getDocumentNumber()), pe);
                    } catch (Exception we) {
                        throw new RuntimeException(String.format("Exception trying to re-submit rejected parent:%s", getDocumentNumber()), we);
                    }
                }
                String pCode = getDevelopmentProposal().getProposalStateTypeCode();
                getDevelopmentProposal().setProposalStateTypeCode(getProposalStateService().getProposalStateTypeCode(this, hasProposalBeenRejected(getDocumentHeader().getWorkflowDocument())));
                if (!StringUtils.equals(pCode, getDevelopmentProposal().getProposalStateTypeCode())) {
                    getDataObjectService().save(getDevelopmentProposal());
                    getDevelopmentProposal().refreshReferenceObject("proposalState");
                }
                if (getDevelopmentProposal().isChild() && StringUtils.equals(KewApiConstants.ACTION_TAKEN_CANCELED_CD, actionTaken.getActionTaken().getCode())) {
                    try {
                        getProposalHierarchyService().removeFromHierarchy(this.getDevelopmentProposal());
                    } catch (ProposalHierarchyException e) {
                        throw new RuntimeException(String.format("COULD NOT REMOVE CHILD:%s", this.getDevelopmentProposal().getProposalNumber()));
                    }
                }
                if (isLastSubmitterApprovalAction(event.getActionTaken()) && shouldAutogenerateInstitutionalProposal()) {
                    final InstitutionalProposal institutionalProposal = getInstitutionalProposalService().createInstitutionalProposal(this.getDevelopmentProposal(), this.getDevelopmentProposal().getFinalBudget());
                    this.setInstitutionalProposalNumber(institutionalProposal.getProposalNumber());
                    getDataObjectService().save(this);
                }
            }
            return null;
        });
    }

    private boolean hasProposalBeenRejected(WorkflowDocument document) {
        return getKcDocumentRejectionService().isDocumentOnInitialNode(document) &&
            getWorkflowDocumentService().getAllActionsTaken(getDocumentNumber())
                .stream().anyMatch(actionTaken -> actionTaken.getActionTaken() == ActionType.COMPLETE);
    }

    private boolean isLastSubmitterApprovalAction(ActionTaken actionTaken) {
        return actionTaken.getActionTaken().getCode().equals(KewApiConstants.ACTION_TAKEN_APPROVED_CD) && getWorkflowDocumentActionsService().isFinalApprover(actionTaken.getDocumentId(), actionTaken.getPrincipalId());
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

    public String getFinalrateClassCode() {
        String retVal = "";
        Budget finalBudget = getDevelopmentProposal().getFinalBudget();
        if (finalBudget != null && finalBudget.getRateClass().getCode() != null) {
            retVal = finalBudget.getRateClass().getCode();
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

        documentHeader = getDocumentHeaderService().saveDocumentHeader(documentHeader);

        if (!isProposalDeleted()) {
            List<? extends Budget> versions = getBudgetParent().getBudgets();
            if (versions != null) {
                updateBudgetDescriptions(versions);
            }
        }
    }

    @Override
    public void processAfterRetrieve() {
        super.processAfterRetrieve();
        if (!isProposalDeleted()) {
            getDevelopmentProposal().updateProposalChangeHistory();
        }
    }

    public Boolean getAllowsNoteAttachments() {
        if (allowsNoteAttachments == null) {
            DocumentEntry entry = getDataDictionaryService().getDataDictionary().getDocumentEntry(getClass().getName());
            allowsNoteAttachments = entry.getAllowsNoteAttachments();
        }
        return allowsNoteAttachments;
    }

    public void setAllowsNoteAttachments(boolean allowsNoteAttachments) {
        this.allowsNoteAttachments = allowsNoteAttachments;
    }

    @Override
    public List<String> getRoleNames() {
        List<String> roleNames = new ArrayList<>();
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

    @Override
    public boolean isComplete() {
        return getDevelopmentProposal().isProposalComplete();
    }

    @Override
    public ExtraButton configureReturnToParentTopButton() {
        ExtraButton returnToProposalButton = new ExtraButton();
        returnToProposalButton.setExtraButtonProperty(RETURN_TO_PROPOSAL_METHOD_TO_CALL);
        returnToProposalButton.setExtraButtonSource(buildExtraButtonSourceURI("tinybutton-retprop.gif"));
        returnToProposalButton.setExtraButtonAltText(RETURN_TO_PROPOSAL_ALT_TEXT);
        return returnToProposalButton;
    }

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
                return new ArrayList<>();
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

    @Override
    public boolean isProposalDeleted() {
        return proposalDeleted;
    }

    public void setProposalDeleted(boolean proposalDeleted) {
        this.proposalDeleted = proposalDeleted;
    }

    public void populateContextQualifiers(Map<String, String> qualifiers) {
        qualifiers.put("namespaceCode", Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT);
        qualifiers.put("name", KcKrmsConstants.ProposalDevelopment.PROPOSAL_DEVELOPMENT_CONTEXT);
    }

    public void addFacts(Facts.Builder factsBuilder) {
    	getProposalDevelopmentFactBuilderService().addFacts(factsBuilder, this);
    }

    public void populateAgendaQualifiers(Map<String, String> qualifiers) {
        qualifiers.put(KcKrmsConstants.UNIT_NUMBER, getDevelopmentProposal().getAllUnitNumbers());
    }

    public void setDefaultDocumentDescription() {
        DevelopmentProposal proposal = getDevelopmentProposal();
        String desc = String.format("%s; Proposal No: %s; PI: %s; Sponsor: %s; Due Date: %s", proposal.getTitle() != null ? proposal.getTitle().substring(0, Math.min(proposal.getTitle().length(), 19)) : "null", proposal.getProposalNumber(), proposal.getPrincipalInvestigatorName(), proposal.getSponsorName(), proposal.getDeadlineDate() != null ? getDateTimeService().toDateString(proposal.getDeadlineDate()) : "null");
        getDocumentHeader().setDocumentDescription(desc);
    }

    public List<? extends DocumentCustomData> getDocumentCustomData() {
        return getCustomDataList();
    }

    @Override
    public List<CustomAttributeDocValue> getCustomDataList() {
        return customDataList;
    }

    public void setCustomDataList(List<CustomAttributeDocValue> customDataList) {
        this.customDataList = customDataList;
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

    @Override
    public NoteType getNoteType() {
        return NoteType.BUSINESS_OBJECT;
    }

    @Override
    public List getNotes() {
        if (StringUtils.isNotBlank(getNoteTarget().getObjectId())) {
            notes = new ArrayList<>(getNoteService().getByRemoteObjectId(getNoteTarget().getObjectId()));
        }
        return notes;
    }

    public DocumentHeaderService getDocumentHeaderService() {
        if (this.documentHeaderService == null) {
            this.documentHeaderService = KcServiceLocator.getService(DocumentHeaderService.class);
        }
        return this.documentHeaderService;
    }

    void setDocumentHeaderService(DocumentHeaderService documentHeaderService) {
        this.documentHeaderService = documentHeaderService;
    }

    public Boolean getCertifyViewOnly() {
		return certifyViewOnly;
	}

	public void setCertifyViewOnly(Boolean certifyViewOnly) {
		this.certifyViewOnly = certifyViewOnly;
	}

    @Override
    public String getCustomLockDescriptor(Person user) {
        return this.getDocumentBoNumber() + "-" + KraAuthorizationConstants.LOCK_DESCRIPTOR_PROPOSAL;
    }
}

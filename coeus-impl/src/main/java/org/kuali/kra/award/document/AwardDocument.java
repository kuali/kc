/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
package org.kuali.kra.award.document;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.coi.framework.Project;
import org.kuali.coeus.coi.framework.ProjectPublisher;
import org.kuali.coeus.coi.framework.ProjectRetrievalService;
import org.kuali.coeus.common.framework.custom.DocumentCustomData;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.common.framework.version.history.VersionHistoryService;
import org.kuali.coeus.common.permissions.impl.PermissionableKeys;
import org.kuali.coeus.common.framework.auth.perm.Permissionable;
import org.kuali.coeus.common.framework.auth.task.Task;
import org.kuali.coeus.sys.framework.controller.DocHandlerService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.award.awardhierarchy.sync.service.AwardSyncService;
import org.kuali.kra.award.budget.AwardBudgetExt;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.contacts.AwardPersonCreditSplit;
import org.kuali.kra.award.contacts.AwardPersonUnit;
import org.kuali.kra.award.document.authorization.AwardTask;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardComment;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.award.home.fundingproposal.AwardFundingProposal;
import org.kuali.kra.award.infrastructure.AwardPermissionConstants;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTermRecipient;
import org.kuali.kra.award.specialreview.AwardSpecialReview;
import org.kuali.kra.award.specialreview.AwardSpecialReviewExemption;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetParentDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.infrastructure.TaskGroupName;
import org.kuali.kra.institutionalproposal.ProposalStatus;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalBoLite;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalService;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.coeus.common.framework.krms.KrmsRulesContext;
import org.kuali.coeus.common.framework.krms.KcKrmsFactBuilderService;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.COMPONENT;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.NAMESPACE;
import org.kuali.rice.ken.util.NotificationConstants;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.framework.postprocessor.DocumentRouteLevelChange;
import org.kuali.rice.kew.framework.postprocessor.DocumentRouteStatusChange;
import org.kuali.rice.kim.api.permission.PermissionService;
import org.kuali.rice.kns.web.ui.ExtraButton;
import org.kuali.rice.krad.document.Copyable;
import org.kuali.rice.krad.document.SessionDocument;
import org.kuali.rice.krad.rules.rule.event.DocumentEvent;
import org.kuali.rice.krad.rules.rule.event.SaveDocumentEvent;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;
import org.kuali.rice.krms.api.engine.Facts;

import java.util.*;

/**
 * 
 * This class represents the Award Document Object.
 * AwardDocument has a 1:1 relationship with Award Business Object.
 * We have declared a list of Award BOs in the AwardDocument at the same time to
 * get around the OJB anonymous keys issue of primary keys of different data types.
 * Also we have provided convenient getter and setter methods so that to the outside world;
 * Award and AwardDocument can have a 1:1 relationship.
 */
@NAMESPACE(namespace=Constants.PARAMETER_MODULE_AWARD)
@COMPONENT(component=ParameterConstants.DOCUMENT_COMPONENT)
public class AwardDocument extends BudgetParentDocument<Award> implements  Copyable, SessionDocument, KrmsRulesContext {
    private static final Log LOG = LogFactory.getLog(AwardDocument.class);
    
    public static final String PLACEHOLDER_DOC_DESCRIPTION = "*****PLACEHOLDER*****";


    private static final long serialVersionUID = 1668673531338660064L;
    
    public static final String DOCUMENT_TYPE_CODE = "AWRD";
    private static final String DEFAULT_TAB = "Versions";
    private static final String ALTERNATE_OPEN_TAB = "Parameters";
    
    private List<Award> awardList;
    private List<AwardBudgetExt> budgets;
    
    private static final String RETURN_TO_AWARD_ALT_TEXT = "return to award";
    private static final String RETURN_TO_AWARD_METHOD_TO_CALL = "methodToCall.returnToAward";
    private static final String KRA_EXTERNALIZABLE_IMAGES_URI_KEY = "kra.externalizable.images.url";
    
    private static final String HAS_SYNC_SPLITNODE = "hasSync";
    private static final String IS_SYNC_CHILD_SPLITNODE = "isSyncChild";
    

    private transient boolean documentSaveAfterVersioning;
    private transient AwardService awardService;

    private transient ProjectRetrievalService projectRetrievalService;
    private transient ProjectPublisher projectPublisher;

    public AwardDocument(){        
        super();        
        init();
    }
    
    /**
     * 
     * This method is a convenience method for facilitating a 1:1 relationship between AwardDocument 
     * and Award to the outside world - aka a single Award field associated with AwardDocument
     */
    public Award getAward() {
        return awardList.size() > 0 ? awardList.get(0) : new Award();
    }

    /**
     * 
     * This method is a convenience method for facilitating a 1:1 relationship between AwardDocument 
     * and Award to the outside world - aka a single Award field associated with AwardDocument
     */
    public void setAward(Award award) {
        awardList.set(0, award);
    }
   

    public List<Award> getAwardList() {
        return awardList;
    }

    public void setAwardList(List<Award> awardList) {
        this.awardList = awardList;
    }

    @Override
    public String getDocumentTypeCode() {
        return DOCUMENT_TYPE_CODE;
    }
    

    public boolean isDocumentSaveAfterVersioning() {
        return documentSaveAfterVersioning;
    }

    public void setDocumentSaveAfterAwardLookupEditOrVersion(boolean documentSaveAfterVersioning) {
        this.documentSaveAfterVersioning = documentSaveAfterVersioning;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        
        Award award = getAward();
        
        addAwardPersonUnitsCollection(managedLists, award);
        managedLists.add(award.getProjectPersons());
        managedLists.add(award.getAwardUnitContacts());
        managedLists.add(award.getSponsorContacts());
        managedLists.add(award.getAwardCostShares());
        managedLists.add(award.getApprovedEquipmentItems());
        managedLists.add(award.getApprovedForeignTravelTrips());
        managedLists.add(award.getAwardFandaRate());
        managedLists.add(getAward().getAwardSponsorTerms());
        managedLists.add(award.getPaymentScheduleItems());
        managedLists.add(award.getAwardTransferringSponsors());
        managedLists.add(award.getAwardDirectFandADistributions());
        managedLists.add(award.getAwardApprovedSubawards());
        managedLists.add(award.getAwardCloseoutItems());
        managedLists.add(award.getAwardAttachments());
        
        List<AwardSpecialReviewExemption> specialReviewExemptions = new ArrayList<>();
        for (AwardSpecialReview specialReview : getAward().getSpecialReviews()) {
            specialReviewExemptions.addAll(specialReview.getSpecialReviewExemptions());            
        }
        managedLists.add(specialReviewExemptions);
        managedLists.add(award.getSpecialReviews());

        List<AwardReportTerm> reportTerms = award.getAwardReportTermItems();
        List<AwardReportTermRecipient> recipients = new ArrayList<>();
        for (AwardReportTerm reportTerm: reportTerms) {
            recipients.addAll(reportTerm.getAwardReportTermRecipients());
        }
        managedLists.add(recipients);
        managedLists.add(reportTerms);
        
        managedLists.add(award.getFundingProposals());

        managedLists.add(awardList);
        
        return managedLists;
    }
    
    @Override
    public void doRouteStatusChange(DocumentRouteStatusChange statusChangeEvent) {
        executeAsLastActionUser(() -> {
            super.doRouteStatusChange(statusChangeEvent);

            String newStatus = statusChangeEvent.getNewRouteStatus();

            if (LOG.isDebugEnabled()) {
                LOG.debug(String.format("********************* Status Change: from %s to %s", statusChangeEvent.getOldRouteStatus(), newStatus));
            }

            if (KewApiConstants.ROUTE_HEADER_FINAL_CD.equalsIgnoreCase(newStatus) || KewApiConstants.ROUTE_HEADER_PROCESSED_CD.equalsIgnoreCase(newStatus)) {
                getAwardService().updateAwardSequenceStatus(getAward(), VersionStatus.ACTIVE);
                getVersionHistoryService().updateVersionHistory(getAward(), VersionStatus.ACTIVE, GlobalVariables.getUserSession().getPrincipalName());
            }
            if (newStatus.equalsIgnoreCase(KewApiConstants.ROUTE_HEADER_CANCEL_CD) || newStatus.equalsIgnoreCase(KewApiConstants.ROUTE_HEADER_DISAPPROVED_CD)) {
                revertFundedProposals();
                disableAwardComments();
                getAwardService().updateAwardSequenceStatus(getAward(), VersionStatus.CANCELED);
                getVersionHistoryService().updateVersionHistory(getAward(), VersionStatus.CANCELED, GlobalVariables.getUserSession().getPrincipalName());
            }

            //reset Award List with updated document - in some scenarios the change in status is not reflected.
            for (Award award : awardList) {
                award.setAwardDocument(this);
            }

            final Project project = getProjectRetrievalService().retrieveProject(getAward().getAwardNumber());
            if (project != null) {
                getProjectPublisher().publishProject(project);
            }

            return null;
        });
    }

    @Override
    public void doRouteLevelChange(DocumentRouteLevelChange levelChangeEvent) {
        executeAsLastActionUser(() -> {

            if (StringUtils.equalsIgnoreCase(levelChangeEvent.getNewNodeName(), Constants.AWARD_SYNC_VALIDATION_NODE_NAME)
                    && !getAward().getSyncChanges().isEmpty()) {
                getAwardSyncService().validateHierarchyChanges(getAward());
            } else if (StringUtils.equalsIgnoreCase(levelChangeEvent.getNewNodeName(), Constants.AWARD_APPLY_SYNC_NODE_NAME)
                    && !getAward().getSyncChanges().isEmpty()) {
                getAwardSyncService().applyAwardSyncChangesToHierarchy(getAward());
            }
            final Project project = getProjectRetrievalService().retrieveProject(getAward().getAwardNumber());
            if (project != null) {
                getProjectPublisher().publishProject(project);
            }
            return null;
        });
    }
    
    @Override
    public boolean answerSplitNodeQuestion( String routeNodeName ) {
        LOG.debug("Processing answerSplitNodeQuestion:"+routeNodeName );
        if (StringUtils.equals(HAS_SYNC_SPLITNODE, routeNodeName)) {
            return !getAward().getSyncChanges().isEmpty();
        }
        if (StringUtils.equals(IS_SYNC_CHILD_SPLITNODE, routeNodeName)) {
            return getAward().isSyncChild();
        }
        //defer to the super class. ResearchDocumentBase will throw the UnsupportedOperationException
        //if no super class answers the question.
        return super.answerSplitNodeQuestion(routeNodeName);
    }
    
    protected void init() {
        awardList = new ArrayList<>();
        awardList.add(new Award());
        budgets = new ArrayList<>();
    }
    
    @Override
    public void prepareForSave() {
        super.prepareForSave();
        if (ObjectUtils.isNull(this.getVersionNumber())) {
            this.setVersionNumber(new Long(0));
        }
        if (getBudgetDocumentVersions() != null) {
            updateDocumentDescriptions(getAward().getBudgets());
        }
        Award award = getAward();
        if(!award.getProjectPersons().isEmpty()) {
            List<AwardPerson> aList = award.getProjectPersonsSorted();
            award.getProjectPersons().clear();
            award.getProjectPersons().addAll(aList);
            this.removeKeyPersonRoleForNoneKeyPerson();
        }
            
    }
    
    public void updateDocumentDescriptions(List<AwardBudgetExt> budgets) {
        for (Budget budgetVersion : budgets) {
            if (budgetVersion.isNameUpdatable() && !StringUtils.isBlank(budgetVersion.getName())) {
                budgetVersion.setNameUpdatable(false);
            }
        }
    }    

	void removeKeyPersonRoleForNoneKeyPerson() {
        for ( AwardPerson person : this.getAward().getProjectPersons() ) {
            if ( !StringUtils.equalsIgnoreCase(person.getContactRole().getRoleCode(), ContactRole.KEY_PERSON_CODE) &&
                    StringUtils.isNotEmpty(person.getKeyPersonRole()) ) {
                person.setKeyPersonRole(null);
            }
        }
    }
    
    @Override
    public void postProcessSave(DocumentEvent event) {
        if (event instanceof SaveDocumentEvent) {
            updateFundedProposals();
        }
    }
    
    private void updateFundedProposals() {
        Set<String> modifiedProposals = new HashSet<>();
        for (AwardFundingProposal afp : getAward().getFundingProposals()) {
            InstitutionalProposalBoLite proposal = afp.getProposal();
            if (!ProposalStatus.FUNDED.equals(proposal.getStatusCode())) {
                modifiedProposals.add(proposal.getProposalNumber());
            }
        }
        if (modifiedProposals.size() > 0) {
            getInstitutionalProposalService().fundInstitutionalProposals(modifiedProposals);
            getAward().refreshReferenceObject("fundingProposals");
        }
    }

    @SuppressWarnings("unchecked")
    private void addAwardPersonUnitsCollection(List managedLists, Award award) {
        List<AwardPersonUnit> personUnits = new ArrayList<>();
        List<AwardPersonCreditSplit> personSplits = new ArrayList<>();
        for(AwardPerson p: award.getProjectPersons()) {
            personUnits.addAll(p.getUnits());
            personSplits.addAll(p.getCreditSplits());
        }
        managedLists.add(personUnits);
        managedLists.add(personSplits);
    }

    public boolean isAuthorizedToPostAward(String principalId) {
        return isPostAwardFeatureEnabled() && getDocumentHeader().getWorkflowDocument().isFinal()
                && getAward().getAccountNumber() != null
                && !getAward().isPosted()
                && hasPostAwardPermission(principalId);
    }

    public boolean hasPostAwardPermission(String principalId) {
        return getPermissionService().hasPermission(principalId, Constants.KC_SYS, AwardPermissionConstants.POST_AWARD.getAwardPermission());
    }

    protected boolean isPostAwardFeatureEnabled() {
        return getParameterService().getParameterValueAsBoolean(
                Constants.PARAMETER_MODULE_AWARD,
                ParameterConstants.ALL_COMPONENT,
                Constants.AWARD_POST_ENABLED);
    }

    protected VersionHistoryService getVersionHistoryService() {
        return KcServiceLocator.getService(VersionHistoryService.class);
    }
    
    protected AwardSyncService getAwardSyncService() {
        return KcServiceLocator.getService(AwardSyncService.class);
    }

    public List<AwardBudgetExt> getBudgetDocumentVersions() {
    	 return this.getAward().getBudgets();
    }

    public List<AwardBudgetExt> getBudgets() {
        return budgets;
    }

    public void setBudgets(List<AwardBudgetExt> budgets) {
    	this.budgets = budgets;
    }

    @Override
    public String getDocumentKey() {
        return PermissionableKeys.AWARD_KEY;
    }

    @Override
    public String getDocumentNumberForPermission() {
        return getAward().getAwardId().toString();
    }

    @Override
    public List<String> getRoleNames() {
        return new ArrayList<>();
    }

    @Override
    public Permissionable getBudgetPermissionable(){
        return new Permissionable(){

            @Override
            public String getDocumentKey() {
                return PermissionableKeys.AWARD_BUDGET_KEY;
            }

            @Override
            public String getDocumentNumberForPermission() {
                return getAward().getAwardId().toString();
            }

            @Override
            public List<String> getRoleNames() {
                return new ArrayList<>();
            }

            @Override
            public String getNamespace() {
                return Constants.MODULE_NAMESPACE_AWARD;
            }

            @Override
            public String getLeadUnitNumber() {
                return getAward().getLeadUnitNumber();
            }

            @Override
            public String getDocumentRoleTypeCode() {
                return RoleConstants.AWARD_ROLE_TYPE;
            }

            @Override
            public void populateAdditionalQualifiedRoleAttributes(Map<String, String> qualifiedRoleAttributes) {
            }
        };
    }

    public String getTaskGroupName() {
        return TaskGroupName.AWARD_BUDGET;
    }

    @Override
    public ExtraButton configureReturnToParentTopButton() {
        ExtraButton returnToProposalButton = new ExtraButton();
        returnToProposalButton.setExtraButtonProperty(RETURN_TO_AWARD_METHOD_TO_CALL);
        returnToProposalButton.setExtraButtonSource(buildExtraButtonSourceURI("tinybutton-returntoaward.gif"));
        returnToProposalButton.setExtraButtonAltText(RETURN_TO_AWARD_ALT_TEXT);
        
        return returnToProposalButton;
    }

    private String buildExtraButtonSourceURI(String buttonFileName) {
        return lookupKualiConfigurationService().getPropertyValueAsString(KRA_EXTERNALIZABLE_IMAGES_URI_KEY) + buttonFileName;
    }

    private ConfigurationService lookupKualiConfigurationService() {
        return CoreApiServiceLocator.getKualiConfigurationService();
    }

    @Override
    public Award getBudgetParent() {
        return getAward();
    }

    @Override
    public Budget getBudgetDocumentVersion(int selectedLine) {
        return getBudgetParent().getBudgets().get(selectedLine);
    }

    public Task getParentAuthZTask(String taskName) {
        return new AwardTask(taskName,getAward());
    }

    @Override
    public boolean isComplete() {
        return true;
    }
    
    /**
     * This method specifies if this document may be edited; i.e. it's only initiated or saved
     */
    public boolean isEditable() {
        WorkflowDocument workflowDoc = getDocumentHeader().getWorkflowDocument();
        return workflowDoc.isInitiated() || workflowDoc.isSaved(); 
    }

    /**
     * Is document in Saved state?
     */
    public boolean isSaved() {
        return getDocumentHeader().getWorkflowDocument().isSaved();
    }

    @Override
    public String getDocumentBoNumber() {
        return getAward().getAwardNumber();
    }

    @Override
    public String getNamespace() {
        return Constants.MODULE_NAMESPACE_AWARD;
    }

    @Override
    public String getLeadUnitNumber() {
        return this.getAward().getLeadUnitNumber();
    }

    @Override
    public String getDocumentRoleTypeCode() {
        return RoleConstants.AWARD_ROLE_TYPE;
    }

    public String getProposalBudgetFlag() {
        return "false";
    } 
    
    public boolean getCanModify() {
        Map<String,String> qualifications =new HashMap<>();
        qualifications.put(KraAuthorizationConstants.QUALIFICATION_UNIT_NUMBER, this.getLeadUnitNumber());
        return getPermissionService().isAuthorized(
                GlobalVariables.getUserSession().getPrincipalId(), 
                KraAuthorizationConstants.KC_AWARD_NAMESPACE, 
                KraAuthorizationConstants.PERMISSION_MODIFY_AWARD, 
                qualifications);
    }
    
    protected PermissionService getPermissionService() {
        return KcServiceLocator.getService(PermissionService.class);
    }
    
    protected InstitutionalProposalService getInstitutionalProposalService() {
        return KcServiceLocator.getService(InstitutionalProposalService.class);
    }

    public AwardBudgetExt getBudgetVersionOverview() {
    	AwardBudgetExt budget = new AwardBudgetExt();
        List<AwardBudgetExt> awardBudgetDocuments = this.getBudgetDocumentVersions();
        for (AwardBudgetExt version : awardBudgetDocuments) {
                if (version != null
                        && (budget.getBudgetVersionNumber() == null || 
                            (budget.getBudgetVersionNumber() != null && version.getBudgetVersionNumber() > budget.getBudgetVersionNumber()))) {
                    budget = version;
                }
        }
        return budget;
    }

    public boolean isPlaceHolderDocument() {
        if (getDocumentHeader() != null) {
            return PLACEHOLDER_DOC_DESCRIPTION.equals(getDocumentHeader().getDocumentDescription());
        }
        return false;
    }
    
    /*
     * Find all Institutional Proposals that were marked as Funded by this Award version,
     * and revert them back to Pending.
     */
    private void revertFundedProposals() {
        Set<String> proposalsToUpdate = new HashSet<>();
        
        for (AwardFundingProposal awardFundingProposal : this.getAward().getFundingProposals()) {
            proposalsToUpdate.add(awardFundingProposal.getProposal().getProposalNumber());
        }
        //remove any funding proposals for this award
        KcServiceLocator.getService(BusinessObjectService.class).delete(getAward().getFundingProposals());
        getAward().getFundingProposals().clear();
        
        getInstitutionalProposalService().defundInstitutionalProposals(proposalsToUpdate, 
                this.getAward().getAwardNumber(), this.getAward().getSequenceNumber());
    }
    
    /*
     * Mark award comments tied to this award as invalid, so they don't show up in History
     */
    private void disableAwardComments() {
        for (AwardComment awardComment : this.getAward().getAwardComments()) {
            awardComment.disableComment();
        }
    }
    /**
     * This method is to check whether rice async routing is ok now.   
     * Close to hack.  called by holdingpageaction
     * Different document type may have different routing set up, so each document type
     * can implement its own isProcessComplete
     */
    @Override
    public boolean isProcessComplete() {
        boolean isComplete = false;
        
        if (getDocumentHeader().hasWorkflowDocument()) {
            /**
             * per KRACOEUS-5394 changing from getDocumentHeader().getWorkflowDocument().isFinal().  This way
             * we route back to the award document more appropriately from holding page.
             */
            if (getDocumentHeader().getWorkflowDocument().isFinal() 
                    || getDocumentHeader().getWorkflowDocument().isProcessed()
                    || KcServiceLocator.getService(KcWorkflowService.class).hasPendingApprovalRequests(getDocumentHeader().getWorkflowDocument())) {
                isComplete = true;
            } else if (!getAward().getSyncChanges().isEmpty() && getAward().getSyncStatuses().size() > 1) {
                //if we are doing a sync(sync changes is not empty) and we have a sync status for an award
                //other than the parent(> 1) then we are complete and should return to award actions page
                //so they can see the award status.
                isComplete = true;
            }
        }
           
        return isComplete;
    }

    public void refreshBudgetDocumentVersions() {
        budgets.clear();
    }

    public AwardService getAwardService() {
        if (awardService == null) {
            awardService = KcServiceLocator.getService(AwardService.class);
        }
        return awardService;
    }

    public void setAwardService(AwardService awardService) {
        this.awardService = awardService;
    }

    @Override
    public void populateContextQualifiers(Map<String, String> qualifiers) {
        qualifiers.put("namespaceCode", getNamespace());
        qualifiers.put("name", getRuleContextName());
    }

    @Override
    public void addFacts(Facts.Builder factsBuilder) {
        KcKrmsFactBuilderService fbService = KcServiceLocator.getService("awardFactBuilderService");
        fbService.addFacts(factsBuilder, this);
    }

    public String getRuleContextName() {
        return KcKrmsConstants.Award.AWARD_CONTEXT;
    }

    @Override
    public void populateAgendaQualifiers(Map<String, String> qualifiers) {
        qualifiers.put(KcKrmsConstants.UNIT_NUMBER, getLeadUnitNumber());
    }
    
    public String buildForwardUrl() {
        DocHandlerService researchDocumentService = KcServiceLocator.getService(DocHandlerService.class);
        String forward = researchDocumentService.getDocHandlerUrl(getDocumentNumber());
        forward = forward.replaceFirst(DEFAULT_TAB, ALTERNATE_OPEN_TAB);
        if (!forward.contains("?")) {
            forward += "?";
        }
        else {
            forward += "&";
        }
        forward += KewApiConstants.DOCUMENT_ID_PARAMETER + "=" + documentNumber;
        forward += "&" + KewApiConstants.COMMAND_PARAMETER + "=" + NotificationConstants.NOTIFICATION_DETAIL_VIEWS.DOC_SEARCH_VIEW;
        if (GlobalVariables.getUserSession().isBackdoorInUse()) {
            forward += "&" + KewApiConstants.BACKDOOR_ID_PARAMETER + "=" + GlobalVariables.getUserSession().getPrincipalName();
        }
        
        return "<a href=\"" + forward + "\"target=\"_blank\">" + documentNumber + "</a>";
    }

    @Override
    public List<? extends DocumentCustomData> getDocumentCustomData() {
        return getAward().getAwardCustomDataList();
    }

    public boolean isCanceled() {
        WorkflowDocument workflow = getDocumentHeader().getWorkflowDocument();
        return workflow.isCanceled();
    }

    public ProjectPublisher getProjectPublisher() {
        if (projectPublisher == null) {
            projectPublisher = KcServiceLocator.getService(ProjectPublisher.class);
        }

        return projectPublisher;
    }

    public void setProjectPublisher(ProjectPublisher projectPublisher) {
        this.projectPublisher = projectPublisher;
    }

    public ProjectRetrievalService getProjectRetrievalService() {
        if (projectRetrievalService == null) {
            projectRetrievalService = KcServiceLocator.getService("awardProjectRetrievalService");
        }

        return projectRetrievalService;
    }

    public void setProjectRetrievalService(ProjectRetrievalService projectRetrievalService) {
        this.projectRetrievalService = projectRetrievalService;
    }
}

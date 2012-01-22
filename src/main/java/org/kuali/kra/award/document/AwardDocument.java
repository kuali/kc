/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.award.document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.authorization.Task;
import org.kuali.kra.award.awardhierarchy.sync.service.AwardSyncService;
import org.kuali.kra.award.budget.AwardBudgetService;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.contacts.AwardPersonUnit;
import org.kuali.kra.award.document.authorization.AwardTask;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardComment;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.award.home.fundingproposal.AwardFundingProposal;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTermRecipient;
import org.kuali.kra.award.specialreview.AwardSpecialReview;
import org.kuali.kra.award.specialreview.AwardSpecialReviewExemption;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.bo.RolePersons;
import org.kuali.kra.bo.versioning.VersionStatus;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.BudgetParentDocument;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.budget.versions.BudgetVersionOverview;
import org.kuali.kra.common.permissions.Permissionable;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.infrastructure.TaskGroupName;
import org.kuali.kra.institutionalproposal.ProposalStatus;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalService;
import org.kuali.kra.service.AwardCustomAttributeService;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.COMPONENT;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.NAMESPACE;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.framework.postprocessor.DocumentRouteLevelChange;
import org.kuali.rice.kew.framework.postprocessor.DocumentRouteStatusChange;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.permission.PermissionService;
import org.kuali.rice.kns.datadictionary.HeaderNavigation;
import org.kuali.rice.kns.web.ui.ExtraButton;
import org.kuali.rice.krad.document.Copyable;
import org.kuali.rice.krad.document.SessionDocument;
import org.kuali.rice.krad.rules.rule.event.KualiDocumentEvent;
import org.kuali.rice.krad.rules.rule.event.SaveDocumentEvent;
import org.kuali.rice.krad.service.KRADServiceLocator;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;

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
public class AwardDocument extends BudgetParentDocument<Award> implements  Copyable, SessionDocument{
    private static final Log LOG = LogFactory.getLog(AwardDocument.class);
    
    public static final String PLACEHOLDER_DOC_DESCRIPTION = "*****PLACEHOLDER*****";

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1668673531338660064L;
    
    public static final String DOCUMENT_TYPE_CODE = "AWRD";
    
    private boolean canEdit;
    
    private List<Award> awardList;
    private List<BudgetDocumentVersion> actualBudgetDocumentVersions;
    private List<BudgetDocumentVersion> budgetDocumentVersions;
    
    private static final String RETURN_TO_AWARD_ALT_TEXT = "return to award";
    private static final String RETURN_TO_AWARD_METHOD_TO_CALL = "methodToCall.returnToAward";
    private static final String KRA_EXTERNALIZABLE_IMAGES_URI_KEY = "kra.externalizable.images.url";
    
    private static final String HAS_SYNC_SPLITNODE = "hasSync";
    private static final String IS_SYNC_CHILD_SPLITNODE = "isSyncChild";
    

    private transient boolean documentSaveAfterVersioning;
    private transient AwardService awardService;
    
    /**
     * Constructs a AwardDocument object
     */
    public AwardDocument(){        
        super();        
        init();
    }
    
    /**
     * 
     * This method is a convenience method for facilitating a 1:1 relationship between AwardDocument 
     * and Award to the outside world - aka a single Award field associated with AwardDocument
     * @return
     */
    public Award getAward() {
        return awardList.size() > 0 ? awardList.get(0) : new Award();
    }

    /**
     * 
     * This method is a convenience method for facilitating a 1:1 relationship between AwardDocument 
     * and Award to the outside world - aka a single Award field associated with AwardDocument
     * @param award
     */
    public void setAward(Award award) {
        awardList.set(0, award);
    }
   
    /**
     *
     * @return
     */
    public List<Award> getAwardList() {
        return awardList;
    }

    /**
     *
     * @param awardList
     */
    public void setAwardList(List<Award> awardList) {
        this.awardList = awardList;
    }
    
    public String getDocumentTypeCode() {
        return DOCUMENT_TYPE_CODE;
    }
    
    /**
     * @return
     */
    public boolean isDocumentSaveAfterVersioning() {
        return documentSaveAfterVersioning;
    }
    
    /**
     * @param documentSaveAfterVersioning
     */
    public void setDocumentSaveAfterAwardLookupEditOrVersion(boolean documentSaveAfterVersioning) {
        this.documentSaveAfterVersioning = documentSaveAfterVersioning;
    }
    
    /**
     * 
     * @see org.kuali.core.bo.PersistableBusinessObjectBase#buildListOfDeletionAwareLists()
     */
    @SuppressWarnings("unchecked")
    @Override
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
        
        List<AwardSpecialReviewExemption> specialReviewExemptions = new ArrayList<AwardSpecialReviewExemption>();
        for (AwardSpecialReview specialReview : getAward().getSpecialReviews()) {
            specialReviewExemptions.addAll(specialReview.getSpecialReviewExemptions());            
        }
        managedLists.add(specialReviewExemptions);
        managedLists.add(award.getSpecialReviews());

        List<AwardReportTerm> reportTerms = award.getAwardReportTermItems();
        List<AwardReportTermRecipient> recipients = new ArrayList<AwardReportTermRecipient>();
        for (AwardReportTerm reportTerm: reportTerms) {
            recipients.addAll(reportTerm.getAwardReportTermRecipients());
        }
        managedLists.add(recipients);
        managedLists.add(reportTerms);
        
        managedLists.add(award.getFundingProposals());

        managedLists.add(awardList);
        
        return managedLists;
    }
    
    /**
     * @see org.kuali.rice.krad.document.DocumentBase#doRouteStatusChange(org.kuali.rice.kew.framework.postprocessor.DocumentRouteStatusChange)
     */
    @Override
    public void doRouteStatusChange(DocumentRouteStatusChange statusChangeEvent) {
        super.doRouteStatusChange(statusChangeEvent);
        
        String newStatus = statusChangeEvent.getNewRouteStatus();
        String oldStatus = statusChangeEvent.getOldRouteStatus();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug(String.format("********************* Status Change: from %s to %s", statusChangeEvent.getOldRouteStatus(), newStatus));
        }
        
       // if (KewApiConstants.ROUTE_HEADER_FINAL_CD.equalsIgnoreCase(newStatus) || KewApiConstants.ROUTE_HEADER_PROCESSED_CD.equalsIgnoreCase(newStatus)) {
        if (KewApiConstants.ROUTE_HEADER_FINAL_CD.equalsIgnoreCase(newStatus)) {

            //getVersionHistoryService().createVersionHistory(getAward(), VersionStatus.ACTIVE, GlobalVariables.getUserSession().getPrincipalName());
            getAwardService().updateAwardSequenceStatus(getAward(), VersionStatus.ACTIVE);
            getVersionHistoryService().updateVersionHistoryOnRouteToFinal(getAward(), VersionStatus.ACTIVE, GlobalVariables.getUserSession().getPrincipalName());
        }
        if (newStatus.equalsIgnoreCase(KewApiConstants.ROUTE_HEADER_CANCEL_CD) || newStatus.equalsIgnoreCase(KewApiConstants.ROUTE_HEADER_DISAPPROVED_CD)) {
            revertFundedProposals();
            disableAwardComments();
            //getVersionHistoryService().createVersionHistory(getAward(), VersionStatus.CANCELED, GlobalVariables.getUserSession().getPrincipalName());
            getAwardService().updateAwardSequenceStatus(getAward(), VersionStatus.CANCELED);
            getVersionHistoryService().updateVersionHistoryOnCancel(getAward(), VersionStatus.CANCELED, GlobalVariables.getUserSession().getPrincipalName());
        }
        
        //reset Award List with updated document - in some scenarios the change in status is not reflected.
        for (Award award : awardList) {
            award.setAwardDocument(this);
        }
    }
    
    public void doRouteLevelChange(DocumentRouteLevelChange levelChangeEvent) {
        if (StringUtils.equalsIgnoreCase(levelChangeEvent.getNewNodeName(), Constants.AWARD_SYNC_VALIDATION_NODE_NAME)
                && !getAward().getSyncChanges().isEmpty()) {
            getAwardSyncService().validateHierarchyChanges(getAward());
        } else if (StringUtils.equalsIgnoreCase(levelChangeEvent.getNewNodeName(), Constants.AWARD_APPLY_SYNC_NODE_NAME)
                && !getAward().getSyncChanges().isEmpty()) {
            getAwardSyncService().applyAwardSyncChangesToHierarchy(getAward());
        }
    }
    
    /**
     * @see org.kuali.kra.document.ResearchDocumentBase#answerSplitNodeQuestion(java.lang.String)
     */
    @Override
    public boolean answerSplitNodeQuestion( String routeNodeName ) throws Exception {
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
        awardList = new ArrayList<Award>();
        awardList.add(new Award());
        budgetDocumentVersions = new ArrayList<BudgetDocumentVersion>();
        actualBudgetDocumentVersions = new ArrayList<BudgetDocumentVersion>();
        populateCustomAttributes();
    }
    
    @Override
    public void prepareForSave() {
        super.prepareForSave();
        if (ObjectUtils.isNull(this.getVersionNumber())) {
            this.setVersionNumber(new Long(0));
        }
        if (getBudgetDocumentVersions() != null) {
            updateDocumentDescriptions(getBudgetDocumentVersions());
        }
        Award award = getAward();
        if(!award.getProjectPersons().isEmpty()) {
            List<AwardPerson> aList = award.getProjectPersonsSorted();
            award.getProjectPersons().clear();
            award.getProjectPersons().addAll(aList);
            this.removeKeyPersonRoleForNoneKeyPerson();
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
    public void postProcessSave(KualiDocumentEvent event) {
        if (event instanceof SaveDocumentEvent) {
            updateFundedProposals();
        }
    }
    
    private void updateFundedProposals() {
        Set<String> modifiedProposals = new HashSet<String>();
        List<AwardFundingProposal> pendingVersions = new ArrayList<AwardFundingProposal>();
        for (AwardFundingProposal afp : getAward().getFundingProposals()) {
            InstitutionalProposal proposal = afp.getProposal();
            if (ProposalStatus.PENDING.equals(proposal.getStatusCode())) {
                modifiedProposals.add(proposal.getProposalNumber());
                pendingVersions.add(afp);
            }
        }
        if (modifiedProposals.size() > 0) {
            List<InstitutionalProposal> fundedVersions = getInstitutionalProposalService().fundInstitutionalProposals(modifiedProposals);
            getAward().getFundingProposals().removeAll(pendingVersions);
            for (InstitutionalProposal institutionalProposal : fundedVersions) {
                AwardFundingProposal awardFundingProposal = new AwardFundingProposal(getAward(), institutionalProposal);
                getAward().getFundingProposals().add(awardFundingProposal);
            }
        }
    }

    /**
     * @see org.kuali.kra.document.ResearchDocumentBase#getAllRolePersons()
     */
    @Override
    protected List<RolePersons> getAllRolePersons() {
        KraAuthorizationService awardAuthService = 
               (KraAuthorizationService) getKraAuthorizationService(); 
        return awardAuthService.getAllRolePersons(getAward());
    }
    
    @SuppressWarnings("unchecked")
    private void addAwardPersonUnitsCollection(List managedLists, Award award) {
        List<AwardPersonUnit> personUnits = new ArrayList<AwardPersonUnit>();
        for(AwardPerson p: award.getProjectPersons()) {
            personUnits.addAll(p.getUnits());
        }
        managedLists.add(personUnits);
    }
    
    protected KraAuthorizationService getKraAuthorizationService(){
        return (KraAuthorizationService) KraServiceLocator.getService(KraAuthorizationService.class);
    }
    
    /**
     * This method populates the customAttributes for this document.
     */
    @Override
    public void populateCustomAttributes() {
        AwardCustomAttributeService awardCustomAttributeService = KraServiceLocator.getService(AwardCustomAttributeService.class);
        Map<String, CustomAttributeDocument> customAttributeDocuments = awardCustomAttributeService.getDefaultAwardCustomAttributeDocuments();
        setCustomAttributeDocuments(customAttributeDocuments);
    }
    
    /**
     * @return
     */
    protected VersionHistoryService getVersionHistoryService() {
        return KraServiceLocator.getService(VersionHistoryService.class);
    }
    
    protected AwardSyncService getAwardSyncService() {
        return KraServiceLocator.getService(AwardSyncService.class);
    }

     public List<BudgetDocumentVersion> getBudgetDocumentVersions() {
        if (budgetDocumentVersions == null || budgetDocumentVersions.isEmpty()) {
            budgetDocumentVersions = KraServiceLocator.getService(AwardBudgetService.class).getAllBudgetsForAward(this);
        }
        return budgetDocumentVersions;
    }

    public void setBudgetDocumentVersions(List<BudgetDocumentVersion> budgetDocumentVersions) {
        this.budgetDocumentVersions = budgetDocumentVersions;
    }

    @Override
    public void saveBudgetFinalVersionStatus(BudgetDocument budgetDocument) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void processAfterRetrieveForBudget(BudgetDocument budgetDocument) {
        // TODO Auto-generated method stub
        
    }

    public String getDocumentKey() {
        return Permissionable.AWARD_KEY;
    }

    public String getDocumentNumberForPermission() {
        return getAward().getAwardId().toString();
    }

    public List<String> getRoleNames() {
        List<String> roleNames = new ArrayList<String>();
        return roleNames;
    }

    public Permissionable getBudgetPermissionable(){
        return new Permissionable(){

            public String getDocumentKey() {
                return Permissionable.AWARD_BUDGET_KEY;
            }

            public String getDocumentNumberForPermission() {
                return getAward().getAwardId().toString();
            }

            public List<String> getRoleNames() {
                List<String> roleNames = new ArrayList<String>();
                return roleNames;
            }
            
            public String getNamespace() {
                return Constants.MODULE_NAMESPACE_AWARD;
            }

            public String getLeadUnitNumber() {
                return getAward().getLeadUnitNumber();
            }

            public String getDocumentRoleTypeCode() {
                return RoleConstants.AWARD_ROLE_TYPE;
            }
            
            public void populateAdditionalQualifiedRoleAttributes(Map<String, String> qualifiedRoleAttributes) {
            }
        };
    }
    @Override
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

    /**
     * This method does what its name says
     * @param buttonFileName
     * @return
     */
    private String buildExtraButtonSourceURI(String buttonFileName) {
        return lookupKualiConfigurationService().getPropertyValueAsString(KRA_EXTERNALIZABLE_IMAGES_URI_KEY) + buttonFileName;
    }

    private ConfigurationService lookupKualiConfigurationService() {
        return KRADServiceLocator.getKualiConfigurationService();
    }
    
    @Override
    public List<HeaderNavigation> getBudgetHeaderNavigatorList() {
        List<HeaderNavigation> budgetHeaderList= super.getBudgetHeaderNavigatorList();
        List<HeaderNavigation> awardBudgetHeaderList = new ArrayList<HeaderNavigation>();
        for (HeaderNavigation headerNavigation : budgetHeaderList){
            if(!headerNavigation.getHeaderTabNavigateTo().equals("modularBudget")){
                awardBudgetHeaderList.add(headerNavigation);
            }
        }
        return awardBudgetHeaderList;   
    }

    @Override
    public Award getBudgetParent() {
        return getAward();
    }

    @Override
    public Task getParentAuthZTask(String taskName) {
        return new AwardTask(taskName,getAward());
    }

    @Override
    public boolean isComplete() {
        return true;
    }
    
    /**
     * This method specifies if this document may be edited; i.e. it's only initiated or saved
     * @return
     */
    public boolean isEditable() {
        WorkflowDocument workflowDoc = getDocumentHeader().getWorkflowDocument();
        return workflowDoc.isInitiated() || workflowDoc.isSaved(); 
    }

    /**
     * Is document in Saved state?
     * @return
     */
    public boolean isSaved() {
        return getDocumentHeader().getWorkflowDocument().isSaved();
    }
    
    /** {@inheritDoc} */
    @Override
    public boolean useCustomLockDescriptors() {
        return true;
    }

    /** {@inheritDoc} */
    @Override
    public String getCustomLockDescriptor(Person user) {
        String activeLockRegion = (String) GlobalVariables.getUserSession().retrieveObject(KraAuthorizationConstants.ACTIVE_LOCK_REGION);
        if (StringUtils.isNotEmpty(activeLockRegion)) {
            return this.getDocumentNumber() + "-" + activeLockRegion; 
        }

        return null;
    }

    public String getNamespace() {
        return Constants.MODULE_NAMESPACE_AWARD;
    }

    public String getLeadUnitNumber() {
        return this.getAward().getLeadUnitNumber();
    }

    public String getDocumentRoleTypeCode() {
        return RoleConstants.AWARD_ROLE_TYPE;
    }

    public String getProposalBudgetFlag() {
        return "false";
    } 
    
    public boolean getCanModify() {
        Map<String,String> permissionDetails =new HashMap<String,String>();
        permissionDetails.put("sectionName", "award");
        permissionDetails.put("documentTypeName", "AwardDocument");
        Map<String,String> qualifications =new HashMap<String,String>();
        qualifications.put(KraAuthorizationConstants.QUALIFICATION_UNIT_NUMBER, this.getLeadUnitNumber());
        return getPermissionService().isAuthorized(
                GlobalVariables.getUserSession().getPrincipalId(), 
                KraAuthorizationConstants.KC_AWARD_NAMESPACE, 
                KraAuthorizationConstants.PERMISSION_MODIFY_AWARD, 
                permissionDetails, 
                qualifications);
    }
    
    protected PermissionService getPermissionService() {
        return KraServiceLocator.getService(PermissionService.class);
    }
    
    protected IdentityService getIdentityService() {
        return KraServiceLocator.getService(IdentityService.class);
    }
    
    protected InstitutionalProposalService getInstitutionalProposalService() {
        return KraServiceLocator.getService(InstitutionalProposalService.class);
    }
    
    public BudgetVersionOverview getBudgetVersionOverview() {
        BudgetVersionOverview budget = null;
        for (BudgetDocumentVersion budgetDocumentVersion : getBudgetDocumentVersions()) {
            for (BudgetVersionOverview budgetVersionOverview : budgetDocumentVersion.getBudgetVersionOverviews()) {
                if (budgetVersionOverview != null
                        && (budget == null || (budget != null && budgetVersionOverview.getBudgetVersionNumber() > budget
                                .getBudgetVersionNumber()))) {
                    budget = budgetVersionOverview;
                }
            }
        }
        return budget;
    }

    public boolean isPlaceHolderDocument() {
        if(getDocumentHeader() != null)
            return PLACEHOLDER_DOC_DESCRIPTION.equals(getDocumentHeader().getDocumentDescription());
        return false;
    }
    
    /*
     * Find all Institutional Proposals that were marked as Funded by this Award version,
     * and revert them back to Pending.
     */
    private void revertFundedProposals() {
        Set<String> proposalsToUpdate = new HashSet<String>();
        
        for (AwardFundingProposal awardFundingProposal : this.getAward().getFundingProposals()) {
            proposalsToUpdate.add(awardFundingProposal.getProposal().getProposalNumber());
        }
        
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
     * @return
     */
    public boolean isProcessComplete() {
        boolean isComplete = false;
        
        if (getDocumentHeader().hasWorkflowDocument()) {
            if (getDocumentHeader().getWorkflowDocument().isFinal()) {
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

    public List<BudgetDocumentVersion> getActualBudgetDocumentVersions() {
        return actualBudgetDocumentVersions;
    }

    public void setActualBudgetDocumentVersions(List<BudgetDocumentVersion> actualBudgetDocumentVersions) {
        this.actualBudgetDocumentVersions = actualBudgetDocumentVersions;
    }

    public void refreshBudgetDocumentVersions() {
        this.refreshReferenceObject("actualBudgetDocumentVersions");
        budgetDocumentVersions.clear();
    }

    public AwardService getAwardService() {
        if (awardService == null) {
            awardService = KraServiceLocator.getService(AwardService.class);
        }
        return awardService;
    }

    public void setAwardService(AwardService awardService) {
        this.awardService = awardService;
    }

}
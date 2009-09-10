/*
 * Copyright 2006-2008 The Kuali Foundation
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

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.authorization.Task;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.contacts.AwardPersonUnit;
import org.kuali.kra.award.document.authorization.AwardTask;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTermRecipient;
import org.kuali.kra.award.specialreview.AwardSpecialReview;
import org.kuali.kra.award.specialreview.AwardSpecialReviewExemption;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.bo.RolePersons;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.bo.versioning.VersionStatus;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.BudgetParentDocument;
import org.kuali.kra.budget.personnel.PersonRolodex;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.common.permissions.Permissionable;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskGroupName;
import org.kuali.kra.proposaldevelopment.bo.ActivityType;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonRole;
import org.kuali.kra.service.AwardCustomAttributeService;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.rice.kew.dto.DocumentRouteStatusChangeDTO;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.datadictionary.HeaderNavigation;
import org.kuali.rice.kns.document.Copyable;
import org.kuali.rice.kns.document.SessionDocument;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.web.ui.ExtraButton;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;

/**
 * 
 * This class represents the Award Document Object.
 * AwardDocument has a 1:1 relationship with Award Business Object.
 * We have declared a list of Award BOs in the AwardDocument at the same time to
 * get around the OJB anonymous keys issue of primary keys of different data types.
 * Also we have provided convenient getter and setter methods so that to the outside world;
 * Award and AwardDocument can have a 1:1 relationship.
 */
public class AwardDocument extends BudgetParentDocument implements  Copyable, SessionDocument{
    private static final Log LOG = LogFactory.getLog(AwardDocument.class);

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1668673531338660064L;
    
    public static final String DOCUMENT_TYPE_CODE = "AWRD";
    
    private List<Award> awardList;
    private List<BudgetDocumentVersion> budgetDocumentVersions;
    private static final String RETURN_TO_AWARD_ALT_TEXT = "return to award";
    private static final String RETURN_TO_AWARD_METHOD_TO_CALL = "methodToCall.returnToAward";
    private static final String KRA_EXTERNALIZABLE_IMAGES_URI_KEY = "kra.externalizable.images.url";
    

    private transient boolean documentSaveAfterVersioning;
    
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
        return awardList.get(0);
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
        List <AwardSpecialReviewExemption> awardSpecialReviewExemptions = new ArrayList<AwardSpecialReviewExemption>();
        
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
                
        for (AwardSpecialReview awardSpecialReview : getAward().getSpecialReviews()) {
            awardSpecialReviewExemptions.addAll(awardSpecialReview.getSpecialReviewExemptions());            
        }
        
        managedLists.add(awardSpecialReviewExemptions);
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
     * @see org.kuali.rice.kns.document.DocumentBase#doRouteStatusChange(org.kuali.rice.kew.dto.DocumentRouteStatusChangeDTO)
     */
    @Override
    public void doRouteStatusChange(DocumentRouteStatusChangeDTO statusChangeEvent) {
        super.doRouteStatusChange(statusChangeEvent);
        
        String newStatus = statusChangeEvent.getNewRouteStatus();
        
        if(LOG.isDebugEnabled()) {
            LOG.debug(String.format("********************* Status Change: from %s to %s", statusChangeEvent.getOldRouteStatus(), newStatus));
        }
        
        if(KEWConstants.ROUTE_HEADER_FINAL_CD.equalsIgnoreCase(newStatus) || KEWConstants.ROUTE_HEADER_PROCESSED_CD.equalsIgnoreCase(newStatus)) {
            getVersionHistoryService().createVersionHistory(getAward(), VersionStatus.ACTIVE, GlobalVariables.getUserSession().getPrincipalName());
        }
        if(newStatus.equalsIgnoreCase(KEWConstants.ROUTE_HEADER_CANCEL_CD) || newStatus.equalsIgnoreCase(KEWConstants.ROUTE_HEADER_DISAPPROVED_CD)) {
            getVersionHistoryService().createVersionHistory(getAward(), VersionStatus.CANCELED, GlobalVariables.getUserSession().getPrincipalName());
        }
    }
    
    protected void init() {
        awardList = new ArrayList<Award>();
        awardList.add(new Award());
        budgetDocumentVersions = new ArrayList<BudgetDocumentVersion>();
        populateCustomAttributes();
    }
    
    @Override
    public void prepareForSave() {
        super.prepareForSave();

        if (getBudgetDocumentVersions() != null) {
            updateDocumentDescriptions(getBudgetDocumentVersions());
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

    @Override
    public ActivityType getActivityType() {
        return getAward().getActivityType();
    }

    @Override
    public String getActivityTypeCode() {
        return getAward().getActivityTypeCode().toString();
    }

    @Override
    public String getBudgetStatus() {
        // hard coded as completed
        return "2";
    }

    @Override
    public Map<String, String> getNihDescription() {
        return new HashMap<String, String>();
    }

    @Override
    public Task getParentAuthZTask(String taskName) {
        return new AwardTask(taskName,getAward());
    }

    @Override
    public List getPersonRolodexList() {
        return getAward().getProjectPersons();
    }

    @Override
    public PersonRolodex getProposalEmployee(String personId) {
        return getPerson(personId,true);
   }

    /**
     * This method...
     * @param personId
     */
    private PersonRolodex getPerson(String personId, boolean personFindFlag) {
        List<AwardPerson> awardPersons = getAward().getProjectPersons();
        for (AwardPerson awardPerson : awardPersons) {
            if(awardPerson.getPersonId().equals(personId)){
                if(personFindFlag && awardPerson.isEmployee()){
                    return awardPerson;
                }else{
                    return awardPerson;
                }
            }
        }
        return null;
    }

    @Override
    public ProposalPersonRole getProposalEmployeeRole(String personId) {
        return new ProposalPersonRole();//((AwardPerson)getProposalEmployee(personId)).getContactRole();
    }

    @Override
    public PersonRolodex getProposalNonEmployee(Integer rolodexId) {
        return getPerson(rolodexId.toString(), false);
    }

    @Override
    public ProposalPersonRole getProposalNonEmployeeRole(Integer rolodexId) {
        return new ProposalPersonRole();
    }

    @Override
    public Date getRequestedEndDateInitial() {
        // FIXME get the obligated start date
        return getAward().getProjectEndDate();
    }

    @Override
    public Date getRequestedStartDateInitial() {
        // FIXME get the obligated end date
        return getAward().getBeginDate();
    }

    @Override
    public Unit getUnit() {
        return getAward().getLeadUnit();
    }

    @Override
    public String getUnitNumber() {
        return getAward().getLeadUnit()==null?"":getAward().getLeadUnit().getUnitNumber();
    }

    @Override
    public boolean isComplete() {
        return true;
    }

    @Override
    public boolean isNih() {
        return false;
    }
    
    /**
     * This method specifies if this document may be edited; i.e. it's only initiated or saved
     * @return
     */
    public boolean isEditable() {
        KualiWorkflowDocument workflowDoc = getDocumentHeader().getWorkflowDocument();
        return workflowDoc.stateIsInitiated() || workflowDoc.stateIsSaved(); 
    }

    @Override
    public void setBudgetStatus(String budgetStatus) {
    }

    public List<BudgetDocumentVersion> getBudgetDocumentVersions() {
        return this.budgetDocumentVersions;
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
        return lookupKualiConfigurationService().getPropertyString(KRA_EXTERNALIZABLE_IMAGES_URI_KEY) + buttonFileName;
    }

    private KualiConfigurationService lookupKualiConfigurationService() {
        return KraServiceLocator.getService(KualiConfigurationService.class);
    }
    
    @Override
    public List<HeaderNavigation> getBudgetHeaderNavigatorList() {
        List<HeaderNavigation> awardHeaderList = super.getBudgetHeaderNavigatorList();
        for (HeaderNavigation headerNavigation : awardHeaderList) {
            if(headerNavigation.getHeaderTabNavigateTo().equals("modularBudget")){
                awardHeaderList.remove(headerNavigation);
            }
        }
        return awardHeaderList;
    }
    
}

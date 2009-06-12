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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.contacts.AwardPersonUnit;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTermRecipient;
import org.kuali.kra.award.specialreview.AwardSpecialReview;
import org.kuali.kra.award.specialreview.AwardSpecialReviewExemption;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.bo.RolePersons;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.AwardCustomAttributeService;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.rice.kns.document.Copyable;
import org.kuali.rice.kns.document.SessionDocument;

/**
 * 
 * This class represents the Award Document Object.
 * AwardDocument has a 1:1 relationship with Award Business Object.
 * We have declared a list of Award BOs in the AwardDocument at the same time to
 * get around the OJB anonymous keys issue of primary keys of different data types.
 * Also we have provided convenient getter and setter methods so that to the outside world;
 * Award and AwardDocument can have a 1:1 relationship.
 */
public class AwardDocument extends ResearchDocumentBase implements  Copyable, SessionDocument{
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1668673531338660064L;
    
    private static final String DOCUMENT_TYPE_CODE = "AWRD";
    
    private List<Award> awardList;
    
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
        managedLists.add(award.getAwardReportTermItems());
        managedLists.add(getAward().getAwardSponsorTerms());
        managedLists.add(award.getPaymentScheduleItems());
        managedLists.add(award.getAwardTransferringSponsors());
        managedLists.add(award.getAwardDirectFandADistributions());
        managedLists.add(award.getAwardApprovedSubawards());
                
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

        managedLists.add(awardList);
        
        return managedLists;
    }
    
    protected void init() {
        awardList = new ArrayList<Award>();
        awardList.add(new Award());
        populateCustomAttributes();
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
}

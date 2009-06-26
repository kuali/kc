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
package org.kuali.kra.award.paymentreports.awardreports;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.contacts.AwardSponsorContact;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.service.KeyValuesService;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.service.KualiRuleService;

/**
 * This class supports the AwardForm class
 */
public class AwardReportsBean implements Serializable {    
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -7425300585057908055L;
    private List<AwardReportTerm> newAwardReportTerms;
    private List<AwardReportTermRecipient> newAwardReportTermRecipients;    
    private KualiRuleService ruleService;
    private AwardForm form;
    
    /**
     * 
     * Constructs a AwardReportsBean.java.
     * @param form
     */
    public AwardReportsBean(AwardForm form) {
        this.form = form;
    }
    
    /**
     * 
     * Constructs a AwardReportsBean.java.
     */
    public AwardReportsBean() {
        
    }
    
    /**
     * This method is called when adding a new Award Report Term Item
     * @param formHelper
     * @return
     */
    public boolean addAwardReportTermItem(String reportClassCode, int index) {        
        AwardReportTerm newAwardReportTerm = getNewAwardReportTerms().get(index);
        newAwardReportTerm.setReportClassCode(reportClassCode);
        AddAwardReportTermRuleEvent event = generateAddAwardReportTermEvent(newAwardReportTerm, reportClassCode, index);
        boolean success = getRuleService().applyRules(event);
        if(success){
            getAward().add(newAwardReportTerm);
            init(index);
        }
        return success;
    }
    
    /**
     * This method is called when adding a new Award Report Term Recipient Item
     * @param formHelper
     * @return
     */
    public boolean addAwardReportTermRecipientItem(int index) {
        AwardReportTermRecipient newAwardReportTermRecipient = getNewAwardReportTermRecipients().get(index);
        
        if(newAwardReportTermRecipient.getContactId()!=null){
            populateContactTypeAndRolodex(newAwardReportTermRecipient);
        }else if(newAwardReportTermRecipient.getRolodexId()!=null){
            newAwardReportTermRecipient.setContactTypeCode(getKualiConfigurationService().getParameter(Constants
                    .PARAMETER_MODULE_AWARD,Constants.PARAMETER_COMPONENT_DOCUMENT
                    ,KeyConstants.CONTACT_TYPE_OTHER).getParameterValue());            
        }
        
        AddAwardReportTermRecipientRuleEvent event = generateAddAwardReportTermRecipientEvent(index);
        boolean success = getRuleService().applyRules(event);
        if(success){            
            getAward().getAwardReportTermItems().get(index).getAwardReportTermRecipients().add(newAwardReportTermRecipient);
            initRecipient(index);
        }
        return success;
    }

    /**
     * 
     * This method deletes a selected Award Report Term Item
     * 
     * @param deletedItemIndex
     */
    public void deleteAwardReportTermItem(int deletedItemIndex) {
        List<AwardReportTerm> items = getAward().getAwardReportTermItems();
        if(deletedItemIndex >= 0 && deletedItemIndex < items.size()) {
            items.remove(deletedItemIndex);
        }        
    }
    
    /**
     * 
     * This method deletes a selected Award Report Term Recipient Item
     * 
     * @param awardReportTermIndex
     * @param deletedItemIndex
     */
    public void deleteAwardReportTermRecipientItem(int awardReportTermIndex, int deletedItemIndex) {
        List<AwardReportTermRecipient> items = getAward().getAwardReportTermItems().get(awardReportTermIndex).getAwardReportTermRecipients();
        if(deletedItemIndex >= 0 && deletedItemIndex < items.size()) {
            items.remove(deletedItemIndex);
        }        
    }
        
    /**
     * @return
     */
    public Award getAward() {
        return form.getAwardDocument().getAward();
    }

    /**
     * 
     * @return
     */
    public AwardDocument getAwardDocument() {
        return form.getAwardDocument();
    }
    
    /**
     * This method is for initializing the new <code>AwardReportTerm</code> object after the add operation.
     */
    public void init(int index) {
        this.getNewAwardReportTerms().set(index, new AwardReportTerm());
        this.getNewAwardReportTermRecipients().add(new AwardReportTermRecipient());
    }
    
    /**
     * 
     * This method is for initializing the new <code>AwardReportTermRecipient</code> object after the add operation.
     * @param index
     */
    public void initRecipient(int index) {
        getNewAwardReportTermRecipients().set(index, new AwardReportTermRecipient());        
    }
    
    /**
     * 
     * This method retrieves the KualiRuleService
     * @return
     */
    protected KualiRuleService getRuleService() {
        if(ruleService == null) {
            ruleService = (KualiRuleService) KraServiceLocator.getService(KualiRuleService.class); 
        }
        return ruleService;
    }
    
    protected void setRuleService(KualiRuleService ruleService) {
        this.ruleService = ruleService;
    }
    
    /**
     * 
     * This method generates the <code>AddAwardReportTermRuleEvent</code> event.
     * 
     * @param newAwardReportTerm
     * @param reportClassCode
     * @param index
     * @return
     */
    AddAwardReportTermRuleEvent generateAddAwardReportTermEvent(AwardReportTerm newAwardReportTerm, String reportClassCode, int index) {
        AddAwardReportTermRuleEvent event = new AddAwardReportTermRuleEvent(
                                                            "awardReportsBean.newAwardReportTerms[" + index + "]",
                                                            getAwardDocument(),
                                                            getAward(),
                                                            newAwardReportTerm);
        return event;
    }
    
    /**
     * 
     * This method generates the <code>AddAwardReportTermRecipientRuleEvent</code> event.
     * 
     * @param index
     * @return
     */
    AddAwardReportTermRecipientRuleEvent generateAddAwardReportTermRecipientEvent(int index) {        
        AddAwardReportTermRecipientRuleEvent event = new AddAwardReportTermRecipientRuleEvent(
                                                            "awardReportsBean.newAwardReportTermRecipients[" + index + "]",
                                                            getAwardDocument(),
                                                            getAward(),
                                                            getAward().getAwardReportTermItems().get(index),
                                                            getNewAwardReportTermRecipients().get(index));
        return event;
    }

    /**
     * Gets the newAwardReportTerms attribute. 
     * @return Returns the newAwardReportTerms.
     */
    public List<AwardReportTerm> getNewAwardReportTerms() {
        return newAwardReportTerms;
    }

    /**
     * Sets the newAwardReportTerms attribute value.
     * @param newAwardReportTerms The newAwardReportTerms to set.
     */
    public void setNewAwardReportTerms(List<AwardReportTerm> newAwardReportTerms) {
        this.newAwardReportTerms = newAwardReportTerms;
    }

    /**
     * Gets the newAwardReportTermRecipients attribute. 
     * @return Returns the newAwardReportTermRecipients.
     */
    public List<AwardReportTermRecipient> getNewAwardReportTermRecipients() {
        return newAwardReportTermRecipients;
    }

    /**
     * Sets the newAwardReportTermRecipients attribute value.
     * @param newAwardReportTermRecipients The newAwardReportTermRecipients to set.
     */
    public void setNewAwardReportTermRecipients(List<AwardReportTermRecipient> newAwardReportTermRecipients) {
        this.newAwardReportTermRecipients = newAwardReportTermRecipients;
    }
    
    /**
     * This method retrieves the contact type code and rolodex id from the selected sponsor contact
     * 
     * @param newAwardReportTermRecipient
     */
    void populateContactTypeAndRolodex(AwardReportTermRecipient newAwardReportTermRecipient){
        
        Collection<AwardSponsorContact> awardSponsorContacts = getSponsorContactsUsingKeyValuesService(newAwardReportTermRecipient.getContactId());
        
        if(awardSponsorContacts.size()>1){
            throw new MultipleSponsorContactsException(awardSponsorContacts.size());
        }
        
        for(AwardSponsorContact awardSponsorContact: awardSponsorContacts){
            newAwardReportTermRecipient.setRolodexId(awardSponsorContact.getRolodexId());
            newAwardReportTermRecipient.setContactTypeCode(awardSponsorContact.getContactRoleCode());
            newAwardReportTermRecipient.refreshReferenceObject("contactType");
        }        
    }
    
    /**
     * 
     * This method retrieves all the sponsor contact for the particular contact id provided.
     * 
     * @param contactId
     * @return
     */
    @SuppressWarnings("all")
    Collection<AwardSponsorContact> getSponsorContactsUsingKeyValuesService(Long contactId){        
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("awardContactId", contactId);
        return getKeyValuesService().findMatching(AwardSponsorContact.class, map);
    }
    
    /**
     * 
     * This is a wrapper method for the retrieval of KualiConfigurationService.
     * 
     * @return
     */
    protected KualiConfigurationService getKualiConfigurationService(){
        return KraServiceLocator.getService(KualiConfigurationService.class);
    }
    
    /**
     * 
     * This is a wrapper method for the retrieval of KeyValuesService.
     * 
     * @return
     */
    protected KeyValuesService getKeyValuesService(){
        return KraServiceLocator.getService(KeyValuesService.class);
    }
    
}

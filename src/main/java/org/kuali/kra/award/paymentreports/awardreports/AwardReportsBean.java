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
import java.util.List;

import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.web.struts.form.AwardForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.service.KualiRuleService;

/**
 * This class supports the AwardForm class
 */
public class AwardReportsBean implements Serializable {    
    
    private static final int HARDCODED_ROLODEX_ID = 20083;
    
    private List<AwardReportTerm> newAwardReportTerms;
    private List<AwardReportTermRecipient> newAwardReportTermRecipients;    
    private KualiRuleService ruleService;
    private AwardForm form;
    
    /**
     * Constructs a PaymentScheduleBean
     * @param parent
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
     * This method is called when adding a new payment schedule item
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
     * This method is called when adding a new payment schedule item
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
            getAward().getAwardReportTermItems().get(index).getAwardReportTermRecipients().add(getNewAwardReportTermRecipients().get(index));
            initRecipient(index);
        }
        return success;
    }

    /**
     * This method delets a selected payment schedule item
     * @param formHelper
     * @param deletedItemIndex
     */
    public void deleteAwardReportTermItem(int deletedItemIndex) {
        List<AwardReportTerm> items = getAward().getAwardReportTermItems();
        if(deletedItemIndex >= 0 && deletedItemIndex < items.size()) {
            items.remove(deletedItemIndex);
        }        
    }
    
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
     * @return
     */
    public AwardDocument getAwardDocument() {
        return form.getAwardDocument();
    }
    
    /**
     * Initialize subform
     */
    public void init(int index) {
        this.getNewAwardReportTerms().set(index, new AwardReportTerm());
        this.getNewAwardReportTermRecipients().add(new AwardReportTermRecipient());
    }
    
    public void initRecipient(int index) {
        getNewAwardReportTermRecipients().set(index, new AwardReportTermRecipient());        
    }
    
    protected KualiRuleService getRuleService() {
        if(ruleService == null) {
            ruleService = (KualiRuleService) KraServiceLocator.getService("kualiRuleService"); 
        }
        return ruleService;
    }
    
    protected void setRuleService(KualiRuleService ruleService) {
        this.ruleService = ruleService;
    }
    
    AddAwardReportTermRuleEvent generateAddAwardReportTermEvent(AwardReportTerm newAwardReportTerm, String reportClassCode, int index) {
        AddAwardReportTermRuleEvent event = new AddAwardReportTermRuleEvent(
                                                            "awardReportsBean.newAwardReportTerms[" + index + "]",
                                                            getAwardDocument(),
                                                            getAward(),
                                                            newAwardReportTerm);
        return event;
    }
    
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
     * Currently this method sets the rolodex id to a constant value 
     * and sets the contactTypeCode to 1 of the 6 values listed in the switch case.
     * These values correspond to the hard coded values finder - ContactTypeValuesFinder.
     * 
     * @param newAwardReportTermRecipient
     */
    //TODO: this method should be refactored once the contact functionality is complete
    void populateContactTypeAndRolodex(
            AwardReportTermRecipient newAwardReportTermRecipient){
        
        newAwardReportTermRecipient.setRolodexId(HARDCODED_ROLODEX_ID);
        switch(newAwardReportTermRecipient.getContactId().intValue()){
            case 1:
                newAwardReportTermRecipient.setContactTypeCode("6");
                break;
            case 2:
                newAwardReportTermRecipient.setContactTypeCode("5");
                break;
            case 3:
                newAwardReportTermRecipient.setContactTypeCode("4");
                break;
            case 4:
                newAwardReportTermRecipient.setContactTypeCode("3");
                break;
            case 5:
                newAwardReportTermRecipient.setContactTypeCode("2");
                break;
            case 6:
                newAwardReportTermRecipient.setContactTypeCode("9");
                break;
            default:                
                break;
        }
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
    
}

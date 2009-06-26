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
package org.kuali.kra.award.paymentreports.paymentschedule;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.AwardScheduleGenerationService;
import org.kuali.rice.kns.service.KualiRuleService;

/**
 * This class supports the AwardForm class
 */
public class PaymentScheduleBean implements Serializable {    
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -5513993757805685581L;
    private AwardPaymentSchedule newAwardPaymentSchedule;
    private KualiRuleService ruleService;
    private AwardForm form;
    
    /**
     * Constructs a PaymentScheduleBean
     * @param parent
     */
    public PaymentScheduleBean(AwardForm form) {
        this.form = form;
    }
    
    /**
     * This method is called when adding a new payment schedule item
     * @param formHelper
     * @return
     */
    public boolean addPaymentScheduleItem() {
        AddAwardPaymentScheduleRuleEvent event = generateAddEvent();
        boolean success = getRuleService().applyRules(event);
        if(success){
            getAward().add(getNewAwardPaymentSchedule());
            init();
        }
        return success;
    }

    /**
     * This method delets a selected payment schedule item
     * @param formHelper
     * @param deletedItemIndex
     */
    public void deletePaymentScheduleItem(int deletedItemIndex) {
        List<AwardPaymentSchedule> items = getAward().getPaymentScheduleItems();
        if(deletedItemIndex >= 0 && deletedItemIndex < items.size()) {
            items.remove(deletedItemIndex);
        }        
    }
    
    /**
     * 
     * This method generates the payment schedules by calling the <code>AwardScheduleGenerationService</code>
     * @throws ParseException
     */
    public void generatePaymentSchedules() throws ParseException{
        List<Date> dates = new ArrayList<Date>();
        
        dates = getAwardScheduleGenerationService().generateSchedules(getAward(), getAward().getAwardReportTermItems(), false);
        
        for(Date date: dates){
            newAwardPaymentSchedule = new AwardPaymentSchedule();
            java.sql.Date sqldate = new java.sql.Date(date.getTime());
            newAwardPaymentSchedule.setDueDate(sqldate);            
            getAward().add(newAwardPaymentSchedule);
        }
        init();
     
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
     * @return
     */
    public Object getData() {
        return getNewAwardPaymentSchedule();
    }
    
    /**
     * Initialize subform
     */
    public void init() {
        newAwardPaymentSchedule = new AwardPaymentSchedule(); 
    }
    
    /**
     * 
     * This is a helper method for the retrieval of KualiRuleService
     * @return
     */
    protected KualiRuleService getRuleService() {
        if(ruleService == null) {
            ruleService = (KualiRuleService) KraServiceLocator.getService(KualiRuleService.class); 
        }
        return ruleService;
    }
    
    /**
     * 
     * @param ruleService
     */
    protected void setRuleService(KualiRuleService ruleService) {
        this.ruleService = ruleService;
    }
    
    /**
     * 
     * @return
     */
    AddAwardPaymentScheduleRuleEvent generateAddEvent() {        
        AddAwardPaymentScheduleRuleEvent event = new AddAwardPaymentScheduleRuleEvent(
                                                            "paymentScheduleBean.newAwardPaymentSchedule",
                                                            getAwardDocument(),
                                                            getAward(),
                                                            getNewAwardPaymentSchedule());
        return event;
    }

    /**
     * Gets the newAwardPaymentSchedule attribute. 
     * @return Returns the newAwardPaymentSchedule.
     */
    public AwardPaymentSchedule getNewAwardPaymentSchedule() {
        return newAwardPaymentSchedule;
    }

    /**
     * Sets the newAwardPaymentSchedule attribute value.
     * @param newAwardPaymentSchedule The newAwardPaymentSchedule to set.
     */
    public void setNewAwardPaymentSchedule(AwardPaymentSchedule newAwardPaymentSchedule) {
        this.newAwardPaymentSchedule = newAwardPaymentSchedule;
    }
    
    /**
     * 
     * This is a helper method to retrieve the AwardScheduleGenerationService.
     * @return
     */
    protected AwardScheduleGenerationService getAwardScheduleGenerationService(){
        return KraServiceLocator.getService(AwardScheduleGenerationService.class);
    }
}

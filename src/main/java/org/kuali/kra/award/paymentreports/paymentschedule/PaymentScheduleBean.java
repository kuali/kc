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

import java.util.List;

import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.web.struts.form.AwardForm;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.service.KualiRuleService;

/**
 * This class supports the AwardForm class
 */
public class PaymentScheduleBean {    
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
    
    protected KualiRuleService getRuleService() {
        if(ruleService == null) {
            ruleService = (KualiRuleService) KraServiceLocator.getService("kualiRuleService"); 
        }
        return ruleService;
    }
    
    protected void setRuleService(KualiRuleService ruleService) {
        this.ruleService = ruleService;
    }
    
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
}

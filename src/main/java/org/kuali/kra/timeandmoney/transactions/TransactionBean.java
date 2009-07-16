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
package org.kuali.kra.timeandmoney.transactions;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.kuali.kra.award.paymentreports.paymentschedule.AwardPaymentSchedule;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.timeandmoney.TimeAndMoneyForm;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.rice.kns.service.KualiRuleService;

/**
 * This class supports the TimeAndMoneyForm class
 */
public class TransactionBean implements Serializable {    
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -5513993757805685581L;
    private PendingTransaction newPendingTransaction;
    private KualiRuleService ruleService;
    private TimeAndMoneyForm form;
    
    /**
     * Constructs a PaymentScheduleBean
     * @param parent
     */
    public TransactionBean(TimeAndMoneyForm form) {
        this.form = form;
    }
    
    /**
     * This method is called when adding a new Pending Transaction item
     * @param formHelper
     * @return
     */
    public boolean addPendingTransactionItem() {
        AddTransactionRuleEvent event = generateAddEvent();
        boolean success = getRuleService().applyRules(event);
        if(success){
            getTimeAndMoneyDocument().add(getNewPendingTransaction());
            init();
        }
        return success;
    }

    /**
     * This method deletes a selected Pending Transaction item
     * @param formHelper
     * @param deletedItemIndex
     */
    public void deletePendingTransactionItem(int deletedItemIndex) {
        List<PendingTransaction> items = getTimeAndMoneyDocument().getPendingTransactions();
        if(deletedItemIndex >= 0 && deletedItemIndex < items.size()) {
            items.remove(deletedItemIndex);
        }        
    }
    
    /**
     * @return
     */
    public TimeAndMoneyDocument getTimeAndMoneyDocument() {
        return form.getTimeAndMoneyDocument();
    }
    
    /**
     * @return
     */
    public Object getData() {
        return getNewPendingTransaction();
    }
    
    /**
     * Initialize subform
     */
    public void init() {
        newPendingTransaction = new PendingTransaction(); 
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
    AddTransactionRuleEvent generateAddEvent() {        
        AddTransactionRuleEvent event = new AddTransactionRuleEvent(
                                                            "transactionBean.newPendingTransaction",
                                                            getTimeAndMoneyDocument(),                                                            
                                                            getNewPendingTransaction());
        return event;
    }

    /**
     * Gets the newPendingTransaction attribute. 
     * @return Returns the newPendingTransaction.
     */
    public PendingTransaction getNewPendingTransaction() {
        return newPendingTransaction;
    }

    /**
     * Sets the newPendingTransaction attribute value.
     * @param newPendingTransaction The newPendingTransaction to set.
     */
    public void setNewPendingTransaction(PendingTransaction newPendingTransaction) {
        this.newPendingTransaction = newPendingTransaction;
    }
}

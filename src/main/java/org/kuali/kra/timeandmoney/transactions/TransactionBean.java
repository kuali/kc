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
package org.kuali.kra.timeandmoney.transactions;

import java.io.Serializable;
import java.util.List;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.timeandmoney.TimeAndMoneyForm;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * This class supports the TimeAndMoneyForm class
 */
public class TransactionBean implements Serializable {    
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -5513993757805685581L;
    private PendingTransaction newPendingTransaction;
    private AwardAmountTransaction newAwardAmountTransaction;
    private transient KualiRuleService ruleService;
    private TimeAndMoneyForm form;
    
    /**
     * Constructs a PaymentScheduleBean
     * @param parent
     */
    public TransactionBean(TimeAndMoneyForm form) {
        this.form = form;
        init();
    }
    
    /**
     * This method is called when adding a new Pending Transaction item
     * @param formHelper
     * @return
     */
    public boolean addPendingTransactionItem() {
        sumDirectIndirectIfViewEnabled();
        AddTransactionRuleEvent event = generateAddEvent();
        updateDocumentFromSession(event.getTimeAndMoneyDocument());
        boolean success = getRuleService().applyRules(event);
        if(success){            
            getTimeAndMoneyDocument().add(getNewPendingTransaction());
            init();
        }
        return success;
    }
    
    /**
     * This method sums direct/indirect costs and adds them to the total of pending transaction for processing.  Total does not need to be displayed
     * in Pending transactions if system parameter for direct/indirect view is enabled.
     */
    private void sumDirectIndirectIfViewEnabled() {
        if(newPendingTransaction.getAnticipatedDirectAmount().isPositive() ||
                newPendingTransaction.getAnticipatedIndirectAmount().isPositive() ||
                    newPendingTransaction.getObligatedDirectAmount().isPositive() ||
                        newPendingTransaction.getObligatedIndirectAmount().isPositive()) {
            newPendingTransaction.setAnticipatedAmount(newPendingTransaction.getAnticipatedDirectAmount().add(newPendingTransaction.getAnticipatedIndirectAmount()));
            newPendingTransaction.setObligatedAmount(newPendingTransaction.getObligatedDirectAmount().add(newPendingTransaction.getObligatedIndirectAmount()));
        }
    }
    
    /*
     * This method...
     * @param doc
     */
    private void updateDocumentFromSession(TimeAndMoneyDocument doc) {
        if(doc.getAwardHierarchyNodes()==null || doc.getAwardHierarchyNodes().size()==0){
            if(GlobalVariables.getUserSession().retrieveObject(GlobalVariables.getUserSession().getKualiSessionId()+Constants.TIME_AND_MONEY_DOCUMENT_STRING_FOR_SESSION)!=null){
                TimeAndMoneyDocument document = (TimeAndMoneyDocument)GlobalVariables.getUserSession().retrieveObject(GlobalVariables.getUserSession().getKualiSessionId()+Constants.TIME_AND_MONEY_DOCUMENT_STRING_FOR_SESSION);
                doc.setAwardHierarchyItems(document.getAwardHierarchyItems());
                doc.setAwardHierarchyNodes(document.getAwardHierarchyNodes());
            }else {
                throw new RuntimeException("Can't Retrieve Time And Money Document from Session");
            }
        }
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

    /**
     * Gets the newAwardAmountTransaction attribute. 
     * @return Returns the newAwardAmountTransaction.
     */
    public AwardAmountTransaction getNewAwardAmountTransaction() {
        return newAwardAmountTransaction;
    }

    /**
     * Sets the newAwardAmountTransaction attribute value.
     * @param newAwardAmountTransaction The newAwardAmountTransaction to set.
     */
    public void setNewAwardAmountTransaction(AwardAmountTransaction newAwardAmountTransaction) {
        this.newAwardAmountTransaction = newAwardAmountTransaction;
    }
}

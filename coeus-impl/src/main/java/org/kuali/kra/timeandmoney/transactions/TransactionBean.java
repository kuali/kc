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
package org.kuali.kra.timeandmoney.transactions;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.timeandmoney.TimeAndMoneyForm;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.io.Serializable;
import java.util.List;

/**
 * This class supports the TimeAndMoneyForm class
 */
public class TransactionBean implements Serializable {    
    

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
        if (StringUtils.equals("1", form.getDirectIndirectViewEnabled())) {
            newPendingTransaction.setAnticipatedAmount(newPendingTransaction.getAnticipatedDirectAmount().add(newPendingTransaction.getAnticipatedIndirectAmount()));
            newPendingTransaction.setObligatedAmount(newPendingTransaction.getObligatedDirectAmount().add(newPendingTransaction.getObligatedIndirectAmount()));
        }
    }

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
    

    public TimeAndMoneyDocument getTimeAndMoneyDocument() {
        return form.getTimeAndMoneyDocument();
    }
    

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
            ruleService = (KualiRuleService) KcServiceLocator.getService(KualiRuleService.class);
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

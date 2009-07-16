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

import java.util.List;

import org.codehaus.plexus.util.StringUtils;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * The AwardPaymentScheduleRuleImpl
 */
public class TransactionRuleImpl extends ResearchDocumentRuleBase implements TransactionRule {
    
    private static final String SOURCE_AWARD_PROPERTY = "sourceAwardNumber";
    private static final String DESTINATION_AWARD_PROPERTY = "destinationAwardNumber";
    private static final String SOURCE_AWARD_ERROR_PARM = "Source Award (Source Award)";
    private static final String DESTINATION_AWARD_ERROR_PARM = "Destination Award (Destination Award)";
    
    /**
     * 
     * @see org.kuali.kra.timeandmoney.transactions.TransactionRule#processPendingTransactionBusinessRules(
     *  org.kuali.kra.timeandmoney.transactions.TransactionRuleEvent)
     */
    public boolean processPendingTransactionBusinessRules(TransactionRuleEvent event) {
        return processCommonValidations(event);        
    }
    /**
     * 
     * This method processes new Pending Transaction rules
     * 
     * @param event
     * @return
     */
    public boolean processAddPendingTransactionBusinessRules(AddTransactionRuleEvent event) {
        return areRequiredFieldsComplete(event.getPendingTransactionItemForValidation()) && processCommonValidations(event);        
    }
    
    private boolean processCommonValidations(TransactionRuleEvent event) {
        PendingTransaction pendingTransactionItem = event.getPendingTransactionItemForValidation();
        List<PendingTransaction> items = event.getTimeAndMoneyDocument().getPendingTransactions();
        return isUnique(items, pendingTransactionItem) && sourceAndDestinationAwardsAreDifferent(pendingTransactionItem);
    }
    
    boolean sourceAndDestinationAwardsAreDifferent(PendingTransaction pendingTransactionItem){
        boolean srcAndDestinationAwardsAreDifferent = !StringUtils.equalsIgnoreCase(pendingTransactionItem.getSourceAwardNumber()
                                                    , pendingTransactionItem.getDestinationAwardNumber());
        if(!srcAndDestinationAwardsAreDifferent){
            reportError(PENDING_TRANSACTION_ITEMS_LIST_ERROR_KEY, KeyConstants.ERROR_TNM_PENDING_TRANSACTION_SOURCE_AND_DESTINATION_AWARDS_ARE_SAME, SOURCE_AWARD_PROPERTY);
        }
        
        return srcAndDestinationAwardsAreDifferent;
    }
    
    /**
     * A pending transaction item is unique if no other matching items are in the collection
     * To know if this is a new add or an edit of an existing pending transaction item, we check 
     * the identifier for nullity. If null, this is an add; otherwise, it's an update
     * If an update, then we expect to find one match in the collection (itself). If an add, 
     * we expect to find no matches in the collection 
     * @param pendingTransactionItems
     * @param pendingTransactionItem
     * @return
     */
    boolean isUnique(List<PendingTransaction> pendingTransactionItems, PendingTransaction pendingTransactionItem) {
        boolean duplicateFound = false;
        for(PendingTransaction listItem: pendingTransactionItems) {
            duplicateFound = pendingTransactionItem != listItem && listItem.equals(pendingTransactionItem);
            if(duplicateFound) {
                break;
            }
        }
        
        if(duplicateFound) {
            if(!hasDuplicateErrorBeenReported()) {
                reportError(PENDING_TRANSACTION_ITEMS_LIST_ERROR_KEY, KeyConstants.ERROR_TNM_PENDING_TRANSACTION_ITEM_NOT_UNIQUE, SOURCE_AWARD_PROPERTY);
            }
        }
        return !duplicateFound;
    }

    /**
     * Validate required fields present
     * @param equipmentItem
     * @return
     */
    boolean areRequiredFieldsComplete(PendingTransaction pendingTransactionItem) {        
        boolean itemValid = isSourceAwardFieldComplete(pendingTransactionItem);
        itemValid &= isDestinationAwardFieldComplete(pendingTransactionItem);
        
        return itemValid;
    }
    
    protected boolean isSourceAwardFieldComplete(PendingTransaction pendingTransactionItem){
        boolean itemValid = pendingTransactionItem.getSourceAwardNumber() != null;
        
        if(!itemValid) {            
            reportError(SOURCE_AWARD_PROPERTY, KeyConstants.ERROR_REQUIRED, SOURCE_AWARD_ERROR_PARM);
        }
        
        return itemValid;
    }
    
    protected boolean isDestinationAwardFieldComplete(PendingTransaction pendingTransactionItem){
        boolean itemValid = pendingTransactionItem.getDestinationAwardNumber() != null;
        
        if(!itemValid) {            
            reportError(DESTINATION_AWARD_PROPERTY, KeyConstants.ERROR_REQUIRED, DESTINATION_AWARD_ERROR_PARM);
        }
        
        return itemValid;
    }
    
    private boolean hasDuplicateErrorBeenReported() {
        return GlobalVariables.getErrorMap().containsMessageKey(KeyConstants.ERROR_TNM_PENDING_TRANSACTION_ITEM_NOT_UNIQUE);
    }
}
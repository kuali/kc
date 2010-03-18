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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.codehaus.plexus.util.StringUtils;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.bo.versioning.VersionHistory;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.history.TransactionDetail;
import org.kuali.kra.timeandmoney.service.ActivePendingTransactionsService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * The AwardPaymentScheduleRuleImpl
 */
public class TransactionRuleImpl extends ResearchDocumentRuleBase implements TransactionRule {
    
    private static final String SOURCE_AWARD_PROPERTY = "sourceAwardNumber";
    private static final String OBLIGATED_AMOUNT_PROPERTY = "obligatedAmount";
    private static final String ANTICIPATED_AMOUNT_PROPERTY = "anticipatedAmount";
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
        List<Award> awards = processTransactions(event.getTimeAndMoneyDocument());
      
        Award award = getLastSourceAwardReferenceInAwards(awards, event.getPendingTransactionItemForValidation().getSourceAwardNumber());
        //if source award is "External, the award will be null and we don't need to validate these amounts.
        boolean validObligatedFunds = true;
        boolean validAnticipatedFunds = true;
        if(!(award == null)) {
            validObligatedFunds = validateSourceObligatedFunds(event.getPendingTransactionItemForValidation(), award);
            validAnticipatedFunds = validateSourceAnticipatedFunds(event.getPendingTransactionItemForValidation(), award);
        }
        
        boolean validFunds = validateAnticipatedGreaterThanObligated (event);
        
        return areRequiredFieldsComplete(event.getPendingTransactionItemForValidation()) && processCommonValidations(event) && 
        validObligatedFunds && validAnticipatedFunds && validFunds;        
    }
    
    private boolean validateAnticipatedGreaterThanObligated (AddTransactionRuleEvent event) {
        boolean valid = true;
        //add the transaction to the document so we can simulate processing the transaction.
        event.getTimeAndMoneyDocument().add(event.getPendingTransactionItemForValidation());
        List<Award> awards = processTransactions(event.getTimeAndMoneyDocument());       
        Award updatedRootAward = findUpdatedRootAward(awards, event.getTimeAndMoneyDocument().getRootAwardNumber());
        AwardAmountInfo awardAmountInfo = updatedRootAward.getAwardAmountInfos().get(updatedRootAward.getAwardAmountInfos().size() -1);
        if (awardAmountInfo.getAnticipatedTotalAmount().subtract(awardAmountInfo.getAmountObligatedToDate()).isNegative()) {
            reportError(OBLIGATED_AMOUNT_PROPERTY, KeyConstants.ERROR_TOTAL_AMOUNT_INVALID);
            valid = false;
        }
        //remove the Transaction from the document.
        event.getTimeAndMoneyDocument().getPendingTransactions().remove(event.getTimeAndMoneyDocument().getPendingTransactions().size() - 1);
        return valid;
    }
    
    private Award findUpdatedRootAward(List<Award> awards, String rootAwardNumber) {
        Award returnAward = null;
        for (Award award : awards) {
            if (award.getAwardNumber() == rootAwardNumber) {
                returnAward = award;
            }
        }
        if(returnAward == null) {
            returnAward = getActiveAwardVersion(rootAwardNumber);
        }
        return returnAward;
    }
    
    private List<Award> processTransactions(TimeAndMoneyDocument timeAndMoneyDocument) {
        Map<String, AwardAmountTransaction> awardAmountTransactionItems = new HashMap<String, AwardAmountTransaction>();
        List<Award> awardItems = new ArrayList<Award>();
        List<TransactionDetail> transactionDetailItems = new ArrayList<TransactionDetail>();        
        ActivePendingTransactionsService service = KraServiceLocator.getService(ActivePendingTransactionsService.class);
        service.processTransactionsForAddRuleProcessing(timeAndMoneyDocument, timeAndMoneyDocument.getAwardAmountTransactions().get(0), 
                awardAmountTransactionItems, awardItems, transactionDetailItems);
        
        return awardItems;
    }
    
    private Award getLastSourceAwardReferenceInAwards (List<Award> awards, String sourceAwardNumber) {
        Award returnAward = null;
        for (Award award : awards) {
            if (award.getAwardNumber() == sourceAwardNumber) {
                returnAward = award;
            }
        }
        if(returnAward == null) {
            returnAward = getActiveAwardVersion(sourceAwardNumber);
        }
        return returnAward;
    }
    
    private boolean validateSourceObligatedFunds (PendingTransaction pendingTransaction, Award award) {
        AwardAmountInfo awardAmountInfo = award.getAwardAmountInfos().get(award.getAwardAmountInfos().size() -1);
        boolean valid = true;
        if (awardAmountInfo.getObliDistributableAmount().subtract(pendingTransaction.getObligatedAmount()).isNegative()) {
            reportError(OBLIGATED_AMOUNT_PROPERTY, KeyConstants.ERROR_OBLIGATED_AMOUNT_INVALID);
            valid = false;
        }
        return valid;
    }
    
    private boolean validateSourceAnticipatedFunds (PendingTransaction pendingTransaction, Award award) {
        AwardAmountInfo awardAmountInfo = award.getAwardAmountInfos().get(award.getAwardAmountInfos().size() -1);
        boolean valid = true;
        if (awardAmountInfo.getAntDistributableAmount().subtract(pendingTransaction.getAnticipatedAmount()).isNegative()) {
            reportError(ANTICIPATED_AMOUNT_PROPERTY, KeyConstants.ERROR_ANTICIPATED_AMOUNT_INVALID);
            valid = false;
        }
        return valid;
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
    
    /**
     * 
     */
    public Award getActiveAwardVersion(String awardNumber) {
        VersionHistoryService vhs = KraServiceLocator.getService(VersionHistoryService.class);  
        VersionHistory vh = vhs.findActiveVersion(Award.class, awardNumber);
        Award award = null;
        
        if(vh!=null){
            award = (Award) vh.getSequenceOwner();
        }else{
            BusinessObjectService businessObjectService =  KraServiceLocator.getService(BusinessObjectService.class);
            List<Award> awards = (List<Award>) businessObjectService.findMatching(Award.class, getHashMap(awardNumber));     
            award = (CollectionUtils.isEmpty(awards) ? null : awards.get(0));
        }
        return award;
    }
    
    private Map<String, String> getHashMap(String goToAwardNumber) {
        Map<String, String> map = new HashMap<String,String>();
        map.put("awardNumber", goToAwardNumber);
        return map;
    }
}
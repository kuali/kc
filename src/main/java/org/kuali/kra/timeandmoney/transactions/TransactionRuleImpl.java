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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.bo.versioning.VersionHistory;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.history.TransactionDetail;
import org.kuali.kra.timeandmoney.service.ActivePendingTransactionsService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.ParameterConstants;
import org.kuali.rice.kns.service.ParameterService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KualiDecimal;

/**
 * The AwardPaymentScheduleRuleImpl
 */
public class TransactionRuleImpl extends ResearchDocumentRuleBase implements TransactionRule {
    
    private static final String SOURCE_AWARD_PROPERTY = "sourceAwardNumber";
    private static final String OBLIGATED_AMOUNT_PROPERTY = "obligatedAmount";
    private static final String ANTICIPATED_AMOUNT_PROPERTY = "anticipatedAmount";
    private static final String DESTINATION_AWARD_PROPERTY = "destinationAwardNumber";
    private static final String CURRENT_FUND_EFFECTIVE_DATE = "currentFundEffectiveDate";
    private static final String SOURCE_AWARD_ERROR_PARM = "Source Award (Source Award)";
    private static final String DESTINATION_AWARD_ERROR_PARM = "Destination Award (Destination Award)";
    private static final String TOTALS = "totals";
    private static final String TIME_AND_MONEY_TRANSACTION = "timeAndMoneyTransaction";
    private static final String NEW_AWARD_AMOUNT_TRANSACTION = "newAwardAmountTransaction";
    private static final String TRANSACTION_TYPE_CODE = ".transactionTypeCode";
    
    private ParameterService parameterService;

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
        boolean validTotalCostLimit = true;
        if(!(award == null)) {
            validObligatedFunds = validateSourceObligatedFunds(event.getPendingTransactionItemForValidation(), award);
            validAnticipatedFunds = validateSourceAnticipatedFunds(event.getPendingTransactionItemForValidation(), award);
            validTotalCostLimit = validateAwardTotalCostLimit(event.getPendingTransactionItemForValidation(), award);
            //need to remove the award amount info created from this process transactions call so there won't be a double entry in collection.
            award.refreshReferenceObject("awardAmountInfos");
        }
        boolean requiredFieldsComplete = areRequiredFieldsComplete(event.getPendingTransactionItemForValidation());
        
        boolean validFunds = false;
        boolean validDates = false;
        boolean validSourceAwardDestinationAward = false;
        if(requiredFieldsComplete){
            validSourceAwardDestinationAward = processCommonValidations(event);
            if(validSourceAwardDestinationAward){
                validFunds = validateAnticipatedGreaterThanObligated (event);
                validDates = validateObligatedDateIsSet(event);
            }
        }
        
        return requiredFieldsComplete && validSourceAwardDestinationAward && validObligatedFunds  
            && validAnticipatedFunds && validFunds && validDates && validTotalCostLimit ;        
    }
    
    /**
     * @see org.kuali.kra.timeandmoney.transactions.TransactionRule#processSingleNodeTransactionBusinessRules(org.kuali.kra.timeandmoney.AwardHierarchyNode, org.kuali.kra.award.home.AwardAmountInfo)
     * Business rules for single node transactions
     * 1)if direct/F&A view is disabled, then we test that transaction will either move anticipated/obligated money into or our of award.
     *   We cannot have the instance where we are moving in and out of award at same time since the transactions have a source and destination award
     * 2)if Direct/F&A view is enabled, then there are two possibilities.
     *      a)We cannot move money in and out of award for direct/indirect amounts in same transaction.
     *      b)The exception to this rule is if doing so does not affect the total amount.  The net affect of this is moving money from idc to dc or
     *        vice versa.
     */
    public boolean processSingleNodeTransactionBusinessRules (AwardHierarchyNode awardHierarchyNode, AwardAmountInfo aai, TimeAndMoneyDocument doc) {
        boolean returnValue;
        if(isDirectIndirectViewEnabled()) {
            returnValue = processParameterEnabledRules(awardHierarchyNode, aai, doc);
        } else {
            returnValue = processParameterDisabledRules(awardHierarchyNode, aai, doc);
        }
        return returnValue;
    }
    
    
    /**
     * Looks up and returns the ParameterService.
     * @return the parameter service. 
     */
    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KraServiceLocator.getService(ParameterService.class);        
        }
        return this.parameterService;
    }
    
    public boolean isDirectIndirectViewEnabled() {
        boolean returnValue = false;
        String directIndirectEnabledValue = getParameterService().getParameterValue(Constants.PARAMETER_MODULE_AWARD, ParameterConstants.DOCUMENT_COMPONENT, "ENABLE_AWD_ANT_OBL_DIRECT_INDIRECT_COST");
        if(directIndirectEnabledValue.equals("1")) {
            returnValue = true;
        }
        return returnValue;
    }
    
    
    private boolean validateAnticipatedGreaterThanObligated (AddTransactionRuleEvent event) {
        boolean valid = true;
        //add the transaction to the document so we can simulate processing the transaction.
        event.getTimeAndMoneyDocument().add(event.getPendingTransactionItemForValidation());
        List<Award> awards = processTransactions(event.getTimeAndMoneyDocument());
        for (Award award : awards) {
            Award activeAward = getWorkingAwardVersion(award.getAwardNumber());
            if(award == null){
                activeAward = getActiveAwardVersion(award.getAwardNumber());
            }
            AwardAmountInfo awardAmountInfo = activeAward.getAwardAmountInfos().get(activeAward.getAwardAmountInfos().size() -1);
            if (awardAmountInfo.getAnticipatedTotalAmount().subtract(awardAmountInfo.getAmountObligatedToDate()).isNegative()) {
                reportError(OBLIGATED_AMOUNT_PROPERTY, KeyConstants.ERROR_TOTAL_AMOUNT_INVALID, activeAward.getAwardNumber());
                valid = false;
            }
            award.refreshReferenceObject("awardAmountInfos");
        }
        //remove the Transaction from the document.
        event.getTimeAndMoneyDocument().getPendingTransactions().remove(event.getTimeAndMoneyDocument().getPendingTransactions().size() - 1);
        return valid;
    }
    
    
    private boolean validateObligatedDateIsSet (AddTransactionRuleEvent event) {
        boolean valid = true;
        //add the transaction to the document so we can simulate processing the transaction.
        event.getTimeAndMoneyDocument().add(event.getPendingTransactionItemForValidation());
        List<Award> awards = processTransactions(event.getTimeAndMoneyDocument());
        for (Award award : awards) {
            Award activeAward = getWorkingAwardVersion(award.getAwardNumber());
            if(award == null){
                activeAward = getActiveAwardVersion(award.getAwardNumber());
            }
            AwardAmountInfo awardAmountInfo = activeAward.getAwardAmountInfos().get(activeAward.getAwardAmountInfos().size() -1);
            if (awardAmountInfo.getAmountObligatedToDate().isPositive() && 
                    (awardAmountInfo.getCurrentFundEffectiveDate() == null || awardAmountInfo.getObligationExpirationDate() == null)) {
                reportError(CURRENT_FUND_EFFECTIVE_DATE, KeyConstants.ERROR_DATE_NOT_SET, activeAward.getAwardNumber());
                valid = false;
            }
            award.refreshReferenceObject("awardAmountInfos");
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
            returnAward = getWorkingAwardVersion(rootAwardNumber);
            if(returnAward == null){
                returnAward = getActiveAwardVersion(rootAwardNumber);
            }
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
            returnAward = getWorkingAwardVersion(sourceAwardNumber);
            if(returnAward == null){
                returnAward = getActiveAwardVersion(sourceAwardNumber);
            }
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
    
    private boolean validateAwardTotalCostLimit(PendingTransaction pendingTransaction, Award award) {
        AwardAmountInfo awardAmountInfo = award.getAwardAmountInfos().get(award.getAwardAmountInfos().size() -1);
        KualiDecimal obliDistributableAmount = awardAmountInfo.getObliDistributableAmount().subtract(pendingTransaction.getObligatedAmount());
        if (award.getTotalCostBudgetLimit() != null
                && award.getTotalCostBudgetLimit().isGreaterThan(obliDistributableAmount)) {
            reportWarning(OBLIGATED_AMOUNT_PROPERTY, KeyConstants.WARNING_TRANSACTION_OBLI_LESS_THAN_BUDGET_LIMIT, new String[]{award.getAwardNumber()});
        }
        return true;
        
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
    
    public Award getWorkingAwardVersion(String goToAwardNumber) {
        Award award = null;
        award = getPendingAwardVersion(goToAwardNumber);
        if (award == null) {
            award = getActiveAwardVersion(goToAwardNumber);
        }
        return award;
    }
    
    /**
     * This method...
     * @param awardHierarchyNode
     * @param aai
     * @return
     */
    public boolean processParameterEnabledRules(AwardHierarchyNode awardHierarchyNode, AwardAmountInfo aai, TimeAndMoneyDocument doc) {
        boolean valid = true;
        KualiDecimal obligatedDirectChange = awardHierarchyNode.getObligatedTotalDirect().subtract(aai.getObligatedTotalDirect());
        KualiDecimal obligatedIndirectChange = awardHierarchyNode.getObligatedTotalIndirect().subtract(aai.getObligatedTotalIndirect());
        KualiDecimal anticipatedDirectChange = awardHierarchyNode.getAnticipatedTotalDirect().subtract(aai.getAnticipatedTotalDirect());
        KualiDecimal anticipatedIndirectChange = awardHierarchyNode.getAnticipatedTotalIndirect().subtract(aai.getAnticipatedTotalIndirect());
        
        boolean obligatedTotalChanged = awardHierarchyNode.getObligatedTotalDirect().add(awardHierarchyNode.getObligatedTotalIndirect()).isNonZero();
        boolean anticipatedTotalChanged = awardHierarchyNode.getAnticipatedTotalDirect().add(awardHierarchyNode.getAnticipatedTotalIndirect()).isNonZero();
        //if totals change and net effect of changes result in reduction of one total with increase of other, we need to report error.
        if(obligatedTotalChanged || anticipatedTotalChanged) {
            KualiDecimal obligatedNetEffect = awardHierarchyNode.getObligatedTotalDirect().add(awardHierarchyNode.getObligatedTotalIndirect());
            KualiDecimal anticipatedNetEffect = awardHierarchyNode.getAnticipatedTotalDirect().add(awardHierarchyNode.getAnticipatedTotalIndirect());
            if((obligatedNetEffect.isNegative() && anticipatedNetEffect.isPositive()) ||
                    (obligatedNetEffect.isPositive() && anticipatedNetEffect.isNegative())) {
                reportError(TOTALS, KeyConstants.ERROR_NET_TOTALS_TRANSACTION);
                valid = false;
            }
        }
        //if indirect/direct change in transaction is a debit of one and credit of the other, then there cannot be a net change in total.
        if((((obligatedDirectChange.isPositive() && obligatedIndirectChange.isNegative()) ||
                (obligatedDirectChange.isNegative() && obligatedIndirectChange.isPositive())) && obligatedTotalChanged) ||
                    (((anticipatedDirectChange.isPositive() && anticipatedIndirectChange.isNegative()) ||
                        (anticipatedDirectChange.isNegative() && anticipatedIndirectChange.isPositive())) && anticipatedTotalChanged)) {
            reportError(TIME_AND_MONEY_TRANSACTION, KeyConstants.ERROR_NET_TOTALS_TRANSACTION);
            valid = false;
        }
        valid &= validateAwardTotalCostLimit(awardHierarchyNode, aai.getAward());
        if (!doc.isInitialSave()) {//this save rule cannot be called on initial save from creation from Award Document.
            if (doc.getAwardAmountTransactions().size() > 0) { 
                    if(doc.getAwardAmountTransactions().get(0).getTransactionTypeCode() == null) {
                        valid = false;
                        reportError(NEW_AWARD_AMOUNT_TRANSACTION+TRANSACTION_TYPE_CODE, 
                            KeyConstants.ERROR_TRANSACTION_TYPE_CODE_REQUIRED);
                    }
            }
        }
        KualiDecimal obligatedTotal = new KualiDecimal(0);
        KualiDecimal anticipatedTotal= new KualiDecimal(0);
        obligatedTotal = obligatedTotal.add(awardHierarchyNode.getObligatedTotalDirect());
        obligatedTotal = obligatedTotal.add(awardHierarchyNode.getObligatedTotalIndirect());
        anticipatedTotal = anticipatedTotal.add(awardHierarchyNode.getAnticipatedTotalDirect());
        anticipatedTotal = anticipatedTotal.add(awardHierarchyNode.getAnticipatedTotalIndirect());
        
        if (obligatedTotal.isGreaterThan(anticipatedTotal)) {
            reportError(TIME_AND_MONEY_TRANSACTION, KeyConstants.ERROR_ANTICIPATED_AMOUNT);
            valid = false;
        }
        if (awardHierarchyNode.getAmountObligatedToDate().isLessThan(KualiDecimal.ZERO)) {
            reportError(TIME_AND_MONEY_TRANSACTION, KeyConstants.ERROR_OBLIGATED_AMOUNT_NEGATIVE);
            valid = false;
        }
        return valid;
    }
    
    /**
     * This method...
     * @param awardHierarchyNode
     * @param aai
     * @return
     */
    public boolean processParameterDisabledRules(AwardHierarchyNode awardHierarchyNode, AwardAmountInfo aai, TimeAndMoneyDocument doc) {
        boolean valid = true;
        KualiDecimal obligatedChange = awardHierarchyNode.getAmountObligatedToDate().subtract(aai.getAmountObligatedToDate());
        KualiDecimal anticipatedChange = awardHierarchyNode.getAnticipatedTotalAmount().subtract(aai.getAnticipatedTotalAmount());
        if ((obligatedChange.isPositive() && anticipatedChange.isNegative()) ||
                obligatedChange.isNegative() && anticipatedChange.isPositive()) {
            reportError(TIME_AND_MONEY_TRANSACTION, KeyConstants.ERROR_NET_TOTALS_TRANSACTION);
            valid = false;
        }
        if (awardHierarchyNode.getAmountObligatedToDate().isGreaterThan(awardHierarchyNode.getAnticipatedTotalAmount())) {
            reportError(TIME_AND_MONEY_TRANSACTION, KeyConstants.ERROR_ANTICIPATED_AMOUNT);
            valid = false;
        }
        if (awardHierarchyNode.getAmountObligatedToDate().isLessThan(KualiDecimal.ZERO)) {
            reportError(TIME_AND_MONEY_TRANSACTION, KeyConstants.ERROR_OBLIGATED_AMOUNT_NEGATIVE);
            valid = false;
        }
        if (awardHierarchyNode.getAnticipatedTotalAmount().isLessThan(KualiDecimal.ZERO)) {
            reportError(TIME_AND_MONEY_TRANSACTION, KeyConstants.ERROR_ANTICIPATED_AMOUNT_NEGATIVE);
            valid = false;            
        }
        valid &= validateAwardTotalCostLimit(awardHierarchyNode, aai.getAward());
        if (!doc.isInitialSave()) {//this save rule cannot be called on initial save from creation from Award Document.
            if (doc.getAwardAmountTransactions().size() > 0) { 
                    if(doc.getAwardAmountTransactions().get(0).getTransactionTypeCode() == null) {
                        valid = false;
                        reportError(NEW_AWARD_AMOUNT_TRANSACTION+TRANSACTION_TYPE_CODE, 
                            KeyConstants.ERROR_TRANSACTION_TYPE_CODE_REQUIRED);
                    }
            }
        }
        return valid;
    }
    
    protected boolean validateAwardTotalCostLimit(AwardHierarchyNode awardHierarchyNode, Award award) {
        if (award.getTotalCostBudgetLimit() != null
                && award.getTotalCostBudgetLimit().isGreaterThan(awardHierarchyNode.getObliDistributableAmount())) {
            reportWarning(TIME_AND_MONEY_TRANSACTION, KeyConstants.WARNING_TRANSACTION_OBLI_LESS_THAN_BUDGET_LIMIT, 
                    new String[]{award.getAwardNumber()});
        }
        return true;
    }
    
    /*
     * This method retrieves the pending award version.
     * 
     * @param doc
     * @param goToAwardNumber
     */
    @SuppressWarnings("unchecked")
    public Award getPendingAwardVersion(String goToAwardNumber) {
        
        Award award = null;
        BusinessObjectService businessObjectService =  KraServiceLocator.getService(BusinessObjectService.class);
        List<Award> awards = (List<Award>)businessObjectService.findMatchingOrderBy(Award.class, getHashMapToFindActiveAward(goToAwardNumber), "sequenceNumber", true);
        if(!(awards.size() == 0)) {
            award = awards.get(awards.size() - 1);
        }
        return award;
    }
    
    /**
     * 
     * @see org.kuali.kra.timeandmoney.service.ActivePendingTransactionsService#getActiveAwardVersion(java.lang.String)
     */
    @SuppressWarnings("unchecked")
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
    
    private Map<String, String> getHashMapToFindActiveAward(String goToAwardNumber) {
        Map<String, String> map = new HashMap<String,String>();
        map.put("awardNumber", goToAwardNumber);
        return map;
    }

    
    private Map<String, String> getHashMap(String goToAwardNumber) {
        Map<String, String> map = new HashMap<String,String>();
        map.put("awardNumber", goToAwardNumber);
        return map;
    }
}
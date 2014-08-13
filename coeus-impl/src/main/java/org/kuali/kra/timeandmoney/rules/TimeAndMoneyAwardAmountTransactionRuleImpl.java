/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.timeandmoney.rules;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.AwardAmountInfoService;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.version.service.AwardVersionService;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.rule.event.TimeAndMoneyAwardAmountTransactionSaveEvent;
import org.kuali.kra.timeandmoney.transactions.PendingTransaction;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.sql.Date;
import java.util.Map.Entry;


public class TimeAndMoneyAwardAmountTransactionRuleImpl extends KcTransactionalDocumentRuleBase implements
        TimeAndMoneyAwardAmountTransactionRule {
    
    private static final String NEW_AWARD_AMOUNT_TRANSACTION = "newAwardAmountTransaction";
    private static final String TRANSACTION_TYPE_CODE = ".transactionTypeCode";
    private static final String AMOUNTS_INVALID_FROM_TRANSACTIONS = "document.pendingTransactionsCauseErrors";
    
    private AwardVersionService awardVersionService;
    private AwardAmountInfoService awardAmountInfoService;

    @Override
    public boolean processSaveAwardAmountTransactionBusinessRules(
            TimeAndMoneyAwardAmountTransactionSaveEvent timeAndMoneyAwardAmountTransactionSaveEvent) {
        TimeAndMoneyDocument timeAndMoneyDocument = (TimeAndMoneyDocument) timeAndMoneyAwardAmountTransactionSaveEvent.getDocument();
        boolean valid = true;
        if (!timeAndMoneyDocument.isInitialSave()) {//this save rule cannot be called on initial save from creation from Award Document.
            if (timeAndMoneyDocument.getAwardAmountTransactions().size() > 0) { 
                    if(timeAndMoneyDocument.getAwardAmountTransactions().get(0).getTransactionTypeCode() == null) {
                        valid = false;
                        reportError(NEW_AWARD_AMOUNT_TRANSACTION+TRANSACTION_TYPE_CODE, 
                            KeyConstants.ERROR_TRANSACTION_TYPE_CODE_REQUIRED);
                    }
            }
            else if (timeAndMoneyDocument.getNewAwardAmountTransaction() == null || 
                    timeAndMoneyDocument.getNewAwardAmountTransaction().getTransactionTypeCode() == null) {
                valid = false;
                reportError(NEW_AWARD_AMOUNT_TRANSACTION+TRANSACTION_TYPE_CODE, 
                    KeyConstants.ERROR_TRANSACTION_TYPE_CODE_REQUIRED);
            }
            // now check pending transactions to make sure we're not messing up obligation stuff 
            if (timeAndMoneyDocument.getPendingTransactions().size() > 0) {
                for(Entry<String, AwardHierarchyNode> awardHierarchyNode : timeAndMoneyDocument.getAwardHierarchyNodes().entrySet()) {
                    Award award = getAwardVersionService().getWorkingAwardVersion(awardHierarchyNode.getValue().getAwardNumber());
                    award.refreshReferenceObject("awardAmountInfos");
                    AwardAmountInfo aai = getAwardAmountInfoService().fetchAwardAmountInfoWithHighestTransactionId(award.getAwardAmountInfos());
                    Date obligatedStartDate = aai.getCurrentFundEffectiveDate();
                    Date obligatedEndDate = aai.getObligationExpirationDate();
                    ScaleTwoDecimal obligatedTotal = aai.getAmountObligatedToDate();
                    ScaleTwoDecimal anticipatedTotal = aai.getAnticipatedTotalAmount();
                    for (PendingTransaction pendingTransaction: timeAndMoneyDocument.getPendingTransactions()) {
                        if (!pendingTransaction.getProcessedFlag().booleanValue()) {
                            if (StringUtils.equals(pendingTransaction.getSourceAwardNumber(), award.getAwardNumber())) {
                                anticipatedTotal = anticipatedTotal.subtract(pendingTransaction.getAnticipatedAmount());
                                obligatedTotal = obligatedTotal.subtract(pendingTransaction.getObligatedAmount());
                            }
                            if (StringUtils.equals(pendingTransaction.getDestinationAwardNumber(), award.getAwardNumber())) {
                                anticipatedTotal = anticipatedTotal.add(pendingTransaction.getAnticipatedAmount());
                                obligatedTotal = obligatedTotal.add(pendingTransaction.getObligatedAmount());
                            }
                        }
                    }
                    MessageMap errorMap = GlobalVariables.getMessageMap();
                    errorMap.clearErrorPath();
                    if (obligatedTotal.isGreaterThan(anticipatedTotal)) {
                        valid = false;
                        reportError(AMOUNTS_INVALID_FROM_TRANSACTIONS, KeyConstants.ERROR_ANTICIPATED_AMOUNT_FROM_TRANSACTIONS, award.getAwardNumber());
                    }
                    if (anticipatedTotal.isNegative()) {
                        valid = false;
                        reportError(AMOUNTS_INVALID_FROM_TRANSACTIONS, KeyConstants.ERROR_AWARD_ANTICIPATED_NEGATIVE_FROM_TRANSACTIONS, award.getAwardNumber());
                    }
                    if (obligatedTotal.isNegative()) {
                        valid = false;
                        reportError(AMOUNTS_INVALID_FROM_TRANSACTIONS, KeyConstants.ERROR_AWARD_OBLIGATED_NEGATIVE_FROM_TRANSACTIONS, award.getAwardNumber());
                    }
                    if (obligatedTotal.isPositive() && (obligatedStartDate == null || obligatedEndDate == null)) {
                        valid = false;
                        reportError(AMOUNTS_INVALID_FROM_TRANSACTIONS, KeyConstants.ERROR_AWARD_OBLIGATED_DATES_FROM_TRANSACTIONS, award.getAwardNumber());
                    }
                }
            }
        }
        return valid;
    }

    public AwardVersionService getAwardVersionService() {
        if (awardVersionService == null) {
            awardVersionService = KcServiceLocator.getService(AwardVersionService.class);
        }
        return awardVersionService;
    }

    public AwardAmountInfoService getAwardAmountInfoService() {
        if (awardAmountInfoService == null) {
            awardAmountInfoService = KcServiceLocator.getService(AwardAmountInfoService.class);
        }
        return awardAmountInfoService;
    }

}

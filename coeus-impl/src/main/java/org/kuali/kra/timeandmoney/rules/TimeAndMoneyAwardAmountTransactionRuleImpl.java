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

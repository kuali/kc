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
package org.kuali.kra.timeandmoney.service;

import org.kuali.kra.award.home.Award;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.history.TransactionDetail;
import org.kuali.kra.timeandmoney.transactions.AwardAmountTransaction;

import java.util.List;
import java.util.Map;

public interface ActivePendingTransactionsService {
    
    /**
     * 
     * This method will approve all pending transactions and accordingly update the awardAmountInfo numbers for all awards.
     * @param document
     * @param newAwardAmountTransaction
     */
    void approveTransactions(TimeAndMoneyDocument document, AwardAmountTransaction newAwardAmountTransaction);
    
    List<AwardAmountTransaction> processTransactions(TimeAndMoneyDocument doc, AwardAmountTransaction newAwardAmountTransaction, Map<String
            , AwardAmountTransaction> awardAmountTransactionItems, List<Award> awardItems, List<TransactionDetail> transactionDetailItems, Boolean refreshFlag);

    List<Award> processTransactionsForAddRuleProcessing(TimeAndMoneyDocument doc, AwardAmountTransaction newAwardAmountTransaction, Map<String
            , AwardAmountTransaction> awardAmountTransactionItems, List<Award> awardItems, List<TransactionDetail> transactionDetailItems);

}

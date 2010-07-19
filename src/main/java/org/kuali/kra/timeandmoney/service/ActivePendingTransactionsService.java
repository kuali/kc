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
package org.kuali.kra.timeandmoney.service;

import java.util.List;
import java.util.Map;

import org.kuali.kra.award.home.Award;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.history.TransactionDetail;
import org.kuali.kra.timeandmoney.transactions.AwardAmountTransaction;

public interface ActivePendingTransactionsService {
    
    /**
     * 
     * This method will approve all pending transactions and accordingly update the awardAmountInfo numbers for all awards.
     * @param document
     * @param newAwardAmountTransaction
     */
    void approveTransactions(TimeAndMoneyDocument document, AwardAmountTransaction newAwardAmountTransaction);
    
    List<AwardAmountTransaction> processTransactions(TimeAndMoneyDocument doc, AwardAmountTransaction newAwardAmountTransaction, Map<String
            , AwardAmountTransaction> awardAmountTransactionItems, List<Award> awardItems, List<TransactionDetail> transactionDetailItems);
    
    List<AwardAmountTransaction> processSingleNodeMoneyTransaction(TimeAndMoneyDocument doc,AwardAmountTransaction newAwardAmountTransaction
            , Map<String, AwardAmountTransaction> awardAmountTransactionItems, List<Award> awardItems, List<TransactionDetail> transactionDetailItems);
    
    List<Award> processTransactionsForAddRuleProcessing(TimeAndMoneyDocument doc, AwardAmountTransaction newAwardAmountTransaction, Map<String
            , AwardAmountTransaction> awardAmountTransactionItems, List<Award> awardItems, List<TransactionDetail> transactionDetailItems);
    
    /**
     * 
     * This method retrieves the active award using the version history service.
     * @param awardNumber
     * @return
     */
    Award getPendingAwardVersion(String awardNumber);
    Award getActiveAwardVersion(String awardNumber);
    Award getWorkingAwardVersion(String awardNumber);

}

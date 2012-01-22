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
package org.kuali.kra.timeandmoney.rules;

import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.rule.event.TimeAndMoneyAwardAmountTransactionSaveEvent;

/**
 * This class...
 */
public class TimeAndMoneyAwardAmountTransactionRuleImpl extends ResearchDocumentRuleBase implements
        TimeAndMoneyAwardAmountTransactionRule {
    
    private static final String NEW_AWARD_AMOUNT_TRANSACTION = "newAwardAmountTransaction";
    private static final String TRANSACTION_TYPE_CODE = ".transactionTypeCode";



    /**
     * @see org.kuali.kra.timeandmoney.rules.TimeAndMoneyAwardAmountTransactionRule#processSaveAwardAmountTransactionBusinessRules(org.kuali.kra.timeandmoney.rule.event.TimeAndMoneyAwardAmountTransactionSaveEvent)
     */
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
        }
        return valid;
    }

}

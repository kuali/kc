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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * This the AwardPaymentScheduleRuleEvent
 */
public class TransactionRuleEvent extends KraDocumentEventBase {
    private static final Log LOG = LogFactory.getLog(TransactionRuleEvent.class);
    
    private PendingTransaction pendingTransactionItem;
    
    public TransactionRuleEvent(String errorPathPrefix, 
                                            TimeAndMoneyDocument timeAndMoneyDocument,
                                            PendingTransaction pendingTransactionItem) {
        super("Pending Transaction item", errorPathPrefix, timeAndMoneyDocument);
        this.pendingTransactionItem = pendingTransactionItem;
    }

    /**
     * Convenience method to return an AwardDocument
     * @return
     */
    public TimeAndMoneyDocument getTimeAndMoneyDocument() {
        return (TimeAndMoneyDocument) getDocument();
    }
    
    /**
     * This method returns the Pending Transaction item for validation
     * @return
     */
    public PendingTransaction getPendingTransactionItemForValidation() {
        return pendingTransactionItem;
    }   

    /**
     * @see org.kuali.kra.rule.event.KraDocumentEventBase#logEvent()
     */
    @Override
    protected void logEvent() {
        LOG.info("Logging TransactionRuleEvent");
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    @SuppressWarnings("unchecked")
    public Class getRuleInterfaceClass() {
        return TransactionRule.class;
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.core.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((TransactionRule)rule).processPendingTransactionBusinessRules(this);
    }   
}

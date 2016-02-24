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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * This the AwardPaymentScheduleRuleEvent
 */
public class TransactionRuleEvent extends KcDocumentEventBase {
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

    @Override
    protected void logEvent() {
        LOG.info("Logging TransactionRuleEvent");
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class getRuleInterfaceClass() {
        return TransactionRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((TransactionRule)rule).processPendingTransactionBusinessRules(this);
    }   
}

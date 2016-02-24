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
package org.kuali.kra.timeandmoney.rule.event;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.rules.TimeAndMoneyAwardAmountTransactionRule;
import org.kuali.rice.krad.rules.rule.BusinessRule;


public class TimeAndMoneyAwardAmountTransactionSaveEvent extends KcDocumentEventBase {

    private static final Log LOG = LogFactory.getLog(TimeAndMoneyAwardAmountTransactionSaveEvent.class);
    private static final String AWARD_AMOUNT_TRANSACTION = "Award Amount Transaction";
    
    /**
     * Constructor for rule event for save rules.
     * @param errorPathPrefix
     * @param timeAndMoneyDocument
     */
    public TimeAndMoneyAwardAmountTransactionSaveEvent(String errorPathPrefix, 
                                                        TimeAndMoneyDocument timeAndMoneyDocument) {
            super(AWARD_AMOUNT_TRANSACTION, errorPathPrefix, timeAndMoneyDocument);
    }
    
    
    
    /**
     * Convenience method to return an TimeAndMoneyDocument
     * @return
     */
    public TimeAndMoneyDocument getTimeAndMoneyDocument() {
        return (TimeAndMoneyDocument) getDocument();
    }
    
    @Override
    protected void logEvent() {
        LOG.info("Logging AwardAmountTransactionRuleEvent");
    }

    @SuppressWarnings("unchecked")
    public Class getRuleInterfaceClass() {
        return TimeAndMoneyAwardAmountTransactionRule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((TimeAndMoneyAwardAmountTransactionRule) rule).processSaveAwardAmountTransactionBusinessRules(this);
    }

}

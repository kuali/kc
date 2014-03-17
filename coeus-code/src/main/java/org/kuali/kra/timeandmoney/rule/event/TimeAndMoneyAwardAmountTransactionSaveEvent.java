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

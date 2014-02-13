/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.award.paymentreports.paymentschedule;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * This the AwardPaymentScheduleRuleEvent
 */
public class AwardPaymentScheduleRuleEvent extends KcDocumentEventBase {
    private static final Log LOG = LogFactory.getLog(AwardPaymentScheduleRuleEvent.class);
    
    private Award award;
    private AwardPaymentSchedule paymentScheduleItem;
    
    public AwardPaymentScheduleRuleEvent(String errorPathPrefix, 
                                            AwardDocument awardDocument,
                                            Award award,
                                            AwardPaymentSchedule paymentScheduleItem) {
        super("Payment Schedule item", errorPathPrefix, awardDocument);
        this.award = award;
        this.paymentScheduleItem = paymentScheduleItem;
    }

    /**
     * Convenience method to return an Award
     * @return
     */
    public Award getAward() {
        return award;
    }
    
    /**
     * Convenience method to return an AwardDocument
     * @return
     */
    public AwardDocument getAwardDocument() {
        return (AwardDocument) getDocument();
    }
    
    /**
     * This method returns the payment schedule item for validation
     * @return
     */
    public AwardPaymentSchedule getPaymentScheduleItemForValidation() {
        return paymentScheduleItem;
    }   

    /**
     * @see org.kuali.coeus.sys.framework.rule.KcDocumentEventBase#logEvent()
     */
    @Override
    protected void logEvent() {
        LOG.info("Logging AwardPaymentScheduleRuleEvent");
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    @SuppressWarnings("unchecked")
    public Class getRuleInterfaceClass() {
        return AwardPaymentScheduleRule.class;
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.core.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AwardPaymentScheduleRule)rule).processAwardPaymentScheduleBusinessRules(this);
    }   
}

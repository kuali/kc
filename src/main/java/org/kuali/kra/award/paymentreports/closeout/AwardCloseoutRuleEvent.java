/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award.paymentreports.closeout;

import org.apache.log4j.Logger;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.rice.kns.rule.BusinessRule;

/**
 * This the AwardCloseoutRuleEvent
 */
public class AwardCloseoutRuleEvent extends KraDocumentEventBase {
    private static final Logger LOG = Logger.getLogger(AwardCloseoutRuleEvent.class);
    
    private Award award;
    private AwardCloseout closeoutItem;
    
    /**
     * 
     * Constructs a AwardCloseoutRuleEvent.java.
     * @param errorPathPrefix
     * @param awardDocument
     * @param award
     * @param closeoutItem
     */
    public AwardCloseoutRuleEvent(String errorPathPrefix, 
                                            AwardDocument awardDocument,
                                            Award award,
                                            AwardCloseout closeoutItem) {
        super("Closeout item", errorPathPrefix, awardDocument);
        this.award = award;
        this.closeoutItem = closeoutItem;
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
     * This method returns the closeout item for validation
     * @return
     */
    public AwardCloseout getCloseoutItemForValidation() {
        return closeoutItem;
    }   

    /**
     * @see org.kuali.kra.rule.event.KraDocumentEventBase#logEvent()
     */
    @Override
    protected void logEvent() {
        LOG.info("Logging AwardCloseoutRuleEvent");
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    @SuppressWarnings("unchecked")
    public Class getRuleInterfaceClass() {
        return AwardCloseoutRule.class;
    }

    /**
     * 
     * @see org.kuali.rice.kns.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.rice.kns.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AwardCloseoutRule)rule).processAwardCloseoutBusinessRules(this);
    }   
}
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
package org.kuali.kra.award.rule.event;

import org.apache.log4j.Logger;
import org.kuali.kra.award.bo.AwardCostShare;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.rule.AwardCostShareRule;
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.rice.kns.rule.BusinessRule;

/**
 * This class...
 */
public class AwardCostShareRuleEvent extends KraDocumentEventBase {
    private static final Logger LOG = Logger.getLogger(AwardCostShareRuleEvent.class);
    
    private AwardCostShare awardCostShare;

    public AwardCostShareRuleEvent(String errorPathPrefix, 
                                           AwardDocument awardDocument,
                                           AwardCostShare awardCostShare) {
        super("Cost Share", errorPathPrefix, awardDocument);
        this.awardCostShare = awardCostShare;
    }
    
    /**
     * Convenience method to return an AwardDocument
     * @return
     */
    public AwardDocument getAwardDocument() {
        return (AwardDocument) getDocument();
    }
    
    /**
     * This method returns the equipment item for validation
     * @return
     */
    public AwardCostShare getCostShareForValidation() {
        return awardCostShare;
    }
    
    
    /**
     * @see org.kuali.kra.rule.event.KraDocumentEventBase#logEvent()
     */
    @Override
    protected void logEvent() {
        LOG.info("Logging AwardCostShareRuleEvent");
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    @SuppressWarnings("unchecked")
    public Class getRuleInterfaceClass() {
        return AwardCostShareRule.class;
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.core.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AwardCostShareRule)rule).processCostShareBusinessRules(this);
    }

}

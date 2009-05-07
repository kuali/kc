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
package org.kuali.kra.award.contacts;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.BusinessRule;
import org.kuali.rice.kns.util.KualiDecimal;

/**
 * This class is used by AwardPersonCreditSplitRule implementers
 */
public class AwardPersonCreditSplitRuleEvent extends KraDocumentEventBase {
    private static final Log LOGGER = LogFactory.getLog(AwardPersonCreditSplitRuleEvent.class);
    
    private Map<String, KualiDecimal> totalsByCreditSplitType;
    
    /**
     * Constructs a AwardPersonCreditSplitRuleEvent
     * @param description
     * @param errorPathPrefix
     * @param document
     */
    public AwardPersonCreditSplitRuleEvent(Document document, Map<String, KualiDecimal> totalsByCreditSplitType) {
        super("Credit splits invalid", "document.awardList[0].creditSplits.*", document);
        this.totalsByCreditSplitType = totalsByCreditSplitType;
    }

    @Override
    protected void logEvent() {
        LOGGER.info("Logging event");
    }

    /**
     * @see org.kuali.rice.kns.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    public Class<AwardPersonCreditSplitRule> getRuleInterfaceClass() {
        return AwardPersonCreditSplitRule.class;
    }

    /**
     * @see org.kuali.rice.kns.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.rice.kns.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return false;
    }

    /**
     * Gets the totalsByCreditSplitType attribute. 
     * @return Returns the totalsByCreditSplitType.
     */
    public Map<String, KualiDecimal> getTotalsByCreditSplitType() {
        return totalsByCreditSplitType;
    }
}

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
package org.kuali.kra.award.contacts;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;

import java.util.Map;

/**
 * This class is used by AwardPersonCreditSplitRule implementers
 */
public class AwardPersonCreditSplitRuleEvent extends KcDocumentEventBase {
    private static final Log LOG = LogFactory.getLog(AwardPersonCreditSplitRuleEvent.class);
    
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
        LOG.info("Logging event");
    }

    @Override
    public Class<AwardPersonCreditSplitRule> getRuleInterfaceClass() {
        return AwardPersonCreditSplitRule.class;
    }

    @Override
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

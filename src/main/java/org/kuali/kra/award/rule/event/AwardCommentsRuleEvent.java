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
package org.kuali.kra.award.rule.event;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.rule.AwardCommentsRule;
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.rice.krad.rules.rule.BusinessRule;

public class AwardCommentsRuleEvent extends KraDocumentEventBase {
    private static final Log LOG = LogFactory.getLog(AwardCommentsRuleEvent.class);

    public AwardCommentsRuleEvent(String errorPathPrefix, AwardDocument awardDocument) {
        super("Processing rules for Award Comments", errorPathPrefix, awardDocument);
    }

    @Override
    protected void logEvent() {
        LOG.info("Logging AwardCommentsRuleEvent");
    }

    @SuppressWarnings("unchecked")
    public Class getRuleInterfaceClass() {
        return AwardCommentsRule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AwardCommentsRule) rule).processAwardCommentsBusinessRules(this);
    }
}

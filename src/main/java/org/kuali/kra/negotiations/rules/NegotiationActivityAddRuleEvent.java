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
package org.kuali.kra.negotiations.rules;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.negotiations.bo.NegotiationActivity;
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * 
 * Negotiation Activity Add Rule Event - Event to use when validating new activities.
 */
public class NegotiationActivityAddRuleEvent extends KraDocumentEventBase {

    private static final Log LOG = LogFactory.getLog(NegotiationActivityAddRuleEvent.class);
    
    private NegotiationActivity newActivity;
    
    /**
     * 
     * Constructs a NegotiationActivityAddRuleEvent.java.
     * @param description
     * @param errorPathPrefix
     * @param document
     * @param newActivity
     */
    public NegotiationActivityAddRuleEvent(String description, String errorPathPrefix, Document document, NegotiationActivity newActivity) {
        super(description, errorPathPrefix, document);
        this.newActivity = newActivity;
    }
    
    public Class<NegotiationActivityAddRule> getRuleInterfaceClass() {
        return NegotiationActivityAddRule.class;
    }

    /**
     * @see org.kuali.rice.krad.rules.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.rice.krad.rules.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((NegotiationActivityAddRule) rule).processAddNegotiationActivityRule(this);
    }

    @Override
    protected void logEvent() {
        LOG.info("Logging NegotiationActivityAddRuleEvent");
    }

    public NegotiationActivity getNewActivity() {
        return newActivity;
    }
}

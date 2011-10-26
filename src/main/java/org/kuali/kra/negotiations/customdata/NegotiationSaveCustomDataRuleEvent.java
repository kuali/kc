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
package org.kuali.kra.negotiations.customdata;

import org.kuali.kra.award.customdata.AwardCustomDataRule;
import org.kuali.kra.award.customdata.AwardSaveCustomDataRuleEvent;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.negotiations.document.NegotiationDocument;
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.rice.kns.rule.BusinessRule;

/**
 * This class...
 */
public class NegotiationSaveCustomDataRuleEvent extends KraDocumentEventBase {

    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory
    .getLog(AwardSaveCustomDataRuleEvent.class);
    
    public NegotiationSaveCustomDataRuleEvent(String errorPathPrefix, ResearchDocumentBase document) {
        super("Adding custom attribute to document " + getDocumentId(document), errorPathPrefix, document);
    }
    
    /**
     * Convenience method to return an NegotiationsDocument
     * @return
     */
    public NegotiationDocument getNegotiationDocument() {
        return (NegotiationDocument) getDocument();
    }
    
    /**
     * @see org.kuali.kra.rule.event.KraDocumentEventBase#logEvent()
     */
    @Override
    protected void logEvent() {
        LOG.info("Save Negotiation custom data event");

    }

    /**
     * @see org.kuali.rice.kns.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    @SuppressWarnings("unchecked")
    public Class getRuleInterfaceClass() {
        return NegotiationCustomDataRule.class;
    }

    /**
     * @see org.kuali.rice.kns.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.rice.kns.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((NegotiationCustomDataRule) rule).processSaveNegotiationCustomDataBusinessRules(this);
    }

}

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

import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.rule.AwardCustomDataRule;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.rice.kns.rule.BusinessRule;

/**
 * Event class for Rule processing on Save for Award Custom Data.
 */
public class AwardSaveCustomDataRuleEvent extends KraDocumentEventBase {

    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory
    .getLog(AwardSaveCustomDataRuleEvent.class);

    public AwardSaveCustomDataRuleEvent(String errorPathPrefix, ResearchDocumentBase document) {
        super("Adding custom attribute to document " + getDocumentId(document), errorPathPrefix, document);
    }
    
    /**
     * Convenience method to return an AwardDocument
     * @return
     */
    public AwardDocument getAwardDocument() {
        return (AwardDocument) getDocument();
    }


    /**
     * @see org.kuali.kra.rule.event.KraDocumentEventBase#logEvent()
     */
    @Override
    protected void logEvent() {
        LOG.info("Save award custom data event");
    }

    /**
     * @see org.kuali.rice.kns.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    public Class getRuleInterfaceClass() {
        return AwardCustomDataRule.class;
    }
    
    /**
     * @see org.kuali.rice.kns.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.rice.kns.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AwardCustomDataRule) rule).processSaveAwardCustomDataBusinessRules(this);
    }


}

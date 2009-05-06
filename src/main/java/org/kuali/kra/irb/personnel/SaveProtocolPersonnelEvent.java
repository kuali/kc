/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.irb.personnel;

import static org.kuali.kra.logging.BufferedLogger.info;

import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.BusinessRule;

/**
 * Event triggered when a protocol personnel is saved to a
 * <code>{@link ProtocolDocument}</code>
 *
 */
public class SaveProtocolPersonnelEvent extends ProtocolPersonnelEventBase {

    /**
     * Constructs an SaveProtocolPersonnelEvent.java with the given errorPathPrefix, document.
     * 
     * @param errorPathPrefix
     * @param document
     * @see org.kuali.kra.rule.event.KraDocumentEventBase#KraDocumentEventBase(String, String, Document)
     */
    public SaveProtocolPersonnelEvent(String errorPathPrefix, ProtocolDocument document) {
        super("Saving protocol personnel on document " + getDocumentId(document), errorPathPrefix, document);
        logEvent();
    }

    /**
     * Constructs an SaveProtocolPersonnelEvent.java with the given errorPathPrefix, document.
     * 
     * @param errorPathPrefix
     * @param document
     * @param proposalPerson
     */
    public SaveProtocolPersonnelEvent(String errorPathPrefix, Document document) {
        this(errorPathPrefix, (ProtocolDocument) document);
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    public Class getRuleInterfaceClass() {
        return SaveProtocolPersonnelRule.class;
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.core.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        info("Calling processSaveProtocolPersonnelBusinessRules on ", rule.getClass().getSimpleName());
        return ((SaveProtocolPersonnelRule) rule).processSaveProtocolPersonnelBusinessRules(this);
    }
}

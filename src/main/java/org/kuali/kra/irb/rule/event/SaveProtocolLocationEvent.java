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
package org.kuali.kra.irb.rule.event;

import static org.kuali.kra.logging.BufferedLogger.info;

import org.kuali.core.document.Document;
import org.kuali.core.rule.BusinessRule;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.irb.rule.SaveProtocolLocationRule;

/**
 * Event triggered when a protocol location is added to a
 * <code>{@link ProtocolDocument}</code>
 *
 */
public class SaveProtocolLocationEvent extends ProtocolLocationEventBase {

    /**
     * Constructs an SaveProtocolLocationEvent.java with the given errorPathPrefix, document, and protocolLocation.
     * 
     * @param errorPathPrefix
     * @param document
     * @see org.kuali.kra.rule.event.KraDocumentEventBase#KraDocumentEventBase(String, String, Document)
     */
    public SaveProtocolLocationEvent(String errorPathPrefix, ProtocolDocument document) {
        super("Saving protocol location on document " + getDocumentId(document), errorPathPrefix, document);
        logEvent();
    }

    /**
     * Constructs an SaveProtocolLocationEvent.java with the given errorPathPrefix, document, and protocolLocation.
     * 
     * @param errorPathPrefix
     * @param document
     * @param proposalPerson
     */
    public SaveProtocolLocationEvent(String errorPathPrefix, Document document) {
        this(errorPathPrefix, (ProtocolDocument) document);
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    public Class getRuleInterfaceClass() {
        return SaveProtocolLocationRule.class;
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.core.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        info("Calling processSaveProtocolLocationBusinessRules on ", rule.getClass().getSimpleName());
        return ((SaveProtocolLocationRule) rule).processSaveProtocolLocationBusinessRules(this);
    }
}

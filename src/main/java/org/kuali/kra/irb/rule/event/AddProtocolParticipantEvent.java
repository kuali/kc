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
package org.kuali.kra.irb.rule.event;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.document.Document;
import org.kuali.core.rule.BusinessRule;
import org.kuali.core.util.ObjectUtils;
import org.kuali.kra.irb.bo.ProtocolParticipant;
import org.kuali.kra.irb.bo.ProtocolReference;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.irb.rule.AddProtocolParticipantRule;
import org.kuali.kra.rule.event.KraDocumentEventBase;

/**
 * This class represents the AddProtocolParticipantEvent
 */
public class AddProtocolParticipantEvent extends ProtocolParticipantEventBase {
    
    public AddProtocolParticipantEvent(String errorPathPrefix, ProtocolDocument document, ProtocolParticipant protocolParticipant) {
        super("adding ProtocolParticipant to document " + getDocumentId(document), errorPathPrefix, document, protocolParticipant);
    }

    public AddProtocolParticipantEvent(String errorPathPrefix, Document document, ProtocolParticipant protocolParticipant) {
        this(errorPathPrefix, (ProtocolDocument) document, protocolParticipant);
    }
    
    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    public Class getRuleInterfaceClass() {
        return AddProtocolParticipantRule.class;
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.core.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AddProtocolParticipantRule) rule).processAddProtocolParticipantBusinessRules(this);
    }

}

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
package org.kuali.kra.protocol.noteattachment;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * Event created when adding a new {@link ProtocolAttachmentProtocolBase ProtocolAttachmentProtocolBase}.
 */
class AddProtocolAttachmentProtocolEvent extends KcDocumentEventBase {

    private static final Log LOG = LogFactory.getLog(AddProtocolAttachmentProtocolEvent.class);
    private final ProtocolAttachmentProtocolBase newAttachmentProtocol;
    
    /**
     * Creates a new event.
     * @param document the document.
     * @param newAttachmentProtocol the new attachment to be added.
     */
    public AddProtocolAttachmentProtocolEvent(final ProtocolDocumentBase document,
        final ProtocolAttachmentProtocolBase newAttachmentProtocol) {
        super("adding new protocol attachment", "notesAttachmentsHelper", document);
        
        if (document == null) {
            throw new IllegalArgumentException("the document is null");
        }
        
        if (newAttachmentProtocol == null) {
            throw new IllegalArgumentException("the newAttachmentProtocol is null");
        }
        
        this.newAttachmentProtocol = newAttachmentProtocol;
    }

    @Override
    protected void logEvent() {
        LOG.debug("adding new: " + this.newAttachmentProtocol + " on doc # " + this.getDocument().getDocumentNumber());
    }

    @Override
    public Class<AddProtocolAttachmentProtocolRule> getRuleInterfaceClass() {
        return AddProtocolAttachmentProtocolRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return this.getRuleInterfaceClass().cast(rule).processAddProtocolAttachmentProtocolRules(this);
    }

    /**
     * Gets new {@link ProtocolAttachmentProtocolBase ProtocolAttachmentProtocolBase}.
     * @return new {@link ProtocolAttachmentProtocolBase ProtocolAttachmentProtocolBase}.
     */
    public ProtocolAttachmentProtocolBase getNewAttachmentProtocol() {
        return this.newAttachmentProtocol;
    }
}

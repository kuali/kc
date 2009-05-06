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
package org.kuali.kra.irb.noteattachment;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.rice.kns.rule.BusinessRule;

/**
 * Event created when adding a new {@link ProtocolAttachmentProtocol ProtocolAttachmentProtocol}.
 */
class AddProtocolAttachmentProtocolEvent extends KraDocumentEventBase {

    private static final Log LOG = LogFactory.getLog(AddProtocolAttachmentProtocolEvent.class);
    private final ProtocolAttachmentProtocol newAttachmentProtocol;
    
    /**
     * Creates a new event.
     * @param document the document.
     * @param newAttachmentProtocol the new attachment to be added.
     */
    public AddProtocolAttachmentProtocolEvent(final ProtocolDocument document,
        final ProtocolAttachmentProtocol newAttachmentProtocol) {
        super("adding new protocol attachment", "notesAndAttachmentsHelper", document);
        
        if (document == null) {
            throw new IllegalArgumentException("the document is null");
        }
        
        if (newAttachmentProtocol == null) {
            throw new IllegalArgumentException("the newAttachmentProtocol is null");
        }
        
        this.newAttachmentProtocol = newAttachmentProtocol;
    }

    /** {@inheritDoc} */
    @Override
    protected void logEvent() {
        LOG.debug("adding new: " + this.newAttachmentProtocol + " on doc # " + this.getDocument().getDocumentNumber());
    }

    /** {@inheritDoc} */
    public Class<AddProtocolAttachmentProtocolRule> getRuleInterfaceClass() {
        return AddProtocolAttachmentProtocolRule.class;
    }

    /** {@inheritDoc} */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return this.getRuleInterfaceClass().cast(rule).processAddProtocolAttachmentProtocolRules(this);
    }

    /**
     * Gets new {@link ProtocolAttachmentProtocol ProtocolAttachmentProtocol}.
     * @return new {@link ProtocolAttachmentProtocol ProtocolAttachmentProtocol}.
     */
    public ProtocolAttachmentProtocol getNewAttachmentProtocol() {
        return this.newAttachmentProtocol;
    }
}

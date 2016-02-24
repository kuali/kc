/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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

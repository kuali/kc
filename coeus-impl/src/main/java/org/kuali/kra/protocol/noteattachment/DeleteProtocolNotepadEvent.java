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
 * Event created when delete an existing {@link ProtocolNotepadBase ProtocolNotepadBase}.
 */
public class DeleteProtocolNotepadEvent extends KcDocumentEventBase {

    private static final Log LOG = LogFactory.getLog(DeleteProtocolNotepadEvent.class);
    private final ProtocolNotepadBase protocolNotepad;
    
    /**
     * Creates a new "delete" event.
     * @param document the document.
     * @param deleteProtocolNotepad the new attachment to be deleted.
     */
    public DeleteProtocolNotepadEvent(final ProtocolDocumentBase document,
        final ProtocolNotepadBase protocolNotepad) {
        super("deleting existing protocol notepad", "notesAttachmentsHelper", document);
        
        if (document == null) {
            throw new IllegalArgumentException("the document is null");
        }
        
        if (protocolNotepad == null) {
            throw new IllegalArgumentException("the protocolNotepad is null");
        }
        
        this.protocolNotepad = protocolNotepad;
    }

    @Override
    protected void logEvent() {
        LOG.debug("adding new: " + this.protocolNotepad + " on doc # " + this.getDocument().getDocumentNumber());
    }

    @Override
    public Class<DeleteProtocolNotepadRule> getRuleInterfaceClass() {
        return DeleteProtocolNotepadRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return this.getRuleInterfaceClass().cast(rule).processDeleteProtocolNotepadRules(this);
    }

    /**
     * Gets new {@link ProtocolNotepadBase ProtocolNotepadBase}.
     * @return new {@link ProtocolNotepadBase ProtocolNotepadBase}.
     */
    public ProtocolNotepadBase getProtocolNotepad() {
        return this.protocolNotepad;
    }
}

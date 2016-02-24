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
package org.kuali.kra.irb.noteattachment;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * Event created when adding a new {@link ProtocolNotepad ProtocolNotepad}.
 */
public class AddProtocolNotepadEvent extends KcDocumentEventBase {

    private static final Log LOG = LogFactory.getLog(AddProtocolNotepadEvent.class);
    private final ProtocolNotepad newProtocolNotepad;
    
    /**
     * Creates a new event.
     * @param document the document.
     * @param newProtocolNotepad the new attachment to be added.
     */
    public AddProtocolNotepadEvent(final ProtocolDocument document,
        final ProtocolNotepad newProtocolNotepad) {
        super("adding new protocol notepad", "notesAttachmentsHelper", document);
        
        if (document == null) {
            throw new IllegalArgumentException("the document is null");
        }
        
        if (newProtocolNotepad == null) {
            throw new IllegalArgumentException("the newProtocolNotepad is null");
        }
        
        this.newProtocolNotepad = newProtocolNotepad;
    }

    @Override
    protected void logEvent() {
        LOG.debug("adding new: " + this.newProtocolNotepad + " on doc # " + this.getDocument().getDocumentNumber());
    }

    @Override
    public Class<AddProtocolNotepadRule> getRuleInterfaceClass() {
        return AddProtocolNotepadRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return this.getRuleInterfaceClass().cast(rule).processAddProtocolNotepadRules(this);
    }

    /**
     * Gets new {@link ProtocolNotepad ProtocolNotepad}.
     * @return new {@link ProtocolNotepad ProtocolNotepad}.
     */
    public ProtocolNotepad getNewProtocolNotepad() {
        return this.newProtocolNotepad;
    }
}

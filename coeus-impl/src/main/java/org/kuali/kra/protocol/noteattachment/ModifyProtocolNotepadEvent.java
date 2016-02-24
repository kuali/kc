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
 * Event created when editing an existing {@link ProtocolNotepadBase ProtocolNotepadBase}.
 */
public class ModifyProtocolNotepadEvent extends KcDocumentEventBase {

    private static final Log LOG = LogFactory.getLog(ModifyProtocolNotepadEvent.class);
    private final ProtocolNotepadBase existingProtocolNotepad;
    
    /**
     * Creates a new event.
     * @param document the document.
     * @param existingProtocolNotepad the new attachment to be added.
     */
    public ModifyProtocolNotepadEvent(final ProtocolDocumentBase document,
        final ProtocolNotepadBase existingProtocolNotepad) {
        super("modifying existing protocol notepad", "notesAttachmentsHelper", document);
        
        if (document == null) {
            throw new IllegalArgumentException("the document is null");
        }
        
        if (existingProtocolNotepad == null) {
            throw new IllegalArgumentException("the existingProtocolNotepad is null");
        }
        
        this.existingProtocolNotepad = existingProtocolNotepad;
    }

    @Override
    protected void logEvent() {
        LOG.debug("adding new: " + this.existingProtocolNotepad + " on doc # " + this.getDocument().getDocumentNumber());
    }

    @Override
    public Class<ModifyProtocolNotepadRule> getRuleInterfaceClass() {
        return ModifyProtocolNotepadRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return this.getRuleInterfaceClass().cast(rule).processModifyProtocolNotepadRules(this);
    }

    /**
     * Gets new {@link ProtocolNotepadBase ProtocolNotepadBase}.
     * @return new {@link ProtocolNotepadBase ProtocolNotepadBase}.
     */
    public ProtocolNotepadBase getExistingProtocolNotepad() {
        return this.existingProtocolNotepad;
    }
}

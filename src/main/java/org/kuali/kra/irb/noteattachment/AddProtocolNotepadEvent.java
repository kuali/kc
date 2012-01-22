/*
 * Copyright 2005-2010 The Kuali Foundation
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
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * Event created when adding a new {@link ProtocolNotepad ProtocolNotepad}.
 */
public class AddProtocolNotepadEvent extends KraDocumentEventBase {

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

    /** {@inheritDoc} */
    @Override
    protected void logEvent() {
        LOG.debug("adding new: " + this.newProtocolNotepad + " on doc # " + this.getDocument().getDocumentNumber());
    }

    /** {@inheritDoc} */
    public Class<AddProtocolNotepadRule> getRuleInterfaceClass() {
        return AddProtocolNotepadRule.class;
    }

    /** {@inheritDoc} */
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

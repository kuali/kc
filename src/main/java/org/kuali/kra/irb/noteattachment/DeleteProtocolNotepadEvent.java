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
import org.kuali.rice.kns.rule.BusinessRule;

/**
 * Event created when delete an existing {@link ProtocolNotepad ProtocolNotepad}.
 */
public class DeleteProtocolNotepadEvent extends KraDocumentEventBase {

    private static final Log LOG = LogFactory.getLog(DeleteProtocolNotepadEvent.class);
    private final ProtocolNotepad protocolNotepad;
    
    /**
     * Creates a new "delete" event.
     * @param document the document.
     * @param deleteProtocolNotepad the new attachment to be deleted.
     */
    public DeleteProtocolNotepadEvent(final ProtocolDocument document,
        final ProtocolNotepad protocolNotepad) {
        super("deleting existing protocol notepad", "notesAttachmentsHelper", document);
        
        if (document == null) {
            throw new IllegalArgumentException("the document is null");
        }
        
        if (protocolNotepad == null) {
            throw new IllegalArgumentException("the protocolNotepad is null");
        }
        
        this.protocolNotepad = protocolNotepad;
    }

    /** {@inheritDoc} */
    @Override
    protected void logEvent() {
        LOG.debug("adding new: " + this.protocolNotepad + " on doc # " + this.getDocument().getDocumentNumber());
    }

    /** {@inheritDoc} */
    public Class<DeleteProtocolNotepadRule> getRuleInterfaceClass() {
        return DeleteProtocolNotepadRule.class;
    }

    /** {@inheritDoc} */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return this.getRuleInterfaceClass().cast(rule).processDeleteProtocolNotepadRules(this);
    }

    /**
     * Gets new {@link ProtocolNotepad ProtocolNotepad}.
     * @return new {@link ProtocolNotepad ProtocolNotepad}.
     */
    public ProtocolNotepad getProtocolNotepad() {
        return this.protocolNotepad;
    }
}

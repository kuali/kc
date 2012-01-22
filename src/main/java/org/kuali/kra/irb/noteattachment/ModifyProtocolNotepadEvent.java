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
 * Event created when editing an existing {@link ProtocolNotepad ProtocolNotepad}.
 */
public class ModifyProtocolNotepadEvent extends KraDocumentEventBase {

    private static final Log LOG = LogFactory.getLog(ModifyProtocolNotepadEvent.class);
    private final ProtocolNotepad existingProtocolNotepad;
    
    /**
     * Creates a new event.
     * @param document the document.
     * @param existingProtocolNotepad the new attachment to be added.
     */
    public ModifyProtocolNotepadEvent(final ProtocolDocument document,
        final ProtocolNotepad existingProtocolNotepad) {
        super("modifying existing protocol notepad", "notesAttachmentsHelper", document);
        
        if (document == null) {
            throw new IllegalArgumentException("the document is null");
        }
        
        if (existingProtocolNotepad == null) {
            throw new IllegalArgumentException("the existingProtocolNotepad is null");
        }
        
        this.existingProtocolNotepad = existingProtocolNotepad;
    }

    /** {@inheritDoc} */
    @Override
    protected void logEvent() {
        LOG.debug("adding new: " + this.existingProtocolNotepad + " on doc # " + this.getDocument().getDocumentNumber());
    }

    /** {@inheritDoc} */
    public Class<ModifyProtocolNotepadRule> getRuleInterfaceClass() {
        return ModifyProtocolNotepadRule.class;
    }

    /** {@inheritDoc} */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return this.getRuleInterfaceClass().cast(rule).processModifyProtocolNotepadRules(this);
    }

    /**
     * Gets new {@link ProtocolNotepad ProtocolNotepad}.
     * @return new {@link ProtocolNotepad ProtocolNotepad}.
     */
    public ProtocolNotepad getExistingProtocolNotepad() {
        return this.existingProtocolNotepad;
    }
}

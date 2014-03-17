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

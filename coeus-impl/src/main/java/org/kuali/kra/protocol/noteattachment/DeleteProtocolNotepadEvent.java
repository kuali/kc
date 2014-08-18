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

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
 * Event created when adding a new {@link ProtocolAttachmentPersonnel ProtocolAttachmentPersonnel}.
 */
class AddProtocolAttachmentPersonnelEvent extends KraDocumentEventBase {

    private static final Log LOG = LogFactory.getLog(AddProtocolAttachmentPersonnelEvent.class);
    private final ProtocolAttachmentPersonnel newAttachmentPersonnel;
    
    /**
     * Creates a new event.
     * @param document the document.
     * @param newAttachmentPersonnel the new attachment to be added.
     */
    public AddProtocolAttachmentPersonnelEvent(final ProtocolDocument document,
        final ProtocolAttachmentPersonnel newAttachmentPersonnel) {
        super("adding new protocol attachment", "notesAndAttachmentsHelper", document);
        
        if (document == null) {
            throw new IllegalArgumentException("the document is null");
        }
        
        if (newAttachmentPersonnel == null) {
            throw new IllegalArgumentException("the newAttachmentPersonnel is null");
        }
        
        this.newAttachmentPersonnel = newAttachmentPersonnel;
    }

    /** {@inheritDoc} */
    @Override
    protected void logEvent() {
        LOG.debug("adding new: " + this.newAttachmentPersonnel + " on doc # " + this.getDocument().getDocumentNumber());
    }

    /** {@inheritDoc} */
    public Class<AddProtocolAttachmentPersonnelRule> getRuleInterfaceClass() {
        return AddProtocolAttachmentPersonnelRule.class;
    }

    /** {@inheritDoc} */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return this.getRuleInterfaceClass().cast(rule).processAddProtocolAttachmentPersonnelRules(this);
    }

    /**
     * Gets new {@link ProtocolAttachmentPersonnel ProtocolAttachmentPersonnel}.
     * @return new {@link ProtocolAttachmentPersonnel ProtocolAttachmentPersonnel}.
     */
    public ProtocolAttachmentPersonnel getNewAttachmentPersonnel() {
        return this.newAttachmentPersonnel;
    }
}

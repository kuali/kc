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
package org.kuali.kra.coi.notesandattachments;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.coi.CoiDisclosureDocument;
import org.kuali.kra.coi.notesandattachments.notes.CoiDisclosureNotepad;
import org.kuali.rice.krad.rules.rule.BusinessRule;

public class AddCoiDisclosureNotepadEvent extends KcDocumentEventBase {

    private static final Log LOG = LogFactory.getLog(AddCoiDisclosureNotepadEvent.class);
    private final CoiDisclosureNotepad newCoiDisclosureNotepad;
    
    /**
     * Creates a new event.
     * @param document the document.
     * @param newProtocolNotepad the new attachment to be added.
     */
    public AddCoiDisclosureNotepadEvent(final CoiDisclosureDocument document,
        final CoiDisclosureNotepad newCoiDisclosureNotepad) {
        super("adding new coi disclosure notepad", "coiNotesAndAttachmentsHelper", document);
        
        if (document == null) {
            throw new IllegalArgumentException("the document is null");
        }
        
        if (newCoiDisclosureNotepad == null) {
            throw new IllegalArgumentException("the newCoiDisclosureNotepad is null");
        }
        
        this.newCoiDisclosureNotepad = newCoiDisclosureNotepad;
    }

    @Override
    protected void logEvent() {
        LOG.debug("adding new: " + this.newCoiDisclosureNotepad + " on doc # " + this.getDocument().getDocumentNumber());
    }

    @Override
    public Class<AddCoiDisclosureNotepadRule> getRuleInterfaceClass() {
        return AddCoiDisclosureNotepadRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return this.getRuleInterfaceClass().cast(rule).processAddCoiDisclosureNotepadRules(this);
    }

    
    public CoiDisclosureNotepad getNewCoiDisclosureNotepad() {
        return this.newCoiDisclosureNotepad;
    }
}

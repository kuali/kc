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


/**
 * Implementation of {@link AddProtocolNotepadRule AddProtocolNotepadRule}.
 * @see AddProtocolNotepadRule for details
 */
public class AddProtocolNotepadRuleImpl implements AddProtocolNotepadRule {
    
    private final ProtocolNotepadRuleHelper notesHelper
        = new ProtocolNotepadRuleHelper(NoteAndAttachmentPrefix.NEW_NOTEPAD.getPrefixName());
    
    /** {@inheritDoc} */
    public boolean processAddProtocolNotepadRules(AddProtocolNotepadEvent event) {
        final ProtocolNotepad newProtocolNotepad = event.getNewProtocolNotepad();
        
        boolean valid = this.notesHelper.validPrimitiveFields(newProtocolNotepad);
               
        return valid;
    }
}

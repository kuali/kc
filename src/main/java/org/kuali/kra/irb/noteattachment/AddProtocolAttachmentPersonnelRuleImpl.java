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

import org.kuali.kra.irb.document.ProtocolDocument;


/**
 * Implementation of {@link AddProtocolAttachmentPersonnelRule AddProtocolAttachmentPersonnelRule}.
 * @see AddProtocolAttachmentPersonnelRule for details
 */
class AddProtocolAttachmentPersonnelRuleImpl implements AddProtocolAttachmentPersonnelRule {
    
    private final ProtocolAttachmentBaseRuleHelper baseHelper
        = new ProtocolAttachmentBaseRuleHelper(NoteAndAttachmentPrefix.NEW_ATTACHMENT_PERSONNEL.getPrefixName());
    private final ProtocolAttachmentPersonnelRuleHelper personnelHelper
        = new ProtocolAttachmentPersonnelRuleHelper(NoteAndAttachmentPrefix.NEW_ATTACHMENT_PERSONNEL.getPrefixName());
    
    /** {@inheritDoc} */
    public boolean processAddProtocolAttachmentPersonnelRules(AddProtocolAttachmentPersonnelEvent event) {      
        final ProtocolDocument document = (ProtocolDocument) event.getDocument();
        final ProtocolAttachmentPersonnel newAttachmentPersonnel = event.getNewAttachmentPersonnel();
        
        boolean valid = this.baseHelper.validAgainstDictionary(newAttachmentPersonnel);
        valid &= this.baseHelper.validDescription(newAttachmentPersonnel);
        valid &= this.personnelHelper.duplicateTypePerson(newAttachmentPersonnel, document);
        
        return valid;
    }
}

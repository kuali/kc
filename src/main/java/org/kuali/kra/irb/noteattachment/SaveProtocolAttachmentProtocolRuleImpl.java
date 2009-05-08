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

import java.util.List;

import org.kuali.kra.irb.ProtocolDocument;


/**
 * Class handles rules for saving a {@link ProtocolAttachmentProtocol ProtocolAttachmentProtocol}.
 * This class does not have a corresponding event or Rule interface because it is just used-by the mega
 * {@link org.kuali.kra.irb.ProtocolDocumentRule ProtocolDocumentRule} for save events.
 */
public class SaveProtocolAttachmentProtocolRuleImpl {
    
    private final ProtocolAttachmentBaseRuleHelper baseHelper = new ProtocolAttachmentBaseRuleHelper();
    private final ProtocolAttachmentProtocolRuleHelper protocolHelper = new ProtocolAttachmentProtocolRuleHelper();
    
    /**
     * Executes the rules related to {@link ProtocolAttachmentProtocol ProtocolAttachmentProtocol} when saving a ProtocolDocument.
     * @param document the document
     * @return true if valid  
     */
    public boolean processSaveProtocolAttachmentProtocolRules(final ProtocolDocument document) {      
        
        if (document == null) {
            throw new IllegalArgumentException("the document was null");
        }
        boolean valid = true;
        
        final List<ProtocolAttachmentProtocol> attachments = document.getProtocol().getAttachmentProtocols();
        
        for (int i = 0; i < attachments.size(); i++) {
            final ProtocolAttachmentProtocol attachment = attachments.get(i);
            this.setPropertyPrefixes(NoteAndAttachmentPrefix.ATTACHMENT_PROTOCOL.getIndexedPrefix(i));
            
            valid &= this.baseHelper.validTypeForGroup(attachment);
            valid &= this.baseHelper.validDescriptionWhenRequired(attachment);
            valid &= this.protocolHelper.validStatus(attachment);
            valid &= this.baseHelper.validFile(attachment);
        }
        return valid;
    }
    
    /**
     * Resets the PropertyPrefixes on the used helpers.
     * @param prefix the prefix.
     */
    private void setPropertyPrefixes(String prefix) {
        this.baseHelper.resetPropertyPrefix(prefix);
        this.protocolHelper.resetPropertyPrefix(prefix);
    }
}

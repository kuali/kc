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
package org.kuali.coeus.common.protocol.framework.attachment;

import org.kuali.kra.protocol.ProtocolDocumentBase;

import java.util.List;

/**
 * Class handles rules for submitting a {@link ProtocolAttachmentProtocolBase ProtocolAttachmentProtocolBase}.
 * This class does not have a corresponding event or Rule interface because it is just used-by the mega
 * {@link org.kuali.kra.protocol.ProtocolDocumentRuleBase ProtocolDocumentRuleBase} for submit events.
 */
public abstract class SubmitProtocolAttachmentProtocolRuleImplBase {

    protected ProtocolAttachmentProtocolRuleHelperBase protocolHelper;

    /**
     * Executes the rules related to {@link ProtocolAttachmentProtocolBase ProtocolAttachmentProtocolBase} when saving a ProtocolDocumentBase.
     * @param document the document
     * @return true if valid  
     */
    public boolean processSubmitProtocolAttachmentProtocolRules(final ProtocolDocumentBase document) {      
        
        if (document == null) {
            throw new IllegalArgumentException("the document was null");
        }
        boolean valid = true;
        
        final List<ProtocolAttachmentProtocolBase> attachments = document.getProtocol().getAttachmentProtocols();
        
        for (int i = 0; i < attachments.size(); i++) {
            final ProtocolAttachmentProtocolBase attachment = attachments.get(i);
            this.setPropertyPrefixes(NoteAndAttachmentPrefix.ATTACHMENT_PROTOCOL.getIndexedPrefix(i));

            valid &= this.protocolHelper.validStatusForSubmission(attachment);
        }
        return valid;
    }
    
    /**
     * Resets the PropertyPrefixes on the used helpers.
     * @param prefix the prefix.
     */
    private void setPropertyPrefixes(String prefix) {
        this.protocolHelper.resetPropertyPrefix(prefix);
    }
}

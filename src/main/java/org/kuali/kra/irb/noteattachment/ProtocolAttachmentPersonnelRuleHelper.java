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

import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.rules.ErrorReporter;

/**
 * This class contains methods to "help" in validating {@link ProtocolAttachmentPersonnel ProtocolAttachmentPersonnel}.
 */
class ProtocolAttachmentPersonnelRuleHelper {
    
    private final ErrorReporter errorReporter = new ErrorReporter();
    private String propertyPrefix;
    /**
     * Creates helper deferring the setting of the prefix to later.
     */
    ProtocolAttachmentPersonnelRuleHelper() {
        super();
    }
    
    /**
     * Creates helper using prefix provided.
     *  
     * @param propertyPrefix the prefix (ex: notesAndAttachmentsHelper.newAttachmentProtocol)
     * @throws IllegalArgumentException if the propertyPrefix is null
     */
    ProtocolAttachmentPersonnelRuleHelper(final String propertyPrefix) {
        this.resetPropertyPrefix(propertyPrefix);
    }
    
    /**
     * Resets the property prefix.
     * @param aPropertyPrefix the prefix (ex: notesAndAttachmentsHelper.newAttachmentProtocol)
     * @throws IllegalArgumentException if the propertyPrefix is null
     */
    void resetPropertyPrefix(final String aPropertyPrefix) {
        if (aPropertyPrefix == null) {
            throw new IllegalArgumentException("propertyPrefix is null");
        }
        
        this.propertyPrefix = aPropertyPrefix;
    }
    
    /**
     * Checks that a type/person combination does not already exist for a document. Creates a hard error.
     * @param attachmentPersonnel the attachment.
     * @param document the document
     * @return true is valid.
     */
    boolean duplicateTypePerson(final ProtocolAttachmentPersonnel attachmentPersonnel, final ProtocolDocument document) {
        
        for (ProtocolAttachmentPersonnel attachment : document.getProtocol().getAttachmentPersonnels()) {
            if (!attachment.getId().equals(attachmentPersonnel.getId())
                && attachment.getTypeCode().equals(attachmentPersonnel.getTypeCode())
                && attachment.getPersonId().equals(attachmentPersonnel.getPersonId())) {
                this.errorReporter.reportError(this.propertyPrefix + "." + ProtocolAttachmentBase.PropertyName.TYPE_CODE,
                    KeyConstants.ERROR_PROTOCOL_ATTACHMENT_DUPLICATE_TYPE);
                return false;
            }
        }
        
        return true;
    }
}

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

import org.kuali.core.util.AuditError;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.rules.ErrorReporter;


/**
 * This class contains methods to "help" in validating {@link ProtocolAttachmentProtocol ProtocolAttachmentProtocol}.
 */
class ProtocolAttachmentProtocolRuleHelper {
    
    //FIXME: probably should find a better place for these constants
    private static final String NOTES_ATTACHMENTS_CLUSTER_LABEL = "Notes & Attachments";
    private static final String NOTES_AND_ATTACHMENT_AUDIT_ERRORS_KEY = "ProtocolAttachmentNotesAndAttachmentAuditErrors";

    private static final String COMPLETE_STATUS_CODE = "2";
    
    private final ErrorReporter errorReporter = new ErrorReporter();
    private String propertyPrefix;
    
    /**
     * Creates helper deferring the setting of the prefix to later.
     */
    ProtocolAttachmentProtocolRuleHelper() {
        super();
    }
    
    /**
     * Creates helper using prefix provided.
     *  
     * @param propertyPrefix the prefix (ex: notesAndAttachmentsHelper.newAttachmentProtocol)
     * @throws IllegalArgumentException if the propertyPrefix is null
     */
    ProtocolAttachmentProtocolRuleHelper(final String propertyPrefix) {
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
     * Checks that a type does not already exist for a document. Creates a hard error.
     * @param attachmentProtocol the attachment.
     * @param document the document
     * @return true is valid.
     */
    boolean duplicateType(final ProtocolAttachmentProtocol attachmentProtocol, final ProtocolDocument document) {
        
        for (ProtocolAttachmentProtocol attachment : document.getProtocol().getAttachmentProtocols()) {
            if (!attachment.getId().equals(attachmentProtocol.getId())
                && attachment.getTypeCode().equals(attachmentProtocol.getTypeCode())) {
                this.errorReporter.reportError(this.propertyPrefix + "." + ProtocolAttachmentBase.PropertyName.TYPE_CODE,
                    KeyConstants.ERROR_PROTOCOL_ATTACHMENT_DUPLICATE_TYPE);
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Checks that the status is marked complete. Creates an audit error.
     * @param attachmentProtocol the attachment.
     * @return true is valid.
     */
    boolean validStatusForSubmission(final ProtocolAttachmentProtocol attachmentProtocol) {
        if (!COMPLETE_STATUS_CODE.equals(attachmentProtocol.getStatusCode())) {
            final AuditError error = new AuditError(this.propertyPrefix + "." + ProtocolAttachmentProtocol.PropertyName.STATUS_CODE.getPropertyName(),
                KeyConstants.AUDIT_ERROR_PROTOCOL_ATTACHMENT_STATUS_COMPLETE, "noteAndAttachment");
            this.errorReporter.reportAuditError(error, NOTES_AND_ATTACHMENT_AUDIT_ERRORS_KEY, NOTES_ATTACHMENTS_CLUSTER_LABEL, Constants.AUDIT_ERRORS);
            return false;
        }
        return true;
    }
}

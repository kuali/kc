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

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.rules.ErrorReporter;


/**
 * This class contains methods to "help" in validating {@link ProtocolAttachmentProtocol ProtocolAttachmentProtocol}.
 */
class ProtocolAttachmentProtocolRuleHelper {

    private ProtocolAttachmentBaseRuleHelper helper;
    private String statusCodeProperty;
    private final ErrorReporter errorReporter = new ErrorReporter();
    
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
     * @param propertyPrefix the prefix (ex: notesAndAttachmentsHelper.newAttachmentProtocol)
     * @throws IllegalArgumentException if the propertyPrefix is null
     */
    public void resetPropertyPrefix(final String propertyPrefix) {
        if (propertyPrefix == null) {
            throw new IllegalArgumentException("propertyPrefix is null");
        }
        
        this.helper = new ProtocolAttachmentBaseRuleHelper(propertyPrefix);
        this.statusCodeProperty = propertyPrefix + ".statusCode";
    }
    
    /**
     * Checks for a valid status.
     * @param attachmentProtocol the attachment.
     * @return true is valid.
     */
    public boolean validStatus(final ProtocolAttachmentProtocol attachmentProtocol) {
        
        if (StringUtils.isBlank(attachmentProtocol.getStatusCode())) {
            this.errorReporter.reportError(this.statusCodeProperty, KeyConstants.ERROR_PROTOCOL_ATTACHMENT_MISSING_STATUS);
            return false;
        }
        return true;
    }
    
    /**
     * Checks that a type does not already exist for a document.
     * @param attachmentProtocol the attachment.
     * @param document the document
     * @return true is valid.
     */
    public boolean duplicateType(final ProtocolAttachmentProtocol attachmentProtocol, final ProtocolDocument document) {
        
        for (ProtocolAttachmentProtocol attachment : document.getProtocol().getAttachmentProtocols()) {
            if (!attachment.getId().equals(attachmentProtocol.getId())
                && attachment.getTypeCode().equals(attachmentProtocol.getTypeCode())) {
                this.errorReporter.reportError(this.helper.getTypeCodeProperty(), KeyConstants.ERROR_PROTOCOL_ATTACHMENT_DUPLICATE_TYPE);
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Gets the Status Code Property.
     * @return the Status Code Property
     */
    public String getStatusCodeProperty() {
        return this.statusCodeProperty;
    }
}

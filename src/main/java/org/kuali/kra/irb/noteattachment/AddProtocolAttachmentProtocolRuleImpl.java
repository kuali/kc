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
 * Implementation of {@link AddProtocolAttachmentProtocolRule AddProtocolAttachmentProtocolRule}.
 * @see AddProtocolAttachmentProtocolRule for details
 */
class AddProtocolAttachmentProtocolRuleImpl implements AddProtocolAttachmentProtocolRule {

    private static final String PROPERTY_PREFIX = "notesAndAttachmentsHelper.newAttachmentProtocol";
    private static final String STATUS_CODE = PROPERTY_PREFIX + ".statusCode";
    private final AddProtocolAttachmentBaseRuleHelper helper = new AddProtocolAttachmentBaseRuleHelper(PROPERTY_PREFIX);
    private final ErrorReporter errorReporter = new ErrorReporter();
    
    /** {@inheritDoc} */
    public boolean processAddProtocolAttachmentProtocolRules(AddProtocolAttachmentProtocolEvent event) {      
        final ProtocolDocument document = (ProtocolDocument) event.getDocument();
        final ProtocolAttachmentProtocol newAttachmentProtocol = event.getNewAttachmentProtocol();
        
        boolean valid = this.helper.validType(newAttachmentProtocol);
        valid &= validStatus(newAttachmentProtocol);
        valid &= this.helper.validFile(newAttachmentProtocol);
        valid &= this.helper.validDescription(newAttachmentProtocol);
        valid &= duplicateType(newAttachmentProtocol, document);
        
        return valid;
    }
    
    /**
     * Checks for a valid status.
     * @param newAttachmentProtocol the attachment.
     * @return true is valid.
     */
    private boolean validStatus(final ProtocolAttachmentProtocol newAttachmentProtocol) {
        
        if (StringUtils.isBlank(newAttachmentProtocol.getStatusCode())) {
            this.errorReporter.reportError(STATUS_CODE, KeyConstants.ERROR_PROTOCOL_ATTACHMENT_MISSING_STATUS);
            return false;
        }
        return true;
    }
    
    /**
     * Checks that a type does not already exist for a document.
     * @param newAttachmentProtocol the attachment.
     * @param document the document
     * @return true is valid.
     */
    private boolean duplicateType(final ProtocolAttachmentProtocol newAttachmentProtocol, final ProtocolDocument document) {
        
        for (ProtocolAttachmentProtocol attachment : document.getProtocol().getAttachmentProtocols()) {
            if (attachment.getTypeCode().equals(newAttachmentProtocol.getTypeCode())) {
                this.errorReporter.reportError(this.helper.getTypeCodeProperty(), KeyConstants.ERROR_PROTOCOL_ATTACHMENT_DUPLICATE_TYPE);
                return false;
            }
        }
        
        return true;
    }
}

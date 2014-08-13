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
package org.kuali.kra.irb.noteattachment;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentProtocolBase;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentProtocolRuleHelperBase;
import org.kuali.rice.kns.util.AuditError;


/**
 * This class contains methods to "help" in validating {@link ProtocolAttachmentProtocol ProtocolAttachmentProtocol}.
 */
class ProtocolAttachmentProtocolRuleHelper extends ProtocolAttachmentProtocolRuleHelperBase {
   
    /**
     * Creates helper using prefix provided.
     *  
     * @param aPropertyPrefix the prefix (ex: notesAttachmentsHelper.newAttachmentProtocol)
     * @throws IllegalArgumentException if the propertyPrefix is null
     */
    ProtocolAttachmentProtocolRuleHelper(final String aPropertyPrefix) {
        super(aPropertyPrefix);
    }
    
    
    /**
     * Creates helper deferring the setting of the prefix to later and setting used services.
     */
    ProtocolAttachmentProtocolRuleHelper() {
        super();
    }
    
    /**
     * Checks that the status is marked complete. Creates an audit error.
     * @param attachmentProtocol the attachment.
     * @return true is valid.
     */
    protected boolean validStatusForSubmission(final ProtocolAttachmentProtocolBase attachmentProtocol) {
        if (!StringUtils.equals(attachmentProtocol.getDocumentStatusCode(), "3")) {
            if (!ProtocolAttachmentProtocol.COMPLETE_STATUS_CODE.equals(attachmentProtocol.getStatusCode())) {
                final AuditError error = new AuditError(this.propertyPrefix + "." + ProtocolAttachmentProtocol.PropertyName.STATUS_CODE,
                    KeyConstants.AUDIT_ERROR_PROTOCOL_ATTACHMENT_STATUS_COMPLETE, NOTE_AND_ATTACHMENT_LINK);
                this.errorReporter.reportAuditError(error, NOTES_AND_ATTACHMENT_AUDIT_ERRORS_KEY, NOTES_ATTACHMENTS_CLUSTER_LABEL, Constants.AUDIT_ERRORS);
                return false;
            }
        }
        return true;
    }
}

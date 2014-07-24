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
package org.kuali.kra.protocol.noteattachment;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.rice.kns.util.AuditError;


/**
 * This class contains methods to "help" in validating {@link ProtocolAttachmentProtocolBase ProtocolAttachmentProtocolBase}.
 */
public abstract class ProtocolAttachmentProtocolRuleHelperBase {
    
    //FIXME: probably should find a better place for these constants
    protected static final String NOTES_ATTACHMENTS_CLUSTER_LABEL = "Notes & Attachments";
    protected static final String NOTES_AND_ATTACHMENT_AUDIT_ERRORS_KEY = "NoteAndAttachmentAuditErrors";
    protected static final String NOTE_AND_ATTACHMENT_LINK = "noteAndAttachment";

    protected ErrorReporter errorReporter;
    protected String propertyPrefix;
    

    protected ProtocolAttachmentProtocolRuleHelperBase() {
    }
    
    /**
     * New constructor added in uplift
     */
    protected ProtocolAttachmentProtocolRuleHelperBase (String aPropertyPrefix) {

        this.resetPropertyPrefix(aPropertyPrefix);        
    }
    
    /**
     * Resets the property prefix.
     * @param aPropertyPrefix the prefix (ex: notesAttachmentsHelper.newAttachmentProtocol)
     * @throws IllegalArgumentException if the propertyPrefix is null
     */
    public void resetPropertyPrefix(final String aPropertyPrefix) {
        if (aPropertyPrefix == null) {
            throw new IllegalArgumentException("propertyPrefix is null");
        }

        this.propertyPrefix = aPropertyPrefix;
    }
      
    /**
     * Checks that the status is marked complete. Creates an audit error.
     * @param attachmentProtocol the attachment.
     * @return true is valid.
     */
    protected boolean validStatusForSubmission(final ProtocolAttachmentProtocolBase attachmentProtocol) {
        if (!StringUtils.equals(attachmentProtocol.getDocumentStatusCode(), "3")) {
            if (!ProtocolAttachmentProtocolBase.COMPLETE_STATUS_CODE.equals(attachmentProtocol.getStatusCode())) {
                final AuditError error = new AuditError(this.propertyPrefix + "." + ProtocolAttachmentProtocolBase.PropertyName.STATUS_CODE,
                    KeyConstants.AUDIT_ERROR_PROTOCOL_ATTACHMENT_STATUS_COMPLETE, NOTE_AND_ATTACHMENT_LINK);
                this.errorReporter.reportAuditError(error, NOTES_AND_ATTACHMENT_AUDIT_ERRORS_KEY, NOTES_ATTACHMENTS_CLUSTER_LABEL, Constants.AUDIT_ERRORS);
                return false;
            }
        }
        return true;
    }
    
    /**
     * Validates that the selected status exists in the system (is valid).
     * Currently always returns true because status is not required.
     * @param attachment the attachment.
     * @return true if valid.
     */
    public boolean validStatus(final ProtocolAttachmentProtocolBase attachment) {
        
        //This assumes that the status object has been refreshed from the DB
        //and if not found the refresh action set the person to null.
        //This is an artifact of using anon keys
        
        //this got much more complex using anon keys
        if (attachment.getStatus() == null
            || StringUtils.isBlank(attachment.getStatus().getCode())) {
            //status not a required field
            return true;
        }
        return true;
    }

    public ErrorReporter getErrorReporter() {
        if (errorReporter == null) {
            KcServiceLocator.getService(ErrorReporter.class);
        }
        return errorReporter;
    }

    public void setErrorReporter(ErrorReporter errorReporter) {
        this.errorReporter = errorReporter;
    }
}

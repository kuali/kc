/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.protocol.noteattachment;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.rice.krad.util.AuditError;


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
                getErrorReporter().reportAuditError(error, NOTES_AND_ATTACHMENT_AUDIT_ERRORS_KEY, NOTES_ATTACHMENTS_CLUSTER_LABEL, Constants.AUDIT_ERRORS);
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
        	errorReporter = KcServiceLocator.getService(ErrorReporter.class);
        }
        return errorReporter;
    }

    public void setErrorReporter(ErrorReporter errorReporter) {
        this.errorReporter = errorReporter;
    }
}

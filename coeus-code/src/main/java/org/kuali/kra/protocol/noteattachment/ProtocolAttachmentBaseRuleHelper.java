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
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.rice.kns.service.DictionaryValidationService;

/**
 * This class contains methods to "help" in validating {@link ProtocolAttachmentBase ProtocolAttachmentBase}.
 */
public abstract class ProtocolAttachmentBaseRuleHelper {

    private static final String OTHER_TYPE_CODE = "9";

    private final ProtocolAttachmentService attachmentService;
    private final DictionaryValidationService validationService;
    private ErrorReporter errorReporter;
    
    private String propertyPrefix;
 
    /**
     * Creates helper deferring the setting of the prefix to later and setting used services.
     * @param attachmentService the Attachment Service
     * @throws IllegalArgumentException if the attachmentService is null
     */
    protected ProtocolAttachmentBaseRuleHelper(final ProtocolAttachmentService attachmentService,
        final DictionaryValidationService validationService) {
        if (attachmentService == null) {
            throw new IllegalArgumentException("the attachmentService is null");
        }
        
        if (validationService == null) {
            throw new IllegalArgumentException("the validationService is null");
        }
        
        this.attachmentService = attachmentService;
        this.validationService = validationService;
    }
    
    protected ProtocolAttachmentBaseRuleHelper(final String aPropertyPrefix,
            final ProtocolAttachmentService attachmentService,
            final DictionaryValidationService validationService) {
        if (attachmentService == null) {
            throw new IllegalArgumentException("the attachmentService is null");
        }
  
        if (validationService == null) {
            throw new IllegalArgumentException("the validationService is null");
        }
  
        this.attachmentService = attachmentService;
        this.validationService = validationService; 
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
     * Checks for a valid description when description is required. Creates a hard error.
     * @param <T> the attachment "type"
     * @param attachment the attachment.
     * @return true is valid.
     */
    public <T extends ProtocolAttachmentBase & TypedAttachment> boolean validDescriptionWhenRequired(final T attachment) {
        
        if (attachment.getType() == null || attachment.getType().getCode() == null) {
            return true;
        }
        
        if (StringUtils.isBlank(attachment.getDescription()) && OTHER_TYPE_CODE.equals(attachment.getType().getCode())) {
            final ProtocolAttachmentTypeBase type = this.attachmentService.getTypeFromCode(attachment.getType().getCode());
            this.errorReporter.reportError(this.propertyPrefix + "." + TypedAttachment.PropertyName.DESCRIPTION,
                KeyConstants.ERROR_PROTOCOL_ATTACHMENT_MISSING_DESC, (type != null) ? type.getDescription(): "");
            return false;
        }
        return true;
    }
    
    /**
     * Checks for a valid type for a group. Creates a hard error.
     * 
     * <p>
     * This method does not validate the existence of a type code.
     * This validation is done by {@link #validType(ProtocolAttachmentBase)}.
     * If the type code is blank this method will return true.
     * </p>
     * 
     * @param <T> the attachment "type"
     * @param attachment the attachment.
     * @return true is valid.
     */
    public <T extends ProtocolAttachmentBase & TypedAttachment> boolean validTypeForGroup(final T attachment) {
        
        if (attachment.getType() == null || attachment.getType().getCode() == null) {
            return true;
        }
        
        for (ProtocolAttachmentTypeBase type : this.attachmentService.getTypesForGroup(attachment.getGroupCode())) {
            if (type != null && attachment.getType().getCode().equals(type.getCode())) {
                return true;
            }
        }
        
        final ProtocolAttachmentTypeBase type = this.attachmentService.getTypeFromCode(attachment.getType().getCode());
        this.errorReporter.reportError(this.propertyPrefix + "." + TypedAttachment.PropertyName.TYPE_CODE,
            KeyConstants.ERROR_PROTOCOL_ATTACHMENT_INVALID_TYPE, (type != null) ? type.getDescription(): "");
        
        return false;
    }
    
    /**
     * Validates that the selected file contains valid fields. Creates a hard error.

     * @param attachmentBase the attachment.
     * @return true if valid.
     */
    boolean validFile(final ProtocolAttachmentBase attachmentBase) {
        //This assumes that the status object has been refreshed from the DB
        //and if not found the refresh action set the person to null.
        //This is an artifact of using anon keys
        
        final boolean valid;
        
        //this got much more complex using anon keys
        if (attachmentBase.getFile() == null) {
            valid = false;
            this.errorReporter.reportError(this.propertyPrefix + ".newFile",
                KeyConstants.ERROR_PROTOCOL_ATTACHMENT_MISSING_FILE);
        } else {
            valid = this.validationService.isBusinessObjectValid(attachmentBase.getFile(), this.propertyPrefix);
        }
        
        return valid;
    }
    
    /**
     * Validates the attachment's primitive fields (non reference fields). Creates a hard error.
     * 
     * @param attachmentBase the attachment
     * @return true if valid.
     */
    public boolean validPrimitiveFields(final ProtocolAttachmentBase attachmentBase) {
        
        final Long oldFileId = attachmentBase.getFileId();
        try {
            //adding a bogus file id to pass the validation service since the fileId is DB generated
            attachmentBase.setFileId(Long.valueOf(0));
            return this.validationService.isBusinessObjectValid(attachmentBase, this.propertyPrefix);
        } finally {
            attachmentBase.setFileId(oldFileId);
        }
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

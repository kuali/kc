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
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rules.ErrorReporter;
import org.kuali.rice.kns.service.DictionaryValidationService;

/**
 * This class contains methods to "help" in validating {@link ProtocolAttachmentBase ProtocolAttachmentBase}.
 */
class ProtocolAttachmentBaseRuleHelper {

    private static final String OTHER_TYPE_CODE = "9";

    private final ProtocolAttachmentService attachmentService;
    private final DictionaryValidationService validationService;
    private final ErrorReporter errorReporter = new ErrorReporter();
    
    private String propertyPrefix;
    
    /**
     * Creates helper deferring the setting of the prefix to later.
     */
    ProtocolAttachmentBaseRuleHelper() {
        this(KraServiceLocator.getService(ProtocolAttachmentService.class),
            KraServiceLocator.getService(DictionaryValidationService.class));
    }
    
    /**
     * Creates helper using prefix provided.
     *  
     * @param aPropertyPrefix the prefix (ex: notesAndAttachmentsHelper.newAttachmentProtocol)
     * @throws IllegalArgumentException if the propertyPrefix is null
     */
    ProtocolAttachmentBaseRuleHelper(final String aPropertyPrefix) {
        this();
        this.resetPropertyPrefix(aPropertyPrefix);
    }
    
    /**
     * Creates helper deferring the setting of the prefix to later and setting used services.
     * @param attachmentService the Attachment Service
     * @throws IllegalArgumentException if the attachmentService is null
     */
    ProtocolAttachmentBaseRuleHelper(final ProtocolAttachmentService attachmentService,
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
     * Checks for a valid description when description is required. Creates a hard error.
     * @param <T> the attachment "type"
     * @param attachment the attachment.
     * @return true is valid.
     */
    <T extends ProtocolAttachmentBase & TypedAttachment> boolean validDescriptionWhenRequired(final T attachment) {
        
        if (attachment.getType() == null || attachment.getType().getCode() == null) {
            return true;
        }
        
        if (StringUtils.isBlank(attachment.getDescription()) && OTHER_TYPE_CODE.equals(attachment.getType().getCode())) {
            final ProtocolAttachmentType type = this.attachmentService.getTypeFromCode(attachment.getType().getCode());
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
    <T extends ProtocolAttachmentBase & TypedAttachment> boolean validTypeForGroup(final T attachment) {
        
        if (attachment.getType() == null || attachment.getType().getCode() == null) {
            return true;
        }
        
        for (ProtocolAttachmentType type : this.attachmentService.getTypesForGroup(attachment.getGroupCode())) {
            if (type != null && attachment.getType().getCode().equals(type.getCode())) {
                return true;
            }
        }
        
        final ProtocolAttachmentType type = this.attachmentService.getTypeFromCode(attachment.getType().getCode());
        this.errorReporter.reportError(this.propertyPrefix + "." + TypedAttachment.PropertyName.TYPE_CODE,
            KeyConstants.ERROR_PROTOCOL_ATTACHMENT_INVALID_TYPE, (type != null) ? type.getDescription(): "");
        
        return false;
    }
    
    /**
     * Validates that the selected type exists in the system (is valid). Creates a hard error.
     * 
     * @param <T> the attachment "type"
     * @param attachment the attachment.
     * @return true if valid.
     */
    <T extends ProtocolAttachmentBase & TypedAttachment> boolean validType(final T attachment) {
        //This assumes that the status object has been refreshed from the DB
        //and if not found the refresh action set the person to null.
        //This is an artifact of using anon keys

        if (attachment.getType() == null
            || StringUtils.isBlank(attachment.getType().getCode())) {
            this.errorReporter.reportError(this.propertyPrefix + "." + TypedAttachment.PropertyName.TYPE_CODE,
                KeyConstants.ERROR_PROTOCOL_ATTACHMENT_MISSING_TYPE);
            return false;
        }
        return true;
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
            this.errorReporter.reportError(this.propertyPrefix + "." + ProtocolAttachmentBase.PropertyName.FILE_ID,
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
    boolean validPrimativeFields(final ProtocolAttachmentBase attachmentBase) {
        
        final Long oldFileId = attachmentBase.getFileId();
        try {
            //adding a bogus file id to pass the validation service since the fileId is DB generated
            attachmentBase.setFileId(Long.valueOf(0));
            return this.validationService.isBusinessObjectValid(attachmentBase, this.propertyPrefix);
        } finally {
            attachmentBase.setFileId(oldFileId);
        }
    }
}

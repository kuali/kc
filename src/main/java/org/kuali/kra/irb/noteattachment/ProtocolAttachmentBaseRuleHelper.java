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
import org.kuali.core.service.DictionaryValidationService;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rules.ErrorReporter;

/**
 * This class contains methods to "help" in validating {@link ProtocolAttachmentBase ProtocolAttachmentBase}.
 */
class ProtocolAttachmentBaseRuleHelper {

    private static final String OTHER_TYPE_CODE = "11";

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
     * Checks for a valid description. Creates a hard error.
     * @param attachmentBase the attachment.
     * @return true is valid.
     */
    boolean validDescription(final ProtocolAttachmentBase attachmentBase) {
        
        if (StringUtils.isBlank(attachmentBase.getDescription()) && OTHER_TYPE_CODE.equals(attachmentBase.getType().getCode())) {
            final ProtocolAttachmentType type = this.attachmentService.getTypeFromCode(attachmentBase.getType().getCode());
            this.errorReporter.reportError(this.propertyPrefix + "." + ProtocolAttachmentBase.PropertyName.DESCRIPTION,
                KeyConstants.ERROR_PROTOCOL_ATTACHMENT_MISSING_DESC, type.getDescription());
            return false;
        }
        return true;
    }
    
    /**
     * Checks for a valid type for a group. Creates a hard error.
     * 
     * <p>
     * This method does not validate the existence of a type code.
     * This validation is done by {@link #validAgainstDictionary(ProtocolAttachmentBase)}.
     * If the type code is blank this method will return true.
     * </p>
     * 
     * @param attachmentBase the attachment.
     * @return true is valid.
     */
    boolean validTypeForGroup(final ProtocolAttachmentBase attachmentBase) {
        
        if (attachmentBase.getType() == null || attachmentBase.getType().getCode() == null) {
            return true;
        }
        
        for (ProtocolAttachmentType type : this.attachmentService.getTypesForGroup(attachmentBase.getGroupCode())) {
            if (type != null && attachmentBase.getType().getCode().equals(type.getCode())) {
                return true;
            }
        }
        
        this.errorReporter.reportError(this.propertyPrefix + "." + ProtocolAttachmentBase.PropertyName.TYPE + ".code",
            KeyConstants.ERROR_PROTOCOL_ATTACHMENT_INVALID_TYPE, attachmentBase.getType().getDescription());
        
        return false;
    }
    
    
    /**
     * Executes the Data Dictionary Validation. Creates a hard error(s).
     * @param attachmentBase the attachment.
     * @return true if valid.
     */
    boolean validAgainstDictionary(final ProtocolAttachmentBase attachmentBase) {
//        //Since the file id is generated by the DB I need to set a file id
//        //to satisfy the validation service if a file has been set.
//        final Long oldFileId = attachmentBase.getFileId();
//        if (attachmentBase.getNewFile() != null && StringUtils.isNotBlank(attachmentBase.getNewFile().getFileName())) {
//            attachmentBase.setFileId(Long.valueOf(0));
//        }
        
        final boolean valid = this.validationService.isBusinessObjectValid(attachmentBase, this.propertyPrefix);
        
//        //reset the file id back.
//        attachmentBase.setFileId(oldFileId);
        return valid;
    }
}

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
import org.kuali.kra.rules.ErrorReporter;

/**
 * This class contains methods to "help" in validating {@link ProtocolAttachmentBase ProtocolAttachmentBase}.
 * 
 * <p>
 * This class is not a super class for other "ProtocolAttachment" rule classes because it would then require other class hierarchies
 * for other class types (like events) which really just creates class bloat.  In order to reuse the logic in this class rule classes
 * will use composition over inheritance.
 * </P>
 */
class AddProtocolAttachmentBaseRuleHelper {

    private final String newFileProperty;
    private final String descriptionProperty;
    private final String typeCodeProperty;
    private final ErrorReporter errorReporter = new ErrorReporter();
    
    /**
     * Creates helper using prefix provided.
     *  
     * @param propertyPrefix the prefix (ex: notesAndAttachmentsHelper.newAttachmentProtocol)
     * @throws IllegalArgumentException if the propertyPrefix is null
     */
    AddProtocolAttachmentBaseRuleHelper(final String propertyPrefix) {
        if (propertyPrefix == null) {
            throw new IllegalArgumentException("propertyPrefix is null");
        }
        
        this.newFileProperty = propertyPrefix + ".newFile";
        this.descriptionProperty = propertyPrefix + ".description";
        this.typeCodeProperty = propertyPrefix + ".typeCode";
    }
    
    /**
     * Checks for a valid file.
     * @param newAttachmentBase the attachment.
     * @return true is valid.
     */
    boolean validFile(final ProtocolAttachmentBase newAttachmentBase) {
        
        if (newAttachmentBase.getNewFile() == null) {
            this.errorReporter.reportError(this.newFileProperty, KeyConstants.ERROR_PROTOCOL_ATTACHMENT_MISSING_FILE);
            return false;
        }
        return true;
    }
    
    /**
     * Checks for a valid description.
     * @param newAttachmentBase the attachment.
     * @return true is valid.
     */
    boolean validDescription(final ProtocolAttachmentBase newAttachmentBase) {
        
        if (StringUtils.isBlank(newAttachmentBase.getDescription())) {
            this.errorReporter.reportError(this.descriptionProperty, KeyConstants.ERROR_PROTOCOL_ATTACHMENT_MISSING_DESCRIPTION);
            return false;
        }
        return true;
    }
    
    /**
     * Checks for a valid type.
     * @param newAttachmentBase the attachment.
     * @return true is valid.
     */
    boolean validType(final ProtocolAttachmentBase newAttachmentBase) {
        
        if (StringUtils.isBlank(newAttachmentBase.getTypeCode())) {
            this.errorReporter.reportError(this.typeCodeProperty, KeyConstants.ERROR_PROTOCOL_ATTACHMENT_MISSING_TYPE);
            return false;
        }
        return true;
    }
    
    /**
     * Gets the New File Property.
     * @return the New File Property
     */
    public String getNewFileProperty() {
        return this.newFileProperty;
    }

    /**
     * Gets the Description Property.
     * @return the Description Property
     */
    public String getDescriptionProperty() {
        return this.descriptionProperty;
    }

    /**
     * Gets the Type Code Property.
     * @return the Type Code Property
     */
    public String getTypeCodeProperty() {
        return this.typeCodeProperty;
    }
}

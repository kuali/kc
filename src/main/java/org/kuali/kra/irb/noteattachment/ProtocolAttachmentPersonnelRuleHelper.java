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
 * This class contains methods to "help" in validating {@link ProtocolAttachmentPersonnel ProtocolAttachmentPersonnel}.
 */
class ProtocolAttachmentPersonnelRuleHelper {
    
    private String personIdProperty;
    private final ErrorReporter errorReporter = new ErrorReporter();
    
    /**
     * Creates helper deferring the setting of the prefix to later.
     */
    ProtocolAttachmentPersonnelRuleHelper() {
        super();
    }
    
    /**
     * Creates helper using prefix provided.
     *  
     * @param propertyPrefix the prefix (ex: notesAndAttachmentsHelper.newAttachmentProtocol)
     * @throws IllegalArgumentException if the propertyPrefix is null
     */
    ProtocolAttachmentPersonnelRuleHelper(final String propertyPrefix) {
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
        
        this.personIdProperty = propertyPrefix + ".personId";
    }
    
    /**
     * Checks for a valid status.
     * @param attachmentPersonnel the attachment.
     * @return true is valid.
     */
    boolean validPerson(final ProtocolAttachmentPersonnel attachmentPersonnel) {
        
        if (StringUtils.isBlank(attachmentPersonnel.getPersonId())) {
            this.errorReporter.reportError(this.personIdProperty, KeyConstants.ERROR_PROTOCOL_ATTACHMENT_MISSING_PERSON);
            return false;
        }
        return true;
    }
    
    /**
     * Gets the Person Id Property.
     * @return the Person Id Property
     */
    public String getPersonIdProperty() {
        return this.personIdProperty;
    }
}

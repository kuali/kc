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
package org.kuali.coeus.common.protocol.impl.note;

import org.kuali.coeus.common.protocol.framework.note.ProtocolNotepadBase;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.kns.service.KNSServiceLocator;

/**
 * This class contains methods to "help" in validating {@link org.kuali.coeus.common.protocol.framework.note.ProtocolNotepadBase ProtocolNotepadBase}.
 */
class ProtocolNotepadRuleHelper {

    private final DictionaryValidationService validationService;
    
    private String propertyPrefix;
    
    /**
     * Creates helper deferring the setting of the prefix to later.
     */
    ProtocolNotepadRuleHelper() {
        this(KNSServiceLocator.getKNSDictionaryValidationService());
    }
    
    /**
     * Creates helper using prefix provided.
     *  
     * @param aPropertyPrefix the prefix (ex: notesAttachmentsHelper.newProtocolNotepad)
     * @throws IllegalArgumentException if the propertyPrefix is null
     */
    ProtocolNotepadRuleHelper(final String aPropertyPrefix) {
        this();
        this.resetPropertyPrefix(aPropertyPrefix);
    }
    
    /**
     * Creates helper deferring the setting of the prefix to later and setting used services.
     * @throws IllegalArgumentException if the validationService is null
     */
    ProtocolNotepadRuleHelper(final DictionaryValidationService validationService) {
        if (validationService == null) {
            throw new IllegalArgumentException("the validationService is null");
        }
        
        this.validationService = validationService;
    }
    
    /**
     * Resets the property prefix.
     * @param aPropertyPrefix the prefix (ex: notesAttachmentsHelper.newAttachmentProtocol)
     * @throws IllegalArgumentException if the propertyPrefix is null
     */
    void resetPropertyPrefix(final String aPropertyPrefix) {
        if (aPropertyPrefix == null) {
            throw new IllegalArgumentException("propertyPrefix is null");
        }
        
        this.propertyPrefix = aPropertyPrefix;
    }
    
    /**
     * Validates the attachment's primitive fields (non reference fields). Creates a hard error.
     * 
     * @return true if valid.
     */
    boolean validPrimitiveFields(final ProtocolNotepadBase notepad) {
        return this.validationService.isBusinessObjectValid(notepad, this.propertyPrefix);
    }
}

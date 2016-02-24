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
package org.kuali.kra.irb.noteattachment;

import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.kns.service.KNSServiceLocator;

/**
 * This class contains methods to "help" in validating {@link ProtocolNotepad ProtocolNotepad}.
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
    boolean validPrimitiveFields(final ProtocolNotepad notepad) {
        return this.validationService.isBusinessObjectValid(notepad, this.propertyPrefix);
    }
}

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
package org.kuali.kra.coi.notesandattachments;

import org.kuali.kra.coi.notesandattachments.notes.CoiDisclosureNotepad;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.kns.service.KNSServiceLocator;

public class CoiDisclosureNotepadRuleHelper {
private final DictionaryValidationService validationService;
    
private final String propertyPrefix = "coiNotesAndAttachmentsHelper.newCoiDisclosureNotepad";
    
    /**
     * Creates helper deferring the setting of the prefix to later.
     */
    CoiDisclosureNotepadRuleHelper() {
        this(KNSServiceLocator.getKNSDictionaryValidationService());
    }
    
    /**
     * Creates helper deferring the setting of the prefix to later and setting used services.
     * @throws IllegalArgumentException if the validationService is null
     */
    CoiDisclosureNotepadRuleHelper(final DictionaryValidationService validationService) {
        if (validationService == null) {
            throw new IllegalArgumentException("the validationService is null");
        }
        
        this.validationService = validationService;
    }
    
   
    /**
     * Validates the attachment's primitive fields (non reference fields). Creates a hard error.
     * 
     * @return true if valid.
     */
    boolean validPrimitiveFields(final CoiDisclosureNotepad notepad) {
        return this.validationService.isBusinessObjectValid(notepad, this.propertyPrefix);
    }
}

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

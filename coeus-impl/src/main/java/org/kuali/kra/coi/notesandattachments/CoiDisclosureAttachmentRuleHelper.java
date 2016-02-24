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

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.coi.notesandattachments.attachments.CoiDisclosureAttachment;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.kns.service.KNSServiceLocator;

public class CoiDisclosureAttachmentRuleHelper {
    private final DictionaryValidationService validationService;
    private final ErrorReporter errorReporter = KcServiceLocator.getService(ErrorReporter.class);
    private final String propertyPrefix = "coiNotesAndAttachmentsHelper.newCoiDisclosureAttachment";


    CoiDisclosureAttachmentRuleHelper() {
        this(KNSServiceLocator.getKNSDictionaryValidationService());
    }
    
    public CoiDisclosureAttachmentRuleHelper(final DictionaryValidationService validationService) {
           
            if (validationService == null) {
                throw new IllegalArgumentException("the validationService is null");
            }            
            this.validationService = validationService;
    }

    public boolean validPrimitiveFields(CoiDisclosureAttachment newCoiDisclosureAttachment) {
        Long oldFileId = newCoiDisclosureAttachment.getFileId();
        boolean valid = true;
        try {
            //adding a bogus file id to pass the validation service since the fileId is DB generated
            newCoiDisclosureAttachment.setFileId(Long.valueOf(0));
            valid &= this.validationService.isBusinessObjectValid(newCoiDisclosureAttachment, propertyPrefix);
        } finally {
            newCoiDisclosureAttachment.setFileId(oldFileId);
        }  
        return valid;
    }

    public boolean validFile(CoiDisclosureAttachment newCoiDisclosureAttachment) {
        final boolean valid;
        
        if (newCoiDisclosureAttachment.getFile() == null) {
            valid = false;
            this.errorReporter.reportError(propertyPrefix + ".newFile",
                KeyConstants.ERROR_COI_ATTACHMENT_MISSING_FILE);
        } else {
            valid = this.validationService.isBusinessObjectValid(newCoiDisclosureAttachment.getFile());
        }
        
        return valid;
    }


}

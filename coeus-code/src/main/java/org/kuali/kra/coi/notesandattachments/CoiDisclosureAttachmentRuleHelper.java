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

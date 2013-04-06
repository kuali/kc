/*
 * Copyright 2005-2013 The Kuali Foundation
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

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.coi.notesandattachments.attachments.CoiDisclosureAttachment;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentService;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentType;
import org.kuali.kra.irb.noteattachment.TypedAttachment;
import org.kuali.kra.rules.ErrorReporter;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.util.GlobalVariables;

public class CoiDisclosureAttachmentRuleHelper {
    private final DictionaryValidationService validationService;
    private final ErrorReporter errorReporter = new ErrorReporter();
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

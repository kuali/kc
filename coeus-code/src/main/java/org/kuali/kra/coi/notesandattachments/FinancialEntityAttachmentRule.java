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

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.kra.coi.notesandattachments.attachments.FinancialEntityAttachment;
import org.kuali.kra.coi.personfinancialentity.AddFinancialEntityAttachmentEvent;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.GlobalVariables;

public class FinancialEntityAttachmentRule extends KcTransactionalDocumentRuleBase implements KcBusinessRule<AddFinancialEntityAttachmentEvent> {

    public boolean validPrimitiveFields(FinancialEntityAttachment newFinancialEntityAttachment) {
        Long oldFileId = newFinancialEntityAttachment.getFileId();
        try {
            //adding a bogus file id to pass the validation service since the fileId is DB generated
            newFinancialEntityAttachment.setFileId(Long.valueOf(0));
            return getDictionaryValidationService().isBusinessObjectValid(newFinancialEntityAttachment);
        } finally {
            newFinancialEntityAttachment.setFileId(oldFileId);
        }    
    }

    public boolean validFile(FinancialEntityAttachment newFinancialEntityAttachment) {
        final boolean valid;
        
        if (newFinancialEntityAttachment.getAttachmentFile() == null) {
            valid = false;
            reportError("newFile",
                KeyConstants.ERROR_COI_ATTACHMENT_MISSING_FILE);
        } else {
            valid = getDictionaryValidationService().isBusinessObjectValid(newFinancialEntityAttachment.getAttachmentFile());
        }
        
        return valid;
    }

    public boolean processRules(AddFinancialEntityAttachmentEvent event) {
        GlobalVariables.getMessageMap().addToErrorPath(event.getErrorPathPrefix());
        boolean result = validPrimitiveFields(event.getAttachment());
        result &= validFile(event.getAttachment());
        GlobalVariables.getMessageMap().removeFromErrorPath(event.getErrorPathPrefix());
        return result;
    }


}

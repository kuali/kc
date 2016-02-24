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

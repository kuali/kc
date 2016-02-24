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
package org.kuali.kra.protocol.actions.reviewcomments;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * 
 * This class implements the business rule when adding new review attachment.  ie, validate description and file are entered.
 */
public class ProtocolAddReviewAttachmentRule<E extends ProtocolAddReviewAttachmentEventBase<?>> extends KcTransactionalDocumentRuleBase implements KcBusinessRule<E> {
    
    @Override
    public boolean processRules(E event) {
        boolean isValid = true;
        
        String errorPathKey = event.getPropertyName() + ".newReviewAttachment";
        GlobalVariables.getMessageMap().addToErrorPath(errorPathKey);
        getDictionaryValidationService().validateBusinessObject(event.getReviewAttachment());
        if (event.getReviewAttachment().getNewFile() == null || StringUtils.isBlank(event.getReviewAttachment().getNewFile().getFileName())) {
            GlobalVariables.getMessageMap().putError("newFile",
            KeyConstants.ERROR_PROTOCOL_ATTACHMENT_MISSING_FILE);
        }
        GlobalVariables.getMessageMap().removeFromErrorPath(errorPathKey);
        
        isValid &= GlobalVariables.getMessageMap().hasNoErrors();
        
        return isValid;
    }

}

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

/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.irb.onlinereview.rules;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.onlinereview.event.AddProtocolOnlineReviewAttachmentEvent;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * 
 * This class implements validation rule when adding new OLR review attachment.
 */
public class AddOnlineReviewAttachmentRule  extends ResearchDocumentRuleBase implements BusinessRuleInterface<AddProtocolOnlineReviewAttachmentEvent> {
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.rule.BusinessRuleInterface#processRules(org.kuali.kra.rule.event.KraDocumentEventBaseExtension)
     */
    public boolean processRules(AddProtocolOnlineReviewAttachmentEvent event) {
        boolean isValid = true;
        
        String errorPathKey = event.getPropertyName() + "newReviewAttachment";
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

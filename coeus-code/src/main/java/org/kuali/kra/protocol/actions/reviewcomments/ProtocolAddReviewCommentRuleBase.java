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

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * Validates the rules for a ProtocolBase Risk Level add action.
 */
public abstract class ProtocolAddReviewCommentRuleBase<E extends ProtocolAddReviewCommentEventBase> extends KcTransactionalDocumentRuleBase implements KcBusinessRule<E> {
    
    @Override
    public boolean processRules(E event) {
        boolean isValid = true;
        
        String errorPathKey = event.getPropertyName() + ".newReviewComment";
        GlobalVariables.getMessageMap().addToErrorPath(errorPathKey);
        getDictionaryValidationService().validateBusinessObject(event.getReviewComment());
        GlobalVariables.getMessageMap().removeFromErrorPath(errorPathKey);
        
        isValid &= GlobalVariables.getMessageMap().hasNoErrors();
        
        return isValid;
    }

}

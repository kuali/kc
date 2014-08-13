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
package org.kuali.kra.iacuc.species.exception.rule;

import org.kuali.kra.iacuc.species.exception.IacucProtocolException;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

/**
 * This class is implementation of <code>AddProtocolExceptionRule</code> interface. Impl makes sure necessary rules are satisfied 
 * before object can be used.
 */
public class ProtocolExceptionRule extends KcTransactionalDocumentRuleBase implements AddProtocolExceptionRule {
    private static final String NEW_PROTOCOL_EXCEPTION_PATH = "iacucProtocolExceptionHelper.newIacucProtocolException";

    @Override
    public boolean processAddProtocolExceptionBusinessRules(AddProtocolExceptionEvent addProtocolExceptionEvent) {
        
        boolean rulePassed = true;
        MessageMap errorMap = GlobalVariables.getMessageMap();
        errorMap.addToErrorPath(NEW_PROTOCOL_EXCEPTION_PATH);
        IacucProtocolException protocolException = addProtocolExceptionEvent.getProtocolException();
        
        getDictionaryValidationService().validateBusinessObject(protocolException);
        rulePassed &= GlobalVariables.getMessageMap().hasNoErrors();

        errorMap.removeFromErrorPath(NEW_PROTOCOL_EXCEPTION_PATH);

        return rulePassed;
    }

}

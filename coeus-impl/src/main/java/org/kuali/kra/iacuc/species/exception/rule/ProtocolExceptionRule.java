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

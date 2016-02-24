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
package org.kuali.kra.protocol.questionnaire;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;

public class SaveProtocolQuestionnaireRule  implements KcBusinessRule<SaveProtocolQuestionnaireEvent> {
    private final ErrorReporter errorReporter = KcServiceLocator.getService(ErrorReporter.class);
    

    @Override
    public boolean processRules(SaveProtocolQuestionnaireEvent event) {
        boolean valid = true;
        // check if any of the answer headers from the event has the "not updated" flag set 
        int answerHeaderIndex = 0;
        for(AnswerHeader header: event.getAnswerHeaders()) {
            if(header.isNotUpdated()){
                valid = false;
                String propertyName = "questionnaireHelper.answerHeaders[" + answerHeaderIndex + "]";
                errorReporter.reportError(propertyName, KeyConstants.ERROR_QUESTIONNAIRE_NOT_UPDATED);
            }            
            answerHeaderIndex++;
        }
        return valid;
    }

}

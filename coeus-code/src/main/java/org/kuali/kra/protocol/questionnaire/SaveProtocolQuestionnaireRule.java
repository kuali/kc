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

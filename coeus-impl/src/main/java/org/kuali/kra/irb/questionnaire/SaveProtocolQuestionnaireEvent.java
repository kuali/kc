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
package org.kuali.kra.irb.questionnaire;

import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.rice.krad.document.Document;

import java.util.List;


public class SaveProtocolQuestionnaireEvent extends KcDocumentEventBaseExtension {
    private List<AnswerHeader> answerHeaders;

    public SaveProtocolQuestionnaireEvent(Document document, List<AnswerHeader> answerHeaders) {
        super("Saving Protocol Questionnaires (and answers) to document" + getDocumentId(document), Constants.EMPTY_STRING, document);        
        this.answerHeaders = answerHeaders;
    }

    @Override
    public SaveProtocolQuestionnaireRule getRule() {
        return new SaveProtocolQuestionnaireRule();
    }
    
    public List<AnswerHeader> getAnswerHeaders() {
        return answerHeaders;
    }

}

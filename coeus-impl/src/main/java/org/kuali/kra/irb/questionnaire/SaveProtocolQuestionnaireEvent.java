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

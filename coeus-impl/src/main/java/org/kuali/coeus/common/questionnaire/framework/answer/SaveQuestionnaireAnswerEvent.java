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
package org.kuali.coeus.common.questionnaire.framework.answer;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.document.Document;

import java.util.List;

/**
 * Encapsulates the Save Questionnaire Answer event.
 */
public class SaveQuestionnaireAnswerEvent extends KcDocumentEventBaseExtension {

    private List<AnswerHeader> answerHeaders;
    private String formProperty = "questionnaireHelper";
    
    /**
     * Constructs a QuestionnaireAnswerEvent.
     * @param description
     * @param errorPathPrefix
     * @param document
     */
    public SaveQuestionnaireAnswerEvent(Document document, List<AnswerHeader> answerHeaders) {
        super("Saving QuestionnaireAnswer to document" + getDocumentId(document), Constants.EMPTY_STRING, document);
        
        this.answerHeaders = answerHeaders;
    }
    
    /**
     * Constructs a QuestionnaireAnswerEvent.
     * @param description
     * @param errorPathPrefix
     * @param document
     */
    public SaveQuestionnaireAnswerEvent(Document document, List<AnswerHeader> answerHeaders, String formProperty) {
        super("Saving QuestionnaireAnswer to document" + getDocumentId(document), Constants.EMPTY_STRING, document);
        this.formProperty = formProperty;
        this.answerHeaders = answerHeaders;
    }
    

    @Override
    @SuppressWarnings("unchecked")
    public KcBusinessRule getRule() {
        return new SaveQuestionnaireAnswerRule();
    }
    
    public List<AnswerHeader> getAnswerHeaders() {
        return answerHeaders;
    }
    
    /**
     * Gets the formProperty attribute. 
     * @return Returns the formProperty.
     */
    public String getFormProperty() {
        return formProperty;
    }

    /**
     * Sets the formProperty attribute value.
     * @param formProperty The formProperty to set.
     */
    public void setFormProperty(String formProperty) {
        this.formProperty = formProperty;
    }



}

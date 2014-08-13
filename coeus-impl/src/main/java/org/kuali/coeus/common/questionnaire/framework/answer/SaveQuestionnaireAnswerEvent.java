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
/*
 * Copyright 2006-2009 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.questionnaire.answer;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.questionnaire.question.Question;

public class QuestionnaireAnswer extends KraPersistableBusinessObjectBase { 
    
    private static final long serialVersionUID = 1L;

    private Long questionnaireAnswerId; 

    private Integer questionNumber; 
    private Integer answerNumber; 
    private String answer; 
    
    private String questionnaireAnswerHeaderId; 
    private QuestionnaireAnswerHeader questionnaireAnswerHeader; 
    private Integer questionId;
    private Question question; 
    
    /**
     * Gets the questionnaireAnswerId attribute. 
     * @return Returns the questionnaireAnswerId.
     */
    public Long getQuestionnaireAnswerId() {
        return this.questionnaireAnswerId;
    }

    /**
     * Sets the questionnaireAnswerId attribute value.
     * @param questionnaireAnswerId The questionnaireAnswersId to set.
     */
    public void setQuestionnaireAnswerId(Long questionnaireAnswerId) {
        this.questionnaireAnswerId = questionnaireAnswerId;
    }

    /**
     * Gets the questionnaireCompletionId attribute. 
     * @return Returns the questionnaireCompletionId.
     */
    public String getQuestionnaireAnswerHeaderId() {
        return this.questionnaireAnswerHeaderId;
    }

    /**
     * Sets the questionnaireAnswerHeaderId attribute value.
     * @param questionnaireAnswerHeaderId The questionnaireAnswerHeaderId to set.
     */
    public void setQuestionnaireAnswerHeaderId(String questionnaireAnswerHeaderId) {
        this.questionnaireAnswerHeaderId = questionnaireAnswerHeaderId;
    }

    /**
     * Gets the questionId attribute. 
     * @return Returns the questionId.
     */
    public Integer getQuestionId() {
        return this.questionId;
    }

    /**
     * Sets the questionId attribute value.
     * @param questionId The questionId to set.
     */
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    /**
     * Gets the questionNumber attribute. 
     * @return Returns the questionNumber.
     */
    public Integer getQuestionNumber() {
        return this.questionNumber;
    }

    /**
     * Sets the questionNumber attribute value.
     * @param questionNumber The questionNumber to set.
     */
    public void setQuestionNumber(Integer questionNumber) {
        this.questionNumber = questionNumber;
    }

    /**
     * Gets the answerNumber attribute. 
     * @return Returns the answerNumber.
     */
    public Integer getAnswerNumber() {
        return this.answerNumber;
    }

    /**
     * Sets the answerNumber attribute value.
     * @param answerNumber The answerNumber to set.
     */
    public void setAnswerNumber(Integer answerNumber) {
        this.answerNumber = answerNumber;
    }

    /**
     * Gets the answer attribute. 
     * @return Returns the answer.
     */
    public String getAnswer() {
        return this.answer;
    }

    /**
     * Sets the answer attribute value.
     * @param answer The answer to set.
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Gets the questionnaireAnsHeader attribute. 
     * @return Returns the questionnaireAnsHeader.
     */
    public QuestionnaireAnswerHeader getQuestionnaireAnsHeader() {
        return this.questionnaireAnswerHeader;
    }

    /**
     * Sets the questionnaireAnswerHeader attribute value.
     * @param questionnaireAnswerHeader The questionnaireAnswerHeader to set.
     */
    public void setQuestionnaireAnswerHeader(QuestionnaireAnswerHeader questionnaireAnswerHeader) {
        this.questionnaireAnswerHeader = questionnaireAnswerHeader;
    }

    /**
     * Gets the question attribute. 
     * @return Returns the question.
     */
    public Question getQuestion() {
        return this.question;
    }

    /**
     * Sets the question attribute value.
     * @param question The question to set.
     */
    public void setQuestion(Question question) {
        this.question = question;
    }

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("questionnaireAnswersId", this.getQuestionnaireAnswerId());
        hashMap.put("questionnaireAnswerHeaderId", this.getQuestionnaireAnswerHeaderId());
        hashMap.put("questionId", this.getQuestionId());
        hashMap.put("questionNumber", this.getQuestionNumber());
        hashMap.put("answerNumber", this.getAnswerNumber());
        hashMap.put("answer", this.getAnswer());
        return hashMap;
    }
}

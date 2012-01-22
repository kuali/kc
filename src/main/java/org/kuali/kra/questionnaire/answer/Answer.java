/*
 * Copyright 2005-2010 The Kuali Foundation
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

import java.util.List;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.questionnaire.QuestionnaireQuestion;
import org.kuali.kra.questionnaire.question.Question;

/**
 * Holds a single answer for a {@link Question Question}.
 */
public class Answer extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer questionNumber;

    private Integer answerNumber;

    private String answer;

    private Long answerHeaderIdFk;

    private AnswerHeader answerHeader;

    private Long questionRefIdFk;

    private Question question;

    private Long questionnaireQuestionsIdFk;

    private QuestionnaireQuestion questionnaireQuestion;

    // transient field : indicate whether a child should be displayed  
    private String matchedChild = "N";

    private List<Answer> parentAnswer;

    /**
     * Gets the id attribute.
     * 
     * @return Returns the id.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Sets the id attribute value.
     * 
     * @param id The questionnaireAnswersId to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the questionId attribute.
     * 
     * @return Returns the questionId.
     */
    public Long getQuestionRefIdFk() {
        return this.questionRefIdFk;
    }

    /**
     * Sets the questionId attribute value.
     * 
     * @param questionRefIdFk The questionId to set.
     */
    public void setQuestionRefIdFk(Long questionRefIdFk) {
        this.questionRefIdFk = questionRefIdFk;
    }

    /**
     * Gets the questionNumber attribute.
     * 
     * @return Returns the questionNumber.
     */
    public Integer getQuestionNumber() {
        return this.questionNumber;
    }

    /**
     * Sets the questionNumber attribute value.
     * 
     * @param questionNumber The questionNumber to set.
     */
    public void setQuestionNumber(Integer questionNumber) {
        this.questionNumber = questionNumber;
    }

    /**
     * Gets the answerNumber attribute.
     * 
     * @return Returns the answerNumber.
     */
    public Integer getAnswerNumber() {
        return this.answerNumber;
    }

    /**
     * Sets the answerNumber attribute value.
     * 
     * @param answerNumber The answerNumber to set.
     */
    public void setAnswerNumber(Integer answerNumber) {
        this.answerNumber = answerNumber;
    }

    /**
     * Gets the answer attribute.
     * 
     * @return Returns the answer.
     */
    public String getAnswer() {
        return this.answer;
    }

    /**
     * Sets the answer attribute value.
     * 
     * @param answer The answer to set.
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Gets the questionnaireAnsHeader attribute.
     * 
     * @return Returns the questionnaireAnsHeader.
     */
    public AnswerHeader getAnswerHeader() {
        return this.answerHeader;
    }

    /**
     * Sets the answerHeader attribute value.
     * 
     * @param answerHeader The answerHeader to set.
     */
    public void setAnswerHeader(AnswerHeader answerHeader) {
        this.answerHeader = answerHeader;
    }

    /**
     * Gets the question attribute.
     * 
     * @return Returns the question.
     */
    public Question getQuestion() {
        return this.question;
    }

    /**
     * Sets the question attribute value.
     * 
     * @param question The question to set.
     */
    public void setQuestion(Question question) {
        this.question = question;
    }

    public Long getAnswerHeaderIdFk() {
        return answerHeaderIdFk;
    }

    public void setAnswerHeaderIdFk(Long answerHeaderIdFk) {
        this.answerHeaderIdFk = answerHeaderIdFk;
    }

    public Long getQuestionnaireQuestionsIdFk() {
        return questionnaireQuestionsIdFk;
    }

    public void setQuestionnaireQuestionsIdFk(Long questionnaireQuestionsIdFk) {
        this.questionnaireQuestionsIdFk = questionnaireQuestionsIdFk;
    }

    public QuestionnaireQuestion getQuestionnaireQuestion() {
        return questionnaireQuestion;
    }

    public void setQuestionnaireQuestion(QuestionnaireQuestion questionnaireQuestion) {
        this.questionnaireQuestion = questionnaireQuestion;
    }

    public String getMatchedChild() {
        return matchedChild;
    }

    public void setMatchedChild(String matchedChild) {
        this.matchedChild = matchedChild;
    }

    public List<Answer> getParentAnswer() {
        return parentAnswer;
    }

    public void setParentAnswer(List<Answer> parentAnswer) {
        this.parentAnswer = parentAnswer;
    }
}

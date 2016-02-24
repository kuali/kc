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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireQuestion;
import org.kuali.coeus.common.questionnaire.framework.question.Question;
import org.kuali.coeus.common.questionnaire.api.answer.AnswerContract;

import java.util.List;

/**
 * Holds a single answer for a {@link Question Question}.
 */
public class Answer extends KcPersistableBusinessObjectBase implements AnswerContract {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer questionNumber;

    private Integer answerNumber;

    private String answer;

    private Long answerHeaderId;

    private Long questionId;

    private Long questionnaireQuestionsId;

    private Question question;

    private AnswerHeader answerHeader;

    private QuestionnaireQuestion questionnaireQuestion;

    // transient field : indicate whether a child should be displayed  
    private String matchedChild = "N";
    
    private boolean ruleMatched = false;

    private List<Answer> parentAnswers;

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    @Override
    public Integer getQuestionSeqId() {
        return this.question.getQuestionSeqId();
    }

    @Override
    public Integer getQuestionNumber() {
        return this.questionNumber;
    }

    public void setQuestionNumber(Integer questionNumber) {
        this.questionNumber = questionNumber;
    }

    @Override
    public Integer getAnswerNumber() {
        return this.answerNumber;
    }

    public void setAnswerNumber(Integer answerNumber) {
        this.answerNumber = answerNumber;
    }

    @Override
    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public AnswerHeader getAnswerHeader() {
        return this.answerHeader;
    }

    public void setAnswerHeader(AnswerHeader answerHeader) {
        this.answerHeader = answerHeader;
    }

    public Question getQuestion() {
        return this.question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public Long getAnswerHeaderId() {
        return answerHeaderId;
    }

    public void setAnswerHeaderId(Long answerHeaderId) {
        this.answerHeaderId = answerHeaderId;
    }

    @Override
    public Long getQuestionnaireQuestionsId() {
        return questionnaireQuestionsId;
    }

    public void setQuestionnaireQuestionsId(Long questionnaireQuestionsId) {
        this.questionnaireQuestionsId = questionnaireQuestionsId;
    }

    public QuestionnaireQuestion getQuestionnaireQuestion() {
        return questionnaireQuestion;
    }

    public void setQuestionnaireQuestion(QuestionnaireQuestion questionnaireQuestion) {
        this.questionnaireQuestion = questionnaireQuestion;
    }

    /**
     * 
     * This method is to get the flag whether to display the child question or not based on parent answer
     * revised : this may also based rule evaluation, and eventually root node may be applied for rule evaluation.
     */
    public String getMatchedChild() {
        return matchedChild;
    }

    public void setMatchedChild(String matchedChild) {
        this.matchedChild = matchedChild;
    }

    @Override
    public List<Answer> getParentAnswers() {
        return parentAnswers;
    }

    public void setParentAnswers(List<Answer> parentAnswers) {
        this.parentAnswers = parentAnswers;
    }

    public boolean isRuleMatched() {
        return ruleMatched;
    }

    public void setRuleMatched(boolean ruleMatched) {
        this.ruleMatched = ruleMatched;
    }

    public boolean isAnswered() {
        if (!StringUtils.isBlank(this.getAnswer())) {
            return true;
        }
        for (Answer answer: answerHeader.getAnswers()) {
            if (answer.getQuestionNumber().equals(questionNumber) && !StringUtils.isBlank(answer.getAnswer())) {
                return true;
            }
        }
        return false;
    }
}

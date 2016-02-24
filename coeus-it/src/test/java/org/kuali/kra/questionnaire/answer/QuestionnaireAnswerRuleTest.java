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
package org.kuali.kra.questionnaire.answer;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.common.questionnaire.framework.answer.Answer;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.answer.SaveQuestionnaireAnswerEvent;
import org.kuali.coeus.common.questionnaire.framework.answer.SaveQuestionnaireAnswerRule;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireQuestion;
import org.kuali.coeus.common.questionnaire.framework.question.Question;
import org.kuali.kra.rules.TemplateRuleTest;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.util.ArrayList;
import java.util.List;

public class QuestionnaireAnswerRuleTest extends KcIntegrationTestBase {

    /**
     * 
     * This method is to test that all answer fields are ok.
     */
    @Test
    public void testRuleIsOK() {
        new TemplateRuleTest<SaveQuestionnaireAnswerEvent, SaveQuestionnaireAnswerRule>() {
            
            @Override
            protected void prerequisite() {
                List<AnswerHeader> answerHeaders = new ArrayList<AnswerHeader>();
                answerHeaders.add(createAnswerHeaderForVersioning(1L, "0912000001", "0"));
                
                event = new SaveQuestionnaireAnswerEvent(null, answerHeaders);
                rule = new SaveQuestionnaireAnswerRule();
                expectedReturnValue = true;
            }
            
            @Override
            public void checkRuleAssertions() {
                MessageMap messages = GlobalVariables.getMessageMap();
                Assert.assertEquals(0, messages.getErrorMessages().size());
            }
            
        };
    }
    
    /**
     * 
     * This method is to test that a couple answer fields have validation error.
     */
    @Test
    public void testRuleIsNotOK() {
        new TemplateRuleTest<SaveQuestionnaireAnswerEvent, SaveQuestionnaireAnswerRule>() {
            
            @Override
            protected void prerequisite() {
                List<AnswerHeader> answerHeaders = new ArrayList<AnswerHeader>();
                answerHeaders.add(createAnswerHeaderForVersioning(1L, "0912000001", "0"));
                answerHeaders.get(0).getAnswers().get(1).setAnswer("1x");
                answerHeaders.get(0).getAnswers().get(2).setAnswer("01012009");
                
                event = new SaveQuestionnaireAnswerEvent(null, answerHeaders);
                rule = new SaveQuestionnaireAnswerRule();
                expectedReturnValue = false;
            }
            
            @Override
            public void checkRuleAssertions() {
                MessageMap messages = GlobalVariables.getMessageMap();
                Assert.assertEquals(2, messages.getErrorMessages().size());
                Assert.assertTrue(messages.getErrorMessages().containsKey("questionnaireHelper.answerHeaders[0].answers[1].answer"));
                Assert.assertTrue(messages.getErrorMessages().containsKey("questionnaireHelper.answerHeaders[0].answers[2].answer"));
                Assert.assertEquals("error.invalidFormat.withFormat(Answer 2, 1x, Number - [0-9])", messages.getErrorMessages().get("questionnaireHelper.answerHeaders[0].answers[1].answer").get(0).toString());
                Assert.assertEquals("error.invalidFormat(Answer 3, 01012009, Date - [xx/xx/xxxx])", messages.getErrorMessages().get("questionnaireHelper.answerHeaders[0].answers[2].answer").get(0).toString());
            }
            
        };
    }

    private AnswerHeader createAnswerHeaderForVersioning(Long questionnaireRefId, String moduleItemKey,String moduleSubItemKey) {
        AnswerHeader answerHeader = new AnswerHeader();
        answerHeader.setModuleItemCode(CoeusModule.IRB_MODULE_CODE);
        answerHeader.setModuleItemKey(moduleItemKey);
        answerHeader.setModuleSubItemKey(moduleSubItemKey);
        answerHeader.setQuestionnaireId(questionnaireRefId);
        answerHeader.setAnswers(new ArrayList<Answer>());
        Answer answer = createAnswer(1,"Y");
        answer.setQuestionNumber(1);
        answer.setQuestionnaireQuestion(createQuestionnaireQuestion(1, 5L));
        answer.setQuestion(answer.getQuestionnaireQuestion().getQuestion());
        answer.setQuestionId(answer.getQuestionnaireQuestion().getQuestion().getId());
        answerHeader.getAnswers().add(answer);
        answer = createAnswer(1,"10");
        answer.setQuestionNumber(3);
        answer.setQuestionnaireQuestion(createChildQuestionnaireQuestion(3,1,"1","Y",3L));
        answer.setQuestion(answer.getQuestionnaireQuestion().getQuestion());
        answer.setQuestionId(answer.getQuestionnaireQuestion().getQuestion().getId());
        answerHeader.getAnswers().add(answer);
        answer = createAnswer(1,"01/01/2009");
        answer.setQuestionNumber(2);
        answer.setQuestionnaireQuestion(createQuestionnaireQuestion(2,4L));
        answer.setQuestion(answer.getQuestionnaireQuestion().getQuestion());
        answer.setQuestionId(answer.getQuestionnaireQuestion().getQuestion().getId());
        answerHeader.getAnswers().add(answer);
        return answerHeader;
        
    }

    private Answer createAnswer(Integer answerNumber, String ans) {
        Answer answer = new Answer();
        answer.setAnswer(ans);
        answer.setAnswerNumber(answerNumber);
        return answer;
        
    }
    
    private QuestionnaireQuestion createQuestionnaireQuestion(Integer questionNumber, Long questionTypeId) {
        QuestionnaireQuestion questionnaireQuestion = new QuestionnaireQuestion();
        questionnaireQuestion.setQuestionNumber(questionNumber);
        questionnaireQuestion.setParentQuestionNumber(0);
        questionnaireQuestion.setQuestion(createQuestion(Long.parseLong(questionNumber.toString()), questionNumber, questionTypeId));
        return questionnaireQuestion;
        
    }
    
    private QuestionnaireQuestion createChildQuestionnaireQuestion(Integer questionNumber, Integer parentQuestionNumber, String condition, String conditionValue, Long questionTypeId) {
        QuestionnaireQuestion questionnaireQuestion = createQuestionnaireQuestion(questionNumber, questionTypeId) ;
        questionnaireQuestion.setParentQuestionNumber(parentQuestionNumber);
        questionnaireQuestion.setCondition(condition);
        questionnaireQuestion.setConditionFlag(true);
        questionnaireQuestion.setConditionValue(conditionValue);
        return questionnaireQuestion;
        
    }
    
    private Question createQuestion(Long questionRefId, Integer questionId, Long questionTypeId) {
        Question question = new Question();
        question.setId(questionRefId);
        question.setQuestionSeqId(questionId);
        question.setQuestionTypeId(questionTypeId);
        question.setMaxAnswers(1);
        return question;
        
    }

}

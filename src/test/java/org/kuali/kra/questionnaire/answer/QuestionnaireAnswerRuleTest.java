/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.questionnaire.answer;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.questionnaire.QuestionnaireQuestion;
import org.kuali.kra.questionnaire.question.Question;
import org.kuali.kra.rules.TemplateRuleTest;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

public class QuestionnaireAnswerRuleTest extends KcUnitTestBase {

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
        answerHeader.setQuestionnaireRefIdFk(questionnaireRefId.toString());
        answerHeader.setAnswers(new ArrayList<Answer>());
        Answer answer = createAnswer(1,"Y");
        answer.setQuestionNumber(1);
        answer.setQuestionnaireQuestion(createQuestionnaireQuestion(1, 5));
        answer.setQuestion(answer.getQuestionnaireQuestion().getQuestion());
        answer.setQuestionRefIdFk(answer.getQuestionnaireQuestion().getQuestion().getQuestionRefId());
        answerHeader.getAnswers().add(answer);
        answer = createAnswer(1,"10");
        answer.setQuestionNumber(3);
        answer.setQuestionnaireQuestion(createChildQuestionnaireQuestion(3,1,"1","Y",3));
        answer.setQuestion(answer.getQuestionnaireQuestion().getQuestion());
        answer.setQuestionRefIdFk(answer.getQuestionnaireQuestion().getQuestion().getQuestionRefId());
        answerHeader.getAnswers().add(answer);
        answer = createAnswer(1,"01/01/2009");
        answer.setQuestionNumber(2);
        answer.setQuestionnaireQuestion(createQuestionnaireQuestion(2,4));
        answer.setQuestion(answer.getQuestionnaireQuestion().getQuestion());
        answer.setQuestionRefIdFk(answer.getQuestionnaireQuestion().getQuestion().getQuestionRefId());
        answerHeader.getAnswers().add(answer);
        return answerHeader;
        
    }

    private Answer createAnswer(Integer answerNumber, String ans) {
        Answer answer = new Answer();
        answer.setAnswer(ans);
        answer.setAnswerNumber(answerNumber);
        return answer;
        
    }
    
    private QuestionnaireQuestion createQuestionnaireQuestion(Integer questionNumber, Integer questionTypeId) {
        QuestionnaireQuestion questionnaireQuestion = new QuestionnaireQuestion();
        questionnaireQuestion.setQuestionNumber(questionNumber);
        questionnaireQuestion.setParentQuestionNumber(0);
        questionnaireQuestion.setQuestion(createQuestion(Long.parseLong(questionNumber.toString()), questionNumber, questionTypeId));
        return questionnaireQuestion;
        
    }
    
    private QuestionnaireQuestion createChildQuestionnaireQuestion(Integer questionNumber, Integer parentQuestionNumber, String condition, String conditionValue, Integer questionTypeId) {
        QuestionnaireQuestion questionnaireQuestion = createQuestionnaireQuestion(questionNumber, questionTypeId) ;
        questionnaireQuestion.setParentQuestionNumber(parentQuestionNumber);
        questionnaireQuestion.setCondition(condition);
        questionnaireQuestion.setConditionFlag(true);
        questionnaireQuestion.setConditionValue(conditionValue);
        return questionnaireQuestion;
        
    }
    
    private Question createQuestion(Long questionRefId, Integer questionId, Integer questionTypeId) {
        Question question = new Question();
        question.setQuestionRefId(questionRefId);
        question.setQuestionIdFromInteger(questionId); 
        question.setQuestionTypeId(questionTypeId);
        question.setMaxAnswers(1);
        return question;
        
    }

}
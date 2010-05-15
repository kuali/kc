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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.questionnaire.QuestionnaireQuestion;
import org.kuali.kra.questionnaire.question.Question;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.MessageMap;

public class QuestionnaireAnswerRuleTest extends KraTestBase {
    private QuestionnaireAnswerRule rule;

    @Before
    public void setUp() throws Exception {
        super.setUp();
       rule = new QuestionnaireAnswerRule();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        rule = null;
    }

    /**
     * 
     * This method is to test that all answer fields are ok.
     */
    @Test
    public void testRuleIsOK() {
        
        
        List<AnswerHeader> answerHeaders = new ArrayList<AnswerHeader>();
        answerHeaders.add(createAnswerHeaderForVersioning(1L, "0912000001", "0"));
        rule.processQuestionnaireAnswerRules(answerHeaders);
        MessageMap messages = GlobalVariables.getMessageMap();
        Assert.assertEquals(0, messages.getErrorMessages().size());
        //assertEquals(answerHeader, questionnaireAnswerServiceImpl.getNewVersionAnswerHeader(new ModuleQuestionnaireBean(CoeusModule.IRB_MODULE_CODE, protocol), questionnaire));
    }
    
    /**
     * 
     * This method is to test that a couple answer fields have validation error.
     */
    @Test
    public void testRuleIsNotOK() {
        
        
        List<AnswerHeader> answerHeaders = new ArrayList<AnswerHeader>();
        answerHeaders.add(createAnswerHeaderForVersioning(1L, "0912000001", "0"));
        answerHeaders.get(0).getAnswers().get(1).setAnswer("1x");
        answerHeaders.get(0).getAnswers().get(2).setAnswer("01012009");
        rule.processQuestionnaireAnswerRules(answerHeaders);
        MessageMap messages = GlobalVariables.getMessageMap();
        Assert.assertEquals(2, messages.getErrorMessages().size());
        Assert.assertTrue(messages.getErrorMessages().containsKey("questionnaireHelper.answerHeaders[0].answers[1].answer"));
        Assert.assertTrue(messages.getErrorMessages().containsKey("questionnaireHelper.answerHeaders[0].answers[2].answer"));
        Assert.assertEquals("error.invalidFormat.withFormat(Answer 2, 1x, Number - [0-9])", messages.getErrorMessages().get("questionnaireHelper.answerHeaders[0].answers[1].answer").get(0).toString());
        Assert.assertEquals("error.invalidFormat(Answer 3, 01012009, Date - [xx/xx/xxxx])", messages.getErrorMessages().get("questionnaireHelper.answerHeaders[0].answers[2].answer").get(0).toString());
    }

    private AnswerHeader createAnswerHeaderForVersioning(Long questionnaireRefId, String moduleItemKey,String moduleSubItemKey) {
        AnswerHeader answerHeader = new AnswerHeader();
        answerHeader.setModuleItemCode(CoeusModule.IRB_MODULE_CODE);
        answerHeader.setModuleItemKey(moduleItemKey);
        answerHeader.setModuleSubItemKey(moduleSubItemKey);
        answerHeader.setQuestionnaireRefIdFk(questionnaireRefId);
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
        question.setQuestionId(questionId); 
        question.setQuestionTypeId(questionTypeId);
        question.setMaxAnswers(1);
        return question;
        
    }

}

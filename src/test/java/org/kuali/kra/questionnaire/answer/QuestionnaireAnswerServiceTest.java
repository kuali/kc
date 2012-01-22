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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.apache.commons.lang.StringUtils;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolFinderDao;
import org.kuali.kra.questionnaire.Questionnaire;
import org.kuali.kra.questionnaire.QuestionnaireQuestion;
import org.kuali.kra.questionnaire.QuestionnaireUsage;
import org.kuali.kra.questionnaire.question.Question;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ObjectUtils;

@RunWith(JMock.class)
public class QuestionnaireAnswerServiceTest {
    private Mockery context = new JUnit4Mockery();
    
    /**
     * 
     * This method is to test a new questionnaire answer header is created for new version of questionnaire.
     * answers from the old version questionnaire answer will not be copied over.
     */
    @Test
    public void testGetNewVersionAnswerHeader() {
        QuestionnaireAnswerServiceImpl questionnaireAnswerServiceImpl = new QuestionnaireAnswerServiceImpl();
        
        
        final Map <String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("moduleItemCode", CoeusModule.IRB_MODULE_CODE);
        fieldValues.put("moduleSubItemCode", "0");
        final Map <String, String> fieldValues1 = new HashMap<String, String>();
        fieldValues1.put("questionnaireId", "1");

        final Questionnaire questionnairenew = getQuestionnaire(1, 1, 2L); 
        questionnairenew.getQuestionnaireQuestions().add(createChildQuestionnaireQuestion(4,1,"1","N"));
        final Collection<QuestionnaireUsage> usages = new ArrayList<QuestionnaireUsage>();
        final Collection<Questionnaire> questionnaires = new ArrayList<Questionnaire>();
        questionnaires.add(questionnairenew);
        QuestionnaireUsage questionnaireUsage = createQuestionnaireUsage(1L, "Test Questionnaire New");
        questionnaireUsage.setQuestionnaire(questionnairenew);
        usages.add(questionnaireUsage);
        final Questionnaire questionnaire = getQuestionnaire(1, 0, 1L); 
        
        final Protocol protocol = new Protocol(){
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                // do nothing
            }

        };
        protocol.setProtocolNumber("0912000001");
        protocol.setSequenceNumber(1);
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            one(businessObjectService).findMatching(QuestionnaireUsage.class, fieldValues); will(returnValue(usages));
            one(businessObjectService).findMatchingOrderBy(Questionnaire.class,
                    fieldValues1, "sequenceNumber", false); will(returnValue(questionnaires));
       }});
        questionnaireAnswerServiceImpl.setBusinessObjectService(businessObjectService);
        
        AnswerHeader answerHeader = questionnaireAnswerServiceImpl.getNewVersionAnswerHeader(new ModuleQuestionnaireBean(CoeusModule.IRB_MODULE_CODE, protocol.getProtocolNumber(), "0", protocol.getSequenceNumber().toString(), false), questionnaire);
        Assert.assertEquals(4, answerHeader.getAnswers().size());
        Assert.assertEquals(1, answerHeader.getAnswers().get(0).getQuestionNumber().intValue());
        Assert.assertEquals(3, answerHeader.getAnswers().get(1).getQuestionNumber().intValue());
        Assert.assertEquals(4, answerHeader.getAnswers().get(2).getQuestionNumber().intValue());
        Assert.assertEquals(2, answerHeader.getAnswers().get(3).getQuestionNumber().intValue());
        Assert.assertTrue(StringUtils.isBlank(answerHeader.getAnswers().get(0).getAnswer()));
        Assert.assertTrue(StringUtils.isBlank(answerHeader.getAnswers().get(1).getAnswer()));
        Assert.assertTrue(StringUtils.isBlank(answerHeader.getAnswers().get(2).getAnswer()));
        Assert.assertTrue(StringUtils.isBlank(answerHeader.getAnswers().get(3).getAnswer()));
        //assertEquals(answerHeader, questionnaireAnswerServiceImpl.getNewVersionAnswerHeader(new ModuleQuestionnaireBean(CoeusModule.IRB_MODULE_CODE, protocol), questionnaire));
    }

    /*
     * set up a questionnaire for test
     */
    private Questionnaire getQuestionnaire(Integer questionnaireId,Integer sequenceNumber, Long id) {
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setQuestionnaireIdFromInteger(questionnaireId);
        questionnaire.setQuestionnaireRefIdFromLong(id);
        questionnaire.setIsFinal(true);
        questionnaire.setSequenceNumber(sequenceNumber);
        List<QuestionnaireQuestion> questionnaireQuestions = new ArrayList<QuestionnaireQuestion>();
        questionnaireQuestions.add(createQuestionnaireQuestion(1));
        questionnaireQuestions.add(createQuestionnaireQuestion(2));
        questionnaireQuestions.add(createChildQuestionnaireQuestion(3,1,"1","Y"));
            
        questionnaire.setQuestionnaireQuestions(questionnaireQuestions);
        List<QuestionnaireUsage> questionnaireUsages = new ArrayList<QuestionnaireUsage>();
        questionnaireUsages.add(createQuestionnaireUsage(1L, "Test Questionnaire"));
        questionnaire.setQuestionnaireUsages(questionnaireUsages);
        
        return questionnaire;
        
    }
    
    /*
     * set up a questionnaire question for test
     */
    private QuestionnaireQuestion createQuestionnaireQuestion(Integer questionNumber) {
        QuestionnaireQuestion questionnaireQuestion = new QuestionnaireQuestion();
        questionnaireQuestion.setQuestionNumber(questionNumber);
        questionnaireQuestion.setQuestionSeqNumber(questionNumber);
        questionnaireQuestion.setParentQuestionNumber(0);
        questionnaireQuestion.setQuestion(createQuestion(Long.parseLong(questionNumber.toString()), questionNumber));
        return questionnaireQuestion;
        
    }
    
    /*
     * set up child questionnaire question for test
     */
    private QuestionnaireQuestion createChildQuestionnaireQuestion(Integer questionNumber, Integer parentQuestionNumber, String condition, String conditionValue) {
        QuestionnaireQuestion questionnaireQuestion = createQuestionnaireQuestion(questionNumber) ;
        questionnaireQuestion.setParentQuestionNumber(parentQuestionNumber);
        questionnaireQuestion.setCondition(condition);
        questionnaireQuestion.setConditionFlag(true);
        questionnaireQuestion.setConditionValue(conditionValue);
        return questionnaireQuestion;
        
    }

    /*
     * set up a question for test
     */
    private Question createQuestion(Long questionRefId, Integer questionId) {
        Question question = new Question();
        question.setQuestionRefId(questionRefId);
        question.setQuestionIdFromInteger(questionId); 
        question.setMaxAnswers(1);
        return question;
        
    }
    
    /*
     * set up questionnaire usage
     */
    private QuestionnaireUsage createQuestionnaireUsage(Long questionnaireRefId, String label) {
        QuestionnaireUsage questionnaireUsage = new QuestionnaireUsage();
        questionnaireUsage.setModuleItemCode(CoeusModule.IRB_MODULE_CODE);
        questionnaireUsage.setModuleSubItemCode("0");
        questionnaireUsage.setQuestionnaireRefIdFk(questionnaireRefId.toString());
        questionnaireUsage.setQuestionnaireLabel(label);
        return questionnaireUsage;
        
    }

    /**
     * 
     * This method to test that a new version of questionnaire answer header is created when Protocol is versioned.
     */
    @Test
    public void testVersioningQuestionnaireAnswer() {
        QuestionnaireAnswerServiceImpl questionnaireAnswerServiceImpl = new QuestionnaireAnswerServiceImpl();
        
        ModuleQuestionnaireBean moduleQuestionnaireBean = new ModuleQuestionnaireBean(CoeusModule.IRB_MODULE_CODE, "0912000001", "0", "0", false);
        final Map <String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("moduleItemCode", CoeusModule.IRB_MODULE_CODE);
        fieldValues.put("moduleItemKey", "0912000001");
        fieldValues.put("moduleSubItemKey", "0");
        fieldValues.put("moduleSubItemCode", "0");
        final Map <String, String> fieldValues1 = new HashMap<String, String>();
        fieldValues1.put("questionnaireId", "1");
        final Map <String, String> fieldValues2 = new HashMap<String, String>();
        fieldValues2.put("moduleItemCode", CoeusModule.IRB_MODULE_CODE);
        fieldValues2.put("moduleSubItemCode", "0");

        final Questionnaire questionnaire = getQuestionnaire(1, 0, 1L); 
       final Collection<AnswerHeader> headers = new ArrayList<AnswerHeader>();
        AnswerHeader answerHeaderOld = createAnswerHeaderForVersioning(1L, "0912000001", "0");
        answerHeaderOld.setQuestionnaire(questionnaire);
        headers.add(answerHeaderOld);
        final Collection<Questionnaire> questionnaires = new ArrayList<Questionnaire>();
        questionnaires.add(questionnaire);
        final Collection<QuestionnaireUsage> usages = new ArrayList<QuestionnaireUsage>();
        QuestionnaireUsage questionnaireUsage = createQuestionnaireUsage(1L, "Test Questionnaire New");
        questionnaireUsage.setQuestionnaire(questionnaire);
        usages.add(questionnaireUsage);

        final Protocol protocol = new Protocol(){
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                // do nothing
            }

        };
        protocol.setProtocolNumber("0912000001");
        protocol.setSequenceNumber(0);
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            one(businessObjectService).findMatching(AnswerHeader.class, fieldValues); will(returnValue(headers));
            one(businessObjectService).findMatching(QuestionnaireUsage.class, fieldValues2); will(returnValue(usages));
           one(businessObjectService).findMatchingOrderBy(Questionnaire.class,
                    fieldValues1, "sequenceNumber", false); will(returnValue(questionnaires));
        }});
        questionnaireAnswerServiceImpl.setBusinessObjectService(businessObjectService);
        
        AnswerHeader answerHeader = questionnaireAnswerServiceImpl.versioningQuestionnaireAnswer(new ModuleQuestionnaireBean(CoeusModule.IRB_MODULE_CODE, protocol.getProtocolNumber(),"0", protocol.getSequenceNumber().toString(), false), 1).get(0);
        Assert.assertEquals(3, answerHeader.getAnswers().size());
        Assert.assertEquals("1", answerHeader.getModuleSubItemKey());
        Assert.assertEquals("0912000001", answerHeader.getModuleItemKey());
        Assert.assertEquals(1, answerHeader.getAnswers().get(0).getQuestionNumber().intValue());
        Assert.assertEquals(answerHeaderOld.getAnswers().get(0).getAnswer(), answerHeader.getAnswers().get(0).getAnswer());
        Assert.assertEquals(3, answerHeader.getAnswers().get(1).getQuestionNumber().intValue());
        Assert.assertEquals(answerHeaderOld.getAnswers().get(1).getAnswer(), answerHeader.getAnswers().get(1).getAnswer());
        Assert.assertEquals(2, answerHeader.getAnswers().get(2).getQuestionNumber().intValue());
        Assert.assertEquals(answerHeaderOld.getAnswers().get(2).getAnswer(), answerHeader.getAnswers().get(2).getAnswer());
        //assertEquals(answerHeader, questionnaireAnswerServiceImpl.getNewVersionAnswerHeader(new ModuleQuestionnaireBean(CoeusModule.IRB_MODULE_CODE, protocol), questionnaire));
    }

    /*
     * set up answer header for test
     */
    private AnswerHeader createAnswerHeaderForVersioning(Long questionnaireRefId, String moduleItemKey,String moduleSubItemKey) {
        AnswerHeader answerHeader = new AnswerHeader();
        answerHeader.setModuleItemCode(CoeusModule.IRB_MODULE_CODE);
        answerHeader.setModuleItemKey(moduleItemKey);
        answerHeader.setModuleSubItemKey(moduleSubItemKey);
        answerHeader.setQuestionnaireRefIdFk(questionnaireRefId.toString());
        answerHeader.setAnswers(new ArrayList<Answer>());
        Answer answer = createAnswer(1,"Y");
        answer.setQuestionNumber(1);
        answer.setQuestionnaireQuestion(createQuestionnaireQuestion(1));
        answer.setQuestion(answer.getQuestionnaireQuestion().getQuestion());
        answer.setQuestionRefIdFk(answer.getQuestionnaireQuestion().getQuestion().getQuestionRefId());
        answerHeader.getAnswers().add(answer);
        answer = createAnswer(1,"10");
        answer.setQuestionNumber(3);
        answer.setQuestionnaireQuestion(createChildQuestionnaireQuestion(3,1,"1","Y"));
        answer.setQuestion(answer.getQuestionnaireQuestion().getQuestion());
        answer.setQuestionRefIdFk(answer.getQuestionnaireQuestion().getQuestion().getQuestionRefId());
        answerHeader.getAnswers().add(answer);
        answer = createAnswer(1,"Test");
        answer.setQuestionNumber(2);
        answer.setQuestionnaireQuestion(createQuestionnaireQuestion(2));
        answer.setQuestion(answer.getQuestionnaireQuestion().getQuestion());
        answer.setQuestionRefIdFk(answer.getQuestionnaireQuestion().getQuestion().getQuestionRefId());
        answerHeader.getAnswers().add(answer);
        return answerHeader;
        
    }
    
    /*
     * set up answer for test
     */
    private Answer createAnswer(Integer answerNumber, String ans) {
        Answer answer = new Answer();
        answer.setAnswer(ans);
        answer.setAnswerNumber(answerNumber);
        answer.setMatchedChild("Y");
        return answer;
        
    }

    
    /**
     * 
     * This method to test the creation of new questionnaire answer header for new version of questionnaire.
     * Answers from old questionnaire is copied over if question is in the same version.
     */
    @Test
    public void testCopyAnswerToNewVersion() {
        QuestionnaireAnswerServiceImpl questionnaireAnswerServiceImpl = new QuestionnaireAnswerServiceImpl();
        
        
        final AnswerHeader oldAnswerHeader = createAnswerHeaderForVersioning(1L, "0912000001", "0");
        final AnswerHeader newAnswerHeader = createAnswerHeaderForVersioning(2L, "0912000001", "0");
        for (Answer answer : newAnswerHeader.getAnswers()) {
            answer.setAnswer("");
        }
        oldAnswerHeader.setCompleted(true);
        
        // add a new answer to new version
        Answer answer = createAnswer(1,"");
        answer.setQuestionNumber(4);
        answer.setQuestionnaireQuestion(createQuestionnaireQuestion(4));
        answer.setQuestion(answer.getQuestionnaireQuestion().getQuestion());
        answer.setQuestionRefIdFk(answer.getQuestionnaireQuestion().getQuestion().getQuestionRefId());
        newAnswerHeader.getAnswers().add(answer);

        // this question is a new version, so answer should not be copied
        answer = newAnswerHeader.getAnswers().get(2);
        answer.setQuestionRefIdFk(5L);
        answer.getQuestion().setQuestionRefId(5L);
                
        questionnaireAnswerServiceImpl.copyAnswerToNewVersion(oldAnswerHeader, newAnswerHeader);
        Assert.assertEquals(4, newAnswerHeader.getAnswers().size());
        Assert.assertEquals("0", newAnswerHeader.getModuleSubItemKey());
        Assert.assertEquals("0912000001", newAnswerHeader.getModuleItemKey());
        Assert.assertEquals(1, newAnswerHeader.getAnswers().get(0).getQuestionNumber().intValue());
        Assert.assertEquals(oldAnswerHeader.getAnswers().get(0).getAnswer(), newAnswerHeader.getAnswers().get(0).getAnswer());
        Assert.assertEquals(3, newAnswerHeader.getAnswers().get(1).getQuestionNumber().intValue());
        Assert.assertEquals(oldAnswerHeader.getAnswers().get(1).getAnswer(), newAnswerHeader.getAnswers().get(1).getAnswer());
        Assert.assertEquals(2, newAnswerHeader.getAnswers().get(2).getQuestionNumber().intValue());
        Assert.assertEquals("", newAnswerHeader.getAnswers().get(2).getAnswer());
        Assert.assertEquals(4, newAnswerHeader.getAnswers().get(3).getQuestionNumber().intValue());
        Assert.assertEquals("", newAnswerHeader.getAnswers().get(3).getAnswer());
        Assert.assertFalse(newAnswerHeader.getCompleted());
        //assertEquals(answerHeader, questionnaireAnswerServiceImpl.getNewVersionAnswerHeader(new ModuleQuestionnaireBean(CoeusModule.IRB_MODULE_CODE, protocol), questionnaire));
    }

    /**
     * 
     * This method is to test to get questionnaire answer if it exists and to create new questionnaire
     * answer header if it does not exist.
     */
    @Test
    public void testGetQuestionnaireAnswer() {
        // answerheader(0) is set up as existing one
        // answerheader(1) is a newly created one
        QuestionnaireAnswerServiceImpl questionnaireAnswerServiceImpl = new QuestionnaireAnswerServiceImpl();
        
        ModuleQuestionnaireBean moduleQuestionnaireBean = new ModuleQuestionnaireBean(CoeusModule.IRB_MODULE_CODE, "0912000001", "0", "0", false);
        final Map <String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("moduleItemCode", CoeusModule.IRB_MODULE_CODE);
        fieldValues.put("moduleItemKey", "0912000001");
        fieldValues.put("moduleSubItemCode", "0");
        fieldValues.put("moduleSubItemKey", "0");
        final Map <String, String> fieldValues2 = new HashMap<String, String>();
        fieldValues2.put("questionnaireId", "1");
        final Map <String, String> fieldValues3 = new HashMap<String, String>();
        fieldValues3.put("questionnaireId", "2");

        final Collection<AnswerHeader> headers = new ArrayList<AnswerHeader>();
        AnswerHeader answerHeader = createAnswerHeaderForVersioning(1L, "0912000001", "0");
        headers.add(answerHeader);
        final Questionnaire questionnaire = getQuestionnaire(1, 0, 1L); 
        answerHeader.setQuestionnaire(questionnaire);
        final Collection<Questionnaire> questionnaires = new ArrayList<Questionnaire>();
        questionnaires.add(questionnaire);
        final Protocol protocol = new Protocol(){
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                // do nothing
            }

        };
        protocol.setProtocolNumber("0912000001");
        protocol.setSequenceNumber(0);
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            one(businessObjectService).findMatching(AnswerHeader.class, fieldValues); will(returnValue(headers));
        }});
        
        //fieldValues = new HashMap<String, String>();
        final Map <String, String> fieldValues1 = new HashMap<String, String>();
        fieldValues1.put("moduleItemCode", CoeusModule.IRB_MODULE_CODE);
        Questionnaire questionnairenew = getQuestionnaire(2, 0, 2L); 
        fieldValues1.put("moduleSubItemCode", "0");
        questionnairenew.getQuestionnaireQuestions().add(createChildQuestionnaireQuestion(4,1,"1","N"));
        final Collection<QuestionnaireUsage> usages = new ArrayList<QuestionnaireUsage>();
        QuestionnaireUsage questionnaireUsage = createQuestionnaireUsage(1L, "Test Questionnaire 1");
        questionnaireUsage.setQuestionnaire(questionnairenew);
        final Collection<Questionnaire> questionnaires1 = new ArrayList<Questionnaire>();
        questionnaires1.add(questionnairenew);
        List<QuestionnaireUsage> workUsages = new ArrayList<QuestionnaireUsage>();
        workUsages.add(questionnaireUsage);
        questionnairenew.setQuestionnaireUsages(workUsages);
        usages.add(questionnaireUsage);
        questionnaireUsage = createQuestionnaireUsage(2L, "Test Questionnaire 2");
        questionnaireUsage.setQuestionnaire(questionnaire);
        usages.add(questionnaireUsage);
        workUsages.clear();
        workUsages.add(questionnaireUsage);
        questionnaire.setQuestionnaireUsages(workUsages);

        //context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            one(businessObjectService).findMatching(QuestionnaireUsage.class, fieldValues1); will(returnValue(usages));
            one(businessObjectService).findMatchingOrderBy(Questionnaire.class,
                    fieldValues2, "sequenceNumber", false); will(returnValue(questionnaires));
            one(businessObjectService).findMatchingOrderBy(Questionnaire.class,
                            fieldValues3, "sequenceNumber", false); will(returnValue(questionnaires1));
       }});

        
        questionnaireAnswerServiceImpl.setBusinessObjectService(businessObjectService);
        
        List<AnswerHeader> answerHeaders = questionnaireAnswerServiceImpl.getQuestionnaireAnswer(new ModuleQuestionnaireBean(CoeusModule.IRB_MODULE_CODE, protocol.getProtocolNumber(),"0", protocol.getSequenceNumber().toString(), false));
        Assert.assertEquals(2, answerHeaders.size());
        Assert.assertEquals(3, answerHeaders.get(0).getAnswers().size());
        Assert.assertEquals(4, answerHeaders.get(1).getAnswers().size());
        Assert.assertFalse(answerHeaders.get(1).getCompleted());
        Assert.assertTrue(answerHeaders.get(0).getCompleted());
        Assert.assertEquals("Y", answerHeaders.get(0).getAnswers().get(0).getAnswer());
        Assert.assertEquals("10", answerHeaders.get(0).getAnswers().get(1).getAnswer());
        Assert.assertEquals("Test", answerHeaders.get(0).getAnswers().get(2).getAnswer());
        Assert.assertTrue(StringUtils.isBlank(answerHeaders.get(1).getAnswers().get(0).getAnswer()));
        Assert.assertTrue(StringUtils.isBlank(answerHeaders.get(1).getAnswers().get(1).getAnswer()));
        Assert.assertTrue(StringUtils.isBlank(answerHeaders.get(1).getAnswers().get(2).getAnswer()));
        Assert.assertTrue(StringUtils.isBlank(answerHeaders.get(1).getAnswers().get(3).getAnswer()));
    }
    
    
    @Test
    public void testcheckIfQuestionnaireIsActiveForModule() {
        // define a questionnaire ID, module code and sub-module code
        Integer questionnaireId = new Integer(4);
        String CORRECT_MODULE_CODE = "correct_module_code";
        String CORRECT_SUB_MODULE_CODE = "correct_sub_module_code";
        
        // define 'incorrect' module and sub-module codes
        String INCORRECT_MODULE_CODE = "incorrect_module_code";
        String INCORRECT_SUB_MODULE_CODE = "inccorrect_sub_module_code";
        
        // define 'incorrect' module and sub-module codes
        String NON_EXISTENT_MODULE_CODE = "nonexistent_module_code";
        String NON_EXISTENT_SUB_MODULE_CODE = "nonexistent_sub_module_code";
        
        // create a questionnaire, don't care about id---does not matter in this test
        Questionnaire questionnaire = new Questionnaire();
        
        // create four questionnaire usages
        QuestionnaireUsage usage1 = new QuestionnaireUsage();
        QuestionnaireUsage usage2 = new QuestionnaireUsage();
        QuestionnaireUsage usage3 = new QuestionnaireUsage();
        QuestionnaireUsage usage4 = new QuestionnaireUsage();
        
        // set the usages into the questionnaire
        List<QuestionnaireUsage> usages = new ArrayList<QuestionnaireUsage>();
        usages.add(usage1);
        usages.add(usage2);
        usages.add(usage3);
        usages.add(usage4);
        questionnaire.setQuestionnaireUsages(usages);
        
        // create the field values map for the mock service
        final Map <String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("questionnaireId", questionnaireId.toString());
                
        // define the mock business object service
        final Collection<Questionnaire> questionnaires = new ArrayList<Questionnaire>();
        questionnaires.add(questionnaire);
        
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            atLeast(1).of(businessObjectService).findMatchingOrderBy(Questionnaire.class, fieldValues, "sequenceNumber", false); will(returnValue(questionnaires));
        }});
        
        // create QuestionnaireAnswerServiceImpl instance and set the mock service
        QuestionnaireAnswerServiceImpl questionnaireAnswerServiceImpl = new QuestionnaireAnswerServiceImpl();
        questionnaireAnswerServiceImpl.setBusinessObjectService(businessObjectService);
        
        // case zero: set questionnaire isFinal to true and one particular usage (usage 3) to correct module code and sub-module code 
        // with all other usages set to incorrect codes
        questionnaire.setIsFinal(true);
        usage1.setModuleItemCode(INCORRECT_MODULE_CODE);
        usage1.setModuleSubItemCode(INCORRECT_SUB_MODULE_CODE);
        
        usage2.setModuleItemCode(INCORRECT_MODULE_CODE);
        usage2.setModuleSubItemCode(CORRECT_SUB_MODULE_CODE);
        
        usage3.setModuleItemCode(CORRECT_MODULE_CODE);
        usage3.setModuleSubItemCode(CORRECT_SUB_MODULE_CODE);
        
        usage4.setModuleItemCode(INCORRECT_MODULE_CODE);
        usage4.setModuleSubItemCode(INCORRECT_SUB_MODULE_CODE);
        
        Assert.assertTrue(questionnaireAnswerServiceImpl.checkIfQuestionnaireIsActiveForModule(questionnaireId, CORRECT_MODULE_CODE, CORRECT_SUB_MODULE_CODE));
        
        
        // case one: set questionnaire isFinal to false  
        questionnaire.setIsFinal(false);
        Assert.assertFalse(questionnaireAnswerServiceImpl.checkIfQuestionnaireIsActiveForModule(questionnaireId, CORRECT_MODULE_CODE, CORRECT_SUB_MODULE_CODE));
        
        // case two: set questionnaire isFinal to true and set the previously correct usage to incorrect module code 
        questionnaire.setIsFinal(true);
        usage3.setModuleItemCode(INCORRECT_MODULE_CODE);
        Assert.assertFalse(questionnaireAnswerServiceImpl.checkIfQuestionnaireIsActiveForModule(questionnaireId, CORRECT_MODULE_CODE, CORRECT_SUB_MODULE_CODE));
        
        // case three: no module match amongst the usages
        questionnaire.setIsFinal(true);
        usage3.setModuleItemCode(CORRECT_MODULE_CODE);
        usage3.setModuleSubItemCode(INCORRECT_SUB_MODULE_CODE);
        questionnaires.clear();
        Assert.assertFalse(questionnaireAnswerServiceImpl.checkIfQuestionnaireIsActiveForModule(questionnaireId, NON_EXISTENT_MODULE_CODE, NON_EXISTENT_SUB_MODULE_CODE));
    }
    
    
    

    /**
     * 
     * This method to test that multiple answers are moved properly,i.e, non-empty answer should be moved to top.
     * also test that if questionnaire answer is completed.
     */
    @Test
    public void testPreSave() {
        QuestionnaireAnswerServiceImpl questionnaireAnswerServiceImpl = new QuestionnaireAnswerServiceImpl();
        
        
        final AnswerHeader oldAnswerHeader = createAnswerHeaderForVersioning(1L, "0912000001", "0");
        final AnswerHeader newAnswerHeader = createAnswerHeaderForVersioning(2L, "0912000001", "0");
        for (Answer answer : oldAnswerHeader.getAnswers()) {
            answer.setMatchedChild("Y");
        }
        for (Answer answer : newAnswerHeader.getAnswers()) {
            answer.setMatchedChild("Y");
        }
        // make it incomplete
        newAnswerHeader.getAnswers().get(2).setAnswer("");
        oldAnswerHeader.getAnswers().get(2).getQuestion().setMaxAnswers(4);
        Answer answer = (Answer)ObjectUtils.deepCopy(oldAnswerHeader.getAnswers().get(2));
        answer.setAnswerNumber(2);
        answer.setAnswer("");
        oldAnswerHeader.getAnswers().add(answer);
        answer = (Answer)ObjectUtils.deepCopy(oldAnswerHeader.getAnswers().get(2));
        answer.setAnswerNumber(3);
        answer.setAnswer("");
        oldAnswerHeader.getAnswers().add(answer);
        answer = (Answer)ObjectUtils.deepCopy(oldAnswerHeader.getAnswers().get(2));
        answer.setAnswerNumber(4);
        answer.setAnswer("Test1");        
        oldAnswerHeader.getAnswers().add(answer);
        
        List<AnswerHeader> answerHeaders = new ArrayList<AnswerHeader>();
        answerHeaders.add(oldAnswerHeader);
        answerHeaders.add(newAnswerHeader);
        questionnaireAnswerServiceImpl.preSave(answerHeaders);
        Assert.assertEquals(2, answerHeaders.size());
        Assert.assertFalse(answerHeaders.get(1).getCompleted());
        Assert.assertTrue(answerHeaders.get(0).getCompleted());
        Assert.assertEquals("Test", answerHeaders.get(0).getAnswers().get(2).getAnswer());
        // answer moved up 
        Assert.assertEquals("Test1", answerHeaders.get(0).getAnswers().get(3).getAnswer());
        Assert.assertEquals("", answerHeaders.get(0).getAnswers().get(4).getAnswer());
        Assert.assertEquals("", answerHeaders.get(0).getAnswers().get(5).getAnswer());
    }

    /**
     * 
     * This method is to test that child display indicaotr is set up properly.
     */
    @Test
    public void testSetupChildAnswerIndicator() {
        QuestionnaireAnswerServiceImpl questionnaireAnswerServiceImpl = new QuestionnaireAnswerServiceImpl();
        
        
        final AnswerHeader newAnswerHeader = createAnswerHeaderForVersioning(2L, "0912000001", "0");
        
        // add a new answer to new version
        Answer answer = createAnswer(1,"");
        answer.setQuestionNumber(4);
        answer.setQuestionnaireQuestion(createChildQuestionnaireQuestion(4,1,"1","N"));
        answer.setQuestion(answer.getQuestionnaireQuestion().getQuestion());
        answer.setQuestionRefIdFk(answer.getQuestionnaireQuestion().getQuestion().getQuestionRefId());
        newAnswerHeader.getAnswers().add(answer);

                
        questionnaireAnswerServiceImpl.setupChildAnswerIndicator(newAnswerHeader.getAnswers());
        Assert.assertEquals(4, newAnswerHeader.getAnswers().size());
        Assert.assertEquals("0", newAnswerHeader.getModuleSubItemKey());
        Assert.assertEquals("0912000001", newAnswerHeader.getModuleItemKey());
        Assert.assertEquals(1, newAnswerHeader.getAnswers().get(0).getQuestionNumber().intValue());
        Assert.assertEquals(3, newAnswerHeader.getAnswers().get(1).getQuestionNumber().intValue());
        Assert.assertEquals(4, newAnswerHeader.getAnswers().get(2).getQuestionNumber().intValue());
        Assert.assertEquals(2, newAnswerHeader.getAnswers().get(3).getQuestionNumber().intValue());
        Assert.assertEquals("Y", newAnswerHeader.getAnswers().get(0).getAnswer());
        Assert.assertEquals("10", newAnswerHeader.getAnswers().get(1).getAnswer());
        Assert.assertEquals("", newAnswerHeader.getAnswers().get(2).getAnswer());
        Assert.assertEquals("Test", newAnswerHeader.getAnswers().get(3).getAnswer());
        Assert.assertEquals("Y", newAnswerHeader.getAnswers().get(0).getMatchedChild());
        Assert.assertEquals("Y", newAnswerHeader.getAnswers().get(1).getMatchedChild());
        Assert.assertEquals("N", newAnswerHeader.getAnswers().get(2).getMatchedChild());
        Assert.assertEquals("Y", newAnswerHeader.getAnswers().get(3).getMatchedChild());
    }
    
    
    @Test
    public void testGetAnswerHeadersForProtocol() {
        QuestionnaireAnswerServiceImpl questionnaireAnswerServiceImpl = new QuestionnaireAnswerServiceImpl();
        final List<String> protocolNumbers = new ArrayList<String>();
        protocolNumbers.add("0912000001");
        protocolNumbers.add("0912000001A001");

        final Map <String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("moduleItemCode", CoeusModule.IRB_MODULE_CODE);
        fieldValues.put("moduleItemKey", protocolNumbers);
        //fieldValues.put("moduleItemKey", "0912000001");

       final Collection<AnswerHeader> headers = new ArrayList<AnswerHeader>();
       AnswerHeader answerHeader1 = createAnswerHeaderForVersioning(1L, "0912000001", "0");
       headers.add(answerHeader1);
       AnswerHeader answerHeader2 = createAnswerHeaderForVersioning(1L, "0912000001A001", "0");
       answerHeader2.setModuleSubItemCode("1");
       headers.add(answerHeader2);
       AnswerHeader answerHeader3 = createAnswerHeaderForVersioning(1L, "0912000001", "1");
       answerHeader3.setModuleSubItemCode("2");
       headers.add(answerHeader3);
  
        final Protocol protocol = new Protocol(){
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                // do nothing
            }

        };
        protocol.setProtocolNumber("0912000001");
        protocol.setSequenceNumber(0);
        final List<Protocol> protocols = new ArrayList<Protocol>();
        Protocol protocol1 = new Protocol(){
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                // do nothing
            }

        };
        protocol1.setProtocolNumber("0912000001");
        protocols.add(protocol1);
        Protocol protocol2 = new Protocol(){
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                // do nothing
            }

        };
        protocol2.setProtocolNumber("0912000001A001");
        protocols.add(protocol2);
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        final ProtocolFinderDao protocolFinderDao = context.mock(ProtocolFinderDao.class);
        context.checking(new Expectations() {{
            one(businessObjectService).findMatching(AnswerHeader.class, fieldValues); will(returnValue(headers));
            one(protocolFinderDao).findProtocols("0912000001"); will(returnValue(protocols));
        }});
        questionnaireAnswerServiceImpl.setBusinessObjectService(businessObjectService);
        questionnaireAnswerServiceImpl.setProtocolFinderDao(protocolFinderDao);
        
        List<AnswerHeader> answerHeaders = questionnaireAnswerServiceImpl.getAnswerHeadersForProtocol("0912000001");
        Assert.assertEquals(3, answerHeaders.size());
        Assert.assertEquals("0", answerHeaders.get(0).getModuleSubItemKey());
        Assert.assertEquals("0912000001", answerHeaders.get(0).getModuleItemKey());
        //assertEquals(answerHeader, questionnaireAnswerServiceImpl.getNewVersionAnswerHeader(new ModuleQuestionnaireBean(CoeusModule.IRB_MODULE_CODE, protocol), questionnaire));
    }

}

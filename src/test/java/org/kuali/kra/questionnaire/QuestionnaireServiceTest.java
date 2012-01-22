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
package org.kuali.kra.questionnaire;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;


public class QuestionnaireServiceTest  extends KcUnitTestBase {
    
        private Mockery context = new JUnit4Mockery();
        private Set<String> expectedModules;
        @Before
        public void setUp() throws Exception {
            super.setUp();
            expectedModules = new HashSet<String>();
            expectedModules.add("3");
            expectedModules.add("7");
            expectedModules.add("2");
        }  


        @Test
        public void testCopyQuestionnaire() {

            final QuestionnaireServiceImpl questionnaireService = new QuestionnaireServiceImpl();
            Questionnaire srcQuestionnaire = setupSourceQuestionnaire();
            Questionnaire destQuestionnaire = new Questionnaire();
            questionnaireService.copyQuestionnaire(srcQuestionnaire, destQuestionnaire);
            List<QuestionnaireQuestion> questionnaireQuestions = destQuestionnaire.getQuestionnaireQuestions();
            List<QuestionnaireUsage> questionnaireUsages = destQuestionnaire.getQuestionnaireUsages();
            assertTrue(questionnaireQuestions.size() == 2);
            Assert.assertNull(questionnaireQuestions.get(0).getQuestionnaireRefIdFk());            
            Assert.assertNull(questionnaireQuestions.get(1).getQuestionnaireRefIdFk());            
            Assert.assertNull(questionnaireQuestions.get(0).getQuestionnaireQuestionsId());            
            Assert.assertNull(questionnaireQuestions.get(1).getQuestionnaireQuestionsId());            
            assertEquals(questionnaireQuestions.get(0).getQuestionRefIdFk(), (Object)1000L);

            
            assertTrue(questionnaireUsages.size() == 2);
            assertEquals(questionnaireUsages.get(0).getQuestionnaireLabel(), "test 1");
            assertEquals(questionnaireUsages.get(1).getQuestionnaireLabel(), "test 2");
            Assert.assertNull(questionnaireUsages.get(0).getQuestionnaireRefIdFk());            
            Assert.assertNull(questionnaireUsages.get(1).getQuestionnaireRefIdFk());            
            Assert.assertNull(questionnaireUsages.get(0).getQuestionnaireUsageId());            
            Assert.assertNull(questionnaireUsages.get(1).getQuestionnaireUsageId());            
        }

        private Questionnaire setupSourceQuestionnaire() {
            Questionnaire questionnaire = new Questionnaire();
            questionnaire.setQuestionnaireRefIdFromLong(1L);
            
            QuestionnaireQuestion questionnaireQuestion = new QuestionnaireQuestion();
            questionnaireQuestion.setQuestionnaireRefIdFk("1");
            questionnaireQuestion.setQuestionRefIdFk(1L);
            questionnaireQuestion.setQuestionnaireQuestionsId(1L);
            questionnaireQuestion.setQuestionRefIdFk(1000L);
            questionnaire.getQuestionnaireQuestions().add(questionnaireQuestion);
            
            questionnaireQuestion = new QuestionnaireQuestion();
            questionnaireQuestion.setQuestionnaireRefIdFk("1");
            questionnaireQuestion.setQuestionRefIdFk(2L);
            questionnaireQuestion.setQuestionnaireQuestionsId(2L);
            questionnaireQuestion.setQuestionRefIdFk(1001L);
            questionnaire.getQuestionnaireQuestions().add(questionnaireQuestion);
            
            QuestionnaireUsage questionnaireUsage = new QuestionnaireUsage();
            questionnaireUsage.setQuestionnaireRefIdFk("1");
            questionnaireUsage.setQuestionnaireLabel("test 1");
            questionnaireUsage.setQuestionnaireUsageId(1L);
            questionnaire.getQuestionnaireUsages().add(questionnaireUsage);
            
            questionnaireUsage = new QuestionnaireUsage();
            questionnaireUsage.setQuestionnaireRefIdFk("1");
            questionnaireUsage.setQuestionnaireLabel("test 2");
            questionnaireUsage.setQuestionnaireUsageId(2L);
            questionnaire.getQuestionnaireUsages().add(questionnaireUsage);
            
            
            return questionnaire;
            
        }
        
        /**
         * 
         * This method valid module code to Associate a Questionnaire.
         * This method can't be done with mock, so has to use get real service call.
         * Mock has problem with setusersession
         */
        @Test
        public void testValidCodes() {
            GlobalVariables.setUserSession(new UserSession("quickstart"));

            final QuestionnaireServiceImpl questionnaireService = new QuestionnaireServiceImpl();


            List<String> modules = KraServiceLocator.getService(QuestionnaireService.class).getAssociateModules();
            //assertEquals(3, modules.size());
            assertEquals(modules.size(), expectedModules.size());
            for (String module : modules ) {
                assertTrue(expectedModules.contains(module));
            }
            
            context.assertIsSatisfied();
                        
        }
        
        @Test
        public void testIsQuestionnaireNameExistTrue() {

            final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
            final QuestionnaireServiceImpl questionnaireService = new QuestionnaireServiceImpl();
            questionnaireService.setBusinessObjectService(businessObjectService);
            final Questionnaire questionnaire = new Questionnaire();
            questionnaire.setQuestionnaireId("1");
            questionnaire.setName("exist name");
            final List<Questionnaire> questionnaires = new ArrayList<Questionnaire>();
            questionnaires.add(questionnaire);
            final Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("name", "exist name");
            context.checking(new Expectations() {{
                one(businessObjectService).findMatching(Questionnaire.class, fieldValues);
                will(returnValue(questionnaires));
            }});

            assertTrue(questionnaireService.isQuestionnaireNameExist(null, "exist name"));

            context.assertIsSatisfied();
                        
        }

        @Test
        public void testIsQuestionnaireNameExistFalse() {

            final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
            final QuestionnaireServiceImpl questionnaireService = new QuestionnaireServiceImpl();
            questionnaireService.setBusinessObjectService(businessObjectService);
            final Questionnaire questionnaire = new Questionnaire();
            questionnaire.setQuestionnaireId("1");
            questionnaire.setName("exist name");
            final List<Questionnaire> questionnaires = new ArrayList<Questionnaire>();
            questionnaires.add(questionnaire);
            final Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("name", "exist name");
            context.checking(new Expectations() {{
                one(businessObjectService).findMatching(Questionnaire.class, fieldValues);
                will(returnValue(questionnaires));
            }});

            assertTrue(!questionnaireService.isQuestionnaireNameExist("1", "exist name"));

            context.assertIsSatisfied();
                        
        }
        
        @Test
        public void testIsQuestionnaireNameExistFalseNoMatch() {

            final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
            final QuestionnaireServiceImpl questionnaireService = new QuestionnaireServiceImpl();
            questionnaireService.setBusinessObjectService(businessObjectService);
            final Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("name", "not exist name");
            final List<Questionnaire> questionnaires = new ArrayList<Questionnaire>();
            context.checking(new Expectations() {{
                one(businessObjectService).findMatching(Questionnaire.class, fieldValues);
                will(returnValue(questionnaires));
            }});

            assertTrue(!questionnaireService.isQuestionnaireNameExist("1", "not exist name"));

            context.assertIsSatisfied();
                        
        }
        
    }



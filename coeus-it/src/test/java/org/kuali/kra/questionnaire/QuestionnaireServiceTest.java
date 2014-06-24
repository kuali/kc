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
package org.kuali.kra.questionnaire;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireService;
import org.kuali.coeus.common.questionnaire.impl.core.QuestionnaireServiceImpl;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class QuestionnaireServiceTest  extends KcIntegrationTestBase {
    
        private Mockery context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
        private Set<String> expectedModules;
        @Before
        public void setUp() throws Exception {
            expectedModules = new HashSet<String>();
            expectedModules.add("3");
            expectedModules.add("7");
            expectedModules.add("2");
            expectedModules.add("8");
            expectedModules.add("9");
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
            Assert.assertNull(questionnaireQuestions.get(0).getQuestionnaireId());
            Assert.assertNull(questionnaireQuestions.get(1).getQuestionnaireId());
            Assert.assertNull(questionnaireQuestions.get(0).getId());
            Assert.assertNull(questionnaireQuestions.get(1).getId());
            assertEquals(questionnaireQuestions.get(0).getQuestionId(), (Object)1000L);

            
            assertTrue(questionnaireUsages.size() == 2);
            assertEquals(questionnaireUsages.get(0).getQuestionnaireLabel(), "test 1");
            assertEquals(questionnaireUsages.get(1).getQuestionnaireLabel(), "test 2");
            Assert.assertNull(questionnaireUsages.get(0).getQuestionnaireId());
            Assert.assertNull(questionnaireUsages.get(1).getQuestionnaireId());
            Assert.assertNull(questionnaireUsages.get(0).getId());
            Assert.assertNull(questionnaireUsages.get(1).getId());
        }

        private Questionnaire setupSourceQuestionnaire() {
            Questionnaire questionnaire = new Questionnaire();
            questionnaire.setQuestionnaireRefIdFromLong(1L);
            
            QuestionnaireQuestion questionnaireQuestion = new QuestionnaireQuestion();
            questionnaireQuestion.setQuestionnaireId(1L);
            questionnaireQuestion.setQuestionId(1L);
            questionnaireQuestion.setId(1L);
            questionnaireQuestion.setQuestionId(1000L);
            questionnaire.getQuestionnaireQuestions().add(questionnaireQuestion);
            
            questionnaireQuestion = new QuestionnaireQuestion();
            questionnaireQuestion.setQuestionnaireId(1L);
            questionnaireQuestion.setQuestionId(2L);
            questionnaireQuestion.setId(2L);
            questionnaireQuestion.setQuestionId(1001L);
            questionnaire.getQuestionnaireQuestions().add(questionnaireQuestion);
            
            QuestionnaireUsage questionnaireUsage = new QuestionnaireUsage();
            questionnaireUsage.setQuestionnaireId(1L);
            questionnaireUsage.setQuestionnaireLabel("test 1");
            questionnaireUsage.setId(1L);
            questionnaire.getQuestionnaireUsages().add(questionnaireUsage);
            
            questionnaireUsage = new QuestionnaireUsage();
            questionnaireUsage.setQuestionnaireId(1L);
            questionnaireUsage.setQuestionnaireLabel("test 2");
            questionnaireUsage.setId(2L);
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

            List<String> modules = KcServiceLocator.getService(QuestionnaireService.class).getAssociateModules();
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
            questionnaire.setQuestionnaireSeqId("1");
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
            questionnaire.setQuestionnaireSeqId("1");
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



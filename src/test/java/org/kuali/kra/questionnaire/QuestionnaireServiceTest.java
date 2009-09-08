/*
 * Copyright 2006-2008 The Kuali Foundation
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Test;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.rice.kns.service.BusinessObjectService;

public class QuestionnaireServiceTest {
    
        private Mockery context = new JUnit4Mockery();

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
            questionnaire.setQuestionnaireRefId(1L);
            
            QuestionnaireQuestion questionnaireQuestion = new QuestionnaireQuestion();
            questionnaireQuestion.setQuestionnaireRefIdFk(1L);
            questionnaireQuestion.setQuestionRefIdFk(1L);
            questionnaireQuestion.setQuestionnaireQuestionsId(1L);
            questionnaireQuestion.setQuestionRefIdFk(1000L);
            questionnaire.getQuestionnaireQuestions().add(questionnaireQuestion);
            
            questionnaireQuestion = new QuestionnaireQuestion();
            questionnaireQuestion.setQuestionnaireRefIdFk(1L);
            questionnaireQuestion.setQuestionRefIdFk(2L);
            questionnaireQuestion.setQuestionnaireQuestionsId(2L);
            questionnaireQuestion.setQuestionRefIdFk(1001L);
            questionnaire.getQuestionnaireQuestions().add(questionnaireQuestion);
            
            QuestionnaireUsage questionnaireUsage = new QuestionnaireUsage();
            questionnaireUsage.setQuestionnaireRefIdFk(1L);
            questionnaireUsage.setQuestionnaireLabel("test 1");
            questionnaireUsage.setQuestionnaireUsageId(1L);
            questionnaire.getQuestionnaireUsages().add(questionnaireUsage);
            
            questionnaireUsage = new QuestionnaireUsage();
            questionnaireUsage.setQuestionnaireRefIdFk(1L);
            questionnaireUsage.setQuestionnaireLabel("test 2");
            questionnaireUsage.setQuestionnaireUsageId(2L);
            questionnaire.getQuestionnaireUsages().add(questionnaireUsage);
            
            
            return questionnaire;
            
        }
        
        @Test
        public void testValidCodes() {

            final QuestionnaireAuthorizationService questionnaireAuthorizationService = context.mock(QuestionnaireAuthorizationService.class);
            final QuestionnaireServiceImpl questionnaireService = new QuestionnaireServiceImpl();
            questionnaireService.setQuestionnaireAuthorizationService(questionnaireAuthorizationService);
            context.checking(new Expectations() {{
                one(questionnaireAuthorizationService).hasPermission(PermissionConstants.MODIFY_PROPOSAL);
                will(returnValue(false));
                one(questionnaireAuthorizationService).hasPermission(PermissionConstants.MODIFY_PROTOCOL);
                will(returnValue(true));
            }});

            List<String> modules = questionnaireService.getAssociateModules();
            assertTrue(modules.size() == 1);
            assertEquals(modules.get(0), "7");

            context.assertIsSatisfied();
                        
        }
        
        @Test
        public void testIsQuestionnaireNameExistTrue() {

            final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
            final QuestionnaireServiceImpl questionnaireService = new QuestionnaireServiceImpl();
            questionnaireService.setBusinessObjectService(businessObjectService);
            final Questionnaire questionnaire = new Questionnaire();
            questionnaire.setQuestionnaireId(1);
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
            questionnaire.setQuestionnaireId(1);
            questionnaire.setName("exist name");
            final List<Questionnaire> questionnaires = new ArrayList<Questionnaire>();
            questionnaires.add(questionnaire);
            final Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("name", "exist name");
            context.checking(new Expectations() {{
                one(businessObjectService).findMatching(Questionnaire.class, fieldValues);
                will(returnValue(questionnaires));
            }});

            assertTrue(!questionnaireService.isQuestionnaireNameExist(1, "exist name"));

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

            assertTrue(!questionnaireService.isQuestionnaireNameExist(1, "not exist name"));

            context.assertIsSatisfied();
                        
        }
        
    }



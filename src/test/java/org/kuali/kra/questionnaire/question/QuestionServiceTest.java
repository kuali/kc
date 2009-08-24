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
package org.kuali.kra.questionnaire.question;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.rice.kns.service.BusinessObjectService;

@RunWith(JMock.class)
public class QuestionServiceTest {
    
        private Mockery context = new JUnit4Mockery();

        /**
         * Verify that the correct question is returned if it is found 
         * for lookups by questionRefId.
         */
        @Test
        public void testGetQuestionByRefIdFound() {
            QuestionServiceImpl questionService = new QuestionServiceImpl();
            
            /*
             * The QuestionServiceImpl will use the Business Object Service
             * to query the database.  Since we "know" the internals to the
             * QuestionServiceImpl, we know data to be sent to the Business
             * Object Service and what will be returned if the question is
             * found.
             */
            final Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("questionRefId", "999");
            
            final Collection<Question> questions = new ArrayList<Question>();
            Question question = new Question();
            questions.add(question);
            
            final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
            context.checking(new Expectations() {{
                one(businessObjectService).findMatching(Question.class, fieldValues); will(returnValue(questions));
            }});
            questionService.setBusinessObjectService(businessObjectService);
            
            assertEquals(question, questionService.getQuestionByRefId("999"));
        }
        
        /**
         * Verify that null is returned if the question is not found
         * for lookups by questionRefId.
         */
        @Test
        public void testGetQuestionByRefIdNotFound() {
            QuestionServiceImpl questionService = new QuestionServiceImpl();
            
            /*
             * The QuestionServiceImpl will use the Business Object Service
             * to query the database.  Since we "know" the internals to the
             * QuestionServiceImpl, we know data to be sent to the Business
             * Object Service and we know that an empty list of questions
             * is returned if the question is not in the database.
             */
            final Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("questionRefId", "999");
            
            final Collection<Question> questions = new ArrayList<Question>();
            
            final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
            context.checking(new Expectations() {{
                one(businessObjectService).findMatching(Question.class, fieldValues); will(returnValue(questions));
            }});
            questionService.setBusinessObjectService(businessObjectService);
            
            assertEquals(null, questionService.getQuestionByRefId("999"));
        }

        /**
         * Verify that the correct question is returned if it is found 
         * for lookups by questionId.
         */
        @Test
        public void testGetQuestionByIdFound() {
            QuestionServiceImpl questionService = new QuestionServiceImpl();
            
            /*
             * The QuestionServiceImpl will use the Business Object Service
             * to query the database.  Since we "know" the internals to the
             * QuestionServiceImpl, we know data to be sent to the Business
             * Object Service and what will be returned if the question is
             * found.
             */
            final Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("questionId", 999);
            
            final Collection<Question> questions = new ArrayList<Question>();
            Question question = new Question();
            questions.add(question);
            
            final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
            context.checking(new Expectations() {{
                one(businessObjectService).findMatching(Question.class, fieldValues); will(returnValue(questions));
            }});
            questionService.setBusinessObjectService(businessObjectService);
            
            assertEquals(question, questionService.getQuestionById(999));
        }
        
        /**
         * Verify that null is returned if the question is not found
         * for lookups by questionId.
         */
        @Test
        public void testGetQuestionByIdNotFound() {
            QuestionServiceImpl questionService = new QuestionServiceImpl();
            
            /*
             * The QuestionServiceImpl will use the Business Object Service
             * to query the database.  Since we "know" the internals to the
             * QuestionServiceImpl, we know data to be sent to the Business
             * Object Service and we know that an empty list of questions
             * is returned if the question is not in the database.
             */
            final Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("questionId", 999);
            
            final Collection<Question> questions = new ArrayList<Question>();
            
            final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
            context.checking(new Expectations() {{
                one(businessObjectService).findMatching(Question.class, fieldValues); will(returnValue(questions));
            }});
            questionService.setBusinessObjectService(businessObjectService);
            
            assertEquals(null, questionService.getQuestionById(999));
        }

    }



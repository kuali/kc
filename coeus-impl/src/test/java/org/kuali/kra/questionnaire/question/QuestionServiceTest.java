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
package org.kuali.kra.questionnaire.question;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireConstants;
import org.kuali.coeus.common.questionnaire.framework.question.Question;
import org.kuali.coeus.common.questionnaire.impl.question.QuestionServiceImpl;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(JMock.class)
public class QuestionServiceTest {

    private Mockery context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};

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
            fieldValues.put(QuestionnaireConstants.QUESTION_ID, new Long(999));
            
            final Question question = new Question();
            
            final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
            context.checking(new Expectations() {{
                one(businessObjectService).findByPrimaryKey(Question.class, fieldValues); will(returnValue(question));
            }});
            questionService.setBusinessObjectService(businessObjectService);
            
            assertEquals(question, questionService.getQuestionByQuestionId(new Long(999)));
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
            fieldValues.put(QuestionnaireConstants.QUESTION_ID, new Long(999));
            
            final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
            context.checking(new Expectations() {{
                one(businessObjectService).findByPrimaryKey(Question.class, fieldValues); will(returnValue(null));
            }});
            questionService.setBusinessObjectService(businessObjectService);
            
            assertEquals(null, questionService.getQuestionByQuestionId(new Long(999)));
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
             * to query the database.  Since we "know" the  9569 beard rdinternals to the
             * QuestionServiceImpl, we know data to be sent to the Business
             * Object Service and what will be returned if the question is
             * found.
             */
            final Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put(QuestionnaireConstants.QUESTION_SEQEQUENCE_ID, 999);
            
            final Collection<Question> questions = new ArrayList<Question>();
            Question question = new Question();
            questions.add(question);
            
            final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
            context.checking(new Expectations() {{
                one(businessObjectService).findMatching(Question.class, fieldValues); will(returnValue(questions));
            }});
            questionService.setBusinessObjectService(businessObjectService);
            
            assertEquals(question, questionService.getQuestionByQuestionSequenceId(999));
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
            fieldValues.put(QuestionnaireConstants.QUESTION_SEQEQUENCE_ID, 999);
            
            final Collection<Question> questions = new ArrayList<Question>();
            
            final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
            context.checking(new Expectations() {{
                one(businessObjectService).findMatching(Question.class, fieldValues); will(returnValue(questions));
            }});
            questionService.setBusinessObjectService(businessObjectService);
            
            assertEquals(null, questionService.getQuestionByQuestionSequenceId(999));
        }

    }



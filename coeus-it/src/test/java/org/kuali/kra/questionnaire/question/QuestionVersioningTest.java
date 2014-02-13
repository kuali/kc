/*
 * Copyright 2005-2013 The Kuali Foundation
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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.service.VersioningService;
import org.kuali.kra.service.impl.VersioningServiceImpl;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

import static org.junit.Assert.assertEquals;

public class QuestionVersioningTest extends KcIntegrationTestBase {
    
    private transient VersioningService versioningService;
    private Question originalQuestion;
    
    @Before
    public void setUp() {
        versioningService  = new VersioningServiceImpl();
        originalQuestion = createQuestion(1, "What is your first name?");        
    }

    @After
    public void tearDown() {
        versioningService = null;
        originalQuestion = null;
    }
    
    @Test 
    public void testQuestionVersioning() throws Exception {
        Question versionedQuestion = (Question) versioningService.createNewVersion(originalQuestion);

        verifyVersioning(originalQuestion, versionedQuestion);
    }
    
    private Question createQuestion(Integer questionId, String questionText) {
        Question question = new Question();
        question.setQuestionIdFromInteger(questionId);
        question.setQuestion(questionText);
        return question;
    }

    private void verifyVersioning(Question originalQuestion, Question versionedQuestion) {
        // Question
        assertEquals(originalQuestion.getQuestionId(), versionedQuestion.getQuestionId());
        assertEquals(originalQuestion.getQuestion(), versionedQuestion.getQuestion());
        Integer expectedSequenceNumber = originalQuestion.getSequenceNumber() + 1;
        assertEquals(expectedSequenceNumber, versionedQuestion.getSequenceNumber());
    }

}

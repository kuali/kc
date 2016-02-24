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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.version.VersioningService;
import org.kuali.coeus.common.impl.version.VersioningServiceImpl;
import org.kuali.coeus.common.questionnaire.framework.question.Question;
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
        question.setQuestionSeqId(questionId);
        question.setQuestion(questionText);
        return question;
    }

    private void verifyVersioning(Question originalQuestion, Question versionedQuestion) {
        // Question
        assertEquals(originalQuestion.getQuestionSeqId(), versionedQuestion.getQuestionSeqId());
        assertEquals(originalQuestion.getQuestion(), versionedQuestion.getQuestion());
        Integer expectedSequenceNumber = originalQuestion.getSequenceNumber() + 1;
        assertEquals(expectedSequenceNumber, versionedQuestion.getSequenceNumber());
    }

}

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
package org.kuali.coeus.common.questionnaire.impl.question;

import org.kuali.coeus.common.questionnaire.framework.question.Question;

/**
 * The Question Service provides a set of methods for
 * working with questions.
 */
public interface QuestionService {

    /**
     * Retrieve a question from the database based upon its questionRefId.
     * @param questionRefId of the question
     * @return the question or null if not found
     */
    Question getQuestionByRefId(String questionRefId);

    /**
     * Retrieve a question from the database based upon its questionId.
     * @param questionId of the question
     * @return the most recent active question or null if not found
     */
    Question getQuestionById(Integer questionId);
    
    /**
     * Check if the question is used in a questionnaire.
     * @param questionId of the question
     * @return true if question is used in an active questionnaire, false otherwise
     */
    boolean isQuestionUsed(Integer questionId);

}

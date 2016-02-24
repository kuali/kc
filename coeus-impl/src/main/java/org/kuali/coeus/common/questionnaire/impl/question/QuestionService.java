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
package org.kuali.coeus.common.questionnaire.impl.question;

import org.kuali.coeus.common.questionnaire.framework.question.Question;

/**
 * The Question Service provides a set of methods for
 * working with questions.
 */
public interface QuestionService {

    /**
     * Retrieve a question from the database based upon its questionRefId.
     * @param questionId of the question
     * @return the question or null if not found
     */
    Question getQuestionByQuestionId(Long questionId);

    /**
     * Retrieve a question from the database based upon its questionId.
     * @param questionSeqId of the question
     * @return the most recent active question or null if not found
     */
    Question getQuestionByQuestionSequenceId(Integer questionSeqId);
    
    /**
     * Check if the question is used in a questionnaire.
     * @param questionId of the question
     * @return true if question is used in an active questionnaire, false otherwise
     */
    boolean isQuestionUsed(Integer questionId);

}

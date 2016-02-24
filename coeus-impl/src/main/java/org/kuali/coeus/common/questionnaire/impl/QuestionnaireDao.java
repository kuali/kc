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

package org.kuali.coeus.common.questionnaire.impl;

import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;

import java.util.List;

/**
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public interface QuestionnaireDao {

    /**
     * Retrieve the sequence number for the questionnaire that is current.
     *
     * @param questionnaireSeqId the id of a specific questionnaire
     * @return the current sequence number for the questionnaire, null if not found
     */
    public Integer getCurrentQuestionnaireSequenceNumber(String questionnaireSeqId);

    public List<AnswerHeader> getQuestionnaireAnswers(String moduleCode, String moduleItemKey, String moduleSubItemKey);

    }

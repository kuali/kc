/**
 * Copyright 2005-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.common.questionnaire.impl;

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

}
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

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.questionnaire.question.Question;
import org.kuali.rice.kns.service.BusinessObjectService;

public class QuestionServiceImpl implements QuestionService {

    private static final String QUESTION_REF_ID = "questionRefId";

    private BusinessObjectService businessObjectService;

    /**
     * Set the Business Object Service.
     * 
     * @param businessObjectService the Business Object Service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * 
     * @see org.kuali.kra.questionnaire.question.QuestionService#getQuestionById(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public Question getQuestionByRefId(String questionRefId) {
        Question question = null;
        if (!StringUtils.isBlank(questionRefId)) {
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put(QUESTION_REF_ID, questionRefId);
            Collection<Question> questions = businessObjectService.findMatching(Question.class, fieldValues);
            if (questions.size() > 0) {
                /*
                 * Since questionRefId is a unique key for questions (database constraint) we will always get
                 * at most one result.
                 */
                question = questions.iterator().next();
            }
        }
        return question;
    }

}

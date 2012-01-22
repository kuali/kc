/*
 * Copyright 2005-2010 The Kuali Foundation
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
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.questionnaire.Questionnaire;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * Implementation of the various Question services.
 * 
 * @see org.kuali.kra.questionnaire.question.QuestionService
 */
public class QuestionServiceImpl implements QuestionService {

    private static final String QUESTION_REF_ID = "questionRefId";
    private static final String QUESTION_ID = "questionId";
    private static final String QUESTION_QUESTION_ID = "questionnaireQuestions.question.questionId";
    private static final String QUESTIONNAIRE_ID = "questionnaireId";

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
     * @see org.kuali.kra.questionnaire.question.QuestionService#getQuestionByRefId(java.lang.String)
     */
    public Question getQuestionByRefId(String questionRefId) {
        Question question = null;
        if (!StringUtils.isBlank(questionRefId)) {
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put(QUESTION_REF_ID, questionRefId);
            question = (Question) businessObjectService.findByPrimaryKey(Question.class, fieldValues);
        }
        return question;
    }

    /**
     * 
     * @see org.kuali.kra.questionnaire.question.QuestionService#getQuestionById(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public Question getQuestionById(String questionId) {
        Question question = null;
        if (questionId != null) {
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put(QUESTION_ID, questionId);
            Collection<Question> questions = businessObjectService.findMatching(Question.class, fieldValues);
            if (questions.size() > 0) {
                /*
                 * Return the most recent approved question (i.e. the question version with the highest 
                 * sequence number that is approved/in the database).
                 */
                question = (Question) Collections.max(questions);
            }
        }
        return question;
    }

    
    /**
     * @see org.kuali.kra.questionnaire.question.QuestionService#isQuestionUsed(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public boolean isQuestionUsed(String questionId) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(QUESTION_QUESTION_ID, questionId);
        List<Questionnaire> questionnaires = (List<Questionnaire>) businessObjectService.findMatching(Questionnaire.class,
                fieldValues);
        for (Questionnaire questionnaire : questionnaires) {
            if (isActiveQuestionnaire(questionnaire)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method determines if the questionnaire is the active (most recent version) of the questionnaire.
     * @param questionnaire
     * @return true if this is the active questionnaire, false otherwise
     */
    @SuppressWarnings("unchecked")
    protected boolean isActiveQuestionnaire(Questionnaire questionnaire) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(QUESTIONNAIRE_ID, questionnaire.getQuestionnaireId());
        Collection<Questionnaire> questionnaires = businessObjectService.findMatching(Questionnaire.class, fieldValues);
        if (questionnaires.size() > 0) {
            Questionnaire maxQuestionnaire = (Questionnaire) Collections.max(questionnaires);
            if (maxQuestionnaire.getQuestionnaireRefId().equals(questionnaire.getQuestionnaireRefId())) {
                return true;
            }
        }
        return false;
    }
}

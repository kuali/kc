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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.questionnaire.framework.question.Question;
import org.kuali.coeus.common.questionnaire.framework.core.Questionnaire;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireConstants;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Implementation of the various Question services.
 * 
 * @see QuestionService
 */
@Component("questionService")
public class QuestionServiceImpl implements QuestionService {

    private static final String QUESTION_QUESTION_ID = "questionnaireQuestions.question.questionSeqId";

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    /**
     * Set the Business Object Service.
     * 
     * @param businessObjectService the Business Object Service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    @Override
    public Question getQuestionByQuestionId(Long questionId) {
        Question question = null;
        if (questionId != null) {
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put(QuestionnaireConstants.QUESTION_ID, questionId);
            question = (Question) businessObjectService.findByPrimaryKey(Question.class, fieldValues);
        }
        return question;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Question getQuestionByQuestionSequenceId(Integer questionSeqId) {
        Question question = null;
        if (questionSeqId != null) {
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put(QuestionnaireConstants.QUESTION_SEQEQUENCE_ID, questionSeqId);
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

    
    @Override
    @SuppressWarnings("unchecked")
    public boolean isQuestionUsed(Integer questionId) {
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
        fieldValues.put(QuestionnaireConstants.QUESTIONNAIRE_SEQUENCE_ID_PARAMETER_NAME, questionnaire.getQuestionnaireSeqId());
        Collection<Questionnaire> questionnaires = businessObjectService.findMatching(Questionnaire.class, fieldValues);
        if (questionnaires.size() > 0) {
            Questionnaire maxQuestionnaire = (Questionnaire) Collections.max(questionnaires);
            if (maxQuestionnaire.getId().equals(questionnaire.getId())) {
                return true;
            }
        }
        return false;
    }
}

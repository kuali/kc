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
package org.kuali.kra.questionnaire;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.util.ObjectUtils;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class QuestionnaireServiceImpl implements QuestionnaireService {

    private QuestionnaireDao questionnaireDao;
    private BusinessObjectService businessObjectService;

    public void saveQuestionnaire(String sqlScripts, Questionnaire questionnaire) {
//        if (questionnaire.getQuestionnaireRefId() != null) {
//            Map pkMap = new HashMap();
//            pkMap.put("questionnaireRefId", questionnaire.getQuestionnaireRefId());
//            questionnaire = (Questionnaire) businessObjectService.findByPrimaryKey(Questionnaire.class, pkMap);
//        }
        questionnaireDao.runScripts(sqlScripts.split(";;;"), questionnaire.getQuestionnaireRefId());

    }


    public boolean isQuestionnaireNameExist(Integer questionnaireId, String name) {
        // TODO : maybe should check questionnaireid. because it should be ok that they have the same name for different version ?
        // only check if the questionnaire is final ?  then should we check before approve to make sure name is unique ?
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("name", name);
        boolean isExist = false;
        List<Questionnaire> questionnaires = (List<Questionnaire>) businessObjectService.findMatching(Questionnaire.class,
                fieldValues);
        for (Questionnaire questionnaire : questionnaires) {
            if (questionnaireId == null || !questionnaire.getQuestionnaireId().equals(questionnaireId)) {
            //if (questionnaireId == null || !questionnaire.getQuestionnaireId().equals(questionnaireId) && isFinal(questionnaire)) {
                isExist = true;
                break;
            }
        }
        return isExist;
    }

    private boolean isFinal(Questionnaire questionnaire) {
        boolean isFinal = true;
        if (questionnaire.getDocumentNumber() == null) {
            isFinal = false;
        } else {
            // TODO : inject documentservice
            try {
            isFinal = KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(questionnaire.getDocumentNumber())
              .getDocumentHeader().getWorkflowDocument().getRouteHeader().getDocRouteStatus().equals("F");
            } catch (Exception e) {
                
            }
        }
        return isFinal;
    }

    public void copyQuestionnaire(Questionnaire src, Questionnaire dest) {
        copyQuestionnaireLists(src, dest);
        businessObjectService.save(dest);

    }

    public void setQuestionnaireDao(QuestionnaireDao questionnaireDao) {
        this.questionnaireDao = questionnaireDao;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    @SuppressWarnings("unchecked")
    public void copyQuestionnaireLists(Questionnaire src, Questionnaire dest) {
        dest.setQuestionnaireQuestions(src.getQuestionnaireQuestions());
        dest.setQuestionnaireUsages(src.getQuestionnaireUsages());
        for (QuestionnaireQuestion question : dest.getQuestionnaireQuestions()) {
        //for (Object obj : dest.getQuestionnaireQuestions()) {
        //    QuestionnaireQuestion question = (QuestionnaireQuestion)obj;
            question.setQuestionnaireRefIdFk(null);
            question.setQuestionnaireQuestionsId(null);
            question.setVersionNumber(new Long(1));
        }
        for (QuestionnaireUsage usage : dest.getQuestionnaireUsages()) {
            usage.setQuestionnaireRefIdFk(null);
            usage.setQuestionnaireUsageId(null);
            usage.setVersionNumber(new Long(1));
        }

    }

    private Object deepCopy(Object obj) {
        if (obj instanceof Serializable) {
            return ObjectUtils.deepCopy((Serializable) obj);
        }
        return obj;
    }

}

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

import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.ObjectUtils;

public class QuestionnaireServiceImpl implements QuestionnaireService{
    
    private QuestionnaireDao questionnaireDao;
    private BusinessObjectService businessObjectService;

    public void saveQuestionnaire(String sqlScripts, Questionnaire questionnaire) {
        businessObjectService.save(questionnaire);
        questionnaireDao.runScripts(sqlScripts.split("#;#"),questionnaire.getQuestionnaireId());
            
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
    private void copyQuestionnaireLists(Questionnaire src, Questionnaire dest) {
        dest.setQuestionnaireQuestions(src.getQuestionnaireQuestions());
        dest.setQuestionnaireUsage(src.getQuestionnaireUsages());
        for (QuestionnaireQuestion question : dest.getQuestionnaireQuestions()) {
            question.setQuestionnaireId(null);
            question.setQuestionnaireQuestionsId(null);
            question.setVersionNumber(new Long(1));
        }
        for (QuestionnaireUsage usage : dest.getQuestionnaireUsages()) {
            usage.setQuestionnaireId(null);
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

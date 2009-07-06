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

import org.kuali.rice.kns.service.BusinessObjectService;

public class QuestionnaireServiceImpl implements QuestionnaireService{
    
    private QuestionnaireDao questionnaireDao;
    private BusinessObjectService businessObjectService;

    public void saveQuestionnaire(String sqlScripts, Questionnaire questionnaire) {
        businessObjectService.save(questionnaire);
        questionnaireDao.runScripts(sqlScripts.split("#;#"),questionnaire.getQuestionnaireId());
            
    }

    public void setQuestionnaireDao(QuestionnaireDao questionnaireDao) {
        this.questionnaireDao = questionnaireDao;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}

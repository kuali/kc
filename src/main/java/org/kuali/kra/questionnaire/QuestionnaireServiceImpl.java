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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class QuestionnaireServiceImpl implements QuestionnaireService {

    private QuestionnaireDao questionnaireDao;
    private BusinessObjectService businessObjectService;
    private QuestionnaireAuthorizationService questionnaireAuthorizationService;
    private Map <String, String> permissionModuleMap ;
    private  static final String[] MODIFY_MODULES_PERMISSIONS = {PermissionConstants.MODIFY_PROPOSAL, PermissionConstants.MODIFY_PROTOCOL};
    public QuestionnaireServiceImpl() {
        super();
        // TODO : (kcirb-378)this is a temporary to get questionnaire modules association list based on permission
        
        permissionModuleMap = new HashMap<String, String>();
        //permissionModuleMap.put(PermissionConstants.MODIFY_AWARD,"1");
//        permissionModuleMap.put(PermissionConstants.MODIFY_PROPOSAL,"2");
        permissionModuleMap.put(PermissionConstants.MODIFY_PROPOSAL,"3");
        //permissionModuleMap.put(PermissionConstants.MODIFY_PROTOCOL,"4");
        //permissionModuleMap.put(PermissionConstants.MODIFY_PROTOCOL,"5");
        //permissionModuleMap.put(PermissionConstants.MODIFY_PROTOCOL,"6");
        permissionModuleMap.put(PermissionConstants.MODIFY_PROTOCOL,"7");
        //permissionModuleMap.put(PermissionConstants.MODIFY_PROTOCOL,"8");
    }
    
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
        dest.setQuestionnaireUsages(src.getQuestionnaireUsages());
        for (QuestionnaireQuestion question : dest.getQuestionnaireQuestions()) {
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

    public List<String> getAssociateModules() {
        List<String> modules = new ArrayList<String>();
        for (String permission : MODIFY_MODULES_PERMISSIONS) {
            if (questionnaireAuthorizationService.hasPermission(permission)) {
                modules.add(permissionModuleMap.get(permission));
            }
        }
        return modules;
    }

    public void setQuestionnaireAuthorizationService(QuestionnaireAuthorizationService questionnaireAuthorizationService) {
        this.questionnaireAuthorizationService = questionnaireAuthorizationService;
    }
}

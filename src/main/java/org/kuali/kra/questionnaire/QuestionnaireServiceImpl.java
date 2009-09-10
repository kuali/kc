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

import org.kuali.kra.infrastructure.AwardPermissionConstants;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class QuestionnaireServiceImpl implements QuestionnaireService {

    private BusinessObjectService businessObjectService;
    private QuestionnaireAuthorizationService questionnaireAuthorizationService;
    private KualiConfigurationService kualiConfigurationService;
    private Map <String, String> permissionModuleMap ;
    private  static final String PARAM_NAME = "associateModuleQuestionnairePermission";

    public QuestionnaireServiceImpl() {
        super();
        // TODO : (kcirb-378)this is a temporary to get questionnaire modules association list based on permission
        permissionModuleMap = new HashMap<String, String>();
        permissionModuleMap.put(AwardPermissionConstants.MODIFY_AWARD.getAwardPermission(),"1");
        permissionModuleMap.put(PermissionConstants.MODIFY_PROPOSAL,"2");
        permissionModuleMap.put(PermissionConstants.MODIFY_PROPOSAL,"3");
        //permissionModuleMap.put(PermissionConstants.SUBCONTRACT,"4");
        //permissionModuleMap.put(PermissionConstants.NEGOTIATION,"5");
        //permissionModuleMap.put(PermissionConstants.MODIFY_PERSON,"6");
        permissionModuleMap.put(PermissionConstants.MODIFY_PROTOCOL,"7");
        //permissionModuleMap.put(PermissionConstants.MODIFY_COI,"8");
    }
    
    /**
     * 
     * @see org.kuali.kra.questionnaire.QuestionnaireService#isQuestionnaireNameExist(java.lang.Integer, java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public boolean isQuestionnaireNameExist(Integer questionnaireId, String name) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("name", name);
        boolean isExist = false;
        List<Questionnaire> questionnaires = (List<Questionnaire>) businessObjectService.findMatching(Questionnaire.class,
                fieldValues);
        for (Questionnaire questionnaire : questionnaires) {
            if (questionnaireId == null || !questionnaire.getQuestionnaireId().equals(questionnaireId)) {
                isExist = true;
                break;
            }
        }
        return isExist;
    }

    /**
     * 
     * @see org.kuali.kra.questionnaire.QuestionnaireService#copyQuestionnaire(org.kuali.kra.questionnaire.Questionnaire, org.kuali.kra.questionnaire.Questionnaire)
     */
    public void copyQuestionnaire(Questionnaire src, Questionnaire dest) {
        copyQuestionnaireLists(src, dest);

    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    private void copyQuestionnaireLists(Questionnaire src, Questionnaire dest) {
        dest.setQuestionnaireQuestions(src.getQuestionnaireQuestions());
        dest.setQuestionnaireUsages(src.getQuestionnaireUsages());
        dest.setQuestionnaireId(null);
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

    /**
     * 
     * @see org.kuali.kra.questionnaire.QuestionnaireService#getAssociateModules()
     */
    public List<String> getAssociateModules() {
        List<String> modules = new ArrayList<String>();
        for (String permission : kualiConfigurationService.getParameterValues(Constants.PARAMETER_MODULE_QUESTIONNAIRE,
                Constants.PARAMETER_COMPONENT_PERMISSION, PARAM_NAME)) {
            if (questionnaireAuthorizationService.hasPermission(permission)) {
                modules.add(permissionModuleMap.get(permission));
            }
        }
        return modules;
    }

    public void setQuestionnaireAuthorizationService(QuestionnaireAuthorizationService questionnaireAuthorizationService) {
        this.questionnaireAuthorizationService = questionnaireAuthorizationService;
    }

    public void setKualiConfigurationService(KualiConfigurationService kualiConfigurationService) {
        this.kualiConfigurationService = kualiConfigurationService;
    }

}

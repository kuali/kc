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
package org.kuali.kra.questionnaire;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.kuali.kra.infrastructure.AwardPermissionConstants;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.service.UnitAuthorizationService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class QuestionnaireServiceImpl implements QuestionnaireService {

    private static final String PARAM_NAME = "associateModuleQuestionnairePermission";
    private BusinessObjectService businessObjectService;
    private UnitAuthorizationService unitAuthorizationService;
    private ParameterService parameterService;
    private Map<String, String> permissionModuleMap;

    public QuestionnaireServiceImpl() {
        super();
        /*
         * TODO : permissionModuleMap is probably for initial release 2. See more comments on getAssociateModules method.
         */
        permissionModuleMap = new HashMap<String, String>();
        permissionModuleMap.put(AwardPermissionConstants.MODIFY_AWARD.getAwardPermission() + ":" + Constants.MODULE_NAMESPACE_AWARD_BUDGET, "1");
        permissionModuleMap.put(PermissionConstants.EDIT_INSTITUTE_PROPOSAL + ":" + Constants.INSTITUTIONAL_PROPOSAL_NAMESPACE,"2");
        permissionModuleMap.put(PermissionConstants.MAINTAIN_QUESTIONNAIRE_USAGE + ":" + Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, "3");
        //permissionModuleMap.put(PermissionConstants.MODIFY_PROPOSAL + ":" + "KC-PD", "3");
        // permissionModuleMap.put(PermissionConstants.SUBCONTRACT,"4");
        // permissionModuleMap.put(PermissionConstants.NEGOTIATION,"5");
        // permissionModuleMap.put(PermissionConstants.MODIFY_PERSON,"6");
        permissionModuleMap.put(PermissionConstants.MODIFY_PROTOCOL + ":" + "KC-PROTOCOL", "7");
        permissionModuleMap.put(PermissionConstants.MAINTAIN_QUESTIONNAIRE_USAGE + ":" + Constants.MODULE_NAMESPACE_PROTOCOL, "7");
        permissionModuleMap.put(PermissionConstants.MAINTAIN_COI_DISCLOSURE + ":" + Constants.MODULE_NAMESPACE_COIDISCLOSURE, "8");
    }

    /**
     * 
     * @see org.kuali.kra.questionnaire.QuestionnaireService#isQuestionnaireNameExist(java.lang.Integer, java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public boolean isQuestionnaireNameExist(String questionnaireId, String name) {
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
     * @see org.kuali.kra.questionnaire.QuestionnaireService#copyQuestionnaire(org.kuali.kra.questionnaire.Questionnaire,
     *      org.kuali.kra.questionnaire.Questionnaire)
     */
    public void copyQuestionnaire(Questionnaire src, Questionnaire dest) {
        copyQuestionnaireLists(src, dest);

    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    protected void copyQuestionnaireLists(Questionnaire src, Questionnaire dest) {
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
        /*
         * TODO : (kcirb-378)this is a temporary (for release 2) to get questionnaire modules association list based on permission
         * When integrated with KIM, this should be able to utilize KIM permission and permission attributes to accomplish this
         * task. The permission attributes could be a combination of module doce & coeus permission right
         */

        Set<String> modules = new HashSet<String>();
        Collection<String> parameters = this.parameterService.getParameterValuesAsString(Constants.PARAMETER_MODULE_QUESTIONNAIRE, Constants.PARAMETER_COMPONENT_PERMISSION, PARAM_NAME);
        for (String permission : parameters) {
            String[] params = permission.split(":");
            boolean unitAuthCheck = unitAuthorizationService.hasPermission(GlobalVariables.getUserSession().getPerson()
                    .getPrincipalId(), params[1], params[0]);
            if (unitAuthCheck && !modules.contains(permissionModuleMap.get(permission))) {
                modules.add(permissionModuleMap.get(permission));
            }
        }
        return new ArrayList<String>(modules);
    }


    public void setUnitAuthorizationService(UnitAuthorizationService unitAuthorizationService) {
        this.unitAuthorizationService = unitAuthorizationService;
    }

    /**
     * Sets the ParameterService.
     * 
     * @param parameterService the parameter service.
     */
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }


}

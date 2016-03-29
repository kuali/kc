/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.common.questionnaire.impl.core;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireService;
import org.kuali.coeus.common.framework.auth.UnitAuthorizationService;
import org.kuali.coeus.common.questionnaire.impl.QuestionnaireDao;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.kra.award.infrastructure.AwardPermissionConstants;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.coeus.common.questionnaire.framework.core.Questionnaire;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireQuestion;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireUsage;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component("questionnaireService")
@Transactional
public class QuestionnaireServiceImpl implements QuestionnaireService {

    private static final String PARAM_NAME = "associateModuleQuestionnairePermission";
    public static final String MODULE_ITEM_CODE = "moduleItemCode";
    public static final String MODULE_SUB_ITEM_CODE = "moduleSubItemCode";
    public static final String NAME = "name";
    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;
    @Autowired
    @Qualifier("unitAuthorizationService")
    private UnitAuthorizationService unitAuthorizationService;
    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    @Autowired
    @Qualifier("questionnaireDao")
    private QuestionnaireDao questionnaireDao;

    private Map<String, String> permissionModuleMap;

    public QuestionnaireServiceImpl() {
        super();
        permissionModuleMap = new HashMap<String, String>();
        permissionModuleMap.put(AwardPermissionConstants.MODIFY_AWARD.getAwardPermission() + ":" + Constants.MODULE_NAMESPACE_AWARD_BUDGET, "1");
        permissionModuleMap.put(PermissionConstants.EDIT_INSTITUTE_PROPOSAL + ":" + Constants.INSTITUTIONAL_PROPOSAL_NAMESPACE,"2");
        permissionModuleMap.put(PermissionConstants.MAINTAIN_QUESTIONNAIRE_USAGE + ":" + Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, "3");
        permissionModuleMap.put(PermissionConstants.MODIFY_PROTOCOL + ":" + "KC-PROTOCOL", "7");
        permissionModuleMap.put(PermissionConstants.MAINTAIN_QUESTIONNAIRE_USAGE + ":" + Constants.MODULE_NAMESPACE_IRB, "7");
        permissionModuleMap.put(PermissionConstants.MAINTAIN_QUESTIONNAIRE_USAGE + ":" + Constants.MODULE_NAMESPACE_COIDISCLOSURE, "8");
        permissionModuleMap.put(PermissionConstants.MAINTAIN_QUESTIONNAIRE_USAGE + ":" + Constants.MODULE_NAMESPACE_IACUC, "9");
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean isQuestionnaireNameExist(String questionnaireId, String name) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(NAME, name);
        boolean isExist = false;
        List<Questionnaire> questionnaires = (List<Questionnaire>) businessObjectService.findMatching(Questionnaire.class,
                fieldValues);
        for (Questionnaire questionnaire : questionnaires) {
            if (questionnaireId == null || !questionnaire.getQuestionnaireSeqId().equals(questionnaireId)) {
                isExist = true;
                break;
            }
        }
        return isExist;
    }

    /**
     * 
     * @see org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireService#copyQuestionnaire(org.kuali.coeus.common.questionnaire.framework.core.Questionnaire,
     *      org.kuali.coeus.common.questionnaire.framework.core.Questionnaire)
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
        dest.setQuestionnaireSeqId(null);
        for (QuestionnaireQuestion question : dest.getQuestionnaireQuestions()) {
            question.setQuestionnaireId(null);
            question.setId(null);
            question.setVersionNumber(new Long(1));
        }
        for (QuestionnaireUsage usage : dest.getQuestionnaireUsages()) {
            usage.setQuestionnaireId(null);
            usage.setId(null);
            usage.setVersionNumber(new Long(1));
        }

    }

    @Override
    public List<String> getAssociateModules() {

        Set<String> modules = new HashSet<String>();
        Collection<String> parameters = this.parameterService.getParameterValuesAsString(Constants.PARAMETER_MODULE_QUESTIONNAIRE, Constants.PARAMETER_COMPONENT_PERMISSION, PARAM_NAME);
        for (String permission : parameters) {
            String[] params = permission.split(":");
            boolean unitAuthCheck = unitAuthorizationService.hasPermission(globalVariableService.getUserSession().getPerson()
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
    
    @Override
    public boolean isUniqueUsage(Questionnaire questionnaire, QuestionnaireUsage usage) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(MODULE_ITEM_CODE, usage.getCoeusModule().getModuleCode());
        fieldValues.put(MODULE_SUB_ITEM_CODE, usage.getCoeusSubModule().getSubModuleCode());
        List<QuestionnaireUsage> usages = (List<QuestionnaireUsage>) businessObjectService.findMatching(QuestionnaireUsage.class, fieldValues);
        for (QuestionnaireUsage curUsage : usages) {
            if (!StringUtils.equals(questionnaire.getQuestionnaireSeqId(), curUsage.getQuestionnaire().getQuestionnaireSeqId())
                    && curUsage.getQuestionnaire().isActive() && isCurrentQuestionnaire(curUsage.getQuestionnaire())) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isCurrentQuestionnaire(Questionnaire questionnaire) {
        return questionnaire.getSequenceNumber().equals(getQuestionnaireDao().getCurrentQuestionnaireSequenceNumber(questionnaire.getQuestionnaireSeqId()));
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public UnitAuthorizationService getUnitAuthorizationService() {
        return unitAuthorizationService;
    }

    public ParameterService getParameterService() {
        return parameterService;
    }

    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }

    public void setQuestionnaireDao(QuestionnaireDao questionnaireDao) {
        this.questionnaireDao = questionnaireDao;
    }

    public QuestionnaireDao getQuestionnaireDao() {
        return questionnaireDao;
    }

}

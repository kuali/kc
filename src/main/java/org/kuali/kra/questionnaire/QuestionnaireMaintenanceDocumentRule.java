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

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.MessageMap;
import org.kuali.rice.kns.util.RiceKeyConstants;

/**
 * 
 * This class is to provide rule validation when questionnaire is to be approved.
 */
public class QuestionnaireMaintenanceDocumentRule extends MaintenanceDocumentRuleBase {

    /**
     * Constructs a CustomAttributeMaintenanceDocumentRule.java.
     */
    public QuestionnaireMaintenanceDocumentRule() {
        super();
    }
    
    /**
     * 
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */ 
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
            return validateQuestionnaire(document);
    }
    
    /**
     * 
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomApproveDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */
    @Override
    protected boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
            return validateQuestionnaire(document);
    }

    /**
     * 
     * This method to check whether if the required fields are ok and whether name exists.
     * @param maintenanceDocument
     * @return
     */
    private boolean validateQuestionnaire(MaintenanceDocument maintenanceDocument) {


        boolean valid = true;
        Questionnaire newQuestionnaire = (Questionnaire)maintenanceDocument.getNewMaintainableObject().getBusinessObject();
        MessageMap errorMap = GlobalVariables.getErrorMap();
        if (StringUtils.isBlank(newQuestionnaire.getName())) {
            errorMap.putError("document.newMaintainableObject.businessObject.name", RiceKeyConstants.ERROR_REQUIRED, "Questionnaire Name");
            valid = false;
        }
        if (StringUtils.isBlank(newQuestionnaire.getDescription())) {
            errorMap.putError("document.newMaintainableObject.businessObject.description", RiceKeyConstants.ERROR_REQUIRED, "Questionnaire Description");
            valid = false;
        }

        if (getQuestionnaireService().isQuestionnaireNameExist(
                newQuestionnaire.getQuestionnaireId(), newQuestionnaire.getName())) {
            errorMap.putError("document.newMaintainableObject.businessObject.name", KeyConstants.ERROR_QUESTIONNAIRE_NAME_EXIST);
            valid = false;

        }
        return valid;

    }
    private QuestionnaireService getQuestionnaireService() {
        return KraServiceLocator.getService(QuestionnaireService.class);
    }

}

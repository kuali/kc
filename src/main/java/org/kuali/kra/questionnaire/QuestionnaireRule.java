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
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.RiceKeyConstants;

public class QuestionnaireRule {
    public QuestionnaireRule() {
    }


    public boolean questionnaireRequiredFields(QuestionnaireForm questionnaireForm) {
        boolean valid = true;

        ErrorMap errorMap = GlobalVariables.getErrorMap();
        if (StringUtils.isBlank(questionnaireForm.getNewQuestionnaire().getName())) {
            errorMap.putError("newQuestionnaire.name", RiceKeyConstants.ERROR_REQUIRED, "Questionnaire Name");
            valid = false;
        }
        if (StringUtils.isBlank(questionnaireForm.getNewQuestionnaire().getDescription())) {
            errorMap.putError("newQuestionnaire.description", RiceKeyConstants.ERROR_REQUIRED, "Questionnaire Description");
            valid = false;
        }

        if (getQuestionnaireService().isQuestionnaireNameExist(
                questionnaireForm.getNewQuestionnaire().getQuestionnaireId(), questionnaireForm.getNewQuestionnaire().getName())) {
            errorMap.putError("newQuestionnaire.name", KeyConstants.ERROR_QUESTIONNAIRE_NAME_EXIST);
            valid = false;

        }
        return valid;
    }

    private QuestionnaireService getQuestionnaireService() {
        return KraServiceLocator.getService(QuestionnaireService.class);
    }
}

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
package org.kuali.kra.irb.actions.submit;

import org.drools.core.util.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rules.KraMaintenanceDocumentRuleBase;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.util.GlobalVariables;


/**
 * 
 * This class is the maintenance document rule for valid submission/review type table.
 */
public class ValidProtocolActionActionMaintenanceDocumentRule extends KraMaintenanceDocumentRuleBase {
    
    /**
     * 
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        ValidProtocolActionAction validProtocolActionAction = (ValidProtocolActionAction) document.getDocumentBusinessObject();
        return validate(validProtocolActionAction);
    }

    
    /**
     * 
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomApproveDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */
    @Override
    protected boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        ValidProtocolActionAction validProtocolActionAction = (ValidProtocolActionAction) document.getDocumentBusinessObject();
        return validate(validProtocolActionAction);
    }

    private boolean validate(ValidProtocolActionAction validProtocolActionAction) {
       boolean result = true;
       result &= validatePromptUser(validProtocolActionAction);
       return result;
    }

    
    private boolean validatePromptUser(ValidProtocolActionAction validProtocolActionAction) {
        boolean result = true;
        if (validProtocolActionAction.getUserPromptFlag() && StringUtils.isEmpty(validProtocolActionAction.getUserPrompt()) ) {
            GlobalVariables.getMessageMap().putError("document.newMaintainableObject.userPrompt",
                    KeyConstants.ERROR_FOLLOWUP_USER_PROMPT_REQUIRED,
                    new String[] { });
            result = false;

        } else if (!validProtocolActionAction.getUserPromptFlag() && !StringUtils.isEmpty(validProtocolActionAction.getUserPrompt())) {
            GlobalVariables.getMessageMap().putError("document.newMaintainableObject.userPrompt",
                    KeyConstants.ERROR_FOLLOWUP_USER_PROMPT_REQUIRED_EMPTY,
                    new String[] { });
            result = false;
        }
       return result; 
    }
}

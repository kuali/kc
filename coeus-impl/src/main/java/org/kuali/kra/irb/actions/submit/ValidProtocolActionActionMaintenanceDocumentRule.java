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
package org.kuali.kra.irb.actions.submit;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.util.GlobalVariables;


/**
 * 
 * This class is the maintenance document rule for valid submission/review type table.
 */
public class ValidProtocolActionActionMaintenanceDocumentRule extends KcMaintenanceDocumentRuleBase {
    
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        ValidProtocolActionAction validProtocolActionAction = (ValidProtocolActionAction) document.getDocumentBusinessObject();
        return validate(validProtocolActionAction);
    }

    
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
        if (validProtocolActionAction.getUserPromptFlag() && StringUtils.isBlank(validProtocolActionAction.getUserPrompt()) ) {
            GlobalVariables.getMessageMap().putError("document.newMaintainableObject.userPrompt",
                    KeyConstants.ERROR_FOLLOWUP_USER_PROMPT_REQUIRED,
                    new String[] { });
            result = false;

        } else if (!validProtocolActionAction.getUserPromptFlag() && StringUtils.isNotBlank(validProtocolActionAction.getUserPrompt())) {
            GlobalVariables.getMessageMap().putError("document.newMaintainableObject.userPrompt",
                    KeyConstants.ERROR_FOLLOWUP_USER_PROMPT_REQUIRED_EMPTY,
                    new String[] { });
            result = false;
        }
       return result; 
    }
}

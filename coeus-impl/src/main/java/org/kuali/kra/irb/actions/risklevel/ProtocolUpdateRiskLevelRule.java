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
package org.kuali.kra.irb.actions.risklevel;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * Validates the rules for a Protocol Risk Level update action.
 */
public class ProtocolUpdateRiskLevelRule extends KcTransactionalDocumentRuleBase implements KcBusinessRule<ProtocolUpdateRiskLevelEvent> {
    
    @Override
    public boolean processRules(ProtocolUpdateRiskLevelEvent event) {
        boolean isValid = true;
        
        int index = event.getIndex();
        String errorPathKey = Constants.PROTOCOL_UPDATE_RISK_LEVEL_KEY + "[" + index + "]";
        ProtocolRiskLevel persistedProtocolRiskLevel = event.getProtocolDocument().getProtocol().getProtocolRiskLevels().get(index);
        
        GlobalVariables.getMessageMap().addToErrorPath(errorPathKey);
        getDictionaryValidationService().validateBusinessObject(persistedProtocolRiskLevel);
        GlobalVariables.getMessageMap().removeFromErrorPath(errorPathKey);
        
        isValid &= GlobalVariables.getMessageMap().hasNoErrors();
        isValid &= validateDateInactivated(persistedProtocolRiskLevel, errorPathKey);
        
        return isValid;
    }
    
    /**
     * Verifies that on an update action, the dateInactivated is non-null.
     * 
     * @param updatedProtocolRiskLevel The updated Protocol Risk Level
     * @param errorPathKey The key on the page on which to visibly place the error (if any)
     * @return true if dateInactivated is non-null, false otherwise
     */
    private boolean validateDateInactivated(ProtocolRiskLevel updatedProtocolRiskLevel, String errorPathKey) {
        boolean isValid = true;
        
        if (updatedProtocolRiskLevel.getDateInactivated() == null) {
            isValid = false;
            GlobalVariables.getMessageMap().putError(errorPathKey + ".dateInactivated", 
                    KeyConstants.ERROR_PROTOCOL_DATE_INACTIVATED_REQUIRED);
        }
        
        return isValid;
    }

}

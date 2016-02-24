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

import java.util.List;

/**
 * Validates the rules for a Protocol Risk Level add action.
 */
public class ProtocolAddRiskLevelRule extends KcTransactionalDocumentRuleBase implements KcBusinessRule<ProtocolAddRiskLevelEvent> {
    
    @Override
    public boolean processRules(ProtocolAddRiskLevelEvent event) {
        boolean isValid = true;
        
        String errorPathKey = event.getPropertyName() + ".newProtocolRiskLevel";
        GlobalVariables.getMessageMap().addToErrorPath(errorPathKey);
        getDictionaryValidationService().validateBusinessObject(event.getProtocolRiskLevel());
        GlobalVariables.getMessageMap().removeFromErrorPath(errorPathKey);
        
        isValid &= GlobalVariables.getMessageMap().hasNoErrors();
        isValid &= validateOneEntryPerRiskLevel(event.getProtocolRiskLevel(), event.getProtocolDocument().getProtocol().getProtocolRiskLevels(), errorPathKey);
        
        return isValid;
    }
    
    /**
     * Verifies that there is only one type of Risk Level over all of the active entries.
     *
     * @param newProtocolRiskLevel The added Protocol Risk Level
     * @param protocolRiskLevels The list of all current Protocol Risk Levels
     * @param errorPathKey The key on the page on which to visibly place the error (if any)
     * @return true if there is only one type of Risk Level over all active entries, false otherwise
     */
    private boolean validateOneEntryPerRiskLevel(ProtocolRiskLevel newProtocolRiskLevel, List<ProtocolRiskLevel> protocolRiskLevels, String errorPathKey) {
        boolean isValid = true;
        
        for (ProtocolRiskLevel protocolRiskLevel : protocolRiskLevels) {
            if (protocolRiskLevel.getRiskLevelCode().equals(newProtocolRiskLevel.getRiskLevelCode()) 
                    && Constants.STATUS_ACTIVE.equals(protocolRiskLevel.getStatus()) 
                    && Constants.STATUS_ACTIVE.equals(newProtocolRiskLevel.getStatus())) {
                isValid = false;
                GlobalVariables.getMessageMap().putError(errorPathKey + ".riskLevelCode", 
                        KeyConstants.ERROR_PROTOCOL_DUPLICATE_RISK_LEVEL);
                continue;
            }
        }
        
        return isValid;
    }

}

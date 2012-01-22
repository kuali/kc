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
package org.kuali.kra.irb.actions.risklevel;

import java.util.List;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * Validates the rules for a Protocol Risk Level add action.
 */
public class ProtocolAddRiskLevelRule extends ResearchDocumentRuleBase implements BusinessRuleInterface<ProtocolAddRiskLevelEvent> {
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.rule.BusinessRuleInterface#processRules(org.kuali.kra.rule.event.KraDocumentEventBaseExtension)
     */
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

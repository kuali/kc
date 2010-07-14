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
import org.kuali.rice.kns.util.GlobalVariables;

public class ProtocolAddRiskLevelRule extends ResearchDocumentRuleBase implements BusinessRuleInterface<ProtocolAddRiskLevelEvent> {
    
    public boolean processRules(ProtocolAddRiskLevelEvent event) {
        boolean valid = true;
        
        String errorPathKey = event.getPropertyName() + ".newProtocolRiskLevel";
        GlobalVariables.getMessageMap().addToErrorPath(errorPathKey);
        getDictionaryValidationService().validateBusinessObject(event.getProtocolRiskLevel());
        GlobalVariables.getMessageMap().removeFromErrorPath(errorPathKey);
        
        valid &= GlobalVariables.getMessageMap().hasNoErrors();
        valid &= validateOneEntryPerRiskLevel(event.getProtocolRiskLevel(), event.getProtocolDocument().getProtocol().getProtocolRiskLevels(), errorPathKey);
        
        return valid;
    }
    
    private boolean validateOneEntryPerRiskLevel(ProtocolRiskLevel newProtocolRiskLevel, List<ProtocolRiskLevel> protocolRiskLevels, String errorPathKey) {
        boolean valid = true;
        
        for (ProtocolRiskLevel protocolRiskLevel : protocolRiskLevels) {
            if (protocolRiskLevel.getRiskLevelCode().equals(newProtocolRiskLevel.getRiskLevelCode()) 
                    && Constants.STATUS_ACTIVE.equals(protocolRiskLevel.getStatus()) 
                    && Constants.STATUS_ACTIVE.equals(newProtocolRiskLevel.getStatus())) {
                valid = false;
                GlobalVariables.getMessageMap().putError(errorPathKey + ".riskLevelCode", 
                        KeyConstants.ERROR_PROTOCOL_DUPLICATE_RISK_LEVEL);
                continue;
            }
        }
        
        return valid;
    }

}

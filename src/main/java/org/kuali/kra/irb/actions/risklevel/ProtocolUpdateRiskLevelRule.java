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

import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.util.GlobalVariables;

public class ProtocolUpdateRiskLevelRule extends ResearchDocumentRuleBase implements BusinessRuleInterface<ProtocolUpdateRiskLevelEvent> {
    
    public boolean processRules(ProtocolUpdateRiskLevelEvent event) {
        boolean valid = true;
        
        int index = event.getIndex();
        String errorPathKey = event.getPropertyName() + "[" + index + "]";
        ProtocolRiskLevel persistedProtocolRiskLevel = event.getProtocolDocument().getProtocol().getProtocolRiskLevels().get(index);
        
        GlobalVariables.getMessageMap().addToErrorPath(errorPathKey);
        getDictionaryValidationService().validateBusinessObject(persistedProtocolRiskLevel);
        GlobalVariables.getMessageMap().removeFromErrorPath(errorPathKey);
        
        valid &= GlobalVariables.getMessageMap().hasNoErrors();
        valid &= validateDateInactivated(persistedProtocolRiskLevel, errorPathKey);
        
        return valid;
    }
    
    private boolean validateDateInactivated(ProtocolRiskLevel updatedProtocolRiskLevel, String errorPathKey) {
        boolean valid = true;
        
        if (updatedProtocolRiskLevel.getDateInactivated() == null) {
            valid = false;
            GlobalVariables.getMessageMap().putError(errorPathKey + ".dateInactivated", 
                    KeyConstants.ERROR_PROTOCOL_DATE_INACTIVATED_REQUIRED);
        }
        
        return valid;
    }

}

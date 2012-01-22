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

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * Validates the rules for a Protocol Risk Level update action.
 */
public class ProtocolUpdateRiskLevelRule extends ResearchDocumentRuleBase implements BusinessRuleInterface<ProtocolUpdateRiskLevelEvent> {
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.rule.BusinessRuleInterface#processRules(org.kuali.kra.rule.event.KraDocumentEventBaseExtension)
     */
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

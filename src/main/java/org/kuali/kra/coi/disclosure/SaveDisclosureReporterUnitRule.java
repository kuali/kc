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
package org.kuali.kra.coi.disclosure;

import org.kuali.kra.coi.DisclosureReporterUnit;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.krad.util.GlobalVariables;

public class SaveDisclosureReporterUnitRule extends ResearchDocumentRuleBase implements BusinessRuleInterface<SaveDisclosureReporterUnitEvent> {
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.rule.BusinessRuleInterface#processRules(org.kuali.kra.rule.event.KraDocumentEventBaseExtension)
     */
    public boolean processRules(SaveDisclosureReporterUnitEvent event) {
        boolean isValid = true;
        
//        String errorPathKey = event.getPropertyName() + ".financialEntityReporter";
        GlobalVariables.getMessageMap().addToErrorPath(event.getPropertyName());
        if (org.apache.commons.collections.CollectionUtils.isEmpty(event.getDisclosureReporterUnits())) {
            GlobalVariables.getMessageMap().putError("unitNumber",
            KeyConstants.ERROR_ONE_UNIT, "Reporter");
            
        } else {
            boolean leadUnitFound = false;
            for (DisclosureReporterUnit unit : event.getDisclosureReporterUnits()) {
                if (unit.isLeadUnitFlag()) {
                    leadUnitFound = true;
                    break;
                }
            }
            if (!leadUnitFound) {
                GlobalVariables.getMessageMap().putError("unitNumber",
                        KeyConstants.ERROR_LEAD_UNIT_REQUIRED);
                
            }
            
        }
        isValid &= GlobalVariables.getMessageMap().hasNoErrors();
        GlobalVariables.getMessageMap().removeFromErrorPath(event.getPropertyName());
        
        return isValid;
    }


}

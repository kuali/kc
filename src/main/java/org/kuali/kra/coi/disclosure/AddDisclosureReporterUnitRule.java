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

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.coi.DisclosureReporterUnit;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.krad.util.GlobalVariables;

public class AddDisclosureReporterUnitRule extends ResearchDocumentRuleBase implements BusinessRuleInterface<AddDisclosureReporterUnitEvent> {
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.rule.BusinessRuleInterface#processRules(org.kuali.kra.rule.event.KraDocumentEventBaseExtension)
     */
    public boolean processRules(AddDisclosureReporterUnitEvent event) {
        boolean isValid = true;

//        String errorPathKey = event.getPropertyName() + ".newDisclosurePersonUnit";
        GlobalVariables.getMessageMap().addToErrorPath(event.getPropertyName());
        if (StringUtils.isBlank(event.getDisclosureReporterUnit().getUnitNumber())) {
            GlobalVariables.getMessageMap().putError("unitNumber", KeyConstants.ERROR_UNIT_NUMBER_REQUIRED);

        }
        else {
            if (!CollectionUtils.isEmpty(event.getDisclosureReporterUnits())) {
                boolean duplicateUnitNumber = false;
                for (DisclosureReporterUnit unit : event.getDisclosureReporterUnits()) {
                    if (StringUtils.equals(unit.getUnitNumber(), event.getDisclosureReporterUnit().getUnitNumber())) {
                        duplicateUnitNumber = true;
                        break;
                    }
                }
                if (duplicateUnitNumber) {
                    GlobalVariables.getMessageMap().putError("unitNumber", KeyConstants.ERROR_PROTOCOL_UNIT_DUPLICATE);

                }

            }
            if (event.getDisclosureReporterUnit().getUnit() == null) {
                GlobalVariables.getMessageMap().putError("unitNumber", KeyConstants.ERROR_INVALID_UNIT, event.getDisclosureReporterUnit().getUnitNumber());                
            }
        }

        isValid &= GlobalVariables.getMessageMap().hasNoErrors();
        GlobalVariables.getMessageMap().removeFromErrorPath(event.getPropertyName());
        return isValid;
    }

}

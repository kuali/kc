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
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.util.GlobalVariables;

public class AddDisclosurePersonUnitRule extends ResearchDocumentRuleBase implements BusinessRuleInterface<AddDisclosurePersonUnitEvent> {
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.rule.BusinessRuleInterface#processRules(org.kuali.kra.rule.event.KraDocumentEventBaseExtension)
     */
    public boolean processRules(AddDisclosurePersonUnitEvent event) {
        boolean isValid = true;

        String errorPathKey = event.getPropertyName() + ".newDisclosurePersonUnit";
        GlobalVariables.getMessageMap().addToErrorPath(errorPathKey);
        if (StringUtils.isBlank(event.getDisclosurePersonUnit().getUnitNumber())) {
            GlobalVariables.getMessageMap().putError("unitNumber", KeyConstants.ERROR_UNIT_NUMBER_REQUIRED);

        }
        else {
            if (!CollectionUtils.isEmpty(event.getDisclosurePersonUnits())) {
                boolean duplicateUnitNumber = false;
                for (DisclosurePersonUnit unit : event.getDisclosurePersonUnits()) {
                    if (StringUtils.equals(unit.getUnitNumber(), event.getDisclosurePersonUnit().getUnitNumber())) {
                        duplicateUnitNumber = true;
                        break;
                    }
                }
                if (duplicateUnitNumber) {
                    GlobalVariables.getMessageMap().putError("unitNumber", KeyConstants.ERROR_PROTOCOL_UNIT_DUPLICATE);

                }

            }
        }

        isValid &= GlobalVariables.getMessageMap().hasNoErrors();
        GlobalVariables.getMessageMap().removeFromErrorPath(errorPathKey);
        return isValid;
    }

}

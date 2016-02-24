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
package org.kuali.kra.coi.disclosure;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.kra.coi.DisclosureReporterUnit;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.GlobalVariables;

public class AddDisclosureReporterUnitRule extends KcTransactionalDocumentRuleBase implements KcBusinessRule<AddDisclosureReporterUnitEvent> {
    
    @Override
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

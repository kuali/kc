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

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.kra.coi.DisclosureReporterUnit;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.GlobalVariables;

public class SaveDisclosureReporterUnitRule extends KcTransactionalDocumentRuleBase implements KcBusinessRule<SaveDisclosureReporterUnitEvent> {
    
    @Override
    public boolean processRules(SaveDisclosureReporterUnitEvent event) {
        boolean isValid = true;
        
//        String errorPathKey = event.getPropertyName() + ".financialEntityReporter";
        GlobalVariables.getMessageMap().addToErrorPath(event.getPropertyName());
        if (org.apache.commons.collections4.CollectionUtils.isEmpty(event.getDisclosureReporterUnits())) {
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

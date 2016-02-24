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
package org.kuali.kra.award.contacts;

import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * This class implements the specified rule
 */
public class AwardPersonUnitAddRuleImpl implements AwardPersonUnitAddRule {

    @Override
    public boolean processAddAwardPersonUnitBusinessRules(AwardPersonUnitRuleAddEvent event) {
        boolean valid = checkForDuplicateUnits(event.getProjectPerson(), event.getNewPersonUnit(), event.getAddUnitPersonIndex());
        valid &= checkForInvalidUnit(event.getNewPersonUnit(), event.getAddUnitPersonIndex());
        return valid;
    }
        
    boolean checkForDuplicateUnits(AwardPerson projectPerson, AwardPersonUnit newAwardPersonUnit, int addUnitPersonIndex) {
        boolean valid = true;
        for(AwardPersonUnit apu: projectPerson.getUnits()) {
            valid = !apu.getUnit().equals(newAwardPersonUnit.getUnit());
            if(!valid) {
                break;
            }
        }
        
        if(!valid) {
            Unit dupeUnit = newAwardPersonUnit.getUnit();
            GlobalVariables.getMessageMap().putError(AWARD_PROJECT_PERSON_LIST_ERROR_KEY+"["+addUnitPersonIndex+"].unitNumber", 
                                                        ERROR_AWARD_PROJECT_PERSON_DUPLICATE_UNITS, 
                                                        dupeUnit.getUnitName(), dupeUnit.getUnitNumber(),
                                                        projectPerson.getFullName());
        }
        
        return valid;
    }
    
    boolean checkForInvalidUnit(AwardPersonUnit newAwardPersonUnit, int addUnitPersonIndex) {
        if (newAwardPersonUnit.getUnit() == null) {
            GlobalVariables.getMessageMap().putError(AWARD_PROJECT_PERSON_LIST_ERROR_KEY+"["+addUnitPersonIndex+"].unitNumber", ERROR_AWARD_PROJECT_PERSON_INVAILD_UNIT);
            return false;
        }
        return true;
    }

}

/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.award.contacts;

import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * This class implements the specified rule
 */
public class AwardPersonUnitAddRuleImpl implements AwardPersonUnitAddRule {

    /**
     * @see org.kuali.kra.award.contacts.AwardPersonUnitAddRule
     */
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

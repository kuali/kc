/*
 * Copyright 2006-2008 The Kuali Foundation
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

import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.bo.Unit;

/**
 * This class implements the specified rule
 */
public class AwardPersonUnitAddRuleImpl implements AwardPersonUnitAddRule {

    /**
     * @see org.kuali.kra.award.contacts.AwardPersonUnitAddRule
     */
    public boolean processAddAwardPersonUnitBusinessRules(AwardPersonUnitRuleAddEvent event) {
        return checkForDuplicateUnits(event.getProjectPerson(), event.getNewPersonUnit());
    }
        
    boolean checkForDuplicateUnits(AwardPerson projectPerson, AwardPersonUnit newAwardPersonUnit) {
        boolean valid = true;
        for(AwardPersonUnit apu: projectPerson.getUnits()) {
            valid = !apu.getUnit().equals(newAwardPersonUnit.getUnit());
            if(!valid) {
                break;
            }
        }
        
        if(!valid) {
            Unit dupeUnit = newAwardPersonUnit.getUnit();
            GlobalVariables.getErrorMap().putError(AWARD_PROJECT_PERSON_LIST_ERROR_KEY, 
                                                        ERROR_AWARD_PROJECT_PERSON_DUPLICATE_UNITS, 
                                                        dupeUnit.getUnitName(), dupeUnit.getUnitNumber(),
                                                        projectPerson.getFullName());
        }
        
        return valid;
    }

}

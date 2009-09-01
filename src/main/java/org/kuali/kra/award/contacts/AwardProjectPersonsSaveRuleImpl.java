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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.kuali.kra.bo.Unit;
import org.kuali.rice.kns.util.GlobalVariables;


/**
 * This defines the rules for AwardProjectPerson
 */
public class AwardProjectPersonsSaveRuleImpl implements AwardProjectPersonsSaveRule {
    
    /**
     * This method will be called when saving Award Project Persons
     * @param event
     * @return
     */
    public boolean processSaveAwardProjectPersonsBusinessRules(SaveAwardProjectPersonsRuleEvent event) {
        List<AwardPerson> projectPersons = event.getProjectPersons();
        if (projectPersons.size() == 0) {
            return true;
        }
        
        boolean valid = checkForDuplicateUnits(projectPersons);
        
        return true;
    }
    
    boolean checkForDuplicateUnits(List<AwardPerson> projectPersons) {
        boolean valid = true;
        for(AwardPerson p: projectPersons) {
            Set<Unit> uniqueUnits = new HashSet<Unit>();
            List<Unit> tempUnits = new ArrayList<Unit>();
            for(AwardPersonUnit apu: p.getUnits()) {
                uniqueUnits.add(apu.getUnit());
                tempUnits.add(apu.getUnit());
            }
            
            valid &= tempUnits.size() == uniqueUnits.size();
            if(!valid) {
                // remove unique units from list of all units
                for(Unit u: uniqueUnits) {
                    tempUnits.remove(u);
                }
                // remove duplicates from remaining units
                Set<Unit> duplicateUnits = new HashSet<Unit>(tempUnits);
                for(Unit dupeUnit: duplicateUnits) {
                    GlobalVariables.getErrorMap().putError(AWARD_PROJECT_PERSON_LIST_ERROR_KEY, 
                                                            ERROR_AWARD_PROJECT_PERSON_DUPLICATE_UNITS, 
                                                            dupeUnit.getUnitName(), dupeUnit.getUnitNumber(),
                                                            p.getFullName());
                }
            }
        }
        
        return valid;
    }
    
}

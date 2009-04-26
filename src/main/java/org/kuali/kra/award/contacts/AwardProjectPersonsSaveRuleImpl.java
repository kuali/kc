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
        
        boolean valid = checkForAPrincipalInvestigator(projectPersons);
        valid &= checkForUnitDetails(projectPersons);
        valid &= checkForLeadUnit(projectPersons);
        valid &= checkForDuplicateUnits(projectPersons);
        
        return valid;
    }

    boolean checkForAPrincipalInvestigator(List<AwardPerson> projectPersons) {
        int piCount = 0;
        for(AwardPerson p: projectPersons) {
            if(p.isPrincipalInvestigator()) {
                piCount++; 
            }
        }
        return handlePiCount(piCount); 
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
    
    boolean checkForLeadUnit(List<AwardPerson> projectPersons) {
        boolean valid = true;
OUTER:  for(AwardPerson p: projectPersons) {
            if(p.isPrincipalInvestigator() ) {
                valid = false;
                for(AwardPersonUnit apu: p.getUnits()) {
                    if(apu.isLeadUnit()) {
                        valid = true;
                        break OUTER;
                    }
                }
                if(!valid) {
                    GlobalVariables.getErrorMap().putError(AWARD_PROJECT_PERSON_LIST_ERROR_KEY, 
                                                            ERROR_AWARD_PROJECT_PERSON_LEAD_UNIT_REQUIRED);
                }
                break OUTER;
            }
        }
        return valid; 
    }
    
    boolean checkForUnitDetails(List<AwardPerson> projectPersons) {
        boolean valid = true;
        for(AwardPerson p: projectPersons) {
            if(p.isPrincipalInvestigator() || p.isCoInvestigator()) {
                valid = p.getUnits().size() > 0;
                if(!valid) {
                    GlobalVariables.getErrorMap().putError(AWARD_PROJECT_PERSON_LIST_ERROR_KEY, 
                                                            ERROR_AWARD_PROJECT_PERSON_UNIT_DETAILS_REQUIRED, p.getFullName());
                }
            }
        }
        return valid; 
    }

    private boolean handlePiCount(int piCount) {
        boolean valid;
        switch(piCount) {
            case 0:
                GlobalVariables.getErrorMap().putError(AWARD_PROJECT_PERSON_LIST_ERROR_KEY, ERROR_AWARD_PROJECT_PERSON_NO_PI);
                valid = false;
                break;
            case 1:
                valid = true;
                break;
            default:
                GlobalVariables.getErrorMap().putError(AWARD_PROJECT_PERSON_LIST_ERROR_KEY, ERROR_AWARD_PROJECT_PERSON_MULTIPLE_PI_EXISTS);
                valid = false;
        }
        return valid;
    }
}

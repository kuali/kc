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
package org.kuali.kra.award.contacts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.bo.Unit;
import org.kuali.rice.krad.util.GlobalVariables;


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
        return processSaveAwardProjectPersonsBusinessRules(event.getProjectPersons());
    }

    /**
     * Default access to allow unit test access since SaveAwardProjectPersonsRuleEvent needs a Document to be constructed   
     * @param projectPersons
     * @return
     */
    boolean processSaveAwardProjectPersonsBusinessRules(List<AwardPerson> projectPersons) {
        if (projectPersons.size() == 0) {
            return true;
        }
        
        boolean valid = checkForDuplicateUnits(projectPersons);
        valid &= checkForKeyPersonProjectRoles(projectPersons);
        valid &= checkForOnePrincipalInvestigator(projectPersons);
        valid &= checkForRequiredUnitDetails(projectPersons);
        valid &= checkForLeadUnitForPI(projectPersons);

        return valid;
    }

    boolean checkForKeyPersonProjectRoles(List<AwardPerson> projectPersons) {
       boolean valid = true;
       for ( AwardPerson person : projectPersons ) {
           if ( StringUtils.equalsIgnoreCase(person.getContactRole().getRoleCode(), ContactRole.KEY_PERSON_CODE) &&
                   StringUtils.isBlank(person.getKeyPersonRole()) ) {
               valid = false;
               GlobalVariables.getMessageMap().putError(AWARD_PROJECT_PERSON_LIST_ERROR_KEY, 
                                       ERROR_AWARD_PROJECT_KEY_PERSON_ROLE_REQUIRED, 
                                       person.getFullName());
           }
       }
       return valid;
    }

    /**
     * Unit details are required for PI and COI persons
     * @param projectPersons
     * @return
     */
    boolean checkForRequiredUnitDetails(List<AwardPerson> projectPersons) {
        boolean valid = true;
        for(AwardPerson p: projectPersons) {
            if(p.isPrincipalInvestigator() || p.isCoInvestigator()
                    || (p.isKeyPerson() && p.isOptInUnitStatus())) {
                if(p.getUnits().size() == 0) {
                    valid = false;
                    GlobalVariables.getMessageMap().putError(AWARD_PROJECT_PERSON_LIST_ERROR_KEY,
                                                                ERROR_AWARD_PROJECT_PERSON_UNIT_DETAILS_REQUIRED,
                                                                p.getFullName());
                }
            }
        }
        return valid;
    }

    /**
     * Duplicate units not allowed
     * @param projectPersons
     * @return
     */
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
                    GlobalVariables.getMessageMap().putError(AWARD_PROJECT_PERSON_LIST_ERROR_KEY, 
                                                            ERROR_AWARD_PROJECT_PERSON_DUPLICATE_UNITS, 
                                                            dupeUnit.getUnitName(), dupeUnit.getUnitNumber(),
                                                            p.getFullName());
                }
            }
        }
        
        return valid;
    }

    /**
     * Only one PI allowed
     * @param projectPersons
     * @return
     */
    boolean checkForOnePrincipalInvestigator(List<AwardPerson> projectPersons) {
        int count = 0;
        for(AwardPerson p: projectPersons) {
            if(p.isPrincipalInvestigator()) {
                count++;
            }
        }
        boolean result = count == 1;
        if(!result) {
            if(count == 0) {
                GlobalVariables.getMessageMap().putError(AWARD_PROJECT_PERSON_LIST_ERROR_KEY, ERROR_AWARD_PROJECT_PERSON_NO_PI);                
            } else {
                GlobalVariables.getMessageMap().putError(AWARD_PROJECT_PERSON_LIST_ERROR_KEY, ERROR_AWARD_PROJECT_PERSON_MULTIPLE_PI_EXISTS);
            }
        }
        return result;
    }

    /**
     * PI must have lead unit
     * @param projectPersons
     * @return
     */
    boolean checkForLeadUnitForPI(List<AwardPerson> projectPersons) {
        boolean valid = true;
        for(AwardPerson p: projectPersons) {
            if(p.isPrincipalInvestigator()) {
                boolean found = false;
                for(AwardPersonUnit apu: p.getUnits()) {
                    found = apu.isLeadUnit();
                    if(found) {
                       break;
                    }
                }
                valid = found;
                if(!valid) {
                    GlobalVariables.getMessageMap().putError(AWARD_PROJECT_PERSON_LIST_ERROR_KEY, ERROR_AWARD_PROJECT_PERSON_LEAD_UNIT_REQUIRED);
                }
            }
        }
        return valid;
    }
}

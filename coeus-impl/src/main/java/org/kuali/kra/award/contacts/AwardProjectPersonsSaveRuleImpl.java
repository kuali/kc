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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * This defines the rules for AwardProjectPerson
 */
public class AwardProjectPersonsSaveRuleImpl implements AwardProjectPersonsSaveRule {
    
    public static final String ERROR_UNIT_REQUIRED = "error.select.unit";
    
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
           if ( StringUtils.equalsIgnoreCase(person.getContactRoleCode(), ContactRole.KEY_PERSON_CODE) &&
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
        int personCount = 0;
        for(AwardPerson p: projectPersons) {
            if(p.isPrincipalInvestigator() || p.isCoInvestigator()
                    || (p.isKeyPerson() && p.isOptInUnitStatus())) {
                personCount = personCount + 1;
                if(p.getUnits().size() == 0) {
                    personCount = personCount - 1;
                    valid = false;
                    if (p.getFullName() != null) {
                        GlobalVariables.getMessageMap().putError(AWARD_PROJECT_PERSON_LIST_ERROR_KEY,
                                                                ERROR_AWARD_PROJECT_PERSON_UNIT_DETAILS_REQUIRED,
                                                                p.getFullName());
                    } else {
                        if (p.isEmployee()) { 
                            GlobalVariables.getMessageMap().putError(AWARD_PROJECT_PERSON_LIST_ERROR_KEY,
                                    ERROR_AWARD_PROJECT_PERSON_UNIT_DETAILS_REQUIRED,
                                    p.getPerson().getUnit().getUnitName());                        
                        } else {
                            GlobalVariables.getMessageMap().putError(AWARD_PROJECT_PERSON_LIST_ERROR_KEY,
                                    ERROR_AWARD_PROJECT_PERSON_UNIT_DETAILS_REQUIRED,
                                    p.getRolodex().getOrganization());
                        }
                    }
                    GlobalVariables.getMessageMap().putError(AWARD_PROJECT_PERSON_LIST_ERROR_KEY+"["+personCount+"].personUnitNumber", ERROR_UNIT_REQUIRED);
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

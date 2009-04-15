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

import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.bo.Unit;

/**
 * This class implements the specified rule
 */
public class AwardProjectPersonAddRuleImpl implements AwardProjectPersonAddRule {

    /**
     * @see org.kuali.kra.award.contacts.AwardProjectPersonAddRule
     */
    public boolean processAddAwardProjectPersonBusinessRules(AwardProjectPersonRuleAddEvent event) {
        AwardPerson newProjectPerson = event.getNewProjectPerson();
        Award award = ((AwardDocument) event.getDocument()).getAward();
        
        boolean valid = checkForExistingPrincipalInvestigators(award, newProjectPerson);
        valid &= checkForDuplicatePerson(award, newProjectPerson);
        
        return valid;
    }
    
    boolean checkForExistingPrincipalInvestigators(Award award, AwardPerson newProjectPerson) {
        boolean valid = true;
        if(newProjectPerson.isPrincipalInvestigator()) {
            for(AwardPerson p: award.getProjectPersons()) {
                if(p.isPrincipalInvestigator()) {
                    valid = false;
                    break;
                }
            }
        }
        
        if(!valid) {
            GlobalVariables.getErrorMap().putError(AWARD_PROJECT_PERSON_LIST_ERROR_KEY, ERROR_AWARD_PROJECT_PERSON_PI_EXISTS);
        }
        
        return valid;
    }

    boolean checkForDuplicatePerson(Award award, AwardPerson newProjectPerson) {
        boolean valid = true;
        for(AwardPerson p: award.getProjectPersons()) {
            if (p.getClass().equals(newProjectPerson.getClass()) 
                    && p.getContact().getIdentifier().equals(newProjectPerson.getContact().getIdentifier())) {
                valid = false;
                break;
            }
        }
        
        if(!valid) {
            GlobalVariables.getErrorMap().putError(AWARD_PROJECT_PERSON_LIST_ERROR_KEY, ERROR_AWARD_PROJECT_PERSON_EXISTS, 
                                                                                newProjectPerson.getContact().getFullName());
        }
        
        return valid;
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

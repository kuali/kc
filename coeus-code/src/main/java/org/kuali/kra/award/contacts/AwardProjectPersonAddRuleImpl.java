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

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * This class implements the specified rule
 */
public class AwardProjectPersonAddRuleImpl extends BaseAwardContactAddRule implements AwardProjectPersonAddRule {

    @Override
    public boolean processAddAwardProjectPersonBusinessRules(AwardProjectPersonRuleAddEvent event) {
        AwardPerson newProjectPerson = event.getNewProjectPerson();
        Award award = ((AwardDocument) event.getDocument()).getAward();
        
        return checkForSelectedContactAndRole(newProjectPerson) 
            && (checkForExistingPrincipalInvestigators(award, newProjectPerson) & checkForDuplicatePerson(award, newProjectPerson))
            && checkForKeyPersonProjectRoles(newProjectPerson);
    }
    
    boolean checkForSelectedContactAndRole(AwardContact newContact) {
        return super.checkForSelectedContactAndRole(newContact, AWARD_PROJECT_PERSON_LIST_ERROR_KEY);
    }
    
    /**
     * Verify a PI exists
     * @param award
     * @param newProjectPerson
     * @return
     */
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
            GlobalVariables.getMessageMap().putError(AWARD_PROJECT_PERSON_LIST_ERROR_KEY, ERROR_AWARD_PROJECT_PERSON_PI_EXISTS);
        }
        
        return valid;
    }

    /**
     * Verify no duplicate person
     * @param award
     * @param newProjectPerson
     * @return
     */
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
            GlobalVariables.getMessageMap().putError(AWARD_PROJECT_PERSON_LIST_ERROR_KEY, ERROR_AWARD_PROJECT_PERSON_EXISTS, 
                                                                                newProjectPerson.getContact().getFullName());
        }
        
        return valid;
    }
    
    boolean checkForKeyPersonProjectRoles(AwardPerson newProjectPerson) {
        boolean valid = true;
        
        if (StringUtils.equalsIgnoreCase(newProjectPerson.getContactRole().getRoleCode(), ContactRole.KEY_PERSON_CODE) 
                && StringUtils.isBlank(newProjectPerson.getKeyPersonRole())) {
            valid = false;
            GlobalVariables.getMessageMap().putError(AWARD_PROJECT_PERSON_LIST_ERROR_KEY + ".keyPersonRole", ERROR_AWARD_PROJECT_KEY_PERSON_ROLE_REQUIRED, 
                    newProjectPerson.getFullName());
        }
        
        return valid;
    }

}

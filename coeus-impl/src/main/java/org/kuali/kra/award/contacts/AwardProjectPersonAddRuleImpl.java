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

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

import org.kuali.kra.award.home.Award;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * This class implements the specified rule
 */
public class AwardCentralAdminAddRuleImpl extends BaseAwardContactAddRule {
    public static final String AWARD_CENTRAL_ADMIN_LIST_ERROR_KEY = "centralAdminContactsBean.newAwardContact";
    public static final String ERROR_AWARD_CENTRAL_ADMIN_EXISTS = "error.awardCentralAdmin.person.exists";
    
    /**
     * @param event
     * @return
     */
    public boolean processAddAwardCentralAdminContactBusinessRules(Award award, AwardUnitContact newUnitContact) {
        return checkForSelectedContactAndRole(newUnitContact) && checkForDuplicatePerson(award, newUnitContact);
    }

    /**
     * @param newContact
     * @return
     */
    boolean checkForSelectedContactAndRole(AwardContact newContact) {
        return super.checkForSelectedContactAndRole(newContact, AWARD_CENTRAL_ADMIN_LIST_ERROR_KEY);
    }
    
    boolean checkForDuplicatePerson(Award award, AwardUnitContact newUnitContact) {
        boolean valid = true;
        for(AwardUnitContact unitContact: award.getAwardUnitContacts()) {
            valid = !unitContact.getPersonId().equals(newUnitContact.getPersonId());
            if(!valid) {
                registerError(newUnitContact);
                break;
            }
        }
        
        return valid;
    }

    private void registerError(AwardUnitContact newUnitContact) {
        GlobalVariables.getMessageMap().putError(AWARD_CENTRAL_ADMIN_LIST_ERROR_KEY, ERROR_AWARD_CENTRAL_ADMIN_EXISTS, 
                                                newUnitContact.getContact().getFullName());        
    }
}

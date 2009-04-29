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

import org.kuali.kra.award.bo.Award;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * This class implements the specified rule
 */
public class AwardSponsorContactAddRuleImpl extends BaseAwardContactAddRule {
    public static final String AWARD_SPONSOR_CONTACT_LIST_ERROR_KEY = "sponsorContactsBean.newAwardContact";
    public static final String ERROR_AWARD_SPONSOR_CONTACT_EXISTS = "error.awardSponsorContact.person.exists";
    
    /**
     * @param event
     * @return
     */
    public boolean processAddAwardSponsorContactBusinessRules(Award award, AwardSponsorContact newContact) {
        return checkForSelectedContactAndRole(newContact, AWARD_SPONSOR_CONTACT_LIST_ERROR_KEY) && checkForDuplicatePerson(award, newContact);
    }

    boolean checkForDuplicatePerson(Award award, AwardSponsorContact newContact) {
        boolean valid = true;
        for(AwardSponsorContact unitContact: award.getSponsorContacts()) {
            valid = !unitContact.getRolodexId().equals(newContact.getRolodexId());
            if(!valid) {
                GlobalVariables.getErrorMap().putError(AWARD_SPONSOR_CONTACT_LIST_ERROR_KEY, ERROR_AWARD_SPONSOR_CONTACT_EXISTS, newContact.getFullName());
                break;
            }
        }
        
        return valid;
    }
}

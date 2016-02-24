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

import org.kuali.kra.award.home.Award;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * This class implements the specified rule
 */
public class AwardSponsorContactAddRuleImpl extends BaseAwardContactAddRule {
    public static final String AWARD_SPONSOR_CONTACT_LIST_ERROR_KEY = "sponsorContactsBean.newAwardContact";
    public static final String ERROR_AWARD_SPONSOR_CONTACT_EXISTS = "error.awardSponsorContact.person.exists";

    public boolean processAddAwardSponsorContactBusinessRules(Award award, AwardSponsorContact newContact) {
        return checkForSelectedContactAndRole(newContact, AWARD_SPONSOR_CONTACT_LIST_ERROR_KEY) && checkForDuplicatePerson(award, newContact);
    }

    boolean checkForDuplicatePerson(Award award, AwardSponsorContact newContact) {
        boolean valid = true;
        for(AwardSponsorContact unitContact: award.getSponsorContacts()) {
            valid = !(unitContact.getRolodexId().equals(newContact.getRolodexId()) && unitContact.getRoleCode().equalsIgnoreCase(newContact.getContactRoleCode()));
            if(!valid) {
                GlobalVariables.getMessageMap().putError(AWARD_SPONSOR_CONTACT_LIST_ERROR_KEY, ERROR_AWARD_SPONSOR_CONTACT_EXISTS, newContact.getFullName(), newContact.getContactRole().getRoleDescription());
                break;
            }
        }
        
        return valid;
    }
}

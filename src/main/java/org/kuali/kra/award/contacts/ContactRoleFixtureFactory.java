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

import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.award.home.ContactType;
import org.kuali.kra.bo.UnitAdministratorType;
import org.kuali.kra.bo.UnitContactType;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonRole;

/**
 * This class defines some mock AwardContactRoles
 */
public final class ContactRoleFixtureFactory {
    static final ContactRole MOCK_PI = getProposalPersonRole(ContactRole.PI_CODE, "Principal Investigator");
    static final ContactRole MOCK_COI = getProposalPersonRole(ContactRole.COI_CODE, "Co-Investigator");
    static final ContactRole MOCK_KEY_PERSON = getProposalPersonRole(ContactRole.KEY_PERSON_CODE, "Key Person");
    
    static final ContactRole MOCK_ACCOUNTANT = getUnitAdministratorType("11", "Accountant", UnitContactType.CONTACT);
    static final ContactRole MOCK_ADMIN_ASSISTANT = getUnitAdministratorType("12", "Admin Assistant", UnitContactType.CONTACT); 
    static final ContactRole MOCK_BUSINESS_MANAGER = getUnitAdministratorType("13", "Business Manager", UnitContactType.CONTACT);
    
    static final ContactRole MOCK_SENIOR_ACCOUNTANT = getContactType("16", "Senior Accountant");
    static final ContactRole MOCK_ADMINISTRATIVE_CONTACT1 = getContactType("17", "Administrative Contact - 1");
    static final ContactRole MOCK_ADMINISTRATIVE_CONTACT2 = getContactType("18", "Administrative Contact - 2");    
    static final ContactRole MOCK_AWARD_OFFICE_CONTACT1 = getContactType("19", "Award Office Contact - 1");
    static final ContactRole MOCK_AWARD_OFFICE_CONTACT2 = getContactType("20", "Award Office Contact - 2");
    static final ContactRole MOCK_CLOSEOUT_CONTACT = getContactType("21", "Closeout Contact");
    
    static final ContactRole MOCK_AUDITOR = getUnitAdministratorType("14", "Auditor", UnitContactType.ADMINISTRATOR);
    static final ContactRole MOCK_FUND_ACCOUNTANT = getUnitAdministratorType("15", "Fund Accountant", UnitContactType.ADMINISTRATOR);
    
    private ContactRoleFixtureFactory() {
        
    }
    
    private static ContactRole getProposalPersonRole(String code, String description) {
        ProposalPersonRole role = new ProposalPersonRole();
        role.setProposalPersonRoleId(code);
        role.setDescription(description);
        return role;
    }
    
    private static ContactRole getUnitAdministratorType(String code, String description, UnitContactType unitContactType) {
        UnitAdministratorType type = new UnitAdministratorType();
        type.setUnitAdministratorTypeCode(code);
        type.setDescription(description);
        type.setUnitContactType(unitContactType);
        return type;
    }
    
    private static ContactRole getContactType(String code, String description) {
        ContactType role = new ContactType();
        role.setContactTypeCode(code);
        role.setDescription(description);
        return role;
    }
}

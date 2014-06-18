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

import org.kuali.coeus.common.framework.person.PropAwardPersonRole;
import org.kuali.coeus.common.framework.unit.UnitContactType;
import org.kuali.coeus.common.framework.unit.admin.UnitAdministratorType;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.award.home.ContactType;

/**
 * This class defines some mock AwardContactRoles
 */
public final class ContactRoleFixtureFactory {
    public static final ContactRole MOCK_PI = getProposalPersonRole(ContactRole.PI_CODE, "Principal Investigator");
    public static final ContactRole MOCK_COI = getProposalPersonRole(ContactRole.COI_CODE, "Co-Investigator");
    public static final ContactRole MOCK_KEY_PERSON = getProposalPersonRole(ContactRole.KEY_PERSON_CODE, "Key Person");

    private ContactRoleFixtureFactory() {
        
    }
    
    private static ContactRole getProposalPersonRole(String code, String description) {
        PropAwardPersonRole role = new PropAwardPersonRole();
        role.setCode(code);
        role.setDescription(description);
        return role;
    }
    
    private static ContactRole getUnitAdministratorType(String code, String description, UnitContactType unitContactType) {
        UnitAdministratorType type = new UnitAdministratorType();
        type.setCode(code);
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

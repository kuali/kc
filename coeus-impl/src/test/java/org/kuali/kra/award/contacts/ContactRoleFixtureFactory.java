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

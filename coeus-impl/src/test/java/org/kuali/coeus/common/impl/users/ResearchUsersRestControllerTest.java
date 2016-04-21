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
package org.kuali.coeus.common.impl.users;

import static org.junit.Assert.*;

import org.junit.Test;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.unit.Unit;

public class ResearchUsersRestControllerTest {

    public static final String UNIT_NUMBER = "1234";
    public static final String PERSON_ID = "5678";
    public static final String TESTER = "TESTER";

    private class MockKcPerson extends KcPerson {
        @Override
        public Unit getUnit() {
            Unit unit = new Unit();
            unit.setUnitNumber(UNIT_NUMBER);
            return unit;
        }

        @Override
        public String getUserName() {
            return TESTER;
        }

        @Override
        public String getPersonId(){
            return PERSON_ID;
        }
    }

    @Test
    public void testConvertToUserDTO() {
        ResearchUsersRestController researchUsersRestController = new ResearchUsersRestController();

        MockKcPerson kcPerson = new MockKcPerson();
        UserDTO user = researchUsersRestController.convertToUserDTO(kcPerson);
        assertEquals(UNIT_NUMBER,user.getPrimaryDepartmentCode());
        assertEquals(PERSON_ID, user.getPersonId());
        assertEquals(TESTER, user.getUserName());
    }
}
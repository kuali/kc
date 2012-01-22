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
package org.kuali.kra.award.awardhierarchy.sync.helpers;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncChange;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncType;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncXmlExport;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.contacts.AwardPersonUnit;
import org.kuali.rice.core.api.util.type.KualiDecimal;

public class AwardSyncPersonHelperTest extends AwardSyncHelperTestBase {
    
    protected AwardPerson person;
    protected static final String unitNumber = "000001";
    
    public AwardSyncPersonHelperTest() {
        super("AwardPerson");
    }
    protected AwardSyncPersonHelperTest(String className) {
        super(className);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        person = new AwardPerson();
        person.setAcademicYearEffort(new KualiDecimal(100.00));
        person.setCalendarYearEffort(new KualiDecimal(100.00));
        person.setRoleCode("PI");
        person.setFaculty(false);
        person.setPersonId("10000000001");
        
        AwardPersonUnit unit = new AwardPersonUnit();
        unit.setAwardPerson(person);
        unit.setLeadUnit(true);
        unit.setUnitNumber(unitNumber);
        person.add(unit);
        unit.setAwardPerson(person);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testBuildXmlExport() throws Exception {
        AwardSyncXmlExport xmlExport = awardSyncHelper.buildXmlExport(person, null);
        assertNotNull(xmlExport);
        assertFalse(xmlExport.getKeys().isEmpty());
        assertFalse(xmlExport.getValues().isEmpty());
        assertEquals(person.getFullName(), xmlExport.getValues().get("fullName")); 
        assertEquals(person.getRoleCode(), xmlExport.getKeys().get("roleCode"));
        assertEquals(person.getCalendarYearEffort(), xmlExport.getValues().get("calendarYearEffort"));
    }
    
    @Test
    public void testCreateAwardSyncChange() throws Exception {
        AwardSyncChange change = 
            awardSyncHelper.createAwardSyncChange(AwardSyncType.ADD_SYNC, person, "projectPersons", null);
        assertNotNull(change);
        assertNotNull(change.getXmlExport());
    }
    
    @Test
    public void testApplySyncChange() throws Exception {
        AwardSyncChange change = 
            awardSyncHelper.createAwardSyncChange(AwardSyncType.ADD_SYNC, person, "projectPersons", null);
        awardSyncHelper.applySyncChange(award, change);
        assertFalse(award.getProjectPersons().isEmpty());
        assertEquals(award.getProjectPerson(0).getFullName(), person.getFullName());
        assertFalse(award.getProjectPerson(0).getUnits().isEmpty());
        
        change = awardSyncHelper.createAwardSyncChange(AwardSyncType.DELETE_SYNC, person, "projectPersons", null);
        awardSyncHelper.applySyncChange(award, change);
        assertTrue(award.getProjectPersons().isEmpty());
    }
}

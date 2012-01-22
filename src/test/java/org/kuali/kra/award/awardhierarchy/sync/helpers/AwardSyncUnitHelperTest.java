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
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncException;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncType;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncXmlExport;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.contacts.AwardPersonUnit;
import org.kuali.kra.bo.Unit;
import org.kuali.rice.krad.util.ObjectUtils;

public class AwardSyncUnitHelperTest extends AwardSyncPersonHelperTest {

    protected AwardPersonUnit unit;
    protected Unit leadUnit;
    
    public AwardSyncUnitHelperTest() { 
        super("AwardPersonUnit");
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        unit = person.getUnit(0);
        leadUnit = new Unit();
        leadUnit.setUnitNumber(unitNumber);
        unit.setUnit(leadUnit);
        award.setLeadUnit(leadUnit);
    }

    @After
    public void tearDown() throws Exception {
        super.setUp();
    }
    
    @Test
    public void testBuildXmlExport() throws Exception {
        AwardSyncXmlExport xmlExport = awardSyncHelper.buildXmlExport(unit, null);
        AwardSyncXmlExport unitExport =  (AwardSyncXmlExport) xmlExport.getValues().get("units");
        assertNotNull(unitExport);
        assertFalse(unitExport.getKeys().isEmpty());
        assertFalse(unitExport.getValues().isEmpty());
        assertEquals(unit.getUnitNumber(), unitExport.getKeys().get("unitNumber"));
        assertEquals(unit.isLeadUnit(), unitExport.getValues().get("leadUnit"));
    }
    
    @Test
    public void testCreateAwardSyncChange() throws Exception {
        AwardSyncChange change = 
            awardSyncHelper.createAwardSyncChange(AwardSyncType.ADD_SYNC, unit, "projectPersons", null);
        assertNotNull(change);
        assertNotNull(change.getXmlExport());
    }
    
    @Test
    public void testApplySyncChange() throws Exception {
        AwardSyncChange change = 
            awardSyncHelper.createAwardSyncChange(AwardSyncType.ADD_SYNC, unit, "projectPersons", null);
        AwardPerson newPerson = (AwardPerson) ObjectUtils.deepCopy(person);
        newPerson.getUnits().clear();
        award.add(newPerson);
        awardSyncHelper.applySyncChange(award, change);
        assertFalse(award.getProjectPerson(0).getUnits().isEmpty());
        assertTrue(award.getProjectPerson(0).getUnit(0).isLeadUnit());
        assertEquals("000001", award.getProjectPerson(0).getUnit(0).getUnitNumber());
        
        change = awardSyncHelper.createAwardSyncChange(AwardSyncType.DELETE_SYNC, unit, "projectPersons", null);
        awardSyncHelper.applySyncChange(award, change);
        assertTrue(award.getProjectPerson(0).getUnits().isEmpty());
    }
    
    @Test(expected=AwardSyncException.class)
    public void testApplySyncChangeWithException() throws Exception {
        AwardSyncChange change = 
            awardSyncHelper.createAwardSyncChange(AwardSyncType.ADD_SYNC, unit, "projectPersons", null);
        awardSyncHelper.applySyncChange(award, change);
    }

}

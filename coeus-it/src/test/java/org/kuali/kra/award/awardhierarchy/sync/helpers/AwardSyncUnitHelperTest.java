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
package org.kuali.kra.award.awardhierarchy.sync.helpers;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncChange;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncException;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncType;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncXmlExport;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.contacts.AwardPersonUnit;
import org.kuali.rice.krad.util.ObjectUtils;
import static org.junit.Assert.*;
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

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
package org.kuali.coeus.common.impl.unit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.common.framework.unit.admin.UnitAdministrator;
import org.kuali.coeus.common.impl.unit.UnitServiceImpl;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;
public class UnitServiceImplTest extends KcIntegrationTestBase {
    
    private UnitService unitService;
    private UnitServiceImpl unitServiceImpl;
    
    private static final String UNIVERSITY_UNIT_NUMBER = "000001";
    private static final String CARDIOLOGY_UNIT_NUMBER = "IN-CARD";
    private static final String BLOOMINGTON_UNIT_NUMBER_PROPER_CASE = "BL-BL";
    private static final String BLOOMINGTON_UNIT_NUMBER_CASE_CHANGED = "bL-Bl";
    

    @Before
    public void setUp() throws Exception {
        unitService = KcServiceLocator.getService(UnitService.class);
        unitServiceImpl = (UnitServiceImpl) KcServiceLocator.getService(UnitService.class);
        
    }

    @After
    public void tearDown() throws Exception {
        unitService = null;
        unitServiceImpl = null;
    }

    @Test
    public void testGetUnitName() {
        String retrievedName = unitService.getUnitName(UNIVERSITY_UNIT_NUMBER);
        assertEquals("University", retrievedName);
    }

    @Test
    public void testGetUnits() {
        Collection<Unit> units = unitService.getUnits();
        assertEquals(13, units.size());
    }
    
    @Test
    public void testGetUnitCaseInsensitive() {
        Unit unit1 = unitService.getUnitCaseInsensitive(BLOOMINGTON_UNIT_NUMBER_CASE_CHANGED);
        Unit unit2 = unitService.getUnitCaseInsensitive(BLOOMINGTON_UNIT_NUMBER_PROPER_CASE);
        assertNotNull(unit1);
        assertNotNull(unit2);
        assertEquals(unit1, unit2);
    }

    @Test
    public void testGetUnit() {
        Unit unit = unitService.getUnit(CARDIOLOGY_UNIT_NUMBER);
        assertEquals("CARDIOLOGY", unit.getUnitName());
    }

    @Test
    public void testGetAllSubUnitsCardiology() {
        List<Unit> units = unitService.getAllSubUnits(CARDIOLOGY_UNIT_NUMBER);
        assertEquals(1, units.size());
    }

    @Test
    public void testGetAllSubUnitsTop() {
        List<Unit> units = unitService.getAllSubUnits("000001");
        assertEquals(12, units.size());
    }

    @Test
    public void testGetBusinessObjectService() {
        assertNotNull(unitServiceImpl.getBusinessObjectService());
    }

    @Test
    public void testGetTopUnit() {
        Unit unit = unitServiceImpl.getTopUnit();
        assertEquals(UNIVERSITY_UNIT_NUMBER, unit.getUnitNumber());
    }

    @Test
    public void testGetInitialUnitsForUnitHierarchy() {
        String tree = unitService.getInitialUnitsForUnitHierarchy();
        assertTrue(tree.contains(UNIVERSITY_UNIT_NUMBER));
    }

    @Test
    public void testGetInitialUnitsForUnitHierarchyInt() {
        String tree = unitService.getInitialUnitsForUnitHierarchy(3);
        assertTrue(tree.contains(UNIVERSITY_UNIT_NUMBER));
    }

    @Test
    public void testRetrieveUnitAdministratorsByUnitNumber() {
        List<UnitAdministrator> admins = unitService.retrieveUnitAdministratorsByUnitNumber(UNIVERSITY_UNIT_NUMBER);
        for (UnitAdministrator admin : admins) {
            if ("10000000001".equals(admin.getPersonId())) {
                assertTrue(true);
                return;
            }
        }
        assertTrue(false);
    }

    @Test
    public void testGetMaxUnitTreeDepth() {
        int retVal = unitService.getMaxUnitTreeDepth();
        assertTrue(retVal>1);
    }
    
    @Test
    public void testGetUnitHierarchyForUnit() {
        List<Unit> units = unitService.getUnitHierarchyForUnit("IN-PERS");
        assertEquals(6, units.size());
    }
    
    @Test
    public void testGetUnitHierarchyForUnit2() {
        List<Unit> units = unitService.getUnitHierarchyForUnit("000001");
        assertEquals(1, units.size());
    }
    
    @Test
    public void testGetUnitHierarchyForUnit3() {
        List<Unit> units = unitService.getUnitHierarchyForUnit("xyz");
        assertEquals(0, units.size());
    }

}

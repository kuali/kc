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
package org.kuali.kra.service.impl;

import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.bo.UnitAdministrator;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.UnitService;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;

public class UnitServiceImplTest extends KcUnitTestBase {
    
    private UnitService unitService;
    private UnitServiceImpl unitServiceImpl;
    
    private static final String UNIVERSITY_UNIT_NUMBER = "000001";
    private static final String CARDIOLOGY_UNIT_NUMBER = "IN-CARD";
    private static final String BLOOMINGTON_UNIT_NUMBER_PROPER_CASE = "BL-BL";
    private static final String BLOOMINGTON_UNIT_NUMBER_CASE_CHANGED = "bL-Bl";
    

    @Before
    public void setUp() throws Exception {
        unitService = KraServiceLocator.getService(UnitService.class);
        unitServiceImpl = (UnitServiceImpl) KraServiceLocator.getService(UnitService.class);
        
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
    public void testGetAllSubUnits() {
        List<Unit> units = unitService.getAllSubUnits(CARDIOLOGY_UNIT_NUMBER);
        assertEquals(1, units.size());
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

}

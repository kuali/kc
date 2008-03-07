/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.kim.mocks.MockKimDatabase;
import org.kuali.kra.kim.mocks.MockKimPersonService;
import org.kuali.kra.kim.mocks.MockKimRoleService;
import org.kuali.kra.kim.service.RoleService;
import org.kuali.kra.service.impl.mocks.MockSystemAuthorizationService;
import org.kuali.kra.service.impl.mocks.MockUnitService;

/**
 * Test the Unit Authorization Service Implementation.  For all of
 * the test, we use mock services.  The unit hierarchy (letters are
 * used for the unit numbers) is:
 * 
 *              A
 *      +-------+-------+
 *      B               C
 *  +---+----+      +---+---+
 *  D        E      F       G
 *    
 * There is only one defined role (Aggregator) with a set of permissions.
 * The qualified roles, using Aggregator, are:
 *     1) don is qualified to (unit: A, subunits: Yes)
 *     2) molly is qualified to (unit: C, subunits: No)
 *     3) nancy is qualified to (unit: C, subunits: No)
 *     4) nancy is qualified to (unit: B, subunits: Yes)
 */
public class UnitAuthorizationServiceImplTest extends KraTestBase {
    
    private MockKimDatabase database;
    private MockUnitService unitService;
    private MockKimPersonService personService;
    private RoleService roleService;
    private MockSystemAuthorizationService systemAuthorizationService;
    private UnitAuthorizationServiceImpl unitAuthService;
    
    /**
     * Create the mock services and insert them into the unit auth service.
     * @see org.kuali.kra.KraTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        database = buildDatabase();
        unitService = buildUnitService();
        personService = buildPersonService();
        roleService = buildRoleService();
        systemAuthorizationService = new MockSystemAuthorizationService();
        
        unitAuthService = new UnitAuthorizationServiceImpl();
        unitAuthService.setUnitService(unitService);
        unitAuthService.setKimPersonService(personService);
        unitAuthService.setKimRoleService(roleService);
        unitAuthService.setSystemAuthorizationService(systemAuthorizationService);
    }
    
    /**
     * Check for permissions when the system authorization service returns
     * a value of true for permissions.  In this case, the unit auth service
     * must return true.
     */
    @Test
    public void testHasSystemPermission() throws Exception {
        systemAuthorizationService.setResult(true);
        assertTrue(unitAuthService.hasPermission("don", "D", "create"));
    }
    
    /**
     * Verify that a user has permission within the unit that they
     * have been explicitly qualified to.
     * @throws Exception
     */
    @Test
    public void testPermissionInUnit() throws Exception {
        systemAuthorizationService.setResult(false);
        assertTrue(unitAuthService.hasPermission("molly", "C", "create"));
    }
    
    /**
     * Start at a leaf in the unit hierarchy and traverse upwards
     * but don't find any permission.
     * @throws Exception
     */
    @Test
    public void testTraverseNoPermission() throws Exception {
        systemAuthorizationService.setResult(false);
        assertFalse(unitAuthService.hasPermission("molly", "D", "create"));
    }
    
    /**
     * Start at a leaf in the unit hierarchy and traverse upwards
     * but find a qualified role that does not descend to subunits.
     * @throws Exception
     */
    @Test
    public void testTraverseNoDescend() throws Exception {
        systemAuthorizationService.setResult(false);
        assertFalse(unitAuthService.hasPermission("molly", "F", "create"));
    }
    
    /**
     * Start at a leaf in the unit hierarchy and traverse upwards
     * until we find a qualified role that has the permission and
     * does descend into subunits.
     * @throws Exception
     */
    @Test
    public void testTraverseDescend() throws Exception {
        systemAuthorizationService.setResult(false);
        assertTrue(unitAuthService.hasPermission("don", "F", "create"));
    }
    
    /**
     * Test to see if a user has a permission in any unit.  The
     * result should be true.
     * @throws Exception
     */
    @Test
    public void testAnyUnitTrue() throws Exception {
        systemAuthorizationService.setResult(false);
        assertTrue(unitAuthService.hasPermission("molly", "create"));
    }
    
    /**
     * Test to see if a user has a permission in any unit.  As
     * opposed to the above test, this one is false.
     * @throws Exception
     */
    @Test
    public void testAnyUnitFalse() throws Exception {
        systemAuthorizationService.setResult(false);
        assertFalse(unitAuthService.hasPermission("molly", "foo"));
    }
    
    /**
     * Test to see if we get all of the units if the system auth service
     * return true.
     * @throws Exception
     */
    @Test
    public void testGetUnitsSystem() throws Exception {
        String[] unitNumbers = { "A", "B", "C", "D", "E", "F", "G" };
        
        systemAuthorizationService.setResult(true);
        List<Unit> units = unitAuthService.getUnits("don", "create");
        assertTrue(units.size() == 7);
        for (String unitNumber : unitNumbers) {
            assertTrue(hasUnit(units, unitNumber));
        }
    }
    
    /**
     * Test that no units are returned if the user doesn't have
     * the given permission in any unit.
     * @throws Exception
     */
    @Test
    public void testGetUnitsNone() throws Exception {
        systemAuthorizationService.setResult(false);
        List<Unit> units = unitAuthService.getUnits("don", "foo");
        assertTrue(units.size() == 0);
    }
    
    /**
     * Test the case where a user has the permission in two units.
     * One descends to subunits and the other doesn't.
     * @throws Exception
     */
    @Test
    public void testGetUnits() throws Exception {
        String[] unitNumbers = { "B", "C", "D", "E" };
        systemAuthorizationService.setResult(false);
        List<Unit> units = unitAuthService.getUnits("nancy", "create");
        assertTrue(units.size() == 4);
        for (String unitNumber : unitNumbers) {
            assertTrue(hasUnit(units, unitNumber));
        }
    }
    
    /**
     * Does the list of units contain a unit with the given unit number?
     * @param units
     * @param unitNumber
     * @return
     */
    private boolean hasUnit(List<Unit> units, String unitNumber) {
        for (Unit unit : units) {
            if (StringUtils.equals(unitNumber, unit.getUnitNumber())) {
                return true;
            }
        }
        return false;
    }
    
    public MockKimDatabase buildDatabase() {
        MockKimDatabase database = new MockKimDatabase();
        
        List<String> permissions = new ArrayList<String>();
        permissions.add("read");
        permissions.add("write");
        permissions.add("create");
        permissions.add("delete");
        database.addRole("Aggregator", permissions);
        
        permissions = new ArrayList<String>();
        permissions.add("read");
        database.addRole("Viewer", permissions);
        
        Map<String, String> qualifiedAttributes = new HashMap<String, String>();
        qualifiedAttributes.put("kra.unitNumber", "A");
        qualifiedAttributes.put("kra.subunits", "Y");
        database.addQualifiedRole("don", "Aggregator", qualifiedAttributes);
        
        qualifiedAttributes = new HashMap<String, String>();
        qualifiedAttributes.put("kra.unitNumber", "C");
        qualifiedAttributes.put("kra.subunits", "N");
        database.addQualifiedRole("molly", "Aggregator", qualifiedAttributes);
        
        qualifiedAttributes = new HashMap<String, String>();
        qualifiedAttributes.put("kra.unitNumber", "B");
        qualifiedAttributes.put("kra.subunits", "Y");
        database.addQualifiedRole("nancy", "Aggregator", qualifiedAttributes);
        
        qualifiedAttributes = new HashMap<String, String>();
        qualifiedAttributes.put("kra.unitNumber", "C");
        qualifiedAttributes.put("kra.subunits", "N");
        database.addQualifiedRole("nancy", "Aggregator", qualifiedAttributes);
        
        return database;
    }
    
    /**
     * Create a Mock Unit Service with a mock unit hierarchy.
     * @return
     */
    private MockUnitService buildUnitService() {
        MockUnitService service = new MockUnitService();
        service.addUnit("A", null);
        service.addUnit("B", "A");
        service.addUnit("C", "A");
        service.addUnit("D", "B");
        service.addUnit("E", "B");
        service.addUnit("F", "C");
        service.addUnit("G", "C");
        return service;
    }
    
    /**
     * Create a Mock KIM Person Service with permissions, roles, and
     * users assigned to qualified roles.
     * @return
     */
    private MockKimPersonService buildPersonService() {
        MockKimPersonService service = new MockKimPersonService();
        service.setDatabase(database);
        return service;
    }
    
    /**
     * Create a Mock KIM Role Service.
     * @return
     */
    private MockKimRoleService buildRoleService() {
        MockKimRoleService service = new MockKimRoleService();
        service.setDatabase(database);
        return service;
    }
}

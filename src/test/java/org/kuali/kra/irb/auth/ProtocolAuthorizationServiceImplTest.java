/*
 * Copyright 2005-2013 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.irb.auth;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.service.impl.KraAuthorizationServiceImpl;
import org.kuali.kra.service.impl.mocks.MockKcPersonService;
import org.kuali.kra.service.impl.mocks.MockUnitAuthorizationService;

/**
 * Test the Protocol Authorization Service Impl. Mocks are used to isolate the service from KIM. Well-defined data is placed into
 * Mock KIM services. The Protocol Authorization Service methods are then invoked and the responses are checked against the expected
 * results.
 */
@Ignore
public class ProtocolAuthorizationServiceImplTest {
    
    //private MockKimDatabase database;
    private MockUnitAuthorizationService unitAuthService;
    private KraAuthorizationServiceImpl kraAuthService;
    private MockKcPersonService kraPersonService;

    /**
     * Create the mock services and insert them into the protocol auth service.
     * 
     * @see org.kuali.kra.KraTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        //database = buildDatabase();
        kraPersonService = buildKraPersonService();

        unitAuthService = new MockUnitAuthorizationService();

        kraAuthService = new KraAuthorizationServiceImpl();
        kraAuthService.setUnitAuthorizationService(unitAuthService);
        kraAuthService.setKcPersonService(kraPersonService);
    }

    /**
     * Test the getUsernames() service method.
     */
    @Test
    public void testGetUsernames() {
//        Protocol protocol = createProtocol("1");
//        List<String> usernames = kraAuthService.getUserNames(protocol, RoleConstants.PROTOCOL_AGGREGATOR);
//        assertTrue(usernames.size() == 2);
//        assertTrue(usernames.contains("don"));
//        assertTrue(usernames.contains("gary"));
//
//        protocol = createProtocol("101");
//        usernames = kraAuthService.getUserNames(protocol, RoleConstants.PROTOCOL_AGGREGATOR);
//        assertTrue(usernames.size() == 0);
    }

    /**
     * Test the addRole() service method.
     */
    @Test
    @Ignore
    public void testAddRole() {
//        Protocol protocol = createProtocol("99");
//        protocolAuthService.addRole("jordan", RoleConstants.PROTOCOL_AGGREGATOR, protocol);
//        List<String> usernames = kraAuthService.getUserNames(protocol, RoleConstants.PROTOCOL_AGGREGATOR);
//        assertTrue(usernames.size() == 1);
//        assertTrue(usernames.contains("jordan"));
    }

    /**
     * Test the removeRole() service method.
     */
    @Test
    public void testRemoveRole() {
//        testAddRole();
//
//        Protocol protocol = createProtocol("99");
//        kraAuthService.removeRole("barre", RoleConstants.PROTOCOL_AGGREGATOR, protocol);
//        List<String> usernames = kraAuthService.getUserNames(protocol, RoleConstants.PROTOCOL_AGGREGATOR);
//        assertTrue(usernames.size() == 1);
//
//        kraAuthService.removeRole("jordan", RoleConstants.PROTOCOL_AGGREGATOR, protocol);
//        usernames = kraAuthService.getUserNames(protocol, RoleConstants.PROTOCOL_AGGREGATOR);
//        assertTrue(usernames.size() == 0);
    }

    /**
     * Test the hasPermission() service method.
     */
    @Test
    public void testHasPermission() {
//        Protocol protocol = createProtocol("1");
//        assertTrue(protocolAuthService.hasPermission("don", protocol, "create"));
//        assertFalse(protocolAuthService.hasPermission("molly", protocol, "create"));
    }

    /**
     * Test the hasRole() service method.
     */
    @Test
    public void testHasRole() {
//        Protocol protocol = createProtocol("1");
//        assertTrue(protocolAuthService.hasRole("don", protocol, RoleConstants.PROTOCOL_AGGREGATOR));
//        assertTrue(protocolAuthService.hasRole("molly", protocol, RoleConstants.PROTOCOL_VIEWER));
//        assertFalse(protocolAuthService.hasRole("don", protocol, RoleConstants.PROTOCOL_VIEWER));
    }

    /**
     * Test the getRoles() service method.
     */
    @Test
    @Ignore
    public void testGetRoles() {
//        Protocol protocol = createProtocol("3");
//        List<String> roles = kraAuthService.getRoles("vicki", protocol);
//        assertTrue(roles.size() == 2);
//        assertTrue(roles.contains(RoleConstants.PROTOCOL_AGGREGATOR));
//        assertTrue(roles.contains(RoleConstants.PROTOCOL_VIEWER));
    }

    /**
     * Test the getPersonsInRole() service method.
     */
    @Test
    public void testGetPersonsInRole() {
//        Protocol protocol = createProtocol("1");
//        List<KcPerson> persons = kraAuthService.getPersonsInRole(protocol, RoleConstants.PROTOCOL_AGGREGATOR);
//        assertEquals(2, persons.size());
//        assertTrue(contains(persons, "don"));
//        assertTrue(contains(persons, "gary"));
    }

    /**
     * Test the getAllRolePersons() service method.
     */
    @Test
    public void testGetAllRolePersons() {
//        Protocol protocol = createProtocol("1");
//
//        List<RolePersons> rolePersonsList = kraAuthService.getAllRolePersons(protocol);
//        assertEquals(2, rolePersonsList.size());
//        for (RolePersons rolePersons : rolePersonsList) {
//            if (rolePersons.getAggregator() != null) {
//                List<String> aggregators = rolePersons.getAggregator();
//                assertEquals(2, aggregators.size());
//                assertTrue(aggregators.contains("don"));
//                assertTrue(aggregators.contains("gary"));
//            }
//            else if (rolePersons.getViewer() != null) {
//                List<String> viewer = rolePersons.getViewer();
//                assertEquals(1, viewer.size());
//                assertTrue(viewer.contains("molly"));
//            }
//        }
    }

    /**
     * Create a protocol. For testing purposes, we only need its protocol number to be set.
     * 
     * @param nbr
     * @return
     */
    private Protocol createProtocol(String nbr) {
        Protocol protocol = new Protocol(){
            @Override
            public void refreshReferenceObject(String referenceObjectName) {}
            
        };
        protocol.setProtocolNumber(nbr);
        return protocol;
    }

//    /**
//     * Build a mock database to be used by the mock KIM service.
//     * 
//     * @return
//     */
//    private MockKimDatabase buildDatabase() {
//        MockKimDatabase database = new MockKimDatabase();
//        addRoles(database);
//        addQualifiedRoles(database);
//        return database;
//    }
//
//    /**
//     * Build a Mock KIM Qualified Role service.
//     * 
//     * @return
//     */
//    private MockKimQualifiedRoleService buildQualifiedRoleService() {
//        MockKimQualifiedRoleService service = new MockKimQualifiedRoleService();
//        service.setDatabase(database);
//        return service;
//    }
//
//    /**
//     * Build a Mock KIM Person Service.
//     * 
//     * @return
//     */
//    private MockKimPersonService buildPersonService() {
//        MockKimPersonService service = new MockKimPersonService();
//        service.setDatabase(database);
//        return service;
//    }

    /**
     * Can we find the username in this list of persons?
     * 
     * @param persons
     * @param username
     * @return
     */
    private boolean contains(List<KcPerson> persons, String username) {
        for (KcPerson person : persons) {
            if (StringUtils.equals(person.getUserName(), username)) {
                return true;
            }
        }
        return false;
    }

//    /**
//     * Add a set of roles/permissions to the mock database.
//     * 
//     * @param service
//     */
//    private void addRoles(MockKimDatabase service) {
//        List<String> permissions = new ArrayList<String>();
//        permissions.add("read");
//        permissions.add("write");
//        permissions.add("create");
//        permissions.add("delete");
//        service.addRole(RoleConstants.PROTOCOL_AGGREGATOR, permissions);
//
//        permissions = new ArrayList<String>();
//        permissions.add("read");
//        service.addRole(RoleConstants.PROTOCOL_VIEWER, permissions);
//    }
//
//    /**
//     * Add a set of qualified roles to the mock database.
//     * 
//     * @param service
//     */
//    private void addQualifiedRoles(MockKimDatabase service) {
//        Map<String, String> qualifiedAttributes = new HashMap<String, String>();
//        qualifiedAttributes.put("protocol", "1");
//        service.addQualifiedRole("don", RoleConstants.PROTOCOL_AGGREGATOR, qualifiedAttributes);
//
//        qualifiedAttributes = new HashMap<String, String>();
//        qualifiedAttributes.put("protocol", "1");
//        service.addQualifiedRole("molly", RoleConstants.PROTOCOL_VIEWER, qualifiedAttributes);
//
//        qualifiedAttributes = new HashMap<String, String>();
//        qualifiedAttributes.put("protocol", "1");
//        service.addQualifiedRole("gary", RoleConstants.PROTOCOL_AGGREGATOR, qualifiedAttributes);
//
//        qualifiedAttributes = new HashMap<String, String>();
//        qualifiedAttributes.put("protocol", "1");
//        service.addQualifiedRole("leo", RoleConstants.UNASSIGNED, qualifiedAttributes);
//
//        qualifiedAttributes = new HashMap<String, String>();
//        qualifiedAttributes.put("protocol", "2");
//        service.addQualifiedRole("nancy", RoleConstants.PROTOCOL_AGGREGATOR, qualifiedAttributes);
//
//        qualifiedAttributes = new HashMap<String, String>();
//        qualifiedAttributes.put("protocol", "3");
//        service.addQualifiedRole("vicki", RoleConstants.PROTOCOL_VIEWER, qualifiedAttributes);
//
//        qualifiedAttributes = new HashMap<String, String>();
//        qualifiedAttributes.put("protocol", "3");
//        service.addQualifiedRole("vicki", RoleConstants.PROTOCOL_AGGREGATOR, qualifiedAttributes);
//
//        qualifiedAttributes = new HashMap<String, String>();
//        qualifiedAttributes.put("bogus", "3");
//        service.addQualifiedRole("vicki", RoleConstants.PROTOCOL_AGGREGATOR, qualifiedAttributes);
//
//        qualifiedAttributes = new HashMap<String, String>();
//        qualifiedAttributes.put("protocol", "3");
//        service.addQualifiedRole("mickey", RoleConstants.PROTOCOL_VIEWER, qualifiedAttributes);
//
//        qualifiedAttributes = new HashMap<String, String>();
//        qualifiedAttributes.put("protocol", "3");
//        service.addQualifiedRole("mickey", RoleConstants.PROTOCOL_AGGREGATOR, qualifiedAttributes);
//    }

    /**
     * Build a Mock KRA Person service with a set of persons.
     * 
     * @return
     */
    private MockKcPersonService buildKraPersonService() {
        MockKcPersonService service = new MockKcPersonService();
        service.addPerson("don");
        service.addPerson("gary");
        service.addPerson("nancy");
        service.addPerson("molly");
        service.addPerson("vicki");
        service.addPerson("mickey");
        service.addPerson("jordan");
        service.addPerson("leo");
        return service;
    }
}

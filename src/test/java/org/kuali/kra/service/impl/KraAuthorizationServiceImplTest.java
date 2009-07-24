/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.RolePersons;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.kim.mocks.MockKimDatabase;
import org.kuali.kra.kim.mocks.MockKimPersonService;
import org.kuali.kra.kim.mocks.MockKimQualifiedRoleService;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.service.impl.mocks.MockPersonService;
import org.kuali.kra.service.impl.mocks.MockUnitAuthorizationService;

/**
 * Test the Kra Authorization Service Impl.  Mocks are used
 * to isolate the service from KIM.  Well-defined data  is placed 
 * into Mock KIM services.  The Kra Authorization Service methods
 * are then invoked and the responses are checked against the expected
 * results.
 */
public class KraAuthorizationServiceImplTest extends KraTestBase {

    private MockKimDatabase database;
    private MockKimPersonService personService;
    private MockKimQualifiedRoleService qualifiedRoleService;
    private MockUnitAuthorizationService unitAuthService;
    private KraAuthorizationServiceImpl kraAuthService;
    private MockPersonService kraPersonService;
    
    /**
     * Create the mock services and insert them into the kra auth service.
     * @see org.kuali.kra.KraTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        database = buildDatabase();
        personService = buildPersonService();
        qualifiedRoleService = buildQualifiedRoleService();
        kraPersonService = buildKraPersonService();
        
        unitAuthService = new MockUnitAuthorizationService();
        
        kraAuthService = new KraAuthorizationServiceImpl();
        kraAuthService.setKimQualifiedRoleService(qualifiedRoleService);
        kraAuthService.setKimPersonService(personService);
        kraAuthService.setUnitAuthorizationService(unitAuthService);
        kraAuthService.setPersonService(kraPersonService);
    }
    
    /**
     * Test the getUsernames() service method.
     */
    @Test
    public void testGetUsernames() {
        ProposalDevelopmentDocument doc = createProposal("1");
        List<String> usernames = kraAuthService.getUserNames(doc, RoleConstants.AGGREGATOR);
        assertTrue(usernames.size() == 2);
        assertTrue(usernames.contains("don"));
        assertTrue(usernames.contains("gary"));
        
        doc = createProposal("101");
        usernames = kraAuthService.getUserNames(doc, RoleConstants.AGGREGATOR);
        assertTrue(usernames.size() == 0);
    }
    
    /**
     * Test the addRole() service method.
     */
    @Test
    public void testAddRole() {
        ProposalDevelopmentDocument doc = createProposal("99");
        kraAuthService.addRole("jordan", RoleConstants.AGGREGATOR, doc);
        List<String> usernames = kraAuthService.getUserNames(doc, RoleConstants.AGGREGATOR);
        assertTrue(usernames.size() == 1);
        assertTrue(usernames.contains("jordan"));
    }
    
    /**
     * Test the removeRole() service method.
     */
    @Test
    public void testRemoveRole() {
        testAddRole();
        
        ProposalDevelopmentDocument doc = createProposal("99");
        kraAuthService.removeRole("barre", RoleConstants.AGGREGATOR, doc);
        List<String> usernames = kraAuthService.getUserNames(doc, RoleConstants.AGGREGATOR);
        assertTrue(usernames.size() == 1);
        
        kraAuthService.removeRole("jordan", RoleConstants.AGGREGATOR, doc);
        usernames = kraAuthService.getUserNames(doc, RoleConstants.AGGREGATOR);
        assertTrue(usernames.size() == 0);
    }
    
    /**
     * Test the hasPermission() service method.
     */
    @Test
    public void testHasPermission() {
        ProposalDevelopmentDocument doc = createProposal("1");
        assertTrue(kraAuthService.hasPermission("don", doc, "create"));
        assertFalse(kraAuthService.hasPermission("molly", doc, "create"));
    }
    
    /**
     * Test the hasRole() service method.
     */
    @Test
    public void testHasRole() {
        ProposalDevelopmentDocument doc = createProposal("1");
        assertTrue(kraAuthService.hasRole("don", doc, RoleConstants.AGGREGATOR));
        assertTrue(kraAuthService.hasRole("molly", doc, RoleConstants.VIEWER));
        assertFalse(kraAuthService.hasRole("don", doc, RoleConstants.VIEWER));
    }
    
    /**
     * Test the getRoles() service method.
     */
    @Test
    public void testGetRoles() {
        ProposalDevelopmentDocument doc = createProposal("3");
        List<String> roles = kraAuthService.getRoles("vicki", doc);
        assertTrue(roles.size() == 2);
        assertTrue(roles.contains(RoleConstants.AGGREGATOR));
        assertTrue(roles.contains(RoleConstants.VIEWER));
    }
    
    /**
     * Test the getPersonsInRole() service method.
     */
    @Test
    public void testGetPersonsInRole() {
        ProposalDevelopmentDocument doc = createProposal("1");
        List<Person> persons = kraAuthService.getPersonsInRole(doc, RoleConstants.AGGREGATOR);
        assertEquals(2, persons.size());
        assertTrue(contains(persons, "don"));
        assertTrue(contains(persons, "gary"));
    }
    
    /**
     * Test the getAllRolePersons() service method.
     */
    @Test
    public void testGetAllRolePersons() {
        ProposalDevelopmentDocument doc = createProposal("1");
       
        
       List<RolePersons> rolePersonsList = kraAuthService.getAllRolePersons(doc);
       assertEquals(4, rolePersonsList.size());
        for (RolePersons rolePersons : rolePersonsList){
            if(rolePersons.getAggregator()!= null )
            {
                List<String> aggregators= rolePersons.getAggregator();
                assertEquals(2, aggregators.size());
                assertTrue(aggregators.contains("don"));
                assertTrue(aggregators.contains("gary"));
                
            }else if(rolePersons.getViewer()!= null){
                List<String> viewer=rolePersons.getViewer();
                assertEquals(1, viewer.size());
                assertTrue(viewer.contains("molly"));
                
            }
            
            
        }
        
    }   
                


    /**
     * Create a proposal development document.  For testing
     * purposes, we only need its proposal number to be set.
     * @param nbr
     * @return
     */
    private ProposalDevelopmentDocument createProposal(String nbr) {
        ProposalDevelopmentDocument doc = new ProposalDevelopmentDocument();
        doc.getDevelopmentProposal().setProposalNumber(nbr);
        return doc;
    }
    
    
    /**
     * Build a mock database to be used by the mock KIM service.
     * @return
     */
    private MockKimDatabase buildDatabase() {
        MockKimDatabase database = new MockKimDatabase();
        addRoles(database);
        addQualifiedRoles(database);
        return database;
    }
    
    /**
     * Build a Mock KIM Qualified Role service.
     * @return
     */
    private MockKimQualifiedRoleService buildQualifiedRoleService() {
        MockKimQualifiedRoleService service = new MockKimQualifiedRoleService();
        service.setDatabase(database);
        return service;
    }
    
    /**
     * Build a Mock KIM Person Service.
     * @return
     */
    private MockKimPersonService buildPersonService() {
        MockKimPersonService service = new MockKimPersonService();
        service.setDatabase(database);
        return service;
    }
    
    /**
     * Can we find the username in this list of persons?
     * @param persons
     * @param username
     * @return
     */
    private boolean contains(List<Person> persons, String username) {
        for (Person person : persons) {
            if (StringUtils.equals(person.getUserName(), username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add a set of roles/permissions to the mock database.
     * @param service
     */
    private void addRoles(MockKimDatabase service) {
        List<String> permissions = new ArrayList<String>();
        permissions.add("read");
        permissions.add("write");
        permissions.add("create");
        permissions.add("delete");
        service.addRole(RoleConstants.AGGREGATOR, permissions);
        
        permissions = new ArrayList<String>();
        permissions.add("read");
        service.addRole(RoleConstants.VIEWER, permissions);
    }
    
    /**
     * Add a set of qualified roles to the mock database.
     * @param service
     */
    private void addQualifiedRoles(MockKimDatabase service) {
        Map<String, String> qualifiedAttributes = new HashMap<String, String>();
        qualifiedAttributes.put("kra.proposal", "1");
        service.addQualifiedRole("don", RoleConstants.AGGREGATOR, qualifiedAttributes);
        
        qualifiedAttributes = new HashMap<String, String>();
        qualifiedAttributes.put("kra.proposal", "1");
        service.addQualifiedRole("molly", RoleConstants.VIEWER, qualifiedAttributes);
        
        qualifiedAttributes = new HashMap<String, String>();
        qualifiedAttributes.put("kra.proposal", "1");
        service.addQualifiedRole("gary", RoleConstants.AGGREGATOR, qualifiedAttributes);
        
        qualifiedAttributes = new HashMap<String, String>();
        qualifiedAttributes.put("kra.proposal", "1");
        service.addQualifiedRole("leo", RoleConstants.UNASSIGNED, qualifiedAttributes);

        qualifiedAttributes = new HashMap<String, String>();
        qualifiedAttributes.put("kra.proposal", "2");
        service.addQualifiedRole("nancy", RoleConstants.AGGREGATOR, qualifiedAttributes);
        
        qualifiedAttributes = new HashMap<String, String>();
        qualifiedAttributes.put("kra.proposal", "3");
        service.addQualifiedRole("vicki", RoleConstants.VIEWER, qualifiedAttributes);
        
        qualifiedAttributes = new HashMap<String, String>();
        qualifiedAttributes.put("kra.proposal", "3");
        service.addQualifiedRole("vicki", RoleConstants.AGGREGATOR, qualifiedAttributes);
        
        qualifiedAttributes = new HashMap<String, String>();
        qualifiedAttributes.put("bogus", "3");
        service.addQualifiedRole("vicki", RoleConstants.AGGREGATOR, qualifiedAttributes);
        
        qualifiedAttributes = new HashMap<String, String>();
        qualifiedAttributes.put("kra.proposal", "3");
        service.addQualifiedRole("mickey", RoleConstants.VIEWER, qualifiedAttributes);
        
        qualifiedAttributes = new HashMap<String, String>();
        qualifiedAttributes.put("kra.proposal", "3");
        service.addQualifiedRole("mickey", RoleConstants.AGGREGATOR, qualifiedAttributes);
    }
    
    /**
     * Build a Mock KRA Person service with a set of persons.
     * @return
     */
    private MockPersonService buildKraPersonService() {
        MockPersonService service = new MockPersonService();
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

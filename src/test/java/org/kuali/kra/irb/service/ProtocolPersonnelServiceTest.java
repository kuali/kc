/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.irb.personnel.ProtocolPersonTrainingService;
import org.kuali.kra.irb.personnel.ProtocolPersonnelService;
import org.kuali.kra.irb.personnel.ProtocolPersonnelServiceImpl;
import org.kuali.kra.irb.personnel.ProtocolUnit;
import org.kuali.kra.irb.service.mocks.MockProtocolPersonTrainingService;

public class ProtocolPersonnelServiceTest {
    protected static final String PRINCIPAL_INVESTIGATOR_PERSON_ID = "000000001";
    protected static final String CO_INVESTIGATOR_PERSON_ID = "000000002";
    protected static final String CO_INVESTIGATOR_NAME = "Philip Berg";
    protected static final String CO_INVESTIGATOR_ROLE_ID = "COI";
    protected static final String PRINCIPAL_INVESTIGATOR_ROLE_ID = "PI";
    protected static final String PRINCIPAL_INVESTIGATOR_UNIT = "BL-BL";
    protected static final String CO_INVESTIGATOR_UNIT = "000001";
    protected static final String CORRESPONDENT_ROLE_ID = "CRC";
    protected static final String PRINCIPAL_INVESTIGATOR_NAME = "CLINKSCALES";
    protected static final String PERSON_ID = "personId";
    private ProtocolPersonTrainingService protocolPersonTrainingService;
    private ProtocolPersonnelServiceImpl protocolPersonnelService;
    ProtocolPersonnelService service;

    @Before
    public void setUp() throws Exception {
        protocolPersonTrainingService = buildPersonTrainingService(); 
        protocolPersonnelService  = new ProtocolPersonnelServiceImpl();
        protocolPersonnelService.setProtocolPersonTrainingService(protocolPersonTrainingService);
        service = new ProtocolPersonnelServiceImpl();
    }
    
    /**
     * This method is to add a new protocol person
     * @throws Exception
     */
    @Test
    public void testAddProtocolPerson() throws Exception {
        Protocol protocol = new Protocol();
        protocolPersonnelService.addProtocolPerson(protocol, getCoInvestigatorPerson() );
        assertEquals(1, protocol.getProtocolPersons().size());
    }

    /**
     * This method is to delete protocol person
     * @throws Exception
     */
    @Test
    public void testDelProtocolPerson() throws Exception {
        Protocol protocol = new Protocol();
        ProtocolPerson protocolPerson = getCoInvestigatorPerson();
        protocolPerson.setDelete(true);
        protocol.getProtocolPersons().add(protocolPerson);
        assertEquals(1, protocol.getProtocolPersons().size());
        service.deleteProtocolPerson(protocol);
        assertEquals(0, protocol.getProtocolPersons().size());
    }

    /**
     * This method is to add protocol unit for a person
     * @throws Exception
     */
    @Test
    public void testAddProtocolUnit() throws Exception {
        Protocol protocol = new Protocol();
        ProtocolPerson protocolPerson = getCoInvestigatorPerson();
        List<ProtocolUnit> protocolPersonUnits = new ArrayList<ProtocolUnit>();
        protocol.getProtocolPersons().add(protocolPerson);
        protocolPersonUnits.add(getProtocolUnit());
        service.addProtocolPersonUnit(protocolPersonUnits, protocolPerson, 0);
        assertEquals(1, protocol.getProtocolPerson(0).getProtocolUnits().size());
    }

    /**
     * This method is to check if Principal Investigator exists in the list
     * @throws Exception
     */
    @Test
    public void testIsPIExists() throws Exception {
        List<ProtocolPerson> protocolPersons = new ArrayList<ProtocolPerson>();
        protocolPersons.add(getPrincipalInvestigatorPerson());
        protocolPersons.add(getCoInvestigatorPerson());
        boolean piExists = service.isPIExists(protocolPersons);
        assertTrue(piExists);
    }

    /**
     * This method is to check for duplicate person
     * @throws Exception
     */
    @Test
    public void testDuplicatePerson() throws Exception {
        List<ProtocolPerson> protocolPersons = new ArrayList<ProtocolPerson>();
        protocolPersons.add(getPrincipalInvestigatorPerson());
        protocolPersons.add(getCoInvestigatorPerson());
        boolean isDuplicate = service.isDuplicatePerson(protocolPersons, getPrincipalInvestigatorPerson());
        assertTrue(isDuplicate);
    }

    /**
     * This method is to get principal investigator person from list
     * @throws Exception
     */
    @Test
    public void testPIPerson() throws Exception {
        List<ProtocolPerson> protocolPersons = new ArrayList<ProtocolPerson>();
        protocolPersons.add(getPrincipalInvestigatorPerson());
        protocolPersons.add(getCoInvestigatorPerson());
        ProtocolPerson investigator = service.getPrincipalInvestigator(protocolPersons);
        assertEquals(investigator.getProtocolPersonRoleId(), PRINCIPAL_INVESTIGATOR_ROLE_ID);
    }

    /**
     * This method is to test switch roles. PI and Co-Investigators roles can be switched.
     * If a PI is set to Co-Investigator then existing Co-Investigator is set as PI
     * @throws Exception
     */
    @Test
    public void testSwitchInvestigatorCoInvestigatorRole() throws Exception {
        List<ProtocolPerson> protocolPersons = getProtocolPersons();
        assertEquals(protocolPersons.get(0).getProtocolPersonRoleId(), PRINCIPAL_INVESTIGATOR_ROLE_ID);
        assertEquals(protocolPersons.get(1).getProtocolPersonRoleId(), CO_INVESTIGATOR_ROLE_ID);
        protocolPersons.get(0).setProtocolPersonRoleId(CO_INVESTIGATOR_ROLE_ID);
        service.switchInvestigatorCoInvestigatorRole(protocolPersons);
        assertEquals(protocolPersons.get(0).getProtocolPersonRoleId(), CO_INVESTIGATOR_ROLE_ID);
        assertEquals(protocolPersons.get(1).getProtocolPersonRoleId(), PRINCIPAL_INVESTIGATOR_ROLE_ID);
    }
    
    /**
     * This method is to test student PI and faculty supervisor combination.
     * If PI or Co-Investigator affiliation type is set to Student Investigator then
     * there should exist a PI or Co-Investigator with affiliation type Faculty Supervisor.
     * Test a valid condition first and then test for invalid combination.
     * There is no affiliation set at first, so it should be valid.
     * @throws Exception
     */
    @Test
    public void testInValidStudentFacultyMatch() throws Exception {
        List<ProtocolPerson> protocolPersons = getProtocolPersons();
        boolean isInvalidMatch = service.isValidStudentFacultyMatch(protocolPersons);
        assertTrue(isInvalidMatch);
        protocolPersons.get(0).setAffiliationTypeCode(getStudentAffiliationType()); 
        isInvalidMatch = service.isValidStudentFacultyMatch(protocolPersons);
        assertFalse(isInvalidMatch);
    }
    
    /**
     * This method to test select protocol unit function
     * It is to select set the unit position with the lead unit flag on
     * for a person.
     * @throws Exception
     */
    @Test
    public void testSelectProtocolUnit() throws Exception {
        List<ProtocolPerson> protocolPersons = getProtocolPersons();
        protocolPersons.get(0).setSelectedUnit(1); 
        protocolPersons.get(0).getProtocolUnits().addAll(getProtocolUnits());
        service.selectProtocolUnit(protocolPersons);
        assertEquals(protocolPersons.get(0).getSelectedUnit(), 0);
    }
    
    /**
     * This method is to get a list of protocol persons
     * @return
     */
    private List<ProtocolPerson> getProtocolPersons() {
        List<ProtocolPerson> protocolPersons = new ArrayList<ProtocolPerson>();
        protocolPersons.add(getPrincipalInvestigatorPerson());
        protocolPersons.add(getCoInvestigatorPerson());
        return protocolPersons;
    }
    
    /**
     * This method is to get a new protocol person
     * @return ProtocolPerson
     */
    private ProtocolPerson getCoInvestigatorPerson() {
        ProtocolPerson protocolPerson = new ProtocolPerson();
        protocolPerson.setPersonId(CO_INVESTIGATOR_PERSON_ID);
        protocolPerson.setPersonName(CO_INVESTIGATOR_NAME);
        protocolPerson.setProtocolPersonRoleId(CO_INVESTIGATOR_ROLE_ID);
        protocolPerson.setPreviousPersonRoleId(CO_INVESTIGATOR_ROLE_ID);
        return protocolPerson;
    }
    
    /**
     * This method is to get protocol person with PI role
     * @return ProtocolPerson
     */
    private ProtocolPerson getPrincipalInvestigatorPerson() {
        ProtocolPerson protocolPerson = new ProtocolPerson();
        protocolPerson.setPersonId(PRINCIPAL_INVESTIGATOR_PERSON_ID);
        protocolPerson.setPersonName(PRINCIPAL_INVESTIGATOR_NAME);
        protocolPerson.setPreviousPersonRoleId(PRINCIPAL_INVESTIGATOR_ROLE_ID);
        protocolPerson.setProtocolPersonRoleId(PRINCIPAL_INVESTIGATOR_ROLE_ID);
        return protocolPerson;
        
    }

    /**
     * This method is to get protocol unit
     * @return ProtocolUnit
     */
    private ProtocolUnit getProtocolUnit() {
        ProtocolUnit protocolUnit = new ProtocolUnit();
        protocolUnit.setUnitNumber(PRINCIPAL_INVESTIGATOR_UNIT);
        protocolUnit.setLeadUnitFlag(false);
        return protocolUnit;
    }

    /**
     * This method is to get a list of protocol units with lead unit flag set
     * @return List<ProtocolUnit>
     */
    private List<ProtocolUnit> getProtocolUnits() {
        List<ProtocolUnit> protocolUnits = new ArrayList<ProtocolUnit>();
        ProtocolUnit protocolUnit = new ProtocolUnit();
        protocolUnit.setUnitNumber(PRINCIPAL_INVESTIGATOR_UNIT);
        protocolUnit.setLeadUnitFlag(true);
        protocolUnits.add(protocolUnit);
        protocolUnit = new ProtocolUnit();
        protocolUnit.setUnitNumber(CO_INVESTIGATOR_UNIT);
        protocolUnit.setLeadUnitFlag(false);
        return protocolUnits;
    }

    /**
     * This method is to build a mock of PersonTrainingService
     * @return
     */
    private MockProtocolPersonTrainingService buildPersonTrainingService() {
        MockProtocolPersonTrainingService personTrainingService = new MockProtocolPersonTrainingService();
        return personTrainingService;
    }

    /**
     * This method is to get student investigator affiliation type
     * @return Integer
     */
    private Integer getStudentAffiliationType() {
        return Constants.AFFILIATION_STUDENT_INVESTIGATOR_TYPE;
    }

}

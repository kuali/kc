/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.irb.personnel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentPersonnel;
import org.kuali.kra.irb.protocol.location.ProtocolLocationService;
import org.kuali.kra.irb.test.mocks.MockProtocolPersonTrainingService;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.kra.protocol.personnel.ProtocolUnitBase;
import org.kuali.kra.protocol.protocol.location.ProtocolLocationBase;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
public class ProtocolPersonnelServiceTest extends KcIntegrationTestBase {
    protected static final String CO_INVESTIGATOR_PERSON_ID = "10000000003";
    protected static final String CO_INVESTIGATOR_NAME = "Nicholas Majors";
    protected static final String CO_INVESTIGATOR_ROLE_ID = "COI";
    protected static final String CO_INVESTIGATOR_UNIT = "000001";
    protected static final String CO_INVESTIGATOR_OBJ_ID="dhafjkhadlkjfha";
    protected static final String PRINCIPAL_INVESTIGATOR_ROLE_ID = "PI";
    protected static final String PRINCIPAL_INVESTIGATOR_UNIT = "IN-CARD";
    protected static final String PRINCIPAL_INVESTIGATOR_PERSON_ID = "10000000001";
    protected static final String PRINCIPAL_INVESTIGATOR_NAME = "Joe Tester";
    private ProtocolPersonTrainingService protocolPersonTrainingService;
    private ProtocolPersonnelServiceImpl protocolPersonnelService;
    private ProtocolPersonnelService service;

    @Before
    public void setUp() throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        protocolPersonTrainingService = buildPersonTrainingService(); 
        //protocolPersonnelService  = new ProtocolPersonnelServiceImpl();
        
        protocolPersonnelService = (ProtocolPersonnelServiceImpl) KcServiceLocator.getService(ProtocolPersonnelService.class);
        service = KcServiceLocator.getService(ProtocolPersonnelService.class);
        
        protocolPersonnelService.setProtocolPersonTrainingService(protocolPersonTrainingService);
        //service = new ProtocolPersonnelServiceImpl();
    }
    
    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        service = null;
        protocolPersonnelService = null;
    }
    
    @Test
    public void testCorrectClass(){
        assertTrue("Should be the same", service.getClass().equals(ProtocolPersonnelServiceImpl.class));
        assertTrue("Should be the same", protocolPersonnelService.getClass().equals(ProtocolPersonnelServiceImpl.class));
    }
    
    /**
     * This method is to add a new protocol person
     * @throws Exception
     */
    @Test
    public void testAddProtocolPerson() throws Exception {
        Protocol protocol = new Protocol(){
            @Override
            public void refreshReferenceObject(String referenceObjectName) {}

         };
        protocolPersonnelService.addProtocolPerson(protocol, getCoInvestigatorPerson() );
        assertEquals(1, protocol.getProtocolPersons().size());
   }

    /**
     * This method is to delete protocol person
     * @throws Exception
     */
    @Test
    public void testDelProtocolPerson() throws Exception {
        Protocol protocol = new Protocol(){
            @Override
            public void refreshReferenceObject(String referenceObjectName) {}

        };
        ProtocolPerson protocolPerson = getCoInvestigatorPerson();
        protocolPerson.setDelete(true);
        protocol.getProtocolPersons().add(protocolPerson);
        assertEquals(1, protocol.getProtocolPersons().size());
        service.deleteProtocolPerson(protocol);
        assertEquals(0, protocol.getProtocolPersons().size());
    }
    
    /**
     * This method test adding an attachment to a person on a protocol.
     */
    @Test
    public void testAddProtocolAttachmentPersonnel() {
        Protocol protocol = new Protocol() {
            @Override
            public void refreshReferenceObject(String referenceObjectName) {}

            @Override
            protected ProtocolLocationService getProtocolLocationService() {
               return new ProtocolLocationService() {
                public void addDefaultProtocolLocation(ProtocolBase protocol) {}
                public void addProtocolLocation(ProtocolBase protocol, ProtocolLocationBase protocolLocation) {}
                public void clearProtocolLocationAddress(ProtocolBase protocol, int lineNumber) { }
               };
            }
        };
        ProtocolPerson protocolPerson = getCoInvestigatorPerson();
        protocol.getProtocolPersons().add(protocolPerson);
        ProtocolAttachmentPersonnel newAttachment = new ProtocolAttachmentPersonnel();
        service.addProtocolPersonAttachment(protocol, newAttachment, 0);
        assertEquals(1, protocol.getProtocolPerson(0).getAttachmentPersonnels().size());
    }

    /**
     * This method is to add protocol unit for a person
     * @throws Exception
     */
    @Test
    public void testAddProtocolUnit() throws Exception {
        Protocol protocol = new Protocol(){
            @Override
            public void refreshReferenceObject(String referenceObjectName) {}

            @Override
            protected ProtocolLocationService getProtocolLocationService() {
               return new ProtocolLocationService() {
                public void addDefaultProtocolLocation(ProtocolBase protocol) {}
                public void addProtocolLocation(ProtocolBase protocol, ProtocolLocationBase protocolLocation) {}
                public void clearProtocolLocationAddress(ProtocolBase protocol, int lineNumber) { }
               };
            }
            
        };
        ProtocolPerson protocolPerson = getCoInvestigatorPerson();
        List<ProtocolUnitBase> protocolPersonUnits = new ArrayList<ProtocolUnitBase>();
        protocol.getProtocolPersons().add(protocolPerson);
        protocolPersonUnits.add(getProtocolUnit());
        service.addProtocolPersonUnit(protocolPersonUnits, protocolPerson, 0);
        assertEquals(1, protocol.getProtocolPerson(0).getProtocolUnits().size());
    }

    /**
     * This method is to check for duplicate person
     * @throws Exception
     */
    @Test
    public void testDuplicatePerson() throws Exception {
        List<ProtocolPersonBase> protocolPersons = new ArrayList<ProtocolPersonBase>();
        protocolPersons.add(getPrincipalInvestigatorPerson());
        ProtocolPerson coi = getCoInvestigatorPerson();
        coi.setPersonId(CO_INVESTIGATOR_PERSON_ID);
        protocolPersons.add(coi);
        boolean isDuplicate = service.isDuplicatePerson(protocolPersons, getPrincipalInvestigatorPerson());
        assertTrue(isDuplicate);
    }

    /**
     * This method is to get principal investigator person from list
     * @throws Exception
     */
    @Test
    public void testPIPerson() throws Exception {
        List<ProtocolPersonBase> protocolPersons = new ArrayList<ProtocolPersonBase>();
        protocolPersons.add(getPrincipalInvestigatorPerson());
        protocolPersons.add(getCoInvestigatorPerson());
        ProtocolPerson investigator = (ProtocolPerson) service.getPrincipalInvestigator(protocolPersons);
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
        service.switchInvestigatorCoInvestigatorRole((List) protocolPersons);
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
        List<ProtocolPersonBase> protocolPersons = (List) getProtocolPersons();
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
        List<ProtocolPersonBase> protocolPersons = (List) getProtocolPersons();
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
        //protocolPerson.setPersonId(CO_INVESTIGATOR_PERSON_ID);
        protocolPerson.setPersonName(CO_INVESTIGATOR_NAME);
        protocolPerson.setProtocolPersonRoleId(CO_INVESTIGATOR_ROLE_ID);
        protocolPerson.setPreviousPersonRoleId(CO_INVESTIGATOR_ROLE_ID);
        protocolPerson.setObjectId(CO_INVESTIGATOR_OBJ_ID);
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

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

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.irb.bo.ProtocolPerson;
import org.kuali.kra.irb.bo.ProtocolUnit;
import org.kuali.kra.irb.service.impl.ProtocolPersonnelServiceImpl;

public class ProtocolPersonnelServiceTest {
    protected static final String PRINCIPAL_INVESTIGATOR_PERSON_ID = "000000001";
    protected static final String CO_INVESTIGATOR_PERSON_ID = "000000002";
    protected static final String CO_INVESTIGATOR_NAME = "Philip Berg";
    protected static final String CO_INVESTIGATOR_ROLE_ID = "COI";
    protected static final String PRINCIPAL_INVESTIGATOR_ROLE_ID = "PI";
    protected static final String PRINCIPAL_INVESTIGATOR_UNIT = "BL-BL";
    protected static final String CORRESPONDENT_ROLE_ID = "CRC";
    protected static final String PRINCIPAL_INVESTIGATOR_NAME = "CLINKSCALES";
    
    /**
     * This method is to add a new protocol person
     * @throws Exception
     */
    @Test
    public void testAddProtocolPerson() throws Exception {
        ProtocolPersonnelService service  = new ProtocolPersonnelServiceImpl();
        Protocol protocol = new Protocol();
        service.addProtocolPerson(protocol, getCoInvestigatorPerson() );
        assertEquals(1, protocol.getProtocolPersons().size());
    }

    /**
     * This method is to delete protocol person
     * @throws Exception
     */
    @Test
    public void testDelProtocolPerson() throws Exception {
        ProtocolPersonnelService service  = new ProtocolPersonnelServiceImpl();
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
        ProtocolPersonnelService service  = new ProtocolPersonnelServiceImpl();
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
    public void testPIExists() throws Exception {
        ProtocolPersonnelService service  = new ProtocolPersonnelServiceImpl();
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
        ProtocolPersonnelService service  = new ProtocolPersonnelServiceImpl();
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
        ProtocolPersonnelService service  = new ProtocolPersonnelServiceImpl();
        List<ProtocolPerson> protocolPersons = new ArrayList<ProtocolPerson>();
        protocolPersons.add(getPrincipalInvestigatorPerson());
        protocolPersons.add(getCoInvestigatorPerson());
        ProtocolPerson investigator = service.getPrincipalInvestigator(protocolPersons);
        assertEquals(investigator.getProtocolPersonRoleId(), PRINCIPAL_INVESTIGATOR_ROLE_ID);
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
     * @return
     */
    private ProtocolUnit getProtocolUnit() {
        ProtocolUnit protocolUnit = new ProtocolUnit();
        protocolUnit.setUnitNumber(PRINCIPAL_INVESTIGATOR_UNIT);
        protocolUnit.setLeadUnitFlag(false);
        return protocolUnit;
    }

}

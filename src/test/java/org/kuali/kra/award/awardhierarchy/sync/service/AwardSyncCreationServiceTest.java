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
package org.kuali.kra.award.awardhierarchy.sync.service;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncChange;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncPendingChangeBean;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncType;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncXmlExport;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.contacts.AwardPersonUnit;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;

public class AwardSyncCreationServiceTest extends KcUnitTestBase {

    private Award award;
    private AwardSyncCreationService awardSyncCreationService;
    private AwardPerson person;
    private AwardPersonUnit personUnit;
    private AwardPersonUnit personUnit2;
    
    @Before
    public void setUp() throws Exception {
        awardSyncCreationService = KraServiceLocator.getService(AwardSyncCreationService.class);
        award = new Award();
        person = new AwardPerson();
        person.setPersonId("10000000001");
        person.setFullName("quickstart");
        person.setRoleCode("PI");
        Unit unit = new Unit();
        unit.setUnitName("TestUnit");
        unit.setUnitNumber("000001");
        personUnit = new AwardPersonUnit();
        personUnit.setAwardPerson(person);
        personUnit.setLeadUnit(true);
        personUnit.setUnit(unit);
        
        unit = new Unit();
        unit.setUnitName("TestUnit2");
        unit.setUnitNumber("BL-BL");
        personUnit2 = new AwardPersonUnit();
        personUnit2.setAwardPerson(person);
        personUnit2.setLeadUnit(false);
        personUnit2.setUnit(unit);

        person.add(personUnit);
        person.add(personUnit2);
        award.add(person);
    }

    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void testCreateSyncChange() throws Exception {
        AwardSyncPendingChangeBean pendingChange = 
            new AwardSyncPendingChangeBean(AwardSyncType.ADD_SYNC, personUnit, "projectPersons", null);
        AwardSyncChange change = awardSyncCreationService.createAwardSyncChange(pendingChange);
        assertNotNull(change);
        assertTrue(change.getXml().length() > 0);
    }
    
    @Test
    public void testAddAwardSyncPendingChange() throws Exception {
        AwardSyncPendingChangeBean pendingChange = 
            new AwardSyncPendingChangeBean(AwardSyncType.ADD_SYNC, personUnit, "projectPersons", null);
        awardSyncCreationService.addAwardSyncChange(award, pendingChange);
        assertTrue(!award.getSyncChanges().isEmpty());
    }
    
    @Test
    public void testAddAwardSyncChange() throws Exception {
        AwardSyncPendingChangeBean pendingChange = 
            new AwardSyncPendingChangeBean(AwardSyncType.DELETE_SYNC, personUnit, "projectPersons", null);
        AwardSyncChange change = awardSyncCreationService.createAwardSyncChange(pendingChange);
        awardSyncCreationService.addAwardSyncChange(award, change);
        assertTrue(!award.getSyncChanges().isEmpty());
    }
    
    @Test
    public void testGetXmlExport() throws Exception {
        AwardSyncPendingChangeBean pendingChange = 
            new AwardSyncPendingChangeBean(AwardSyncType.DELETE_SYNC, personUnit, "projectPersons", null);
        AwardSyncChange change = awardSyncCreationService.createAwardSyncChange(pendingChange);
        change.setXmlExport(null);
        AwardSyncXmlExport xmlExport = awardSyncCreationService.getXmlExport(change);
        assertNotNull(xmlExport);
    }
    
    @Test
    public void testAddUniqueUnits() throws Exception {
        AwardSyncPendingChangeBean pendingChange = 
            new AwardSyncPendingChangeBean(AwardSyncType.DELETE_SYNC, personUnit, "projectPersons", null);
        AwardSyncChange change = awardSyncCreationService.createAwardSyncChange(pendingChange);
        awardSyncCreationService.addAwardSyncChange(award, change);
        assertTrue(!award.getSyncChanges().isEmpty());
        
        pendingChange = 
            new AwardSyncPendingChangeBean(AwardSyncType.DELETE_SYNC, personUnit2, "projectPersons", null);
        change = awardSyncCreationService.createAwardSyncChange(pendingChange);
        awardSyncCreationService.addAwardSyncChange(award, change);
        assertEquals(2, award.getSyncChanges().size());
    }
    
    @Test
    public void testAddDuplicateUnits() throws Exception {
        AwardSyncPendingChangeBean pendingChange = 
            new AwardSyncPendingChangeBean(AwardSyncType.DELETE_SYNC, personUnit, "projectPersons", null);
        AwardSyncChange change = awardSyncCreationService.createAwardSyncChange(pendingChange);
        awardSyncCreationService.addAwardSyncChange(award, change);
        assertTrue(!award.getSyncChanges().isEmpty());
        
        personUnit2.setUnit(personUnit.getUnit());
        pendingChange = 
            new AwardSyncPendingChangeBean(AwardSyncType.DELETE_SYNC, personUnit2, "projectPersons", null);
        change = awardSyncCreationService.createAwardSyncChange(pendingChange);
        awardSyncCreationService.addAwardSyncChange(award, change);
        assertEquals(1, award.getSyncChanges().size());
    }    
}

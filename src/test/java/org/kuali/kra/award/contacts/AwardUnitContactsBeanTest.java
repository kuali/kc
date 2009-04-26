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
package org.kuali.kra.award.contacts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.bo.ContactType;
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.bo.UnitContactType;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * This class tests the AwardUnitContactsBean 
 */
@RunWith(JMock.class)
public class AwardUnitContactsBeanTest extends KraTestBase {

    private static final int THREE = 3;
    private static final String LEAD_UNIT = "IN-CARD";
    private static final String NONLEAD_UNIT = "CARDIO";
    private Award award;
    private AwardUnitContactsBean bean;

    private Mockery context = new JUnit4Mockery();
    private BusinessObjectService bos;
    private List<Person> personnel;
    private List<ContactType> contactTypes;
    
    @Before
    public void setUp() {
        initAward();
        initBean();
        bos = context.mock(BusinessObjectService.class);
        initializePeople();
        initializeContactTypes();
    }

    private void initializeContactTypes() {
        contactTypes = new ArrayList<ContactType>();
        ContactType ct = new ContactType();
        ct.setDescription("a1");
        ct.setContactTypeCode("A1");
        contactTypes.add(ct);
        ct = new ContactType();
        ct.setDescription("a2");
        ct.setContactTypeCode("A2");
        contactTypes.add(ct);
    }

    @Test
    public void testGettingUnitContacts_NoExistingContacts() {
        List<Person> unitPersonnel = new ArrayList<Person>();
        unitPersonnel.add(personnel.get(0));
        unitPersonnel.add(personnel.get(2));
        context.checking(createExpectation(unitPersonnel));
        bean.setBusinessObjectService(bos);
        
        List<AwardUnitContact> unitContacts = bean.getUnitContacts();        
        Assert.assertEquals(2, unitContacts.size());
        int index = 0;
        for(AwardUnitContact unitContact: unitContacts) {
            Assert.assertEquals(unitPersonnel.get(index++).getFullName(), unitContact.getPerson().getFullName());
        }
    }
    
    @Test
    public void testGettingUnitContacts_OneExists_OneAdded() {
        AwardUnitContact contact = new AwardUnitContact();
        contact.setPerson(personnel.get(0));
        Unit unit = new Unit();
        unit.setUnitNumber(LEAD_UNIT);
        unit.setUnitName(LEAD_UNIT);
        contact.setUnitName(LEAD_UNIT);
        contact.setUnitContactType(UnitContactType.CONTACT);
        award.add(contact);
        
        List<Person> unitPersonnel = new ArrayList<Person>();
        unitPersonnel.add(personnel.get(0));
        unitPersonnel.add(personnel.get(2));
        
        context.checking(createExpectation(unitPersonnel));
        bean.setBusinessObjectService(bos);
        List<AwardUnitContact> unitContacts = bean.getUnitContacts();        
        Assert.assertEquals(2, unitContacts.size());
        int index = 0;
        for(AwardUnitContact unitContact: unitContacts) {
            Assert.assertEquals(unitPersonnel.get(index++).getFullName(), unitContact.getPerson().getFullName());
        }
    }

    @Test
    public void testGettingUnitContacts_TwoExist_OneRemoved() {
        AwardUnitContact contact = new AwardUnitContact();
        contact.setPerson(personnel.get(0));
        contact.setUnitContactType(UnitContactType.CONTACT);
        award.add(contact);
        
        contact = new AwardUnitContact();
        contact.setPerson(personnel.get(2));
        contact.setUnitContactType(UnitContactType.CONTACT);
        award.add(contact);
        
        List<Person> unitPersonnel = new ArrayList<Person>();
        unitPersonnel.add(personnel.get(0));
        
        context.checking(createExpectation(unitPersonnel));
        bean.setBusinessObjectService(bos);
        List<AwardUnitContact> unitContacts = bean.getUnitContacts();        
        Assert.assertEquals(1, unitContacts.size());
        Assert.assertEquals(unitPersonnel.get(0).getFullName(), unitContacts.get(0).getPerson().getFullName());
    }
    
    @Test
    public void testGettingUnitContacts_ThreeExist_TwoFromLeadUnit() {
        List<Person> unitPersonnel = new ArrayList<Person>();
        unitPersonnel.add(personnel.get(0));
        unitPersonnel.add(personnel.get(2));
        
        for(Person p: personnel) {
        AwardUnitContact contact = new AwardUnitContact();
            contact.setPerson(p);
            contact.setUnitContactType(UnitContactType.CONTACT);
            award.add(contact);
        }
        
        context.checking(createExpectation(unitPersonnel));
        bean.setBusinessObjectService(bos);
        List<AwardUnitContact> unitContacts = bean.getUnitContacts();        
        Assert.assertEquals(THREE, unitContacts.size());
        Assert.assertEquals(personnel.get(0).getFullName(), unitContacts.get(0).getPerson().getFullName());
        Assert.assertEquals(personnel.get(2).getFullName(), unitContacts.get(2).getPerson().getFullName());
        Assert.assertEquals(personnel.get(1).getFullName(), unitContacts.get(1).getPerson().getFullName());
    }
    
    @Test
    public void testGettingUnitContacts_ThreeExist_ChangeOneContactRole() {
        List<Person> unitPersonnel = new ArrayList<Person>();
        unitPersonnel.add(personnel.get(0));
        unitPersonnel.add(personnel.get(2));
        
        for(Person p: personnel) {
            AwardUnitContact contact = new AwardUnitContact();
            contact.setPerson(p);
            contact.setUnitContactType(UnitContactType.CONTACT);
            contact.setContactRole(contactTypes.get(0));
            award.add(contact);
        }
        
        context.checking(createExpectation(unitPersonnel));
        bean.setBusinessObjectService(bos);
        List<AwardUnitContact> unitContacts = bean.getUnitContacts();        
        Assert.assertEquals(contactTypes.get(0).getContactTypeCode(), unitContacts.get(0).getContactRoleCode());
        unitContacts.get(0).setContactRole(contactTypes.get(1));
        Assert.assertEquals(contactTypes.get(1).getContactTypeCode(), unitContacts.get(0).getContactRoleCode());
    }
    
    @Test
    public void testGettingUnitContacts_ThreeExist_TwoFromLeadUnit_OneRemoved() {
        List<Person> unitPersonnel = new ArrayList<Person>();
        unitPersonnel.add(personnel.get(0));
        
        for(Person p: personnel) {
        AwardUnitContact contact = new AwardUnitContact();
            contact.setPerson(p);
            contact.setUnitContactType(UnitContactType.CONTACT);
            award.add(contact);
        }
        
        context.checking(createExpectation(unitPersonnel));
        bean.setBusinessObjectService(bos);
        List<AwardUnitContact> unitContacts = bean.getUnitContacts();        
        Assert.assertEquals(2, unitContacts.size());
        Assert.assertEquals(personnel.get(0).getFullName(), unitContacts.get(0).getPerson().getFullName());
        Assert.assertEquals(personnel.get(1).getFullName(), unitContacts.get(1).getPerson().getFullName());
    }
    
    private Expectations createExpectation(final List<Person> unitPeople) {
        final Map<String, Object> FIELD_VALUES = new HashMap<String, Object>();
        FIELD_VALUES.put("homeUnit", LEAD_UNIT);
        return new Expectations() {{
            atLeast(1).of(bos).findMatching(Person.class, FIELD_VALUES); 
            will(returnValue(unitPeople));
            allowing(bos).findAll(ContactType.class);
            will(returnValue(contactTypes));
        }};
    }

    private void initAward() {
        award = new Award();
        AwardPerson p = new AwardPerson();
        AwardPersonUnit apu = new AwardPersonUnit();
        Unit unit = new Unit();
        unit.setUnitNumber(LEAD_UNIT);
        apu.setUnit(unit);
        apu.setAwardPerson(p);
        apu.setLeadUnit(true);
        p.add(apu);
        award.add(p);
    }
    
    private void initBean() {
        bean = new AwardUnitContactsBean(null) {
            @Override
            protected Award getAward() {
                return award;
            }            
        };
    }
    
    private void initializePeople() {
        personnel = new ArrayList<Person>();
        
        Person p = new Person();
        p.setFullName("Person 1");
        p.setHomeUnit(LEAD_UNIT);
        p.setPersonId("1");
        personnel.add(p);
        
        p = new Person();
        p.setFullName("Person 2");
        p.setHomeUnit(NONLEAD_UNIT);
        p.setPersonId("2");
        personnel.add(p);
        
        p = new Person();
        p.setFullName("Person 3");
        p.setHomeUnit(LEAD_UNIT);
        p.setPersonId("3");
        personnel.add(p);
    }
}

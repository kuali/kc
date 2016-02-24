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
package org.kuali.kra.award.home;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.rolodex.NonOrganizationalRolodex;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.contacts.AwardPersonUnit;
import org.kuali.kra.award.contacts.ContactRoleFixtureFactory;
import org.kuali.kra.award.paymentreports.specialapproval.approvedequipment.AwardApprovedEquipment;
import org.kuali.kra.bo.KcPersonFixtureFactory;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class tests methods in Award.java class
 */
public class AwardTest { 
    private static final double DELTA = 0.001;

    private static final String PERSON_ID = "1001";
    private static final String KP_PERSON_ID = "1002";
    private static final int ROLODEX_ID = 1002;
    
    private Award awardBo;

    @Before
    public void setUp() throws Exception {
        awardBo = new Award();
    }

    @After
    public void tearDown() throws Exception {
        awardBo = null;
    }
    
    @Test
    public void testCalculatingValuableItemTotal() {
        final double AMOUNT1 = 100.00;
        final double AMOUNT2 = 200.00;
        final double AMOUNT3 = 300.00;
        Award award = new Award();
        List<AwardApprovedEquipment> items = new ArrayList<AwardApprovedEquipment>();
        items.add(createValuableItem(AMOUNT1));
        items.add(createValuableItem(AMOUNT2));
        items.add(createValuableItem(AMOUNT3));
        award.setApprovedEquipmentItems(items);
        Assert.assertEquals(AMOUNT1 + AMOUNT2 + AMOUNT3, award.getTotalAmount(items).doubleValue(), DELTA);
    }
    
    private AwardApprovedEquipment createValuableItem(double amount) {
        AwardApprovedEquipment item = new AwardApprovedEquipment();
        item.setAmount(new ScaleTwoDecimal(amount));
        return item;
    }
    
    @Test
    public void testIsNew_CaseAwardIdIsNull(){
        awardBo.setAwardId(null);
        Assert.assertTrue(awardBo.isNew());
    }
    
    @Test
    public void testIsNew_CaseAwardIdIsNotNull(){
        awardBo.setAwardId(new Long(1));
        Assert.assertFalse(awardBo.isNew());
    }
    
    @Test
    public void testGetProjectPesons() {
        this.addProjectPersonsToAward();
        //test getProjectPersons
        awardBo.setSponsorNihMultiplePi(false);
        List<AwardPerson> aList = awardBo.getProjectPersons();
        Assert.assertEquals(3, aList.size());
        
        aList.get(0).getContactRole();
        Assert.assertEquals(ContactRole.COI_CODE, aList.get(0).getContactRoleCode());
        Assert.assertEquals(ContactRole.KEY_PERSON_CODE, aList.get(1).getContactRoleCode());
        Assert.assertEquals(ContactRole.PI_CODE, aList.get(2).getContactRoleCode());
        
        // test getProjectPersonsSorted
        aList = awardBo.getProjectPersonsSorted();
        Assert.assertEquals(3, aList.size());
        Assert.assertEquals(ContactRole.PI_CODE, aList.get(0).getContactRoleCode());
        Assert.assertEquals(ContactRole.COI_CODE, aList.get(1).getContactRoleCode());
        Assert.assertEquals(ContactRole.KEY_PERSON_CODE, aList.get(2).getContactRoleCode());        
    }
    
    public void addProjectPersonsToAward() {
        awardBo = new Award();

        Unit unitA = new Unit();
        unitA.setUnitName("a");
        unitA.setUnitNumber("1");

        Unit unitB = new Unit();
        unitB.setUnitName("b");
        unitB.setUnitNumber("2");

        KcPerson employee = KcPersonFixtureFactory.createKcPerson(PERSON_ID);
        AwardPerson piPerson = new AwardPerson(employee, ContactRoleFixtureFactory.MOCK_PI);
        AwardPersonUnit aUnit = new AwardPersonUnit();
        aUnit.setAwardPerson(piPerson);
        aUnit.setUnit(unitA);
        aUnit.setLeadUnit(true);
        piPerson.add(aUnit);

        NonOrganizationalRolodex nonEmployee;
        nonEmployee = new NonOrganizationalRolodex();
        nonEmployee.setRolodexId(ROLODEX_ID);
        AwardPerson coiPerson = new AwardPerson(nonEmployee, ContactRoleFixtureFactory.MOCK_COI);
        aUnit.setAwardPerson(coiPerson);
        aUnit.setUnit(unitA);
        aUnit.setLeadUnit(false);
        coiPerson.add(aUnit);

        KcPerson employee2 = KcPersonFixtureFactory.createKcPerson(KP_PERSON_ID);
        AwardPerson kpPerson = new AwardPerson(employee2, ContactRoleFixtureFactory.MOCK_KEY_PERSON);
        kpPerson.setKeyPersonRole("Tester");  
        aUnit.setAwardPerson(kpPerson);
        aUnit.setUnit(unitA);
        aUnit.setLeadUnit(false);
        kpPerson.add(aUnit);
        
        awardBo.add(coiPerson);
        awardBo.add(kpPerson);
        awardBo.add(piPerson);
    }
}

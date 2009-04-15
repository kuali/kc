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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.Unit;

/**
 * This class tests AwardContact
 */
public class AwardPersonTest {
    private AwardPerson contact;
    private Unit unitA;
    private Unit unitB;
    
    @Before
    public void setUp() throws Exception {
        contact = new AwardPerson();
        unitA = new Unit();
        unitA.setUnitName("UnitA");
        unitB = new Unit();
        unitB.setUnitName("UnitB");        
    }
    
    @After
    public void tearDown() throws Exception {
        contact = null;
        unitA = null;
        unitB = null;
    }
    
    @Test
    public void testAddingUnits() throws Exception {
        Assert.assertEquals(0, contact.getUnits().size());
        contact.add(new AwardPersonUnit(contact, unitA, AwardPersonUnit.IS_NOT_LEAD_UNIT));
        contact.add(new AwardPersonUnit(contact, unitB, AwardPersonUnit.IS_LEAD_UNIT));
        Assert.assertEquals(2, contact.getUnits().size());        
    }
    
    @Test
    public void testFindingLeadUnit() throws Exception {
        contact.add(new AwardPersonUnit(contact, unitA, AwardPersonUnit.IS_NOT_LEAD_UNIT));
        contact.add(new AwardPersonUnit(contact, unitB, AwardPersonUnit.IS_LEAD_UNIT));
        Assert.assertEquals(unitB.getUnitName(), contact.findLeadUnit().getUnitName());
    }
    
    @Test
    public void testFindingPrincipalInvestigator() {
        contact.setContactRole(ContactRoleFixtureFactory.MOCK_COI);
        Assert.assertFalse("PI misidentified", contact.isPrincipalInvestigator());
        
        contact.setContactRole(ContactRoleFixtureFactory.MOCK_PI);
        Assert.assertTrue("PI not identified", contact.isPrincipalInvestigator());
    }
}

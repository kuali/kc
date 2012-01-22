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
package org.kuali.kra.award.contacts;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.NonOrganizationalRolodex;
import org.kuali.kra.bo.Unit;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

public class AwardProjectPersonsAuditRuleTest {
    
    private static final String MISSING_UNIT_DETAILS_NOT_IDENTIFIED = "Missing unit Details not identified";
    private static final int ROLODEX_ID = 1002;
    private Award award;
    private AwardProjectPersonsAuditRule rule;
    private KcPerson person1;
    private NonOrganizationalRolodex person2;
    private Unit unitA;
    private Unit unitB;
    
    @Before
    public void setUp() {
        rule = new AwardProjectPersonsAuditRule();
        award = new Award();
        
        person1 = new KcPerson();
        person1.setPersonId("1001");
        
        person2 = new NonOrganizationalRolodex();
        person2.setRolodexId(ROLODEX_ID);
        
        award.add(new AwardPerson(person1, ContactRoleFixtureFactory.MOCK_PI));
        award.add(new AwardPerson(person2, ContactRoleFixtureFactory.MOCK_COI));
        
        unitA = new Unit();
        unitA.setUnitName("a");
        unitA.setUnitNumber("1");
        
        unitB = new Unit();
        unitB.setUnitName("b");
        unitB.setUnitNumber("2");
        
        GlobalVariables.setMessageMap(new MessageMap());
    }
    
    @After
    public void tearDown() {
        rule = null;
        award = null;
    }
    
    @Test
    public void testCheckForExistingPI_DuplicateFound() {
        AwardPerson newPerson = new AwardPerson(new KcPerson(), ContactRoleFixtureFactory.MOCK_PI);
        award.add(newPerson);
        Assert.assertFalse("Duplicate PI not identified", rule.checkPrincipalInvestigators(award.getProjectPersons()));
    }
    
    @Test
    public void testCheckForExistingPI_NoDuplicateFound() {
        AwardPerson newPerson = new AwardPerson(new KcPerson(), ContactRoleFixtureFactory.MOCK_KEY_PERSON);
        award.add(newPerson);
        Assert.assertTrue("Duplicate PI misidentified", rule.checkPrincipalInvestigators(award.getProjectPersons()));
    }

    @Test
    public void testCheckForUnitDetails_RequiredForPI() {
        award.getProjectPersons().get(1).setContactRole(ContactRoleFixtureFactory.MOCK_KEY_PERSON);
        Assert.assertFalse(MISSING_UNIT_DETAILS_NOT_IDENTIFIED, rule.checkUnits(award.getProjectPersons()));
    }
    
    @Test
    public void testCheckForUnitDetails_RequiredForCoI() {
        award.getProjectPersons().get(0).setContactRole(ContactRoleFixtureFactory.MOCK_KEY_PERSON);
        Assert.assertFalse(MISSING_UNIT_DETAILS_NOT_IDENTIFIED, rule.checkUnits(award.getProjectPersons()));
    }
    
    @Test
    public void testCheckForUnitDetails_NotRequiredForKeyPerson() {
        award.getProjectPersons().get(0).setContactRole(ContactRoleFixtureFactory.MOCK_KEY_PERSON);
        award.getProjectPersons().get(1).setContactRole(ContactRoleFixtureFactory.MOCK_KEY_PERSON);
        Assert.assertTrue("Unit details are not required for key person", rule.checkLeadUnits(award.getProjectPersons()));
    }
}

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
package org.kuali.kra.award.contacts;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.rolodex.NonOrganizationalRolodex;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

public class AwardProjectPersonsAuditRuleTest extends KcIntegrationTestBase {
    
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

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
package org.kuali.kra.institutionalproposal.contacts;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.contacts.ContactRoleFixtureFactory;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.NonOrganizationalRolodex;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

public class InstitutionalProposalProjectPersonsAuditRuleTest extends KcUnitTestBase {
    
    private static final String MISSING_UNIT_DETAILS_NOT_IDENTIFIED = "Missing unit Details not identified";
    private static final int ROLODEX_ID = 1002;
    private InstitutionalProposal institutionalProposal;
    private InstitutionalProposalPersonAuditRule rule;
    private KcPerson person1;
    private NonOrganizationalRolodex person2;
    private Unit unitA;
    private Unit unitB;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        rule = new InstitutionalProposalPersonAuditRule();
        institutionalProposal = new InstitutionalProposal();
        
        person1 = new KcPerson();
        person1.setPersonId("1001");
        
        person2 = new NonOrganizationalRolodex();
        person2.setRolodexId(ROLODEX_ID);
        
        institutionalProposal.add(new InstitutionalProposalPerson(person1, ContactRoleFixtureFactory.MOCK_PI));
        institutionalProposal.add(new InstitutionalProposalPerson(person2, ContactRoleFixtureFactory.MOCK_COI));
        
        unitA = new Unit();
        unitA.setUnitName("a");
        unitA.setUnitNumber("1");
        
        unitB = new Unit();
        unitB.setUnitName("b");
        unitB.setUnitNumber("2");
        
        GlobalVariables.setMessageMap(new MessageMap());
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        rule = null;
        institutionalProposal = null;
    }
    
    @Test
    public void testCheckForExistingPI_DuplicateFound() {
        InstitutionalProposalPerson newPerson = new InstitutionalProposalPerson(new KcPerson(), ContactRoleFixtureFactory.MOCK_PI);
        institutionalProposal.add(newPerson);
        Assert.assertFalse("Duplicate PI not identified", rule.checkPrincipalInvestigators(institutionalProposal.getProjectPersons()));
    }
    
    @Test
    public void testCheckForExistingPI_NoDuplicateFound() {
        InstitutionalProposalPerson newPerson = new InstitutionalProposalPerson(new KcPerson(), ContactRoleFixtureFactory.MOCK_KEY_PERSON);
        institutionalProposal.add(newPerson);
        Assert.assertTrue("Duplicate PI misidentified", rule.checkPrincipalInvestigators(institutionalProposal.getProjectPersons()));
    }

    @Test
    public void testCheckForUnitDetails_RequiredForPI() {
        institutionalProposal.getProjectPersons().get(1).setContactRole(ContactRoleFixtureFactory.MOCK_KEY_PERSON);
        Assert.assertFalse(MISSING_UNIT_DETAILS_NOT_IDENTIFIED, rule.checkUnits(institutionalProposal.getProjectPersons()));
    }
    
    @Test
    public void testCheckForUnitDetails_RequiredForCoI() {
        institutionalProposal.getProjectPersons().get(0).setContactRole(ContactRoleFixtureFactory.MOCK_KEY_PERSON);
        Assert.assertFalse(MISSING_UNIT_DETAILS_NOT_IDENTIFIED, rule.checkUnits(institutionalProposal.getProjectPersons()));
    }
    
    @Test
    public void testCheckForUnitDetails_NotRequiredForKeyPerson() {
        institutionalProposal.getProjectPersons().get(0).setContactRole(ContactRoleFixtureFactory.MOCK_KEY_PERSON);
        institutionalProposal.getProjectPersons().get(1).setContactRole(ContactRoleFixtureFactory.MOCK_KEY_PERSON);
        Assert.assertTrue("Unit details are not required for key person", rule.checkLeadUnits(institutionalProposal.getProjectPersons()));
    }
}

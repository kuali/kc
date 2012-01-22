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
import org.kuali.kra.bo.KcPersonFixtureFactory;
import org.kuali.kra.bo.NonOrganizationalRolodex;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

/**
 * This class tests AddAwardProjectPersonRuleImpl
 */
public class InstitutionalProposalProjectPersonAddRuleImplTest {

    private static final int ROLODEX_ID = 1002;
    private InstitutionalProposal institutionalProposal;
    private InstitutionalProposalProjectPersonAddRuleImpl rule;
    private KcPerson person1;
    private static final String PERSON_ID = "1001";

    @Before
    public void setUp() {
        rule = new InstitutionalProposalProjectPersonAddRuleImpl();
        institutionalProposal = new InstitutionalProposal();

        person1 = KcPersonFixtureFactory.createKcPerson(PERSON_ID);

        NonOrganizationalRolodex person2 = new NonOrganizationalRolodex();
        person2.setRolodexId(ROLODEX_ID);
        
        institutionalProposal.add(new InstitutionalProposalPerson(person1, ContactRoleFixtureFactory.MOCK_PI));
        institutionalProposal.add(new InstitutionalProposalPerson(person2, ContactRoleFixtureFactory.MOCK_COI));
        
        
        GlobalVariables.setMessageMap(new MessageMap());
    }

    @After
    public void tearDown() {
        rule = null;
        institutionalProposal = null;
    }

    @Test
    public void testCheckForExistingPI_DuplicateFound() {
        InstitutionalProposalPerson newPerson = new InstitutionalProposalPerson(new KcPerson(), ContactRoleFixtureFactory.MOCK_PI);
        Assert.assertFalse("Duplicate PI not identified", rule.checkForExistingPrincipalInvestigators(institutionalProposal, newPerson));
    }

    @Test
    public void testCheckForExistingPI_NoDuplicateFound() {
        InstitutionalProposalPerson newPerson = new InstitutionalProposalPerson(new KcPerson(), ContactRoleFixtureFactory.MOCK_KEY_PERSON);
        Assert.assertTrue("Duplicate PI misidentified", rule.checkForExistingPrincipalInvestigators(institutionalProposal, newPerson));
    }

    @Test
    public void testCheckForDuplicateContact_DuplicatePersonFound() {
        KcPerson duplicatePerson = KcPersonFixtureFactory.createKcPerson(PERSON_ID);
        duplicatePerson.setPersonId(person1.getPersonId());
        InstitutionalProposalPerson newPerson = new InstitutionalProposalPerson(duplicatePerson, ContactRoleFixtureFactory.MOCK_COI);
        Assert.assertFalse("Duplicate Person not identified", rule.checkForDuplicatePerson(institutionalProposal, newPerson));
    }


    @Test
    public void testCheckForDuplicateContact_DuplicateRolodexFound() {
        NonOrganizationalRolodex duplicatePerson = new NonOrganizationalRolodex ();
        duplicatePerson.setRolodexId(ROLODEX_ID);
        InstitutionalProposalPerson newPerson = new InstitutionalProposalPerson(duplicatePerson, ContactRoleFixtureFactory.MOCK_COI);
        Assert.assertFalse("Duplicate Rolodex not identified", rule.checkForDuplicatePerson(institutionalProposal, newPerson));
    }
}

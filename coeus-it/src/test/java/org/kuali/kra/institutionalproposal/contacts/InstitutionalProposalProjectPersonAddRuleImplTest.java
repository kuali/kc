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
package org.kuali.kra.institutionalproposal.contacts;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.rolodex.NonOrganizationalRolodex;
import org.kuali.kra.award.contacts.ContactRoleFixtureFactory;
import org.kuali.kra.bo.KcPersonFixtureFactory;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

/**
 * This class tests AddAwardProjectPersonRuleImpl
 */
public class InstitutionalProposalProjectPersonAddRuleImplTest  extends KcIntegrationTestBase {

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

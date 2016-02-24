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
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.kra.award.contacts.AwardProjectPersonsSaveRule;
import org.kuali.kra.award.contacts.ContactRoleFixtureFactory;
import org.kuali.kra.bo.KcPersonFixtureFactory;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.util.List;

/**
 * This class tests AddAwardProjectPersonRuleImpl
 */
public class InstitutionalProposalProjectPersonsSaveRuleImplTest extends KcIntegrationTestBase {

    private static final String MISSING_UNIT_DETAILS_NOT_IDENTIFIED = "Missing unit Details not identified";
    private static final int ROLODEX_ID = 1002;
    private InstitutionalProposal institutionalProposal;
    private InstitutionalProposalPersonSaveRuleImpl rule;
    private Unit unitA;
    private Unit unitB;
    private static final String PERSON_ID = "1001";
    private static final String KP_PERSON_ID = "1002";
	protected static final String SPONSOR_CODE = "000500";

    private InstitutionalProposalPerson piPerson;
    private InstitutionalProposalPerson coiPerson;
    private InstitutionalProposalPerson kpPerson;

    @Before
    public void setUp() {
        rule = new InstitutionalProposalPersonSaveRuleImpl();
        institutionalProposal = new InstitutionalProposal();
        institutionalProposal.setSponsorCode(SPONSOR_CODE);

        unitA = new Unit();
        unitA.setUnitName("a");
        unitA.setUnitNumber("1");

        unitB = new Unit();
        unitB.setUnitName("b");
        unitB.setUnitNumber("2");

        KcPerson employee = KcPersonFixtureFactory.createKcPerson(PERSON_ID);
        piPerson = new InstitutionalProposalPerson(employee, ContactRoleFixtureFactory.MOCK_PI);
        piPerson.add(new InstitutionalProposalPersonUnit(piPerson, unitA, true));

        NonOrganizationalRolodex nonEmployee;
        nonEmployee = new NonOrganizationalRolodex();
        nonEmployee.setRolodexId(ROLODEX_ID);
        coiPerson = new InstitutionalProposalPerson(nonEmployee, ContactRoleFixtureFactory.MOCK_COI);
        coiPerson.add(new InstitutionalProposalPersonUnit(coiPerson, unitA, false));

        KcPerson employee2 = KcPersonFixtureFactory.createKcPerson(KP_PERSON_ID);
        kpPerson = new InstitutionalProposalPerson(employee2, ContactRoleFixtureFactory.MOCK_KEY_PERSON);
        kpPerson.setKeyPersonRole("Tester");                
        kpPerson.add(new InstitutionalProposalPersonUnit(kpPerson, unitA, false));

        piPerson.setInstitutionalProposal(institutionalProposal);
        coiPerson.setInstitutionalProposal(institutionalProposal);
        kpPerson.setInstitutionalProposal(institutionalProposal);

        institutionalProposal.add(piPerson);
        institutionalProposal.add(coiPerson);
        institutionalProposal.add(kpPerson);
        
        GlobalVariables.setMessageMap(new MessageMap());
    }
    
    @After
    public void tearDown() {
        rule = null;
        institutionalProposal = null;
    }
    
    @Test
    public void testCheckForExistingPI() {
        Assert.assertTrue("PI not found or more than one found", rule.checkForOnePrincipalInvestigator(institutionalProposal.getProjectPersons()));
        institutionalProposal.getProjectPersons().remove(0);
        Assert.assertFalse("PI existence check failed", rule.checkForOnePrincipalInvestigator(institutionalProposal.getProjectPersons()));

        checkErrorState(InstitutionalProposalPersonSaveRule.PROPOSAL_PROJECT_PERSON_LIST_ERROR_KEY, InstitutionalProposalPersonSaveRule.ERROR_PROPOSAL_PROJECT_PERSON_NO_PI);
    }

    @Test
    public void testCheckForMultiplePIs() {
        InstitutionalProposalPerson newPerson = new InstitutionalProposalPerson(KcPersonFixtureFactory.createKcPerson(KP_PERSON_ID), ContactRoleFixtureFactory.MOCK_PI);
        institutionalProposal.add(newPerson);
        Assert.assertFalse("Multiple PIs not detected", rule.checkForOnePrincipalInvestigator(institutionalProposal.getProjectPersons()));

        checkErrorState(AwardProjectPersonsSaveRule.AWARD_PROJECT_PERSON_LIST_ERROR_KEY,
                                AwardProjectPersonsSaveRule.ERROR_AWARD_PROJECT_PERSON_MULTIPLE_PI_EXISTS);
    }

    @Test
    public void testCheckForRequiredUnitDetails_PI() {
        Assert.assertTrue(MISSING_UNIT_DETAILS_NOT_IDENTIFIED, rule.checkForRequiredUnitDetails(institutionalProposal.getProjectPersons()));

        piPerson.getUnits().clear();
        Assert.assertFalse(MISSING_UNIT_DETAILS_NOT_IDENTIFIED, rule.checkForRequiredUnitDetails(institutionalProposal.getProjectPersons()));

        checkErrorState(InstitutionalProposalPersonSaveRule.PROPOSAL_PROJECT_PERSON_LIST_ERROR_KEY,
                InstitutionalProposalPersonSaveRule.ERROR_PROPOSAL_PROJECT_PERSON_UNIT_DETAILS_REQUIRED);
    }

    @Test
    public void testCheckForRequiredUnitDetails_COI() {
        Assert.assertTrue(MISSING_UNIT_DETAILS_NOT_IDENTIFIED, rule.checkForRequiredUnitDetails(institutionalProposal.getProjectPersons()));

        coiPerson.getUnits().clear();
        Assert.assertFalse(MISSING_UNIT_DETAILS_NOT_IDENTIFIED, rule.checkForRequiredUnitDetails(institutionalProposal.getProjectPersons()));

        checkErrorState(InstitutionalProposalPersonSaveRule.PROPOSAL_PROJECT_PERSON_LIST_ERROR_KEY,
                InstitutionalProposalPersonSaveRule.ERROR_PROPOSAL_PROJECT_PERSON_UNIT_DETAILS_REQUIRED);
    }

    @Test
    public void testCheckForUnitDetailsNotRequired_KP() {
        Assert.assertTrue(MISSING_UNIT_DETAILS_NOT_IDENTIFIED, rule.checkForRequiredUnitDetails(institutionalProposal.getProjectPersons()));

        kpPerson.getUnits().clear();
        Assert.assertTrue(MISSING_UNIT_DETAILS_NOT_IDENTIFIED, rule.checkForRequiredUnitDetails(institutionalProposal.getProjectPersons()));
        Assert.assertEquals(0, GlobalVariables.getMessageMap().getErrorCount());
    }
    
    @Test
    public void testCheckForDuplicateUnits_NoneFound() {
        piPerson.add(new InstitutionalProposalPersonUnit(piPerson, unitB, false));
        Assert.assertTrue(rule.checkForDuplicateUnits(institutionalProposal.getProjectPersons()));
    }

    @Test
    public void testCheckForDuplicateUnits_DupeFound() {
        piPerson.add(new InstitutionalProposalPersonUnit(piPerson, unitA, false));
        Assert.assertFalse("Duplicate should have been found", rule.checkForDuplicateUnits(institutionalProposal.getProjectPersons()));

        checkErrorState(InstitutionalProposalPersonSaveRule.PROPOSAL_PROJECT_PERSON_LIST_ERROR_KEY,
                InstitutionalProposalPersonSaveRule.ERROR_PROPOSAL_PROJECT_PERSON_DUPLICATE_UNITS);
    }

    @Test
    public void testCheckForLeadUnit_NoneFound() {
        Assert.assertTrue("No lead unit was found", rule.checkForLeadUnitForPI(institutionalProposal.getProjectPersons()));

        piPerson.getUnit(0).setLeadUnit(false);
        Assert.assertFalse("No lead unit should have been found", rule.checkForLeadUnitForPI(institutionalProposal.getProjectPersons()));

        checkErrorState(InstitutionalProposalPersonSaveRule.PROPOSAL_PROJECT_PERSON_LIST_ERROR_KEY,
                InstitutionalProposalPersonSaveRule.ERROR_PROPOSAL_PROJECT_PERSON_LEAD_UNIT_REQUIRED);
    }

    @Test
    public void testCheckForKeyPersonRole_NotFound() {
        kpPerson.setKeyPersonRole(null);
        Assert.assertFalse("Key Person Role not checked for", rule.checkForKeyPersonProjectRoles(institutionalProposal.getProjectPersons()));

        checkErrorState(InstitutionalProposalPersonSaveRule.PROPOSAL_PROJECT_PERSON_LIST_ERROR_KEY + "[" + institutionalProposal.getProjectPersons().indexOf(kpPerson) + "].keyPersonRole",
                InstitutionalProposalPersonSaveRule.ERROR_PROPOSAL_PROJECT_KEY_PERSON_ROLE_REQUIRED);
    }

    @Test
    public void testCheckForKeyPersonRole_Found() {
        Assert.assertTrue("Key Person Role not checked for", rule.checkForKeyPersonProjectRoles(institutionalProposal.getProjectPersons()));
    }

    private void checkErrorState(String errorProperty, String errorMessageKey) {
        MessageMap messageMap = GlobalVariables.getMessageMap();
        Assert.assertEquals(1, messageMap.getErrorCount());
        @SuppressWarnings("unchecked") List<ErrorMessage> errors = messageMap.getErrorMessagesForProperty(errorProperty);
        if(errors != null) {
            Assert.assertEquals(1, errors.size());
            Assert.assertEquals(errorMessageKey, errors.get(0).getErrorKey());
        } else {
            Assert.fail("No errors posted");
        }

        Assert.assertFalse("rule should return false", rule.processSaveInstitutionalProposalProjectPersonsBusinessRules(institutionalProposal.getProjectPersons()));
    }
}

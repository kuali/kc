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

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.contacts.AwardProjectPersonsSaveRule;
import org.kuali.kra.award.contacts.ContactRoleFixtureFactory;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.KcPersonFixtureFactory;
import org.kuali.kra.bo.NonOrganizationalRolodex;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

/**
 * This class tests AddAwardProjectPersonRuleImpl
 */
public class InstitutionalProposalProjectPersonsSaveRuleImplTest {

    private static final String MISSING_UNIT_DETAILS_NOT_IDENTIFIED = "Missing unit Details not identified";
    private static final int ROLODEX_ID = 1002;
    private InstitutionalProposal institutionalProposal;
    private InstitutionalProposalPersonSaveRuleImpl rule;
    private Unit unitA;
    private Unit unitB;
    private static final String PERSON_ID = "1001";
    private static final String KP_PERSON_ID = "1002";

    private InstitutionalProposalPerson piPerson;
    private InstitutionalProposalPerson coiPerson;
    private InstitutionalProposalPerson kpPerson;

    @Before
    public void setUp() {
        rule = new InstitutionalProposalPersonSaveRuleImpl();
        institutionalProposal = new InstitutionalProposal();

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

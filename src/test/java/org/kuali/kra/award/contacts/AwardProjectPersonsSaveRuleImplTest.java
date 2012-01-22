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

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.KcPersonFixtureFactory;
import org.kuali.kra.bo.NonOrganizationalRolodex;
import org.kuali.kra.bo.Unit;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

/**
 * This class tests AddAwardProjectPersonRuleImpl
 */
public class AwardProjectPersonsSaveRuleImplTest {

    private static final String MISSING_UNIT_DETAILS_NOT_IDENTIFIED = "Missing unit Details not identified";
    private static final int ROLODEX_ID = 1002;
    private Award award;
    private AwardProjectPersonsSaveRuleImpl rule;
    private Unit unitA;
    private Unit unitB;
    private static final String PERSON_ID = "1001";
    private static final String KP_PERSON_ID = "1002";

    private AwardPerson piPerson;
    private AwardPerson coiPerson;
    private AwardPerson kpPerson;

    @Before
    public void setUp() {
        rule = new AwardProjectPersonsSaveRuleImpl();
        award = new Award();

        unitA = new Unit();
        unitA.setUnitName("a");
        unitA.setUnitNumber("1");

        unitB = new Unit();
        unitB.setUnitName("b");
        unitB.setUnitNumber("2");

        KcPerson employee = KcPersonFixtureFactory.createKcPerson(PERSON_ID);
        piPerson = new AwardPerson(employee, ContactRoleFixtureFactory.MOCK_PI);
        piPerson.add(new AwardPersonUnit(piPerson, unitA, true));

        NonOrganizationalRolodex nonEmployee;
        nonEmployee = new NonOrganizationalRolodex();
        nonEmployee.setRolodexId(ROLODEX_ID);
        coiPerson = new AwardPerson(nonEmployee, ContactRoleFixtureFactory.MOCK_COI);
        coiPerson.add(new AwardPersonUnit(coiPerson, unitA, false));

        KcPerson employee2 = KcPersonFixtureFactory.createKcPerson(KP_PERSON_ID);
        kpPerson = new AwardPerson(employee2, ContactRoleFixtureFactory.MOCK_KEY_PERSON);
        kpPerson.setKeyPersonRole("Tester");                
        kpPerson.add(new AwardPersonUnit(kpPerson, unitA, false));

        award.add(piPerson);
        award.add(coiPerson);
        award.add(kpPerson);
        
        GlobalVariables.setMessageMap(new MessageMap());
    }
    
    @After
    public void tearDown() {
        rule = null;
        award = null;
    }
    
    @Test
    public void testCheckForExistingPI() {
        Assert.assertTrue("PI not found or more than one found", rule.checkForOnePrincipalInvestigator(award.getProjectPersons()));
        award.getProjectPersons().remove(0);
        Assert.assertFalse("PI existence check failed", rule.checkForOnePrincipalInvestigator(award.getProjectPersons()));

        checkErrorState(AwardProjectPersonsSaveRule.AWARD_PROJECT_PERSON_LIST_ERROR_KEY, AwardProjectPersonsSaveRule.ERROR_AWARD_PROJECT_PERSON_NO_PI);
    }

    @Test
    public void testCheckForMultiplePIs() {
        AwardPerson newPerson = new AwardPerson(KcPersonFixtureFactory.createKcPerson(KP_PERSON_ID), ContactRoleFixtureFactory.MOCK_PI);
        award.add(newPerson);
        Assert.assertFalse("Multiple PIs not detected", rule.checkForOnePrincipalInvestigator(award.getProjectPersons()));

        checkErrorState(AwardProjectPersonsSaveRule.AWARD_PROJECT_PERSON_LIST_ERROR_KEY,
                                AwardProjectPersonsSaveRule.ERROR_AWARD_PROJECT_PERSON_MULTIPLE_PI_EXISTS);
    }

    @Test
    public void testCheckForRequiredUnitDetails_PI() {
        Assert.assertTrue(MISSING_UNIT_DETAILS_NOT_IDENTIFIED, rule.checkForRequiredUnitDetails(award.getProjectPersons()));

        piPerson.getUnits().clear();
        Assert.assertFalse(MISSING_UNIT_DETAILS_NOT_IDENTIFIED, rule.checkForRequiredUnitDetails(award.getProjectPersons()));

        checkErrorState(AwardProjectPersonsSaveRule.AWARD_PROJECT_PERSON_LIST_ERROR_KEY,
                                AwardProjectPersonsSaveRule.ERROR_AWARD_PROJECT_PERSON_UNIT_DETAILS_REQUIRED);
    }

    @Test
    public void testCheckForRequiredUnitDetails_COI() {
        Assert.assertTrue(MISSING_UNIT_DETAILS_NOT_IDENTIFIED, rule.checkForRequiredUnitDetails(award.getProjectPersons()));

        coiPerson.getUnits().clear();
        Assert.assertFalse(MISSING_UNIT_DETAILS_NOT_IDENTIFIED, rule.checkForRequiredUnitDetails(award.getProjectPersons()));

        checkErrorState(AwardProjectPersonsSaveRule.AWARD_PROJECT_PERSON_LIST_ERROR_KEY,
                                AwardProjectPersonsSaveRule.ERROR_AWARD_PROJECT_PERSON_UNIT_DETAILS_REQUIRED);
    }

    @Test
    public void testCheckForUnitDetailsNotRequired_KP() {
        Assert.assertTrue(MISSING_UNIT_DETAILS_NOT_IDENTIFIED, rule.checkForRequiredUnitDetails(award.getProjectPersons()));

        kpPerson.getUnits().clear();
        Assert.assertTrue(MISSING_UNIT_DETAILS_NOT_IDENTIFIED, rule.checkForRequiredUnitDetails(award.getProjectPersons()));
        Assert.assertEquals(0, GlobalVariables.getMessageMap().getErrorCount());
    }
    
    @Test
    public void testCheckForDuplicateUnits_NoneFound() {
        piPerson.add(new AwardPersonUnit(piPerson, unitB, false));
        Assert.assertTrue(rule.checkForDuplicateUnits(award.getProjectPersons()));
    }

    @Test
    public void testCheckForDuplicateUnits_DupeFound() {
        piPerson.add(new AwardPersonUnit(piPerson, unitA, false));
        Assert.assertFalse("Duplicate should have been found", rule.checkForDuplicateUnits(award.getProjectPersons()));

        checkErrorState(AwardProjectPersonsSaveRule.AWARD_PROJECT_PERSON_LIST_ERROR_KEY,
                                AwardProjectPersonsSaveRule.ERROR_AWARD_PROJECT_PERSON_DUPLICATE_UNITS);
    }

    @Test
    public void testCheckForLeadUnit_NoneFound() {
        Assert.assertTrue("No lead unit was found", rule.checkForLeadUnitForPI(award.getProjectPersons()));

        piPerson.getUnit(0).setLeadUnit(false);
        Assert.assertFalse("No lead unit should have been found", rule.checkForLeadUnitForPI(award.getProjectPersons()));

        checkErrorState(AwardProjectPersonsSaveRule.AWARD_PROJECT_PERSON_LIST_ERROR_KEY,
                                AwardProjectPersonsSaveRule.ERROR_AWARD_PROJECT_PERSON_LEAD_UNIT_REQUIRED);
    }

    @Test
    public void testCheckForKeyPersonRole_NotFound() {
        kpPerson.setKeyPersonRole(null);
        Assert.assertFalse("Key Person Role not checked for", rule.checkForKeyPersonProjectRoles(award.getProjectPersons()));

        checkErrorState(AwardProjectPersonsSaveRule.AWARD_PROJECT_PERSON_LIST_ERROR_KEY,
                                AwardProjectPersonsSaveRule.ERROR_AWARD_PROJECT_KEY_PERSON_ROLE_REQUIRED);
    }

    @Test
    public void testCheckForKeyPersonRole_Found() {
        Assert.assertTrue("Key Person Role not checked for", rule.checkForKeyPersonProjectRoles(award.getProjectPersons()));
    }
    
    @Test
    public void testProjectRolesChanges() {
        // when a coi is changed to key person
        coiPerson.setContactRole(ContactRoleFixtureFactory.MOCK_KEY_PERSON);
        Assert.assertFalse("Key Person Role not checked for", rule.checkForKeyPersonProjectRoles(award.getProjectPersons()));
        coiPerson.setKeyPersonRole("fromCOI");
        Assert.assertTrue("Key Person Role not checked for", rule.checkForKeyPersonProjectRoles(award.getProjectPersons()));
        
        // when a key person is changed to coi
        kpPerson.setContactRole(ContactRoleFixtureFactory.MOCK_COI);
        Assert.assertTrue("rule should return true", rule.processSaveAwardProjectPersonsBusinessRules(award.getProjectPersons()));
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

        Assert.assertFalse("rule should return false", rule.processSaveAwardProjectPersonsBusinessRules(award.getProjectPersons()));
    }
}

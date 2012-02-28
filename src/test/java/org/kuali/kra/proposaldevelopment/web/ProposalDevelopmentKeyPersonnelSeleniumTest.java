/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

/**
 * Tests the Key Personnel page of a Development Proposal.
 */
public class ProposalDevelopmentKeyPersonnelSeleniumTest extends KcSeleniumTestBase {
    
    private static final String NEW_PERSON_PREFIX = "newProposalPerson.";
    private static final String NEW_PERSON_DEGREE_PREFIX = "newProposalPersonDegree[0].";
    private static final String NEW_PERSON_UNIT_PREFIX = "newProposalPersonUnit[0].";
    private static final String NEW_USER_PREFIX = "newProposalUser.";
    private static final String LIST_PERSON_PREFIX = "document.developmentProposalList[0].proposalPersons[%d].";
    
    private static final String PERSON_ID_TAG = "newPersonId";
    private static final String ROLODEX_ID_TAG = "newRolodexId";
    private static final String PERSON_ID_ID = "personId";
    private static final String ROLODEX_ID_ID = "rolodexId";
    private static final String PERSON_ROLE_ID_ID = "proposalPersonRoleId";
    private static final String PROJECT_ROLE_ID = "projectRole";
    private static final String DEGREE_CODE_ID = "degreeCode";
    private static final String DEGREE_ID = "degree";
    private static final String GRADUATION_YEAR_ID = "graduationYear";
    private static final String SCHOOL_ID = "school";
    private static final String UNIT_NUMBER_ID = "unitNumber";
    private static final String USERNAME_ID = "username";
    private static final String ROLE_NAME_ID = "roleName";
    private static final String NEW_PERSON_ROLE_ID_ID = NEW_PERSON_PREFIX + PERSON_ROLE_ID_ID;
    private static final String NEW_PROJECT_ROLE_ID = NEW_PERSON_PREFIX + PROJECT_ROLE_ID;
    private static final String NEW_DEGREE_CODE_ID = NEW_PERSON_DEGREE_PREFIX + DEGREE_CODE_ID;
    private static final String NEW_DEGREE_ID = NEW_PERSON_DEGREE_PREFIX + DEGREE_ID;
    private static final String NEW_GRADUATION_YEAR_ID = NEW_PERSON_DEGREE_PREFIX + GRADUATION_YEAR_ID;
    private static final String NEW_SCHOOL_ID = NEW_PERSON_DEGREE_PREFIX + SCHOOL_ID;
    private static final String NEW_UNIT_NUMBER_ID = NEW_PERSON_UNIT_PREFIX + UNIT_NUMBER_ID;
    private static final String NEW_USERNAME_ID = NEW_USER_PREFIX + USERNAME_ID;
    private static final String NEW_ROLE_NAME_ID = NEW_USER_PREFIX + ROLE_NAME_ID;
    private static final String LIST_PERSON_ROLE_ID_ID = LIST_PERSON_PREFIX + PERSON_ROLE_ID_ID;
    
    private static final String SPONSOR_CODE_ID = "document.developmentProposalList[0].sponsorCode";
    
    private static final String DEGREE_ROW_TAG = "tbody[id='G4'] tr:nth-child(3) td";
    private static final String UNIT_ROW_TAG = "tbody[id='G3'] tr td";
    
    private static final String PERSON_ID = "10000000004";
    private static final String ROLODEX_ID = "1727";
    
    private static final String EMPLOYEE_FULL_NAME = "Nicholas Majors";
    private static final String ROLODEX_FULL_NAME = "Pauline Ho";
    private static final String PRIMARY_INVESTIGATOR_CONTACT_ROLE = "Principal Investigator";
    private static final String CO_INVESTIGATOR_CONTACT_ROLE = "Co-Investigator";
    private static final String KEY_PERSON_CONTACT_ROLE = "Key Person";
    private static final String KEY_PERSON_PROJECT_ROLE = "Tester";
    private static final String DEGREE_CODE_NAME = "Associate in Science";
    private static final String DEGREE = "Sega";
    private static final String GRADUATION_YEAR = "2000";
    private static final String SCHOOL = "Monroe";
    private static final String OPTION_UNIT_DETAILS = "You have the option to add unit details for a key person";
    private static final String UNIT_NAME_IU_UNIV = "IU-UNIV";
    private static final String UNIT_NAME_000001 = "000001";
    private static final String OPTION_CERTIFICATION_QUESTIONS = "You have the option to add Certification Questions for a key person";
    private static final String PROPOSAL_PERSON_CERTIFICATION_QUESTION = "Have lobbying activities been conducted on behalf of this proposal?";
    private static final String COMBINED_CREDIT_SPLIT = "Combined Credit Split";
    private static final String NIH_HIERARCHY_SPONSOR_CODE = "000340";
    private static final String NON_NIH_HIERARCHY_SPONSOR_CODE = "000500";
    private static final String USERNAME = "tdurkin";
    private static final String ROLE_NAME = "Viewer";
    private static final String PRINCIPAL_INVESTIGATOR_CONTACT_ROLE_NIH = "PI/Contact";
    private static final String PRINCIPAL_INVESTIGATOR_CONTACT_ROLE_NON_NIH = "Principal Investigator";
    
    private static final String INFO_PI_CONTACT_ROLE = "PI/Contact is a required Proposal Role";
    
    private static final String INSERT_PROPOSAL_PERSON_BUTTON = "methodToCall.insertProposalPerson";
    private static final String CLEAR_PROPOSAL_PERSON_BUTTON = "methodToCall.clearProposalPerson";
    private static final String INSERT_DEGREE_BUTTON = "methodToCall.insertDegree";
    private static final String DELETE_DEGREE_BUTTON = "methodToCall.deleteDegree." + LIST_PERSON_PREFIX + "line%d";
    private static final String INSERT_UNIT_BUTTON = "methodToCall.insertUnit";
    private static final String DELETE_UNIT_BUTTON = "methodToCall.deleteUnit." + LIST_PERSON_PREFIX + "line%d";
    private static final String INSERT_UNIT_DETAILS_BUTTON = "methodToCall.addUnitDetails";
    private static final String DELETE_UNIT_DETAILS_BUTTON = "methodToCall.removeUnitDetails." + LIST_PERSON_PREFIX + "line%d";
    private static final String INSERT_CERTIFICATION_QUESTION_BUTTON = "methodToCall.addCertificationQuestion";
    private static final String DELETE_CERTIFICATION_QUESTION_BUTTON = "methodToCall.removeCertificationQuestion";
    private static final String INSERT_USER_BUTTON = "methodToCall.addProposalUser";
    
    private ProposalDevelopmentSeleniumHelper helper;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        helper = ProposalDevelopmentSeleniumHelper.instance(driver);
        
        helper.loginBackdoor();
    }
    
    @After
    public void tearDown() throws Exception {
        helper.loginBackdoor();
        
        helper = null;
        
        super.tearDown();
    }

    /**
     * Test adding an employee principal investigator.
     * 
     * @throws Exception
     */
    @Test
    public void testAddEmployeePrincipalInvestigator() throws Exception {
        helper.createProposalDevelopment();
        helper.clickProposalDevelopmentKeyPersonnelPage();
        
        helper.lookup(PERSON_ID_TAG, PERSON_ID_ID, PERSON_ID);
        helper.assertPageContains(EMPLOYEE_FULL_NAME);
        helper.set(NEW_PERSON_ROLE_ID_ID, PRIMARY_INVESTIGATOR_CONTACT_ROLE);
        helper.click(INSERT_PROPOSAL_PERSON_BUTTON);

        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertPageContains(EMPLOYEE_FULL_NAME);
    }
    
    /**
     * Test adding a rolodex principal investigator.
     * 
     * @throws Exception
     */
    @Test
    public void testAddRolodexPrincipalInvestigator() throws Exception {
        helper.createProposalDevelopment();
        helper.clickProposalDevelopmentKeyPersonnelPage();
        
        helper.lookup(ROLODEX_ID_TAG, ROLODEX_ID_ID, ROLODEX_ID);
        helper.assertPageContains(ROLODEX_FULL_NAME);
        helper.set(NEW_PERSON_ROLE_ID_ID, PRIMARY_INVESTIGATOR_CONTACT_ROLE);
        helper.click(INSERT_PROPOSAL_PERSON_BUTTON);

        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertPageContains(ROLODEX_FULL_NAME);
    }

    /**
     * Test clearing a principal investigator.
     * 
     * @throws Exception
     */
    @Test
    public void testClearPrincipalInvestigator() throws Exception {
        helper.createProposalDevelopment();
        helper.clickProposalDevelopmentKeyPersonnelPage();
        
        helper.lookup(PERSON_ID_TAG, PERSON_ID_ID, PERSON_ID);
        helper.assertPageContains(EMPLOYEE_FULL_NAME);
        helper.set(NEW_PERSON_ROLE_ID_ID, PRIMARY_INVESTIGATOR_CONTACT_ROLE);
        helper.click(CLEAR_PROPOSAL_PERSON_BUTTON);

        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertPageDoesNotContain(EMPLOYEE_FULL_NAME);
    }
    
    /**
     * Test changing the role from principal investigator to co-investigator.
     * 
     * @throws Exception
     */
    @Test
    public void testChangeRole() throws Exception {
        helper.createProposalDevelopment();
        helper.clickProposalDevelopmentKeyPersonnelPage();
        
        helper.lookup(PERSON_ID_TAG, PERSON_ID_ID, PERSON_ID);
        helper.set(NEW_PERSON_ROLE_ID_ID, PRIMARY_INVESTIGATOR_CONTACT_ROLE);
        helper.assertPageContains(PRIMARY_INVESTIGATOR_CONTACT_ROLE);
        helper.click(INSERT_PROPOSAL_PERSON_BUTTON);

        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.set(String.format(LIST_PERSON_ROLE_ID_ID, 0), CO_INVESTIGATOR_CONTACT_ROLE);
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertPageContains(CO_INVESTIGATOR_CONTACT_ROLE);
    }
    
    /**
     * Test adding a key person
     */
    @Test
    public void testAddKeyPerson() throws Exception {
        helper.createProposalDevelopment();
        helper.clickProposalDevelopmentKeyPersonnelPage();
        
        helper.lookup(PERSON_ID_TAG, PERSON_ID_ID, PERSON_ID);
        helper.set(NEW_PERSON_ROLE_ID_ID, KEY_PERSON_CONTACT_ROLE);
        helper.set(NEW_PROJECT_ROLE_ID, KEY_PERSON_PROJECT_ROLE);
        helper.assertPageContains(KEY_PERSON_CONTACT_ROLE);
        helper.click(INSERT_PROPOSAL_PERSON_BUTTON);

        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertPageContains(OPTION_UNIT_DETAILS);
        helper.assertPageContains(OPTION_CERTIFICATION_QUESTIONS);
    }

    /**
     * Test adding and removing a degree.
     * 
     * @throws Exception
     */
    @Test
    public void testAddRemoveDegree() throws Exception {
        helper.createProposalDevelopment();
        helper.clickProposalDevelopmentKeyPersonnelPage();
        
        helper.lookup(PERSON_ID_TAG, PERSON_ID_ID, PERSON_ID);
        helper.set(NEW_PERSON_ROLE_ID_ID, PRIMARY_INVESTIGATOR_CONTACT_ROLE);
        helper.click(INSERT_PROPOSAL_PERSON_BUTTON);
        
        helper.saveDocument();
        helper.assertNoPageErrors();

        helper.openTab(0);
        
        helper.openTab(3);
        helper.set(NEW_DEGREE_CODE_ID, DEGREE_CODE_NAME);
        helper.set(NEW_DEGREE_ID, DEGREE);
        helper.set(NEW_GRADUATION_YEAR_ID, GRADUATION_YEAR);
        helper.set(NEW_SCHOOL_ID, SCHOOL);
        helper.assertElementContains(NEW_DEGREE_CODE_ID, DEGREE_CODE_NAME);
        helper.assertElementContains(NEW_DEGREE_ID, DEGREE);
        helper.assertElementContains(NEW_GRADUATION_YEAR_ID, GRADUATION_YEAR);
        helper.assertElementContains(NEW_SCHOOL_ID, SCHOOL);
        helper.click(INSERT_DEGREE_BUTTON);
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertSelectorContains(DEGREE_ROW_TAG, DEGREE_CODE_NAME);
        helper.assertSelectorContains(DEGREE_ROW_TAG, DEGREE);
        helper.assertSelectorContains(DEGREE_ROW_TAG, GRADUATION_YEAR);
        helper.assertSelectorContains(DEGREE_ROW_TAG, SCHOOL);
        
        helper.click(String.format(DELETE_DEGREE_BUTTON, 0, 0));
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertSelectorDoesNotContain(DEGREE_ROW_TAG, DEGREE_CODE_NAME);
        helper.assertSelectorDoesNotContain(DEGREE_ROW_TAG, DEGREE);
        helper.assertSelectorDoesNotContain(DEGREE_ROW_TAG, GRADUATION_YEAR);
        helper.assertSelectorDoesNotContain(DEGREE_ROW_TAG, SCHOOL);
    }
    
    /**
     * Test adding and removing a unit.
     */
    @Test
    public void testAddRemoveUnit() throws Exception {
        helper.createProposalDevelopment();
        helper.clickProposalDevelopmentKeyPersonnelPage();
        
        helper.lookup(PERSON_ID_TAG, PERSON_ID_ID, PERSON_ID);
        helper.set(NEW_PERSON_ROLE_ID_ID, PRIMARY_INVESTIGATOR_CONTACT_ROLE);
        helper.click(INSERT_PROPOSAL_PERSON_BUTTON);
        
        helper.saveDocument();
        helper.assertNoPageErrors();

        helper.openTab(0);
        
        helper.openTab(4);
        helper.set(NEW_UNIT_NUMBER_ID, UNIT_NAME_IU_UNIV);
        helper.assertElementContains(NEW_UNIT_NUMBER_ID, UNIT_NAME_IU_UNIV);
        helper.click(INSERT_UNIT_BUTTON);
        
        helper.saveDocument();
        helper.assertNoPageErrors();

        helper.assertPageContains(UNIT_NAME_IU_UNIV);

        helper.click(String.format(DELETE_UNIT_BUTTON, 0, 1));
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertPageDoesNotContain(UNIT_NAME_IU_UNIV);
    }
    
    /**
     * Test adding and removing unit details for a key person.
     * 
     * @throws Exception
     */
    @Test
    public void testAddRemoveUnitDetailsKeyPerson() throws Exception {
        helper.createProposalDevelopment();
        helper.clickProposalDevelopmentKeyPersonnelPage();
        
        helper.lookup(PERSON_ID_TAG, PERSON_ID_ID, PERSON_ID);
        helper.set(NEW_PERSON_ROLE_ID_ID, KEY_PERSON_CONTACT_ROLE);
        helper.set(NEW_PROJECT_ROLE_ID, KEY_PERSON_PROJECT_ROLE);
        helper.click(INSERT_PROPOSAL_PERSON_BUTTON);

        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.openTab(0);
        
        helper.openTab(4);
        helper.click(INSERT_UNIT_DETAILS_BUTTON);

        helper.assertSelectorContains(UNIT_ROW_TAG, UNIT_NAME_000001);
        helper.assertPageContains(COMBINED_CREDIT_SPLIT);
        
        helper.click(String.format(DELETE_UNIT_DETAILS_BUTTON, 0, 0));
        
        helper.assertSelectorDoesNotContain(UNIT_ROW_TAG, UNIT_NAME_000001);
        helper.assertPageDoesNotContain(COMBINED_CREDIT_SPLIT);
    }

    /**
     * Test adding and removing certification questions for a key person.
     * 
     * @throws Exception
     */
    @Test
    public void testAddRemoveCertificationQuestionKeyPerson() throws Exception {
        helper.createProposalDevelopment();
        helper.clickProposalDevelopmentKeyPersonnelPage();
        
        helper.lookup(PERSON_ID_TAG, PERSON_ID_ID, PERSON_ID);
        helper.set(NEW_PERSON_ROLE_ID_ID, KEY_PERSON_CONTACT_ROLE);
        helper.set(NEW_PROJECT_ROLE_ID, KEY_PERSON_PROJECT_ROLE);
        helper.assertPageContains(KEY_PERSON_CONTACT_ROLE);
        helper.click(INSERT_PROPOSAL_PERSON_BUTTON);

        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.openTab(0);
        
        helper.openTab(5);
        helper.click(INSERT_CERTIFICATION_QUESTION_BUTTON);
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertPageContains(PROPOSAL_PERSON_CERTIFICATION_QUESTION);
        
        helper.click(DELETE_CERTIFICATION_QUESTION_BUTTON);
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertPageDoesNotContain(PROPOSAL_PERSON_CERTIFICATION_QUESTION);
    }
    
    /**
     * Test that a proposal viewer should not be able to opt in for the certify questions for a key person.
     * 
     * @throws Exception
     */
    @Test
    public void testNoAddCertificationQuestionKeyPersonForViewer() throws Exception {
        helper.createProposalDevelopment();
        helper.clickProposalDevelopmentKeyPersonnelPage();
        
        helper.lookup(PERSON_ID_TAG, PERSON_ID_ID, PERSON_ID);
        helper.set(NEW_PERSON_ROLE_ID_ID, KEY_PERSON_CONTACT_ROLE);
        helper.set(NEW_PROJECT_ROLE_ID, KEY_PERSON_PROJECT_ROLE);
        helper.assertPageContains(KEY_PERSON_CONTACT_ROLE);
        helper.click(INSERT_PROPOSAL_PERSON_BUTTON);

        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.clickProposalDevelopmentPermissionsPage();
        helper.set(NEW_USERNAME_ID, USERNAME);
        helper.set(NEW_ROLE_NAME_ID, ROLE_NAME);
        helper.click(INSERT_USER_BUTTON);
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        String documentNumber = helper.getDocumentNumber();

        helper.closeDocument();
        helper.loginBackdoor(USERNAME);
        helper.docSearch(documentNumber);
        helper.clickProposalDevelopmentKeyPersonnelPage();
        
        helper.assertPageDoesNotContain(OPTION_UNIT_DETAILS);
        helper.assertPageDoesNotContain(OPTION_CERTIFICATION_QUESTIONS);
        
        helper.closeDocument();
        helper.loginBackdoor();
        helper.docSearch(documentNumber);
        helper.clickProposalDevelopmentKeyPersonnelPage();
        
        helper.assertPageContains(OPTION_UNIT_DETAILS);
        helper.assertPageContains(OPTION_CERTIFICATION_QUESTIONS);
    }
    
    /**
     * Test that a non-NIH sponsor includes the Principal Investigator option and not the PI/Contact option for a role.
     * 
     * @throws Exception
     */
    @Test
    public void testNonNihSponsorPersonnelLabels() throws Exception {
        helper.createProposalDevelopment();
        helper.set(SPONSOR_CODE_ID, NON_NIH_HIERARCHY_SPONSOR_CODE);
        helper.clickProposalDevelopmentKeyPersonnelPage();
        
        helper.assertOptionsContain(NEW_PERSON_ROLE_ID_ID, PRINCIPAL_INVESTIGATOR_CONTACT_ROLE_NON_NIH);
        helper.assertOptionsDoNotContain(NEW_PERSON_ROLE_ID_ID, PRINCIPAL_INVESTIGATOR_CONTACT_ROLE_NIH);
    }
    
    /**
     * Test that an NIH sponsor includes the PI/Contact option and not the Principal Investigator option for a role.
     * 
     * @throws Exception
     */
    @Test
    public void testNihSponsorPersonnelLabels() throws Exception {
        helper.createProposalDevelopment();
        helper.set(SPONSOR_CODE_ID, NIH_HIERARCHY_SPONSOR_CODE);
        helper.clickProposalDevelopmentKeyPersonnelPage();
        
        helper.assertOptionsContain(NEW_PERSON_ROLE_ID_ID, PRINCIPAL_INVESTIGATOR_CONTACT_ROLE_NIH);
        helper.assertOptionsDoNotContain(NEW_PERSON_ROLE_ID_ID, PRINCIPAL_INVESTIGATOR_CONTACT_ROLE_NON_NIH);
        
        helper.assertPageContains(INFO_PI_CONTACT_ROLE);
    }

    /**
     * Verify that the key personnel help link works.
     * 
     * @throws Exception
     */
    @Test
    public void testKeyPersonnelHelpLink() throws Exception {
        helper.createProposalDevelopment();
        helper.clickProposalDevelopmentKeyPersonnelPage();
        
        helper.lookup(PERSON_ID_TAG, PERSON_ID_ID, PERSON_ID);
        helper.set(NEW_PERSON_ROLE_ID_ID, PRIMARY_INVESTIGATOR_CONTACT_ROLE);
        helper.click(INSERT_PROPOSAL_PERSON_BUTTON);
        
        helper.saveDocument();
        helper.assertNoPageErrors();

        helper.openTab(0);
        
        helper.assertHelpLink(KcPerson.class);
    }
    
}
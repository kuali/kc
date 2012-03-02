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
package org.kuali.kra.committee.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

/**
 * 
 * This is the integration test for committee member screen. 
 */
public class CommitteeMembershipSeleniumTest extends KcSeleniumTestBase {
    
    private static final String MEMBERSHIP_ROLE_TABLE_ID = "membership-role-table-0";
    private static final String MEMBERSHIP_EXPERTISE_TABLE_ID = "membership-expertise-table-0";
    
    private static final String PERSON_ID_TAG = "committeeHelper.newCommitteeMembership.personId";
    private static final String ROLODEX_ID_TAG = "committeeHelper.newCommitteeMembership.rolodexId";
    private static final String RESEARCH_AREAS_TAG = "committeeResearchAreas";
    
    private static final String LIST_PREFIX = "document.committeeList[0].";
    private static final String COMMITTEE_MEMBERSHIPS_PREFIX = "committeeMemberships[%d].";
    private static final String HELPER_PREFIX = "committeeHelper.";
    private static final String COMMITTEE_MEMBERSHIP_ROLES_PREFIX = "newCommitteeMembershipRoles[%d].";
    
    private static final String PERSON_ID_ID = "personId";
    private static final String ROLODEX_ID_ID = "rolodexId";
    private static final String MEMBERSHIP_TYPE_CODE_ID = "membershipTypeCode";
    private static final String TERM_START_DATE_ID = "termStartDate";
    private static final String TERM_END_DATE_ID = "termEndDate";
    private static final String MEMBERSHIP_ROLE_CODE_ID = "membershipRoleCode";
    private static final String START_DATE_ID = "startDate";
    private static final String END_DATE_ID = "endDate";
    private static final String RESEARCH_AREA_CODE_ID = "researchAreaCode";
    private static final String LIST_MEMBERSHIP_TYPE_CODE_ID = LIST_PREFIX + COMMITTEE_MEMBERSHIPS_PREFIX + MEMBERSHIP_TYPE_CODE_ID;
    private static final String LIST_TERM_START_DATE_ID = LIST_PREFIX + COMMITTEE_MEMBERSHIPS_PREFIX + TERM_START_DATE_ID;
    private static final String LIST_TERM_END_DATE_ID = LIST_PREFIX + COMMITTEE_MEMBERSHIPS_PREFIX + TERM_END_DATE_ID;
    private static final String HELPER_MEMBERSHIP_ROLE_CODE_ID = HELPER_PREFIX + COMMITTEE_MEMBERSHIP_ROLES_PREFIX + MEMBERSHIP_ROLE_CODE_ID;
    private static final String HELPER_START_DATE_ID = HELPER_PREFIX + COMMITTEE_MEMBERSHIP_ROLES_PREFIX + START_DATE_ID;
    private static final String HELPER_END_DATE_ID = HELPER_PREFIX + COMMITTEE_MEMBERSHIP_ROLES_PREFIX + END_DATE_ID;

    private static final String EMPLOYEE_ID = "10000000002";
    private static final String EMPLOYEE_NAME = "Joe Tester";
    private static final String NON_EMPLOYEE_ID = "1727";
    private static final String NON_EMPLOYEE_NAME = "Ho, Pauline";
    private static final String VOTING_CHAIR_MEMBERSHIP_TYPE = "Voting member";
    private static final String MEMBERSHIP_ROLE_CODE_CHAIR = "Chair";
    private static final String MEMBERSHIP_ROLE_CODE_MEMBER_SCIENTIST = "Member - Scientist";
    private static final String RESEARCH_AREA_CODE = "01.0101";
    private static final String RESEARCH_AREA_CODE_NAME = "01.0101 Agricultural Business and Management, General";
    private static final String DATE_2009_01_01 = "01/01/2009";
    private static final String DATE_2009_01_05 = "01/05/2009";
    private static final String DATE_2009_01_10 = "01/10/2009";
    private static final String DATE_2009_01_15 = "01/15/2009";
    private static final String DATE_2009_02_01 = "02/01/2009";
    private static final String DATE_2009_02_10 = "02/10/2009";
    private static final String DATE_2009_12_31 = "12/31/2009";
    private static final String DATE_9999_12_31 = "12/31/9999";
    
    private static final String ERROR_MEMBERSHIP_TYPE_REQUIRED = "Membership Type is a required field.";
    private static final String ERROR_TERM_START_DATE_REQUIRED = "Term Start Date is a required field.";
    private static final String ERROR_TERM_END_DATE_REQUIRED = "Term End Date is a required field.";
    private static final String ERROR_MEMBER_ROLE = "Each member must have at least one role.";
    private static final String ERROR_MEMBER_AREA_OF_RESEARCH = "Each member must have at least one area of research.";
    private static final String ERROR_ROLE_END_DATE_RANGE = "Role end date must be greater than or equal to role start date.";
    private static final String ERROR_ROLE_OVERLAPPING = "A member may not have the same role for overlapping time periods.";
    
    private static final String ADD_COMMITTEE_MEMBERSHIP_BUTTON = "methodToCall.addCommitteeMembership";
    private static final String CLEAR_COMMITTEE_MEMBERSHIP_BUTTON = "methodToCall.clearCommitteeMembership";
    private static final String ADD_COMMITTEE_MEMBERSHIP_ROLE_BUTTON = "methodToCall.addCommitteeMembershipRole.document.committeeList[0].committeeMemberships[0].line0";

    private CommitteeSeleniumHelper helper;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        helper = CommitteeSeleniumHelper.instance(driver);
    }
    
    @After
    public void tearDown() throws Exception {
        helper = null;
        
        super.tearDown();
    }

    /**
     * This method is to test add an employee
     * @throws Exception
     */
    @Test
    public void testAddEmployee() throws Exception {
        helper.createCommittee();
        helper.clickCommitteeMembersPage();
        
        helper.lookup(PERSON_ID_TAG, PERSON_ID_ID, EMPLOYEE_ID);
        helper.assertPageContains(EMPLOYEE_NAME);
        
        helper.click(ADD_COMMITTEE_MEMBERSHIP_BUTTON);
        helper.assertNoPageErrors();
    }
    
    /**
     * This method is to test add an non-employee
     * @throws Exception
     */
    @Test
    public void testAddNonEmployee() throws Exception {
        helper.createCommittee();
        helper.clickCommitteeMembersPage();
        
        helper.lookup(ROLODEX_ID_TAG, ROLODEX_ID_ID, NON_EMPLOYEE_ID);
        helper.assertPageContains(NON_EMPLOYEE_NAME);
        
        helper.click(ADD_COMMITTEE_MEMBERSHIP_BUTTON);
        helper.assertNoPageErrors();
    }
    
    /**
     * This method is to test clear option.
     * Select an employee and clear selected employee to search for a new person.
     * @throws Exception
     */
    @Test
    public void testClearEmployee() throws Exception {
        helper.createCommittee();
        helper.clickCommitteeMembersPage();
        
        helper.lookup(PERSON_ID_TAG, PERSON_ID_ID, EMPLOYEE_ID);
        helper.assertPageContains(EMPLOYEE_NAME);

        helper.click(CLEAR_COMMITTEE_MEMBERSHIP_BUTTON);
        helper.assertPageDoesNotContain(EMPLOYEE_NAME);
    }
    
    /**
     * This method is to test the saving of a committee member.
     * Select an employee, add required data, and save document
     * @throws Exception
     */
    @Test
    public void testSaveEmployee() throws Exception {
        helper.createCommittee();
        helper.clickCommitteeMembersPage();
        
        helper.lookup(PERSON_ID_TAG, PERSON_ID_ID, EMPLOYEE_ID);
        helper.click(ADD_COMMITTEE_MEMBERSHIP_BUTTON);
        
        helper.openTab(0);
        helper.openTab(1);
        helper.set(String.format(LIST_MEMBERSHIP_TYPE_CODE_ID, 0), VOTING_CHAIR_MEMBERSHIP_TYPE);
        helper.set(String.format(LIST_TERM_START_DATE_ID, 0), DATE_2009_01_01);
        helper.set(String.format(LIST_TERM_END_DATE_ID, 0), DATE_9999_12_31);
        
        helper.openTab(3);
        addMemberRole(0, MEMBERSHIP_ROLE_CODE_MEMBER_SCIENTIST, DATE_2009_01_01, DATE_9999_12_31);
        addMemberRole(0, MEMBERSHIP_ROLE_CODE_CHAIR, DATE_2009_01_01, DATE_2009_01_10);
        addMemberRole(0, MEMBERSHIP_ROLE_CODE_CHAIR, DATE_2009_02_01, DATE_2009_02_10);
        
        helper.openTab(4);
        helper.multiLookup(RESEARCH_AREAS_TAG, RESEARCH_AREA_CODE_ID, RESEARCH_AREA_CODE);

        helper.saveDocument();
        helper.assertNoPageErrors();

        helper.assertTableRowCount(MEMBERSHIP_ROLE_TABLE_ID, 5);
        helper.assertTableCellValueContains(MEMBERSHIP_ROLE_TABLE_ID, MEMBERSHIP_ROLE_CODE_MEMBER_SCIENTIST);
        helper.assertTableCellValueContains(MEMBERSHIP_ROLE_TABLE_ID, MEMBERSHIP_ROLE_CODE_CHAIR);

        helper.assertTableRowCount(MEMBERSHIP_EXPERTISE_TABLE_ID, 3);
        helper.assertTableCellValueContains(MEMBERSHIP_EXPERTISE_TABLE_ID, RESEARCH_AREA_CODE_NAME);
    }

    /**
     * This method is to test for error when saving a committee member where the required
     * data is missing.
     * Select an employee and save document
     * @throws Exception
     */
    @Test
    public void testInvalidSaveEmployeeMissingData() throws Exception {
        helper.createCommittee();
        helper.clickCommitteeMembersPage();
        
        helper.lookup(PERSON_ID_TAG, PERSON_ID_ID, EMPLOYEE_ID);
        helper.click(ADD_COMMITTEE_MEMBERSHIP_BUTTON);
        
        helper.saveDocument();
        helper.assertPageErrors();
        
        helper.assertPageContains(ERROR_MEMBERSHIP_TYPE_REQUIRED);
        helper.assertPageContains(ERROR_TERM_START_DATE_REQUIRED);
        helper.assertPageContains(ERROR_TERM_END_DATE_REQUIRED);
        
        helper.assertPageContains(ERROR_MEMBER_ROLE);
        helper.assertPageContains(ERROR_MEMBER_AREA_OF_RESEARCH);
    }

    /**
     * This method is to test for error when adding a role whose start date is after the end date.
     * Select an employee and add a role to the employee
     * @throws Exception
     */
    @Test
    public void testInvalidAddRoleStartDateAfterEndDate() throws Exception {
        helper.createCommittee();
        helper.clickCommitteeMembersPage();
        
        helper.lookup(PERSON_ID_TAG, PERSON_ID_ID, EMPLOYEE_ID);
        helper.click(ADD_COMMITTEE_MEMBERSHIP_BUTTON);
        
        helper.openTab(0);
        helper.openTab(3);
        
        addMemberRole(0, MEMBERSHIP_ROLE_CODE_MEMBER_SCIENTIST, DATE_2009_12_31, DATE_2009_01_01);

        helper.assertPageErrors();
        
        helper.assertPageContains(ERROR_ROLE_END_DATE_RANGE);
    }
    
    /**
     * This method is to test for error when adding a role whose start date is after the end date.
     * Select an employee and add a role to the employee
     * @throws Exception
     */
    @Test
    public void testInvalidAddRoleOverlappingDates() throws Exception {
        helper.createCommittee();
        helper.clickCommitteeMembersPage();
        
        helper.lookup(PERSON_ID_TAG, PERSON_ID_ID, EMPLOYEE_ID);
        helper.click(ADD_COMMITTEE_MEMBERSHIP_BUTTON);

        helper.openTab(0);
        helper.openTab(3);
        
        addMemberRole(0, MEMBERSHIP_ROLE_CODE_MEMBER_SCIENTIST, DATE_2009_01_01, DATE_2009_01_10);
        helper.assertNoPageErrors();

        addMemberRole(0, MEMBERSHIP_ROLE_CODE_MEMBER_SCIENTIST, DATE_2009_01_05, DATE_2009_01_15);
        helper.assertPageErrors();
        
        helper.assertPageContains(ERROR_ROLE_OVERLAPPING);
    }

    /**
     * This method adds a role to a member
     * @param membersPage
     * @param memberIndex
     * @param membershipRoleCode
     * @param startDate
     * @param endDate
     * @return membersPage
     * @throws Exception
     */
    private void addMemberRole(int index, String membershipRoleCode, String startDate, String endDate) throws Exception {
        helper.set(String.format(HELPER_MEMBERSHIP_ROLE_CODE_ID, index), membershipRoleCode);
        helper.set(String.format(HELPER_START_DATE_ID, index), startDate);
        helper.set(String.format(HELPER_END_DATE_ID, index), endDate);
        
        helper.click(ADD_COMMITTEE_MEMBERSHIP_ROLE_BUTTON);
    }

}
/*
 * Copyright 2006-2008 The Kuali Foundation
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

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;

/**
 * 
 * This is the integration test for committee member screen. 
 */
public class CommitteeMembershipWebTest extends CommitteeWebTestBase {

    protected static final String ERRORS_FOUND_ON_PAGE = "error(s) found on page";

    private static final String PERSON_LOOKUP = "org.kuali.kra.bo.Person";
    private static final String PERSON_ID_FIELD = "personId";
    private static final String ROLODEX_LOOKUP ="org.kuali.kra.bo.NonOrganizationalRolodex";
    private static final String ROLODEX_ID_FIELD = "rolodexId";
    private static final String EMPLOYEE_ID = "000000002";
    private static final String EMPLOYEE_NAME = "Philip Berg";
    private static final String NON_EMPLOYEE_ID = "1727";
    private static final String NON_EMPLOYEE_NAME = "Pauline Ho";
    private static final String ADD_MEMBER_BUTTON = "methodToCall.addCommitteeMembership";
    private static final String CLEAR_BUTTON = "methodToCall.clearCommitteeMembership";
    
    private static final String ADD_ROLE_BUTTON = "methodToCall.addCommitteeMembershipRole.document.committeeList[0].committeeMemberships[0].line0";

    private HtmlPage membersPage;

    /**
     * The set up method calls the parent super method and gets the 
     * committee page after saving initial required fields.
     * Then navigate to committee members page
     * @see org.kuali.kra.irb.web.CommitteeWebTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.membersPage = getMembersPage();
        
    }

    /**
     * This method calls parent tear down method and than sets membersPage to null
     * @see org.kuali.kra.irb.web.CommitteeWebTestBase#tearDown()
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        this.membersPage = null;
    }

    /**
     * This method is to test add an employee
     * @throws Exception
     */
    @Test
    public void testAddEmployee() throws Exception {
        membersPage = getEmployee(membersPage);
        assertTrue(membersPage.asText().contains(EMPLOYEE_NAME));
        membersPage = addMember(membersPage);
        assertFalse(membersPage.asText().contains(ERRORS_FOUND_ON_PAGE));
    }
    
    /**
     * This method is to test add an non-employee
     * @throws Exception
     */
    @Test
    public void testAddNonEmployee() throws Exception {
        membersPage = getNonEmployee(membersPage);
        assertTrue(membersPage.asText().contains(NON_EMPLOYEE_NAME));
        membersPage = addMember(membersPage);
        assertFalse(membersPage.asText().contains(ERRORS_FOUND_ON_PAGE));
    }
    
    /**
     * This method is to test clear option.
     * Select an employee and clear selected employee to search for a new person.
     * @throws Exception
     */
    @Test
    public void testClearEmployee() throws Exception {
        membersPage = getEmployee(membersPage);
        assertTrue(membersPage.asText().contains(EMPLOYEE_NAME));
        membersPage = clickOn(getElementByName(membersPage, CLEAR_BUTTON, true));
        assertFalse(membersPage.asText().contains(EMPLOYEE_NAME));
    }
    
    /**
     * This method is to test the saving of a committee member.
     * Select an employee, add required data, and save document
     * @throws Exception
     */
    @Test
    public void testSaveEmployee() throws Exception {
        membersPage = getEmployee(membersPage);
        membersPage = addMember(membersPage);
        
        setFieldValue(membersPage, "document.committeeList[0].committeeMemberships[0].membershipTypeCode", "1");
        setFieldValue(membersPage, "document.committeeList[0].committeeMemberships[0].termStartDate", "01/01/2009");
        setFieldValue(membersPage, "document.committeeList[0].committeeMemberships[0].termEndDate", "12/31/2009");
        
        membersPage = addMemberRole(membersPage, 0, "2", "01/01/2009", "12/31/2009");
        membersPage = addMemberRole(membersPage, 0, "1", "01/01/2009", "01/10/2009");
        membersPage = addMemberRole(membersPage, 0, "1", "02/01/2009", "02/10/2009");
        membersPage = addMemberExpertise(membersPage, "01.0101");

        /*
         * Verify that we can save the document without getting any errors.
         */
        membersPage = clickOn(membersPage, "save");
        assertFalse(hasError(membersPage));
        assertContains(membersPage, "Document was successfully saved.");

        /*
         * Get the document again and verify that it was correctly saved to the database.
         */
        membersPage = clickOn(membersPage, "reload");
        assertFalse(hasError(membersPage));
        assertContains(membersPage, "Document was successfully reloaded.");
        
        /*
         * Verify that the role table of the first member has five rows (title, form, and new role row),
         * and verify that the new roles are what we expected.
         */
        HtmlTable table = getTable(membersPage, "membership-role-table-0");
        assertEquals(5, table.getRowCount());
        assertTrue(table.asText().contains("Member - Scientist"));
        assertTrue(table.asText().contains("Chair"));

        /*
         * Verify that the expertise table of the first member has three rows (title, form, and new expertise row),
         * and verify that the new research area is what we expected.
         */
        table = getTable(membersPage, "membership-expertise-table-0");
        assertEquals(3, table.getRowCount());
        assertContains(membersPage, "Agricultural Business and Management");
        assertTrue(table.asText().contains("Agricultural Business and Management"));
    }

    /**
     * This method is to test for error when saving a committee member where the required
     * data is missing.
     * Select an employee and save document
     * @throws Exception
     */
    @Test
    public void testInvalidSaveEmployeeMissingData() throws Exception {
        membersPage = getEmployee(membersPage);
        membersPage = addMember(membersPage);
        membersPage = clickOn(membersPage, "save");
        
        assertTrue(hasError(membersPage));
        assertContains(membersPage, "Membership Type (Membership Type) is a required field.");
        assertContains(membersPage, "Term End Date (Term End Date) is a required field.");
        assertContains(membersPage, "Term Start Date (Term Start Date) is a required field.");
        assertContains(membersPage, "Each member must have at least one role.");
        assertContains(membersPage, "Each member must have at least one area of research.");
    }

    /**
     * This method is to test for error when adding a role whose start date is after the end date.
     * Select an employee and add a role to the employee
     * @throws Exception
     */
    @Test
    public void testInvalidAddRoleStartDateAferEndDate() throws Exception {
        membersPage = getEmployee(membersPage);
        membersPage = addMember(membersPage);
        membersPage = addMemberRole(membersPage, 0, "2", "12/31/2009", "01/01/2009");

        assertTrue(hasError(membersPage));
        assertContains(membersPage, "Role end date must be greater than or equal to role start date.");
    }
    
    /**
     * This method is to test for error when adding a role whose start date is after the end date.
     * Select an employee and add a role to the employee
     * @throws Exception
     */
    @Test
    public void testInvalidAddRoleOverlappingDates() throws Exception {
        membersPage = getEmployee(membersPage);
        membersPage = addMember(membersPage);

        membersPage = addMemberRole(membersPage, 0, "2", "01/01/2009", "01/10/2009");
        assertFalse(hasError(membersPage));

        membersPage = addMemberRole(membersPage, 0, "2", "01/05/2009", "01/15/2009");
        assertTrue(hasError(membersPage));
        assertContains(membersPage, "A member may not have the same role for overlapping time periods.");
    }

    /**
     * This method is to look up an employee
     * @return HtmlPage
     * @throws Exception
     */
    private HtmlPage getEmployee(HtmlPage membersPage) throws Exception {
        return lookup(membersPage , PERSON_LOOKUP, PERSON_ID_FIELD, EMPLOYEE_ID);
    }

    /**
     * This method is to look up a non-employee
     * @return HtmlPage
     * @throws Exception
     */
    private HtmlPage getNonEmployee(HtmlPage membersPage) throws Exception {
        return lookup(membersPage , ROLODEX_LOOKUP, ROLODEX_ID_FIELD, NON_EMPLOYEE_ID);
    }

    /**
     * This method adds a member to the page
     * @return HtmlPage
     * @throws Exception
     */
    private HtmlPage addMember(HtmlPage page) throws Exception {
        return clickOn(getElementByName(page, ADD_MEMBER_BUTTON, true));
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
    private HtmlPage addMemberRole(HtmlPage membersPage, int memberIndex, String membershipRoleCode, String startDate, String endDate) throws Exception {
        setFieldValue(membersPage, "membershipRolesHelper.newCommitteeMembershipRoles[" + memberIndex + "].membershipRoleCode", membershipRoleCode);
        setFieldValue(membersPage, "membershipRolesHelper.newCommitteeMembershipRoles[" + memberIndex + "].startDate" ,startDate);
        setFieldValue(membersPage, "membershipRolesHelper.newCommitteeMembershipRoles[" + memberIndex + "].endDate", endDate);
        return clickOn(getElementByName(membersPage, ADD_ROLE_BUTTON, true));
    }
    
    /**
     * This method add a expertise to a member
     * @param membersPage
     * @param researchAreaCode
     * @return membersPage
     * @throws Exception
     */
    private HtmlPage addMemberExpertise(HtmlPage membersPage, String researchAreaCode) throws Exception {
        return multiLookup(membersPage, "committeeResearchAreas", "researchAreaCode", researchAreaCode);
    }
}
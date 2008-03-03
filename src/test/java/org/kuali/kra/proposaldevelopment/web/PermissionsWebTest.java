/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

public class PermissionsWebTest extends ProposalDevelopmentWebTestBase {
    
    private static final String QUICKSTART_USERNAME = "quickstart";
    private static final String QUICKSTART_FULLNAME = "Geoff McGregor";
    
    private static final String TESTER_USERNAME = "jtester";
    private static final String TESTER_FULLNAME = "Joe Tester";
    
    private static final String USER1_USERNAME = "tdurkin";
    private static final String USER1_FULLNAME = "Terry Durkin";
    
    private static final String USER2_USERNAME = "aslusar";
    private static final String USER2_FULLNAME = "Andrew Slusar";
    
    private static final String USER3_USERNAME = "pcberg";
    private static final String USER3_FULLNAME = "Philip Berg";
    
    private static final String USER4_USERNAME = "ljoconno";
    private static final String USER4_FULLNAME = "Lora OConnor";
    
    private static final String AGGREGATORS_ID = "Aggregators";
    private static final String BUDGET_CREATORS_ID = "BudgetCreators";
    private static final String NARRATIVE_WRITERS_ID = "NarrativeWriters";
    private static final String VIEWERS_ID = "Viewers";
    
    private static final String USER_TABLE_ID = "user-roles";
    
    private static final String AGGREGATOR_NAME = "Aggregator";
    private static final String BUDGET_CREATOR_NAME = "Budget Creator";
    private static final String NARRATIVE_WRITER_NAME = "Narrative Writer";
    private static final String VIEWER_NAME = "Viewer";
    private static final String UNASSIGNED_NAME = "unassigned";
    
    private static final String UNIT_NUMBER = "000001";
    private static final String UNIT_NAME = "University";
    
    private static final String AGGREGATOR_ROLENAME = "Aggregator";
    private static final String BUDGET_CREATOR_ROLENAME = "Budget Creator";
    private static final String NARRATIVE_WRITER_ROLENAME = "Narrative Writer";
    private static final String VIEWER_ROLENAME = "Viewer";
    private static final String UNASSIGNED_ROLENAME = "unassigned";
    
    private static final String AGGREGATOR_ROLE_LABEL = "Aggregator";
    private static final String BUDGET_CREATOR_ROLE_LABEL = "Budget Creator";
    private static final String NARRATIVE_WRITER_ROLE_LABEL = "Narrative Writer";
    private static final String VIEWER_ROLE_LABEL = "Viewer";
    private static final String UNASSIGNED_ROLE_LABEL = "unassigned";
    
    private static final String USERNAME_FIELD_ID = "newProposalUser.username";
    private static final String ROLENAME_FIELD_ID = "newProposalUser.roleName";
    private static final String ADD_BTN_ID = "methodToCall.addProposalUser";
    
    private static final String USER_TAB_DIV = "tab-Users-div";
    private static final String ROLES_TAB_DIV = "tab-Roles-div";
    
    private static final String YES_BTN_ID = "methodToCall.processAnswer.button0";
    private static final String NO_BTN_ID = "methodToCall.processAnswer.button1";
    
    private static final String DELETE_BTN = "methodToCall.deleteProposalUser";
    private static final String EDIT_ROLES_BTN = "methodToCall.editRoles";
    
    
    private static final String AGGREGATOR_FIELD_ID = "proposalUserEditRoles.aggregator";
    private static final String BUDGET_CREATOR_FIELD_ID = "proposalUserEditRoles.budgetCreator";
    private static final String NARRATIVE_WRITER_FIELD_ID = "proposalUserEditRoles.narrativeWriter";
    
    private class User {
        String username;
        String fullname;
        String unitNumber;
        String unitName;
        String[] roleNames;
        
        User(String username, String fullname, String unitNumber, String unitName, String roleName) {
            this.username = username;
            this.fullname = fullname;
            this.unitNumber = unitNumber;
            this.unitName = unitName;
            this.roleNames = new String[1];
            this.roleNames[0] = roleName;
        }
        
        User(String username, String fullname, String unitNumber, String unitName, String roleNames[]) {
            this.username = username;
            this.fullname = fullname;
            this.unitNumber = unitNumber;
            this.unitName = unitName;
            this.roleNames = roleNames;
        }
        
        public boolean equals(Object obj) {
            return true;
        }
    }
    
    /***********************************************************************
     * Setup and TearDown
     ***********************************************************************/
    
    private boolean javaScriptEnabled;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        javaScriptEnabled = webClient.isJavaScriptEnabled();
        webClient.setJavaScriptEnabled(false);
    }
    
    @After
    public void tearDown() throws Exception {
        webClient.setJavaScriptEnabled(javaScriptEnabled);
        super.tearDown();
    }
    
    /***********************************************************************
     * General Purpose Helper Methods.
     ***********************************************************************/

    /**
     * Saves and closes the document and then perform a search for it.
     *
     * @param docPage the document page to close.
     * @return the Abstracts & Attachments web page
     * @throws Exception
     */
    protected HtmlPage saveAndSearch(HtmlPage docPage) throws Exception {
        HtmlPage proposalPage = saveAndSearchDoc(docPage);
        return clickOnTab(proposalPage, PERMISSIONS_LINK_NAME);
    }
    
    /***********************************************************************
     * General Unit Test for entire Web Page.
     ***********************************************************************/

    /**
     * Verify that all the Help links on the web page go to the Kuali Help Web Page.
     * This will also test the help links on other panels on the page, but no big deal.
     * @throws Exception
     */
    @Test
    public void testHelpLinks() throws Exception {
        HtmlPage permissionsPage = getPermissionsPage();
        this.checkHelpLinks(permissionsPage);
    }
    
    /***********************************************************************
     * Unit Tests for Permissions web page.
     ***********************************************************************/
    
    /**
     * Test the initial creation of a proposal.  When a proposal is initially
     * save/created, the initiator is added as the Aggregator.
     * 
     * @throws Exception
     */
    @Test
    public void testProposalCreation() throws Exception {
        HtmlPage permissionsPage = getPermissionsPage();
        
        // Upon initial creation of a proposal, the initiator must 
        // be assigned the Aggregator role and is the only user assigned
        // to the proposal.  Start by looking at the Assigned Roles
        // and verifying that only quickstart is the only aggregator
        // and no other roles are assigned.
        
        String[] aggregators = new String[1];
        aggregators[0] = QUICKSTART_FULLNAME;
        checkAssignedRoles(permissionsPage, aggregators, null, null, null);
        
        // Now verify that the User Permissions table only has one entry with
        // quickstart as the aggregator.
        
        List<User> users = new ArrayList<User>();
        users.add(new User(QUICKSTART_USERNAME, QUICKSTART_FULLNAME, UNIT_NUMBER, UNIT_NAME, AGGREGATOR_NAME));
        checkUserTable(permissionsPage, users, 2);
    }
    
    /**
     * Test adding people to the list of users with permission to access
     * the proposal.  The following is being tested:
     * 
     *     1. Make sure each role type works by adding one user
     *        for each type of role.
     *     2. Make sure the displayed table is sorted by full name.
     *     3. Make sure the Assigned Roles panel shows the names as expected.
     *     4. Verify that the save works and that the displayed information
     *        is indeed correct.
     *
     * @throws Exception
     */
    @Test
    public void testAddUsers() throws Exception {
        HtmlPage permissionsPage = getPermissionsPage();
        
        // Add the users.
        
        permissionsPage = addAllUsers(permissionsPage);
        
        // Check the Assigned Roles  panel.
        
        String[] aggregators = buildArray(QUICKSTART_FULLNAME, USER1_FULLNAME);
        String[] budgetCreators = buildArray(USER2_FULLNAME);
        String[] narrativeWriters = buildArray(USER3_FULLNAME);
        String[] viewers = buildArray(TESTER_FULLNAME);
        checkAssignedRoles(permissionsPage, aggregators, budgetCreators, narrativeWriters, viewers);
        
        // Check the User table.
        
        List<User> users = new ArrayList<User>();
        users.add(new User(USER2_USERNAME, USER2_FULLNAME, UNIT_NUMBER, UNIT_NAME, BUDGET_CREATOR_NAME));
        users.add(new User(QUICKSTART_USERNAME, QUICKSTART_FULLNAME, UNIT_NUMBER, UNIT_NAME, AGGREGATOR_NAME));
        users.add(new User(TESTER_USERNAME, TESTER_FULLNAME, UNIT_NUMBER, UNIT_NAME, VIEWER_NAME));
        users.add(new User(USER4_USERNAME, USER4_FULLNAME, UNIT_NUMBER, UNIT_NAME, UNASSIGNED_NAME));
        users.add(new User(USER3_USERNAME, USER3_FULLNAME, UNIT_NUMBER, UNIT_NAME, NARRATIVE_WRITER_NAME));
        users.add(new User(USER1_USERNAME, USER1_FULLNAME, UNIT_NUMBER, UNIT_NAME, AGGREGATOR_NAME));
        checkUserTable(permissionsPage, users, 2);
        
        // Save the proposal and re-check to be sure the data is still correctly displayed.
        
        permissionsPage = saveAndSearch(permissionsPage);
        checkAssignedRoles(permissionsPage, aggregators, budgetCreators, narrativeWriters, viewers);
        checkUserTable(permissionsPage, users, 2);
    }
    
    /**
     * Test adding a duplicate user.  Should result in an error.
     * @throws Exception
     */
    @Test
    public void testAddDuplicate() throws Exception {
        HtmlPage permissionsPage = getPermissionsPage();
       
        permissionsPage = addUser(permissionsPage, TESTER_USERNAME, VIEWER_ROLENAME);
        assertTrue(!hasError(permissionsPage));
        
        permissionsPage = addUser(permissionsPage, TESTER_USERNAME, VIEWER_ROLENAME);
        assertTrue(hasError(permissionsPage));
        
        List<String> errors = getErrors(permissionsPage, USER_TAB_DIV);
        assertEquals(errors.size(), 1);
        assertTrue(containsError(errors, "User has already been added"));
    }
    
    /**
     * Test adding an unknown user.  Should result in an error.
     * @throws Exception
     */
    @Test
    public void testAddUnknownUser() throws Exception {
        HtmlPage permissionsPage = getPermissionsPage();
        
        permissionsPage = addUser(permissionsPage, "xxx", VIEWER_ROLENAME);
        assertTrue(hasError(permissionsPage));
        
        List<String> errors = getErrors(permissionsPage, USER_TAB_DIV);
        assertEquals(errors.size(), 1);
        assertTrue(containsError(errors, "The person is unknown"));
    }
    
    /**
     * Test the lookup of a user.  The lookup page should have the
     * home unit field filled in with the lead unit of the proposal.
     * @throws Exception
     */
    @Test
    public void testUserLookup() throws Exception {
        HtmlPage permissionsPage = getPermissionsPage();
        
        // Verify that the home unit field is initialized with
        // the lead unit of the proposal.
        
        HtmlPage lookupPage = clickOnLookup(permissionsPage, "newProposalUser");
        String homeUnit = getFieldValue(lookupPage, "homeUnit");
        assertEquals(UNIT_NUMBER, homeUnit);
        
        // Go back to the permissions page and use the lookup() method
        // to verify the lookup of a person. The username field should 
        // not be empty.
        
        permissionsPage = clickOn(lookupPage, "cancel");
        permissionsPage = lookup(permissionsPage, "newProposalUser");
        
        String username = getFieldValue(permissionsPage, "newProposalUser.username");
        assertTrue(!StringUtils.equals(username, ""));
    }
    
    /**
     * Test the deletion of a user.  Add lots of users and then delete
     * one from the middle of the list.  Make sure that a save and retrieval
     * of the proposal also reflects the deletion.  Beside the user table,
     * the Assigned Roles panel must also be updated correctly.
     * @throws Exception
     */
    @Test
    public void testDeleteUser() throws Exception {
        HtmlPage permissionsPage = getPermissionsPage();
        permissionsPage = addAllUsers(permissionsPage);
        permissionsPage = saveAndSearch(permissionsPage);
        permissionsPage = deleteUser(permissionsPage, TESTER_USERNAME, true);
        
        // Check the Assigned Roles  panel.
        
        String[] aggregators = buildArray(QUICKSTART_FULLNAME, USER1_FULLNAME);
        String[] budgetCreators = buildArray(USER2_FULLNAME);
        String[] narrativeWriters = buildArray(USER3_FULLNAME);
        
        checkAssignedRoles(permissionsPage, aggregators, budgetCreators, narrativeWriters, null);
        
        // Check the User table.
        
        List<User> users = new ArrayList<User>();
        users.add(new User(USER2_USERNAME, USER2_FULLNAME, UNIT_NUMBER, UNIT_NAME, BUDGET_CREATOR_NAME));
        users.add(new User(QUICKSTART_USERNAME, QUICKSTART_FULLNAME, UNIT_NUMBER, UNIT_NAME, AGGREGATOR_NAME));
        users.add(new User(USER4_USERNAME, USER4_FULLNAME, UNIT_NUMBER, UNIT_NAME, UNASSIGNED_NAME));
        users.add(new User(USER3_USERNAME, USER3_FULLNAME, UNIT_NUMBER, UNIT_NAME, NARRATIVE_WRITER_NAME));
        users.add(new User(USER1_USERNAME, USER1_FULLNAME, UNIT_NUMBER, UNIT_NAME, AGGREGATOR_NAME));
        checkUserTable(permissionsPage, users, 2);
        
        // Save the proposal and re-check to be sure the data is still correctly displayed.
        
        permissionsPage = saveAndSearch(permissionsPage);
        checkAssignedRoles(permissionsPage, aggregators, budgetCreators, narrativeWriters, null);
        checkUserTable(permissionsPage, users, 2);
    }
    
    /**
     * Test the deletion of a user but answer no to the confirmation.  
     * Add lots of users and then delete one from the middle of the list.  
     * Make sure that a save and retrieval of the proposal shows that the
     * deletion did not occur.  Beside the user table, the Assigned Roles panel 
     * must be unchanged.
     * @throws Exception
     */
    @Test
    public void testDeleteUserNoConfirm() throws Exception {
        HtmlPage permissionsPage = getPermissionsPage();
        permissionsPage = addAllUsers(permissionsPage);
        permissionsPage = saveAndSearch(permissionsPage);
        permissionsPage = deleteUser(permissionsPage, TESTER_USERNAME, false);
        
        // Check the Assigned Roles  panel.
        
        String[] aggregators = buildArray(QUICKSTART_FULLNAME, USER1_FULLNAME);
        String[] budgetCreators = buildArray(USER2_FULLNAME);
        String[] narrativeWriters = buildArray(USER3_FULLNAME);
        String[] viewers = buildArray(TESTER_FULLNAME);
        checkAssignedRoles(permissionsPage, aggregators, budgetCreators, narrativeWriters, viewers);
        
        // Check the User table.
        
        List<User> users = new ArrayList<User>();
        users.add(new User(USER2_USERNAME, USER2_FULLNAME, UNIT_NUMBER, UNIT_NAME, BUDGET_CREATOR_NAME));
        users.add(new User(QUICKSTART_USERNAME, QUICKSTART_FULLNAME, UNIT_NUMBER, UNIT_NAME, AGGREGATOR_NAME));
        users.add(new User(TESTER_USERNAME, TESTER_FULLNAME, UNIT_NUMBER, UNIT_NAME, VIEWER_NAME));
        users.add(new User(USER4_USERNAME, USER4_FULLNAME, UNIT_NUMBER, UNIT_NAME, UNASSIGNED_NAME));
        users.add(new User(USER3_USERNAME, USER3_FULLNAME, UNIT_NUMBER, UNIT_NAME, NARRATIVE_WRITER_NAME));
        users.add(new User(USER1_USERNAME, USER1_FULLNAME, UNIT_NUMBER, UNIT_NAME, AGGREGATOR_NAME));
        checkUserTable(permissionsPage, users, 2);
        
        // Save the proposal and re-check to be sure the data is still correctly displayed.
        
        permissionsPage = saveAndSearch(permissionsPage);
        checkAssignedRoles(permissionsPage, aggregators, budgetCreators, narrativeWriters, viewers);
        checkUserTable(permissionsPage, users, 2);
    }
    
    /**
     * Test deleting the last aggregator.  This is an illegal operation
     * and should result in an error.
     * @throws Exception
     */
    @Test
    public void testDeleteLastAggregator() throws Exception {
        HtmlPage permissionsPage = getPermissionsPage();
        HtmlTableRow row = findRowByUsername(permissionsPage, QUICKSTART_USERNAME);
        HtmlElement deleteBtn = getElementByName(row, DELETE_BTN, true);
        permissionsPage = clickOn(deleteBtn);
        assertTrue(hasError(permissionsPage));
        
        List<String> errors = getErrors(permissionsPage, USER_TAB_DIV);
        assertEquals(errors.size(), 1);
        assertTrue(containsError(errors, "Must have at least one Aggregator"));
    }
    
    /**
     * Test editing the roles of a user.  For a user with the budget creator
     * role, add the narrative role and make sure it works, i.e. it gets saved
     * to the database.  Then edit the roles again and remove the budget
     * creator role and verify the change.
     * @throws Exception
     */
    @Test
    public void testEditRoles() throws Exception {
        HtmlPage permissionsPage = getPermissionsPage();
        permissionsPage = addAllUsers(permissionsPage);
        permissionsPage = saveAndSearch(permissionsPage);
        
        HtmlPage editRolesPage = gotoEditRoles(permissionsPage, USER2_USERNAME);
        setFieldValue(editRolesPage, NARRATIVE_WRITER_FIELD_ID, "on");
        permissionsPage = clickOn(editRolesPage, "save");
        
        // Check the Assigned Roles  panel.
        
        String[] aggregators = buildArray(QUICKSTART_FULLNAME, USER1_FULLNAME);
        String[] budgetCreators = buildArray(USER2_FULLNAME);
        String[] narrativeWriters = buildArray(USER2_FULLNAME, USER3_FULLNAME);
        String[] viewers = buildArray(TESTER_FULLNAME);
        checkAssignedRoles(permissionsPage, aggregators, budgetCreators, narrativeWriters, viewers);
        
        // Check the User table.
        
        List<User> users = new ArrayList<User>();
        String[] roles = buildArray(BUDGET_CREATOR_NAME, NARRATIVE_WRITER_NAME);
        users.add(new User(USER2_USERNAME, USER2_FULLNAME, UNIT_NUMBER, UNIT_NAME, roles));
        users.add(new User(QUICKSTART_USERNAME, QUICKSTART_FULLNAME, UNIT_NUMBER, UNIT_NAME, AGGREGATOR_NAME));
        users.add(new User(TESTER_USERNAME, TESTER_FULLNAME, UNIT_NUMBER, UNIT_NAME, VIEWER_NAME));
        users.add(new User(USER4_USERNAME, USER4_FULLNAME, UNIT_NUMBER, UNIT_NAME, UNASSIGNED_NAME));
        users.add(new User(USER3_USERNAME, USER3_FULLNAME, UNIT_NUMBER, UNIT_NAME, NARRATIVE_WRITER_NAME));
        users.add(new User(USER1_USERNAME, USER1_FULLNAME, UNIT_NUMBER, UNIT_NAME, AGGREGATOR_NAME));
        checkUserTable(permissionsPage, users, 2);
        
        // Save the proposal and re-check to be sure the data is still correctly displayed.
        
        permissionsPage = saveAndSearch(permissionsPage);
        checkAssignedRoles(permissionsPage, aggregators, budgetCreators, narrativeWriters, viewers);
        checkUserTable(permissionsPage, users, 2);
        
        editRolesPage = gotoEditRoles(permissionsPage, USER2_USERNAME);
        setFieldValue(editRolesPage, BUDGET_CREATOR_FIELD_ID, "off");
        permissionsPage = clickOn(editRolesPage, "save");
        
        // Check the Assigned Roles  panel.
        
        aggregators = buildArray(QUICKSTART_FULLNAME, USER1_FULLNAME);
        budgetCreators = null;
        narrativeWriters = buildArray(USER2_FULLNAME, USER3_FULLNAME);
        viewers = buildArray(TESTER_FULLNAME);
        checkAssignedRoles(permissionsPage, aggregators, budgetCreators, narrativeWriters, viewers);
        
        // Check the User table.
        
        users = new ArrayList<User>();
        users.add(new User(USER2_USERNAME, USER2_FULLNAME, UNIT_NUMBER, UNIT_NAME, NARRATIVE_WRITER_NAME));
        users.add(new User(QUICKSTART_USERNAME, QUICKSTART_FULLNAME, UNIT_NUMBER, UNIT_NAME, AGGREGATOR_NAME));
        users.add(new User(TESTER_USERNAME, TESTER_FULLNAME, UNIT_NUMBER, UNIT_NAME, VIEWER_NAME));
        users.add(new User(USER4_USERNAME, USER4_FULLNAME, UNIT_NUMBER, UNIT_NAME, UNASSIGNED_NAME));
        users.add(new User(USER3_USERNAME, USER3_FULLNAME, UNIT_NUMBER, UNIT_NAME, NARRATIVE_WRITER_NAME));
        users.add(new User(USER1_USERNAME, USER1_FULLNAME, UNIT_NUMBER, UNIT_NAME, AGGREGATOR_NAME));
        checkUserTable(permissionsPage, users, 2);
        
        // Save the proposal and re-check to be sure the data is still correctly displayed.
        
        permissionsPage = saveAndSearch(permissionsPage);
        checkAssignedRoles(permissionsPage, aggregators, budgetCreators, narrativeWriters, viewers);
        checkUserTable(permissionsPage, users, 2);
    }
    
    /**
     * Test editing the roles where the user select Aggregator along
     * with another role.  This is illegal.  If Aggregator is selected,
     * it can be the only selected role.
     * @throws Exception
     */
    @Test
    public void testEditAggregatorOnly() throws Exception {
        HtmlPage permissionsPage = getPermissionsPage();
        permissionsPage = addAllUsers(permissionsPage);
        permissionsPage = saveAndSearch(permissionsPage);
        
        HtmlPage editRolesPage = gotoEditRoles(permissionsPage, USER2_USERNAME);
        
        setFieldValue(editRolesPage, AGGREGATOR_FIELD_ID, "on");
        editRolesPage = clickOn(editRolesPage, "save");

        List<String> errors = getErrors(editRolesPage, ROLES_TAB_DIV);
        assertEquals(errors.size(), 1);
        assertTrue(containsError(errors, "Do not select other roles when Aggregator is selected"));
    }
    
    /**
     * Test editing the roles where this is the last aggregator and
     * his/her aggregator role is being removed.  This cannot be allowed
     * to happen.  There must always be at least one aggregator on a
     * proposal.
     * @throws Exception
     */
    @Test
    public void testEditLastAggregator() throws Exception {
        HtmlPage permissionsPage = getPermissionsPage();
        
        HtmlPage editRolesPage = gotoEditRoles(permissionsPage, QUICKSTART_USERNAME);
        setFieldValue(editRolesPage, AGGREGATOR_FIELD_ID, "off");
        setFieldValue(editRolesPage, NARRATIVE_WRITER_FIELD_ID, "on");
        editRolesPage = clickOn(editRolesPage, "save");

        List<String> errors = getErrors(editRolesPage, ROLES_TAB_DIV);
        assertEquals(errors.size(), 1);
        assertTrue(containsError(errors, "Must have at least one Aggregator"));
    }
    
    /**
     * Verify that when a user is added, that user is also added to 
     * each narrative's set of user rights.  This test handles two cases:
     *      1. When users are added, they are added to current narratives.
     *      2. When a new narrative is created, it gets the current users.
     * This test also verifies the default narrative user right is correct
     * given the current permissions of the user.
     * @throws Exception
     */
    @Test
    public void testAddWithNarrative() throws Exception {
        String userNames[] = { USER2_FULLNAME, QUICKSTART_FULLNAME, TESTER_FULLNAME,
                               USER4_FULLNAME, USER3_FULLNAME, USER1_FULLNAME };
        String expectedValues[] = { "R", "M", "R", "N", "M", "M" };
        
        HtmlPage page = getAbstractsAndAttachmentsPage();
        page = addNarrative(page, "4");
        page = clickOnTab(page, PERMISSIONS_LINK_NAME);
        page = addAllUsers(page);
        page = addNarrative(page, "5");
        for (int j = 0; j < 2; j++) {
            HtmlPage editRightsPage = clickOn(page, "getProposalAttachmentRights.line" + j);
            HtmlTable table = getTable(editRightsPage, "narrative-rights-table");
            assertEquals(7, table.getRowCount());
            for (int i = 0; i < 6; i++) {
                HtmlTableRow row = table.getRow(i);
                
                HtmlTableCell nameCell = row.getCell(0);
                assertEquals(userNames[i], nameCell.asText().trim());
                
                String value = getFieldValue(editRightsPage, "newNarrativeUserRight[" + i + "].accessType");
                assertEquals(userNames[i], expectedValues[i], value);
            }
        }
    }
    
    /**
     * When a user is deleted, that user must also be deleted from the set
     * of user rights for each narrative.
     * @throws Exception
     */
    @Test
    public void testDeleteWithNarrative() throws Exception {
        String userNames[] = { USER2_FULLNAME, QUICKSTART_FULLNAME, 
                USER4_FULLNAME, USER3_FULLNAME, USER1_FULLNAME };
        String expectedValues[] = { "R", "M", "N", "M", "M" };

        HtmlPage page = getAbstractsAndAttachmentsPage();
        page = addNarrative(page, "4");
        page = addNarrative(page, "5");
        page = clickOnTab(page, PERMISSIONS_LINK_NAME);
        page = addAllUsers(page);
        page = deleteUser(page, TESTER_USERNAME, true);
        page = clickOnTab(page, ABSTRACTS_ATTACHMENTS_LINK_NAME);
        
        for (int j = 0; j < 2; j++) {
            HtmlPage editRightsPage = clickOn(page, "getProposalAttachmentRights.line" + j);
            HtmlTable table = getTable(editRightsPage, "narrative-rights-table");
            assertEquals(6, table.getRowCount());
            for (int i = 0; i < 5; i++) {
                HtmlTableRow row = table.getRow(i);
                
                HtmlTableCell nameCell = row.getCell(0);
                assertEquals(userNames[i], nameCell.asText().trim());
                
                String value = getFieldValue(editRightsPage, "newNarrativeUserRight[" + i + "].accessType");
                assertEquals(userNames[i], expectedValues[i], value);
            }
        }
    }
    
    /**
     * Test the case where a user originally has the Narrative Writer role
     * and thus has the modify right for the narratives.  The user's role
     * is then changed to unassigned.  In this case, the user's narrative
     * rights must be down-graded to none.
     * @throws Exception
     */
    @Test
    public void testEditRolesWithNarrative() throws Exception {
        String userNames[] = {QUICKSTART_FULLNAME, TESTER_FULLNAME };
        String expectedValues[] = { "M", "N" };

        HtmlPage page = getAbstractsAndAttachmentsPage();
        page = addNarrative(page, "4");
        page = addNarrative(page, "5");
        page = clickOnTab(page, PERMISSIONS_LINK_NAME);
        page = addUser(page, TESTER_USERNAME, NARRATIVE_WRITER_ROLENAME);
        
        HtmlPage editRolesPage = gotoEditRoles(page, TESTER_USERNAME);
        setFieldValue(editRolesPage, NARRATIVE_WRITER_FIELD_ID, "off");
        page = clickOn(editRolesPage, "save");
        
        page = clickOnTab(page, ABSTRACTS_ATTACHMENTS_LINK_NAME);
        for (int j = 0; j < 2; j++) {
            HtmlPage editRightsPage = clickOn(page, "getProposalAttachmentRights.line" + j);
            HtmlTable table = getTable(editRightsPage, "narrative-rights-table");
            assertEquals(3, table.getRowCount());
            for (int i = 0; i < 2; i++) {
                HtmlTableRow row = table.getRow(i);
                
                HtmlTableCell nameCell = row.getCell(0);
                assertEquals(userNames[i], nameCell.asText().trim());
                
                String value = getFieldValue(editRightsPage, "newNarrativeUserRight[" + i + "].accessType");
                assertEquals(userNames[i], expectedValues[i], value);
            }
        }
        
    }
    
    /**
     * Make sure the View Rights web page can be displayed.  Not much
     * to test here.  Just make sure it can come up and we can close it.
     * I'm not looking for permissions since that could change.  I just
     * make sure the four roles are displayed.
     * @throws Exception
     */
    @Test
    public void testViewRights() throws Exception {
        HtmlPage permissionsPage = getPermissionsPage();
        
        HtmlElement btn = getElementByName(permissionsPage, "methodToCall.getPermissionsRoleRights", true);
        HtmlPage page = clickOn(btn);
        
        assertContains(page, "Aggregator");
        assertContains(page, "Budget Creator");
        assertContains(page, "Narrative Writer");
        assertContains(page, "Viewer");
        
        page = clickOn(page, "methodToCall.permissions");
        assertContains(page, "Assigned Roles");
    }
    
    /**
     * Test a user who only has read only permission for a proposal.
     * Add the user as a Viewer and then use the backdoor to login as
     * that user.  Make sure the permissions page doesn't have a save
     * button, add button, edit roles button, and delete button.
     * @throws Exception
     */
    @Test
    public void testViewOnly() throws Exception {
        HtmlPage page = getPermissionsPage();
        page = addUser(page, TESTER_USERNAME, VIEWER_ROLENAME);
        String docNbr = this.getDocNbr(page);
        clickOn(page, "save");
        loginAsTester();
        page = docSearch(docNbr);
        page = clickOnTab(page, PERMISSIONS_LINK_NAME);
        
        // Must be impossible to save the document, i.e. no save button
        HtmlElement saveBtn = getElement(page, "save");
        assertNull(saveBtn);
        
        // There must not be an add button
        HtmlElement addBtn = getElementByName(page, ADD_BTN_ID, true);
        assertNull(addBtn);

        // There must not be an edit roles or delete button
        HtmlTableRow row = findRowByUsername(page, TESTER_USERNAME);
        HtmlElement editRolesBtn = getElementByName(row, EDIT_ROLES_BTN, true);
        assertNull(editRolesBtn);
        HtmlElement deleteBtn = getElementByName(row, DELETE_BTN, true);
        assertNull(deleteBtn);
    }
    
    /**
     * Test the cancel button.  Add some users and then click on
     * the Cancel button and answer yes for the confirmation.  Retrieve
     * the proposal and make sure those users are not there.
     * @throws Exception
     */
    @Test
    public void testCancel() throws Exception {
        HtmlPage page = getPermissionsPage();
        String docNbr = this.getDocNbr(page);
        page = addAllUsers(page);
        HtmlPage confirmationPage = clickOn(page, "cancel");
        
        clickOn(confirmationPage, YES_BTN_ID);
        page = docSearch(docNbr);
        page = clickOnTab(page, PERMISSIONS_LINK_NAME);
        
        // Check the User table.
        
        List<User> users = new ArrayList<User>();
        users.add(new User(QUICKSTART_USERNAME, QUICKSTART_FULLNAME, UNIT_NUMBER, UNIT_NAME, AGGREGATOR_NAME));
        checkUserTable(page, users, 2);
    }
    
    
    /**
     * Add a bunch of users to the proposal using all of the five roles.
     * @param permissionsPage
     * @return
     * @throws Exception
     */
    private HtmlPage addAllUsers(HtmlPage permissionsPage) throws Exception {
        permissionsPage = addUser(permissionsPage, TESTER_USERNAME, VIEWER_ROLENAME);
        permissionsPage = addUser(permissionsPage, USER1_USERNAME, AGGREGATOR_ROLENAME);
        permissionsPage = addUser(permissionsPage, USER2_USERNAME, BUDGET_CREATOR_ROLENAME);
        permissionsPage = addUser(permissionsPage, USER3_USERNAME, NARRATIVE_WRITER_ROLENAME);
        permissionsPage = addUser(permissionsPage, USER4_USERNAME, UNASSIGNED_ROLENAME);
        return permissionsPage;
    }
    
    /**
     * Add a single user to the proposal.
     * @param page
     * @param username
     * @param roleName
     * @return
     * @throws Exception
     */
    private HtmlPage addUser(HtmlPage page, String username, String roleName) throws Exception {
        setFieldValue(page, USERNAME_FIELD_ID, username);
        setFieldValue(page, ROLENAME_FIELD_ID, roleName);
        HtmlElement addBtn = getElementByName(page, ADD_BTN_ID, true);
        return clickOn(addBtn);
    }
    
    /**
     * Click on the edit roles button to obtain the edit roles web page.
     * @param page
     * @param username
     * @return
     * @throws Exception
     */
    private HtmlPage gotoEditRoles(HtmlPage page, String username) throws Exception {
        HtmlTableRow row = findRowByUsername(page, username);
        HtmlElement editRolesBtn = getElementByName(row, EDIT_ROLES_BTN, true);
        return clickOn(editRolesBtn);
    }
    
    /**
     * Delete a user from the list of users.
     * @param page
     * @param username
     * @param confirm true to confirm the deletion; otherwise don't do the delete
     * @return
     * @throws Exception
     */
    private HtmlPage deleteUser(HtmlPage page, String username, boolean confirm) throws Exception {
        HtmlTableRow row = findRowByUsername(page, username);
        if (row != null) {
            HtmlElement deleteBtn = getElementByName(row, DELETE_BTN, true);
            HtmlPage confirmationPage = clickOn(deleteBtn);
            if (confirm) {
                return clickOn(confirmationPage, YES_BTN_ID);
            } else {
                return clickOn(confirmationPage, NO_BTN_ID);
            }
        }
        return page;
    }
    
    /**
     * Find a row in the user table based upon a user's username.
     * @param page
     * @param username
     * @return
     */
    private HtmlTableRow findRowByUsername(HtmlPage page, String username) {
        HtmlTable userTable = getTable(page, USER_TABLE_ID);
        int rowCount = userTable.getRowCount();
        for (int i = 2; i < rowCount; i++) {
            HtmlTableRow row = userTable.getRow(i);
            HtmlTableCell cell = row.getCell(1);
            String name = cell.asText().trim();
            if (StringUtils.equals(name, username)) {
                return row;
            }
        }
        return null;
    }
    
    /**
     * Checks the Assigned Roles.  For each of the four proposal roles, verify that
     * the list of user names matches what is expected.
     * 
     * @param page the Permissions Page
     * @param aggregators the expected aggregator user names
     * @param budgetCreators the expected budget create user names
     * @param narrativeWriters the expected narrative writer user names
     * @param viewers the expected viewer user names
     */
    private void checkAssignedRoles(HtmlPage page, String aggregators[], String budgetCreators[], String narrativeWriters[], String viewers[]) {
        checkUserNames(page, AGGREGATORS_ID, aggregators);
        checkUserNames(page, BUDGET_CREATORS_ID, budgetCreators);
        checkUserNames(page, NARRATIVE_WRITERS_ID, narrativeWriters);
        checkUserNames(page, VIEWERS_ID, viewers);
    }
    
    /**
     * This method...
     * @param page
     * @param users
     * @param startRow
     */
    private void checkUserTable(HtmlPage page, List<User> users, int startRow) {
        HtmlTable userTable = getTable(page, USER_TABLE_ID);
        int rowCount = userTable.getRowCount();
        assertTrue(rowCount == (startRow + users.size()));
        
        for (int rowIndex = startRow; rowIndex < rowCount; rowIndex++) {
            HtmlTableRow row = userTable.getRow(rowIndex);
            int index = rowIndex - startRow;
            User user = users.get(index);
            
            assertCellValue(row, 0, Integer.toString(index + 1));
            assertCellValue(row, 1, user.username);
            assertCellValue(row, 2, user.fullname);
            assertCellValue(row, 3, user.unitNumber);
            assertCellValue(row, 4, user.unitName);
            StringBuffer buf = new StringBuffer();
            for (String roleName : user.roleNames) {
                buf.append(roleName + " ");
            }
            assertCellValue(row, 5, buf.toString().trim());
        }
    }
    
    /**
     * Assert that a cell in a table has the expected string value.
     * @param row
     * @param cellIndex
     * @param expectedValue
     */
    private void assertCellValue(HtmlTableRow row, int cellIndex, String expectedValue) {
        HtmlTableCell cell = row.getCell(cellIndex);
        String value = cell.asText().trim();
        assertTrue("Expected: " + expectedValue + "; Found: " + value, StringUtils.equals(value, expectedValue));
    }
    
    /**
     * Checks the user names for a specific assigned role.  
     * @param page the Permissions Page
     * @param roleId the element id of the role to check
     * @param names the list of expected names (may be null)
     */
    private void checkUserNames(HtmlPage page, String roleId, String names[]) {
        String[] userNames = getUserNames(page, roleId);
        if (names == null) {
            assertTrue(userNames.length == 0);
        } else {
            assertTrue(userNames.length == names.length);
            for (String name : names) {
                assertTrue(contains(userNames, name));
            }
        }
    }

    /**
     * Determine if an array of strings contains a particular string.
     * @param list the array of strings to search through
     * @param item the string item to search for
     * @return true if the item is in the array; otherwise false
     */
    private boolean contains(String list[], String item) {
        for (String listItem : list) {
            if (StringUtils.equals(listItem, item)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Gets the user names for a specific assigned role.  The text
     * is extracted from the assigned role.  The user names are
     * easily split out because they are always separated by semi-colons.
     * 
     * @param page the Permissions Page
     * @param roleId the element id of the assigned role
     * @return the array of user names for the role
     */
    private String[] getUserNames(HtmlPage page, String roleId) {
        HtmlElement list = getElementById(page, roleId);
        String names = list.asText().trim();
        if (names.length() == 0) {
            return new String[0];
        }
        String nameArr[] = names.split("; ");
        for (int i = 0; i < nameArr.length; i++) {
            nameArr[i] = nameArr[i].trim();
        }
        return  nameArr;
    }
    
    /**
     * Build an array of string.
     * @param str the variable list of strings
     * @return the array of strings
     */
    private String[] buildArray(String ... str) {
        String arr[] = new String[str.length];
        for (int i = 0; i < str.length; i++) {
            arr[i] = str[i];
        }
        return arr;
    }
    
    /**
     * Add a narrative to the proposal.
     * @param page
     * @param type
     * @return
     * @throws Exception
     */
    private HtmlPage addNarrative(HtmlPage page, String type) throws Exception {
        page = clickOnTab(page, ABSTRACTS_ATTACHMENTS_LINK_NAME);
        assertTrue(!hasError(page));
        setFieldValue(page, "newNarrative.narrativeTypeCode", type);
        setFieldValue(page, "newNarrative.moduleStatusCode", "I");
        page = clickOn(page, "methodToCall.addProposalAttachment");
        return page;
    }
}

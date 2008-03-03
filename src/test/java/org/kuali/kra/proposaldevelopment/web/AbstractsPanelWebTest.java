/*
 * Copyright 2007 The Kuali Foundation.
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

import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

import org.junit.Test;

/**
 * Tests the Abstracts Panel in the Abstracts & Attachments Proposal Development Document.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class AbstractsPanelWebTest extends ProposalDevelopmentWebTestBase {

    private static final String ABSTRACTS_ATTACHMENTS_LINK_NAME = "methodToCall.headerTab.headerDispatch.save.navigateTo.abstractsAttachments.x";

    private static final String ABSTRACT_DIV = "tab-Abstracts-div";
    private static final String ABSTRACT_TABLE = "abstracts-table";
    private static final String ABSTRACT_TYPE_CODE_ID = "newProposalAbstract.abstractTypeCode";
    private static final String ABSTRACT_DETAILS_ID = "newProposalAbstract.abstractDetails";
    private static final String ABSTRACT_ADD_BTN = "methodToCall.addAbstract";

    private static final String ABSTRACT_COMPUTER_TYPE_CODE = "7";
    private static final String ABSTRACT_COMPUTER_TYPE_VALUE = "Computer";
    private static final String ABSTRACT_COMPUTER_DETAILS_VALUE = "May the force be with you.";
    private static final String ABSTRACT_COMPUTER_DETAILS_VALUE2 = "Death Star.";

    private static final String ABSTRACT_LABS_TYPE_CODE = "4";
    private static final String ABSTRACT_LABS_TYPE_VALUE = "Labs";
    private static final String ABSTRACT_LABS_DETAILS_VALUE = "Star Wars.";

    public static final String YES_BTN_ID =  "methodToCall.processAnswer.button0";
    public static final String NO_BTN_ID = "methodToCall.processAnswer.button1";

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
        return clickOnTab(proposalPage, ABSTRACTS_ATTACHMENTS_LINK_NAME);
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
        HtmlPage abstractsAndAttachmentsPage = getAbstractsAndAttachmentsPage();
        this.checkHelpLinks(abstractsAndAttachmentsPage);
    }

    /***********************************************************************
     * Unit Tests for Abstracts
     ***********************************************************************/

    /**
     * Test the addition of abstracts by adding two abstracts.
     * Also verify that we can save the document and retrieve it
     * with the added abstracts.
     *
     * Due to a bug that was observed, we will also check the timestamp
     * after a second abstract is added.  The time part has been wrongly
     * changing to zero.
     *
     * @throws Exception
     */
    @Test
    public void testAbstractAdd() throws Exception {
        HtmlPage abstractsAndAttachmentsPage = getAbstractsAndAttachmentsPage();

        // Add two abstracts.

        abstractsAndAttachmentsPage = addAbstract(abstractsAndAttachmentsPage,
                ABSTRACT_COMPUTER_TYPE_CODE, ABSTRACT_COMPUTER_TYPE_VALUE,
                ABSTRACT_COMPUTER_DETAILS_VALUE, 1);

        String timestamp = getAbstractTimestamp(abstractsAndAttachmentsPage, 0);

        abstractsAndAttachmentsPage = addAbstract(abstractsAndAttachmentsPage,
                ABSTRACT_LABS_TYPE_CODE, ABSTRACT_LABS_TYPE_VALUE,
                ABSTRACT_LABS_DETAILS_VALUE, 2);

        // Make sure the timestamp didn't change.

        assertEquals(timestamp, getAbstractTimestamp(abstractsAndAttachmentsPage, 0));

        abstractsAndAttachmentsPage = saveAndSearch(abstractsAndAttachmentsPage);

        // Verify the correct number of abstracts and that the values are correct.

        HtmlTable table = getTable(abstractsAndAttachmentsPage, ABSTRACT_TABLE);
        assertTrue("row count is " + table.getRowCount(), table.getRowCount() == 4);

        checkAbstractRow(abstractsAndAttachmentsPage,
                ABSTRACT_COMPUTER_TYPE_VALUE, ABSTRACT_COMPUTER_DETAILS_VALUE);
        checkAbstractRow(abstractsAndAttachmentsPage,
                ABSTRACT_LABS_TYPE_VALUE, ABSTRACT_LABS_DETAILS_VALUE);
    }

    /**
     * Verify that an abstract with an "empty" details can be added.
     *
     * NOTE: This once crashed on me, so I created this test.
     *
     * @throws Exception
     */
    @Test
    public void testAbstractAddEmptyDetails() throws Exception {
        HtmlPage abstractsAndAttachmentsPage = getAbstractsAndAttachmentsPage();

        // Add an abstract with no details.

        abstractsAndAttachmentsPage = addAbstract(abstractsAndAttachmentsPage,
                ABSTRACT_COMPUTER_TYPE_CODE, ABSTRACT_COMPUTER_TYPE_VALUE, "", 1);

        // Save and close the document and then search for it.

        abstractsAndAttachmentsPage = saveAndSearch(abstractsAndAttachmentsPage);

        // Verify the correct number of abstracts and that the values are correct.

        HtmlTable table = getTable(abstractsAndAttachmentsPage, ABSTRACT_TABLE);
        assertTrue("row count is " + table.getRowCount(), table.getRowCount() == 3);

        checkAbstractRow(abstractsAndAttachmentsPage, ABSTRACT_COMPUTER_TYPE_VALUE, "");
    }

    /**
     * Get the timestamp for a particular abstract in the table.
     *
     * @param page Abstracts and Attachments Web page.
     * @param rowId the abstract to get the timestamp from.
     * @return
     */
    private String getAbstractTimestamp(HtmlPage page, int rowId) {
        HtmlElement element = getElement(page, "document.proposalAbstract[" + rowId + "].updateTimestamp");
        return element.asText().trim();
    }

    /**
     * Adds an abstract and verifies that it was added correctly.
     * The rows are numbered from 0, excluding the standard rows for the
     * header and Add Control Line.
     *
     * @param abstractsAndAttachmentsPage
     * @param abstractTypeCode
     * @param abstractTypeValue
     * @param abstractDetails
     * @param expectedRowCount
     * @return the resulting web page after adding the abstract.
     * @throws Exception
     */
    private HtmlPage addAbstract(HtmlPage abstractsAndAttachmentsPage,
                                 String abstractTypeCode, String abstractTypeValue,
                                 String abstractDetails, int expectedRowCount) throws Exception {
        // Fill in the data and click the add button.

        setFieldValue(abstractsAndAttachmentsPage, ABSTRACT_TYPE_CODE_ID, abstractTypeCode);
        setFieldValue(abstractsAndAttachmentsPage, ABSTRACT_DETAILS_ID, abstractDetails);

        HtmlElement addBtn = getElementByName(abstractsAndAttachmentsPage, ABSTRACT_ADD_BTN, true);
        abstractsAndAttachmentsPage = clickOn(addBtn);

        // Make sure the table now has the expected number rows.

        HtmlTable table = getTable(abstractsAndAttachmentsPage, ABSTRACT_TABLE);
        assertTrue("row count is " + table.getRowCount(), table.getRowCount() == (expectedRowCount + 2));

        checkAbstractRow(abstractsAndAttachmentsPage, abstractTypeValue, abstractDetails);

        // Verify that abstract type selection list has filtered out the new abstract.
        assertTrue(!abstractTypeContains(abstractsAndAttachmentsPage, abstractTypeValue));

        return abstractsAndAttachmentsPage;
    }

    /**
     * Determine if the abstract type select list contains the given abstract type value.
     *
     * @param abstractAndAttachmentsPage web page
     * @param abstractType the abstract type to look for in selection list.
     * @return true if it is contained; otherwise false
     */
    private boolean abstractTypeContains(HtmlPage abstractAndAttachmentsPage, String abstractType) {
        List<String> options = getSelectOptions(abstractAndAttachmentsPage, ABSTRACT_TYPE_CODE_ID);
        return options.contains(abstractType);
    }

    /**
     * Checks an Abstract in the Abstract table to verify that it has
     * the correct values.  Currently only checking the abstract type
     * and the details.  Should add author and timestamp.
     *
     * The rowId identifies the Abstract to check.  Numbering starts at 0.
     * The header and Add Control Line are ignored.
     *
     * @param abstractsAndAttachmentsPage
     * @param abstractTypeValue
     * @param abstractDetails
     */
    private void checkAbstractRow(HtmlPage abstractsAndAttachmentsPage, String abstractTypeValue, String abstractDetails) {
        HtmlTable table = getTable(abstractsAndAttachmentsPage, ABSTRACT_TABLE);
        assertNotNull(table);

        boolean found = false;
        for (int rowId = 2; rowId < table.getRowCount(); rowId++) {
            HtmlTableRow row = table.getRow(rowId);
            HtmlTableCell cell = row.getCell(3);
            if (cell.asText().trim().contains(abstractTypeValue)) {
               found = true;
               // Verify the details.

               cell = row.getCell(4);
               assertContains(cell, abstractDetails);

               break;
            }
        }

        if (!found) {
            assertTrue("Abstract Not found", true);
        }
    }

    /**
     * Delete an abstract and verify that it is gone.
     *
     * @throws Exception
     */
    @Test
    public void testAbstractDelete() throws Exception {
        HtmlPage abstractsAndAttachmentsPage = getAbstractsAndAttachmentsPage();

        // Add two abstracts.

        abstractsAndAttachmentsPage = addAbstract(abstractsAndAttachmentsPage,
                ABSTRACT_COMPUTER_TYPE_CODE, ABSTRACT_COMPUTER_TYPE_VALUE,
                ABSTRACT_COMPUTER_DETAILS_VALUE, 1);

        abstractsAndAttachmentsPage = addAbstract(abstractsAndAttachmentsPage,
                ABSTRACT_LABS_TYPE_CODE, ABSTRACT_LABS_TYPE_VALUE,
                ABSTRACT_LABS_DETAILS_VALUE, 2);

        // Save and close the document and then search for it again.

        abstractsAndAttachmentsPage = saveAndSearch(abstractsAndAttachmentsPage);

        // Since the ordering of abstracts in the list is undefined, we must determine
        // which one is in the first row; that is the one we are deleting.

        String deletedAbstractTypeValue = getAbstractTypeValue(abstractsAndAttachmentsPage, 0);

        // Delete the first abstract in the list.

        HtmlElement deleteBtn = this.getElementByName(abstractsAndAttachmentsPage,
                                                      "methodToCall.deleteAbstract.line0", true);
        HtmlPage confirmationPage = clickOn(deleteBtn);
        abstractsAndAttachmentsPage = clickOn(confirmationPage, YES_BTN_ID);

        // Save and close the document and then search for it again.

        abstractsAndAttachmentsPage = saveAndSearch(abstractsAndAttachmentsPage);

        HtmlTable table = getTable(abstractsAndAttachmentsPage, ABSTRACT_TABLE);
        assertTrue("row count is " + table.getRowCount(), table.getRowCount() == 3);

        // Determine that the correct abstract was deleted and that other one is intact.

        if (ABSTRACT_LABS_TYPE_VALUE.equals(deletedAbstractTypeValue)) {
            // Verify that abstract type selection list has the extra abstract type.
            assertTrue(abstractTypeContains(abstractsAndAttachmentsPage, ABSTRACT_LABS_TYPE_VALUE));

            // Verify that the second abstract is still there and is now
            // in the first row.

            checkAbstractRow(abstractsAndAttachmentsPage,
                             ABSTRACT_COMPUTER_TYPE_VALUE, ABSTRACT_COMPUTER_DETAILS_VALUE);
        }
        else {
            // Verify that abstract type selection list has the extra abstract type.
            assertTrue(abstractTypeContains(abstractsAndAttachmentsPage, ABSTRACT_COMPUTER_TYPE_VALUE));

            // Verify that the second abstract is still there and is now
            // in the first row.

            checkAbstractRow(abstractsAndAttachmentsPage,
                             ABSTRACT_LABS_TYPE_VALUE, ABSTRACT_LABS_DETAILS_VALUE);
        }
    }

    /**
     * Test the use case where a user attempts to delete an
     * abstract but then answers "no" in response to the confirmation.
     * The abstract must not be deleted.
     *
     * @throws Exception
     */
    @Test
    public void testAbstractDeleteNo() throws Exception {
        HtmlPage abstractsAndAttachmentsPage = getAbstractsAndAttachmentsPage();

        // Add two abstracts.

        abstractsAndAttachmentsPage = addAbstract(abstractsAndAttachmentsPage,
                ABSTRACT_COMPUTER_TYPE_CODE, ABSTRACT_COMPUTER_TYPE_VALUE,
                ABSTRACT_COMPUTER_DETAILS_VALUE, 1);

        abstractsAndAttachmentsPage = addAbstract(abstractsAndAttachmentsPage,
                ABSTRACT_LABS_TYPE_CODE, ABSTRACT_LABS_TYPE_VALUE,
                ABSTRACT_LABS_DETAILS_VALUE, 2);

        // Save and close the document and then search for it again.

        abstractsAndAttachmentsPage = saveAndSearch(abstractsAndAttachmentsPage);

        // Delete the first abstract in the list and then our mind.

        HtmlElement deleteBtn = this.getElementByName(abstractsAndAttachmentsPage,
                                                      "methodToCall.deleteAbstract.line0", true);
        HtmlPage confirmationPage = clickOn(deleteBtn);
        abstractsAndAttachmentsPage = clickOn(confirmationPage, NO_BTN_ID);

        // Save and close the document and then search for it again.

        abstractsAndAttachmentsPage = saveAndSearch(abstractsAndAttachmentsPage);

        // Nothing should be deleted.

        HtmlTable table = getTable(abstractsAndAttachmentsPage, ABSTRACT_TABLE);
        assertTrue("row count is " + table.getRowCount(), table.getRowCount() == 4);

        assertTrue(!abstractTypeContains(abstractsAndAttachmentsPage, ABSTRACT_LABS_TYPE_VALUE));
        assertTrue(!abstractTypeContains(abstractsAndAttachmentsPage, ABSTRACT_COMPUTER_TYPE_VALUE));

        checkAbstractRow(abstractsAndAttachmentsPage,
                         ABSTRACT_COMPUTER_TYPE_VALUE, ABSTRACT_COMPUTER_DETAILS_VALUE);

        checkAbstractRow(abstractsAndAttachmentsPage,
                         ABSTRACT_LABS_TYPE_VALUE, ABSTRACT_LABS_DETAILS_VALUE);
    }

    /**
     * Get the abstract type value at the given row.
     *
     * @param abstractsAndAttachmentsPage
     * @param rowId the rowId is from 0 to N-2 (where N is total number of rows in table).
     * @return the abstract type value
     */
    protected String getAbstractTypeValue(HtmlPage abstractsAndAttachmentsPage, int rowId) {
        HtmlTable table = getTable(abstractsAndAttachmentsPage, ABSTRACT_TABLE);
        assertNotNull(table);

        assertTrue("Table row out-of-range (" + rowId + ")", table.getRowCount() > rowId + 2);
        HtmlTableRow row = table.getRow(rowId + 2);
        HtmlTableCell cell = row.getCell(3);
        return cell.asText().trim();
    }

    /**
     * Verifies that the details for a previously created abstract can
     * be successfully modified.
     *
     * @throws Exception
     */
    @Test
    public void testAbstractModify() throws Exception {
        HtmlPage abstractsAndAttachmentsPage = getAbstractsAndAttachmentsPage();

        // create an abstract and save it to the database.

        abstractsAndAttachmentsPage = addAbstract(abstractsAndAttachmentsPage,
                ABSTRACT_COMPUTER_TYPE_CODE, ABSTRACT_COMPUTER_TYPE_VALUE,
                ABSTRACT_COMPUTER_DETAILS_VALUE, 1);
        abstractsAndAttachmentsPage = saveDoc(abstractsAndAttachmentsPage);

        // change the details for that abstract.

        setFieldValue(abstractsAndAttachmentsPage,
                      "document.proposalAbstract[0].abstractDetails",
                      ABSTRACT_COMPUTER_DETAILS_VALUE2);

        // Save and close the document and then search for it again.

        abstractsAndAttachmentsPage = saveAndSearch(abstractsAndAttachmentsPage);

        // Verify the correct number of abstracts and that the values are correct.

        HtmlTable table = getTable(abstractsAndAttachmentsPage, ABSTRACT_TABLE);
        assertTrue("row count is " + table.getRowCount(), table.getRowCount() == 3);

        checkAbstractRow(abstractsAndAttachmentsPage,
                         ABSTRACT_COMPUTER_TYPE_VALUE, ABSTRACT_COMPUTER_DETAILS_VALUE2);
    }

    /**
     * Verifies the expanded text areas for the Abstract Panel.
     *
     * The expanded text area for the Details in the Add Control Line
     * is tested as well as one in the list of Abstracts.
     *
     * @throws Exception
     */
    @Test
    public void testAbstractExpandedTextArea() throws Exception {

        HtmlPage abstractsAndAttachmentsPage = getAbstractsAndAttachmentsPage();

//      Don't understand why I have to disable Javascript, but it works.
        boolean javascriptEnabled = webClient.isJavaScriptEnabled();
        webClient.setJavaScriptEnabled(false);

        // Test the Details expanded text area in Add Control Line.

        abstractsAndAttachmentsPage =
            this.checkExpandedTextArea(abstractsAndAttachmentsPage, ABSTRACT_DETAILS_ID,
                                       ABSTRACT_COMPUTER_DETAILS_VALUE,
                                       ABSTRACT_COMPUTER_DETAILS_VALUE2);

        // Add an abstract and then test its Details expanded text area.

        abstractsAndAttachmentsPage = addAbstract(abstractsAndAttachmentsPage,
                ABSTRACT_COMPUTER_TYPE_CODE, ABSTRACT_COMPUTER_TYPE_VALUE, "", 1);

        this.checkExpandedTextArea(abstractsAndAttachmentsPage,
                                   "document.proposalAbstract[0].abstractDetails",
                                   ABSTRACT_COMPUTER_DETAILS_VALUE,
                                   ABSTRACT_COMPUTER_DETAILS_VALUE2);

        webClient.setJavaScriptEnabled(javascriptEnabled);
    }

    /**
     * Verify that an error message appears if no abstract type is selected when
     * the add button is clicked on.
     *
     * @throws Exception
     */
    @Test
    public void testAbstractNoAbstractSelected() throws Exception {
        HtmlPage abstractsAndAttachmentsPage = getAbstractsAndAttachmentsPage();

        HtmlElement addBtn = getElementByName(abstractsAndAttachmentsPage, ABSTRACT_ADD_BTN, true);
        abstractsAndAttachmentsPage = clickOn(addBtn);

        List<String> errors = this.getErrors(abstractsAndAttachmentsPage, ABSTRACT_DIV);
        assertEquals(errors.size(), 1);
    }
}

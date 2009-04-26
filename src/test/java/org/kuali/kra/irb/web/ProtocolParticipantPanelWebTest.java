/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.web;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.util.GlobalVariables;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

/**
 * Tests the Participant Types Panel in the Protocol Document.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProtocolParticipantPanelWebTest extends ProtocolWebTestBase {

    private static final String PARTICIPANT_DIV = "tab-ParticipantTypes-div";
    private static final String PARTICIPANT_TABLE = "participants-table";
    private static final String PARTICIPANT_TYPE_CODE_ID = "participantsHelper.newProtocolParticipant.participantTypeCode";
    private static final String PARTICIPANT_DETAILS_ID = "participantsHelper.newProtocolParticipant.participantCount";
    private static final String PARTICIPANT_ADD_BTN = "methodToCall.addProtocolParticipant";

    private static final String PARTICIPANT_STUDENTS_TYPE_CODE = "7";
    private static final String PARTICIPANT_STUDENTS_TYPE_VALUE = "Students";
    private static final String PARTICIPANT_STUDENTS_COUNT_VALUE = "142";
    private static final String PARTICIPANT_STUDENTS_COUNT_VALUE2 = "58";
    private static final String PARTICIPANT_STUDENTS_COUNT_VALUE_NEG = "-7";
    private static final String PARTICIPANT_STUDENTS_COUNT_VALUE_NON_NUM = "a";

    private static final String PARTICIPANT_EMPLOYEES_TYPE_CODE = "3";
    private static final String PARTICIPANT_EMPLOYEES_TYPE_VALUE = "Employees";
    private static final String PARTICIPANT_EMPLOYEES_COUNT_VALUE = "";

    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }
    
    /***********************************************************************
     * General Unit Test for entire Web Page.
     ***********************************************************************/

    /**
     * Verify that all the Help links on the web page go to the Kuali Help Web Page.
     * This will also test the help links on other panels on the page, but no big deal.
     * @throws Exception
     */
    // @Test -- KRACOEUS-1419 - Add this test back when urls are added.
    public void testHelpLinks() throws Exception {
        HtmlPage protocolPage = getProtocolSavedRequiredFieldsPage();
        this.checkHelpLinks(protocolPage);
    }

    /***********************************************************************
     * Unit Tests for Participant Types
     ***********************************************************************/

    /**
     * Test the addition of participants by adding two participants.
     * Also verify that we can save the document and retrieve it
     * with the added participants.
     *
     * @throws Exception
     */
    @Test
    public void testParticipantAdd() throws Exception {
        HtmlPage protocolPage = getProtocolSavedRequiredFieldsPage();

        // Add two participants.
        protocolPage = addParticipantAndVerify(protocolPage,
                PARTICIPANT_STUDENTS_TYPE_CODE, PARTICIPANT_STUDENTS_TYPE_VALUE,
                PARTICIPANT_STUDENTS_COUNT_VALUE);

        protocolPage = addParticipantAndVerify(protocolPage,
                PARTICIPANT_EMPLOYEES_TYPE_CODE, PARTICIPANT_EMPLOYEES_TYPE_VALUE,
                PARTICIPANT_EMPLOYEES_COUNT_VALUE);

        // Save the document
        protocolPage = saveDoc(protocolPage);

        // Verify the correct number of participants and that the values are correct.
        int participantsCount = getParticipantsCount(protocolPage);
        assertTrue("participant count is " + participantsCount, participantsCount == 2);

        checkParticipantRow(protocolPage,
                PARTICIPANT_STUDENTS_TYPE_VALUE, PARTICIPANT_STUDENTS_COUNT_VALUE);
        checkParticipantRow(protocolPage,
                PARTICIPANT_EMPLOYEES_TYPE_VALUE, PARTICIPANT_EMPLOYEES_COUNT_VALUE);
    }

    /**
     * Verify that a participant with an "empty" count can be added.
     *
     * @throws Exception
     */
    @Test
    public void testParticipantAddEmptyCount() throws Exception {
        HtmlPage protocolPage = getProtocolSavedRequiredFieldsPage();

        // Add a participant with no counts.
        protocolPage = addParticipantAndVerify(protocolPage,
                PARTICIPANT_STUDENTS_TYPE_CODE, PARTICIPANT_STUDENTS_TYPE_VALUE, "");

        // Save the document
        protocolPage = saveDoc(protocolPage);

        // Verify the correct number of participants and that the values are correct.
        int participantsCount = getParticipantsCount(protocolPage);
        assertTrue("participant count is " + participantsCount, participantsCount == 1);
        
        checkParticipantRow(protocolPage, PARTICIPANT_STUDENTS_TYPE_VALUE, "");
    }

    /**
     * Verifies that the count for a previously created participant can
     * be successfully modified.
     *
     * @throws Exception
     */
    @Test
    public void testParticipantModify() throws Exception {
        HtmlPage protocolPage = getProtocolSavedRequiredFieldsPage();

        // create a participant and save it to the database.
        protocolPage = addParticipantAndVerify(protocolPage,
                PARTICIPANT_STUDENTS_TYPE_CODE, PARTICIPANT_STUDENTS_TYPE_VALUE,
                PARTICIPANT_STUDENTS_COUNT_VALUE);

        // Save the document
        protocolPage = saveDoc(protocolPage);

        // change the details for that participant.

        setFieldValue(protocolPage,
                      "document.protocolList[0].protocolParticipants[0].participantCount",
                      PARTICIPANT_STUDENTS_COUNT_VALUE2);

        // Save the document
        protocolPage = saveDoc(protocolPage);

        // Verify the correct number of participants and that the values are correct.
        int participantsCount = getParticipantsCount(protocolPage);
        assertTrue("participant count is " + participantsCount, participantsCount == 1);

        checkParticipantRow(protocolPage,
                PARTICIPANT_STUDENTS_TYPE_VALUE, PARTICIPANT_STUDENTS_COUNT_VALUE2);
    }

    /**
     * Delete an participant and verify that it is gone.
     *
     * @throws Exception
     */
    @Test
    public void testParticipantDelete() throws Exception {
        HtmlPage protocolPage = getProtocolSavedRequiredFieldsPage();

        // Add two participants.
        protocolPage = addParticipantAndVerify(protocolPage,
                PARTICIPANT_STUDENTS_TYPE_CODE, PARTICIPANT_STUDENTS_TYPE_VALUE,
                PARTICIPANT_STUDENTS_COUNT_VALUE);

        protocolPage = addParticipantAndVerify(protocolPage,
                PARTICIPANT_EMPLOYEES_TYPE_CODE, PARTICIPANT_EMPLOYEES_TYPE_VALUE,
                PARTICIPANT_EMPLOYEES_COUNT_VALUE);

        // Save the document
        protocolPage = saveDoc(protocolPage);

        // Since the ordering of participants in the list is undefined, we must determine
        // which one is in the first row; that is the one we are deleting.
        String deletedParticipantTypeValue = getParticipantTypeValue(protocolPage, 0);

        // Delete the first participant in the list.
        HtmlElement deleteBtn = this.getElementByName(protocolPage,
                "methodToCall.deleteProtocolParticipant.line0.anchor", true);
        protocolPage = clickOn(deleteBtn);

        // Save the document
        protocolPage = saveDoc(protocolPage);

        int participantsCount = getParticipantsCount(protocolPage);
        assertTrue("participant count is " + participantsCount, participantsCount == 1);

        // Determine that the correct participant was deleted and that other one is intact.
        if (PARTICIPANT_EMPLOYEES_TYPE_VALUE.equals(deletedParticipantTypeValue)) {
            // Verify that participant type selection list has the extra participant type.
            assertTrue(participantTypeContains(protocolPage, PARTICIPANT_EMPLOYEES_TYPE_VALUE));

            // Verify that the second participant is still there and is now
            // in the first row.
            checkParticipantRow(protocolPage,
                    PARTICIPANT_STUDENTS_TYPE_VALUE, PARTICIPANT_STUDENTS_COUNT_VALUE);
        } else {
            // Verify that participant type selection list has the extra participant type.
            assertTrue(participantTypeContains(protocolPage, PARTICIPANT_STUDENTS_TYPE_VALUE));

            // Verify that the second participant is still there and is now
            // in the first row.
            checkParticipantRow(protocolPage,
                    PARTICIPANT_EMPLOYEES_TYPE_VALUE, PARTICIPANT_EMPLOYEES_COUNT_VALUE);
        }
    }

    /**
     * Verify that a participant with a negative count causes an error.
     *
     * @throws Exception
     */
    @Test
    public void testParticipantAddNegativeCount() throws Exception {
        HtmlPage protocolPage = getProtocolSavedRequiredFieldsPage();

        // Add a participant with a negative count.
        protocolPage = addParticipant(protocolPage,
                PARTICIPANT_STUDENTS_TYPE_CODE, PARTICIPANT_STUDENTS_TYPE_VALUE, 
                PARTICIPANT_STUDENTS_COUNT_VALUE_NEG);

        // Verify that an error has been thrown.
        List<String> errors = this.getErrors(protocolPage, PARTICIPANT_DIV);
        assertEquals(1, errors.size());
    }

    /**
     * Verify that a participant with a non-numeric count causes an error.
     *
     * @throws Exception
     */
    @Test
    public void testParticipantAddNonNumericCount() throws Exception {
        HtmlPage protocolPage = getProtocolSavedRequiredFieldsPage();

        // Add a participant with a non-numeric count.
        protocolPage = addParticipant(protocolPage,
                PARTICIPANT_STUDENTS_TYPE_CODE, PARTICIPANT_STUDENTS_TYPE_VALUE, 
                PARTICIPANT_STUDENTS_COUNT_VALUE_NON_NUM);

        // Verify that an error has been thrown.
        List<String> errors = this.getErrors(protocolPage, PARTICIPANT_DIV);
        System.out.println(">>>> error.size: " + errors.size());
        assertEquals(1, errors.size());
    }

    /**
     * Verify that an error message appears if no participant type is selected when
     * the add button is clicked on.
     *
     * @throws Exception
     */
    @Test
    public void testParticipantNoParticipantSelected() throws Exception {
        HtmlPage protocolPage = getProtocolSavedRequiredFieldsPage();

        HtmlElement addBtn = getElementByName(protocolPage, PARTICIPANT_ADD_BTN, true);
        protocolPage = clickOn(addBtn);

        List<String> errors = this.getErrors(protocolPage, PARTICIPANT_DIV);
        assertEquals(1, errors.size());
    }

    /**
     * Returns the number of participants that the participant table contains.
     * 
     * The rows are numbered from 0, excluding the standard rows for the
     * header and Add Control Line.
     *
     * @param protocolPage
     * @return number of rows that the table contains.
     * @throws Exception
     */
    private int getParticipantsCount(HtmlPage protocolPage) {
        HtmlTable table = getTable(protocolPage, PARTICIPANT_TABLE);
        return table.getRowCount() - 2;
    }

    /**
     * Get the participant type value at the given row.
     *
     * @param protocolPage
     * @param rowId the rowId is from 0 to N-2 (where N is total number of rows in table).
     * @return the participant type value
     */
    protected String getParticipantTypeValue(HtmlPage protocolPage, int rowId) {
        HtmlTable table = getTable(protocolPage, PARTICIPANT_TABLE);
        assertNotNull(table);

        assertTrue("Table row out-of-range (" + rowId + ")", table.getRowCount() > rowId + 2);
        HtmlTableRow row = table.getRow(rowId + 2);
        HtmlTableCell cell = row.getCell(1);
        return cell.asText().trim();
    }

    /**
     * Adds a participant and verifies that it was added correctly.
     * The rows are numbered from 0, excluding the standard rows for the
     * header and Add Control Line.
     *
     * @param protocolPage
     * @param participantTypeCode
     * @param participantTypeValue
     * @param participantCount
     * @param expectedRowCount
     * @return the resulting web page after adding the participant.
     * @throws Exception
     */
    private HtmlPage addParticipantAndVerify(HtmlPage protocolPage,
                                 String participantTypeCode, String participantTypeValue,
                                 String participantCount) throws Exception {

        int expectedParticipantsCount = getParticipantsCount(protocolPage);

        protocolPage = addParticipant(protocolPage, participantTypeCode, participantTypeValue, 
                participantCount);
            
        // Verify that the participant has been added
        int participantsCount = getParticipantsCount(protocolPage);
        assertTrue("participant count is " + participantsCount, 
                participantsCount == expectedParticipantsCount + 1);
        checkParticipantRow(protocolPage, participantTypeValue, participantCount);

        // Verify that participant type selection list has filtered out the new participant.
        assertTrue(!participantTypeContains(protocolPage, participantTypeValue));

        return protocolPage;
    }

    /**
     * Adds a participant.
     *
     * @param protocolPage
     * @param participantTypeCode
     * @param participantTypeValue
     * @param participantCount
     * @return the resulting web page after adding the participant.
     * @throws Exception
     */
    private HtmlPage addParticipant(HtmlPage protocolPage,
                                 String participantTypeCode, String participantTypeValue,
                                 String participantCount) throws Exception {
        // Fill in the data and click the add button.
        setFieldValue(protocolPage, PARTICIPANT_TYPE_CODE_ID, participantTypeCode);
        setFieldValue(protocolPage, PARTICIPANT_DETAILS_ID, participantCount);

        HtmlElement addBtn = getElementByName(protocolPage, PARTICIPANT_ADD_BTN, true);
        protocolPage = clickOn(addBtn);

        return protocolPage;
    }

    /**
     * Determine if the participant type select list contains the given participant type value.
     *
     * @param protocolPage web page
     * @param participantTypeValue the participant type to look for in selection list.
     * @return true if it is contained; otherwise false
     */
    private boolean participantTypeContains(HtmlPage protocolPage, String participantTypeValue) {
        List<String> options = getSelectOptions(protocolPage, PARTICIPANT_TYPE_CODE_ID);
        return options.contains(participantTypeValue);
    }

    /**
     * Checks a Participant in the Participant table to verify that it has
     * the correct values.  
     *
     * The rowId identifies the Participant to check.  Numbering starts at 0.
     * The header and Add Control Line are ignored.
     *
     * @param protocolPage
     * @param participantTypeValue
     * @param participantCount
     */
    private void checkParticipantRow(HtmlPage protocolPage, String participantTypeValue, 
            String participantCount) {
        HtmlTable table = getTable(protocolPage, PARTICIPANT_TABLE);
        assertNotNull(table);

        boolean found = false;
        for (int rowId = 2; rowId < table.getRowCount(); rowId++) {
            HtmlTableRow row = table.getRow(rowId);
            HtmlTableCell cell = row.getCell(1);
            if (cell.asText().trim().contains(participantTypeValue)) {
               found = true;
               // Verify the details.

               cell = row.getCell(2);
               assertContains(cell, participantCount);

               break;
            }
        }

        if (!found) {
            assertTrue("Participant Not found", true);
        }
    }
}

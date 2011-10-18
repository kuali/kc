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
package org.kuali.kra.irb.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.irb.protocol.participant.ProtocolParticipant;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

/**
 * Tests the Participant tab in the Protocol page of a Protocol.
 */
public class ProtocolParticipantPanelSeleniumTest extends KcSeleniumTestBase {

    private static final String TAB_ID = "Participant Types";
    private static final String ERROR_PANEL_ID = "tab-ParticipantTypes-div";
    private static final String TABLE_ID = "participants-table";
    
    private static final String HELPER_PREFIX = "protocolHelper.newProtocolParticipant.";
    private static final String LIST_PREFIX = "document.protocolList[0].protocolParticipants[0].";
    
    private static final String PARTICIPANT_TYPE_CODE_ID = "participantTypeCode";
    private static final String PARTICPANT_COUNT_ID = "participantCount";
    private static final String HELPER_PARTICIPANT_TYPE_CODE_ID = HELPER_PREFIX + PARTICIPANT_TYPE_CODE_ID;
    private static final String HELPER_PARTICIPANT_COUNT_ID = HELPER_PREFIX + PARTICPANT_COUNT_ID;
    private static final String LIST_PARTICIPANT_COUNT_ID = LIST_PREFIX + PARTICPANT_COUNT_ID;
    
    private static final String PARTICIPANT_TYPE_CODE_PRISONERS = "Prisoners";
    private static final String PARTICIPANT_TYPE_CODE_STUDENTS = "Students";
    private static final String PARTICIPANT_COUNT_1 = "142";
    private static final String PARTICIPANT_COUNT_2 = "58";
    private static final String PARTICIPANT_COUNT_EMPTY = "0";
    private static final String PARTICIPANT_COUNT_NEGATIVE = "-7";
    private static final String PARTICIPANT_COUNT_NON_NUMERIC = "a";
    
    private static final String ADD_PROTOCOL_PARTICIPANT_BUTTON = "methodToCall.addProtocolParticipant";
    private static final String DELETE_PROTOCOL_PARTICIPANT_BUTTON = "methodToCall.deleteProtocolParticipant.line%d";

    private ProtocolSeleniumHelper helper;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        helper = ProtocolSeleniumHelper.instance(driver);
    }
    
    @After
    public void tearDown() throws Exception {
        helper = null;
        
        super.tearDown();
    }

    /**
     * Test adding a participant with a non-empty count.
     *
     * @throws Exception
     */
    @Test
    public void testAddParticipantNonEmptyCount() throws Exception {
        helper.createProtocol();
        
        helper.openTab(TAB_ID);
        
        helper.assertTableRowCount(TABLE_ID, 2);
        
        helper.set(HELPER_PARTICIPANT_TYPE_CODE_ID, PARTICIPANT_TYPE_CODE_STUDENTS);
        helper.set(HELPER_PARTICIPANT_COUNT_ID, PARTICIPANT_COUNT_1);

        helper.click(ADD_PROTOCOL_PARTICIPANT_BUTTON);
            
        helper.assertTableRowCount(TABLE_ID, 3);
        
        helper.assertTableCellValueContains(TABLE_ID, PARTICIPANT_TYPE_CODE_STUDENTS);
        helper.assertElementContains(LIST_PARTICIPANT_COUNT_ID, PARTICIPANT_COUNT_1);

        helper.assertOptionsDoNotContain(HELPER_PARTICIPANT_TYPE_CODE_ID, PARTICIPANT_TYPE_CODE_STUDENTS);

        helper.saveDocument();
        helper.assertNoPageErrors();

        helper.assertTableRowCount(TABLE_ID, 3);
        
        helper.assertTableCellValueContains(TABLE_ID, PARTICIPANT_TYPE_CODE_STUDENTS);
        helper.assertElementContains(LIST_PARTICIPANT_COUNT_ID, PARTICIPANT_COUNT_1);
        
        helper.assertOptionsDoNotContain(HELPER_PARTICIPANT_TYPE_CODE_ID, PARTICIPANT_TYPE_CODE_STUDENTS);
    }

    /**
     * Test adding a participant with an empty count.
     *
     * @throws Exception
     */
    public void testAddParticipantEmptyCount() throws Exception {
        helper.createProtocol();
        
        helper.openTab(TAB_ID);
        
        helper.set(HELPER_PARTICIPANT_TYPE_CODE_ID, PARTICIPANT_TYPE_CODE_PRISONERS);
        helper.set(HELPER_PARTICIPANT_COUNT_ID, PARTICIPANT_COUNT_EMPTY);

        helper.click(ADD_PROTOCOL_PARTICIPANT_BUTTON);
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertTableCellValueContains(TABLE_ID, PARTICIPANT_TYPE_CODE_PRISONERS);
        helper.assertTableCellValueContains(TABLE_ID, PARTICIPANT_COUNT_EMPTY);
    }
    
    /**
     * Test adding a participant with no information and verify the error.
     *
     * @throws Exception
     */
    @Test
    public void testAddParticipantBlank() throws Exception {
        helper.createProtocol();
        
        helper.openTab(TAB_ID);

        helper.click(ADD_PROTOCOL_PARTICIPANT_BUTTON);

        helper.assertErrorCount(ERROR_PANEL_ID, 1);
    }

    /**
     * Test modifying a participant.
     *
     * @throws Exception
     */
    @Test
    public void testModifyParticipant() throws Exception {
        helper.createProtocol();
        
        helper.openTab(TAB_ID);
        
        helper.set(HELPER_PARTICIPANT_TYPE_CODE_ID, PARTICIPANT_TYPE_CODE_STUDENTS);
        helper.set(HELPER_PARTICIPANT_COUNT_ID, PARTICIPANT_COUNT_1);

        helper.click(ADD_PROTOCOL_PARTICIPANT_BUTTON);
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertTableCellValueContains(TABLE_ID, PARTICIPANT_TYPE_CODE_STUDENTS);
        helper.assertElementContains(LIST_PARTICIPANT_COUNT_ID, PARTICIPANT_COUNT_1);

        helper.set(LIST_PARTICIPANT_COUNT_ID, PARTICIPANT_COUNT_2);

        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertTableCellValueContains(TABLE_ID, PARTICIPANT_TYPE_CODE_STUDENTS);
        helper.assertElementContains(LIST_PARTICIPANT_COUNT_ID, PARTICIPANT_COUNT_2);
    }

    /**
     * Test deleting a participant.
     *
     * @throws Exception
     */
    @Test
    public void testDeleteParticipant() throws Exception {
        helper.createProtocol();
        
        helper.openTab(TAB_ID);
        
        helper.assertTableRowCount(TABLE_ID, 2);
        
        helper.set(HELPER_PARTICIPANT_TYPE_CODE_ID, PARTICIPANT_TYPE_CODE_STUDENTS);
        helper.set(HELPER_PARTICIPANT_COUNT_ID, PARTICIPANT_COUNT_1);

        helper.click(ADD_PROTOCOL_PARTICIPANT_BUTTON);
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertTableRowCount(TABLE_ID, 3);
        
        helper.assertTableCellValueContains(TABLE_ID, PARTICIPANT_TYPE_CODE_STUDENTS);
        helper.assertElementContains(LIST_PARTICIPANT_COUNT_ID, PARTICIPANT_COUNT_1);

        helper.click(String.format(DELETE_PROTOCOL_PARTICIPANT_BUTTON, 0));

        helper.saveDocument();
        helper.assertNoPageErrors();

        helper.assertTableRowCount(TABLE_ID, 2);
    }

    /**
     * Test adding a participant with a negative count and verify the error.
     *
     * @throws Exception
     */
    @Test
    public void testAddParticipantNegativeCount() throws Exception {
        helper.createProtocol();
        
        helper.openTab(TAB_ID);
        
        helper.set(HELPER_PARTICIPANT_TYPE_CODE_ID, PARTICIPANT_TYPE_CODE_STUDENTS);
        helper.set(HELPER_PARTICIPANT_COUNT_ID, PARTICIPANT_COUNT_NEGATIVE);

        helper.click(ADD_PROTOCOL_PARTICIPANT_BUTTON);

        helper.assertErrorCount(ERROR_PANEL_ID, 1);
    }

    /**
     * Test adding a participant with a non-numeric count and verify the error.
     *
     * @throws Exception
     */
    @Test
    public void testAddParticipantNonNumericCount() throws Exception {
        helper.createProtocol();
        
        helper.openTab(TAB_ID);

        helper.set(HELPER_PARTICIPANT_TYPE_CODE_ID, PARTICIPANT_TYPE_CODE_STUDENTS);
        helper.set(HELPER_PARTICIPANT_COUNT_ID, PARTICIPANT_COUNT_NON_NUMERIC);

        helper.click(ADD_PROTOCOL_PARTICIPANT_BUTTON);

        helper.assertErrorCount(ERROR_PANEL_ID, 1);
    }
    
    /**
     * Verify that the participant help link works.
     * 
     * @throws Exception
     */
    @Test
    public void testParticipantHelpLink() throws Exception {
        helper.createProtocol();
        
        helper.openTab(TAB_ID);
        
        helper.assertHelpLink(ProtocolParticipant.class);
    }
    
}
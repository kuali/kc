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
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.bo.ProposalAbstract;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

/**
 * Tests the Abstracts tab in the Abstracts & Attachments page of a Development Proposal.
 */
public class ProposalDevelopmentAbstractsPanelSeleniumTest extends KcSeleniumTestBase {
    
    private static final String TAB_ID = "Abstracts";
    private static final String TABLE_ID = "abstracts-table";
    private static final String ERROR_PANEL_ID = "tab-Abstracts-div";
    
    private static final String NEW_PREFIX = "newProposalAbstract.";
    private static final String LIST_PREFIX = "document.developmentProposalList[0].proposalAbstract[%d].";

    private static final String ABSTRACT_TYPE_CODE_ID = "abstractTypeCode";
    private static final String ABSTRACT_DETAILS_ID = "abstractDetails";
    private static final String NEW_ABSTRACT_TYPE_CODE_ID = NEW_PREFIX + ABSTRACT_TYPE_CODE_ID;
    private static final String NEW_ABSTRACT_DETAILS_ID = NEW_PREFIX + ABSTRACT_DETAILS_ID;
    private static final String LIST_ABSTRACT_DETAILS_ID = LIST_PREFIX + ABSTRACT_DETAILS_ID;

    private static final String ABSTRACT_TYPE_COMPUTER_NAME = "Computer";
    private static final String ABSTRACT_DETAILS_COMPUTER_1 = "May the force be with you.";
    private static final String ABSTRACT_DETAILS_COMPUTER_2 = "Death Star.";
    private static final String ABSTRACT_TYPE_LABS_NAME = "Labs";
    private static final String ABSTRACT_DETAILS_LABS = "Star Wars.";
    
    private static final String ADD_ABSTRACT_BUTTON = "methodToCall.addAbstract";
    private static final String DELETE_ABSTRACT_BUTTON = "methodToCall.deleteAbstract.line%d";

    private ProposalDevelopmentSeleniumHelper helper;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        helper = ProposalDevelopmentSeleniumHelper.instance(driver);
    }
    
    @After
    public void tearDown() throws Exception {
        helper = null;
        
        super.tearDown();
    }
    
    /**
     * Test the addition of abstracts by adding two abstracts.  Verify that the document is saved and retrieved with the added abstracts.
     *
     * @throws Exception
     */
    @Test
    public void testAddAbstract() throws Exception {
        helper.createProposalDevelopment();
        helper.clickProposalDevelopmentAbstractsAndAttachmentsPage();
        helper.openTab(TAB_ID);

        addAbstract(ABSTRACT_TYPE_COMPUTER_NAME, ABSTRACT_DETAILS_COMPUTER_1);
        String timestampDisplayComputer = helper.getTableCellValue(TABLE_ID, 2, 0);

        addAbstract(ABSTRACT_TYPE_LABS_NAME, ABSTRACT_DETAILS_LABS);
        String timestampDisplayLabs = helper.getTableCellValue(TABLE_ID, 3, 0);
        
        helper.closeAndSearchDocument();
        helper.clickProposalDevelopmentAbstractsAndAttachmentsPage();
        helper.openTab(TAB_ID);
        
        helper.assertTableRowCount(TABLE_ID, 4);
        checkRow(timestampDisplayComputer, ABSTRACT_TYPE_COMPUTER_NAME, ABSTRACT_DETAILS_COMPUTER_1, 2);
        checkRow(timestampDisplayLabs, ABSTRACT_TYPE_LABS_NAME, ABSTRACT_DETAILS_LABS, 3);
    }
    
    /**
     * Test the addition of an abstract with no abstract type selected.
     *
     * @throws Exception
     */
    @Test
    public void testAddAbstractNoAbstractType() throws Exception {
        helper.createProposalDevelopment();
        helper.clickProposalDevelopmentAbstractsAndAttachmentsPage();
        helper.openTab(TAB_ID);

        helper.click(ADD_ABSTRACT_BUTTON);
        
        helper.assertErrorCount(ERROR_PANEL_ID, 1);
    }

    /**
     * Test the addition of an abstract with empty details.
     *
     * @throws Exception
     */
    @Test
    public void testAddAbstractNoAbstractDetails() throws Exception {
        helper.createProposalDevelopment();
        helper.clickProposalDevelopmentAbstractsAndAttachmentsPage();
        helper.openTab(TAB_ID);

        addAbstract(ABSTRACT_TYPE_COMPUTER_NAME, Constants.EMPTY_STRING);
        String timestampDisplayComputer = helper.getTableCellValue(TABLE_ID, 2, 0);
        
        helper.closeAndSearchDocument();
        helper.clickProposalDevelopmentAbstractsAndAttachmentsPage();
        helper.openTab(TAB_ID);
        helper.assertTableRowCount(TABLE_ID, 3);
        checkRow(timestampDisplayComputer, ABSTRACT_TYPE_COMPUTER_NAME, Constants.EMPTY_STRING, 2);
    }
    
    /**
     * Test the modification of details for a previously created abstract.
     *
     * @throws Exception
     */
    @Test
    public void testModifyAbstract() throws Exception {
        helper.createProposalDevelopment();
        helper.clickProposalDevelopmentAbstractsAndAttachmentsPage();
        helper.openTab(TAB_ID);

        addAbstract(ABSTRACT_TYPE_COMPUTER_NAME, ABSTRACT_DETAILS_COMPUTER_1);
        String timestampDisplayComputer = helper.getTableCellValue(TABLE_ID, 2, 0);
        
        helper.saveDocument();

        helper.set(String.format(LIST_ABSTRACT_DETAILS_ID, 0), ABSTRACT_DETAILS_COMPUTER_2);
        
        helper.closeAndSearchDocument();
        helper.clickProposalDevelopmentAbstractsAndAttachmentsPage();
        helper.openTab(TAB_ID);

        helper.assertTableRowCount(TABLE_ID, 3);
        checkRow(timestampDisplayComputer, ABSTRACT_TYPE_COMPUTER_NAME, ABSTRACT_DETAILS_COMPUTER_2, 2);
    }

    /**
     * Test the deletion of an abstract where the user confirms the deletion.  Verify that the document is saved and retrieved with no abstracts.
     *
     * @throws Exception
     */
    @Test
    public void testDeleteAbstractYes() throws Exception {
        helper.createProposalDevelopment();
        helper.clickProposalDevelopmentAbstractsAndAttachmentsPage();
        helper.openTab(TAB_ID);

        addAbstract(ABSTRACT_TYPE_COMPUTER_NAME, ABSTRACT_DETAILS_COMPUTER_1);

        helper.saveDocument();
        
        helper.click(String.format(DELETE_ABSTRACT_BUTTON, 0));
        helper.clickYesAnswer();

        helper.closeAndSearchDocument();
        helper.clickProposalDevelopmentAbstractsAndAttachmentsPage();
        helper.openTab(TAB_ID);
        
        helper.assertTableRowCount(TABLE_ID, 2);
    }

    /**
     * Test the deletion of an abstract where the user does not confirm the deletion.  Verify that the document is saved and retrieved with one abstract.
     *
     * @throws Exception
     */
    @Test
    public void testDeleteAbstractNo() throws Exception {
        helper.createProposalDevelopment();
        helper.clickProposalDevelopmentAbstractsAndAttachmentsPage();
        helper.openTab(TAB_ID);

        addAbstract(ABSTRACT_TYPE_COMPUTER_NAME, ABSTRACT_DETAILS_COMPUTER_1);
        String timestampDisplayComputer = helper.getTableCellValue(TABLE_ID, 2, 0);

        helper.saveDocument();

        helper.click(String.format(DELETE_ABSTRACT_BUTTON, 0));
        helper.clickNoAnswer();

        helper.closeAndSearchDocument();
        helper.clickProposalDevelopmentAbstractsAndAttachmentsPage();
        helper.openTab(TAB_ID);

        helper.assertOptionsDoNotContain(NEW_ABSTRACT_TYPE_CODE_ID, ABSTRACT_TYPE_COMPUTER_NAME);

        helper.assertTableRowCount(TABLE_ID, 3);
        checkRow(timestampDisplayComputer, ABSTRACT_TYPE_COMPUTER_NAME, ABSTRACT_DETAILS_COMPUTER_1, 2);
    }

    /**
     * Test the expanded text area.
     *
     * @throws Exception
     */
    @Test
    public void testAbstractExpandedTextArea() throws Exception {
        helper.createProposalDevelopment();
        helper.clickProposalDevelopmentAbstractsAndAttachmentsPage();
        helper.openTab(TAB_ID);

        helper.assertExpandedTextArea(NEW_ABSTRACT_DETAILS_ID, ABSTRACT_DETAILS_COMPUTER_1, ABSTRACT_DETAILS_COMPUTER_2);

        addAbstract(ABSTRACT_TYPE_COMPUTER_NAME, Constants.EMPTY_STRING);

        helper.assertExpandedTextArea(String.format(LIST_ABSTRACT_DETAILS_ID, 0), ABSTRACT_DETAILS_COMPUTER_1, ABSTRACT_DETAILS_COMPUTER_2);
    }
    
    /**
     * Verify that the help link works.
     * 
     * @throws Exception
     */
    @Test
    public void testAbstractHelpLink() throws Exception {
        helper.createProposalDevelopment();
        helper.clickProposalDevelopmentAbstractsAndAttachmentsPage();
        helper.openTab(TAB_ID);
        
        helper.assertHelpLink(ProposalAbstract.class);
    }
    
    /**
     * Adds an abstract.
     *
     * @param abstractTypeName the abstract type name to add
     * @param abstractDetails the abstract details to add
     * 
     * @throws Exception
     */
    private void addAbstract(String abstractTypeName, String abstractDetails) throws Exception {
        helper.set(NEW_ABSTRACT_TYPE_CODE_ID, abstractTypeName);
        helper.set(NEW_ABSTRACT_DETAILS_ID, abstractDetails);

        helper.click(ADD_ABSTRACT_BUTTON);
    }

    /**
     * Checks an added row to verify it contains the correct data.
     *
     * @param timestampDisplay the expected timestamp
     * @param abstractTypeName the expected abstract type name
     * @param abstractDetails the expected abstract details
     * @param rowIndex the index of the row to check
     */
    private void checkRow(String timestampDisplay, String abstractTypeName, String abstractDetails, int rowIndex) {
        helper.assertTableCellValue(TABLE_ID, rowIndex, 0, timestampDisplay);
        helper.assertTableCellValue(TABLE_ID, rowIndex, 2, abstractTypeName);
        helper.assertTableCellValue(TABLE_ID, rowIndex, 3, abstractDetails);
    }
    
}
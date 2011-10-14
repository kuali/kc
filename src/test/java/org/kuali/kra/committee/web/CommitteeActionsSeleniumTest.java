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
 * Tests the Actions page of a Committee.
 */
public class CommitteeActionsSeleniumTest extends KcSeleniumTestBase {
    
    private static final String BATCH_CORRESPONDENCE_TAB_ID = "Batch Correspondence";
    private static final String ERROR_TAB_ID = "tab-BatchCorrespondence-div";
    private static final String SUB_TAB_ID = "div[class='innerTab-head']";
    
    private static final String HELPER_PREFIX = "committeeHelper.";
    
    private static final String GENERATE_BATCH_CORRESPONDENCE_TYPE_CODE_ID = "generateBatchCorrespondenceTypeCode";
    private static final String HISTORY_BATCH_CORRESPONDENCE_TYPE_CODE_ID = "historyBatchCorrespondenceTypeCode";
    private static final String GENERATE_START_DATE_ID = "generateStartDate";
    private static final String GENERATE_END_DATE_ID = "generateEndDate";
    private static final String HELPER_GENERATE_BATCH_CORRESPONDENCE_TYPE_CODE_ID = HELPER_PREFIX + GENERATE_BATCH_CORRESPONDENCE_TYPE_CODE_ID;
    private static final String HELPER_HISTORY_BATCH_CORRESPONDENCE_TYPE_CODE_ID = HELPER_PREFIX + HISTORY_BATCH_CORRESPONDENCE_TYPE_CODE_ID;
    private static final String HELPER_GENERATE_START_DATE_ID = HELPER_PREFIX + GENERATE_START_DATE_ID;
    private static final String HELPER_GENERATE_END_DATE_ID = HELPER_PREFIX + GENERATE_END_DATE_ID;
    
    private static final String BATCH_CORRESPONDENCE_TYPE_CODE_NAME = "Protocol Renewal Reminders";
    private static final String START_DATE = "01/01/2010";
    private static final String END_DATE = "12/31/2010";
    private static final String GENERATED_BATCH_CORRESPONDENCE = "Generated Batch Correspondence";
    private static final String HISTORY_BATCH_CORRESPONDENCE = "Batch Type: Protocol Renewal Reminders";
    private static final String START_DATE_THROUGH_END_DATE = "01/01/2010 through 12/31/2010";
    
    private static final String ERROR_BATCH_TYPE_MISSING = "Batch type missing.";
    private static final String ERROR_START_DATE_MISSING = "Start date missing.";
    private static final String ERROR_END_DATE_MISSING = "End date missing.";
    private static final String ERROR_NO_REPORT_SELECTED = "No report selected for printing.";
    
    private static final String GENERATE_BATCH_CORRESPONDENCE_BUTTON = "methodToCall.generateBatchCorrespondence";
    private static final String FILTER_BATCH_CORRESPONDENCE_HISTORY_BUTTON = "methodToCall.filterBatchCorrespondenceHistory";
    private static final String PRINT_COMMITTEE_DOCUMENT_BUTTON = "methodToCall.printCommitteeDocument";
    
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
     * Test the Generate Batch Correspondence action.
     * 
     * @throws Exception
     */
    @Test
    public void testGenerateRenewalReminders() throws Exception {
        helper.createCommittee();
        helper.blanketApproveDocument();
        helper.clickCommitteeActionsPage();
        
        helper.openTab(BATCH_CORRESPONDENCE_TAB_ID);
        
        helper.click(GENERATE_BATCH_CORRESPONDENCE_BUTTON);
        helper.assertPageErrors();
        
        helper.assertError(ERROR_TAB_ID, ERROR_BATCH_TYPE_MISSING);
        helper.assertError(ERROR_TAB_ID, ERROR_START_DATE_MISSING);
        helper.assertError(ERROR_TAB_ID, ERROR_END_DATE_MISSING);

        helper.set(HELPER_GENERATE_BATCH_CORRESPONDENCE_TYPE_CODE_ID, BATCH_CORRESPONDENCE_TYPE_CODE_NAME);
        helper.set(HELPER_GENERATE_START_DATE_ID, START_DATE);
        helper.set(HELPER_GENERATE_END_DATE_ID, END_DATE);
        
        helper.click(GENERATE_BATCH_CORRESPONDENCE_BUTTON);
        helper.assertNoPageErrors();
        
        helper.assertPageContains(GENERATED_BATCH_CORRESPONDENCE);
        helper.assertSelectorContains(SUB_TAB_ID, START_DATE_THROUGH_END_DATE);
    }
    
    /**
     * Test the Filter Batch Correspondence History action.
     * 
     * @throws Exception
     */
    @Test
    public void testFilterBatchCorrespondenceHistory() throws Exception {
        helper.createCommittee();
        helper.blanketApproveDocument();
        helper.clickCommitteeActionsPage();
        
        helper.openTab(BATCH_CORRESPONDENCE_TAB_ID);
        
        helper.click(FILTER_BATCH_CORRESPONDENCE_HISTORY_BUTTON);
        helper.assertPageErrors();
        
        helper.assertError(ERROR_TAB_ID, ERROR_BATCH_TYPE_MISSING);
        
        helper.set(HELPER_HISTORY_BATCH_CORRESPONDENCE_TYPE_CODE_ID, BATCH_CORRESPONDENCE_TYPE_CODE_NAME);
        
        helper.click(FILTER_BATCH_CORRESPONDENCE_HISTORY_BUTTON);
        helper.assertNoPageErrors();
        
        helper.assertPageContains(HISTORY_BATCH_CORRESPONDENCE);
        helper.assertSelectorContains(SUB_TAB_ID, START_DATE_THROUGH_END_DATE);
    }
    
    /**
     * Test the Print action errors when no reports are selected for printing.
     * 
     * @throws Exception
     */
    @Test
    public void testPrintErrors() throws Exception {
        helper.createCommittee();
        helper.blanketApproveDocument();
        helper.clickCommitteeActionsPage();
        
        helper.openTab(1);
        
        helper.click(PRINT_COMMITTEE_DOCUMENT_BUTTON);
        helper.assertPageErrors();

        helper.assertPageContains(ERROR_NO_REPORT_SELECTED);
    }

}
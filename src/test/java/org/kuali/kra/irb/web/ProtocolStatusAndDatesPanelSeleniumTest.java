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
package org.kuali.kra.irb.web;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

/**
 * Tests the Status & Dates tab in the Protocol page of a Protocol.
 */
public class ProtocolStatusAndDatesPanelSeleniumTest extends KcSeleniumTestBase {
    
    private static final String TAB_ID = "Status Dates";
    private static final String TABLE_ID = "status-dates-table";
    
    private static final String PROTOCOL_STATUS = "Pending/In Progress";
    private static final String INITIAL_APPROVAL_DATE = "Generated on Approval";
    private static final String LAST_APPROVAL_DATE = "Generated on Renewal Approval";
    private static final String INITIAL_SUBMISSION_DATE = "Generated on Initial Submission";
    private static final String EXPIRATION_DATE = "Generated on Approval"; 
    
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
     * Test the Status & Dates panel.
     * 
     * @throws Exception
     */
    @Test
    public void testStatusAndDates() throws Exception {
        helper.createProtocol();
        helper.assertNoPageErrors();
        
        helper.openTab(TAB_ID);

        assertTrue(StringUtils.isNotBlank(helper.getTableCellValue(TABLE_ID, 0, 0)));
        helper.assertTableCellValueContains(TABLE_ID, 0, 1, PROTOCOL_STATUS);
        helper.assertTableCellValueContains(TABLE_ID, 1, 0, INITIAL_APPROVAL_DATE);
        helper.assertTableCellValueContains(TABLE_ID, 1, 1, LAST_APPROVAL_DATE);
        helper.assertTableCellValueContains(TABLE_ID, 2, 0, INITIAL_SUBMISSION_DATE);
        helper.assertTableCellValueContains(TABLE_ID, 2, 1, EXPIRATION_DATE);
    }
    
}
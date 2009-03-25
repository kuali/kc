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
package org.kuali.kra.award.htmlunitwebtest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;

/**
 * 
 * This is the integration test for Award Home Page. 
 */
@SuppressWarnings("unchecked")
public class AwardHomeWebTest extends AwardWebTestBase {
    
    protected static final String ACCOUNT_TYPE_ID = AWARD_ID_PREFIX + "accountTypeCode";
    protected static final String ACCOUNT_ID = AWARD_ID_PREFIX + "accountNumber";
    protected static final String CFDA_NUMBER = AWARD_ID_PREFIX + "cfdaNumber";
    protected static final String NSF_CODE = AWARD_ID_PREFIX + "nsfCode";
    protected static final String NEW_TRANSFERRING_SPONSOR_LOOKUP = "newAwardTransferringSponsor.sponsorCode";
    
    private static final String TEST_CFDA_VALUE = "00.000";
    private static final String TEST_NSF_CODE_VALUE = "A.03";
    private static final String ONE = "1";
    private static final String SAVE_METHOD = "methodToCall.save";
    private static final String RESET_METHOD = "methodToCall.reload";
    private static final String ADD_TRANSFERRING_SPONSOR_METHOD = "methodToCall.addAwardTransferringSponsor.anchorDetailsDates";
    private static final String DELETE_TRANSFERRING_SPONSOR_METHOD = "methodToCall.deleteAwardTransferringSponsor.line0.anchor2";
    private static final String TRANSFERRING_SPONSOR_TABLE = "sponsor-funding-transferred-table";

    HtmlPage awardHomePage;
    
    /**
     * The set up method calls the parent super method and gets the 
     * award Home page after that.
     * @see org.kuali.kra.award.htmlunitwebtest.AwardWebTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        awardHomePage = getAwardHomePage();
    }
    
    /**
     * This method calls parent tear down method and than sets awardHomePage to null
     * @see org.kuali.kra.award.htmlunitwebtest.AwardWebTestBase#tearDown()
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        awardHomePage = null;
    }
    
    /**
     * This method tests the Details and Dates panel:
     * 1. Populate fields, including add row to list of Transferring Sponsors
     * 2. Save
     * 3. Delete Transferring Sponsor
     * 4. Save
     * 
     * @throws Exception
     */
    @Test
    public void testDetailsAndDatesPanel() throws Exception {
        
        // Required fields already populated - finish populating optional fields
        setFieldValue(awardHomePage, ACCOUNT_TYPE_ID, ONE);
        setFieldValue(awardHomePage, ACCOUNT_ID, ONE);
        setFieldValue(awardHomePage, CFDA_NUMBER, TEST_CFDA_VALUE);
        setFieldValue(awardHomePage, NSF_CODE, TEST_NSF_CODE_VALUE);
        
        // Add a transferring sponsor
        awardHomePage = lookup(awardHomePage, NEW_TRANSFERRING_SPONSOR_LOOKUP);
        awardHomePage = clickOn(awardHomePage, ADD_TRANSFERRING_SPONSOR_METHOD);
        
        // Verify the new row is there.
        HtmlTable table = getTable(awardHomePage, TRANSFERRING_SPONSOR_TABLE);
        assertTrue("row count is " + table.getRowCount(), table.getRowCount() == 3);
        
        // Save page
        HtmlPage awardHomePageAfterSave = clickOn(awardHomePage, SAVE_METHOD);
        assertContains(awardHomePageAfterSave, SAVE_SUCCESS_MESSAGE);
        
        // Delete transferring sponsor
        awardHomePageAfterSave = clickOn(awardHomePageAfterSave, DELETE_TRANSFERRING_SPONSOR_METHOD);
        
        awardHomePageAfterSave = clickOn(awardHomePageAfterSave, SAVE_METHOD);
        assertContains(awardHomePageAfterSave, SAVE_SUCCESS_MESSAGE);
        
        // Verify deletion
        awardHomePageAfterSave = clickOn(awardHomePageAfterSave, RESET_METHOD);
        
        table = getTable(awardHomePageAfterSave, TRANSFERRING_SPONSOR_TABLE);
        assertTrue("row count is " + table.getRowCount(), table.getRowCount() == 2);
    }
    
}

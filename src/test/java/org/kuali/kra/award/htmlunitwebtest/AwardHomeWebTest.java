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

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;

/**
 * 
 * This is the integration test for Award Home Page. 
 */
public class AwardHomeWebTest extends AwardWebTestBase {
    
    protected static final String ACCOUNT_TYPE_ID = AWARD_ID_PREFIX + "accountTypeCode";
    protected static final String ACCOUNT_ID = AWARD_ID_PREFIX + "accountNumber";
    protected static final String CFDA_NUMBER = AWARD_ID_PREFIX + "cfdaNumber";
    protected static final String NSF_CODE = AWARD_ID_PREFIX + "nsfCode";
    protected static final String NEW_TRANSFERRING_SPONSOR_LOOKUP = "sponsorToBecomeAwardTransferringSponsor.sponsorCode";
    
    private static final String TEST_CFDA_VALUE = "00.000";
    private static final String TEST_NSF_CODE_VALUE = "A.03";
    private static final String ONE = "1";
    private static final String SAVE_METHOD = "methodToCall.save";
    private static final String RESET_METHOD = "methodToCall.reload";
    private static final String ADD_TRANSFERRING_SPONSOR_METHOD = "methodToCall.addAwardTransferringSponsor.anchorDetailsDates";
    private static final String DELETE_TRANSFERRING_SPONSOR_METHOD = "methodToCall.deleteAwardTransferringSponsor.line0.anchor2";
    private static final String TRANSFERRING_SPONSOR_TABLE = "sponsor-funding-transferred-table";
    private static final int TRANSFERRING_SPONSOR_TABLE_ROWS_AFTER_ADDING = 3;
    private static final int TRANSFERRING_SPONSOR_TABLE_ROWS_AFTER_DELETING = 2;
    
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
        
        populateOptionalDetailsAndDatesFields();
        
        addAwardTransferringSponsor();
        savePageAndVerifySave();
        validateTableRows(TRANSFERRING_SPONSOR_TABLE, TRANSFERRING_SPONSOR_TABLE_ROWS_AFTER_ADDING);
        
        deleteFirstAwardTransferringSponsor();
        
        savePageAndVerifySave();
        
        awardHomePage = clickOn(awardHomePage, RESET_METHOD);
        validateTableRows(TRANSFERRING_SPONSOR_TABLE, TRANSFERRING_SPONSOR_TABLE_ROWS_AFTER_DELETING);
    }
    
    private void populateOptionalDetailsAndDatesFields() {
        setFieldValue(awardHomePage, ACCOUNT_TYPE_ID, ONE);
        setFieldValue(awardHomePage, ACCOUNT_ID, ONE);
        setFieldValue(awardHomePage, CFDA_NUMBER, TEST_CFDA_VALUE);
        setFieldValue(awardHomePage, NSF_CODE, TEST_NSF_CODE_VALUE);
    }
    
    private void addAwardTransferringSponsor() throws IOException {
        awardHomePage = lookup(awardHomePage, NEW_TRANSFERRING_SPONSOR_LOOKUP);
        awardHomePage = clickOn(awardHomePage, ADD_TRANSFERRING_SPONSOR_METHOD);
    }
    
    private void savePageAndVerifySave() throws IOException {
        awardHomePage = clickOn(awardHomePage, SAVE_METHOD);
        assertContains(awardHomePage, SAVE_SUCCESS_MESSAGE);
    }
    
    private void deleteFirstAwardTransferringSponsor() throws IOException {
        awardHomePage = clickOn(awardHomePage, DELETE_TRANSFERRING_SPONSOR_METHOD);
    }
    
    private void validateTableRows(String tableName, int rows) {
        HtmlTable table = getTable(awardHomePage, tableName);
        assertTrue("row count is " + table.getRowCount(), table.getRowCount() == rows);
    }
    
}

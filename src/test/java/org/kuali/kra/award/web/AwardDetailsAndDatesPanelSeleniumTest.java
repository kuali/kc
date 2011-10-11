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
package org.kuali.kra.award.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

/**
 * Tests the Details and Dates tab in the Home page of an Award.
 */
public class AwardDetailsAndDatesPanelSeleniumTest extends KcSeleniumTestBase {
    
    private static final String SPONSOR_FUNDING_TRANSFERRED_TAB_ID = "DetailsDates:SponsorFundingTransferred";
    private static final String SPONSOR_FUNDING_TRANSFERRED_TABLE_ID = "sponsor-funding-transferred-table";
    
    private static final String TRANSFERRING_SPONSOR_TAG_ID = "sponsorToBecomeAwardTransferringSponsor.sponsorCode";
    
    private static final String LIST_PREFIX = "document.awardList[0].";
    
    private static final String ACCOUNT_TYPE_CODE_ID = "accountTypeCode";
    private static final String CFDA_NUMBER_ID = "cfdaNumber";
    private static final String NSF_CODE_ID = "nsfCode";
    private static final String LIST_ACCOUNT_TYPE_CODE_ID = LIST_PREFIX + ACCOUNT_TYPE_CODE_ID;
    private static final String LIST_CFDA_NUMBER_ID = LIST_PREFIX + CFDA_NUMBER_ID;
    private static final String LIST_NSF_CODE_ID = LIST_PREFIX + NSF_CODE_ID;
    
    private static final String ACCOUNT_TYPE = "Regular";
    private static final String CFDA_NUMBER = "00.000";
    private static final String NSF_CODE = "A.03";
    
    private static final String ADD_TRANSFERRING_SPONSOR_BUTTON = "methodToCall.addAwardTransferringSponsor";
    private static final String DELETE_TRANSFERRING_SPONSOR_BUTTON = "methodToCall.deleteAwardTransferringSponsor.line0";
    
    private AwardSeleniumHelper helper;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        helper = AwardSeleniumHelper.instance(driver);
    }
    
    @After
    public void tearDown() throws Exception {
        helper = null;
        
        super.tearDown();
    }
    
    /**
     * Tests the addition and deletion of transferring sponsors.
     * 
     * @throws Exception
     */
    @Test
    public void testAddDeleteTransferringSponsor() throws Exception {
        helper.createAward();
        
        helper.set(LIST_ACCOUNT_TYPE_CODE_ID, ACCOUNT_TYPE);
        helper.set(LIST_CFDA_NUMBER_ID, CFDA_NUMBER);
        helper.set(LIST_NSF_CODE_ID, NSF_CODE);
        
        helper.openTab(SPONSOR_FUNDING_TRANSFERRED_TAB_ID);
        helper.lookup(TRANSFERRING_SPONSOR_TAG_ID);
        helper.click(ADD_TRANSFERRING_SPONSOR_BUTTON);
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertTableRowCount(SPONSOR_FUNDING_TRANSFERRED_TABLE_ID, 3);
        
        helper.click(DELETE_TRANSFERRING_SPONSOR_BUTTON);
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.reloadDocument();
        
        helper.assertTableRowCount(SPONSOR_FUNDING_TRANSFERRED_TABLE_ID, 2);
    }
    
}
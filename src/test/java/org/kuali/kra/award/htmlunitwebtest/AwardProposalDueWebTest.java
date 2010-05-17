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
package org.kuali.kra.award.htmlunitwebtest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * 
 * This is the integration test for Award Proposal Due Tab. 
 */
@org.junit.Ignore("This test is not meant to be run against the 2.0 release")
public class AwardProposalDueWebTest extends AwardPaymentsAndTermsWebTest {
    
    /**
     * The set up method calls the parent super method and gets the 
     * award Payment Reports And Terms page after that.
     * @see org.kuali.kra.award.htmlunitwebtest.AwardWebTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * This method calls parent tear down method and than sets paymentReportsAndTermsPage to null
     * @see org.kuali.kra.award.htmlunitwebtest.AwardWebTestBase#tearDown()
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    /**
     * 
     * This method tests Preaward Authorizations Panel.  Adding amounts and effective dates. 
     * and saving them.  
     * @throws Exception
     */
    @Test
    public void testProposalDuePanel() throws Exception{
        setFieldValue(paymentReportsAndTermsPage, "document.awardList[0].competingRenewalProposalDue", "8");
        setFieldValue(paymentReportsAndTermsPage, "document.awardList[0].nonCompetingContProposalDue", "9");
        
        HtmlPage awardPaymentReportsAndTermsAfterSave = clickOn(paymentReportsAndTermsPage, "methodToCall.save");
        assertDoesNotContain(awardPaymentReportsAndTermsAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);
        assertDoesNotContain(awardPaymentReportsAndTermsAfterSave, ERRORS_FOUND_ON_PAGE);
        assertContains(awardPaymentReportsAndTermsAfterSave,SAVE_SUCCESS_MESSAGE);
        
    }
}

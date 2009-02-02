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

/**
 * 
 * This is the integration test for Award Benefits Rate on Rates Tab. 
 */

public class AwardBenefitsRateWebTest extends AwardTimeAndMoneyWebTest {
    
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
    public void testBenefitsRatePanel() throws Exception{
        
        setFieldValue(awardTimeAndMoneyPage, "document.awardList[0].specialEbRateOffCampus", "50");
        setFieldValue(awardTimeAndMoneyPage, "document.awardList[0].specialEbRateOnCampus", "60");
        
        HtmlPage awardTimeAndMoneyPageAfterSave = clickOn(awardTimeAndMoneyPage, "methodToCall.save");
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERRORS_FOUND_ON_PAGE);
        assertContains(awardTimeAndMoneyPageAfterSave,SAVE_SUCCESS_MESSAGE);
        
    }
    
    /**
     * 
     * This method tests Preaward Sponsor Authorization Comments
     * @throws Exception
     */
    @Test
    public void testBenefitsRateComments() throws Exception{
        
        setFieldValue(awardTimeAndMoneyPage, "document.awardList[0].specialEbRateOffCampus", "30");
        setFieldValue(awardTimeAndMoneyPage, "document.awardList[0].specialEbRateOnCampus", "40");
        
        HtmlPage awardTimeAndMoneyPageAfterSave = clickOn(awardTimeAndMoneyPage, "methodToCall.save");
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERRORS_FOUND_ON_PAGE);
        assertContains(awardTimeAndMoneyPageAfterSave,SAVE_SUCCESS_MESSAGE);
        
        setFieldValue(awardTimeAndMoneyPageAfterSave, "document.awardList[0].awardBenefitsRateComment.comments", "We are testing Benefits Rate Comments");
        
        HtmlPage awardTimeAndMoneyPageAfterAnotherSave = clickOn(awardTimeAndMoneyPageAfterSave, "methodToCall.save");
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);
        assertDoesNotContain(awardTimeAndMoneyPageAfterAnotherSave, ERRORS_FOUND_ON_PAGE);
        assertContains(awardTimeAndMoneyPageAfterAnotherSave,SAVE_SUCCESS_MESSAGE);
        HtmlPage awardTimeAndMoneyPageAfterAnotherReload = clickOn(awardTimeAndMoneyPageAfterAnotherSave, "methodToCall.reload");
        assertContains(awardTimeAndMoneyPageAfterAnotherReload,"We are testing Benefits Rate Comments");
        
    }

}

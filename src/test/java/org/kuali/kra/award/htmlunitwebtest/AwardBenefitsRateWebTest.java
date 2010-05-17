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
 * This is the integration test for Award Benefits Rate on Rates Tab. 
 */
@org.junit.Ignore("This test is not meant to be run against the 2.0 release")
public class AwardBenefitsRateWebTest extends AwardTimeAndMoneyWebTest {
    
    private static final String DOCUMENT_AWARD_OFFCAMPUS = "document.awardList[0].specialEbRateOffCampus";
    private static final String DOCUMENT_AWARD_ONCAMPUS = "document.awardList[0].specialEbRateOnCampus";
    private static final String DOCUMENT_AWARD_BENEFITS_RATE_COMMENT = "document.awardList[0].awardBenefitsRateComment.comments";
    private static final String SAVE_COST_SHARE_METHOD = "methodToCall.save";
    private static final String RELOAD_METHOD = "methodToCall.reload";
    private static final String TEST_COMMENTS = "we are testing comments.";
    private static final String ZERO = "0";

    
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
     * This method tests Benefits Rates Panel.  Adding pn and off campus rates. 
     * and saving them.  
     * @throws Exception
     */
    @Test
    public void testBenefitsRatePanel() throws Exception{
        
        setFieldValue(awardTimeAndMoneyPage, DOCUMENT_AWARD_OFFCAMPUS, ZERO);
        setFieldValue(awardTimeAndMoneyPage, DOCUMENT_AWARD_ONCAMPUS, ZERO);
        
        HtmlPage awardTimeAndMoneyPageAfterSave = clickOn(awardTimeAndMoneyPage, SAVE_COST_SHARE_METHOD);
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERRORS_FOUND_ON_PAGE);
        assertContains(awardTimeAndMoneyPageAfterSave,SAVE_SUCCESS_MESSAGE);
        
    }
    
    /**
     * 
     * This method tests Benefits Rates Comments
     * @throws Exception
     */
    @Test
    public void testBenefitsRateComments() throws Exception{
        
        setFieldValue(awardTimeAndMoneyPage, 
                        DOCUMENT_AWARD_BENEFITS_RATE_COMMENT, 
                         TEST_COMMENTS);
        
        HtmlPage awardTimeAndMoneyPageAfterSave = clickOn(awardTimeAndMoneyPage,
                SAVE_COST_SHARE_METHOD);
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERRORS_FOUND_ON_PAGE);
        assertContains(awardTimeAndMoneyPageAfterSave,SAVE_SUCCESS_MESSAGE);
        HtmlPage awardTimeAndMoneyPageAfterReload = clickOn(awardTimeAndMoneyPageAfterSave, 
                                                                    RELOAD_METHOD);
        assertContains(awardTimeAndMoneyPageAfterReload,TEST_COMMENTS);
        
    }

}

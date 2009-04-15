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
 * This is the integration test for AwardDirectFandADistribution Tab. 
 */
public class AwardDirectFandADistributionWebTest extends AwardTimeAndMoneyWebTest {
    
    private static final String NEW_AWARD_DIRECT_DISTRIBUTION_FIELD = "directFandADistributionFormHelper.newAwardDirectFandADistribution.";
    private static final String START_DATE = "startDate";
    private static final String END_DATE = "endDate";
    private static final String DIRECT_COST = "directCost";
    private static final String INDIRECT_COST = "indirectCost";
    private static final String START_DATE_STRING = "01/02/2010";
    private static final String END_DATE_STRING = "01/31/2010";
    private static final String SET_END_DATE = "01/01/2010";
    private static final String RESET_END_DATE = "03/28/2010";
    private static final String TEN_THOUSAND = "10000";
    private static final String TWENTY_FIVE_THOUSAND = "25000";
    private static final String ADD_METHOD = "methodToCall.addAwardDirectFandADistribution.anchorDirectFAFundsDistribution";
    private static final String SAVE_METHOD = "methodToCall.save";
    private static final String END_DATE_FIELD_STRING = "document.awardList[0].awardDirectFandADistributions[0].endDate";
    
    

    /**
     * The set up method calls the parent super method and gets the 
     * award Time and Money page after that.
     * @see org.kuali.kra.award.htmlunitwebtest.AwardWebTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * This method calls parent tear down method and than sets awardTimeAndMoneyPage to null
     * @see org.kuali.kra.award.htmlunitwebtest.AwardWebTestBase#tearDown()
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    
    /**
     * 
     * This method tests Award Direct F and A Distribution Panel. First Adding an invalid date range and
     * then altering dates so the target date can be added successfully.
     * @throws Exception
     */
    @Test
    public void testAddDirectFandADistributionToAward() throws Exception{
        setFieldValue(awardTimeAndMoneyPage, NEW_AWARD_DIRECT_DISTRIBUTION_FIELD + START_DATE, START_DATE_STRING);
        setFieldValue(awardTimeAndMoneyPage, NEW_AWARD_DIRECT_DISTRIBUTION_FIELD + END_DATE, END_DATE_STRING);
        setFieldValue(awardTimeAndMoneyPage, NEW_AWARD_DIRECT_DISTRIBUTION_FIELD + DIRECT_COST, TEN_THOUSAND);
        setFieldValue(awardTimeAndMoneyPage, NEW_AWARD_DIRECT_DISTRIBUTION_FIELD + INDIRECT_COST, TWENTY_FIVE_THOUSAND);
        
        HtmlPage awardTimeAndMoneyPageAfterAdd = clickOn(awardTimeAndMoneyPage, ADD_METHOD);
        
        assertContains(awardTimeAndMoneyPageAfterAdd, ERRORS_FOUND_ON_PAGE);
        
        setFieldValue(awardTimeAndMoneyPageAfterAdd, END_DATE_FIELD_STRING, SET_END_DATE);
        HtmlPage awardTimeAndMoneyPageAfterSecondAdd = 
            clickOn(awardTimeAndMoneyPageAfterAdd, ADD_METHOD);
        assertDoesNotContain(awardTimeAndMoneyPageAfterSecondAdd, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);
        assertDoesNotContain(awardTimeAndMoneyPageAfterSecondAdd, ERRORS_FOUND_ON_PAGE);  
    }
    
    /**
     * This method tests saving valid and invalid date ranges.
     * @throws Exception
     */
    @Test
    public void testSaveAndOverlappingDateFields() throws Exception{
        setFieldValue(awardTimeAndMoneyPage, NEW_AWARD_DIRECT_DISTRIBUTION_FIELD + START_DATE, START_DATE_STRING);
        setFieldValue(awardTimeAndMoneyPage, NEW_AWARD_DIRECT_DISTRIBUTION_FIELD + END_DATE, END_DATE_STRING);
        setFieldValue(awardTimeAndMoneyPage, NEW_AWARD_DIRECT_DISTRIBUTION_FIELD + DIRECT_COST, TEN_THOUSAND);
        setFieldValue(awardTimeAndMoneyPage, NEW_AWARD_DIRECT_DISTRIBUTION_FIELD + INDIRECT_COST, TWENTY_FIVE_THOUSAND);
        setFieldValue(awardTimeAndMoneyPage, END_DATE_FIELD_STRING, SET_END_DATE);
        
        HtmlPage awardTimeAndMoneyPageAfterAdd = clickOn(awardTimeAndMoneyPage, ADD_METHOD);
        
        assertDoesNotContain(awardTimeAndMoneyPageAfterAdd, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);
        assertDoesNotContain(awardTimeAndMoneyPageAfterAdd, ERRORS_FOUND_ON_PAGE);
        
        HtmlPage awardTimeAndMoneyPageAfterSave = clickOn(awardTimeAndMoneyPageAfterAdd, SAVE_METHOD);
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERRORS_FOUND_ON_PAGE);
        assertContains(awardTimeAndMoneyPageAfterSave,SAVE_SUCCESS_MESSAGE);
        
        setFieldValue(awardTimeAndMoneyPageAfterSave, END_DATE_FIELD_STRING, RESET_END_DATE);
        
        HtmlPage awardTimeAndMoneyPageAfterSecondSave = clickOn(awardTimeAndMoneyPageAfterSave, SAVE_METHOD);
        assertContains(awardTimeAndMoneyPageAfterSecondSave, ERRORS_FOUND_ON_PAGE);
    }
    
    
    
    
}

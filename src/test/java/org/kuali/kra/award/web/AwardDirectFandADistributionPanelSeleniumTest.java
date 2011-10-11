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
import org.kuali.kra.timeandmoney.web.TimeAndMoneySeleniumHelper;

/**
 * Tests the Direct F & A Distribution Panel in a Time & Money document in an Award. 
 */
public class AwardDirectFandADistributionPanelSeleniumTest extends KcSeleniumTestBase {
    
    private static final String TAB_ID = "Direct FA Funds Distribution";
    
    private static final String BEAN_PREFIX = "awardDirectFandADistributionBean.newAwardDirectFandADistribution.";
    private static final String LIST_PREFIX = "document.award.awardDirectFandADistributions[0].";
    
    private static final String START_DATE_ID = "startDate";
    private static final String END_DATE_ID = "endDate";
    private static final String DIRECT_COST_ID = "directCost";
    private static final String INDIRECT_COST_ID = "indirectCost";
    private static final String BEAN_START_DATE_ID = BEAN_PREFIX + START_DATE_ID;
    private static final String BEAN_END_DATE_ID = BEAN_PREFIX + END_DATE_ID;
    private static final String BEAN_DIRECT_COST_ID = BEAN_PREFIX + DIRECT_COST_ID;
    private static final String BEAN_INDIRECT_COST_ID = BEAN_PREFIX + INDIRECT_COST_ID;
    private static final String LIST_END_DATE_ID = LIST_PREFIX + END_DATE_ID;
    
    private static final String START_DATE = "01/02/2010";
    private static final String END_DATE_FIRST = "01/01/2010";
    private static final String END_DATE_MIDDLE = "01/31/2010";
    private static final String END_DATE_LAST = "03/28/2010";
    private static final String TEN_THOUSAND = "10000";
    private static final String TWENTY_FIVE_THOUSAND = "25000";
    
    private static final String ADD_AWARD_DIRECT_F_AND_A_DISTRIBUTION_BUTTON = "methodToCall.addAwardDirectFandADistribution.anchorDirectFAFundsDistribution";
 
    private AwardSeleniumHelper awardHelper;
    private TimeAndMoneySeleniumHelper timeAndMoneyHelper;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        awardHelper = AwardSeleniumHelper.instance(driver);
        timeAndMoneyHelper = TimeAndMoneySeleniumHelper.instance(driver);
    }
    
    @After
    public void tearDown() throws Exception {
        awardHelper = null;
        timeAndMoneyHelper = null;
        
        super.tearDown();
    }
    
    /**
     * Test adding an invalid date range to the Direct F & A Distribution panel and then altering dates so the target date can be added successfully.
     * 
     * @throws Exception
     */
    @Test
    public void testAddDirectFandADistribution() throws Exception {
        awardHelper.createAward();
        awardHelper.openTimeAndMoneyDocument();
        
        timeAndMoneyHelper.openTab(TAB_ID);
        
        timeAndMoneyHelper.set(BEAN_START_DATE_ID, START_DATE);
        timeAndMoneyHelper.set(BEAN_END_DATE_ID, END_DATE_MIDDLE);
        timeAndMoneyHelper.set(BEAN_DIRECT_COST_ID, TEN_THOUSAND);
        timeAndMoneyHelper.set(BEAN_INDIRECT_COST_ID, TWENTY_FIVE_THOUSAND);
        
        timeAndMoneyHelper.click(ADD_AWARD_DIRECT_F_AND_A_DISTRIBUTION_BUTTON);
        
        timeAndMoneyHelper.assertPageErrors();
        
        timeAndMoneyHelper.set(LIST_END_DATE_ID, END_DATE_FIRST);
 
        timeAndMoneyHelper.saveDocument();
        timeAndMoneyHelper.assertNoPageErrors();
    }
    
    /**
     * Test saving valid and invalid date ranges.
     * 
     * @throws Exception
     */
    @Test
    public void testSaveOverlappingDateFields() throws Exception {
        awardHelper.createAward();
        awardHelper.openTimeAndMoneyDocument();
        
        timeAndMoneyHelper.openTab(TAB_ID);
        
        timeAndMoneyHelper.saveDocument();
        timeAndMoneyHelper.assertNoPageErrors();
        
        timeAndMoneyHelper.set(LIST_END_DATE_ID, END_DATE_FIRST);

        timeAndMoneyHelper.saveDocument();
        timeAndMoneyHelper.assertNoPageErrors();
        
        timeAndMoneyHelper.set(BEAN_START_DATE_ID, START_DATE);
        timeAndMoneyHelper.set(BEAN_END_DATE_ID, END_DATE_MIDDLE);
        timeAndMoneyHelper.set(BEAN_DIRECT_COST_ID, TEN_THOUSAND);
        timeAndMoneyHelper.set(BEAN_INDIRECT_COST_ID, TWENTY_FIVE_THOUSAND);
        
        timeAndMoneyHelper.click(ADD_AWARD_DIRECT_F_AND_A_DISTRIBUTION_BUTTON);
        
        timeAndMoneyHelper.saveDocument();
        timeAndMoneyHelper.assertNoPageErrors();
        
        timeAndMoneyHelper.set(LIST_END_DATE_ID, END_DATE_LAST);
        
        timeAndMoneyHelper.saveDocument();
        timeAndMoneyHelper.assertPageErrors();
    }
       
}
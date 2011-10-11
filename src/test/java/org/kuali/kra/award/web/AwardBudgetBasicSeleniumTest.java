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
import org.kuali.kra.budget.web.BudgetSeleniumHelper;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

public class AwardBudgetBasicSeleniumTest extends KcSeleniumTestBase {
    
    private AwardSeleniumHelper helper;
    private BudgetSeleniumHelper budgetHelper;
    
    private static final String DEFAULT_LINE_ITEM_DESC1 = "Equipment - Not MTDC";
    private static final String DEFAULT_LINE_ITEM_QUANTITY1 = "1";
    private static final String DEFAULT_LINE_ITEM_DESC2 = "Travel";
    private static final String DEFAULT_LINE_ITEM_QUANTITY2 = null;
    
    private String awardBudgetDocNbr;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        helper = AwardSeleniumHelper.instance(driver);
        budgetHelper = BudgetSeleniumHelper.instance(driver);

        helper.loginBackdoor();
    }
    
    @After
    public void tearDown() throws Exception {
        helper.loginBackdoor();
        
        helper = null;
        
        super.tearDown();
    }
    
    @Test
    public void testAwardBudgetBasic() {
        helper.createAward();
        
        helper.addSponsorTemplate();
        
        helper.addContacts();
        
        helper.addCustomData();
        
        helper.submit();

        helper.addBudget();
        helper.openBudget(0);
        
        awardBudgetDocNbr = budgetHelper.getDocumentNumber();
        
        populateBudget();
        
        submitBudget();
        
        approveBudget();
        
        postBudget();
    }
    
    protected void populateBudget() {
        budgetHelper.addPersonnel();
        budgetHelper.setPersonPercents(0, 0, 0, "35", "35");
        budgetHelper.clickBudgetNonPersonnelTab();
        budgetHelper.addLineItem(0, DEFAULT_LINE_ITEM_DESC1, DEFAULT_LINE_ITEM_QUANTITY1, "3.51");
        budgetHelper.addLineItem(1, DEFAULT_LINE_ITEM_DESC2, DEFAULT_LINE_ITEM_QUANTITY2, "1,759.60");
        budgetHelper.saveDocument();
    }
    
    protected void submitBudget() {
        budgetHelper.clickBudgetActionsTab();
        budgetHelper.routeDocument();
        budgetHelper.assertRoute();
        budgetHelper.closeDocument();
    }
    
    protected void approveBudget() {
        helper.loginBackdoor("jtester");
        helper.docSearch(awardBudgetDocNbr);
        budgetHelper.clickBudgetActionsTab();
        budgetHelper.approveDocument();
    }
    
    protected void postBudget() {
        helper.loginBackdoor();
        helper.docSearch(awardBudgetDocNbr);
        budgetHelper.clickBudgetActionsTab();
        budgetHelper.postBudget();
        budgetHelper.returnToAward();
        helper.clickAwardBudgetVersionsPage();
        helper.assertSelectorContains("div.tab-container tr:nth-child(5) > td:nth-child(2)", "Posted");
    }
}
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
import org.openqa.selenium.support.ui.Select;

public class AwardBudgetBasicSeleniumTest extends KcSeleniumTestBase {
    
    private AwardSeleniumHelper helper;
    private BudgetSeleniumHelper budgetHelper;
    
    public static final String AMOUNT = "10000.00";
    
    private static final String DEFAULT_LINE_ITEM_DESC1 = "Equipment - Not MTDC";
    private static final String DEFAULT_LINE_ITEM_QUANTITY1 = "1";
    private static final String DEFAULT_LINE_ITEM_DESC2 = "Travel";
    private static final String DEFAULT_LINE_ITEM_QUANTITY2 = null;
    
    private String awardDocNbr;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        helper = AwardSeleniumHelper.instance(driver);
        budgetHelper = BudgetSeleniumHelper.instance(driver);
    }
    
    @After
    public void tearDown() throws Exception {
        helper = null;
        
        super.tearDown();
    }
    
    @Test
    public void testAwardBudgetBasic() {
        helper.createAward();
        
        helper.addSponsorTemplate();
        
        helper.addContacts();
        
        helper.addCustomData();
        
        helper.clickAwardHomePage();
        helper.set(AwardSeleniumHelper.ANTICIPATED_AMOUNT_ID, AMOUNT);
        helper.set(AwardSeleniumHelper.OBLIGATED_AMOUNT_ID, AMOUNT);
        
        helper.submit();
        
        awardDocNbr = helper.getDocumentNumber();
        
        helper.clickAwardBudgetVersionsPage();
        helper.set(AwardSeleniumHelper.BUDGET_NAME_ID, "Ver1");
        helper.click(AwardSeleniumHelper.ADD_BUDGET_NAME);
        helper.click(AwardSeleniumHelper.OPEN_BUDGET_NAME + "0");
        
        populateBudget();
        
        submitBudget();
        
        approveBudget();
        
        postBudget();
    }
    
    protected void populateBudget() {
        budgetHelper.addPersonnel();
        budgetHelper.setPersonPercents(0, 0, 0, "35", "35");
        budgetHelper.clickBudgetNonPersonnelTab();
        budgetHelper.addLineItem(0, DEFAULT_LINE_ITEM_DESC1, "1", "3.51");
        budgetHelper.addLineItem(1, DEFAULT_LINE_ITEM_DESC2, null, "1,759.60");
        budgetHelper.saveDocument();
    }
    
    protected void submitBudget() {
        budgetHelper.clickBudgetActionsTab();
        budgetHelper.routeDocument();
        budgetHelper.assertRoute();
    }
    
    protected void approveBudget() {
        helper.login("jtester");
        helper.docSearch(awardDocNbr);
        helper.clickAwardBudgetVersionsPage();
        helper.click(AwardSeleniumHelper.OPEN_BUDGET_NAME + "0");
        budgetHelper.clickBudgetActionsTab();
        budgetHelper.approveDocument();
    }
    
    protected void postBudget() {
        helper.login("quickstart");
        helper.docSearch(awardDocNbr);
        helper.clickAwardBudgetVersionsPage();
        helper.click(AwardSeleniumHelper.OPEN_BUDGET_NAME + "0");
        budgetHelper.clickBudgetActionsTab();
        budgetHelper.postBudget();
        budgetHelper.returnToAward();
        helper.clickAwardBudgetVersionsPage();
        helper.assertElementContains("tr td", "Posted");
    }
}
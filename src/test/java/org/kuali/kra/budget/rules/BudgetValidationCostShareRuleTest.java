/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.budget.rules;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetCostShare;
import org.kuali.kra.budget.rule.BudgetValidationCostShareRule;
import org.kuali.kra.budget.rule.event.BudgetValidationCostShareEvent;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;

public class BudgetValidationCostShareRuleTest extends TestCase {
    private static final int BUDGET_FISCAL_YEAR = 2008;
    private static final BudgetDecimal SHARE_AMOUNT = new BudgetDecimal(100.00);
    private static final BudgetDecimal SHARE_PCT = new BudgetDecimal(5.55);
    private static final String SOURCE_ACCOUNT = "12345A";
    
    private BudgetCostShare budgetCostShare;
    private BudgetValidationCostShareRule budgetCostShareRule;
    
    @Before
    public void setUp() {
        GlobalVariables.setErrorMap(new ErrorMap());
        budgetCostShareRule = new BudgetCostShareRuleImpl();
    }

    @After
    public void tearDown() {
        GlobalVariables.clear();
        budgetCostShareRule = null;
        budgetCostShare = null;
    }
    
    @Test
    public void testValidatingRequiredFields_AllRequiredFieldsFilled() throws Exception { 
        budgetCostShare = new BudgetCostShare(BUDGET_FISCAL_YEAR, SHARE_PCT, SHARE_AMOUNT, SOURCE_ACCOUNT);        
        Assert.assertTrue(budgetCostShareRule.processBudgetValidationCostShareBusinessRules(getEvent(budgetCostShare)));
        Assert.assertEquals(0, GlobalVariables.getErrorMap().keySet().size());
    }

    @Test
    public void testValidatingRequiredFields_FiscalYearMissing() throws Exception {
        budgetCostShare = new BudgetCostShare(null, SHARE_PCT, SHARE_AMOUNT, SOURCE_ACCOUNT);
        Assert.assertFalse(budgetCostShareRule.processBudgetValidationCostShareBusinessRules(getEvent(budgetCostShare)));
        Assert.assertEquals(1, GlobalVariables.getErrorMap().keySet().size());
    }

    @Test
    public void testValidatingRequiredFields_NoneSet() throws Exception {
        budgetCostShare = new BudgetCostShare();
        Assert.assertFalse(budgetCostShareRule.processBudgetValidationCostShareBusinessRules(getEvent(budgetCostShare)));
        Assert.assertEquals(2, GlobalVariables.getErrorMap().keySet().size());
    }
    
    @Test
    public void testValidatingRequiredFields_ShareAmountMissing() throws Exception {
        budgetCostShare = new BudgetCostShare(BUDGET_FISCAL_YEAR, null, SHARE_PCT, SOURCE_ACCOUNT);
        Assert.assertTrue(budgetCostShareRule.processBudgetValidationCostShareBusinessRules(getEvent(budgetCostShare)));
        Assert.assertEquals(0, GlobalVariables.getErrorMap().keySet().size());
    }
    
    @Test
    public void testValidatingRequiredFields_SourceAccountMissing() throws Exception {
        budgetCostShare = new BudgetCostShare(BUDGET_FISCAL_YEAR, SHARE_PCT, SHARE_AMOUNT, null);
        Assert.assertFalse(budgetCostShareRule.processBudgetValidationCostShareBusinessRules(getEvent(budgetCostShare)));
        Assert.assertEquals(1, GlobalVariables.getErrorMap().keySet().size());
    }
    
    private BudgetValidationCostShareEvent getEvent(BudgetCostShare budgetCostShare) {
        return new BudgetValidationCostShareEvent(null, null, null, budgetCostShare);
    }
}

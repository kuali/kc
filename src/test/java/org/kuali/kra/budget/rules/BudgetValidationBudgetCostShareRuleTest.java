/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.budget.rules;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetCostShare;
import org.kuali.kra.budget.rule.BudgetValidationBudgetCostShareRule;
import org.kuali.kra.budget.rule.event.BudgetValidationBudgetCostShareEvent;

public class BudgetValidationBudgetCostShareRuleTest extends TestCase {
    private static final BudgetDecimal SHARE_AMOUNT = new BudgetDecimal(100.00);
    private static final int BUDGET_FISCAL_YEAR = 2008;
    
    private BudgetValidationBudgetCostShareRule budgetCostShareRule;
    private BudgetCostShare budgetCostShare;
    private BudgetValidationBudgetCostShareEvent budgetValidationBudgetCostShareEvent;
    
    @Test
    public void testValidatingRequiredFields_NoneSet() throws Exception {
        Assert.assertFalse(budgetCostShareRule.processBudgetValidationBudgetCostShareBusinessRules(budgetValidationBudgetCostShareEvent));
        Assert.assertEquals(3, GlobalVariables.getErrorMap().keySet().size());
    }

    @Test
    public void testValidatingRequiredFields_AllRequiredFieldsFilled() throws Exception {        
        budgetCostShare.setFiscalYear(BUDGET_FISCAL_YEAR);
        budgetCostShare.setShareAmount(SHARE_AMOUNT);
        budgetCostShare.setSourceAccount("12345A");
        Assert.assertTrue(budgetCostShareRule.processBudgetValidationBudgetCostShareBusinessRules(budgetValidationBudgetCostShareEvent));
        Assert.assertEquals(0, GlobalVariables.getErrorMap().keySet().size());
    }

    @Test
    public void testValidatingRequiredFields_BudgetPeriodAndProjectIncomeSet() throws Exception {
        budgetCostShare.setFiscalYear(BUDGET_FISCAL_YEAR);
        budgetCostShare.setShareAmount(SHARE_AMOUNT);
        Assert.assertFalse(budgetCostShareRule.processBudgetValidationBudgetCostShareBusinessRules(budgetValidationBudgetCostShareEvent));
        Assert.assertEquals(1, GlobalVariables.getErrorMap().keySet().size());
    }

    @Test
    public void testValidatingRequiredFields_BudgetPeriodSet() throws Exception {
        budgetCostShare.setFiscalYear(BUDGET_FISCAL_YEAR);
        Assert.assertFalse(budgetCostShareRule.processBudgetValidationBudgetCostShareBusinessRules(budgetValidationBudgetCostShareEvent));
        Assert.assertEquals(2, GlobalVariables.getErrorMap().keySet().size());
    }
    
    @Before
    public void setUp() {
        GlobalVariables.setErrorMap(new ErrorMap());
        budgetCostShare = new BudgetCostShare();
        budgetValidationBudgetCostShareEvent = new BudgetValidationBudgetCostShareEvent(null, null, null, budgetCostShare);
        budgetCostShareRule = new BudgetCostShareRuleImpl();
    }
    
    @After
    public void tearDown() {
        GlobalVariables.clear();
        budgetCostShareRule = null;
        budgetCostShare = null;
        budgetValidationBudgetCostShareEvent = null;
    }
}

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
import org.kuali.core.util.KualiDecimal;
import org.kuali.kra.budget.bo.BudgetProjectIncome;
import org.kuali.kra.budget.rule.AddBudgetProjectIncomeRule;
import org.kuali.kra.budget.rule.event.AddBudgetProjectIncomeEvent;

public class BudgetProjectIncomeRuleTest extends TestCase {
    private static final KualiDecimal PROJECT_INCOME_AMOUNT = new KualiDecimal(100.00);
    private static final int BUDGET_PERIOD_NO = 1;
    private AddBudgetProjectIncomeRule addBudgetProjectIncomeRule;
    private BudgetProjectIncome budgetProjectIncome;
    private AddBudgetProjectIncomeEvent addBudgetIncomeEvent;
    
    @Test
    public void testValidatingRequiredFields_NoneSet() throws Exception {
        Assert.assertFalse(addBudgetProjectIncomeRule.processAddBudgetProjectIncomeBusinessRules(addBudgetIncomeEvent));
        Assert.assertEquals(3, GlobalVariables.getErrorMap().keySet().size());
    }

    @Test
    public void testValidatingRequiredFields_DescriptionFilledWithWhiteSpace() throws Exception {        
        budgetProjectIncome.setBudgetPeriodNumber(BUDGET_PERIOD_NO);
        budgetProjectIncome.setProjectIncome(PROJECT_INCOME_AMOUNT);
        budgetProjectIncome.setDescription("  ");
        Assert.assertFalse(addBudgetProjectIncomeRule.processAddBudgetProjectIncomeBusinessRules(addBudgetIncomeEvent));
        Assert.assertEquals(1, GlobalVariables.getErrorMap().keySet().size());
    }
    
    @Test
    public void testValidatingRequiredFields_AllRequiredFieldsFilled() throws Exception {        
        budgetProjectIncome.setBudgetPeriodNumber(BUDGET_PERIOD_NO);
        budgetProjectIncome.setProjectIncome(PROJECT_INCOME_AMOUNT);
        budgetProjectIncome.setDescription("Description");
        Assert.assertTrue(addBudgetProjectIncomeRule.processAddBudgetProjectIncomeBusinessRules(addBudgetIncomeEvent));
        Assert.assertEquals(0, GlobalVariables.getErrorMap().keySet().size());
    }

    @Test
    public void testValidatingRequiredFields_BudgetPeriodAndProjectIncomeSet() throws Exception {
        budgetProjectIncome.setBudgetPeriodNumber(BUDGET_PERIOD_NO);
        budgetProjectIncome.setProjectIncome(PROJECT_INCOME_AMOUNT);
        Assert.assertFalse(addBudgetProjectIncomeRule.processAddBudgetProjectIncomeBusinessRules(addBudgetIncomeEvent));
        Assert.assertEquals(BUDGET_PERIOD_NO, GlobalVariables.getErrorMap().keySet().size());
    }

    @Test
    public void testValidatingRequiredFields_BudgetPeriodSet() throws Exception {
        budgetProjectIncome.setBudgetPeriodNumber(BUDGET_PERIOD_NO);
        Assert.assertFalse(addBudgetProjectIncomeRule.processAddBudgetProjectIncomeBusinessRules(addBudgetIncomeEvent));
        Assert.assertEquals(2, GlobalVariables.getErrorMap().keySet().size());
    }
    
    @Before
    public void setUp() {
        GlobalVariables.setErrorMap(new ErrorMap());
        budgetProjectIncome = new BudgetProjectIncome();
        addBudgetIncomeEvent = new AddBudgetProjectIncomeEvent(null, null, null, budgetProjectIncome);
        addBudgetProjectIncomeRule = new BudgetProjectIncomeRuleImpl();
    }
    
    @After
    public void tearDown() {
        GlobalVariables.clear();
        addBudgetProjectIncomeRule = null;
        budgetProjectIncome = null;
        addBudgetIncomeEvent = null;
    }
}

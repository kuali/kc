/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.budget.rules;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.budget.framework.income.AddBudgetProjectIncomeEvent;
import org.kuali.coeus.common.budget.framework.income.BudgetProjectIncome;
import org.kuali.coeus.common.budget.framework.income.BudgetProjectIncomeRule;
import org.kuali.coeus.common.budget.impl.core.ValidationHelper;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.impl.gv.GlobalVariableServiceImpl;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.util.List;
import java.util.Properties;

public class AddBudgetProjectIncomeRuleTest {
    private static final ScaleTwoDecimal PROJECT_INCOME_AMOUNT = new ScaleTwoDecimal(100.00);
    private static final int BUDGET_PERIOD_NO = 1;
    private BudgetProjectIncomeRule addBudgetProjectIncomeRule;
    private BudgetProjectIncome budgetProjectIncome;
    private AddBudgetProjectIncomeEvent addBudgetIncomeEvent;
    
    @Test
    public void testValidatingRequiredFields_NoneSet() throws Exception {
        Assert.assertFalse(addBudgetProjectIncomeRule.processAddBudgetProjectIncomeBusinessRules(addBudgetIncomeEvent));
        Assert.assertEquals(3, GlobalVariables.getMessageMap().getErrorMessages().keySet().size());
    }

    @Test
    public void testValidatingRequiredFields_DescriptionFilledWithWhiteSpace() throws Exception {        
        budgetProjectIncome.setBudgetPeriodNumber(BUDGET_PERIOD_NO);
        budgetProjectIncome.setProjectIncome(PROJECT_INCOME_AMOUNT);
        budgetProjectIncome.setDescription("  ");
        Assert.assertFalse(addBudgetProjectIncomeRule.processAddBudgetProjectIncomeBusinessRules(addBudgetIncomeEvent));
        Assert.assertEquals(1, GlobalVariables.getMessageMap().getErrorMessages().keySet().size());
    }
    
    @Test
    public void testValidatingRequiredFields_AllRequiredFieldsFilled() throws Exception {        
        budgetProjectIncome.setBudgetPeriodNumber(BUDGET_PERIOD_NO);
        budgetProjectIncome.setProjectIncome(PROJECT_INCOME_AMOUNT);
        budgetProjectIncome.setDescription("Description");
        Assert.assertTrue(addBudgetProjectIncomeRule.processAddBudgetProjectIncomeBusinessRules(addBudgetIncomeEvent));
        Assert.assertEquals(0, GlobalVariables.getMessageMap().getErrorMessages().keySet().size());
    }

    @Test
    public void testValidatingProjectIncomeField_Zero() throws Exception {        
        budgetProjectIncome.setBudgetPeriodNumber(BUDGET_PERIOD_NO);
        budgetProjectIncome.setProjectIncome(ScaleTwoDecimal.ZERO);
        budgetProjectIncome.setDescription("Description");
        Assert.assertFalse(addBudgetProjectIncomeRule.processAddBudgetProjectIncomeBusinessRules(addBudgetIncomeEvent));
        Assert.assertEquals(1, GlobalVariables.getMessageMap().getErrorMessages().keySet().size());
        ErrorMessage errMsg = (ErrorMessage)((List<ErrorMessage>) GlobalVariables.getMessageMap().getErrorMessages().get("newBudgetProjectIncome.projectIncome")).get(0);
        Assert.assertEquals("error.projectIncome.negativeOrZero", errMsg.getErrorKey());        
        Assert.assertEquals("Income amount must be greater than zero (0.00)", getErrorForkey("error.projectIncome.negativeOrZero"));
    }
    
    private String getErrorForkey(String key) throws Exception {
        Properties props = new Properties();
        props.load(getClass().getClassLoader().getResourceAsStream("ApplicationResources.properties"));
        return props.getProperty(key);
    }

    @Test
    public void testValidatingRequiredFields_BudgetPeriodAndProjectIncomeSet() throws Exception {
        budgetProjectIncome.setBudgetPeriodNumber(BUDGET_PERIOD_NO);
        budgetProjectIncome.setProjectIncome(PROJECT_INCOME_AMOUNT);
        Assert.assertFalse(addBudgetProjectIncomeRule.processAddBudgetProjectIncomeBusinessRules(addBudgetIncomeEvent));
        Assert.assertEquals(BUDGET_PERIOD_NO, GlobalVariables.getMessageMap().getErrorMessages().keySet().size());
    }

    @Test
    public void testValidatingRequiredFields_BudgetPeriodSet() throws Exception {
        budgetProjectIncome.setBudgetPeriodNumber(BUDGET_PERIOD_NO);
        Assert.assertFalse(addBudgetProjectIncomeRule.processAddBudgetProjectIncomeBusinessRules(addBudgetIncomeEvent));
        Assert.assertEquals(2, GlobalVariables.getMessageMap().getErrorMessages().keySet().size());
    }
    
    @Before
    public void setUp() {
        GlobalVariables.setMessageMap(new MessageMap());
        budgetProjectIncome = new BudgetProjectIncome();
        addBudgetIncomeEvent = new AddBudgetProjectIncomeEvent(null, budgetProjectIncome);
        addBudgetProjectIncomeRule = new BudgetProjectIncomeRule();
        ValidationHelper helper = new ValidationHelper();
        helper.setGlobalVariableService(new GlobalVariableServiceImpl());
        addBudgetProjectIncomeRule.setValidationHelper(helper);
        
    }
    
    @After
    public void tearDown() {
        GlobalVariables.clear();
        addBudgetProjectIncomeRule = null;
        budgetProjectIncome = null;
        addBudgetIncomeEvent = null;
    }
}

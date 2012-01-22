/*
 * Copyright 2005-2010 The Kuali Foundation
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

import java.util.Properties;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.budget.distributionincome.AddBudgetProjectIncomeEvent;
import org.kuali.kra.budget.distributionincome.AddBudgetProjectIncomeRule;
import org.kuali.kra.budget.distributionincome.BudgetProjectIncome;
import org.kuali.kra.budget.distributionincome.BudgetProjectIncomeRuleImpl;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;
import org.springframework.util.AutoPopulatingList;

public class AddBudgetProjectIncomeRuleTest {
    private static final KualiDecimal PROJECT_INCOME_AMOUNT = new KualiDecimal(100.00);
    private static final int BUDGET_PERIOD_NO = 1;
    private AddBudgetProjectIncomeRule addBudgetProjectIncomeRule;
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
        budgetProjectIncome.setProjectIncome(KualiDecimal.ZERO);
        budgetProjectIncome.setDescription("Description");
        Assert.assertFalse(addBudgetProjectIncomeRule.processAddBudgetProjectIncomeBusinessRules(addBudgetIncomeEvent));
        Assert.assertEquals(1, GlobalVariables.getMessageMap().getErrorMessages().keySet().size());
        ErrorMessage errMsg = (ErrorMessage)((AutoPopulatingList<ErrorMessage>) GlobalVariables.getMessageMap().getErrorMessages().get("newBudgetProjectIncome.projectIncome")).get(0);
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

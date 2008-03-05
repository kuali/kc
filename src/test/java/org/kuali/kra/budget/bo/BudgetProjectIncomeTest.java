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
package org.kuali.kra.budget.bo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.util.KualiDecimal;
import org.kuali.kra.budget.document.BudgetDocument;

public class BudgetProjectIncomeTest {

    private static final double _1K = 1000.00;
    private static final double _2K = 2000.00;
    private static final double _3K = 3000.00;
    private static final double _4K = 4000.00;
    private static final double _500 = 500.00;
    
    private static final int BUDGET_PERIOD_1 = 1;
    private static final int BUDGET_PERIOD_2 = 2;
    private static final int BUDGET_PERIOD_3 = 3;
    private static final int BUDGET_PERIOD_4 = 4;
    
    private BudgetDocument document;

    @Before
    public void setUp() throws Exception {
        document = new BudgetDocument();
        document.add(createBudgetProjectIncome(BUDGET_PERIOD_1, _1K));
        document.add(createBudgetProjectIncome(BUDGET_PERIOD_2, _2K));        
        document.add(createBudgetProjectIncome(BUDGET_PERIOD_3, _3K));
        document.add(createBudgetProjectIncome(BUDGET_PERIOD_3, _500));
        document.add(createBudgetProjectIncome(BUDGET_PERIOD_4, _4K));
        document.add(createBudgetProjectIncome(BUDGET_PERIOD_4, _500));
        document.add(createBudgetProjectIncome(BUDGET_PERIOD_4, _1K));
    }

    @After
    public void tearDown() throws Exception {
        document = null;
    }

    @Test
    public void testTotalingProjectIncomesForBudgetDocument() throws Exception {
        Assert.assertEquals(_1K + _2K + _3K + _500 + _4K + _500 + _1K, document.getProjectIncomeTotal().doubleValue(), 0.01);
        
        double total = extractPeriodTotal(BUDGET_PERIOD_1) + extractPeriodTotal(BUDGET_PERIOD_2) + 
                        extractPeriodTotal(BUDGET_PERIOD_3) + extractPeriodTotal(BUDGET_PERIOD_4);
        
        Assert.assertEquals(total, document.getProjectIncomeTotal().doubleValue(), 0.01);
    }
    
    @Test
    public void testTotalingProjectIncomesForPeriod() throws Exception {
        Assert.assertEquals(7, document.getBudgetProjectIncomes().size());
        Assert.assertEquals(4, document.getProjectIncomePeriodTotals().size());
        Assert.assertEquals(_1K, extractPeriodTotal(BUDGET_PERIOD_1), 0.01);
        Assert.assertEquals(_2K, extractPeriodTotal(BUDGET_PERIOD_2), 0.01);
        Assert.assertEquals(_3K+_500, extractPeriodTotal(BUDGET_PERIOD_3), 0.01);
        Assert.assertEquals(_4K+_500+_1K, extractPeriodTotal(BUDGET_PERIOD_4), 0.01);
    }
    
    private BudgetProjectIncome createBudgetProjectIncome(Integer budgetPeriodNumber, double amount) {
        BudgetProjectIncome budgetProjectIncome = new BudgetProjectIncome();        
        budgetProjectIncome.setBudgetPeriodNumber(budgetPeriodNumber);
        budgetProjectIncome.setProjectIncome(new KualiDecimal(amount));
        return budgetProjectIncome;
    }
    
    private double extractPeriodTotal(int budgetPeriodNo) {
        return document.getProjectIncomePeriodTotals().get(budgetPeriodNo - 1).doubleValue();
    }
}

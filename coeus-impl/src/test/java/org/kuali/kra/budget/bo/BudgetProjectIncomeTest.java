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
package org.kuali.kra.budget.bo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.income.BudgetProjectIncome;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.sys.framework.util.DateUtils;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.util.Calendar;

public class BudgetProjectIncomeTest {

    private static final double _1K = 1000.00;
    private static final double _2K = 2000.00;
    private static final double _3K = 3000.00;
    private static final double _4K = 4000.00;
    private static final double _500 = 500.00;
    
    private Budget document;

    private int nextValue = 1;
    
    @Before
    public void setUp() throws Exception {
        initializeDocument();
        document.add(createBudgetProjectIncome(document.getBudgetPeriod(0), _1K));
        document.add(createBudgetProjectIncome(document.getBudgetPeriod(1), _2K));        
        document.add(createBudgetProjectIncome(document.getBudgetPeriod(2), _3K));
        document.add(createBudgetProjectIncome(document.getBudgetPeriod(2), _500));
        document.add(createBudgetProjectIncome(document.getBudgetPeriod(3), _4K));
        document.add(createBudgetProjectIncome(document.getBudgetPeriod(3), _500));
        document.add(createBudgetProjectIncome(document.getBudgetPeriod(3), _1K));
    }

    @After
    public void tearDown() throws Exception {
        document = null;
    }

    @Test
    public void testTotalingProjectIncomesForBudgetDocument() throws Exception {
        Assert.assertEquals(_1K + _2K + _3K + _500 + _4K + _500 + _1K, document.getProjectIncomeTotal().doubleValue(), 0.01);
        
        double total = 0.0;
        for(BudgetPeriod bp: document.getBudgetPeriods()) {
            total += extractPeriodTotal(bp);
        }
        
        Assert.assertEquals(total, document.getProjectIncomeTotal().doubleValue(), 0.01);
    }
    
    @Test
    public void testTotalingProjectIncomesForPeriod() throws Exception {
        Assert.assertEquals(7, document.getBudgetProjectIncomes().size());
        Assert.assertEquals(4, document.getProjectIncomePeriodTotalsForEachBudgetPeriod().size());
        Assert.assertEquals(_1K, extractPeriodTotal(document.getBudgetPeriod(0)), 0.01);
        Assert.assertEquals(_2K, extractPeriodTotal(document.getBudgetPeriod(1)), 0.01);
        Assert.assertEquals(_3K+_500, extractPeriodTotal(document.getBudgetPeriod(2)), 0.01);
        Assert.assertEquals(_4K+_500+_1K, extractPeriodTotal(document.getBudgetPeriod(3)), 0.01);
    }
    
    private BudgetProjectIncome createBudgetProjectIncome(BudgetPeriod budgetPeriod, double amount) {
        BudgetProjectIncome budgetProjectIncome = new BudgetProjectIncome();        
        budgetProjectIncome.setBudgetPeriodNumber(budgetPeriod.getBudgetPeriod());
        budgetProjectIncome.setProjectIncome(new ScaleTwoDecimal(amount));
        return budgetProjectIncome;
    }
    
    private double extractPeriodTotal(BudgetPeriod budgetPeriod) {
        return document.getProjectIncomePeriodTotalsForEachBudgetPeriod().get(budgetPeriod.getBudgetPeriod() - 1).doubleValue();
    }
    
    @SuppressWarnings("serial")
    private void initializeDocument() {
        document = new Budget() {
            // stub out method to avoid loading Spring and database
            @Override
            public Integer getNextValue(String propertyName) {
                return nextValue++;
            }            
        };
        
        document.add(createBudgetPeriod(1, 2007));
        document.add(createBudgetPeriod(2, 2008));
        document.add(createBudgetPeriod(3, 2009));
        document.add(createBudgetPeriod(4, 2010));
    }
    
    private BudgetPeriod createBudgetPeriod(int budgetPeriodNumber, int year) {
        BudgetPeriod budgetPeriod = new BudgetPeriod();
        budgetPeriod.setBudgetPeriod(budgetPeriodNumber);
        budgetPeriod.setStartDate(DateUtils.newDate(year, Calendar.JANUARY, 1, 0, 0, 0));
        budgetPeriod.setEndDate(DateUtils.newDate(year, Calendar.DECEMBER, 31, 23, 59, 59));
        return budgetPeriod;
    }
}

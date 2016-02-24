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
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.core.Budget.FiscalYearSummary;

import java.util.Calendar;
import java.util.List;

public class BudgetUnrecoveredFandATest extends BudgetDistributionAndIncomeTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    @Test
    public void testCalculatingTotalUnrecoveredFandA() {
        createAndAddBudgetPeriod().setUnderrecoveryAmount(FY_2007_Q3_AMT);
        Assert.assertEquals(FY_2007_Q3_AMT, budgetDocument.getAvailableUnrecoveredFandA());
        
        createAndAddBudgetPeriod().setUnderrecoveryAmount(FY_2007_Q4_AMT);
        Assert.assertEquals(FY_2007_Q3_AMT.add(FY_2007_Q4_AMT), budgetDocument.getAvailableUnrecoveredFandA());
        
        createAndAddBudgetPeriod().setUnderrecoveryAmount(FY_2008_Q1_AMT);
        Assert.assertEquals(FY_2007_Q3_AMT.add(FY_2007_Q4_AMT).add(FY_2008_Q1_AMT), budgetDocument.getAvailableUnrecoveredFandA());
        
        Assert.assertTrue(budgetDocument.isUnrecoveredFandAAvailable());
    }
    
    @Test
    public void testIfUnrecoveredFandAApplicable_UnrecoveredAmountsPresentAndApplicable() {
        createAndAddBudgetPeriod().setUnderrecoveryAmount(FY_2007_Q3_AMT);
        createAndAddBudgetPeriod().setUnderrecoveryAmount(ScaleTwoDecimal.ZERO);
        createAndAddBudgetPeriod().setUnderrecoveryAmount(FY_2008_Q1_AMT);
        
        Assert.assertTrue(budgetDocument.isUnrecoveredFandAApplicable());
    }
    
    @Test
    public void testIfUnrecoveredFandAApplicable_UnrecoveredAmountsPresentAndNotApplicable() {
        // replace budgetDocument with one where unrecovered F&A is not applicable
        budgetDocument = new BudgetDocument_CostShareAndUnrecoveredFandANotApplicable();
        
        createAndAddBudgetPeriod().setUnderrecoveryAmount(FY_2007_Q3_AMT);
        createAndAddBudgetPeriod().setUnderrecoveryAmount(FY_2007_Q4_AMT);
        createAndAddBudgetPeriod().setUnderrecoveryAmount(FY_2008_Q1_AMT);
        
        Assert.assertFalse(budgetDocument.isUnrecoveredFandAApplicable());
    }

    @Test
    public void testIfUnrecoveredFandAIsAvailable_BudgetPeriodPresentWithNonZeroUnrecovery() {
        Assert.assertFalse(budgetDocument.isUnrecoveredFandAAvailable());
        
        createAndAddBudgetPeriod().setUnderrecoveryAmount(FY_2007_Q3_AMT);
        Assert.assertTrue(budgetDocument.isUnrecoveredFandAAvailable());
    }
    
    @Test
    public void testIfUnrecoveredFandAIsAvailable_BudgetPeriodPresentButNoUnrecoveredFandA() {
        Assert.assertFalse(budgetDocument.isUnrecoveredFandAAvailable());
    }
    
    @Test
    public void testFindingUnrecoveredFandAForFiscalYear() throws Exception {
        createBudgetPeriodsForThreeFiscalYears();
        Assert.assertEquals(FY_2007_Q3_AMT.add(FY_2007_Q4_AMT), budgetDocument.findUnrecoveredFandAForFiscalYear(YEAR_2007));
        Assert.assertEquals(FY_2008_Q1_AMT.add(FY_2008_Q2_AMT), budgetDocument.findUnrecoveredFandAForFiscalYear(YEAR_2008));
        Assert.assertEquals(FY_2009_Q1_AMT.add(FY_2009_Q2_AMT), budgetDocument.findUnrecoveredFandAForFiscalYear(YEAR_2009));
        Assert.assertEquals(ScaleTwoDecimal.ZERO, budgetDocument.findUnrecoveredFandAForFiscalYear(YEAR_2000));
    }
    
    @Test
    public void testGettingFiscalYearUnrecoveredFandATotals() {
        createBudgetPeriodsForThreeFiscalYears();
        
        List<FiscalYearSummary> fiscalYearUnrecoveredFandATotals = budgetDocument.getFiscalYearUnrecoveredFandATotals();
        Assert.assertEquals(3, fiscalYearUnrecoveredFandATotals.size());
        
        FiscalYearSummary fiscalYearSummary = fiscalYearUnrecoveredFandATotals.get(0); 
        Assert.assertEquals(2007, fiscalYearSummary.getFiscalYear());
        Assert.assertEquals(getDate(YEAR_2007, Calendar.JANUARY, DAY_1), fiscalYearSummary.getAssignedBudgetPeriod().getStartDate());
        Assert.assertEquals(FY_2007_Q3_AMT.add(FY_2007_Q4_AMT), fiscalYearSummary.getUnrecoveredFandA());
        
        fiscalYearSummary = fiscalYearUnrecoveredFandATotals.get(1); 
        Assert.assertEquals(2008, fiscalYearSummary.getFiscalYear());
        Assert.assertEquals(getDate(YEAR_2007, Calendar.JULY, DAY_1), fiscalYearSummary.getAssignedBudgetPeriod().getStartDate());
        Assert.assertEquals(FY_2008_Q1_AMT.add(FY_2008_Q2_AMT), fiscalYearSummary.getUnrecoveredFandA());
        
        fiscalYearSummary = fiscalYearUnrecoveredFandATotals.get(2); 
        Assert.assertEquals(2009, fiscalYearSummary.getFiscalYear());
        Assert.assertEquals(getDate(YEAR_2008, Calendar.OCTOBER, DAY_1), fiscalYearSummary.getAssignedBudgetPeriod().getStartDate());
        Assert.assertEquals(FY_2009_Q1_AMT.add(FY_2009_Q2_AMT), fiscalYearSummary.getUnrecoveredFandA());
    }
}

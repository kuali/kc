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
package org.kuali.kra.budget.bo;

import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.core.Budget.FiscalYearSummary;

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
        createAndAddBudgetPeriod().setUnderrecoveryAmount(BudgetDecimal.ZERO);
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
        Assert.assertEquals(BudgetDecimal.ZERO, budgetDocument.findUnrecoveredFandAForFiscalYear(YEAR_2000));
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

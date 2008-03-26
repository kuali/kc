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

import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.budget.document.BudgetDocument.FiscalYearSummary;

public class BudgetUnrecoveredFandATest extends BudgetDistributionAndIncomeTest {

    @Before
    public void setUp() {
        super.setUp();
    }
    
    @After
    public void tearDown() {
        super.tearDown();
    }
    
    @Test
    public void testCalculatingTotalUnrecoveredFandA() {
        createAndAddBudgetPeriod().setUnderrecoveryAmount(TEST_AMOUNT_250);
        Assert.assertEquals(TEST_AMOUNT_250, budgetDocument.getAvailableUnrecoveredFandA());
        
        createAndAddBudgetPeriod().setUnderrecoveryAmount(ZERO_AMOUNT);
        Assert.assertEquals(TEST_AMOUNT_250, budgetDocument.getAvailableUnrecoveredFandA());
        
        createAndAddBudgetPeriod().setUnderrecoveryAmount(TEST_AMOUNT_100);
        Assert.assertEquals(TEST_AMOUNT_250.add(TEST_AMOUNT_100), budgetDocument.getAvailableUnrecoveredFandA());
        
        Assert.assertTrue(budgetDocument.isUnrecoveredFandAAvailable());
    }
    
    @Test
    public void testIfUnrecoveredFandAApplicable_UnrecoveredAmountsPresentAndApplicable() {
        createAndAddBudgetPeriod().setUnderrecoveryAmount(TEST_AMOUNT_250);
        createAndAddBudgetPeriod().setUnderrecoveryAmount(ZERO_AMOUNT);
        createAndAddBudgetPeriod().setUnderrecoveryAmount(TEST_AMOUNT_100);
        
        Assert.assertTrue(budgetDocument.isUnrecoveredFandAApplicable());
    }
    
    @Test
    public void testIfUnrecoveredFandAApplicable_UnrecoveredAmountsPresentAndNotApplicable() {
        // replace budgetDocument with one where unrecovered F&A is not applicable
        budgetDocument = new BudgetDocument_CostShareAndUnrecoveredFandANotApplicable();
        
        createAndAddBudgetPeriod().setUnderrecoveryAmount(TEST_AMOUNT_250);
        createAndAddBudgetPeriod().setUnderrecoveryAmount(ZERO_AMOUNT);
        createAndAddBudgetPeriod().setUnderrecoveryAmount(TEST_AMOUNT_100);
        
        Assert.assertFalse(budgetDocument.isUnrecoveredFandAApplicable());
    }

    @Test
    public void testIfUnrecoveredFandAIsAvailable_BudgetPeriodPresentWithNonZeroUnrecovery() {
        Assert.assertFalse(budgetDocument.isUnrecoveredFandAAvailable());
        
        createAndAddBudgetPeriod().setUnderrecoveryAmount(TEST_AMOUNT_100);
        Assert.assertTrue(budgetDocument.isUnrecoveredFandAAvailable());
    }
    
    @Test
    public void testIfUnrecoveredFandAIsAvailable_BudgetPeriodPresentButNoUnrecoveredFandA() {
        Assert.assertFalse(budgetDocument.isUnrecoveredFandAAvailable());
    }
    
    @Test
    public void testFindingUnrecoveredFandAForFiscalYear() throws Exception {
        createBudgetPeriodsForThreeFiscalYears();
        Assert.assertEquals(TEST_AMOUNT_250.add(TEST_AMOUNT_100), budgetDocument.findUnrecoveredFandAForFiscalYear(YEAR_2007));
        Assert.assertEquals(TEST_AMOUNT_250, budgetDocument.findUnrecoveredFandAForFiscalYear(YEAR_2008));
        Assert.assertEquals(TEST_AMOUNT_250.add(TEST_AMOUNT_100), budgetDocument.findUnrecoveredFandAForFiscalYear(YEAR_2009));
        Assert.assertEquals(ZERO_AMOUNT, budgetDocument.findUnrecoveredFandAForFiscalYear(YEAR_2000));
    }
    
    @Test
    public void testGettingFiscalYearUnrecoveredFandATotals() {
        createBudgetPeriodsForThreeFiscalYears();
        
        List<FiscalYearSummary> fiscalYearUnrecoveredFandATotals = budgetDocument.getFiscalYearUnrecoveredFandATotals();
        Assert.assertEquals(3, fiscalYearUnrecoveredFandATotals.size());
        
        FiscalYearSummary fiscalYearSummary = fiscalYearUnrecoveredFandATotals.get(0); 
        Assert.assertEquals(2007, fiscalYearSummary.getFiscalYear());
        Assert.assertEquals(getDate(YEAR_2007, Calendar.JANUARY, DAY_1), fiscalYearSummary.getAssignedBudgetPeriod().getStartDate());
        Assert.assertEquals(TEST_AMOUNT_250.add(TEST_AMOUNT_100), fiscalYearSummary.getUnrecoveredFandA());
        
        fiscalYearSummary = fiscalYearUnrecoveredFandATotals.get(1); 
        Assert.assertEquals(2008, fiscalYearSummary.getFiscalYear());
        Assert.assertEquals(getDate(YEAR_2007, Calendar.NOVEMBER, DAY_1), fiscalYearSummary.getAssignedBudgetPeriod().getStartDate());
        Assert.assertEquals(TEST_AMOUNT_250, fiscalYearSummary.getUnrecoveredFandA());
        
        fiscalYearSummary = fiscalYearUnrecoveredFandATotals.get(2); 
        Assert.assertEquals(2009, fiscalYearSummary.getFiscalYear());
        Assert.assertEquals(getDate(YEAR_2008, Calendar.OCTOBER, DAY_1), fiscalYearSummary.getAssignedBudgetPeriod().getStartDate());
        Assert.assertEquals(TEST_AMOUNT_250.add(TEST_AMOUNT_100), fiscalYearSummary.getUnrecoveredFandA());
    }
}

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

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.BudgetDocument.FiscalYearSummary;

public class BudgetCostShareTest extends BudgetDistributionAndIncomeTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    @Test
    public void testCalculatingTotalCostSharing() {
        createAndAddBudgetPeriod().setCostSharingAmount(TEST_AMOUNT_100);
        Assert.assertEquals(TEST_AMOUNT_100, budgetDocument.getAvailableCostSharing());
        
        createAndAddBudgetPeriod().setCostSharingAmount(ZERO_AMOUNT);
        Assert.assertEquals(TEST_AMOUNT_100, budgetDocument.getAvailableCostSharing());
        
        createAndAddBudgetPeriod().setCostSharingAmount(TEST_AMOUNT_100);
        Assert.assertEquals(TEST_AMOUNT_100.add(TEST_AMOUNT_100), budgetDocument.getAvailableCostSharing());
        
        Assert.assertTrue(budgetDocument.isCostSharingAvailable());
    }
    
    @Test
    public void testGettingFiscalYearCostShareTotals() {
        createBudgetPeriodsForThreeFiscalYears();
        
        List<FiscalYearSummary> fiscalYearCostShareTotals = budgetDocument.getFiscalYearCostShareTotals();
        Assert.assertEquals(3, fiscalYearCostShareTotals.size());
        
        FiscalYearSummary fiscalYearSummary = fiscalYearCostShareTotals.get(0); 
        Assert.assertEquals(2007, fiscalYearSummary.getFiscalYear());
        Assert.assertEquals(getDate(YEAR_2007, Calendar.JANUARY, DAY_1), fiscalYearSummary.getAssignedBudgetPeriod().getStartDate());
        Assert.assertEquals(TEST_AMOUNT_100.add(TEST_AMOUNT_100), fiscalYearSummary.getCostShare());
        
        fiscalYearSummary = fiscalYearCostShareTotals.get(1); 
        Assert.assertEquals(2008, fiscalYearSummary.getFiscalYear());
        Assert.assertEquals(getDate(YEAR_2007, Calendar.NOVEMBER, DAY_1), fiscalYearSummary.getAssignedBudgetPeriod().getStartDate());
        Assert.assertEquals(TEST_AMOUNT_100, fiscalYearSummary.getCostShare());
        
        fiscalYearSummary = fiscalYearCostShareTotals.get(2); 
        Assert.assertEquals(2009, fiscalYearSummary.getFiscalYear());
        Assert.assertEquals(getDate(YEAR_2008, Calendar.OCTOBER, DAY_1), fiscalYearSummary.getAssignedBudgetPeriod().getStartDate());
        Assert.assertEquals(TEST_AMOUNT_100.add(TEST_AMOUNT_100), fiscalYearSummary.getCostShare());
    }
    
    @Test
    public void testIfCostSharingIsAvailable_BudgetPeriodPresentWithNonZeroCostSharing() {
        Assert.assertFalse(budgetDocument.isCostSharingAvailable());
        
        createAndAddBudgetPeriod().setCostSharingAmount(TEST_AMOUNT_100);
        Assert.assertTrue(budgetDocument.isCostSharingAvailable());
    }
    
    @Test
    public void testCorrectBudgetPeriodAssignedForFiscalYear() {
        createAndAddBudgetPeriod(getDate(YEAR_2007, Calendar.NOVEMBER, DAY_1), getDate(YEAR_2007, Calendar.DECEMBER, DAY_30)).setCostSharingAmount(TEST_AMOUNT_100);
        createAndAddBudgetPeriod(getDate(YEAR_2007, Calendar.JULY, DAY_1), getDate(YEAR_2007, Calendar.SEPTEMBER, DAY_30)).setCostSharingAmount(TEST_AMOUNT_100);
        createAndAddBudgetPeriod(getDate(YEAR_2007, Calendar.APRIL, DAY_1), getDate(YEAR_2007, Calendar.JUNE, DAY_30)).setCostSharingAmount(ZERO_AMOUNT);
        
        Date startDate = getDate(YEAR_2007, Calendar.JANUARY, DAY_1);
        createAndAddBudgetPeriod(startDate, getDate(YEAR_2007, Calendar.MARCH, DAY_30)).setCostSharingAmount(TEST_AMOUNT_100);        
                
        List<FiscalYearSummary> fiscalYearCostShareTotals = budgetDocument.getFiscalYearCostShareTotals();
        FiscalYearSummary fiscalYearSummary = fiscalYearCostShareTotals.get(0); 
        Assert.assertEquals(startDate, fiscalYearSummary.getAssignedBudgetPeriod().getStartDate());        
    }
    
    @Test
    public void testFindingCostSharingForFiscalYear() throws Exception {
        createBudgetPeriodsForThreeFiscalYears();
        Assert.assertEquals(TEST_AMOUNT_100.add(TEST_AMOUNT_100), budgetDocument.findCostSharingForFiscalYear(YEAR_2007));
        Assert.assertEquals(TEST_AMOUNT_100, budgetDocument.findCostSharingForFiscalYear(YEAR_2008));
        Assert.assertEquals(TEST_AMOUNT_100.add(TEST_AMOUNT_100), budgetDocument.findCostSharingForFiscalYear(YEAR_2009));
        Assert.assertEquals(ZERO_AMOUNT, budgetDocument.findCostSharingForFiscalYear(YEAR_2000));
    }
    
    @Test
    public void testIfCostSharingIsAvailable_BudgetPeriodPresentWithZeroCostSharing() {
        createAndAddBudgetPeriod().setCostSharingAmount(ZERO_AMOUNT);
        Assert.assertFalse(budgetDocument.isCostSharingAvailable());
    }
    
    @Test
    public void testIfCostSharingIsAvailable_BudgetPeriodPresentButNoCostSharing() {
        Assert.assertFalse(budgetDocument.isCostSharingAvailable());
    }

    @Test
    public void testIfCostSharingIsAvailable_NoBudgetPeriods() {
        budgetDocument.getBudgetPeriods().clear();
        Assert.assertFalse(budgetDocument.isCostSharingAvailable());
    }
    
    @Test
    public void testIfCostSharingIsAvailable_BudgetPeriodsPresentWithCostSharingInOne() {
        createAndAddBudgetPeriod().setCostSharingAmount(ZERO_AMOUNT);        
        createAndAddBudgetPeriod().setCostSharingAmount(ZERO_AMOUNT);        
        createAndAddBudgetPeriod().setCostSharingAmount(TEST_AMOUNT_100);
        
        Assert.assertTrue(budgetDocument.isCostSharingAvailable());
    }
    
    @Test
    public void testCostSharingIsApplicable() {        
        Assert.assertTrue(budgetDocument.isCostSharingApplicable());
    }
    
    @Test
    public void testCostSharingIsNotApplicable() {
        // replace budgetDocument with one where cost sharing is not applicable
        budgetDocument = new BudgetDocument_CostShareAndUnrecoveredFandANotApplicable();
        
        createAndAddBudgetPeriod().setCostSharingAmount(ZERO_AMOUNT);        
        createAndAddBudgetPeriod().setCostSharingAmount(ZERO_AMOUNT);        
        createAndAddBudgetPeriod().setCostSharingAmount(TEST_AMOUNT_100);
        
        Assert.assertFalse(budgetDocument.isCostSharingApplicable());
    }
}

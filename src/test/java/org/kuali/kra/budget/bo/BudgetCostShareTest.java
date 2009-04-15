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

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.budget.BudgetDecimal;
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
        createAndAddBudgetPeriod().setCostSharingAmount(FY_2007_Q3_AMT);
        Assert.assertEquals(FY_2007_Q3_AMT, budgetDocument.getAvailableCostSharing());
        
        createAndAddBudgetPeriod().setCostSharingAmount(FY_2007_Q4_AMT);
        Assert.assertEquals(FY_2007_Q3_AMT.add(FY_2007_Q4_AMT), budgetDocument.getAvailableCostSharing());
        
        createAndAddBudgetPeriod().setCostSharingAmount(FY_2008_Q1_AMT);
        Assert.assertEquals(FY_2007_Q3_AMT.add(FY_2007_Q4_AMT).add(FY_2008_Q1_AMT), budgetDocument.getAvailableCostSharing());
        
        Assert.assertTrue(budgetDocument.isCostSharingAvailable());
    }
    
    @Test
    public void testGettingFiscalYearCostShareTotals() {
        createBudgetPeriodsForThreeFiscalYears();
        
        List<FiscalYearSummary> fiscalYearCostShareTotals = budgetDocument.getFiscalYearCostShareTotals();
        Assert.assertEquals(3, fiscalYearCostShareTotals.size());
        
        FiscalYearSummary fiscalYearSummary = fiscalYearCostShareTotals.get(0); 
        Assert.assertEquals(YEAR_2007, fiscalYearSummary.getFiscalYear());
        Assert.assertEquals(getDate(YEAR_2007, Calendar.JANUARY, DAY_1), fiscalYearSummary.getAssignedBudgetPeriod().getStartDate());
        Assert.assertEquals(FY_2007_Q3_AMT.add(FY_2007_Q4_AMT), fiscalYearSummary.getCostShare());
        
        fiscalYearSummary = fiscalYearCostShareTotals.get(1); 
        Assert.assertEquals(YEAR_2008, fiscalYearSummary.getFiscalYear());
        Assert.assertEquals(getDate(YEAR_2007, Calendar.JULY, DAY_1), fiscalYearSummary.getAssignedBudgetPeriod().getStartDate());
        Assert.assertEquals(FY_2008_Q1_AMT.add(FY_2008_Q2_AMT), fiscalYearSummary.getCostShare());
        
        fiscalYearSummary = fiscalYearCostShareTotals.get(2); 
        Assert.assertEquals(YEAR_2009, fiscalYearSummary.getFiscalYear());
        Assert.assertEquals(getDate(YEAR_2008, Calendar.OCTOBER, DAY_1), fiscalYearSummary.getAssignedBudgetPeriod().getStartDate());
        Assert.assertEquals(FY_2009_Q1_AMT.add(FY_2009_Q2_AMT), fiscalYearSummary.getCostShare());
    }
    
    @Test
    public void testIfCostSharingIsAvailable_BudgetPeriodPresentWithNonZeroCostSharing() {
        Assert.assertFalse(budgetDocument.isCostSharingAvailable());
        
        createAndAddBudgetPeriod().setCostSharingAmount(FY_2007_Q3_AMT);
        Assert.assertTrue(budgetDocument.isCostSharingAvailable());
    }
    
    @Test
    public void testCorrectBudgetPeriodAssignedForFiscalYear() {
        createAndAddBudgetPeriod(getDate(YEAR_2007, Calendar.NOVEMBER, DAY_1), getDate(YEAR_2007, Calendar.DECEMBER, DAY_30)).setCostSharingAmount(FY_2007_Q3_AMT);
        createAndAddBudgetPeriod(getDate(YEAR_2007, Calendar.JULY, DAY_1), getDate(YEAR_2007, Calendar.SEPTEMBER, DAY_30)).setCostSharingAmount(FY_2008_Q1_AMT);
        createAndAddBudgetPeriod(getDate(YEAR_2007, Calendar.APRIL, DAY_1), getDate(YEAR_2007, Calendar.JUNE, DAY_30)).setCostSharingAmount(FY_2007_Q4_AMT);
        
        Date startDate = getDate(YEAR_2007, Calendar.JANUARY, DAY_1);
        createAndAddBudgetPeriod(startDate, getDate(YEAR_2007, Calendar.MARCH, DAY_30)).setCostSharingAmount(FY_2007_Q3_AMT);        
                
        List<FiscalYearSummary> fiscalYearCostShareTotals = budgetDocument.getFiscalYearCostShareTotals();
        FiscalYearSummary fiscalYearSummary = fiscalYearCostShareTotals.get(0); 
        Assert.assertEquals(startDate, fiscalYearSummary.getAssignedBudgetPeriod().getStartDate());        
    }
    
    @Test
    public void testFindingCostSharingForFiscalYear() throws Exception {
        createBudgetPeriodsForThreeFiscalYears();
        Assert.assertEquals(FY_2007_Q3_AMT.add(FY_2007_Q4_AMT), budgetDocument.findCostSharingForFiscalYear(YEAR_2007));
        Assert.assertEquals(FY_2008_Q1_AMT.add(FY_2008_Q2_AMT), budgetDocument.findCostSharingForFiscalYear(YEAR_2008));
        Assert.assertEquals(FY_2009_Q1_AMT.add(FY_2009_Q2_AMT), budgetDocument.findCostSharingForFiscalYear(YEAR_2009));
        Assert.assertEquals(BudgetDecimal.ZERO, budgetDocument.findCostSharingForFiscalYear(YEAR_2000));
    }
    
    @Test
    public void testIfCostSharingIsAvailable_BudgetPeriodPresentWithZeroCostSharing() {
        createAndAddBudgetPeriod().setCostSharingAmount(BudgetDecimal.ZERO);
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
        createAndAddBudgetPeriod().setCostSharingAmount(BudgetDecimal.ZERO);        
        createAndAddBudgetPeriod().setCostSharingAmount(FY_2007_Q3_AMT);        
        createAndAddBudgetPeriod().setCostSharingAmount(BudgetDecimal.ZERO);
        
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
        
        createAndAddBudgetPeriod().setCostSharingAmount(FY_2007_Q3_AMT);        
        createAndAddBudgetPeriod().setCostSharingAmount(FY_2007_Q4_AMT);        
        createAndAddBudgetPeriod().setCostSharingAmount(FY_2008_Q1_AMT);
        
        Assert.assertFalse(budgetDocument.isCostSharingApplicable());
    }
}

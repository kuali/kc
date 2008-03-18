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
package org.kuali.kra.budget.document;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.document.BudgetDocument.FiscalYearCostShare;

public class BudgetDocumentTest {
    private static final int DAY_1 = 1;
    private static final int DAY_2 = 2;
    private static final int DAY_30 = 30;
    private static final int YEAR_2000 = 2000;
    private static final int YEAR_2007 = 2007;
    private static final int YEAR_2008 = 2008;
    private static final int YEAR_2009 = 2009;
    
    private static final BudgetDecimal COST_SHARE_AMOUNT = new BudgetDecimal(100.0);
    private static final BudgetDecimal ZERO_COST_SHARING = new BudgetDecimal(0.0);
    private BudgetDocument budgetDocument;
    private Calendar calendar;
    private Date fiscalYearStart;
    
    @Before
    public void setUp() {
        calendar = GregorianCalendar.getInstance();
        fiscalYearStart = getDate(YEAR_2000, Calendar.OCTOBER, DAY_1);
        budgetDocument = new BudgetDocument();
        budgetDocument.setFiscalYearStart(fiscalYearStart); 
    }
    
    @After
    public void tearDown() {
        budgetDocument = null;
        calendar = null;
    }
    
    @Test
    public void testCalculatingDatefromString() throws Exception {
        BudgetDocument bd = new BudgetDocument();
        Date fiscalYearDate = bd.createDateFromString("07/01/2000");
        
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(2000, Calendar.JULY, 1, 0, 0, 0);
        Assert.assertEquals(new Date(cal.getTimeInMillis()), fiscalYearDate);
    }
    
    @Test
    public void testCalculatingTotalCostSharing() {
        createAndAddBudgetPeriod(COST_SHARE_AMOUNT);
        Assert.assertEquals(COST_SHARE_AMOUNT, budgetDocument.getAvailableCostSharing());
        
        createAndAddBudgetPeriod(ZERO_COST_SHARING);
        Assert.assertEquals(COST_SHARE_AMOUNT, budgetDocument.getAvailableCostSharing());
        
        createAndAddBudgetPeriod(COST_SHARE_AMOUNT);
        Assert.assertEquals(COST_SHARE_AMOUNT.add(COST_SHARE_AMOUNT), budgetDocument.getAvailableCostSharing());
        
        Assert.assertTrue(budgetDocument.isCostSharingAvailable());
    }
    
    @Test
    public void testIfCostSharingIsAvailable_BudgetPeriodPresentButNoCostSharing() {
        Assert.assertFalse(budgetDocument.isCostSharingAvailable());
    }
    
    @Test
    public void testIfCostSharingIsAvailable_BudgetPeriodPresentWithNonZeroCostSharing() {
        createAndAddBudgetPeriod(COST_SHARE_AMOUNT);
        Assert.assertTrue(budgetDocument.isCostSharingAvailable());
    }
    
    @Test
    public void testIfCostSharingIsAvailable_BudgetPeriodPresentWithZeroCostSharing() {
        createAndAddBudgetPeriod(ZERO_COST_SHARING);
        Assert.assertFalse(budgetDocument.isCostSharingAvailable());
    }

    @Test
    public void testIfCostSharingIsAvailable_BudgetPeriodsPresentWithCostSharingInOne() {
        createAndAddBudgetPeriod(ZERO_COST_SHARING);        
        createAndAddBudgetPeriod(ZERO_COST_SHARING);        
        createAndAddBudgetPeriod(COST_SHARE_AMOUNT);
        
        Assert.assertTrue(budgetDocument.isCostSharingAvailable());
    }

    @Test
    public void testIfCostSharingIsAvailable_NoBudgetPeriods() {
        Assert.assertFalse(budgetDocument.isCostSharingAvailable());
    }
    
    @Test
    public void testCorrectBudgetPeriodAssignedForFiscalYear() {
        createAndAddBudgetPeriod(getDate(YEAR_2007, Calendar.NOVEMBER, DAY_1), getDate(YEAR_2007, Calendar.DECEMBER, DAY_30), COST_SHARE_AMOUNT);
        createAndAddBudgetPeriod(getDate(YEAR_2007, Calendar.JULY, DAY_1), getDate(YEAR_2007, Calendar.SEPTEMBER, DAY_30), COST_SHARE_AMOUNT);
        createAndAddBudgetPeriod(getDate(YEAR_2007, Calendar.APRIL, DAY_1), getDate(YEAR_2007, Calendar.JUNE, DAY_30), ZERO_COST_SHARING);
        
        Date startDate = getDate(YEAR_2007, Calendar.JANUARY, DAY_1);
        createAndAddBudgetPeriod(startDate, getDate(YEAR_2007, Calendar.MARCH, DAY_30), COST_SHARE_AMOUNT);        
                
        List<FiscalYearCostShare> fiscalYearCostShareTotals = budgetDocument.getFiscalYearCostShareTotals();
        FiscalYearCostShare fiscalYearCostShare = fiscalYearCostShareTotals.get(0); 
        Assert.assertEquals(startDate, fiscalYearCostShare.getAssignedBudgetPeriod().getStartDate());        
    }
    
    @Test
    public void testGettingFiscalYearCostShareTotals() {
        //FY 2007
        createAndAddBudgetPeriod(getDate(YEAR_2007, Calendar.JANUARY, DAY_1), getDate(YEAR_2007, Calendar.MARCH, DAY_30), COST_SHARE_AMOUNT);        
        createAndAddBudgetPeriod(getDate(YEAR_2007, Calendar.APRIL, DAY_1), getDate(YEAR_2007, Calendar.JUNE, DAY_30), ZERO_COST_SHARING);        
        createAndAddBudgetPeriod(getDate(YEAR_2007, Calendar.JULY, DAY_1), getDate(YEAR_2007, Calendar.SEPTEMBER, DAY_30), COST_SHARE_AMOUNT);
        
        //FY 2008
        createAndAddBudgetPeriod(getDate(YEAR_2007, Calendar.NOVEMBER, DAY_1), getDate(YEAR_2007, Calendar.DECEMBER, DAY_30), COST_SHARE_AMOUNT);
        
        //FY 2009
        createAndAddBudgetPeriod(getDate(YEAR_2008, Calendar.OCTOBER, DAY_2), getDate(YEAR_2008, Calendar.DECEMBER, DAY_30), COST_SHARE_AMOUNT);
        createAndAddBudgetPeriod(getDate(YEAR_2008, Calendar.OCTOBER, DAY_1), getDate(YEAR_2009, Calendar.OCTOBER, DAY_30), COST_SHARE_AMOUNT);
        
        List<FiscalYearCostShare> fiscalYearCostShareTotals = budgetDocument.getFiscalYearCostShareTotals();
        Assert.assertEquals(3, fiscalYearCostShareTotals.size());
        
        FiscalYearCostShare fiscalYearCostShare = fiscalYearCostShareTotals.get(0); 
        Assert.assertEquals(2007, fiscalYearCostShare.getFiscalYear());
        Assert.assertEquals(getDate(YEAR_2007, Calendar.JANUARY, DAY_1), fiscalYearCostShare.getAssignedBudgetPeriod().getStartDate());
        Assert.assertEquals(COST_SHARE_AMOUNT.add(COST_SHARE_AMOUNT), fiscalYearCostShare.getCostShare());
        
        fiscalYearCostShare = fiscalYearCostShareTotals.get(1); 
        Assert.assertEquals(2008, fiscalYearCostShare.getFiscalYear());
        Assert.assertEquals(getDate(YEAR_2007, Calendar.NOVEMBER, DAY_1), fiscalYearCostShare.getAssignedBudgetPeriod().getStartDate());
        Assert.assertEquals(COST_SHARE_AMOUNT, fiscalYearCostShare.getCostShare());
        
        fiscalYearCostShare = fiscalYearCostShareTotals.get(2); 
        Assert.assertEquals(2009, fiscalYearCostShare.getFiscalYear());
        Assert.assertEquals(getDate(YEAR_2008, Calendar.OCTOBER, DAY_1), fiscalYearCostShare.getAssignedBudgetPeriod().getStartDate());
        Assert.assertEquals(COST_SHARE_AMOUNT.add(COST_SHARE_AMOUNT), fiscalYearCostShare.getCostShare());
    }
    
    private BudgetPeriod createAndAddBudgetPeriod(BudgetDecimal costShareAmount) {
        BudgetPeriod budgetPeriod = new BudgetPeriod();
        budgetPeriod.setCostSharingAmount(costShareAmount);
        budgetDocument.add(budgetPeriod);
        
        return budgetPeriod;
    }
    
    private BudgetPeriod createAndAddBudgetPeriod(Date startDate, Date endDate, BudgetDecimal costShareAmount) {
        BudgetPeriod budgetPeriod = createAndAddBudgetPeriod(costShareAmount);
        budgetPeriod.setStartDate(startDate);
        budgetPeriod.setEndDate(endDate);
        return budgetPeriod;
    }
    
    private Date getDate(int year, int month, int date) {
        calendar.set(year, month, date);
        return new Date(calendar.getTimeInMillis());
    }
}
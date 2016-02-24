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
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BudgetPeriodTest {
    private static final int DAY_1 = 1;
    private static final int DAY_30 = 30;
    private static final int YEAR_2000 = 2000;
    private static final int YEAR_2007 = 2007;
    private static final int YEAR_2008 = 2008;
    private static final int YEAR_2009 = 2009;
    private static final int YEAR_2010 = 2010;
    
    private Calendar calendar;    
    private BudgetPeriod budgetPeriod;
    private Date fiscalYearStart;    
    
    @Test
    public void testFindingFiscalYear_PriorCalendarYearBeforeFiscalCutoff() throws Exception {              
        budgetPeriod.setStartDate(getDate(YEAR_2007, Calendar.JANUARY, DAY_1));
        budgetPeriod.setEndDate(getDate(YEAR_2007, Calendar.MARCH, DAY_30));        
        Assert.assertEquals(YEAR_2007, budgetPeriod.calculateFiscalYear(fiscalYearStart));
    }
    
    @Test
    public void testFindingFiscalYear_SameCalendarYearBeforeFiscalCutoff() throws Exception {              
        budgetPeriod.setStartDate(getDate(YEAR_2007, Calendar.MARCH, DAY_1));
        budgetPeriod.setEndDate(getDate(YEAR_2007, Calendar.JUNE, DAY_30));        
        Assert.assertEquals(YEAR_2007, budgetPeriod.calculateFiscalYear(fiscalYearStart));
    }
    
    @Test
    public void testFindingFiscalYear_AtFiscalYearBeginning() throws Exception {              
        budgetPeriod.setStartDate(getDate(YEAR_2008, Calendar.JULY, DAY_1));
        budgetPeriod.setEndDate(getDate(YEAR_2008, Calendar.DECEMBER, DAY_30));        
        Assert.assertEquals(YEAR_2009, budgetPeriod.calculateFiscalYear(fiscalYearStart));
    }
    
    @Test
    public void testFindingFiscalYear_NextCalendarYearBeforeFiscalCutoff() throws Exception {              
        budgetPeriod.setStartDate(getDate(YEAR_2009, Calendar.JUNE, DAY_1));
        budgetPeriod.setEndDate(getDate(YEAR_2009, Calendar.JUNE, DAY_30));        
        Assert.assertEquals(YEAR_2009, budgetPeriod.calculateFiscalYear(fiscalYearStart));
    }
    
    @Test
    public void testFindingFiscalYear_NextCalendarYearAfterFiscalCutoff() throws Exception {              
        budgetPeriod.setStartDate(getDate(YEAR_2009, Calendar.JULY, DAY_1));
        budgetPeriod.setEndDate(getDate(YEAR_2009, Calendar.JULY, DAY_30));        
        Assert.assertEquals(YEAR_2010, budgetPeriod.calculateFiscalYear(fiscalYearStart));
    }
    
    @Test
    public void testFindingFiscalYear_PeriodSpansFiscalYearBoundary() throws Exception {              
        budgetPeriod.setStartDate(getDate(YEAR_2008, Calendar.JUNE, DAY_1));
        budgetPeriod.setEndDate(getDate(YEAR_2008, Calendar.JULY, DAY_30));        
        Assert.assertEquals(YEAR_2008, budgetPeriod.calculateFiscalYear(fiscalYearStart));
    }
    
    @Test
    public void testFindingFiscalYear_NullFiscalYearStartDate() throws Exception {              
        Assert.assertEquals(0, budgetPeriod.calculateFiscalYear(null));
    }
    
    @Test
    public void testFindingFiscalYear_NullStartDate() throws Exception {
        budgetPeriod.setStartDate(null);
        Assert.assertEquals(0, budgetPeriod.calculateFiscalYear(fiscalYearStart));
    }
    
    @Test
    public void testIfBudgetPeriodIsReadOnly() throws Exception {
        // jira - 1177 period 1 is not default read only.  Only 'delete' button is grayed out
        budgetPeriod.setBudgetPeriod(1);
        budgetPeriod.getBudgetLineItems().clear();
        Assert.assertFalse(budgetPeriod.isReadOnly());
        
        budgetPeriod.setBudgetPeriod(1);
        budgetPeriod.getBudgetLineItems().add(budgetPeriod.getNewBudgetLineItem());
        Assert.assertTrue(budgetPeriod.isReadOnly());
        
        budgetPeriod.setBudgetPeriod(2);
        budgetPeriod.getBudgetLineItems().clear();
        Assert.assertFalse(budgetPeriod.isReadOnly());
        
        budgetPeriod.setBudgetPeriod(2);
        budgetPeriod.getBudgetLineItems().add(budgetPeriod.getNewBudgetLineItem());
        Assert.assertTrue(budgetPeriod.isReadOnly());
    }
    
    @Before
    public void setUp() {
        calendar = GregorianCalendar.getInstance();
        budgetPeriod = new BudgetPeriod();                
        fiscalYearStart = getDate(YEAR_2000, Calendar.JULY, DAY_1);        
    }
    
    @After
    public void tearDown() {
        fiscalYearStart = null;
        budgetPeriod = null;
        calendar = null;
    }
    
    private Date getDate(int year, int month, int date) {
        calendar.set(year, month, date);
        return new Date(calendar.getTimeInMillis());
    }
}

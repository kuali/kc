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
import org.junit.Before;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class BudgetDistributionAndIncomeTest {
    protected static final ScaleTwoDecimal FY_2007_Q3_AMT = new ScaleTwoDecimal(100.0);
    protected static final ScaleTwoDecimal FY_2007_Q4_AMT = ScaleTwoDecimal.ZERO;
    protected static final ScaleTwoDecimal FY_2008_Q1_AMT = new ScaleTwoDecimal(200.0);
    protected static final ScaleTwoDecimal FY_2008_Q2_AMT = new ScaleTwoDecimal(300.0);
    protected static final ScaleTwoDecimal FY_2009_Q1_AMT = new ScaleTwoDecimal(400.0);
    protected static final ScaleTwoDecimal FY_2009_Q2_AMT = new ScaleTwoDecimal(500.0);

    protected static final int DAY_1 = 1;
    protected static final int DAY_2 = 2;
    protected static final int DAY_30 = 30;
    protected static final int YEAR_2000 = 2000;
    protected static final int YEAR_2007 = 2007;
    protected static final int YEAR_2008 = 2008;
    protected static final int YEAR_2009 = 2009;
    
    
    protected ScaleTwoDecimal[] costShareAmounts = { FY_2007_Q3_AMT, FY_2007_Q4_AMT, FY_2008_Q1_AMT, FY_2008_Q2_AMT, FY_2009_Q1_AMT, FY_2009_Q2_AMT };
    protected ScaleTwoDecimal[] unrecoveredFandAAmounts = { FY_2007_Q3_AMT, FY_2007_Q4_AMT, FY_2008_Q1_AMT, FY_2008_Q2_AMT, FY_2009_Q1_AMT, FY_2009_Q2_AMT };
    
    protected Budget budgetDocument;
    protected Calendar calendar;

    private Date fiscalYearStartArtifact;
    
    @Before
    public void setUp() throws Exception {
        calendar = GregorianCalendar.getInstance();
        fiscalYearStartArtifact = getDate(YEAR_2000, Calendar.JULY, DAY_1);
        budgetDocument = new BudgetDocument_CostShareAndUnrecoveredFandAApplicable();
    }
    
    @After
    public void tearDown() throws Exception {
        budgetDocument = null;
        calendar = null;
    }
    
    protected BudgetPeriod createAndAddBudgetPeriod(Date startDate, Date endDate) {
        BudgetPeriod budgetPeriod = new BudgetPeriod();
        budgetPeriod.setStartDate(startDate);
        budgetPeriod.setEndDate(endDate);
        budgetDocument.add(budgetPeriod);
        return budgetPeriod;
    }
    
    protected Date getDate(int year, int month, int date) {
        calendar.set(year, month, date);
        return new Date(calendar.getTimeInMillis());
    }

    protected BudgetPeriod createAndAddBudgetPeriod() {
        BudgetPeriod budgetPeriod = new BudgetPeriod();
        budgetDocument.add(budgetPeriod);
        return budgetPeriod;
    }

    protected void createBudgetPeriodsForThreeFiscalYears() {
        //FY 2007
        createAndAddBudgetPeriod(getDate(YEAR_2007, Calendar.JANUARY, DAY_1), getDate(YEAR_2007, Calendar.MARCH, DAY_30));        
        createAndAddBudgetPeriod(getDate(YEAR_2007, Calendar.APRIL, DAY_1), getDate(YEAR_2007, Calendar.JUNE, DAY_30));
        
        //FY 2008
        createAndAddBudgetPeriod(getDate(YEAR_2007, Calendar.JULY, DAY_1), getDate(YEAR_2007, Calendar.SEPTEMBER, DAY_30));
        createAndAddBudgetPeriod(getDate(YEAR_2007, Calendar.OCTOBER, DAY_1), getDate(YEAR_2007, Calendar.DECEMBER, DAY_30));
        
        //FY 2009
        createAndAddBudgetPeriod(getDate(YEAR_2008, Calendar.OCTOBER, DAY_1), getDate(YEAR_2008, Calendar.DECEMBER, DAY_30));
        createAndAddBudgetPeriod(getDate(YEAR_2008, Calendar.OCTOBER, DAY_2), getDate(YEAR_2009, Calendar.JUNE, DAY_30));
        
        
        int i = 0;
        for(BudgetPeriod bp: budgetDocument.getBudgetPeriods()) {
            bp.setCostSharingAmount(costShareAmounts[i]);
            bp.setUnderrecoveryAmount(unrecoveredFandAAmounts[i]);
            i++;
        }        
    }
    
    public class Budget_TestRoot extends Budget {
        //removes dependence on BudgetDocument
        private Integer nextVal = 1;
        public Integer getNextValue(String key) {
            return nextVal++;
        }
    }
    
    public class BudgetDocument_CostShareAndUnrecoveredFandAApplicable extends Budget_TestRoot {
        private static final long serialVersionUID = 1L;
                
        @Override
        protected Boolean loadCostSharingApplicability() {
           return Boolean.TRUE;
        }

        @Override
        public Date loadFiscalYearStart() {
            return fiscalYearStartArtifact;
        }

        @Override
        protected Boolean loadUnrecoveredFandAApplicability() {
            return Boolean.TRUE;
        }
        
        protected void setCostShareApplicability(Boolean value) {
            
        }
    }
    
    public class BudgetDocument_CostShareAndUnrecoveredFandANotApplicable extends Budget_TestRoot {
        private static final long serialVersionUID = 1L;
                
        @Override
        protected Boolean loadCostSharingApplicability() {
           return Boolean.FALSE;
        }

        @Override
        public Date loadFiscalYearStart() {
            return fiscalYearStartArtifact;
        }

        @Override
        protected Boolean loadUnrecoveredFandAApplicability() {
            return Boolean.FALSE;
        }
    }
}

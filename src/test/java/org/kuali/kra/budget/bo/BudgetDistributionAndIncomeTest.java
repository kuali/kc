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
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.distributionincome.BudgetDistributionAndIncomeService;
import org.kuali.kra.budget.distributionincome.BudgetDistributionAndIncomeServiceImpl;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.parameters.BudgetPeriod;

public abstract class BudgetDistributionAndIncomeTest {
    protected static final BudgetDecimal FY_2007_Q3_AMT = new BudgetDecimal(100.0);
    protected static final BudgetDecimal FY_2007_Q4_AMT = BudgetDecimal.ZERO;
    protected static final BudgetDecimal FY_2008_Q1_AMT = new BudgetDecimal(200.0);
    protected static final BudgetDecimal FY_2008_Q2_AMT = new BudgetDecimal(300.0);
    protected static final BudgetDecimal FY_2009_Q1_AMT = new BudgetDecimal(400.0);
    protected static final BudgetDecimal FY_2009_Q2_AMT = new BudgetDecimal(500.0);

    protected static final int DAY_1 = 1;
    protected static final int DAY_2 = 2;
    protected static final int DAY_30 = 30;
    protected static final int YEAR_2000 = 2000;
    protected static final int YEAR_2007 = 2007;
    protected static final int YEAR_2008 = 2008;
    protected static final int YEAR_2009 = 2009;
    
    
    protected BudgetDecimal[] costShareAmounts = { FY_2007_Q3_AMT, FY_2007_Q4_AMT, FY_2008_Q1_AMT, FY_2008_Q2_AMT, FY_2009_Q1_AMT, FY_2009_Q2_AMT };
    protected BudgetDecimal[] unrecoveredFandAAmounts = { FY_2007_Q3_AMT, FY_2007_Q4_AMT, FY_2008_Q1_AMT, FY_2008_Q2_AMT, FY_2009_Q1_AMT, FY_2009_Q2_AMT };
    
    protected Budget budgetDocument;
    protected Calendar calendar;
    protected BudgetDistributionAndIncomeService budgetDistributionAndIncomeService;
    
    private Date fiscalYearStartArtifact;
    
    @Before
    public void setUp() throws Exception {
        calendar = GregorianCalendar.getInstance();
        fiscalYearStartArtifact = getDate(YEAR_2000, Calendar.JULY, DAY_1);
        budgetDocument = new BudgetDocument_CostShareAndUnrecoveredFandAApplicable();
        budgetDistributionAndIncomeService = new BudgetDistributionAndIncomeServiceImpl();
    }
    
    @After
    public void tearDown() throws Exception {
        budgetDocument = null;
        calendar = null;
        budgetDistributionAndIncomeService = null;
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
    
    public class BudgetDocument_CostShareAndUnrecoveredFandAApplicable extends Budget {
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
    
    public class BudgetDocument_CostShareAndUnrecoveredFandANotApplicable extends Budget {
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
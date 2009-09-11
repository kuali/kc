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
package org.kuali.kra.budget.service;

import java.sql.Date;
import java.util.Calendar;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.RateDecimal;
import org.kuali.kra.budget.bo.BudgetDistributionAndIncomeTest;
import org.kuali.kra.budget.distributionincome.BudgetCostShare;
import org.kuali.kra.budget.distributionincome.BudgetDistributionAndIncomeService;
import org.kuali.kra.budget.distributionincome.BudgetDistributionAndIncomeServiceImpl;
import org.kuali.kra.budget.distributionincome.BudgetUnrecoveredFandA;
import org.kuali.kra.budget.parameters.BudgetPeriod;


public class BudgetDistributionAndIncomeServiceTest extends BudgetDistributionAndIncomeTest {
    private static final String finalStatusParameterValue = "1";
    private BudgetDistributionAndIncomeService bdiService;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        bdiService = new BudgetDistributionAndIncomeServiceImpl();
        addBudgetPeriods();
    }

    @After
    public void tearDown() throws Exception {
        bdiService = null;
        super.tearDown();
    }
    
    @Test
    public void testInitializingCollectionDefaults_CostSharingApplicable() {
        Assert.assertEquals(0, budgetDocument.getBudgetCostShares().size());
        bdiService.initializeCollectionDefaults(budgetDocument);
        
        final int FISCAL_YEARS_IN_BUDGET = 2;
        Assert.assertEquals(FISCAL_YEARS_IN_BUDGET, budgetDocument.getBudgetCostShares().size());
        Assert.assertEquals(budgetDocument.findCostSharingForFiscalYear(2008), budgetDocument.getBudgetCostShares().get(0).getShareAmount());
        Assert.assertEquals(budgetDocument.findCostSharingForFiscalYear(2009), budgetDocument.getBudgetCostShares().get(1).getShareAmount());
    }
    
    @Test
    public void testInitializingCollectionDefaults_ExistingCostShareBlocksDefaults() {
        Assert.assertEquals(0, budgetDocument.getBudgetCostShares().size());
        
        BudgetCostShare budgetCostShare = new BudgetCostShare(2008, new BudgetDecimal(1000.00), BudgetDecimal.ZERO, "12345A");
        budgetDocument.add(budgetCostShare);        
        
        bdiService.initializeCollectionDefaults(budgetDocument);
        
        Assert.assertEquals(1, budgetDocument.getBudgetCostShares().size());
        Assert.assertEquals(budgetCostShare.getShareAmount(), budgetDocument.getBudgetCostShares().get(0).getShareAmount());
    }
    
    @Test
    public void testInitializingCollectionDefaults_ExistingUnrecoveredFandABlocksDefaults() {
        Assert.assertEquals(0, budgetDocument.getBudgetUnrecoveredFandAs().size());
        
        BudgetUnrecoveredFandA budgetUnrecoveredFandA = new BudgetUnrecoveredFandA(2008, new BudgetDecimal(1000.00), RateDecimal.ZERO_RATE, "Y", "12345A");
        budgetDocument.add(budgetUnrecoveredFandA);        
        
        bdiService.initializeCollectionDefaults(budgetDocument);
        
        Assert.assertEquals(1, budgetDocument.getBudgetUnrecoveredFandAs().size());
        Assert.assertEquals(budgetUnrecoveredFandA.getAmount(), budgetDocument.getBudgetUnrecoveredFandAs().get(0).getAmount());
    }
    
    @Test
    public void testInitializingCollectionDefaults_FinalAndCompleteBudget() {
        budgetDocument.setFinalVersionFlag(true);
        budgetDocument.setBudgetStatus(finalStatusParameterValue);
        Assert.assertEquals(0, budgetDocument.getBudgetCostShares().size());
        
        bdiService.initializeCollectionDefaults(budgetDocument);
        Assert.assertEquals(0, budgetDocument.getBudgetCostShares().size());
        Assert.assertEquals(0, budgetDocument.getBudgetUnrecoveredFandAs().size());
    }
    
    @Test
    public void testInitializingCollectionDefaults_UnrecoveredFandAApplicable() {
        Assert.assertEquals(0, budgetDocument.getBudgetUnrecoveredFandAs().size());
        bdiService.initializeCollectionDefaults(budgetDocument);
        
        final int FISCAL_YEARS_IN_BUDGET = 2;
        final int DEFAULT_UNRECOVERED_FANDA_ELEMENTS = FISCAL_YEARS_IN_BUDGET * 2;
        Assert.assertEquals(DEFAULT_UNRECOVERED_FANDA_ELEMENTS, budgetDocument.getBudgetUnrecoveredFandAs().size());
        Assert.assertEquals(BudgetDecimal.ZERO, budgetDocument.getBudgetUnrecoveredFandAs().get(0).getAmount());
        Assert.assertEquals(BudgetDecimal.ZERO, budgetDocument.getBudgetUnrecoveredFandAs().get(1).getAmount());
        Assert.assertEquals(BudgetDecimal.ZERO, budgetDocument.getBudgetUnrecoveredFandAs().get(2).getAmount());
        Assert.assertEquals(BudgetDecimal.ZERO, budgetDocument.getBudgetUnrecoveredFandAs().get(3).getAmount());
    }
        
    @Test
    public void testInitializingCollectionDefaults_CostSharingNotApplicable() {
        budgetDocument = new BudgetDocument_CostShareAndUnrecoveredFandANotApplicable();
        addBudgetPeriods();        
        bdiService.initializeCollectionDefaults(budgetDocument);                
        Assert.assertEquals(0, budgetDocument.getBudgetCostShares().size());        
    }
    
    @Test
    public void testInitializingCollectionDefaults_CostSharingNotAvailable() {
        removeBudgetCostSharingAmounts();
        bdiService.initializeCollectionDefaults(budgetDocument);        
        Assert.assertEquals(0, budgetDocument.getBudgetCostShares().size());        
    }

    private void addBudgetPeriods() {
        budgetDocument.add(createBudgetPeriod(getDate(2008, Calendar.JANUARY, 1), getDate(2008, Calendar.MARCH, 31), new BudgetDecimal(2000.00), new BudgetDecimal(1000.00)));
        budgetDocument.add(createBudgetPeriod(getDate(2008, Calendar.APRIL, 1), getDate(2008, Calendar.JUNE, 30), new BudgetDecimal(4000.00), new BudgetDecimal(3000.00)));
        budgetDocument.add(createBudgetPeriod(getDate(2008, Calendar.JULY, 1), getDate(2008, Calendar.SEPTEMBER, 30), new BudgetDecimal(6000.00), new BudgetDecimal(5000.00)));
        budgetDocument.add(createBudgetPeriod(getDate(2008, Calendar.OCTOBER, 1), getDate(2008, Calendar.DECEMBER, 31), new BudgetDecimal(8000.00), new BudgetDecimal(7000.00)));
    }
    
    private BudgetPeriod createBudgetPeriod(Date startDate, Date endDate, BudgetDecimal costSharingAmount, BudgetDecimal underrecoveryAmount) {
        BudgetPeriod bp = new BudgetPeriod();
        bp.setStartDate(startDate);
        bp.setEndDate(endDate);
        bp.setCostSharingAmount(costSharingAmount);
        bp.setUnderrecoveryAmount(underrecoveryAmount);
        return bp;
    }

    private void removeBudgetCostSharingAmounts() {
        for(BudgetPeriod bp: budgetDocument.getBudgetPeriods()) {
            bp.setCostSharingAmount(BudgetDecimal.ZERO);
        }
    }
}

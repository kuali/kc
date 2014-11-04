/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.coeus.common.budget.framework.core;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.distribution.BudgetCostShare;
import org.kuali.coeus.common.budget.framework.income.BudgetProjectIncome;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.framework.costshare.CostShareService;
import org.kuali.kra.costshare.CostShareServiceTest;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.coreservice.api.parameter.Parameter;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.DocumentService;

import static org.junit.Assert.*;
public class BudgetDocumentRuleTest extends KcIntegrationTestBase {

    ProposalDevelopmentBudgetExt budget;
    BudgetDocumentRule budgetDocRule;
    DocumentService docService;
    
    @Before
    public void setUp() throws Exception {
        docService = KcServiceLocator.getService(DocumentService.class);
        budget = new ProposalDevelopmentBudgetExt();
        budgetDocRule = new BudgetDocumentRule();
    }

    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void testBudgetProjectIncomeBusinessRule() throws Exception {
        
        ParameterService ps = KcServiceLocator.getService(ParameterService.class);
        //ps.clearCache();
        //ps.setParameterForTesting(CostShareServiceTest.class, "CostShareProjectPeriodNameLabel", "Fiscal Year");
        Parameter parameter = ps.getParameter(CostShareServiceTest.class, "CostShareProjectPeriodNameLabel");
        Parameter.Builder parameterForUpdate = Parameter.Builder.create(parameter);
        parameterForUpdate.setValue("Fiscal Year");
        ps.updateParameter(parameterForUpdate.build());
        
        CostShareService costShareService = KcServiceLocator.getService(CostShareService.class);
        costShareService.getCostShareLabel();
        budgetDocRule.setCostShareService(costShareService);
        BudgetSaveEvent saveEvent = new BudgetSaveEvent(budget);
        assertTrue(budgetDocRule.processBudgetProjectIncomeBusinessRule(saveEvent));
        for (int i = 0; i < 5; i++) {
            BudgetCostShare tempCostShare = new BudgetCostShare();
            Integer projectPeriod = 2010+(i+1);
            tempCostShare.setProjectPeriod(projectPeriod);
            tempCostShare.setShareAmount(new ScaleTwoDecimal(10000.00));
            budget.getBudgetCostShares().add(tempCostShare);
        }
        assertEquals(5, budget.getBudgetCostShares().size());
        
        BudgetPeriod budgetPeriod = new BudgetPeriod();
        budget.add(budgetPeriod);
        
        assertTrue(budgetDocRule.processBudgetProjectIncomeBusinessRule(saveEvent));
        
        budget.getBudgetCostShares().get(0).setProjectPeriod(null);
        assertFalse(budgetDocRule.processBudgetProjectIncomeBusinessRule(saveEvent));
        
        budget.getBudgetCostShares().get(0).setProjectPeriod(1984);
        assertTrue(budgetDocRule.processBudgetProjectIncomeBusinessRule(saveEvent));
        
        budget.getBudgetCostShares().get(1).setSourceAccount("abcd1234");
        assertTrue(budgetDocRule.processBudgetProjectIncomeBusinessRule(saveEvent));
        
        budget.getBudgetCostShares().get(0).setProjectPeriod(2010);
        assertTrue(budgetDocRule.processBudgetProjectIncomeBusinessRule(saveEvent));
        
        budget.getBudgetCostShares().get(1).setProjectPeriod(2010);
        assertTrue(budgetDocRule.processBudgetProjectIncomeBusinessRule(saveEvent));
        
        budget.getBudgetCostShares().get(1).setSourceAccount(null);
        assertFalse(budgetDocRule.processBudgetProjectIncomeBusinessRule(saveEvent));
        
        
        budget.getBudgetCostShares().clear();
    }
    
    @Test
    public void testProjectIncomeValidation() {
    	BudgetSaveEvent saveEvent = new BudgetSaveEvent(budget);
        assertTrue(budgetDocRule.processBudgetProjectIncomeBusinessRule(saveEvent));
        BudgetProjectIncome projectIncome = new BudgetProjectIncome();
        projectIncome.setProjectIncome(new ScaleTwoDecimal(5.00));
        budget.getBudgetProjectIncomes().add(projectIncome);
        assertTrue(budgetDocRule.processBudgetProjectIncomeBusinessRule(saveEvent));
        budget.getBudgetProjectIncome(0).setProjectIncome(new ScaleTwoDecimal(0.00));
        assertFalse(budgetDocRule.processBudgetProjectIncomeBusinessRule(saveEvent));
        budget.getBudgetProjectIncome(0).setProjectIncome(null);
        assertFalse(budgetDocRule.processBudgetProjectIncomeBusinessRule(saveEvent));
    }

}

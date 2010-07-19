/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.budget.document;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.distributionincome.BudgetCostShare;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kns.service.DocumentService;

public class BudgetDocumentRuleTest extends KcUnitTestBase {

    BudgetDocument<DevelopmentProposal> budgetDoc;
    BudgetDocumentRule budgetDocRule;
    DocumentService docService;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        docService = KraServiceLocator.getService(DocumentService.class);
        budgetDoc = (BudgetDocument)docService.getNewDocument(BudgetDocument.class);
        budgetDocRule = new BudgetDocumentRule();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    @Test
    public void testBudgetProjectIncomeBusinessRule() throws Exception {
        assertTrue(budgetDocRule.processBudgetProjectIncomeBusinessRule(budgetDoc));
        for (int i = 0; i < 5; i++) {
            BudgetCostShare tempCostShare = new BudgetCostShare();
            tempCostShare.setFiscalYear(2010+i);
            tempCostShare.setShareAmount(new BudgetDecimal(10000.00));
            budgetDoc.getBudget().getBudgetCostShares().add(tempCostShare);
        }
        assertTrue(budgetDocRule.processBudgetProjectIncomeBusinessRule(budgetDoc));
        
        budgetDoc.getBudget().getBudgetCostShares().get(0).setFiscalYear(null);
        assertTrue(budgetDocRule.processBudgetProjectIncomeBusinessRule(budgetDoc));
        
        budgetDoc.getBudget().getBudgetCostShares().get(1).setFiscalYear(null);
        assertFalse(budgetDocRule.processBudgetProjectIncomeBusinessRule(budgetDoc));
        
        budgetDoc.getBudget().getBudgetCostShares().get(1).setSourceAccount("abcd1234");
        assertTrue(budgetDocRule.processBudgetProjectIncomeBusinessRule(budgetDoc));
        
        budgetDoc.getBudget().getBudgetCostShares().get(0).setFiscalYear(2010);
        assertTrue(budgetDocRule.processBudgetProjectIncomeBusinessRule(budgetDoc));
        
        budgetDoc.getBudget().getBudgetCostShares().get(1).setFiscalYear(2010);
        assertTrue(budgetDocRule.processBudgetProjectIncomeBusinessRule(budgetDoc));
        
        budgetDoc.getBudget().getBudgetCostShares().get(1).setSourceAccount(null);
        assertFalse(budgetDocRule.processBudgetProjectIncomeBusinessRule(budgetDoc));
        
        
        budgetDoc.getBudget().getBudgetCostShares().clear();
    }

}

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
package org.kuali.kra.budget.rules;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.web.struts.form.KualiForm;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetCostShare;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.BudgetDocumentContainer;
import org.kuali.kra.budget.rule.AddBudgetCostShareRule;
import org.kuali.kra.budget.rule.event.AddBudgetCostShareEvent;

public class AddBudgetCostShareRuleTest extends TestCase {
    private static final BudgetDecimal SHARE_AMOUNT = new BudgetDecimal(100.00);
    private static final BudgetDecimal SHARE_PCT = new BudgetDecimal(5.00);
    private static final int BUDGET_FISCAL_YEAR = 2008;
    private static final String SOURCE_ACCOUNT = "12345";
    
    private AddBudgetCostShareRule budgetCostShareRule;
    private BudgetDocument document;
    private BudgetCostShare firstCostShare;
    
    @Test
    public void testAddingCostShare_DuplicateFiscalYear() throws Exception {
        BudgetCostShare addCandidate = new BudgetCostShare(BUDGET_FISCAL_YEAR, null, null, null);
        AddBudgetCostShareEvent budgetCostShareEvent = new AddBudgetCostShareEvent(null, null, null, addCandidate);
        Assert.assertTrue(budgetCostShareRule.processAddBudgetCostShareBusinessRules(budgetCostShareEvent));
    }
    
    @Test
    public void testAddingCostShare_DuplicateSharePercentage() throws Exception {
        BudgetCostShare addCandidate = new BudgetCostShare(null, SHARE_PCT, null, null);
        AddBudgetCostShareEvent budgetCostShareEvent = new AddBudgetCostShareEvent(null, null, null, addCandidate);
        Assert.assertTrue(budgetCostShareRule.processAddBudgetCostShareBusinessRules(budgetCostShareEvent));
    }
    
    @Test
    public void testAddingCostShare_DuplicateShareAmount() throws Exception {
        BudgetCostShare addCandidate = new BudgetCostShare(null, SHARE_PCT, null, null);
        AddBudgetCostShareEvent budgetCostShareEvent = new AddBudgetCostShareEvent(null, null, null, addCandidate);
        Assert.assertTrue(budgetCostShareRule.processAddBudgetCostShareBusinessRules(budgetCostShareEvent));
    }
    
    @Test
    public void testAddingCostShare_DuplicateSourceAccount() throws Exception {
        BudgetCostShare addCandidate = new BudgetCostShare(null, null, null, SOURCE_ACCOUNT);
        AddBudgetCostShareEvent budgetCostShareEvent = new AddBudgetCostShareEvent(null, null, null, addCandidate);
        Assert.assertTrue(budgetCostShareRule.processAddBudgetCostShareBusinessRules(budgetCostShareEvent));
    }
    
    @Test
    public void testAddingCostShare_DuplicateCostShare() throws Exception {
        BudgetCostShare addCandidate = new BudgetCostShare(BUDGET_FISCAL_YEAR, null, SHARE_AMOUNT, SOURCE_ACCOUNT);
        AddBudgetCostShareEvent budgetCostShareEvent = new AddBudgetCostShareEvent(null, null, null, addCandidate);
        Assert.assertFalse(budgetCostShareRule.processAddBudgetCostShareBusinessRules(budgetCostShareEvent));
        Assert.assertEquals(1, GlobalVariables.getErrorMap().size());
    }
    
    @Before
    public void setUp() {
        GlobalVariables.setErrorMap(new ErrorMap());
        document = new MockBudgetDocument();        
        MockBudgetForm budgetForm = new MockBudgetForm(document);       
        GlobalVariables.setKualiForm(budgetForm);
        
        budgetCostShareRule = new BudgetCostShareRuleImpl();
        firstCostShare = new BudgetCostShare(BUDGET_FISCAL_YEAR, SHARE_PCT, SHARE_AMOUNT, SOURCE_ACCOUNT);
        document.add(firstCostShare);
    }
    
    @After
    public void tearDown() {
        GlobalVariables.clear();
        budgetCostShareRule = null;
        document = null;
        GlobalVariables.setKualiForm(null);
    }
    
    @SuppressWarnings("serial")
    private class MockBudgetForm extends KualiForm implements BudgetDocumentContainer {
        private BudgetDocument document;
        
        public MockBudgetForm(BudgetDocument document) {
            super();
            this.document = document;
        }
        
        public BudgetDocument getBudgetDocument() { return document; }
        public void setDocument(BudgetDocument document) { this.document = document; }
    }
    
    @SuppressWarnings("serial")
    private class MockBudgetDocument extends BudgetDocument {

        @Override
        public void refreshReferenceObject(String referenceObjectName) {
            // do nothing
        }
     
        
    }
}

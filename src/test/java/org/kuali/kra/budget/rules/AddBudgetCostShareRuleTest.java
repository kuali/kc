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
package org.kuali.kra.budget.rules;

import org.junit.After;
import org.junit.Before;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.distributionincome.AddBudgetCostShareEvent;
import org.kuali.kra.budget.distributionincome.AddBudgetCostShareRule;
import org.kuali.kra.budget.distributionincome.BudgetCostShare;
import org.kuali.kra.budget.distributionincome.BudgetCostShareRuleImpl;

public class AddBudgetCostShareRuleTest extends AddBudgetDistributionAndIncomeTest {
    private static final int BUDGET_FISCAL_YEAR = 2008;
    private static final BudgetDecimal SHARE_AMOUNT = new BudgetDecimal(100.00);
    private static final BudgetDecimal SHARE_PCT = new BudgetDecimal(5.00);
    private static final String SOURCE_ACCOUNT = "12345";
    
    private AddBudgetCostShareRule budgetCostShareRule;
    
    @Before
    public void setUp() {
        super.setUp();
        budgetCostShareRule = new BudgetCostShareRuleImpl();
    }
    
    @After
    public void tearDown() {
        budgetCostShareRule = null;
        super.tearDown();
    }
    
    @Override
    protected void addArtifactToDocument(Object artifact) {
        document.getBudget().add((BudgetCostShare) artifact);
    }
    
    @Override
    protected Object generateDifferentArtifact() {
        BudgetCostShare ref = (BudgetCostShare) generateReferenceArtifact();
        return new BudgetCostShare(ref.getFiscalYear() + 1 , ref.getSharePercentage().add(SHARE_PCT), 
                                    ref.getShareAmount().add(SHARE_AMOUNT), ref.getSourceAccount() + "-v2");
    }

    @Override
    protected Object generateEmptyArtifact() {
        return new BudgetCostShare();
    }
    
    @Override
    protected Object generateReferenceArtifact() {
        return new BudgetCostShare(BUDGET_FISCAL_YEAR, SHARE_PCT, SHARE_AMOUNT, SOURCE_ACCOUNT);
    }
    
    @Override
    protected Object generateRuleEvent(Object addCandidate) {
        return new AddBudgetCostShareEvent(null, null, null, (BudgetCostShare) addCandidate);
    }

    @Override
    protected boolean processRules(Object ruleEvent) {
        return budgetCostShareRule.processAddBudgetCostShareBusinessRules((AddBudgetCostShareEvent) ruleEvent);
    }   
}
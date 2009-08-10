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
import org.kuali.kra.budget.RateDecimal;
import org.kuali.kra.budget.distributionincome.AddBudgetUnrecoveredFandAEvent;
import org.kuali.kra.budget.distributionincome.AddBudgetUnrecoveredFandARule;
import org.kuali.kra.budget.distributionincome.BudgetUnrecoveredFandA;
import org.kuali.kra.budget.distributionincome.BudgetUnrecoveredFandARuleImpl;

public class AddBudgetUnrecoveredFandARuleTest extends AddBudgetDistributionAndIncomeTest {
    private static final int BUDGET_FISCAL_YEAR = 2008;
    private static final BudgetDecimal AMOUNT = new BudgetDecimal(100.00);
    private static final RateDecimal APPLICABLE_RATE = new RateDecimal(5.375);
    private static final String ON_CAMPUS_FLAG = "Y";
    private static final String SOURCE_ACCOUNT = "12345";
    
    private AddBudgetUnrecoveredFandARule budgetUnrecoveredFandARule;
    
    @Before
    public void setUp() {
        super.setUp();
        budgetUnrecoveredFandARule = new BudgetUnrecoveredFandARuleImpl();
    }
    
    @After
    public void tearDown() {
        budgetUnrecoveredFandARule = null;
        super.tearDown();
    }
    
    @Override
    protected void addArtifactToDocument(Object artifact) {
        document.getBudget().add((BudgetUnrecoveredFandA) artifact);
    }

    @Override
    protected Object generateEmptyArtifact() {
        return new BudgetUnrecoveredFandA();
    }
    
    @Override
    protected Object generateRuleEvent(Object addCandidate) {
        return new AddBudgetUnrecoveredFandAEvent(null, null, null, (BudgetUnrecoveredFandA) addCandidate);
    }

    @Override
    protected boolean processRules(Object ruleEvent) {
        return budgetUnrecoveredFandARule.processAddBudgetUnrecoveredFandABusinessRules((AddBudgetUnrecoveredFandAEvent) ruleEvent);
    }

    @Override
    protected Object generateReferenceArtifact() {
        return new BudgetUnrecoveredFandA(BUDGET_FISCAL_YEAR, AMOUNT, APPLICABLE_RATE, ON_CAMPUS_FLAG, SOURCE_ACCOUNT);
    }
    
    @Override
    protected Object generateDifferentArtifact() {
        BudgetUnrecoveredFandA ref = (BudgetUnrecoveredFandA) generateReferenceArtifact();
        return new BudgetUnrecoveredFandA(ref.getFiscalYear() + 1 , ref.getAmount().add(AMOUNT), 
                                        ref.getApplicableRate().add(APPLICABLE_RATE), "N", ref.getSourceAccount() + "-v2");
    }
}

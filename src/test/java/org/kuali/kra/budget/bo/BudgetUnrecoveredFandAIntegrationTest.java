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

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.RateDecimal;
import org.kuali.kra.budget.document.BudgetDocument;

public class BudgetUnrecoveredFandAIntegrationTest extends BudgetDistributionAndIncomeIntegrationTest {
    private static final RateDecimal APPLICABLE_RATE_1 =  new RateDecimal(5.555);
    private static final RateDecimal APPLICABLE_RATE_2 =  new RateDecimal(10.345);
    private static final RateDecimal APPLICABLE_RATE_3 =  new RateDecimal(99.999);
    
    private static final String CAMPUS_NO = "N";
    private static final String CAMPUS_YES = "Y";
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    @Test
    public void testSave_MissingFieldsRequiredAtProposalValidation() throws Exception {
        BudgetUnrecoveredFandA budgetUnrecoveredFandA = new BudgetUnrecoveredFandA();
        budgetDocument.add(budgetUnrecoveredFandA);
        getDocumentService().saveDocument(budgetDocument);
        
        BudgetDocument savedDocument = (BudgetDocument) getDocumentService().getByDocumentHeaderId(budgetDocument.getDocumentNumber());
        assertNotNull(savedDocument);        
        assertEquals(1, savedDocument.getBudgetUnrecoveredFandAs().size());
        BudgetUnrecoveredFandA savedBudgetUnrecoveredFandA = savedDocument.getBudgetUnrecoveredFandAs().get(0); 
        assertEquals(BudgetDecimal.ZERO, savedBudgetUnrecoveredFandA.getAmount());
        assertNull(savedBudgetUnrecoveredFandA.getApplicableRate());
        assertNull(savedBudgetUnrecoveredFandA.getOnCampusFlag());
        assertNull(savedBudgetUnrecoveredFandA.getFiscalYear());
        assertNull(savedBudgetUnrecoveredFandA.getSourceAccount());
    }

    @Override
    protected void addBudgetDistributionAndIncomeComponent(BudgetDistributionAndIncomeComponent component) {
        budgetDocument.add((BudgetUnrecoveredFandA) component);
    }

    @Override
    protected BudgetDistributionAndIncomeComponent[] createBudgetDistributionAndIncomeComponentCollection() {
        return new BudgetDistributionAndIncomeComponent[] { 
                new BudgetUnrecoveredFandA(FY_2008, AMOUNT_1, APPLICABLE_RATE_1, CAMPUS_YES, SOURCE_ACCOUNT_1), 
                new BudgetUnrecoveredFandA(FY_2008, AMOUNT_2, APPLICABLE_RATE_2, CAMPUS_NO, SOURCE_ACCOUNT_2),
                new BudgetUnrecoveredFandA(FY_2009, AMOUNT_3, APPLICABLE_RATE_3, CAMPUS_YES, SOURCE_ACCOUNT_3)
            };
    }

    @Override
    protected List<? extends BudgetDistributionAndIncomeComponent> getBudgetDistributionAndIncomeComponents(BudgetDocument budgetDocument) {
        return budgetDocument.getBudgetUnrecoveredFandAs();
    }

    @Override
    protected BudgetDistributionAndIncomeComponent createBudgetDistributionAndIncomeComponent() {
        return new BudgetUnrecoveredFandA(FY_2009, AMOUNT_2, APPLICABLE_RATE_2, CAMPUS_NO, SOURCE_ACCOUNT_2);
    }
}

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
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.document.BudgetDocument;

public class BudgetCostShareIntegrationTest extends BudgetDistributionAndIncomeIntegrationTest {

    private static final BudgetDecimal SHARE_PCT_0 =  new BudgetDecimal(0.00);
    private static final BudgetDecimal SHARE_PCT_1 =  new BudgetDecimal(5.55);
    private static final BudgetDecimal SHARE_PCT_2 =  new BudgetDecimal(10.00);
    private static final BudgetDecimal SHARE_PCT_3 =  new BudgetDecimal(99.99);
    
    @Before
    public void setUp() throws Exception {
        super.setUp();        
    }
    
    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);  
        super.tearDown();
    }
    
    @Test
    public void testSave_MissingFieldsRequiredAtProposalValidation() throws Exception {
        BudgetCostShare budgetCostShare = new BudgetCostShare();
        budgetCostShare.setSourceAccount(null);
        budgetDocument.add(budgetCostShare);
        getDocumentService().saveDocument(budgetDocument);
        
        BudgetDocument savedDocument = (BudgetDocument) getDocumentService().getByDocumentHeaderId(budgetDocument.getDocumentNumber());
        assertNotNull(savedDocument);        
        assertEquals(1, savedDocument.getBudgetCostShares().size());
        assertNull(savedDocument.getBudgetCostShares().get(0).getFiscalYear());
        assertEquals(BudgetDecimal.ZERO, savedDocument.getBudgetCostShares().get(0).getShareAmount());
        assertEquals(BudgetDecimal.ZERO, savedDocument.getBudgetCostShares().get(0).getSharePercentage());
        assertNull(savedDocument.getBudgetCostShares().get(0).getSourceAccount());
    }

    protected BudgetDistributionAndIncomeComponent[] createBudgetDistributionAndIncomeComponentCollection() {
        return new BudgetCostShare[] { new BudgetCostShare(FY_2008, AMOUNT_1, SHARE_PCT_1, SOURCE_ACCOUNT_1), 
                                            new BudgetCostShare(FY_2009, AMOUNT_2, SHARE_PCT_2, SOURCE_ACCOUNT_2),
                                            new BudgetCostShare(FY_2010, AMOUNT_3, SHARE_PCT_3, SOURCE_ACCOUNT_3)
                                        };
    }

    protected void addBudgetDistributionAndIncomeComponent(BudgetDistributionAndIncomeComponent costShare) {
        budgetDocument.add((BudgetCostShare)costShare);
    }
    
    protected List<? extends BudgetDistributionAndIncomeComponent> getBudgetDistributionAndIncomeComponents(BudgetDocument budgetDocument) {
        return budgetDocument.getBudgetCostShares();
    }

    @Override
    protected BudgetDistributionAndIncomeComponent createBudgetDistributionAndIncomeComponent() {
        return new BudgetCostShare(FY_2008, AMOUNT_1, SHARE_PCT_0, SOURCE_ACCOUNT_1);
    }
}

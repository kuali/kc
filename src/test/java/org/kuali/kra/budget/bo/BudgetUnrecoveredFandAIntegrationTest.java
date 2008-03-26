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
package org.kuali.kra.budget.bo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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
    public void testDelete_FromMultipleInstances() throws Exception {
        BudgetUnrecoveredFandA[] unrecoveredFandAs = createBudgetUnrecoveredFandACollection();
        BudgetDocument savedDocument = saveAndRetrieveBudgetWithUnrecoveredFandAs(unrecoveredFandAs);
                
        for(int i = 0, expectedSize = unrecoveredFandAs.length - 1; i < unrecoveredFandAs.length; i++, expectedSize--) {
            savedDocument.remove(unrecoveredFandAs[i]);
            getDocumentService().saveDocument(budgetDocument);
            savedDocument = (BudgetDocument) getDocumentService().getByDocumentHeaderId(budgetDocument.getDocumentNumber());
            assertEquals(expectedSize, savedDocument.getBudgetUnrecoveredFandAs().size());
        }
    }
    
    @Test
    public void testDelete_SingleInstance() throws Exception {
        BudgetUnrecoveredFandA unrecoveredFandA = new BudgetUnrecoveredFandA(FY_2008, AMOUNT_1, APPLICABLE_RATE_1, CAMPUS_YES, SOURCE_ACCOUNT_1);
        budgetDocument.add(unrecoveredFandA);
        getDocumentService().saveDocument(budgetDocument);
        
        BudgetDocument savedDocument = (BudgetDocument) getDocumentService().getByDocumentHeaderId(budgetDocument.getDocumentNumber());
        assertNotNull(savedDocument);
        assertEquals(1, savedDocument.getBudgetUnrecoveredFandAs().size());
        
        savedDocument.remove(unrecoveredFandA);
        getDocumentService().saveDocument(savedDocument);
        
        savedDocument = (BudgetDocument) getDocumentService().getByDocumentHeaderId(budgetDocument.getDocumentNumber());
        assertEquals(0, savedDocument.getBudgetUnrecoveredFandAs().size());
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
        assertNull(savedBudgetUnrecoveredFandA.getAmount());
        assertNull(savedBudgetUnrecoveredFandA.getApplicableRate());
        assertNull(savedBudgetUnrecoveredFandA.getOnCampusFlag());
        assertNull(savedBudgetUnrecoveredFandA.getFiscalYear());
        assertNull(savedBudgetUnrecoveredFandA.getSourceAccount());
    }


    @Test
    public void testSave_MultipleInstances() throws Exception {
        BudgetUnrecoveredFandA[] unrecoveredFandAs = createBudgetUnrecoveredFandACollection();
        BudgetDocument savedDocument = saveAndRetrieveBudgetWithUnrecoveredFandAs(unrecoveredFandAs);
        assertNotNull(savedDocument);        
        assertEquals(unrecoveredFandAs.length, savedDocument.getBudgetUnrecoveredFandAs().size());        
    }
    
   
    @Test
    public void testSave_SingleInstance() throws Exception {
        BudgetUnrecoveredFandA unrecoveredFandA = new BudgetUnrecoveredFandA(FY_2009, AMOUNT_2, APPLICABLE_RATE_2, CAMPUS_NO, SOURCE_ACCOUNT_2);
        budgetDocument.add(unrecoveredFandA);
        getDocumentService().saveDocument(budgetDocument);

        BudgetDocument savedDocument = (BudgetDocument) getDocumentService().getByDocumentHeaderId(budgetDocument.getDocumentNumber());
        assertNotNull(savedDocument);        
        assertEquals(1, savedDocument.getBudgetUnrecoveredFandAs().size());        
    }
    
    private BudgetUnrecoveredFandA[] createBudgetUnrecoveredFandACollection() {
        return new BudgetUnrecoveredFandA[] { new BudgetUnrecoveredFandA(FY_2008, AMOUNT_1, APPLICABLE_RATE_1, CAMPUS_YES, SOURCE_ACCOUNT_1), 
                                                new BudgetUnrecoveredFandA(FY_2008, AMOUNT_2, APPLICABLE_RATE_2, CAMPUS_NO, SOURCE_ACCOUNT_2),
                                                new BudgetUnrecoveredFandA(FY_2009, AMOUNT_3, APPLICABLE_RATE_3, CAMPUS_YES, SOURCE_ACCOUNT_3)
                                            };
    }    
    
    private BudgetDocument saveAndRetrieveBudgetWithUnrecoveredFandAs(BudgetUnrecoveredFandA[] unrecoveredFandAs) throws Exception {
        for(BudgetUnrecoveredFandA unrecoveredFandA: unrecoveredFandAs) {
              budgetDocument.add(unrecoveredFandA);
          }
                  
        getDocumentService().saveDocument(budgetDocument);
    
        return (BudgetDocument) getDocumentService().getByDocumentHeaderId(budgetDocument.getDocumentNumber());
    }
}
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
    }
    
    @Test
    public void testDelete_SingleInstance() throws Exception {
        BudgetCostShare costShare = new BudgetCostShare(FY_2008, AMOUNT_1, SHARE_PCT_0, SOURCE_ACCOUNT_1);
        budgetDocument.add(costShare);
        documentService.saveDocument(budgetDocument);
      
        BudgetDocument savedDocument = (BudgetDocument) documentService.getByDocumentHeaderId(budgetDocument.getDocumentNumber());
        assertNotNull(savedDocument);        
        assertEquals(1, savedDocument.getBudgetCostShares().size());
              
        savedDocument.remove(costShare);
        documentService.saveDocument(savedDocument);
      
        savedDocument = (BudgetDocument) documentService.getByDocumentHeaderId(budgetDocument.getDocumentNumber());
        assertEquals(0, savedDocument.getBudgetCostShares().size());
    }
    
    @Test
    public void testSave_SingleInstance() throws Exception {
        BudgetCostShare costShare = new BudgetCostShare(FY_2008, AMOUNT_1, SHARE_PCT_0, SOURCE_ACCOUNT_1);
        budgetDocument.add(costShare);
        documentService.saveDocument(budgetDocument);

        BudgetDocument savedDocument = (BudgetDocument) documentService.getByDocumentHeaderId(budgetDocument.getDocumentNumber());
        assertNotNull(savedDocument);        
        assertEquals(1, savedDocument.getBudgetCostShares().size());        
    }
    
//    @Test
//    public void testSave_MultipleInstances() throws Exception {
//        BudgetCostShare[] costShares = { new BudgetCostShare(FY_2008, SHARE_PCT_1, AMOUNT_1, SOURCE_ACCOUNT_1), 
//                                            new BudgetCostShare(FY_2009, SHARE_PCT_2, SHARE_AMOUNT_2, SOURCE_ACCOUNT_2),
//                                            new BudgetCostShare(FY_2010, SHARE_PCT_3, AMOUNT_3, SOURCE_ACCOUNT_3)
//                                        };
//        for(BudgetCostShare costShare: costShares) {
//            budgetDocument.add(costShare);
//        }
//                
//        documentService.saveDocument(budgetDocument);
//
//        BudgetDocument savedDocument = (BudgetDocument) documentService.getByDocumentHeaderId(budgetDocument.getDocumentNumber());
//        assertNotNull(savedDocument);        
//        assertEquals(3, savedDocument.getBudgetCostShares().size());        
//    }
    
//  @Test
//  public void testDelete_FromMultipleInstances() throws Exception {
//      BudgetCostShare[] costShares = createBudgetCostShareCollection();
//      for(BudgetCostShare costShare: costShares) {
//          budgetDocument.add(costShare);
//      }
//      
//      documentService.saveDocument(budgetDocument);
//      
//      BudgetDocument savedDocument = (BudgetDocument) documentService.getByDocumentHeaderId(budgetDocument.getDocumentNumber());
//      assertNotNull(savedDocument);        
//      assertEquals(costShares.length, savedDocument.getBudgetCostShares().size());
//              
//      savedDocument.remove(costShares[1]);
//      documentService.saveDocument(savedDocument);
//      
//      savedDocument = (BudgetDocument) documentService.getByDocumentHeaderId(budgetDocument.getDocumentNumber());
//      assertEquals(2, savedDocument.getBudgetCostShares().size());
//      
//      savedDocument = (BudgetDocument) documentService.getByDocumentHeaderId(budgetDocument.getDocumentNumber());
//      savedDocument.getBudgetCostShares().clear();
//      documentService.saveDocument(savedDocument);
//      
//      savedDocument = (BudgetDocument) documentService.getByDocumentHeaderId(budgetDocument.getDocumentNumber());
//      assertEquals(0, savedDocument.getBudgetCostShares().size());
//  }

    @Test
    public void testSave_MissingFieldsRequiredAtProposalValidation() throws Exception {
        BudgetCostShare budgetCostShare = new BudgetCostShare();
        budgetCostShare.setSourceAccount(null);
        budgetDocument.add(budgetCostShare);
        documentService.saveDocument(budgetDocument);
        
        BudgetDocument savedDocument = (BudgetDocument) documentService.getByDocumentHeaderId(budgetDocument.getDocumentNumber());
        assertNotNull(savedDocument);        
        assertEquals(1, savedDocument.getBudgetCostShares().size());
        assertNull(savedDocument.getBudgetCostShares().get(0).getFiscalYear());
        assertNull(savedDocument.getBudgetCostShares().get(0).getShareAmount());
        assertNull(savedDocument.getBudgetCostShares().get(0).getSharePercentage());
        assertNull(savedDocument.getBudgetCostShares().get(0).getSourceAccount());
    }

//    private BudgetCostShare[] createBudgetCostShareCollection() {
//        return new BudgetCostShare[] { new BudgetCostShare(FY_2008, SHARE_PCT_1, AMOUNT_1, SOURCE_ACCOUNT_1), 
//                                            new BudgetCostShare(FY_2009, SHARE_PCT_2, SHARE_AMOUNT_2, SOURCE_ACCOUNT_2),
//                                            new BudgetCostShare(FY_2010, SHARE_PCT_3, AMOUNT_3, SOURCE_ACCOUNT_3)
//                                        };
//    }

}

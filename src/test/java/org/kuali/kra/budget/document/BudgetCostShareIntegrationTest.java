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
package org.kuali.kra.budget.document;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.UserSession;
import org.kuali.core.service.DocumentService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetCostShare;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.KNSServiceLocator;

public class BudgetCostShareIntegrationTest extends KraTestBase {

    private static final int FY_2008 = 2008;
//    private static final int FY_2009 = 2009;
//    private static final int FY_2010 = 2010;
    private static final BudgetDecimal SHARE_AMOUNT_1 = new BudgetDecimal(1000.00);
//    private static final BudgetDecimal SHARE_AMOUNT_2 =  new BudgetDecimal(2000.00);
//    private static final BudgetDecimal SHARE_AMOUNT_3 =  new BudgetDecimal(3000.00);
    private static final BudgetDecimal SHARE_PCT_0 =  new BudgetDecimal(0.00);
//    private static final BudgetDecimal SHARE_PCT_1 =  new BudgetDecimal(5.55);
//    private static final BudgetDecimal SHARE_PCT_2 =  new BudgetDecimal(10.00);
//    private static final BudgetDecimal SHARE_PCT_3 =  new BudgetDecimal(85.99);
    private static final String SOURCE_ACCOUNT_1 = "12345A";
//    private static final String SOURCE_ACCOUNT_2 = "12345B";
//    private static final String SOURCE_ACCOUNT_3 = "12345C";
    private BudgetDocument budgetDocument;
    private DocumentService documentService;
    private ProposalDevelopmentDocument proposalDocument;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        documentService = KNSServiceLocator.getDocumentService();
        initProposalDocument();
        initBudgetDocument();
    }
    
    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        documentService = null;
        budgetDocument = null;
        super.tearDown();
    }
    
    @Test
    public void testDelete() throws Exception {
        BudgetCostShare costShare = new BudgetCostShare(FY_2008, SHARE_AMOUNT_1, SHARE_PCT_0, SOURCE_ACCOUNT_1);
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
    public void testLoadingFiscalYearStart() {
        Date fiscalYearStart = budgetDocument.loadFiscalYearStart();
        
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(2000, Calendar.JULY, 1, 0, 0, 0); // test data set via load_system_params.sql
        Assert.assertEquals(cal.getTimeInMillis(), fiscalYearStart.getTime());
    }
    
    @Test
    public void testSave_SingleInstance() throws Exception {
        BudgetCostShare costShare = new BudgetCostShare(FY_2008, SHARE_AMOUNT_1, SHARE_PCT_0, SOURCE_ACCOUNT_1);
        budgetDocument.add(costShare);
        documentService.saveDocument(budgetDocument);

        BudgetDocument savedDocument = (BudgetDocument) documentService.getByDocumentHeaderId(budgetDocument.getDocumentNumber());
        assertNotNull(savedDocument);        
        assertEquals(1, savedDocument.getBudgetCostShares().size());        
    }
    
//    @Test
//    public void testSave_MultipleInstances() throws Exception {
//        BudgetCostShare[] costShares = createBudgetCostShareCollection();
//        for(BudgetCostShare costShare: costShares) {
//            budgetDocument.add(costShare);
//        }
//        
//        for(BudgetCostShare costShare: budgetDocument.getBudgetCostShares()) {
//            System.out.println("************ Cost Share " + costShare);
//        }
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
//        return new BudgetCostShare[] { new BudgetCostShare(FY_2008, SHARE_PCT_1, SHARE_AMOUNT_1, SOURCE_ACCOUNT_1), 
//                                            new BudgetCostShare(FY_2009, SHARE_PCT_2, SHARE_AMOUNT_2, SOURCE_ACCOUNT_2),
//                                            new BudgetCostShare(FY_2010, SHARE_PCT_3, SHARE_AMOUNT_3, SOURCE_ACCOUNT_3)
//                                        };
//    }

    private BudgetPeriod createBudgetPeriod(Integer budgetPeriodNumber) {
        BudgetPeriod budgetPeriod = new BudgetPeriod();
        budgetPeriod.setBudgetPeriod(budgetPeriodNumber);
        budgetPeriod.setStartDate(getNowDate());
        budgetPeriod.setEndDate(getNowDate());
        return budgetPeriod;
    }

//    private BudgetProjectIncome[] createBudgetCostShareCollection() {
//        BudgetProjectIncome[] projectIncomes = { createBudgetCostShare(BUDGET_FISCAL_YEAR_1, SHARE_AMOUNT_1, DESCRIPTION_1), 
//                                                    createBudgetCostShare(BUDGET_FISCAL_YEAR_2, SHARE_AMOUNT_2, DESCRIPTION_2),
//                                                    createBudgetCostShare(BUDGET_FISCAL_YEAR_3, SHARE_AMOUNT_3, DESCRIPTION_3) };
//        return projectIncomes;
//    }

    private Date getNowDate() {
        return new Date(System.currentTimeMillis());
    }

    private void initBudgetDocument() throws Exception {
        budgetDocument = (BudgetDocument) documentService.getNewDocument("BudgetDocument");
        budgetDocument.setProposalNumber(proposalDocument.getProposalNumber());
        budgetDocument.getDocumentHeader().setFinancialDocumentDescription("Budget Document");
        budgetDocument.setBudgetVersionNumber(1);
        budgetDocument.setStartDate(getNowDate());
        budgetDocument.setEndDate(getNowDate());
        budgetDocument.setOhRateClassCode("1");
        budgetDocument.setUrRateClassCode("2");
        budgetDocument.setModularBudgetFlag("N");
        budgetDocument.add(createBudgetPeriod(1));
        budgetDocument.add(createBudgetPeriod(2));
        
        documentService.saveDocument(budgetDocument);
        budgetDocument = (BudgetDocument) documentService.getByDocumentHeaderId(budgetDocument.getDocumentNumber());  
    }
    
    
    private void initProposalDocument() throws Exception {
        proposalDocument = (ProposalDevelopmentDocument) documentService.getNewDocument("ProposalDevelopmentDocument");
        proposalDocument.getDocumentHeader().setFinancialDocumentDescription("ProposalDevelopmentDocumentTest test doc");
        proposalDocument.setSponsorCode("005770");
        proposalDocument.setTitle("project title");
        proposalDocument.setRequestedStartDateInitial(getNowDate());
        proposalDocument.setRequestedEndDateInitial(getNowDate());
        proposalDocument.setActivityTypeCode("1");
        proposalDocument.setProposalTypeCode("1");
        proposalDocument.setOwnedByUnitNumber("000001");

        documentService.saveDocument(proposalDocument);

        proposalDocument = (ProposalDevelopmentDocument) documentService.getByDocumentHeaderId(proposalDocument.getDocumentNumber());        
    }
}

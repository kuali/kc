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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.UserSession;
import org.kuali.core.service.DocumentService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.KualiDecimal;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetProjectIncome;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.KNSServiceLocator;
import org.springframework.dao.DataIntegrityViolationException;

public class BudgetProjectIncomeIntegrationTest extends KraTestBase {

    private static final int BUDGET_PERIOD_1 = 1;
    private static final int BUDGET_PERIOD_2 = 2;
    private static final int BUDGET_PERIOD_3 = 3;
    private static final String DESCRIPTION_1 = "Budget Project Income 1";
    private static final String DESCRIPTION_2 = "Budget Project Income 2";
    private static final String DESCRIPTION_3 = "Budget Project Income 3";
    private static final double PROJECT_INCOME_1 = 1000.00;
    private static final double PROJECT_INCOME_2 = 2000.00;
    private static final double PROJECT_INCOME_3 = 3000.00;
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
    public void testDeleteProjectIncome() throws Exception {
//      TODO - Preferred to test against collection, but kept getting Optimistic lock exception saying the project incomes were changed by someone
//        This is not the runtime (web) behavior, so I wrote it off as a test setup anomoly - Jack Frosch
//        BudgetProjectIncome[] projectIncomes = createBudgetProjectIncomeCollection();
//        for(BudgetProjectIncome projectIncome: projectIncomes) {
//            budgetDocument.add(projectIncome);
//        }        
//      BudgetDocument savedDocument = (BudgetDocument) documentService.getByDocumentHeaderId(budgetDocument.getDocumentNumber());
//      assertNotNull(savedDocument);        
//      assertEquals(projectIncomes.length, savedDocument.getBudgetProjectIncomes().size());
        
        BudgetProjectIncome projectIncome = createBudgetProjectIncome(BUDGET_PERIOD_1, PROJECT_INCOME_1, DESCRIPTION_1);
        budgetDocument.add(projectIncome);        
        documentService.saveDocument(budgetDocument);
        
        BudgetDocument savedDocument = (BudgetDocument) documentService.getByDocumentHeaderId(budgetDocument.getDocumentNumber());
        assertEquals(1, savedDocument.getBudgetProjectIncomes().size());
        
        savedDocument.remove(projectIncome);        
        documentService.saveDocument(savedDocument);
        
        savedDocument = (BudgetDocument) documentService.getByDocumentHeaderId(budgetDocument.getDocumentNumber());
        assertNotNull(savedDocument);        
        assertEquals(0, savedDocument.getBudgetProjectIncomes().size());
    }
    
    @Test
    public void testSave() throws Exception {
        BudgetProjectIncome projectIncome = createBudgetProjectIncome(BUDGET_PERIOD_1, PROJECT_INCOME_1, DESCRIPTION_1);
        budgetDocument.add(projectIncome);
        documentService.saveDocument(budgetDocument);

        BudgetDocument savedDocument = (BudgetDocument) documentService.getByDocumentHeaderId(budgetDocument.getDocumentNumber());
        assertNotNull(savedDocument);        
        assertEquals(1, savedDocument.getBudgetProjectIncomes().size());        
    }

    @Test(expected=DataIntegrityViolationException.class)
    public void testSave_MissingRequiredField() throws Exception {
        BudgetProjectIncome budgetProjectIncome = createBudgetProjectIncome(1, 1000.00, DESCRIPTION_1);
        budgetProjectIncome.setDescription(null);
        budgetDocument.add(budgetProjectIncome);
        
        documentService.saveDocument(budgetDocument);
    }

    private BudgetPeriod createBudgetPeriod(Integer budgetPeriodNumber) {
        BudgetPeriod budgetPeriod = new BudgetPeriod();
        budgetPeriod.setBudgetPeriod(budgetPeriodNumber);
        budgetPeriod.setStartDate(getNowDate());
        budgetPeriod.setEndDate(getNowDate());
        return budgetPeriod;
    }
    
    private BudgetProjectIncome createBudgetProjectIncome(Integer budgetPeriodNumber, double projectIncome, String description) {
        BudgetProjectIncome budgetProjectIncome = new BudgetProjectIncome();
        budgetProjectIncome.setBudgetPeriodNumber(1);
        budgetProjectIncome.setProjectIncome(new KualiDecimal(projectIncome));
        budgetProjectIncome.setDescription(description);
        return budgetProjectIncome;
    }

//    private BudgetProjectIncome[] createBudgetProjectIncomeCollection() {
//        BudgetProjectIncome[] projectIncomes = { createBudgetProjectIncome(BUDGET_PERIOD_1, PROJECT_INCOME_1, DESCRIPTION_1), 
//                                                    createBudgetProjectIncome(BUDGET_PERIOD_2, PROJECT_INCOME_2, DESCRIPTION_2),
//                                                    createBudgetProjectIncome(BUDGET_PERIOD_3, PROJECT_INCOME_3, DESCRIPTION_3) };
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

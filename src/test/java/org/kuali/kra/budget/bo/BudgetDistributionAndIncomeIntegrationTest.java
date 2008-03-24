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

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.kuali.core.UserSession;
import org.kuali.core.service.DocumentService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.KNSServiceLocator;

public abstract class BudgetDistributionAndIncomeIntegrationTest extends KraTestBase {
    protected static final BudgetDecimal AMOUNT_1 = new BudgetDecimal(1000.00);
    protected static final BudgetDecimal AMOUNT_2 = new BudgetDecimal(2000.00);
    protected static final BudgetDecimal AMOUNT_3 = new BudgetDecimal(3000.00);
    
    protected static final int FY_2008 = 2008;
    protected static final int FY_2009 = 2009;
    protected static final int FY_2010 = 2010;
    
    protected static final String SOURCE_ACCOUNT_1 = "12345A";
    protected static final String SOURCE_ACCOUNT_2 = "12345B";
    protected static final String SOURCE_ACCOUNT_3 = "12345C";
    
    protected BudgetDocument budgetDocument;
    protected DocumentService documentService;
    
    protected ProposalDevelopmentDocument proposalDocument;
    
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
    
    
    
    protected Date getNowDate() {
        return new Date(System.currentTimeMillis());
    }
    
    private BudgetPeriod createBudgetPeriod(Integer budgetPeriodNumber) {
        BudgetPeriod budgetPeriod = new BudgetPeriod();
        budgetPeriod.setBudgetPeriod(budgetPeriodNumber);
        budgetPeriod.setStartDate(getNowDate());
        budgetPeriod.setEndDate(getNowDate());
        return budgetPeriod;
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

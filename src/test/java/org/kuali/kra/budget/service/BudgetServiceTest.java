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
package org.kuali.kra.budget.service;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.RiceConstants;
import org.kuali.core.UserSession;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

/**
 * Unit tests for the BudgetService interface
 */
public class BudgetServiceTest extends KraTestBase {
    
    BudgetService budgetService;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        budgetService = getService(BudgetService.class);
    }
    
    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        budgetService = null;
        super.tearDown();
    }
    
    /**
     * Test getNewBudgetVersion() method
     * 
     * @throws Exception
     */
    @Test
    public void testGetNewBudgetVersion() throws Exception {
 
        ProposalDevelopmentDocument pdDocument = getPersistedProposalDevelopmentDocument();
        
        String testProposalNumber = pdDocument.getProposalNumber();
        String testDocumentDescription = "Test New Budget Doc";
        
        BudgetDocument budgetDocument = budgetService.getNewBudgetVersion(pdDocument, testDocumentDescription);
        
        // Verify that status is final
        assertEquals(RiceConstants.DocumentStatusCodes.APPROVED, budgetDocument.getDocumentHeader().getFinancialDocumentStatusCode());
        
        // Verify that fields were set properly
        assertEquals(budgetDocument.getProposalNumber(), testProposalNumber);
        assertEquals(budgetDocument.getDocumentHeader().getFinancialDocumentDescription(), testDocumentDescription);
    }
    
    /**
     * Test copyBudgetVersion method
     * 
     * @throws Exception
     */
    public void testCopyBudgetVersion() throws Exception {
        
        ProposalDevelopmentDocument pdDocument = getPersistedProposalDevelopmentDocument();
        
        String testProposalNumber = pdDocument.getProposalNumber();
        String testDocumentDescription = "Test Copy Budget Doc";
        
        BudgetDocument budgetDocument = budgetService.getNewBudgetVersion(pdDocument, testDocumentDescription);
        
        BudgetDocument copyBudgetDocument = budgetService.copyBudgetVersion(budgetDocument);
        
//      Verify that status is final
        assertEquals(RiceConstants.DocumentStatusCodes.APPROVED, budgetDocument.getDocumentHeader().getDocumentStatus());
        
        // Verify that fields were set properly
        assertEquals(copyBudgetDocument.getProposalNumber(), testProposalNumber);
        assertEquals(copyBudgetDocument.getDocumentHeader().getFinancialDocumentDescription(), testDocumentDescription);
    }
    
    /**
     * A BudgetDocument can only be created by a persisted ProposalDevelopmentDocument.  This convenience
     * method will provide a persisted ProposalDevelopmentDocument.
     * 
     * @return ProposalDevelopmentDocument
     * @throws Exception
     */
    protected ProposalDevelopmentDocument getPersistedProposalDevelopmentDocument() throws Exception {
        ProposalDevelopmentDocument pdDocument = 
            (ProposalDevelopmentDocument) getDocumentService().getNewDocument(ProposalDevelopmentDocument.class);
        
        pdDocument.getDocumentHeader().setFinancialDocumentDescription("Testing budget versions");
        pdDocument.setActivityTypeCode("2");
        pdDocument.setOwnedByUnitNumber("IN-CARD");
        pdDocument.setProposalTypeCode("1");
        pdDocument.setTitle("Testing budget versions");
        pdDocument.setSponsorCode("005770");
        pdDocument.setRequestedStartDateInitial(new Date(1/1/2008));
        pdDocument.setRequestedEndDateInitial(new Date(12/31/2008));
        
        getDocumentService().saveDocument(pdDocument);
        
        return pdDocument;
    }
    
}

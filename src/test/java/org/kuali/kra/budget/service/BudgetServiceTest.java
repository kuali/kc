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
package org.kuali.kra.budget.service;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.budget.core.BudgetService;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.document.authorization.PessimisticLock;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.util.GlobalVariables;

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
        
        String testProposalNumber = pdDocument.getDevelopmentProposal().getProposalNumber();
        String testDocumentDescription = "Test New Budget Doc";
        
        UserSession currentSession = GlobalVariables.getUserSession();
        PessimisticLock lock = KNSServiceLocator.getPessimisticLockService().generateNewLock(pdDocument.getDocumentNumber(), pdDocument.getDocumentNumber()+"-BUDGET", currentSession.getPerson());
        pdDocument.addPessimisticLock(lock);
        
        BudgetDocument budgetDocument = budgetService.getNewBudgetVersion(pdDocument, testDocumentDescription);
        
        // Verify that status is final
        assertTrue(budgetDocument.getDocumentHeader().getWorkflowDocument().stateIsApproved());
        //assertEquals(KNSConstants.DocumentStatusCodes.APPROVED, budgetDocument.getDocumentHeader().getDocumentStatusCode());
        
        // Verify that fields were set properly
        assertEquals(((ProposalDevelopmentDocument)budgetDocument.getParentDocument()).getDevelopmentProposal().getProposalNumber(), testProposalNumber);
        assertEquals(budgetDocument.getDocumentHeader().getDocumentDescription(), testDocumentDescription);
    }
    
    /**
     * Test copyBudgetVersion method
     * 
     * @throws Exception
     */
    public void testCopyBudgetVersion() throws Exception {
        
        ProposalDevelopmentDocument pdDocument = getPersistedProposalDevelopmentDocument();
        
        String testProposalNumber = pdDocument.getDevelopmentProposal().getProposalNumber();
        String testDocumentDescription = "Test Copy Budget Doc";
        
        BudgetDocument budgetDocument = budgetService.getNewBudgetVersion(pdDocument, testDocumentDescription);
        
        BudgetDocument copyBudgetDocument = budgetService.copyBudgetVersion(budgetDocument);
        
//      //Verify that status is final
        assertTrue(budgetDocument.getDocumentHeader().getWorkflowDocument().stateIsApproved());
        //assertEquals(KNSConstants.DocumentStatusCodes.APPROVED, budgetDocument.getDocumentHeader().getDocumentStatus());
        
        // Verify that fields were set properly
        assertEquals(((ProposalDevelopmentDocument)copyBudgetDocument.getParentDocument()).getDevelopmentProposal().getProposalNumber(), testProposalNumber);
        assertEquals(copyBudgetDocument.getDocumentHeader().getDocumentDescription(), testDocumentDescription);
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
        
        pdDocument.getDocumentHeader().setDocumentDescription("Testing budget versions");
        pdDocument.getDevelopmentProposal().setActivityTypeCode("2");
        pdDocument.getDevelopmentProposal().setOwnedByUnitNumber("IN-CARD");
        pdDocument.getDevelopmentProposal().setProposalTypeCode("1");
        pdDocument.getDevelopmentProposal().setTitle("Testing budget versions");
        pdDocument.getDevelopmentProposal().setSponsorCode("005770");
        pdDocument.getDevelopmentProposal().setRequestedStartDateInitial(new Date(1/1/2008));
        pdDocument.getDevelopmentProposal().setRequestedEndDateInitial(new Date(12/31/2008));
        
        getDocumentService().saveDocument(pdDocument);
        
        pdDocument.getDevelopmentProposal().refreshReferenceObject("ownedByUnit");
        
        String username = "quickstart";
        KraAuthorizationService kraAuthorizationService = KraServiceLocator.getService(KraAuthorizationService.class);
        kraAuthorizationService.addRole(username, RoleConstants.AGGREGATOR, pdDocument);
        
        return pdDocument;
    }
    
}

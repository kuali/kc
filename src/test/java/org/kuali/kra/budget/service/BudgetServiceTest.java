/*
 * Copyright 2005-2010 The Kuali Foundation
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
import org.kuali.kra.budget.core.BudgetCommonService;
import org.kuali.kra.budget.core.BudgetService;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.proposaldevelopment.budget.service.ProposalBudgetService;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * Unit tests for the BudgetService interface
 */
public class BudgetServiceTest extends KcUnitTestBase {
    
    private BudgetService budgetService;
    private BudgetCommonService budgetCommonService;
    private ProposalDevelopmentService proposalDevelopmentService;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        budgetService = getService(BudgetService.class);
        budgetCommonService = getService(ProposalBudgetService.class);
        proposalDevelopmentService = getService(ProposalDevelopmentService.class);
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
        PessimisticLock lock = KRADServiceLocatorWeb.getPessimisticLockService().generateNewLock(pdDocument.getDocumentNumber(), pdDocument.getDocumentNumber()+"-BUDGET", currentSession.getPerson());
        pdDocument.addPessimisticLock(lock);
        
        BudgetDocument budgetDocument = budgetCommonService.getNewBudgetVersion(pdDocument, testDocumentDescription);
        
        // Verify that status is final
        assertTrue(budgetDocument.getDocumentHeader().getWorkflowDocument().isApproved());
        //assertEquals(KRADConstants.DocumentStatusCodes.APPROVED, budgetDocument.getDocumentHeader().getDocumentStatusCode());
        
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
        
        BudgetDocument budgetDocument = budgetCommonService.getNewBudgetVersion(pdDocument, testDocumentDescription);
        
        BudgetDocument copyBudgetDocument = budgetCommonService.copyBudgetVersion(budgetDocument);
        
//      //Verify that status is final
        assertTrue(budgetDocument.getDocumentHeader().getWorkflowDocument().isApproved());
        //assertEquals(KRADConstants.DocumentStatusCodes.APPROVED, budgetDocument.getDocumentHeader().getDocumentStatus());
        
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
        pdDocument.getDevelopmentProposal().setPrimeSponsorCode("000120");
        
        proposalDevelopmentService.initializeUnitOrganizationLocation(pdDocument);
        proposalDevelopmentService.initializeProposalSiteNumbers(pdDocument);
        
        getDocumentService().saveDocument(pdDocument);
        
        pdDocument.getDevelopmentProposal().refreshReferenceObject("ownedByUnit");
        
        PersonService personService = getService(PersonService.class);
        Person user = personService.getPersonByPrincipalName("quickstart");
        
        getService(KraAuthorizationService.class).addRole(user.getPrincipalId(), RoleConstants.AGGREGATOR, pdDocument);
        
        return pdDocument;
    }
    
}

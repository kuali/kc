/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentService;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetCommonService;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.coeus.propdev.impl.budget.ProposalBudgetService;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.GlobalVariables;

import java.sql.Date;
import java.util.Collections;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;
/**
 * Unit tests for the BudgetService interface
 */
public class BudgetServiceTest extends KcIntegrationTestBase {
    
    private BudgetCommonService budgetCommonService;
    private ProposalDevelopmentService proposalDevelopmentService;
    
    @Before
    public void setUp() throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        budgetCommonService = getService(ProposalBudgetService.class);
        proposalDevelopmentService = getService(ProposalDevelopmentService.class);
    }
    
    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
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
        String kualiSessionId = currentSession.getKualiSessionId();
        if (kualiSessionId == null) {
            kualiSessionId = UUID.randomUUID().toString();
            currentSession.setKualiSessionId(kualiSessionId);
        }
        PessimisticLock lock = KRADServiceLocatorWeb.getPessimisticLockService().generateNewLock(pdDocument.getDocumentNumber(), pdDocument.getDocumentNumber()+"-BUDGET", currentSession.getPerson());
        
        pdDocument.addPessimisticLock(lock);
        
        Budget budget = budgetCommonService.getNewBudgetVersion(pdDocument, testDocumentDescription, Collections.EMPTY_MAP);
        
        // Verify that status is final
//        assertTrue(budgetDocument.getDocumentHeader().getWorkflowDocument().isApproved());
        //assertEquals(KRADConstants.DocumentStatusCodes.APPROVED, budgetDocument.getDocumentHeader().getDocumentStatusCode());
        
        // Verify that fields were set properly
        assertEquals(((DevelopmentProposal)budget.getBudgetParent()).getProposalNumber(), testProposalNumber);
//        assertEquals(budgetDocument.getDocumentHeader().getDocumentDescription(), testDocumentDescription);
    }
    
    /**
     * Test copyBudgetVersion method
     * 
     * @throws Exception
     */
    @Test @Ignore("KCINFR-983")
    public void testCopyBudgetVersion() throws Exception {
        
        ProposalDevelopmentDocument pdDocument = getPersistedProposalDevelopmentDocument();
        
        String testProposalNumber = pdDocument.getDevelopmentProposal().getProposalNumber();
        String testDocumentDescription = "Test Copy Budget Doc";
        
        Budget budgetDocument = budgetCommonService.getNewBudgetVersion(pdDocument, testDocumentDescription, Collections.EMPTY_MAP);
        
        Budget copyBudgetDocument = budgetCommonService.copyBudgetVersion(budgetDocument);
        
        // Verify that fields were set properly
        assertEquals(((DevelopmentProposal)copyBudgetDocument.getBudgetParent()).getProposalNumber(), testProposalNumber);
        assertEquals(copyBudgetDocument.getName(), testDocumentDescription);
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
            (ProposalDevelopmentDocument) KRADServiceLocatorWeb.getDocumentService().getNewDocument(ProposalDevelopmentDocument.class);
        
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

        pdDocument = (ProposalDevelopmentDocument) KRADServiceLocatorWeb.getDocumentService().saveDocument(pdDocument);
        
        pdDocument.getDevelopmentProposal().refreshReferenceObject("ownedByUnit");
        
        PersonService personService = getService(PersonService.class);
        Person user = personService.getPersonByPrincipalName("quickstart");
        
        getService(KcAuthorizationService.class).addRole(user.getPrincipalId(), RoleConstants.AGGREGATOR, pdDocument);
        
        return pdDocument;
    }
    
}

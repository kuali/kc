/*
 * Copyright 2005-2013 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.budget.core.BudgetService;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.test.fixtures.ProposalDevelopmentDocumentFixture;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;

import static org.junit.Assert.*;
public class ProposalDevelopmentServiceTest extends KcIntegrationTestBase {
    
    private ProposalDevelopmentService proposalDevelopmentService;
    private BudgetService budgetService;
    private ProposalDevelopmentDocument document;

    @Before
    public void setUp() throws Exception {
        proposalDevelopmentService = KraServiceLocator.getService(ProposalDevelopmentService.class);
        budgetService = KraServiceLocator.getService(BudgetService.class);
        document = ProposalDevelopmentDocumentFixture.NORMAL_DOCUMENT.getDocument();
        document.getDevelopmentProposal().setPrimeSponsorCode("000120");
    }
    
    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void testDeleteProposal() throws WorkflowException {
        KRADServiceLocatorWeb.getDocumentService().saveDocument(document);
        proposalDevelopmentService.deleteProposal(document);
        document = (ProposalDevelopmentDocument) KRADServiceLocatorWeb.getDocumentService().getByDocumentHeaderId(document.getDocumentNumber());
        assertTrue(document.isProposalDeleted());
        assertTrue(document.getDevelopmentProposal().getTitle() == null);
    }
    
    @Test
    public void testDeleteProposalWithBudget() throws WorkflowException {
        BudgetDocument<DevelopmentProposal> budget1 = budgetService.addBudgetVersion(document, "Ver1");
        BudgetDocument<DevelopmentProposal> budget2 = budgetService.addBudgetVersion(document, "Ver2 With Long Name");
        KRADServiceLocatorWeb.getDocumentService().saveDocument(document);
        proposalDevelopmentService.deleteProposal(document);
        document = (ProposalDevelopmentDocument) KRADServiceLocatorWeb.getDocumentService().getByDocumentHeaderId(document.getDocumentNumber());
        assertTrue(document.isProposalDeleted());
        assertTrue(document.getDevelopmentProposal().getTitle() == null);
        budget1 = (BudgetDocument) KRADServiceLocatorWeb.getDocumentService().getByDocumentHeaderId(budget1.getDocumentNumber());
        budget2 = (BudgetDocument) KRADServiceLocatorWeb.getDocumentService().getByDocumentHeaderId(budget2.getDocumentNumber());
        assertTrue(budget1.isBudgetDeleted());
        assertTrue(budget2.isBudgetDeleted());
        assertTrue(budget1.getBudget().getName() == null);
        assertTrue(budget2.getBudget().getName() == null);
    }

}

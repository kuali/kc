/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.proposaldevelopment.service;

import java.util.Collections;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.propdev.impl.budget.ProposalBudgetService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetService;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.kra.test.fixtures.ProposalDevelopmentDocumentFixture;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.KRADServiceLocator;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
public class ProposalDevelopmentServiceTest extends KcIntegrationTestBase {
    
    private ProposalDevelopmentService proposalDevelopmentService;
    private BudgetService<DevelopmentProposal> budgetService;
    private ProposalDevelopmentDocument document;

    @Before
    public void setUp() throws Exception {
        proposalDevelopmentService = KcServiceLocator.getService(ProposalDevelopmentService.class);
        budgetService = KcServiceLocator.getService(ProposalBudgetService.class);
        document = ProposalDevelopmentDocumentFixture.NORMAL_DOCUMENT.getDocument();
        document.getDevelopmentProposal().setPrimeSponsorCode("000120");
        document.getDevelopmentProposal().setSponsorCode("000120");
    }
    
    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void testDeleteProposal() throws WorkflowException {
        document = (ProposalDevelopmentDocument) KRADServiceLocatorWeb.getDocumentService().saveDocument(document);
        document = proposalDevelopmentService.deleteProposal(document);
        document = (ProposalDevelopmentDocument) KRADServiceLocatorWeb.getDocumentService().getByDocumentHeaderId(document.getDocumentNumber());
        assertTrue(document.isProposalDeleted());
        assertTrue(document.getDevelopmentProposal() == null);
    }
    
    @Test
    public void testDeleteProposalWithBudget() throws WorkflowException {
        document = (ProposalDevelopmentDocument) KRADServiceLocatorWeb.getDocumentService().saveDocument(document);
        Budget budget1 = budgetService.addBudgetVersion(document, "Ver1", Collections.<String, Object>emptyMap());
        Budget budget2 = budgetService.addBudgetVersion(document, "Ver2 With Long Name", Collections.<String, Object>emptyMap());
        document = (ProposalDevelopmentDocument) KRADServiceLocatorWeb.getDocumentService().saveDocument(document);
        document = proposalDevelopmentService.deleteProposal(document);
        document = (ProposalDevelopmentDocument) KRADServiceLocatorWeb.getDocumentService().getByDocumentHeaderId(document.getDocumentNumber());
        assertTrue(document.isProposalDeleted());
        assertTrue(document.getDevelopmentProposal() == null);
        budget1 = KRADServiceLocator.getDataObjectService().findUnique(Budget.class, QueryByCriteria.Builder.forAttribute("budgetId", budget1.getBudgetId()).build());
        budget2 = KRADServiceLocator.getDataObjectService().findUnique(Budget.class, QueryByCriteria.Builder.forAttribute("budgetId", budget2.getBudgetId()).build());
        assertNull(budget1);
        assertNull(budget2);
    }

}

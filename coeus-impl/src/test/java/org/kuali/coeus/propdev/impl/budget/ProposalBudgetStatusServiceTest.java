/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.propdev.impl.budget;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.rice.krad.data.DataObjectService;

public class ProposalBudgetStatusServiceTest {
	private ProposalBudgetStatusServiceImpl proposalBudgetStatusService;
	private Mockery context;
	private ProposalDevelopmentDocument proposalDocument;
	private DataObjectService dataObjectService;

	@Before
	public void setUp() throws Exception {
		context = new JUnit4Mockery() {
			{
				setThreadingPolicy(new Synchroniser());
			}
		};
		proposalBudgetStatusService = new ProposalBudgetStatusServiceImpl();
		dataObjectService = context.mock(DataObjectService.class);
		proposalDocument = createProposal();
	}

	private ProposalDevelopmentDocument createProposal() {
		ProposalDevelopmentDocument document = new ProposalDevelopmentDocument();
		document.getDevelopmentProposal().setProposalNumber("111");
		document.getDevelopmentProposal().setBudgetStatus("test");
		return document;
	}

	@Test
	public void test_saveBudgetFinalVersionStatus() {
		final ProposalBudgetStatus proposalBudgetStatus = new ProposalBudgetStatus();
		context.checking(new Expectations() {
			{
				oneOf(dataObjectService)
						.find(ProposalBudgetStatus.class, "111");
				will(returnValue(proposalBudgetStatus));
				oneOf(dataObjectService).save(proposalBudgetStatus);
				will(returnValue(proposalBudgetStatus));
			}
		});
		proposalBudgetStatusService.setDataObjectService(dataObjectService);
		proposalBudgetStatusService
				.saveBudgetFinalVersionStatus(proposalDocument);
	}

	@Test(expected = NullPointerException.class)
	public void test_saveBudgetFinalVersionStatus_null_document() {
		proposalBudgetStatusService.saveBudgetFinalVersionStatus(null);
	}

	@Test
	public void test_loadBudgetStatus_with_proposal() {
		final ProposalBudgetStatus proposalBudgetStatus = new ProposalBudgetStatus();
		context.checking(new Expectations() {
			{
				oneOf(dataObjectService)
						.find(ProposalBudgetStatus.class, "111");
				will(returnValue(proposalBudgetStatus));
			}
		});
		proposalBudgetStatusService.setDataObjectService(dataObjectService);
		proposalBudgetStatusService.loadBudgetStatus(proposalDocument
				.getDevelopmentProposal());
	}

	@Test
	public void test_loadBudgetStatus_with_null_proposal() {
		proposalBudgetStatusService.setDataObjectService(dataObjectService);
		proposalBudgetStatusService.loadBudgetStatus(null);
	}

	@Test
	public void test_loadBudgetStatusByProposalDocumentNumber() {
		final DevelopmentProposal developmentProposal = proposalDocument
				.getDevelopmentProposal();
		final ProposalBudgetStatus proposalBudgetStatus = new ProposalBudgetStatus();
		context.checking(new Expectations() {
			{
				oneOf(dataObjectService).find(DevelopmentProposal.class, "111");
				will(returnValue(developmentProposal));
				oneOf(dataObjectService)
						.find(ProposalBudgetStatus.class, "111");
				will(returnValue(proposalBudgetStatus));
			}
		});
		proposalBudgetStatusService.setDataObjectService(dataObjectService);
		proposalBudgetStatusService
				.loadBudgetStatusByProposalDocumentNumber("111");
	}

	@Test
	public void test_loadBudgetStatusByProposalDocumentNumber_with_null_document() {
		final DevelopmentProposal developmentProposal = null;
		final ProposalBudgetStatus proposalBudgetStatus = null;
		context.checking(new Expectations() {
			{
				oneOf(dataObjectService).find(DevelopmentProposal.class, null);
				will(returnValue(developmentProposal));
				oneOf(dataObjectService).find(ProposalBudgetStatus.class, null);
				will(returnValue(proposalBudgetStatus));
			}
		});
		proposalBudgetStatusService.setDataObjectService(dataObjectService);
		proposalBudgetStatusService
				.loadBudgetStatusByProposalDocumentNumber(null);
	}
}

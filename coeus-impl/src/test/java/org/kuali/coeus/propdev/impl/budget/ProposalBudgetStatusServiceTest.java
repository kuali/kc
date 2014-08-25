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

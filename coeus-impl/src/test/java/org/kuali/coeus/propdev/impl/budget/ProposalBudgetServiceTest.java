package org.kuali.coeus.propdev.impl.budget;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.budget.framework.version.BudgetVersionOverview;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.DocumentService;

public class ProposalBudgetServiceTest {
	private ProposalBudgetServiceImpl proposalBudgetService = null;
	private Mockery context;
	private DocumentService docService = null;

	@Before
	public void setUp() throws Exception {
		context = new JUnit4Mockery() {
			{
				setThreadingPolicy(new Synchroniser());
			}
		};
		proposalBudgetService = new ProposalBudgetServiceImpl();
		docService = context.mock(DocumentService.class);
	}

	@Test
	public void test_getFinalBudgetVersion_noBudgetDoc()
			throws WorkflowException {
		final ProposalDevelopmentDocument proposalDevelopmentDocument = new ProposalDevelopmentDocument();
		ProposalDevelopmentBudgetExt finalBudget = null;
		proposalDevelopmentDocument.getDevelopmentProposal().setFinalBudget(
				finalBudget);

		proposalBudgetService.setDocumentService(docService);
		ProposalDevelopmentBudgetExt assertBudgetDocument = null;
		assertBudgetDocument = proposalBudgetService
				.getFinalBudgetVersion(proposalDevelopmentDocument);
		assertNull(assertBudgetDocument);
	}

	@Test
	public void test_getFinalBudgetVersion_no_docHeaderId()
			throws WorkflowException {
		final ProposalDevelopmentDocument proposalDevelopmentDocument = new ProposalDevelopmentDocument();
		ProposalDevelopmentBudgetExt finalBudget = new ProposalDevelopmentBudgetExt();
		proposalDevelopmentDocument.getDevelopmentProposal().setFinalBudget(
				finalBudget);
		context.checking(new Expectations() {
			{
				oneOf(docService).getByDocumentHeaderId(null);
				will(returnValue(null));
			}
		});
		proposalBudgetService.setDocumentService(docService);
		ProposalDevelopmentBudgetExt assertBudgetDocument = null;
		assertBudgetDocument = proposalBudgetService
				.getFinalBudgetVersion(proposalDevelopmentDocument);
		assertNotNull(assertBudgetDocument);
	}

	@Test
	public void test_getFinalBudgetVersion_withoutFinalBudgetVersion()
			throws WorkflowException {
		final BudgetVersionOverview budgetVersionOverview = new BudgetVersionOverview();
		budgetVersionOverview.setDocumentNumber("1234");
		final ProposalDevelopmentDocument proposalDevelopmentDocument = new ProposalDevelopmentDocument();
		final ProposalDevelopmentBudgetExt budgetDocument = null;
		context.checking(new Expectations() {
			{
				oneOf(docService).getByDocumentHeaderId("1234");
				will(returnValue(budgetDocument));
			}
		});
		proposalBudgetService.setDocumentService(docService);
		ProposalDevelopmentBudgetExt assertBudgetDocument = null;
		assertBudgetDocument = proposalBudgetService
				.getFinalBudgetVersion(proposalDevelopmentDocument);
		assertNull(assertBudgetDocument);
	}

	@Test(expected = NullPointerException.class)
	public void test_getFinalBudgetVersion_no_argument()
			throws WorkflowException {
		proposalBudgetService.getFinalBudgetVersion(null);
	}
}

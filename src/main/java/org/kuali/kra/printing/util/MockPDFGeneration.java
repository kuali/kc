package org.kuali.kra.printing.util;

import java.util.HashMap;
import java.util.Map;

import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.printing.AwardPrintType;
import org.kuali.kra.award.printing.service.AwardPrintingService;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.printing.BudgetPrintType;
import org.kuali.kra.budget.printing.service.BudgetPrintingService;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.printing.InstitutionalProposalPrintType;
import org.kuali.kra.institutionalproposal.printing.service.InstitutionalProposalPrintingService;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.service.PrintService;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.util.GlobalVariables;

public class MockPDFGeneration {
	public void testAwardReports(String docId, String seqtran) {
		DocumentService documentService = KraServiceLocator
				.getService(DocumentService.class);
		AwardPrintingService awardPrintingService = KraServiceLocator
				.getService(AwardPrintingService.class);
		String sequenceNo = seqtran.substring(0, seqtran.indexOf("_"));
		String transactionId = seqtran.substring(seqtran.indexOf("_") + 1);

		GlobalVariables.setUserSession(new UserSession("quickstart"));
		System.out.println("Generating report for Document:" + docId);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sequenceNumber", Integer.parseInt(sequenceNo));
		params.put("transactionId", Integer.parseInt(transactionId));
		params.putAll(PrintingTestUtils.getAwardNoticeReportParameters());
		AwardDocument awardDoc = null;
		try {
			awardDoc = (AwardDocument) documentService
					.getByDocumentHeaderId(docId);

		} catch (WorkflowException e) {
			e.printStackTrace();
		}
		try {
			AttachmentDataSource pdfBytes = awardPrintingService
					.printAwardReport(awardDoc,
							AwardPrintType.AWARD_DELTA_REPORT
									.getAwardPrintType(), params);
			PrintingTestUtils.writePdftoDisk(pdfBytes,
					AwardPrintType.AWARD_DELTA_REPORT.getAwardPrintType());
			AttachmentDataSource pdfBytes1 = awardPrintingService
					.printAwardReport(awardDoc,
							AwardPrintType.AWARD_NOTICE_REPORT
									.getAwardPrintType(), params);
			PrintingTestUtils.writePdftoDisk(pdfBytes1,
					AwardPrintType.AWARD_NOTICE_REPORT.getAwardPrintType());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testBudgetReports(String pdDocId) {
		DocumentService documentService = KraServiceLocator
				.getService(DocumentService.class);
		BudgetPrintingService budgetPrintingService = KraServiceLocator
				.getService(BudgetPrintingService.class);

		GlobalVariables.setUserSession(new UserSession("quickstart"));
		System.out.println("Generating report for Document:" + pdDocId);
		ProposalDevelopmentDocument pdDoc = null;
		BudgetDocument budgetDoc = null;
		try {
			pdDoc = (ProposalDevelopmentDocument) documentService
					.getByDocumentHeaderId(pdDocId);
			budgetDoc = (BudgetDocument) documentService
					.getByDocumentHeaderId(pdDoc.getBudgetDocumentVersion(0)
							.getDocumentNumber());
		} catch (Exception e1) {
			System.out.println("Exception while generating PDF");
			e1.printStackTrace();
		}

		// testBudgetSummaryReportPrinting
		try {
			AttachmentDataSource pdfBytes = budgetPrintingService
					.printBudgetReport(budgetDoc,
							BudgetPrintType.BUDGET_SUMMARY_REPORT
									.getBudgetPrintType(), PrintingTestUtils
									.getBudgetSummaryReportParameters());
			PrintingTestUtils.writePdftoDisk(pdfBytes,
					BudgetPrintType.BUDGET_SUMMARY_REPORT.getBudgetPrintType());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// testBudgetTotalReportPrinting
		try {
			AttachmentDataSource pdfBytes = budgetPrintingService
					.printBudgetReport(budgetDoc,
							BudgetPrintType.BUDGET_TOTAL_REPORT
									.getBudgetPrintType(), PrintingTestUtils
									.getBudgetTotalReportParameters());
			PrintingTestUtils.writePdftoDisk(pdfBytes,
					BudgetPrintType.BUDGET_TOTAL_REPORT.getBudgetPrintType());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// testBudgetSummaryTotalReportPrinting
		try {
			AttachmentDataSource pdfBytes = budgetPrintingService
					.printBudgetReport(budgetDoc,
							BudgetPrintType.BUDGET_SUMMARY_TOTAL_REPORT
									.getBudgetPrintType(), PrintingTestUtils
									.getBudgetSummaryTotalReportParameters());
			PrintingTestUtils.writePdftoDisk(pdfBytes,
					BudgetPrintType.BUDGET_SUMMARY_TOTAL_REPORT
							.getBudgetPrintType());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// testIndustrialCumulativeBudgetReportPrinting
		try {
			AttachmentDataSource pdfBytes = budgetPrintingService
					.printBudgetReport(
							budgetDoc,
							BudgetPrintType.INDUSTRIAL_CUMULATIVE_BUDGET_REPORT
									.getBudgetPrintType(),
							PrintingTestUtils
									.getIndustrialCumulativeBudgetReportParameters());
			PrintingTestUtils.writePdftoDisk(pdfBytes,
					BudgetPrintType.INDUSTRIAL_CUMULATIVE_BUDGET_REPORT
							.getBudgetPrintType());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// testBudgetSalaryReportPrinting
		try {
			AttachmentDataSource pdfBytes = budgetPrintingService
					.printBudgetReport(budgetDoc,
							BudgetPrintType.BUDGET_SALARY_REPORT
									.getBudgetPrintType(), PrintingTestUtils
									.getBudgetSalaryReportParameters());
			PrintingTestUtils.writePdftoDisk(pdfBytes,
					BudgetPrintType.BUDGET_SALARY_REPORT.getBudgetPrintType());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// testBudgetCumulativeReportPrinting
		try {
			AttachmentDataSource pdfBytes = budgetPrintingService
					.printBudgetReport(budgetDoc,
							BudgetPrintType.BUDGET_CUMULATIVE_REPORT
									.getBudgetPrintType(), PrintingTestUtils
									.getBudgetCumulativeReportParameters());
			PrintingTestUtils.writePdftoDisk(pdfBytes,
					BudgetPrintType.BUDGET_CUMULATIVE_REPORT
							.getBudgetPrintType());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// testIndustrialBudgetReportPrinting
		try {
			AttachmentDataSource pdfBytes = budgetPrintingService
					.printBudgetReport(budgetDoc,
							BudgetPrintType.INDUSTRIAL_BUDGET_REPORT
									.getBudgetPrintType(), PrintingTestUtils
									.getIndustrialBudgetReportParameters());
			PrintingTestUtils.writePdftoDisk(pdfBytes,
					BudgetPrintType.INDUSTRIAL_BUDGET_REPORT
							.getBudgetPrintType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// testCostSharingSummaryByPeriodReportPrinting
		try {
			AttachmentDataSource pdfBytes = budgetPrintingService
					.printBudgetReport(
							budgetDoc,
							BudgetPrintType.BUDGET_COST_SHARE_SUMMARY_REPORT
									.getBudgetPrintType(),
							PrintingTestUtils
									.getCostSharingSummaryByPeriodReportParameters());
			PrintingTestUtils.writePdftoDisk(pdfBytes,
					BudgetPrintType.BUDGET_COST_SHARE_SUMMARY_REPORT
							.getBudgetPrintType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("done");
	}

	public void testInstituteProposalreports(String pdDocId) {
		DocumentService documentService = KraServiceLocator
				.getService(DocumentService.class);
		InstitutionalProposalPrintingService ipService = KraServiceLocator
				.getService(InstitutionalProposalPrintingService.class);

		GlobalVariables.setUserSession(new UserSession("quickstart"));
		System.out.println("Generating report for Document:" + pdDocId);
		InstitutionalProposalDocument ipDoc = (InstitutionalProposalDocument) PrintingTestUtils.getInstituteProposalDocument();
//		try {
//			ipDoc = (InstitutionalProposalDocument) documentService
//					.getByDocumentHeaderId(pdDocId);
//		} catch (Exception e1) {
//			System.out.println("Exception while generating PDF");
//			e1.printStackTrace();
//		}

		// testCurrentProposalReportPrinting
		try {
			AttachmentDataSource pdfBytes = ipService
					.printInstitutionalProposalReport(ipDoc,
							InstitutionalProposalPrintType.CURRENT_REPORT
									.getInstitutionalProposalPrintType(),
							PrintingTestUtils
									.getBudgetSummaryReportParameters());
			PrintingTestUtils.writePdftoDisk(pdfBytes,
					InstitutionalProposalPrintType.CURRENT_REPORT
					.getInstitutionalProposalPrintType());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// testPendingProposalReportPrinting
		try {
			AttachmentDataSource pdfBytes = ipService
					.printInstitutionalProposalReport(ipDoc,
							InstitutionalProposalPrintType.PENDING_REPORT
									.getInstitutionalProposalPrintType(),
							PrintingTestUtils.getBudgetTotalReportParameters());
			PrintingTestUtils.writePdftoDisk(pdfBytes,
					InstitutionalProposalPrintType.PENDING_REPORT
					.getInstitutionalProposalPrintType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// testInstituteProposalReportPrinting
		try {
			AttachmentDataSource pdfBytes = ipService
					.printInstitutionalProposalReport(ipDoc,
							InstitutionalProposalPrintType.INSTITUTIONAL_PROPOSAL_REPORT
									.getInstitutionalProposalPrintType(),
							PrintingTestUtils.getBudgetTotalReportParameters());
			PrintingTestUtils.writePdftoDisk(pdfBytes,
					InstitutionalProposalPrintType.INSTITUTIONAL_PROPOSAL_REPORT
					.getInstitutionalProposalPrintType());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testReports(String pdDocId, String id) {
		testInstituteProposalreports(pdDocId);
	}

	public static void main(String[] args) {
		MockPDFGeneration mockPDF = new MockPDFGeneration();
		//mockPDF.testInstituteProposalreports("3687");
		mockPDF.printS2SForms("4179");
	}

	public void printS2SForms(String pdDocId) {
		DocumentService documentService = KraServiceLocator
				.getService(DocumentService.class);
		GlobalVariables.setUserSession(new UserSession("quickstart"));
		System.out.println("Generating report for Document:" + pdDocId);
		ProposalDevelopmentDocument pdDoc = null;
		try {
			pdDoc = (ProposalDevelopmentDocument) documentService
					.getByDocumentHeaderId(pdDocId);
			PrintService printService = KraServiceLocator
					.getService(PrintService.class);
			AttachmentDataSource ads = printService.printForm(pdDoc);
			PrintingTestUtils.writePdftoDisk(ads, "S2S");
		} catch (Exception e1) {
			System.out.println("Exception while generating PDF");
			e1.printStackTrace();
		}
	}
}

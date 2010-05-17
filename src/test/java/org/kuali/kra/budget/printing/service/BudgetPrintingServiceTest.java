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
package org.kuali.kra.budget.printing.service;

import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.printing.BudgetPrintType;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.printing.util.PrintingServiceTestBase;
import org.kuali.kra.printing.util.PrintingTestUtils;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;

/**
 * This class tests BudgetPrintingService. It tests all reports that are part of
 * Budget printing.
 * 
 */
@org.junit.Ignore("This test is not meant to be run against the 2.0 release")
public class BudgetPrintingServiceTest extends PrintingServiceTestBase {
	private BudgetPrintingService budgetPrintingService;
	private BudgetDocument budgetDoc;

	/**
	 * This method tests Budget Summary Report. It generates PDF bytes for the
	 * report.
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp();
		budgetDoc = (BudgetDocument) PrintingTestUtils.getBudgetDocument();
	}

	@Test
	public void testBudgetSummaryReportPrinting() {
		try {
			AttachmentDataSource pdfBytes = getPrintingService()
					.printBudgetReport(
							budgetDoc,
							BudgetPrintType.BUDGET_SUMMARY_REPORT
									.getBudgetPrintType(),
							PrintingTestUtils
									.getBudgetSummaryReportParameters());
			assertNotNull(pdfBytes);
		} catch (Exception e) {
			e.printStackTrace();
			//assert false;
			throw new RuntimeException(e);
		}
	}

	/**
	 * This method tests Budget Total Report. It generates PDF bytes for the
	 * report.
	 */
	@Test
	public void testBudgetTotalReportPrinting() {
		try {
			AttachmentDataSource pdfBytes = getPrintingService()
					.printBudgetReport(
							budgetDoc,
							BudgetPrintType.BUDGET_TOTAL_REPORT
									.getBudgetPrintType(),
							PrintingTestUtils.getBudgetTotalReportParameters());
			assertNotNull(pdfBytes);
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}

	/**
	 * This method tests Budget Summary Total Report. It generates PDF bytes for
	 * the report.
	 */
	@Test
	public void testBudgetSummaryTotalReportPrinting() {
		try {
			AttachmentDataSource pdfBytes = getPrintingService()
					.printBudgetReport(
							budgetDoc,
							BudgetPrintType.BUDGET_SUMMARY_TOTAL_REPORT
									.getBudgetPrintType(),
							PrintingTestUtils
									.getBudgetSummaryTotalReportParameters());
			assertNotNull(pdfBytes);
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}

	/**
	 * This method tests Budget Industrial Cumulative Budget Total Report. It
	 * generates PDF bytes for the report.
	 */
	@Test
	public void testIndustrialCumulativeBudgetReportPrinting() {
		try {
			AttachmentDataSource pdfBytes = getPrintingService()
					.printBudgetReport(
							budgetDoc,
							BudgetPrintType.INDUSTRIAL_CUMULATIVE_BUDGET_REPORT
									.getBudgetPrintType(),
							PrintingTestUtils
									.getIndustrialCumulativeBudgetReportParameters());
			assertNotNull(pdfBytes);
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}

	/**
	 * This method tests Budget Salary Report. It generates PDF bytes for the
	 * report.
	 */
	@Test
	public void testBudgetSalaryReportPrinting() {
		try {
			AttachmentDataSource pdfBytes = getPrintingService()
					.printBudgetReport(
							budgetDoc,
							BudgetPrintType.BUDGET_SALARY_REPORT
									.getBudgetPrintType(),
							PrintingTestUtils.getBudgetSalaryReportParameters());
			assertNotNull(pdfBytes);
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}

	/**
	 * This method tests Budget Cumulative Report. It generates PDF bytes for
	 * the report.
	 */
	@Test
	public void testBudgetCumulativeReportPrinting() {
		try {
			AttachmentDataSource pdfBytes = getPrintingService()
					.printBudgetReport(
							budgetDoc,
							BudgetPrintType.BUDGET_CUMULATIVE_REPORT
									.getBudgetPrintType(),
							PrintingTestUtils
									.getBudgetCumulativeReportParameters());
			assertNotNull(pdfBytes);
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}

	/**
	 * This method tests Industrial Budget Report. It generates PDF bytes for
	 * the report.
	 */
	@Test
	public void testIndustrialBudgetReportPrinting() {
		try {
			AttachmentDataSource pdfBytes = getPrintingService()
					.printBudgetReport(
							budgetDoc,
							BudgetPrintType.INDUSTRIAL_BUDGET_REPORT
									.getBudgetPrintType(),
							PrintingTestUtils
									.getIndustrialBudgetReportParameters());
			assertNotNull(pdfBytes);
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}

	/**
	 * This method tests Industrial Cost Sharing summary by period Report. It
	 * generates PDF bytes for the report.
	 */
	@Test
	public void testCostSharingSummaryByPeriodReportPrinting() {
		try {
			AttachmentDataSource pdfBytes = getPrintingService()
					.printBudgetReport(
							budgetDoc,
							BudgetPrintType.BUDGET_COST_SHARE_SUMMARY_REPORT
									.getBudgetPrintType(),
							PrintingTestUtils
									.getCostSharingSummaryByPeriodReportParameters());
			assertNotNull(pdfBytes);
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}

	private BudgetPrintingService getPrintingService() {
		if (budgetPrintingService == null) {
			budgetPrintingService = KraServiceLocator
					.getService(BudgetPrintingService.class);
		}
		return budgetPrintingService;
	}

}

/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.budget.printing.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.printing.BudgetPrintType;
import org.kuali.kra.budget.printing.print.BudgetCostShareSummaryPrint;
import org.kuali.kra.budget.printing.print.BudgetCumulativePrint;
import org.kuali.kra.budget.printing.print.BudgetSalaryPrint;
import org.kuali.kra.budget.printing.print.BudgetSummaryPrint;
import org.kuali.kra.budget.printing.print.BudgetSummaryTotalPrint;
import org.kuali.kra.budget.printing.print.BudgetTotalPrint;
import org.kuali.kra.budget.printing.print.IndustrialBudgetPrint;
import org.kuali.kra.budget.printing.print.IndustrialCumulativeBudgetPrint;
import org.kuali.kra.budget.printing.service.BudgetPrintingService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.printing.print.AbstractPrint;
import org.kuali.kra.printing.service.PrintingService;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.proposaldevelopment.budget.bo.BudgetPrintForm;
import org.kuali.kra.proposaldevelopment.budget.service.BudgetPrintService;

/**
 * This class is the implementation of {@link BudgetPrintingService}. It has
 * capability to print any reports related to Budget like Budget Summary,
 * Cost-Share Summary etc.
 * 
 */

public class BudgetPrintingServiceImpl implements BudgetPrintService {
	private BudgetSummaryPrint budgetSummaryPrint;
	private BudgetCostShareSummaryPrint budgetCostShareSummaryPrint;
	private BudgetSalaryPrint budgetSalaryPrint;
	private PrintingService printingService;
	private BudgetTotalPrint budgetTotalPrint;
	private BudgetSummaryTotalPrint budgetSummaryTotalPrint;
	private IndustrialCumulativeBudgetPrint industrialCumulativeBudgetPrint;
	private IndustrialBudgetPrint industrialBudgetPrint;
	private BudgetCumulativePrint budgetCumulativePrint;
	private static final Log LOG = LogFactory.getLog(BudgetPrintingServiceImpl.class);

	/**
	 * This method generates the required report and returns the PDF stream as
	 * {@link AttachmentDataSource}. It first identifies the report type to be
	 * printed, then fetches the required report generator. The report generator
	 * generates XML which is then passed to {@link PrintingService} for
	 * transforming into PDF.
	 * 
	 * @param budgetDocument
	 *            Award data using which report is generated
	 * @param reportName
	 *            report to be generated
	 * @param reportParameters
	 *            {@link Map} of parameters required for report generation
	 * @return {@link AttachmentDataSource} which contains the byte array of the
	 *         generated PDF
	 * @throws PrintingException
	 *             if any errors occur during report generation
	 * 
	 */
	public AttachmentDataSource printBudgetReport(
	        KraPersistableBusinessObjectBase budget, String reportName)
			throws PrintingException {
		AttachmentDataSource attachmentDataSource = null;
		AbstractPrint printable = null;
		String fileName = reportName+"-"+((Budget)budget).getBudgetDocument().getParentDocumentKey()+Constants.PDF_FILE_EXTENSION;
		if (reportName.equals(BudgetPrintType.BUDGET_SUMMARY_REPORT
				.getBudgetPrintType())) {
			printable = getBudgetSummaryPrint();
		} else if (reportName
				.equals(BudgetPrintType.BUDGET_COST_SHARE_SUMMARY_REPORT
						.getBudgetPrintType())) {
			printable = getBudgetCostShareSummaryPrint();
		} else if (reportName
				.equals(BudgetPrintType.INDUSTRIAL_CUMULATIVE_BUDGET_REPORT
						.getBudgetPrintType())) {
			printable = getIndustrialCumulativeBudgetPrint();
		} else if (reportName.equals(BudgetPrintType.BUDGET_SALARY_REPORT
				.getBudgetPrintType())) {
			printable = getBudgetSalaryPrint();
		} else if (reportName.equals(BudgetPrintType.BUDGET_TOTAL_REPORT
				.getBudgetPrintType())) {
			printable = getBudgetTotalPrint();
		} else if (reportName
				.equals(BudgetPrintType.BUDGET_SUMMARY_TOTAL_REPORT
						.getBudgetPrintType())) {
			printable = getBudgetSummaryTotalPrint();
		} else if (reportName.equals(BudgetPrintType.BUDGET_CUMULATIVE_REPORT
				.getBudgetPrintType())) {
			printable = getBudgetCumulativePrint();
		} else if (reportName.equals(BudgetPrintType.INDUSTRIAL_BUDGET_REPORT
				.getBudgetPrintType())) {
			printable = getIndustrialBudgetPrint();
		}
		printable.setPrintableBusinessObject(budget);
		attachmentDataSource = getPrintingService().print(printable);
		try {
            attachmentDataSource.setFileName(URLEncoder.encode(fileName,"UTF-8"));
        }
        catch (UnsupportedEncodingException e) {
            attachmentDataSource.setFileName(fileName);
        }
		attachmentDataSource.setContentType(Constants.PDF_REPORT_CONTENT_TYPE);
		
		return attachmentDataSource;
	}

	/**
	 * Populates the various forms that are part of Budget on UI
	 * 
	 * @param budget
	 */
	public void populateBudgetPrintForms(Budget budget) {
		BudgetPrintForm budgetCostShareForm = new BudgetPrintForm();
		budgetCostShareForm
				.setBudgetReportId(BudgetPrintType.BUDGET_COST_SHARE_SUMMARY_REPORT
						.getBudgetPrintType());
		budgetCostShareForm
				.setBudgetReportName(BudgetPrintType.BUDGET_COST_SHARE_SUMMARY_REPORT
						.getBudgetPrintType());

		BudgetPrintForm budgetCumulativeForm = new BudgetPrintForm();
		budgetCumulativeForm
				.setBudgetReportId(BudgetPrintType.BUDGET_CUMULATIVE_REPORT
						.getBudgetPrintType());
		budgetCumulativeForm
				.setBudgetReportName(BudgetPrintType.BUDGET_CUMULATIVE_REPORT
						.getBudgetPrintType());

		BudgetPrintForm budgetSalaryForm = new BudgetPrintForm();
		budgetSalaryForm.setBudgetReportId(BudgetPrintType.BUDGET_SALARY_REPORT
				.getBudgetPrintType());
		budgetSalaryForm
				.setBudgetReportName(BudgetPrintType.BUDGET_SALARY_REPORT
						.getBudgetPrintType());

		BudgetPrintForm budgetSummaryForm = new BudgetPrintForm();
		budgetSummaryForm
				.setBudgetReportId(BudgetPrintType.BUDGET_SUMMARY_REPORT
						.getBudgetPrintType());
		budgetSummaryForm
				.setBudgetReportName(BudgetPrintType.BUDGET_SUMMARY_REPORT
						.getBudgetPrintType());

		BudgetPrintForm budgetSummaryTotalForm = new BudgetPrintForm();
		budgetSummaryTotalForm
				.setBudgetReportId(BudgetPrintType.BUDGET_SUMMARY_TOTAL_REPORT
						.getBudgetPrintType());
		budgetSummaryTotalForm
				.setBudgetReportName(BudgetPrintType.BUDGET_SUMMARY_TOTAL_REPORT
						.getBudgetPrintType());

		BudgetPrintForm budgetTotalForm = new BudgetPrintForm();
		budgetTotalForm.setBudgetReportId(BudgetPrintType.BUDGET_TOTAL_REPORT
				.getBudgetPrintType());
		budgetTotalForm.setBudgetReportName(BudgetPrintType.BUDGET_TOTAL_REPORT
				.getBudgetPrintType());

		BudgetPrintForm industrialBudgetForm = new BudgetPrintForm();
		industrialBudgetForm
				.setBudgetReportId(BudgetPrintType.INDUSTRIAL_BUDGET_REPORT
						.getBudgetPrintType());
		industrialBudgetForm
				.setBudgetReportName(BudgetPrintType.INDUSTRIAL_BUDGET_REPORT
						.getBudgetPrintType());

		BudgetPrintForm industrialBudgetCumulativeForm = new BudgetPrintForm();
		industrialBudgetCumulativeForm
				.setBudgetReportId(BudgetPrintType.INDUSTRIAL_CUMULATIVE_BUDGET_REPORT
						.getBudgetPrintType());
		industrialBudgetCumulativeForm
				.setBudgetReportName(BudgetPrintType.INDUSTRIAL_CUMULATIVE_BUDGET_REPORT
						.getBudgetPrintType());

		List<BudgetPrintForm> printForms = new ArrayList<BudgetPrintForm>();
		printForms.add(budgetCostShareForm);
		printForms.add(budgetCumulativeForm);
		printForms.add(budgetSalaryForm);
		printForms.add(budgetSummaryForm);
		printForms.add(budgetSummaryTotalForm);
		printForms.add(budgetTotalForm);
		printForms.add(industrialBudgetForm);
		printForms.add(industrialBudgetCumulativeForm);
		budget.setBudgetPrintForms(printForms);
	}

	/**
	 * Prints all the selected budget forms
	 * 
	 * @param budget
	 *            {@link Budget}
	 * @param selectedBudgetPrintFormId
	 *            list of selected budget forms
	 * @param response
	 * @return boolean status
	 */
	public boolean printBudgetForms(Budget budget,
			String[] selectedBudgetPrintFormId, HttpServletResponse response) {
		AttachmentDataSource pdf;
		for (int i = 0; i < selectedBudgetPrintFormId.length; i++) {
			pdf = readBudgetPrintStream(budget, selectedBudgetPrintFormId[i]);
		}
		return false;
	}

	/**
	 * Generates the report specified and returns the bytes
	 * 
	 * @param budget
	 *            {@link Budget}
	 * @param selectedBudgetPrintFormId
	 *            form to print
	 * @return {@link AttachmentDataSource} bytes of the generated form
	 */
	public AttachmentDataSource readBudgetPrintStream(Budget budget,
			String selectedBudgetPrintFormId) {
		try {
			return printBudgetReport(budget,selectedBudgetPrintFormId);
		} catch (PrintingException e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * @return the budgetSummaryPrint
	 */
	public BudgetSummaryPrint getBudgetSummaryPrint() {
		return budgetSummaryPrint;
	}

	/**
	 * @param budgetSummaryPrint
	 *            the budgetSummaryPrint to set
	 */
	public void setBudgetSummaryPrint(BudgetSummaryPrint budgetSummaryPrint) {
		this.budgetSummaryPrint = budgetSummaryPrint;
	}

	/**
	 * @return the budgetCostShareSummaryPrint
	 */
	public BudgetCostShareSummaryPrint getBudgetCostShareSummaryPrint() {
		return budgetCostShareSummaryPrint;
	}

	/**
	 * @param budgetCostShareSummaryPrint
	 *            the budgetCostShareSummaryPrint to set
	 */
	public void setBudgetCostShareSummaryPrint(
			BudgetCostShareSummaryPrint budgetCostShareSummaryPrint) {
		this.budgetCostShareSummaryPrint = budgetCostShareSummaryPrint;
	}

	/**
	 * @return the budgetSalaryPrint
	 */
	public BudgetSalaryPrint getBudgetSalaryPrint() {
		return budgetSalaryPrint;
	}

	/**
	 * @param budgetSalaryPrint
	 *            the budgetSalaryPrint to set
	 */
	public void setBudgetSalaryPrint(BudgetSalaryPrint budgetSalaryPrint) {
		this.budgetSalaryPrint = budgetSalaryPrint;
	}

	/**
	 * @return the printingService
	 */
	public PrintingService getPrintingService() {
		return printingService;
	}

	/**
	 * @param printingService
	 *            the printingService to set
	 */
	public void setPrintingService(PrintingService printingService) {
		this.printingService = printingService;
	}

	/**
	 * @return the budgetTotalPrint
	 */
	public BudgetTotalPrint getBudgetTotalPrint() {
		return budgetTotalPrint;
	}

	/**
	 * @param budgetTotalPrint
	 *            the budgetTotalPrint to set
	 */
	public void setBudgetTotalPrint(BudgetTotalPrint budgetTotalPrint) {
		this.budgetTotalPrint = budgetTotalPrint;
	}

	/**
	 * @return the budgetSummaryTotalPrint
	 */
	public BudgetSummaryTotalPrint getBudgetSummaryTotalPrint() {
		return budgetSummaryTotalPrint;
	}

	/**
	 * @param budgetSummaryTotalPrint
	 *            the budgetSummaryTotalPrint to set
	 */
	public void setBudgetSummaryTotalPrint(
			BudgetSummaryTotalPrint budgetSummaryTotalPrint) {
		this.budgetSummaryTotalPrint = budgetSummaryTotalPrint;
	}

	/**
	 * @return the industrialCumulativeBudgetPrint
	 */
	public IndustrialCumulativeBudgetPrint getIndustrialCumulativeBudgetPrint() {
		return industrialCumulativeBudgetPrint;
	}

	/**
	 * @param industrialCumulativeBudgetPrint
	 *            the industrialCumulativeBudgetPrint to set
	 */
	public void setIndustrialCumulativeBudgetPrint(
			IndustrialCumulativeBudgetPrint industrialCumulativeBudgetPrint) {
		this.industrialCumulativeBudgetPrint = industrialCumulativeBudgetPrint;
	}

	/**
	 * @return the industrialBudgetPrint
	 */
	public IndustrialBudgetPrint getIndustrialBudgetPrint() {
		return industrialBudgetPrint;
	}

	/**
	 * @param industrialBudgetPrint
	 *            the industrialBudgetPrint to set
	 */
	public void setIndustrialBudgetPrint(
			IndustrialBudgetPrint industrialBudgetPrint) {
		this.industrialBudgetPrint = industrialBudgetPrint;
	}

	/**
	 * @return the budgetCumulativePrint
	 */
	public BudgetCumulativePrint getBudgetCumulativePrint() {
		return budgetCumulativePrint;
	}

	/**
	 * @param budgetCumulativePrint
	 *            the budgetCumulativePrint to set
	 */
	public void setBudgetCumulativePrint(
			BudgetCumulativePrint budgetCumulativePrint) {
		this.budgetCumulativePrint = budgetCumulativePrint;
	}
}

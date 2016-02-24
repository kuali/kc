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
package org.kuali.coeus.common.budget.impl.print;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.budget.framework.print.BudgetPrintType;
import org.kuali.coeus.common.framework.print.AbstractPrint;
import org.kuali.coeus.common.framework.print.Printable;
import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.common.framework.print.PrintingService;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.coeus.common.budget.framework.print.BudgetPrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the implementation of {@link BudgetPrintService}. It has
 * capability to print any reports related to Budget like Budget Summary,
 * Cost-Share Summary etc.
 * 
 */

@Component("budgetPrintService")
public class BudgetPrintingServiceImpl implements BudgetPrintService {

    private static final Log LOG = LogFactory.getLog(BudgetPrintingServiceImpl.class);

    @Autowired
    @Qualifier("budgetSummaryPrint")
	private BudgetSummaryPrint budgetSummaryPrint;
    @Autowired
    @Qualifier("budgetCostShareSummaryPrint")
	private BudgetCostShareSummaryPrint budgetCostShareSummaryPrint;
    @Autowired
    @Qualifier("budgetSalaryPrint")
	private BudgetSalaryPrint budgetSalaryPrint;
    @Autowired
    @Qualifier("printingService")
	private PrintingService printingService;
    @Autowired
    @Qualifier("budgetTotalPrint")
	private BudgetTotalPrint budgetTotalPrint;
    @Autowired
    @Qualifier("budgetSummaryTotalPrint")
	private BudgetSummaryTotalPrint budgetSummaryTotalPrint;
    @Autowired
    @Qualifier("industrialCumulativeBudgetPrint")
	private IndustrialCumulativeBudgetPrint industrialCumulativeBudgetPrint;
    @Autowired
    @Qualifier("industrialBudgetPrint")
	private IndustrialBudgetPrint industrialBudgetPrint;
    @Autowired
    @Qualifier("budgetCumulativePrint")
	private BudgetCumulativePrint budgetCumulativePrint;

	/**
	 * This method generates the required report and returns the PDF stream as
	 * {@link AttachmentDataSource}. It first identifies the report type to be
	 * printed, then fetches the required report generator. The report generator
	 * generates XML which is then passed to {@link PrintingService} for
	 * transforming into PDF.
	 * 
	 * @param budget
	 *            Award data using which report is generated
	 * @param reportName
	 *            report to be generated
	 * @return {@link AttachmentDataSource} which contains the byte array of the
	 *         generated PDF
	 * @throws PrintingException
	 *             if any errors occur during report generation
	 * 
	 */
	public AttachmentDataSource printBudgetReport(
	        KcPersistableBusinessObjectBase budget, String reportName)
			throws PrintingException {
		AttachmentDataSource attachmentDataSource = null;
		AbstractPrint printable = null;
		String fileName = reportName+"-"+((Budget)budget).getParentDocumentKey()+Constants.PDF_FILE_EXTENSION;
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
            attachmentDataSource.setName(URLEncoder.encode(fileName,"UTF-8"));
        }
        catch (UnsupportedEncodingException e) {
            attachmentDataSource.setName(fileName);
        }
		attachmentDataSource.setType(Constants.PDF_REPORT_CONTENT_TYPE);
		
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
     * Generates the selected reports and returns the bytes
     * 
     * @param budget {@link Budget}
     * @param budgetPrintForms {@link BudgetPrintForm} selected forms to print
     * @param reportName to setting the file name
     * @return {@link AttachmentDataSource} bytes of the generated form
     */
	public AttachmentDataSource readBudgetSelectedPrintStreams(Budget budget,
			List<BudgetPrintForm> budgetPrintForms, String reportName) {
		try {
			return printBudgetSelectedReports(budget, budgetPrintForms, reportName);
		} catch (PrintingException e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * This method generates the selected reports and returns the PDF stream as
	 * {@link AttachmentDataSource}. It first identifies the reports type to be
	 * printed, then fetches the required reports generator. The report generator
	 * generates XML which is then passed to {@link PrintingService} for
	 * transforming into PDF.
	 * 
	 * @param budget data using which report is generated
	 * @param formName report to be generated
	 * @return {@link AttachmentDataSource} which contains the byte array of the generated PDF
	 * @throws PrintingException if any errors occur during report generation
	 * 
	 */
	public AttachmentDataSource printBudgetSelectedReports(
			KcPersistableBusinessObjectBase budget,
			List<BudgetPrintForm> budgetPrintForms, String formName) throws PrintingException {
		AttachmentDataSource attachmentDataSource = null;
		List<Printable> printableArtifactList = new ArrayList<Printable>();
		String fileName = formName + "-" + ((Budget) budget).getParentDocumentKey()
				+ Constants.PDF_FILE_EXTENSION;
		for (BudgetPrintForm budgetPrintForm : budgetPrintForms) {
			Printable printable = null;
			String reportName = budgetPrintForm.getBudgetReportId();
			if (reportName.equals(BudgetPrintType.BUDGET_SUMMARY_REPORT
					.getBudgetPrintType())) {
				printable = getBudgetSummaryPrint();
			} else if (reportName.equals(BudgetPrintType.BUDGET_COST_SHARE_SUMMARY_REPORT
							.getBudgetPrintType())) {
				printable = getBudgetCostShareSummaryPrint();
			} else if (reportName.equals(BudgetPrintType.INDUSTRIAL_CUMULATIVE_BUDGET_REPORT
							.getBudgetPrintType())) {
				printable = getIndustrialCumulativeBudgetPrint();
			} else if (reportName.equals(BudgetPrintType.BUDGET_SALARY_REPORT
					.getBudgetPrintType())) {
				printable = getBudgetSalaryPrint();
			} else if (reportName.equals(BudgetPrintType.BUDGET_TOTAL_REPORT
					.getBudgetPrintType())) {
				printable = getBudgetTotalPrint();
			} else if (reportName.equals(BudgetPrintType.BUDGET_SUMMARY_TOTAL_REPORT
							.getBudgetPrintType())) {
				printable = getBudgetSummaryTotalPrint();
			} else if (reportName.equals(BudgetPrintType.BUDGET_CUMULATIVE_REPORT
							.getBudgetPrintType())) {
				printable = getBudgetCumulativePrint();
			} else if (reportName.equals(BudgetPrintType.INDUSTRIAL_BUDGET_REPORT
							.getBudgetPrintType())) {
				printable = getIndustrialBudgetPrint();
			}
			Budget copiedBudget = null;
			if (Boolean.TRUE.equals(budgetPrintForm.getSelectToComment())) {
				copiedBudget = (Budget) budget;
				copiedBudget.setPrintBudgetCommentFlag("true");
			} else {
				copiedBudget = (Budget) budget;
				copiedBudget.setPrintBudgetCommentFlag("false");
			}
			((AbstractPrint) printable).setPrintableBusinessObject(copiedBudget);
			printableArtifactList.add(printable);
		}
		attachmentDataSource = getPrintingService().print(printableArtifactList);
		try {
			attachmentDataSource.setName(URLEncoder.encode(fileName, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			attachmentDataSource.setName(fileName);
		}
		attachmentDataSource.setType(Constants.PDF_REPORT_CONTENT_TYPE);
		return attachmentDataSource;
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

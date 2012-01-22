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
package org.kuali.kra.award.printing.service.impl;

import java.util.Map;

import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardTemplate;
import org.kuali.kra.award.printing.AwardPrintType;
import org.kuali.kra.award.printing.print.AwardBudgetHierarchyPrint;
import org.kuali.kra.award.printing.print.AwardBudgetHistoryTransactionPrint;
import org.kuali.kra.award.printing.print.AwardDeltaPrint;
import org.kuali.kra.award.printing.print.AwardNoticePrint;
import org.kuali.kra.award.printing.print.AwardTemplatePrint;
import org.kuali.kra.award.printing.print.MoneyAndEndDatesHistoryPrint;
import org.kuali.kra.award.printing.service.AwardPrintingService;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.printing.print.AbstractPrint;
import org.kuali.kra.printing.service.PrintingService;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;

/**
 * This class is the implementation of {@link AwardPrintingService}. It has
 * capability to print any reports related to Award like Delta Report, Award
 * Notice etc.
 * 
 * @author
 * 
 */

public class AwardPrintingServiceImpl implements AwardPrintingService {
	private AwardDeltaPrint awardDeltaPrint;
	private AwardNoticePrint awardNoticePrint;
	private AwardTemplatePrint awardTemplatePrint;
	private MoneyAndEndDatesHistoryPrint moneyAndEndDatesHistoryPrint;
	private AwardBudgetHierarchyPrint awardBudgetHierarchyPrint;
	private AwardBudgetHistoryTransactionPrint awardBudgetHistoryTransactionPrint;
	private PrintingService printingService;

	/**
	 * This method generates the required report and returns the PDF stream as
	 * {@link AttachmentDataSource}. It first identifies the report type to be
	 * printed, then fetches the required report generator. The report generator
	 * generates XML which is then passed to {@link PrintingService} for
	 * transforming into PDF.
	 * 
	 * @param printableBO
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
	public AttachmentDataSource printAwardReport(
			KraPersistableBusinessObjectBase printableBO, AwardPrintType awardReportType,
			Map<String, Object> reportParameters) throws PrintingException {
		AttachmentDataSource source = null;
		AbstractPrint printable = null;
		String repoprtFileNamePrefix= "";
		switch(awardReportType){
		    case AWARD_DELTA_REPORT:
		        printable = getAwardDeltaPrint();
		        repoprtFileNamePrefix = ((Award)printableBO).getAwardNumber();
		        break;
		    case AWARD_NOTICE_REPORT:
		        printable = getAwardNoticePrint();
                repoprtFileNamePrefix = ((Award)printableBO).getAwardNumber();
		        break;
		    case MONEY_AND_END_DATES_HISTORY:
		        printable = getMoneyAndEndDatesHistoryPrint();
                repoprtFileNamePrefix = ((Award)printableBO).getAwardNumber();
		        break;
		    case AWARD_BUDGET_HIERARCHY:
		        printable = getAwardBudgetHierarchyPrint();
                repoprtFileNamePrefix = ((Award)printableBO).getAwardNumber();
		        break;
		    case AWARD_BUDGET_HISTORY_TRANSACTION:
		        printable = getAwardBudgetHistoryTransactionPrint();
                repoprtFileNamePrefix = ((Award)printableBO).getAwardNumber();
		        break;
            case AWARD_TEMPLATE:
                printable = getAwardTemplatePrint();
                repoprtFileNamePrefix = ((AwardTemplate)printableBO).getTemplateCode().toString();
                break;
		        
		}
//		if (reportName.equals(AwardPrintType.AWARD_DELTA_REPORT
//				.getAwardPrintType())) {
//			printable = getAwardDeltaPrint();
//		} else if (reportName.equals(AwardPrintType.AWARD_NOTICE_REPORT
//				.getAwardPrintType())) {
//			printable = getAwardNoticePrint();
//		} else if (reportName.equals(AwardPrintType.AWARD_TEMPLATE
//				.getAwardPrintType())) {
//			printable = getAwardTemplatePrint();
//		} else if (reportName.equals(AwardPrintType.MONEY_AND_END_DATES_HISTORY
//				.getAwardPrintType())) {
//			printable = getMoneyAndEndDatesHistoryPrint();
//		} else if (reportName.equals(AwardPrintType.AWARD_BUDGET_HIERARCHY
//				.getAwardPrintType())) {
//			printable = getAwardBudgetHierarchyPrint();
//		} else if (reportName
//				.equals(AwardPrintType.AWARD_BUDGET_HISTORY_TRANSACTION
//						.getAwardPrintType())) {
//			printable = getAwardBudgetHistoryTransactionPrint();
//		}
		if (printable != null) {
			printable.setPrintableBusinessObject(printableBO);
			printable.setReportParameters(reportParameters);
			source = getPrintingService().print(printable);
			
			source.setFileName(getReportName(repoprtFileNamePrefix, awardReportType.getAwardPrintType()));
			source.setContentType(Constants.PDF_REPORT_CONTENT_TYPE);
		}
		return source;
	}

	protected String getReportName(String reportFileNamePrefix, String reportName) {
//		Award award = ((Award) researchDoc);
//		String awardNumber = award.getAwardNumber();

		StringBuilder reportFullName = new StringBuilder(reportFileNamePrefix).append(
				"_").append(reportName.replace(' ', '_')).append(
				Constants.PDF_FILE_EXTENSION);
		return reportFullName.toString();
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
	 * @return the awardDeltaPrint
	 */
	public AwardDeltaPrint getAwardDeltaPrint() {
		return awardDeltaPrint;
	}

	/**
	 * @param awardDeltaPrint
	 *            the awardDeltaPrint to set
	 */
	public void setAwardDeltaPrint(AwardDeltaPrint awardDeltaPrint) {
		this.awardDeltaPrint = awardDeltaPrint;
	}

	/**
	 * @return the awardNoticePrint
	 */
	public AwardNoticePrint getAwardNoticePrint() {
		return awardNoticePrint;
	}

	/**
	 * @param awardNoticePrint
	 *            the awardNoticePrint to set
	 */
	public void setAwardNoticePrint(AwardNoticePrint awardNoticePrint) {
		this.awardNoticePrint = awardNoticePrint;
	}

	public AwardTemplatePrint getAwardTemplatePrint() {
		return awardTemplatePrint;
	}

	public void setAwardTemplatePrint(AwardTemplatePrint awardTemplatePrint) {
		this.awardTemplatePrint = awardTemplatePrint;
	}

	/**
	 * @return the moneyAndEndDatesHistoryPrint
	 */
	public MoneyAndEndDatesHistoryPrint getMoneyAndEndDatesHistoryPrint() {
		return moneyAndEndDatesHistoryPrint;
	}

	/**
	 * @param moneyAndEndDatesHistoryPrint
	 *            the moneyAndEndDatesHistoryPrint to set
	 */
	public void setMoneyAndEndDatesHistoryPrint(
			MoneyAndEndDatesHistoryPrint moneyAndEndDatesHistoryPrint) {
		this.moneyAndEndDatesHistoryPrint = moneyAndEndDatesHistoryPrint;
	}

	/**
	 * @return the awardBudgetHierarchyPrint
	 */
	public AwardBudgetHierarchyPrint getAwardBudgetHierarchyPrint() {
		return awardBudgetHierarchyPrint;
	}

	/**
	 * @param awardBudgetHierarchyPrint
	 *            the awardBudgetHierarchyPrint to set
	 */
	public void setAwardBudgetHierarchyPrint(
			AwardBudgetHierarchyPrint awardBudgetHierarchyPrint) {
		this.awardBudgetHierarchyPrint = awardBudgetHierarchyPrint;
	}

	/**
	 * @return the awardBudgetHistoryTransactionPrint
	 */
	public AwardBudgetHistoryTransactionPrint getAwardBudgetHistoryTransactionPrint() {
		return awardBudgetHistoryTransactionPrint;
	}

	/**
	 * @param awardBudgetHistoryTransactionPrint
	 *            the awardBudgetHistoryTransactionPrint to set
	 */
	public void setAwardBudgetHistoryTransactionPrint(
			AwardBudgetHistoryTransactionPrint awardBudgetHistoryTransactionPrint) {
		this.awardBudgetHistoryTransactionPrint = awardBudgetHistoryTransactionPrint;
	}
}

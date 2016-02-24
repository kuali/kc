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
package org.kuali.kra.award.printing.service.impl;

import org.kuali.coeus.common.framework.print.AbstractPrint;
import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.common.framework.print.PrintingService;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardTemplate;
import org.kuali.kra.award.printing.AwardPrintType;
import org.kuali.kra.award.printing.print.*;
import org.kuali.kra.award.printing.service.AwardPrintingService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;

import java.util.Map;

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
	 * @param awardReportType
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
			KcPersistableBusinessObjectBase printableBO, AwardPrintType awardReportType,
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
			
			source.setName(getReportName(repoprtFileNamePrefix, awardReportType.getAwardPrintType()));
			source.setType(Constants.PDF_REPORT_CONTENT_TYPE);
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

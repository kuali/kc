package org.kuali.kra.institutionalproposal.printing.service.impl;

import java.util.Map;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.institutionalproposal.printing.InstitutionalProposalPrintType;
import org.kuali.kra.institutionalproposal.printing.print.InstitutionalProposalPrint;
import org.kuali.kra.institutionalproposal.printing.service.InstitutionalProposalPrintingService;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.printing.print.AbstractPrint;
import org.kuali.kra.printing.service.PrintingService;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;

public class InstitutionalProposalPrintingServiceImpl implements
		InstitutionalProposalPrintingService {

	private PrintingService printingService;
	private InstitutionalProposalPrint institutionalProposalPrint;

	/**
	 * This method generates the required report and returns the PDF stream as
	 * {@link AttachmentDataSource}. It first identifies the report type to be
	 * printed, then fetches the required report generator. The report generator
	 * generates XML which is then passed to {@link PrintingService} for
	 * transforming into PDF.
	 * 
	 * @param proposalDocument
	 *            proposal data using which report is generated
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
	public AttachmentDataSource printInstitutionalProposalReport(
			KraPersistableBusinessObjectBase institutionalProposal, String reportName,
			Map<String, Object> reportParameters) throws PrintingException {
		AttachmentDataSource source = null;
		AbstractPrint printable = null;
		if (reportName
				.equals(InstitutionalProposalPrintType.INSTITUTIONAL_PROPOSAL_REPORT
						.getInstitutionalProposalPrintType())) {
			printable = getInstitutionalProposalPrint();
		}
		printable.setPrintableBusinessObject(institutionalProposal);
		printable.setReportParameters(reportParameters);
		source = getPrintingService().print(printable);
		return source;
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
	 * @return the institutionalProposalPrint
	 */
	public InstitutionalProposalPrint getInstitutionalProposalPrint() {
		return institutionalProposalPrint;
	}

	/**
	 * @param institutionalProposalPrint the institutionalProposalPrint to set
	 */
	public void setInstitutionalProposalPrint(
			InstitutionalProposalPrint institutionalProposalPrint) {
		this.institutionalProposalPrint = institutionalProposalPrint;
	}

}

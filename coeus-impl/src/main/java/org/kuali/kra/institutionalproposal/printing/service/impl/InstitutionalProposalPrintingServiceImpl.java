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
package org.kuali.kra.institutionalproposal.printing.service.impl;

import org.kuali.coeus.common.framework.print.AbstractPrint;
import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.common.framework.print.PrintingService;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.institutionalproposal.printing.InstitutionalProposalPrintType;
import org.kuali.kra.institutionalproposal.printing.print.InstitutionalProposalPrint;
import org.kuali.kra.institutionalproposal.printing.service.InstitutionalProposalPrintingService;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;

import java.util.Map;

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
			KcPersistableBusinessObjectBase institutionalProposal, String reportName,
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

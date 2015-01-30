/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.negotiations.printing.service;

import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.negotiations.printing.NegotiationActivityPrintType;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;

import java.util.Map;

/**
 * This class provides the means for printing reports related to Negotiation.
 * 
 * @author
 * 
 */
public interface NegotiationPrintingService {

	/**
	 * This method generates the required report and returns the PDF stream as
	 * {@link AttachmentDataSource}
	 * 
	 * @param negotiationDocument
	 *            Negotiation data using which report is generated
	 * @param reportName
	 *            report to be generated
	 * @param reportParameters
	 *            {@link Map} of parameters required for report generation
	 * @return {@link AttachmentDataSource} which contains the byte array of the
	 *         generated PDF
	 * @throws PrintingException
	 *             if any errors occur during report generation
	 */
	public AttachmentDataSource printNegotiationActivityReport(
			KcPersistableBusinessObjectBase negotiationDocument, NegotiationActivityPrintType negotiationReportType,
			Map<String, Object> reportParameters) throws PrintingException;
}

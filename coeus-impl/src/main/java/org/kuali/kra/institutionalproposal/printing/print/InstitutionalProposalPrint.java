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
package org.kuali.kra.institutionalproposal.printing.print;

import org.kuali.coeus.common.framework.print.AbstractPrint;
import org.kuali.coeus.common.framework.print.util.PrintingUtils;
import org.kuali.kra.institutionalproposal.printing.InstitutionalProposalPrintType;

import javax.xml.transform.Source;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides the implementation for printing Institute proposal
 * Report. It generates XML that conforms with Institute proposal XSD, fetches
 * XSL style-sheets applicable to this XML, returns XML and XSL for any consumer
 * that would use this XML and XSls for any purpose like report generation, PDF
 * streaming etc.
 * 
 * 
 */
public class InstitutionalProposalPrint extends AbstractPrint {

	/**
	 * This method fetches the XSL style-sheets required for transforming the
	 * generated XML into PDF.
	 * 
	 * @return {@link ArrayList}} of {@link Source} XSLs
	 */
	public List<Source> getXSLTemplates() {
		ArrayList<Source> sourceList = PrintingUtils
				.getXSLTforReport(InstitutionalProposalPrintType.INSTITUTIONAL_PROPOSAL_REPORT
						.getInstitutionalProposalPrintType());
		return sourceList;
	}

}

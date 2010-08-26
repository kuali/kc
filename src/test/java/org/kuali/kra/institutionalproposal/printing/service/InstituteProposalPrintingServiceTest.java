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
package org.kuali.kra.institutionalproposal.printing.service;

import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.printing.InstitutionalProposalPrintType;
import org.kuali.kra.printing.util.PrintingServiceTestBase;
import org.kuali.kra.printing.util.PrintingTestUtils;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;

/**
 * This class tests InstituteProposalPrintingService. It tests all reports that
 * are part of Institute Proposal printing.
 * 
 */

public class InstituteProposalPrintingServiceTest extends
		PrintingServiceTestBase {
	private InstitutionalProposalPrintingService instituteProposalPrintingService;

	/**
	 * This method tests Institute Proposal Report. It generates PDF bytes for
	 * the report.
	 */
	@Test
	public void testInstituteProposalReportPrinting() {
		try {
			AttachmentDataSource pdfBytes = getPrintingService()
					.printInstitutionalProposalReport(
							PrintingTestUtils
									.getInstituteProposalDocument().getInstitutionalProposal(),
							InstitutionalProposalPrintType.INSTITUTIONAL_PROPOSAL_REPORT
									.getInstitutionalProposalPrintType(),
							PrintingTestUtils
									.getInstituteProposalReportParameters());
			// FIXME Writing PDF to disk for testing purpose only.
			PrintingTestUtils
					.writePdftoDisk(
							pdfBytes,
							InstitutionalProposalPrintType.INSTITUTIONAL_PROPOSAL_REPORT
									.getInstitutionalProposalPrintType());
			assertNotNull(pdfBytes);
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
	}

	public InstitutionalProposalPrintingService getPrintingService() {
		if (instituteProposalPrintingService != null) {
			instituteProposalPrintingService = KraServiceLocator
					.getService(InstitutionalProposalPrintingService.class);
		}
		return instituteProposalPrintingService;
	}
}

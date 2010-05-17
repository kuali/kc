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
package org.kuali.kra.award.printing.service;

import org.junit.Test;
import org.kuali.kra.award.printing.AwardPrintType;
import org.kuali.kra.award.printing.service.impl.AwardPrintingServiceImpl;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.printing.util.PrintingServiceTestBase;
import org.kuali.kra.printing.util.PrintingTestUtils;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;

/**
 * This class tests AwardPrintingService. It tests all reports that are part of
 * award printing.
 */
@org.junit.Ignore("This test is not meant to be run against the 2.0 release")
public class AwardPrintingServiceTest extends PrintingServiceTestBase {
	private AwardPrintingService awardPrintingService;

	/**
	 * This method tests AwardNoticeReport. It generates AwardNoticeReport.
	 */
	@Test
	public void testAwardNoticeReportPrinting() {
		try {
			AttachmentDataSource pdfBytes = getPrintingService()
					.printAwardReport(PrintingTestUtils.getAwardDocument(),
							AwardPrintType.AWARD_NOTICE_REPORT.getAwardPrintType(),
							PrintingTestUtils.getAwardNoticeReportParameters());

			// FIXME Writing PDF to disk for testing purpose only.
			PrintingTestUtils.writePdftoDisk(pdfBytes,
					AwardPrintType.AWARD_NOTICE_REPORT.getAwardPrintType());
			assertNotNull(pdfBytes);
		} catch (Exception e) {
			e.printStackTrace();
			//assert false;
			throw new RuntimeException(e);
		}
	}

	/**
	 * This method tests AwardDeltaReport. It generates AwardDeltaReport.
	 */
	@Test
	public void testAwardDeltaReportPrinting() {
		try {
			AttachmentDataSource pdfBytes = getPrintingService()
					.printAwardReport(PrintingTestUtils.getAwardDocument(),
							AwardPrintType.AWARD_DELTA_REPORT.getAwardPrintType(),
							PrintingTestUtils.getAwardDeltaReportParameters());

			// FIXME Writing PDF to disk for testing purpose only.
			PrintingTestUtils.writePdftoDisk(pdfBytes,
					AwardPrintType.AWARD_DELTA_REPORT.getAwardPrintType());
			assertNotNull(pdfBytes);
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
			throw new RuntimeException(e);
		}
	}

	private AwardPrintingService getPrintingService() {
		if (awardPrintingService == null) {
			awardPrintingService = KraServiceLocator
					.getService(AwardPrintingService.class);
		}
		return awardPrintingService;
	}
}
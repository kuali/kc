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

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.*;


import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.common.framework.unit.admin.UnitAdministrator;
import org.kuali.coeus.common.framework.unit.admin.UnitAdministratorType;
import org.kuali.coeus.common.impl.print.PrintingServiceImpl;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.printing.AwardPrintParameters;
import org.kuali.kra.award.printing.AwardPrintType;
import org.kuali.kra.award.printing.print.AwardNoticePrint;
import org.kuali.kra.award.printing.xmlstream.AwardNoticeXmlStream;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.printing.schema.AwardType;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.util.KRADConstants;

public class AwardPrintingServiceImplTest {
	
	private Mockery context;
	
	private PrintingServiceImpl printService;
	private UnitService unitService;

	@Before
	public void setUp() throws Exception {
		context = new JUnit4Mockery() {
			{
				setThreadingPolicy(new Synchroniser());
			}
		};
		printService = new PrintingServiceImpl();
		
		final DateTimeService dateTimeService = context.mock(DateTimeService.class);
		final Date now = new Date();
		context.checking(new Expectations() {
			{
				oneOf(dateTimeService).getCurrentTimestamp();
				will(returnValue(new Timestamp(now.getYear(), now.getMonth(), now.getDay(), now.getHours(), now.getMinutes(), now.getSeconds(), 0)));
			}
		});
		context.checking(new Expectations() {
			{
				oneOf(dateTimeService).getCurrentDate();
				will(returnValue(now));
			}
		});
		context.checking(new Expectations() {
			{
				oneOf(dateTimeService).getCurrentCalendar();
				will(returnValue(new GregorianCalendar()));
			}
		});
		printService.setDateTimeService(dateTimeService);
		
		final ConfigurationService configurationService = context.mock(ConfigurationService.class);
		context.checking(new Expectations() {
			{
				oneOf(configurationService).getPropertyValueAsBoolean(Constants.PRINT_LOGGING_ENABLE);
				will(returnValue(false));
				oneOf(configurationService).getPropertyValueAsBoolean(Constants.PRINT_PDF_LOGGING_ENABLE);
				will(returnValue(false));
				oneOf(configurationService).getPropertyValueAsString(KRADConstants.APPLICATION_URL_KEY);
				will(returnValue("foo"));
				oneOf(configurationService).getPropertyValueAsString(Constants.KRA_EXTERNALIZABLE_IMAGES_URI_KEY);
				will(returnValue("foo"));
			}
		});
		printService.setKualiConfigurationService(configurationService);
		unitService = context.mock(UnitService.class);

		context.checking(new Expectations() {
			{
				oneOf(unitService).retrieveUnitAdministratorsByUnitNumber("123456");

				UnitAdministrator admin = new UnitAdministrator();
				admin.setUnitNumber("123456");

				UnitAdministratorType type = new UnitAdministratorType();
				type.setCode(UnitAdministratorType.ADMINISTRATIVE_OFFICER_TYPE_CODE);
				type.setDefaultGroupFlag("Bleh");

				admin.setUnitAdministratorTypeCode(UnitAdministratorType.ADMINISTRATIVE_OFFICER_TYPE_CODE);
				admin.setUnitAdministratorType(type);

				will(returnValue(Collections.singletonList(admin)));
			}
		});
	}
	
	@Test
	public void testPrintAward1() {
		AwardPrintingServiceImpl awardPrintingService = new AwardPrintingServiceImpl();
		AwardNoticePrint awardNoticePrint = new AwardNoticePrint();
		TestableAwardNoticePrintStream printStream = new TestableAwardNoticePrintStream();
		printStream.setDateTimeService(printService.getDateTimeService());
		awardNoticePrint.setXmlStream(printStream);
		awardPrintingService.setAwardNoticePrint(awardNoticePrint);
		awardPrintingService.setPrintingService(printService);
		Map<String, Object> reportParameters = getReportParameters();
		AttachmentDataSource ads = awardPrintingService.printAwardReport(createAward(), AwardPrintType.AWARD_NOTICE_REPORT, reportParameters);
		assertTrue(ads.getData().length > 0);
	}
	
	@Test
	public void testPrintAward2() {
		AwardPrintingServiceImpl awardPrintingService = new AwardPrintingServiceImpl();
		AwardNoticePrint awardNoticePrint = new AwardNoticePrint();
		TestableAwardNoticePrintStream printStream = new TestableAwardNoticePrintStream();
		printStream.setDateTimeService(printService.getDateTimeService());
		awardNoticePrint.setXmlStream(printStream);
		awardPrintingService.setAwardNoticePrint(awardNoticePrint);
		awardPrintingService.setPrintingService(printService);
		Map<String, Object> reportParameters = getReportParameters();
		reportParameters.put(AwardPrintParameters.CLOSEOUT.getAwardPrintParameter(), true);
		AttachmentDataSource ads = awardPrintingService.printAwardReport(createAward(), AwardPrintType.AWARD_NOTICE_REPORT, reportParameters);
		assertTrue(ads.getData().length > 0);
	}
	
	private Map<String, Object> getReportParameters() {
		Map<String, Object> reportParameters = new HashMap<String, Object>();
        reportParameters.put(AwardPrintParameters.ADDRESS_LIST.getAwardPrintParameter(), true);
        reportParameters.put(AwardPrintParameters.FOREIGN_TRAVEL.getAwardPrintParameter(), true);
        reportParameters.put(AwardPrintParameters.REPORTING.getAwardPrintParameter(), true);
        reportParameters.put(AwardPrintParameters.FUNDING_SUMMARY.getAwardPrintParameter(), true);
        reportParameters.put(AwardPrintParameters.SPECIAL_REVIEW.getAwardPrintParameter(), true);
        reportParameters.put(AwardPrintParameters.COMMENTS.getAwardPrintParameter(), true);
        reportParameters.put(AwardPrintParameters.HIERARCHY_INFO.getAwardPrintParameter(), true);
        reportParameters.put(AwardPrintParameters.SUBCONTRACT.getAwardPrintParameter(), true);
        reportParameters.put(AwardPrintParameters.COST_SHARING.getAwardPrintParameter(), true);
        reportParameters.put(AwardPrintParameters.KEYWORDS.getAwardPrintParameter(), true);
        reportParameters.put(AwardPrintParameters.TECHNICAL_REPORTING.getAwardPrintParameter(), true);
        reportParameters.put(AwardPrintParameters.EQUIPMENT.getAwardPrintParameter(), true);
        reportParameters.put(AwardPrintParameters.OTHER_DATA.getAwardPrintParameter(), true);
        reportParameters.put(AwardPrintParameters.TERMS.getAwardPrintParameter(), true);
        reportParameters.put(AwardPrintParameters.FA_COST.getAwardPrintParameter(), true);
        reportParameters.put(AwardPrintParameters.PAYMENT.getAwardPrintParameter(), true);
        reportParameters.put(AwardPrintParameters.FLOW_THRU.getAwardPrintParameter(), true);
        reportParameters.put(AwardPrintParameters.PROPOSAL_DUE.getAwardPrintParameter(), false);
        reportParameters.put(AwardPrintParameters.SIGNATURE_REQUIRED.getAwardPrintParameter(), true);
        return reportParameters;
	}
	
	private Award createAward() {
		Award award = new Award();
		award.setAwardNumber("000001-00001");
		AwardDocument ad = new AwardDocument();
		ad.setDocumentNumber("123");
		ad.setAward(award);
		award.setAwardDocument(ad);
		award.setUnitNumber("123456");
		award.setUnitService(unitService);
		return award;
	}
	
	private class TestableAwardNoticePrintStream extends AwardNoticeXmlStream {
		
		@Override
		protected AwardType getAward() {
			AwardType at = org.kuali.kra.printing.schema.AwardType.Factory.newInstance();
			return at;
		}
	}

}

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
package org.kuali.kra.award.paymentreports.awardreports.reporting.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.kuali.kra.award.AwardFixtureFactory;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.award.home.AwardServiceImpl;
import org.kuali.kra.award.paymentreports.awardreports.reporting.ReportTracking;
import org.kuali.kra.bo.versioning.VersionStatus;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.VersionException;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

public class ReportTrackingNotificationServiceTest extends KcUnitTestBase {

    private ReportTrackingNotificationServiceImpl service;
    private BusinessObjectService boService;
    private DocumentService documentService;
    
    public void setUp() throws Exception {
        super.setUp();
        service = (ReportTrackingNotificationServiceImpl) KraServiceLocator.getService(ReportTrackingNotificationService.class);
        boService = KraServiceLocator.getService(BusinessObjectService.class);
        documentService = KraServiceLocator.getService(DocumentService.class);
        AwardDocument awardDoc = (AwardDocument) documentService.getNewDocument(AwardDocument.class);
        final Award award = AwardFixtureFactory.createAwardFixture();
        awardDoc.setAward(award);
        awardDoc.getDocumentHeader().setDocumentDescription("Testing");
        documentService.saveDocument(awardDoc);
        AwardService mockAwardService = new AwardServiceImpl() {

            @Override
            public Award getActiveOrNewestAward(String awardNumber) {
                return award;
            }
        };
        service.setAwardService(mockAwardService);
        boService.save(getNewReportTracking(award, "4", "4", Calendar.getInstance().getTime()));
        Calendar newDate = Calendar.getInstance();
        newDate.add(Calendar.DAY_OF_MONTH, -30);
        boService.save(getNewReportTracking(award, "4", "4", newDate.getTime()));
        
        service.getNotifications().clear();
        service.getNotifications().add(new ReportTrackingNotification("Test", "401", true, 30, 30, "4"));
    }
    
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    @Test
    public void testRunReportTrackingNotifications() {
        List<ReportTrackingNotificationDetails> details = service.runReportTrackingNotifications();
        assertEquals(1, details.size());
        assertTrue(details.get(0).isNotificationActive());
        assertEquals(1, details.get(0).getNotificationRecipients());
        assertEquals(2, details.get(0).getTrackingRecordsFound());
        assertEquals(1, details.get(0).getTrackingRecordsMatched());
        assertEquals(2, details.get(0).getNotificationsSent());
        List<SentReportNotification> notificationsSent = (List<SentReportNotification>) boService.findAll(SentReportNotification.class);
        assertEquals(1, notificationsSent.size());
    }
    
    @Test
    public void testRunReportTrackingNotificationsPreviouslySent() {
        List<ReportTrackingNotificationDetails> details = service.runReportTrackingNotifications();
        List<SentReportNotification> notificationsSent = (List<SentReportNotification>) boService.findAll(SentReportNotification.class);
        assertEquals(1, notificationsSent.size());
        
        details = service.runReportTrackingNotifications();
        assertEquals(1, details.size());
        assertTrue(details.get(0).isNotificationActive());
        assertEquals(1, details.get(0).getNotificationRecipients());
        assertEquals(2, details.get(0).getTrackingRecordsFound());
        assertEquals(0, details.get(0).getTrackingRecordsMatched());
        assertEquals(0, details.get(0).getNotificationsSent());
    }
    
    public ReportTracking getNewReportTracking(Award award, String reportClassCode, String frequencyBaseCode, Date dueDate) {
        ReportTracking result = new ReportTracking();
        result.setAwardNumber(award.getAwardNumber());
        result.setPiName("Quickstart Quickstart");
        result.setLeadUnitNumber("000001");
        result.setReportClassCode(reportClassCode);
        result.setReportCode("4");
        result.setSponsorCode("4");
        result.setTitle("Testing");
        result.setLastUpdateUser("quickstart");
        result.setLastUpdateDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        result.setUpdateUser("quickstart");
        result.setUpdateTimestamp(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        result.setFrequencyBaseCode(frequencyBaseCode);
        result.setDueDate(new java.sql.Date(dueDate.getTime()));
        return result;
    }

}

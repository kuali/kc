/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.AwardFixtureFactory;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.award.home.AwardServiceImpl;
import org.kuali.kra.award.paymentreports.awardreports.reporting.ReportTracking;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

 /*
 >>> org.kuali.kra.award.paymentreports.awardreports.reporting.service.ReportTrackingNotificationServiceTest.testRunReportTrackingNotificationsPreviouslySent 	2 ms	1
>>> org.kuali.kra.award.paymentreports.awardreports.reporting.service.ReportTrackingNotificationServiceTest.testRunReportTrackingNotificationsPreviouslySent 	6 ms	1
>>> org.kuali.kra.award.paymentreports.awardreports.reporting.service.ReportTrackingNotificationServiceTest.testRunReportTrackingNotifications 	1 ms	1
>>> org.kuali.kra.award.paymentreports.awardreports.reporting.service.ReportTrackingNotificationServiceTest.testRunReportTrackingNotifications 	1 ms	1
>>> org.kuali.kra.award.paymentreports.awardreports.reporting.service.ReportTrackingNotificationServiceTest.testDateBarriers 	1 ms	1
>>> org.kuali.kra.award.paymentreports.awardreports.reporting.service.ReportTrackingNotificationServiceTest.testDateBarriers

  */
public class ReportTrackingNotificationServiceTest extends KcUnitTestBase {

    private ReportTrackingNotificationServiceImpl service;
    private BusinessObjectService boService;
    private DocumentService documentService;
    private Long currentTermId = 1L;
    
    private Award award;

     @Before
    public void setUp() throws Exception {
        super.setUp();
        service = (ReportTrackingNotificationServiceImpl) KraServiceLocator.getService(ReportTrackingNotificationService.class);
        boService = KraServiceLocator.getService(BusinessObjectService.class);
        documentService = KraServiceLocator.getService(DocumentService.class);
        AwardDocument awardDoc = (AwardDocument) documentService.getNewDocument(AwardDocument.class);
        award = AwardFixtureFactory.createAwardFixture();
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

    }

     @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    @Test
    public void tstRunReportTrackingNotifications() {
        boService.save(getNewReportTracking(award, "4", "4", Calendar.getInstance().getTime()));
        Calendar newDate = Calendar.getInstance();
        newDate.add(Calendar.DAY_OF_MONTH, -40);
        boService.save(getNewReportTracking(award, "4", "4", newDate.getTime()));
        
        service.getNotifications().clear();
        service.getNotifications().add(new ReportTrackingNotification("Test", "401", true, 30, 30, "4"));
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
    public void tstRunReportTrackingNotificationsPreviouslySent() {
        boService.save(getNewReportTracking(award, "4", "4", Calendar.getInstance().getTime()));
        Calendar newDate = Calendar.getInstance();
        newDate.add(Calendar.DAY_OF_MONTH, -40);
        boService.save(getNewReportTracking(award, "4", "4", newDate.getTime())); 
        
        service.getNotifications().clear();
        service.getNotifications().add(new ReportTrackingNotification("Test", "401", true, 30, 30, "4"));        
        
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
    
    @Test
    public void tstDateBarriers() {
        boService.save(getNewReportTracking(award, "4", "4", Calendar.getInstance().getTime()));
        Calendar newDate = Calendar.getInstance();
        newDate.add(Calendar.DAY_OF_YEAR, -29);
        boService.save(getNewReportTracking(award, "4", "4", newDate.getTime()));        
        newDate = Calendar.getInstance();
        newDate.add(Calendar.DAY_OF_YEAR, -30);
        boService.save(getNewReportTracking(award, "4", "4", newDate.getTime()));
        newDate = Calendar.getInstance();
        newDate.add(Calendar.DAY_OF_YEAR, -59);
        boService.save(getNewReportTracking(award, "4", "4", newDate.getTime()));        
        newDate = Calendar.getInstance();
        newDate.add(Calendar.DAY_OF_YEAR, -60);
        boService.save(getNewReportTracking(award, "4", "4", newDate.getTime()));
        newDate = Calendar.getInstance();
        newDate.add(Calendar.DAY_OF_YEAR, 10);
        boService.save(getNewReportTracking(award, "4", "4", newDate.getTime()));
        newDate = Calendar.getInstance();
        newDate.add(Calendar.DAY_OF_YEAR, 30);
        boService.save(getNewReportTracking(award, "4", "4", newDate.getTime()));        
        newDate = Calendar.getInstance();
        newDate.add(Calendar.DAY_OF_YEAR, 60);
        boService.save(getNewReportTracking(award, "4", "4", newDate.getTime()));
        newDate = Calendar.getInstance();
        newDate.add(Calendar.DAY_OF_YEAR, 61);
        boService.save(getNewReportTracking(award, "4", "4", newDate.getTime()));        
        
        service.getNotifications().clear();
        service.getNotifications().add(new ReportTrackingNotification("Test", "401", true, 30, 30, "4"));
        service.getNotifications().add(new ReportTrackingNotification("Test", "402", true, 60, 30, "4"));
        service.getNotifications().add(new ReportTrackingNotification("Test", "403", false, 30, 30, "4"));
        service.getNotifications().add(new ReportTrackingNotification("Test", "404", false, 60, 30, "4"));
        
        List<ReportTrackingNotificationDetails> details = service.runReportTrackingNotifications();
        List<SentReportNotification> notificationsSent = (List<SentReportNotification>) boService.findAll(SentReportNotification.class);
        assertEquals(6, notificationsSent.size());
        assertEquals(4, details.size());
        assertEquals("401", details.get(0).getActionCode());
        assertEquals(9, details.get(0).getTrackingRecordsFound());
        assertEquals(2, details.get(0).getTrackingRecordsMatched());
        //the below is based on current demo data and the number of OSP Admins for 000001
        assertEquals(2, details.get(0).getNotificationsSent());
        
        assertEquals("402", details.get(1).getActionCode());
        assertEquals(9, details.get(1).getTrackingRecordsFound());
        assertEquals(1, details.get(1).getTrackingRecordsMatched());
        //the below is based on current demo data and the number of OSP Admins for 000001        
        assertEquals(2, details.get(1).getNotificationsSent());  
        
        assertEquals("403", details.get(2).getActionCode());
        assertEquals(9, details.get(2).getTrackingRecordsFound());
        assertEquals(2, details.get(2).getTrackingRecordsMatched());
        //the below is based on current demo data and the number of OSP Admins for 000001        
        assertEquals(2, details.get(2).getNotificationsSent());
        
        assertEquals("404", details.get(3).getActionCode());
        assertEquals(9, details.get(3).getTrackingRecordsFound());
        assertEquals(1, details.get(3).getTrackingRecordsMatched());
        //the below is based on current demo data and the number of OSP Admins for 000001        
        assertEquals(2, details.get(3).getNotificationsSent());            
    }   
    
    public ReportTracking getNewReportTracking(Award award, String reportClassCode, String frequencyBaseCode, Date dueDate) {
        ReportTracking result = new ReportTracking();
        result.setAwardReportTermId(currentTermId++);
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

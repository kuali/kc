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
package org.kuali.kra.award.paymentreports.awardreports.reporting.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.AwardFixtureFactory;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.award.home.AwardServiceImpl;
import org.kuali.kra.award.paymentreports.awardreports.reporting.ReportTracking;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ReportTrackingNotificationServiceTest extends KcIntegrationTestBase {

    private ReportTrackingNotificationServiceImpl service;
    private BusinessObjectService boService;
    private DocumentService documentService;
    private Long currentTermId = 1L;
    
    private Award award;

     @Before
    public void setUp() throws Exception {
        service = (ReportTrackingNotificationServiceImpl) KcServiceLocator.getService(ReportTrackingNotificationService.class);
        boService = KcServiceLocator.getService(BusinessObjectService.class);
        documentService = KcServiceLocator.getService(DocumentService.class);
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

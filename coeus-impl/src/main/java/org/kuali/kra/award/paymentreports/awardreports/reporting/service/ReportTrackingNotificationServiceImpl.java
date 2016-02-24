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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.notification.impl.bo.NotificationType;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.award.notification.AwardNotificationContext;
import org.kuali.kra.award.notification.AwardReportTrackingNotificationRenderer;
import org.kuali.kra.award.paymentreports.awardreports.reporting.ReportTracking;
import org.kuali.rice.ken.api.notification.NotificationRecipient;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.*;

/**
 * Service to handle sending report tracking notifications.
 */
public class ReportTrackingNotificationServiceImpl implements ReportTrackingNotificationService {
    
    private static final Log LOG = LogFactory.getLog(ReportTrackingNotificationServiceImpl.class);
    private BusinessObjectService businessObjectService;
    private AwardService awardService;
    private KcNotificationService notificationService;
    
    private List<ReportTrackingNotification> notifications;
    
    public ReportTrackingNotificationServiceImpl() {
        notifications = new ArrayList<ReportTrackingNotification>();
    }
    
    @Override
    public List<ReportTrackingNotificationDetails> runReportTrackingNotifications() {
        List<ReportTrackingNotificationDetails> resultDetails = new ArrayList<ReportTrackingNotificationDetails>();
        AwardReportTrackingNotificationRenderer renderer = new AwardReportTrackingNotificationRenderer();
        
        for (ReportTrackingNotification notification : notifications) {
            ReportTrackingNotificationDetails details = new ReportTrackingNotificationDetails();
            details.setActionCode(notification.getActionCode());
            details.setNotificationName(notification.getName());
            resultDetails.add(details);
            try {
                NotificationType notificationType = notificationService.getNotificationType("1", notification.getActionCode());
                if (notificationType != null && notificationType.isActive() 
                        && notificationType.getNotificationTypeRecipients() != null
                        && !notificationType.getNotificationTypeRecipients().isEmpty()) {
                    details.setNotificationActive(true);
                    details.setNotificationRecipients(notificationType.getNotificationTypeRecipients().size());
                    int recordsFound = 0;
                    int recordsMatched = 0;
                    int notificationsSent = 0;
    
                    // either add or subtract, based on the overdue flag the number of days specified in the notification from today
                    Calendar checkFor = Calendar.getInstance();
                    Calendar until = null;
                    if (notification.isOverdue()) {
                        checkFor.add(Calendar.DAY_OF_MONTH, (notification.getDays()+notification.getScope())*-1);                       
                    } else {
                        checkFor.add(Calendar.DAY_OF_MONTH, notification.getDays()-notification.getScope());                                                
                    }
                    until = (Calendar) checkFor.clone();
                    until.add(Calendar.DAY_OF_MONTH, notification.getScope());
                    clearTimeFields(checkFor);
                    clearTimeFields(until);
                    Map<Award, List<ReportTracking>> matchedReports = new HashMap<Award, List<ReportTracking>>();
                    Map<NotificationRecipient.Builder, List<ReportTracking>> recipients = 
                        new TreeMap<NotificationRecipient.Builder, List<ReportTracking>>(new Comparator<NotificationRecipient.Builder>() {
                            public int compare(NotificationRecipient.Builder o1, NotificationRecipient.Builder o2) {
                                return o1.getRecipientId().compareTo(o2.getRecipientId());
                            }
                        }); 
                    for (ReportTrackingNotificationTask task : notification.getTasks()) {
                        List<ReportTracking> reports = 
                            (List<ReportTracking>) businessObjectService.findMatching(ReportTracking.class, task.getReportTrackingValueMap());
                        recordsFound += reports.size();
                        for (ReportTracking report : reports) {
                            //if the report's due date is between the checked for date and the scoped date, and a notification
                            //hasn't previously been sent for it, add it to the matched reports.
                            if (report.getDueDate() != null &&
                                    doDatesMatch(report.getDueDate(), checkFor, until) 
                                    && !hasSentNotification(report, notification)) {
                                recordsMatched++;
                                Award curAward = awardService.getActiveOrNewestAward(report.getAwardNumber());
                                report.setAward(curAward);
                                List<ReportTracking> curReports = matchedReports.get(curAward);
                                if (curReports == null) {
                                    curReports = new ArrayList<ReportTracking>();
                                    matchedReports.put(curAward, curReports);
                                }
                                curReports.add(report);
                            }
                        }
                        //rehash the reports per recipient found in the notification
                        for (Award award : matchedReports.keySet()) {
                            Set<NotificationRecipient.Builder> recips = notificationService.getNotificationRecipients(
                                            new AwardNotificationContext(award, notification.getActionCode(), notification.getName()));
                            for (NotificationRecipient.Builder recip : recips) {
                                List<ReportTracking> curReports = recipients.get(recip); 
                                if (curReports == null) {
                                    curReports = new ArrayList<ReportTracking>();
                                    recipients.put(recip, curReports);
                                }
                                curReports.addAll(matchedReports.get(award));
                            }
                        }
                        for (Map.Entry<NotificationRecipient.Builder, List<ReportTracking>> entry : recipients.entrySet()) {
                            renderer.setReports(entry.getValue());
                            String message = renderer.render(notificationType.getMessage());
                            notificationService.sendNotification(notification.getName(), notificationType.getSubject(), message, Collections.singleton(entry.getKey()));
                            notificationsSent++;
                        }
                        //for each matched report, create a sent report record to make sure we don't attempt
                        //to send this notification for this report again.
                        List<SentReportNotification> sentReports = new ArrayList<SentReportNotification>();
                        for (Map.Entry<Award, List<ReportTracking>> entry : matchedReports.entrySet()) {
                            for (ReportTracking report : entry.getValue()) {
                                sentReports.add(new SentReportNotification(notification.getActionCode(), report));
                            }
                        }
                        businessObjectService.save(sentReports);
                    }
                    details.setTrackingRecordsFound(recordsFound);
                    details.setTrackingRecordsMatched(recordsMatched);
                    details.setNotificationsSent(notificationsSent);
                }
            } catch (Exception e) {
                LOG.error("Error sending report tracking notifications for " + notification.getActionCode(), e);
                details.setErrorMessage(e.getMessage());
            }
        }
        
        return resultDetails;
    }
    
    /**
     * Is date1 after from and before until?
     * @param date1
     * @param from
     * @param until
     * @return
     */
    protected boolean doDatesMatch(Date date1, Calendar from, Calendar until) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        clearTimeFields(cal1);
        return cal1.after(from) && (cal1.equals(until) || cal1.before(until));
    }
    
    /**
     * Check to see if this report tracking record has already had a notification sent.
     * @param report
     * @param notification
     * @return
     */
    protected boolean hasSentNotification(ReportTracking report, ReportTrackingNotification notification) {

        Map<String, Object> values = new HashMap<String, Object>();
        if (report.getAwardReportTermId() != null) {
            values.put("awardReportTermId", report.getAwardReportTermId());
        }
        values.put("awardNumber", report.getAwardNumber());
        values.put("dueDate", report.getDueDate());
        values.put("actionCode", notification.getActionCode());
        List<SentReportNotification> notifications = (List<SentReportNotification>) getBusinessObjectService().findMatching(SentReportNotification.class, values);
        return notifications != null && !notifications.isEmpty();
    }
    
    protected void clearTimeFields(Calendar date) {
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
    }

    protected BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    protected KcNotificationService getNotificationService() {
        return notificationService;
    }

    public void setNotificationService(KcNotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public List<ReportTrackingNotification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<ReportTrackingNotification> notifications) {
        this.notifications = notifications;
    }

    public AwardService getAwardService() {
        return awardService;
    }

    public void setAwardService(AwardService awardService) {
        this.awardService = awardService;
    }

}

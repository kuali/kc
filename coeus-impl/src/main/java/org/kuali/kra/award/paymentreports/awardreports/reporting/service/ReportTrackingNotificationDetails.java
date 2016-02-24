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

/**
 * Simple POJO used to return details related to the report tracking notification service
 * quartz job.
 */
public class ReportTrackingNotificationDetails {

    private String errorMessage;
    private String notificationName;
    private String actionCode;
    private boolean notificationActive;
    private int notificationRecipients;
    private int trackingRecordsFound;
    private int trackingRecordsMatched;
    private int notificationsSent;
    
    public int getTrackingRecordsMatched() {
        return trackingRecordsMatched;
    }
    public void setTrackingRecordsMatched(int trackingRecordsMatched) {
        this.trackingRecordsMatched = trackingRecordsMatched;
    }
    public int getNotificationsSent() {
        return notificationsSent;
    }
    public void setNotificationsSent(int notificationsSent) {
        this.notificationsSent = notificationsSent;
    }
    public int getTrackingRecordsFound() {
        return trackingRecordsFound;
    }
    public void setTrackingRecordsFound(int trackingRecordsFound) {
        this.trackingRecordsFound = trackingRecordsFound;
    }
    public String getActionCode() {
        return actionCode;
    }
    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }
    public boolean isNotificationActive() {
        return notificationActive;
    }
    public void setNotificationActive(boolean notificationActive) {
        this.notificationActive = notificationActive;
    }
    public int getNotificationRecipients() {
        return notificationRecipients;
    }
    public void setNotificationRecipients(int notificationRecipients) {
        this.notificationRecipients = notificationRecipients;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public String getNotificationName() {
        return notificationName;
    }
    public void setNotificationName(String notificationName) {
        this.notificationName = notificationName;
    }
}

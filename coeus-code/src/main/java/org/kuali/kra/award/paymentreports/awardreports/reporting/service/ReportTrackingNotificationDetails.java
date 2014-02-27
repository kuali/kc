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

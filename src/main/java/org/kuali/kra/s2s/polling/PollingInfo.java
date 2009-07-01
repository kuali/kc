/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.s2s.polling;

import java.util.List;

/**
 * 
 * This class stores all information related to a task that is meant to be scheduled to run periodically.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class PollingInfo implements TaskInfo {
    private String taskId;
    private long pollingInterval;
    private long delayToStart;
    private long mailInterval;
    private StatusInfo[] statuses;
    private boolean terminate;
    private int stopPollInterval;
    private List<MailInfo> mailInfoList;

    /**
     * Creates a new instance of PollingInfo
     */
    public PollingInfo() {
    }


    /**
     * Getter for property grpId.
     * 
     * @return Value of property grpId.
     */
    public java.lang.String getTaskId() {
        return taskId;
    }

    /**
     * Setter for property grpId.
     * 
     * @param grpId New value of property grpId.
     */
    public void setTaskId(java.lang.String taskId) {
        this.taskId = taskId;
    }

    /**
     * Getter for property statuses.
     * 
     * @return Value of property statuses.
     */
    public StatusInfo[] getStatuses() {
        return this.statuses;
    }

    /**
     * Setter for property statuses.
     * 
     * @param statuses New value of property statuses.
     */
    public void setStatuses(StatusInfo[] statuses) {
        this.statuses = statuses;
    }

    /**
     * Setter for property pollInterval.
     * 
     * @param pollInterval New value of property pollInterval.
     */
    public void setPollingInterval(java.lang.String pollingInterval) {
        this.pollingInterval = pollingInterval != null && pollingInterval.trim().length() > 0 ? Long.parseLong(pollingInterval) : 0;
    }

    /**
     * 
     * @see org.kuali.kra.s2s.polling.TaskInfo#getPollingInterval()
     */
    public long getPollingInterval() {
        return this.pollingInterval;
    }

    /**
     * Getter for property mailInterval.
     * 
     * @return Value of property mailInterval.
     */
    public long getMailInterval() {
        return mailInterval;
    }

    /**
     * Setter for property mailInterval.
     * 
     * @param mailInterval New value of property mailInterval.
     */
    public void setMailInterval(java.lang.String mailInterval) {
        this.mailInterval = mailInterval != null && mailInterval.trim().length() > 0 ? Long.parseLong(mailInterval) : 0;
    }

    /**
     * Getter for property terminate.
     * 
     * @return Value of property terminate.
     */
    public boolean isTerminate() {
        return terminate;
    }

    /**
     * Setter for property terminate.
     * 
     * @param terminate New value of property terminate.
     */
    public void setTerminate(boolean terminate) {
        this.terminate = terminate;
    }

    /**
     * Getter for property stopPollInterval.
     * 
     * @return Value of property stopPollInterval.
     */
    public int getStopPollInterval() {
        return stopPollInterval;
    }

    /**
     * Setter for property stopPollInterval.
     * 
     * @param stopPollInterval New value of property stopPollInterval.
     */
    public void setStopPollInterval(String stopPollInterval) {
        this.stopPollInterval = (stopPollInterval != null && !stopPollInterval.trim().equals("")) ? Integer
                .parseInt(stopPollInterval) : 0;
        if (this.stopPollInterval > 0) {
            setTerminate(true);
        }
    }

    /**
     * 
     * @see org.kuali.kra.s2s.polling.TaskInfo#getDelayToStart()
     */
    public long getDelayToStart() {
        return delayToStart;
    }

    /**
     * Setter for property delayToStart.
     * 
     * @param delayToStart New value of property delayToStart.
     */
    public void setDelayToStart(long delayToStart) {
        this.delayToStart = delayToStart;
    }

    /**
     * Setter for property mailInfoList.
     * 
     * @param mailInfoList New value of property mailInfoList.
     */
    public List<MailInfo> getMailInfoList() {
        return mailInfoList;
    }

    public void setMailInfoList(List<MailInfo> mailInfoList) {
        this.mailInfoList = mailInfoList;
    }

}

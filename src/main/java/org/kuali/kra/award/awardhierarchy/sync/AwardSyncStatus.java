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
package org.kuali.kra.award.awardhierarchy.sync;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class AwardSyncStatus extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = -8047529476039817371L;

    private Long awardSyncStatusId;

    private Long parentAwardId;

    private Long awardId;

    private String awardNumber;

    private String status;

    private boolean success;

    private boolean syncComplete;

    private Award award;

    private List<AwardSyncLog> changeLogs = new ArrayList<AwardSyncLog>();

    private List<AwardSyncLog> validationLogs = new ArrayList<AwardSyncLog>();

    public void clearLogs() {
        changeLogs.clear();
        validationLogs.clear();
    }

    public void addChangeLog(AwardSyncChange change, String statusMessage, boolean logSuccess) {
        AwardSyncLog log = new AwardSyncLog();
        log.setSyncStatus(this);
        log.setChange(change);
        log.setLogType(AwardSyncLogType.CHANGE_LOG);
        log.setStatus(statusMessage);
        log.setSuccess(logSuccess);
        changeLogs.add(log);
    }

    public void addValidationLog(String statusMessage, boolean logSuccess, String messageKey) {
        AwardSyncLog log = new AwardSyncLog();
        log.setSyncStatus(this);
        log.setLogType(AwardSyncLogType.VALIDATION_MESSAGE);
        log.setStatus(statusMessage);
        log.setSuccess(logSuccess);
        log.setMessageKey(messageKey);
        validationLogs.add(log);
    }

    public Long getAwardSyncStatusId() {
        return awardSyncStatusId;
    }

    public void setAwardSyncStatusId(Long awardSyncStatusId) {
        this.awardSyncStatusId = awardSyncStatusId;
    }

    public Long getParentAwardId() {
        return parentAwardId;
    }

    public void setParentAwardId(Long parentAwardId) {
        this.parentAwardId = parentAwardId;
    }

    public Long getAwardId() {
        return awardId;
    }

    public void setAwardId(Long awardId) {
        this.awardId = awardId;
    }

    public String getAwardNumber() {
        return awardNumber;
    }

    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isSyncComplete() {
        return syncComplete;
    }

    public void setSyncComplete(boolean syncComplete) {
        this.syncComplete = syncComplete;
    }

    public List<AwardSyncLog> getChangeLogs() {
        return changeLogs;
    }

    public void setChangeLogs(List<AwardSyncLog> changeLogs) {
        this.changeLogs = changeLogs;
    }

    public List<AwardSyncLog> getValidationLogs() {
        return validationLogs;
    }

    public void setValidationLogs(List<AwardSyncLog> validationLogs) {
        this.validationLogs = validationLogs;
    }

    public Award getAward() {
        return award;
    }

    public void setAward(Award award) {
        this.award = award;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

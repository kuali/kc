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
package org.kuali.kra.award.awardhierarchy.sync;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.award.home.Award;

import java.util.ArrayList;
import java.util.List;

public class AwardSyncStatus extends KcPersistableBusinessObjectBase {

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

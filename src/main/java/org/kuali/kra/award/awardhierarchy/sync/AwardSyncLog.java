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

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * Log entry for award sync.
 */
public class AwardSyncLog extends KraPersistableBusinessObjectBase implements Comparable<AwardSyncLog> {

    private static final long serialVersionUID = 6519251953519350489L;

    private Long awardSyncLogId;

    private Long awardSyncStatusId;

    private Long awardSyncChangeId;

    private boolean success;

    private String logTypeCode;

    private String status;

    private AwardSyncStatus syncStatus;

    private AwardSyncChange change;

    private transient String messageKey;

    public Long getAwardSyncLogId() {
        return awardSyncLogId;
    }

    public void setAwardSyncLogId(Long awardSyncLogId) {
        this.awardSyncLogId = awardSyncLogId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    public Long getAwardSyncChangeId() {
        return awardSyncChangeId;
    }

    public void setAwardSyncChangeId(Long awardSyncChangeId) {
        this.awardSyncChangeId = awardSyncChangeId;
    }

    public AwardSyncChange getChange() {
        return change;
    }

    public void setChange(AwardSyncChange change) {
        this.change = change;
    }

    public int compareTo(AwardSyncLog log) {
        if (getAwardSyncLogId() == null) {
            return 1;
        } else if (log.getAwardSyncLogId() == null) {
            return -1;
        } else {
            return getAwardSyncLogId().compareTo(log.getAwardSyncLogId());
        }
    }

    public String getLogTypeCode() {
        return logTypeCode;
    }

    public void setLogTypeCode(String logTypeCode) {
        this.logTypeCode = logTypeCode;
    }

    public AwardSyncLogType getLogType() {
        if (logTypeCode == null) {
            return null;
        } else {
            return AwardSyncLogType.getLogTypeFromCode(logTypeCode);
        }
    }

    public void setLogType(AwardSyncLogType logType) {
        if (logType != null) {
            this.logTypeCode = logType.getCode();
        } else {
            this.logTypeCode = null;
        }
    }

    public boolean isChangeLog() {
        return StringUtils.equals(logTypeCode, AwardSyncLogType.CHANGE_LOG.getCode());
    }

    public boolean isValidationLog() {
        return StringUtils.equals(logTypeCode, AwardSyncLogType.VALIDATION_MESSAGE.getCode());
    }

    public Long getAwardSyncStatusId() {
        return awardSyncStatusId;
    }

    public void setAwardSyncStatusId(Long awardSyncStatusId) {
        this.awardSyncStatusId = awardSyncStatusId;
    }

    public AwardSyncStatus getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(AwardSyncStatus syncStatus) {
        this.syncStatus = syncStatus;
    }
}

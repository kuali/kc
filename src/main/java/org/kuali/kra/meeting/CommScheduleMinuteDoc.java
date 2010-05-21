/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.meeting;

import java.sql.Timestamp;
import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.committee.bo.CommitteeSchedule;

/**
 * 
 * This class is for meeting generated minute doc.
 */
public class CommScheduleMinuteDoc extends KraPersistableBusinessObjectBase { 
    
    private static final long serialVersionUID = 2574809115702106379L;
    private Long commScheduleMinuteDocId; 
    private Long scheduleIdFk; 
    private Integer minuteNumber; 
    private String minuteName; 
    private byte[] pdfStore; 
    private Timestamp createTimestamp;
    private String createUser;
    private CommitteeSchedule committeeSchedule;    
    
    public CommScheduleMinuteDoc() { 

    } 
    
    public Long getCommScheduleMinuteDocId() {
        return commScheduleMinuteDocId;
    }

    public void setCommScheduleMinuteDocId(Long commScheduleMinuteDocId) {
        this.commScheduleMinuteDocId = commScheduleMinuteDocId;
    }

    public Integer getMinuteNumber() {
        return minuteNumber;
    }

    public void setMinuteNumber(Integer minuteNumber) {
        this.minuteNumber = minuteNumber;
    }

    public String getMinuteName() {
        return minuteName;
    }

    public void setMinuteName(String minuteName) {
        this.minuteName = minuteName;
    }

    public byte[] getPdfStore() {
        return pdfStore;
    }

    public void setPdfStore(byte[] pdfStore) {
        this.pdfStore = pdfStore;
    }


    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("commScheduleMinuteDocId", this.getCommScheduleMinuteDocId());
        hashMap.put("scheduleIdFk", this.getScheduleIdFk());
        hashMap.put("minuteNumber", this.getMinuteNumber());
        hashMap.put("minuteName", this.getMinuteName());
        hashMap.put("pdfStore", this.getPdfStore());
        return hashMap;
    }

    public CommitteeSchedule getCommitteeSchedule() {
        return committeeSchedule;
    }

    public void setCommitteeSchedule(CommitteeSchedule committeeSchedule) {
        this.committeeSchedule = committeeSchedule;
    }

    public Long getScheduleIdFk() {
        return scheduleIdFk;
    }

    public void setScheduleIdFk(Long scheduleIdFk) {
        this.scheduleIdFk = scheduleIdFk;
    }

    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    
}

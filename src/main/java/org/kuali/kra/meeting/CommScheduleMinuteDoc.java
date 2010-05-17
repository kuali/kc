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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.committee.bo.CommitteeSchedule;

/**
 * 
 * This class is for meeting generated minute doc.
 */
@Entity 
@Table(name="COMM_SCHEDULE_MINUTE_DOC")
public class CommScheduleMinuteDoc extends KraPersistableBusinessObjectBase { 
    
    private static final long serialVersionUID = 2574809115702106379L;
    @Id 
    @Column(name="COMM_SCHEDULE_MINUTE_DOC_ID")
    private Long commScheduleMinuteDocId; 
    @Column(name="SCHEDULE_ID_FK")
    private Long scheduleIdFk; 
    @Column(name="MINUTE_NUMBER")
    private Integer minuteNumber; 
    @Column(name="MINUTE_NAME")
    private String minuteName; 
    @Column(name="PDF_STORE")
    private byte[] pdfStore; 
    @Column(name="CREATE_TIMESTAMP")
    private Timestamp createTimestamp;
    @Column(name="CREATE_USER")
    private String createUser;

    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="SCHEDULE_ID_FK", insertable=false, updatable=false)
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

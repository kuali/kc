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
 * This class is meeting generated agenda doc.
 */
@Entity 
@Table(name="SCHEDULE_AGENDA")
public class ScheduleAgenda extends KraPersistableBusinessObjectBase { 
    

    private static final long serialVersionUID = -3448403457020324952L;
    @Id 
    @Column(name="SCHEDULE_AGENDA_ID")
    private Long scheduleAgendaId; 
    @Column(name="SCHEDULE_ID_FK")
    private Long scheduleIdFk; 
    @Column(name="AGENDA_NUMBER")
    private Integer agendaNumber; 
    @Column(name="AGENDA_NAME")
    private String agendaName; 
    @Column(name="PDF_STORE")
    private byte[] pdfStore; 
    @Column(name="CREATE_TIMESTAMP")
    private Timestamp createTimestamp;
    @Column(name="CREATE_USER")
    private String createUser;
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="SCHEDULE_ID_FK", insertable=false, updatable=false)
    private CommitteeSchedule committeeSchedule;
    
    
    
    public ScheduleAgenda() { 

    } 
    
    public Long getScheduleAgendaId() {
        return scheduleAgendaId;
    }

    public void setScheduleAgendaId(Long scheduleAgendaId) {
        this.scheduleAgendaId = scheduleAgendaId;
    }

    public Integer getAgendaNumber() {
        return agendaNumber;
    }

    public void setAgendaNumber(Integer agendaNumber) {
        this.agendaNumber = agendaNumber;
    }

    public String getAgendaName() {
        return agendaName;
    }

    public void setAgendaName(String agendaName) {
        this.agendaName = agendaName;
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
        hashMap.put("scheduleAgendaId", this.getScheduleAgendaId());
        hashMap.put("scheduleIdFk", this.getScheduleIdFk());
        hashMap.put("agendaNumber", this.getAgendaNumber());
        hashMap.put("agendaName", this.getAgendaName());
        hashMap.put("pdfStore", this.getPdfStore());
        return hashMap;
    }

    public Long getScheduleIdFk() {
        return scheduleIdFk;
    }

    public void setScheduleIdFk(Long scheduleIdFk) {
        this.scheduleIdFk = scheduleIdFk;
    }

    public CommitteeSchedule getCommitteeSchedule() {
        return committeeSchedule;
    }

    public void setCommitteeSchedule(CommitteeSchedule committeeSchedule) {
        this.committeeSchedule = committeeSchedule;
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
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

import java.util.LinkedHashMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.irb.Protocol;

/**
 * 
 * This class is for member abstained from vote.
 */
@Entity 
@Table(name="PROTOCOL_VOTE_ABSTAINEES")
public class ProtocolVoteAbstainee extends KraPersistableBusinessObjectBase { 
    
    private static final long serialVersionUID = 6207540592702779518L;
    @Id 
    @Column(name="PROTOCOL_VOTE_ABSTAINEES_ID")
    private Integer protocolVoteAbstaineesId; 
    @Column(name="PROTOCOL_ID_FK")
    private Long protocolIdFk; 
    @Column(name="SCHEDULE_ID_FK")
    private Long scheduleIdFk; 
    @Column(name="PERSON_ID")
    private String personId; 
    @Type(type="yes_no")
    @Column(name="NON_EMPLOYEE_FLAG")
    private boolean nonEmployeeFlag; 
    @Column(name="COMMENTS")
    private String comments; 
    
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="PROTOCOL_ID_FK", insertable=false, updatable=false)
    private Protocol protocol;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="SCHEDULE_ID_FK", insertable=false, updatable=false)
    private CommitteeSchedule committeeSchedule;
    
    
    
    public ProtocolVoteAbstainee() { 

    } 
    
    public Integer getProtocolVoteAbstaineesId() {
        return protocolVoteAbstaineesId;
    }

    public void setProtocolVoteAbstaineesId(Integer protocolVoteAbstaineesId) {
        this.protocolVoteAbstaineesId = protocolVoteAbstaineesId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public boolean getNonEmployeeFlag() {
        return nonEmployeeFlag;
    }

    public void setNonEmployeeFlag(boolean nonEmployeeFlag) {
        this.nonEmployeeFlag = nonEmployeeFlag;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }


    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("protocolVoteAbstaineesId", this.getProtocolVoteAbstaineesId());
        hashMap.put("protocolIdFk", this.getProtocolIdFk());
        hashMap.put("scheduleIdFk", this.getScheduleIdFk());
        hashMap.put("personId", this.getPersonId());
        hashMap.put("nonEmployeeFlag", this.getNonEmployeeFlag());
        hashMap.put("comments", this.getComments());
        return hashMap;
    }

    public Long getProtocolIdFk() {
        return protocolIdFk;
    }

    public void setProtocolIdFk(Long protocolIdFk) {
        this.protocolIdFk = protocolIdFk;
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
    
}
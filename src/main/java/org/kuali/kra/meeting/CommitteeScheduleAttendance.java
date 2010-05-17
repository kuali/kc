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
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.committee.bo.CommitteeSchedule;

/**
 * 
 * This is a Bo class for committee schedule attendance.
 */
@Entity 
@Table(name="COMM_SCHEDULE_ATTENDANCE")
public class CommitteeScheduleAttendance extends KraPersistableBusinessObjectBase { 
    
    private static final long serialVersionUID = -6010677692125364332L;
    @Id 
    @Column(name="COMM_SCHEDULE_ATTENDANCE_ID")
    private Long commScheduleAttendanceId; 
    @Column(name="SCHEDULE_ID_FK")
    private Long scheduleIdFk; 
    @Column(name="PERSON_ID")
    private String personId; 
    @Type(type="yes_no")
    @Column(name="GUEST_FLAG")
    private boolean guestFlag; 
    @Type(type="yes_no")
    @Column(name="ALTERNATE_FLAG")
    private boolean alternateFlag; 
    @Column(name="ALTERNATE_FOR")
    private String alternateFor; 
    @Type(type="yes_no")
    @Column(name="NON_EMPLOYEE_FLAG")
    private boolean nonEmployeeFlag; 
    @Column(name="COMMENTS")
    private String comments; 
    @Column(name="PERSON_NAME")
    private String personName; 
    
    @Transient
    private String roleName; 

    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="SCHEDULE_ID_FK", insertable=false, updatable=false)
    private CommitteeSchedule committeeSchedule; 
    
    public CommitteeScheduleAttendance() { 

    } 
    

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public boolean getGuestFlag() {
        return guestFlag;
    }

    public void setGuestFlag(boolean guestFlag) {
        this.guestFlag = guestFlag;
    }

    public boolean getAlternateFlag() {
        return alternateFlag;
    }

    public void setAlternateFlag(boolean alternateFlag) {
        this.alternateFlag = alternateFlag;
    }

    public String getAlternateFor() {
        return alternateFor;
    }

    public void setAlternateFor(String alternateFor) {
        this.alternateFor = alternateFor;
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

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("commScheduleAttendanceId", this.getCommScheduleAttendanceId());
        hashMap.put("scheduleIdFk", this.getScheduleIdFk());
        hashMap.put("personId", this.getPersonId());
        hashMap.put("guestFlag", this.getGuestFlag());
        hashMap.put("alternateFlag", this.getAlternateFlag());
        hashMap.put("alternateFor", this.getAlternateFor());
        hashMap.put("nonEmployeeFlag", this.getNonEmployeeFlag());
        hashMap.put("comments", this.getComments());
        hashMap.put("personName", this.getPersonName());
        return hashMap;
    }

    public Long getScheduleIdFk() {
        return scheduleIdFk;
    }

    public void setScheduleIdFk(Long scheduleIdFk) {
        this.scheduleIdFk = scheduleIdFk;
    }

    public void setCommScheduleAttendanceId(Long commScheduleAttendanceId) {
        this.commScheduleAttendanceId = commScheduleAttendanceId;
    }


    public CommitteeSchedule getCommitteeSchedule() {
        return committeeSchedule;
    }


    public void setCommitteeSchedule(CommitteeSchedule committeeSchedule) {
        this.committeeSchedule = committeeSchedule;
    }


    public Long getCommScheduleAttendanceId() {
        return commScheduleAttendanceId;
    }


    public String getPersonName() {
        return personName;
    }


    public void setPersonName(String personName) {
        this.personName = personName;
    }


    public String getRoleName() {
        return roleName;
    }


    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
}

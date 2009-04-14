/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.committee.bo;

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
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.Rolodex;

@Entity 
@Table(name="COMM_SCHEDULE_ATTENDANCE")
public class CommitteeScheduleAttendance extends KraPersistableBusinessObjectBase { 

    private static final long serialVersionUID = 1455905507264239367L;

    @Id 
    @Column(name="ID")
    private Integer id; 

    @Column(name="SCHEDULE_ID")
    private String scheduleId; 

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
    
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="PERSON_ID", insertable=false, updatable=false)
    private Person person;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="ROLODEX_ID", insertable=false, updatable=false)
    private Rolodex rolodex;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="SCHEDULE_ID_NEW", insertable=false, updatable=false)
    private CommitteeSchedule committeeSchedule;
  
    public CommitteeScheduleAttendance() { 
    } 
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Rolodex getRolodex() {
        return rolodex;
    }

    public void setRolodex(Rolodex rolodex) {
        this.rolodex = rolodex;
    }

    public CommitteeSchedule getCommitteeSchedule() {
        return committeeSchedule;
    }

    public void setCommitteeSchedule(CommitteeSchedule committeeSchedule) {
        this.committeeSchedule = committeeSchedule;
    }

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("id", this.getId());
        hashMap.put("scheduleId", this.getScheduleId());
        hashMap.put("guestFlag", this.getGuestFlag());
        hashMap.put("alternateFlag", this.getAlternateFlag());
        hashMap.put("alternateFor", this.getAlternateFor());
        hashMap.put("nonEmployeeFlag", this.getNonEmployeeFlag());
        hashMap.put("comments", this.getComments());
        return hashMap;
    }
    
}
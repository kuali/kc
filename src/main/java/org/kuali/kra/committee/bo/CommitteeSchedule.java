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

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import java.util.LinkedHashMap;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

@Entity 
@Table(name="COMM_SCHEDULE")
public class CommitteeSchedule extends KraPersistableBusinessObjectBase { 

    private static final long serialVersionUID = -360139608123017188L;

    @javax.persistence.Id 
    @Column(name="ID")
    private Integer id; 

    @Column(name="SCHEDULE_ID")
    private String scheduleId; 
    
    @Column(name="COMMITTEE_ID")
    private Integer committeeId;
    
    @Column(name="SCHEDULED_DATE")
    private Date scheduledDate;
    
    @Column(name="PLACE")
    private String place;
    
    @Column(name="TIME")
    private Date time;
    
    @Column(name="PROTOCOL_SUB_DEADLINE")
    private Date protocolSubDeadline;
    
    @Column(name="SCHEDULE_STATUS_CODE")
    private Integer scheduleStatusCode;
    
    @Column(name="MEETING_DATE")
    private Date meetingDate;
    
    @Column(name="START_TIME")
    private Date startTime;
    
    @Column(name="END_TIME")
    private Date endTime;
    
    @Column(name="AGENDA_PROD_REV_DATE")
    private Date agendaProdRevDate;
    
    @Column(name="MAX_PROTOCOLS")
    private Integer maxProtocols;
    
    @Column(name="COMMENTS")
    private String comments; 
	
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="COMMITTEE_ID", insertable=false, updatable=false)
	private Committee committee; 
	
	public CommitteeSchedule() { 

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

	public Integer getCommitteeId() {
		return committeeId;
	}

	public void setCommitteeId(Integer committeeId) {
		this.committeeId = committeeId;
	}

	public Date getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Date getProtocolSubDeadline() {
		return protocolSubDeadline;
	}

	public void setProtocolSubDeadline(Date protocolSubDeadline) {
		this.protocolSubDeadline = protocolSubDeadline;
	}

	public Integer getScheduleStatusCode() {
		return scheduleStatusCode;
	}

	public void setScheduleStatusCode(Integer scheduleStatusCode) {
		this.scheduleStatusCode = scheduleStatusCode;
	}

	public Date getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(Date meetingDate) {
		this.meetingDate = meetingDate;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getAgendaProdRevDate() {
		return agendaProdRevDate;
	}

	public void setAgendaProdRevDate(Date agendaProdRevDate) {
		this.agendaProdRevDate = agendaProdRevDate;
	}

	public Integer getMaxProtocols() {
		return maxProtocols;
	}

	public void setMaxProtocols(Integer maxProtocols) {
		this.maxProtocols = maxProtocols;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Committee getCommittee() {
		return committee;
	}

	public void setCommittee(Committee committee) {
		this.committee = committee;
	}

	@SuppressWarnings("unchecked")
    @Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("id", getId());
		hashMap.put("scheduleId", getScheduleId());
		hashMap.put("committeeId", getCommitteeId());
		hashMap.put("scheduledDate", getScheduledDate());
		hashMap.put("place", getPlace());
		hashMap.put("time", getTime());
		hashMap.put("protocolSubDeadline", getProtocolSubDeadline());
		hashMap.put("scheduleStatusCode", getScheduleStatusCode());
		hashMap.put("meetingDate", getMeetingDate());
		hashMap.put("startTime", getStartTime());
		hashMap.put("endTime", getEndTime());
		hashMap.put("agendaProdRevDate", getAgendaProdRevDate());
		hashMap.put("maxProtocols", getMaxProtocols());
		hashMap.put("comments", getComments());
		return hashMap;
	}
	
}
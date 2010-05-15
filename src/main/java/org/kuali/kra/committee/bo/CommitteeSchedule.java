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
package org.kuali.kra.committee.bo;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.time.DateUtils;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.committee.web.struts.form.schedule.DayOfWeek;
import org.kuali.kra.committee.web.struts.form.schedule.Time12HrFmt;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.meeting.CommScheduleActItem;
import org.kuali.kra.meeting.CommitteeScheduleAttendance;
import org.kuali.kra.meeting.CommitteeScheduleMinute;

/**
 * This is BO class to support CommitteeScheulde. It has three transient field to support UI.
 */
@Entity 
@Table(name="COMM_SCHEDULE")
public class CommitteeSchedule extends CommitteeAssociate { 
    
    private static final long serialVersionUID = -360139608123017188L;
    
    @Transient
    private Time12HrFmt viewTime;
    
    @Transient
    private boolean filter = true;
    
    @Transient
    private boolean delete = false;
    
    @Transient
    private transient boolean selected = false;
    
    @javax.persistence.Id 
    @Column(name="ID")
    private Long id; 

    @Column(name="SCHEDULE_ID")
    private String scheduleId; 
    
    @Column(name="SCHEDULED_DATE")
    private Date scheduledDate;
    
    @Column(name="PLACE")
    private String place;
    
    @Column(name="TIME")
    private Timestamp time;
    
    @Column(name="PROTOCOL_SUB_DEADLINE")
    private Date protocolSubDeadline;
    
    @Column(name="SCHEDULE_STATUS_CODE")
    private Integer scheduleStatusCode;
    
    @Column(name="MEETING_DATE")
    private Date meetingDate;
    
    @Column(name="START_TIME")
    private Timestamp startTime;
    
    @Column(name="END_TIME")
    private Timestamp endTime;
    
    @Column(name="AGENDA_PROD_REV_DATE")
    private Date agendaProdRevDate;
    
    @Column(name="MAX_PROTOCOLS")
    private Integer maxProtocols;
    
    @Column(name="COMMENTS")
    private String comments; 
	
// TODO : recursive reference    
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="COMMITTEE_ID_FK", insertable=false, updatable=false)
	private Committee committee; 
	
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="SCHEDULE_STATUS_CODE", insertable=false, updatable=false)
    private ScheduleStatus scheduleStatus;
    
    private List<CommitteeScheduleAttendance> committeeScheduleAttendances;        
    private List<CommitteeScheduleMinute> committeeScheduleMinutes;        
    @SkipVersioning
    private List<ProtocolSubmission> protocolSubmissions;        
    private List<CommScheduleActItem>  commScheduleActItems;
    //TODO revisit required during meeting management to map Protocol
    @SkipVersioning
    private List<Protocol> protocols;
    private Time12HrFmt viewStartTime;
    private Time12HrFmt viewEndTime;

    
    public CommitteeSchedule() { 
        setCommitteeScheduleAttendances(new ArrayList<CommitteeScheduleAttendance>()); 
        setCommScheduleActItems(new ArrayList<CommScheduleActItem>()); 
        setProtocolSubmissions(new ArrayList<ProtocolSubmission>()); 
        setCommitteeScheduleMinutes(new ArrayList<CommitteeScheduleMinute>()); 
	} 
	
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }	
	
	public String getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
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

	/**
	 * This method is BO persistent accessor method, which adds Time to Date on each call. 
	 * In support to UI.
	 * @return
	 */
	public Timestamp getTime() {
	    java.util.Date dt = new java.util.Date(this.time.getTime());
	    dt = DateUtils.round(dt, Calendar.DAY_OF_MONTH);
	    if (viewTime != null) {
            dt = new java.util.Date(0); // 12/31/1969 19:00:00
            dt = DateUtils.round(dt, Calendar.DAY_OF_MONTH);
	        dt = DateUtils.addMinutes(dt, viewTime.findMinutes()); // to set it to 1970-01-01
            //dt = DateUtils.addMinutes(dt, viewTime.findMinutes());
            //dt = DateUtils.addMinutes(dt, getViewTime().findMinutes());
	        this.time = new Timestamp(dt.getTime());
	    }
	    return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	public Timestamp getActualTime() {
	    return time;
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

	public Timestamp getStartTime() {
        if (startTime == null || startTime.getTime() == 0) {
            java.util.Date dt = new java.util.Date(0);
            dt = DateUtils.round(dt, Calendar.DAY_OF_MONTH);
            if (viewStartTime != null) {
                dt = DateUtils.addMinutes(dt, viewStartTime.findMinutes());
                // dt = DateUtils.addMinutes(dt, getViewTime().findMinutes());
            }
            this.startTime = new Timestamp(dt.getTime());
        }

        return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
        if (endTime == null || endTime.getTime() == 0) {
            java.util.Date dt = new java.util.Date(0); // set to 1969/12/31 19:00 ?
            dt = DateUtils.round(dt, Calendar.DAY_OF_MONTH); // force it to 1970-01-01
            if (viewEndTime != null) {
                dt = DateUtils.addMinutes(dt, viewEndTime.findMinutes());
                // dt = DateUtils.addMinutes(dt, getViewTime().findMinutes());
            } 
            this.endTime = new Timestamp(dt.getTime());
        }
        return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Date getAgendaProdRevDate() {
		return agendaProdRevDate;
	}

	public void setAgendaProdRevDate(Date agendaProdRevDate) {
		this.agendaProdRevDate = agendaProdRevDate;
	}

	public Integer getMaxProtocols() {
        if (maxProtocols == null) {
            maxProtocols = committee.getMaxProtocols();
        }
		return maxProtocols;
	}

	public void setMaxProtocols(Integer maxProtocols) {
	    if (maxProtocols == null) {
	        maxProtocols = 0;
	    }
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

    public ScheduleStatus getScheduleStatus() {
        return scheduleStatus;
    }

    public void setScheduleStatus(ScheduleStatus scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }	
	
    public void setDayOfWeek(String dayOfWeek){
        //Do nothing, struts needs it on refresh
    }
    
    /**
     * This UI support method to find day Of week from BO's persistent field scheduledDate.
     * @return
     */
    public String getDayOfWeek() {
        Calendar cl = new GregorianCalendar();
        cl.setTime(scheduledDate);
        DayOfWeek dayOfWeek = null;        
        switch (cl.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY:
                dayOfWeek = DayOfWeek.Sunday;
                break;
            case Calendar.MONDAY:
                dayOfWeek = DayOfWeek.Monday;
                break;
            case Calendar.TUESDAY:
                dayOfWeek = DayOfWeek.Tuesday;
                break;
            case Calendar.WEDNESDAY:
                dayOfWeek = DayOfWeek.Wednesday;
                break;
            case Calendar.THURSDAY:
                dayOfWeek = DayOfWeek.Thursday;
                break;
            case Calendar.FRIDAY:
                dayOfWeek = DayOfWeek.Friday;
                break;
            case Calendar.SATURDAY:
                dayOfWeek = DayOfWeek.Saturday;
                break;
        }
        return dayOfWeek.name().toUpperCase();
    }
    
    public void setFilter(boolean filter) {
        this.filter = filter;
    }

    public boolean getFilter() {
        return filter;
    }    
    
    public Time12HrFmt getViewTime() {
        if(null == this.viewTime)
            this.viewTime = new Time12HrFmt(time);
        return viewTime;
    }

    public void setViewTime(Time12HrFmt viewTime) {
        this.viewTime = viewTime;
    }
    
    public boolean getDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }        
    
    public List<Protocol> getProtocols() {
        return protocols;
    }

    public void setProtocols(List<Protocol> protocols) {
        this.protocols = protocols;
    }
    
    public List<CommitteeScheduleAttendance> getCommitteeScheduleAttendances() {
        return committeeScheduleAttendances;
    }

    public void setCommitteeScheduleAttendances(List<CommitteeScheduleAttendance> committeeScheduleAttendances) {
        this.committeeScheduleAttendances = committeeScheduleAttendances;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        CommitteeSchedule committeeSchedule = (CommitteeSchedule) obj;
        if (this.getId() != null && this.getId().equals(committeeSchedule.getId())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + (this.getId() == null ? 0 : this.getId().hashCode());
        return result;     
    }
    
    @SuppressWarnings("unchecked")
    @Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = super.toStringMapper();
		hashMap.put("id", getId());
		hashMap.put("scheduleId", getScheduleId());
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
	
    public void resetPersistenceState() {
        setId(null);
    }

    public List<ProtocolSubmission> getProtocolSubmissions() {
        return protocolSubmissions;
    }

    public void setProtocolSubmissions(List<ProtocolSubmission> protocolSubmissions) {
        this.protocolSubmissions = protocolSubmissions;
    }
    public Time12HrFmt getViewStartTime() {
        if (null == this.viewStartTime) {
            this.viewStartTime = new Time12HrFmt(startTime);
        }
        return viewStartTime;
    }

    public void setViewStartTime(Time12HrFmt viewStartTime) {
        this.viewStartTime = viewStartTime;
    }

    public Time12HrFmt getViewEndTime() {
        if (null == this.viewEndTime) {
            this.viewEndTime = new Time12HrFmt(endTime);
        }
        return viewEndTime;
    }

    public void setViewEndTime(Time12HrFmt viewEndTime) {
        this.viewEndTime = viewEndTime;
    }

    public List<CommScheduleActItem> getCommScheduleActItems() {
        return commScheduleActItems;
    }

    public void setCommScheduleActItems(List<CommScheduleActItem> commScheduleActItems) {
        this.commScheduleActItems = commScheduleActItems;
    }

    public List<CommitteeScheduleMinute> getCommitteeScheduleMinutes() {
        return committeeScheduleMinutes;
    }

    public void setCommitteeScheduleMinutes(List<CommitteeScheduleMinute> committeeScheduleMinutes) {
        this.committeeScheduleMinutes = committeeScheduleMinutes;
    }

}
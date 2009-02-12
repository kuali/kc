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

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.time.DateUtils;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.committee.web.struts.form.schedule.ScheduleData;

@Entity 
@Table(name="COMM_SCHEDULE")
public class CommitteeSchedule extends KraPersistableBusinessObjectBase { 
    
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(CommitteeSchedule.class);
    
    private static final long serialVersionUID = -360139608123017188L;
    
    public static final String SUNDAY = "Sunday";
    
    public static final String MONDAY = "Monday";
    
    public static final String TUESDAY = "Tuesday";
    
    public static final String WEDNESDAY = "Wednesday";
    
    public static final String THURSDAY = "Thursday";
    
    public static final String FRIDAY = "Friday";
    
    public static final String SATURDAY = "Saturday";
    
    private String displayTime;
    
    private String meridiem;
    
    @javax.persistence.Id 
    @Column(name="ID")
    private Long id; 

    @Column(name="SCHEDULE_ID")
    private String scheduleId; 
    
    @Column(name="COMMITTEE_ID")
    private Long committeeId;
    
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
	
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="SCHEDULE_STATUS_CODE", insertable=false, updatable=false)
    private ScheduleStatus scheduleStatus;
    
	public CommitteeSchedule() { 

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

	public Long getCommitteeId() {
		return committeeId;
	}

	public void setCommitteeId(Long committeeId) {
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

	public Timestamp getTime() {
	    java.util.Date dt = new java.util.Date(this.time.getTime());
	    dt = DateUtils.round(dt, Calendar.DAY_OF_MONTH);
	    dt = DateUtils.addMinutes(dt, findMinutes(displayTime, meridiem));
	    this.time = new Timestamp(dt.getTime());
	    LOG.info("TIME getTime():" + time.toString());
	    return time;
	}

	public void setTime(Timestamp time) {
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

    public ScheduleStatus getScheduleStatus() {
        return scheduleStatus;
    }

    public void setScheduleStatus(ScheduleStatus scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }	
	
    public void setDayOfWeek(String dayOfWeek){
        //Do nothing, struts needs it on refresh
    }
    
    public String getDayOfWeek() {
        Calendar cl = new GregorianCalendar();
        cl.setTime(time);
        String dayOfWeek = null;
        switch(cl.get(Calendar.DAY_OF_WEEK)) {
            case 1: dayOfWeek = SUNDAY; 
                    break;
            case 2: dayOfWeek = MONDAY; 
                    break;
            case 3: dayOfWeek = TUESDAY; 
                    break;
            case 4: dayOfWeek = WEDNESDAY; 
                    break;
            case 5: dayOfWeek = THURSDAY; 
                    break;
            case 6: dayOfWeek = FRIDAY; 
                    break;
            case 7: dayOfWeek = SATURDAY; 
                    break;
        }
        return dayOfWeek;
    }
    
    public void setDisplayTime(String displayTime){
      this.displayTime = displayTime;
    }
    
    public String getDisplayTime() {
        Calendar cl = new GregorianCalendar();
        cl.setTime(time);
        StringBuilder str = new StringBuilder();
        LOG.info("Timestamp :" + time.toString());
        LOG.info("cl.get(Calendar.AM_PM) :" + cl.get(Calendar.AM_PM));        
        int hr = cl.get(Calendar.HOUR_OF_DAY);
        hr = (hr>12?hr-12:hr);
        
        if (hr < 10) 
            str.append("0").append(hr);
        else
            str.append(hr);
       
        str.append(":");
        int min = cl.get(Calendar.MINUTE);
        
        if(min < 10) 
            str.append("0").append(min);
        else
            str.append(min);
        
        return str.toString();
    }
    
    public void setMeridiem(String meridiem) {
        this.meridiem = meridiem;
    }
    
    public String getMeridiem() {
        Calendar cl = new GregorianCalendar();
        cl.setTime(time);
        String str = null;       
        if(cl.get(Calendar.AM_PM) == 0) 
            str = "AM";
        else
            str = "PM";
        return str;
    }
    
    private int findMinutes(String time, String meridiem) {        
        
        String[] result = time.split(":");
        int hrs = new Integer(result[0]);
        int min = new Integer(result[1]);
                
        boolean am_pm = false;
        if(meridiem.equalsIgnoreCase("AM"))
            am_pm = true;
          
        LOG.info("DATE hrs:" + hrs + " :min: " + min + " :AM: " + am_pm);
        
        if(hrs == 12) {
            return 0 + min + (am_pm?0:12*60); 
        }
        return (hrs * 60)  + min + (am_pm?0:12*60);
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
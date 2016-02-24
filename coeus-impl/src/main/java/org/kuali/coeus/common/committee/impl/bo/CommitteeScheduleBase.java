/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.common.committee.impl.bo;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.meeting.*;
import org.kuali.coeus.common.committee.impl.web.struts.form.schedule.DayOfWeek;
import org.kuali.coeus.common.committee.impl.web.struts.form.schedule.Time12HrFmt;
import org.kuali.coeus.common.permissions.impl.PermissionableKeys;
import org.kuali.coeus.common.framework.auth.perm.Permissionable;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionLiteBase;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This is BO class to support CommitteeScheulde. It has three transient field to support UI.
 */
public abstract class CommitteeScheduleBase<CS extends CommitteeScheduleBase<CS, CMT, PS, CSM>,
                                              CMT extends CommitteeBase<CMT, ?, CS>, 
                                              PS extends ProtocolSubmissionLiteBase,
                                              CSM extends CommitteeScheduleMinuteBase<CSM, CS>>

                                              extends CommitteeAssociateBase implements Comparable<CS>, Permissionable{

    private static final long serialVersionUID = -360139608123017188L;
    public static final Long DEFAULT_SCHEDULE_ID = 9999999999L;

    private Time12HrFmt viewTime;
    
    private boolean filter = true;
    private boolean delete = false;
    private transient boolean selected = false;
    private Long id; 
    private String scheduleId; 
    private Date scheduledDate;
    private String place;
    private Timestamp time;

    private Date protocolSubDeadline;
    private Integer scheduleStatusCode;
    private Date meetingDate;
    private Timestamp startTime;
    private Timestamp endTime;
    private Date agendaProdRevDate;
    private Integer maxProtocols;
    private String comments; 
    private boolean availableToReviewers;
	
// TODO : recursive reference    
//	private CMT committee; 
    private ScheduleStatus scheduleStatus;
    
    //TODO revisit required during meeting management to map ProtocolBase
    @SkipVersioning
    private List<ProtocolBase> protocols;
    private Time12HrFmt viewStartTime;
    private Time12HrFmt viewEndTime;

    // Following are related to meeting data.  They will not be serialized to doc content.
    // So, keep the "transient". Also, they are not versioned with version service.   
    // Merging these data from old version committee to the new version committee at the time of "approval" of new version committee.
    private transient List<CommitteeScheduleAttendanceBase> committeeScheduleAttendances;        
    private transient List<CSM> committeeScheduleMinutes;  
    private transient List<CommitteeScheduleAttachmentsBase> committeeScheduleAttachments;
    @SkipVersioning
    private transient List<PS> protocolSubmissions;        
    private transient List<CommScheduleActItemBase>  commScheduleActItems;
    private transient List<CommScheduleMinuteDocBase> minuteDocs;        
    private transient List<ScheduleAgendaBase> scheduleAgendas;        

    
    public CommitteeScheduleBase() { 
        setCommitteeScheduleAttendances(new ArrayList<CommitteeScheduleAttendanceBase>()); 
        setCommScheduleActItems(new ArrayList<CommScheduleActItemBase>()); 
        setProtocolSubmissions(new ArrayList<PS>()); 
        setCommitteeScheduleMinutes(new ArrayList<CSM>()); 
        setMinuteDocs(new ArrayList<CommScheduleMinuteDocBase>()); 
        setScheduleAgendas(new ArrayList<ScheduleAgendaBase>()); 
        setCommitteeScheduleAttachments(new ArrayList<CommitteeScheduleAttachmentsBase>());
	} 
    
    // to be called only when creating a light-weight version of this schedule. 
    // this method will nullify all "heavy" references. 
    public void nullifyHeavyMeetingData() {
        this.protocols = null;
        this.committeeScheduleAttendances = null;        
        this.committeeScheduleMinutes = null;  
        this.committeeScheduleAttachments = null;
        this.protocolSubmissions = null;        
        this.commScheduleActItems = null;
        this.minuteDocs = null;        
        this.scheduleAgendas = null;  
    }
    
    // this method will copy over the editable 'light' references and primitives 
    // from the sourceSchedule onto this schedule.
    public void copyLightDataFrom(CS sourceSchedule) {
        this.filter = sourceSchedule.getFilter();
        this.delete = sourceSchedule.getDelete();
        this.selected = sourceSchedule.isSelected();
 
        this.scheduledDate = sourceSchedule.getScheduledDate();
        this.place = sourceSchedule.getPlace();
        this.time = sourceSchedule.getTime();

        this.protocolSubDeadline = sourceSchedule.getProtocolSubDeadline();
        this.scheduleStatusCode = sourceSchedule.getScheduleStatusCode();
        this.meetingDate = sourceSchedule.getMeetingDate();
        this.startTime = sourceSchedule.getStartTime();
        this.endTime = sourceSchedule.getEndTime();
        this.agendaProdRevDate = sourceSchedule.getAgendaProdRevDate();
        this.maxProtocols = sourceSchedule.getMaxProtocols();
        this.comments = sourceSchedule.getComments();
        this.availableToReviewers = sourceSchedule.isAvailableToReviewers();
        
        this.viewStartTime = sourceSchedule.getViewStartTime();
        this.viewEndTime = sourceSchedule.getViewEndTime();
    }
        
    /*
     * check if there is any meeting data in this schedule.
     */
    public boolean hasMeetingData() {
        boolean retVal = false; 
        retVal = retVal || CollectionUtils.isNotEmpty(this.getCommitteeScheduleAttendances());
        retVal = retVal || CollectionUtils.isNotEmpty(this.getCommitteeScheduleMinutes());
        retVal = retVal || CollectionUtils.isNotEmpty(this.getCommScheduleActItems());
        retVal = retVal || CollectionUtils.isNotEmpty(this.getMinuteDocs());
        retVal = retVal || CollectionUtils.isNotEmpty(this.getScheduleAgendas());
        retVal = retVal || CollectionUtils.isNotEmpty(this.getLatestProtocolSubmissions());
        return retVal;
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
		if(this.time != null) {
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
        if (maxProtocols == null && getParentCommittee() != null) {
            maxProtocols = getParentCommittee().getMaxProtocols();
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

	public boolean isAvailableToReviewers() {
        return availableToReviewers;
    }

    public void setAvailableToReviewers(boolean availableToReviewers) {
        this.availableToReviewers = availableToReviewers;
    }


    public abstract CMT getParentCommittee();

	
	public abstract void setCommittee(CMT committee);

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
    
    public List<ProtocolBase> getProtocols() {
        return protocols;
    }

    public void setProtocols(List<ProtocolBase> protocols) {
        this.protocols = protocols;
    }
    
    public List<CommitteeScheduleAttendanceBase> getCommitteeScheduleAttendances() {
        return committeeScheduleAttendances;
    }

    public void setCommitteeScheduleAttendances(List<CommitteeScheduleAttendanceBase> committeeScheduleAttendances) {
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
        CS committeeSchedule = (CS) obj;
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
    
    /**
     * Compares the schedule dates for two instances of <code>CommitteeScheduleBase</code>
     * in order to enforce an ordering.
     * 
     * @param other The CommitteeScheduleBase to be compared.
     * @return the result of comparing this <code>scheduledDate</code> to the other <code>scheduledDate</code>
     */
    public int compareTo(CS other) {
        int compareResult;
        
        if (getScheduledDate() == null) {
            if (other.getScheduledDate() == null) {
                compareResult = 0;                
            } else {
                compareResult = -1;
            }
        }  else {
            if (other.getScheduledDate() == null) {
                compareResult = 1;
            } else {
                compareResult = getScheduledDate().compareTo(other.getScheduledDate());
            }
        }
        return compareResult;
    }
    
    public void resetPersistenceState() {
        setId(null);
    }

    public List<PS> getProtocolSubmissions() {
        return protocolSubmissions;
    }

    public List<PS> getLatestProtocolSubmissions() {
        return protocolSubmissions.stream()
                .filter(submission -> submission.isProtocolActive() || StringUtils.equals(submission.getProtocolStatusCode(), ProtocolStatus.DISAPPROVED))
                .collect(Collectors.toList());
    }

    public void setProtocolSubmissions(List<PS> protocolSubmissions) {
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

    public List<CommScheduleActItemBase> getCommScheduleActItems() {
        return commScheduleActItems;
    }

    public void setCommScheduleActItems(List<CommScheduleActItemBase> commScheduleActItems) {
        this.commScheduleActItems = commScheduleActItems;
    }

    public List<CSM> getCommitteeScheduleMinutes() {
        if (committeeScheduleMinutes != null) {
            Collections.sort(committeeScheduleMinutes, new Comparator<CSM>() {
                @Override
                public int compare(CSM o1, CSM o2) {
                    return o1.getEntryNumber().compareTo(o2.getEntryNumber());
                }
            });
        }
        return committeeScheduleMinutes;
    }

    public void setCommitteeScheduleMinutes(List<CSM> committeeScheduleMinutes) {
        this.committeeScheduleMinutes = committeeScheduleMinutes;
    }

    public List<CommitteeScheduleAttachmentsBase> getCommitteeScheduleAttachments() {
        return committeeScheduleAttachments;
    }

    public void setCommitteeScheduleAttachments(List<CommitteeScheduleAttachmentsBase> committeeScheduleAttachments) {
        this.committeeScheduleAttachments = committeeScheduleAttachments;
    }

    public List<CommScheduleMinuteDocBase> getMinuteDocs() {
        return minuteDocs;
    }

    public void setMinuteDocs(List<CommScheduleMinuteDocBase> minuteDocs) {
        this.minuteDocs = minuteDocs;
    }

    public List<ScheduleAgendaBase> getScheduleAgendas() {
        return scheduleAgendas;
    }

    public void setScheduleAgendas(List<ScheduleAgendaBase> scheduleAgendas) {
        this.scheduleAgendas = scheduleAgendas;
    }

    //Permissionable interface
    public String getDocumentKey() {
        return PermissionableKeys.COMMITTEE_SCHEDULE_KEY;
    }

    public String getDocumentNumberForPermission() {
        return getScheduleId();
    }

    public String getDocumentRoleTypeCode() {
        return null;
    }

    public String getLeadUnitNumber() {

        return null;
    }

    public String getNamespace() {
        return getParentCommittee() != null ? getParentCommittee().getNamespace() : null;
    }

    public List<String> getRoleNames() {
        List<String> roleNames = new ArrayList<String>();
        roleNames.add(RoleConstants.IRB_ADMINISTRATOR);
        roleNames.add(RoleConstants.IRB_REVIEWER);
        return roleNames;
    }

    public void populateAdditionalQualifiedRoleAttributes(Map<String, String> qualifiedRoleAttributes) {
        qualifiedRoleAttributes.put(KcKimAttributes.COMMITTEE, getParentCommittee().getCommitteeId());
        qualifiedRoleAttributes.put(KcKimAttributes.COMMITTEESCHEDULE, this.getScheduleId());
    }
    
    
    /**
     * This method returns true if the given personId has a membership in the schedule's parent committee that is active 
     * for the schedule date, and false otherwise. Also returns false if the personId parameter or the 
     * parent committee of the schedule is null. 
     * @param personId
     * @return
     */
    public boolean isActiveFor(String personId) {
        boolean retVal = false;
        CMT parentCommittee = this.getParentCommittee();
        if(parentCommittee != null){
            CommitteeMembershipBase member = parentCommittee.getCommitteeMembershipFor(personId);
            if(member != null) {
                retVal = member.isActive(this.scheduledDate);
            }
        }
        return retVal;        
    }
    
    /**
     * This method returns true if and only if the schedule date has passed
     * @return
     */
    public boolean isScheduleDateInPast(){
        boolean retVal = false;
        Date currentDate = org.kuali.coeus.sys.framework.util.DateUtils.clearTimeFields(new Date(System.currentTimeMillis()));
        if(this.scheduledDate != null) {
            retVal = this.scheduledDate.before(currentDate);
        }
        return retVal;
    }

}

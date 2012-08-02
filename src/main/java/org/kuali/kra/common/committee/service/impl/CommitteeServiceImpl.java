/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.common.committee.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.ResearchArea;
import org.kuali.kra.common.committee.bo.CommonCommittee;
import org.kuali.kra.common.committee.bo.CommitteeMembership;
import org.kuali.kra.common.committee.bo.CommitteeMembershipRole;
import org.kuali.kra.common.committee.bo.CommitteeResearchArea;
import org.kuali.kra.common.committee.bo.CommonCommitteeSchedule;
import org.kuali.kra.common.committee.meeting.CommScheduleActItem;
import org.kuali.kra.common.committee.meeting.CommScheduleMinuteDoc;
import org.kuali.kra.common.committee.meeting.CommitteeScheduleAttendance;
import org.kuali.kra.common.committee.meeting.CommitteeScheduleMinute;
import org.kuali.kra.common.committee.meeting.ScheduleAgenda;
import org.kuali.kra.common.committee.service.CommonCommitteeService;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.SequenceAccessorService;
import org.kuali.rice.krad.util.ObjectUtils;

/**
 * The Committee Service implementation.
 */
public class CommitteeServiceImpl implements CommonCommitteeService {

    private static final String COMMITTEE_ID = "committeeId";
    private static final String NO_PLACE = "[no location]";

    private BusinessObjectService businessObjectService;
    private SequenceAccessorService sequenceAccessorService;

    /**
     * Set the Business Object Service.
     * 
     * @param businessObjectService the Business Object Service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * @see org.kuali.kra.common.committee.service.CommonCommitteeService#getCommitteeById(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public CommonCommittee getCommitteeById(String committeeId) {
        CommonCommittee committee = null;
        if (!StringUtils.isBlank(committeeId)) {
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put(COMMITTEE_ID, committeeId);
            Collection<CommonCommittee> committees = businessObjectService.findMatching(CommonCommittee.class, fieldValues);
            if (committees.size() > 0) {
                /*
                 * Return the most recent approved committee (i.e. the committee version with the highest 
                 * sequence number that is approved/in the database).
                 */
                committee = (CommonCommittee) Collections.max(committees);
            }
        }
        return committee;
    }

    /**
     * @see org.kuali.kra.common.committee.service.CommonCommitteeService#addResearchAreas(org.kuali.kra.common.committee.bo.CommonCommittee,
     *      java.util.Collection)
     */
    public void addResearchAreas(CommonCommittee committee, Collection<ResearchArea> researchAreas) {
        for (ResearchArea researchArea : researchAreas) {
            if (!hasResearchArea(committee, researchArea)) {
                addCommitteeResearchArea(committee, researchArea);
            }
        }
    }

    /**
     * Does the committee already have this research area?
     * 
     * @param committee
     * @param researchArea
     * @return true if the committee has the research area; otherwise false
     */
    protected boolean hasResearchArea(CommonCommittee committee, ResearchArea researchArea) {
        for (CommitteeResearchArea committeeResearchArea : committee.getCommitteeResearchAreas()) {
            if (StringUtils.equals(committeeResearchArea.getResearchAreaCode(), researchArea.getResearchAreaCode())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add a research area to the committee.
     * 
     * @param committee
     * @param researchArea
     */
    protected void addCommitteeResearchArea(CommonCommittee committee, ResearchArea researchArea) {
        CommitteeResearchArea committeeResearchArea = new CommitteeResearchArea();
        committeeResearchArea.setCommittee(committee);
        committeeResearchArea.setCommitteeIdFk(committee.getId());
        committeeResearchArea.setResearchArea(researchArea);
        committeeResearchArea.setResearchAreaCode(researchArea.getResearchAreaCode());
        committee.getCommitteeResearchAreas().add(committeeResearchArea);
    }

    /**
     * @see org.kuali.kra.common.committee.service.CommonCommitteeService#getAvailableCommitteeDates(java.lang.String)
     */
    public List<KeyValue> getAvailableCommitteeDates(String committeeId) {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue("", "select"));
        CommonCommittee committee = getCommitteeById(committeeId);
        if (committee != null) {
            List<CommonCommitteeSchedule> schedules = committee.getCommitteeSchedules();
            Collections.sort(schedules);
            for (CommonCommitteeSchedule schedule : schedules) {
                if (isOkayToScheduleReview(committee, schedule)) {
                    keyValues.add(new ConcreteKeyValue(schedule.getScheduleId(), getDescription(schedule)));
                }
            }
        }
        return keyValues;
    }

    /**
     * Is it OK to schedule a review for the given committee and schedule?
     * @param committee
     * @param schedule
     * @return
     */
    protected boolean isOkayToScheduleReview(CommonCommittee committee, CommonCommitteeSchedule schedule) {
        Calendar now = getCalendar(new Date());
        Calendar scheduleCalendar = getCalendar(schedule.getScheduledDate());
       // now.add(Calendar.DAY_OF_MONTH, committee.getAdvancedSubmissionDaysRequired());
        boolean dateRangeOK = now.compareTo(getCalendar(schedule.getProtocolSubDeadline())) <= 0;
        boolean statusOK = "Scheduled".equals(schedule.getScheduleStatus().getDescription());
        return dateRangeOK && statusOK;
    }
    
    /*
     * Create a calendar without hour, minutes, seconds, or milliseconds.
     */
    protected Calendar getCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    /**
     * Get the date/place/time description that will be displayed in 
     * the drop-down menu for the user.
     * @param schedule
     * @return
     */
    protected String getDescription(CommonCommitteeSchedule schedule) {
        Date date = schedule.getScheduledDate();
        final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        final SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
        StringBuffer sb = new StringBuffer(dateFormat.format(date));
        sb.append( ", ");
        if (schedule.getPlace() == null) {
            sb.append(NO_PLACE);
        } else {
            sb.append(schedule.getPlace());
        }
        sb.append(", ").append(timeFormat.format(schedule.getActualTime()));
        return sb.toString();
    }

    /**
     * @see org.kuali.kra.common.committee.service.CommonCommitteeService#getAvailableMembers(java.lang.String, java.lang.String)
     */
    public List<CommitteeMembership> getAvailableMembers(String committeeId, String scheduleId) {
        if (StringUtils.isBlank(scheduleId)) {
            return getAvailableMembersNow(committeeId);
        }
        List<CommitteeMembership> availableMembers = new ArrayList<CommitteeMembership>();
        CommonCommittee committee = getCommitteeById(committeeId);
        if (committee != null) {
            CommonCommitteeSchedule schedule = getCommitteeSchedule(committee, scheduleId);
            if (schedule != null) {
                List<CommitteeMembership> members = committee.getCommitteeMemberships();
                for (CommitteeMembership member : members) {
                    if (isMemberAvailable(member, schedule.getScheduledDate())) {
                        availableMembers.add(member);
                    }
                }
            }
        }
        return availableMembers;
    }
    
    /**
     * @see org.kuali.kra.common.committee.service.CommonCommitteeService#getAvailableMembers(java.lang.String, java.lang.String)
     */
    public List<CommitteeMembership> getAvailableMembersNow(String committeeId) {
        List<CommitteeMembership> availableMembers = new ArrayList<CommitteeMembership>();
        CommonCommittee committee = getCommitteeById(committeeId);
        if (committee != null) {
            List<CommitteeMembership> members = committee.getCommitteeMemberships();
            Date currentDate = new Date(); 
            for (CommitteeMembership member : members) {
                if (isMemberAvailable(member, currentDate)) {
                    availableMembers.add(member);
                }
            }
        }
        return availableMembers;
    }
    
    /**
     * Is the member available for the given schedule meeting date?
     * The member must have a role for that date.
     * @param member the member
     * @param scheduledDate the date of the meeting
     * @return true if the member will be at the meeting; otherwise false
     * TODO: This method calls member.isActive() and then carries out date checking that has 
     *  already been performed in member.isActive()? Perhaps this method should be removed and 
     *  its invocation in getAvailableMembers() above should be replaced by a single 
     *  call to member.isActive(schedule.getScheduledDate())
     */
    protected boolean isMemberAvailable(CommitteeMembership member, Date scheduledDate) {
        java.sql.Date sqlDate = new java.sql.Date(scheduledDate.getTime());
        if (member.isActive(sqlDate)) {
            Calendar scheduleCalendar = getCalendar(scheduledDate);
            List<CommitteeMembershipRole> roles = member.getMembershipRoles();
            for (CommitteeMembershipRole role : roles) {
                Calendar startCalendar = getCalendar(role.getStartDate());
                Calendar endCalendar = getCalendar(role.getEndDate());
                if (scheduleCalendar.compareTo(startCalendar) >= 0 &&
                    scheduleCalendar.compareTo(endCalendar) <= 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @see org.kuali.kra.common.committee.service.CommonCommitteeService#getCommitteeSchedule(org.kuali.kra.common.committee.bo.CommonCommittee, java.lang.String)
     */
    public CommonCommitteeSchedule getCommitteeSchedule(CommonCommittee committee, String scheduleId) {
        //TODO the code belongs in and should be moved to Committee BO?
        List<CommonCommitteeSchedule> schedules = committee.getCommitteeSchedules();
        for (CommonCommitteeSchedule schedule : schedules) {
            if (StringUtils.equals(schedule.getScheduleId(), scheduleId)) {
                return schedule;
            }
        }
        return null;
    }
    
    /**
     * 
     * @see org.kuali.kra.common.committee.service.CommonCommitteeService#mergeCommitteeSchedule(java.lang.String)
     */
    public List<CommonCommitteeSchedule> mergeCommitteeSchedule(String committeeId) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(COMMITTEE_ID, committeeId);
        List<CommonCommittee> committees = (List<CommonCommittee>) businessObjectService.findMatching(CommonCommittee.class, fieldValues);
        Collections.sort(committees);
        CommonCommittee newCommittee = committees.get(committees.size() - 1);
        CommonCommittee oldCommittee = committees.get(committees.size() - 2);
        List<CommonCommitteeSchedule> copiedSchedules = new ArrayList<CommonCommitteeSchedule>();
        if (CollectionUtils.isNotEmpty(newCommittee.getCommitteeSchedules())
                || CollectionUtils.isNotEmpty(oldCommittee.getCommitteeSchedules())) {
            copiedSchedules = copySchedules(newCommittee.getCommitteeSchedules(), oldCommittee.getCommitteeSchedules());
        }
        return copiedSchedules;
    }

    
    /*
     * copy schedules from old committee to new committee if the old schedule has meeting data.
     * All newly added schedules, i.e. those that are present only in the new schedule listing, will be automatically included in the return list.
     */
    protected List<CommonCommitteeSchedule> copySchedules(List<CommonCommitteeSchedule> newSchedules, List<CommonCommitteeSchedule> oldSchedules) {
        List<CommonCommitteeSchedule> copiedSchedules = new ArrayList<CommonCommitteeSchedule>();
        for (CommonCommitteeSchedule schedule : oldSchedules) {
            if (isNotEmptyData(schedule) || isInNewCommittee(schedule, newSchedules)) {
                CommonCommitteeSchedule copiedSchedule = getCopiedSchedule(schedule);
                if (isInNewCommittee(schedule, newSchedules)) {
                    CommonCommitteeSchedule newSchedule = getNewCommitteeSchedule(schedule, newSchedules);
                    copiedSchedule.setScheduleStatusCode(newSchedule.getScheduleStatusCode());
                    copiedSchedule.setPlace(newSchedule.getPlace());
                    copiedSchedule.setTime(newSchedule.getTime());
                    copiedSchedule.setScheduledDate(newSchedule.getScheduledDate());
                    copiedSchedule.setProtocolSubDeadline(newSchedule.getProtocolSubDeadline());
                }
                copiedSchedules.add(copiedSchedule);
            } 
        }
        for (CommonCommitteeSchedule schedule : newSchedules) {
            if (!isScheduleDateMatched(schedule, copiedSchedules)) {
                copiedSchedules.add(schedule);
            }
        }
        return copiedSchedules;

    }
    
    /*
     * check if schedule contain meeting which also include whether protocol submitted.
     */
    protected boolean isNotEmptyData(CommonCommitteeSchedule schedule) {
        return CollectionUtils.isNotEmpty(schedule.getCommitteeScheduleAttendances())
        || CollectionUtils.isNotEmpty(schedule.getCommitteeScheduleMinutes())
        || CollectionUtils.isNotEmpty(schedule.getCommScheduleActItems())
        || CollectionUtils.isNotEmpty(schedule.getMinuteDocs())
        || CollectionUtils.isNotEmpty(schedule.getScheduleAgendas())
        || CollectionUtils.isNotEmpty(schedule.getLatestProtocolSubmissions()) ;
        
    }
    
    /*
     * get schedule that will copy all meeting data from old schedule
     */
    protected CommonCommitteeSchedule getCopiedSchedule(CommonCommitteeSchedule schedule) {
        CommonCommitteeSchedule copiedSchedule = (CommonCommitteeSchedule) ObjectUtils.deepCopy(schedule);
        copiedSchedule.setId(null);
        schedule.getCommScheduleActItems().size();
        // all the collection are set up as transient because the complexity
        // of doc content and deepcopy.  keep it this way.
        copiedSchedule.setScheduleAgendas(schedule.getScheduleAgendas());
        copiedSchedule.setMinuteDocs(schedule.getMinuteDocs());
        copiedSchedule.setCommitteeScheduleAttendances(schedule.getCommitteeScheduleAttendances());
        copiedSchedule.setCommitteeScheduleMinutes(schedule.getCommitteeScheduleMinutes());
        copiedSchedule.setCommScheduleActItems(schedule.getCommScheduleActItems());
        copiedSchedule.setProtocolSubmissions(schedule.getLatestProtocolSubmissions());
        for (CommitteeScheduleAttendance attendance : copiedSchedule.getCommitteeScheduleAttendances()) {
            attendance.setCommScheduleAttendanceId(null);
        }
        for (CommitteeScheduleMinute minute : copiedSchedule.getCommitteeScheduleMinutes()) {
            minute.setCommScheduleMinutesId(null);
        }
        for (CommScheduleActItem actItem : copiedSchedule.getCommScheduleActItems()) {
            setActItemId(actItem, copiedSchedule.getCommitteeScheduleMinutes());
        }
        for (CommScheduleMinuteDoc minuteDoc : copiedSchedule.getMinuteDocs()) {
            minuteDoc.setCommScheduleMinuteDocId(null);
        }
        for (ScheduleAgenda agenda : copiedSchedule.getScheduleAgendas()) {
            agenda.setScheduleAgendaId(null);
        }
        return copiedSchedule;
        
    }
    
    /*
     * Since actitemid is reset, and if the act item is referenced in minute;
     * then this relationship need to be set up properly.
     */
    protected void setActItemId(CommScheduleActItem actItem, List<CommitteeScheduleMinute> minutes) {
        Long nextCommScheduleActItemId = sequenceAccessorService.getNextAvailableSequenceNumber("SEQ_MEETING_ID");
        for (CommitteeScheduleMinute minute : minutes) {
            if (minute.getCommScheduleActItemsIdFk() != null && actItem.getCommScheduleActItemsId().equals(minute.getCommScheduleActItemsIdFk())) {
                minute.setCommScheduleActItemsIdFk(nextCommScheduleActItemId);
            }            
        }
        actItem.setCommScheduleActItemsId(nextCommScheduleActItemId);
    }
    
    /*
     * check if new schedule is already exist in the new copied schedule list.
     */
    protected boolean isScheduleDateMatched(CommonCommitteeSchedule schedule, List<CommonCommitteeSchedule> schedules) {
        for (CommonCommitteeSchedule copiedSchedule : schedules) {
            if (schedule.getScheduledDate().equals(copiedSchedule.getScheduledDate())) {
                return true;
            }
        }
        return false;
    }
    
    /*
     * check if old schedule still exist in new committee
     */
    protected boolean isInNewCommittee(CommonCommitteeSchedule schedule, List<CommonCommitteeSchedule> schedules) {
        for (CommonCommitteeSchedule newSchedule : schedules) {
            if (StringUtils.equals(newSchedule.getScheduleId(), schedule.getScheduleId())) {
                return true;
            }
        }
        return false;
    }
    
    /*
     * get the matched schedule from new committee.
     */
    protected CommonCommitteeSchedule getNewCommitteeSchedule(CommonCommitteeSchedule schedule, List<CommonCommitteeSchedule> schedules) {
        for (CommonCommitteeSchedule newSchedule : schedules) {
            if (StringUtils.equals(newSchedule.getScheduleId(), schedule.getScheduleId())) {
                return newSchedule;
            }
        }
        return null;
    }

    public void setSequenceAccessorService(SequenceAccessorService sequenceAccessorService) {
        this.sequenceAccessorService = sequenceAccessorService;
    }

}

/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.committee.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.ResearchArea;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeMembershipRole;
import org.kuali.kra.committee.bo.CommitteeResearchArea;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

/**
 * The Committee Service implementation.
 */
public class CommitteeServiceImpl implements CommitteeService {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");

    private BusinessObjectService businessObjectService;

    /**
     * Set the Business Object Service.
     * 
     * @param businessObjectService the Business Object Service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * @see org.kuali.kra.committee.service.CommitteeService#getCommitteeById(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public Committee getCommitteeById(String committeeId) {
        Committee committee = null;
        if (!StringUtils.isBlank(committeeId)) {
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("committeeId", committeeId);
            Collection<Committee> committees = businessObjectService.findMatching(Committee.class, fieldValues);
            if (committees.size() > 0) {
                /*
                 * There is a database unique constraint that prevents more than committee from having the same committee ID.
                 * Therefore, the returned collection will always have zero or one entry.
                 */
                committee = committees.iterator().next();
            }
        }
        return committee;
    }

    /**
     * @see org.kuali.kra.committee.service.CommitteeService#addResearchAreas(org.kuali.kra.committee.bo.Committee,
     *      java.util.Collection)
     */
    public void addResearchAreas(Committee committee, Collection<ResearchArea> researchAreas) {
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
    private boolean hasResearchArea(Committee committee, ResearchArea researchArea) {
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
    private void addCommitteeResearchArea(Committee committee, ResearchArea researchArea) {
        CommitteeResearchArea committeeResearchArea = new CommitteeResearchArea();
        committeeResearchArea.setCommittee(committee);
        committeeResearchArea.setCommitteeIdFk(committee.getId());
        committeeResearchArea.setCommitteeId(committee.getCommitteeId());
        committeeResearchArea.setResearchArea(researchArea);
        committeeResearchArea.setResearchAreaCode(researchArea.getResearchAreaCode());
        committee.getCommitteeResearchAreas().add(committeeResearchArea);
    }

    /**
     * @see org.kuali.kra.committee.service.CommitteeService#getAvailableCommitteeDates(java.lang.String)
     */
    public List<KeyLabelPair> getAvailableCommitteeDates(String committeeId) {
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        keyValues.add(new KeyLabelPair("", "select"));
        Committee committee = getCommitteeById(committeeId);
        if (committee != null) {
            List<CommitteeSchedule> schedules = committee.getCommitteeSchedules();
            for (CommitteeSchedule schedule : schedules) {
                if (isOkayToScheduleReview(committee, schedule)) {
                    keyValues.add(new KeyLabelPair(schedule.getScheduleId(), getDescription(schedule)));
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
    private boolean isOkayToScheduleReview(Committee committee, CommitteeSchedule schedule) {
        Calendar now = getCalendar(new Date());
        Calendar scheduleCalendar = getCalendar(schedule.getScheduledDate());
        now.add(Calendar.DAY_OF_MONTH, committee.getAdvancedSubmissionDaysRequired());
        return now.compareTo(scheduleCalendar) <= 0;
    }
    
    /*
     * Create a calendar without hour, minutes, seconds, or milliseconds.
     */
    private Calendar getCalendar(Date date) {
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
    private String getDescription(CommitteeSchedule schedule) {
        Date date = schedule.getScheduledDate();
        if (schedule.getPlace() == null) {
            return dateFormat.format(date) + ", [no location], " + timeFormat.format(schedule.getActualTime());
        }
        return dateFormat.format(date) + ", " + schedule.getPlace() + ", " + timeFormat.format(schedule.getActualTime());
    }

    /**
     * @see org.kuali.kra.committee.service.CommitteeService#getAvailableMembers(java.lang.String, java.lang.String)
     */
    public List<CommitteeMembership> getAvailableMembers(String committeeId, String scheduleId) {
        List<CommitteeMembership> availableMembers = new ArrayList<CommitteeMembership>();
        Committee committee = getCommitteeById(committeeId);
        CommitteeSchedule schedule = getCommitteeSchedule(committee, scheduleId);
        List <CommitteeMembership> members = committee.getCommitteeMemberships();
        for (CommitteeMembership member : members) {
            if (isMemberAvailable(member, schedule.getScheduledDate())) {
                availableMembers.add(member);
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
     */
    private boolean isMemberAvailable(CommitteeMembership member, Date scheduledDate) {
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
        return false;
    }

    /**
     * @see org.kuali.kra.committee.service.CommitteeService#getCommitteeSchedule(org.kuali.kra.committee.bo.Committee, java.lang.String)
     */
    public CommitteeSchedule getCommitteeSchedule(Committee committee, String scheduleId) {
        List<CommitteeSchedule> schedules = committee.getCommitteeSchedules();
        for (CommitteeSchedule schedule : schedules) {
            if (StringUtils.equals(schedule.getScheduleId(), scheduleId)) {
                return schedule;
            }
        }
        return null;
    }
}

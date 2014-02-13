/*
 * Copyright 2005-2013 The Kuali Foundation
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

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.ResearchAreaBase;
import org.kuali.kra.common.committee.bo.*;
import org.kuali.kra.common.committee.meeting.CommitteeScheduleMinuteBase;
import org.kuali.kra.common.committee.service.CommitteeServiceBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.service.VersioningService;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The CommitteeBase Service implementation.
 */
public abstract class CommitteeServiceImplBase<CMT extends CommitteeBase<CMT, ?, CS>, 
                                           CS extends CommitteeScheduleBase<CS, CMT, PS, CSM>,
                                           PS extends ProtocolSubmissionBase,
                                           CSM extends CommitteeScheduleMinuteBase<CSM, CS>> 

                                           implements CommitteeServiceBase<CMT, CS> {

    private static final String COMMITTEE_ID = "committeeId";
    private static final String NO_PLACE = "[no location]";

    private BusinessObjectService businessObjectService;
    private VersioningService versioningService;


    /**
     * Set the Business Object Service.
     * 
     * @param businessObjectService the Business Object Service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    public BusinessObjectService getBusinessObjectService() {
        if(this.businessObjectService == null) {
            this.businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        }
        return this.businessObjectService;
    }

    
    
    /**
     * Inject the Versioning Service.
     * @param versioningService
     */
    public void setVersioningService(VersioningService versioningService) {
        this.versioningService = versioningService;
    }
    
    public VersioningService getVersioningService() {
        if(this.versioningService == null) {
            this.versioningService = KcServiceLocator.getService(VersioningService.class);
        }
        return versioningService;
    }
    

    /**
     * @see org.kuali.kra.common.committee.service.CommitteeServiceBase#getCommitteeById(java.lang.String)
     */
    public CMT getCommitteeById(String committeeId) {
        CMT committee = null;
        if (!StringUtils.isBlank(committeeId)) {
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put(COMMITTEE_ID, committeeId);
            Collection<CMT> committees = businessObjectService.findMatching(getCommitteeBOClassHook(), fieldValues);
            if (committees.size() > 0) {
                /*
                 * Return the most recent approved committee (i.e. the committee version with the highest 
                 * sequence number that is approved/in the database).
                 */
                committee = (CMT) Collections.max(committees);
            }
        }
        return committee;
    }

    protected abstract Class<CMT> getCommitteeBOClassHook();

    /**
     * @see org.kuali.kra.common.committee.service.CommitteeServiceBase#addResearchAreas(org.kuali.kra.common.committee.bo.CMT,
     *      java.util.Collection)
     */
    public void addResearchAreas(CMT committee, Collection<ResearchAreaBase> researchAreas) {
        for (ResearchAreaBase researchArea : researchAreas) {
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
    protected boolean hasResearchArea(CMT committee, ResearchAreaBase researchArea) {
        for (CommitteeResearchAreaBase committeeResearchArea : committee.getCommitteeResearchAreas()) {
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
    protected void addCommitteeResearchArea(CMT committee, ResearchAreaBase researchArea) { 
        CommitteeResearchAreaBase committeeResearchArea = getNewCommitteeResearchAreaInstanceHook();
        committeeResearchArea.setCommittee(committee);
        committeeResearchArea.setCommitteeIdFk(committee.getId());
        committeeResearchArea.setResearchArea(researchArea);
        committeeResearchArea.setResearchAreaCode(researchArea.getResearchAreaCode());
        committee.getCommitteeResearchAreas().add(committeeResearchArea);
    }

    protected abstract CommitteeResearchAreaBase getNewCommitteeResearchAreaInstanceHook();

    /**
     * @see org.kuali.kra.common.committee.service.CommitteeServiceBase#getAvailableCommitteeDates(java.lang.String)
     */
    public List<KeyValue> getAvailableCommitteeDates(String committeeId) {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue("", "select"));
        CMT committee = getCommitteeById(committeeId);
        if (committee != null) {
            List<CS> schedules = committee.getCommitteeSchedules();
            Collections.sort(schedules);
            for (CS schedule : schedules) {
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
    protected boolean isOkayToScheduleReview(CMT committee, CS schedule) {
        Calendar now = getCalendar(new Date());
        Calendar scheduleCalendar = getCalendar(schedule.getScheduledDate());
       // now.add(Calendar.DAY_OF_MONTH, committee.getAdvancedSubmissionDaysRequired());
        boolean dateRangeOK = now.compareTo(getCalendar(schedule.getProtocolSubDeadline())) <= 0;
        schedule.refreshReferenceObject("scheduleStatus");
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
    protected String getDescription(CS schedule) {
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
     * @see org.kuali.kra.common.committee.service.CommitteeServiceBase#getAvailableMembers(java.lang.String, java.lang.String)
     */
    public List<CommitteeMembershipBase> getAvailableMembers(String committeeId, String scheduleId) {
        if (StringUtils.isBlank(scheduleId)) {
            return getAvailableMembersNow(committeeId);
        }
        List<CommitteeMembershipBase> availableMembers = new ArrayList<CommitteeMembershipBase>();
        CMT committee = getCommitteeById(committeeId);
        if (committee != null) {
            CS schedule = getCommitteeSchedule(committee, scheduleId);
            if (schedule != null) {
                List<CommitteeMembershipBase> members = committee.getCommitteeMemberships();
                for (CommitteeMembershipBase member : members) {
                    if (isMemberAvailable(member, schedule.getScheduledDate())) {
                        availableMembers.add(member);
                    }
                }
            }
        }
        return availableMembers;
    }
    
    /**
     * @see org.kuali.kra.common.committee.service.CommitteeServiceBase#getAvailableMembers(java.lang.String, java.lang.String)
     */
    public List<CommitteeMembershipBase> getAvailableMembersNow(String committeeId) {
        List<CommitteeMembershipBase> availableMembers = new ArrayList<CommitteeMembershipBase>();
        CMT committee = getCommitteeById(committeeId);
        if (committee != null) {
            List<CommitteeMembershipBase> members = committee.getCommitteeMemberships();
            Date currentDate = new Date(); 
            for (CommitteeMembershipBase member : members) {
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
    protected boolean isMemberAvailable(CommitteeMembershipBase member, Date scheduledDate) {
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
     * @see org.kuali.kra.common.committee.service.CommitteeServiceBase#getCommitteeSchedule(org.kuali.kra.common.committee.bo.CommitteeBase, java.lang.String)
     */
    public CS getCommitteeSchedule(CMT committee, String scheduleId) {
        return committee.getCommitteeSchedule(scheduleId);
    }
    
    /**
     * 
     * @see org.kuali.kra.common.committee.service.CommitteeServiceBase#mergeCommitteeSchedule(java.lang.String)
     */
    public List<CS> mergeCommitteeSchedule(String committeeId) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(COMMITTEE_ID, committeeId);
        List<CMT> committees = (List<CMT>) getBusinessObjectService().findMatching(getCommitteeBOClassHook(), fieldValues);
        Collections.sort(committees);
        CMT newCommittee = committees.get(committees.size() - 1);
        CMT oldCommittee = committees.get(committees.size() - 2);
        List<CS> newMasterSchedules = new ArrayList<CS>();
        
        List<CS> oldMasterSchedules = oldCommittee.getCommitteeSchedules();
        List<CS> newSchedules = newCommittee.getCommitteeSchedules();
        
        // remove the to-be-retained old schedules and new schedules from the old master list and the new master list 
        // respectively, and combine these removed schedules to create the new master list.
        if (CollectionUtils.isNotEmpty(newSchedules) || CollectionUtils.isNotEmpty(oldMasterSchedules)) {
            newMasterSchedules = createNewMasterScheduleList(newSchedules, oldMasterSchedules);
            // delete the remaining old master schedule list and the remaining new schedule list 
            getBusinessObjectService().delete(oldMasterSchedules);
            getBusinessObjectService().delete(newSchedules);
        }
        
        
        return newMasterSchedules;
    }


    /*
     * If any of the schedules in the old schedule list also exists in the new schedule liset, then copy over the light data from the 
     * corresponding new schedule to the old schedule. Move all such old schedules from the old master schedule list to the the return list. 
     * Finally, all newly added schedules, i.e. those that are present only in the new schedule listing, are also moved to the return list from
     * the new schedule list.
     */
    protected List<CS> createNewMasterScheduleList(List<CS> newSchedules, List<CS> oldSchedules) {
        List<CS> masterSchedules = new ArrayList<CS>();
        // make shallow copies of the new and old schedule list in order to avoid concurrent modification exceptions
        // as we remove schedules from the original lists.
        List<CS> newSchedulesCopy = new ArrayList<CS>(newSchedules);
        List<CS> oldSchedulesCopy = new ArrayList<CS>(oldSchedules);
        
        // loop over the old schedules and process those that need to be retained in the master list
        for (CS oldSchedule : oldSchedulesCopy) {
            CS newCommitteeVersionOfOldSchedule = getNewCommitteeSchedule(oldSchedule, newSchedules);
            if (oldSchedule.hasMeetingData() || (newCommitteeVersionOfOldSchedule != null)) {
                // if its in the new committee, then the schedule's light data may have been modified, so copy it over
                if (newCommitteeVersionOfOldSchedule != null) {
                    oldSchedule.copyLightDataFrom(newCommitteeVersionOfOldSchedule);
                }
                masterSchedules.add(oldSchedule);
                // remove the old schedule from the old list
                oldSchedules.remove(oldSchedule);
            } 
        }
        // add all the newly-added new schedules to the master list as well
        for (CS newSchedule : newSchedulesCopy) {
            if (!isScheduleDateMatched(newSchedule, masterSchedules)) {
                masterSchedules.add(newSchedule);
                // remove them from the new schedule list
                newSchedules.remove(newSchedule);
            }
        }
        return masterSchedules;
    }
    
    
    /*
     * check if new schedule is already exist in the new copied schedule list.
     */
    protected boolean isScheduleDateMatched(CS schedule, List<CS> schedules) {
        for (CS copiedSchedule : schedules) {
            if (schedule.getScheduledDate().equals(copiedSchedule.getScheduledDate())) {
                return true;
            }
        }
        return false;
    }
    
    /*
     * get the matched schedule from new committee.
     */
    protected CS getNewCommitteeSchedule(CS schedule, List<CS> schedules) {
        for (CS newSchedule : schedules) {
            if (StringUtils.equals(newSchedule.getScheduleId(), schedule.getScheduleId())) {
                return newSchedule;
            }
        }
        return null;
    }

    
    
    /**
     * @see org.kuali.kra.common.committee.service.CommitteeServiceBase#updateCommitteeForProtocolSubmissions(org.kuali.kra.common.committee.bo.CommitteeBase)
     */
    @Override
    public void updateCommitteeForProtocolSubmissions(CMT committee) {
        // loop thru all the schedules for the committee and update each schedule's submissions if any
        for(CS committeeSchedule : committee.getCommitteeSchedules()) {
            for(PS protocolSubmission : committeeSchedule.getProtocolSubmissions()) {
                protocolSubmission.setCommitteeIdFk(committee.getId());
                protocolSubmission.setCommittee(committee);
                getBusinessObjectService().save(protocolSubmission); 
            }
        }
    }
    
    
    @Override
    public CMT getLightVersion(String committeeId) throws Exception{
        CMT committee = getCommitteeById(committeeId);
        // nullify the original committeeDocument
        committee.setCommitteeDocument(null);
        // iterate through each of the schedules and nullify the 'heavy' links
        for(CS committeeSchedule : committee.getCommitteeSchedules()) {
            committeeSchedule.nullifyHeavyMeetingData();
        }
        return getVersioningService().createNewVersion(committee);
    }
    

}

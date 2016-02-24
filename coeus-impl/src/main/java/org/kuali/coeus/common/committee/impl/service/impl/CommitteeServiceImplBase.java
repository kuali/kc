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
package org.kuali.coeus.common.committee.impl.service.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.*;
import org.kuali.coeus.common.committee.impl.meeting.CommitteeScheduleMinuteBase;
import org.kuali.coeus.common.committee.impl.service.CommitteeServiceBase;
import org.kuali.coeus.common.framework.version.VersioningService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.ResearchAreaBase;
import org.kuali.kra.committee.dao.CustomCommitteeDao;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionLiteBase;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The CommitteeBase Service implementation.
 */
public abstract class CommitteeServiceImplBase<CMT extends CommitteeBase<CMT, ?, CS>, 
                                           CS extends CommitteeScheduleBase<CS, CMT, PS, CSM>,
                                           PS extends ProtocolSubmissionLiteBase,
                                           CSM extends CommitteeScheduleMinuteBase<CSM, CS>> 

                                           implements CommitteeServiceBase<CMT, CS> {

    private static final String COMMITTEE_ID = "committeeId";
    private static final String NO_PLACE = "[no location]";
    private static final int SCHEDULED = 1;
    private static final String COMMITTEE_DOCUMENT_STATUS_CODE = "committeeDocument.docStatusCode";

    private BusinessObjectService businessObjectService;
    private VersioningService versioningService;
    private CustomCommitteeDao customCommitteeDao;

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
    

    @Override
    public CMT getCommitteeById(String committeeId) {
        CMT committee = null;
        if (!StringUtils.isBlank(committeeId)) {
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put(COMMITTEE_ID, committeeId);
            fieldValues.put(COMMITTEE_DOCUMENT_STATUS_CODE, KewApiConstants.ROUTE_HEADER_FINAL_CD);
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

    @Override
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
     */
    protected boolean isOkayToScheduleReview(CMT committee, CS schedule) {
        final Calendar now = getCalendar(new Date());
        final boolean dateRangeOK = now.compareTo(getCalendar(schedule.getProtocolSubDeadline())) <= 0;

        final boolean statusOK = Integer.valueOf(SCHEDULED).equals(schedule.getScheduleStatusCode());
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

    @Override
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

    @Override
    public CS getCommitteeSchedule(CMT committee, String scheduleId) {
        return committee.getCommitteeSchedule(scheduleId);
    }
    
    @Override
    public List<CS> mergeCommitteeSchedule(CMT committee) {
        CMT newCommittee = committee;
        CMT oldCommittee = getCommitteeById(committee.getCommitteeId());
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

    
    
    @Override
    public void updateCommitteeForProtocolSubmissions(CMT committee) {
        getCustomCommitteeDao().updateSubmissionsToNewCommitteeVersion(committee, committee.getCommitteeSchedules());
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

    public CustomCommitteeDao getCustomCommitteeDao() {
        if(this.customCommitteeDao == null) {
            this.customCommitteeDao = KcServiceLocator.getService(CustomCommitteeDao.class);
        }
        return customCommitteeDao;
    }

    public void setCustomCommitteeDao(CustomCommitteeDao customCommitteeDao) {
        this.customCommitteeDao = customCommitteeDao;
    }
}

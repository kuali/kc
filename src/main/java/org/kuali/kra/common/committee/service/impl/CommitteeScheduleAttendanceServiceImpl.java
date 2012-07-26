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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.common.committee.bo.CommonCommittee;
import org.kuali.kra.common.committee.bo.CommitteeMembership;
import org.kuali.kra.common.committee.bo.CommonCommitteeSchedule;
import org.kuali.kra.common.committee.document.CommonCommitteeDocument;
import org.kuali.kra.common.committee.meeting.CommitteeScheduleAttendance;
import org.kuali.kra.common.committee.service.CommonCommitteeScheduleAttendanceService;
import org.kuali.kra.common.committee.service.CommonCommitteeService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;


public class CommitteeScheduleAttendanceServiceImpl implements CommonCommitteeScheduleAttendanceService {
    private CommonCommitteeService committeeService;
    private ParameterService parameterService;
    
    public void setCommitteeService(CommonCommitteeService committeeService) {
        this.committeeService = committeeService;
    }
    
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    
    public Set<String> getVotingMembersPresent(String committeeId, String scheduleId) {
        Set<String> attendedMembers = new HashSet<String>();
        CommonCommittee committee = committeeService.getCommitteeById(committeeId);
        CommonCommitteeSchedule schedule = committeeService.getCommitteeSchedule(committee, scheduleId);
        List<CommitteeScheduleAttendance> attendances = schedule.getCommitteeScheduleAttendances();
        for (CommitteeScheduleAttendance attendance : attendances) {
             attendedMembers.add(attendance.getPersonId());
        }
        String memberId = null;
        String votingMemberType = parameterService.getParameterValueAsString(CommonCommitteeDocument.class, Constants.COMMITTEE_VOTING_MEMBERSHIP_TYPE_CODE);
        
        for(CommitteeMembership member : committee.getCommitteeMemberships()) {
            memberId = member.getRolodexId() != null ? member.getRolodexId().toString() : member.getPersonId();
            if(StringUtils.isNotBlank(memberId) && !member.getMembershipTypeCode().equals(votingMemberType)) { 
                attendedMembers.remove(memberId);
            }
        }
        
        return attendedMembers;
    }
  
    public Set<String> getActualVotingMembersPresent(String committeeId, String scheduleId) {
        Set<String> attendedMembers = new HashSet<String>();
        CommonCommittee committee = committeeService.getCommitteeById(committeeId);
        CommonCommitteeSchedule schedule = committeeService.getCommitteeSchedule(committee, scheduleId);
        List<CommitteeScheduleAttendance> attendances = schedule.getCommitteeScheduleAttendances();
        for (CommitteeScheduleAttendance attendance : attendances) {
             if(!attendance.getGuestFlag()) {
                 attendedMembers.add(attendance.getPersonId());
             }
        }
        return attendedMembers;
    }

    public int getActualVotingMembersCount(String committeeId, String scheduleId) {
        return getActualVotingMembersPresent(committeeId, scheduleId).size();
    }
    
    
}

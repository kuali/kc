/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.committee.impl.service.impl;

import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.committee.impl.document.CommitteeDocumentBase;
import org.kuali.coeus.common.committee.impl.meeting.CommitteeScheduleAttendanceBase;
import org.kuali.coeus.common.committee.impl.service.CommitteeScheduleAttendanceServiceBase;
import org.kuali.coeus.common.committee.impl.service.CommitteeServiceBase;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public abstract class CommitteeScheduleAttendanceServiceImplBase<CSRV extends CommitteeServiceBase<CMT, CS>,
                                                             CMT extends CommitteeBase<CMT, CD, CS>, 
                                                             CD extends CommitteeDocumentBase<CD, CMT, CS>,
                                                             CS extends CommitteeScheduleBase<CS, CMT, ?, ?>>
                                                                
                                                             implements CommitteeScheduleAttendanceServiceBase {
    
    private CSRV committeeService;
    
    public void setCommitteeService(CSRV committeeService) {
        this.committeeService = committeeService;
    }
  
    protected abstract Class<CD> getCommitteeDocumentClassBOHook();
    

    protected Set<String> getActualVotingMembersPresent(String committeeId, String scheduleId) {
        Set<String> attendedMembers = new HashSet<String>();
        CMT committee = committeeService.getCommitteeById(committeeId);
        CS schedule = committeeService.getCommitteeSchedule(committee, scheduleId);
        List<CommitteeScheduleAttendanceBase> attendances = schedule.getCommitteeScheduleAttendances();
        for (CommitteeScheduleAttendanceBase attendance : attendances) {
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

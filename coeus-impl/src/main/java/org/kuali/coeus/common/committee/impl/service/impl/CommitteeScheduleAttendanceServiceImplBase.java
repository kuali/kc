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

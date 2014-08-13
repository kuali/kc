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
package org.kuali.kra.irb.actions.assigncmtsched;

import org.kuali.kra.irb.Protocol;

/**
 * Handles the processing of assigning a protocol to a committee/schedule.
 */
public interface ProtocolAssignCmtSchedService {

    /**
     * Get the committee currently assigned to the protocol.
     * @param protocol
     * @return the committeeId or null if there is none
     */
    public String getAssignedCommitteeId(Protocol protocol);
    
    /**
     * Get the schedule currently assigned to the protocol.
     * @param protocol
     * @return the scheduleId or null if there is none
     */
    public String getAssignedScheduleId(Protocol protocol);
    
    /**
     * Assign a protocol to a committee/schedule.
     * @param protocol the protocol
     * @param actionBean contains committee/schedule
     * @throws Exception 
     */
    public void assignToCommitteeAndSchedule(Protocol protocol, ProtocolAssignCmtSchedBean actionBean) throws Exception;

    /**
     * Assign a protocol to a committee/schedule after the protocol has already been assigned to an agenda.
     * @param protocol the protocol
     * @param actionBean contains committee/schedule
     * @throws Exception 
     */
    public void assignToCommitteeAndSchedulePostAgendaAssignment(Protocol protocol, ProtocolAssignCmtSchedBean cmtAssignBean) throws Exception;
}

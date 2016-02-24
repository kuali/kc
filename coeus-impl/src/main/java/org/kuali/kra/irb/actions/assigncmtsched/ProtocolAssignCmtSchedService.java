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

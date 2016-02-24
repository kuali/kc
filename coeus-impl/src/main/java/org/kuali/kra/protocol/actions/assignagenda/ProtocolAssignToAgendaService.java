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
package org.kuali.kra.protocol.actions.assignagenda;

import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;


/**
 * Handles the processing of assigning a protocol to an agenda.
 */
public interface ProtocolAssignToAgendaService {

    /**
     * Get the committee currently assigned to the protocol.
     * @param protocol The ProtocolBase object to be queried from
     * @return the committeeId or null if there is none
     */
    String getAssignedCommitteeId(ProtocolBase protocol);
    
    /**
     * 
     * This method gets the committee name that the protocol is assigned to.
     * @param protocol The ProtocolBase object to be queried from
     * @return the committee name
     */
    String getAssignedCommitteeName(ProtocolBase protocol);
    
    /**
     * 
     * This method gets the date of the agenda that the protocol is assigned to.
     * @param protocol The ProtocolBase object to be queried from
     * @return the date of the agenda that the protocol is assigned to
     */
    String getAssignedScheduleDate(ProtocolBase protocol);
    
    /**
     * 
     * This method gets any comments made when the protocol was assigned.
     * @param protocol The ProtocolBase object to be queried from
     * @return the comments
     */
    String getAssignToAgendaComments(ProtocolBase protocol);
    
    /**
     * 
     * This method returns a boolean pertaining to whether the protocol is assigned to an agenda or not.
     * @param protocol The ProtocolBase object to be queried from
     * @return a boolean pertaining to whether the protocol is assigned to an agenda or not
     */
    boolean isAssignedToAgenda(ProtocolBase protocol);
    
    /**
     * Assign a protocol to an agenda.
     * @param protocol The ProtocolBase object to be queried from
     * @param actionBean ProtocolAssignToAgendaBean object
     * @throws Exception that may occur for a number of reasons, like a DB issue.
     */
    void assignToAgenda(ProtocolBase protocol, ProtocolAssignToAgendaBean actionBean) throws Exception;

    /**
     * 
     * This method returns the protocol action that assigned the passed in protocol to a committee agenda.
     * @param protocol the protocol to check with.
     * @return a ProtocolActionBase object.
     */
    ProtocolActionBase getAssignedToAgendaProtocolAction(ProtocolBase protocol);
}

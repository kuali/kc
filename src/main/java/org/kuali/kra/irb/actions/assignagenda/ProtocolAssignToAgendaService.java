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
package org.kuali.kra.irb.actions.assignagenda;

import org.kuali.kra.irb.Protocol;
import java.util.Date;

/**
 * Handles the processing of assigning a protocol to a committee/schedule.
 */
public interface ProtocolAssignToAgendaService {

    /**
     * Get the committee currently assigned to the protocol.
     * @param protocol
     * @return the committeeId or null if there is none
     */
    public String getAssignedCommitteeId(Protocol protocol);
    
    public String getAssignedCommitteeName(Protocol protocol);
    
    
    public Date getAssignedScheduleDate(Protocol protocol);
    
    
    public String getAssignToAgendaComments(Protocol protocol);
    
    public boolean isAssignedToAgenda(Protocol protocol);
    
    /**
     * Assign a protocol to a committee/schedule.
     * @param protocol the protocol
     * @param actionBean contains committee/schedule
     * @throws Exception 
     */
    public void assignToAgenda(Protocol protocol, ProtocolAssignToAgendaBean actionBean) throws Exception;
}

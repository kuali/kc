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
import org.kuali.kra.irb.actions.ProtocolAction;

import java.util.Date;

/**
 * Handles the processing of assigning a protocol to an agenda.
 */
public interface ProtocolAssignToAgendaService {

    
    /**
     * Assign a protocol to an agenda.
     * @param protocol The Protocol object to be queried from
     * @param actionBean ProtocolAssignToAgendaBean object
     * @throws Exception that may occur for a number of reasons, like a DB issue.
     */
    void assignToAgenda(Protocol protocol, ProtocolAssignToAgendaBean actionBean) throws Exception;
    
    /**
     * 
     * This method returns a protocolAction based on a protocol object.
     * @param protocol Protocol object
     * @return ProtocolAction object.
     */
    ProtocolAction getAssignedToAgendaProtocolAction(Protocol protocol);
}

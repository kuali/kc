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
package org.kuali.kra.protocol.actions.submit;

import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;

import java.util.List;


public interface ProtocolActionService {

    /**
     * 
     * This method is to get a list of protocol actions that can be performed based on 
     * this protocol's status or submission status and user's permission
     * @param protocol
     * @return
     */
    public List<String> getActionsAllowed(ProtocolBase protocol);

    /**
     * 
     * This method is to update protocol status or submission status after the action is submitted.
     * @param protocolActionBo
     * @param protocol
     */
    public void updateProtocolStatus(ProtocolActionBase protocolActionBo, ProtocolBase protocol);
    
    /**
     * 
     * This method is to undo the protocol status or submission status update.
     * @param protocolActionBo
     * @param protocol
     */
    public void resetProtocolStatus(ProtocolActionBase protocolActionBo, ProtocolBase protocol);

    /**
     * 
     * This method is to check if this protocol action can be performed based on protocol status or submission status
     * and also check if the user is authorized to perform this action.
     * @param actionTypeCode
     * @param protocol
     * @return
     */
    public boolean isActionAllowed(String actionTypeCode, ProtocolBase protocol);
    
    /**
     * Determines whether the given action in the protocol should be opened, based on whether it is a followup action.
     * @param protocolActionTypeCode The code for the protocol action
     * @param protocol The ProtocolBase
     * @return true, if the Drools rules state that the action should now be open for followup, false otherwise
     */
    public boolean isActionOpenForFollowup(String protocolActionTypeCode, ProtocolBase protocol);
    
    
    /**
     * Determines whether the logged in user is ProtocolBase Personnel
     *@param protocol The ProtocolBase
     * @return true, if the logged in user is protocol personnel else returns false
     */
    
    public boolean isProtocolPersonnel(ProtocolBase protocol) ;
}

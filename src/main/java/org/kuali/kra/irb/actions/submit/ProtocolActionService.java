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
package org.kuali.kra.irb.actions.submit;

import java.util.List;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;


public interface ProtocolActionService {

    /**
     * 
     * This method is to get a list of protocol actions that can be performed based on 
     * this protocol's status or submission status and user's permission
     * @param protocol
     * @return
     */
    public List<String> getActionsAllowed(Protocol protocol);

    /**
     * 
     * This method is to update protocol status or submission status after the action is submitted.
     * @param protocolActionBo
     * @param protocol
     */
    public void updateProtocolStatus(ProtocolAction protocolActionBo, Protocol protocol);
    
    /**
     * 
     * This method is to check if this protocol action can be performed based on protocol status or submission status
     * and also check if the user is authorized to perform this action.
     * @param actionTypeCode
     * @param protocol
     * @return
     */
    public boolean isActionAllowed(String actionTypeCode, Protocol protocol);

}
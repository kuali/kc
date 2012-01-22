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
package org.kuali.kra.irb.actions.proccessbillable;

import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.auth.ProtocolTask;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * 
 * This class should be extended by an service implementation that needs to set the billable field of the protocol submission.
 */
public abstract class ProtocolProccessBillableService {
    
    private TaskAuthorizationService taskAuthorizationService;
    
    public TaskAuthorizationService getTaskAuthorizationService() {
        return taskAuthorizationService;
    }
    
    /**
     * 
     * This method does a permission check to make sure the logged in user can perform the action.  
     * Then sets the billable field as appropriate.
     * @param protocol
     * @param billable
     */
    public void proccessBillable(Protocol protocol, boolean billable) {
        if (canUpdateBillableField(protocol)) {
            protocol.getProtocolSubmission().setBillable(billable);
        }
    }

    public void setTaskAuthorizationService(TaskAuthorizationService taskAuthorizationService) {
        this.taskAuthorizationService = taskAuthorizationService;
    }

    private boolean canUpdateBillableField(Protocol protocol) {
        ProtocolTask task = new ProtocolTask(TaskName.MODIFY_PROTOCOL_BILLABLE, protocol);
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private String getUserIdentifier() {
        return GlobalVariables.getUserSession().getPrincipalId();
   }

}

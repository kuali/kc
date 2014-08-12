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
package org.kuali.kra.iacuc.actions.processBillable;

import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.auth.IacucProtocolTask;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.rice.krad.util.GlobalVariables;

public abstract class IacucProtocolProcessBillableService {

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
    public void processBillable(ProtocolBase protocol, boolean billable) {
        if (canUpdateBillableField(protocol)) {
            protocol.getProtocolSubmission().setBillable(billable);
        }
    }

    public void setTaskAuthorizationService(TaskAuthorizationService taskAuthorizationService) {
        this.taskAuthorizationService = taskAuthorizationService;
    }

    private boolean canUpdateBillableField(ProtocolBase protocol) {
        IacucProtocolTask task = new IacucProtocolTask(TaskName.MODIFY_IACUC_PROTOCOL_BILLABLE, (IacucProtocol)protocol);
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    private String getUserIdentifier() {
        return GlobalVariables.getUserSession().getPrincipalId();
   }

}

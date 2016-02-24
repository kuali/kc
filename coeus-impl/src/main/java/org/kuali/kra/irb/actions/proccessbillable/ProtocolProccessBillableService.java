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
package org.kuali.kra.irb.actions.proccessbillable;

import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.auth.ProtocolTask;
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

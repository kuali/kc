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
package org.kuali.kra.protocol.correspondence;

import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.auth.ProtocolTaskBase;
import org.kuali.rice.krad.util.GlobalVariables;

public abstract class ProtocolCorrespondenceAuthorizationServiceImplBase implements ProtocolCorrespondenceAuthorizationService {
    
    private static final String VIEW_PROTOCOL_CORRESPONDENCE_TASK_NAME = "viewProtocolCorrespondence";
    private static final String MODIFY_PROTOCOL_CORRESPONDENCE_TASK_NAME = "modifyProtocolCorrespondence";
    private static final String CREATE_PROTOCOL_CORRESPONDENCE_TASK_NAME = "createProtocolCorrespondence";
    
    protected TaskAuthorizationService taskAuthorizationService;

    protected boolean hasPermission(String taskName, ProtocolBase protocol) {
        ProtocolTaskBase task = getNewProtocolTaskInstanceHook(taskName, protocol);
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    public void setTaskAuthorizationService(TaskAuthorizationService taskAuthorizationService) {
        this.taskAuthorizationService = taskAuthorizationService;
    }
    
    protected TaskAuthorizationService getTaskAuthorizationService() {
        if (this.taskAuthorizationService == null) {
            this.taskAuthorizationService = KcServiceLocator.getService(TaskAuthorizationService.class);
        }
        return this.taskAuthorizationService;
    }
    
    /**
     * Get the userName of the user for the current session.
     * @return the current session's userName
     */
    protected String getUserIdentifier() {
         return GlobalVariables.getUserSession().getPrincipalId();
    }
    

    protected abstract ProtocolTaskBase getNewProtocolTaskInstanceHook(String taskName, ProtocolBase protocol);

    @Override
    public boolean isAllowedToViewProtocolCorrespondence(ProtocolBase protocol) {
        return hasPermission(VIEW_PROTOCOL_CORRESPONDENCE_TASK_NAME, protocol) || isAllowedToUpdateProtocolCorrespondence(protocol);
    }

    @Override
    public boolean isAllowedToUpdateProtocolCorrespondence(ProtocolBase protocol) {
        return hasPermission(MODIFY_PROTOCOL_CORRESPONDENCE_TASK_NAME, protocol) || isAllowedToRegenerateProtocolCorrespondence(protocol);
    }

    @Override
    public boolean isAllowedToRegenerateProtocolCorrespondence(ProtocolBase protocol) {
        return hasPermission(CREATE_PROTOCOL_CORRESPONDENCE_TASK_NAME, protocol);
    }

}

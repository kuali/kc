/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.common.framework.auth.task;

import org.kuali.coeus.common.framework.auth.UnitAuthorizationService;

/**
 * Base implementation for all Task Authorizers.  The Unit and Proposal Authorization Services
 * and KRA Workflow Services are defined here because many derived classes make use of them.
 */
public abstract class TaskAuthorizerBase implements TaskAuthorizer {

    private String taskName;
    private UnitAuthorizationService unitAuthorizationService;

    /**
     * Set the name of the task.  Injected by the Spring Framework.
     * @param taskName the task's name
     */
    public final void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    
    /**
     * Set the Unit Authorization Service.  Injected by the Spring Framework.
     * @param unitAuthorizationService the Unit Authorization Service
     */
    public final void setUnitAuthorizationService(UnitAuthorizationService unitAuthorizationService) {
        this.unitAuthorizationService = unitAuthorizationService;
    }
    
    @Override
    public final String getTaskName() {
        return taskName;
    }
    
    /**
     * Does the given user has the permission for this proposal development document?
     * @param userId the unique username of the user
     * @param permissionName the name of the permission
     * @return true if the person has the permission; otherwise false
     */
    protected final boolean hasUnitPermission(String userId, String namespaceCode, String permissionName) {
        return unitAuthorizationService.hasPermission(userId, namespaceCode, permissionName);
    }
    
    protected final boolean hasUnitPermission(String userId, String unitNumber, String namespaceCode, String permissionName) {
        return unitAuthorizationService.hasPermission(userId, unitNumber, namespaceCode, permissionName);
    }
}

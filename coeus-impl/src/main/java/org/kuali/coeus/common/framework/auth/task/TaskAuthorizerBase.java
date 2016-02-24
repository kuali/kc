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

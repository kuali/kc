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
package org.kuali.kra.common.notification.service;

import java.util.List;

import org.kuali.kra.common.notification.bo.NotificationModuleRole;

public interface KcNotificationModuleRoleService {

    /**
     * This method allows you to add a relationship between a coeus module code and a role name
     * @param moduleCode The module code as defined in the CoeusModule object
     * @param roleName The KIM namespace and role name, combined with a semicolon, ie. namespace:rolename
     * @return The newly created module role association
     * @see org.kuali.kra.bo.CoeusModule
     */
    public NotificationModuleRole addNotificationModuleRole(String moduleCode, String roleName);
    
    /**
     * 
     * This method retrieves all notification module roles for a given coeus module code
     * @param moduleCode The module code as defined in the CoeusModule object
     * @return The list of matching notification modules roles for the given code
     * @see org.kuali.kra.bo.CoeusModule
     */
    public List<NotificationModuleRole> getNotificationModuleRoles(String moduleCode);
    
    /**
     * 
     * This method retrieves all notification module roles for a given coeus module code and role name
     * @param moduleCode The module code as defined in the CoeusModule object
     * @param roleName The KIM namespace and role name, combined with a semicolon, ie. namespace:rolename
     * @return The list of matching notification modules roles for the given code and role name
     * @see org.kuali.kra.bo.CoeusModule
     */
    public List<NotificationModuleRole> getNotificationModuleRolesForKimRole(String moduleCode, String roleName);
    
    /**
     * 
     * This method saves a list of notification module roles
     * @param notificationModuleRoles The list of module roles to save
     */
    public void saveNotificationModuleRoles(List<NotificationModuleRole> notificationModuleRoles);
}

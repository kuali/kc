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
package org.kuali.coeus.common.notification.impl.service;

import org.kuali.coeus.common.notification.impl.bo.NotificationModuleRole;

import java.util.List;

public interface KcNotificationModuleRoleService {
    
    /**
     * This method retrieves all notification module roles for a given coeus module code.
     * @param moduleCode The module code as defined in the CoeusModule object
     * @return The list of matching notification modules roles for the given code
     * @see org.kuali.coeus.common.framework.module.CoeusModule
     */
    List<NotificationModuleRole> getNotificationModuleRoles(String moduleCode);
    
    /**
     * This method retrieves all notification module roles for a given coeus module code as a String.
     * @param moduleCode The module code as defined in the CoeusModule object
     * @return The String representation of the list of matching notification modules roles for the given code
     * @see org.kuali.coeus.common.framework.module.CoeusModule
     */
    String getNotificationModuleRolesString(String moduleCode);
    
    /**
     * This method retrieves all notification module roles for a given coeus module code and role name.
     * @param moduleCode The module code as defined in the CoeusModule object
     * @param roleName The KIM namespace and role name, combined with a semicolon, ie. namespace:rolename
     * @return The list of matching notification modules roles for the given code and role name
     * @see org.kuali.coeus.common.framework.module.CoeusModule
     */
    List<NotificationModuleRole> getNotificationModuleRolesForKimRole(String moduleCode, String roleName);
}
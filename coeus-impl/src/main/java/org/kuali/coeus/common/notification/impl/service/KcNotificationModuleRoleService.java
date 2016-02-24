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

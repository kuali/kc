/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.service;

import java.util.List;

import org.kuali.kra.kim.pojo.Permission;
import org.kuali.kra.kim.pojo.Role;

/**
 * The System Authorization Service handles authorization in the global space.
 * It can be used to determine if a user has permission in the global space.  Also,
 * since all Roles and Permissions are at the global space, this service also provides
 * methods for querying Roles and Permissions.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public interface SystemAuthorizationService {
    
    /**
     * Get a Role based upon its unique role name.
     * 
     * @param roleName the Roles' unique role name.
     * @return the role or null if not found.
     */
    public Role getRole(String roleName);
    
    /**
     * Get the permissions in a role.
     * 
     * @param roleName the Role's unique role name.
     * @return the list of permissions in the role.
     */
    public List<Permission> getPermissionsForRole(String roleName);
    
    /**
     * Does the given user have the given permission?
     * 
     * @param username the user's username
     * @param permissionName the permission name
     * @return true if the user has the given permission; otherwise false.
     */
    public boolean hasPermission(String username, String permissionName);
}

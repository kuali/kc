/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.service;

import java.util.List;

import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.kim.api.type.KimType;

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
     * Get all of the roles for a particular type.
     * @param roleType the type of role
     * @return the KIM roles
     */
    public List<Role> getRoles(String namespaceCode);
    
    public List<Role> getRolesForPermission(String permissionName, String namespaceCode);
    
    public List<String> getRoleNamesForPermission(String permissionName, String namespaceCode);
    
    public List<String> getRoleIdsForPermission(String permissionName, String namespaceCode);
    
    public KimType getKimTypeInfoForRole(Role role);
    
}

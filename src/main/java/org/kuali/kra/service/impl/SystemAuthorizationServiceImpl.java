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
package org.kuali.kra.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.service.SystemAuthorizationService;
import org.kuali.rice.kim.bo.Role;
import org.kuali.rice.kim.service.RoleManagementService;

/**
 * The System Authorization Service Implementation.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class SystemAuthorizationServiceImpl implements SystemAuthorizationService {  
    private RoleManagementService roleManagementService;
    
    public void setRoleManagementService(RoleManagementService roleManagementService) {
        this.roleManagementService = roleManagementService;
    }

    public List<Role> getRolesForPermission(String permissionName, String namespaceCode) {
        List<Role> roles = new ArrayList<Role>();
        Map<String, String> roleFilter = new HashMap<String, String>();
        roleFilter.put("permName", permissionName);
        roleFilter.put("permNamespaceCode", namespaceCode);
        return (List<Role>) roleManagementService.getRolesSearchResults(roleFilter);
    }

    public List<String> getRoleNamesForPermission(String permissionName, String namespaceCode) {
        List<String> roleNames = new ArrayList<String>();
        List<Role> roles = getRolesForPermission(permissionName, namespaceCode);
        for(Role role: roles) {
            roleNames.add(role.getRoleName());
        }
        return roleNames;
    }

    public List<String> getRoleIdsForPermission(String permissionName, String namespaceCode) {
        List<String> roleNames = new ArrayList<String>();
        List<Role> roles = getRolesForPermission(permissionName, namespaceCode);
        for(Role role: roles) {
            roleNames.add(role.getRoleId());
        }
        return roleNames;
    }
    
    /**
     * @see org.kuali.kra.service.SystemAuthorizationService#getRoles(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<Role> getRoles(String namespaceCode) {
        Map<String, String> roleFilter = new HashMap<String, String>();
        roleFilter.put("namespaceCode", namespaceCode);
        return (List<Role>) roleManagementService.getRolesSearchResults(roleFilter);
    }

}

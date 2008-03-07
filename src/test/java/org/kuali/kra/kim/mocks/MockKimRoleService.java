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
package org.kuali.kra.kim.mocks;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.kim.mocks.MockKimDatabase.MockRole;
import org.kuali.kra.kim.pojo.Group;
import org.kuali.kra.kim.pojo.GroupQualifiedRole;
import org.kuali.kra.kim.pojo.Permission;
import org.kuali.kra.kim.pojo.Person;
import org.kuali.kra.kim.pojo.PersonQualifiedRole;
import org.kuali.kra.kim.pojo.Role;
import org.kuali.kra.kim.service.RoleService;

/**
 * A Mock for the KIM Role Service.
 */
public class MockKimRoleService extends MockKimService implements RoleService {
   
    /**
     * @see org.kuali.kra.kim.service.RoleService#gePersonQualifiedRoles(java.lang.String, java.util.Map)
     */
    public List<PersonQualifiedRole> gePersonQualifiedRoles(String roleName, Map<String, String> qualifiedRoleAttributes) {
        return null;
    }

    /**
     * @see org.kuali.kra.kim.service.RoleService#getGroupNames(java.lang.String)
     */
    public List<String> getGroupNames(String roleName) {
        return null;
    }

    /**
     * @see org.kuali.kra.kim.service.RoleService#getGroupQualifiedRoles(java.lang.String)
     */
    public List<GroupQualifiedRole> getGroupQualifiedRoles(String roleName) {
        return null;
    }

    /**
     * @see org.kuali.kra.kim.service.RoleService#getGroupQualifiedRoles(java.lang.String, java.util.Map)
     */
    public List<GroupQualifiedRole> getGroupQualifiedRoles(String roleName, Map<String, String> qualifiedRoleAttributes) {
        return null;
    }

    /**
     * @see org.kuali.kra.kim.service.RoleService#getGroups(java.lang.String)
     */
    public List<Group> getGroups(String roleName) {
        return null;
    }

    /**
     * @see org.kuali.kra.kim.service.RoleService#getPermissionNames(java.lang.String)
     */
    public List<String> getPermissionNames(String roleName) {
        return null;
    }

    /**
     * @see org.kuali.kra.kim.service.RoleService#getPermissions(java.lang.String)
     */
    public List<Permission> getPermissions(String roleName) {
        return null;
    }

    /**
     * @see org.kuali.kra.kim.service.RoleService#getPersonQualifiedRoles(java.lang.String)
     */
    public List<PersonQualifiedRole> getPersonQualifiedRoles(String roleName) {
        return null;
    }

    /**
     * @see org.kuali.kra.kim.service.RoleService#getPersonUsernames(java.lang.String)
     */
    public List<String> getPersonUsernames(String roleName) {
        return null;
    }

    /**
     * @see org.kuali.kra.kim.service.RoleService#getPersons(java.lang.String)
     */
    public List<Person> getPersons(String roleName) {
        return null;
    }

    /**
     * @see org.kuali.kra.kim.service.RoleService#getRole(java.lang.String)
     */
    public Role getRole(String roleName) {
        return null;
    }

    /**
     * @see org.kuali.kra.kim.service.RoleService#hasPermission(java.lang.String, java.lang.String, java.lang.String)
     */
    public boolean hasPermission(String roleName, String namespaceName, String permissionName) {
        for (MockRole role : database.getRoles()) {
            if (StringUtils.equals(roleName, role.getRoleName())) {
                if (role.hasPermission(permissionName)) {
                    return true;
                }
            }
        }
        return false;
    }
}

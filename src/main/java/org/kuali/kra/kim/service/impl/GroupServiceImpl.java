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
package org.kuali.kra.kim.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.kuali.kra.kim.bo.KimGroup;
import org.kuali.kra.kim.bo.KimGroupAttribute;
import org.kuali.kra.kim.bo.KimPermission;
import org.kuali.kra.kim.bo.KimPerson;
import org.kuali.kra.kim.bo.KimRole;
import org.kuali.kra.kim.pojo.Group;
import org.kuali.kra.kim.pojo.Permission;
import org.kuali.kra.kim.pojo.Person;
import org.kuali.kra.kim.pojo.Role;
import org.kuali.kra.kim.service.GroupService;

/**
 * The Group Service.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class GroupServiceImpl implements GroupService {
    
    private ServiceBase helper;
    
    /**
     * Set the Service Helper.  Injected by the Spring Framework.
     * @param helper the service helper
     */
    public void setServiceHelper(ServiceBase helper) {
        this.helper = helper;
    }

    /**
     * @see org.kuali.kra.kim.service.GroupService#getGroupNames(java.lang.String)
     */
    public List<String> getGroupNames(String groupName) {
        List<String> groupNames = new ArrayList<String>();
        Long groupId = helper.getGroupId(groupName);
        if (groupId != null) {
            List<KimGroup> kimGroups = helper.getGroupMemberGroups(groupId);
            for (KimGroup kimGroup : kimGroups) {
                groupNames.add(kimGroup.getName());
            }
        }
        return groupNames;
    }

    /**
     * @see org.kuali.kra.kim.service.GroupService#getGroups(java.lang.String)
     */
    public List<Group> getGroups(String groupName) {
        List<Group> groups = new ArrayList<Group>();
        Long groupId = helper.getGroupId(groupName);
        List<KimGroup> kimGroups = helper.getGroupMemberGroups(groupId);
        for (KimGroup kimGroup : kimGroups) {
            Group group = helper.buildGroup(kimGroup);
            groups.add(group);
        }
        return groups;
    }

    /**
     * @see org.kuali.kra.kim.service.GroupService#getGroupNames(java.util.Map)
     */
    public List<String> getGroupNames(Map<String, String> groupAttributes) {
        List<String> groupNames = new ArrayList<String>();
        Collection<KimGroup> allGroups = helper.getAllGroups();
        for (KimGroup kimGroup : allGroups) {
            Collection<KimGroupAttribute> kimGroupAttributes = helper.getGroupAttributes(kimGroup.getId());
            if (helper.containsAttrs(kimGroupAttributes, groupAttributes)) {
                groupNames.add(kimGroup.getName());
            }
        }
        return groupNames;
    }

    /**
     * @see org.kuali.kra.kim.service.GroupService#getGroups(java.util.Map)
     */
    public List<Group> getGroups(Map<String, String> groupAttributes) {
        List<Group> groups = new ArrayList<Group>();
        Collection<KimGroup> allGroups = helper.getAllGroups();
        for (KimGroup kimGroup : allGroups) {
            Collection<KimGroupAttribute> kimGroupAttributes = helper.getGroupAttributes(kimGroup.getId());
            if (helper.containsAttrs(kimGroupAttributes, groupAttributes)) {
                Group group = helper.buildGroup(kimGroup);
                groups.add(group);
            }
        }
        return groups;
    }

    /**
     * @see org.kuali.kra.kim.service.GroupService#getPermissionNames(java.lang.String)
     */
    public List<String> getPermissionNames(String groupName) {
        List<String> permissionNames = new ArrayList<String>();
        Long groupId = helper.getGroupId(groupName);
        Set<KimPermission> kimPermissions = helper.getGroupPermissions(groupId);
        for (KimPermission kimPermission : kimPermissions) {
            permissionNames.add(kimPermission.getName());
        }
        return permissionNames;
    }

    /**
     * @see org.kuali.kra.kim.service.GroupService#getPermissions(java.lang.String)
     */
    public List<Permission> getPermissions(String groupName) {
        List<Permission> permissions = new ArrayList<Permission>();
        Long groupId = helper.getGroupId(groupName);
        Set<KimPermission> kimPermissions = helper.getGroupPermissions(groupId);
        for (KimPermission kimPermission : kimPermissions) {
            Permission permission = helper.buildPermission(kimPermission);
            permissions.add(permission);
        }
        return permissions;
    }

    /**
     * @see org.kuali.kra.kim.service.GroupService#getPersonUsernames(java.lang.String)
     */
    public List<String> getPersonUsernames(String groupName) {
        List<String> personNames = new ArrayList<String>();
        Long groupId = helper.getGroupId(groupName);
        List<KimPerson> kimPersons = helper.getGroupMemberPersons(groupId);
        for (KimPerson kimPerson : kimPersons) {
            personNames.add(kimPerson.getUsername());
        }
        return personNames;
    }

    /**
     * @see org.kuali.kra.kim.service.GroupService#getPersons(java.lang.String)
     */
    public List<Person> getPersons(String groupName) {
        List<Person> persons = new ArrayList<Person>();
        Long groupId = helper.getGroupId(groupName);
        List<KimPerson> kimPersons = helper.getGroupMemberPersons(groupId);
        for (KimPerson kimPerson : kimPersons) {
            Person person = helper.buildPerson(kimPerson);
            persons.add(person);
        }
        return persons;
    }

    /**
     * @see org.kuali.kra.kim.service.GroupService#getRoleNames(java.lang.String)
     */
    public List<String> getRoleNames(String groupName) {
        List<String> roleNames = new ArrayList<String>();
        Long groupId = helper.getGroupId(groupName);
        List<KimRole> kimRoles = helper.getGroupRoles(groupId);
        for (KimRole kimRole : kimRoles) {
            roleNames.add(kimRole.getName());
        }
        return roleNames;
    }

    /**
     * @see org.kuali.kra.kim.service.GroupService#getRoles(java.lang.String)
     */
    public List<Role> getRoles(String groupName) {
        List<Role> roles = new ArrayList<Role>();
        Long groupId = helper.getGroupId(groupName);
        List<KimRole> kimRoles = helper.getGroupRoles(groupId);
        for (KimRole kimRole : kimRoles) {
            Role role = helper.buildRole(kimRole);
            roles.add(role);
        }
        return roles;
    }

    /**
     * @see org.kuali.kra.kim.service.GroupService#hasAttributes(java.lang.String, java.util.Map)
     */
    public boolean hasAttributes(String groupName, Map<String, String> groupAttributes) {
        Long groupId = helper.getGroupId(groupName);
        Collection<KimGroupAttribute> kimGroupAttributes = helper.getGroupAttributes(groupId);
        return helper.containsAttrs(kimGroupAttributes, groupAttributes);
    }

    /**
     * @see org.kuali.kra.kim.service.GroupService#hasQualifiedPermission(java.lang.String, java.lang.String, java.util.Map)
     */
    public boolean hasQualifiedPermission(String groupName, String namespaceName, String permissionName, Map<String, String> qualifiedRoleAttributes) {
        boolean hasPermission = false;
        Long groupId = helper.getGroupId(groupName);
        Long namespaceId = helper.getNamespaceId(namespaceName);
        Long permissionId = helper.getPermissionId(namespaceId, permissionName);
         
        Set<Long> roleIds = helper.getGroupQualifiedRoleIds(groupId, qualifiedRoleAttributes);
        if (helper.hasPermission(roleIds, permissionId)) {
            hasPermission = true;
        }
       
        if (!hasPermission) { // try groups
            Set<Long> parentGroupIds = helper.getGroupParentGroupIds(groupId, true);
            for (Long parentGroupId : parentGroupIds) {
                roleIds = helper.getGroupQualifiedRoleIds(parentGroupId, qualifiedRoleAttributes);
                if (helper.hasPermission(roleIds, permissionId)) {
                    hasPermission = true;
                    break;
                }
            }
        }

        return hasPermission;
    }
}

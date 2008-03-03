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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.kim.bo.KimGroup;
import org.kuali.kra.kim.bo.KimNamespace;
import org.kuali.kra.kim.bo.KimPermission;
import org.kuali.kra.kim.bo.KimPerson;
import org.kuali.kra.kim.bo.KimQualifiedRoleGroup;
import org.kuali.kra.kim.bo.KimRole;
import org.kuali.kra.kim.bo.KimRolePermission;
import org.kuali.kra.kim.bo.KimQualifiedRolePerson;
import org.kuali.kra.kim.pojo.Group;
import org.kuali.kra.kim.pojo.GroupQualifiedRole;
import org.kuali.kra.kim.pojo.Permission;
import org.kuali.kra.kim.pojo.Person;
import org.kuali.kra.kim.pojo.PersonQualifiedRole;
import org.kuali.kra.kim.pojo.Role;
import org.kuali.kra.kim.service.RoleService;

/**
 * The Role Service.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class RoleServiceImpl implements RoleService {

    private ServiceBase helper;
    
    /**
     * Set the Service Helper.  Injected by the Spring Framework.
     * @param helper the service helper
     */
    public void setServiceHelper(ServiceBase helper) {
        this.helper = helper;
    }
    
    /**
     * @see org.kuali.kra.kim.service.RoleService#getRole(java.lang.String)
     */
    public Role getRole(String roleName) {
        KimRole kimRole = helper.getRoleByName(roleName);
        return helper.buildRole(kimRole);
    }
    
    /**
     * @see org.kuali.kra.kim.service.RoleService#getGroupNames(java.lang.String)
     */
    public List<String> getGroupNames(String roleName) {
        List<String> groupNames = new ArrayList<String>();
        Long roleId = helper.getRoleId(roleName);
   
        List<KimGroup> kimGroups = helper.getRoleGroups(roleId);
        for (KimGroup kimGroup : kimGroups) {
            groupNames.add(kimGroup.getName());
        }
        return groupNames;
    }

    /**
     * @see org.kuali.kra.kim.service.RoleService#getGroups(java.lang.String)
     */
    public List<Group> getGroups(String roleName) {
        List<Group> groups = new ArrayList<Group>();
        Long roleId = helper.getRoleId(roleName);

        List<KimGroup> kimGroups = helper.getRoleGroups(roleId);
        for (KimGroup kimGroup : kimGroups) {
            Group group = helper.buildGroup(kimGroup);
            groups.add(group);
        }
        return groups;
    }
    
    /**
     * @see org.kuali.kra.kim.service.RoleService#getGroupQualifiedRoles(java.lang.String)
     */
    public List<GroupQualifiedRole> getGroupQualifiedRoles(String roleName) {
        List<GroupQualifiedRole> qualifiedRoles = new ArrayList<GroupQualifiedRole>();
        Long roleId = helper.getRoleId(roleName);

        Collection<KimQualifiedRoleGroup> roleGroups = helper.findMatching(KimQualifiedRoleGroup.class, "roleId", roleId);
        for (KimQualifiedRoleGroup roleGroup : roleGroups) {
            KimGroup group = helper.getGroup(roleGroup.getGroupId());
                
            GroupQualifiedRole qualifiedRole = new GroupQualifiedRole();
            qualifiedRole.setRoleName(roleName);
            qualifiedRole.setGroupName(group.getName());
            qualifiedRole.setQualifiedRoleAttributes(roleGroup.getQualifiedRoleAttributeMap());
            qualifiedRoles.add(qualifiedRole);
        }
        return qualifiedRoles;
    }

    /**
     * @see org.kuali.kra.kim.service.RoleService#getGroupQualifiedRoles(java.lang.String, java.util.Map)
     */
    public List<GroupQualifiedRole> getGroupQualifiedRoles(String roleName, Map<String, String> qualifiedRoleAttributes) {
        List<GroupQualifiedRole> qualifiedRoles = new ArrayList<GroupQualifiedRole>();
        Long roleId = helper.getRoleId(roleName);

        Collection<KimQualifiedRoleGroup> roleGroups = helper.findMatching(KimQualifiedRoleGroup.class, "roleId", roleId);
        for (KimQualifiedRoleGroup roleGroup : roleGroups) {
            if (roleGroup.matches(qualifiedRoleAttributes)) {
                KimGroup group = helper.getGroup(roleGroup.getGroupId());
                    
                GroupQualifiedRole qualifiedRole = new GroupQualifiedRole();
                qualifiedRole.setRoleName(roleName);
                qualifiedRole.setGroupName(group.getName());
                qualifiedRole.setQualifiedRoleAttributes(roleGroup.getQualifiedRoleAttributeMap());
                qualifiedRoles.add(qualifiedRole);
            }
        }
        return qualifiedRoles;
    }

    /**
     * @see org.kuali.kra.kim.service.RoleService#getPermissionNames(java.lang.String)
     */
    public List<String> getPermissionNames(String roleName) {
        List<String> permissionNames = new ArrayList<String>();
        Long roleId = helper.getRoleId(roleName);
        
        List<KimPermission> kimPermissions = helper.getRolePermissions(roleId);
        for (KimPermission kimPermission : kimPermissions) {
            permissionNames.add(kimPermission.getName());
        }
        return permissionNames;
    }

    /**
     * @see org.kuali.kra.kim.service.RoleService#getPermissions(java.lang.String)
     */
    public List<Permission> getPermissions(String roleName) {
        List<Permission> permissions = new ArrayList<Permission>();
        Long roleId = helper.getRoleId(roleName);

        List<KimPermission> kimPermissions = helper.getRolePermissions(roleId);
        for (KimPermission kimPermission : kimPermissions) {
            Permission permission = helper.buildPermission(kimPermission);
            permissions.add(permission);
        }
        return permissions;
    }
    
    /**
     * @see org.kuali.kra.kim.service.RoleService#hasPermission(java.lang.String, java.lang.String, java.lang.String)
     */
    public boolean hasPermission(String roleName, String namespaceName, String permissionName) {
        Long roleId = helper.getRoleId(roleName);
        Long namespaceId = helper.getNamespaceId(namespaceName);
        Long permissionId = helper.getPermissionId(namespaceId, permissionName);
     
        int cnt = helper.countMatching(KimRolePermission.class, "roleId", roleId,
                                                         "permissionId", permissionId);
        return cnt > 0;
    }

    /**
     * @see org.kuali.kra.kim.service.RoleService#getPersonQualifiedRoles(java.lang.String)
     */
    public List<PersonQualifiedRole> getPersonQualifiedRoles(String roleName) {
        List<PersonQualifiedRole> qualifiedRoles = new ArrayList<PersonQualifiedRole>();
        Long roleId = helper.getRoleId(roleName);
        
        Collection<KimQualifiedRolePerson> rolePersons = helper.findMatching(KimQualifiedRolePerson.class, "roleId", roleId);
        for (KimQualifiedRolePerson rolePerson : rolePersons) {
            KimPerson person = helper.getPerson(rolePerson.getPersonId());
                
            PersonQualifiedRole qualifiedRole = new PersonQualifiedRole();
            qualifiedRole.setRoleName(roleName);
            qualifiedRole.setUsername(person.getUsername());
            qualifiedRole.setQualifiedRoleAttributes(rolePerson.getQualifiedRoleAttributeMap());
            qualifiedRoles.add(qualifiedRole);
        }
        return qualifiedRoles;
    }

    /**
     * @see org.kuali.kra.kim.service.RoleService#getPersonQualifiedRoles(java.lang.String, java.util.Map)
     */
    public List<PersonQualifiedRole> gePersonQualifiedRoles(String roleName, Map<String, String> qualifiedRoleAttributes) {
        List<PersonQualifiedRole> qualifiedRoles = new ArrayList<PersonQualifiedRole>();
        Long roleId = helper.getRoleId(roleName);

        Collection<KimQualifiedRolePerson> rolePersons = helper.findMatching(KimQualifiedRolePerson.class, "roleId", roleId);
        for (KimQualifiedRolePerson rolePerson : rolePersons) {
            if (rolePerson.matches(qualifiedRoleAttributes)) {
                KimPerson person = helper.getPerson(rolePerson.getPersonId());
                    
                PersonQualifiedRole qualifiedRole = new PersonQualifiedRole();
                qualifiedRole.setRoleName(roleName);
                qualifiedRole.setUsername(person.getUsername());
                qualifiedRole.setQualifiedRoleAttributes(rolePerson.getQualifiedRoleAttributeMap());
                qualifiedRoles.add(qualifiedRole);
            }
        }
        return qualifiedRoles;
    }

    /**
     * @see org.kuali.kra.kim.service.RoleService#getPersonUsernames(java.lang.String)
     */
    public List<String> getPersonUsernames(String roleName) {
        List<String> personNames = new ArrayList<String>();
        Long roleId = helper.getRoleId(roleName);

        List<KimPerson> kimPersons = helper.getRolePersons(roleId);
        for (KimPerson kimPerson : kimPersons) {
            personNames.add(kimPerson.getUsername());
        }
        return personNames;
    }

    /**
     * @see org.kuali.kra.kim.service.RoleService#getPersons(java.lang.String)
     */
    public List<Person> getPersons(String roleName) {
        List<Person> persons = new ArrayList<Person>();
        Long roleId = helper.getRoleId(roleName);

        List<KimPerson> kimPersons = helper.getRolePersons(roleId);
        for (KimPerson kimPerson : kimPersons) {
            Person person = helper.buildPerson(kimPerson);
            persons.add(person);
        }
        return persons;
    }
}

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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.kuali.kra.kim.bo.KimPerson;
import org.kuali.kra.kim.bo.KimPersonQualifiedRoleAttribute;
import org.kuali.kra.kim.bo.KimQualifiedRoleAttribute;
import org.kuali.kra.kim.bo.KimQualifiedRoleGroup;
import org.kuali.kra.kim.bo.KimRolePerson;
import org.kuali.kra.kim.bo.KimQualifiedRolePerson;
import org.kuali.kra.kim.pojo.Person;
import org.kuali.kra.kim.pojo.QualifiedRole;
import org.kuali.kra.kim.service.PersonService;

/**
 * The KIM Person Service.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class PersonServiceImpl extends ServiceBase implements PersonService {
    
    /**
     * @see org.kuali.kra.kim.service.PersonService#getAttributeValue(java.lang.String, java.lang.String)
     */
    public String getAttributeValue(String personUserName, String namespaceName, String attributeName) {
        Long personId = getPersonId(personUserName);
        Long namespaceId = getNamespaceId(namespaceName);
        return this.getPersonAttributeValue(personId, namespaceId, attributeName);
    }

    /**
     * @see org.kuali.kra.kim.service.PersonService#getPersonUserNames(java.util.Map)
     */
    public List<String> getPersonUserNames(String namespaceName, Map<String, String> personAttributes) {
        List<String> personNames = new ArrayList<String>();
        Long namespaceId = getNamespaceId(namespaceName);
        List<KimPerson> kimPersons = this.getPersons(namespaceId, personAttributes);
        for (KimPerson kimPerson : kimPersons) {
            personNames.add(kimPerson.getUsername());
        }
        return personNames;
    }

    /**
     * @see org.kuali.kra.kim.service.PersonService#getPersons(java.util.Map)
     */
    public List<Person> getPersons(String namespaceName, Map<String, String> personAttributes) {
        List<Person> persons = new ArrayList<Person>();
        Long namespaceId = getNamespaceId(namespaceName);
        List<KimPerson> kimPersons = this.getPersons(namespaceId, personAttributes);
        for (KimPerson kimPerson : kimPersons) {
            Person person = buildPerson(kimPerson);
            persons.add(person);
        }
        return persons;
    }

    /**
     * @see org.kuali.kra.kim.service.PersonService#hasAttributes(java.lang.String, java.util.Map)
     */
    public boolean hasAttributes(String personUserName, String namespaceName, Map<String, String> personAttributes) {
        Long personId = getPersonId(personUserName);
        Long namespaceId = getNamespaceId(namespaceName);
        
        Iterator<Entry<String, String>> iterator = personAttributes.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, String> entry = iterator.next();
            if (!hasPersonAttribute(personId, namespaceId, entry.getKey(), entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    /**
     * @see org.kuali.kra.kim.service.PersonService#hasPermission(java.lang.String, java.lang.String)
     */
    public boolean hasPermission(String personUserName, String namespaceName, String permissionName) {
        Long personId = getPersonId(personUserName);
        Long namespaceId = getNamespaceId(namespaceName);
        Long permissionId = getPermissionId(namespaceId, permissionName);
       
        Set<Long> roleIds = getPersonRoleIds(personId, true);
        return this.hasPermission(roleIds, permissionId);
    }

    /**
     * @see org.kuali.kra.kim.service.PersonService#hasQualifiedPermission(java.lang.String, java.lang.String, java.util.Map)
     */
    public boolean hasQualifiedPermission(String personUserName, String namespaceName, String permissionName, Map<String, String> qualifiedRoleAttributes) {
        boolean hasPermission = false;
        Long personId = getPersonId(personUserName);
        Long namespaceId = getNamespaceId(namespaceName);
        Long permissionId = getPermissionId(namespaceId, permissionName);
        
        Set<Long> roleIds = this.getPersonQualifiedRoleIds(personId, qualifiedRoleAttributes);
        if (this.hasPermission(roleIds, permissionId)) {
            hasPermission = true;
        }
        else {
             Set<Long> groupIds = this.getPersonGroupIds(personId, true);
             for (Long groupId : groupIds) {
                roleIds = this.getGroupQualifiedRoleIds(groupId, qualifiedRoleAttributes);
                if (this.hasPermission(roleIds, permissionId)) {
                    hasPermission = true;
                    break;
                }
            }
        }
        return hasPermission;
    }

    /**
     * @see org.kuali.kra.kim.service.PersonService#hasQualifiedRole(java.lang.String, java.lang.String, java.util.Map)
     */
    public boolean hasQualifiedRole(String personUserName, String roleName, Map<String, String> qualifiedRoleAttributes) {
        Long personId = getPersonId(personUserName);
        Long roleId = getRoleId(roleName);
 
        boolean hasQualifiedRole = this.hasPersonQualifiedRole(personId, roleId, qualifiedRoleAttributes);
        if (!hasQualifiedRole) {
            Set<Long> groupIds = this.getPersonGroupIds(personId, true);
            for (Long groupId : groupIds) {
                hasQualifiedRole = this.hasGroupQualifiedRole(groupId, roleId, qualifiedRoleAttributes);
                if (hasQualifiedRole) {
                    break;
                }
            }
        }
        return hasQualifiedRole;
    }
    
    /**
     * @see org.kuali.kra.kim.service.PersonService#addQualifiedRole(java.lang.String, java.lang.String, java.util.Map)
     */
    public void addQualifiedRole(String personUserName, String roleName, Map<String, String> qualifiedRoleAttributes) {
        Long personId = getPersonId(personUserName);
        Long roleId = getRoleId(roleName);

        KimQualifiedRolePerson rolePerson = new KimQualifiedRolePerson();
        rolePerson.setPersonId(personId);
        rolePerson.setRoleId(roleId);
        List<KimPersonQualifiedRoleAttribute> kimQualifiedRoleAttributes = new ArrayList<KimPersonQualifiedRoleAttribute>();
        Iterator<Entry<String, String>> iterator = qualifiedRoleAttributes.entrySet().iterator();
        while (iterator.hasNext()) {
             Entry<String, String> entry = iterator.next();
             KimPersonQualifiedRoleAttribute kimQualifiedRoleAttribute = new KimPersonQualifiedRoleAttribute();
             kimQualifiedRoleAttribute.setAttributeName(entry.getKey());
             kimQualifiedRoleAttribute.setAttributeValue(entry.getValue());
             kimQualifiedRoleAttributes.add(kimQualifiedRoleAttribute);
        }
        rolePerson.setQualifiedRoleAttributes(kimQualifiedRoleAttributes);
        saveToDatabase(rolePerson);
    }
    
    /**
     * @see org.kuali.kra.kim.service.PersonService#removeQualifiedRole(java.lang.String, java.lang.String, java.util.Map)
     */
    public void removeQualifiedRole(String personUserName, String roleName, Map<String, String> qualifiedRoleAttributes) {
        Long personId = getPersonId(personUserName);
        Long roleId = getRoleId(roleName);
        
        KimQualifiedRolePerson rolePerson = this.getQualifiedRolePerson(roleId, personId, qualifiedRoleAttributes);
        deleteFromDatabase(rolePerson);
    }
    
    /**
     * @see org.kuali.kra.kim.service.PersonService#getQualifiedRoles(java.lang.String)
     */
    public List<QualifiedRole> getQualifiedRoles(String personUserName) {
        List<QualifiedRole> qualifiedRoles = new ArrayList<QualifiedRole>();
        Long personId = getPersonId(personUserName);

        // Add the qualified roles that the person is directly in.
        
        List<KimQualifiedRolePerson> rolePersons = this.getPersonQualifiedRoles(personId);
        for (KimQualifiedRolePerson rolePerson : rolePersons) {
            QualifiedRole qualifiedRole = new QualifiedRole();
            qualifiedRole.setRoleName(this.getRole(rolePerson.getRoleId()).getName());
            Map<String, String> attributes = new HashMap<String, String>();
            for (KimQualifiedRoleAttribute qualification : rolePerson.getQualifiedRoleAttributes()) {
                attributes.put(qualification.getAttributeName(), qualification.getAttributeValue());
            }
            qualifiedRole.setQualifiedRoleAttributes(attributes);
            qualifiedRoles.add(qualifiedRole);
        }
            
        // Add the qualified roles based upon the groups that the person is in.
        
        Set<Long> groupIds = this.getPersonGroupIds(personId, true);
        for (Long groupId : groupIds) {
            List<KimQualifiedRoleGroup> roleGroups = this.getGroupQualifiedRoles(groupId);
            for (KimQualifiedRoleGroup roleGroup : roleGroups) {
                QualifiedRole qualifiedRole = new QualifiedRole();
                qualifiedRole.setRoleName(this.getRole(roleGroup.getRoleId()).getName());
                Map<String, String> attributes = new HashMap<String, String>();
                for (KimQualifiedRoleAttribute qualification : roleGroup.getQualifiedRoleAttributes()) {
                    attributes.put(qualification.getAttributeName(), qualification.getAttributeValue());
                }
                qualifiedRole.setQualifiedRoleAttributes(attributes);
                qualifiedRoles.add(qualifiedRole);
            }
        }
        
        return qualifiedRoles;
    }
    
    /**
     * @see org.kuali.kra.kim.service.PersonService#hasRole(java.lang.String, java.lang.String)
     */
    public boolean hasRole(String personUserName, String roleName) {
        Long personId = getPersonId(personUserName);
        Long roleId = getRoleId(roleName);
        
        Set<Long> roleIds = getPersonRoleIds(personId, true);
        return roleIds.contains(roleId);
    }
    
    /**
     * @see org.kuali.kra.kim.service.PersonService#addRole(java.lang.String, java.lang.String)
     */
    public void addRole(String personUserName, String roleName) {
        Long personId = getPersonId(personUserName);
        Long roleId = getRoleId(roleName);
        
        KimRolePerson rolePerson = new KimRolePerson();
        rolePerson.setPersonId(personId);
        rolePerson.setRoleId(roleId);
        saveToDatabase(rolePerson);
    }
    
    /**
     * @see org.kuali.kra.kim.service.PersonService#removeRole(java.lang.String, java.lang.String)
     */
    public void removeRole(String personUserName, String roleName) {
        Long personId = getPersonId(personUserName);
        Long roleId = getRoleId(roleName);
       
        KimRolePerson rolePerson = getRolePerson(roleId, personId);
        deleteFromDatabase(rolePerson);
    }
    
    /**
     * @see org.kuali.kra.kim.service.PersonService#isMemberOfGroup(java.lang.String, java.lang.String)
     */
    public boolean isMemberOfGroup(String personUserName, String groupName) {
        Long groupId = getGroupId(groupName);
        Long personId = getPersonId(personUserName);
      
        Set<Long> groupIds = this.getPersonGroupIds(personId, true);
        return groupIds.contains(groupId);
    }
}

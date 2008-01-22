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

import org.kuali.kra.kim.bo.KimGroup;
import org.kuali.kra.kim.bo.KimPerson;
import org.kuali.kra.kim.bo.KimQualifiedRoleGroup;
import org.kuali.kra.kim.bo.KimQualifiedRolePerson;
import org.kuali.kra.kim.pojo.Group;
import org.kuali.kra.kim.pojo.GroupQualifiedRole;
import org.kuali.kra.kim.pojo.Person;
import org.kuali.kra.kim.pojo.PersonQualifiedRole;
import org.kuali.kra.kim.pojo.Role;
import org.kuali.kra.kim.service.QualifiedRoleService;

/**
 * The Qualified Role Service.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class QualifiedRoleServiceImpl extends ServiceBase implements QualifiedRoleService {

    /**
     * @see org.kuali.kra.kim.service.QualifiedRoleService#getGroupNames(java.lang.String, java.util.Map)
     */
    public List<String> getGroupNames(String roleName, Map<String, String> qualifiedRoleAttributes) {
        List<String> groupNames = new ArrayList<String>();
        Long roleId = getRoleId(roleName);

        Collection<KimQualifiedRoleGroup> roleGroups = findMatching(KimQualifiedRoleGroup.class, "roleId", roleId);
        for (KimQualifiedRoleGroup roleGroup : roleGroups) {
            if (roleGroup.matches(qualifiedRoleAttributes)) {
                KimGroup kimGroup = getGroup(roleGroup.getGroupId());
                groupNames.add(kimGroup.getName());
            }
        }
        return groupNames;
    }

    /**
     * @see org.kuali.kra.kim.service.QualifiedRoleService#getGroups(java.lang.String, java.util.Map)
     */
    public List<Group> getGroups(String roleName, Map<String, String> qualifiedRoleAttributes) {
        List<Group> groups = new ArrayList<Group>();
        Long roleId = getRoleId(roleName);

        Collection<KimQualifiedRoleGroup> roleGroups = findMatching(KimQualifiedRoleGroup.class, "roleId", roleId);
        for (KimQualifiedRoleGroup roleGroup : roleGroups) {
            if (roleGroup.matches(qualifiedRoleAttributes)) {
                KimGroup kimGroup = getGroup(roleGroup.getGroupId());
                Group group = buildGroup(kimGroup);
                groups.add(group);
            }
        }
        return groups;
    }

    /**
     * @see org.kuali.kra.kim.service.QualifiedRoleService#getPersonUsernames(java.lang.String, java.util.Map)
     */
    public List<String> getPersonUsernames(String roleName, Map<String, String> qualifiedRoleAttributes) {
        List<String> personNames = new ArrayList<String>();
        Long roleId = getRoleId(roleName);
        
        Collection<KimQualifiedRolePerson> rolePersons = findMatching(KimQualifiedRolePerson.class, "roleId", roleId);
        for (KimQualifiedRolePerson rolePerson : rolePersons) {
            if (rolePerson.matches(qualifiedRoleAttributes)) {
                KimPerson kimPerson = getPerson(rolePerson.getPersonId());
                personNames.add(kimPerson.getUsername());
            }
        }
        return personNames;
    }

    /**
     * @see org.kuali.kra.kim.service.QualifiedRoleService#getPersons(java.lang.String, java.util.Map)
     */
    public List<Person> getPersons(String roleName, Map<String, String> qualifiedRoleAttributes) {
        List<Person> persons = new ArrayList<Person>();
        Long roleId = getRoleId(roleName);
        
        Collection<KimQualifiedRolePerson> rolePersons = findMatching(KimQualifiedRolePerson.class, "roleId", roleId);
        for (KimQualifiedRolePerson rolePerson : rolePersons) {
            if (rolePerson.matches(qualifiedRoleAttributes)) {
                KimPerson kimPerson = getPerson(rolePerson.getPersonId());
                Person person = buildPerson(kimPerson);
                persons.add(person);
            }
        }
        return persons;
    }

    /**
     * This method and the next one appear to me to be stupid.
     * 
     * @see org.kuali.kra.kim.service.QualifiedRoleService#getRoleNames(java.util.Map)
     */
    public List<String> getRoleNames(Map<String, String> qualifiedRoleAttributes) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see org.kuali.kra.kim.service.QualifiedRoleService#getRoles(java.util.Map)
     */
    public List<Role> getRoles(Map<String, String> qualifiedRoleAttributes) {
        // TODO Auto-generated method stub
        return null;
    }

}

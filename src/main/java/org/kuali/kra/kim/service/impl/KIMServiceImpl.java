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

import org.kuali.kra.kim.bo.KimGroup;
import org.kuali.kra.kim.bo.KimNamespace;
import org.kuali.kra.kim.bo.KimPerson;
import org.kuali.kra.kim.bo.KimRole;
import org.kuali.kra.kim.pojo.Group;
import org.kuali.kra.kim.pojo.Namespace;
import org.kuali.kra.kim.pojo.Person;
import org.kuali.kra.kim.pojo.Role;
import org.kuali.kra.kim.service.KIMService;

/**
 * The KIM Service allows a user to query KIM regarding
 * Namespaces, Persons, Groups, and Roles.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class KIMServiceImpl implements KIMService {

    private ServiceBase helper;
    
    /**
     * Set the Service Helper.  Injected by the Spring Framework.
     * @param helper the service helper
     */
    public void setServiceHelper(ServiceBase helper) {
        this.helper = helper;
    }

    /**
     * @see org.kuali.kra.kim.service.KIMService#getGroupNames()
     */
    public List<String> getGroupNames() {
        List<String> groupNames = new ArrayList<String>();
        Collection<KimGroup> kimGroups = helper.getAllGroups();
        for (KimGroup kimGroup : kimGroups) {
            groupNames.add(kimGroup.getName());
        }
        return groupNames;
    }

    /**
     * @see org.kuali.kra.kim.service.KIMService#getGroups()
     */
    public List<Group> getGroups() {
        List<Group> groups = new ArrayList<Group>();
        Collection<KimGroup> kimGroups = helper.getAllGroups();
        for (KimGroup kimGroup : kimGroups) {
            Group group = helper.buildGroup(kimGroup);
            groups.add(group);
        }
        return groups;
    }

    /**
     * @see org.kuali.kra.kim.service.KIMService#getNamespaceNames()
     */
    public List<String> getNamespaceNames() {
        List<String> namespaceNames = new ArrayList<String>();
        Collection<KimNamespace> kimNamespaces = helper.getAllNamespaces();
        for (KimNamespace kimNamespace : kimNamespaces) {
            namespaceNames.add(kimNamespace.getName());
        }
        return namespaceNames;
    }

    /**
     * @see org.kuali.kra.kim.service.KIMService#getNamespaces()
     */
    public List<Namespace> getNamespaces() {
        List<Namespace> namespaces = new ArrayList<Namespace>();
        Collection<KimNamespace> kimNamespaces = helper.getAllNamespaces();
        for (KimNamespace kimNamespace : kimNamespaces) {
            Namespace namespace = helper.buildNamespace(kimNamespace);
            namespaces.add(namespace);
        }
        return namespaces;
    }

    /**
     * @see org.kuali.kra.kim.service.KIMService#getPersonUsernames()
     */
    public List<String> getPersonUsernames() {
        List<String> personNames = new ArrayList<String>();
        Collection<KimPerson> kimPersons = helper.getAllPersons();
        for (KimPerson kimPerson : kimPersons) {
            personNames.add(kimPerson.getUsername());
        }
        return personNames;
    }

    /**
     * @see org.kuali.kra.kim.service.KIMService#getPersons()
     */
    public List<Person> getPersons() {
        List<Person> persons = new ArrayList<Person>();
        Collection<KimPerson> kimPersons = helper.getAllPersons();
        for (KimPerson kimPerson : kimPersons) {
            Person person = helper.buildPerson(kimPerson);
            persons.add(person);
        }
        return persons;
    }

    /**
     * @see org.kuali.kra.kim.service.KIMService#getRoleNames()
     */
    public List<String> getRoleNames() {
        List<String> roleNames = new ArrayList<String>();
        Collection<KimRole> kimRoles = helper.getAllRoles();
        for (KimRole kimRole : kimRoles) {
            roleNames.add(kimRole.getName());
        }
        return roleNames;
    }

    /**
     * @see org.kuali.kra.kim.service.KIMService#getRoles()
     */
    public List<Role> getRoles() {
        List<Role> roles = new ArrayList<Role>();
        Collection<KimRole> kimRoles = helper.getAllRoles();
        for (KimRole kimRole : kimRoles) {
            Role role = helper.buildRole(kimRole);
            roles.add(role);
        }
        return roles;
    }
}

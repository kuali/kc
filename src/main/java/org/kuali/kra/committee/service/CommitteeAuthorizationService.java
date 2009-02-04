/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.committee.service;

import java.util.List;

import org.kuali.kra.bo.Person;
import org.kuali.kra.committee.bo.Committee;

/**
 * The Committee Authorization Service handles access to Committees.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public interface CommitteeAuthorizationService {

    /**
     * Get the list of usernames of people who have the given role with respect to
     * the given Committee.
     * @param committee the Committee
     * @param roleName the name of the Role
     * @return the list of usernames 
     */
    public List<String> getUserNames(Committee committee, String roleName);
    
    /**
     * Add a user to a role within a Committee.  Standard roles for
     * Committees are Aggregator and Viewer.
     * @param username the user's username
     * @param roleName the name of the Role
     * @param committee the Committee
     */
    public void addRole(String username, String roleName, Committee committee);
    
    /**
     * Remove a user from a role within a Committee. Standard roles for
     * Committees are Aggregator and Viewer.
     * @param username the user's username
     * @param roleName the name of the Role
     * @param committee the Committee
     */
    public void removeRole(String username, String roleName, Committee committee);
    
    /**
     * Does the user have the given permission for the given Committee?
     * @param username the user's username
     * @param committee the Committee
     * @param permissionName the name of the Permission
     * @return true if the user has permission; otherwise false
     */
    public boolean hasPermission(String username, Committee committee, String permissionName);

    /**
     * Does the user have the given role for the given Committee?
     * @param username the user's username
     * @param doc the Committee
     * @param roleName the name of the Role
     * @return true if the user has the role; otherwise false
     */
    public boolean hasRole(String username, Committee committee, String roleName);
    
    /**
     * Get the roles for this user in the Committee.
     * @param username the user's username
     * @param committee the Committee
     * @return the list of role names this person has in the committee
     */
    public List<String> getRoles(String username, Committee committee);
    
    /**
     * Get the list of persons in a specific role for a given committee.
     * @param committee the Committee
     * @param roleName the name of the role
     * @return the list of persons in the role for the committee
     */
    public List<Person> getPersonsInRole(Committee committee, String roleName);
}
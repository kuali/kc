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
package org.kuali.kra.award.service;

import java.util.List;

import org.kuali.kra.award.bo.Award;
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.RolePersons;

/**
 * The Award Authorization Service handles access to Award Documents.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public interface AwardAuthorizationService {

    /**
     * Get the list of usernames of people who have the given role with respect to
     * the given Award.
     * @param award the Award
     * @param roleName the name of the Role
     * @return the list of usernames 
     */
    public List<String> getUserNames(Award award, String roleName);
    
    /**
     * Add a user to a role within an Award.  Standard roles for
     * Award are Aggregator and Viewer.
     * @param username the user's username
     * @param roleName the name of the Role
     * @param award the Award
     */
    public void addRole(String username, String roleName, Award award);
    
    /**
     * Remove a user from a role within a Award. Standard roles for
     * Awards are Aggregator and Viewer.
     * @param username the user's username
     * @param roleName the name of the Role
     * @param award the Award
     */
    public void removeRole(String username, String roleName, Award award);
    
    /**
     * Does the user have the given permission for the given Award?
     * @param username the user's username
     * @param award the Award
     * @param permissionName the name of the Permission
     * @return true if the user has permission; otherwise false
     */
    public boolean hasPermission(String username, Award award, String permissionName);

    /**
     * Does the user have the given role for the given Award?
     * @param username the user's username
     * @param doc the Award
     * @param roleName the name of the Role
     * @return true if the user has the role; otherwise false
     */
    public boolean hasRole(String username, Award award, String roleName);
    
    /**
     * Get the roles for this user in the Award.
     * @param username the user's username
     * @param award the Award
     * @return the list of role names this person has in the award
     */
    public List<String> getRoles(String username, Award award);
    
    /**
     * Get the list of persons in a specific role for a given award.
     * @param award the Award
     * @param roleName the name of the role
     * @return the list of persons in the role for the award
     */
    public List<Person> getPersonsInRole(Award award, String roleName);
    
    /**
     * Get the list of all of the award roles and the persons in those
     * roles for a given Award.
     * @param award the Award
     * @return the list of all roles and the people in those roles
     */
    public List<RolePersons> getAllRolePersons(Award award);
}
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
package org.kuali.kra.irb.auth;

import java.util.List;

import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.RolePersons;
import org.kuali.kra.irb.bo.Protocol;

/**
 * The Protocol Authorization Service handles access to Protocol Documents.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public interface ProtocolAuthorizationService {

    /**
     * Get the list of usernames of people who have the given role with respect to
     * the given Protocol.
     * @param protocol the Protocol
     * @param roleName the name of the Role
     * @return the list of usernames 
     */
    public List<String> getUserNames(Protocol protocol, String roleName);
    
    /**
     * Add a user to a role within a Protocol.  Standard roles for
     * Protocols are Aggregator and Viewer.
     * @param username the user's username
     * @param roleName the name of the Role
     * @param protocol the Protocol
     */
    public void addRole(String username, String roleName, Protocol protocol);
    
    /**
     * Remove a user from a role within a Protocol. Standard roles for
     * Protocols are Aggregator and Viewer.
     * @param username the user's username
     * @param roleName the name of the Role
     * @param protocol the Protocol
     */
    public void removeRole(String username, String roleName, Protocol protocol);
    
    /**
     * Does the user have the given permission for the given Protocol?
     * @param username the user's username
     * @param protocol the Protocol
     * @param permissionName the name of the Permission
     * @return true if the user has permission; otherwise false
     */
    public boolean hasPermission(String username, Protocol protocol, String permissionName);

    /**
     * Does the user have the given role for the given Protocol?
     * @param username the user's username
     * @param doc the Protocol
     * @param roleName the name of the Role
     * @return true if the user has the role; otherwise false
     */
    public boolean hasRole(String username, Protocol protocol, String roleName);
    
    /**
     * Get the roles for this user in the Protocol.
     * @param username the user's username
     * @param protocol the Protocol
     * @return the list of role names this person has in the protocol
     */
    public List<String> getRoles(String username, Protocol protocol);
    
    /**
     * Get the list of persons in a specific role for a given protocol.
     * @param protocol the Protocol
     * @param roleName the name of the role
     * @return the list of persons in the role for the protocol
     */
    public List<Person> getPersonsInRole(Protocol protocol, String roleName);
    
    /**
     * Get the list of all of the protocol roles and the persons in those
     * roles for a given protocol.
     * @param protocol the protocol
     * @return the list of all roles and the people in those roles
     */
    public List<RolePersons> getAllRolePersons(Protocol protocol);
}
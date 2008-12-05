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
package org.kuali.kra.irb.service;

import java.util.List;

import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.RolePersons;
import org.kuali.kra.irb.document.ProtocolDocument;

/**
 * The Protocol Authorization Service handles access to Protocol Documents.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public interface ProtocolAuthorizationService {

    /**
     * Get the list of usernames of people who have the given role with respect to
     * the given Protocol Document.
     * @param doc the Protocol Document
     * @param roleName the name of the Role
     * @return the list of usernames 
     */
    public List<String> getUserNames(ProtocolDocument doc, String roleName);
    
    /**
     * Add a user to a role within a Protocol Document.  Standard roles for
     * Protocols are Aggregator and Viewer.
     * @param username the user's username
     * @param roleName the name of the Role
     * @param doc the Protocol Document
     */
    public void addRole(String username, String roleName, ProtocolDocument doc);
    
    /**
     * Remove a user from a role within a Protocol Document. Standard roles for
     * Protocols are Aggregator and Viewer.
     * @param username the user's username
     * @param roleName the name of the Role
     * @param doc the Protocol Document
     */
    public void removeRole(String username, String roleName, ProtocolDocument doc);
    
    /**
     * Does the user have the given permission for the given Protocol Document?
     * @param username the user's username
     * @param doc the Protocol Document
     * @param permissionName the name of the Permission
     * @return true if the user has permission; otherwise false
     */
    public boolean hasPermission(String username, ProtocolDocument doc, String permissionName);

    /**
     * Does the user have the given role for the given Protocol Document?
     * @param username the user's username
     * @param doc the Protocol Document
     * @param roleName the name of the Role
     * @return true if the user has the role; otherwise false
     */
    public boolean hasRole(String username, ProtocolDocument doc, String roleName);
    
    /**
     * Get the roles for this user in the Protocol Document.
     * @param username the user's username
     * @param doc the Protocol Document
     * @return the list of role names this person has in the protocol
     */
    public List<String> getRoles(String username, ProtocolDocument doc);
    
    /**
     * Get the list of persons in a specific role for a given protocol.
     * @param doc the Protocol Document
     * @param roleName the name of the role
     * @return the list of persons in the role for the protocol
     */
    public List<Person> getPersonsInRole(ProtocolDocument doc, String roleName);
    
    /**
     * Get the list of all of the protocol roles and the persons in those
     * roles for a given protocol.
     * @param doc the protocol document
     * @return the list of all roles and the people in those roles
     */
    public List<RolePersons> getAllRolePersons(ProtocolDocument doc);
}
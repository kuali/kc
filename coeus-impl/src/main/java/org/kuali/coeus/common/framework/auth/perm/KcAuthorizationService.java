/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.framework.auth.perm;

import java.util.List;

/**
 * The Authorization Service handles access to Documents.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public interface KcAuthorizationService {

    /**
     * Get the list of usernames of people who have the given role with respect to
     * the given Permissionable.
     * @param permissionable the Permissionable
     * @param roleName the name of the Role
     * @return the list of usernames 
     */
    public List<String> getUserNames(Permissionable permissionable, String roleName);
    
    /**
     * Add a user to a role within an Permissionable.  Standard roles for
     * Permissionable are Aggregator and Viewer.
     * @param userId the user's userId
     * @param roleName the name of the Role
     * @param permissionable the Permissionable
     */
    public void addRole(String userId, String roleName, Permissionable permissionable);
    
    /**
     * Remove a user from a role within a Permissionable. Standard roles for
     * Permissionable are Aggregator and Viewer.
     * @param userId the user's userId
     * @param roleName the name of the Role
     * @param permissionable the Permissionable
     */
    public void removeRole(String userId, String roleName, Permissionable permissionable);
    
    /**
     * Does the user have the given permission for the given Permissionable?
     * @param userId the user's userId
     * @param permissionable the Permissionable
     * @param permissionName the name of the Permission
     * @return true if the user has permission; otherwise false
     */
    public boolean hasPermission(String userId, Permissionable permissionable, String permissionName);
    
    /**
     * Does the user have the given permission in the given namespace?
     * @param userId the user's userId
     * @param permissionable the Permissionable
     * @param permissionNamespace the permission namespace
     * @param permissionName the name of the Permission
     * @return true if the user has permission; otherwise false
     */
    boolean hasPermission(String userId, Permissionable permissionable, String permissionNamespace, String permissionName);    

    /**
     * Does the user have the given role for the given Permissionable?
     * @param userId the user's userId
     * @param permissionable the Permissionable
     * @param roleName the name of the Role
     * @return true if the user has the role; otherwise false
     */
    public boolean hasRole(String userId, Permissionable permissionable, String roleName);
    
    /**
     * Get the roles for this user in the Permissionable.
     * @param userId the user's userId
     * @param permissionable the Permissionable
     * @return the list of role names this person has in the Permissionable
     */
    public List<String> getRoles(String userId, Permissionable permissionable);
    
    /**
     * Get the list of principal ids in a specific role for a given permissionable.
     * @param permissionable the Permissionable
     * @param roleName the name of the role
     * @return the list of principal ids in the role for the permissionable
     */
    public List<String> getPrincipalsInRole(Permissionable permissionable, String roleName);
    
    public boolean hasRole(String userId, String namespace, String roleName);
}

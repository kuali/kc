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
     * Add a user to a role within an Permissionable. The passed in role must be a document
     * level type.
     * @param userId the user's userId
     * @param roleName the name of the Role
     * @param permissionable the Permissionable
     */
    public void addDocumentLevelRole(String userId, String roleName, Permissionable permissionable);
    
    /**
     * Remove a user from a role within a Permissionable. The passed in role must be a document
     * level type.
     * @param userId the user's userId
     * @param roleName the name of the Role
     * @param permissionable the Permissionable
     */
    public void removeDocumentLevelRole(String userId, String roleName, Permissionable permissionable);

    /**
     * Remove a user from a role within a Permissionable. The passed in role must be a document
     * level type.
     * @param userId the user's userId
     * @param roleName the name of the Role
     * @param permissionable the Permissionable
     */
    public boolean hasDocumentLevelRole(String userId, String roleName, Permissionable permissionable);
    
    /**
     * Does the user have the given permission for the given Permissionable?
     * @param userId the user's userId
     * @param permissionable the Permissionable
     * @param permissionName the name of the Permission
     * @return true if the user has permission; otherwise false
     */
    public boolean hasPermission(String userId, Permissionable permissionable, String permissionName);

    
    /**
     * Get the list of principal ids in a specific role for a given permissionable.
     * @param roleName the name of the role
     * @param permissionable the Permissionable
     * @return the list of principal ids in the role for the permissionable
     */
    public List<String> getPrincipalsInRole(String roleName, Permissionable permissionable);
}

/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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

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
package org.kuali.coeus.common.framework.auth;

/**
 * The Unit Authorization Service handles users and their permissions
 * within units.  
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public interface UnitAuthorizationService {
    
    /**
     * Does the user have the given permission?
     * If the user has the permission in the global space, this method will 
     * return true.  Likewise, true will be returned in the user has this
     * permission in any qualified role.
     * @param userId the user's username
     * @param permissionName the name of the permission
     * @return true if the user has permission; otherwise false
     */
    public boolean hasPermission(String userId, String namespaceCode, String permissionName);
    
    /**
     * Does the user have the given permission in the specified unit?  
     * Along with checking the specific unit, if the user has the permission
     * in the global space, this method will also return true.
     * @param userId the user's username
     * @param unitNumber the Unit's unique number
     * @param permissionName the name of the permission
     * @return true if the user has permission; otherwise false
     */
    public boolean hasPermission(String userId, String unitNumber, String namespaceCode, String permissionName);
    

    public boolean hasMatchingQualifiedUnits(String userId, String namespaceCode, String permissionName, String unitNumber); 
}

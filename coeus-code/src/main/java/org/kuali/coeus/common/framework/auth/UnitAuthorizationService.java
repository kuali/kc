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

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
package org.kuali.kra.service;

import java.util.List;

import org.kuali.kra.bo.Unit;

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
     * @param username the user's username
     * @param permissionName the name of the permission
     * @return true if the user has permission; otherwise false
     */
    public boolean hasPermission(String username, String permissionName);
    
    /**
     * Does the user have the given permission in the specified unit?  
     * Along with checking the specific unit, if the user has the permission
     * in the global space, this method will also return true.
     * @param username the user's username
     * @param unitNumber the Unit's unique number
     * @param permissionName the name of the permission
     * @return true if the user has permission; otherwise false
     */
    public boolean hasPermission(String username, String unitNumber, String permissionName);
    
    /**
     * Get the units that the user has the given permission in.
     * If the user has the permission in the global space, all of
     * the units will be returned.  If the user doesn't have permission
     * in any unit, an empty list is returned.
     * @param username the user's username
     * @param permissionName the name of the permission
     * @return the list of units the user has this permission in
     */
    public List<Unit> getUnits(String username, String permissionName);
}

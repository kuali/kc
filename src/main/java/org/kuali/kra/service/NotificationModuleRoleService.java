/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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

import org.kuali.kra.common.notification.bo.NotificationModuleRole;

/**
 * 
 * This class allows the lookup of module roles by specific parameters as
 * well as providing support for specific ajax calls
 */
public interface NotificationModuleRoleService {

    /**
     * 
     * This method returns a list of all module roles associated with the module name
     * @param moduleName - The Coeus Module name
     * @return A list of module roles associated with the module name.
     * @see org.kuali.kra.boCoeusModule
     */
    public List<NotificationModuleRole> getModuleRolesByModuleName(String moduleName);
    
    /**
     * 
     * This method returns a parsable string for use in display of the list
     * of matching module roles
     * @param moduleName - The Coeus Module name that the module roles are associated with
     * @return an ajax response to be used in a select box
     */
    public String getModuleRolesForAjaxCall(String moduleName);
}

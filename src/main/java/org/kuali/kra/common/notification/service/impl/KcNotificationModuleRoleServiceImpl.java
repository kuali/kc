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
package org.kuali.kra.common.notification.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.common.notification.bo.NotificationModuleRole;
import org.kuali.kra.common.notification.service.KcNotificationModuleRoleService;
import org.kuali.rice.kns.service.BusinessObjectService;

public class KcNotificationModuleRoleServiceImpl implements KcNotificationModuleRoleService {
    
    private BusinessObjectService businessObjectService;

    /**
     * 
     * @see org.kuali.kra.common.notification.service.KcNotificationModuleRoleService#addNotificationModuleRole(java.lang.String, java.lang.String)
     */
    public NotificationModuleRole addNotificationModuleRole(String moduleCode, String roleName) {
        NotificationModuleRole moduleRole = new NotificationModuleRole();
        moduleRole.setModuleCode(moduleCode);
        moduleRole.setRoleName(roleName);
        
        getBusinessObjectService().save(moduleRole);
        return moduleRole;
    }

    /**
     * 
     * @see org.kuali.kra.common.notification.service.KcNotificationModuleRoleService#getNotificationModuleRoles(java.lang.String)
     */
    public List<NotificationModuleRole> getNotificationModuleRoles(String moduleCode) {

        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("moduleCode", moduleCode);
        List<NotificationModuleRole> moduleRoles = 
            (List<NotificationModuleRole>) getBusinessObjectService().findMatching(NotificationModuleRole.class, fieldValues);
        
        return moduleRoles;
    }
    
    /**
     * 
     * @see org.kuali.kra.common.notification.service.KcNotificationModuleRoleService#getNotificationModuleRolesForKimRole(java.lang.String, java.lang.String)
     */
    public List<NotificationModuleRole> getNotificationModuleRolesForKimRole(String moduleCode, String roleName) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("moduleCode", moduleCode);
        fieldValues.put("roleName", roleName);
        List<NotificationModuleRole> moduleRoles = 
            (List<NotificationModuleRole>) getBusinessObjectService().findMatching(NotificationModuleRole.class, fieldValues);
        
        return moduleRoles;
    }

    /**
     * 
     * @see org.kuali.kra.common.notification.service.KcNotificationModuleRoleService#saveNotificationModuleRoles(java.util.List)
     */
    public void saveNotificationModuleRoles(List<NotificationModuleRole> notificationModuleRoles) {
        getBusinessObjectService().save(notificationModuleRoles);
    }

    /**
     * 
     * Convenience method to get the business object service.
     * @return the business object service reference
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * 
     * Convenience method to set the business object service.
     * @param businessObjectService The reference to the business object service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}

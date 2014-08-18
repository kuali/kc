/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.notification.impl.service.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.kuali.coeus.common.notification.impl.bo.NotificationModuleRole;
import org.kuali.coeus.common.notification.impl.service.KcNotificationModuleRoleService;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KcNotificationModuleRoleServiceImpl implements KcNotificationModuleRoleService {
    
    private BusinessObjectService businessObjectService;


    @Override
    public List<NotificationModuleRole> getNotificationModuleRoles(String moduleCode) {

        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("moduleCode", moduleCode);
        List<NotificationModuleRole> moduleRoles = 
            (List<NotificationModuleRole>) getBusinessObjectService().findMatching(NotificationModuleRole.class, fieldValues);
        
        return moduleRoles;
    }
    
    @Override
    public String getNotificationModuleRolesString(String moduleCode) {
        String resultStr = "";
        List<NotificationModuleRole> moduleRoles = getNotificationModuleRoles(moduleCode);
        if (CollectionUtils.isNotEmpty(moduleRoles)) {
            for (NotificationModuleRole moduleRole : moduleRoles) {
                resultStr += moduleRole.getRoleName() + ",";
            }
        }
        return resultStr;
    }
    
    @Override
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

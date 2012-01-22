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
package org.kuali.kra.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.kra.common.notification.bo.NotificationModuleRole;
import org.kuali.kra.service.NotificationModuleRoleService;
import org.kuali.rice.krad.service.BusinessObjectService;

public class NotificationModuleRoleServiceImpl implements NotificationModuleRoleService {
    
    private BusinessObjectService businessObjectService;
   
    @Override
    public List<NotificationModuleRole> getModuleRolesByModuleName(String moduleName) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("moduleCode", moduleName);
        List<NotificationModuleRole> moduleRoles =
            (List<NotificationModuleRole>)getBusinessObjectService().findMatching(NotificationModuleRole.class, fieldValues);
        
        return moduleRoles;
    }

    @Override
    public String getModuleRolesForAjaxCall(String moduleName) {
        String resultStr = "";
        List<NotificationModuleRole> moduleRoles = getModuleRolesByModuleName(moduleName);
        if (CollectionUtils.isNotEmpty(moduleRoles)) {
            for (NotificationModuleRole moduleRole : moduleRoles) {
                resultStr += moduleRole.getRoleName() + ",";
            }
        }
        return resultStr;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}

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
package org.kuali.coeus.sys.impl.auth;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.auth.SystemAuthorizationService;
import org.kuali.coeus.sys.framework.auth.UnitAuthorizationService;
import org.kuali.rice.kim.api.permission.PermissionService;
import org.kuali.rice.kim.api.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Unit Authorization Service Implementation.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@Component("unitAuthorizationService")
public class UnitAuthorizationServiceImpl implements UnitAuthorizationService {

    public static final String UNIT_NUMBER = "unitNumber";

    @Autowired
    @Qualifier("systemAuthorizationService")
    private SystemAuthorizationService systemAuthorizationService;

    @Autowired
    @Qualifier("roleService")
    private RoleService roleManagementService;

    @Autowired
    @Qualifier("permissionService")
    private PermissionService permissionService;
    
    protected RoleService getRoleService() {
        return roleManagementService;
    }

    public void setRoleManagementService(RoleService roleManagementService) {
        this.roleManagementService = roleManagementService;
    }

    protected SystemAuthorizationService getSystemAuthorizationService() {
        return systemAuthorizationService;
    }
    
    public void setSystemAuthorizationService(SystemAuthorizationService systemAuthorizationService) {
        this.systemAuthorizationService = systemAuthorizationService;
    }

    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Override
    public boolean hasPermission(String userId, String unitNumber, String namespaceCode, String permissionName) {
        boolean userHasPermission = false;

        if (unitNumber != null) {
            // Check the unit for the permission. If the user doesn't have the permission
            // in the given unit, traverse up the Unit Hierarchy to see if the user has
            // the permission in a higher-level unit with the SUBUNITS flag set to YES.
            Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
            qualifiedRoleAttributes.put(UNIT_NUMBER, unitNumber);
            
            //The UnitHierarchyRoleTypeService takes care of traversing the Unit tree.
            userHasPermission = permissionService.isAuthorized(userId, namespaceCode, permissionName, qualifiedRoleAttributes); 
        }
        return userHasPermission;
    }

    public boolean hasPermission(String userId, String namespaceCode, String permissionName) {
        boolean userHasPermission = false;
        Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put(UNIT_NUMBER, "*");
        userHasPermission = permissionService.isAuthorized(userId, namespaceCode, permissionName, qualifiedRoleAttributes);
        return userHasPermission;
    }
    
    
    public boolean hasMatchingQualifiedUnits(String userId, String namespaceCode, String permissionName, String unitNumber) {
        List<String> roleIds = new ArrayList<String>();
        Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put(UNIT_NUMBER, unitNumber);
        roleIds = systemAuthorizationService.getRoleIdsForPermission(permissionName, namespaceCode);
        
        List<Map<String,String>> qualifiers = roleManagementService.getNestedRoleQualifiersForPrincipalByRoleIds(userId, roleIds, qualifiedRoleAttributes);
        List<String> units = new ArrayList<String>();    
        for(Map<String,String> qualifier : qualifiers) {
            String tmpUnitNumber = qualifier.get(UNIT_NUMBER);
            if(StringUtils.isNotEmpty(tmpUnitNumber)) {
                units.add(tmpUnitNumber);
            }  
        }
        
        return CollectionUtils.isNotEmpty(units) ;
    }
}

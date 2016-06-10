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
package org.kuali.coeus.common.impl.auth;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.auth.SystemAuthorizationService;
import org.kuali.coeus.common.framework.auth.UnitAuthorizationService;
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
}

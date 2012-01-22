/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.PermissionAttributes;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.service.SystemAuthorizationService;
import org.kuali.kra.service.UnitAuthorizationService;
import org.kuali.kra.service.UnitService;
import org.kuali.rice.kim.api.permission.PermissionService;
import org.kuali.rice.kim.api.role.RoleService;

/**
 * The Unit Authorization Service Implementation.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class UnitAuthorizationServiceImpl implements UnitAuthorizationService {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(KraAuthorizationServiceImpl.class);

    private UnitService unitService;
    private SystemAuthorizationService systemAuthorizationService;
    private RoleService roleManagementService;
    private PermissionService permissionService;
    
    protected RoleService getRoleService() {
        return roleManagementService;
    }

    public void setRoleManagementService(RoleService roleManagementService) {
        this.roleManagementService = roleManagementService;
    }

    protected UnitService getUnitService() {
        return unitService;
    }
    
    public void setUnitService(UnitService unitService) {
        this.unitService = unitService;
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

    /**
     * @see org.kuali.kra.service.UnitAuthorizationService#hasPermission(java.lang.String, java.lang.String, java.lang.String)
     */
    public boolean hasPermission(String userId, String unitNumber, String namespaceCode, String permissionName) {
        boolean userHasPermission = false;
        Map<String, String> permissionAttributes = PermissionAttributes.getAttributes(permissionName);

        if (unitNumber != null) {
            // Check the unit for the permission. If the user doesn't have the permission
            // in the given unit, traverse up the Unit Hierarchy to see if the user has
            // the permission in a higher-level unit with the SUBUNITS flag set to YES.
            Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
            qualifiedRoleAttributes.put(KcKimAttributes.UNIT_NUMBER, unitNumber);
            
            //The UnitHierarchyRoleTypeService takes care of traversing the Unit tree.
            userHasPermission = permissionService.isAuthorized(userId, namespaceCode, permissionName, permissionAttributes, qualifiedRoleAttributes); 
        }
        return userHasPermission;
    }

    /**
     * @see org.kuali.kra.service.UnitAuthorizationService#hasPermission(java.lang.String, java.lang.String)
     */
    public boolean hasPermission(String userId, String namespaceCode, String permissionName) {
        boolean userHasPermission = false;
        Map<String, String> permissionAttributes = PermissionAttributes.getAttributes(permissionName);
        
        Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put(KcKimAttributes.UNIT_NUMBER, "*"); 
        userHasPermission = permissionService.isAuthorized(userId, namespaceCode, permissionName, permissionAttributes, qualifiedRoleAttributes);
        return userHasPermission;
    }
    
    
    public boolean hasMatchingQualifiedUnits(String userId, String namespaceCode, String permissionName, String unitNumber) {
        List<String> roleIds = new ArrayList<String>();
        Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put(KcKimAttributes.UNIT_NUMBER, unitNumber);
        roleIds = systemAuthorizationService.getRoleIdsForPermission(permissionName, namespaceCode);
        
        List<Map<String,String>> qualifiers = roleManagementService.getNestedRoleQualifiersForPrincipalByRoleIds(userId, roleIds, qualifiedRoleAttributes);
        List<String> units = new ArrayList<String>();    
        for(Map<String,String> qualifier : qualifiers) {
            String tmpUnitNumber = qualifier.get(KcKimAttributes.UNIT_NUMBER);
            if(StringUtils.isNotEmpty(tmpUnitNumber)) {
                units.add(tmpUnitNumber);
            }  
        }
        
        return CollectionUtils.isNotEmpty(units) ;
    }

    /**
     * @see org.kuali.kra.service.UnitAuthorizationService#getUnits(java.lang.String, java.lang.String)
     */
    public List<Unit> getUnits(String userId, String namespaceCode, String permissionName) {
        Set<Unit> units = new LinkedHashSet<Unit>();
        // Start by getting all of the Qualified Roles that the person is in.  For each
        // qualified role that has the UNIT_NUMBER qualification, check to see if the role
        // has the required permission.  If so, add that unit to the list.  Also, if the
        // qualified role has the SUBUNITS qualification set to YES, then also add all of the 
        // subunits the to the list.
        List<String> roleIds = new ArrayList<String>();
        Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put(KcKimAttributes.UNIT_NUMBER, "*");
        Map<String,String> qualification =new HashMap<String,String>(qualifiedRoleAttributes);
        roleIds = systemAuthorizationService.getRoleIdsForPermission(permissionName, namespaceCode);
        
        List<Map<String,String>> qualifiers = roleManagementService.getNestedRoleQualifiersForPrincipalByRoleIds(userId, roleIds, qualification);
        for (Map<String,String> qualifier : qualifiers) {
            Unit unit = unitService.getUnit(qualifier.get(KcKimAttributes.UNIT_NUMBER));
            if (unit != null) {
                units.add(unit);
                if (qualifier.containsKey(KcKimAttributes.SUBUNITS) && StringUtils.equalsIgnoreCase("Y", qualifier.get(KcKimAttributes.SUBUNITS))) {
                    addDescendantUnits(unit, units);
                }
            }
        }
        //the above line could potentially be a performance problem - need to revisit
        return new ArrayList<Unit>(units);
    }
    
    protected void addDescendantUnits(Unit parentUnit, Set<Unit> units) { 
        List<Unit> subunits = unitService.getSubUnits(parentUnit.getUnitNumber());
        if (CollectionUtils.isNotEmpty(subunits)) {
            units.addAll(subunits); 
            for (Unit subunit : subunits) {
                addDescendantUnits(subunit, units);
            }
        }
    }
}

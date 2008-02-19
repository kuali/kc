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
package org.kuali.kra.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.kim.pojo.QualifiedRole;
import org.kuali.kra.kim.service.PersonService;
import org.kuali.kra.kim.service.RoleService;
import org.kuali.kra.service.SystemAuthorizationService;
import org.kuali.kra.service.UnitAuthorizationService;
import org.kuali.kra.service.UnitService;

/**
 * The Unit Authorization Service Implementation.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class UnitAuthorizationServiceImpl implements UnitAuthorizationService {

    private static final String UNIT_NUMBER_KEY = "kra.unitNumber";
    private static final String SUBUNITS_KEY = "kra.subunits";
    private static final String YES = "Y";
    
    private SystemAuthorizationService systemAuthorizationService;
    private PersonService kimPersonService;
    private RoleService kimRoleService;
    private UnitService unitService;

    /**
     * Set the System Authorization Service.  Injected by Spring.
     * @param systemAuthorizationService the System Authorization Service
     */
    public void setSystemAuthorizationService(SystemAuthorizationService systemAuthorizationService) {
        this.systemAuthorizationService = systemAuthorizationService;
    }

    /**
     * Set the KIM Person Service.  Injected by Spring.
     * @param personService the KIM Person Service
     */
    public void setKimPersonService(PersonService personService) {
        this.kimPersonService = personService;
    }
    
    /**
     * Set the KIM Role Service.  Injected by Spring.
     * @param roleService the KIM Role Service
     */
    public void setKimRoleService(RoleService roleService) {
        this.kimRoleService = roleService;
    }
    
    /**
     * Set the Unit Service.  Injected by Spring.
     * @param unitService the Unit Service
     */
    public void setUnitService(UnitService unitService) {
        this.unitService = unitService;
    }
    
    /**
     * @see org.kuali.kra.service.UnitAuthorizationService#hasPermission(java.lang.String, java.lang.String, java.lang.String)
     */
    public boolean hasPermission(String username, String unitNumber, String permissionName) {
        
        // Do a quick check to see if the user has the permission
        // in the global space.
        
        boolean userHasPermission = systemAuthorizationService.hasPermission(username, permissionName);
        if (!userHasPermission && unitNumber != null) {
            
            // Check the unit for the permission. If the user doesn't have the permission
            // in the given unit, traverse up the Unit Hierarchy to see if the user has
            // the permission in a higher-level unit with the SUBUNITS flag set to YES.
            
            Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
            qualifiedRoleAttributes.put(UNIT_NUMBER_KEY, unitNumber);
            userHasPermission = kimPersonService.hasQualifiedPermission(username, Constants.KRA_NAMESPACE, permissionName, qualifiedRoleAttributes);
            while (!userHasPermission) {
                Unit unit = unitService.getUnit(unitNumber);
                if (unit != null) {
                    String parentUnitNumber = unit.getParentUnitNumber();
                    if (parentUnitNumber == null) {
                        break;
                    }
                    unitNumber = parentUnitNumber;
                    qualifiedRoleAttributes = new HashMap<String, String>();
                    qualifiedRoleAttributes.put(UNIT_NUMBER_KEY, unitNumber);
                    qualifiedRoleAttributes.put(SUBUNITS_KEY, YES);
                    userHasPermission = kimPersonService.hasQualifiedPermission(username, Constants.KRA_NAMESPACE, permissionName, qualifiedRoleAttributes);
                }
            }
        }
        return userHasPermission;
    }

    /**
     * @see org.kuali.kra.service.UnitAuthorizationService#hasPermission(java.lang.String, java.lang.String)
     */
    public boolean hasPermission(String username, String permissionName) {
        
        // Do a quick check to see if the user has the permission
        // in the global space.
        
        boolean userHasPermission = systemAuthorizationService.hasPermission(username, permissionName);
        if (!userHasPermission) {
            
            // Does the user have this permission in a qualified role?  
            // NOTE: We should only be checking qualified roles corresponding
            //       to units, but the KIM API doesn't provide this.
            
            Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
            userHasPermission = kimPersonService.hasQualifiedPermission(username, Constants.KRA_NAMESPACE, permissionName, qualifiedRoleAttributes);
        }
        return userHasPermission;
    }
    
    /**
     * @see org.kuali.kra.service.UnitAuthorizationService#getUnits(java.lang.String, java.lang.String)
     */
    public List<Unit> getUnits(String username, String permissionName) {
        List<Unit> units = new ArrayList<Unit>();
        
        // Do a quick check to see if the user has the permission
        // in the global space.  If so, the the user has that permission
        // in every unit.
        
        boolean userHasPermission = systemAuthorizationService.hasPermission(username, permissionName);
        if (userHasPermission) {
            units.addAll(unitService.getUnits());
        }
        else {
            
            // Start by getting all of the Qualified Roles that the person is in.  For each
            // qualified role that has the UNIT_NUMBER qualification, check to see if the role
            // has the required permission.  If so, add that unit to the list.  Also, if the
            // qualified role has the SUBUNITS qualification set to YES, then also add all of the 
            // subunits the to the list.
            
            units = new ArrayList<Unit>();
            List<QualifiedRole> qualifiedRoles = kimPersonService.getQualifiedRoles(username);
            
            for (QualifiedRole qualifiedRole : qualifiedRoles) {
                if (qualifiedRole.getQualifiedRoleAttributes().containsKey(UNIT_NUMBER_KEY)) {
                    if (kimRoleService.hasPermission(qualifiedRole.getRoleName(), Constants.KRA_NAMESPACE, permissionName)) {
                        String unitNumber = qualifiedRole.getQualifiedRoleAttributes().get(UNIT_NUMBER_KEY);
                        Unit unit = unitService.getUnit(unitNumber);
                        if (!units.contains(unit)) {
                            units.add(unit);
                            String descendFlag = qualifiedRole.getQualifiedRoleAttributes().get(SUBUNITS_KEY);
                            if (YES.equalsIgnoreCase(descendFlag)) {
                                units.addAll(unitService.getAllSubUnits(unit.getUnitNumber()));
                            }
                        }
                    } 
                }
            }
        }
        return units;
    }
}

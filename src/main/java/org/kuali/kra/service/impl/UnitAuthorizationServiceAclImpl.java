/*
 * Copyright 2006-2008 The Kuali Foundation
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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.bo.UnitAclEntry;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.kim.bo.KimRole;
import org.kuali.kra.kim.service.PersonService;
import org.kuali.kra.kim.service.RoleService;
import org.kuali.kra.service.SystemAuthorizationService;
import org.kuali.kra.service.UnitAuthorizationService;
import org.kuali.kra.service.UnitService;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * The Unit Authorization Service Implementation.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class UnitAuthorizationServiceAclImpl implements UnitAuthorizationService {
    
    private static final String PROPOSAL_ROLE_TYPE = "P";
    
    private SystemAuthorizationService systemAuthorizationService;
    private PersonService kimPersonService;
    private RoleService kimRoleService;
    private UnitService unitService;
    private BusinessObjectService businessObjectService;
    private org.kuali.kra.service.PersonService personService;

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
    
    public void setPersonService(org.kuali.kra.service.PersonService personService) {
        this.personService = personService;
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
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
            
            boolean requireSubunits = false;
            Collection<UnitAclEntry> entries = getAclEntries(username, unitNumber, permissionName);
            while (entries.isEmpty()) {
                requireSubunits = true;
                Unit unit = unitService.getUnit(unitNumber);
                if (unit == null) {
                    break;
                }
                String parentUnitNumber = unit.getParentUnitNumber();
                if (parentUnitNumber == null) {
                    break;
                }
                unitNumber = parentUnitNumber;
                entries = getAclEntries(username, unitNumber, permissionName);
            }
            
            for (UnitAclEntry entry : entries) {
                if (!requireSubunits || entry.getSubunits()) {
                    userHasPermission = true;
                    break;
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
            
            // We just want to know if the person has the given permission anywhere.
            // So, if they do have that permission in any unit, then the answer is yes.
            
            Collection<UnitAclEntry> entries = getAclEntries(username, permissionName);
            if (entries.size() > 0) {
                userHasPermission = true;
            }
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
            /*
             * The following algorithm is a little tricky.  First, find all the
             * ACL entries where the user has the permission.  For those ACL entries
             * that descend to subunits, we must add those subunits.  But there is
             * one exception: we don't traverse subunits that are also in the list
             * of ACL entries.  This is because the permission may be assigned in
             * different units within the unit hierarchy tree.  As we go down the
             * tree, we may encounter a unit where the permission no longer descends.
             */
           Collection<UnitAclEntry> entries = getAclEntries(username, permissionName);
           for (UnitAclEntry entry : entries) {
               addUnitToList(entry.getUnit(), units);
               if (entry.getSubunits()) {
                   addUnitsToList(getSubUnits(entry.getUnitNumber(), entries), units);
               }
           }
        }
        return units;
    }
    
    /**
     * Don't add duplicate units to the list.
     * @param unit
     * @param unitList
     */
    private void addUnitToList(Unit unit, List<Unit> unitList) {
        if (!unitList.contains(unit)) {
            unitList.add(unit);
        }
    }
    
    /**
     * Don't add duplicate units to the list.
     * @param units
     * @param unitList
     */
    private void addUnitsToList(List<Unit> units, List<Unit> unitList) {
        for (Unit unit : units) {
            addUnitToList(unit, unitList);
        }
    }
    
    /**
     * Get all of the subunits for a unit, but exclude any subunits that are
     * in the Unit ACL.
     * @param unitNumber
     * @param entries
     * @return
     */
    private List<Unit> getSubUnits(String unitNumber, Collection<UnitAclEntry> entries) {
        List<Unit> units = new ArrayList<Unit>();
        List<Unit> subunits = unitService.getSubUnits(unitNumber);
        for (Unit unit : subunits) {
            if (!isInAcl(unit, entries)) {
                units.add(unit);
                units.addAll(getSubUnits(unit.getUnitNumber(), entries));
            }
        }
        return units;
    }
    
    /**
     * Is the unit in the Unit ACL?
     * @param unit
     * @param entries
     * @return
     */
    private boolean isInAcl(Unit unit, Collection<UnitAclEntry> entries) {
        String unitNumber = unit.getUnitNumber();
        for (UnitAclEntry entry : entries) {
            if (StringUtils.equals(unitNumber, entry.getUnitNumber())) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Get the list of Unit ACL entries where the user has the given permission.
     * @param username
     * @param permissionName
     * @return
     */
    private Collection<UnitAclEntry> getAclEntries(String username, String permissionName) {
        Person person = personService.getPersonByName(username);
        String personId = person.getPersonId();
        
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("personId", personId);
        fieldValues.put("active", true);
        Collection<UnitAclEntry> aclList = businessObjectService.findMatching(UnitAclEntry.class, fieldValues);
        return filterAcl(aclList, permissionName);
    }
    
    /**
     * Get the list of Unit ACL entries where the user has the given permission in the
     * given unit.
     * @param username
     * @param unitNumber
     * @param permissionName
     * @return
     */
    private Collection<UnitAclEntry> getAclEntries(String username, String unitNumber, String permissionName) {
        Person person = personService.getPersonByName(username);
        String personId = person.getPersonId();
        
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("personId", personId);
        fieldValues.put("unitNumber", unitNumber);
        fieldValues.put("active", true);
        Collection<UnitAclEntry> aclList = businessObjectService.findMatching(UnitAclEntry.class, fieldValues);
        return filterAcl(aclList, permissionName);
    }
    
    /**
     * Filter out the Proposal Roles.  Within the Unit ACL, the assignment
     * of a person to a proposal role is only used as a template, not a 
     * real assignment of a person to a role.
     * @param aclList
     * @return
     */
    private Collection<UnitAclEntry> filterAcl(Collection<UnitAclEntry> aclList, String permissionName) {
        List<UnitAclEntry> list = new ArrayList<UnitAclEntry>();
        for (UnitAclEntry aclEntry : aclList) {
            KimRole role = aclEntry.getRole();
            if (!StringUtils.equals(role.getRoleTypeCode(), PROPOSAL_ROLE_TYPE)) {
                String roleName = role.getName();
                if (kimRoleService.hasPermission(roleName, Constants.KRA_NAMESPACE, permissionName)) {
                    list.add(aclEntry);
                }
            }
        }
        return list;
    }
}

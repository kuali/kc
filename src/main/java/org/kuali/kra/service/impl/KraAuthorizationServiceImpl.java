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
package org.kuali.kra.service.impl;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.RolePersons;
import org.kuali.kra.common.permissions.Permissionable;
import org.kuali.kra.infrastructure.PermissionAttributes;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.service.UnitAuthorizationService;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.identity.principal.PrincipalContract;
import org.kuali.rice.kim.api.permission.PermissionService;
import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.kim.api.role.RoleMembership;
import org.kuali.rice.kim.api.role.RoleService;

import java.util.*;

/**
 * The Kra Authorization Service Implementation.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class KraAuthorizationServiceImpl implements KraAuthorizationService { 
    
    private UnitAuthorizationService unitAuthorizationService;
    private KcPersonService kcPersonService;
    
    private RoleService roleManagementService;
    private IdentityService identityManagementService;
    private PermissionService permissionService;
    
    /**
     * Set the Unit Authorization Service.  Injected by Spring.
     * @param unitAuthorizationService the Unit Authorization Service
     */
    public void setUnitAuthorizationService(UnitAuthorizationService unitAuthorizationService) {
        this.unitAuthorizationService = unitAuthorizationService;
    }
    
    /**
     * Set the KRA Person Service.  Injected by Spring.
     * @param personService the KRA Person Service
     */
    public void setKcPersonService(KcPersonService personService) {
        this.kcPersonService = personService;
    }
    
    public void setRoleManagementService(RoleService roleManagementService) {
        this.roleManagementService = roleManagementService;
    }
    
    public void setIdentityManagementService(IdentityService identityManagementService) {
        this.identityManagementService = identityManagementService;
    }

    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Override
    public List<String> getUserNames(Permissionable permissionable, String roleName) {
        List<String> userNames = new ArrayList<String>();
        Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put(permissionable.getDocumentKey(), permissionable.getDocumentNumberForPermission());
        Collection<String> users = roleManagementService.getRoleMemberPrincipalIds(permissionable.getNamespace(), roleName,new HashMap<String,String>(qualifiedRoleAttributes));
        for(String userId: users) {
            PrincipalContract principal = identityManagementService.getPrincipal(userId);
            if(principal != null) {
                userNames.add(principal.getPrincipalName());
            }
        }
        return userNames;
    }

    @Override
    public void addRole(String userId, String roleName, Permissionable permissionable) {
        Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put(permissionable.getDocumentKey(), permissionable.getDocumentNumberForPermission());
        roleManagementService.assignPrincipalToRole(userId, permissionable.getNamespace(), roleName, new HashMap<String, String>(qualifiedRoleAttributes));
    }

    @Override
    public void removeRole(String userId, String roleName, Permissionable permissionable) {
        Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put(permissionable.getDocumentKey(), permissionable.getDocumentNumberForPermission());
        roleManagementService.removePrincipalFromRole(userId, permissionable.getNamespace(), roleName, new HashMap<String, String>(qualifiedRoleAttributes));
    }

    @Override
    public boolean hasPermission(String userId, Permissionable permissionable, String permissionName) {
        return hasPermission(userId, permissionable, permissionable.getNamespace(), permissionName);
    }

    @Override
    public boolean hasPermission(String userId, Permissionable permissionable, String permissionNamespace, String permissionName) {
        boolean userHasPermission = false;
        Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put(permissionable.getDocumentKey(), permissionable.getDocumentNumberForPermission());
        permissionable.populateAdditionalQualifiedRoleAttributes(qualifiedRoleAttributes);
        Map<String, String> permissionAttributes = PermissionAttributes.getAttributes(permissionName);
        
        String unitNumber = permissionable.getLeadUnitNumber();
        
        if(StringUtils.isNotEmpty(permissionable.getDocumentNumberForPermission())) {
            userHasPermission = permissionService.isAuthorized(userId, permissionNamespace, permissionName, qualifiedRoleAttributes); 
        }
        if (!userHasPermission && StringUtils.isNotEmpty(unitNumber)) {
            userHasPermission = unitAuthorizationService.hasPermission(userId, unitNumber, permissionNamespace, permissionName);
        }
        return userHasPermission;
    }

    @Override
    public boolean hasRole(String userId, Permissionable permissionable, String roleName) {
        Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put(permissionable.getDocumentKey(), permissionable.getDocumentNumberForPermission());
        Role role = roleManagementService.getRoleByNamespaceCodeAndName(permissionable.getNamespace(), roleName);
        if(role != null) {
            return roleManagementService.principalHasRole(userId, Collections.singletonList(role.getId()),new HashMap<String,String>(qualifiedRoleAttributes));
        }
        return false;
    }

    @Override
    public List<String> getRoles(String userId, Permissionable permissionable) {
        Set<String> roleNames = new HashSet<String>();
        String documentNumber = permissionable.getDocumentNumberForPermission();
        Map<String, String> qualifiedRoleAttrs = new HashMap<String, String>();
        qualifiedRoleAttrs.put(permissionable.getDocumentKey(), documentNumber);
        if (documentNumber != null) {
            List<String> roleIds = new ArrayList<String>();
            Map<String, String> roleNameIdMap = new HashMap<String, String>();
            for(String role : permissionable.getRoleNames()) {
                String roleId = roleManagementService.getRoleIdByNamespaceCodeAndName(permissionable.getNamespace(), role);
                roleNameIdMap.put(roleId, role);
                roleIds.add(roleId);
            }
            List<RoleMembership> membershipInfoList = roleManagementService.getRoleMembers(roleIds,new HashMap<String,String>(qualifiedRoleAttrs));
            for(RoleMembership memberShipInfo : membershipInfoList) {
                if(memberShipInfo.getMemberId().equalsIgnoreCase(userId)) {
                    roleNames.add(roleNameIdMap.get(memberShipInfo.getRoleId()));
                }
            }
        }
        
        return new ArrayList<String>(roleNames); 
    }

    @Override
    public List<KcPerson> getPersonsInRole(Permissionable permissionable, String roleName) {
        List<KcPerson> persons = new ArrayList<KcPerson>();
        if(permissionable != null && StringUtils.isNotBlank(roleName)) { 
            Map<String, String> qualifiedRoleAttrs = new HashMap<String, String>();
            qualifiedRoleAttrs.put(permissionable.getDocumentKey(), permissionable.getDocumentNumberForPermission());
            Collection<String> users = roleManagementService.getRoleMemberPrincipalIds(permissionable.getNamespace(), roleName,new HashMap<String,String>(qualifiedRoleAttrs));
            for(String userId : users) {
                KcPerson person = kcPersonService.getKcPersonByPersonId(userId);
                if (person != null && person.getActive()) {
                    persons.add(person);
                }
            }
        } 
        return persons;
    }

    @Override
    public List<RolePersons> getAllRolePersons(Permissionable permissionable) {
        List<RolePersons> rolePersonsList = new ArrayList<RolePersons>();
        
        if(permissionable != null) {
            List<String> roleNames = permissionable.getRoleNames();
            
            for (String roleName : roleNames) {
                List<String> usernames = getUserNames(permissionable, roleName);
                RolePersons rolePersons = new RolePersons();
                rolePersonsList.add(rolePersons);
     
                if(roleName.contains(RoleConstants.AGGREGATOR)) {
                    rolePersons.setAggregator(usernames);
                } else if(roleName.contains(RoleConstants.VIEWER)) {
                    rolePersons.setViewer(usernames); 
                } else if(roleName.contains(RoleConstants.NARRATIVE_WRITER)) {
                    rolePersons.setNarrativewriter(usernames);
                } else if(roleName.contains(RoleConstants.BUDGET_CREATOR)) {
                    rolePersons.setBudgetcreator(usernames);
                }
            }
        }
        
        return rolePersonsList;
    }

    @Override
    public boolean hasRole(String userId, String namespace, String roleName) {
        Role role = roleManagementService.getRoleByNamespaceCodeAndName(namespace, roleName);
        if(role != null) {
            return roleManagementService.principalHasRole(userId, Collections.singletonList(role.getId()), null);
        }
        return false;
    }

    public RoleService getRoleService() {
        return roleManagementService;
    }

    public IdentityService getIdentityService() {
        return identityManagementService;
    }
}
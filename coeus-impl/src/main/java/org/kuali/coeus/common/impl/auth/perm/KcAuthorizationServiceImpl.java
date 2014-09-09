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
package org.kuali.coeus.common.impl.auth.perm;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.auth.UnitAuthorizationService;
import org.kuali.coeus.common.framework.auth.docperm.DocumentAccess;
import org.kuali.coeus.common.framework.auth.perm.DocumentLevelPermissionable;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.common.framework.auth.perm.Permissionable;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.identity.principal.PrincipalContract;
import org.kuali.rice.kim.api.permission.PermissionService;
import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.kim.api.role.RoleMembership;
import org.kuali.rice.kim.api.role.RoleService;
import org.kuali.rice.krad.data.DataObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

import static org.kuali.rice.core.api.criteria.PredicateFactory.*;

/**
 * The KC Authorization Service Implementation.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@Component("kcAuthorizationService")
public class KcAuthorizationServiceImpl implements KcAuthorizationService {

    @Autowired
    @Qualifier("unitAuthorizationService")
    private UnitAuthorizationService unitAuthorizationService;

    @Autowired
    @Qualifier("roleService")
    private RoleService roleManagementService;

    @Autowired
    @Qualifier("identityService")
    private IdentityService identityManagementService;

    @Autowired
    @Qualifier("permissionService")
    private PermissionService permissionService;

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    protected String toDocumentLevelRoleName(String roleName) {
        return roleName + " Document Level";
    }

    protected List<String> filterDocumentLevelRoleNames(Collection<String> roleNames) {
        Set<String> filtered = new HashSet<>();

        for (String name : roleNames) {
            if (StringUtils.endsWith(name, " Document Level")) {
                filtered.add(StringUtils.removeEnd(name, " Document Level"));
            }
        }

        return new ArrayList<>(filtered);
    }

    @Override
    public List<String> getUserNames(Permissionable permissionable, String roleName) {
        Set<String> userNames = new HashSet<String>();
        final Map<String, String> qualifiedRoleAttributes = createStandardQualifiers(permissionable);
        Collection<String> users = new HashSet<>(roleManagementService.getRoleMemberPrincipalIds(permissionable.getNamespace(), roleName,new HashMap<String,String>(qualifiedRoleAttributes)));
        if (permissionable instanceof DocumentLevelPermissionable) {
            users.addAll(roleManagementService.getRoleMemberPrincipalIds(permissionable.getNamespace(), toDocumentLevelRoleName(roleName),new HashMap<String,String>(qualifiedRoleAttributes)));
        }

        for(String userId: users) {
            PrincipalContract principal = identityManagementService.getPrincipal(userId);
            if(principal != null) {
                userNames.add(principal.getPrincipalName());
            }
        }

        return new ArrayList<>(userNames);
    }

    @Override
    public void addRole(String userId, String roleName, Permissionable permissionable) {
        if (permissionable instanceof DocumentLevelPermissionable) {
            validateDocumentLevelArguments(((DocumentLevelPermissionable) permissionable).getDocumentNumber(), userId, roleName, permissionable.getNamespace());
            dataObjectService.save(new DocumentAccess(((DocumentLevelPermissionable) permissionable).getDocumentNumber(),
                    userId, toDocumentLevelRoleName(roleName), permissionable.getNamespace()));
        } else {
            final Map<String, String> qualifiedRoleAttributes = createStandardQualifiers(permissionable);
            roleManagementService.assignPrincipalToRole(userId, permissionable.getNamespace(), roleName, new HashMap<String, String>(qualifiedRoleAttributes));
        }
    }

    @Override
    public void removeRole(String userId, String roleName, Permissionable permissionable) {
        if (permissionable instanceof DocumentLevelPermissionable) {
            validateDocumentLevelArguments(((DocumentLevelPermissionable) permissionable).getDocumentNumber(), userId, roleName, permissionable.getNamespace());
            dataObjectService.deleteMatching(DocumentAccess.class, QueryByCriteria.Builder.fromPredicates(
                equal("documentNumber", ((DocumentLevelPermissionable) permissionable).getDocumentNumber()),
                equal("principalId", userId),
                equal("roleName", toDocumentLevelRoleName(roleName)),
                equal("namespaceCode", permissionable.getNamespace())
            ));
        } else {
            final Map<String, String> qualifiedRoleAttributes = createStandardQualifiers(permissionable);
            roleManagementService.removePrincipalFromRole(userId, permissionable.getNamespace(), roleName, new HashMap<String, String>(qualifiedRoleAttributes));
        }
    }

    protected void validateDocumentLevelArguments(String documentNumber, String principalId, String roleName, String namespaceCode) {
        if (StringUtils.isBlank(documentNumber)) {
            throw new IllegalArgumentException("documentNumber is blank");
        }

        if (StringUtils.isBlank(principalId)) {
            throw new IllegalArgumentException("principalId is blank");
        }

        if (StringUtils.isBlank(roleName)) {
            throw new IllegalArgumentException("roleName is blank");
        }

        if (StringUtils.isBlank(namespaceCode)) {
            throw new IllegalArgumentException("namespaceCode is blank");
        }
    }

    @Override
    public boolean hasPermission(String userId, Permissionable permissionable, String permissionName) {
        return hasPermission(userId, permissionable, permissionable.getNamespace(), permissionName);
    }

    protected boolean hasPermission(String userId, Permissionable permissionable, String permissionNamespace, String permissionName) {
        boolean userHasPermission = false;
        final Map<String, String> qualifiedRoleAttributes = createStandardQualifiers(permissionable);
        permissionable.populateAdditionalQualifiedRoleAttributes(qualifiedRoleAttributes);

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
        boolean hasRole = false;

        final Map<String, String> qualifiedRoleAttributes = createStandardQualifiers(permissionable);
        Role role = roleManagementService.getRoleByNamespaceCodeAndName(permissionable.getNamespace(), roleName);
        if(role != null) {
            hasRole = roleManagementService.principalHasRole(userId, Collections.singletonList(role.getId()),new HashMap<String,String>(qualifiedRoleAttributes));
        }

        if (!hasRole && permissionable instanceof DocumentLevelPermissionable) {
            role = roleManagementService.getRoleByNamespaceCodeAndName(permissionable.getNamespace(), toDocumentLevelRoleName(roleName));
            if(role != null) {
                hasRole = roleManagementService.principalHasRole(userId, Collections.singletonList(role.getId()),new HashMap<String,String>(qualifiedRoleAttributes));
            }
        }

        return hasRole;
    }

    @Override
    public List<String> getRoles(String userId, Permissionable permissionable) {
        Set<String> roleNames = new HashSet<String>();
        String documentNumber = permissionable.getDocumentNumberForPermission();
        final Map<String, String> qualifiedRoleAttrs = createStandardQualifiers(permissionable);
        if (documentNumber != null) {
            List<String> roleIds = new ArrayList<String>();
            Map<String, String> roleNameIdMap = new HashMap<String, String>();
            for(String role : permissionable.getRoleNames()) {
                String roleId = roleManagementService.getRoleIdByNamespaceCodeAndName(permissionable.getNamespace(), role);
                roleNameIdMap.put(roleId, role);
                roleIds.add(roleId);

                roleId = roleManagementService.getRoleIdByNamespaceCodeAndName(permissionable.getNamespace(), toDocumentLevelRoleName(role));
                roleNameIdMap.put(roleId, toDocumentLevelRoleName(role));
                roleIds.add(roleId);
            }
            List<RoleMembership> membershipInfoList = roleManagementService.getRoleMembers(roleIds,new HashMap<String,String>(qualifiedRoleAttrs));
            for(RoleMembership memberShipInfo : membershipInfoList) {
                if(memberShipInfo.getMemberId().equalsIgnoreCase(userId)) {
                    roleNames.add(roleNameIdMap.get(memberShipInfo.getRoleId()));
                }
            }
        }
        
        return filterDocumentLevelRoleNames(roleNames);
    }

    @Override
    public List<String> getPrincipalsInRole(Permissionable permissionable, String roleName) {
        Set<String> principals = new HashSet<>();

        if (permissionable != null && StringUtils.isNotBlank(roleName)) {
            principals.addAll(roleManagementService.getRoleMemberPrincipalIds(permissionable.getNamespace(), roleName, createStandardQualifiers(permissionable)));
            if (permissionable instanceof DocumentLevelPermissionable) {
                principals.addAll(roleManagementService.getRoleMemberPrincipalIds(permissionable.getNamespace(), toDocumentLevelRoleName(roleName), createStandardQualifiers(permissionable)));
            }
        }

        return new ArrayList<>(principals);
    }

    protected  Map<String, String> createStandardQualifiers(Permissionable permissionable) {
        final Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put(permissionable.getDocumentKey(), permissionable.getDocumentNumberForPermission());
        addDocumentQualifiers(permissionable, qualifiedRoleAttributes);
        return qualifiedRoleAttributes;
    }

    protected void addDocumentQualifiers(Permissionable permissionable, Map<String, String> qualifiers) {
        if (permissionable instanceof DocumentLevelPermissionable && qualifiers != null) {
            qualifiers.put(KcKimAttributes.DOCUMENT_NUMBER, ((DocumentLevelPermissionable) permissionable).getDocumentNumber());
        }
    }

    public void setUnitAuthorizationService(UnitAuthorizationService unitAuthorizationService) {
        this.unitAuthorizationService = unitAuthorizationService;
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

    public RoleService getRoleService() {
        return roleManagementService;
    }

    public IdentityService getIdentityService() {
        return identityManagementService;
    }
}
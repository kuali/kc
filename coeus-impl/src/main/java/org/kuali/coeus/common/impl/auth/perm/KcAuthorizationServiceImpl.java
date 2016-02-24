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
package org.kuali.coeus.common.impl.auth.perm;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.auth.UnitAuthorizationService;
import org.kuali.coeus.common.framework.auth.docperm.DocumentAccess;
import org.kuali.coeus.common.framework.auth.docperm.DocumentAccessConstants;
import org.kuali.coeus.common.framework.auth.perm.DocumentLevelPermissionable;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.common.framework.auth.perm.Permissionable;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.kim.api.permission.PermissionService;
import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.kim.api.role.RoleService;
import org.kuali.rice.kim.api.type.KimType;
import org.kuali.rice.kim.api.type.KimTypeInfoService;
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
    @Qualifier("permissionService")
    private PermissionService permissionService;

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    @Autowired
    @Qualifier("kimTypeInfoService")
    private KimTypeInfoService kimTypeInfoService;

    protected String toDocumentLevelRoleName(String roleName) {

        if (!roleName.endsWith(" Document Level")) {
            return roleName + " Document Level";
        }
        return roleName;
    }

    @Override
    public void addDocumentLevelRole(String userId, String roleName, Permissionable permissionable) {
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
    public void removeDocumentLevelRole(String userId, String roleName, Permissionable permissionable) {
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

    @Override
    public boolean hasDocumentLevelRole(String userId, String roleName, Permissionable permissionable) {
        String updatedRoleName = roleName;
        if (permissionable instanceof DocumentLevelPermissionable) {
            validateDocumentLevelArguments(((DocumentLevelPermissionable) permissionable).getDocumentNumber(), userId, roleName, permissionable.getNamespace());
            updatedRoleName = toDocumentLevelRoleName(roleName);
        }

        final Map<String, String> qualifiedRoleAttributes = createStandardQualifiers(permissionable);
        Role role = roleManagementService.getRoleByNamespaceCodeAndName(permissionable.getNamespace(), updatedRoleName);
        return role != null && roleManagementService.principalHasRole(userId, Collections.singletonList(role.getId()), qualifiedRoleAttributes);
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

        Role role = roleManagementService.getRoleByNamespaceCodeAndName(namespaceCode, toDocumentLevelRoleName(roleName));
        if (role == null) {
            throw new IllegalStateException("role not found with namespace: " + namespaceCode + " and name " + toDocumentLevelRoleName(roleName));
        }

        final KimType type = kimTypeInfoService.getKimType(role.getKimTypeId());
        if (!type.getName().equals(DocumentAccessConstants.DOC_LEVEL_KIM_TYPE_NAME) || !type.getNamespaceCode().equals(DocumentAccessConstants.DOC_LEVEL_KIM_TYPE_NAMESPACE)) {
            throw new IllegalArgumentException("role not document level type with namespace: " + namespaceCode + " and name " + toDocumentLevelRoleName(roleName));
        }
    }

    @Override
    public boolean hasPermission(String userId, Permissionable permissionable, String permissionName) {
        return hasPermission(userId, permissionable, permissionable == null ? null : permissionable.getNamespace(), permissionName);
    }

    protected boolean hasPermission(String userId, Permissionable permissionable, String permissionNamespace, String permissionName) {
        boolean userHasPermission = false;
        if (permissionable != null) {
	        final Map<String, String> qualifiedRoleAttributes = createStandardQualifiers(permissionable);
	        permissionable.populateAdditionalQualifiedRoleAttributes(qualifiedRoleAttributes);
	
	        String unitNumber = permissionable.getLeadUnitNumber();
	        
	        if(StringUtils.isNotEmpty(permissionable.getDocumentNumberForPermission())) {
	            userHasPermission = permissionService.isAuthorized(userId, permissionNamespace, permissionName, qualifiedRoleAttributes); 
	        }
	        if (!userHasPermission && StringUtils.isNotEmpty(unitNumber)) {
	            userHasPermission = unitAuthorizationService.hasPermission(userId, unitNumber, permissionNamespace, permissionName);
	        }
        }
        return userHasPermission;
    }

    @Override
    public List<String> getPrincipalsInRole(String roleName, Permissionable permissionable) {
        Set<String> principals = new HashSet<>();

        if (permissionable != null && StringUtils.isNotBlank(roleName)) {
            principals.addAll(roleManagementService.getRoleMemberPrincipalIds(permissionable.getNamespace(), roleName, createStandardQualifiers(permissionable)));
            if (permissionable instanceof DocumentLevelPermissionable) {
                String docLevelRole = toDocumentLevelRoleName(roleName);
                if (roleManagementService.getRoleByNamespaceCodeAndName(permissionable.getNamespace(), docLevelRole) != null) {
                    principals.addAll(roleManagementService.getRoleMemberPrincipalIds(permissionable.getNamespace(), toDocumentLevelRoleName(roleName), createStandardQualifiers(permissionable)));
                }
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

    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    public RoleService getRoleService() {
        return roleManagementService;
    }

    public UnitAuthorizationService getUnitAuthorizationService() {
        return unitAuthorizationService;
    }

    public RoleService getRoleManagementService() {
        return roleManagementService;
    }

    public PermissionService getPermissionService() {
        return permissionService;
    }

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }

    public KimTypeInfoService getKimTypeInfoService() {
        return kimTypeInfoService;
    }

    public void setKimTypeInfoService(KimTypeInfoService kimTypeInfoService) {
        this.kimTypeInfoService = kimTypeInfoService;
    }
}

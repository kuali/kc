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

import org.kuali.coeus.common.framework.auth.SystemAuthorizationService;
import org.kuali.rice.core.api.criteria.Predicate;
import org.kuali.rice.core.api.criteria.PredicateFactory;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.kim.api.KimConstants;
import org.kuali.rice.kim.api.permission.PermissionService;
import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.kim.api.role.RoleQueryResults;
import org.kuali.rice.kim.api.role.RoleService;
import org.kuali.rice.kim.api.type.KimType;
import org.kuali.rice.kim.api.type.KimTypeInfoService;
import org.kuali.rice.krad.util.KRADPropertyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The System Authorization Service Implementation.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@Component("systemAuthorizationService")
public class SystemAuthorizationServiceImpl implements SystemAuthorizationService {

    @Autowired
    @Qualifier("permissionService")
    private PermissionService permissionService;

    @Autowired
    @Qualifier("roleService")
    private RoleService roleManagementService;

    @Autowired
    @Qualifier("kimTypeInfoService")
    private KimTypeInfoService kimTypeInfoService;
    
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    public void setRoleManagementService(RoleService roleManagementService) {
        this.roleManagementService = roleManagementService;
    }

    protected KimTypeInfoService getKimTypeInfoService() {
        return kimTypeInfoService;
    }

    public void setKimTypeInfoService(KimTypeInfoService kimTypeInfoService) {
        this.kimTypeInfoService = kimTypeInfoService;
    }
    

    public List<Role> getRolesForPermission(String permissionName, String namespaceCode) {
        List<String> roleResults = permissionService.getRoleIdsForPermission(namespaceCode, permissionName);
        return roleManagementService.getRoles(roleResults);
    }

    public List<String> getRoleNamesForPermission(String permissionName, String namespaceCode) {
        List<String> roleNames = new ArrayList<String>();
        List<Role> roles = getRolesForPermission(permissionName, namespaceCode);
        for(Role role: roles) {
            roleNames.add(role.getName());
        }
        return roleNames;
    }

    public List<String> getRoleIdsForPermission(String permissionName, String namespaceCode) {
        List<String> roleNames = new ArrayList<String>();
        List<Role> roles = getRolesForPermission(permissionName, namespaceCode);
        for(Role role: roles) {
            roleNames.add(role.getId());
        }
        return roleNames;
    }

    @Override
    public boolean hasRole(String userId, String namespace, String roleName) {
        Role role = roleManagementService.getRoleByNamespaceCodeAndName(namespace, roleName);
        return role != null && roleManagementService.principalHasRole(userId, Collections.singletonList(role.getId()), null);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Role> getRoles(String namespaceCode) {
        QueryByCriteria.Builder queryBuilder = QueryByCriteria.Builder.create();
        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(PredicateFactory.equal(KimConstants.UniqueKeyConstants.NAMESPACE_CODE, namespaceCode));
        predicates.add(PredicateFactory.equal(KRADPropertyConstants.ACTIVE, Boolean.TRUE));
        queryBuilder.setPredicates(PredicateFactory.and(predicates.toArray(new Predicate[] {})));
        RoleQueryResults roleResults = roleManagementService.findRoles(queryBuilder.build());
        return roleResults.getResults();
    }

    public KimType getKimTypeInfoForRole(Role role) {
        return getKimTypeInfoService().getKimType(role.getKimTypeId());
    }

    @Override
    public List<Role> getRolesByType(String roleNamespaceCode, String typeName, String typeNamespace) {
        final List<Role> roles = getRoles(roleNamespaceCode);
        return filterByType(roles, typeName, typeNamespace);
    }

    protected List<Role> filterByType(List<Role> roles, String typeName, String typeNamespace) {
        List<Role> filtered = new ArrayList<>();
        for (Role r : roles) {
            KimType type = getKimTypeInfoForRole(r);
            if (type.getNamespaceCode().equals(typeNamespace) && type.getName().equals(typeName)) {
                filtered.add(r);
            }
        }
        return filtered;
    }
}

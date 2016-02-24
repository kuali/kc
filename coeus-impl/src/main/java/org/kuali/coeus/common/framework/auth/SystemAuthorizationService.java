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
package org.kuali.coeus.common.framework.auth;

import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.kim.api.type.KimType;

import java.util.List;

/**
 * The System Authorization Service handles authorization in the global space.
 * It can be used to determine if a user has permission in the global space.  Also,
 * since all Roles and Permissions are at the global space, this service also provides
 * methods for querying Roles and Permissions.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public interface SystemAuthorizationService {
    
    /**
     * Get all of the roles for a particular type.
     * @param namespaceCode the namespace of the role
     * @return the KIM roles
     */
    public List<Role> getRoles(String namespaceCode);
    
    public List<Role> getRolesForPermission(String permissionName, String namespaceCode);
    
    public List<String> getRoleNamesForPermission(String permissionName, String namespaceCode);
    
    public List<String> getRoleIdsForPermission(String permissionName, String namespaceCode);
    
    public KimType getKimTypeInfoForRole(Role role);

    public List<Role> getRolesByType(String roleNamespaceCode, String typeName, String typeNamespace);

    public boolean hasRole(String userId, String namespace, String roleName);
    
}

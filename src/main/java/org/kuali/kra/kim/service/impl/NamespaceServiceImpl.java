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
package org.kuali.kra.kim.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.kuali.kra.kim.bo.KimPermission;
import org.kuali.kra.kim.pojo.Permission;
import org.kuali.kra.kim.service.NamespaceService;

/**
 * The KIM Namespace Service allows users to query the
 * Permissions associated with a Namespace.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class NamespaceServiceImpl extends ServiceBase implements NamespaceService {
    
    /**
     * @see org.kuali.kra.kim.service.NamespaceService#getPermissionNames(java.lang.String)
     */
    public List<String> getPermissionNames(String namespaceName) {
        List<String> permissionNames = new ArrayList<String>();
        Long namespaceId = getNamespaceId(namespaceName);
        Collection<KimPermission> kimPermissions = getNamespacePermissions(namespaceId);
        for (KimPermission kimPermission : kimPermissions) {
            permissionNames.add(kimPermission.getName());
        }
        return permissionNames;
    }

    /**
     * @see org.kuali.kra.kim.service.NamespaceService#getPermissions(java.lang.String)
     */
    public List<Permission> getPermissions(String namespaceName) {
        List<Permission> permissions = new ArrayList<Permission>();
        Long namespaceId = getNamespaceId(namespaceName);
        Collection<KimPermission> kimPermissions = getNamespacePermissions(namespaceId);
        for (KimPermission kimPermission : kimPermissions) {
            Permission permission = buildPermission(kimPermission);
            permissions.add(permission);
        }
        return permissions;
    }
}

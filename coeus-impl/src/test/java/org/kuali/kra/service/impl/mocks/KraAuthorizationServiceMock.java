/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.service.impl.mocks;

import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.common.framework.auth.perm.Permissionable;

import java.util.List;

public class KraAuthorizationServiceMock implements KcAuthorizationService {

    private boolean hasPermission;
    
    public KraAuthorizationServiceMock(boolean hasPermission) {
        this.hasPermission = hasPermission;
    }
    @Override
    public void addRole(String username, String roleName, Permissionable permissionable) {
     
    }

    @Override
    public List<String> getPrincipalsInRole(Permissionable permissionable, String roleName) {
        return null;
    }
    @Override
    public List<String> getRoles(String username, Permissionable permissionable) {
        return null;
    }
    @Override
    public List<String> getUserNames(Permissionable permissionable, String roleName) {
        return null;
    }
    @Override
    public boolean hasPermission(String username, Permissionable permissionable, String permissionName) {
        return hasPermission;
    }
    @Override
    public boolean hasRole(String username, Permissionable permissionable, String roleName) {
        return false;
    }
    @Override
    public void removeRole(String username, String roleName, Permissionable permissionable) {
        
    }
    @Override
    public boolean hasRole(String userId, String namespace, String roleName) {
        return false;
    }

    @Override
    public boolean hasPermission(String userId, Permissionable permissionable, String permissionNamespace, String permissionName) {
        return hasPermission;
    }
    
}

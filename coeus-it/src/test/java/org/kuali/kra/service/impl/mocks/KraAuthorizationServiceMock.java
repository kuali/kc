/*
 * Copyright 2005-2013 The Kuali Foundation
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
    public void addDocumentLevelRole(String username, String roleName, Permissionable permissionable) {
     
    }
    @Override
    public List<String> getPrincipalsInRole(String roleName, Permissionable permissionable) {
        return null;
    }

    @Override
    public boolean hasPermission(String username, Permissionable permissionable, String permissionName) {
        return hasPermission;
    }
    @Override
    public boolean hasDocumentLevelRole(String username, String roleName, Permissionable permissionable) {
        return false;
    }
    @Override
    public void removeDocumentLevelRole(String username, String roleName, Permissionable permissionable) {
        
    }
}

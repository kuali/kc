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

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
package org.kuali.kra.committee.authorizers;

import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.auth.UnitAuthorizationService;

import java.util.List;

public class UnitAuthorizationServiceMock implements UnitAuthorizationService {
    
    private boolean hasPermission;
    
    public UnitAuthorizationServiceMock(boolean hasPermission) {
        this.hasPermission = hasPermission;
    }

    public List<Unit> getUnits(String userId, String namespaceCode, String permissionName) {
        return null;
    }

    public boolean hasPermission(String userId, String namespaceCode, String permissionName) {
        return hasPermission;
    }

    public boolean hasPermission(String userId, String unitNumber, String namespaceCode, String permissionName) {
        return false;
    }
    public boolean hasMatchingQualifiedUnits(String userId, String namespaceCode, String permissionName, String unitNumber) {
        return false;
    }
    
}

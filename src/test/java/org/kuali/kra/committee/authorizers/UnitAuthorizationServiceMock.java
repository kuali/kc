/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.committee.authorizers;

import java.util.List;

import org.kuali.kra.bo.Unit;
import org.kuali.kra.service.UnitAuthorizationService;

public class UnitAuthorizationServiceMock implements UnitAuthorizationService {
    
    private boolean hasPermission;
    
    public UnitAuthorizationServiceMock(boolean hasPermission) {
        this.hasPermission = hasPermission;
    }

    public List<Unit> getUnits(String username, String permissionName) {
        return null;
    }

    public boolean hasPermission(String username, String permissionName) {
        return hasPermission;
    }

    public boolean hasPermission(String username, String unitNumber, String permissionName) {
        return false;
    }
}

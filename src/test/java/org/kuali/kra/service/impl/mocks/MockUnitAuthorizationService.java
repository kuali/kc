/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.service.impl.mocks;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.bo.Unit;
import org.kuali.kra.service.UnitAuthorizationService;

/**
 * Mock System Authorization Service.  The result determines the return
 * value of the hasPermission() method.
 */
public class MockUnitAuthorizationService implements UnitAuthorizationService {
    
    /**
     * @see org.kuali.kra.service.UnitAuthorizationService#getUnits(java.lang.String, java.lang.String)
     */
    public List<Unit> getUnits(String username, String permissionName) {
        return new ArrayList<Unit>();
    }

    /**
     * @see org.kuali.kra.service.UnitAuthorizationService#hasPermission(java.lang.String, java.lang.String)
     */
    public boolean hasPermission(String username, String permissionName) {
        return false;
    }

    /**
     * @see org.kuali.kra.service.UnitAuthorizationService#hasPermission(java.lang.String, java.lang.String, java.lang.String)
     */
    public boolean hasPermission(String username, String unitNumber, String permissionName) {
        return false;
    }
}

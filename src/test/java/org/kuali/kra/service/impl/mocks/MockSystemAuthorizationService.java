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
package org.kuali.kra.service.impl.mocks;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.kim.pojo.Permission;
import org.kuali.kra.kim.pojo.Role;
import org.kuali.kra.service.SystemAuthorizationService;

/**
 * Mock System Authorization Service.  The result determines the return
 * value of the hasPermission() method.
 */
public class MockSystemAuthorizationService implements SystemAuthorizationService {

    private boolean result = false;
    
    /**
     * Constructs a MockSystemAuthorizationService.
     */
    public MockSystemAuthorizationService() {
        
    }
    
    /**
     * Constructs a MockSystemAuthorizationService.
     * @param result
     */
    public MockSystemAuthorizationService(boolean result) {
        this.result = result;
    }
    
    /**
     * This method...
     * @param result
     */
    public void setResult(boolean result) {
        this.result = result;
    }
    
    /**
     * @see org.kuali.kra.service.SystemAuthorizationService#getPermissionsForRole(java.lang.String)
     */
    public List<Permission> getPermissionsForRole(String roleName) {
        return new ArrayList<Permission>();
    }

    /**
     * @see org.kuali.kra.service.SystemAuthorizationService#getRole(java.lang.String)
     */
    public Role getRole(String roleName) {
        return null;
    }

    /**
     * @see org.kuali.kra.service.SystemAuthorizationService#hasPermission(java.lang.String, java.lang.String)
     */
    public boolean hasPermission(String username, String permissionName) {
        return result;
    }
}

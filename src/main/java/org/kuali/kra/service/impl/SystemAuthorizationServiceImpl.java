/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.kim.bo.KimRole;
import org.kuali.kra.kim.pojo.Permission;
import org.kuali.kra.kim.pojo.Role;
import org.kuali.kra.kim.service.PersonService;
import org.kuali.kra.kim.service.RoleService;
import org.kuali.kra.service.SystemAuthorizationService;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * The System Authorization Service Implementation.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class SystemAuthorizationServiceImpl implements SystemAuthorizationService {
    
    private BusinessObjectService businessObjectService;
    private PersonService kimPersonService;
    private RoleService kimRoleService;

    /**
     * Set the BusinessObjectService.
     * @param businessObjectService
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    /**
     * Set the KIM Person Service.  Set by Spring with dependency injection.
     * 
     * @param kimPersonService the KIM Person Service
     */
    public void setKimPersonService(PersonService kimPersonService) {
        this.kimPersonService = kimPersonService;
    }
    
    /**
     * Set the KIM Role Service.  Set by Spring with dependency injection.
     * 
     * @param kimRoleService the KIM Role Service
     */
    public void setKimRoleService(RoleService kimRoleService) {
        this.kimRoleService = kimRoleService;
    }
    
    /**
     * @see org.kuali.kra.service.SystemAuthorizationService#getRole(java.lang.Integer)
     */
    public Role getRole(String roleName) {
        return kimRoleService.getRole(roleName);
    }
    
    /**
     * @see org.kuali.kra.service.SystemAuthorizationService#getPermissionsForRole(java.lang.String)
     * @throws a RuntimeException if the Role does not exist.
     */
    public List<Permission> getPermissionsForRole(String roleName) {
        return kimRoleService.getPermissions(roleName);
    }

    /**
     * @see org.kuali.kra.service.SystemAuthorizationService#hasPermission(java.lang.String, java.lang.String)
     */
    public boolean hasPermission(String username, String permissionName) {
        return kimPersonService.hasPermission(username, Constants.KRA_NAMESPACE, permissionName);
    }
    
    /**
     * @see org.kuali.kra.service.SystemAuthorizationService#getRoles(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public Collection<KimRole> getRoles(String roleType) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("roleTypeCode", roleType);
        return businessObjectService.findMatching(KimRole.class, fieldValues);
    }
}

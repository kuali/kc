/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.kim.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.kim.service.ProposalRoleService;
import org.kuali.kra.service.SystemAuthorizationService;
import org.kuali.rice.kim.api.role.Role;

public class ProposalRoleServiceImpl implements ProposalRoleService {
    private SystemAuthorizationService systemAuthorizationService;
    
    public void setSystemAuthorizationService(SystemAuthorizationService systemAuthorizationService) {
        this.systemAuthorizationService = systemAuthorizationService;
    }

    public List<Role> getRoles() {
        return systemAuthorizationService.getRoles(RoleConstants.PROPOSAL_ROLE_TYPE);
    }
    
    protected boolean isStandardProposalRole(String roleName) {
        return StringUtils.equals(roleName, RoleConstants.AGGREGATOR)
                || StringUtils.equals(roleName, RoleConstants.NARRATIVE_WRITER)
                || StringUtils.equals(roleName, RoleConstants.BUDGET_CREATOR) || StringUtils.equals(roleName, RoleConstants.VIEWER);
    }
    
    protected boolean isRoleUnassigned(String roleName) {
        return StringUtils.equals(roleName, RoleConstants.UNASSIGNED);
    }
    
    public List<Role> getRolesForDisplay() {
        List<Role> proposalRoles = getRoles();
        List<Role> finalRoleList = new ArrayList<Role>();
        
        for (Role role : proposalRoles) {
            if (isRoleUnassigned(role.getName())) { 
                finalRoleList.add(role);
            } else if (isStandardProposalRole(role.getName())){
                finalRoleList.add(role);
            }
        }
        
        /*
         * Now add in all of the other user-defined proposal roles.
         */
        for (Role role : proposalRoles) {
            if (!isRoleUnassigned(role.getName()) && !isStandardProposalRole(role.getName())) {
                finalRoleList.add(role);
            }
        }

        return finalRoleList;
    }
    
}

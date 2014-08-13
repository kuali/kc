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
package org.kuali.kra.coi.auth;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiUserRole;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.identity.principal.Principal;
import org.kuali.rice.kim.api.role.RoleService;

import java.util.Collection;
import java.util.List;

public abstract class CoiDeleteUpdateNotesAttachmentsAuthorizerBase extends CoiDisclosureAuthorizer {

    public boolean isAuthorized(String userId, CoiDisclosureTask task, String updateUser) {
        Principal user = getIdentityService().getPrincipal(userId);
        String currentUser = user.getPrincipalName();
        
        if (!StringUtils.equalsIgnoreCase(updateUser, currentUser)) {
            // if the note has been created by neither reviewer nor admin return false;                                                                                          
            // if current user is admin and if note not created by reviewer or admin, return false.                                                                          
            if (isAdministrator(userId)) {
                // if note creator is reviewer or administrator                                                                                                              
                if (!isReviewer(updateUser, task.getCoiDisclosure()) && !isAdministrator(updateUser)) {
                    return false;
                }
                // if not administrator you cannot edit or delete anybody else's note.                                                                                       
            } else {
                return false;
            }
        }

        return true;
    }
    
    protected boolean isAdministrator(String userId) {                                                                                                                       
        Collection<String> ids = getRoleService().getRoleMemberPrincipalIds(RoleConstants.COI_DISCLOSURE_ROLE_TYPE,RoleConstants.COI_ADMINISTRATOR, null);                         
        return ids.contains(userId);                                                                                                                                          
    }                                                                                                                                                                         

    protected boolean isReviewer(String userId, CoiDisclosure disclosure) {                                                                                                   
        List<CoiUserRole> userRoles = disclosure.getCoiUserRoles();                                                                                                           
        for (CoiUserRole userRole : userRoles) {                                                                                                                              
            if (StringUtils.equalsIgnoreCase(userRole.getRoleName(), RoleConstants.COI_REVIEWER)) {                                                                           
                if (StringUtils.equalsIgnoreCase(userId, userRole.getUserId())) {                                                                                             
                    return true;                                                                                                                                              
                }                                                                                                                                                             
            }                                                                                                                                                                 
        }                                                                                                                                                                     
        return false;                                                                                                                                                        
    }        
    
    public abstract IdentityService getIdentityService();

    public abstract RoleService getRoleService();
}


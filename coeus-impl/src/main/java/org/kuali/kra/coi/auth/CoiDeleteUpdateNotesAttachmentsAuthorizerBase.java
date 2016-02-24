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


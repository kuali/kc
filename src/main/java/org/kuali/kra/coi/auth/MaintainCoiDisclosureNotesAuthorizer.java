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
package org.kuali.kra.coi.auth;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiUserRole;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.identity.principal.Principal;
import org.kuali.rice.kim.api.role.RoleService;

public class MaintainCoiDisclosureNotesAuthorizer extends CoiDisclosureAuthorizer {

    private IdentityService identityService;
    private RoleService roleService;

    @Override
    public boolean isAuthorized(String userId, CoiDisclosureTask task) {
        boolean hasPermission = true;
      
            // check if the user is creating a new disclosure and has the explicit report disclosure permissions
            CoiDisclosure coiDisclosure = task.getCoiDisclosure();
            if (isNewDisclosure(coiDisclosure)) {
                hasPermission = hasUnitPermission(userId, Constants.MODULE_NAMESPACE_COIDISCLOSURE, PermissionConstants.REPORT_COI_DISCLOSURE);
            } 
            else {
                // check if the user is the original reporter for the saved disclosure and that the disclosure is editable, 
                // and that it is not yet submitted (certified)
                hasPermission = hasPermissionToEdit(coiDisclosure, userId);

            }
        return hasPermission;  
    }
    
    protected boolean isNewDisclosure(CoiDisclosure coiDisclosure) {
        return coiDisclosure == null || !coiDisclosure.isSubmitted();
    }
    
    /*                                                                                                                                                                           
     * If the person is the original reporter and not submitted or if the person is reviewer or admin and not approved.                                                          
     */
    protected boolean hasPermissionToEdit(CoiDisclosure disclosure, String userId) {
        if (isAdministrator(userId) || isReviewer(userId, disclosure)) {
            System.out.println(disclosure.getCoiDisclosureDocument().getDocumentHeader().getWorkflowDocument().isFinal());
            /*
             * Going to keep this auth so that once a disclosure is approved, you cannot edit N&A. This is because, in order to edit Notes
             * or replace attachments, you need the save button and that button does not appear if the document has been approved because the MasterDisclosure.jsp which
             * is where we forward to once a disclosure is approved, and that page sets viewOnly to true in the documentControls.tag file.
             */
            if(!disclosure.getCoiDisclosureDocument().getDocumentHeader().getWorkflowDocument().isFinal() &&
                    isEditableByAdminReviewer(disclosure)) {
                return true;
            }
        } else {
            // check if the user is the original reporter for the saved disclosure and that the disclosure is editable,                                                          
            // and that it is not yet submitted (certified)                                                                                                                      
            return StringUtils.equals(userId, disclosure.getPersonId()) && isDisclosureEditable(disclosure);

        }
        return false;
    }
    
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
    
    public void setIdentityService(IdentityService identityService) {
        this.identityService = identityService;
    }

    protected boolean isEditableByAdminReviewer(CoiDisclosure coiDisclosure) {
        return (coiDisclosure != null)
        && !coiDisclosure.getCoiDisclosureDocument().isViewOnly()
        && !isPessimisticLocked(coiDisclosure.getCoiDisclosureDocument());
    }


    protected boolean isAdministrator(String userId) {                                                                                                                       
        RoleService roleService = getRoleService();                                                                                            
        Collection<String> ids = roleService.getRoleMemberPrincipalIds(RoleConstants.COI_DISCLOSURE_ROLE_TYPE,RoleConstants.COI_ADMINISTRATOR, null);                         
        return ids.contains(userId);                                                                                                                                          
    }                                                                                                                                                                         

    protected boolean isReviewer(String userId, CoiDisclosure disclosure) {                                                                                                   
        List<CoiUserRole> userRoles = disclosure.getCoiUserRoles();                                                                                                           
        for (CoiUserRole userRole : userRoles) {                                                                                                                              
            if (StringUtils.equalsIgnoreCase(userRole.getRoleName(), RoleConstants.COI_REVIEWER)) {                                                                           
                if (StringUtils.equalsIgnoreCase(getPrincipalName(userId), userRole.getUserId())) {                                                                                             
                    return true;                                                                                                                                              
                }                                                                                                                                                             
            }                                                                                                                                                                 
        }                                                                                                                                                                     
        return false;                                                                                                                                                        
    }                
   
    protected String getPrincipalName(String userId) {
        Principal user = getIdentityService().getPrincipal(userId);
        return user.getPrincipalName();
    }
    
    public IdentityService getIdentityService() {
        return identityService;
    }

    public RoleService getRoleService() {
        return roleService;
    }

}
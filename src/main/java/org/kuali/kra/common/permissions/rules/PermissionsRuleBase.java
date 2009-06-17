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
package org.kuali.kra.common.permissions.rules;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.common.permissions.bo.PermissionsRoleState;
import org.kuali.kra.common.permissions.bo.PermissionsUser;
import org.kuali.kra.common.permissions.bo.PermissionsUserEditRoles;
import org.kuali.kra.common.permissions.rule.PermissionsRule;
import org.kuali.kra.common.permissions.web.bean.Role;
import org.kuali.kra.common.permissions.web.bean.User;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.kra.service.PersonService;
import org.kuali.rice.kns.document.Document;

/**
 * Business Rule to determine the legality of modifying the access to a Document.
 * Please see the Permissions tab web page.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class PermissionsRuleBase extends ResearchDocumentRuleBase implements PermissionsRule {
    
    private static final String USERNAME_FIELD_NAME = "userName";
    
    /**
     * @see org.kuali.kra.common.permissions.rule.PermissionsRule#processAddPermissionsUserBusinessRules(org.kuali.core.document.Document, java.util.List, org.kuali.kra.common.permissions.bo.PermissionsUser)
     */
    public boolean processAddPermissionsUserBusinessRules(Document document, List<User> users, PermissionsUser newUser) {
        boolean isValid = true;
       
        // The given username must be valid, i.e. it must correspond
        // to a person in the database.
            
        if (!isValidUser(newUser.getUserName())) {
            isValid = false;
            this.reportError(Constants.PERMISSION_USERS_PROPERTY_KEY + "." + USERNAME_FIELD_NAME, 
                             KeyConstants.ERROR_UNKNOWN_USERNAME);
        }
            
        // Don't add the same user to the list.  The "edit roles" button
        // must be used to modify roles for an existing user.
            
        else if (isDuplicate(newUser.getUserName(), users)) {
            isValid = false;
            this.reportError(Constants.PERMISSION_USERS_PROPERTY_KEY + "." + USERNAME_FIELD_NAME, 
                             KeyConstants.ERROR_DUPLICATE_PERMISSIONS_USER);
        }
        
        return isValid;
    }
    
    /**
     * @see org.kuali.kra.common.permissions.rule.PermissionsRule#processDeletePermissionsUserBusinessRules(org.kuali.core.document.Document, java.util.List, int)
     */
    public boolean processDeletePermissionsUserBusinessRules(Document document, List<User> users, int index) {
        boolean isValid = true;
        
        // The user cannot delete the last Administrator on a document.
            
        String adminRoleName = getAdministratorRoleName();
        if (adminRoleName != null) {
            User user = users.get(index);
            if (isLastAdministrator(user.getPerson().getUserName(), users, adminRoleName)) {
                isValid = false;
                this.reportError(Constants.PERMISSION_USERS_PROPERTY_KEY, 
                                 KeyConstants.ERROR_PERMISSIONS_LAST_ADMINSTRATOR,
                                 adminRoleName);
            }
        }
        
        return isValid;
    }
    
    /**
     * @see org.kuali.kra.common.permissions.rule.PermissionsRule#processEditPermissionsUserRolesBusinessRules(org.kuali.core.document.Document, java.util.List, org.kuali.kra.common.permissions.bo.PermissionsUserEditRoles)
     */
    public boolean processEditPermissionsUserRolesBusinessRules(Document document, List<User> users, PermissionsUserEditRoles editRoles) {
         boolean isValid = true;
        
        // The Administrator encompasses all of the other roles.  Therefore, if the
        // user selects the Administrator role, don't allow any of the other roles
        // to be selected.
         
        String adminRoleName = getAdministratorRoleName();
        if (adminRoleName != null) {
            if (hasAdministrator(editRoles, adminRoleName) && hasNonAdministrator(editRoles, adminRoleName)) {
                isValid = false;
                this.reportError(Constants.PERMISSIONS_EDIT_ROLES_PROPERTY_KEY, 
                                 KeyConstants.ERROR_PERMISSIONS_ADMINSTRATOR_INCLUSIVE,
                                 adminRoleName);
            }
                
            // The user cannot delete the last Administrator on a proposal.
                
            else if (!hasAdministrator(editRoles, adminRoleName) && isLastAdministrator(editRoles.getUserName(), users, adminRoleName)) {
                isValid = false;
                this.reportError(Constants.PERMISSIONS_EDIT_ROLES_PROPERTY_KEY,
                                 KeyConstants.ERROR_PERMISSIONS_LAST_ADMINSTRATOR,
                                 adminRoleName);
            }
        }
        
        return isValid;
    }
    
    /**
     * Get the name of the role for the Administrator.  The term "Administrator"
     * is generic.  It refers to the a role which has full access to a document.
     * For example, the "Protocol Aggregator" is the name of the administrative
     * role for a Protocol.  Since different documents will have different
     * administrative roles, the subclass must provide this value.
     * 
     * @return the name of the role for the Administrator
     */
    protected abstract String getAdministratorRoleName();
    
    /**
     * Has the Administrator role been selected?
     * @param editRoles the new roles for a user
     * @param adminRoleName the name of the administrative role
     * @return true if the Administrator is selected; otherwise false
     */
    private boolean hasAdministrator(PermissionsUserEditRoles editRoles, String adminRoleName) {
        List<PermissionsRoleState> roleStates = editRoles.getRoleStates();
        for (PermissionsRoleState roleState : roleStates) {
            if (roleState.getState()) {
                String roleName = roleState.getRole().getName();
                if (StringUtils.equals(roleName, adminRoleName)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Has any role other than Administrator been selected?
     * @param editRoles the new roles for the user
     * @param adminRoleName the name of the administrative role
     * @return true if a role other than Administrator has been selected; otherwise false
     */
    private boolean hasNonAdministrator(PermissionsUserEditRoles editRoles, String adminRoleName) {
        List<PermissionsRoleState> roleStates = editRoles.getRoleStates();
        for (PermissionsRoleState roleState : roleStates) {
            if (roleState.getState()) {
                String roleName = roleState.getRole().getName();
                if (!StringUtils.equals(roleName, adminRoleName)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Is this a valid userName?  The User must reside in the database.
     * @param userName the userName
     * @return true if valid; otherwise false
     */
    private boolean isValidUser(String userName) {
        PersonService personService = KraServiceLocator.getService(PersonService.class);
        return personService.getPersonByName(userName) != null;
    }

    /**
     * Is this a duplicate user?  In other words, has this user
     * already been added to the list of users who can access the
     * proposal?
     * @param userName the user's userName
     * @param users the current list of users with roles in the document
     * @return true if the user is already in the list; otherwise false
     */
    private boolean isDuplicate(String userName, List<User> users) {
        for (User user : users) {
            if (StringUtils.equals(userName, user.getPerson().getUserName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Is this user the last Administrator in the list?  There must always be at least
     * one user with the Administrator role.  Any attempt to delete that user or remove
     * the Administrator role from that user must be prevented.
     * @param userName the userName of the user who's Administrative role is to be removed
     * @param users the current list of users with roles in the document
     * @param adminRoleName the name of the administrative role
     * @return true if the user is the last Administrator; otherwise false
     */
    private boolean isLastAdministrator(String userName, List<User> users, String adminRoleName) {
        for (User user : users) {
            if (!StringUtils.equals(userName, user.getPerson().getUserName())) {
                List<Role> roles = user.getRoles();
                for (Role role : roles) {
                    if (StringUtils.equals(role.getName(), adminRoleName)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}

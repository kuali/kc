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
package org.kuali.coeus.common.permissions.impl.rules;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.permissions.impl.bo.PermissionsRoleState;
import org.kuali.coeus.common.permissions.impl.bo.PermissionsUser;
import org.kuali.coeus.common.permissions.impl.bo.PermissionsUserEditRoles;
import org.kuali.coeus.common.permissions.impl.rule.PermissionsRule;
import org.kuali.coeus.common.permissions.impl.web.bean.Role;
import org.kuali.coeus.common.permissions.impl.web.bean.User;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.document.Document;

import java.util.List;

/**
 * Business Rule to determine the legality of modifying the access to a Document.
 * Please see the Permissions tab web page.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class PermissionsRuleBase extends KcTransactionalDocumentRuleBase implements PermissionsRule {
    
    private static final String USERNAME_FIELD_NAME = "userName";
    private transient KcPersonService kcPersonService;
    
    @Override
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
    
    @Override
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
    
    @Override
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
        return StringUtils.isNotBlank(userName) && getKcPersonService().getKcPersonByUserName(userName) != null;
    }
    
    /**
     * Gets the KC Person Service.
     * @return KC Person Service.
     */
    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        
        return this.kcPersonService;
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

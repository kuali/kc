/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.common.permissions.web.struts.form;

import org.apache.commons.lang.StringUtils;
import org.kuali.coeus.sys.framework.auth.SystemAuthorizationService;
import org.kuali.coeus.sys.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.common.permissions.bo.PermissionsUser;
import org.kuali.kra.common.permissions.bo.PermissionsUserEditRoles;
import org.kuali.kra.common.permissions.web.bean.*;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.core.api.criteria.Predicate;
import org.kuali.rice.core.api.criteria.PredicateFactory;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kim.api.permission.PermissionQueryResults;
import org.kuali.rice.kim.api.permission.PermissionService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The PermissionsHelperBase is the base class of all PermissionsHelperBase classes.
 * Every document that requires a Permissions tab web page must have a form that
 * has a PermissionsHelperBase derived from PermissionsHelperBase.
 */
public abstract class PermissionsHelperBase implements Serializable {
    
    private transient KcPersonService kcPersonService; 
    
    /*
     * The form data for a new user.  See the Users panel
     * on the Permissions tab web page.
     */
    private PermissionsUser newUser;
    
    /*
     * All of the supported roles for the document.
     */
    private List<Role> roles = null;
    
    /*
     * The current set of users and their roles states.
     */
    private List<UserState> userStates = new ArrayList<UserState>();
    
    /*
     * The form data for editing the roles of a user.
     */
    private PermissionsUserEditRoles editRoles;
    
    /*
     * Is the end-user allowed to modify the users and roles
     * on the Permissions web page?
     */
    private boolean modifyPermissions = false;
   
    /**
     * Constructs a AbstractPermissionsHelper with a null role type.
     */
    public PermissionsHelperBase() {
        initialize(null);
    }
    
    /**
     * Constructs an AbstractPermissionsHelper.
     * @param roleType the type of role (may be null)
     */
    public PermissionsHelperBase(String roleType) {
       initialize(roleType);
    }    
    
    /*
     * Initialize the class.
     */
    private void initialize(String roleType) {
        clearNewUser();
        buildRoles(roleType);
    }
    
    /**
     * To prepare a view, the set of users and their roles must be
     * initialized only once.  Keep in mind that the Initiator must
     * be added as an Administrator (Aggregator) once the document is
     * initially saved.  Therefore, we keep building the set of users
     * until it has at least one user.
     */
    public void prepareView() {
       if (userStates.size() == 0) {
           buildUserStates();
       }
       initializePermissions();
    }
    
    /**
     * This method empties the userStates cache such that the data is updated.
     * Needed when adding or removing permissions through service calls.
     */
    public void resetUserStates() {
        userStates = new ArrayList<UserState>(); 
    }
    
    /**
     * Initialize the permissions for viewing/editing the Permissions web page.
     */
    private void initializePermissions() {
        modifyPermissions = canModifyPermissions();
    }
    
    /**
     * Can the current user modify the users and their roles?
     * @return true if can modify the users and roles; otherwise false
     */
    public abstract boolean canModifyPermissions();
    
    /**
     * Get the ModifyPermissions value.
     * @return the ModifyPermissions value
     */
    public boolean getModifyPermissions() {
        return modifyPermissions;
    }
    
    /**
     * Get the roles for the document.
     * @return the roles for the document
     */
    public List<Role> getRoles() {
        return roles;
    }
    
    /**
     * Set the roles, this is for classes that
     * have to override the buildRoles or otherwise
     * need to set the List.
     *
     */
    
    protected void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    
    
    /**
     * Get the form data for editing the roles for a user.
     * @return the EditRoles form data
     */
    public PermissionsUserEditRoles getEditRoles() {
        return editRoles;
    }
    
    /**
     * Get the "normal" roles for the document.  The excludes
     * the special "unassigned" role if it is available. Sometimes,
     * such as the Edit Roles web page, we don't want to show the
     * unassigned role.
     * @return the "normal" roles for the document
     */
    public List<Role> getNormalRoles() {
        List<Role> normalRoles = new ArrayList<Role>();
        for (Role role : roles) {
            if (!isUnassignedRoleName(role.getName())) {
                normalRoles.add(role);
            }
        }
        return normalRoles;
    }
    
    /**
     * Get all of the users and their current role states.
     * @return the users and their role states
     */
    public List<UserState> getUserStates() {
        return userStates;
    }
    
    /**
     * Get a UserState for a particular user.
     * @param userName the user's unique userName
     * @return the user's UserState or null if not found
     */
    public UserState getUserState(String userName) {
        for (UserState userState : userStates) {
            if (StringUtils.equals(userState.getPerson().getUserName(), userName)) {
                return userState;
            }
        }
        return null;
    }
    
    /**
     * Get the name of the unassigned role.  If the derived class needs
     * to support an unassigned role, it must override this method and
     * to provide the role name.
     * @return the name of the unassigned role or null if the unassigned role is not supported
     */
    public String getUnassignedRoleName() {
        return null;
    }
    
    /**
     * Get the display name of the unassigned role.
     * @return the display name of the unassigned role or null is the unassigned role is not supported
     */
    public String getUnassignedRoleDisplayName() {
        String unassignedRoleName = getUnassignedRoleName();
        if (unassignedRoleName != null) {
            return getRoleDisplayName(unassignedRoleName);
        }
        return null;
    }
    
    /*
     * Is this an unassigned role?
     */
    private boolean isUnassignedRoleName(String roleName) {
        return StringUtils.equals(getUnassignedRoleName(), roleName);
    }
    
    /**
     * Is this one of the standard roles?  Every document comes
     * with one or more standard roles (Aggregator, Budget Creator, Viewer, etc.).
     * End-users may also define extra roles to be used with the document.
     * When displaying the roles, we want to display the standard roles
     * before the user-defined roles.  In order to do this, the derived
     * class must define this method.
     * @param roleName the name of the role
     * @return true if a standard role; otherwise false
     */
    protected abstract boolean isStandardRoleName(String roleName);
    
    /**
     * Get the role's display name.  By default, the name of role is also
     * its display name.  A derived class may override this method in order
     * to change the display name.  For example, one may wish to have the
     * "ProtocolBase Aggregator" role simply displayed as "Aggregator".
     * @param roleName the name of the role
     * @return the role's display name
     */
    protected String getRoleDisplayName(String roleName) {
        return roleName;
    }
            
    /*
     * Build the set of users and their role states.  Each person (user) may
     * be assigned to more than one role.  Since we are reading the information
     * from the database, we can set the Current state to reflect what is saved
     * in the database.
     */
    private void buildUserStates() {  
        for (Role role : roles) {
            String roleName = role.getName();
            List<KcPerson> persons = getPersonsInRole(roleName);
            for (KcPerson person : persons) {
                UserState userState = getUserState(person);
                userState.setSaved(roleName, true);
            }
        }
    }
    
    /*
     * Get the UserState for a person.  If the userState is not found, create
     * a new one for that person.
     */
    private UserState getUserState(KcPerson person) {
        for (UserState userState : userStates) {
            if (StringUtils.equals(userState.getPerson().getUserName(), person.getUserName())) {
                return userState;
            }
        }
        return createUserState(person);
    }
    
    /*
     * Create a UserState for a person.  All of the roles states, current and new,
     * are initialized to false.
     */
    private UserState createUserState(KcPerson person) {
        UserState userState = new UserState(person, roles);
        userStates.add(userState);
        return userState;
    }
    
    /**
     * Get the persons in a role.  Must be overridden by the derived class.
     * @param roleName the name of the role
     * @return the list of persons
     */
    protected abstract List<KcPerson> getPersonsInRole(String roleName);
    
    /*
     * Build the list of roles for the document.
     */
    protected void buildRoles(String roleType) {
        roles = new ArrayList<Role>();
        List<org.kuali.rice.kim.api.role.Role> kimRoles = getSortedKimRoles(roleType);
        QueryByCriteria.Builder queryBuilder = QueryByCriteria.Builder.create();
        List<Predicate> predicates = new ArrayList<Predicate>();
        PermissionQueryResults permissionResults = null;
        
        for (org.kuali.rice.kim.api.role.Role kimRole : kimRoles) {
            predicates.add(PredicateFactory.equal("rolePermissions.roleId", kimRole.getId()));
            queryBuilder.setPredicates(PredicateFactory.and(predicates.toArray(new Predicate[] {})));
            permissionResults = getKimPermissionService().findPermissions(queryBuilder.build());
            if(permissionResults != null && permissionResults.getTotalRowCount() != null && permissionResults.getTotalRowCount() > 0) {
                Role role = new Role(kimRole.getName(), getRoleDisplayName(kimRole.getName()), permissionResults.getResults());
                roles.add(role);
            }
            predicates.clear();
            queryBuilder = QueryByCriteria.Builder.create();
            permissionResults = null;
        }
    }
    
    /**
     * Get the sorted list of KIM roles.  By sorted, we mean that any unassigned role
     * is shown first, followed by the standard roles, and then by the user-defined roles.
     * @return the sorted list of KIM roles
     */
    protected List<org.kuali.rice.kim.api.role.Role> getSortedKimRoles(String roleType) {
        
        List<org.kuali.rice.kim.api.role.Role> sortedKimRoles = new ArrayList<org.kuali.rice.kim.api.role.Role>();
        List<org.kuali.rice.kim.api.role.Role> kimRoles = getKimRoles(roleType);
        
        /*
         * Add in unassigned and standard roles first so that
         * they always show up first on the web pages.
         */
        for (org.kuali.rice.kim.api.role.Role kimRole : kimRoles) {
            if (isUnassignedRoleName(kimRole.getName())) {
                sortedKimRoles.add(0, kimRole);
            } else if (isStandardRoleName(kimRole.getName())) {
                sortedKimRoles.add(kimRole);
            }
        }
        
        /*
         * Now add in any user-defined roles.
         */
        for (org.kuali.rice.kim.api.role.Role kimRole : kimRoles) {
            if (!sortedKimRoles.contains(kimRole)) {
                sortedKimRoles.add(kimRole);
            }
        }
        return sortedKimRoles;
    }
    
    /**
     * Get the list of Kim Roles.  The default implementation gets all of the roles for
     * a particular type.  If roleType was set to null in the constructor, the derived
     * class must override this method to obtain the roles for the document.
     * @return the list of KIM roles for the document
     */
    protected List<org.kuali.rice.kim.api.role.Role> getKimRoles(String roleType) {
        SystemAuthorizationService systemAuthorizationService = KcServiceLocator.getService(SystemAuthorizationService.class);
        return systemAuthorizationService.getRoles(roleType);
    }
    
    /**
     * Get a specific role.
     * @param roleName the name of the role
     * @return the role or null if not found
     */
    public Role getRole(String roleName) {
        for (Role role : roles) {
            if (StringUtils.equals(roleName, role.getName())) {
                return role;
            }
        }
        return null;
    }
    
    /**
     * Get the list of assigned roles.  This is used by the Assigned Roles
     * panel on the Permissions tab web page.  Each role has a sorted list
     * of the userNames assigned to that role.  Note that the unassigned
     * role is excluded.
     * @return the list of assigned roles
     */
    public List<AssignedRole> getAssignedRoles() {
        List<AssignedRole> assignedRoles = new ArrayList<AssignedRole>(); 
        for (Role role : roles) {
            if (!isUnassignedRoleName(role.getName())) {
                AssignedRole assignedRole = new AssignedRole(role);
                for (UserState userState : userStates) {
                    if (userState.isRoleAssigned(role.getName())) {
                        assignedRole.add(new User(userState.getPerson()));
                    }
                }
                assignedRoles.add(assignedRole);
            }
        }
        return assignedRoles;
    }
    
    /**
     * Get the users.  This is the list of users as shown on the 
     * Users panel on the Permissions web page.  
     * @return the sorted list of users
     */
    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        for (UserState userState : userStates) { 
            User user = new User(userState.getPerson());
            for (RoleState roleState : userState.getRoleStates()) {
                if (roleState.isAssigned()) {
                    user.addRole(roleState.getRole());
                }
            }
            if (user.getRoles().size() > 0) {
                users.add(user);
            }
        }
        
        sortUsers(users);
        return users;
    }
    
    /*
     * Sort the list of users by their Last Name.
     */
    @SuppressWarnings("unchecked")
    private void sortUsers(List<User> users) {
        Collections.sort(users, new Comparator() {
            public int compare(Object o1, Object o2) {
                User user1 = (User) o1;
                User user2 = (User) o2;
                return user1.getPerson().getLastName().compareTo(user2.getPerson().getLastName());
            }
        });
    }
    
    /** 
     * Gets the new user.  This is the user that is filled
     * in by the end-user on the Users panel in the Permissions web page.
     * @return the new user
     */
    public PermissionsUser getNewUser() {
        return newUser;
    }

    /*
     * Clear the new user for another person to be entered.
     */
    private void clearNewUser() {
        newUser = new PermissionsUser();
    }
    
    /**
     * Add the new user to the list of UserStates.  Note that the
     * person will never be null since the business rules will 
     * verify the person's existence in the database.
     */
    public void addNewUser() {
        KcPerson person = findPerson(newUser.getUserName());
        UserState userState = new UserState(person, roles);
        userState.setAssigned(newUser.getRoleName(), true);
        userStates.add(userState);
        clearNewUser();
    }
    
    /**
     * Get list of roles to display in the drop-down menu in the Users
     * panel of the Permissions web page.
     * @return list of roles for the drop-down menu
     */
    public List<KeyValue> getRoleSelection() {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        for (Role role : roles) {
            KeyValue pair = new ConcreteKeyValue(role.getName(), role.getDisplayName());    
            keyValues.add(pair);
        }
        return keyValues;
    }
    
    /**
     * Get the UserEditRoles which is the form data for the Edit Roles web page.
     * @param editRoles the form data for the Edit Roles web page
     */
    public void setUserEditRoles(PermissionsUserEditRoles editRoles) {
        this.editRoles = editRoles;
    }

    /*
     * Find the person with a given userName.
     */
    private KcPerson findPerson(String userName) {
        return getKcPersonService().getKcPersonByUserName(userName);
    }
    
    protected TaskAuthorizationService getTaskAuthorizationService() {
        return KcServiceLocator.getService(TaskAuthorizationService.class);
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
    
    protected PermissionService getKimPermissionService() {
        return KcServiceLocator.getService("kimPermissionService");
    }

    /**
     * Get the userName of the user for the current session.
     * @return the current session's userName
     */
    protected String getUserIdentifier() {
         return GlobalVariables.getUserSession().getPrincipalId();
    }
}

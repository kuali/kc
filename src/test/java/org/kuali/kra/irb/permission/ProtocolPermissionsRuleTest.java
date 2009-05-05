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
package org.kuali.kra.irb.permission;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.Person;
import org.kuali.kra.common.permissions.bo.PermissionsRoleState;
import org.kuali.kra.common.permissions.bo.PermissionsUser;
import org.kuali.kra.common.permissions.bo.PermissionsUserEditRoles;
import org.kuali.kra.common.permissions.web.bean.Role;
import org.kuali.kra.common.permissions.web.bean.User;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.irb.permission.ProtocolPermissionsRule;
import org.kuali.kra.irb.rules.ProtocolRuleTestBase;

/**
 * Test the business rules for Protocol Permissions.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProtocolPermissionsRuleTest extends ProtocolRuleTestBase {

    private ProtocolPermissionsRule rule = null;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new ProtocolPermissionsRule();
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        super.tearDown();
    }

    /**
     * Test a valid addition of a user.
     * @throws Exception
     */
    @Test
    public void testAddOK() throws Exception {
        ProtocolDocument document = getNewProtocolDocument();
        List<User> users = getPermissionUsers();
        PermissionsUser permissionsUser = createPermissionsUser("tdurkin");
        assertTrue(rule.processAddPermissionsUserBusinessRules(document, users, permissionsUser));
    }
    
    /**
     * Test adding an unknown/invalid user.
     * @throws Exception
     */
    @Test
    public void testAddInvalidUser() throws Exception {
        ProtocolDocument document = getNewProtocolDocument();
        List<User> users = getPermissionUsers();
        PermissionsUser permissionsUser = createPermissionsUser("xxx");
        assertFalse(rule.processAddPermissionsUserBusinessRules(document, users, permissionsUser));
        assertError(Constants.PERMISSION_USERS_PROPERTY_KEY + ".userName", KeyConstants.ERROR_UNKNOWN_USERNAME);
    }
    
    /**
     * Test adding a duplicate user.
     * @throws Exception
     */
    @Test
    public void testAddDuplicate() throws Exception {
        ProtocolDocument document = getNewProtocolDocument();
        List<User> users = getPermissionUsers();
        PermissionsUser permissionsUser = createPermissionsUser("quickstart");
        assertFalse(rule.processAddPermissionsUserBusinessRules(document, users, permissionsUser));
        assertError(Constants.PERMISSION_USERS_PROPERTY_KEY + ".userName", KeyConstants.ERROR_DUPLICATE_PERMISSIONS_USER);
    }
    
    /**
     * Test a valid deletion.
     * @throws Exception
     */
    @Test
    public void testDeleteOK() throws Exception {
        ProtocolDocument document = getNewProtocolDocument();
        List<User> users = getPermissionUsers();
        assertTrue(rule.processDeletePermissionsUserBusinessRules(document, users, 1));
    }
    
    /**
     * Test a deletion of a user who is the last aggregator.  
     * @throws Exception
     */
    @Test
    public void testDeleteLastAggregator() throws Exception {
        ProtocolDocument document = getNewProtocolDocument();
        List<User> users = getPermissionUsers();
        assertFalse(rule.processDeletePermissionsUserBusinessRules(document, users, 0));
        assertError(Constants.PERMISSION_USERS_PROPERTY_KEY, KeyConstants.ERROR_PERMISSIONS_LAST_ADMINSTRATOR);
    }
    
    /**
     * Test a valid edit.
     * @throws Exception
     */
    @Test
    public void testEditOK() throws Exception {
        ProtocolDocument document = getNewProtocolDocument();
        List<User> users = getPermissionUsers();
        PermissionsUserEditRoles editRoles = createPermissionsUserEditRoles("aslusar");
        editRoles.setRoleState(RoleConstants.PROTOCOL_VIEWER, Boolean.TRUE);
        assertTrue(rule.processEditPermissionsUserRolesBusinessRules(document, users, editRoles));
    }

    /**
     * Test setting a user to have an Aggregator role with another role.
     *  
     * @throws Exception
     */
    @Test
    public void testEditAggregatorOnly() throws Exception {
        ProtocolDocument document = getNewProtocolDocument();
        List<User> users = getPermissionUsers();
        PermissionsUserEditRoles editRoles = createPermissionsUserEditRoles("aslusar");
        editRoles.setRoleState(RoleConstants.PROTOCOL_AGGREGATOR, Boolean.TRUE);
        editRoles.setRoleState(RoleConstants.PROTOCOL_VIEWER, Boolean.TRUE);
        assertFalse(rule.processEditPermissionsUserRolesBusinessRules(document, users, editRoles));
        assertError(Constants.PERMISSIONS_EDIT_ROLES_PROPERTY_KEY, KeyConstants.ERROR_PERMISSIONS_ADMINSTRATOR_INCLUSIVE);
    }
    
    /**
     * Create a Permissions Edit Roles.  The standard protocol roles will be added
     * and their states will be set to false.
     * @param username the username of the user
     * @return a Permissions Edit Roles
     */
    private PermissionsUserEditRoles createPermissionsUserEditRoles(String username) {
        PermissionsUserEditRoles editRoles = new PermissionsUserEditRoles();
        editRoles.setUserName(username);
        List<PermissionsRoleState> roleStates = new ArrayList<PermissionsRoleState>();
        Role role = new Role(RoleConstants.PROTOCOL_AGGREGATOR, "Aggregator");
        roleStates.add(new PermissionsRoleState(role));
        role = new Role(RoleConstants.PROTOCOL_VIEWER, "Viewer");
        roleStates.add(new PermissionsRoleState(role));
        editRoles.setRoleStates(roleStates);
        return editRoles;
    }

    /**
     * Try removing the Aggregator role from the last user to have that role.
     *  
     * @throws Exception
     */
    @Test
    public void testEditLastAggregator() throws Exception {
        ProtocolDocument document = getNewProtocolDocument();
        List<User> users = getPermissionUsers();
        PermissionsUserEditRoles editRoles = createPermissionsUserEditRoles("quickstart");
        editRoles.setRoleState(RoleConstants.PROTOCOL_VIEWER, Boolean.TRUE);
        assertFalse(rule.processEditPermissionsUserRolesBusinessRules(document, users, editRoles));
        assertError(Constants.PERMISSIONS_EDIT_ROLES_PROPERTY_KEY, KeyConstants.ERROR_PERMISSIONS_LAST_ADMINSTRATOR);
    }
    
    /**
     * Create a list of protocol users.
     * @return
     */
    private List<User> getPermissionUsers() {
        List<User> users = new ArrayList<User>();
        
        Person person = new Person();
        person.setUserName("quickstart");
        User user = new User(person);
        Role role = new Role(RoleConstants.PROTOCOL_AGGREGATOR, "Aggregator");
        user.addRole(role);
        users.add(user);
        
        person = new Person();
        person.setUserName("aslusar");
        user = new User(person);
        role = new Role(RoleConstants.PROTOCOL_VIEWER, "Viewer");
        user.addRole(role);
        users.add(user);
        
        return users;
    }
    
    /**
     * Create a protocol user to be added.
     * @param username
     * @return
     */
    private PermissionsUser createPermissionsUser(String userName) {
        PermissionsUser user = new PermissionsUser();
        user.setUserName(userName);
        user.setRoleName(RoleConstants.PROTOCOL_AGGREGATOR);
        return user;
    }
}

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
package org.kuali.kra.kim;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.kim.exception.UnknownNamespaceNameException;
import org.kuali.kra.kim.exception.UnknownPermissionNameException;
import org.kuali.kra.kim.exception.UnknownRoleNameException;
import org.kuali.kra.kim.pojo.Permission;
import org.kuali.kra.kim.pojo.Role;
import org.kuali.kra.kim.service.RoleService;

/**
 * Test the KIM Role Service.  We are only testing the methods
 * that are currently in use by KRA.
 */
public class RoleServiceTest extends KraTestBase {

    /**
     * Test the getRole() method.
     * 
     * @throws Exception
     */
    @Test
    public void testGetRole() throws Exception {

        String roleNames[] = { RoleConstants.AGGREGATOR, RoleConstants.BUDGET_CREATOR, RoleConstants.NARRATIVE_WRITER,
                RoleConstants.VIEWER, RoleConstants.UNASSIGNED };

        RoleService roleService = (RoleService) KraServiceLocator.getService("kimRoleService");

        /*
         * test some valid role names
         */
        for (String roleName : roleNames) {
            Role role = roleService.getRole(roleName);
            assertEquals(roleName, role.getName());
        }

        /*
         * test an invalid role name; we should get an exception
         */
        try {
            roleService.getRole("bogusRole");
            assertTrue("Did not throw UnknownRoleNameException", false);
        }
        catch (UnknownRoleNameException ex) {
            assertTrue(true);
        }
    }

    /**
     * Test the getPermissions() method.
     * 
     * @throws Exception
     */
    @Test
    public void testGetPermissions() throws Exception {

        String roleNames[] = { RoleConstants.AGGREGATOR, RoleConstants.BUDGET_CREATOR, 
                               RoleConstants.NARRATIVE_WRITER,
                               RoleConstants.VIEWER, RoleConstants.UNASSIGNED };

        RoleService roleService = (RoleService) KraServiceLocator.getService("kimRoleService");

        /*
         * test some valid role names
         */
        for (String roleName : roleNames) {
            List<Permission> permissions = roleService.getPermissions(roleName);
            assertEquals(permissions, getPermissionNames(roleName));
        }

        /*
         * test an invalid role name; we should get an exception
         */
        try {
            roleService.getPermissions("bogusRole");
            assertTrue("Did not throw UnknownRoleNameException", false);
        }
        catch (UnknownRoleNameException ex) {
            assertTrue(true);
        }
    }

    /**
     * Test the hasPermission() method.
     * 
     * @throws Exception
     */
    @Test
    public void testHasPermissions() throws Exception {
        
        String roleNames[] = { RoleConstants.AGGREGATOR, RoleConstants.BUDGET_CREATOR, 
                RoleConstants.NARRATIVE_WRITER,
                RoleConstants.VIEWER, RoleConstants.UNASSIGNED };

        RoleService roleService = (RoleService) KraServiceLocator.getService("kimRoleService");

        /*
         * test some valid role names
         */
        for (String roleName : roleNames) {
            /*
             * Test all of the permissions that a role should have
             */
            List<String> permissionNames = getPermissionNames(roleName);
            for (String permissionName : permissionNames) {
                assertTrue(roleService.hasPermission(roleName, Constants.KRA_NAMESPACE, permissionName));
            }
            
            /*
             * test a permission that we know the role doesn't have
             */
            assertTrue(!roleService.hasPermission(roleName, Constants.KRA_NAMESPACE, PermissionConstants.CREATE_PROPOSAL));
        }

        /*
         * test an invalid role name; we should get an exception
         */
        try {
            roleService.hasPermission("bogusRole", Constants.KRA_NAMESPACE, PermissionConstants.VIEW_PROPOSAL);
            assertTrue("Did not throw UnknownRoleNameException", false);
        }
        catch (UnknownRoleNameException ex) {
            assertTrue(true);
        }
        
        /*
         * test an invalid namespace; we should get an exception
         */
        try {
            roleService.hasPermission(RoleConstants.AGGREGATOR, "bogusNamespace", PermissionConstants.VIEW_PROPOSAL);
            assertTrue("Did not throw UnknownNamespaceNameException", false);
        }
        catch (UnknownNamespaceNameException ex) {
            assertTrue(true);
        }
        
        /*
         * test an invalid permission name; we should get an exception
         */
        try {
            roleService.hasPermission(RoleConstants.AGGREGATOR, Constants.KRA_NAMESPACE, "bogusPermission");
            assertTrue("Did not throw UnknownPermissionNameException", false);
        }
        catch (UnknownPermissionNameException ex) {
            assertTrue(true);
        }
    }
    
    /**
     * Get the name of the permissions for the given role.
     * @param roleName the name of the role
     * @return the list of permission names
     */
    private List<String> getPermissionNames(String roleName) {
        List<String> permissions = new ArrayList<String>();
        if (StringUtils.equals(RoleConstants.AGGREGATOR, roleName)) {
            permissions.add(PermissionConstants.VIEW_PROPOSAL);
            permissions.add(PermissionConstants.VIEW_BUDGET);
            permissions.add(PermissionConstants.VIEW_NARRATIVE);
            permissions.add(PermissionConstants.MODIFY_PROPOSAL);
            permissions.add(PermissionConstants.MODIFY_BUDGET);
            permissions.add(PermissionConstants.MODIFY_NARRATIVE);
            permissions.add(PermissionConstants.MAINTAIN_PROPOSAL_ACCESS);
            permissions.add(PermissionConstants.CERTIFY);
            permissions.add(PermissionConstants.PRINT_PROPOSAL);
            permissions.add(PermissionConstants.SUBMIT_PROPOSAL);
            permissions.add(PermissionConstants.ADD_PROPOSAL_VIEWER);
        } else if (StringUtils.equals(RoleConstants.BUDGET_CREATOR, roleName)) {
            permissions.add(PermissionConstants.VIEW_PROPOSAL);
            permissions.add(PermissionConstants.VIEW_BUDGET);
            permissions.add(PermissionConstants.VIEW_NARRATIVE);
            permissions.add(PermissionConstants.MODIFY_PROPOSAL);
            permissions.add(PermissionConstants.MODIFY_BUDGET);
            permissions.add(PermissionConstants.PRINT_PROPOSAL);
        } else if (StringUtils.equals(RoleConstants.NARRATIVE_WRITER, roleName)) {
            permissions.add(PermissionConstants.VIEW_PROPOSAL);
            permissions.add(PermissionConstants.VIEW_BUDGET);
            permissions.add(PermissionConstants.VIEW_NARRATIVE);
            permissions.add(PermissionConstants.MODIFY_PROPOSAL);
            permissions.add(PermissionConstants.MODIFY_NARRATIVE);
            permissions.add(PermissionConstants.PRINT_PROPOSAL);
        } else if (StringUtils.equals(RoleConstants.VIEWER, roleName)) {
            permissions.add(PermissionConstants.VIEW_PROPOSAL);
            permissions.add(PermissionConstants.VIEW_BUDGET);
            permissions.add(PermissionConstants.VIEW_NARRATIVE);
            permissions.add(PermissionConstants.PRINT_PROPOSAL);
        }
        return permissions;
    }
    
    /**
     * Assert the list of permissions is the same as the list of permission names.
     * @param permissions the list of permissions
     * @param permissionNames the list of permission names
     */
    private void assertEquals(List<Permission> permissions, List<String> permissionNames) {
        assertEquals(permissionNames.size(), permissions.size());
        for (String permissionName : permissionNames) {
            assertTrue(permissionName + " not found", contains(permissions, permissionName));
        }
    }
    
    /**
     * Does the list of permissions contain the given permission name?
     * @param permissions list of permissions
     * @param permissionName the name of the permission
     * @return true if found; otherwise false
     */
    private boolean contains(List<Permission> permissions, String permissionName) {
        for (Permission permission : permissions) {
            if (StringUtils.equals(permission.getName(), permissionName)) {
                return true;
            }
        }
        return false;
    }
}

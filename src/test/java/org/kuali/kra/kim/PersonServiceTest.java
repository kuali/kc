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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.kuali.kra.kim.exception.UnknownUsernameException;
import org.kuali.kra.kim.pojo.QualifiedRole;
import org.kuali.kra.kim.service.PersonService;

/**
 * Test the KIM Person Service.  We are only testing the methods
 * that are currently in use by KRA.
 */
public class PersonServiceTest extends KraTestBase {
    
    private static final String QUICKSTART = "quickstart";
    private static final String JTESTER = "jtester";
    private static final String TDURKIN = "tdurkin";
    private static final String ASLUSAR = "aslusar";
    
    /**
     * Test the hasPermission() method.
     * @throws Exception
     */
    @Test
    public void testHasPermission() throws Exception {
        PersonService personService = (PersonService) KraServiceLocator.getService("kimPersonService");
       
        /*
         * At the moment, KIM is read-only with respect to role assignments
         * in the global space.  Therefore, we can't add any and thus there are
         * none.  So, we should always get back false.
         */
        assertTrue(!personService.hasPermission(QUICKSTART, Constants.KRA_NAMESPACE, PermissionConstants.CREATE_PROPOSAL));
    }

    /**
     * Test the hasQualifiedPermission() method.
     * @throws Exception
     */
    @Test
    public void testHasQualifiedPermission() throws Exception {
        
        PersonService personService = (PersonService) KraServiceLocator.getService("kimPersonService");
        
        /*
         * add some test data to KIM
         */
        addQualifiedRoles();
        
        /*
         * test a simple case; since both permissions are in the Aggregator
         * role, both should return true
         */
        Map<String, String> qualifiedAttributes = new HashMap<String, String>();
        qualifiedAttributes.put("age", "46");
        qualifiedAttributes.put("sex", "M");
        assertTrue(personService.hasQualifiedPermission(QUICKSTART, Constants.KRA_NAMESPACE, PermissionConstants.MODIFY_PROPOSAL, qualifiedAttributes));
        assertTrue(personService.hasQualifiedPermission(QUICKSTART, Constants.KRA_NAMESPACE, PermissionConstants.MODIFY_BUDGET, qualifiedAttributes));
        
        /*
         * test a user who doesn't match the above qualified attributes; expect false
         */
        assertTrue(!personService.hasQualifiedPermission(JTESTER, Constants.KRA_NAMESPACE, PermissionConstants.MODIFY_PROPOSAL, qualifiedAttributes));
        
        /*
         * test a case where two users use the same qualified attributes
         */
        qualifiedAttributes = new HashMap<String, String>();
        qualifiedAttributes.put("age", "45");
        qualifiedAttributes.put("sex", "F");
        assertTrue(personService.hasQualifiedPermission(TDURKIN, Constants.KRA_NAMESPACE, PermissionConstants.MODIFY_PROPOSAL, qualifiedAttributes));
        assertTrue(personService.hasQualifiedPermission(ASLUSAR, Constants.KRA_NAMESPACE, PermissionConstants.MODIFY_PROPOSAL, qualifiedAttributes));
        
        /*
         * test the partial match case; verify that both males are matched
         * against and the female is not
         */
        qualifiedAttributes = new HashMap<String, String>();
        qualifiedAttributes.put("sex", "M");
        assertTrue(personService.hasQualifiedPermission(QUICKSTART, Constants.KRA_NAMESPACE, PermissionConstants.MODIFY_PROPOSAL, qualifiedAttributes));
        assertTrue(personService.hasQualifiedPermission(JTESTER, Constants.KRA_NAMESPACE, PermissionConstants.MODIFY_PROPOSAL, qualifiedAttributes));
        assertTrue(!personService.hasQualifiedPermission(TDURKIN, Constants.KRA_NAMESPACE, PermissionConstants.MODIFY_PROPOSAL, qualifiedAttributes));
        
        /*
         * test the null attribute list; this is a partial match that
         * will match against everyone.
         */
        assertTrue(personService.hasQualifiedPermission(QUICKSTART, Constants.KRA_NAMESPACE, PermissionConstants.MODIFY_PROPOSAL, null));
        assertTrue(personService.hasQualifiedPermission(JTESTER, Constants.KRA_NAMESPACE, PermissionConstants.MODIFY_PROPOSAL, null));
        assertTrue(personService.hasQualifiedPermission(TDURKIN, Constants.KRA_NAMESPACE, PermissionConstants.MODIFY_PROPOSAL, null));
        assertTrue(personService.hasQualifiedPermission(ASLUSAR, Constants.KRA_NAMESPACE, PermissionConstants.MODIFY_PROPOSAL, null));
        
        /*
         * test an invalid user name; we should get an exception
         */
        try {
            personService.hasQualifiedPermission("bogusName",Constants.KRA_NAMESPACE, PermissionConstants.MODIFY_PROPOSAL, qualifiedAttributes);
            assertTrue("Did not throw UnknownUsernameException", false);
        }
        catch (UnknownUsernameException ex) {
            assertTrue(true);
        }
        
        /*
         * test an invalid namespace name; we should get an exception
         */
        try {
            personService.hasQualifiedPermission(QUICKSTART, "bogusNamespace", PermissionConstants.MODIFY_PROPOSAL, qualifiedAttributes);
            assertTrue("Did not throw UnknownNamespaceNameException", false);
        }
        catch (UnknownNamespaceNameException ex) {
            assertTrue(true);
        }
        
        /*
         * test an invalid permission name; we should get an exception
         */
        try {
            personService.hasQualifiedPermission(QUICKSTART, Constants.KRA_NAMESPACE, "bogusPermission", qualifiedAttributes);
            assertTrue("Did not throw UnknownPermissionNameException", false);
        }
        catch (UnknownPermissionNameException ex) {
            assertTrue(true);
        }
    }

    /**
     * Test the getQualifiedRoles() method.
     * @throws Exception
     */
    @Test
    public void testGetQualifiedRoles() throws Exception {
        PersonService personService = (PersonService) KraServiceLocator.getService("kimPersonService");
       
        /*
         * add some test data to KIM
         */
        addQualifiedRoles();
        
        int cnt = 0;
        List<QualifiedRole> qualifiedRoles = personService.getQualifiedRoles(QUICKSTART);
        for (QualifiedRole qualifiedRole : qualifiedRoles) {
            Map<String, String> qualifiedAttrs = qualifiedRole.getQualifiedRoleAttributes();
            if (StringUtils.equals(RoleConstants.AGGREGATOR, qualifiedRole.getRoleName()) &&
                qualifiedAttrs.size() == 2) {
                cnt++;
                assertTrue(StringUtils.equals(qualifiedAttrs.get("age"), "46"));
                assertTrue(StringUtils.equals(qualifiedAttrs.get("sex"), "M"));
            } else if (StringUtils.equals(RoleConstants.VIEWER, qualifiedRole.getRoleName())) {
                if (qualifiedAttrs.get("sport") != null) {
                    cnt++;
                    assertTrue(StringUtils.equals(qualifiedAttrs.get("sport"), "football"));
                }
            }
        }
        assertEquals(2, cnt);
        
        cnt = 0;
        qualifiedRoles = personService.getQualifiedRoles(JTESTER);
        for (QualifiedRole qualifiedRole : qualifiedRoles) {
            Map<String, String> qualifiedAttrs = qualifiedRole.getQualifiedRoleAttributes();
            if (StringUtils.equals(RoleConstants.AGGREGATOR, qualifiedRole.getRoleName()) &&
                    qualifiedAttrs.size() == 2) {
                cnt++;
                assertTrue(StringUtils.equals(qualifiedAttrs.get("age"), "10"));
                assertTrue(StringUtils.equals(qualifiedAttrs.get("sex"), "M"));
            }
        }
        assertEquals(1, cnt);
        
        /*
         * test an invalid user name; we should get an exception
         */
        try {
            personService.getQualifiedRoles("bogusName");
            assertTrue("Did not throw UnknownUsernameException", false);
        }
        catch (UnknownUsernameException ex) {
            assertTrue(true);
        }
    }
    
    /**
     * Test the addQualifiedRole() method.
     * @throws Exception
     */
    @Test
    public void testAddQualifiedRole() throws Exception {
        PersonService personService = (PersonService) KraServiceLocator.getService("kimPersonService");
        
        Map<String, String> qualifiedAttributes = new HashMap<String, String>();
        qualifiedAttributes.put("age", "46");
        qualifiedAttributes.put("sex", "M");
        assertTrue(!personService.hasQualifiedRole(QUICKSTART, RoleConstants.AGGREGATOR, qualifiedAttributes));
       
        personService.addQualifiedRole(QUICKSTART, RoleConstants.AGGREGATOR, qualifiedAttributes);
        
        assertTrue(personService.hasQualifiedRole(QUICKSTART, RoleConstants.AGGREGATOR, qualifiedAttributes));
    
        /*
         * test an invalid user name; we should get an exception
         */
        try {
            personService.addQualifiedRole("bogusName", RoleConstants.AGGREGATOR, qualifiedAttributes);
            assertTrue("Did not throw UnknownUsernameException", false);
        }
        catch (UnknownUsernameException ex) {
            assertTrue(true);
        }
        
        /*
         * test an invalid role name; we should get an exception
         */
        try {
            personService.addQualifiedRole(QUICKSTART, "bogusRole", qualifiedAttributes);
            assertTrue("Did not throw UnknownRoleNameException", false);
        }
        catch (UnknownRoleNameException ex) {
            assertTrue(true);
        }
    }
    
    /**
     * Test the removeQualifiedRole() method.
     * @throws Exception
     */
    @Test
    public void testRemoveQualifiedRole() throws Exception {
        PersonService personService = (PersonService) KraServiceLocator.getService("kimPersonService");
        
        /*
         * Add a qualified role and verify that it is there.
         */
        Map<String, String> qualifiedAttributes = new HashMap<String, String>();
        qualifiedAttributes.put("age", "46");
        qualifiedAttributes.put("sex", "M");
        personService.addQualifiedRole(QUICKSTART, RoleConstants.AGGREGATOR, qualifiedAttributes);
        assertTrue(personService.hasQualifiedRole(QUICKSTART, RoleConstants.AGGREGATOR, qualifiedAttributes));
    
        /*
         * Now try a bogus delete.  Since the qualified attributes are slightly
         * different, the role should not be removed.
         */
        qualifiedAttributes = new HashMap<String, String>();
        qualifiedAttributes.put("age", "46");
        qualifiedAttributes.put("sex", "F");
        personService.removeQualifiedRole(QUICKSTART, RoleConstants.AGGREGATOR, qualifiedAttributes);
        
        qualifiedAttributes = new HashMap<String, String>();
        qualifiedAttributes.put("age", "46");
        qualifiedAttributes.put("sex", "M");
        assertTrue(personService.hasQualifiedRole(QUICKSTART, RoleConstants.AGGREGATOR, qualifiedAttributes));
        
        /*
         * Now let's do a real deletion.
         */
        qualifiedAttributes = new HashMap<String, String>();
        qualifiedAttributes.put("age", "46");
        qualifiedAttributes.put("sex", "M");
        personService.removeQualifiedRole(QUICKSTART, RoleConstants.AGGREGATOR, qualifiedAttributes);
        
        qualifiedAttributes = new HashMap<String, String>();
        qualifiedAttributes.put("age", "46");
        qualifiedAttributes.put("sex", "M");
        assertTrue(!personService.hasQualifiedRole(QUICKSTART, RoleConstants.AGGREGATOR, qualifiedAttributes));
        
        /*
         * test an invalid user name; we should get an exception
         */
        try {
            personService.removeQualifiedRole("bogusName", RoleConstants.AGGREGATOR, qualifiedAttributes);
            assertTrue("Did not throw UnknownUsernameException", false);
        }
        catch (UnknownUsernameException ex) {
            assertTrue(true);
        }
        
        /*
         * test an invalid role name; we should get an exception
         */
        try {
            personService.removeQualifiedRole(QUICKSTART, "bogusRole", qualifiedAttributes);
            assertTrue("Did not throw UnknownRoleNameException", false);
        }
        catch (UnknownRoleNameException ex) {
            assertTrue(true);
        }
    }
    
    /**
     * Test the hasQualifiedRole() method.
     * @throws Exception
     */
    @Test
    public void testHasQualifiedRole() throws Exception {
        PersonService personService = (PersonService) KraServiceLocator.getService("kimPersonService");
        
        Map<String, String> qualifiedAttributes = new HashMap<String, String>();
        qualifiedAttributes.put("age", "46");
        qualifiedAttributes.put("sex", "M");
        
        /* 
         * Verify that the qualified role does not exist.
         */
        assertTrue(!personService.hasQualifiedRole(QUICKSTART, RoleConstants.AGGREGATOR, qualifiedAttributes));
        
        /*
         * Add the qualified role and verify that it is now there.
         */
        personService.addQualifiedRole(QUICKSTART, RoleConstants.AGGREGATOR, qualifiedAttributes);
        assertTrue(personService.hasQualifiedRole(QUICKSTART, RoleConstants.AGGREGATOR, qualifiedAttributes));
    
        /*
         * test an invalid user name; we should get an exception
         */
        try {
            personService.hasQualifiedRole("bogusName", RoleConstants.AGGREGATOR, qualifiedAttributes);
            assertTrue("Did not throw UnknownUsernameException", false);
        }
        catch (UnknownUsernameException ex) {
            assertTrue(true);
        }
        
        /*
         * test an invalid role name; we should get an exception
         */
        try {
            personService.hasQualifiedRole(QUICKSTART, "bogusRole", qualifiedAttributes);
            assertTrue("Did not throw UnknownRoleNameException", false);
        }
        catch (UnknownRoleNameException ex) {
            assertTrue(true);
        }
    }
    
    /**
     * Add some test data to KIM. 
     */
    private void addQualifiedRoles() {
        PersonService personService = (PersonService) KraServiceLocator.getService("kimPersonService");
        
        Map<String, String> qualifiedAttributes = new HashMap<String, String>();
        qualifiedAttributes.put("age", "46");
        qualifiedAttributes.put("sex", "M");
        personService.addQualifiedRole(QUICKSTART, RoleConstants.AGGREGATOR, qualifiedAttributes);
        
        qualifiedAttributes = new HashMap<String, String>();
        qualifiedAttributes.put("sport", "football");
        personService.addQualifiedRole(QUICKSTART, RoleConstants.VIEWER, qualifiedAttributes);
        
        qualifiedAttributes = new HashMap<String, String>();
        qualifiedAttributes.put("age", "10");
        qualifiedAttributes.put("sex", "M");
        personService.addQualifiedRole(JTESTER, RoleConstants.AGGREGATOR, qualifiedAttributes);
        
        qualifiedAttributes = new HashMap<String, String>();
        qualifiedAttributes.put("age", "45");
        qualifiedAttributes.put("sex", "F");
        personService.addQualifiedRole(TDURKIN, RoleConstants.AGGREGATOR, qualifiedAttributes);
        
        qualifiedAttributes = new HashMap<String, String>();
        qualifiedAttributes.put("age", "45");
        qualifiedAttributes.put("sex", "F");
        personService.addQualifiedRole(ASLUSAR, RoleConstants.AGGREGATOR, qualifiedAttributes);
    }
}

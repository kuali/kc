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

import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.kim.exception.UnknownRoleNameException;
import org.kuali.kra.kim.service.PersonService;
import org.kuali.kra.kim.service.QualifiedRoleService;

/**
 * Test the KIM Role Service.  We are only testing the methods
 * that are currently in use by KRA.
 */
public class QualifiedRoleServiceTest extends KraTestBase {
    
    private static final String QUICKSTART = "quickstart";
    private static final String JTESTER = "jtester";
    private static final String TDURKIN = "tdurkin";
    private static final String ASLUSAR = "aslusar";

    /**
     * Test the getPersonUsernames() method.
     * 
     * @throws Exception
     */
    @Test
    public void testGetPersonUsernames() throws Exception {

        QualifiedRoleService qualifiedRoleService = (QualifiedRoleService) KraServiceLocator.getService("kimQualifiedRoleService");

        /*
         * add some test data to KIM
         */
        addQualifiedRoles();
        
        /*
         * test a case where only one user is returned
         */
        Map<String, String> qualifiedAttributes = new HashMap<String, String>();
        qualifiedAttributes.put("age", "46");
        //qualifiedAttributes.put("sex", "M");
        List<String> usernames = qualifiedRoleService.getPersonUsernames(RoleConstants.AGGREGATOR, qualifiedAttributes);
        assertEquals(2, usernames.size());
        assertTrue(usernames.contains(QUICKSTART));
        
        /*
         * test a case where two users are returned
         */
        qualifiedAttributes = new HashMap<String, String>();
        qualifiedAttributes.put("age", "45");
        //qualifiedAttributes.put("sex", "F");
        usernames = qualifiedRoleService.getPersonUsernames(RoleConstants.AGGREGATOR, qualifiedAttributes);
        assertEquals(2, usernames.size());
        assertTrue(usernames.contains(TDURKIN));
        assertTrue(usernames.contains(ASLUSAR));
        
        /*
         * test a case where no users are found
         */
        qualifiedAttributes = new HashMap<String, String>();
        qualifiedAttributes.put("age", "10");
        //qualifiedAttributes.put("sex", "M");
        usernames = qualifiedRoleService.getPersonUsernames(RoleConstants.AGGREGATOR, qualifiedAttributes);
        assertEquals(0, usernames.size());
        
        /*
         * test the partial match case; should return nothing
         */
        //qualifiedAttributes = new HashMap<String, String>();
        //qualifiedAttributes.put("sex", "M");
        //usernames = qualifiedRoleService.getPersonUsernames(RoleConstants.AGGREGATOR, qualifiedAttributes);
        //assertEquals(0, usernames.size());
        
        /*
         * test an invalid role name; we should get an exception
         */
        try {
            qualifiedRoleService.getPersonUsernames("bogusRole", null);
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
        //qualifiedAttributes.put("sex", "M");
        personService.addQualifiedRole(QUICKSTART, RoleConstants.AGGREGATOR, qualifiedAttributes);
        
        qualifiedAttributes = new HashMap<String, String>();
        qualifiedAttributes.put("age", "46");
        //qualifiedAttributes.put("sex", "F");
        personService.addQualifiedRole(JTESTER, RoleConstants.AGGREGATOR, qualifiedAttributes);
        
        qualifiedAttributes = new HashMap<String, String>();
        qualifiedAttributes.put("age", "45");
        //qualifiedAttributes.put("sex", "F");
        personService.addQualifiedRole(TDURKIN, RoleConstants.AGGREGATOR, qualifiedAttributes);
        
        qualifiedAttributes = new HashMap<String, String>();
        qualifiedAttributes.put("age", "45");
        //qualifiedAttributes.put("sex", "F");
        personService.addQualifiedRole(ASLUSAR, RoleConstants.AGGREGATOR, qualifiedAttributes);
    }
}

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
package org.kuali.kra.service.impl;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.common.notification.bo.NotificationModuleRole;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.NotificationModuleRoleService;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;


public class NotificationModuleRoleServiceImplTest  extends KcUnitTestBase {

    private NotificationModuleRoleService notificationModuleRoleService = null;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        notificationModuleRoleService = KraServiceLocator.getService(NotificationModuleRoleService.class);
    }
    
    @After
    public void tearDown() throws Exception {
        notificationModuleRoleService = null;
        super.tearDown();
    }    
    
    @Test
    public void testGetModuleRolesByModuleName() throws Exception {
        List<NotificationModuleRole> moduleRoles = 
            notificationModuleRoleService.getModuleRolesByModuleName(CoeusModule.IRB_MODULE_CODE);
        
        assertNotNull(moduleRoles);
        assertTrue(moduleRoles.size() > 0);
    }
    
    @Test
    public void testGetModuleRoleForAjaxCall() throws Exception {
        String result = notificationModuleRoleService.getModuleRolesForAjaxCall(CoeusModule.IRB_MODULE_CODE);
        
        assertNotNull(result);
        
    }
}

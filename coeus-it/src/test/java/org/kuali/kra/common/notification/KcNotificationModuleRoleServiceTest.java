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
package org.kuali.kra.common.notification;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.common.notification.impl.bo.NotificationModuleRole;
import org.kuali.coeus.common.notification.impl.service.KcNotificationModuleRoleService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
public class KcNotificationModuleRoleServiceTest extends KcIntegrationTestBase {

    protected KcNotificationModuleRoleService kcNotificationModuleRoleService;
    
    @Before
    public void setUp() throws Exception {
        kcNotificationModuleRoleService = KcServiceLocator.getService(KcNotificationModuleRoleService.class);
    }

    @After
    public void tearDown() throws Exception {
        kcNotificationModuleRoleService = null;
    }
    
    @Test
    public void testGetNotifications() {
        List<NotificationModuleRole> moduleRoles =
            kcNotificationModuleRoleService.getNotificationModuleRoles(CoeusModule.IRB_MODULE_CODE);
        
        assertTrue(moduleRoles.size() > 0);
    }
    
    @Test
    public void testGetModuleRoleForAjaxCall() throws Exception {
        String result = kcNotificationModuleRoleService.getNotificationModuleRolesString(CoeusModule.IRB_MODULE_CODE);
        
        assertNotNull(result);
    }
    
}
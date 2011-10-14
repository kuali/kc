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
package org.kuali.kra.common.notification;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.common.notification.bo.NotificationModuleRole;
import org.kuali.kra.common.notification.service.KcNotificationModuleRoleService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;

public class KcNotificationModuleRoleServiceTest extends KcUnitTestBase {

    protected KcNotificationModuleRoleService notificationModuleRoleService;
    
    @Before
    public void setUp() throws Exception {
        notificationModuleRoleService = KraServiceLocator.getService(KcNotificationModuleRoleService.class);
    }

    @After
    public void tearDown() throws Exception {
        notificationModuleRoleService = null;
    }    
    
    @Test
    public void testAddNotificationModuleRole() {
        NotificationModuleRole moduleRole = 
            notificationModuleRoleService.addNotificationModuleRole(CoeusModule.IRB_MODULE_CODE, 
                    Constants.MODULE_NAMESPACE_PROTOCOL + ":" + RoleConstants.PROTOCOL_VIEWER); 

        assertTrue(moduleRole.getNotificationModuleRoleId().intValue() > 0);
    }
    
    @Test
    public void testGetNotifications() {
        List<NotificationModuleRole> moduleRoles =
            notificationModuleRoleService.getNotificationModuleRoles(CoeusModule.IRB_MODULE_CODE);
        
        assertTrue(moduleRoles.size() > 0);
    }
}

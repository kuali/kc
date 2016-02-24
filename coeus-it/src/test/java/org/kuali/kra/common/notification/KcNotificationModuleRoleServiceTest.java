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

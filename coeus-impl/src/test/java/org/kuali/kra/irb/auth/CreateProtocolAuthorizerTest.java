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
package org.kuali.kra.irb.auth;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.coeus.common.framework.auth.UnitAuthorizationService;
import org.kuali.coeus.common.framework.auth.task.ApplicationTask;
import org.kuali.kra.irb.authorizer.CreateProtocolAuthorizer;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.TaskName;

import static org.junit.Assert.assertEquals;

/**
 * Test the Create Protocol Authorizer.
 */
@RunWith(JMock.class)
public class CreateProtocolAuthorizerTest {

    private static final String USERNAME = "quickstart";
    
    private Mockery context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
    
    @Test
    public void testHasPermission() {
        CreateProtocolAuthorizer authorizer = new CreateProtocolAuthorizer();
        
        final UnitAuthorizationService unitAuthorizationService = context.mock(UnitAuthorizationService.class);
        context.checking(new Expectations() {{
            one(unitAuthorizationService).hasPermission(USERNAME, Constants.MODULE_NAMESPACE_PROTOCOL, PermissionConstants.CREATE_PROTOCOL); will(returnValue(true));
        }});
        authorizer.setUnitAuthorizationService(unitAuthorizationService);
        
        ApplicationTask task = new ApplicationTask(TaskName.CREATE_PROTOCOL);
        assertEquals(true, authorizer.isAuthorized(USERNAME, task));
    }
    
    @Test
    public void testDoesNotHavePermission() {
        CreateProtocolAuthorizer authorizer = new CreateProtocolAuthorizer();
        
        final UnitAuthorizationService unitAuthorizationService = context.mock(UnitAuthorizationService.class);
        context.checking(new Expectations() {{
            one(unitAuthorizationService).hasPermission(USERNAME, Constants.MODULE_NAMESPACE_PROTOCOL, PermissionConstants.CREATE_PROTOCOL); will(returnValue(false));
        }});
        authorizer.setUnitAuthorizationService(unitAuthorizationService);
        
        ApplicationTask task = new ApplicationTask(TaskName.CREATE_PROTOCOL);
        assertEquals(false, authorizer.isAuthorized(USERNAME, task));
    }
}

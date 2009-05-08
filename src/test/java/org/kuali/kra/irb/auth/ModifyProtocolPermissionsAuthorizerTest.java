/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.auth;

import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.auth.ModifyProtocolPermissionsAuthorizer;
import org.kuali.kra.irb.auth.ProtocolAuthorizationService;
import org.kuali.kra.irb.auth.ProtocolTask;

/**
 * Test the Modify Protocol Permissions Authorizer.
 */
@RunWith(JMock.class)
public class ModifyProtocolPermissionsAuthorizerTest {

    private static final String USERNAME = "quickstart";
    
    private Mockery context = new JUnit4Mockery();
    
    @Test
    public void testModifyPermission() {
        ModifyProtocolPermissionsAuthorizer authorizer = new ModifyProtocolPermissionsAuthorizer();
        
        final Protocol protocol = new Protocol();
        protocol.setProtocolId(1L);
        
        final ProtocolAuthorizationService protocolAuthorizationService = context.mock(ProtocolAuthorizationService.class);
        context.checking(new Expectations() {{
            one(protocolAuthorizationService).hasPermission(USERNAME, protocol, PermissionConstants.MAINTAIN_PROTOCOL_ACCESS); will(returnValue(true));
        }});
        authorizer.setProtocolAuthorizationService(protocolAuthorizationService);
        
        ProtocolTask task = new ProtocolTask(TaskName.MODIFY_PROTOCOL_ROLES, protocol);
        assertEquals(true, authorizer.isAuthorized(USERNAME, task));
    }
    
    @Test
    public void testNotModifyPermission() {
        ModifyProtocolPermissionsAuthorizer authorizer = new ModifyProtocolPermissionsAuthorizer();
        
        final Protocol protocol = new Protocol();
        protocol.setProtocolId(1L);
        
        final ProtocolAuthorizationService protocolAuthorizationService = context.mock(ProtocolAuthorizationService.class);
        context.checking(new Expectations() {{
            one(protocolAuthorizationService).hasPermission(USERNAME, protocol, PermissionConstants.MAINTAIN_PROTOCOL_ACCESS); will(returnValue(false));
        }});
        authorizer.setProtocolAuthorizationService(protocolAuthorizationService);
        
        ProtocolTask task = new ProtocolTask(TaskName.MODIFY_PROTOCOL_ROLES, protocol);
        assertEquals(false, authorizer.isAuthorized(USERNAME, task));
    }
}

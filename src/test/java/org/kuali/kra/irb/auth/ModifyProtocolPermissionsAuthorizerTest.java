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
package org.kuali.kra.irb.auth;

import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.service.KraWorkflowService;
import org.kuali.kra.service.impl.mocks.KraAuthorizationServiceMock;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kew.exception.WorkflowException;

/**
 * Test the Modify Protocol Permissions Authorizer.
 */
public class ModifyProtocolPermissionsAuthorizerTest extends KcUnitTestBase {
    //UserID for Quickstart user
    private static final String USERID = "10000000000";
    
    @Test
    public void testModifyPermission() throws WorkflowException {
        ModifyProtocolPermissionsAuthorizer authorizer = new ModifyProtocolPermissionsAuthorizer();
        authorizer.setKraWorkflowService(KraServiceLocator.getService(KraWorkflowService.class));
        
        final Protocol protocol = createProtocol(1L, false);
        
        final KraAuthorizationService protocolAuthorizationService = new KraAuthorizationServiceMock(true);
        authorizer.setKraAuthorizationService(protocolAuthorizationService);
        
        ProtocolTask task = new ProtocolTask(TaskName.MODIFY_PROTOCOL_ROLES, protocol);
        assertEquals(true, authorizer.isAuthorized(USERID, task));
    }
    
    @Test
    public void testNotModifyPermission() throws WorkflowException {
        ModifyProtocolPermissionsAuthorizer authorizer = new ModifyProtocolPermissionsAuthorizer();
        authorizer.setKraWorkflowService(KraServiceLocator.getService(KraWorkflowService.class));
        final Protocol protocol = createProtocol(1L, false);
        
        final KraAuthorizationService protocolAuthorizationService = new KraAuthorizationServiceMock(false);
        authorizer.setKraAuthorizationService(protocolAuthorizationService);
        
        ProtocolTask task = new ProtocolTask(TaskName.MODIFY_PROTOCOL_ROLES, protocol);
        assertEquals(false, authorizer.isAuthorized(USERID, task));
    }
    
    @Test
    public void testViewOnly() throws WorkflowException {
        ModifyProtocolPermissionsAuthorizer authorizer = new ModifyProtocolPermissionsAuthorizer();
        
        final Protocol protocol = createProtocol(1L, true);
        
        final KraAuthorizationService protocolAuthorizationService = new KraAuthorizationServiceMock(true);
        authorizer.setKraAuthorizationService(protocolAuthorizationService);
        
        ProtocolTask task = new ProtocolTask(TaskName.MODIFY_PROTOCOL_ROLES, protocol);
        assertEquals(false, authorizer.isAuthorized(USERID, task));
    }
    
    private Protocol createProtocol(Long id, boolean viewOnly) throws WorkflowException {
        
        ProtocolDocument doc = new ProtocolDocument();
        doc.getProtocol().setProtocolId(id);
        doc.setViewOnly(viewOnly);
        
        return doc.getProtocol();
    }
}

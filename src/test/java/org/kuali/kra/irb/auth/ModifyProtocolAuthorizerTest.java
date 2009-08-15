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

import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.committee.authorizers.UnitAuthorizationServiceMock;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.auth.ModifyProtocolAuthorizer;
import org.kuali.kra.irb.auth.ProtocolAuthorizationService;
import org.kuali.kra.irb.auth.ProtocolTask;
import org.kuali.kra.service.UnitAuthorizationService;
import org.kuali.rice.kew.exception.WorkflowException;

/**
 * Test the Modify Protocol Authorizer.
 */
public class ModifyProtocolAuthorizerTest extends KraTestBase {

    private static final String USERNAME = "quickstart";
    
    @Test
    public void testCreatePermission() throws WorkflowException {
        ModifyProtocolAuthorizer authorizer = new ModifyProtocolAuthorizer();
        
        final UnitAuthorizationService unitAuthorizationService = new UnitAuthorizationServiceMock(true);
        authorizer.setUnitAuthorizationService(unitAuthorizationService);
        
        Protocol protocol = createProtocol(null, false);
        ProtocolTask task = new ProtocolTask(TaskName.MODIFY_PROTOCOL, protocol);
        assertEquals(true, authorizer.isAuthorized(USERNAME, task));
    }
    
    @Test
    public void testNotCreatePermission() throws WorkflowException {
        ModifyProtocolAuthorizer authorizer = new ModifyProtocolAuthorizer();
        
        final UnitAuthorizationService unitAuthorizationService = new UnitAuthorizationServiceMock(false);
        authorizer.setUnitAuthorizationService(unitAuthorizationService);
        
        Protocol protocol = createProtocol(null, false);
        ProtocolTask task = new ProtocolTask(TaskName.MODIFY_PROTOCOL, protocol);
        assertEquals(false, authorizer.isAuthorized(USERNAME, task));
    }
    
    
    @Test
    public void testModifyPermission() throws WorkflowException {
        ModifyProtocolAuthorizer authorizer = new ModifyProtocolAuthorizer();
        
        final Protocol protocol = createProtocol(1L, false);
        
        final ProtocolAuthorizationService protocolAuthorizationService = new ProtocolAuthorizationServiceMock(true);
        authorizer.setProtocolAuthorizationService(protocolAuthorizationService);
        
        ProtocolTask task = new ProtocolTask(TaskName.MODIFY_PROTOCOL, protocol);
        assertEquals(true, authorizer.isAuthorized(USERNAME, task));
    }
    
    @Test
    public void testNotModifyPermission() throws WorkflowException {
        ModifyProtocolAuthorizer authorizer = new ModifyProtocolAuthorizer();
        
        final Protocol protocol = createProtocol(1L, false);
        
        final ProtocolAuthorizationService protocolAuthorizationService = new ProtocolAuthorizationServiceMock(false);
        authorizer.setProtocolAuthorizationService(protocolAuthorizationService);
        
        ProtocolTask task = new ProtocolTask(TaskName.MODIFY_PROTOCOL, protocol);
        assertEquals(false, authorizer.isAuthorized(USERNAME, task));
    }
    
    @Test
    public void testViewOnly() throws WorkflowException {
        ModifyProtocolAuthorizer authorizer = new ModifyProtocolAuthorizer();
        
        final Protocol protocol = createProtocol(1L, true);
        
        final ProtocolAuthorizationService protocolAuthorizationService = new ProtocolAuthorizationServiceMock(true);
        authorizer.setProtocolAuthorizationService(protocolAuthorizationService);
        
        ProtocolTask task = new ProtocolTask(TaskName.MODIFY_PROTOCOL, protocol);
        assertEquals(false, authorizer.isAuthorized(USERNAME, task));
    }
    
    private Protocol createProtocol(Long id, boolean viewOnly) throws WorkflowException {
        
        ProtocolDocument doc = new ProtocolDocument();
        doc.getProtocol().setProtocolId(id);
        doc.setViewOnly(viewOnly);
        
        return doc.getProtocol();
    }
}

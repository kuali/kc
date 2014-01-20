/*
 * Copyright 2005-2014 The Kuali Foundation
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
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.ProtocolDocument;

/**
 * Test the Modify Protocol Authorizer.
 */
public class ModifyProtocolAuthorizerTest extends ProtocolAuthorizerTestBase {
    
    @Test
    public void testCreatePermission() throws Exception {
        runModifyProtocolAuthorizerTest(PROTOCOL_NUMBER, true, false, true, false, true);
    }
    
    @Test
    public void testNotCreatePermission() throws Exception {
        runModifyProtocolAuthorizerTest(PROTOCOL_NUMBER, true, false, false, false, false);
    }
    
    
    @Test
    public void testModifyPermission() throws Exception {
        runModifyProtocolAuthorizerTest(PROTOCOL_NUMBER, false, false, true, false, true);
    }
    
    @Test
    public void testNotModifyPermission() throws Exception {
        runModifyProtocolAuthorizerTest(PROTOCOL_NUMBER, false, false, false, false, false);
    }
    
    @Test
    public void testViewOnly() throws Exception {
        runModifyProtocolAuthorizerTest(PROTOCOL_NUMBER, false, true, true, false, false);
    }
    
    @Test
    public void testInWorkflow() throws Exception {
        runModifyProtocolAuthorizerTest(PROTOCOL_NUMBER, false, false, false, true, false);
    }
    
    @Override
    protected ProtocolAuthorizer createProtocolAuthorizer(ProtocolDocument protocolDocument, boolean hasPermission, boolean isActionAllowed, boolean isInWorkflow) {
        ProtocolAuthorizer authorizer = new ModifyProtocolAuthorizer();
        authorizer.setUnitAuthorizationService(buildUnitAuthorizationService(PermissionConstants.CREATE_PROTOCOL, hasPermission));
        authorizer.setKraAuthorizationService(buildKraAuthorizationService(protocolDocument, PermissionConstants.MODIFY_PROTOCOL, hasPermission));
        authorizer.setKraWorkflowService(buildKraWorkflowService(protocolDocument, isInWorkflow));
        return authorizer;
    }
    
    @Override
    protected String getTaskName() {
        return TaskName.MODIFY_PROTOCOL;
    }

}
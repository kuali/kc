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
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolActionType;

/**
 * Test the Protocol Request Suspension Authorizer.
 */
public class ProtocolRequestSuspensionAuthorizerTest extends ProtocolAuthorizerTestBase {
    
    @Test
    public void testHasPermission() throws Exception {
        runActionAuthorizerTest(PROTOCOL_NUMBER, true, true, true);
    }
    
    @Test
    public void testNotAProtocol() throws Exception {
        runActionAuthorizerTest(PROTOCOL_NUMBER + "A001", true, true, false);
    }
    
    @Test
    public void testNoPermission() throws Exception {
        runActionAuthorizerTest(PROTOCOL_NUMBER, false, true, false);
    }
    
    @Test
    public void testActionNotAllowed() throws Exception {
        runActionAuthorizerTest(PROTOCOL_NUMBER, true, false, false);
    }
    
    @Override
    protected ProtocolAuthorizer createProtocolAuthorizer(ProtocolDocument protocolDocument, boolean hasPermission, boolean isActionAllowed, boolean isInWorkflow) {
        ProtocolAuthorizer authorizer = new ProtocolRequestSuspensionAuthorizer();
        authorizer.setKraAuthorizationService(buildKraAuthorizationService(protocolDocument, PermissionConstants.SUBMIT_PROTOCOL, hasPermission));
        authorizer.setProtocolActionService(buildProtocolActionService(ProtocolActionType.REQUEST_FOR_SUSPENSION, protocolDocument, isActionAllowed));
        return authorizer;
    }
    
    @Override
    protected String getTaskName() {
        return TaskName.PROTOCOL_REQUEST_SUSPENSION;
    }

}
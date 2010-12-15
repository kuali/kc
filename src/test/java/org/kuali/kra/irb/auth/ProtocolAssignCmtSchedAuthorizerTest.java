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
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;

/**
 * Test the Protocol Assign to Committee/Schedule Authorizer.
 */
public class ProtocolAssignCmtSchedAuthorizerTest extends ProtocolAuthorizerTestBase {
    
    @Test
    public void testPending() throws Exception {
        runSubmissionStatusTest(PROTOCOL_NUMBER, ProtocolSubmissionStatus.PENDING, true, true, true);
    }
    
    @Test
    public void testSubmittedToCommittee() throws Exception {
        runSubmissionStatusTest(PROTOCOL_NUMBER, ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE, true, true, true);
    }
    
    @Test
    public void testNotInWorkflow() throws Exception {
        runSubmissionStatusTest(PROTOCOL_NUMBER, ProtocolSubmissionStatus.PENDING, true, false, false);
    }
    
    @Test
    public void testNoPermission() throws Exception {
        runSubmissionStatusTest(PROTOCOL_NUMBER, ProtocolSubmissionStatus.PENDING, false, true, false);
    }
    
    @Test
    public void testInvalidSubmissionStatus() throws Exception {
        runSubmissionStatusTest(PROTOCOL_NUMBER, ProtocolSubmissionStatus.APPROVED, true, true, false);
    }
    
    @Override
    protected ProtocolAuthorizer createProtocolAuthorizer(ProtocolDocument protocolDocument, boolean hasPermission, boolean isActionAllowed, boolean isInWorkflow) {
        ProtocolAuthorizer authorizer = new ProtocolAssignCmtSchedAuthorizer();
        authorizer.setProtocolActionService(KraServiceLocator.getService(ProtocolActionService.class));
        authorizer.setKraAuthorizationService(buildKraAuthorizationService(protocolDocument, PermissionConstants.PERFORM_IRB_ACTIONS_ON_PROTO, hasPermission));
        authorizer.setKraWorkflowService(buildKraWorkflowService(protocolDocument, isInWorkflow));
        return authorizer;
    }
    
    @Override
    protected String getTaskName() {
        return TaskName.ASSIGN_TO_COMMITTEE_SCHEDULE;
    }

}
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

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.service.KraWorkflowService;

/**
 * Test the Protocol Assign to Committee/Schedule Authorizer.
 */
@RunWith(JMock.class)
public class ProtocolAssignCmtSchedAuthorizerTest {

    private static final String USERNAME = "quickstart";
    
    private Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};
    
    @Test
    public void testPending() {
        runTest(ProtocolSubmissionStatus.PENDING, true, true, true);
    }
    
    @Test
    public void testSubmittedToCommittee() {
        runTest(ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE, true, true, true);
    }
    
    @Test
    public void testNotInWorkflow() {
        runTest(ProtocolSubmissionStatus.PENDING, true, false, false);
    }
    
    @Test
    public void testNoPermission() {
        runTest(ProtocolSubmissionStatus.PENDING, false, true, false);
    }
    
    @Test
    public void testInvalidSubmissionStatus() {
        runTest(ProtocolSubmissionStatus.APPROVED, true, true, false);
    }
    
    private void runTest(final String submissionStatus, final boolean hasPermission, final boolean isInWorkflow, boolean expected) {
        ProtocolAssignCmtSchedAuthorizer authorizer = new ProtocolAssignCmtSchedAuthorizer();
        
        final ProtocolSubmission submission = context.mock(ProtocolSubmission.class);
        final List<ProtocolSubmission> submissions = new ArrayList<ProtocolSubmission>();
        submissions.add(submission);
        context.checking(new Expectations() {{
            allowing(submission).getSubmissionStatusCode(); will(returnValue(submissionStatus)); 
        }});
        
        final Protocol protocol = context.mock(Protocol.class);
        context.checking(new Expectations() {{
            allowing(protocol).getProtocolDocument(); will(returnValue(null));
            allowing(protocol).getProtocolSubmissions(); will(returnValue(submissions));
        }});
        
        final KraWorkflowService workflowService = context.mock(KraWorkflowService.class);
        context.checking(new Expectations() {{
            allowing(workflowService).isInWorkflow(null); will(returnValue(isInWorkflow)); 
        }});
        authorizer.setKraWorkflowService(workflowService);
        
        final KraAuthorizationService authorizationService = context.mock(KraAuthorizationService.class);
        context.checking(new Expectations() {{
            allowing(authorizationService).hasPermission(USERNAME, protocol, PermissionConstants.PERFORM_IRB_ACTIONS_ON_PROTO); will(returnValue(hasPermission));
        }});
        authorizer.setKraAuthorizationService(authorizationService);
        
        ProtocolTask task = new ProtocolTask(TaskName.ASSIGN_TO_COMMITTEE_SCHEDULE, protocol);
        assertEquals(expected, authorizer.isAuthorized(USERNAME, task));
    }
}

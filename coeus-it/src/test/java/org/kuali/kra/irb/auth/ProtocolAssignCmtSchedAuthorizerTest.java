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

import org.junit.Test;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
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
        ProtocolAssignCmtSchedAuthorizer authorizer = new ProtocolAssignCmtSchedAuthorizer();
        authorizer.setProtocolActionService(KcServiceLocator.getService(ProtocolActionService.class));
        authorizer.setKraAuthorizationService(buildKraAuthorizationService(protocolDocument, PermissionConstants.PERFORM_IRB_ACTIONS_ON_PROTO, hasPermission));
        authorizer.setKraWorkflowService(buildKraWorkflowService(protocolDocument, isInWorkflow));
        return authorizer;
    }
    
    @Override
    protected String getTaskName() {
        return TaskName.ASSIGN_TO_COMMITTEE_SCHEDULE;
    }

}

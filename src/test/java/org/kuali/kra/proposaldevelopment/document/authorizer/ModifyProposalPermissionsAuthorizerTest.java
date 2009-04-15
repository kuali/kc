/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.document.authorizer;

import org.jmock.Expectations;
import org.jmock.MockObjectTestCase;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.document.authorization.ProposalTask;
import org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService;
import org.kuali.kra.service.KraWorkflowService;

public class ModifyProposalPermissionsAuthorizerTest extends MockObjectTestCase {

    private Mockery context = new JUnit4Mockery();

    private ProposalDevelopmentDocument doc;
    private ProposalTask task;
    String username = "joeUser";


    @Test
    public void testSuccessFullAuthorization() {
        doc = new ProposalDevelopmentDocument();
        doc.setSubmitFlag(false);
        task = new ProposalTask(TaskName.MODIFY_PROPOSAL_ROLES, doc);

        ModifyProposalPermissionsAuthorizer auth = new ModifyProposalPermissionsAuthorizer();
        auth.setProposalAuthorizationService(getProposalAuthorizationServiceMock(true, true));
        auth.setKraWorkflowService(getKraWorkflowServiceMock(false, false, false));

        assertTrue(auth.isAuthorized(username, task));
    }

    @Test
    public void testFailureSubmittedToSponsor() {
        doc = new ProposalDevelopmentDocument();
        doc.setSubmitFlag(true);
        task = new ProposalTask(TaskName.MODIFY_PROPOSAL_ROLES, doc);

        ModifyProposalPermissionsAuthorizer auth = new ModifyProposalPermissionsAuthorizer();
        auth.setProposalAuthorizationService(getProposalAuthorizationServiceMock(true, true));
        auth.setKraWorkflowService(getKraWorkflowServiceMock(false, false, false));

        assertFalse(auth.isAuthorized(username, task));
    }

    @Test
    public void testFailureAllConditions() {
        doc = new ProposalDevelopmentDocument();
        doc.setSubmitFlag(true);
        task = new ProposalTask(TaskName.MODIFY_PROPOSAL_ROLES, doc);

        ModifyProposalPermissionsAuthorizer auth = new ModifyProposalPermissionsAuthorizer();
        auth.setProposalAuthorizationService(getProposalAuthorizationServiceMock(false, false));
        auth.setKraWorkflowService(getKraWorkflowServiceMock(false, false, false));

        assertFalse(auth.isAuthorized(username, task));
    }

    @Test
    public void testSuccessHasAddViewerPermissionAndIsEnRoute() {
        doc = new ProposalDevelopmentDocument();
        doc.setSubmitFlag(false);
        task = new ProposalTask(TaskName.MODIFY_PROPOSAL_ROLES, doc);

        ModifyProposalPermissionsAuthorizer auth = new ModifyProposalPermissionsAuthorizer();
        auth.setProposalAuthorizationService(getProposalAuthorizationServiceMock(false, true));
        auth.setKraWorkflowService(getKraWorkflowServiceMock(false, true, false));

        assertTrue(auth.isAuthorized(username, task));
    }

    @Test
    public void testSuccessHasAddViewerPermissionAndIsClosed() {
        doc = new ProposalDevelopmentDocument();
        doc.setSubmitFlag(false);
        task = new ProposalTask(TaskName.MODIFY_PROPOSAL_ROLES, doc);

        ModifyProposalPermissionsAuthorizer auth = new ModifyProposalPermissionsAuthorizer();
        auth.setProposalAuthorizationService(getProposalAuthorizationServiceMock(false, true));
        auth.setKraWorkflowService(getKraWorkflowServiceMock(false, true, true));

        assertTrue(auth.isAuthorized(username, task));
    }

    @Test
    public void testFailureHasAddViewerPermissionAndNotEnRouteOrClosed() {
        doc = new ProposalDevelopmentDocument();
        doc.setSubmitFlag(false);
        task = new ProposalTask(TaskName.MODIFY_PROPOSAL_ROLES, doc);

        ModifyProposalPermissionsAuthorizer auth = new ModifyProposalPermissionsAuthorizer();
        auth.setProposalAuthorizationService(getProposalAuthorizationServiceMock(false, true));
        auth.setKraWorkflowService(getKraWorkflowServiceMock(false, false, false));

        assertFalse(auth.isAuthorized(username, task));
    }

    @Test
    public void testSuccessIsApproverAndIsEnroute() {
        doc = new ProposalDevelopmentDocument();
        doc.setSubmitFlag(false);
        task = new ProposalTask(TaskName.MODIFY_PROPOSAL_ROLES, doc);

        ModifyProposalPermissionsAuthorizer auth = new ModifyProposalPermissionsAuthorizer();
        auth.setProposalAuthorizationService(getProposalAuthorizationServiceMock(false, false));
        auth.setKraWorkflowService(getKraWorkflowServiceMock(true, true, false));

        assertTrue(auth.isAuthorized(username, task));
    }

    @Test
    public void testFailureIsApproverAndIsClosed() {
        doc = new ProposalDevelopmentDocument();
        doc.setSubmitFlag(false);
        task = new ProposalTask(TaskName.MODIFY_PROPOSAL_ROLES, doc);

        ModifyProposalPermissionsAuthorizer auth = new ModifyProposalPermissionsAuthorizer();
        auth.setProposalAuthorizationService(getProposalAuthorizationServiceMock(false, false));
        auth.setKraWorkflowService(getKraWorkflowServiceMock(true, true, true));

        assertFalse(auth.isAuthorized(username, task));
    }

    @Test
    public void testFailureIsApproverAndNotEnRouteOrClosed() {
        doc = new ProposalDevelopmentDocument();
        doc.setSubmitFlag(false);
        task = new ProposalTask(TaskName.MODIFY_PROPOSAL_ROLES, doc);

        ModifyProposalPermissionsAuthorizer auth = new ModifyProposalPermissionsAuthorizer();
        auth.setProposalAuthorizationService(getProposalAuthorizationServiceMock(false, false));
        auth.setKraWorkflowService(getKraWorkflowServiceMock(true, false, false));

        assertFalse(auth.isAuthorized(username, task));
    }

    private ProposalAuthorizationService getProposalAuthorizationServiceMock(final boolean hasMaintainAccess,
            final boolean hasAddViewerAccess) {
        final ProposalAuthorizationService authorizationService = context.mock(ProposalAuthorizationService.class);
        context.checking(new Expectations() {
            {
                allowing(authorizationService).hasPermission(username, doc, PermissionConstants.ADD_PROPOSAL_VIEWER);
                will(returnValue(hasAddViewerAccess));
                allowing(authorizationService).hasPermission(username, doc, PermissionConstants.MAINTAIN_PROPOSAL_ACCESS);
                will(returnValue(hasMaintainAccess));
            }
        });
        return authorizationService;
    }

    private KraWorkflowService getKraWorkflowServiceMock(final boolean hasWorkflowPermission, final boolean isInWorkflow,
            final boolean isClosed) {
        final KraWorkflowService workflowService = context.mock(KraWorkflowService.class);
        context.checking(new Expectations() {
            {
                allowing(workflowService).hasWorkflowPermission(username, doc);
                will(returnValue(hasWorkflowPermission));
                allowing(workflowService).isInWorkflow(doc);
                will(returnValue(isInWorkflow));
                allowing(workflowService).isEnRoute(doc);
                will(returnValue(isInWorkflow && !isClosed));
                allowing(workflowService).isClosed(doc);
                will(returnValue(isClosed));
            }
        });
        return workflowService;
    }
}

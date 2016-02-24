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
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.kuali.coeus.common.framework.auth.SystemAuthorizationService;
import org.kuali.coeus.common.framework.auth.UnitAuthorizationService;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewService;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
public abstract class ProtocolAuthorizerTestBase extends KcIntegrationTestBase {
    
    protected static final String PROTOCOL_NUMBER = "0906000001";
    private static final String USERNAME = "quickstart";
    
    private static final String KC_PROTOCOL_NAMESPACE = "KC-PROTOCOL";
    private static final String KC_UNIT_NAMESPACE = "KC-UNT";
    
    private Mockery context;
    
    @Before
    public void setUp() throws Exception {
        context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
    }
    
    @After
    public void tearDown() throws Exception {
        context = null;
    }
    
    protected void runModifyProtocolAuthorizerTest(String protocolNumber, boolean isProtocolNew, boolean isProtocolViewOnly, boolean hasPermission, 
        boolean isInWorkflow, boolean expected) throws Exception {
        
        ProtocolDocument document = ProtocolFactory.createProtocolDocument(protocolNumber);
        if (isProtocolNew) {
            document.getProtocol().setProtocolId(null);
        }
        document.setViewOnly(isProtocolViewOnly);
        ProtocolAuthorizer authorizer = createProtocolAuthorizer(document, hasPermission, false, isInWorkflow);
        
        runTest(document, authorizer, expected);
    }
    
    protected void runActionAuthorizerTest(String protocolNumber, boolean hasPermission, boolean isActionAllowed, boolean expected) 
        throws Exception {
        
        ProtocolDocument document = ProtocolFactory.createProtocolDocument(protocolNumber);
        ProtocolAuthorizer authorizer = createProtocolAuthorizer(document, hasPermission, isActionAllowed, true);
        
        runTest(document, authorizer, expected);
    }
    
    protected void runStatusAuthorizerTest(String protocolNumber, String protocolStatusCode, boolean hasPermission, boolean expected) 
        throws Exception {
        
        ProtocolDocument document = ProtocolFactory.createProtocolDocument(protocolNumber);
        document.getProtocol().setProtocolStatusCode(protocolStatusCode);
        ProtocolAuthorizer authorizer = createProtocolAuthorizer(document, hasPermission, false, true);
        
        runTest(document, authorizer, expected);
    }
    
    protected void runSubmissionStatusTest(String protocolNumber, String submissionStatusCode, boolean hasPermission, boolean isInWorkflow, boolean expected) 
        throws Exception {
        
        ProtocolDocument document = ProtocolFactory.createProtocolDocument(protocolNumber);
        List<ProtocolSubmissionBase> submissions = new ArrayList<ProtocolSubmissionBase>();
        ProtocolSubmission submission = new ProtocolSubmission();
        submission.setSubmissionStatusCode(submissionStatusCode);
        submissions.add(submission);
        document.getProtocol().setProtocolSubmissions(submissions);
        ProtocolAuthorizer authorizer = createProtocolAuthorizer(document, hasPermission, false, isInWorkflow);
        
        runTest(document, authorizer, expected);
    }
    
    protected void runTest(ProtocolDocument protocolDocument, ProtocolAuthorizer authorizer, boolean expected) {
        ProtocolTask task = new ProtocolTask(getTaskName(), protocolDocument.getProtocol());
        assertEquals(expected, authorizer.isAuthorized(USERNAME, task));
    }
    
    protected abstract ProtocolAuthorizer createProtocolAuthorizer(ProtocolDocument protocolDocument, boolean hasPermission, boolean isActionAllowed, boolean isInWorkflow) throws Exception;
    
    /**
     * Return the name of the task which corresponds to the authorizer that will be invoked.
     * @return
     */
    protected abstract String getTaskName();
    
    protected UnitAuthorizationService buildUnitAuthorizationService(final String permissionConstant, final boolean hasPermission) {
        final UnitAuthorizationService service = context.mock(UnitAuthorizationService.class);
        
        context.checking(new Expectations() {{
            allowing(service).hasPermission(USERNAME, Constants.MODULE_NAMESPACE_PROTOCOL, permissionConstant); 
            will(returnValue(hasPermission));
        }});
        
        return service;
    }

    protected KcAuthorizationService buildKraAuthorizationService(final ProtocolDocument protocolDocument, final String permissionConstant, final boolean hasPermission) {
        return buildKraAuthorizationService (protocolDocument, permissionConstant, hasPermission, RoleConstants.PROTOCOL_AGGREGATOR, true);
    }
        
    protected KcAuthorizationService buildKraAuthorizationService(final ProtocolDocument protocolDocument, final String permissionConstant, final boolean hasPermission, final String roleConstant, final boolean hasRole) {    
        final KcAuthorizationService service = context.mock(KcAuthorizationService.class);
        context.checking(new Expectations() {{
            allowing(service).hasPermission(USERNAME, protocolDocument.getProtocol(), permissionConstant); 
            will(returnValue(hasPermission));
            if (PermissionConstants.CREATE_AMMENDMENT.equals(permissionConstant) && !hasPermission) {
                allowing(service).hasPermission(USERNAME, protocolDocument.getProtocol(), PermissionConstants.CREATE_ANY_AMENDMENT); 
                will(returnValue(hasPermission));
            } else if (PermissionConstants.CREATE_RENEWAL.equals(permissionConstant) && !hasPermission) {
                allowing(service).hasPermission(USERNAME, protocolDocument.getProtocol(), PermissionConstants.CREATE_ANY_RENEWAL); 
                will(returnValue(hasPermission));
            }
        }});
        
        return service;
    }

    protected SystemAuthorizationService buildSystemAuthorizationService(final ProtocolDocument protocolDocument, final String permissionConstant, final boolean hasPermission, final String roleConstant, final boolean hasRole) {
        final SystemAuthorizationService service = context.mock(SystemAuthorizationService.class);
        context.checking(new Expectations() {{
            //set roles for appropriate namespace based on role passed in when admin role check is required in the authorizer
            if(RoleConstants.PROTOCOL_AGGREGATOR.equals(roleConstant)) {
                allowing(service).hasRole(USERNAME, KC_PROTOCOL_NAMESPACE, RoleConstants.PROTOCOL_AGGREGATOR);
                will(returnValue(hasRole));
            } else if (RoleConstants.IRB_ADMINISTRATOR.equals(roleConstant)) {
                allowing(service).hasRole(USERNAME, KC_UNIT_NAMESPACE, roleConstant);
                will(returnValue(hasRole));
            }
        }});

        return service;
    }

    protected KcWorkflowService buildKraWorkflowService(final ProtocolDocument protocolDocument, final boolean isInWorkflow) {
        final KcWorkflowService service = context.mock(KcWorkflowService.class);
        
        context.checking(new Expectations() {{
            allowing(service).isInWorkflow(protocolDocument); will(returnValue(isInWorkflow)); 
        }});
        
        context.checking(new Expectations() {{
            allowing(service).hasWorkflowPermission(USERNAME, protocolDocument); will(returnValue(isInWorkflow)); 
        }});
        
        return service;
    }
    
    protected KcWorkflowService buildKraWorkflowNodeService(final ProtocolDocument protocolDocument, final boolean isInWorkflow) {
        final KcWorkflowService service = context.mock(KcWorkflowService.class);
        
        context.checking(new Expectations() {{
            allowing(service).isInWorkflow(protocolDocument); will(returnValue(isInWorkflow)); 
        }});
        
        context.checking(new Expectations() {{
            allowing(service).isDocumentOnNode(protocolDocument,Constants.PROTOCOL_IRBREVIEW_ROUTE_NODE_NAME); will(returnValue(isInWorkflow)); 
        }});
        
        return service;
    }

    protected ProtocolActionService buildProtocolActionService(final String protocolActionTypeCode, final ProtocolDocument protocolDocument, final boolean isActionAllowed) {
        final ProtocolActionService service = context.mock(ProtocolActionService.class);
        
        context.checking(new Expectations() {{
            allowing(service).isActionAllowed(protocolActionTypeCode, protocolDocument.getProtocol()); 
            will(returnValue(isActionAllowed));
        }});
        
        return service;
    }
    
    protected ProtocolAmendRenewService buildProtocolAmendRenewService(final ProtocolDocument protocolDocument, final List<String> availableModules) throws Exception {
        final ProtocolAmendRenewService service = context.mock(ProtocolAmendRenewService.class);
        
        context.checking(new Expectations() {{
            allowing(service).getAvailableModules(protocolDocument.getProtocol().getProtocolNumber()); 
            will(returnValue(availableModules));
        }});
        
        return service;
    }

}

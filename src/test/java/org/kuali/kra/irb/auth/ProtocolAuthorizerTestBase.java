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

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewService;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.service.KraWorkflowService;
import org.kuali.kra.service.UnitAuthorizationService;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;

public abstract class ProtocolAuthorizerTestBase extends KcUnitTestBase {
    
    protected static final String PROTOCOL_NUMBER = "0906000001";
    private static final String USERNAME = "quickstart";
    
    private Mockery context;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        context = new JUnit4Mockery();
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
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
        List<ProtocolSubmission> submissions = new ArrayList<ProtocolSubmission>();
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

    protected KraAuthorizationService buildKraAuthorizationService(final ProtocolDocument protocolDocument, final String permissionConstant, final boolean hasPermission) {
        final KraAuthorizationService service = context.mock(KraAuthorizationService.class);
        
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
    
    protected KraWorkflowService buildKraWorkflowService(final ProtocolDocument protocolDocument, final boolean isInWorkflow) {
        final KraWorkflowService service = context.mock(KraWorkflowService.class);
        
        context.checking(new Expectations() {{
            allowing(service).isInWorkflow(protocolDocument); will(returnValue(isInWorkflow)); 
        }});
        
        context.checking(new Expectations() {{
            allowing(service).hasWorkflowPermission(USERNAME, protocolDocument); will(returnValue(isInWorkflow)); 
        }});
        
        return service;
    }
    
    protected KraWorkflowService buildKraWorkflowNodeService(final ProtocolDocument protocolDocument, final boolean isInWorkflow) {
        final KraWorkflowService service = context.mock(KraWorkflowService.class);
        
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
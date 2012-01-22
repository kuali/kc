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
package org.kuali.kra.committee.authorizers;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.document.authorization.CommitteeTask;
import org.kuali.kra.committee.document.authorizer.CommitteeActionAuthorizer;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.committee.test.CommitteeFactory;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.service.impl.mocks.KraAuthorizationServiceMock;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * Test the Committee Action Authorizer.
 */
public class CommitteeActionAuthorizerTest extends KcUnitTestBase {

    private static final String USERNAME = "quickstart";
    private static final String COMMITTEE_ID = "Actn Auth Test";
    
    private CommitteeDocument committeeDocument;
    private Mockery context = new JUnit4Mockery();

    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        committeeDocument = CommitteeFactory.createCommitteeDocument("Actn Auth Test");
    }
    
    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        super.tearDown();
    }
    
    @Test
    public void testPerformActionsPermission() throws WorkflowException {
        CommitteeActionAuthorizer authorizer = new CommitteeActionAuthorizer();
        
        final Committee committee = committeeDocument.getCommittee();
        final CommitteeService committeeService = context.mock(CommitteeService.class);
        context.checking(new Expectations() {{
            allowing(committeeService).getCommitteeById(COMMITTEE_ID); will(returnValue(committee));
        }});
        authorizer.setCommitteeService(committeeService);

        final KraAuthorizationServiceMock kraAuthorizationService = new KraAuthorizationServiceMock(true);
        authorizer.setKraAuthorizationService(kraAuthorizationService);
        
        committeeDocument.getDocumentHeader().getWorkflowDocument().route("");
        CommitteeTask task = new CommitteeTask(TaskName.PERFORM_COMMITTEE_ACTIONS, committeeDocument.getCommittee());
        assertEquals(true, authorizer.isAuthorized(USERNAME, task));
    }
    
    @Test
    public void testNoPerformActionsPermissionBasedOnPermission() throws WorkflowException {
        CommitteeActionAuthorizer authorizer = new CommitteeActionAuthorizer();
        
        final Committee committee = committeeDocument.getCommittee();
        final CommitteeService committeeService = context.mock(CommitteeService.class);
        context.checking(new Expectations() {{
            allowing(committeeService).getCommitteeById(COMMITTEE_ID); will(returnValue(committee));
        }});
        authorizer.setCommitteeService(committeeService);

        final KraAuthorizationServiceMock kraAuthorizationService = new KraAuthorizationServiceMock(false);
        authorizer.setKraAuthorizationService(kraAuthorizationService);

        committeeDocument.getDocumentHeader().getWorkflowDocument().route("");
        CommitteeTask task = new CommitteeTask(TaskName.PERFORM_COMMITTEE_ACTIONS, committeeDocument.getCommittee());
        assertEquals(false, authorizer.isAuthorized(USERNAME, task));
    }
    
    @Test
    public void testNoPerformActionsPermissionBasedOnDocumentStatus() throws WorkflowException {
        CommitteeActionAuthorizer authorizer = new CommitteeActionAuthorizer();
        
        final Committee committee = committeeDocument.getCommittee();
        final CommitteeService committeeService = context.mock(CommitteeService.class);
        context.checking(new Expectations() {{
            allowing(committeeService).getCommitteeById(COMMITTEE_ID); will(returnValue(committee));
        }});
        authorizer.setCommitteeService(committeeService);

        final KraAuthorizationServiceMock kraAuthorizationService = new KraAuthorizationServiceMock(true);
        authorizer.setKraAuthorizationService(kraAuthorizationService);
        
        CommitteeTask task = new CommitteeTask(TaskName.PERFORM_COMMITTEE_ACTIONS, committeeDocument.getCommittee());
        assertEquals(false, authorizer.isAuthorized(USERNAME, task));
    }
    
    @Test
    public void testNoPerformActionsPermissionBasedOnVersion() throws WorkflowException {
        CommitteeActionAuthorizer authorizer = new CommitteeActionAuthorizer();
        
        final Committee committee = new Committee();
        committee.setId(1L);
        final CommitteeService committeeService = context.mock(CommitteeService.class);
        context.checking(new Expectations() {{
            allowing(committeeService).getCommitteeById(COMMITTEE_ID); will(returnValue(committee));
        }});
        authorizer.setCommitteeService(committeeService);

        final KraAuthorizationServiceMock kraAuthorizationService = new KraAuthorizationServiceMock(true);
        authorizer.setKraAuthorizationService(kraAuthorizationService);
        
        committeeDocument.getDocumentHeader().getWorkflowDocument().route("");
        CommitteeTask task = new CommitteeTask(TaskName.PERFORM_COMMITTEE_ACTIONS, committeeDocument.getCommittee());
        assertEquals(false, authorizer.isAuthorized(USERNAME, task));
    }
    
}

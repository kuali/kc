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
package org.kuali.kra.proposaldevelopment.document.authorizer;

import java.util.LinkedList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.document.authorization.ProposalTask;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.service.KraWorkflowService;
import org.kuali.rice.core.lifecycle.Lifecycle;

@RunWith(JMock.class)
public class ModifyProposalPermissionsAuthorizerTest extends KraTestBase {
    
    private Mockery context = new JUnit4Mockery();

    private ProposalDevelopmentDocument doc;
    private ProposalTask task;
    
    @Test
    public void testSimpleSuccess() {
        
        doc = new ProposalDevelopmentDocument();
        doc.getDevelopmentProposal().setSubmitFlag(false);       

        task = new ProposalTask(TaskName.MODIFY_PROPOSAL_ROLES, doc);
        ModifyProposalPermissionsAuthorizer mpa = new ModifyProposalPermissionsAuthorizer();

        final KraAuthorizationService authorizationService = context.mock(KraAuthorizationService.class);
        context.checking(new Expectations() {{
            one(authorizationService).hasPermission(TaskName.MODIFY_PROPOSAL_ROLES, 
                                                    doc, 
                                                    PermissionConstants.MAINTAIN_PROPOSAL_ACCESS); 
            will(returnValue(true));
        }});
        mpa.setKraAuthorizationService(authorizationService);
        
        final KraWorkflowService workflowService = context.mock(KraWorkflowService.class);
        context.checking(new Expectations() {{
            one(workflowService).isInWorkflow(doc); 
            will(returnValue(false));
        }});
        mpa.setKraWorkflowService(workflowService);
        
        assertTrue(mpa.isAuthorized(TaskName.MODIFY_PROPOSAL_ROLES, task));          
    }
    
    @Test
    public void testNegativeSubmittedToSponsor() {
      
        doc = new ProposalDevelopmentDocument();
        doc.getDevelopmentProposal().setSubmitFlag(true);       

        task = new ProposalTask(TaskName.MODIFY_PROPOSAL_ROLES, doc);
        ModifyProposalPermissionsAuthorizer mpa = new ModifyProposalPermissionsAuthorizer();

        final KraAuthorizationService authorizationService = context.mock(KraAuthorizationService.class);
        context.checking(new Expectations() {{
            one(authorizationService).hasPermission(TaskName.MODIFY_PROPOSAL_ROLES, 
                                                    doc, 
                                                    PermissionConstants.MAINTAIN_PROPOSAL_ACCESS); 
            will(returnValue(true));
        }});
        mpa.setKraAuthorizationService(authorizationService);
        
        final KraWorkflowService workflowService = context.mock(KraWorkflowService.class);
        context.checking(new Expectations() {{
            one(workflowService).isInWorkflow(doc); 
            will(returnValue(false));
        }});
        mpa.setKraWorkflowService(workflowService);
        
        assertFalse(mpa.isAuthorized(TaskName.MODIFY_PROPOSAL_ROLES, task));        
    }
    
    @Test
    public void testNegativeSubmittedToWorkflow() {
      
        doc = new ProposalDevelopmentDocument();
        doc.getDevelopmentProposal().setSubmitFlag(false);       

        task = new ProposalTask(TaskName.MODIFY_PROPOSAL_ROLES, doc);
        ModifyProposalPermissionsAuthorizer mpa = new ModifyProposalPermissionsAuthorizer();

        final KraAuthorizationService authorizationService = context.mock(KraAuthorizationService.class);
        context.checking(new Expectations() {{
            one(authorizationService).hasPermission(TaskName.MODIFY_PROPOSAL_ROLES, 
                                                    doc, 
                                                    PermissionConstants.MAINTAIN_PROPOSAL_ACCESS); 
            will(returnValue(true));
        }});
        mpa.setKraAuthorizationService(authorizationService);
        
        final KraWorkflowService workflowService = context.mock(KraWorkflowService.class);
        context.checking(new Expectations() {{
            one(workflowService).isInWorkflow(doc); 
            will(returnValue(true));
        }});
        mpa.setKraWorkflowService(workflowService);
        
        assertFalse(mpa.isAuthorized(TaskName.MODIFY_PROPOSAL_ROLES, task));        
        
    }
    
    @Test
    public void testNegativeNotPermitted() {
     
        doc = new ProposalDevelopmentDocument();
        doc.getDevelopmentProposal().setSubmitFlag(false);       

        task = new ProposalTask(TaskName.MODIFY_PROPOSAL_ROLES, doc);
        ModifyProposalPermissionsAuthorizer mpa = new ModifyProposalPermissionsAuthorizer();

        final KraAuthorizationService authorizationService = context.mock(KraAuthorizationService.class);
        context.checking(new Expectations() {{
            one(authorizationService).hasPermission(TaskName.MODIFY_PROPOSAL_ROLES, 
                                                    doc, 
                                                    PermissionConstants.MAINTAIN_PROPOSAL_ACCESS); 
            will(returnValue(false));
        }});
        mpa.setKraAuthorizationService(authorizationService);
        
        final KraWorkflowService workflowService = context.mock(KraWorkflowService.class);
        context.checking(new Expectations() {{
            ignoring(workflowService).isInWorkflow(doc); 
            will(returnValue(false));
        }});
        mpa.setKraWorkflowService(workflowService);
        
        assertFalse(mpa.isAuthorized(TaskName.MODIFY_PROPOSAL_ROLES, task));        
        
    }
    
    protected List<Lifecycle> getDefaultPerTestLifecycles() {
        List<Lifecycle> lifecycles = new LinkedList<Lifecycle>();
        return lifecycles;
    }
}

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
    boolean inWorkFlow = false;
    boolean submittedToSponsor = false;
    boolean permitted=true;

    
    @Test
    public void testSimpleSuccess() {
        
        doc = new ProposalDevelopmentDocument();
        doc.setSubmitFlag(submittedToSponsor);       

        task = new ProposalTask(TaskName.MODIFY_PROPOSAL_ROLES, doc);
        ModifyProposalPermissionsAuthorizer mpa = new ModifyProposalPermissionsAuthorizer();

        final ProposalAuthorizationService authorizationService = context.mock(ProposalAuthorizationService.class);
        context.checking(new Expectations() {{
            one(authorizationService).hasPermission(TaskName.MODIFY_PROPOSAL_ROLES, 
                                                    doc, 
                                                    PermissionConstants.MAINTAIN_PROPOSAL_ACCESS); 
            will(returnValue(true));
        }});
        mpa.setProposalAuthorizationService(authorizationService);
        
        final KraWorkflowService workflowService = context.mock(KraWorkflowService.class);
        context.checking(new Expectations() {{
            one(workflowService).isInWorkflow(doc); 
            will(returnValue(inWorkFlow));
        }});
        mpa.setKraWorkflowService(workflowService);
        
        assertTrue(mpa.isAuthorized(TaskName.MODIFY_PROPOSAL_ROLES, task));          
    }
    
    @Test
    public void testNegativeSubmittedToSponsor() {
        submittedToSponsor=true;
        doc = new ProposalDevelopmentDocument();
        doc.setSubmitFlag(submittedToSponsor);       

        task = new ProposalTask(TaskName.MODIFY_PROPOSAL_ROLES, doc);
        ModifyProposalPermissionsAuthorizer mpa = new ModifyProposalPermissionsAuthorizer();

        final ProposalAuthorizationService authorizationService = context.mock(ProposalAuthorizationService.class);
        context.checking(new Expectations() {{
            one(authorizationService).hasPermission(TaskName.MODIFY_PROPOSAL_ROLES, 
                                                    doc, 
                                                    PermissionConstants.MAINTAIN_PROPOSAL_ACCESS); 
            will(returnValue(true));
        }});
        mpa.setProposalAuthorizationService(authorizationService);
        
        final KraWorkflowService workflowService = context.mock(KraWorkflowService.class);
        context.checking(new Expectations() {{
            one(workflowService).isInWorkflow(doc); 
            will(returnValue(inWorkFlow));
        }});
        mpa.setKraWorkflowService(workflowService);
        
        assertFalse(mpa.isAuthorized(TaskName.MODIFY_PROPOSAL_ROLES, task));        
    }
    
    @Test
    public void testNegativeSubmittedToWorkflow() {
        inWorkFlow=true; 
        doc = new ProposalDevelopmentDocument();
        doc.setSubmitFlag(submittedToSponsor);       

        task = new ProposalTask(TaskName.MODIFY_PROPOSAL_ROLES, doc);
        ModifyProposalPermissionsAuthorizer mpa = new ModifyProposalPermissionsAuthorizer();

        final ProposalAuthorizationService authorizationService = context.mock(ProposalAuthorizationService.class);
        context.checking(new Expectations() {{
            one(authorizationService).hasPermission(TaskName.MODIFY_PROPOSAL_ROLES, 
                                                    doc, 
                                                    PermissionConstants.MAINTAIN_PROPOSAL_ACCESS); 
            will(returnValue(true));
        }});
        mpa.setProposalAuthorizationService(authorizationService);
        
        final KraWorkflowService workflowService = context.mock(KraWorkflowService.class);
        context.checking(new Expectations() {{
            one(workflowService).isInWorkflow(doc); 
            will(returnValue(inWorkFlow));
        }});
        mpa.setKraWorkflowService(workflowService);
        
        assertFalse(mpa.isAuthorized(TaskName.MODIFY_PROPOSAL_ROLES, task));        
        
    }
    
    @Test
    public void testNegativeNotPermitted() {
        permitted=false;
        doc = new ProposalDevelopmentDocument();
        doc.setSubmitFlag(submittedToSponsor);       

        task = new ProposalTask(TaskName.MODIFY_PROPOSAL_ROLES, doc);
        ModifyProposalPermissionsAuthorizer mpa = new ModifyProposalPermissionsAuthorizer();

        final ProposalAuthorizationService authorizationService = context.mock(ProposalAuthorizationService.class);
        context.checking(new Expectations() {{
            one(authorizationService).hasPermission(TaskName.MODIFY_PROPOSAL_ROLES, 
                                                    doc, 
                                                    PermissionConstants.MAINTAIN_PROPOSAL_ACCESS); 
            will(returnValue(false));
        }});
        mpa.setProposalAuthorizationService(authorizationService);
        
        final KraWorkflowService workflowService = context.mock(KraWorkflowService.class);
        context.checking(new Expectations() {{
            one(workflowService).isInWorkflow(doc); 
            will(returnValue(permitted));
        }});
        mpa.setKraWorkflowService(workflowService);
        
        assertFalse(mpa.isAuthorized(TaskName.MODIFY_PROPOSAL_ROLES, task));        
        
    }
}

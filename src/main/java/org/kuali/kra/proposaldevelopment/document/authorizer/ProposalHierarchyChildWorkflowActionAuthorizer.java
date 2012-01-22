/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.document.authorizer;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.document.authorization.ProposalTask;
import org.kuali.kra.proposaldevelopment.hierarchy.ProposalHierarchyException;
import org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService;
import org.kuali.rice.kew.api.WorkflowDocument;

/**
 * This authorizer determines if the user has the permission
 * to execute any workflow actions.  The isAuthorized method currently
 * only looks to see if the proposal is a child document, and if the parent
 * is enroute it denies the authorization ( hierarchy children cannot be cancelled or disapproved
 * when the parent is enroute.
 */
public class ProposalHierarchyChildWorkflowActionAuthorizer extends ProposalAuthorizer {

    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalHierarchyChildWorkflowActionAuthorizer.class);
    /**
     * @see org.kuali.kra.proposaldevelopment.document.authorizer.ProposalAuthorizer#isAuthorized(org.kuali.rice.kns.bo.user.UniversalUser, org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm)
     */
    public boolean isAuthorized(String username, ProposalTask task) {
        boolean authorized = true;
        ProposalDevelopmentDocument doc = task.getDocument();
        
        if( doc.getDevelopmentProposal().isChild() ) {
            try {
                WorkflowDocument parentWDoc  = KraServiceLocator.getService(ProposalHierarchyService.class).getParentWorkflowDocument(doc);
                if( task.getTaskName().equals(TaskName.PROPOSAL_HIERARCHY_CHILD_ACKNOWLEDGE_ACTION)) {
                    if( (!parentWDoc.isAcknowledgeRequested()) || parentWDoc.isInitiated() ) authorized = false; 
                } else if ( task.getTaskName().equals(TaskName.PROPOSAL_HIERARCHY_CHILD_WORKFLOW_ACTION) ) {
                    if( !parentWDoc.isInitiated()  ) authorized = false;
                }
            } catch (ProposalHierarchyException e) {
                LOG.error( String.format( "Could not find parent workflow document for proposal document number:%s, which claims to be a child. Returning false.",doc.getDocumentHeader().getDocumentNumber()),e);
                authorized = false;
            }
        }
        
         
        return authorized;
    }




}

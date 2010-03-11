/*
 * Copyright 2006-2009 The Kuali Foundation
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

import org.apache.commons.lang.ArrayUtils;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.document.authorization.ProposalTask;
import org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;

/**
 * This authorizer determines if the user has the permission
 * to reject a proposal.  You can reject if:
 * 1) The document is not at route level 0. ( initiated )
 * 2) You have an approval pending on the document.
 * 3) The document state is enroute.
 * 
 * 
 */
public class RejectProposalAuthorizer extends ProposalAuthorizer {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(RejectProposalAuthorizer.class);
    /**
     * @see org.kuali.kra.proposaldevelopment.document.authorizer.ProposalAuthorizer#isAuthorized(org.kuali.rice.kns.bo.user.UniversalUser, org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm)
     */
    public boolean isAuthorized(String username, ProposalTask task) {
        ProposalDevelopmentDocument doc = task.getDocument();
        KualiWorkflowDocument workDoc = doc.getDocumentHeader().getWorkflowDocument();
        String[] currentNodes;
        try {
            currentNodes = workDoc.getNodeNames();
        } catch ( WorkflowException we ) {
            LOG.error( String.format( "Workflow exception encountered getting current route node names for document %s", doc.getDocumentNumber()),we);
            throw new IllegalStateException( String.format( "Workflow exception encountered getting current route node names for document %s", doc.getDocumentNumber()),we );
        }
        return (!workDoc.getRouteHeader().isCompleteRequested()) && (! KraServiceLocator.getService(ProposalHierarchyService.class).isProposalOnInitialRouteNode(doc)) && (workDoc.isApprovalRequested()) && (workDoc.stateIsEnroute());
    }
    
    
}

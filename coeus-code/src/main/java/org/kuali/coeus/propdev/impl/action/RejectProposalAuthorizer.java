/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.action;

import org.kuali.coeus.propdev.impl.auth.task.ProposalAuthorizer;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.workflow.KcDocumentRejectionService;
import org.kuali.coeus.propdev.impl.auth.task.ProposalTask;
import org.kuali.rice.kew.api.WorkflowDocument;

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


    public boolean isAuthorized(String username, ProposalTask task) {
        ProposalDevelopmentDocument doc = task.getDocument();
        WorkflowDocument workDoc = doc.getDocumentHeader().getWorkflowDocument();
        return (!workDoc.isCompletionRequested()) && (! KcServiceLocator.getService(KcDocumentRejectionService.class).isDocumentOnInitialNode(doc)) && (workDoc.isApprovalRequested()) && (workDoc.isEnroute());
    }
    
    
}

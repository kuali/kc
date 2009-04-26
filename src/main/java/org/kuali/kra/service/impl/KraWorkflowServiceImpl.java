/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.service.impl;

import org.kuali.kra.service.KraWorkflowService;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kew.service.WorkflowInfo;
import org.kuali.rice.kns.bo.DocumentHeader;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;

/**
 * KRA Workflow Service Implementation.
 */
public class KraWorkflowServiceImpl implements KraWorkflowService {

    /**
     * @see org.kuali.kra.service.KraWorkflowService#hasWorkflowPermission(java.lang.String, org.kuali.rice.kns.document.Document)
     */
    public boolean hasWorkflowPermission(String username, Document doc) {
        boolean hasPermission = false;
        KualiWorkflowDocument workflowDoc = getWorkflowDocument(doc);
        if (workflowDoc != null && isInWorkflow(doc)) {
            Long routeHeaderId = workflowDoc.getRouteHeader().getRouteHeaderId();
            WorkflowInfo info = new WorkflowInfo();
            try {
                hasPermission = info.isUserAuthenticatedByRouteLog(routeHeaderId, username, true);
            }
            catch (WorkflowException e) {
            }
        }
        return hasPermission;
    }

    /**
     * @see org.kuali.kra.service.KraWorkflowService#isClosed(org.kuali.rice.kns.document.Document)
     */
    public boolean isClosed(Document doc) {
        boolean isClosed = false;
        KualiWorkflowDocument workflowDoc = getWorkflowDocument(doc);
        if (workflowDoc != null) {
            isClosed = workflowDoc.stateIsApproved() ||
                       workflowDoc.stateIsCanceled() ||
                       workflowDoc.stateIsDisapproved() ||
                       workflowDoc.stateIsException();
        }
        return isClosed;
    }

    /**
     * @see org.kuali.kra.service.KraWorkflowService#isEnRoute(org.kuali.rice.kns.document.Document)
     */
    public boolean isEnRoute(Document doc) {
        boolean isEnRoute = false;
        KualiWorkflowDocument workflowDoc = getWorkflowDocument(doc);
        if (workflowDoc != null) {
            isEnRoute = workflowDoc.stateIsEnroute();
        }
        return isEnRoute;
    }
   
    /**
     * @see org.kuali.kra.service.KraWorkflowService#isInWorkflow(org.kuali.rice.kns.document.Document)
     */
    public boolean isInWorkflow(Document doc) {
        boolean isInWorkflow = false;
        KualiWorkflowDocument workflowDoc = getWorkflowDocument(doc);
        if (workflowDoc != null) {
            isInWorkflow = !(workflowDoc.stateIsInitiated() ||
                             workflowDoc.stateIsSaved());
        }
        return isInWorkflow;
    }
    
    /**
     * Get the corresponding workflow document.  
     * @param doc the document
     * @return the workflow document or null if there is none
     */
    private KualiWorkflowDocument getWorkflowDocument(Document doc) {
        KualiWorkflowDocument workflowDocument = null;
        if (doc != null) {
            DocumentHeader header = doc.getDocumentHeader();
            if (header != null) {
                try {
                    workflowDocument = header.getWorkflowDocument();
                } 
                catch (RuntimeException ex) {
                    // do nothing; there is no workflow document
                }
            }
        }
        return workflowDocument;
    }
}

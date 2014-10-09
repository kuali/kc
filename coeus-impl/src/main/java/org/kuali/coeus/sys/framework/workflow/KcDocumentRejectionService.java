/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.sys.framework.workflow;

import org.kuali.rice.kew.api.WorkflowDocument;

/**
 * Encapsulates reject functionality.
 * 
 * Reject == return the document to the intial workflow node and generate an approval action for the initiator.
 * 
 */
public interface KcDocumentRejectionService {

    /**
     * Reject a document.  
     * @param document The document to reject.
     * @param reason An explanation of why the document has been rejected.
     * @param principalId The principal to reject the document as.
     * @param appDocStatus The application document status for the document.  Only applied if non-null.
     */
    void reject(WorkflowDocument document, String reason, String principalId, String appDocStatus);

    /**
     * Reject a document and send it back to a particular node.
     * @param document The document to reject
     * @param reason An explanation of why the document has been rejected.
     * @param principalId The principal to reject the document as.
     * @param appDocStatus The application document status for the document.  Only applied if non-null.
     * @param nodeName the node name to return to
     */
    void reject(WorkflowDocument document, String reason, String principalId, String appDocStatus, String nodeName);


    /**
     * Determine if the document is on it's initial node.  A document can be in workflow and on it's initial node
     * if it has been rejected ( returned to the initial node ).
     * @param document the document
     * @return if doc is on initial node
     */
    boolean isDocumentOnInitialNode(WorkflowDocument document);
}


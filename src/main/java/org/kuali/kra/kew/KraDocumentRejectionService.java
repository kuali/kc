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
package org.kuali.kra.kew;

import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.document.Document;

/**
 * Encapsulates reject functionality.
 * 
 * Reject == return the document to the intial workflow node and generate an approval action for the initiator.
 * 
 */
public interface KraDocumentRejectionService {

    /**
     * Reject a document.  
     * @param documentNumber The number of the document to reject.
     * @param reason An explanation of why the document has been rejected.
     * @param principalId The principal to reject the document as.
     * @param appDocStatus The application document status for the document.  Only applied if non-null.
     * @throws WorkflowException
     */
    void reject(Document document, String reason, String principalId, String appDocStatus);
    
    /**
     * Reject a document.  
     * @param documentNumber The number of the document to reject.
     * @param reason An explanation of why the document has been rejected.
     * @param principalId The principal to reject the document as.
     * @throws WorkflowException
     */
    void reject(Document document, String reason, String principalId);


    /**
     * Reject a document.  
     * @param documentNumber The number of the document to reject.
     * @param reason An explanation of why the document has been rejected.
     * @param principalId The principal to reject the document as.
     * @param appDocStatus The application document status for the document.  Only applied if non-null.
     * @throws WorkflowException
     */
    void reject(String documentNumber, String reason, String principalId, String appDocStatus);
    
    /**
     * Reject a document.  
     * @param documentNumber The number of the document to reject.
     * @param reason An explanation of why the document has been rejected.
     * @param principalId The principal to reject the document as.
     * @throws WorkflowException
     */
    void reject(String documentNumber, String reason, String principalId);
    
    /**
     * Get the name of the first workflow node for the document.
     * @param documentType The document type you want the initial node for.
     * @return The name of the initial workflow node.
     */
    String getWorkflowInitialNodeName(String documentType);
    
    /**
     * Determine if the document is on it's initial node.  A document can be in workflow and on it's initial node
     * if it has been rejected ( returned to the initial node ).
     * @param documentNumber
     * @return
     * @throws WorkflowException 
     */
    boolean isDocumentOnInitialNode(String documentNumber);

    /**
     * Determine if the document is on it's initial node.  A document can be in workflow and on it's initial node
     * if it has been rejected ( returned to the initial node ).
     * @param documentNumber
     * @return
     * @throws WorkflowException 
     */
    boolean isDocumentOnInitialNode(Document documentNumber);

    /**
     * Determine if the document is on a particular node.  
     * 
     * @param documentNumber
     * @param nodeName
     * @return
     */
    boolean isDocumentOnNode(String documentNumber, String nodeName);

    /**
     * Determine if the document is on a particular node.  
     * 
     * @param documentNumber
     * @param nodeName
     * @return
     */
    boolean isDocumentOnNode(Document documentNumber, String nodeName);
    
    /**
     * Reject a document and send it back to a particular node.  
     * @param document The document number to reject
     * @param reason An explanation of why the document has been rejected.
     * @param principalId The principal to reject the document as.
     * @param appDocStatus The application document status for the document.  Only applied if non-null.
     * @throws WorkflowException
     */
    void reject(Document document, String reason, String principalId, String appDocStatus, String nodeName);
    
}


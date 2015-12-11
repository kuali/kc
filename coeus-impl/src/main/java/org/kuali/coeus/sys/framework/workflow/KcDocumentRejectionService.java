/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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

    String getWorkflowInitialNodeName(String docTypeName);
}


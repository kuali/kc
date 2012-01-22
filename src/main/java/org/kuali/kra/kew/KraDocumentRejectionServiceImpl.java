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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.WorkflowDocumentFactory;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kew.doctype.bo.DocumentType;
import org.kuali.rice.kew.doctype.service.DocumentTypeService;
import org.kuali.rice.kew.service.KEWServiceLocator;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.workflow.service.WorkflowDocumentService;

public class KraDocumentRejectionServiceImpl implements KraDocumentRejectionService {

    private static final Log LOG = LogFactory.getLog(KraDocumentRejectionServiceImpl.class);
    private DocumentService documentService;
    private WorkflowDocumentService workflowDocumentService;
    
    public void setWorkflowDocumentService(WorkflowDocumentService workflowDocumentService) {
        this.workflowDocumentService = workflowDocumentService;
    }

    /**
     * @see org.kuali.kra.kew.KraDocumentRejectionService#getWorkflowInitialNodeName(java.lang.String)
     */
    public String getWorkflowInitialNodeName(String documentType) {
        DocumentTypeService documentTypeService = KEWServiceLocator.getDocumentTypeService();
        DocumentType proposalDevDocType = documentTypeService.findByName(documentType);
        return proposalDevDocType.getPrimaryProcess().getInitialRouteNode().getRouteNodeName();        
    }

    /**
     * @see org.kuali.kra.kew.KraDocumentRejectionService#reject(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public void reject(String documentNumber, String reason, String principalId, String appDocStatus) {
        try {
            if( LOG.isDebugEnabled() )
                LOG.debug( String.format( "Rejecting document:%s as %s with reason '%s'", documentNumber, principalId, reason ));
            WorkflowDocument workflowDocument = WorkflowDocumentFactory.loadDocument(principalId, documentNumber);
            workflowDocument.returnToPreviousNode(reason, getWorkflowInitialNodeName( workflowDocument.getDocumentTypeName()) );
            if( appDocStatus != null ) {
                if( LOG.isDebugEnabled() ) {
                    LOG.debug( String.format( "Setting application document status of document %s to %s", documentNumber, appDocStatus));
                }
                workflowDocument.setApplicationDocumentStatus( appDocStatus );
            }
        } catch ( Exception we ) {
            LOG.error( String.format( "Exception generated when trying to return document %s to initial route node.  Reason:%s", documentNumber, we.getMessage()) );
            throw new RuntimeException( String.format( "Exception generated when trying to return document %s to initial route node.  Reason:%s", documentNumber, we.getMessage()), we );
        }
    }

    public void reject(String documentNumber, String reason, String principalId) {
        reject( documentNumber, reason, principalId, null );
    }


    /**
     * @see org.kuali.kra.kew.KraDocumentRejectionService#reject(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public void reject(Document document, String reason, String principalId, String appDocStatus) {
        try {
            if( LOG.isDebugEnabled() )
                LOG.debug( String.format( "Rejecting document:%s as %s with reason '%s'", document.getDocumentNumber(), principalId, reason ));
            WorkflowDocument workflowDocument = WorkflowDocumentFactory.loadDocument( principalId, document.getDocumentHeader().getWorkflowDocument().getDocumentId() );
            workflowDocument.returnToPreviousNode(reason, getWorkflowInitialNodeName( workflowDocument.getDocumentTypeName()) );
            if( appDocStatus != null ) {
                if( LOG.isDebugEnabled() )
                    LOG.debug( String.format( "Setting application document status of document %s to %s", document.getDocumentNumber(), appDocStatus));
                //workflowDocument.setApplicationDocumentStatus( appDocStatus );
            }
        } catch ( Exception we ) {
            LOG.error( String.format( "Exception generated when trying to return document %s to initial route node.  Reason:%s", document.getDocumentNumber(), we.getMessage()) );
            throw new RuntimeException( String.format( "Exception generated when trying to return document %s to initial route node.  Reason:%s", document.getDocumentNumber(), we.getMessage()), we );
        }
    }

    public void reject(Document document, String reason, String principalId) {
        reject( document, reason, principalId, null );
    }
    
    /**
     * @see org.kuali.kra.kew.KraDocumentRejectionService#isDocumentOnInitialNode(org.kuali.rice.krad.document.Document)
     */
    public boolean isDocumentOnInitialNode(Document document)  {
        boolean ret = false;
        if (document!=null)
	        ret = isDocumentOnNode(document,getWorkflowInitialNodeName(document.getDocumentHeader().getWorkflowDocument().getDocumentTypeName()));
        return ret;
    }
    
    /**
     * @see org.kuali.kra.kew.KraDocumentRejectionService#isDocumentOnInitialNode(java.lang.String)
     */
    public boolean isDocumentOnInitialNode( String documentNumber ) {
        try {
            Document document = documentService.getByDocumentHeaderId(documentNumber);
            return isDocumentOnInitialNode( document );
        } catch ( WorkflowException we ) {
            LOG.error( String.format( "WorkflowException generated when trying to return document %s to initial route node.  Reason:%s", documentNumber, we.getMessage()) );
            throw new RuntimeException( String.format( "WorkflowException generated when trying to return document %s to initial route node.  Reason:%s", documentNumber, we.getMessage()), we );
        }
    }
    
    /**
     * @see org.kuali.kra.kew.KraDocumentRejectionService#isDocumentOnNode(org.kuali.rice.krad.document.Document, java.lang.String)
     */
    public boolean isDocumentOnNode(Document document,String nodeName) {
        if(document != null && StringUtils.isNotEmpty(nodeName)) {
            String currentRouteNodeNames = workflowDocumentService.getCurrentRouteNodeNames(document.getDocumentHeader().getWorkflowDocument());
            return StringUtils.contains(currentRouteNodeNames, nodeName);
        }
        
        return false;
    }   
    
    /**
     * @see org.kuali.kra.kew.KraDocumentRejectionService#isDocumentOnNode(java.lang.String, java.lang.String)
     */
    public boolean isDocumentOnNode(String documentNumber, String nodeName) {
        try {
            Document document = documentService.getByDocumentHeaderId(documentNumber);
            return isDocumentOnNode(document,nodeName);
        } catch (WorkflowException we) {
            LOG.error(String.format("WorkflowException generated when trying to determine if document %s is on %s node.  Reason:%s", nodeName, documentNumber, we.getMessage()));
            throw new RuntimeException(String.format("WorkflowException generated when trying determine if document %s is on %s route node.  Reason:%s", nodeName, documentNumber, we.getMessage()), we);
        }
        
    }
 
    /**
     * @see org.kuali.kra.kew.KraDocumentRejectionService#reject(org.kuali.rice.krad.document.Document, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public void reject(Document document, String reason, String principalId, String appDocStatus, String nodeName) {
        if( LOG.isDebugEnabled() ) {
            LOG.debug( String.format( "Rejecting document %s to node %s as %s with reason '%s'", document.getDocumentNumber(), nodeName, principalId, reason ));
        }
        WorkflowDocument workflowDocument = WorkflowDocumentFactory.loadDocument( principalId, document.getDocumentHeader().getWorkflowDocument().getDocumentId() );
        workflowDocument.returnToPreviousNode(reason, nodeName );
        if( appDocStatus != null ) {
            if( LOG.isDebugEnabled() ) {
                LOG.debug( String.format( "Setting application document status of document %s to %s", document.getDocumentNumber(), appDocStatus));
            }
            //workflowDocument.setApplicationDocumentStatus( appDocStatus );
        }
    }

    public DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
}

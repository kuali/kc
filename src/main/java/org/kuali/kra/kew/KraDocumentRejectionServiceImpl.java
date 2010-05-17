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

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.rice.kew.doctype.service.DocumentTypeService;
import org.kuali.rice.kew.dto.DocumentTypeDTO;
import org.kuali.rice.kew.dto.ProcessDTO;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kew.service.KEWServiceLocator;
import org.kuali.rice.kew.service.WorkflowDocument;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;

public class KraDocumentRejectionServiceImpl implements KraDocumentRejectionService {


    private DocumentService documentService;
    private static final Log LOG = LogFactory.getLog(KraDocumentRejectionServiceImpl.class);
    
    /**
     * @see org.kuali.kra.kew.KraDocumentRejectionService#getWorkflowInitialNodeName(java.lang.String)
     */
    public String getWorkflowInitialNodeName(String documentType) {
        DocumentTypeService documentTypeService = KEWServiceLocator.getDocumentTypeService();
        DocumentTypeDTO proposalDevDocType = documentTypeService.getDocumentTypeVO(documentType);
        ProcessDTO p = proposalDevDocType.getRoutePath().getPrimaryProcess();
        return p.getInitialRouteNode().getRouteNodeName();
    }

    /**
     * @see org.kuali.kra.kew.KraDocumentRejectionService#reject(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public void reject(String documentNumber, String reason, String principalId, String appDocStatus) {
        try {
            if( LOG.isDebugEnabled() )
                LOG.debug( String.format( "Rejecting document:%s as %s with reason '%s'", documentNumber, principalId, reason ));
            WorkflowDocument workflowDocument = new WorkflowDocument(principalId, Long.parseLong(documentNumber));
            workflowDocument.returnToPreviousNode(reason, getWorkflowInitialNodeName( workflowDocument.getDocumentType()) );
            if( appDocStatus != null ) {
                if( LOG.isDebugEnabled() )
                    LOG.debug( String.format( "Setting application document status of document %s to %s", documentNumber, appDocStatus));
                workflowDocument.updateAppDocStatus( appDocStatus );
            }
        } catch ( WorkflowException we ) {
            LOG.error( String.format( "WorkflowException generated when trying to return document %s to initial route node.  Reason:%s", documentNumber, we.getMessage()) );
            throw new RuntimeException( String.format( "WorkflowException generated when trying to return document %s to initial route node.  Reason:%s", documentNumber, we.getMessage()), we );
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
            WorkflowDocument workflowDocument = new WorkflowDocument( principalId, document.getDocumentHeader().getWorkflowDocument().getRouteHeaderId() );
            workflowDocument.returnToPreviousNode(reason, getWorkflowInitialNodeName( workflowDocument.getDocumentType()) );
            if( appDocStatus != null ) {
                if( LOG.isDebugEnabled() )
                    LOG.debug( String.format( "Setting application document status of document %s to %s", document.getDocumentNumber(), appDocStatus));
                //workflowDocument.updateAppDocStatus( appDocStatus );
            }
        } catch ( WorkflowException we ) {
            LOG.error( String.format( "WorkflowException generated when trying to return document %s to initial route node.  Reason:%s", document.getDocumentNumber(), we.getMessage()) );
            throw new RuntimeException( String.format( "WorkflowException generated when trying to return document %s to initial route node.  Reason:%s", document.getDocumentNumber(), we.getMessage()), we );
        }
    }

    public void reject(Document document, String reason, String principalId) {
        reject( document, reason, principalId, null );
    }

    
    
    
    public boolean isDocumentOnInitialNode(Document document)  {
        try {
            boolean ret = false;
            if( document != null && ArrayUtils.contains(document.getDocumentHeader().getWorkflowDocument().getNodeNames(), getWorkflowInitialNodeName(document.getDocumentHeader().getWorkflowDocument().getDocumentType()))) ret = true;
            return ret;
        } catch ( WorkflowException we ) {
            LOG.error( String.format( "WorkflowException generated when trying to determine if document %s is on initial node.  Reason:%s", document.getDocumentNumber(), we.getMessage()) );
            throw new RuntimeException( String.format( "WorkflowException generated when trying determine if document %s is on initial route node.  Reason:%s", document.getDocumentNumber(), we.getMessage()), we );
        }
    }
    
    public boolean isDocumentOnInitialNode( String documentNumber ) {
        try {
            Document document = documentService.getByDocumentHeaderId(documentNumber);
            return isDocumentOnInitialNode( document );
        } catch ( WorkflowException we ) {
            LOG.error( String.format( "WorkflowException generated when trying to return document %s to initial route node.  Reason:%s", documentNumber, we.getMessage()) );
            throw new RuntimeException( String.format( "WorkflowException generated when trying to return document %s to initial route node.  Reason:%s", documentNumber, we.getMessage()), we );
        }
    }

    public DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    
      
}

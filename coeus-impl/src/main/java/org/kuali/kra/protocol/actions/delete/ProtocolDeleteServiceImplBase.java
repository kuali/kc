/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
package org.kuali.kra.protocol.actions.delete;

import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

public abstract class ProtocolDeleteServiceImplBase implements ProtocolDeleteService {

    private DocumentService documentService;
    private BusinessObjectService businessObjectService;
    private ProtocolOnlineReviewService protocolOnlineReviewService;
    
    private static final String DELETE_FINALIZE_OLR_ANNOTATION = "Online Review finalized as part of withdraw action on protocol.";

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    
    /**
     * Set the business object service.
     * @param businessObjectService the business object service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * We never delete documents from the database.  Rather, we simply mark
     * it as deleted.
     * @throws WorkflowException
     * @param protocolDocument
     */
    public void delete(ProtocolDocumentBase protocolDocument) throws WorkflowException {
        protocolDocument.getProtocol().setProtocolStatusCode(getDeletedProtocolStatusCodeHook());
        protocolDocument.getProtocol().setActive(false);
        businessObjectService.save(protocolDocument);
        
        /*
         * By marking the protocol document as canceled, the protocol
         * is removed from the user's action list.
         */
        documentService.cancelDocument(protocolDocument, null);
        protocolOnlineReviewService.finalizeOnlineReviews(protocolDocument.getProtocol().getProtocolSubmission(), DELETE_FINALIZE_OLR_ANNOTATION);
    
    }

    protected abstract String getDeletedProtocolStatusCodeHook();

    public ProtocolOnlineReviewService getProtocolOnlineReviewService() {
        return protocolOnlineReviewService;
    }

    public void setProtocolOnlineReviewService(ProtocolOnlineReviewService protocolOnlineReviewService) {
        this.protocolOnlineReviewService = protocolOnlineReviewService;
    }

}

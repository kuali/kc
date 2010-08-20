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
package org.kuali.kra.irb.actions.defer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolVersionService;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.assignagenda.ProtocolAssignToAgendaService;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericActionBean;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericActionService;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kew.service.WorkflowDocument;
import org.kuali.rice.kim.service.IdentityManagementService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.util.KNSConstants;

/**
 * The ProtocolWithdrawService implementation.
 */
public class ProtocolDeferServiceImpl implements ProtocolDeferService {

    private static final Log LOG = LogFactory.getLog(ProtocolDeferServiceImpl.class);
    private DocumentService documentService;
    private BusinessObjectService businessObjectService;
    private ProtocolVersionService protocolVersionService;
    private ProtocolAssignToAgendaService protocolAssignToAgendaService;
    private IdentityManagementService identityManagementService;
    
    private ProtocolGenericActionService protocolGenericActionService;
    /**
     * Set the document service.
     * @param documentService
     */
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
    
    public void setProtocolAssignToAgendaService(ProtocolAssignToAgendaService protocolAssignToAgendaService) {
        this.protocolAssignToAgendaService = protocolAssignToAgendaService;
    }
    
    public void setProtocolVersionService(ProtocolVersionService protocolVersionService) {
        this.protocolVersionService = protocolVersionService;
    }
    
    public void setIdentityManagementService(IdentityManagementService identityManagementService) {
        this.identityManagementService = identityManagementService;
    }
    
    public void setProtocolGenericActionService(ProtocolGenericActionService protocolGenericActionService) {
        this.protocolGenericActionService = protocolGenericActionService;
    }

    /**
     * @see org.kuali.kra.irb.actions.withdraw.ProtocolWithdrawService#withdraw(org.kuali.kra.irb.Protocol, org.kuali.kra.irb.actions.withdraw.ProtocolWithdrawBean)
     */
    public ProtocolDocument defer(Protocol protocol, ProtocolGenericActionBean deferBean) throws Exception {
        protocolGenericActionService.defer(protocol, deferBean);
        
        cancelWorkflow(protocol);
        //need to cancel any outstanding review documents
        for (ProtocolOnlineReview review : protocol.getProtocolOnlineReviews()) {
            cancelWorkflow(review);
        }
        
        ProtocolDocument newProtocolDocument = protocolVersionService.versionProtocolDocument(protocol.getProtocolDocument());
        newProtocolDocument.getProtocol().setProtocolStatusCode(ProtocolStatus.DEFERRED);
        newProtocolDocument.getProtocol().setProtocolSubmission(null);
        newProtocolDocument.getProtocol().setApprovalDate(null);
        newProtocolDocument.getProtocol().setExpirationDate(null);
        newProtocolDocument.getProtocol().refreshReferenceObject("protocolStatus");
        documentService.saveDocument(newProtocolDocument);

        //if there is an assign to agenda protocol action, remove it.
        ProtocolAction assignToAgendaProtoclAction = this.protocolAssignToAgendaService.getAssignedToAgendaProtocolAction(newProtocolDocument.getProtocol());
        if (assignToAgendaProtoclAction != null) {
            newProtocolDocument.getProtocol().getProtocolActions().remove(assignToAgendaProtoclAction);
            businessObjectService.delete(assignToAgendaProtoclAction);
        }        
        newProtocolDocument.getProtocol().refreshReferenceObject("protocolStatus");
        documentService.saveDocument(newProtocolDocument);
        return newProtocolDocument;
    }
    
    /**
     * By canceling the protocol in workflow, we are preventing it from being
     * reviewed by the IRB office.  A user will then be able to continue editing
     * the protocol in order to submit it again at a later time.
     * @param protocol
     * @throws WorkflowException
     */
    private void cancelWorkflow(Protocol protocol) throws WorkflowException {
        documentService.cancelDocument(protocol.getProtocolDocument(), null);
    }
  
    private void cancelWorkflow(ProtocolOnlineReview review) {
        final String principalId = identityManagementService.getPrincipalByPrincipalName(KNSConstants.SYSTEM_USER).getPrincipalId();
        try {
            WorkflowDocument workflowDocument = new WorkflowDocument(principalId, review.getProtocolOnlineReviewDocument().getDocumentHeader().getWorkflowDocument().getRouteHeaderId());
            workflowDocument.superUserCancel("Review cancelled - protocol has been withdrawn.");
        }
        catch (WorkflowException e) {
           LOG.error(String.format("WorkflowException generated when cancel called on protocolOnlineReview document number:%s", review.getProtocolOnlineReviewDocument().getDocumentNumber(), e));
           throw new RuntimeException(String.format("WorkflowException generated when cancel called on protocolOnlineReview document number:%s", review.getProtocolOnlineReviewDocument().getDocumentNumber(), e));
        }
    }
}

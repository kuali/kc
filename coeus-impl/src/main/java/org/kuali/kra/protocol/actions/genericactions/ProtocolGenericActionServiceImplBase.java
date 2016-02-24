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
package org.kuali.kra.protocol.actions.genericactions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.ProtocolVersionService;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.ProtocolActionRequestService;
import org.kuali.kra.protocol.actions.assignagenda.ProtocolAssignToAgendaService;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionsCorrespondenceBase;
import org.kuali.kra.protocol.actions.submit.ProtocolActionService;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewService;
import org.kuali.rice.kew.actiontaken.ActionTakenValue;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

import java.sql.Timestamp;

/**
 * This class handles the generic actions that can be made to a protocol.  A generic action contain a comment, action date, and a 
 * state change.
 */
public abstract class ProtocolGenericActionServiceImplBase implements ProtocolGenericActionService {
    private static final Log LOG = LogFactory.getLog(ProtocolGenericActionServiceImplBase.class);
    
    private static final String PROTOCOL_SUBMISSION = "protocolSubmission";
    private ProtocolActionService protocolActionService;
    private DocumentService documentService;
      
    private ProtocolActionRequestService protocolActionRequestService;
    
    private ProtocolOnlineReviewService protocolOnlineReviewService;
    private ProtocolVersionService protocolVersionService;
    private ProtocolAssignToAgendaService protocolAssignToAgendaService;
    private BusinessObjectService businessObjectService;

    /**
     * Performs the generic action, which includes a state change, action date, and a comment, that's it!
     * @param protocol
     * @param actionBean
     * @param protocolActionType
     * @param newProtocolStatus
     * @throws Exception
     */
    protected void performGenericAction(ProtocolBase protocol, ProtocolGenericActionBean actionBean, String protocolActionType, String newProtocolStatus) throws Exception {
        
        ProtocolActionBase protocolAction = createProtocolActionAndAttach(protocol, actionBean, protocolActionType);
        
        protocolActionService.updateProtocolStatus(protocolAction, protocol);
        protocol.setProtocolStatusCode(newProtocolStatus);
        protocol.refreshReferenceObject("protocolStatus");
        documentService.saveDocument(protocol.getProtocolDocument());
                 
        //createCorrespondenceAndAttach(protocol, protocolActionType);
    }

    protected ProtocolActionBase createProtocolActionAndAttach(ProtocolBase protocol, ProtocolGenericActionBean actionBean, String protocolActionType) {
        protocol.refreshReferenceObject("protocolSubmission");
        ProtocolActionBase protocolAction = getNewProtocolActionInstanceHook(protocol, protocol.getProtocolSubmission(), protocolActionType);
        protocolAction.setComments(actionBean.getComments());
        protocolAction.setActionDate(new Timestamp(actionBean.getActionDate().getTime()));
        protocol.getProtocolActions().add(protocolAction);
        
        return protocolAction;
    }
    
    protected abstract ProtocolActionBase getNewProtocolActionInstanceHook(ProtocolBase protocol, ProtocolSubmissionBase submission, String protocolActionType);
    
    protected abstract ProtocolActionsCorrespondenceBase getNewProtocolActionsCorrespondenceHook(String protocolActionType);

    protected void performDisapprove(ProtocolBase protocol) throws Exception {
        if (protocol.getProtocolDocument() != null) {
            WorkflowDocument currentWorkflowDocument = protocol.getProtocolDocument().getDocumentHeader().getWorkflowDocument();
            if (currentWorkflowDocument != null) {
                currentWorkflowDocument.disapprove("ProtocolBase document disapproved after committee decision");
            }
        }        
    }

    protected ProtocolDocumentBase getReturnedVersionedDocument(ProtocolBase protocol) throws Exception {
        documentService.cancelDocument(protocol.getProtocolDocument(), "ProtocolBase document cancelled - protocol has been returned for revisions.");        
        return getVersionedDocument(protocol);
    }

    @Override
    public void recordDisapprovedInRoutingActionAndUpdateStatuses(ProtocolBase protocol, ActionTakenValue latestCurrentActionTakenVal) {
        // add the action to the action history
        ProtocolActionBase protocolAction = getNewDisapprovedInRoutingProtocolActionInstanceHook(protocol);
        protocolAction.setComments(latestCurrentActionTakenVal.getAnnotation());
        protocolAction.setActionDate(latestCurrentActionTakenVal.getActionDate());
        protocol.getProtocolActions().add(protocolAction);
        // update the statuses and persist the protocol
        protocol.setProtocolStatusCode(getDisapprovedProtocolStatusCodeHook());
        protocol.refreshReferenceObject(Constants.PROPERTY_PROTOCOL_STATUS);
        protocol.getProtocolSubmission().setSubmissionStatusCode(getProtocolSubmissionStatusRejectedInRoutingCodeHook());
        protocol.refreshReferenceObject(PROTOCOL_SUBMISSION);
        this.getBusinessObjectService().save(protocol);
        try {
            getProtocolActionRequestService().rejectedInRouting(protocol);
        }catch(Exception ex) {
            LOG.error(ex.getMessage());
        }
    }
    
    protected abstract String getProtocolSubmissionStatusRejectedInRoutingCodeHook();

    protected abstract String getDisapprovedProtocolStatusCodeHook();

    protected abstract ProtocolActionBase getNewDisapprovedInRoutingProtocolActionInstanceHook(ProtocolBase protocol);


    @Override
    public ProtocolDocumentBase versionAfterDisapprovalInRouting(ProtocolBase oldProtocol) throws Exception {
        // the new document version will be persisted along with the new version of the old protocol instance
        ProtocolDocumentBase newDocument = getVersionedDocument(oldProtocol);
        
        // set the 'pending/in progress' status for the new protocol 
        ProtocolBase newProtocol = newDocument.getProtocol();
        newProtocol.setProtocolStatusCode(getProtocolPendingInProgressStatusCodeHook());
        newProtocol.refreshReferenceObject(Constants.PROPERTY_PROTOCOL_STATUS);
        
        // set the submission status to disapproved in routing
        newProtocol.getProtocolSubmission().setSubmissionStatusCode(getProtocolSubmissionStatusRejectedInRoutingCodeHook());
        newProtocol.refreshReferenceObject(PROTOCOL_SUBMISSION);
        // finally save the protocol (and its submission)
        this.getBusinessObjectService().save(newProtocol);
        documentService.saveDocument(newDocument);
        
        return newDocument;
    }
    

    protected abstract String getProtocolPendingInProgressStatusCodeHook();


    
    protected ProtocolDocumentBase getVersionedDocument(ProtocolBase protocol) throws Exception {
        ProtocolDocumentBase newDocument = protocolVersionService.versionProtocolDocument(protocol.getProtocolDocument());
        newDocument.getProtocol().setProtocolSubmission(null);
        if(!protocol.isAmendment()) {
            newDocument.getProtocol().setApprovalDate(null);
            newDocument.getProtocol().setLastApprovalDate(null);
            newDocument.getProtocol().setExpirationDate(null);
        }        
        newDocument.getProtocol().refreshReferenceObject(Constants.PROPERTY_PROTOCOL_STATUS); 
        newDocument.getProtocol().refreshReferenceObject(PROTOCOL_SUBMISSION);
        documentService.saveDocument(newDocument);
        
        return newDocument;
    }
    
    protected abstract String getRecallProtocolActionTypeCodeHook();
    
    public void recall(ProtocolBase protocol) {
        ProtocolActionBase protocolAction = getNewProtocolActionInstanceHook(protocol, null, getRecallProtocolActionTypeCodeHook());
        protocolAction.setComments("Recalled in Routing");
        protocol.getProtocolActions().add(protocolAction);
        getProtocolActionService().updateProtocolStatus(protocolAction, protocol);
        try {
            getProtocolActionRequestService().recalledInRouting(protocol);
        }catch(Exception ex) {
            LOG.error(ex.getMessage());
        }
    }

    public void setProtocolActionService(ProtocolActionService protocolActionService) {
        this.protocolActionService = protocolActionService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    public void setProtocolOnlineReviewService(ProtocolOnlineReviewService protocolOnlineReviewService) {
        this.protocolOnlineReviewService = protocolOnlineReviewService;
    }

    public void setProtocolVersionService(ProtocolVersionService protocolVersionService) {
        this.protocolVersionService = protocolVersionService;
    }

    public void setProtocolAssignToAgendaService(ProtocolAssignToAgendaService protocolAssignToAgendaService) {
        this.protocolAssignToAgendaService = protocolAssignToAgendaService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    protected ProtocolActionService getProtocolActionService() {
        return protocolActionService;
    }

    protected DocumentService getDocumentService() {
        return documentService;
    }

    protected ProtocolOnlineReviewService getProtocolOnlineReviewService() {
        return protocolOnlineReviewService;
    }

    protected ProtocolVersionService getProtocolVersionService() {
        return protocolVersionService;
    }

    protected ProtocolAssignToAgendaService getProtocolAssignToAgendaService() {
        return protocolAssignToAgendaService;
    }

    protected BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public ProtocolActionRequestService getProtocolActionRequestService() {
        return protocolActionRequestService;
    }

    public void setProtocolActionRequestService(ProtocolActionRequestService protocolActionRequestService) {
        this.protocolActionRequestService = protocolActionRequestService;
    }

}

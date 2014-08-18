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
package org.kuali.kra.protocol.actions.approve;

import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionsCorrespondenceBase;
import org.kuali.kra.protocol.actions.submit.ProtocolActionService;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewService;
import org.kuali.rice.krad.service.DocumentService;

import java.sql.Timestamp;

/**
 * Approves a protocol, either for a full, expedited, or response protocol submission.
 */
public abstract class ProtocolApproveServiceImplBase implements ProtocolApproveService {
    
    protected static final String RESPONSE_APPROVAL_FINALIZE_OLR_ANNOTATION = "Online Review finalized as part of response approval action on protocol.";
    private static final String ADMIN_APPROVAL_FINALIZE_OLR_ANNOTATION = "Online Review finalized as part of administrative approval action on protocol.";
    
    private DocumentService documentService;
    private ProtocolActionService protocolActionService;
         

    protected ProtocolOnlineReviewService protocolOnlineReviewService;
    
    @Override
    public void grantResponseApproval(ProtocolBase protocol, ProtocolApproveBean actionBean) throws Exception {
        generateProtocolActionAndAttach(protocol, actionBean, getProtocolActionTypeCodeForResponseApprovalHook());
        
        if (protocol.getApprovalDate() == null) {
            protocol.setApprovalDate(actionBean.getApprovalDate());
        }
        if (protocol.isRenewal() || protocol.isAmendment()) {
            protocol.setLastApprovalDate(actionBean.getApprovalDate());
        }
        protocol.setExpirationDate(actionBean.getExpirationDate());
        
        finalizeReviewsAndSave(protocol, getProtocolActionTypeCodeForResponseApprovalHook(), RESPONSE_APPROVAL_FINALIZE_OLR_ANNOTATION);
        
        protocol.getProtocolDocument().getDocumentHeader().getWorkflowDocument().approve(actionBean.getComments());
    }   
    
    protected abstract String getProtocolActionTypeCodeForResponseApprovalHook();

    
    
    @Override
    public void grantAdminApproval(ProtocolBase protocol, ProtocolApproveBean actionBean) throws Exception {
        generateProtocolActionAndAttach(protocol, actionBean, getProtocolActionTypeCodeForAdminApprovalHook());
        
        if (protocol.getApprovalDate() == null) {
            protocol.setApprovalDate(actionBean.getApprovalDate());
        }
        if (protocol.isRenewal() || protocol.isAmendment()) {
            protocol.setLastApprovalDate(actionBean.getApprovalDate());
        }
        protocol.setExpirationDate(actionBean.getExpirationDate());
        
        finalizeReviewsAndSave(protocol, getProtocolActionTypeCodeForAdminApprovalHook(), ADMIN_APPROVAL_FINALIZE_OLR_ANNOTATION);
        
        protocol.getProtocolDocument().getDocumentHeader().getWorkflowDocument().approve(actionBean.getComments());
    }


    protected abstract String getProtocolActionTypeCodeForAdminApprovalHook();



    protected void generateProtocolActionAndAttach(ProtocolBase protocol, ProtocolApproveBean actionBean, String protocolActionTypeCode) {
        ProtocolActionBase protocolAction = getNewProtocolActionInstanceHook(protocol, protocol.getProtocolSubmission(), protocolActionTypeCode);
        protocolAction.setComments(actionBean.getComments());
        protocolAction.setActionDate(new Timestamp(actionBean.getActionDate().getTime()));
        protocolAction.setSubmissionIdFk(protocol.getLastProtocolAction().getSubmissionIdFk());
        protocolAction.setSubmissionNumber(protocol.getLastProtocolAction().getSubmissionNumber());
        protocol.getProtocolActions().add(protocolAction);
        protocolActionService.updateProtocolStatus(protocolAction, protocol);
    }
    
    
    protected abstract ProtocolActionBase getNewProtocolActionInstanceHook(ProtocolBase protocol, ProtocolSubmissionBase submission, String protocolActionTypeCode);
    
    protected abstract ProtocolActionsCorrespondenceBase getNewProtocolActionsCorrespondence(String protocolActionTypeCode);


    protected void finalizeReviewsAndSave(ProtocolBase protocol, String protocolActionTypeCode, String reviewAnnotation) throws Exception {
        protocol.refreshReferenceObject("protocolStatus");

        protocolOnlineReviewService.finalizeOnlineReviews(protocol.getProtocolSubmission(), reviewAnnotation);
        
        documentService.saveDocument(protocol.getProtocolDocument());
         
    }
    
    
    
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    public void setProtocolActionService(ProtocolActionService protocolActionService) {
        this.protocolActionService = protocolActionService;
    }

    public ProtocolOnlineReviewService getProtocolOnlineReviewService() {
        return protocolOnlineReviewService;
    }

    public void setProtocolOnlineReviewService(ProtocolOnlineReviewService protocolOnlineReviewService) {
        this.protocolOnlineReviewService = protocolOnlineReviewService;
    }

    protected DocumentService getDocumentService() {
        return documentService;
    }

    protected ProtocolActionService getProtocolActionService() {
        return protocolActionService;
    }
    
}

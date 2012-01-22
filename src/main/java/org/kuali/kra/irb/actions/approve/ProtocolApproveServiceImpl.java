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
package org.kuali.kra.irb.actions.approve;

import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.correspondence.ProtocolActionCorrespondenceGenerationService;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericCorrespondence;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.DocumentService;

/**
 * Approves a protocol, either for a full, expedited, or response protocol submission.
 */
public class ProtocolApproveServiceImpl implements ProtocolApproveService {
    
    private static final String FULL_APPROVAL_FINALIZE_OLR_ANNOTATION = "Online Review finalized as part of full approval action on protocol.";
    private static final String EXPEDITED_APPROVAL_FINALIZE_OLR_ANNOTATION = "Online Review finalized as part of expedited approval action on protocol.";
    private static final String RESPONSE_APPROVAL_FINALIZE_OLR_ANNOTATION = "Online Review finalized as part of response approval action on protocol.";
    
    private DocumentService documentService;
    private ProtocolActionService protocolActionService;
    private ProtocolActionCorrespondenceGenerationService protocolActionCorrespondenceGenerationService;
    private ProtocolOnlineReviewService protocolOnlineReviewService;
    private ParameterService parameterService;

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.actions.approve.ProtocolApproveService#grantFullApproval(org.kuali.kra.irb.Protocol, 
     *      org.kuali.kra.irb.actions.approve.ProtocolApproveBean)
     */
    public void grantFullApproval(Protocol protocol, ProtocolApproveBean actionBean) throws Exception {
        generateProtocolActionAndAttach(protocol, actionBean, ProtocolActionType.APPROVED);   

        if (protocol.getApprovalDate() == null) {
            protocol.setApprovalDate(actionBean.getApprovalDate());
        }
        if (protocol.isRenewal()) {
            protocol.setLastApprovalDate(actionBean.getApprovalDate());
        }
        String exemptProtocolTypeCode = parameterService.getParameterValueAsString(ProtocolDocument.class, Constants.PROTOCOL_TYPE_CODE_EXEMPT);
        if (!StringUtils.equals(exemptProtocolTypeCode, protocol.getProtocolTypeCode())) {
            protocol.setExpirationDate(actionBean.getExpirationDate());
        }
        
        finalizeReviewsAndSave(protocol, ProtocolActionType.APPROVED, FULL_APPROVAL_FINALIZE_OLR_ANNOTATION);
    }  

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.actions.approve.ProtocolApproveService#grantExpeditedApproval(org.kuali.kra.irb.Protocol, 
     *      org.kuali.kra.irb.actions.approve.ProtocolApproveBean)
     */
    public void grantExpeditedApproval(Protocol protocol, ProtocolApproveBean actionBean) throws Exception {
        generateProtocolActionAndAttach(protocol, actionBean, ProtocolActionType.EXPEDITE_APPROVAL);
        
        protocol.setApprovalDate(actionBean.getApprovalDate());
        protocol.setExpirationDate(actionBean.getExpirationDate());
        
        finalizeReviewsAndSave(protocol, ProtocolActionType.EXPEDITE_APPROVAL, EXPEDITED_APPROVAL_FINALIZE_OLR_ANNOTATION);
        
        protocol.getProtocolDocument().getDocumentHeader().getWorkflowDocument().approve(actionBean.getComments());
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.actions.approve.ProtocolApproveService#grantResponseApproval(org.kuali.kra.irb.Protocol, 
     *      org.kuali.kra.irb.actions.approve.ProtocolApproveBean)
     */
    public void grantResponseApproval(Protocol protocol, ProtocolApproveBean actionBean) throws Exception {
        generateProtocolActionAndAttach(protocol, actionBean, ProtocolActionType.RESPONSE_APPROVAL);
        
        if (protocol.getApprovalDate() == null) {
            protocol.setApprovalDate(actionBean.getApprovalDate());
        }
        if (protocol.isRenewal()) {
            protocol.setLastApprovalDate(actionBean.getApprovalDate());
        }
        protocol.setExpirationDate(actionBean.getExpirationDate());
        
        finalizeReviewsAndSave(protocol, ProtocolActionType.APPROVED, RESPONSE_APPROVAL_FINALIZE_OLR_ANNOTATION);
        
        protocol.getProtocolDocument().getDocumentHeader().getWorkflowDocument().approve(actionBean.getComments());
    }
    
    private void generateProtocolActionAndAttach(Protocol protocol, ProtocolApproveBean actionBean, String protocolActionTypeCode) {
        ProtocolAction protocolAction = new ProtocolAction(protocol, null, protocolActionTypeCode);
        protocolAction.setComments(actionBean.getComments());
        protocolAction.setActionDate(new Timestamp(actionBean.getActionDate().getTime()));
        protocolAction.setSubmissionIdFk(protocol.getLastProtocolAction().getSubmissionIdFk());
        protocolAction.setSubmissionNumber(protocol.getLastProtocolAction().getSubmissionNumber());
        protocol.getProtocolActions().add(protocolAction);
        protocolActionService.updateProtocolStatus(protocolAction, protocol);
    }
    
    private void finalizeReviewsAndSave(Protocol protocol, String protocolActionTypeCode, String reviewAnnotation) throws Exception {
        protocol.refreshReferenceObject("protocolStatus");
        protocolOnlineReviewService.finalizeOnlineReviews(protocol.getProtocolSubmission(), reviewAnnotation);
        documentService.saveDocument(protocol.getProtocolDocument());
        
        ProtocolGenericCorrespondence correspondence = new ProtocolGenericCorrespondence(protocolActionTypeCode);
        correspondence.setPrintableBusinessObject(protocol);
        correspondence.setProtocol(protocol);
        protocolActionCorrespondenceGenerationService.generateCorrespondenceDocumentAndAttach(correspondence);
    }
    
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    public void setProtocolActionService(ProtocolActionService protocolActionService) {
        this.protocolActionService = protocolActionService;
    }
    
    public void setProtocolActionCorrespondenceGenerationService(ProtocolActionCorrespondenceGenerationService protocolActionCorrespondenceGenerationService) {
        this.protocolActionCorrespondenceGenerationService = protocolActionCorrespondenceGenerationService;
    }

    public ProtocolOnlineReviewService getProtocolOnlineReviewService() {
        return protocolOnlineReviewService;
    }

    public void setProtocolOnlineReviewService(ProtocolOnlineReviewService protocolOnlineReviewService) {
        this.protocolOnlineReviewService = protocolOnlineReviewService;
    } 

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
}
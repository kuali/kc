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
package org.kuali.kra.protocol.actions.approve;

import java.sql.Timestamp;

import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.actions.ProtocolAction;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionsCorrespondence;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionCorrespondenceGenerationService;
import org.kuali.kra.protocol.actions.submit.ProtocolActionService;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.DocumentService;

/**
 * Approves a protocol, either for a full, expedited, or response protocol submission.
 */
public abstract class ProtocolApproveServiceImpl implements ProtocolApproveService {
    
// TODO *********commented the code below during IACUC refactoring*********     
//    private static final String FULL_APPROVAL_FINALIZE_OLR_ANNOTATION = "Online Review finalized as part of full approval action on protocol.";
//    private static final String EXPEDITED_APPROVAL_FINALIZE_OLR_ANNOTATION = "Online Review finalized as part of expedited approval action on protocol.";
    private static final String RESPONSE_APPROVAL_FINALIZE_OLR_ANNOTATION = "Online Review finalized as part of response approval action on protocol.";
    private static final String ADMIN_APPROVAL_FINALIZE_OLR_ANNOTATION = "Online Review finalized as part of administrative approval action on protocol.";
    
    private DocumentService documentService;
    private ProtocolActionService protocolActionService;
         
    private ProtocolActionCorrespondenceGenerationService protocolActionCorrespondenceGenerationService;
    
    protected ProtocolOnlineReviewService protocolOnlineReviewService;

// TODO *********commented the code below during IACUC refactoring*********     
//    protected ParameterService parameterService;


// TODO *********commented the code below during IACUC refactoring********* 
// This method has been delegated to the subclasses    
//    /**
//     * {@inheritDoc}
//     * @see org.kuali.kra.protocol.actions.approve.ProtocolApproveService#grantFullApproval(org.kuali.kra.protocol.Protocol, 
//     *      org.kuali.kra.protocol.actions.approve.ProtocolApproveBean)
//     */
//    public void grantFullApproval(Protocol protocol, ProtocolApproveBean actionBean) throws Exception {
//        generateProtocolActionAndAttach(protocol, actionBean, ProtocolActionType.APPROVED);   
//
//        if (protocol.getApprovalDate() == null) {
//            protocol.setApprovalDate(actionBean.getApprovalDate());
//        }
//        if (protocol.isRenewal()) {
//            protocol.setLastApprovalDate(actionBean.getApprovalDate());
//        }
//        String exemptProtocolTypeCode = parameterService.getParameterValueAsString(ProtocolDocument.class, Constants.PROTOCOL_TYPE_CODE_EXEMPT);
//        if (!StringUtils.equals(exemptProtocolTypeCode, protocol.getProtocolTypeCode())) {
//            protocol.setExpirationDate(actionBean.getExpirationDate());
//        }
//        
//        finalizeReviewsAndSave(protocol, ProtocolActionType.APPROVED, FULL_APPROVAL_FINALIZE_OLR_ANNOTATION);
//    }  

    
// TODO *********commented the code below during IACUC refactoring********* 
// This method does not seem relevant for IACUC, so push it down only to IRB and declare it only in the IRB service interface
//    /**
//     * {@inheritDoc}
//     * @see org.kuali.kra.protocol.actions.approve.ProtocolApproveService#grantExpeditedApproval(org.kuali.kra.protocol.Protocol, 
//     *      org.kuali.kra.protocol.actions.approve.ProtocolApproveBean)
//     */
//    public void grantExpeditedApproval(Protocol protocol, ProtocolApproveBean actionBean) throws Exception {
//        generateProtocolActionAndAttach(protocol, actionBean, ProtocolActionType.EXPEDITE_APPROVAL);
//        
//        protocol.setApprovalDate(actionBean.getApprovalDate());
//        protocol.setExpirationDate(actionBean.getExpirationDate());
//        if (protocol.isRenewal()) {
//            protocol.setLastApprovalDate(actionBean.getApprovalDate());
//        }
//        finalizeReviewsAndSave(protocol, ProtocolActionType.EXPEDITE_APPROVAL, EXPEDITED_APPROVAL_FINALIZE_OLR_ANNOTATION);
//        
//        protocol.getProtocolDocument().getDocumentHeader().getWorkflowDocument().approve(actionBean.getComments());
//    }
//
    
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.protocol.actions.approve.ProtocolApproveService#grantResponseApproval(org.kuali.kra.protocol.Protocol, 
     *      org.kuali.kra.protocol.actions.approve.ProtocolApproveBean)
     */
    public void grantResponseApproval(Protocol protocol, ProtocolApproveBean actionBean) throws Exception {
        generateProtocolActionAndAttach(protocol, actionBean, getProtocolActionTypeCodeForResponseApprovalHook());
        
        if (protocol.getApprovalDate() == null) {
            protocol.setApprovalDate(actionBean.getApprovalDate());
        }
        if (protocol.isRenewal()) {
            protocol.setLastApprovalDate(actionBean.getApprovalDate());
        }
        protocol.setExpirationDate(actionBean.getExpirationDate());
        
        finalizeReviewsAndSave(protocol, getProtocolActionTypeCodeForResponseApprovalHook(), RESPONSE_APPROVAL_FINALIZE_OLR_ANNOTATION);
        
        protocol.getProtocolDocument().getDocumentHeader().getWorkflowDocument().approve(actionBean.getComments());
    }
    
    
    
    protected abstract String getProtocolActionTypeCodeForResponseApprovalHook();

    
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.protocol.actions.approve.ProtocolApproveService#grantAdminApproval(org.kuali.kra.protocol.Protocol, 
     *      org.kuali.kra.protocol.actions.approve.ProtocolApproveBean)
     */
    public void grantAdminApproval(Protocol protocol, ProtocolApproveBean actionBean) throws Exception {
        generateProtocolActionAndAttach(protocol, actionBean, getProtocolActionTypeCodeForAdminApprovalHook());
        
        if (protocol.getApprovalDate() == null) {
            protocol.setApprovalDate(actionBean.getApprovalDate());
        }
        if (protocol.isRenewal()) {
            protocol.setLastApprovalDate(actionBean.getApprovalDate());
        }
        protocol.setExpirationDate(actionBean.getExpirationDate());
        
        finalizeReviewsAndSave(protocol, getProtocolActionTypeCodeForAdminApprovalHook(), ADMIN_APPROVAL_FINALIZE_OLR_ANNOTATION);
        
        protocol.getProtocolDocument().getDocumentHeader().getWorkflowDocument().approve(actionBean.getComments());
    }


    protected abstract String getProtocolActionTypeCodeForAdminApprovalHook();



    protected void generateProtocolActionAndAttach(Protocol protocol, ProtocolApproveBean actionBean, String protocolActionTypeCode) {
        ProtocolAction protocolAction = getNewProtocolActionInstanceHook(protocol, null, protocolActionTypeCode);
        protocolAction.setComments(actionBean.getComments());
        protocolAction.setActionDate(new Timestamp(actionBean.getActionDate().getTime()));
        protocolAction.setSubmissionIdFk(protocol.getLastProtocolAction().getSubmissionIdFk());
        protocolAction.setSubmissionNumber(protocol.getLastProtocolAction().getSubmissionNumber());
        protocol.getProtocolActions().add(protocolAction);
        protocolActionService.updateProtocolStatus(protocolAction, protocol);
    }
    
    
    protected abstract ProtocolAction getNewProtocolActionInstanceHook(Protocol protocol, Object object, String protocolActionTypeCode);
    
    protected abstract ProtocolActionsCorrespondence getNewProtocolActionsCorrespondence(String protocolActionTypeCode);


    protected void finalizeReviewsAndSave(Protocol protocol, String protocolActionTypeCode, String reviewAnnotation) throws Exception {
        protocol.refreshReferenceObject("protocolStatus");

        protocolOnlineReviewService.finalizeOnlineReviews(protocol.getProtocolSubmission(), reviewAnnotation);
        
        documentService.saveDocument(protocol.getProtocolDocument());
         
        ProtocolActionsCorrespondence correspondence = getNewProtocolActionsCorrespondence(protocolActionTypeCode);
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



    protected DocumentService getDocumentService() {
        return documentService;
    }



    protected ProtocolActionService getProtocolActionService() {
        return protocolActionService;
    }



    protected ProtocolActionCorrespondenceGenerationService getProtocolActionCorrespondenceGenerationService() {
        return protocolActionCorrespondenceGenerationService;
    } 

    
//    public void setParameterService(ParameterService parameterService) {
//        this.parameterService = parameterService;
//    }
    
}
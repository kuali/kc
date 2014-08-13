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
package org.kuali.kra.irb.actions.approve;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericCorrespondence;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.approve.ProtocolApproveServiceImplBase;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionsCorrespondenceBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

/**
 * Approves a protocol, either for a full, expedited, or response protocol submission.
 */
public class ProtocolApproveServiceImpl extends ProtocolApproveServiceImplBase implements ProtocolApproveService {
    
    private static final String FULL_APPROVAL_FINALIZE_OLR_ANNOTATION = "Online Review finalized as part of full approval action on protocol.";
    private static final String EXPEDITED_APPROVAL_FINALIZE_OLR_ANNOTATION = "Online Review finalized as part of expedited approval action on protocol.";
    
    private ParameterService parameterService;

    @Override
    public void grantFullApproval(ProtocolBase protocol, org.kuali.kra.protocol.actions.approve.ProtocolApproveBean actionBean) throws Exception {
        generateProtocolActionAndAttach(protocol, actionBean, ProtocolActionType.APPROVED);   

        if (protocol.getApprovalDate() == null) {
            protocol.setApprovalDate(actionBean.getApprovalDate());
        }
        if (protocol.isRenewal() || protocol.isAmendment()) {
            protocol.setLastApprovalDate(actionBean.getApprovalDate());
        }
        String exemptProtocolTypeCode = parameterService.getParameterValueAsString(ProtocolDocument.class, Constants.PROTOCOL_TYPE_CODE_EXEMPT);
        if (!StringUtils.equals(exemptProtocolTypeCode, protocol.getProtocolTypeCode())) {
            protocol.setExpirationDate(actionBean.getExpirationDate());
        }
        
        finalizeReviewsAndSave(protocol, ProtocolActionType.APPROVED, FULL_APPROVAL_FINALIZE_OLR_ANNOTATION);
    }  

    @Override
    public void grantExpeditedApproval(Protocol protocol, ProtocolApproveBean actionBean) throws Exception {
        generateProtocolActionAndAttach(protocol, actionBean, ProtocolActionType.EXPEDITE_APPROVAL);
        
        protocol.setApprovalDate(actionBean.getApprovalDate());
        protocol.setExpirationDate(actionBean.getExpirationDate());
        if (protocol.isRenewal() || protocol.isAmendment()) {
            protocol.setLastApprovalDate(actionBean.getApprovalDate());
        }
        finalizeReviewsAndSave(protocol, ProtocolActionType.EXPEDITE_APPROVAL, EXPEDITED_APPROVAL_FINALIZE_OLR_ANNOTATION);
        
        protocol.getProtocolDocument().getDocumentHeader().getWorkflowDocument().approve(actionBean.getComments());
    }
    
    public void grantResponseApproval(Protocol protocol, ProtocolApproveBean actionBean) throws Exception {
        generateProtocolActionAndAttach(protocol, actionBean, ProtocolActionType.RESPONSE_APPROVAL);
        
        if (protocol.getApprovalDate() == null) {
            protocol.setApprovalDate(actionBean.getApprovalDate());
        }
        if (protocol.isRenewal() || protocol.isAmendment()) {
            protocol.setLastApprovalDate(actionBean.getApprovalDate());
        }
        protocol.setExpirationDate(actionBean.getExpirationDate());
        
        finalizeReviewsAndSave(protocol, ProtocolActionType.APPROVED, RESPONSE_APPROVAL_FINALIZE_OLR_ANNOTATION);
        
        protocol.getProtocolDocument().getDocumentHeader().getWorkflowDocument().approve(actionBean.getComments());
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    @Override
    protected String getProtocolActionTypeCodeForResponseApprovalHook() {
        return ProtocolActionType.RESPONSE_APPROVAL;
    }

    @Override
    protected String getProtocolActionTypeCodeForAdminApprovalHook() {
        // TODO this method needs to be populated after the IRB backfit is done in order for IRB to also have the admin approve action
        return null;
    }

    @Override
    protected ProtocolActionBase getNewProtocolActionInstanceHook(ProtocolBase protocol, ProtocolSubmissionBase submission, String protocolActionTypeCode) {
        return new ProtocolAction((Protocol) protocol, (ProtocolSubmission) submission, protocolActionTypeCode);
    }

    @Override
    protected ProtocolActionsCorrespondenceBase getNewProtocolActionsCorrespondence(String protocolActionTypeCode) {
        return new ProtocolGenericCorrespondence(protocolActionTypeCode);
    }
    
}
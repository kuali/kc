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
        if (!protocol.isNew()) {
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
        if (!protocol.isNew()) {
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
        if (!protocol.isNew()) {
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

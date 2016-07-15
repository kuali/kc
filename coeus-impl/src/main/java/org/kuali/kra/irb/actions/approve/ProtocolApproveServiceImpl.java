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
import org.apache.commons.lang3.time.DateUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericCorrespondence;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.approve.ProtocolApproveServiceImplBase;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionsCorrespondenceBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

import java.sql.Date;

/**
 * Approves a protocol, either for a full, expedited, or response protocol submission.
 */
public class ProtocolApproveServiceImpl extends ProtocolApproveServiceImplBase implements ProtocolApproveService {
    
    private static final String FULL_APPROVAL_FINALIZE_OLR_ANNOTATION = "Online Review finalized as part of full approval action on protocol.";
    private static final String EXPEDITED_APPROVAL_FINALIZE_OLR_ANNOTATION = "Online Review finalized as part of expedited approval action on protocol.";
    
    private ParameterService parameterService;

    @Override
    public void grantFullApproval(ProtocolDocumentBase protocolDocument, org.kuali.kra.protocol.actions.approve.ProtocolApproveBean actionBean) throws Exception {
        generateProtocolActionAndAttach(protocolDocument.getProtocol(), actionBean, ProtocolActionType.APPROVED);

        if (protocolDocument.getProtocol().getApprovalDate() == null) {
            protocolDocument.getProtocol().setApprovalDate(actionBean.getApprovalDate());
        }
        if (!protocolDocument.getProtocol().isNew()) {
            protocolDocument.getProtocol().setLastApprovalDate(actionBean.getApprovalDate());
        }
        String exemptProtocolTypeCode = parameterService.getParameterValueAsString(ProtocolDocument.class, Constants.PROTOCOL_TYPE_CODE_EXEMPT);
        if (!StringUtils.equals(exemptProtocolTypeCode, protocolDocument.getProtocol().getProtocolTypeCode())) {
            protocolDocument.getProtocol().setExpirationDate(actionBean.getExpirationDate());
        }
        
        finalizeReviewsAndSave(protocolDocument, ProtocolActionType.APPROVED, FULL_APPROVAL_FINALIZE_OLR_ANNOTATION);
    }  

    @Override
    public void grantExpeditedApproval(ProtocolDocumentBase protocolDocument, ProtocolApproveBean actionBean) throws Exception {
        generateProtocolActionAndAttach(protocolDocument.getProtocol(), actionBean, ProtocolActionType.EXPEDITE_APPROVAL);
        
        protocolDocument.getProtocol().setApprovalDate(actionBean.getApprovalDate());
        protocolDocument.getProtocol().setExpirationDate(actionBean.getExpirationDate());
        if (!protocolDocument.getProtocol().isNew()) {
            protocolDocument.getProtocol().setLastApprovalDate(actionBean.getApprovalDate());
        }
        finalizeReviewsAndSave(protocolDocument, ProtocolActionType.EXPEDITE_APPROVAL, EXPEDITED_APPROVAL_FINALIZE_OLR_ANNOTATION);
        
        protocolDocument.getDocumentHeader().getWorkflowDocument().approve(actionBean.getComments());
    }
    
    public void grantResponseApproval(ProtocolDocumentBase protocolDocument, ProtocolApproveBean actionBean) throws Exception {
        generateProtocolActionAndAttach(protocolDocument.getProtocol(), actionBean, ProtocolActionType.RESPONSE_APPROVAL);
        
        if (protocolDocument.getProtocol().getApprovalDate() == null) {
            protocolDocument.getProtocol().setApprovalDate(actionBean.getApprovalDate());
        }
        if (!protocolDocument.getProtocol().isNew()) {
            protocolDocument.getProtocol().setLastApprovalDate(actionBean.getApprovalDate());
        }
        protocolDocument.getProtocol().setExpirationDate(actionBean.getExpirationDate());
        
        finalizeReviewsAndSave(protocolDocument, ProtocolActionType.APPROVED, RESPONSE_APPROVAL_FINALIZE_OLR_ANNOTATION);
        
        protocolDocument.getDocumentHeader().getWorkflowDocument().approve(actionBean.getComments());
    }

    /**
     * Builds an expiration date, defaulting to the expiration date from the protocol.
     *
     * If the expiration date from the protocol is null, or if the protocol is new or a renewal, creates an expiration date exactly one year ahead and one day
     * less than the approval date.
     *
     * @param protocol
     * @param approvalDate
     * @return a non-null expiration date
     */
    public Date buildExpirationDate(ProtocolBase protocol, Date approvalDate) {
        Date expirationDate = protocol.getExpirationDate();

        if (expirationDate == null || protocol.isNew() || protocol.isRenewal()) {
            java.util.Date newExpirationDate = DateUtils.addYears(approvalDate, getDefaultExpirationDateDifference());
            newExpirationDate = DateUtils.addDays(newExpirationDate, -1);
            expirationDate = org.kuali.coeus.sys.framework.util.DateUtils.convertToSqlDate(newExpirationDate);
        }

        return expirationDate;
    }

    /**
     *
     * This method returns the number of years to add for the default expiration date.
     * @return
     */
    public int getDefaultExpirationDateDifference() {
        return 1;
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

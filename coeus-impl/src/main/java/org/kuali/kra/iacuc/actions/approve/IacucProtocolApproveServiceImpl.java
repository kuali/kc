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
package org.kuali.kra.iacuc.actions.approve;

import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.correspondence.IacucProtocolActionsCorrespondence;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.approve.ProtocolApproveBean;
import org.kuali.kra.protocol.actions.approve.ProtocolApproveServiceImplBase;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionsCorrespondenceBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;

public class IacucProtocolApproveServiceImpl extends ProtocolApproveServiceImplBase implements IacucProtocolApproveService {

    private static final String FULL_APPROVAL_FINALIZE_OLR_ANNOTATION = "Online Review finalized as part of full approval action on protocol.";
    
    
    @Override
    public void grantFullApproval(ProtocolDocumentBase protocolDocument, ProtocolApproveBean actionBean) throws Exception {
        generateProtocolActionAndAttach(protocolDocument.getProtocol(), actionBean, IacucProtocolActionType.IACUC_APPROVED);

        if (protocolDocument.getProtocol().getApprovalDate() == null) {
            protocolDocument.getProtocol().setApprovalDate(actionBean.getApprovalDate());
        }              
        if (!protocolDocument.getProtocol().isNew()) {
            protocolDocument.getProtocol().setLastApprovalDate(actionBean.getApprovalDate());
        }
        protocolDocument.getProtocol().setExpirationDate(actionBean.getExpirationDate());
        
        finalizeReviewsAndSave(protocolDocument, IacucProtocolActionType.IACUC_APPROVED, FULL_APPROVAL_FINALIZE_OLR_ANNOTATION);
        protocolDocument.getDocumentHeader().getWorkflowDocument().approve(actionBean.getComments());
    }  

    
    @Override
    protected String getProtocolActionTypeCodeForAdminApprovalHook() {
        return IacucProtocolActionType.ADMINISTRATIVE_APPROVAL;
    }

    
    @Override
    protected String getProtocolActionTypeCodeForResponseApprovalHook() {
        return IacucProtocolActionType.RESPONSE_APPROVAL;
    }
    
    
    @Override
    protected ProtocolActionBase getNewProtocolActionInstanceHook(ProtocolBase protocol, ProtocolSubmissionBase submission, String protocolActionTypeCode) {
        return new IacucProtocolAction((IacucProtocol)protocol, (IacucProtocolSubmission) submission, protocolActionTypeCode);
    }


    @Override
    protected ProtocolActionsCorrespondenceBase getNewProtocolActionsCorrespondence(String protocolActionTypeCode) {
        return new IacucProtocolActionsCorrespondence(protocolActionTypeCode);
    }

    
}

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
package org.kuali.kra.iacuc.actions.approve;

import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.correspondence.IacucProtocolActionsCorrespondence;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.actions.ProtocolAction;
import org.kuali.kra.protocol.actions.approve.ProtocolApproveBean;
import org.kuali.kra.protocol.actions.approve.ProtocolApproveServiceImpl;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionsCorrespondence;

public class IacucProtocolApproveServiceImpl extends ProtocolApproveServiceImpl implements IacucProtocolApproveService {

    private static final String FULL_APPROVAL_FINALIZE_OLR_ANNOTATION = "Online Review finalized as part of full approval action on protocol.";
    
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.protocol.actions.approve.ProtocolApproveService#grantFullApproval(org.kuali.kra.protocol.Protocol, 
     *      org.kuali.kra.protocol.actions.approve.ProtocolApproveBean)
     */
    public void grantFullApproval(Protocol protocol, ProtocolApproveBean actionBean) throws Exception {
        generateProtocolActionAndAttach(protocol, actionBean, IacucProtocolActionType.IACUC_APPROVED);   

        if (protocol.getApprovalDate() == null) {
            protocol.setApprovalDate(actionBean.getApprovalDate());
        }
        if (protocol.isRenewal() || ((IacucProtocol)protocol).isContinuation()) {
            protocol.setLastApprovalDate(actionBean.getApprovalDate());
        }
        
        finalizeReviewsAndSave(protocol, IacucProtocolActionType.IACUC_APPROVED, FULL_APPROVAL_FINALIZE_OLR_ANNOTATION);
        protocol.getProtocolDocument().getDocumentHeader().getWorkflowDocument().approve(actionBean.getComments());
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
    protected ProtocolAction getNewProtocolActionInstanceHook(Protocol protocol, Object object, String protocolActionTypeCode) {
        return new IacucProtocolAction((IacucProtocol)protocol, null, protocolActionTypeCode);
    }


    @Override
    protected ProtocolActionsCorrespondence getNewProtocolActionsCorrespondence(String protocolActionTypeCode) {
        return new IacucProtocolActionsCorrespondence(protocolActionTypeCode);
    }

    
}

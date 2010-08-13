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
package org.kuali.kra.irb.actions.expediteapproval;

import java.sql.Timestamp;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.approve.ProtocolApproveBean;
import org.kuali.kra.irb.actions.correspondence.ProtocolActionCorrespondenceGenerationService;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericCorrespondence;
import org.kuali.kra.irb.actions.proccessbillable.ProtocolProccessBillableService;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.printing.PrintingException;
import org.kuali.rice.kns.service.DocumentService;

/**
 * The ProtocolExpediteApprovalService implementation.
 */
public class ProtocolExpediteApprovalServiceImpl extends ProtocolProccessBillableService implements ProtocolExpediteApprovalService {

    private DocumentService documentService;
    private ProtocolActionService protocolActionService;
    private ProtocolActionCorrespondenceGenerationService protocolActionCorrespondenceGenerationService;

    /**
     * Set the document service.
     * @param documentService
     */
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    
    /**
     * Set the Protocol Action Service.
     * @param protocolActionService
     */
    public void setProtocolActionService(ProtocolActionService protocolActionService) {
        this.protocolActionService = protocolActionService;
    }
    
    public void setProtocolActionCorrespondenceGenerationService(
            ProtocolActionCorrespondenceGenerationService protocolActionCorrespondenceGenerationService) {
        this.protocolActionCorrespondenceGenerationService = protocolActionCorrespondenceGenerationService;
    }

    public void generateCorrespondenceDocumentAndAttach(Protocol protocol) throws PrintingException {
        ProtocolGenericCorrespondence correspondence = new ProtocolGenericCorrespondence(ProtocolActionType.EXPEDITE_APPROVAL);
        correspondence.setDocument(protocol.getProtocolDocument());
        correspondence.setProtocolDocument(protocol.getProtocolDocument());
        protocolActionCorrespondenceGenerationService.generateCorrespondenceDocumentAndAttach(correspondence);
    }    

    /**
     * @see org.kuali.kra.irb.actions.grantexemption.ProtocolGrantExemptionService#grantExemption(org.kuali.kra.irb.Protocol, org.kuali.kra.irb.actions.grantexemption.ProtocolGrantExemptionBean)
     */
    public void grantExpeditedApproval(Protocol protocol, ProtocolApproveBean actionBean) throws Exception {
        ProtocolAction protocolAction = new ProtocolAction(protocol, null, ProtocolActionType.EXPEDITE_APPROVAL); 
        protocolAction.setComments(actionBean.getComments());
        protocolAction.setActionDate(new Timestamp(actionBean.getActionDate().getTime()));
        protocol.getProtocolActions().add(protocolAction);
        protocolActionService.updateProtocolStatus(protocolAction, protocol);
        this.proccessBillable(protocol, actionBean.isBillable());
        protocol.setApprovalDate(actionBean.getApprovalDate());
        protocol.setLastApprovalDate(actionBean.getApprovalDate());
        protocol.setExpirationDate(actionBean.getExpirationDate());
        protocol.refreshReferenceObject("protocolStatus");
        generateCorrespondenceDocumentAndAttach(protocol); 
        documentService.saveDocument(protocol.getProtocolDocument());
        
        protocol.getProtocolDocument().getDocumentHeader().getWorkflowDocument().approve(actionBean.getComments());
    }
}

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

import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.correspondence.ProtocolActionCorrespondenceGenerationService;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericCorrespondence;
import org.kuali.kra.irb.actions.proccessbillable.ProtocolProccessBillableService;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.auth.ProtocolTask;
import org.kuali.kra.printing.PrintingException;
import org.kuali.rice.kns.service.DocumentService;

/**
 * 
 * This class handles approving a protocol status change.
 */
public class ProtocolApproveServiceImpl extends ProtocolProccessBillableService implements ProtocolApproveService {
    
    private DocumentService documentService;
    private ProtocolActionService protocolActionService;
    private ProtocolActionCorrespondenceGenerationService protocolActionCorrespondenceGenerationService;
    
   
    /**{@inheritDoc}**/
    public void approve(ProtocolDocument protocolDocument, ProtocolApproveBean actionBean) throws Exception {
        Protocol protocol = protocolDocument.getProtocol();
        ProtocolAction protocolAction = new ProtocolAction(protocol, null, ProtocolActionType.APPROVED);
        protocolAction.setComments(actionBean.getComments());
        protocolAction.setActionDate(new Timestamp(actionBean.getActionDate().getTime()));
        protocol.getProtocolActions().add(protocolAction);
        protocolActionService.updateProtocolStatus(protocolAction, protocol);
        
        this.proccessBillable(protocol, actionBean.isBillable());        
        
        protocol.setProtocolStatusCode(ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT);
        protocol.setApprovalDate(actionBean.getApprovalDate());
        protocol.setLastApprovalDate(actionBean.getApprovalDate());
        protocol.setExpirationDate(actionBean.getExpirationDate());
        protocol.refreshReferenceObject("protocolStatus");
        documentService.saveDocument(protocolDocument);
        generateCorrespondenceDocumentAndAttach(protocol); 
        
        protocolDocument.getDocumentHeader().getWorkflowDocument().approve(actionBean.getComments());

    }
    
    /**
     * 
     * This method will generate a correspondence document, and attach it to the protocol.
     * @param protocol a Protocol object.
     */
    private void generateCorrespondenceDocumentAndAttach(Protocol protocol) throws PrintingException {
        ProtocolGenericCorrespondence correspondence = new ProtocolGenericCorrespondence(ProtocolActionType.APPROVED);
        correspondence.setDocument(protocol.getProtocolDocument());
        correspondence.setProtocolDocument(protocol.getProtocolDocument());
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
}
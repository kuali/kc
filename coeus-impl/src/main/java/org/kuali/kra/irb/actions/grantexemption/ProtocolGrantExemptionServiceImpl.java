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
package org.kuali.kra.irb.actions.grantexemption;

import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.correspondence.ProtocolActionCorrespondenceGenerationService;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.rice.krad.service.DocumentService;

import java.sql.Timestamp;

/**
 * The ProtocolGrantExemptionService implementation.
 */
public class ProtocolGrantExemptionServiceImpl implements ProtocolGrantExemptionService {

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

    protected void generateCorrespondenceDocumentAndAttach(Protocol protocol, ProtocolGrantExemptionBean actionBean) throws PrintingException {
        
        GrantExemptionCorrespondence correspondence = actionBean.getCorrespondence();
        correspondence.setPrintableBusinessObject(protocol);
        correspondence.setProtocol(protocol);
        protocolActionCorrespondenceGenerationService.generateCorrespondenceDocumentAndAttach(correspondence);
    }    

    @Override
    public void grantExemption(Protocol protocol, ProtocolGrantExemptionBean actionBean) throws Exception {
        ProtocolAction protocolAction = new ProtocolAction(protocol, protocol.getProtocolSubmission(), ProtocolActionType.GRANT_EXEMPTION); 
        protocolAction.setComments(actionBean.getComments());
        protocolAction.setActionDate(new Timestamp(actionBean.getActionDate().getTime()));
        protocolAction.setSubmissionIdFk(protocol.getLastProtocolAction().getSubmissionIdFk());
        protocolAction.setSubmissionNumber(protocol.getLastProtocolAction().getSubmissionNumber());
        protocol.getProtocolActions().add(protocolAction);
        protocolActionService.updateProtocolStatus(protocolAction, protocol);
        
        protocol.setApprovalDate(actionBean.getApprovalDate());
        protocol.refreshReferenceObject("protocolStatus");
        protocol.getProtocolDocument().getDocumentHeader().getWorkflowDocument().approve(actionBean.getComments());
    }
}

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
import java.util.List;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.correspondence.ProtocolActionCorrespondenceGenerationService;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceTemplate;
import org.kuali.kra.printing.PrintingException;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * 
 * This class handles approving a protocol status change.
 */
public class ProtocolApproveServiceImpl implements ProtocolApproveService {
    
    private BusinessObjectService businessObjectService;
    private ProtocolActionService protocolActionService;
    private ProtocolActionCorrespondenceGenerationService protocolActionCorrespondenceGenerationService;
    
   
    /**{@inheritDoc}**/
    public void approve(Protocol protocol, ProtocolApproveBean actionBean) throws Exception {
        ProtocolAction protocolAction = new ProtocolAction(protocol, null, ProtocolActionType.APPROVED);
        protocolAction.setComments(actionBean.getComments());
        protocolAction.setActionDate(new Timestamp(actionBean.getActionDate().getTime()));
        protocol.getProtocolActions().add(protocolAction);
        protocolActionService.updateProtocolStatus(protocolAction, protocol);
        
        protocol.setProtocolStatusCode(ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT);
        protocol.setApprovalDate(actionBean.getApprovalDate());
        protocol.setExpirationDate(actionBean.getExpirationDate());
        protocol.refreshReferenceObject("protocolStatus");
        businessObjectService.save(protocol);
        generateCorrespondenceDocumentAndAttach(protocol);

    }
    
    /**
     * 
     * This method will call the ProtocolGenerateCorrespondenceService, the ProtocolXMLStreamService(needs to be 
     * dummied up), and pass those returns to the print service, and attach the generated PDF to the Protocol.
     * @param protocol a Protocol object.
     */
    public void generateCorrespondenceDocumentAndAttach(Protocol protocol) throws PrintingException {
        List<ProtocolCorrespondenceTemplate> approvedTemplates = 
            protocolActionCorrespondenceGenerationService.getCorrespondenceTemplates(ProtocolActionType.APPROVED);
        String attachmentDescription = "Approved Correspondence Template Document";
        protocolActionCorrespondenceGenerationService.generateCorrespondenceDocumentAndAttach(protocol, approvedTemplates, attachmentDescription);
    }    
    
    /**
     * Set the business object service.
     * @param businessObjectService the business object service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public void setProtocolActionService(ProtocolActionService protocolActionService) {
        this.protocolActionService = protocolActionService;
    }
    
    public void setProtocolActionCorrespondenceGenerationService(ProtocolActionCorrespondenceGenerationService protocolActionCorrespondenceGenerationService) {
        this.protocolActionCorrespondenceGenerationService = protocolActionCorrespondenceGenerationService;
    }
    
    
}
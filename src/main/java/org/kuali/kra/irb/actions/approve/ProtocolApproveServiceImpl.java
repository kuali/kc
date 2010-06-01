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

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;

import org.apache.struts.upload.FormFile;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.correspondence.ActionCorrespondenceGenerationHelper;
import org.kuali.kra.irb.actions.correspondence.ActionCorrespondenceGenerationService;
import org.kuali.kra.irb.actions.correspondence.ProtocolActionTypeToCorrespondenceTemplateService;
import org.kuali.kra.irb.actions.correspondence.ProtocolXMLStreamService;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceTemplate;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentService;
import org.kuali.kra.printing.Printable;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.printing.service.PrintingService;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * 
 * This class handles approving a protocol status change.
 */
public class ProtocolApproveServiceImpl implements ProtocolApproveService, ActionCorrespondenceGenerationService {
    
    private BusinessObjectService businessObjectService;
    private ProtocolActionService protocolActionService;
    private ProtocolActionTypeToCorrespondenceTemplateService protocolActionTypeToCorrespondenceTemplateService;
    private ProtocolAttachmentService protocolAttachmentService;
    private PrintingService printingService;
    private ProtocolXMLStreamService protocolXMLStreamService;
   
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
            this.protocolActionTypeToCorrespondenceTemplateService.getTemplatesByProtocolAction(ProtocolActionType.APPROVED);
        
        ActionCorrespondenceGenerationHelper helper = new ActionCorrespondenceGenerationHelper(this.businessObjectService);
        for (ProtocolCorrespondenceTemplate template : approvedTemplates) {
            Printable printable = this.protocolXMLStreamService.getPrintableXMLStream(protocol, template);
            AttachmentDataSource ads = this.printingService.print(printable);         
            helper.buildAndAttachProtocolAttachmentProtocol(protocol, ads.getContent(), "Approved Correspondence Template Document");
        }
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
    
    public void setProtocolActionTypeToCorrespondenceTemplateService(ProtocolActionTypeToCorrespondenceTemplateService protocolActionTypeToCorrespondenceTemplateService) {
        this.protocolActionTypeToCorrespondenceTemplateService = protocolActionTypeToCorrespondenceTemplateService;
    }
    
    public void setProtocolAttachmentService(ProtocolAttachmentService protocolAttachmentService) {
        this.protocolAttachmentService = protocolAttachmentService;
    }
    public void setPrintingService(PrintingService printingService){
        this.printingService = printingService;
    }
    public void setProtocolXMLStreamService(ProtocolXMLStreamService protocolXMLStreamService){
        this.protocolXMLStreamService = protocolXMLStreamService;
    }
}
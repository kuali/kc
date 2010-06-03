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
package org.kuali.kra.irb.actions.correspondence;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.bo.AttachmentFile;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceTemplate;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentProtocol;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentStatus;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentType;
import org.kuali.kra.printing.Printable;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.printing.service.PrintingService;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * 
 * This class deals with making a protocol attachment from a template based on an action.
 */
public class ProtocolActionCorrespondenceGenerationServiceImpl implements ProtocolActionCorrespondenceGenerationService {

    private BusinessObjectService businessObjectService;
    private PrintingService printingService;
    private ProtocolXMLStreamService protocolXMLStreamService;
    private ProtocolActionTypeToCorrespondenceTemplateService protocolActionTypeToCorrespondenceTemplateService;
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    public void setPrintingService(PrintingService printingService) {
        this.printingService = printingService;
    }
    public void setProtocolXMLStreamService(ProtocolXMLStreamService protocolXMLStreamService) {
        this.protocolXMLStreamService = protocolXMLStreamService;
    }
    public void setProtocolActionTypeToCorrespondenceTemplateService(ProtocolActionTypeToCorrespondenceTemplateService protocolActionTypeToCorrespondenceTemplateService) {
        this.protocolActionTypeToCorrespondenceTemplateService = protocolActionTypeToCorrespondenceTemplateService;
    }
    
    /**{@inheritDoc}**/
    public void buildAndAttachProtocolAttachmentProtocol(Protocol protocol, byte[] data, String attachmentDescription) {
        
        ProtocolAttachmentProtocol protocolAttachment = new ProtocolAttachmentProtocol();
        AttachmentFile attachFile = new AttachmentFile(attachmentDescription, "pdf", data);
        protocolAttachment.setFile(attachFile);
        protocolAttachment.setProtocol(protocol);
        protocolAttachment.setDescription(attachmentDescription);
        protocolAttachment.setTypeCode(getProtocolOtherTypeCode());
        protocolAttachment.setDocumentId(protocol.getSequenceNumber());
        protocolAttachment.setDocumentStatusCode(getDOcumentStatusCode());
        protocolAttachment.setStatusCode(getCompleteAttachmentStatusCode());     
        protocol.addAttachmentsByType(protocolAttachment);
        this.businessObjectService.save(protocol);
    }
    
    private String getProtocolOtherTypeCode() {
        Map matching = new HashMap();
        matching.put("description", "Other");
        Collection<ProtocolAttachmentType> types = this.businessObjectService.findMatching(ProtocolAttachmentType.class, matching);
        return types.iterator().next().getCode();
    }
    
    private String getCompleteAttachmentStatusCode() {
        Map matching = new HashMap();
        matching.put("description", "Complete");
        Collection<ProtocolAttachmentStatus> statuses = this.businessObjectService.findMatching(ProtocolAttachmentStatus.class, matching);
        return statuses.iterator().next().getCode();
    }
    
    /**    
     * 
     * This method returns the document status code
     * @return String
     * TODO figure out what the status code does, and return an intelligent value;
     */
    private String getDOcumentStatusCode() {
        return "1";
    }
    
    /**{@inheritDoc}**/
    public void generateCorrespondenceDocumentAndAttach(Protocol protocol, List<ProtocolCorrespondenceTemplate> templates, 
            String attachmentDescription) throws PrintingException {        
        for (ProtocolCorrespondenceTemplate template : templates) {
            Printable printable = this.protocolXMLStreamService.getPrintableXMLStream(protocol, template);
            AttachmentDataSource ads = this.printingService.print(printable);         
            buildAndAttachProtocolAttachmentProtocol(protocol, ads.getContent(), attachmentDescription);
        }
    }
    
    /**{@inheritDoc}**/
    public List<ProtocolCorrespondenceTemplate> getCorrespondenceTemplates(String actionType) {
        List<ProtocolCorrespondenceTemplate> templates = 
            this.protocolActionTypeToCorrespondenceTemplateService.getTemplatesByProtocolAction(actionType);
        return templates;
    }

}

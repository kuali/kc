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
import java.util.Map;

import org.apache.struts.upload.FormFile;
import org.kuali.kra.bo.AttachmentFile;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentProtocol;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentStatus;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentType;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * 
 * This class provides some helper functions to build a protocol attachment.
 */
public class ActionCorrespondenceGenerationHelper {
    
    private BusinessObjectService businessObjectService;
    
    /**
     * 
     * Constructs a ActionCorrespondenceGenerationHelper.java.
     */
    public ActionCorrespondenceGenerationHelper() {
        
    }
    
    /**
     * 
     * Constructs a ActionCorrespondenceGenerationHelper.java.
     * @param businessObjectService
     */
    public ActionCorrespondenceGenerationHelper(BusinessObjectService businessObjectService) {
        setBusinessObjectService(businessObjectService);
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    private BusinessObjectService getBusinessObjectService() {
        if (this.businessObjectService == null) {
            this.businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        }
        return this.businessObjectService;
    }
    
    public void buildAndAttachProtocolAttachmentProtocol(Protocol protocol, FormFile file, String attachmentDescription) {
        
        ProtocolAttachmentProtocol protocolAttachment = new ProtocolAttachmentProtocol();
        AttachmentFile attachFile = AttachmentFile.createFromFormFile(file);
        protocolAttachment.setFile(attachFile);
        protocolAttachment.setProtocol(protocol);
        protocolAttachment.setDescription(attachmentDescription);
        protocolAttachment.setTypeCode(getProtocolNarativeTypeCode());
        protocolAttachment.setDocumentId(protocol.getSequenceNumber());
        protocolAttachment.setDocumentStatusCode(getDOcumentStatusCode());
        protocolAttachment.setStatusCode(getCompleteAttachmentStatusCode());     
        protocol.addAttachmentsByType(protocolAttachment);
        this.getBusinessObjectService().save(protocol);
    }
    
    private String getProtocolNarativeTypeCode() {
        Map matching = new HashMap();
        matching.put("description", "Protocol Narrative");
        Collection<ProtocolAttachmentType> types = this.getBusinessObjectService().findMatching(ProtocolAttachmentType.class, matching);
        return types.iterator().next().getCode();
    }
    
    private String getCompleteAttachmentStatusCode() {
        Map matching = new HashMap();
        matching.put("description", "Complete");
        Collection<ProtocolAttachmentStatus> statuses = this.getBusinessObjectService().findMatching(ProtocolAttachmentStatus.class, matching);
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
}

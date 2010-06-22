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

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondence;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceTemplate;
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
     private static Map<String, String> actionTypesToAttachmentDescription;
    
    static {
        actionTypesToAttachmentDescription = new HashMap<String, String>();
        actionTypesToAttachmentDescription.put(ProtocolActionType.SUBMIT_TO_IRB, "");
        actionTypesToAttachmentDescription.put(ProtocolActionType.WITHDRAWN, "Withdraw Correspondence Template Document");
        actionTypesToAttachmentDescription.put(ProtocolActionType.REQUEST_TO_CLOSE, "");
        actionTypesToAttachmentDescription.put(ProtocolActionType.REQUEST_FOR_DATA_ANALYSIS_ONLY, "");
        actionTypesToAttachmentDescription.put(ProtocolActionType.REQUEST_FOR_TERMINATION, "");
        actionTypesToAttachmentDescription.put(ProtocolActionType.REQUEST_TO_REOPEN_ENROLLMENT, "");
        actionTypesToAttachmentDescription.put(ProtocolActionType.NOTIFY_IRB, "");
        actionTypesToAttachmentDescription.put(ProtocolActionType.AMENDMENT_CREATED, "");
        actionTypesToAttachmentDescription.put(ProtocolActionType.RENEWAL_CREATED, "");
        actionTypesToAttachmentDescription.put(ProtocolActionType.ASSIGN_TO_AGENDA, "");
        actionTypesToAttachmentDescription.put(ProtocolActionType.APPROVED, "Approved Correspondence Template Document");
        actionTypesToAttachmentDescription.put(ProtocolActionType.DISAPPROVED, "");
        actionTypesToAttachmentDescription.put(ProtocolActionType.PROTOCOL_CREATED, "");
        actionTypesToAttachmentDescription.put(ProtocolActionType.CLOSED_FOR_ENROLLMENT, "Closed for Enrollment Correspondence Template Document");
        actionTypesToAttachmentDescription.put(ProtocolActionType.RESPONSE_APPROVAL, "");
        actionTypesToAttachmentDescription.put(ProtocolActionType.IRB_ACKNOWLEDGEMENT, "");
        actionTypesToAttachmentDescription.put(ProtocolActionType.IRB_REVIEW_NOT_REQUIRED, "");
        actionTypesToAttachmentDescription.put(ProtocolActionType.DATA_ANALYSIS_ONLY, "Data Analysis Only Correspondence Template Document");
        actionTypesToAttachmentDescription.put(ProtocolActionType.REOPEN_ENROLLMENT, "Reopened Correspondence Template Document");
        actionTypesToAttachmentDescription.put(ProtocolActionType.CLOSED_ADMINISTRATIVELY_CLOSED, "Closed Correspondence Template Document");
        actionTypesToAttachmentDescription.put(ProtocolActionType.TERMINATED, "Terminated Correspondence Template Document");
        actionTypesToAttachmentDescription.put(ProtocolActionType.SUSPENDED, "Suspended by xxx Correspondence Template Document");
        actionTypesToAttachmentDescription.put(ProtocolActionType.EXPIRED, "Expired Correspondence Template Document");
        actionTypesToAttachmentDescription.put(ProtocolActionType.SUSPENDED_BY_DSMB, "Suspended by DSMB Correspondence Template Document");
        actionTypesToAttachmentDescription.put(ProtocolActionType.EXPEDITE_APPROVAL, "");
        actionTypesToAttachmentDescription.put(ProtocolActionType.GRANT_EXEMPTION, "");
        actionTypesToAttachmentDescription.put(ProtocolActionType.ADMINISTRATIVE_CORRECTION, "");
    }
    
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
    public void buildAndAttachProtocolCorrespondence(Protocol protocol, byte[] data, String attachmentDescription, String correspTypeCode) {
        //Temp code since printing impl is not in place
        byte[] bytes = {'a', 'b', 'c','d'};
        
        ProtocolCorrespondence protocolCorrespondence = new ProtocolCorrespondence();
        protocolCorrespondence.setProtocol(protocol);
        protocolCorrespondence.setProtocolId(protocol.getProtocolId());
        protocolCorrespondence.setProtocolNumber(protocol.getProtocolNumber());
        protocolCorrespondence.setSequenceNumber(protocol.getSequenceNumber());
        protocolCorrespondence.setProtoCorrespTypeCode(correspTypeCode);
        ProtocolAction lastAction = protocol.getLastProtocolAction();
        protocolCorrespondence.setProtocolAction(lastAction);
        protocolCorrespondence.setActionIdFk(lastAction.getProtocolActionId());
        protocolCorrespondence.setCorrespondence(bytes); 
        protocolCorrespondence.setActionId(lastAction.getActionId());
        //No placeholder for attachment description???
        //What is Final flag used for?
        protocolCorrespondence.setFinalFlag(true);
        this.businessObjectService.save(protocolCorrespondence);
        
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
    
    private String getAttachmentDescription(String actionTypeCode, String protocolStatusCode) {
        String attachmentDescription = actionTypesToAttachmentDescription.get(actionTypeCode);
        if(ProtocolStatus.SUSPENDED_BY_IRB.equals(protocolStatusCode)) {
            protocolStatusCode.replace("xxx", "IRB");
        } else if (ProtocolStatus.SUSPENDED_BY_PI.equals(protocolStatusCode)) {
            protocolStatusCode.replace("xxx", "PI");
        }
        return attachmentDescription;
    }
    
    /**{@inheritDoc}**/
    public void generateCorrespondenceDocumentAndAttach(Protocol protocol, List<ProtocolCorrespondenceTemplate> templates, 
            String actionTypeCode) throws PrintingException {  
        String attachmentDescription = getAttachmentDescription(actionTypeCode, protocol.getProtocolStatusCode());        
        for (ProtocolCorrespondenceTemplate template : templates) {
            Printable printable = this.protocolXMLStreamService.getPrintableXMLStream(protocol, template);
            AttachmentDataSource ads = this.printingService.print(printable);         
            buildAndAttachProtocolCorrespondence(protocol, ads.getContent(), attachmentDescription, template.getProtoCorrespTypeCode());
        }
    }
    
    /**{@inheritDoc}**/
    public List<ProtocolCorrespondenceTemplate> getCorrespondenceTemplates(String actionType) {
        List<ProtocolCorrespondenceTemplate> templates = 
            this.protocolActionTypeToCorrespondenceTemplateService.getTemplatesByProtocolAction(actionType);
        return templates;
    }

}

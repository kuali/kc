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

import java.util.List;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondence;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceTemplate;
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
    
    private BusinessObjectService getBusinessObjectService() {
        return this.businessObjectService;
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
    
    
    private void buildAndAttachProtocolCorrespondence(Protocol protocol, byte[] data, String correspTypeCode) {
        //Temp code since printing impl is not in place
        
        ProtocolCorrespondence protocolCorrespondence = new ProtocolCorrespondence();
        protocolCorrespondence.setProtocol(protocol);
        protocolCorrespondence.setProtocolId(protocol.getProtocolId());
        protocolCorrespondence.setProtocolNumber(protocol.getProtocolNumber());
        protocolCorrespondence.setSequenceNumber(protocol.getSequenceNumber());
        protocolCorrespondence.setProtoCorrespTypeCode(correspTypeCode);
        
        ProtocolAction lastAction = protocol.getLastProtocolAction();
        protocolCorrespondence.setProtocolAction(lastAction);
        protocolCorrespondence.setActionIdFk(lastAction.getProtocolActionId());
        protocolCorrespondence.setCorrespondence(data); 
        protocolCorrespondence.setActionId(lastAction.getActionId());

        //What is Final flag used for? ANSWER: the final flag is used by the IRB admin to denote correspondences 
        //that are ready to be sent/published to the PI etc.
        protocolCorrespondence.setFinalFlag(false);
        
        getBusinessObjectService().save(protocolCorrespondence);
        
        getBusinessObjectService().save(protocol);
    }
    
    /**{@inheritDoc}**/
    public void generateCorrespondenceDocumentAndAttach(Protocol protocol, List<ProtocolCorrespondenceTemplate> templates, 
            String actionTypeCode) throws PrintingException {          
        for (ProtocolCorrespondenceTemplate template : templates) {
            System.err.println(template.toString());
            Printable printable = this.protocolXMLStreamService.getPrintableXMLStream(protocol, template);
            if (printable != null) {
                AttachmentDataSource ads = this.printingService.print(printable);
                if (ads != null) {
                    buildAndAttachProtocolCorrespondence(protocol, ads.getContent(), template.getProtoCorrespTypeCode());
                }
            }
        }
    }
    
    /**{@inheritDoc}**/
    public void generateCorrespondenceDocumentAndAttach(AbstractProtocolActionsCorrespondence printableCorrespondence) 
        throws PrintingException {
        if (printableCorrespondence.getXSLTemplates().size() > 0) {
            //there are templates in play, lets do some printing and attaching
            AttachmentDataSource ads = this.printingService.print(printableCorrespondence);
            Protocol protocol = printableCorrespondence.getProtocolDocument().getProtocol();
            buildAndAttachProtocolCorrespondence(protocol, ads.getContent(), printableCorrespondence.getProtoCorrespTypeCode());
        }
    }
    
    /**{@inheritDoc}**/
    public List<ProtocolCorrespondenceTemplate> getCorrespondenceTemplates(String actionType) {
        List<ProtocolCorrespondenceTemplate> templates = 
            this.protocolActionTypeToCorrespondenceTemplateService.getTemplatesByProtocolAction(actionType);
        return templates;
    }
}
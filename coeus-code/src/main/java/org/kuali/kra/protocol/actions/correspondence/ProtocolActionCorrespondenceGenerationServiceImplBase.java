/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.protocol.actions.correspondence;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.person.signature.PersonSignatureService;
import org.kuali.coeus.common.framework.print.PrintableAttachment;
import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.common.framework.print.PrintingService;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondence;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class deals with making a protocol attachment from a template based on an action.
 */
public abstract class ProtocolActionCorrespondenceGenerationServiceImplBase implements ProtocolActionCorrespondenceGenerationService {
    
    private BusinessObjectService businessObjectService;
    private PrintingService printingService;
    private DateTimeService dateTimeService;

    private static final Log LOG = LogFactory.getLog(ProtocolActionCorrespondenceGenerationServiceImplBase.class);
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    protected BusinessObjectService getBusinessObjectService() {
        return this.businessObjectService;
    }
    
    public void setPrintingService(PrintingService printingService) {
        this.printingService = printingService;
    }
    
    protected PrintingService getPrintingService() {
        return printingService;
    }
    
    protected abstract ProtocolCorrespondence getNewProtocolCorrespondenceHook();
    
    protected void buildAndAttachProtocolCorrespondence(ProtocolBase protocol, byte[] data, String correspTypeCode) {
        
        ProtocolCorrespondence protocolCorrespondence = getNewProtocolCorrespondenceHook();
        protocolCorrespondence.setProtocol(protocol);
        protocolCorrespondence.setProtocolId(protocol.getProtocolId());
        protocolCorrespondence.setProtocolNumber(protocol.getProtocolNumber());
        protocolCorrespondence.setSequenceNumber(protocol.getSequenceNumber());
        protocolCorrespondence.setProtoCorrespTypeCode(correspTypeCode);
        
        ProtocolActionBase lastAction = protocol.getLastProtocolAction();
        protocolCorrespondence.setProtocolAction(lastAction);
        protocolCorrespondence.setActionIdFk(lastAction.getProtocolActionId());
        protocolCorrespondence.setCorrespondence(data); 
        protocolCorrespondence.setActionId(lastAction.getActionId());

        //What is Final flag used for? ANSWER: the final flag is used by the IRB admin to denote correspondences 
        //that are ready to be sent/published to the PI etc.
        protocolCorrespondence.setFinalFlag(false);
        protocolCorrespondence.setCreateUser(GlobalVariables.getUserSession().getPrincipalName());
        protocolCorrespondence.setCreateTimestamp(dateTimeService.getCurrentTimestamp());
       
        if(lastAction.getProtocolCorrespondences() == null) {
            List<ProtocolCorrespondence> correspondences = new ArrayList<ProtocolCorrespondence>();
            correspondences.add(protocolCorrespondence);
            lastAction.setProtocolCorrespondences(correspondences);
            protocol.refreshReferenceObject("protocolSubmissions");
        } else {
            lastAction.getProtocolCorrespondences().add(protocolCorrespondence);
        }
        
        getBusinessObjectService().save(protocolCorrespondence);
        
        getBusinessObjectService().save(protocol);
    }
    
    public void attachProtocolCorrespondence(ProtocolBase protocol, byte[] data, String correspTypeCode) {
        buildAndAttachProtocolCorrespondence(protocol, data, correspTypeCode);
    }
    
    @Override
    public void generateCorrespondenceDocumentAndAttach(ProtocolActionsCorrespondenceBase printableCorrespondence) 
            throws PrintingException {
            if (printableCorrespondence.getXSLTemplates().size() > 0) {
                //there are templates in play, lets do some printing and attaching            
                ProtocolBase protocol = printableCorrespondence.getProtocol();
                AttachmentDataSource ads = this.printingService.print(printableCorrespondence);
                applySignatureInDocument(printableCorrespondence, ads);
                if (ads.getData().length > 0) {
                    //only need to do this, if there is actually printable correspondence to save
                    //this may not be the case if a bad template is put in place, or under certain testing conditions.
                    buildAndAttachProtocolCorrespondence(protocol, ads.getData(), printableCorrespondence.getProtoCorrespTypeCode());
                }
            }
        }
        
    public AttachmentDataSource reGenerateCorrespondenceDocument(ProtocolActionsCorrespondenceBase printableCorrespondence) 
            throws PrintingException {
            if (printableCorrespondence.getXSLTemplates().size() > 0) {
                //there are templates in play, lets do some printing and attaching            
                ProtocolBase protocol = printableCorrespondence.getProtocol();
                AttachmentDataSource ads = this.printingService.print(printableCorrespondence);
                applySignatureInDocument(printableCorrespondence, ads);
                return ads;
            }
            return null;
        }
        
    
    
    protected void applySignatureInDocument(ProtocolActionsCorrespondenceBase printableCorrespondence, AttachmentDataSource attachmentDataSource) {
        try {  
            PrintableAttachment printablePdf = (PrintableAttachment)attachmentDataSource;
            byte[] pdfBytes = printablePdf.getData();
            pdfBytes = getPersonSignatureServiceHook().applySignature(pdfBytes);
            printablePdf.setData(pdfBytes);
        } catch (Exception e) {
            LOG.error("Exception Occured in ProtocolActionCorrespondenceGenerationServiceImplBase. Person Signature Exception: ",e);    
        }  
    }
    
    protected abstract PersonSignatureService getPersonSignatureServiceHook();

    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

}

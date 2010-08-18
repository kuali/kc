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
package org.kuali.kra.irb.actions.acknowledgement;

import java.sql.Timestamp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.notification.IrbAcknowledgementEvent;
import org.kuali.kra.irb.actions.notification.ProtocolActionsNotificationService;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.rice.kns.service.DocumentService;

/**
 * 
 * This class to implement the detail of IRB acknowledgement submit action.
 */
public class IrbAcknowledgementServiceImpl implements IrbAcknowledgementService {

    private static final Log LOG = LogFactory.getLog(IrbAcknowledgementServiceImpl.class);
    private DocumentService documentService;
    private ProtocolActionService protocolActionService;
    private ProtocolActionsNotificationService protocolActionsNotificationService;

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
    

    /**
     * 
     * @see org.kuali.kra.irb.actions.acknowledgement.IrbAcknowledgementService#irbAcknowledgement(org.kuali.kra.irb.Protocol,
     *      org.kuali.kra.irb.actions.acknowledgement.IrbAcknowledgementBean)
     */
    public void irbAcknowledgement(Protocol protocol, IrbAcknowledgementBean irbAcknowledgementBean) throws Exception {
        ProtocolAction protocolAction = new ProtocolAction(protocol, null, ProtocolActionType.IRB_ACKNOWLEDGEMENT);
        protocolAction.setComments(irbAcknowledgementBean.getComments());
        protocolAction.setActionDate(new Timestamp(irbAcknowledgementBean.getActionDate().getTime()));
        protocol.getProtocolActions().add(protocolAction);
        protocolActionService.updateProtocolStatus(protocolAction, protocol);

        protocol.refreshReferenceObject("protocolStatus");
        documentService.saveDocument(protocol.getProtocolDocument());
        try {
            protocolActionsNotificationService.sendActionsNotification(protocol, new IrbAcknowledgementEvent(protocol));
        } catch (Exception e) {
            LOG.info("IRB Acknowledgement Notification exception " + e.getStackTrace());
        }

    }

    public void setProtocolActionsNotificationService(ProtocolActionsNotificationService protocolActionsNotificationService) {
        this.protocolActionsNotificationService = protocolActionsNotificationService;
    }
}

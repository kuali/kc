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
package org.kuali.kra.protocol.actions.abandon;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.actions.ProtocolAction;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionsCorrespondence;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionCorrespondenceGenerationService;
import org.kuali.kra.protocol.actions.genericactions.ProtocolGenericActionBean;
import org.kuali.kra.protocol.actions.submit.ProtocolActionService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.DocumentService;

public abstract class ProtocolAbandonServiceImpl implements ProtocolAbandonService {

    private static final Log LOG = LogFactory.getLog(ProtocolAbandonServiceImpl.class);
    private DocumentService documentService;
    private ProtocolActionService protocolActionService;
    private ProtocolActionCorrespondenceGenerationService protocolActionCorrespondenceGenerationService;
    private KcNotificationService kcNotificationService;

    /**
     * 
     * @see org.kuali.kra.irb.actions.abandon.ProtocolAbandonService#abandonProtocol(org.kuali.kra.irb.Protocol, org.kuali.kra.irb.actions.genericactions.ProtocolGenericActionBean)
     */
    public void abandonProtocol(Protocol protocol, ProtocolGenericActionBean protocolAbandonBean) throws WorkflowException {

        ProtocolAction protocolAction = getNewActionHook(protocol); 
        protocolAction.setComments(protocolAbandonBean.getComments());
        protocol.getProtocolActions().add(protocolAction);
        protocolActionService.updateProtocolStatus(protocolAction, protocol);
        protocol.setActive(false);
        documentService.cancelDocument(protocol.getProtocolDocument(), null);
        try {
            // cannot do now because of missing notifications
//            IRBNotificationRenderer renderer = new IRBNotificationRenderer(protocol);
//            IRBNotificationContext context = new IRBNotificationContext(protocol, ProtocolActionType.ABANDON_PROTOCOL, "Abandon", renderer);
//            if (!isPromptUserForNotification) {
//                kcNotificationService.sendNotification(context);
//            }
        createCorrespondenceAndAttach(protocol, getActionType());
        } catch (Exception e) {
            LOG.info("Abandon Protocol Notification exception ", e);
        }
        
    }

    protected abstract String getActionType();
    
    public abstract ProtocolAction getNewActionHook(Protocol protocol);
    
    protected abstract ProtocolActionsCorrespondence getNewProtocolCorrespondenceHook(String actionType);
    
    private void createCorrespondenceAndAttach(Protocol protocol, String protocolActionType) throws PrintingException {
        ProtocolActionsCorrespondence correspondence = getNewProtocolCorrespondenceHook(protocolActionType);
        correspondence.setPrintableBusinessObject(protocol);
        correspondence.setProtocol(protocol);
        protocolActionCorrespondenceGenerationService.generateCorrespondenceDocumentAndAttach(correspondence);
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    public void setProtocolActionService(ProtocolActionService protocolActionService) {
        this.protocolActionService = protocolActionService;
    }

    public void setProtocolActionCorrespondenceGenerationService(
            ProtocolActionCorrespondenceGenerationService protocolActionCorrespondenceGenerationService) {
        this.protocolActionCorrespondenceGenerationService = protocolActionCorrespondenceGenerationService;
    }
    
    public void setKcNotificationService(KcNotificationService kcNotificationService) {
        this.kcNotificationService = kcNotificationService;
    }


}

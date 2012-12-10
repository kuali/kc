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
package org.kuali.kra.irb.actions.abandon;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericCorrespondence;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.abandon.ProtocolAbandonServiceImplBase;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionsCorrespondenceBase;

/**
 * 
 * This class to implement the detail of abandon protocol
 */
public class ProtocolAbandonServiceImpl extends ProtocolAbandonServiceImplBase implements ProtocolAbandonService {

    protected String getActionType() {
        return ProtocolActionType.ABANDON_PROTOCOL;
    }
  
    @Override
    public ProtocolActionBase getNewActionHook(ProtocolBase protocol) {
        return new ProtocolAction((Protocol)protocol, null, getActionType());

    }

    @Override
    protected ProtocolActionsCorrespondenceBase getNewProtocolCorrespondenceHook(String actionType) {
        return new ProtocolGenericCorrespondence(actionType);
    }

 // TODO ********************** commented out during IRB backfit ************************
//    private static final Log LOG = LogFactory.getLog(ProtocolAbandonServiceImpl.class);
//    private DocumentService documentService;
//    private ProtocolActionService protocolActionService;
//    private ProtocolActionCorrespondenceGenerationService protocolActionCorrespondenceGenerationService;
//    private KcNotificationService kcNotificationService;
//
//    /**
//     * 
//     * @see org.kuali.kra.irb.actions.abandon.ProtocolAbandonService#abandonProtocol(org.kuali.kra.irb.Protocol, org.kuali.kra.irb.actions.genericactions.ProtocolGenericActionBean)
//     */
//    public void abandonProtocol(Protocol protocol, ProtocolGenericActionBean protocolAbandonBean) throws WorkflowException {
//
//        ProtocolAction protocolAction = new ProtocolAction(protocol, null, ProtocolActionType.ABANDON_PROTOCOL);
//        protocolAction.setComments(protocolAbandonBean.getComments());
//        protocol.getProtocolActions().add(protocolAction);
//        protocolActionService.updateProtocolStatus(protocolAction, protocol);
//        protocol.setActive(false);
//        documentService.cancelDocument(protocol.getProtocolDocument(), null);
//        try {
////            IRBNotificationRenderer renderer = new IRBNotificationRenderer(protocol);
////            IRBNotificationContext context = new IRBNotificationContext(protocol, ProtocolActionType.ABANDON_PROTOCOL, "Abandon", renderer);
////            if (!isPromptUserForNotification) {
////                kcNotificationService.sendNotification(context);
////            }
//            createCorrespondenceAndAttach(protocol, ProtocolActionType.ABANDON_PROTOCOL);
//        } catch (Exception e) {
//            LOG.info("Abandon Protocol Notification exception " + e.getStackTrace());
//        }
//        
//    }
//
//    private void createCorrespondenceAndAttach(Protocol protocol, String protocolActionType) throws PrintingException {
//        ProtocolGenericCorrespondence correspondence = new ProtocolGenericCorrespondence(protocolActionType);
//        correspondence.setPrintableBusinessObject(protocol);
//        correspondence.setProtocol(protocol);
//        protocolActionCorrespondenceGenerationService.generateCorrespondenceDocumentAndAttach(correspondence);
//    }
//
//    public void setDocumentService(DocumentService documentService) {
//        this.documentService = documentService;
//    }
//
//    public void setProtocolActionService(ProtocolActionService protocolActionService) {
//        this.protocolActionService = protocolActionService;
//    }
//
//    public void setProtocolActionCorrespondenceGenerationService(
//            ProtocolActionCorrespondenceGenerationService protocolActionCorrespondenceGenerationService) {
//        this.protocolActionCorrespondenceGenerationService = protocolActionCorrespondenceGenerationService;
//    }
//    
//    public void setKcNotificationService(KcNotificationService kcNotificationService) {
//        this.kcNotificationService = kcNotificationService;
//    }

}

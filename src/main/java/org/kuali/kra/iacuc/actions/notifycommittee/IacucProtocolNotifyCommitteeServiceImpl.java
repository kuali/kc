/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.iacuc.actions.notifycommittee;

import java.sql.Timestamp;

import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.protocol.actions.submit.ProtocolActionService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.DocumentService;

/**
 * Protocol Request Service Implementation.
 */
public class IacucProtocolNotifyCommitteeServiceImpl implements IacucProtocolNotifyCommitteeService {
    
    private DocumentService documentService;
    private ProtocolActionService protocolActionService;

    /**
     * Set the business object service.
     * @param businessObjectService the business office service
     */
    public void setProtocolActionService(ProtocolActionService protocolActionService) {
        this.protocolActionService = protocolActionService;
    }

    /**
     * @throws WorkflowException 
     * @see org.kuali.kra.protocol.actions.notifycommittee.ProtocolNotifyCommitteeService#submitCommitteeNotification(org.kuali.kra.protocol.Protocol, org.kuali.kra.protocol.actions.notifycommittee.ProtocolNotifyCommitteeBean)
     */
    public void submitCommitteeNotification(IacucProtocol protocol, IacucProtocolNotifyCommitteeBean notifyCommitteeBean) throws WorkflowException {
        /*
         * The submission is created first so that its new primary key can be added
         * to the protocol action entry.
         */
        IacucProtocolAction protocolAction = new IacucProtocolAction(protocol, null, IacucProtocolActionType.NOTIFIED_COMMITTEE);
        protocolAction.setComments(notifyCommitteeBean.getComment());
        protocolAction.setActionDate(new Timestamp(notifyCommitteeBean.getActionDate().getTime()));
        protocol.getProtocolActions().add(protocolAction);
        protocolActionService.updateProtocolStatus(protocolAction, protocol);
        documentService.saveDocument(protocol.getProtocolDocument());
    }
    
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    
}

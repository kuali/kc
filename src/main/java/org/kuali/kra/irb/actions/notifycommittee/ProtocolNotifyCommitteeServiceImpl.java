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
package org.kuali.kra.irb.actions.notifycommittee;

import java.sql.Timestamp;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.DocumentService;

/**
 * Protocol Request Service Implementation.
 */
public class ProtocolNotifyCommitteeServiceImpl implements ProtocolNotifyCommitteeService {
    
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
     * @see org.kuali.kra.irb.actions.notifycommittee.ProtocolNotifyCommitteeService#submitCommitteeNotification(org.kuali.kra.irb.Protocol, org.kuali.kra.irb.actions.notifycommittee.ProtocolNotifyCommitteeBean)
     */
    public void submitCommitteeNotification(Protocol protocol, ProtocolNotifyCommitteeBean notifyCommitteeBean) throws WorkflowException {
        /*
         * The submission is created first so that its new primary key can be added
         * to the protocol action entry.
         */
        ProtocolAction protocolAction = new ProtocolAction(protocol, null, ProtocolActionType.NOTIFIED_COMMITTEE);
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

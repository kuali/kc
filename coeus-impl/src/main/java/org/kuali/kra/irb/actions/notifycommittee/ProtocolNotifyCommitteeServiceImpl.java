/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.irb.actions.notifycommittee;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.DocumentService;

import java.sql.Timestamp;

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
        // we do not create a new submission here unlike in notify IRB
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

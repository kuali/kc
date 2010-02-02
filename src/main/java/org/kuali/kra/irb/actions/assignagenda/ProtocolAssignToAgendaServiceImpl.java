/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.actions.assignagenda;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.rice.kns.service.DocumentService;


/**
 * 
 * This class implements ProtocolAssignToAgendaService.
 */
public class ProtocolAssignToAgendaServiceImpl implements ProtocolAssignToAgendaService {
    
    private DocumentService documentService;
    private ProtocolActionService protocolActionService;
    
    
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    
    public void setProtocolActionService(ProtocolActionService protocolActionService) {
        this.protocolActionService = protocolActionService;
    }
    
    private ProtocolSubmission findSubmission(Protocol protocol) {
        for (ProtocolSubmission submission : protocol.getProtocolSubmissions()) {
            if (StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.PENDING) 
                    || StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE)) {
                return submission;
            }
        }
        return null;
    }
    
    /** {@inheritDoc} */
    public void assignToAgenda(Protocol protocol, ProtocolAssignToAgendaBean actionBean) throws Exception {
        
        ProtocolSubmission submission = findSubmission(protocol);
        ProtocolAction protocolActionFound = getAssignedToAgendaProtocolAction(protocol);
        if (actionBean.isProtocolAssigned()) {
            if (protocolActionFound != null) {
                //add a new protocol action
                ProtocolAction protocolAction = new ProtocolAction(protocol, submission, ProtocolActionType.ASSIGN_TO_AGENDA);
                protocolAction.setComments(actionBean.getComments());
                protocolActionService.updateProtocolStatus(protocolAction, protocol);
                protocol.getProtocolActions().add(protocolAction);
                protocolAction.setActionDate(new Timestamp(actionBean.getScheduleDate().getTime()));
            } else {
                //update the comment of an existing protocol action
                ProtocolAction pa = getAssignedToAgendaProtocolAction(protocol);
                pa.setComments(actionBean.getComments());
                documentService.saveDocument(protocol.getProtocolDocument());
                return;
            }            
            documentService.saveDocument(protocol.getProtocolDocument());
        }else if(!actionBean.isProtocolAssigned() && protocolActionFound != null) {
            //un assign the protocol            
            ProtocolAction pa = getAssignedToAgendaProtocolAction(protocol);
            pa.setProtocolActionTypeCode(ProtocolActionType.DISAPPROVED);
            pa.setComments(actionBean.getComments());
            documentService.saveDocument(protocol.getProtocolDocument());
            return;
        }
        
    }
    
    /** {@inheritDoc} */
    public ProtocolAction getAssignedToAgendaProtocolAction(Protocol protocol) {
        Iterator<ProtocolAction> i = protocol.getProtocolActions().iterator();
        while (i.hasNext()) {
            ProtocolAction pa = i.next();
            if (pa.getProtocolActionType().getProtocolActionTypeCode().equals(ProtocolActionType.ASSIGN_TO_AGENDA)) {                
                return pa;
            }
        } 
        return null;
    }
}
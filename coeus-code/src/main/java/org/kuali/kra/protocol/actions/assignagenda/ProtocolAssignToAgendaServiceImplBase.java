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
package org.kuali.kra.protocol.actions.assignagenda;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.service.CommitteeServiceBase;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.submit.ProtocolActionService;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.rice.krad.service.DocumentService;

import java.sql.Timestamp;
import java.util.Iterator;

/**
 * 
 * This class implements ProtocolAssignToAgendaService.
 */
public abstract class ProtocolAssignToAgendaServiceImplBase implements ProtocolAssignToAgendaService {

    private DocumentService documentService;
    private ProtocolActionService protocolActionService;
    
    private CommitteeServiceBase committeeService;

    public CommitteeServiceBase getCommitteeService() {
        return committeeService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    
    public DocumentService getDocumentService() {
        return this.documentService;
    }

    public void setProtocolActionService(ProtocolActionService protocolActionService) {
        this.protocolActionService = protocolActionService;
    }
    
    public ProtocolActionService getProtocolActionService() {
        return this.protocolActionService;
    }
    
    public void setCommitteeService(CommitteeServiceBase committeeService) {
        this.committeeService = committeeService;        
    }    
    
    protected ProtocolSubmissionBase findSubmission(ProtocolBase protocol) {
        ProtocolSubmissionBase returnSubmission = null;
        for (ProtocolSubmissionBase submission : protocol.getProtocolSubmissions()) {
            if ((StringUtils.equals(submission.getSubmissionStatusCode(), getProtocolSubmissionStatusPendingCodeHook())
                    || StringUtils.equals(submission.getSubmissionStatusCode(), getProtocolSubmissionStatusSubmittedToCommitteeCodeHook()))
                    && (returnSubmission == null || returnSubmission.getSequenceNumber() < submission.getSequenceNumber())) {
                returnSubmission = submission;
            }
        }
        return returnSubmission;
    }
    

    protected abstract String getProtocolSubmissionStatusSubmittedToCommitteeCodeHook();

    protected abstract String getProtocolSubmissionStatusPendingCodeHook();
    
    

    @Override
    public void assignToAgenda(ProtocolBase protocol, ProtocolAssignToAgendaBean actionBean) throws Exception {

        ProtocolSubmissionBase submission = findSubmission(protocol);
        // add a new protocol action
        ProtocolActionBase protocolAction = getNewProtocolAssignToAgendaActionInstanceHook(protocol, submission);
        protocolAction.setComments(actionBean.getComments());
        protocolAction.setActionDate(new Timestamp(actionBean.getActionDate().getTime()));
        protocol.getProtocolActions().add(protocolAction);
        protocolActionService.updateProtocolStatus(protocolAction, protocol);
        documentService.saveDocument(protocol.getProtocolDocument());    
    }

    protected abstract ProtocolActionBase getNewProtocolAssignToAgendaActionInstanceHook(ProtocolBase protocol, ProtocolSubmissionBase submission);

    
    
    
    
    @Override
    public boolean isAssignedToAgenda(ProtocolBase protocol) {
        String protocolSubmissionStatusCode = protocol.getProtocolSubmission().getSubmissionStatusCode();
        
        return getProtocolSubmissionStatusInAgendaCodeHook().equals(protocolSubmissionStatusCode);
    }

    protected abstract String getProtocolSubmissionStatusInAgendaCodeHook();
    
    

    @Override
    public String getAssignToAgendaComments(ProtocolBase protocol) {
        ProtocolActionBase pa = getAssignedToAgendaProtocolAction(protocol);
        if (pa == null) {
            return "";
        } else {
            return pa.getComments();
        }
    }
    
    @Override
    public ProtocolActionBase getAssignedToAgendaProtocolAction(ProtocolBase protocol) {
        Iterator<ProtocolActionBase> i = protocol.getProtocolActions().iterator();
        ProtocolActionBase returnAction = null;
        while (i.hasNext()) {
            ProtocolActionBase pa = i.next();
            //the last check verifies the correct instance of the protocol version.
            if (pa.getProtocolActionType().getProtocolActionTypeCode().equals(getProtocolActionTypeAssignToAgendaCodeHook()) 
                    && (returnAction == null || returnAction.getSequenceNumber().intValue() < pa.getSequenceNumber().intValue())
                    && pa.getProtocolId().equals(protocol.getProtocolId())) {
                returnAction = pa;
            } 
        }
        return returnAction;
    }

    protected abstract String getProtocolActionTypeAssignToAgendaCodeHook();
    
    @Override
    public String getAssignedCommitteeName(ProtocolBase protocol) {
        String committeeID = getAssignedCommitteeId(protocol);
        if (committeeID != null) {
            CommitteeBase com = this.committeeService.getCommitteeById(committeeID);
            if (com != null) {
                String committeeName = com.getCommitteeName();
                return committeeName;
            }
        }
        return null;
    }
}

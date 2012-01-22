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
package org.kuali.kra.irb.actions.assignagenda;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.assigncmtsched.ProtocolAssignCmtSchedService;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.DocumentService;

/**
 * 
 * This class implements ProtocolAssignToAgendaService.
 */
public class ProtocolAssignToAgendaServiceImpl implements ProtocolAssignToAgendaService {
    
    private static final long serialVersionUID = 986748376;

    private DocumentService documentService;
    private ProtocolActionService protocolActionService;
    private ProtocolAssignCmtSchedService protocolAssignCmtSchedService;
    private CommitteeService committeeService;

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    public void setProtocolActionService(ProtocolActionService protocolActionService) {
        this.protocolActionService = protocolActionService;
    }
    
    public void setProtocolAssignCmtSchedService(ProtocolAssignCmtSchedService protocolAssignCmtSchedService) {
        this.protocolAssignCmtSchedService = protocolAssignCmtSchedService;        
    }
    
    public void setCommitteeService(CommitteeService committeeService) {
        this.committeeService = committeeService;        
    }

    protected ProtocolSubmission findSubmission(Protocol protocol) {
        ProtocolSubmission returnSubmission = null;
        for (ProtocolSubmission submission : protocol.getProtocolSubmissions()) {
            if ((StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.PENDING)
                    || StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE))
                    && (returnSubmission == null || returnSubmission.getSequenceNumber() < submission.getSequenceNumber())) {
                returnSubmission = submission;
            }
        }
        return returnSubmission;
    }

    /** {@inheritDoc} */
    public void assignToAgenda(Protocol protocol, ProtocolAssignToAgendaBean actionBean) throws Exception {

        ProtocolSubmission submission = findSubmission(protocol);
        // add a new protocol action
        ProtocolAction protocolAction = new ProtocolAction(protocol, submission, ProtocolActionType.ASSIGN_TO_AGENDA);
        protocolAction.setComments(actionBean.getComments());
        protocolAction.setActionDate(new Timestamp(actionBean.getActionDate().getTime()));
        protocol.getProtocolActions().add(protocolAction);
        protocolActionService.updateProtocolStatus(protocolAction, protocol);
        documentService.saveDocument(protocol.getProtocolDocument());    
    }

    /** {@inheritDoc} */
    public boolean isAssignedToAgenda(Protocol protocol) {
        String protocolSubmissionStatusCode = protocol.getProtocolSubmission().getSubmissionStatusCode();
        
        return ProtocolSubmissionStatus.IN_AGENDA.equals(protocolSubmissionStatusCode);
    }

    /** {@inheritDoc} */
    public String getAssignToAgendaComments(Protocol protocol) {
        ProtocolAction pa = getAssignedToAgendaProtocolAction(protocol);
        if (pa == null) {
            return "";
        } else {
            return pa.getComments();
        }
    }
    
    /** {@inheritDoc} */
    public ProtocolAction getAssignedToAgendaProtocolAction(Protocol protocol) {
        Iterator<ProtocolAction> i = protocol.getProtocolActions().iterator();
        ProtocolAction returnAction = null;
        while (i.hasNext()) {
            ProtocolAction pa = i.next();
            //the last check verifies the correct instance of the protcol version.
            if (pa.getProtocolActionType().getProtocolActionTypeCode().equals(ProtocolActionType.ASSIGN_TO_AGENDA) 
                    && (returnAction == null || returnAction.getSequenceNumber().intValue() < pa.getSequenceNumber().intValue())
                    && pa.getProtocolId().equals(protocol.getProtocolId())) {
                returnAction = pa;
            } 
        }
        return returnAction;
    }

    protected ProtocolAction getSubmitToIrbProtocolAction(Protocol protocol) {
        Iterator<ProtocolAction> i = protocol.getProtocolActions().iterator();
        ProtocolAction returnAction = null;
        while (i.hasNext()) {
            ProtocolAction pa = i.next();
            if (pa.getProtocolActionType().getProtocolActionTypeCode().equals(ProtocolActionType.SUBMIT_TO_IRB) 
                    && (returnAction == null || returnAction.getSequenceNumber().intValue() < pa.getSequenceNumber().intValue() )) {
                returnAction = pa;
            }else if(pa.getProtocolActionType().getProtocolActionTypeCode().equals(ProtocolActionType.WITHDRAWN)){
                returnAction = null;
            }
        }
        // no proper protocol action found, return null
        return returnAction;
    }

    /** {@inheritDoc} */
    public String getAssignedCommitteeId(Protocol protocol) {
        String committeeID = this.protocolAssignCmtSchedService.getAssignedCommitteeId(protocol);
        return committeeID;
    }

    /** {@inheritDoc} */
    public String getAssignedCommitteeName(Protocol protocol) {
        String committeeID = getAssignedCommitteeId(protocol);
        if (committeeID != null) {
            Committee com = this.committeeService.getCommitteeById(committeeID);
            if (com != null) {
                String committeeName = com.getCommitteeName();
                return committeeName;
            }
        }
        return null;
    }

    /** {@inheritDoc} */
    public String getAssignedScheduleDate(Protocol protocol) {
        String scheduleId = this.protocolAssignCmtSchedService.getAssignedScheduleId(protocol);
        List<KeyValue> keyPair = this.committeeService.getAvailableCommitteeDates(getAssignedCommitteeId(protocol));
        for (KeyValue kp : keyPair) {
            if (kp.getKey().equals(scheduleId)) {
                return kp.getValue();
            }
        }
        return null;
    }

}

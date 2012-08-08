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
package org.kuali.kra.protocol.actions.assignagenda;

import java.sql.Timestamp;
import java.util.Iterator;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.common.committee.bo.CommonCommittee;
import org.kuali.kra.common.committee.service.CommonCommitteeService;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.actions.ProtocolAction;
import org.kuali.kra.protocol.actions.submit.ProtocolActionService;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmission;
import org.kuali.rice.krad.service.DocumentService;

/**
 * 
 * This class implements ProtocolAssignToAgendaService.
 */
public abstract class ProtocolAssignToAgendaServiceImpl implements ProtocolAssignToAgendaService {
    
    private static final long serialVersionUID = 986748376;

    private DocumentService documentService;
    private ProtocolActionService protocolActionService;

    
    
// TODO *********commented the code below during IACUC refactoring*********     
//    private ProtocolAssignCmtSchedService protocolAssignCmtSchedService;
    
    
    
    private CommonCommitteeService committeeService;

    public CommonCommitteeService getCommitteeService() {
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
 
    
    
// TODO *********commented the code below during IACUC refactoring*********     
//    public void setProtocolAssignCmtSchedService(ProtocolAssignCmtSchedService protocolAssignCmtSchedService) {
//        this.protocolAssignCmtSchedService = protocolAssignCmtSchedService;        
//    }
    
    
    
    public void setCommitteeService(CommonCommitteeService committeeService) {
        this.committeeService = committeeService;        
    }

    
    
    protected ProtocolSubmission findSubmission(Protocol protocol) {
        ProtocolSubmission returnSubmission = null;
        for (ProtocolSubmission submission : protocol.getProtocolSubmissions()) {
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
    
    

    /** {@inheritDoc} */
    public void assignToAgenda(Protocol protocol, ProtocolAssignToAgendaBean actionBean) throws Exception {

        ProtocolSubmission submission = findSubmission(protocol);
        // add a new protocol action
        ProtocolAction protocolAction = getNewProtocolAssignToAgendaActionInstanceHook(protocol, submission);
        protocolAction.setComments(actionBean.getComments());
        protocolAction.setActionDate(new Timestamp(actionBean.getActionDate().getTime()));
        protocol.getProtocolActions().add(protocolAction);
        protocolActionService.updateProtocolStatus(protocolAction, protocol);
        documentService.saveDocument(protocol.getProtocolDocument());    
    }

    protected abstract ProtocolAction getNewProtocolAssignToAgendaActionInstanceHook(Protocol protocol, ProtocolSubmission submission);

    
    
    
    
    /** {@inheritDoc} */
    public boolean isAssignedToAgenda(Protocol protocol) {
        String protocolSubmissionStatusCode = protocol.getProtocolSubmission().getSubmissionStatusCode();
        
        return getProtocolSubmissionStatusInAgendaCodeHook().equals(protocolSubmissionStatusCode);
    }

    protected abstract String getProtocolSubmissionStatusInAgendaCodeHook();
    
    

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
    
    
// TODO *********commented the code below during IACUC refactoring*********     
//    protected ProtocolAction getSubmitToIrbProtocolAction(Protocol protocol) {
//        Iterator<ProtocolAction> i = protocol.getProtocolActions().iterator();
//        ProtocolAction returnAction = null;
//        while (i.hasNext()) {
//            ProtocolAction pa = i.next();
//            if (pa.getProtocolActionType().getProtocolActionTypeCode().equals(ProtocolActionType.SUBMIT_TO_IRB) 
//                    && (returnAction == null || returnAction.getSequenceNumber().intValue() < pa.getSequenceNumber().intValue() )) {
//                returnAction = pa;
//            }else if(pa.getProtocolActionType().getProtocolActionTypeCode().equals(ProtocolActionType.WITHDRAWN)){
//                returnAction = null;
//            }
//        }
//        // no proper protocol action found, return null
//        return returnAction;
//    }

    
    
// TODO *********commented the code below during IACUC refactoring*********     
//    /** {@inheritDoc} */
//    public String getAssignedCommitteeId(Protocol protocol) {
//        String committeeID = this.protocolAssignCmtSchedService.getAssignedCommitteeId(protocol);
//        return committeeID;
//    }

    
    
    /** {@inheritDoc} */
    public String getAssignedCommitteeName(Protocol protocol) {
        String committeeID = getAssignedCommitteeId(protocol);
        if (committeeID != null) {
            CommonCommittee com = this.committeeService.getCommitteeById(committeeID);
            if (com != null) {
                String committeeName = com.getCommitteeName();
                return committeeName;
            }
        }
        return null;
    }

    
    
// TODO *********commented the code below during IACUC refactoring*********     
//    /** {@inheritDoc} */
//    public String getAssignedScheduleDate(Protocol protocol) {
//        String scheduleId = this.protocolAssignCmtSchedService.getAssignedScheduleId(protocol);
//        List<KeyValue> keyPair = this.committeeService.getAvailableCommitteeDates(getAssignedCommitteeId(protocol));
//        for (KeyValue kp : keyPair) {
//            if (kp.getKey().equals(scheduleId)) {
//                return kp.getValue();
//            }
//        }
//        return null;
//    }

}

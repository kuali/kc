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
package org.kuali.kra.iacuc.actions.assignagenda;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.assignCmt.IacucProtocolAssignCmtService;
import org.kuali.kra.iacuc.actions.genericactions.IacucProtocolGenericActionBean;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionStatus;
import org.kuali.kra.protocol.actions.ProtocolAction;
import org.kuali.kra.protocol.actions.assignagenda.ProtocolAssignToAgendaBean;
import org.kuali.kra.protocol.actions.assignagenda.ProtocolAssignToAgendaServiceImpl;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmission;
import org.kuali.kra.protocol.Protocol;
import org.kuali.rice.core.api.util.KeyValue;



/**
 * 
 * This class implements ProtocolAssignToAgendaService.
 */
public class IacucProtocolAssignToAgendaServiceImpl extends ProtocolAssignToAgendaServiceImpl implements IacucProtocolAssignToAgendaService {
    
    private IacucProtocolAssignCmtService protocolAssignCmtService;

    
    public void setProtocolAssignCmtService(IacucProtocolAssignCmtService protocolAssignCmtService) {
        this.protocolAssignCmtService = protocolAssignCmtService;
    }


    @Override
    public String getAssignedCommitteeId(Protocol protocol) {
        return this.protocolAssignCmtService.getAssignedCommitteeId(protocol);
    }

    @Override
    public String getAssignedScheduleDate(Protocol protocol) {
        String scheduleId = this.protocolAssignCmtService.getAssignedScheduleId(protocol);
        List<KeyValue> keyPair = this.getCommitteeService().getAvailableCommitteeDates(getAssignedCommitteeId(protocol));
        for (KeyValue kp : keyPair) {
            if (kp.getKey().equals(scheduleId)) {
                return kp.getValue();
            }
        }
        return null;
    }
    
    public void removeFromAgenda(IacucProtocol protocol, IacucProtocolGenericActionBean actionBean) throws Exception {

        IacucProtocolSubmission submission = (IacucProtocolSubmission) findLastSubmission(protocol);
        submission.setSubmissionStatusCode("102");
        // add a new protocol action
        ProtocolAction protocolAction = getNewProtocolRemoveFromAgendaActionInstanceHook(protocol, submission);
        protocolAction.setComments(actionBean.getComments());
        protocolAction.setActionDate(new Timestamp(actionBean.getActionDate().getTime()));
        protocol.getProtocolActions().add(protocolAction);
        getProtocolActionService().updateProtocolStatus(protocolAction, protocol);
        getDocumentService().saveDocument(protocol.getProtocolDocument());    
    }
    
    protected ProtocolSubmission findLastSubmission(Protocol protocol) {
        ProtocolSubmission returnSubmission = null;
        for (ProtocolSubmission submission : protocol.getProtocolSubmissions()) {
            if (returnSubmission == null || returnSubmission.getSequenceNumber() < submission.getSequenceNumber()) {
                returnSubmission = submission;
            }
        }
        return returnSubmission;
    }
    
    public ProtocolAction getNewProtocolRemoveFromAgendaActionInstanceHook(IacucProtocol protocol, IacucProtocolSubmission submission) {
        return new IacucProtocolAction( protocol, submission, IacucProtocolActionType.REMOVE_FROM_AGENDA);
    }
        

    @Override
    protected String getProtocolSubmissionStatusSubmittedToCommitteeCodeHook() {
        return IacucProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE;
    }
    

    @Override
    protected String getProtocolSubmissionStatusPendingCodeHook() {
        return IacucProtocolSubmissionStatus.PENDING;
    }
    

    @Override
    protected ProtocolAction getNewProtocolAssignToAgendaActionInstanceHook(Protocol protocol, ProtocolSubmission submission) {
        return new IacucProtocolAction( (IacucProtocol)protocol, (IacucProtocolSubmission) submission, IacucProtocolActionType.ASSIGNED_TO_AGENDA);
    }

    @Override
    protected String getProtocolActionTypeAssignToAgendaCodeHook() {
        return IacucProtocolActionType.ASSIGNED_TO_AGENDA;
    }
    

    @Override
    protected String getProtocolSubmissionStatusInAgendaCodeHook() {
        return IacucProtocolSubmissionStatus.IN_AGENDA;
    }
    

}

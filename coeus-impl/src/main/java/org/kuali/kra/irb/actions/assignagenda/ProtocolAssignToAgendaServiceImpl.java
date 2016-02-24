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
package org.kuali.kra.irb.actions.assignagenda;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.assigncmtsched.ProtocolAssignCmtSchedService;
import org.kuali.kra.irb.actions.expeditedapprove.ProtocolExpeditedApproveBean;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.assignagenda.ProtocolAssignToAgendaServiceImplBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.rice.core.api.util.KeyValue;

import java.sql.Timestamp;
import java.util.List;

/**
 * 
 * This class implements ProtocolAssignToAgendaService.
 */
public class ProtocolAssignToAgendaServiceImpl extends ProtocolAssignToAgendaServiceImplBase implements ProtocolAssignToAgendaService {

    private ProtocolAssignCmtSchedService protocolAssignCmtSchedService;
    
    public void setProtocolAssignCmtSchedService(ProtocolAssignCmtSchedService protocolAssignCmtSchedService) {
        this.protocolAssignCmtSchedService = protocolAssignCmtSchedService;        
    }
    
    protected String getProtocolSubmissionStatusSubmittedToCommitteeCodeHook() {
        return ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE;
    }

    protected String getProtocolSubmissionStatusPendingCodeHook() {
        return ProtocolSubmissionStatus.PENDING;
    }

    protected ProtocolActionBase getNewProtocolAssignToAgendaActionInstanceHook(ProtocolBase protocol, ProtocolSubmissionBase submission) {
        return new ProtocolAction((Protocol) protocol, (ProtocolSubmission) submission, ProtocolActionType.ASSIGN_TO_AGENDA);
    }

    @Override
    public void assignToAgenda(Protocol protocol, ProtocolExpeditedApproveBean actionBean) throws Exception {

        ProtocolSubmission submission = (ProtocolSubmission) findSubmission(protocol);
        // add a new protocol action
        ProtocolAction protocolAction = new ProtocolAction(protocol, submission, ProtocolActionType.ASSIGN_TO_AGENDA);
        protocolAction.setComments(actionBean.getComments());
        protocolAction.setActionDate(new Timestamp(actionBean.getActionDate().getTime()));
        protocol.getProtocolActions().add(protocolAction);
        getProtocolActionService().updateProtocolStatus(protocolAction, protocol);
        getDocumentService().saveDocument(protocol.getProtocolDocument());    
    }
    
    protected String getProtocolSubmissionStatusInAgendaCodeHook() {
        return ProtocolSubmissionStatus.IN_AGENDA;
    }
    
    protected String getProtocolActionTypeAssignToAgendaCodeHook() {
        return ProtocolActionType.ASSIGN_TO_AGENDA;
    }

    @Override
    public String getAssignedCommitteeId(ProtocolBase protocol) {
        String committeeID = this.protocolAssignCmtSchedService.getAssignedCommitteeId((Protocol) protocol);
        return committeeID;
    }

    @Override
    public String getAssignedScheduleDate(ProtocolBase protocol) {
        String scheduleId = this.protocolAssignCmtSchedService.getAssignedScheduleId((Protocol) protocol);
        List<KeyValue> keyPair = this.getCommitteeService().getAvailableCommitteeDates(getAssignedCommitteeId(protocol));
        for (KeyValue kp : keyPair) {
            if (kp.getKey().equals(scheduleId)) {
                return kp.getValue();
            }
        }
        return null;
    }
}

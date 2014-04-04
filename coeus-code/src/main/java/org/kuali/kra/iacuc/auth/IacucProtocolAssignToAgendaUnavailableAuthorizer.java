/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.iacuc.auth;

import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;

/**
 * Determine if a user can assign a protocol to a committee/schedule.
 */
public class IacucProtocolAssignToAgendaUnavailableAuthorizer extends IacucProtocolAuthorizer {

    private KcWorkflowService kraWorkflowService;

    @Override
    public boolean isAuthorized(String username, IacucProtocolTask task) {
        ProtocolBase protocol = task.getProtocol();
        return !( kraWorkflowService.isInWorkflow(protocol.getProtocolDocument())
                && kraWorkflowService.isCurrentNode(protocol.getProtocolDocument(), Constants.PROTOCOL_IACUCREVIEW_ROUTE_NODE_NAME)
                && canExecuteAction(protocol, IacucProtocolActionType.ASSIGNED_TO_AGENDA) 
                && !isAssignedToCommittee(protocol)
                )
                && hasPermission(username, protocol, PermissionConstants.PERFORM_IACUC_ACTIONS_ON_PROTO);
    }

    public KcWorkflowService getKraWorkflowService() {
        return kraWorkflowService;
    }
    
    public void setKraWorkflowService(KcWorkflowService kraWorkflowService) {
        this.kraWorkflowService = kraWorkflowService;
    }    
    
    /**
     * Is the protocol's submission in a pending or submitted to committee status?
     * 
     * @param protocol
     * @return
     */
    private boolean isAssignedToCommittee(ProtocolBase protocol) {
        ProtocolSubmissionBase ps = findSubmission(protocol);
        return ps != null && ps.getCommitteeSchedule() != null && ps.getCommitteeSchedule().getScheduledDate() != null;
    }
}

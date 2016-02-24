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
package org.kuali.kra.irb.auth;

import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;

/**
 * Determine if a user can assign a protocol to a committee/schedule.
 */
public class ProtocolAssignToAgendaAuthorizer extends ProtocolAuthorizer {

    private KcWorkflowService kraWorkflowService;

    @Override
    public boolean isAuthorized(String username, ProtocolTask task) {
        Protocol protocol = task.getProtocol();
        return kraWorkflowService.isInWorkflow(protocol.getProtocolDocument())
                && kraWorkflowService.isCurrentNode(protocol.getProtocolDocument(), Constants.PROTOCOL_IRBREVIEW_ROUTE_NODE_NAME)
                && isAssignedToCommittee(protocol)
                && hasPermission(username, protocol, PermissionConstants.PERFORM_IRB_ACTIONS_ON_PROTO);
    }

    /**
     * Is the protocol's submission in a pending or submitted to committee status?
     * 
     * @param protocol
     * @return
     */
    private boolean isAssignedToCommittee(Protocol protocol) {
        ProtocolSubmission ps = findSubmission(protocol);
        return ps != null && ps.getCommitteeSchedule() != null && ps.getCommitteeSchedule().getScheduledDate() != null;
    }
    
    public KcWorkflowService getKraWorkflowService() {
        return kraWorkflowService;
    }

    public void setKraWorkflowService(KcWorkflowService kraWorkflowService) {
        this.kraWorkflowService = kraWorkflowService;
    }

}

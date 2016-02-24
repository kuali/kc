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
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;

/**
 * Is the user allowed to record committee decisions for a protocol?
 */
public class RecordCommitteeDecisionProtocolAuthorizer extends ProtocolAuthorizer {

    private KcWorkflowService kraWorkflowService;

    @Override
    public boolean isAuthorized(String userId, ProtocolTask task) {
        return kraWorkflowService.isInWorkflow(task.getProtocol().getProtocolDocument()) &&
                canExecuteAction(task.getProtocol(), ProtocolActionType.RECORD_COMMITTEE_DECISION) && 
               hasPermission(userId, task.getProtocol(), PermissionConstants.PERFORM_IRB_ACTIONS_ON_PROTO) &&
               canRecordCommitteeDecision(task.getProtocol().getLastProtocolAction());
    }
    
    /**
     * 
     * record committee decision doesn't change the protocol status, after one record committee decision as approve, then
     * the approve screen comes up, this function prevents the record committee decision from remianing open after the action
     * has been done.
     * @param lastAction
     * @return
     */
    private boolean canRecordCommitteeDecision(ProtocolAction lastAction) {
        if(lastAction != null && !ProtocolActionType.RECORD_COMMITTEE_DECISION.equals(lastAction.getProtocolActionTypeCode())) {
            return true;
        }
                
        return false;
    }

    public KcWorkflowService getKraWorkflowService() {
        return kraWorkflowService;
    }

    public void setKraWorkflowService(KcWorkflowService kraWorkflowService) {
        this.kraWorkflowService = kraWorkflowService;
    }
}

/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.iacuc.auth;

import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;

/**
 * Determine if a user can return a protocol to a principal investigator.
 */
public class IacucReturnToPIUnavailableAuthorizer extends IacucProtocolAuthorizer {

    private KcWorkflowService kraWorkflowService;

    @Override
    public boolean isAuthorized(String username, IacucProtocolTask task) {
        if ( (!kraWorkflowService.isInWorkflow(task.getProtocol().getProtocolDocument()) ||
              !kraWorkflowService.isDocumentOnNode(task.getProtocol().getProtocolDocument(), Constants.IACUC_PROTOCOL_IACUCREVIEW_ROUTE_NODE_NAME) ||              
              !canExecuteAction(task.getProtocol(), IacucProtocolActionType.RETURNED_TO_PI) ) &&
              hasPermission(username, task.getProtocol(), PermissionConstants.PERFORM_IACUC_ACTIONS_ON_PROTO) ) {
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

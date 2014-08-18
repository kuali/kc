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

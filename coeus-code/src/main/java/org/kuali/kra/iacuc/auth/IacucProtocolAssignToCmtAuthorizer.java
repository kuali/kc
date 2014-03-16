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
package org.kuali.kra.iacuc.auth;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionStatus;
import org.kuali.kra.infrastructure.PermissionConstants;

public class IacucProtocolAssignToCmtAuthorizer extends IacucProtocolAuthorizer {

    private KcWorkflowService kraWorkflowService;

    @Override
    public boolean isAuthorized(String userId, IacucProtocolTask task) {
        IacucProtocol protocol = task.getProtocol();
        return kraWorkflowService.isInWorkflow(protocol.getProtocolDocument()) &&
                // this is where the drools are used, in the protocolActionService
               canExecuteAction(task.getProtocol(), IacucProtocolActionType.NOTIFIED_COMMITTEE) &&
               !StringUtils.equals(IacucProtocolSubmissionStatus.APPROVED, protocol.getProtocolSubmission().getSubmissionStatusCode()) &&
               hasPermission(userId, protocol, PermissionConstants.PERFORM_IACUC_ACTIONS_ON_PROTO);
   
    }

    public KcWorkflowService getKraWorkflowService() {
        return kraWorkflowService;
    }

    public void setKraWorkflowService(KcWorkflowService kraWorkflowService) {
        this.kraWorkflowService = kraWorkflowService;
    }
}

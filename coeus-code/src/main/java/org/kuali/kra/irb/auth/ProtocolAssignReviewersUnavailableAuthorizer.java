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
package org.kuali.kra.irb.auth;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;

/**
 * Determine if a user can assign a protocol to a committee/schedule.
 */
public class ProtocolAssignReviewersUnavailableAuthorizer extends ProtocolAuthorizer {

    private KcWorkflowService kraWorkflowService;

    @Override
    public boolean isAuthorized(String username, ProtocolTask task) {
        Protocol protocol = task.getProtocol();
        return (!kraWorkflowService.isCurrentNode(protocol.getProtocolDocument(), Constants.PROTOCOL_IRBREVIEW_ROUTE_NODE_NAME) ||
                !isPendingOrSubmittedToCommittee(protocol) ||
                !(isInSchedule(protocol) || canPerformActionOnExpedited(protocol) || isNotifyIrbSubmission(protocol))) &&
               hasPermission(username, protocol, PermissionConstants.PERFORM_IRB_ACTIONS_ON_PROTO);
    }

    /**
     * Is the protocol's submission in a pending or submitted to committee status?
     * @param protocol
     * @return
     */
    private boolean isPendingOrSubmittedToCommittee(Protocol protocol) {
        return findSubmission(protocol) != null;
    }
    
    /**
     * Is the submission assigned to a committee and schedule?
     * @param protocol
     * @return
     */
    private boolean isInSchedule(Protocol protocol) {
        ProtocolSubmission submission = findSubmission(protocol);
        return submission != null &&
               !StringUtils.isBlank(submission.getCommitteeId()) &&
               !StringUtils.isBlank(submission.getScheduleId());
    }
    
    /**
     * Is the submission Notify IRB?
     * @param protocol
     * @return
     */
    private boolean isNotifyIrbSubmission(Protocol protocol) {
        ProtocolSubmission submission = findSubmission(protocol);
        return submission != null && ProtocolSubmissionType.NOTIFY_IRB.equals(submission.getProtocolSubmissionType().getSubmissionTypeCode());
    }
}

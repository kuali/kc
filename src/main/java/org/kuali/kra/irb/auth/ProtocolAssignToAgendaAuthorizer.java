/*
 * Copyright 2005-2010 The Kuali Foundation
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

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;

/**
 * Determine if a user can assign a protocol to a committee/schedule.
 */
public class ProtocolAssignToAgendaAuthorizer extends ProtocolAuthorizer {

    /** {@inheritDoc} */
    @Override
    public boolean isAuthorized(String username, ProtocolTask task) {
        Protocol protocol = task.getProtocol();
        return kraWorkflowService.isInWorkflow(protocol.getProtocolDocument())
                && kraWorkflowService.isDocumentOnNode(protocol.getProtocolDocument(), Constants.PROTOCOL_IRBREVIEW_ROUTE_NODE_NAME)
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

    /**
     * Find the submission. It is the submission that is either currently pending or already submitted to a committee.
     * 
     * @param protocol
     * @return
     */
    private ProtocolSubmission findSubmission(Protocol protocol) {

        // need to loop thru to find the last submission.
        // it may have submit/Wd/notify irb/submit, and this will cause problem if don't loop thru.
        ProtocolSubmission protocolSubmission = null;
        for (ProtocolSubmission submission : protocol.getProtocolSubmissions()) {
            if (StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.PENDING)
                    || StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE)) {
                protocolSubmission = submission;
            }
        }
        return protocolSubmission;
    }


}

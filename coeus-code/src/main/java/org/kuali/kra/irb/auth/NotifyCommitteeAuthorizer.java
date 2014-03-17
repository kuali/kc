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
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;

/**
 * Is the user allowed to notify the IRB office?
 */
public class NotifyCommitteeAuthorizer extends ProtocolAuthorizer {

    @Override
    public boolean isAuthorized(String userId, ProtocolTask task) {
        return isStatusValid(task.getProtocol()) &&
        canExecuteAction(task.getProtocol(), ProtocolActionType.NOTIFIED_COMMITTEE) &&
        hasPermission(userId, task.getProtocol(), PermissionConstants.PERFORM_IRB_ACTIONS_ON_PROTO) && hasCommitteeId(task.getProtocol());
    }
    
    private boolean isStatusValid(Protocol protocol) {
        String submissionStatusCode = protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode();
        return StringUtils.isNotBlank(submissionStatusCode)
                && (ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE.equals(submissionStatusCode));
    }

}

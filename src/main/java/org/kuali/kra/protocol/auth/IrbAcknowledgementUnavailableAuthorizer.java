/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.protocol.auth;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;

/**
 * 
 * This class to check the authorization to perform IRB acknowledgement action and the action is currently not available.
 */
public class IrbAcknowledgementUnavailableAuthorizer extends ProtocolAuthorizerBase {

    /** {@inheritDoc} */
    @Override
    public boolean isAuthorized(String userId, ProtocolTaskBase task) {
        return !isValidToPerform(task)
                && hasPermission(userId, task.getProtocol(), PermissionConstants.PERFORM_IRB_ACTIONS_ON_PROTO);
    }

    private boolean isValidToPerform(ProtocolTaskBase task) {
        boolean isValid = false;
        ProtocolBase protocol = task.getProtocol();
        if (protocol.getNotifyIrbSubmissionId() != null) {
            // not the current submission, then check programically
            for (ProtocolSubmissionBase submission : protocol.getProtocolSubmissions()) {
                if (submission.getSubmissionId().equals(protocol.getNotifyIrbSubmissionId())) {
                    isValid = isValidFYI(submission);
                }
            }
        }
        if (!isValid) {
            // if not valid, then this submission is not for ack, so not needed.
            protocol.setNotifyIrbSubmissionId(null);
            isValid = canExecuteAction(task.getProtocol(), ProtocolActionType.IRB_ACKNOWLEDGEMENT);
        }
        return isValid;
    }

    private boolean isValidFYI(ProtocolSubmissionBase submission) {
        return isFYISubmission(submission.getSubmissionTypeCode()) && isFYIReview(submission.getProtocolReviewTypeCode())
                && isStatusValid(submission.getSubmissionStatusCode());
    }


    private boolean isFYISubmission(String submissionTypeCode) {
        return StringUtils.isNotBlank(submissionTypeCode) && ProtocolSubmissionType.NOTIFY_IRB.equals(submissionTypeCode);
    }

    private boolean isFYIReview(String reviewTypeCode) {
        // in coeus 4.4, it does not check whether its FYI or not.
        return StringUtils.isNotBlank(reviewTypeCode);
    }

    private boolean isStatusValid(String submissionStatusCode) {
        return StringUtils.isNotBlank(submissionStatusCode)
                && (ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE.equals(submissionStatusCode) || ProtocolSubmissionStatus.IN_AGENDA
                        .equals(submissionStatusCode));
    }
}

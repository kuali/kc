/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.irb.auth;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;

/**
 * 
 * This class to check the authorization to perform IRB acknowledgement action.
 */
public class IrbAcknowledgementAuthorizer extends ProtocolAuthorizer {

    /** {@inheritDoc} */
    @Override
    public boolean isAuthorized(String userId, ProtocolTask task) {
        return isValidToPerform(task)
                && hasPermission(userId, task.getProtocol(), PermissionConstants.PERFORM_IRB_ACTIONS_ON_PROTO);
    }

    private boolean isValidToPerform(ProtocolTask task) {
        boolean isValid = false;
        Protocol protocol = task.getProtocol();
        if (protocol.getNotifyIrbSubmissionId() != null) {
            // not the current submission, then check programically
            for (ProtocolSubmission submission : protocol.getProtocolSubmissions()) {
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

    private boolean isValidFYI(ProtocolSubmission submission) {
        return isFYISubmission(submission.getSubmissionTypeCode()) && isFYIReview(submission.getProtocolReviewTypeCode())
                && isStatusValid(submission.getSubmissionStatusCode());
    }


    private boolean isFYISubmission(String submissionTypeCode) {
        return StringUtils.isNotBlank(submissionTypeCode) && ProtocolSubmissionType.NOTIFY_IRB.equals(submissionTypeCode);
    }

    private boolean isFYIReview(String reviewTypeCode) {
        // in coeus 4.4, it does not check whether its FYI or not.
        return StringUtils.isNotBlank(reviewTypeCode);
       // return StringUtils.isNotBlank(reviewTypeCode) && ProtocolReviewType.FYI_TYPE_CODE.equals(reviewTypeCode);
    }

    private boolean isStatusValid(String submissionStatusCode) {
        return StringUtils.isNotBlank(submissionStatusCode)
                && (ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE.equals(submissionStatusCode) || ProtocolSubmissionStatus.IN_AGENDA
                        .equals(submissionStatusCode));
    }
}

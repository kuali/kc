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

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;

/**
 * 
 * This class to check the authorization to perform IRB acknowledgement action and the action is currently not available.
 */
public class IrbAcknowledgementUnavailableAuthorizer extends ProtocolAuthorizer {

    @Override
    public boolean isAuthorized(String userId, ProtocolTask task) {
        return !isValidToPerform(task)
                && hasPermission(userId, task.getProtocol(), PermissionConstants.PERFORM_IRB_ACTIONS_ON_PROTO);
    }

    private boolean isValidToPerform(ProtocolTask task) {
        boolean isValid = false;
        Protocol protocol = task.getProtocol();
        if (protocol.getNotifyIrbSubmissionId() != null) {
            // not the current submission, then check programically
            for (ProtocolSubmissionBase submission : protocol.getProtocolSubmissions()) {
                if (submission.getSubmissionId().equals(protocol.getNotifyIrbSubmissionId())) {
                    isValid = isValidFYI((ProtocolSubmission) submission);
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
    }

    private boolean isStatusValid(String submissionStatusCode) {
        return StringUtils.isNotBlank(submissionStatusCode)
                && (ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE.equals(submissionStatusCode) || ProtocolSubmissionStatus.IN_AGENDA
                        .equals(submissionStatusCode));
    }
}

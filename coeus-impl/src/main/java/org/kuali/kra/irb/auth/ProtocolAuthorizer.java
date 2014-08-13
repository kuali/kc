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
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.submit.ProtocolReviewType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.auth.ProtocolAuthorizerBase;
import org.kuali.kra.protocol.auth.ProtocolTaskBase;
import org.kuali.rice.krad.util.ObjectUtils;

/**
 * A Protocol Authorizer determines if a user can perform
 * a given task on a protocol.  
 */
public abstract class ProtocolAuthorizer extends ProtocolAuthorizerBase {
    
    private static final String NAMESPACE = "KC-UNT";
  
    public final boolean isAuthorized(String userId, ProtocolTaskBase task) {
        return isAuthorized(userId, (ProtocolTask) task);
    }
    
    
    /**
     * Is the user authorized to execute the given protocol task?
     * @param username the user's unique username
     * @param task the protocol task
     * @return true if the user is authorized; otherwise false
     */
    public abstract boolean isAuthorized(String userId, ProtocolTask task);
        
    protected final boolean hasCommitteeId(Protocol protocol) {
        return protocol.getProtocolSubmission().getCommitteeId() != null;
    }
    
    public boolean isIrbAdmin(String userId) {
        return kraAuthorizationService.hasRole(userId, NAMESPACE, RoleConstants.IRB_ADMINISTRATOR);
    }
    
    /**
     * Find the submission.  It is the submission that is either currently pending or
     * already submitted to a committee. 
     * @param protocol
     * @return
     */
    protected ProtocolSubmission findSubmission(Protocol protocol) {
        return findSubmission(protocol, false);
    }
    
    
    /**
     * Find the submission.  It is the submission that is either currently pending or
     * already submitted to a committee, or is in agenda.
     * @param protocol
     * @return
     */
    protected ProtocolSubmission findSubmissionIncludingInAgenda(Protocol protocol) {
        return findSubmission(protocol, true);
    }
    
    /**
     * Find the submission.  It is the submission that is either currently pending or
     * already submitted to a committee, or is in agenda if the includeInAgenda parameter is set. 
     * @param protocol
     * @return
     */
    private ProtocolSubmission findSubmission(Protocol protocol, boolean includeInAgenda) {
        // need to loop thru to find the last submission.
        // it may have submit/Wd/notify irb/submit, and this will cause problem if don't loop thru.
        ProtocolSubmission protocolSubmission = null;
        for (ProtocolSubmissionBase submission : protocol.getProtocolSubmissions()) {
            if ( StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.PENDING) 
                 ||
                 StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE) 
                 ||
                 (includeInAgenda && StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.IN_AGENDA)) ) {
                protocolSubmission = (ProtocolSubmission)submission;
            }
        }
        return protocolSubmission;
    }
    
    /**
     * Is the submission a request for suspension?
     * @param protocol
     * @return true when the protocol submission type is request for suspension; otherwise false
     */
    protected boolean isRequestForSuspension(Protocol protocol) {
        ProtocolSubmission submission = findSubmission(protocol);
        if (submission != null && ProtocolSubmissionType.REQUEST_FOR_SUSPENSION.equals(submission.getSubmissionTypeCode())) {
            return true;
        }
        return false;
    }
    
    protected boolean canPerformActionOnExpeditedOrExempt(ProtocolSubmission lastSubmission, ProtocolAction lastAction) {
        boolean canPerform = false;
        if(isSubmissionValidForAction(lastSubmission)) {
            canPerform = isExpeditedOrExempt(lastSubmission.getProtocolReviewType().getReviewTypeCode()) && ProtocolActionType.SUBMIT_TO_IRB.equals(lastAction.getProtocolActionTypeCode());
        }
        return canPerform;
    }
    
    protected boolean canPerformActionOnExpedited(Protocol protocol) {
        ProtocolSubmission submission = findSubmission(protocol);
        boolean canPerform = false;
        if(isSubmissionValidForAction(submission)) {
            canPerform = isExpeditedSubmission(submission);
        }
        return canPerform;
    }
    
    private boolean isSubmissionValidForAction(ProtocolSubmission submission) {
        return ObjectUtils.isNotNull(submission.getCommitteeId()) && ObjectUtils.isNotNull(submission.getScheduleId());
    }

    private boolean isExpeditedOrExempt(String reviewTypeCode){
        return isExpedited(reviewTypeCode) || isExempt(reviewTypeCode);
    }
    
    private boolean isExpedited(String reviewTypeCode) {
        return ProtocolReviewType.EXPEDITED_REVIEW_TYPE_CODE.equals(reviewTypeCode);
    }

    private boolean isExempt(String reviewTypeCode) {
        return ProtocolReviewType.EXEMPT_STUDIES_REVIEW_TYPE_CODE.equals(reviewTypeCode);
    }
    
    /**
     * Is the submission expedited?
     * @param submission
     * @return
     */
    private boolean isExpeditedSubmission(ProtocolSubmission submission) {
        return submission != null && isExpedited(submission.getProtocolReviewTypeCode());
    }


}

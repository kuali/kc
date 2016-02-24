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
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.submit.ProtocolReviewType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;

/**
 * Determine if a user can assign a protocol to a committee/schedule.
 */
public class ProtocolAssignReviewersUnavailableAuthorizer extends ProtocolAuthorizer {

    private KcWorkflowService kraWorkflowService;

    @Override
    public boolean isAuthorized(String username, ProtocolTask task) {
        Protocol protocol = task.getProtocol();
        ProtocolSubmission submission = findSubmission(protocol);
        
        return (!kraWorkflowService.isCurrentNode(protocol.getProtocolDocument(), Constants.PROTOCOL_IRBREVIEW_ROUTE_NODE_NAME) ||
                !isPendingOrSubmittedToCommittee(protocol) ||
                !(canPerformActionOnExpedited(protocol) || (isScheduleRequiredForReview(submission) && isAssignedToCommitteeAndSchedule(submission))
                        || (!isScheduleRequiredForReview(submission) && isAssignedToCommittee(submission)))
               ) &&
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
     * Is the submission assigned to a committee?
     * @param protocol
     * @return
     */
    private boolean isAssignedToCommittee(ProtocolSubmission submission) {
        return submission != null && !StringUtils.isBlank(submission.getCommitteeId());
    }
    
    /**
     * Is the submission assigned to a committee and schedule?
     * @param protocol
     * @return
     */
    private boolean isAssignedToCommitteeAndSchedule(ProtocolSubmission submission) {
        return !StringUtils.isBlank(submission.getCommitteeId()) && !StringUtils.isBlank(submission.getScheduleId());
    }

    /**
     * Is the submission for a full committee review
     * @param submission
     * @return
     */
    private boolean isFullCommitteeReview(ProtocolSubmission submission) {
        return submission != null && ProtocolReviewType.FULL_TYPE_CODE.equals(submission.getProtocolReviewTypeCode());
    }
    
    private boolean isScheduleRequiredForReview (ProtocolSubmission submission) {        
        return isFullCommitteeReview(submission) && StringUtils.isBlank(submission.getScheduleId());
    }
    
    public KcWorkflowService getKraWorkflowService() {
        return kraWorkflowService;
    }

    public void setKraWorkflowService(KcWorkflowService kraWorkflowService) {
        this.kraWorkflowService = kraWorkflowService;
    }
}

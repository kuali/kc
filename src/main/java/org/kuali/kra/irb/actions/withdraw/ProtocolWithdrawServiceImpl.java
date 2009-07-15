/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.actions.withdraw;

import java.sql.Timestamp;
import java.util.List;

import org.codehaus.plexus.util.StringUtils;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * The ProtocolWithdrawService implementation.
 */
public class ProtocolWithdrawServiceImpl implements ProtocolWithdrawService {

    private BusinessObjectService businessObjectService;
    private ProtocolActionService protocolActionService;

    /**
     * Set the business object service.
     * @param businessObjectService the business object service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    /**
     * Set the Protocol Action Service.
     * @param protocolActionService
     */
    public void setProtocolActionService(ProtocolActionService protocolActionService) {
        this.protocolActionService = protocolActionService;
    }

    /**
     * @see org.kuali.kra.irb.actions.withdraw.ProtocolWithdrawService#withdraw(org.kuali.kra.irb.Protocol, org.kuali.kra.irb.actions.withdraw.ProtocolWithdrawBean)
     */
    public void withdraw(Protocol protocol, ProtocolWithdrawBean withdrawBean) {
        ProtocolAction protocolAction = new ProtocolAction(protocol, null, ProtocolActionType.WITHDRAWN);
        protocolAction.setComments(withdrawBean.getReason());
        protocol.getProtocolActions().add(protocolAction);
        
        protocolActionService.updateProtocolStatus(protocolAction, protocol);
        
        ProtocolSubmission submission = getSubmission(protocol);
        if (submission != null) {
            submission.setSubmissionDate(new Timestamp(System.currentTimeMillis()));
            submission.setSubmissionStatusCode(ProtocolSubmissionStatus.WITHDRAWN);
        }
        
        businessObjectService.save(protocol.getProtocolDocument());
    }

    /**
     * Get the submission that is being withdrawn.  Since a protocol can have
     * multiple submissions, go backwards until we find a submission that can
     * be withdrawn
     * @param protocol
     * @return
     */
    private ProtocolSubmission getSubmission(Protocol protocol) {
        List<ProtocolSubmission> submissions = protocol.getProtocolSubmissions();
        for (int i = submissions.size() - 1; i >= 0; i--) {
            ProtocolSubmission submission = submissions.get(i);
            if (isWithdrawable(submission)) {
                return submission;
            }
        }
        return null;
    }

    /**
     * A submission is only withdrawable if it corresponds to a request to review
     * the submission.  The submissions that meet this criteria are the initial 
     * request for review, amendments, and renewals.  Submissions such as Notify IRB
     * cannot be withdrawn.
     * @param submission
     * @return
     */
    private boolean isWithdrawable(ProtocolSubmission submission) {
        return StringUtils.equals(submission.getSubmissionTypeCode(), ProtocolSubmissionType.AMENDMENT) ||
               StringUtils.equals(submission.getSubmissionTypeCode(), ProtocolSubmissionType.INITIAL_SUBMISSION) ||
               StringUtils.equals(submission.getSubmissionTypeCode(), ProtocolSubmissionType.CONTINUATION) ||
               StringUtils.equals(submission.getSubmissionTypeCode(), ProtocolSubmissionType.CONTINUATION_WITH_AMENDMENT);
    }
}

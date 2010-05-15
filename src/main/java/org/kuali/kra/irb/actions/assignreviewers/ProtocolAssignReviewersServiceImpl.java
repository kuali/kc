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
package org.kuali.kra.irb.actions.assignreviewers;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.submit.ProtocolReviewer;
import org.kuali.kra.irb.actions.submit.ProtocolReviewerBean;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * Implementation of the ProtocolAssignReviewersService.
 */
public class ProtocolAssignReviewersServiceImpl implements ProtocolAssignReviewersService {

    private BusinessObjectService businessObjectService;
    
    /**
     * Set the Business Object Service.
     * @param businessObjectService businessObjectService.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    /**
     * @see org.kuali.kra.irb.actions.assignreviewers.ProtocolAssignReviewersService#getCurrentSubmission(org.kuali.kra.irb.Protocol)
     */
    public ProtocolSubmission getCurrentSubmission(Protocol protocol) {
        return findSubmission(protocol);
    }

    /**
     * Find the submission.  It is the submission that is either currently pending or
     * already submitted to a committee. 
     * @param protocol
     * @return
     */
    private ProtocolSubmission findSubmission(Protocol protocol) {
        for (ProtocolSubmission submission : protocol.getProtocolSubmissions()) {
            if (StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.PENDING) ||
                StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE)) {
                return submission;
            }
        }
        return null;
    }

    /**
     * @see org.kuali.kra.irb.actions.assignreviewers.ProtocolAssignReviewersService#assignReviewers(org.kuali.kra.irb.Protocol, java.util.List)
     */
    public void assignReviewers(Protocol protocol, List<ProtocolReviewerBean> reviewerBeans) {
        ProtocolSubmission submission = findSubmission(protocol);
        if (submission != null) {
            List<ProtocolReviewer> reviewers = new ArrayList<ProtocolReviewer>();
            for (ProtocolReviewerBean reviewerBean : reviewerBeans) {
                if (reviewerBean.getChecked()) {
                    reviewers.add(createReviewer(submission,
                                                 reviewerBean.getPersonId(),
                                                 reviewerBean.getReviewerTypeCode(),
                                                 reviewerBean.getNonEmployeeFlag()));
                }
            }
            submission.setProtocolReviewers(reviewers);
            businessObjectService.save(submission);
        }
    }
    
    /**
     * Create a protocol reviewer.
     * @param personId
     * @param reviewerTypeCode
     * @param nonEmployeeFlag
     * @return
     */
    private ProtocolReviewer createReviewer(ProtocolSubmission submission, String personId, String reviewerTypeCode, boolean nonEmployeeFlag) {
        ProtocolReviewer protocolReviewer = new ProtocolReviewer();
        protocolReviewer.setProtocolId(submission.getProtocolId());
        protocolReviewer.setSubmissionIdFk(submission.getSubmissionId());
        protocolReviewer.setProtocolNumber(submission.getProtocolNumber());
        protocolReviewer.setSequenceNumber(submission.getSequenceNumber());
        protocolReviewer.setSubmissionNumber(submission.getSubmissionNumber());
        protocolReviewer.setPersonId(personId);
        protocolReviewer.setReviewerTypeCode(reviewerTypeCode);
        protocolReviewer.setNonEmployeeFlag(nonEmployeeFlag);
        return protocolReviewer;
    }
}
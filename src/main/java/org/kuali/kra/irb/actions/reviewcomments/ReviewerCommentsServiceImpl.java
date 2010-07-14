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
package org.kuali.kra.irb.actions.reviewcomments;

import org.kuali.kra.committee.service.CommitteeScheduleService;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.kra.meeting.MinuteEntryType;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * 
 * This class takes care of the persistence for Reviewer comments.
 */
public class ReviewerCommentsServiceImpl implements ReviewerCommentsService {
    
    private BusinessObjectService businessObjectService;
    
    private CommitteeScheduleService committeeScheduleService;
    
    /**
     * Set the Business Object Service.
     * @param businessObjectService BusinessObjectService
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    /**
     * This method sets the committee schedule service.
     * @param committeeScheduleService CommitteeScheduleService
     */
    public void setCommitteeScheduleService(CommitteeScheduleService committeeScheduleService) {
        this.committeeScheduleService = committeeScheduleService;
    }
    
    /** {@inheritDoc} */
    public void persistReviewerComments(ReviewerComments reviewComments, Protocol protocol) {
        int nextEntryNumber = 0;
        this.businessObjectService.delete(reviewComments.getCommentsToDelete());
        reviewComments.resetComentsToDelete();
        for (CommitteeScheduleMinute minute : reviewComments.getComments()) {
            minute.setEntryNumber(nextEntryNumber);
            boolean doUpdate = false;
            if (minute.getCommScheduleMinutesId() != null) {
                CommitteeScheduleMinute existing = this.committeeScheduleService.getCommitteeScheduleMinute(minute.getCommScheduleMinutesId());
                doUpdate = !minute.equals(existing);
            } else {
                //brand new review comment / minute entry, set some cool stuff
                minute.setMinuteEntryTypeCode(MinuteEntryType.PROTOCOL);
                minute.setSubmissionIdFk(protocol.getProtocolSubmission().getSubmissionId());
                minute.setProtocolIdFk(protocol.getProtocolSubmission().getProtocolId());
                minute.setScheduleIdFk(protocol.getProtocolSubmission().getScheduleIdFk());
                doUpdate = true;
            }
            if (doUpdate) {
                this.businessObjectService.save(minute);
            }
            nextEntryNumber++;
        }
    }

    public void persistReviewerComments(ReviewerComments reviewComments, Protocol protocol, ProtocolOnlineReview protocolOnlineReview) {
        //set the protocolOnlineReviewIdFk for each of the comments.
        for (CommitteeScheduleMinute minute : reviewComments.getComments()) {
           minute.setProtocolOnlineReview(protocolOnlineReview);
           minute.setProtocolOnlineReviewIdFk(protocolOnlineReview.getProtocolOnlineReviewId());
        }
        persistReviewerComments(reviewComments, protocol );
    }
}

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

import java.util.List;

import org.kuali.kra.committee.service.CommitteeScheduleService;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ActionHelper;
import org.kuali.kra.meeting.CommitteeScheduleMinute;

/**
 * This class defines functions that need to be implemented in a "bean" that needs to provide support for reviewer comments.
 */
public class ReviewerCommentsBean {
    
    private ActionHelper actionHelper;
    
    private ReviewerComments reviewComments = new ReviewerComments();
    
    private CommitteeScheduleService committeeScheduleService;
    
    /**
     * Constructs a ReviewerCommentsBean.
     * @param actionHelper Reference back to the parent ActionHelper
     */
    public ReviewerCommentsBean(ActionHelper actionHelper) {
        this.actionHelper = actionHelper;
    }
    
    /**
     * Initializes the ReviewerComments.
     */
    public void initComments() {
        Long scheduleIdFk = actionHelper.getProtocol().getProtocolSubmission().getScheduleIdFk();
        List<CommitteeScheduleMinute> minutes = getCommitteeScheduleService().getMinutesBySchedule(scheduleIdFk);
        reviewComments.setComments(minutes);
        reviewComments.setProtocolId(actionHelper.getProtocol().getProtocolId());
    }
    
    /**
     * This method needs to return the reviewer comments object held by the bean. Maintained by data entry by the remote user
     * 
     * @return ReviewComments object
     */
    public ReviewerComments getReviewComments() {
        return reviewComments;
    } 

    /**
     * This method sets the reviewer comments object of the bean, pulled from the database or by user input.
     * 
     * @param reviewComments ReviewComments object
     */
    public void setReviewComments(ReviewerComments reviewComments) {
        this.reviewComments = reviewComments;
    }
    
    /**
     * Returns the parent ActionHelper.
     * @return ActionHelper
     */
    public ActionHelper getActionHelper() {
        return actionHelper;
    }
    
    private CommitteeScheduleService getCommitteeScheduleService() {
        if (committeeScheduleService == null) {
            committeeScheduleService = KraServiceLocator.getService(CommitteeScheduleService.class);        
        }
        return committeeScheduleService;
    }
    
}
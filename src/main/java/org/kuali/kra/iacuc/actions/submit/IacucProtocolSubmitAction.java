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
package org.kuali.kra.iacuc.actions.submit;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.iacuc.actions.IacucActionHelper;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewerBean;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmitAction;

/**
 * This class is really just a "form" for submitting a protocol for review in the Submit for Review Action.
 */
@SuppressWarnings("unchecked")
public class IacucProtocolSubmitAction extends ProtocolSubmitAction {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -4276322966748812194L;


    /**
     * Constructs a ProtocolSubmitAction.
     * 
     * @param actionHelper Reference back to the action helper for this bean
     */
    public IacucProtocolSubmitAction(IacucActionHelper actionHelper) {
        super(actionHelper);
    }

    /**
     * Prepare the Submit for Review for rendering with JSP.
     */
    public void prepareView() {
        /*
         * The Submit for Review has to work with and without JavaScript. When JavaScript is enabled, the newly selected committee
         * and schedule are what we want to continue to display. When JavaScript is disabled, we have to change the schedule dates
         * that we display if the committee has changed.
         */
        if (!this.getJavascriptEnabled()) {
            if ((!StringUtils.isBlank(this.committeeId)) && (!this.committeeIdChanged) && (!StringUtils.isBlank(this.scheduleId))) {
                if (this.scheduleIdChanged) {
                    reviewers.clear();
                    buildReviewers();
                }
            }
            else {
                reviewers.clear();
                this.reviewerListAvailable = false;
            }
        }
        else {
            // use the numberOfReviewers property (sent in as a hidden input field) to truncate the reviewers collection if needed
            this.reviewers.subList(this.numberOfReviewers, this.reviewers.size()).clear();            
        }
    }


    /**
     * Create the list of reviewers based upon the currently selected committee and schedule.
     */
    private void buildReviewers() {
        this.reviewerListAvailable = true;
        List<CommitteeMembership> members = getProtocol().filterOutProtocolPersonnel(
                getCommitteeService().getAvailableMembers(this.committeeId, this.scheduleId));
        for (CommitteeMembership member : members) {
            ProtocolReviewerBean reviewer = new ProtocolReviewerBean(member);
            reviewers.add(reviewer);
        }
    }
    

}

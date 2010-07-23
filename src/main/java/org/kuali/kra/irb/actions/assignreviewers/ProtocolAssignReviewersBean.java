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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ActionHelper;
import org.kuali.kra.irb.actions.submit.ProtocolReviewer;
import org.kuali.kra.irb.actions.submit.ProtocolReviewerBean;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;

/**
 * This class is really just a "form" for assigning a protocol
 * to one or more reviewers.
 */
@SuppressWarnings("serial")
public class ProtocolAssignReviewersBean implements Serializable{
    
    private ActionHelper actionHelper;
    
    private String currentCommitteeId = null;
    private String currentScheduleId = null;
    private List<ProtocolReviewerBean> reviewers = new ArrayList<ProtocolReviewerBean>();
    
    public ProtocolAssignReviewersBean(ActionHelper actionHelper) {
        this.actionHelper = actionHelper;
    }

    private Protocol getProtocol() {
        return actionHelper.getProtocolForm().getProtocolDocument().getProtocol();
    }
    
    /**
     * Create the list of reviewers based upon the current committee
     * and schedule.
     */
    public void prepareView() {
        ProtocolSubmission submission = getProtocolAssignReviewersService().getCurrentSubmission(getProtocol());
        if (submission != null) {
            String committeeId = submission.getCommitteeId();
            String scheduleId = submission.getScheduleId();
            
            if (!StringUtils.equals(committeeId, currentCommitteeId) || !StringUtils.equals(scheduleId, currentScheduleId)) {
                currentCommitteeId = committeeId;
                currentScheduleId = scheduleId;
                reviewers.clear();
                if (!StringUtils.isBlank(committeeId) && !StringUtils.isBlank(scheduleId)) {
                    List<CommitteeMembership> members = getCommitteeService().getAvailableMembers(committeeId, scheduleId);
                    for (CommitteeMembership member : members) {
                        ProtocolReviewerBean reviewer = new ProtocolReviewerBean();
                        if (!StringUtils.isBlank(member.getPersonId())) {
                            reviewer.setPersonId(member.getPersonId());
                            reviewer.setNonEmployeeFlag(false);
                        }
                        else {
                            reviewer.setPersonId(member.getRolodexId().toString());
                            reviewer.setNonEmployeeFlag(true);
                        }
                        reviewer.setFullName(member.getPersonName());
                        reviewers.add(reviewer);
                    }
                    
                    for (ProtocolOnlineReview review : submission.getProtocolOnlineReviews()) {
                        for (ProtocolReviewerBean reviewerBean : reviewers) {
                            if ((review.getProtocolReviewer().getNonEmployeeFlag() == reviewerBean.getNonEmployeeFlag()) &&
                                (StringUtils.equals(reviewerBean.getPersonId(), review.getProtocolReviewer().getPersonId()))) {
                                reviewerBean.setChecked(true);
                                reviewerBean.setReviewerTypeCode(review.getProtocolReviewer().getReviewerTypeCode());
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
    
    private ProtocolAssignReviewersService getProtocolAssignReviewersService() {
        return KraServiceLocator.getService(ProtocolAssignReviewersService.class);
    }
    
    private CommitteeService getCommitteeService() {
        return KraServiceLocator.getService(CommitteeService.class);
    }
    
    public List<ProtocolReviewerBean> getReviewers() {
        return reviewers;
    }
    
    public ProtocolReviewerBean getReviewer(int i) {
        return reviewers.get(i);
    }
    
    /**
     * We display the reviewers in two columns.  These are the
     * reviewers in the left column.
     * @return
     */
    public List<ProtocolReviewerBean> getLeftReviewers() {
        List<ProtocolReviewerBean> leftReviewers = new ArrayList<ProtocolReviewerBean>();
        for (int i = 0; i < (reviewers.size() + 1) / 2; i++) {
            leftReviewers.add(reviewers.get(i));
        }
        return leftReviewers;
    }
    
    /**
     * We display the reviewers in two columns.  These are the
     * reviewers in the right column.
     * @return
     */
    public List<ProtocolReviewerBean> getRightReviewers() {
        List<ProtocolReviewerBean> rightReviewers = new ArrayList<ProtocolReviewerBean>();
        for (int i = (reviewers.size() + 1) / 2; i < reviewers.size(); i++) {
            rightReviewers.add(reviewers.get(i));
        }
        return rightReviewers;
    }
}

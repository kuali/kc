/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.iacuc.actions.modifysubmission;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipBase;
import org.kuali.coeus.common.committee.impl.service.CommitteeServiceBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.iacuc.actions.IacucProtocolActionBean;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolReviewerBean;
import org.kuali.kra.iacuc.committee.service.IacucCommitteeService;
import org.kuali.kra.protocol.actions.ActionHelperBase;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewerBeanBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewBase;
import org.springframework.util.AutoPopulatingList;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class IacucProtocolModifySubmissionBean extends IacucProtocolActionBean implements Serializable {


    private static final long serialVersionUID = 1L;
  
    private String submissionTypeCode = "";
    private String protocolReviewTypeCode = "";
    private String submissionQualifierTypeCode = "";
    private boolean billable;
    private String committeeId = null;
    private String scheduleId = null;
    private List<ProtocolReviewerBeanBase> reviewers = new AutoPopulatingList<ProtocolReviewerBeanBase>(IacucProtocolReviewerBean.class);
    private Date dueDate;
    private int numberOfReviewers = 0;

    private int checkListItemDescriptionIndex = 0;
    private String selectedProtocolReviewTypeCode = null;
    
    /**
     * Constructs a ProtocolModifySubmissionBean.
     * @param actionHelper Reference back to the action helper for this bean
     */
    public IacucProtocolModifySubmissionBean(ActionHelperBase actionHelper) {
        super(actionHelper);
        
        this.submissionTypeCode = actionHelper.getProtocol().getProtocolSubmission().getProtocolSubmissionType().getSubmissionTypeCode();
        this.submissionQualifierTypeCode = actionHelper.getProtocol().getProtocolSubmission().getSubmissionTypeQualifierCode();
        this.protocolReviewTypeCode = actionHelper.getProtocol().getProtocolSubmission().getProtocolReviewTypeCode();
        this.billable = actionHelper.getProtocol().getProtocolSubmission().isBillable();
        this.committeeId = actionHelper.getProtocol().getProtocolSubmission().getCommitteeId();
        this.scheduleId = actionHelper.getProtocol().getProtocolSubmission().getScheduleId();
        // TODO set due date here probably from submission?
    }

    public void setNumberOfReviewers(int numberOfReviewers) {
        this.numberOfReviewers = numberOfReviewers;
    }

    
    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    /* ASSIGN REVIEWERS PART STARTS HERE */

    /**
     * Create the list of reviewers based upon the current committee and schedule, and assigns their reviewer types if any have been
     * saved in the last submission's reviews
     */
    public void prepareView() {
        ProtocolSubmissionBase submission = getProtocol().getProtocolSubmission();

        if (submission != null) {
            // whenever submission is not null, we will show the cmt and schedule chosen for the last submission
            IacucProtocolForm iacucProtocolForm = (IacucProtocolForm)getActionHelper().getProtocolForm();
            if (iacucProtocolForm.isReinitializeModifySubmissionFields()) {
                iacucProtocolForm.setReinitializeModifySubmissionFields(false);
                committeeId = submission.getCommitteeId();
                scheduleId = submission.getScheduleId();
                reviewers.clear();
            }

            /*
             * need to build only if committee was chosen in the last submission
             */
            if (!StringUtils.isBlank(committeeId) && reviewers.isEmpty()) {
                /*
                 * no reviewers should be assigned if schedule not chosen.
                 */
                if (!StringUtils.isBlank(scheduleId)) {
                    List<CommitteeMembershipBase> members = getProtocol().filterOutProtocolPersonnel(getCommitteeService().getAvailableMembers(committeeId, scheduleId));
                    for (CommitteeMembershipBase member : members) {
                        reviewers.add(new IacucProtocolReviewerBean(member));
                    }
    
                    for (ProtocolOnlineReviewBase review : submission.getProtocolOnlineReviews()) {
                        if (review.isActive()) {
                            for (ProtocolReviewerBeanBase reviewerBean : reviewers) {
                                if (reviewerBean.isProtocolReviewerBeanForReviewer(review.getProtocolReviewer())) {
                                    reviewerBean.setReviewerTypeCode(review.getProtocolReviewer().getReviewerTypeCode());
                                    break;
                                }
                            }
                        }
                    }
                }
                else {
                    reviewers.clear();
                }
            }
        }
    }    
    
    public void setReviewers(List<ProtocolReviewerBeanBase> reviewers) {
        this.reviewers = reviewers;
    }

    public String getCommitteeId() {
        return committeeId;
    }

    public void setCommitteeId(String committeeId) {
        this.committeeId = committeeId;
    }

    public String getCurrentScheduleId() {
        return scheduleId;
    }

    public void setCurrentScheduleId(String currentScheduleId) {
        this.scheduleId = currentScheduleId;
    }

    public List<ProtocolReviewerBeanBase> getReviewers() {
        return reviewers;
    }
    
    public ProtocolReviewerBeanBase getReviewer(int i) {
        return reviewers.get(i);
    }
    
    /**
     * We display the reviewers in two columns.  These are the
     * reviewers in the left column.
     * @return
     */
    public List<ProtocolReviewerBeanBase> getLeftReviewers() {
        List<ProtocolReviewerBeanBase> leftReviewers = new ArrayList<ProtocolReviewerBeanBase>();
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
    public List<ProtocolReviewerBeanBase> getRightReviewers() {
        List<ProtocolReviewerBeanBase> rightReviewers = new ArrayList<ProtocolReviewerBeanBase>();
        for (int i = (reviewers.size() + 1) / 2; i < reviewers.size(); i++) {
            rightReviewers.add(reviewers.get(i));
        }
        return rightReviewers;
    }
    /*ASSIGN REVIEWERS ENDS HERE*/
    
    
    public String getSubmissionTypeCode() {
        return submissionTypeCode;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setSubmissionTypeCode(String submissionTypeCode) {
        this.submissionTypeCode = submissionTypeCode;
    }
    
    public String getProtocolReviewTypeCode() {
        return protocolReviewTypeCode;
    }

    public void setProtocolReviewTypeCode(String protocolReviewTypeCode) {
        this.protocolReviewTypeCode = protocolReviewTypeCode;
    }
    
    public String getSubmissionQualifierTypeCode() {
        return submissionQualifierTypeCode;
    }

    public void setSubmissionQualifierTypeCode(String submissionQualifierTypeCode) {
        this.submissionQualifierTypeCode = submissionQualifierTypeCode;
    }
  
    public boolean isBillable() {
        return billable;
    }

    public void setBillable(boolean billable) {
        this.billable = billable;
    }
    
    /**
     * When a user wants to display the entire description of check list item,
     * the currently selected protocol review type and the index of the check list
     * item are stored here for later rendering.
     * @param protocolReviewTypeCode
     * @param index
     */
    public void setCheckListItemDescriptionInfo(String protocolReviewTypeCode, int index) {
        this.selectedProtocolReviewTypeCode = protocolReviewTypeCode;
        checkListItemDescriptionIndex = index;   
    }
    
    private CommitteeServiceBase getCommitteeService() {
        return KcServiceLocator.getService(IacucCommitteeService.class);
    }
  
}

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
package org.kuali.kra.iacuc.actions.modifysubmission;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import org.kuali.kra.common.committee.bo.CommitteeMembership;
import org.kuali.kra.common.committee.service.CommonCommitteeService;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.iacuc.actions.IacucProtocolActionBean;
import org.kuali.kra.iacuc.actions.assignCmt.IacucProtocolAssignCmtService;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolReviewerBean;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.protocol.actions.ActionHelper;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewerBean;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmission;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReview;
import org.springframework.util.AutoPopulatingList;
import org.apache.commons.lang.StringUtils;


public class IacucProtocolModifySubmissionBean extends IacucProtocolActionBean implements Serializable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
  
    private String submissionTypeCode = "";
    private String protocolReviewTypeCode = "";
    private String submissionQualifierTypeCode = "";
    private boolean billable;
    private String committeeId = null;
    private String scheduleId = null;
    private List<ProtocolReviewerBean> reviewers = new AutoPopulatingList<ProtocolReviewerBean>(IacucProtocolReviewerBean.class);
    private Date dueDate;
    private int numberOfReviewers = 0;

    private int checkListItemDescriptionIndex = 0;
    private String selectedProtocolReviewTypeCode = null;
    
    /**
     * Constructs a ProtocolModifySubmissionBean.
     * @param actionHelper Reference back to the action helper for this bean
     */
    public IacucProtocolModifySubmissionBean(ActionHelper actionHelper) {
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
        ProtocolSubmission submission = getProtocol().getProtocolSubmission();

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
                 * just getAvailable members here by sending blank schedule id if schedule not chosen.
                 */
                List<CommitteeMembership> members = getProtocol().filterOutProtocolPersonnel(getCommitteeService().getAvailableMembers(committeeId, scheduleId));
                for (CommitteeMembership member : members) {
                    reviewers.add(new IacucProtocolReviewerBean(member));
                }

                for (ProtocolOnlineReview review : submission.getProtocolOnlineReviews()) {
                    if (review.isActive()) {
                        for (ProtocolReviewerBean reviewerBean : reviewers) {
                            if (reviewerBean.isProtocolReviewerBeanForReviewer(review.getProtocolReviewer())) {
                                reviewerBean.setReviewerTypeCode(review.getProtocolReviewer().getReviewerTypeCode());
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
    
   /* public void init() {
        String committeeId = getProtocolAssignCmtService().getAssignedCommitteeId(getProtocol());
        if (committeeId != null) {
            this.committeeId = committeeId;
            String scheduleId = getProtocolModifySubmissionService().getAssignedScheduleId(getProtocol());
            if (scheduleId != null) {
                this.scheduleId = scheduleId;
            }
        }
    }*/
    
    
    public void setReviewers(List<ProtocolReviewerBean> reviewers) {
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

    private IacucProtocolAssignCmtService getProtocolAssignCmtService() {
        return KraServiceLocator.getService(IacucProtocolAssignCmtService.class);
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
    
    private CommonCommitteeService getCommitteeService() {
        return KraServiceLocator.getService(CommonCommitteeService.class);
    }
    
    protected IacucProtocolModifySubmissionService getProtocolModifySubmissionService() {
        return KraServiceLocator.getService(IacucProtocolModifySubmissionService.class);
    }
  
}

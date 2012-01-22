/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.irb.actions.submit;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeDecisionMotionType;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.irb.ProtocolAssociate;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.kra.irb.onlinereview.ProtocolReviewAttachment;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.kra.meeting.ProtocolVoteAbstainee;
import org.kuali.kra.meeting.ProtocolVoteRecused;

/**
 * 
 * This class tracks the data associated with the submisison of a protocol for review.
 */
public class ProtocolSubmission extends ProtocolAssociate {

    private static final long serialVersionUID = 2158830045312905591L;

    private Long submissionId;

    private Integer submissionNumber;

    private String scheduleId;

    private String committeeId;

    private Long committeeIdFk;

    private Long scheduleIdFk;

    private String submissionTypeCode;

    private String submissionTypeQualifierCode;

    private String submissionStatusCode;

    private String protocolReviewTypeCode;

    private Date submissionDate;

    private String comments;

    private String committeeDecisionMotionTypeCode;

    private Integer yesVoteCount;

    private Integer noVoteCount;

    //these two are here just for persistence to work, fields get recorded to the db, but in code, they are always calculated.  
    private Integer recusedCount;

    private Integer abstainerCount;

    private String votingComments;

    private Boolean billable;

    private List<ProtocolExemptStudiesCheckListItem> exemptStudiesCheckList = new ArrayList<ProtocolExemptStudiesCheckListItem>();

    private List<ProtocolExpeditedReviewCheckListItem> expeditedReviewCheckList = new ArrayList<ProtocolExpeditedReviewCheckListItem>();

    @SkipVersioning
    private transient List<ProtocolOnlineReview> protocolOnlineReviews;

    private ProtocolSubmissionType protocolSubmissionType;

    private ProtocolSubmissionQualifierType protocolSubmissionQualifierType;

    private CommitteeDecisionMotionType committeeDecisionMotionType;

    private List<ProtocolVoteAbstainee> abstainers = new ArrayList<ProtocolVoteAbstainee>();

    private List<ProtocolVoteRecused> recusers = new ArrayList<ProtocolVoteRecused>();

    @SkipVersioning
    private transient CommitteeSchedule committeeSchedule;

    @SkipVersioning
    private transient List<CommitteeScheduleMinute> committeeScheduleMinutes;

    @SkipVersioning
    private transient List<ProtocolReviewAttachment> reviewAttachments;

    private ProtocolReviewType protocolReviewType;

    @SkipVersioning
    private transient Committee committee;

    private ProtocolSubmissionStatus submissionStatus;

    // lookup field  
    private String piName;

    /**
     * 
     * Constructs a ProtocolSubmission.java.
     */
    public ProtocolSubmission() {
        this.billable = false;
    }

    public Integer getRecusedCount() {
        this.refreshReferenceObject("recusers");
        if (this.recusers == null) {
            return 0;
        } else {
            return this.recusers.size();
        }
    }

    public void setRecusedCount(Integer recusedCount) {
        this.recusedCount = recusedCount;
    }

    public List<ProtocolVoteAbstainee> getAbstainers() {
        return abstainers;
    }

    public void setAbstainers(List<ProtocolVoteAbstainee> abstainers) {
        this.abstainers = abstainers;
    }

    public List<ProtocolVoteRecused> getRecusers() {
        return recusers;
    }

    public void setRecusers(List<ProtocolVoteRecused> recusers) {
        this.recusers = recusers;
    }

    public Long getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Long submissionId) {
        this.submissionId = submissionId;
    }

    public Integer getSubmissionNumber() {
        return submissionNumber;
    }

    public void setSubmissionNumber(Integer submissionNumber) {
        this.submissionNumber = submissionNumber;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getCommitteeId() {
        return committeeId;
    }

    public void setCommitteeId(String committeeId) {
        this.committeeId = committeeId;
    }

    public Long getCommitteeIdFk() {
        return committeeIdFk;
    }

    public void setCommitteeIdFk(Long committeeIdFk) {
        this.committeeIdFk = committeeIdFk;
    }

    public Long getScheduleIdFk() {
        return scheduleIdFk;
    }

    public void setScheduleIdFk(Long scheduleIdFk) {
        this.scheduleIdFk = scheduleIdFk;
    }

    public String getSubmissionTypeCode() {
        return submissionTypeCode;
    }

    public void setSubmissionTypeCode(String submissionTypeCode) {
        this.submissionTypeCode = submissionTypeCode;
    }

    public String getSubmissionTypeQualifierCode() {
        return submissionTypeQualifierCode;
    }

    public void setSubmissionTypeQualifierCode(String submissionTypeQualifierCode) {
        this.submissionTypeQualifierCode = submissionTypeQualifierCode;
    }

    public String getSubmissionStatusCode() {
        return submissionStatusCode;
    }

    /**
     * 
     * This method set the submission status code.
     * @param submissionStatusCode
     */
    public void setSubmissionStatusCode(String submissionStatusCode) {
        this.submissionStatusCode = submissionStatusCode;
        if (StringUtils.isBlank(submissionStatusCode)) {
            submissionStatus = null;
        } else if (getSubmissionStatus() == null || !submissionStatusCode.equals(getSubmissionStatus().getProtocolSubmissionStatusCode())) {
            refreshReferenceObject("submissionStatus");
        }
    }

    public String getProtocolReviewTypeCode() {
        return protocolReviewTypeCode;
    }

    public void setProtocolReviewTypeCode(String protocolReviewTypeCode) {
        this.protocolReviewTypeCode = protocolReviewTypeCode;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCommitteeDecisionMotionTypeCode() {
        return committeeDecisionMotionTypeCode;
    }

    public void setCommitteeDecisionMotionTypeCode(String committeeDecisionMotionTypeCode) {
        this.committeeDecisionMotionTypeCode = committeeDecisionMotionTypeCode;
    }

    public Integer getYesVoteCount() {
        return yesVoteCount;
    }

    public void setYesVoteCount(Integer yesVoteCount) {
        this.yesVoteCount = yesVoteCount;
    }

    public Integer getNoVoteCount() {
        return noVoteCount;
    }

    public void setNoVoteCount(Integer noVoteCount) {
        this.noVoteCount = noVoteCount;
    }

    public Integer getAbstainerCount() {
        this.refreshReferenceObject("abstainers");
        if (this.abstainers == null) {
            return 0;
        } else {
            return this.abstainers.size();
        }
    }

    public void setAbstainerCount(Integer abstainerCount) {
        this.abstainerCount = abstainerCount;
    }

    public String getVotingComments() {
        return votingComments;
    }

    public void setVotingComments(String votingComments) {
        this.votingComments = votingComments;
    }

    public void setExemptStudiesCheckList(List<ProtocolExemptStudiesCheckListItem> exemptStudiesCheckList) {
        this.exemptStudiesCheckList = exemptStudiesCheckList;
    }

    public List<ProtocolExemptStudiesCheckListItem> getExemptStudiesCheckList() {
        return exemptStudiesCheckList;
    }

    public void setExpeditedReviewCheckList(List<ProtocolExpeditedReviewCheckListItem> expeditedReviewCheckList) {
        this.expeditedReviewCheckList = expeditedReviewCheckList;
    }

    public List<ProtocolExpeditedReviewCheckListItem> getExpeditedReviewCheckList() {
        return expeditedReviewCheckList;
    }

    /**
     * Gets the protocolReviews attribute. 
     * @return Returns the protocolReviews.
     */
    public List<ProtocolOnlineReview> getProtocolOnlineReviews() {
        if (protocolOnlineReviews == null) {
            refreshReferenceObject("protocolOnlineReviews");
        }
        return protocolOnlineReviews;
    }

    /**
     * Sets the protocolReviews attribute value.
     * @param protocolOnlineReviews The protocolReviews to set.
     */
    public void setProtocolOnlineReviews(List<ProtocolOnlineReview> protocolOnlineReviews) {
        this.protocolOnlineReviews = protocolOnlineReviews;
    }

    public ProtocolReviewType getProtocolReviewType() {
        return protocolReviewType;
    }

    public void setProtocolReviewType(ProtocolReviewType protocolReviewType) {
        this.protocolReviewType = protocolReviewType;
    }

    public List<ProtocolReviewer> getProtocolReviewers() {
        List<ProtocolReviewer> reviewers = new ArrayList<ProtocolReviewer>();
        List<ProtocolOnlineReview> reviews = getProtocolOnlineReviews();
        if (reviews != null) {
            for (ProtocolOnlineReview review : reviews) {
                if (review.isActive()) {
                    reviewers.add(review.getProtocolReviewer());
                }
            }
        }
        return reviewers;
    }

    /**
     * 
     * This method returns the committee object.
     * @return
     */
    public Committee getCommittee() {
        if (committeeIdFk != null && committee == null) {
            refreshReferenceObject("committee");
        }
        return committee;
    }

    public void setCommittee(Committee committee) {
        this.committee = committee;
    }

    public ProtocolSubmissionStatus getSubmissionStatus() {
        if (StringUtils.isNotBlank(submissionStatusCode) && submissionStatus == null) {
            this.refreshReferenceObject("submissionStatus");
        }
        return submissionStatus;
    }

    public void setSubmissionStatus(ProtocolSubmissionStatus submissionStatus) {
        this.submissionStatus = submissionStatus;
    }

    public void setProtocolSubmissionType(ProtocolSubmissionType protocolSubmissionType) {
        this.protocolSubmissionType = protocolSubmissionType;
    }

    public ProtocolSubmissionType getProtocolSubmissionType() {
        if (StringUtils.isNotBlank(submissionTypeCode) && protocolSubmissionType == null) {
            this.refreshReferenceObject("protocolSubmissionType");
        }
        return protocolSubmissionType;
    }

    public void setProtocolSubmissionQualifierType(ProtocolSubmissionQualifierType protocolSubmissionQualifierType) {
        this.protocolSubmissionQualifierType = protocolSubmissionQualifierType;
    }

    public ProtocolSubmissionQualifierType getProtocolSubmissionQualifierType() {
        return protocolSubmissionQualifierType;
    }

    public void setCommitteeDecisionMotionType(CommitteeDecisionMotionType committeeDecisionMotionType) {
        this.committeeDecisionMotionType = committeeDecisionMotionType;
    }

    public CommitteeDecisionMotionType getCommitteeDecisionMotionType() {
        return committeeDecisionMotionType;
    }

    public void setCommitteeSchedule(CommitteeSchedule committeeSchedule) {
        this.committeeSchedule = committeeSchedule;
    }

    /**
     * 
     * This method returns the committee schedule.
     * @return
     */
    public CommitteeSchedule getCommitteeSchedule() {
        if (scheduleIdFk != null && committeeSchedule == null) {
            refreshReferenceObject("committeeSchedule");
        }
        return committeeSchedule;
    }

    public List<CommitteeScheduleMinute> getCommitteeScheduleMinutes() {
        refreshReferenceObject("committeeScheduleMinutes");
        return committeeScheduleMinutes;
    }

    public void setCommitteeScheduleMinutes(List<CommitteeScheduleMinute> committeeScheduleMinutes) {
        this.committeeScheduleMinutes = committeeScheduleMinutes;
    }

    /**
     * 
     * @see org.kuali.kra.Sequenceable#resetPersistenceState()
     */
    public void resetPersistenceState() {
        submissionId = null;
    }

    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        managedLists.add(protocolOnlineReviews);
        return managedLists;
    }

    public String getPiName() {
        return getProtocol().getPrincipalInvestigator().getPersonName();
    }

    public void setPiName(String piName) {
        this.piName = piName;
    }

    /**
     * This method returns true if this submission has the same submission id as the passed in submission id.
     * @param o a ProtocolSubmission object to compare for equality
     * @return a boolean
     */
    @Override
    public boolean equals(Object o) {
        ProtocolSubmission ps = (ProtocolSubmission) o;
        try {
            return this.getSubmissionId().equals(ps.getSubmissionId());
        } catch (Exception e) {
            //an NPE would happen if the submission IDs aren't set.  
            return false;
        }
    }

    public boolean isBillable() {
        return billable == null ? false : billable;
    }

    public void setBillable(boolean billable) {
        this.billable = billable;
    }

    public List<ProtocolReviewAttachment> getReviewAttachments() {
        return reviewAttachments;
    }

    public void setReviewAttachments(List<ProtocolReviewAttachment> reviewAttachments) {
        this.reviewAttachments = reviewAttachments;
    }
}

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
package org.kuali.kra.protocol.actions.submit;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeDecisionMotionType;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.committee.impl.meeting.CommitteeScheduleMinuteBase;
import org.kuali.coeus.common.committee.impl.meeting.ProtocolVoteAbstaineeBase;
import org.kuali.coeus.common.committee.impl.meeting.ProtocolVoteRecusedBase;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.protocol.ProtocolAssociateBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewBase;
import org.kuali.kra.protocol.onlinereview.ProtocolReviewAttachmentBase;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class tracks the data associated with the submisison of a protocol for review.
 */
public abstract class ProtocolSubmissionBase extends ProtocolAssociateBase {

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

    @SkipVersioning
    private transient List<ProtocolOnlineReviewBase> protocolOnlineReviews;

    private ProtocolSubmissionTypeBase protocolSubmissionType;

    protected ProtocolSubmissionQualifierTypeBase protocolSubmissionQualifierType;

    private CommitteeDecisionMotionType committeeDecisionMotionType;

    private List<ProtocolVoteAbstaineeBase> abstainers = new ArrayList<ProtocolVoteAbstaineeBase>();

    private List<ProtocolVoteRecusedBase> recusers = new ArrayList<ProtocolVoteRecusedBase>();

    @SkipVersioning
    private transient CommitteeScheduleBase committeeSchedule;

    private List<CommitteeScheduleMinuteBase> committeeScheduleMinutes;

    @SkipVersioning
    private transient List<ProtocolReviewAttachmentBase> reviewAttachments;

    private ProtocolReviewTypeBase protocolReviewType;

    @SkipVersioning
    private transient CommitteeBase committee;

    private ProtocolSubmissionStatusBase submissionStatus;

    // lookup field  
    private String piName;


    public ProtocolSubmissionBase() {
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

    public List<ProtocolVoteAbstaineeBase> getAbstainers() {
        if (abstainers == null || abstainers.size() == 0) {
            refreshReferenceObject("abstainers");
        }
        return abstainers;
    }

    public void setAbstainers(List<ProtocolVoteAbstaineeBase> abstainers) {
        this.abstainers = abstainers;
    }

    public List<ProtocolVoteRecusedBase> getRecusers() {
        if (recusers == null || recusers.size() == 0) {
            refreshReferenceObject("recusers");
        }
        return recusers;
    }

    public void setRecusers(List<ProtocolVoteRecusedBase> recusers) {
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

    /**
     * Gets the protocolReviews attribute. 
     * @return Returns the protocolReviews.
     */
    public List<ProtocolOnlineReviewBase> getProtocolOnlineReviews() {
        if (protocolOnlineReviews == null) {
            refreshReferenceObject("protocolOnlineReviews");
        }
        return protocolOnlineReviews;
    }

    /**
     * Sets the protocolReviews attribute value.
     * @param protocolOnlineReviews The protocolReviews to set.
     */
    public void setProtocolOnlineReviews(List<ProtocolOnlineReviewBase> protocolOnlineReviews) {
        this.protocolOnlineReviews = protocolOnlineReviews;
    }

    public ProtocolReviewTypeBase getProtocolReviewType() {
        return protocolReviewType;
    }

    public void setProtocolReviewType(ProtocolReviewTypeBase protocolReviewType) {
        this.protocolReviewType = protocolReviewType;
    }

  
    /**
     * Gets only the active reviews from all the protocolReviews for this submission 
     * @return Returns non-null list of active protocol reviews.
     */
    public List<ProtocolOnlineReviewBase> getActiveProtocolOnlineReviews() {
        List<ProtocolOnlineReviewBase> activeReviews = new ArrayList<ProtocolOnlineReviewBase>();
        List<ProtocolOnlineReviewBase> allReviews = getProtocolOnlineReviews();
        if (allReviews != null) {
            for (ProtocolOnlineReviewBase review : allReviews) {
                if (review.isActive()) {
                    activeReviews.add(review);
                }
            }
        }
        return activeReviews;
    }
    
    /**
     * Gets the reviewers for only the active reviews for this submission 
     * @return Returns non-null list of protocol reviewers.
     */
    public List<ProtocolReviewer> getProtocolReviewers() {
        List<ProtocolReviewer> reviewers = new ArrayList<ProtocolReviewer>();
        List<ProtocolOnlineReviewBase> activeReviews = getActiveProtocolOnlineReviews();
        for (ProtocolOnlineReviewBase review : activeReviews) {
            reviewers.add(review.getProtocolReviewer());
        }
        return reviewers;
    }

    /**
     * 
     * This method returns the committee object.
     * @return
     */
    public CommitteeBase getCommittee() {
        if (committeeIdFk != null && committee == null) {
            refreshReferenceObject("committee");
        }
        return committee;
    }

    public void setCommittee(CommitteeBase committee) {
        this.committee = committee;
    }

    public ProtocolSubmissionStatusBase getSubmissionStatus() {
        if (StringUtils.isNotBlank(submissionStatusCode) && submissionStatus == null) {
            this.refreshReferenceObject("submissionStatus");
        }
        return submissionStatus;
    }

    public void setSubmissionStatus(ProtocolSubmissionStatusBase submissionStatus) {
        this.submissionStatus = submissionStatus;
    }

    public void setProtocolSubmissionType(ProtocolSubmissionTypeBase protocolSubmissionType) {
        this.protocolSubmissionType = protocolSubmissionType;
    }

    public ProtocolSubmissionTypeBase getProtocolSubmissionType() {
        if (StringUtils.isNotBlank(submissionTypeCode) && protocolSubmissionType == null) {
            this.refreshReferenceObject("protocolSubmissionType");
        }
        return protocolSubmissionType;
    }

    public void setProtocolSubmissionQualifierType(ProtocolSubmissionQualifierTypeBase protocolSubmissionQualifierType) {
        this.protocolSubmissionQualifierType = protocolSubmissionQualifierType;
    }

    public ProtocolSubmissionQualifierTypeBase getProtocolSubmissionQualifierType() {
        if(null == protocolSubmissionQualifierType) {
            protocolSubmissionQualifierType = getNewInstanceProtocolSubmissionQualifierTypeHook();
        }
        return protocolSubmissionQualifierType;
    }

    protected abstract ProtocolSubmissionQualifierTypeBase getNewInstanceProtocolSubmissionQualifierTypeHook();

    public void setCommitteeDecisionMotionType(CommitteeDecisionMotionType committeeDecisionMotionType) {
        this.committeeDecisionMotionType = committeeDecisionMotionType;
    }

    public CommitteeDecisionMotionType getCommitteeDecisionMotionType() {
        return committeeDecisionMotionType;
    }

    public void setCommitteeSchedule(CommitteeScheduleBase committeeSchedule) {
        this.committeeSchedule = committeeSchedule;
    }

    /**
     * 
     * This method returns the committee schedule.
     * @return
     */
    public CommitteeScheduleBase getCommitteeSchedule() {
        if (scheduleIdFk != null && committeeSchedule == null) {
            refreshReferenceObject("committeeSchedule");
        }
        return committeeSchedule;
    }

    public List<CommitteeScheduleMinuteBase> getCommitteeScheduleMinutes() {
        if (committeeScheduleMinutes == null || committeeScheduleMinutes.isEmpty()) {
            refreshReferenceObject("committeeScheduleMinutes");
        }
        return committeeScheduleMinutes;
    }

    public void setCommitteeScheduleMinutes(List<CommitteeScheduleMinuteBase> committeeScheduleMinutes) {
        this.committeeScheduleMinutes = committeeScheduleMinutes;
    }

    @Override
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
        return (getProtocol().getPrincipalInvestigator() != null ? getProtocol().getPrincipalInvestigator().getPersonName() : "");
    }

    public void setPiName(String piName) {
        this.piName = piName;
    }

    /**
     * This method returns true if this submission has the same submission id as the passed in submission id.
     * @param o a ProtocolSubmissionBase object to compare for equality
     * @return a boolean
     */
    @Override
    public boolean equals(Object o) {
        ProtocolSubmissionBase ps = (ProtocolSubmissionBase) o;
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

    public List<ProtocolReviewAttachmentBase> getReviewAttachments() {
        return reviewAttachments;
    }

    public void setReviewAttachments(List<ProtocolReviewAttachmentBase> reviewAttachments) {
        this.reviewAttachments = reviewAttachments;
    }
    
    /**
     * This help protocol submission search, just returns the protocol title
     * @return
     */
    public String getProtocolTitle() {
    	return this.getProtocol() != null ? this.getProtocol().getTitle() : "";
    }
    
    /**
     * This help protocol submission search, just returns the committee schedule date
     * @return
     */
    public Date getCommitteeScheduleDate() {
    	return this.getCommitteeSchedule() != null ? this.getCommitteeSchedule().getScheduledDate() : null;
    }
}

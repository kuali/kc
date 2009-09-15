/*
 * Copyright 2006-2008 The Kuali Foundation
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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.plexus.util.StringUtils;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.irb.ProtocolAssociate;

@Entity 
@Table(name="PROTOCOL_SUBMISSION")
public class ProtocolSubmission extends ProtocolAssociate { 

    private static final long serialVersionUID = -5443313755174483591L;

    @Id 
    @Column(name = "SUBMISSION_ID")
    private Long submissionId;
    
    @Column(name="SUBMISSION_NUMBER")
    private Integer submissionNumber; 
    
    @Column(name="SCHEDULE_ID")
    private String scheduleId; 

    @Column(name="COMMITTEE_ID")
    private String committeeId; 

    @Column(name = "COMITTEE_ID_FK")
    private Long committeeIdFk;
    
    @Column(name = "SCHEDULE_ID_FK")
    private Long scheduleIdFk;
    
    @Column(name = "SUBMISSION_TYPE_CODE")
    private String submissionTypeCode;
    
    @Column(name = "SUBMISSION_TYPE_QUAL_CODE")
    private String submissionTypeQualifierCode;
    
    @Column(name = "SUBMISSION_STATUS_CODE")
    private String submissionStatusCode;
    
    @Column(name = "PROTOCOL_REVIEW_TYPE_CODE")
    private String protocolReviewTypeCode;
    
    @Column(name="SUBMISSION_DATE")
    private Timestamp submissionDate; 

    @Column(name="COMMENTS")
    private String comments; 

    @Column(name="YES_VOTE_COUNT")
    private Integer yesVoteCount; 

    @Column(name="NO_VOTE_COUNT")
    private Integer noVoteCount; 

    @Column(name="ABSTAINER_COUNT")
    private Integer abstainerCount; 

    @Column(name="VOTING_COMMENTS")
    private String votingComments; 
    
    private List<ProtocolExemptStudiesCheckListItem> exemptStudiesCheckList = new ArrayList<ProtocolExemptStudiesCheckListItem>();
    private List<ProtocolExpeditedReviewCheckListItem> expeditedReviewCheckList = new ArrayList<ProtocolExpeditedReviewCheckListItem>();
    
    private List<ProtocolReviewer> protocolReviewers = new ArrayList<ProtocolReviewer>();
    
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="SUBMISSION_TYPE_CODE", insertable=false, updatable=false)
    private ProtocolSubmissionType protocolSubmissionType;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="SUBMISSION_TYPE_QUAL_CODE", insertable=false, updatable=false)
    private ProtocolSubmissionQualifierType protocolSubmissionQualifierType;
    
    @SkipVersioning
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="SCHEDULE_ID_FK", insertable=false, updatable=false)
    private CommitteeSchedule committeeSchedule;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="PROTOCOL_REVIEW_TYPE_CODE", insertable=false, updatable=false)
    private ProtocolReviewType protocolReviewType;
    
    @SkipVersioning
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="COMMITTEE_ID_FK", insertable=false, updatable=false)
    private Committee committee;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="SUBMISSION_STATUS_CODE", insertable=false, updatable=false)
    private ProtocolSubmissionStatus submissionStatus;    
    
    public ProtocolSubmission() { 

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

    public void setSubmissionStatusCode(String submissionStatusCode) {
        this.submissionStatusCode = submissionStatusCode;
        if (StringUtils.isBlank(submissionStatusCode)) {
            submissionStatus = null;
        }
        else {
            refreshReferenceObject("submissionStatus");
        }
    }

    public String getProtocolReviewTypeCode() {
        return protocolReviewTypeCode;
    }

    public void setProtocolReviewTypeCode(String protocolReviewTypeCode) {
        this.protocolReviewTypeCode = protocolReviewTypeCode;
    }

    public Timestamp getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Timestamp submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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
        return abstainerCount;
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

    public void setProtocolReviewers(List<ProtocolReviewer> protocolReviewers) {
        this.protocolReviewers = protocolReviewers;
    }

    public List<ProtocolReviewer> getProtocolReviewers() {
        return protocolReviewers;
    }

    public ProtocolReviewType getProtocolReviewType() {
        return protocolReviewType;
    }

    public void setProtocolReviewType(ProtocolReviewType protocolReviewType) {
        this.protocolReviewType = protocolReviewType;
    }

    public Committee getCommittee() {
        return committee;
    }

    public void setCommittee(Committee committee) {
        this.committee = committee;
    }

    public ProtocolSubmissionStatus getSubmissionStatus() {
        return submissionStatus;
    }

    public void setSubmissionStatus(ProtocolSubmissionStatus submissionStatus) {
        this.submissionStatus = submissionStatus;
    }

    public void setProtocolSubmissionType(ProtocolSubmissionType protocolSubmissionType) {
        this.protocolSubmissionType = protocolSubmissionType;
    }

    public ProtocolSubmissionType getProtocolSubmissionType() {
        return protocolSubmissionType;
    }

    public void setProtocolSubmissionQualifierType(ProtocolSubmissionQualifierType protocolSubmissionQualifierType) {
        this.protocolSubmissionQualifierType = protocolSubmissionQualifierType;
    }

    public ProtocolSubmissionQualifierType getProtocolSubmissionQualifierType() {
        return protocolSubmissionQualifierType;
    }

    public void setCommitteeSchedule(CommitteeSchedule committeeSchedule) {
        this.committeeSchedule = committeeSchedule;
    }

    public CommitteeSchedule getCommitteeSchedule() {
        return committeeSchedule;
    }

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("submissionId", getSubmissionId());
        hashMap.put("submissionNumber", this.getSubmissionNumber());
        hashMap.put("protocolNumber", this.getProtocolNumber());
        hashMap.put("sequenceNumber", this.getSequenceNumber());
        hashMap.put("scheduleId", this.getScheduleId());
        hashMap.put("committeeId", this.getCommitteeId());
        hashMap.put("protocolId", getProtocolId());
        hashMap.put("comitteeIdFk", getCommitteeIdFk());
        hashMap.put("scheduleIdFk", getScheduleIdFk());
        hashMap.put("submissionTypeCode", getSubmissionTypeCode());
        hashMap.put("submissionTypeQualifierCode", getSubmissionTypeQualifierCode());
        hashMap.put("submissionStatusCode", getSubmissionStatusCode());
        hashMap.put("protocolReviewTypeCode", getProtocolReviewTypeCode());
        hashMap.put("submissionDate", this.getSubmissionDate());
        hashMap.put("comments", this.getComments());
        hashMap.put("yesVoteCount", this.getYesVoteCount());
        hashMap.put("noVoteCount", this.getNoVoteCount());
        hashMap.put("abstainerCount", this.getAbstainerCount());
        hashMap.put("votingComments", this.getVotingComments());
        hashMap.put("exemptStudiesCheckList", getExemptStudiesCheckList());
        hashMap.put("expeditedReviewCheckList", getExpeditedReviewCheckList());
        return hashMap;
    }

    public void resetPersistenceState() {
        submissionId = null;
        committeeIdFk = null;
        scheduleIdFk = null;
    }
}
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

import java.sql.Date;
import java.util.LinkedHashMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.irb.bo.Protocol;

@Entity 
@Table(name="PROTOCOL_SUBMISSION")
public class ProtocolSubmission extends KraPersistableBusinessObjectBase { 

    private static final long serialVersionUID = -5443313755174483591L;

    @Id 
    @Column(name="SUBMISSION_NUMBER")
    private Integer submissionNumber; 

    @Column(name="PROTOCOL_NUMBER")
    private String protocolNumber; 

    @Column(name="SEQUENCE_NUMBER")
    private Integer sequenceNumber; 

    @Column(name="SCHEDULE_ID")
    private String scheduleId; 

    @Column(name="COMMITTEE_ID")
    private String committeeId; 

    @Column(name="SUBMISSION_DATE")
    private Date submissionDate; 

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
    
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="SUBMISSION_TYPE_CODE", insertable=false, updatable=false)
    private ProtocolSubmissionType protocolSubmissionType;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="SUBMISSION_TYPE_QUAL_CODE", insertable=false, updatable=false)
    private ProtocolSubmissionQualifierType protocolSubmissionQualifierType;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="COMM_SCHEDULE_ID", insertable=false, updatable=false)
    private CommitteeSchedule committeeSchedule;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="PROTOCOL_ID", insertable=false, updatable=false)
    private Protocol protocol;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="PROTOCOL_REVIEW_TYPE_CODE", insertable=false, updatable=false)
    private ProtocolReviewType protocolReviewType;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="COMMITTEE_ID_NEW", insertable=false, updatable=false)
    private Committee committee;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="SUBMISSION_STATUS_CODE", insertable=false, updatable=false)
    private ProtocolSubmissionStatus submissionStatus;    
    
    public ProtocolSubmission() { 

    } 
    
    public Integer getSubmissionNumber() {
        return submissionNumber;
    }

    public void setSubmissionNumber(Integer submissionNumber) {
        this.submissionNumber = submissionNumber;
    }

    public String getProtocolNumber() {
        return protocolNumber;
    }

    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
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

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
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
        hashMap.put("submissionNumber", this.getSubmissionNumber());
        hashMap.put("protocolNumber", this.getProtocolNumber());
        hashMap.put("sequenceNumber", this.getSequenceNumber());
        hashMap.put("scheduleId", this.getScheduleId());
        hashMap.put("committeeId", this.getCommitteeId());
        hashMap.put("submissionDate", this.getSubmissionDate());
        hashMap.put("comments", this.getComments());
        hashMap.put("yesVoteCount", this.getYesVoteCount());
        hashMap.put("noVoteCount", this.getNoVoteCount());
        hashMap.put("abstainerCount", this.getAbstainerCount());
        hashMap.put("votingComments", this.getVotingComments());
        return hashMap;
    }
    
}
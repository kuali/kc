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

import org.kuali.coeus.common.committee.impl.bo.CommitteeDecisionMotionType;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import java.sql.Date;

public abstract class ProtocolSubmissionLiteBase extends KcPersistableBusinessObjectBase {

    private Long protocolId;

    private Integer sequenceNumber;

    private String protocolNumber;

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

    private Integer recusedCount;

    private Integer abstainerCount;

    private String votingComments;

    private Boolean billable;

    private boolean protocolActive;

    private String protocolStatusCode;

    private String protocolTitle;

    private String piPersonId;

    private String piPersonName;

    private Integer piRolodexId;

    private ProtocolSubmissionTypeBase protocolSubmissionType;

    private ProtocolSubmissionQualifierTypeBase protocolSubmissionQualifierType;

    private CommitteeDecisionMotionType committeeDecisionMotionType;

    private ProtocolReviewTypeBase protocolReviewType;

    private ProtocolSubmissionStatusBase submissionStatus;

    //from protocol submission
    public Long getProtocolId() {
        return this.protocolId;
    }
    public void setProtocolId(Long protocolId) {
        this.protocolId = protocolId;
    }
    public Integer getSequenceNumber() {
        return this.sequenceNumber;
    }
    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }
    public String getProtocolNumber() {
        return this.protocolNumber;
    }
    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
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

    public Integer getRecusedCount() {
        return recusedCount;
    }

    public void setRecusedCount(Integer recusedCount) {
        this.recusedCount = recusedCount;
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

    public Boolean getBillable() {
        return billable;
    }

    public void setBillable(Boolean billable) {
        this.billable = billable;
    }

    public ProtocolSubmissionTypeBase getProtocolSubmissionType() {
        return protocolSubmissionType;
    }

    public void setProtocolSubmissionType(ProtocolSubmissionTypeBase protocolSubmissionType) {
        this.protocolSubmissionType = protocolSubmissionType;
    }

    public ProtocolSubmissionQualifierTypeBase getProtocolSubmissionQualifierType() {
        return protocolSubmissionQualifierType;
    }

    public void setProtocolSubmissionQualifierType(ProtocolSubmissionQualifierTypeBase protocolSubmissionQualifierType) {
        this.protocolSubmissionQualifierType = protocolSubmissionQualifierType;
    }

    public CommitteeDecisionMotionType getCommitteeDecisionMotionType() {
        return committeeDecisionMotionType;
    }

    public void setCommitteeDecisionMotionType(CommitteeDecisionMotionType committeeDecisionMotionType) {
        this.committeeDecisionMotionType = committeeDecisionMotionType;
    }

    public ProtocolReviewTypeBase getProtocolReviewType() {
        return protocolReviewType;
    }

    public void setProtocolReviewType(ProtocolReviewTypeBase protocolReviewType) {
        this.protocolReviewType = protocolReviewType;
    }

    public ProtocolSubmissionStatusBase getSubmissionStatus() {
        return submissionStatus;
    }

    public void setSubmissionStatus(ProtocolSubmissionStatusBase submissionStatus) {
        this.submissionStatus = submissionStatus;
    }

    //from protocol
    public boolean isProtocolActive() {
        return protocolActive;
    }
    public void setProtocolActive(boolean protocolActive) {
        this.protocolActive = protocolActive;
    }
    public String getProtocolStatusCode() {
        return protocolStatusCode;
    }
    public void setProtocolStatusCode(String protocolStatusCode) {
        this.protocolStatusCode = protocolStatusCode;
    }

    public String getProtocolTitle() {
        return protocolTitle;
    }

    public void setProtocolTitle(String protocolTitle) {
        this.protocolTitle = protocolTitle;
    }

    public String getPiPersonId() {
        return piPersonId;
    }

    public void setPiPersonId(String piPersonId) {
        this.piPersonId = piPersonId;
    }

    public String getPiPersonName() {
        return piPersonName;
    }

    public void setPiPersonName(String piPersonName) {
        this.piPersonName = piPersonName;
    }

    public Integer getPiRolodexId() {
        return piRolodexId;
    }

    public void setPiRolodexId(Integer piRolodexId) {
        this.piRolodexId = piRolodexId;
    }
}

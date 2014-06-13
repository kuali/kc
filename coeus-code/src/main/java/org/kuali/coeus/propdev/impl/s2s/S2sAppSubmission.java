/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.s2s;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.propdev.api.s2s.S2sAppSubmissionContract;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "S2S_APP_SUBMISSION")
@IdClass(S2sAppSubmission.S2sAppSubmissionId.class)
public class S2sAppSubmission extends KcPersistableBusinessObjectBase implements S2sAppSubmissionContract {

    @Id
    @Column(name = "PROPOSAL_NUMBER")
    private String proposalNumber;

    @Id
    @Column(name = "SUBMISSION_NUMBER")
    private Integer submissionNumber;

    @Column(name = "AGENCY_TRACKING_ID")
    private String agencyTrackingId;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "GG_TRACKING_ID")
    private String ggTrackingId;

    @Column(name = "LAST_MODIFIED_DATE")
    private Timestamp lastModifiedDate;

    @Column(name = "LAST_NOTIFIED_DATE")
    private Timestamp lastNotifiedDate;

    @Column(name = "RECEIVED_DATE")
    private Timestamp receivedDate;

    @Column(name = "STATUS")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @JoinColumn(name = "PROPOSAL_NUMBER", referencedColumnName = "PROPOSAL_NUMBER", insertable = false, updatable = false)
    private S2sApplication s2sApplication;

    @Override
    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    @Override
    public Integer getSubmissionNumber() {
        return submissionNumber;
    }

    public void setSubmissionNumber(Integer submissionNumber) {
        this.submissionNumber = submissionNumber;
    }

    @Override
    public String getAgencyTrackingId() {
        return agencyTrackingId;
    }

    public void setAgencyTrackingId(String agencyTrackingId) {
        this.agencyTrackingId = agencyTrackingId;
    }

    @Override
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String getGgTrackingId() {
        return ggTrackingId;
    }

    public void setGgTrackingId(String ggTrackingId) {
        this.ggTrackingId = ggTrackingId;
    }

    @Override
    public Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public Timestamp getLastNotifiedDate() {
        return lastNotifiedDate;
    }

    public void setLastNotifiedDate(Timestamp lastNotifiedDate) {
        this.lastNotifiedDate = lastNotifiedDate;
    }

    @Override
    public Timestamp getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Timestamp receivedDate) {
        this.receivedDate = receivedDate;
    }

    @Override
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public S2sApplication getS2sApplication() {
        return s2sApplication;
    }

    public void setS2sApplication(S2sApplication s2sApplication) {
        this.s2sApplication = s2sApplication;
    }

    public static final class S2sAppSubmissionId implements Serializable, Comparable<S2sAppSubmissionId> {

        private String proposalNumber;

        private Integer submissionNumber;

        public String getProposalNumber() {
            return this.proposalNumber;
        }

        public void setProposalNumber(String proposalNumber) {
            this.proposalNumber = proposalNumber;
        }

        public Integer getSubmissionNumber() {
            return this.submissionNumber;
        }

        public void setSubmissionNumber(Integer submissionNumber) {
            this.submissionNumber = submissionNumber;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("proposalNumber", this.proposalNumber).append("submissionNumber", this.submissionNumber).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final S2sAppSubmissionId rhs = (S2sAppSubmissionId) other;
            return new EqualsBuilder().append(this.proposalNumber, rhs.proposalNumber).append(this.submissionNumber, rhs.submissionNumber).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.proposalNumber).append(this.submissionNumber).toHashCode();
        }

        @Override
        public int compareTo(S2sAppSubmissionId other) {
            return new CompareToBuilder().append(this.proposalNumber, other.proposalNumber).append(this.submissionNumber, other.submissionNumber).toComparison();
        }
    }
}

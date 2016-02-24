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
package org.kuali.coeus.propdev.impl.ynq;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.common.framework.ynq.Ynq;
import org.kuali.coeus.propdev.api.ynq.ProposalYnqContract;
import org.kuali.kra.bo.KraSortablePersistableBusinessObjectBase;

@Entity
@Table(name = "EPS_PROP_YNQ")
@IdClass(ProposalYnq.ProposalYnqId.class)
public class ProposalYnq extends KraSortablePersistableBusinessObjectBase implements Comparable<ProposalYnq>, ProposalYnqContract {

    @Id
    @Column(name = "PROPOSAL_NUMBER")
    private String proposalNumber;

    @Id
    @Column(name = "QUESTION_ID")
    private String questionId;

    @Column(name = "ANSWER")
    private String answer;

    @Column(name = "EXPLANATION")
    @Lob
    private String explanation;

    @Column(name = "REVIEW_DATE")
    private Date reviewDate;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "QUESTION_ID", referencedColumnName = "QUESTION_ID", insertable = false, updatable = false)
    private Ynq ynq;

    @Transient
    private String dummyAnswer;

    @Transient
    private boolean explanationRequried = true;

    @Transient
    private boolean reviewDateRequired = true;

    @Transient
    private String explanationRequiredDescription;

    @Transient
    private String reviewDateRequiredDescription;

    @Override
    public Ynq getYnq() {
        return ynq;
    }

    public void setYnq(Ynq ynq) {
        this.ynq = ynq;
    }

    @Override
    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    @Override
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    @Override
    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getDummyAnswer() {
        return dummyAnswer;
    }

    public void setDummyAnswer(String dummyAnswer) {
        this.dummyAnswer = dummyAnswer;
    }

    public boolean getExplanationRequried() {
        return explanationRequried;
    }

    public void setExplanationRequried(boolean explanationRequried) {
        this.explanationRequried = explanationRequried;
    }

    public boolean getReviewDateRequired() {
        return reviewDateRequired;
    }

    public void setReviewDateRequired(boolean reviewDateRequired) {
        this.reviewDateRequired = reviewDateRequired;
    }

    public String getExplanationRequiredDescription() {
        return explanationRequiredDescription;
    }

    public void setExplanationRequiredDescription(String explanationRequiredDescription) {
        this.explanationRequiredDescription = explanationRequiredDescription;
    }

    public String getReviewDateRequiredDescription() {
        return reviewDateRequiredDescription;
    }

    public void setReviewDateRequiredDescription(String reviewDateRequiredDescription) {
        this.reviewDateRequiredDescription = reviewDateRequiredDescription;
    }

    public int compareTo(ProposalYnq proposalYnq) {
        int comparator;
        if (getSortId() != null && proposalYnq.getSortId() != null) {
            comparator = getSortId().compareTo(proposalYnq.getSortId());
        } else {
            comparator = getQuestionId().compareTo(proposalYnq.getQuestionId());
        }
        return comparator;
    }

    public static final class ProposalYnqId implements Serializable, Comparable<ProposalYnqId> {

        private String proposalNumber;

        private String questionId;

        public String getProposalNumber() {
            return this.proposalNumber;
        }

        public void setProposalNumber(String proposalNumber) {
            this.proposalNumber = proposalNumber;
        }

        public String getQuestionId() {
            return this.questionId;
        }

        public void setQuestionId(String questionId) {
            this.questionId = questionId;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("proposalNumber", this.proposalNumber).append("questionId", this.questionId).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final ProposalYnqId rhs = (ProposalYnqId) other;
            return new EqualsBuilder().append(this.proposalNumber, rhs.proposalNumber).append(this.questionId, rhs.questionId).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.proposalNumber).append(this.questionId).toHashCode();
        }

        @Override
        public int compareTo(ProposalYnqId other) {
            return new CompareToBuilder().append(this.proposalNumber, other.proposalNumber).append(this.questionId, other.questionId).toComparison();
        }
    }
}

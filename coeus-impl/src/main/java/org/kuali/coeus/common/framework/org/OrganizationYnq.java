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
package org.kuali.coeus.common.framework.org;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.common.api.org.OrganizationYnqContract;
import org.kuali.coeus.common.framework.ynq.Ynq;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "ORGANIZATION_YNQ")
@IdClass(OrganizationYnq.OrganizationYnqId.class)
public class OrganizationYnq extends KcPersistableBusinessObjectBase implements OrganizationYnqContract {

    @Id
    @Column(name = "ORGANIZATION_ID")
    private String organizationId;

    @Id
    @Column(name = "QUESTION_ID")
    private String questionId;

    @Column(name = "ANSWER")
    private String answer;

    @Column(name = "EXPLANATION")
    private String explanation;

    @Column(name = "REVIEW_DATE")
    private Date reviewDate;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "ORGANIZATION_ID", referencedColumnName = "ORGANIZATION_ID", insertable = false, updatable = false)
    private Organization organization;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "QUESTION_ID", referencedColumnName = "QUESTION_ID", insertable = false, updatable = false)
    private Ynq ynq;

    @Override
    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    @Override
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

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }


    public Ynq getYnq() {
        return ynq;
    }

    public void setYnq(Ynq ynq) {
        this.ynq = ynq;
    }

    public static final class OrganizationYnqId implements Serializable, Comparable<OrganizationYnqId> {

        private String organizationId;

        private String questionId;

        public String getOrganizationId() {
            return this.organizationId;
        }

        public void setOrganizationId(String organizationId) {
            this.organizationId = organizationId;
        }

        public String getQuestionId() {
            return this.questionId;
        }

        public void setQuestionId(String questionId) {
            this.questionId = questionId;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("organizationId", this.organizationId).append("questionId", this.questionId).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final OrganizationYnqId rhs = (OrganizationYnqId) other;
            return new EqualsBuilder().append(this.organizationId, rhs.organizationId).append(this.questionId, rhs.questionId).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.organizationId).append(this.questionId).toHashCode();
        }

        @Override
        public int compareTo(OrganizationYnqId other) {
            return new CompareToBuilder().append(this.organizationId, other.organizationId).append(this.questionId, other.questionId).toComparison();
        }
    }
}

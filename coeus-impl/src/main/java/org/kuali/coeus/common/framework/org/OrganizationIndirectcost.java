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
import org.kuali.coeus.common.api.org.OrganizationIndirectcostContract;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.persistence.ScaleTwoDecimalConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "ORGANIZATION_IDC")
@IdClass(OrganizationIndirectcost.OrganizationIndirectcostId.class)
public class OrganizationIndirectcost extends KcPersistableBusinessObjectBase implements OrganizationIndirectcostContract {

    @Id
    @Column(name = "IDC_NUMBER")
    private Integer idcNumber;

    @Id
    @Column(name = "ORGANIZATION_ID")
    private String organizationId;

    @Column(name = "APPLICABLE_IDC_RATE")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal applicableIndirectcostRate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "IDC_COMMENT")
    private String idcComment;

    @Column(name = "IDC_RATE_TYPE_CODE")
    private Integer idcRateTypeCode;

    @Column(name = "REQUESTED_DATE")
    private Date requestedDate;

    @Column(name = "START_DATE")
    private Date startDate;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "ORGANIZATION_ID", referencedColumnName = "ORGANIZATION_ID", insertable = false, updatable = false)
    private Organization organization;

    @Override
    public Integer getIdcNumber() {
        return idcNumber;
    }

    public void setIdcNumber(Integer idcNumber) {
        this.idcNumber = idcNumber;
    }

    @Override
    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    @Override
    public ScaleTwoDecimal getApplicableIndirectcostRate() {
        return applicableIndirectcostRate;
    }

    public void setApplicableIndirectcostRate(ScaleTwoDecimal applicableIndirectcostRate) {
        this.applicableIndirectcostRate = applicableIndirectcostRate;
    }

    @Override
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String getIdcComment() {
        return idcComment;
    }

    public void setIdcComment(String idcComment) {
        this.idcComment = idcComment;
    }

    @Override
    public Integer getIdcRateTypeCode() {
        return idcRateTypeCode;
    }

    public void setIdcRateTypeCode(Integer idcRateTypeCode) {
        this.idcRateTypeCode = idcRateTypeCode;
    }

    @Override
    public Date getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(Date requestedDate) {
        this.requestedDate = requestedDate;
    }

    @Override
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public static final class OrganizationIndirectcostId implements Serializable, Comparable<OrganizationIndirectcostId> {

        private Integer idcNumber;

        private String organizationId;

        public Integer getIdcNumber() {
            return this.idcNumber;
        }

        public void setIdcNumber(Integer idcNumber) {
            this.idcNumber = idcNumber;
        }

        public String getOrganizationId() {
            return this.organizationId;
        }

        public void setOrganizationId(String organizationId) {
            this.organizationId = organizationId;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("idcNumber", this.idcNumber).append("organizationId", this.organizationId).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final OrganizationIndirectcostId rhs = (OrganizationIndirectcostId) other;
            return new EqualsBuilder().append(this.idcNumber, rhs.idcNumber).append(this.organizationId, rhs.organizationId).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.idcNumber).append(this.organizationId).toHashCode();
        }

        @Override
        public int compareTo(OrganizationIndirectcostId other) {
            return new CompareToBuilder().append(this.idcNumber, other.idcNumber).append(this.organizationId, other.organizationId).toComparison();
        }
    }
}

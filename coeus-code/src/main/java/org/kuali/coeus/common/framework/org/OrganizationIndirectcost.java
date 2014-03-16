/*
 * Copyright 2005-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the License);
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
package org.kuali.coeus.common.framework.org;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.krad.data.jpa.converters.KualiDecimalConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "ORGANIZATION_IDC")
@IdClass(OrganizationIndirectcost.OrganizationIndirectcostId.class)
public class OrganizationIndirectcost extends KcPersistableBusinessObjectBase {

    @Id
    @Column(name = "IDC_NUMBER")
    private Integer idcNumber;

    @Id
    @Column(name = "ORGANIZATION_ID")
    private String organizationId;

    @Column(name = "APPLICABLE_IDC_RATE")
    @Convert(converter = KualiDecimalConverter.class)
    private KualiDecimal applicableIndirectcostRate;

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

    @ManyToOne(targetEntity = Organization.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "ORGANIZATION_ID", referencedColumnName = "ORGANIZATION_ID", insertable = false, updatable = false)
    private Organization organization;

    public OrganizationIndirectcost() {
        super();
    }

    public Integer getIdcNumber() {
        return idcNumber;
    }

    public void setIdcNumber(Integer idcNumber) {
        this.idcNumber = idcNumber;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public KualiDecimal getApplicableIndirectcostRate() {
        return applicableIndirectcostRate;
    }

    public void setApplicableIndirectcostRate(KualiDecimal applicableIndirectcostRate) {
        this.applicableIndirectcostRate = applicableIndirectcostRate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getIdcComment() {
        return idcComment;
    }

    public void setIdcComment(String idcComment) {
        this.idcComment = idcComment;
    }

    public Integer getIdcRateTypeCode() {
        return idcRateTypeCode;
    }

    public void setIdcRateTypeCode(Integer idcRateTypeCode) {
        this.idcRateTypeCode = idcRateTypeCode;
    }

    public Date getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(Date requestedDate) {
        this.requestedDate = requestedDate;
    }

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

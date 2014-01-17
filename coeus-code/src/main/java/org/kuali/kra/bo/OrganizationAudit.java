/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.bo;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.kuali.kra.bo.Organization;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;

@Entity
@Table(name = "ORGANIZATION_AUDIT")
@IdClass(OrganizationAudit.OrganizationAuditId.class)
public class OrganizationAudit extends KraPersistableBusinessObjectBase {

    @Id
    @Column(name = "FISCAL_YEAR")
    private String fiscalYear;

    @Id
    @Column(name = "ORGANIZATION_ID")
    private String organizationId;

    @Column(name = "AUDIT_ACCEPTED")
    @Convert(converter = BooleanYNConverter.class)
    private boolean auditAccepted;

    @Column(name = "AUDIT_COMMENT")
    private String auditComment;

    @ManyToOne(targetEntity = Organization.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "ORGANIZATION_ID", referencedColumnName = "ORGANIZATION_ID", insertable = false, updatable = false)
    private Organization organization;

    public OrganizationAudit() {
        super();
    }

    public String getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(String fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public boolean getAuditAccepted() {
        return auditAccepted;
    }

    public void setAuditAccepted(boolean auditAccepted) {
        this.auditAccepted = auditAccepted;
    }

    public String getAuditComment() {
        return auditComment;
    }

    public void setAuditComment(String auditComment) {
        this.auditComment = auditComment;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public static final class OrganizationAuditId implements Serializable, Comparable<OrganizationAuditId> {

        private String fiscalYear;

        private String organizationId;

        public String getFiscalYear() {
            return this.fiscalYear;
        }

        public void setFiscalYear(String fiscalYear) {
            this.fiscalYear = fiscalYear;
        }

        public String getOrganizationId() {
            return this.organizationId;
        }

        public void setOrganizationId(String organizationId) {
            this.organizationId = organizationId;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("fiscalYear", this.fiscalYear).append("organizationId", this.organizationId).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final OrganizationAuditId rhs = (OrganizationAuditId) other;
            return new EqualsBuilder().append(this.fiscalYear, rhs.fiscalYear).append(this.organizationId, rhs.organizationId).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.fiscalYear).append(this.organizationId).toHashCode();
        }

        @Override
        public int compareTo(OrganizationAuditId other) {
            return new CompareToBuilder().append(this.fiscalYear, other.fiscalYear).append(this.organizationId, other.organizationId).toComparison();
        }
    }
}

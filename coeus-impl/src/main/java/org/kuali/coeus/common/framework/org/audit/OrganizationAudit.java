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
package org.kuali.coeus.common.framework.org.audit;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.common.api.org.audit.OrganizationAuditContract;
import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ORGANIZATION_AUDIT")
@IdClass(OrganizationAudit.OrganizationAuditId.class)
public class OrganizationAudit extends KcPersistableBusinessObjectBase implements OrganizationAuditContract {

    private static final String ACCEPTED = "1";

    @Id
    @Column(name = "FISCAL_YEAR")
    private String fiscalYear;

    @Id
    @Column(name = "ORGANIZATION_ID")
    private String organizationId;

    @Column(name = "AUDIT_ACCEPTED")
    private String auditAcceptedCode;

    @Column(name = "AUDIT_COMMENT")
    private String auditComment;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "ORGANIZATION_ID", referencedColumnName = "ORGANIZATION_ID", insertable = false, updatable = false)
    private Organization organization;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "AUDIT_ACCEPTED", referencedColumnName = "CODE", insertable = false, updatable = false)
    private OrganizationAuditAcceptedType auditAcceptedType;

    @Override
    public String getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(String fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    @Override
    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    @Override
    public boolean getAuditAccepted() {
        return ACCEPTED.equals(getAuditAcceptedCode());
    }

    public String getAuditAcceptedCode() {
        return auditAcceptedCode;
    }

    public void setAuditAcceptedCode(String auditAcceptedCode) {
        this.auditAcceptedCode = auditAcceptedCode;
    }

    public OrganizationAuditAcceptedType getAuditAcceptedType() {
        return auditAcceptedType;
    }

    public void setAuditAcceptedType(OrganizationAuditAcceptedType auditAcceptedType) {
        this.auditAcceptedType = auditAcceptedType;
    }

    @Override
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

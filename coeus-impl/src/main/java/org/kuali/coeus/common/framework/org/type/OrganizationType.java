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
package org.kuali.coeus.common.framework.org.type;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.common.api.org.type.OrganizationTypeContract;
import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ORGANIZATION_TYPE")
@IdClass(OrganizationType.OrganizationTypeId.class)
public class OrganizationType extends KcPersistableBusinessObjectBase implements OrganizationTypeContract {

    @Id
    @Column(name = "ORGANIZATION_ID")
    private String organizationId;

    @Id
    @Column(name = "ORGANIZATION_TYPE_CODE")
    private Integer organizationTypeCode;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "ORGANIZATION_TYPE_CODE", referencedColumnName = "ORGANIZATION_TYPE_CODE", insertable = false, updatable = false)
    private OrganizationTypeList organizationTypeList;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "ORGANIZATION_ID", referencedColumnName = "ORGANIZATION_ID", insertable = false, updatable = false)
    private Organization organization;

    public OrganizationType() {
        super();
    }

    @Override
    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getOrganizationTypeCode() {
        return organizationTypeCode;
    }

    public void setOrganizationTypeCode(Integer organizationTypeCode) {
        this.organizationTypeCode = organizationTypeCode;
    }

    @Override
    public OrganizationTypeList getOrganizationTypeList() {
        return organizationTypeList;
    }

    public void setOrganizationTypeList(OrganizationTypeList organizationTypeList) {
        this.organizationTypeList = organizationTypeList;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public static final class OrganizationTypeId implements Serializable, Comparable<OrganizationTypeId> {

        private String organizationId;

        private Integer organizationTypeCode;

        public String getOrganizationId() {
            return this.organizationId;
        }

        public void setOrganizationId(String organizationId) {
            this.organizationId = organizationId;
        }

        public Integer getOrganizationTypeCode() {
            return this.organizationTypeCode;
        }

        public void setOrganizationTypeCode(Integer organizationTypeCode) {
            this.organizationTypeCode = organizationTypeCode;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("organizationId", this.organizationId).append("organizationTypeCode", this.organizationTypeCode).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final OrganizationTypeId rhs = (OrganizationTypeId) other;
            return new EqualsBuilder().append(this.organizationId, rhs.organizationId).append(this.organizationTypeCode, rhs.organizationTypeCode).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.organizationId).append(this.organizationTypeCode).toHashCode();
        }

        @Override
        public int compareTo(OrganizationTypeId other) {
            return new CompareToBuilder().append(this.organizationId, other.organizationId).append(this.organizationTypeCode, other.organizationTypeCode).toComparison();
        }
    }
}

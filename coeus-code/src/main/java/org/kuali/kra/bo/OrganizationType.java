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
import org.kuali.kra.bo.OrganizationTypeList;

@Entity
@Table(name = "ORGANIZATION_TYPE")
@IdClass(OrganizationType.OrganizationTypeId.class)
public class OrganizationType extends KraPersistableBusinessObjectBase {

    @Id
    @Column(name = "ORGANIZATION_ID")
    private String organizationId;

    @Id
    @Column(name = "ORGANIZATION_TYPE_CODE")
    private Integer organizationTypeCode;

    @ManyToOne(targetEntity = OrganizationTypeList.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "ORGANIZATION_TYPE_CODE", referencedColumnName = "ORGANIZATION_TYPE_CODE", insertable = false, updatable = false)
    private OrganizationTypeList organizationTypeList;

    public OrganizationType() {
        super();
    }

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

    public OrganizationTypeList getOrganizationTypeList() {
        return organizationTypeList;
    }

    public void setOrganizationTypeList(OrganizationTypeList organizationTypeList) {
        this.organizationTypeList = organizationTypeList;
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

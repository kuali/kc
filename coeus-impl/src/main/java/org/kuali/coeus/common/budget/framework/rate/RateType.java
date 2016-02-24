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
package org.kuali.coeus.common.budget.framework.rate;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.common.budget.api.rate.RateTypeContract;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

@Entity
@Table(name = "RATE_TYPE")
@IdClass(RateType.RateTypeId.class)
public class RateType extends KcPersistableBusinessObjectBase implements Comparable<RateType>, RateTypeContract {

    @Id
    @Column(name = "RATE_CLASS_CODE")
    private String rateClassCode;

    @Id
    @Column(name = "RATE_TYPE_CODE")
    private String rateTypeCode;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "RATE_CLASS_CODE", referencedColumnName = "RATE_CLASS_CODE", insertable = false, updatable = false)
    private RateClass rateClass;

    @Override
    public String getRateClassCode() {
        return rateClassCode;
    }

    public void setRateClassCode(String rateClassCode) {
        this.rateClassCode = rateClassCode;
    }

    @Override
    public String getRateTypeCode() {
        return rateTypeCode;
    }

    public void setRateTypeCode(String rateTypeCode) {
        this.rateTypeCode = rateTypeCode;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * This is helper method to get prefix for total page display.
     * @return
     */
    public String getRateClassPrefix() {
        this.refreshReferenceObject("rateClass");
        RateClassType rateClassType = getRateClass().getRateClassType();
        return rateClassType.getDescription();
    }

    @Override
    public RateClass getRateClass() {
        return rateClass;
    }

    public void setRateClass(RateClass rateClass) {
        this.rateClass = rateClass;
    }

    @Override
    public int compareTo(RateType rateType) {
        return new CompareToBuilder().append(this.rateClassCode, rateType.rateClassCode).append(this.rateTypeCode, rateType.rateTypeCode).toComparison();
    }

    public static final class RateTypeId implements Serializable, Comparable<RateTypeId> {

        private String rateClassCode;

        private String rateTypeCode;

        public String getRateClassCode() {
            return this.rateClassCode;
        }

        public void setRateClassCode(String rateClassCode) {
            this.rateClassCode = rateClassCode;
        }

        public String getRateTypeCode() {
            return this.rateTypeCode;
        }

        public void setRateTypeCode(String rateTypeCode) {
            this.rateTypeCode = rateTypeCode;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("rateClassCode", this.rateClassCode).append("rateTypeCode", this.rateTypeCode).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final RateTypeId rhs = (RateTypeId) other;
            return new EqualsBuilder().append(this.rateClassCode, rhs.rateClassCode).append(this.rateTypeCode, rhs.rateTypeCode).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.rateClassCode).append(this.rateTypeCode).toHashCode();
        }

        @Override
        public int compareTo(RateTypeId other) {
            return new CompareToBuilder().append(this.rateClassCode, other.rateClassCode).append(this.rateTypeCode, other.rateTypeCode).toComparison();
        }
    }
}

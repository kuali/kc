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
import org.kuali.coeus.common.budget.api.rate.ValidCeRateTypeContract;
import org.kuali.coeus.common.budget.framework.core.CostElement;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;

@Entity
@Table(name = "VALID_CE_RATE_TYPES")
@IdClass(ValidCeRateType.ValidCeRateTypeId.class)
public class ValidCeRateType extends KcPersistableBusinessObjectBase implements MutableInactivatable, ValidCeRateTypeContract {

    @Id
    @Column(name = "COST_ELEMENT")
    private String costElement;

    @Id
    @Column(name = "RATE_CLASS_CODE")
    private String rateClassCode;

    @Id
    @Column(name = "RATE_TYPE_CODE")
    private String rateTypeCode;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "RATE_CLASS_CODE", referencedColumnName = "RATE_CLASS_CODE", insertable = false, updatable = false)
    private RateClass rateClass;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    @JoinColumns({ @JoinColumn(name = "RATE_CLASS_CODE", referencedColumnName = "RATE_CLASS_CODE", insertable = false, updatable = false), @JoinColumn(name = "RATE_TYPE_CODE", referencedColumnName = "RATE_TYPE_CODE", insertable = false, updatable = false) })
    private RateType rateType;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "COST_ELEMENT", referencedColumnName = "COST_ELEMENT", insertable = false, updatable = false)
    private CostElement costElementBo;

    @Column(name = "ACTIVE_FLAG")
    @Convert(converter = BooleanYNConverter.class)
    private boolean active;

    @Override
    public RateClass getRateClass() {
        return rateClass;
    }

    public void setRateClass(RateClass rateClass) {
        this.rateClass = rateClass;
    }

    @Override
    public String getCostElement() {
        return costElement;
    }

    public void setCostElement(String costElement) {
        this.costElement = costElement;
    }

    public String getRateClassCode() {
        return rateClassCode;
    }

    public void setRateClassCode(String rateClassCode) {
        this.rateClassCode = rateClassCode;
    }

    public String getRateTypeCode() {
        return rateTypeCode;
    }

    public void setRateTypeCode(String rateTypeCode) {
        this.rateTypeCode = rateTypeCode;
    }

    @Override
    public RateType getRateType() {
        return rateType;
    }

    public void setRateType(RateType rateType) {
        this.rateType = rateType;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRateClassType() {
        return rateClass.getRateClassTypeCode();
    }

    public CostElement getCostElementBo() {
        return costElementBo;
    }

    public void setCostElementBo(CostElement costElementBo) {
        this.costElementBo = costElementBo;
    }

    public static final class ValidCeRateTypeId implements Serializable, Comparable<ValidCeRateTypeId> {

        private String costElement;

        private String rateClassCode;

        private String rateTypeCode;

        public String getCostElement() {
            return this.costElement;
        }

        public void setCostElement(String costElement) {
            this.costElement = costElement;
        }

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
            return new ToStringBuilder(this).append("costElement", this.costElement).append("rateClassCode", this.rateClassCode).append("rateTypeCode", this.rateTypeCode).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final ValidCeRateTypeId rhs = (ValidCeRateTypeId) other;
            return new EqualsBuilder().append(this.costElement, rhs.costElement).append(this.rateClassCode, rhs.rateClassCode).append(this.rateTypeCode, rhs.rateTypeCode).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.costElement).append(this.rateClassCode).append(this.rateTypeCode).toHashCode();
        }

        @Override
        public int compareTo(ValidCeRateTypeId other) {
            return new CompareToBuilder().append(this.costElement, other.costElement).append(this.rateClassCode, other.rateClassCode).append(this.rateTypeCode, other.rateTypeCode).toComparison();
        }
    }
}

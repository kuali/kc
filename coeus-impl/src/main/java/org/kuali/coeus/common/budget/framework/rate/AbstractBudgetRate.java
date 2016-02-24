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
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.common.budget.api.core.IdentifiableBudget;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.api.rate.RateClassType;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.sys.framework.persistence.ScaleTwoDecimalConverter;

@MappedSuperclass
@IdClass(AbstractBudgetRate.AbstractBudgetRateId.class)
public abstract class AbstractBudgetRate extends AbstractInstituteRate implements IdentifiableBudget {

    private static final long serialVersionUID = -7152006670699620080L;

    @Id
    @Column(name = "BUDGET_ID", insertable = false, updatable = false)
    private Long budgetId;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "BUDGET_ID", referencedColumnName = "BUDGET_ID")
    private Budget budget;

    @Column(name = "APPLICABLE_RATE")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal applicableRate;

    @Transient
    private ScaleTwoDecimal oldApplicableRate;

    @Transient
    private boolean rateChanged;

    @Transient
    private String viewLocation;

    @Transient
    private boolean displayLocation = true;

    @Transient
    private String budgetPeriod;

    @Transient
    private String affectedBudgetPeriod;

    @Transient
    private String trackAffectedPeriod;

    public AbstractBudgetRate() {
        super();
    }

    public AbstractBudgetRate(String unitNumber, AbstractInstituteRate abstractInstituteRate) {
        this();
        copyValues(unitNumber, abstractInstituteRate);
    }

    private void copyValues(String unitNumber, AbstractInstituteRate abstractInstituteRate) {
        setApplicableRate(abstractInstituteRate.getExternalApplicableRate());
        setFiscalYear(abstractInstituteRate.getFiscalYear());
        setInstituteRate(abstractInstituteRate.getInstituteRate());
        setOnOffCampusFlag(abstractInstituteRate.getOnOffCampusFlag());
        setRateClass(abstractInstituteRate.getRateClass());
        setRateClassCode(abstractInstituteRate.getRateClassCode());
        setRateType(abstractInstituteRate.getRateType());
        setRateTypeCode(abstractInstituteRate.getRateTypeCode());
        setStartDate(abstractInstituteRate.getStartDate());
        setUnitNumber(unitNumber);
        setOldApplicableRate(abstractInstituteRate.getInstituteRate());
        setNonEditableRateFlag(abstractInstituteRate.getNonEditableRateFlag());
    }

    @Override
    public boolean getNonEditableRateFlag() {
        boolean nonEditableRate = false;
        if (getBudget() != null) {
            if (RateClassType.EMPLOYEE_BENEFITS.getRateClassType().equals(getRateClassType())) {
                nonEditableRate = getBudget().getEbRatesNonEditable();
            } else if (RateClassType.OVERHEAD.getRateClassType().equals(getRateClassType())) {
                nonEditableRate = getBudget().getOhRatesNonEditable();
            }
        }
        return nonEditableRate;
    }

    public ScaleTwoDecimal getApplicableRate() {
        return ScaleTwoDecimal.returnZeroIfNull(applicableRate);
    }

    public ScaleTwoDecimal getExactApplicableRate() {
        return applicableRate;
    }

    public boolean isApplicableRateNull() {
        return applicableRate == null;
    }

    public void setExactApplicableRate(ScaleTwoDecimal applicableRate) {
        setApplicableRate(applicableRate);
    }

    public void setApplicableRate(ScaleTwoDecimal applicableRate) {
        setOldApplicableRate(this.applicableRate);
        if (!ObjectUtils.equals(this.applicableRate, applicableRate)) {
            setRateChanged(true);
        }
        this.applicableRate = applicableRate;
    }

    public ScaleTwoDecimal getOldApplicableRate() {
        return ScaleTwoDecimal.returnZeroIfNull(oldApplicableRate);
    }

    public void setOldApplicableRate(ScaleTwoDecimal oldApplicableRate) {
        this.oldApplicableRate = oldApplicableRate;
    }

    public String getViewLocation() {
        return viewLocation;
    }

    public void setViewLocation(String viewLocation) {
        this.viewLocation = viewLocation;
    }

    public boolean isDisplayLocation() {
        return displayLocation;
    }

    public void setDisplayLocation(boolean displayLocation) {
        this.displayLocation = displayLocation;
    }

    public String getBudgetPeriod() {
        return budgetPeriod;
    }

    public void setBudgetPeriod(String budgetPeriod) {
        this.budgetPeriod = budgetPeriod;
    }

    public final String getAffectedBudgetPeriod() {
        return affectedBudgetPeriod;
    }

    public final void setAffectedBudgetPeriod(String affectedBudgetPeriod) {
        this.affectedBudgetPeriod = affectedBudgetPeriod;
    }

    public final String getTrackAffectedPeriod() {
        return trackAffectedPeriod;
    }

    public final void setTrackAffectedPeriod(String trackAffectedPeriod) {
        this.trackAffectedPeriod = trackAffectedPeriod;
    }

    /**
     * Gets the budget attribute. 
     * @return Returns the budget.
     */
    public Budget getBudget() {
        return budget;
    }

    /**
     * Sets the budget attribute value.
     * @param budget The budget to set.
     */
    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    /**
     * Gets the budgetId attribute. 
     * @return Returns the budgetId.
     */
    public Long getBudgetId() {
        return budgetId;
    }

    /**
     * Sets the budgetId attribute value.
     * @param budgetId The budgetId to set.
     */
    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((affectedBudgetPeriod == null) ? 0 : affectedBudgetPeriod.hashCode());
        result = prime * result + ((applicableRate == null) ? 0 : applicableRate.hashCode());
        result = prime * result + ((budgetId == null) ? 0 : budgetId.hashCode());
        result = prime * result + ((budgetPeriod == null) ? 0 : budgetPeriod.hashCode());
        result = prime * result + (displayLocation ? 1231 : 1237);
        result = prime * result + ((oldApplicableRate == null) ? 0 : oldApplicableRate.hashCode());
        result = prime * result + ((trackAffectedPeriod == null) ? 0 : trackAffectedPeriod.hashCode());
        result = prime * result + ((viewLocation == null) ? 0 : viewLocation.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractBudgetRate other = (AbstractBudgetRate) obj;
        if (affectedBudgetPeriod == null) {
            if (other.affectedBudgetPeriod != null)
                return false;
        } else if (!affectedBudgetPeriod.equals(other.affectedBudgetPeriod))
            return false;
        if (applicableRate == null) {
            if (other.applicableRate != null)
                return false;
        } else if (!applicableRate.equals(other.applicableRate))
            return false;
        if (budget == null) {
            if (other.budget != null)
                return false;
        } else if (!budget.equals(other.budget))
            return false;
        if (budgetId == null) {
            if (other.budgetId != null)
                return false;
        } else if (!budgetId.equals(other.budgetId))
            return false;
        if (budgetPeriod == null) {
            if (other.budgetPeriod != null)
                return false;
        } else if (!budgetPeriod.equals(other.budgetPeriod))
            return false;
        if (displayLocation != other.displayLocation)
            return false;
        if (oldApplicableRate == null) {
            if (other.oldApplicableRate != null)
                return false;
        } else if (!oldApplicableRate.equals(other.oldApplicableRate))
            return false;
        if (trackAffectedPeriod == null) {
            if (other.trackAffectedPeriod != null)
                return false;
        } else if (!trackAffectedPeriod.equals(other.trackAffectedPeriod))
            return false;
        if (viewLocation == null) {
            if (other.viewLocation != null)
                return false;
        } else if (!viewLocation.equals(other.viewLocation))
            return false;
        return true;
    }

    public boolean isRateChanged() {
        return rateChanged;
    }

    public void setRateChanged(boolean rateChanged) {
        this.rateChanged = rateChanged;
    }
    
    public static final class AbstractBudgetRateId extends AbstractInstituteRateId implements Serializable, Comparable<AbstractBudgetRateId> {

    	private Long budgetId;

        @Override
        public String toString() {
            return new ToStringBuilder(this).appendSuper(super.toString())
            		.append("budgetId", this.budgetId).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final AbstractBudgetRateId rhs = (AbstractBudgetRateId) other;
            return new EqualsBuilder().appendSuper(super.equals(rhs)).append(this.budgetId, rhs.budgetId).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder().appendSuper(super.hashCode()).append(this.budgetId).toHashCode();
        }

        @Override
        public int compareTo(AbstractBudgetRateId other) {
            return new CompareToBuilder().appendSuper(super.compareTo((AbstractInstituteRate.AbstractInstituteRateId)other)).append(this.budgetId, other.budgetId).toComparison();
        }

		public Long getBudgetId() {
			return budgetId;
		}

		public void setBudgetId(Long budgetId) {
			this.budgetId = budgetId;
		}
    }
}

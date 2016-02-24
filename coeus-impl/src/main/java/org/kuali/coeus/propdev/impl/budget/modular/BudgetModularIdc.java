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
package org.kuali.coeus.propdev.impl.budget.modular;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.common.budget.framework.rate.RateClass;
import org.kuali.coeus.propdev.api.budget.modular.BudgetModularIdcContract;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.persistence.ScaleTwoDecimalConverter;

@Entity
@Table(name = "BUDGET_MODULAR_IDC")
@IdClass(BudgetModularIdc.BudgetModularIdcId.class)
public class BudgetModularIdc extends KcPersistableBusinessObjectBase implements BudgetModularIdcContract {

    private static final long serialVersionUID = 9162516694202776979L;

    @Id
    @Column(name = "BUDGET_PERIOD_NUMBER" , insertable = false, updatable = false)
    private Long budgetPeriodId;

    @Column(name = "BUDGET_PERIOD")
    private Integer budgetPeriod;

    @Id
    @Column(name = "RATE_NUMBER")
    private Integer rateNumber;

    @Column(name = "BUDGET_ID")
    private Long budgetId;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IDC_RATE")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal idcRate;

    @Column(name = "IDC_BASE")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal idcBase;

    @Column(name = "FUNDS_REQUESTED")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal fundsRequested;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "DESCRIPTION", referencedColumnName = "RATE_CLASS_CODE", insertable = false, updatable = false)
    private RateClass rateClass;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "BUDGET_PERIOD_NUMBER", referencedColumnName = "BUDGET_PERIOD_NUMBER")
    private BudgetModular budgetModular;

    public BudgetModularIdc() {
        idcRate = new ScaleTwoDecimal(0.0);
        idcBase = new ScaleTwoDecimal(0.0);
        fundsRequested = new ScaleTwoDecimal(0.0);
    }

    @Override
    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

    @Override
    public Integer getBudgetPeriod() {
        return budgetPeriod;
    }

    public void setBudgetPeriod(Integer budgetPeriod) {
        this.budgetPeriod = budgetPeriod;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public ScaleTwoDecimal getFundsRequested() {
        return fundsRequested;
    }

    public void setFundsRequested(ScaleTwoDecimal fundsRequested) {
        this.fundsRequested = fundsRequested;
    }

    @Override
    public ScaleTwoDecimal getIdcBase() {
        return idcBase;
    }

    public void setIdcBase(ScaleTwoDecimal idcBase) {
        this.idcBase = idcBase;
    }

    @Override
    public ScaleTwoDecimal getIdcRate() {
        return idcRate;
    }

    public void setIdcRate(ScaleTwoDecimal idcRate) {
        this.idcRate = idcRate;
    }

    @Override
    public Integer getRateNumber() {
        return rateNumber;
    }

    public void setRateNumber(Integer rateNumber) {
        this.rateNumber = rateNumber;
    }

    @Override
    public RateClass getRateClass() {
        return rateClass;
    }

    public void setRateClass(RateClass rateClass) {
        this.rateClass = rateClass;
    }

    public void calculateFundsRequested() {
        ScaleTwoDecimal fundsRequested = new ScaleTwoDecimal(0);
        if (this.getIdcBase() != null && this.getIdcRate() != null) {
            fundsRequested = this.getIdcBase().percentage(this.getIdcRate());
        }
        this.setFundsRequested(fundsRequested);
    }

    @Override
    public Long getBudgetPeriodId() {
        return budgetPeriodId;
    }

    public void setBudgetPeriodId(Long budgetPeriodId) {
        this.budgetPeriodId = budgetPeriodId;
    }

    public static final class BudgetModularIdcId implements Serializable, Comparable<BudgetModularIdcId> {

        private Long budgetPeriodId;

        private Integer rateNumber;

        public Long getBudgetPeriodId() {
            return this.budgetPeriodId;
        }

        public void setBudgetPeriodId(Long budgetPeriodId) {
            this.budgetPeriodId = budgetPeriodId;
        }

        public Integer getRateNumber() {
            return this.rateNumber;
        }

        public void setRateNumber(Integer rateNumber) {
            this.rateNumber = rateNumber;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("budgetPeriodId", this.budgetPeriodId).append("rateNumber", this.rateNumber).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final BudgetModularIdcId rhs = (BudgetModularIdcId) other;
            return new EqualsBuilder().append(this.budgetPeriodId, rhs.budgetPeriodId).append(this.rateNumber, rhs.rateNumber).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.budgetPeriodId).append(this.rateNumber).toHashCode();
        }

        @Override
        public int compareTo(BudgetModularIdcId other) {
            return new CompareToBuilder().append(this.budgetPeriodId, other.budgetPeriodId).append(this.rateNumber, other.rateNumber).toComparison();
        }
    }
    public BudgetModular getBudgetModular() { return budgetModular; }

    public void setBudgetModular(BudgetModular budgetModular) {
        this.budgetModular = budgetModular;
    }

}

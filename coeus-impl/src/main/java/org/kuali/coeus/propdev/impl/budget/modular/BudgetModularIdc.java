/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
    @Column(name = "BUDGET_PERIOD_NUMBER")
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
}

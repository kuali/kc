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
package org.kuali.coeus.common.budget.framework.nonpersonnel;

import javax.persistence.*;

import org.kuali.coeus.common.budget.api.nonpersonnel.AbstractBudgetCalculatedAmountContract;
import org.kuali.coeus.common.budget.framework.rate.BudgetRatesService;
import org.kuali.coeus.common.budget.framework.rate.RateClass;
import org.kuali.coeus.common.budget.framework.rate.RateType;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.persistence.ScaleTwoDecimalConverter;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;

@MappedSuperclass
public abstract class AbstractBudgetCalculatedAmount extends KcPersistableBusinessObjectBase implements AbstractBudgetCalculatedAmountContract {

    private static final long serialVersionUID = 4346953317701218299L;


    @Column(name = "BUDGET_ID")
    private Long budgetId;

    @Column(name = "BUDGET_PERIOD")
    private Integer budgetPeriod;

    @Column(name = "BUDGET_PERIOD_NUMBER")
    private Long budgetPeriodId;

    @Column(name = "LINE_ITEM_NUMBER")
    private Integer lineItemNumber;

    @Column(name = "RATE_CLASS_CODE")
    private String rateClassCode;

    @Column(name = "RATE_TYPE_CODE")
    private String rateTypeCode;

    @Column(name = "APPLY_RATE_FLAG")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean applyRateFlag;

    @Column(name = "CALCULATED_COST")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal calculatedCost;

    @Column(name = "CALCULATED_COST_SHARING")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal calculatedCostSharing;

    @Column(name = "RATE_TYPE_DESCRIPTION")
    private String rateTypeDescription;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "RATE_CLASS_CODE", referencedColumnName = "RATE_CLASS_CODE", insertable = false, updatable = false)
    private RateClass rateClass;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    @JoinColumns({ @JoinColumn(name = "RATE_CLASS_CODE", referencedColumnName = "RATE_CLASS_CODE", insertable = false, updatable = false), @JoinColumn(name = "RATE_TYPE_CODE", referencedColumnName = "RATE_TYPE_CODE", insertable = false, updatable = false) })
    private RateType rateType;
    
    @Transient
    private String rateClassType;

    @Transient
    private Integer rateNumber;

    public Long getBudgetPeriodId() {
        return budgetPeriodId;
    }

    public void setBudgetPeriodId(Long budgetPeriodId) {
        this.budgetPeriodId = budgetPeriodId;
    }

    @Override
    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

    @Override
    public RateClass getRateClass() {
        return rateClass;
    }

    public void setRateClass(RateClass rateClass) {
        this.rateClass = rateClass;
    }

    @Override
    public Integer getBudgetPeriod() {
        return budgetPeriod;
    }

    public void setBudgetPeriod(Integer budgetPeriod) {
        this.budgetPeriod = budgetPeriod;
    }

    @Override
    public Integer getLineItemNumber() {
        return lineItemNumber;
    }

    public void setLineItemNumber(Integer lineItemNumber) {
        this.lineItemNumber = lineItemNumber;
    }

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
    public Boolean getApplyRateFlag() {
        return applyRateFlag;
    }

    public void setApplyRateFlag(Boolean applyRateFlag) {
        this.applyRateFlag = applyRateFlag;
    }

    @Override
    public ScaleTwoDecimal getCalculatedCost() {
        return calculatedCost;
    }

    public void setCalculatedCost(ScaleTwoDecimal calculatedCost) {
        this.calculatedCost = calculatedCost;
    }

    @Override
    public ScaleTwoDecimal getCalculatedCostSharing() {
        return calculatedCostSharing;
    }

    public void setCalculatedCostSharing(ScaleTwoDecimal calculatedCostSharing) {
        this.calculatedCostSharing = calculatedCostSharing;
    }

    public String getRateClassType() {
        return rateClassType;
    }

    public void setRateClassType(String rateClassType) {
        this.rateClassType = rateClassType;
    }

    public Integer getRateNumber() {
        return rateNumber;
    }

    public void setRateNumber(Integer rateNumber) {
        this.rateNumber = rateNumber;
    }

    @Override
    public String getRateTypeDescription() {
        return rateTypeDescription;
    }

    public void setRateTypeDescription(String rateTypeDescription) {
        this.rateTypeDescription = rateTypeDescription;
    }

    public boolean getAddToFringeRate() {
        return !(getBudgetRatesService().isEmployeeBenefitOnLabAllocation(getRateClassCode(), getRateTypeCode())
                || getBudgetRatesService().isVacationOnLabAllocation(getRateClassCode(), getRateTypeCode())
                || getBudgetRatesService().isLabAllocationSalary(getRateClass().getRateClassTypeCode()))
                && (getBudgetRatesService().isEmployeeBenefit(getRateClass().getRateClassTypeCode()) || getBudgetRatesService().isVacation(getRateClass().getRateClassTypeCode()));
    }

    private BudgetRatesService getBudgetRatesService() {
        return KcServiceLocator.getService(BudgetRatesService.class);
    }

    public RateType getRateType() {
		return rateType;
	}

	public void setRateType(RateType rateType) {
		this.rateType = rateType;
	}

}

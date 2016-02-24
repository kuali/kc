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

import java.sql.Date;
import javax.persistence.*;

import org.kuali.coeus.common.budget.api.nonpersonnel.AbstractBudgetRateAndBaseContract;
import org.kuali.coeus.common.budget.framework.rate.RateClass;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.persistence.BooleanNFConverter;
import org.kuali.coeus.sys.framework.persistence.ScaleTwoDecimalConverter;

@MappedSuperclass
public abstract class AbstractBudgetRateAndBase extends KcPersistableBusinessObjectBase implements AbstractBudgetRateAndBaseContract {

    private static final long serialVersionUID = 5786156490914012479L;

    @Column(name = "BUDGET_ID")
    private Long budgetId;

    @Column(name = "BUDGET_PERIOD")
    private Integer budgetPeriod;

    @Column(name = "LINE_ITEM_NUMBER")
    private Integer lineItemNumber;

    @Column(name = "RATE_CLASS_CODE")
    private String rateClassCode;

    @Column(name = "RATE_NUMBER")
    private Integer rateNumber;

    @Column(name = "RATE_TYPE_CODE")
    private String rateTypeCode;

    @Column(name = "APPLIED_RATE")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal appliedRate;

    @Column(name = "BASE_COST_SHARING")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal baseCostSharing;

    @Column(name = "CALCULATED_COST")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal calculatedCost;

    @Column(name = "CALCULATED_COST_SHARING")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal calculatedCostSharing;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "ON_OFF_CAMPUS_FLAG")
    @Convert(converter = BooleanNFConverter.class)
    private Boolean onOffCampusFlag;

    @Column(name = "START_DATE")
    private Date startDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "RATE_CLASS_CODE", referencedColumnName = "RATE_CLASS_CODE", insertable = false, updatable = false)
    private RateClass rateClass;

    @Column(name = "BUDGET_PERIOD_NUMBER")
    private Long budgetPeriodId; 

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
    public Integer getLineItemNumber() {
        return lineItemNumber;
    }

    public void setLineItemNumber(Integer lineItemNumber) {
        this.lineItemNumber = lineItemNumber;
    }

    @Override
    public String getRateClassCode() {
        return rateClassCode;
    }

    public void setRateClassCode(String rateClassCode) {
        this.rateClassCode = rateClassCode;
    }

    @Override
    public Integer getRateNumber() {
        return rateNumber;
    }

    public void setRateNumber(Integer rateNumber) {
        this.rateNumber = rateNumber;
    }

    @Override
    public String getRateTypeCode() {
        return rateTypeCode;
    }

    public void setRateTypeCode(String rateTypeCode) {
        this.rateTypeCode = rateTypeCode;
    }

    @Override
    public ScaleTwoDecimal getAppliedRate() {
        return ScaleTwoDecimal.returnZeroIfNull(appliedRate);
    }

    public void setAppliedRate(ScaleTwoDecimal appliedRate) {
        this.appliedRate = appliedRate;
    }

    @Override
    public ScaleTwoDecimal getBaseCostSharing() {
        return ScaleTwoDecimal.returnZeroIfNull(baseCostSharing);
    }

    public void setBaseCostSharing(ScaleTwoDecimal baseCostSharing) {
        this.baseCostSharing = baseCostSharing;
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

    @Override
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public Boolean getOnOffCampusFlag() {
        return onOffCampusFlag;
    }

    public void setOnOffCampusFlag(Boolean onOffCampusFlag) {
        this.onOffCampusFlag = onOffCampusFlag;
    }

    @Override
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public RateClass getRateClass() {
        return rateClass;
    }

    public void setRateClass(RateClass rateClass) {
        this.rateClass = rateClass;
    }

    public Long getBudgetPeriodId() {
        return budgetPeriodId;
    }

    public void setBudgetPeriodId(Long budgetPeriodId) {
        this.budgetPeriodId = budgetPeriodId;
    }
}

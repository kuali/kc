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
package org.kuali.coeus.common.budget.framework.nonpersonnel;

import java.sql.Date;
import javax.persistence.*;

import org.kuali.coeus.common.budget.framework.rate.RateClass;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.persistence.BooleanNFConverter;
import org.kuali.coeus.sys.framework.persistence.ScaleTwoDecimalConverter;

@MappedSuperclass
public abstract class AbstractBudgetRateAndBase extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = 5786156490914012479L;

    @Column(name = "BUDGET_ID")
    private Long budgetId;

    @Transient
    private Long budgetPeriodId;

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

    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

    public Integer getBudgetPeriod() {
        return budgetPeriod;
    }

    public void setBudgetPeriod(Integer budgetPeriod) {
        this.budgetPeriod = budgetPeriod;
    }

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

    public Integer getRateNumber() {
        return rateNumber;
    }

    public void setRateNumber(Integer rateNumber) {
        this.rateNumber = rateNumber;
    }

    public String getRateTypeCode() {
        return rateTypeCode;
    }

    public void setRateTypeCode(String rateTypeCode) {
        this.rateTypeCode = rateTypeCode;
    }

    public ScaleTwoDecimal getAppliedRate() {
        return ScaleTwoDecimal.returnZeroIfNull(appliedRate);
    }

    public void setAppliedRate(ScaleTwoDecimal appliedRate) {
        this.appliedRate = appliedRate;
    }

    public ScaleTwoDecimal getBaseCostSharing() {
        return ScaleTwoDecimal.returnZeroIfNull(baseCostSharing);
    }

    public void setBaseCostSharing(ScaleTwoDecimal baseCostSharing) {
        this.baseCostSharing = baseCostSharing;
    }

    public ScaleTwoDecimal getCalculatedCost() {
        return calculatedCost;
    }

    public void setCalculatedCost(ScaleTwoDecimal calculatedCost) {
        this.calculatedCost = calculatedCost;
    }

    public ScaleTwoDecimal getCalculatedCostSharing() {
        return calculatedCostSharing;
    }

    public void setCalculatedCostSharing(ScaleTwoDecimal calculatedCostSharing) {
        this.calculatedCostSharing = calculatedCostSharing;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getOnOffCampusFlag() {
        return onOffCampusFlag;
    }

    public void setOnOffCampusFlag(Boolean onOffCampusFlag) {
        this.onOffCampusFlag = onOffCampusFlag;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public RateClass getRateClass() {
        return rateClass;
    }

    public void setRateClass(RateClass rateClass) {
        this.rateClass = rateClass;
    }

    /**
     * Gets the budgetPeriodId attribute.
     * @return Returns the budgetPeriodId.
     */
    public Long getBudgetPeriodId() {
        return budgetPeriodId;
    }

    /**
     * Sets the budgetPeriodId attribute value.
     * @param budgetPeriodId The budgetPeriodId to set.
     */
    public void setBudgetPeriodId(Long budgetPeriodId) {
        this.budgetPeriodId = budgetPeriodId;
    }
}

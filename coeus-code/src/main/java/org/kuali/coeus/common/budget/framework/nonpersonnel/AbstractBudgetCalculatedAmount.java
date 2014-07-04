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

import javax.persistence.*;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.api.nonpersonnel.AbstractBudgetCalculatedAmountContract;
import org.kuali.coeus.common.budget.api.rate.RateClassType;
import org.kuali.coeus.common.budget.framework.rate.RateClass;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.persistence.ScaleTwoDecimalConverter;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;

@MappedSuperclass
public abstract class AbstractBudgetCalculatedAmount extends KcPersistableBusinessObjectBase implements AbstractBudgetCalculatedAmountContract {

    private static final long serialVersionUID = 4346953317701218299L;

    @Column(name = "BUDGET_ID")
    private Long budgetId;

    @Column(name = "BUDGET_PERIOD")
    private Integer budgetPeriod;

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

    @Column(name = "BUDGET_DETAILS_ID")
    private Long budgetLineItemId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "RATE_CLASS_CODE", referencedColumnName = "RATE_CLASS_CODE", insertable = false, updatable = false)
    private RateClass rateClass;

    @Transient
    private String rateClassType;

    @Transient
    private Integer rateNumber;

    @Transient
    private Long budgetPeriodId;

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
    public Long getBudgetLineItemId() {
        return budgetLineItemId;
    }

    public void setBudgetLineItemId(Long budgetLineItemId) {
        this.budgetLineItemId = budgetLineItemId;
    }

    @Override
    public String getRateTypeDescription() {
        return rateTypeDescription;
    }

    public void setRateTypeDescription(String rateTypeDescription) {
        this.rateTypeDescription = rateTypeDescription;
    }

    public boolean getAddToFringeRate() {
        //employee benefits, research rate (not EB on LA)  
        boolean isEmployee = StringUtils.equalsIgnoreCase(this.getRateClass().getRateClassTypeCode(), RateClassType.EMPLOYEE_BENEFITS.getRateClassType());
        //vacation, vacation (not vacation LA)  
        boolean isGoodVacation = StringUtils.equalsIgnoreCase(this.getRateClass().getRateClassTypeCode(), RateClassType.VACATION.getRateClassType());
        return isEmployee || isGoodVacation;
    }
}

package org.kuali.coeus.award.dto;
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

import com.codiform.moo.annotation.Property;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;


public class BudgetLineItemCalculatedAmountDto {
    private Long budgetId;
    private Integer budgetPeriod;
    private Long budgetPeriodId;
    private Integer lineItemNumber;
    private Boolean applyRateFlag;
    private ScaleTwoDecimal calculatedCost;
    private ScaleTwoDecimal calculatedCostSharing;
    private String rateTypeDescription;
    private String rateClassCode;
    @JsonIgnore
    @Property(translate = true)
    private RateClassDto rateClass;
    private String rateTypeCode;
    @JsonIgnore
    @Property(translate = true)
    private RateTypeDto rateType;
    private Long budgetLineItemCalculatedAmountId;
    private Long budgetLineItemId;

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

    public Long getBudgetPeriodId() {
        return budgetPeriodId;
    }

    public void setBudgetPeriodId(Long budgetPeriodId) {
        this.budgetPeriodId = budgetPeriodId;
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

    public String getRateTypeCode() {
        return rateTypeCode;
    }

    public void setRateTypeCode(String rateTypeCode) {
        this.rateTypeCode = rateTypeCode;
    }

    public Boolean getApplyRateFlag() {
        return applyRateFlag;
    }

    public void setApplyRateFlag(Boolean applyRateFlag) {
        this.applyRateFlag = applyRateFlag;
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

    public String getRateTypeDescription() {
        return rateTypeDescription;
    }

    public void setRateTypeDescription(String rateTypeDescription) {
        this.rateTypeDescription = rateTypeDescription;
    }
    @JsonProperty
    public RateClassDto getRateClass() {
        return rateClass;
    }
    @JsonIgnore
    public void setRateClass(RateClassDto rateClass) {
        this.rateClass = rateClass;
    }
    @JsonProperty
    public RateTypeDto getRateType() {
        return rateType;
    }
    @JsonIgnore
    public void setRateType(RateTypeDto rateType) {
        this.rateType = rateType;
    }

    public Long getBudgetLineItemCalculatedAmountId() {
        return budgetLineItemCalculatedAmountId;
    }

    public void setBudgetLineItemCalculatedAmountId(Long budgetLineItemCalculatedAmountId) {
        this.budgetLineItemCalculatedAmountId = budgetLineItemCalculatedAmountId;
    }

    public Long getBudgetLineItemId() {
        return budgetLineItemId;
    }

    public void setBudgetLineItemId(Long budgetLineItemId) {
        this.budgetLineItemId = budgetLineItemId;
    }
}

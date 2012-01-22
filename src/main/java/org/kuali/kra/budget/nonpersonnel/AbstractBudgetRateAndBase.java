/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.budget.nonpersonnel;

import java.sql.Date;

import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.core.BudgetAssociate;
import org.kuali.kra.budget.rates.RateClass;

public abstract class AbstractBudgetRateAndBase extends BudgetAssociate {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 5786156490914012479L;

    private Long budgetPeriodId;

    private Integer budgetPeriod;

    private Integer lineItemNumber;

    private String rateClassCode;

    private Integer rateNumber;

    private String rateTypeCode;

    private BudgetDecimal appliedRate;

    private BudgetDecimal baseCostSharing;

    private BudgetDecimal calculatedCost;

    private BudgetDecimal calculatedCostSharing;

    private Date endDate;

    private Boolean onOffCampusFlag;

    private Date startDate;

    private RateClass rateClass;

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

    public BudgetDecimal getAppliedRate() {
        return BudgetDecimal.returnZeroIfNull(appliedRate);
    }

    public void setAppliedRate(BudgetDecimal appliedRate) {
        this.appliedRate = appliedRate;
    }

    public BudgetDecimal getBaseCostSharing() {
        return BudgetDecimal.returnZeroIfNull(baseCostSharing);
    }

    public void setBaseCostSharing(BudgetDecimal baseCostSharing) {
        this.baseCostSharing = baseCostSharing;
    }

    public BudgetDecimal getCalculatedCost() {
        return calculatedCost;
    }

    public void setCalculatedCost(BudgetDecimal calculatedCost) {
        this.calculatedCost = calculatedCost;
    }

    public BudgetDecimal getCalculatedCostSharing() {
        return calculatedCostSharing;
    }

    public void setCalculatedCostSharing(BudgetDecimal calculatedCostSharing) {
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

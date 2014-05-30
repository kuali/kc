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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.calculator.RateClassType;
import org.kuali.coeus.common.budget.framework.core.BudgetAssociate;
import org.kuali.coeus.common.budget.framework.rate.RateClass;

public abstract class AbstractBudgetCalculatedAmount extends BudgetAssociate {


    private static final long serialVersionUID = 4346953317701218299L;

    private Integer budgetPeriod;

    private Integer lineItemNumber;

    private String rateClassCode;

    private String rateTypeCode;

    private Boolean applyRateFlag;

    private ScaleTwoDecimal calculatedCost;

    private ScaleTwoDecimal calculatedCostSharing;

    private String rateClassType;

    private Integer rateNumber;

    private RateClass rateClass;

    private String rateTypeDescription;

    //	private RateType rateType; 
    private Long budgetPeriodId;

    private Long budgetLineItemId;

    public Long getBudgetPeriodId() {
        return budgetPeriodId;
    }

    public void setBudgetPeriodId(Long budgetPeriodId) {
        this.budgetPeriodId = budgetPeriodId;
    }

    /**
     * Gets the rateClass attribute. 
     * @return Returns the rateClass.
     */
    public RateClass getRateClass() {
        return rateClass;
    }

    /**
     * Sets the rateClass attribute value.
     * @param rateClass The rateClass to set.
     */
    public void setRateClass(RateClass rateClass) {
        this.rateClass = rateClass;
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

    /**
     * Gets the rateClassType attribute. 
     * @return Returns the rateClassType.
     */
    public String getRateClassType() {
        return rateClassType;
    }

    /**
     * Sets the rateClassType attribute value.
     * @param rateClassType The rateClassType to set.
     */
    public void setRateClassType(String rateClassType) {
        this.rateClassType = rateClassType;
    }

    /**
     * Gets the rateNumber attribute. 
     * @return Returns the rateNumber.
     */
    public Integer getRateNumber() {
        return rateNumber;
    }

    /**
     * Sets the rateNumber attribute value.
     * @param rateNumber The rateNumber to set.
     */
    public void setRateNumber(Integer rateNumber) {
        this.rateNumber = rateNumber;
    }

    /**
     * Gets the budgetLineItemId attribute. 
     * @return Returns the budgetLineItemId.
     */
    public Long getBudgetLineItemId() {
        return budgetLineItemId;
    }

    /**
     * Sets the budgetLineItemId attribute value.
     * @param budgetLineItemId The budgetLineItemId to set.
     */
    public void setBudgetLineItemId(Long budgetLineItemId) {
        this.budgetLineItemId = budgetLineItemId;
    }

    /**
     * Gets the rateTypeDescription attribute. 
     * @return Returns the rateTypeDescription.
     */
    public String getRateTypeDescription() {
        return rateTypeDescription;
    }

    /**
     * Sets the rateTypeDescription attribute value.
     * @param rateTypeDescription The rateTypeDescription to set.
     */
    public void setRateTypeDescription(String rateTypeDescription) {
        this.rateTypeDescription = rateTypeDescription;
    }
    
    public boolean getAddToFringeRate() {
        //employee benefits, research rate (not EB on LA)
        boolean isEmployee = StringUtils.equalsIgnoreCase(this.getRateClass().getRateClassType(), 
                RateClassType.EMPLOYEE_BENEFITS.getRateClassType());
        //vacation, vacation (not vacation LA)
        boolean isGoodVacation = StringUtils.equalsIgnoreCase(this.getRateClass().getRateClassType(), 
                RateClassType.VACATION.getRateClassType());

        
        return isEmployee || isGoodVacation;
    }
}

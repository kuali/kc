/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.budget.calculator;

import org.kuali.kra.budget.BudgetDecimal;

/**
 * Holds all the calculated amounts for a rate class - rate type combination for each
 * breakup interval.
 *
 */
public class RateAndCost{
    private String rateClassType;
    private String rateClassCode; 
    private String rateTypeCode; 
    private Boolean applyRateFlag;  
    private BudgetDecimal appliedRate;
    private BudgetDecimal calculatedCost; 
    private BudgetDecimal calculatedCostSharing; 
    private BudgetDecimal underRecovery; 
    private BudgetDecimal baseAmount;

    /** Getter for property rateClassCode.
     * @return Value of property rateClassCode.
     */
    public String getRateClassCode() {
        return rateClassCode;
    }    

    /** Setter for property rateClassCode.
     * @param rateClassCode New value of property rateClassCode.
     */
    public void setRateClassCode(String rateClassCode) {
        this.rateClassCode = rateClassCode;
    }
    
    /** Getter for property rateTypeCode.
     * @return Value of property rateTypeCode.
     */
    public String getRateTypeCode() {
        return rateTypeCode;
    }
    
    /** Setter for property rateTypeCode.
     * @param rateTypeCode New value of property rateTypeCode.
     */
    public void setRateTypeCode(String rateTypeCode) {
        this.rateTypeCode = rateTypeCode;
    }
    
    /** Getter for property applyRateFlag.
     * @return Value of property applyRateFlag.
     */
    public Boolean isApplyRateFlag() {
        return applyRateFlag;
    }
    
    /** Setter for property applyRateFlag.
     * @param applyRateFlag New value of property applyRateFlag.
     */
    public void setApplyRateFlag(Boolean applyRateFlag) {
        this.applyRateFlag = applyRateFlag;
    }
    
    /** Getter for property calculatedCost.
     * @return Value of property calculatedCost.
     */
    public BudgetDecimal getCalculatedCost() {
        return calculatedCost;
    }
    
    /** Setter for property calculatedCost.
     * @param calculatedCost New value of property calculatedCost.
     */
    public void setCalculatedCost(BudgetDecimal calculatedCost) {
        this.calculatedCost = calculatedCost;
    }
    
    /** Getter for property calculatedCostSharing.
     * @return Value of property calculatedCostSharing.
     */
    public BudgetDecimal getCalculatedCostSharing() {
        return calculatedCostSharing;
    }
    
    /** Setter for property calculatedCostSharing.
     * @param calculatedCostSharing New value of property calculatedCostSharing.
     */
    public void setCalculatedCostSharing(BudgetDecimal calculatedCostSharing) {
        this.calculatedCostSharing = calculatedCostSharing;
    }
    
    /** Getter for property rateClassType.
     * @return Value of property rateClassType.
     */
    public java.lang.String getRateClassType() {
        return rateClassType;
    }
    
    /** Setter for property rateClassType.
     * @param rateClassType New value of property rateClassType.
     */
    public void setRateClassType(java.lang.String rateClassType) {
        this.rateClassType = rateClassType;
    }
    
    /** Getter for property underRecovery.
     * @return Value of property underRecovery.
     */
    public BudgetDecimal getUnderRecovery() {
        return underRecovery;
    }
    
    /** Setter for property underRecovery.
     * @param underRecovery New value of property underRecovery.
     */
    public void setUnderRecovery(BudgetDecimal underRecovery) {
        this.underRecovery = underRecovery;
    }
    
    /** Getter for property appliedRate.
     * @return Value of property appliedRate.
     *
     */
    public BudgetDecimal getAppliedRate() {
        return appliedRate;
    }
    
    /** Setter for property appliedRate.
     * @param appliedRate New value of property appliedRate.
     *
     */
    public void setAppliedRate(BudgetDecimal appliedRate) {
        this.appliedRate = appliedRate;
    }
    
    public boolean equals(Object obj) {
        RateAndCost amountBean = (RateAndCost)obj;
        if(amountBean.getRateClassType().equals(getRateClassType()) &&
            amountBean.getRateClassCode() == getRateClassCode() &&
            amountBean.getRateTypeCode() == getRateTypeCode()){
            return true;
        }else {
            return false;
        }
    }
    
    /**
     * Getter for property baseAmount.
     * @return Value of property baseAmount.
     */
    public BudgetDecimal getBaseAmount() {
        return baseAmount;
    }
    
    /**
     * Setter for property baseAmount.
     * @param baseAmount New value of property baseAmount.
     */
    public void setBaseAmount(BudgetDecimal baseAmount) {
        this.baseAmount = baseAmount;
    }
    
 }
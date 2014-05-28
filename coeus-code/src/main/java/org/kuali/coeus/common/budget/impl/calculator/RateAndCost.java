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
package org.kuali.coeus.common.budget.impl.calculator;

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

/**
 * Holds all the calculated amounts for a rate class - rate type combination for each
 * breakup interval.
 *
 */
public class RateAndCost{
    @Override
    public String toString() {
        return "RateAndCost [rateClassType=" + rateClassType + ", rateClassCode=" + rateClassCode + ", rateTypeCode="
                + rateTypeCode + ", applyRateFlag=" + applyRateFlag + ", appliedRate=" + appliedRate + ", calculatedCost="
                + calculatedCost + ", calculatedCostSharing=" + calculatedCostSharing + ", underRecovery=" + underRecovery
                + ", baseAmount=" + baseAmount + ", baseCostSharingAmount=" + baseCostSharingAmount + ", calculated=" + calculated
                + "]";
    }

    private String rateClassType;
    private String rateClassCode; 
    private String rateTypeCode; 
    private Boolean applyRateFlag;  
    private ScaleTwoDecimal appliedRate;
    private ScaleTwoDecimal calculatedCost;
    private ScaleTwoDecimal calculatedCostSharing;
    private ScaleTwoDecimal underRecovery;
    private ScaleTwoDecimal baseAmount;
    private ScaleTwoDecimal baseCostSharingAmount;
    private boolean calculated;

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
    public ScaleTwoDecimal getCalculatedCost() {
        return calculatedCost;
    }
    
    /** Setter for property calculatedCost.
     * @param calculatedCost New value of property calculatedCost.
     */
    public void setCalculatedCost(ScaleTwoDecimal calculatedCost) {
        this.calculatedCost = calculatedCost;
    }
    
    /** Getter for property calculatedCostSharing.
     * @return Value of property calculatedCostSharing.
     */
    public ScaleTwoDecimal getCalculatedCostSharing() {
        return calculatedCostSharing;
    }
    
    /** Setter for property calculatedCostSharing.
     * @param calculatedCostSharing New value of property calculatedCostSharing.
     */
    public void setCalculatedCostSharing(ScaleTwoDecimal calculatedCostSharing) {
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
    public ScaleTwoDecimal getUnderRecovery() {
        return underRecovery==null? ScaleTwoDecimal.ZERO:underRecovery;
    }
    
    /** Setter for property underRecovery.
     * @param underRecovery New value of property underRecovery.
     */
    public void setUnderRecovery(ScaleTwoDecimal underRecovery) {
        this.underRecovery = underRecovery;
    }
    
    /** Getter for property appliedRate.
     * @return Value of property appliedRate.
     *
     */
    public ScaleTwoDecimal getAppliedRate() {
        return appliedRate;
    }
    
    /** Setter for property appliedRate.
     * @param appliedRate New value of property appliedRate.
     *
     */
    public void setAppliedRate(ScaleTwoDecimal appliedRate) {
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
    public ScaleTwoDecimal getBaseAmount() {
        return baseAmount==null? ScaleTwoDecimal.ZERO:baseAmount;
    }
    
    /**
     * Setter for property baseAmount.
     * @param baseAmount New value of property baseAmount.
     */
    public void setBaseAmount(ScaleTwoDecimal baseAmount) {
        this.baseAmount = baseAmount;
    }

    /**
     * Gets the baseCostSharingAmount attribute. 
     * @return Returns the baseCostSharingAmount.
     */
    public ScaleTwoDecimal getBaseCostSharingAmount() {
        return baseCostSharingAmount==null? ScaleTwoDecimal.ZERO:baseCostSharingAmount;
    }

    /**
     * Sets the baseCostSharingAmount attribute value.
     * @param baseCostSharingAmount The baseCostSharingAmount to set.
     */
    public void setBaseCostSharingAmount(ScaleTwoDecimal baseCostSharingAmount) {
        this.baseCostSharingAmount = baseCostSharingAmount;
    }

    /**
     * Gets the calculated attribute. 
     * @return Returns the calculated.
     */
    public boolean isCalculated() {
        return calculated;
    }

    /**
     * Sets the calculated attribute value.
     * @param calculated The calculated to set.
     */
    public void setCalculated(boolean calculated) {
        this.calculated = calculated;
    }
    
 }

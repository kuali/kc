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

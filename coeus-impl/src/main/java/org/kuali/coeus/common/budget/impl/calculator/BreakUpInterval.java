/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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

import org.kuali.coeus.common.budget.framework.query.QueryList;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetRateAndBase;
import org.kuali.coeus.common.budget.framework.rate.BudgetLaRate;
import org.kuali.coeus.common.budget.framework.rate.BudgetRate;

/**
 * Holds all the info required for the breakup interval for which calculation 
 * has to be performed.
 *
 */
public class BreakUpInterval{
    private Boundary boundary;
    private ScaleTwoDecimal underRecovery;
    private QueryList<BudgetRate> breakupIntervalRates;
    private QueryList<BudgetLaRate> breakUpIntervalLaRates; 
    private QueryList<RateAndCost> breakupCalculatedAmounts;

    private ScaleTwoDecimal applicableAmt = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal applicableAmtCostSharing = ScaleTwoDecimal.ZERO;
    private BudgetRate uRRatesBean;


    private Long budgetId;
    private Integer budgetPeriod;
    private Integer lineItemNumber;
    private Integer rateNumber;
    private QueryList<BudgetRateAndBase> rateBaseList ;
   

/**return the vector which contains Budget Rate and Base data
    *
    */
    public QueryList getRateBase() {
        return rateBaseList;
    }
    
    /** Getter for property boundary.
     * @return Value of property boundary.
     */
    public Boundary getBoundary() {
        return boundary;
    }
    
    /** Setter for property boundary.
     * @param boundary New value of property boundary.
     */
    public void setBoundary(Boundary boundary) {
        this.boundary = boundary;
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
    
    /** Getter for property cvPropRates.
     * @return Value of property cvPropRates.
     */
    public QueryList<BudgetRate> getBudgetProposalRates() {
        return breakupIntervalRates;
    }
    
    /** Setter for property cvPropRates.
     * @param budgetRates New value of property cvPropRates.
     */
    public void setBudgetProposalRates(QueryList<BudgetRate> budgetRates) {
        this.breakupIntervalRates = budgetRates;
    }
    
    /** Getter for property cvPropLARates.
     * @return Value of property cvPropLARates.
     */
    public QueryList<BudgetLaRate> getBudgetProposalLaRates() {
        return breakUpIntervalLaRates;
    }
    
    /** Setter for property cvPropLARates.
     * @param budgetLaRates New value of property cvPropLARates.
     */
    public void setBudgetProposalLaRates(QueryList<BudgetLaRate> budgetLaRates) {
        this.breakUpIntervalLaRates = budgetLaRates;
    }
    
    /** Getter for property cvAmountDetails.
     * @return Value of property cvAmountDetails.
     */
    public QueryList<RateAndCost> getRateAndCosts() {
        return breakupCalculatedAmounts;
    }
    
    /** Setter for property cvAmountDetails.
     * @param breakupCalculatedAmounts New value of property cvAmountDetails.
     */
    public void setRateAndCosts(QueryList<RateAndCost> breakupCalculatedAmounts) {
        this.breakupCalculatedAmounts = breakupCalculatedAmounts;
    }
    
    /** Getter for property applicableAmt.
     * @return Value of property applicableAmt.
     *
     */
    public ScaleTwoDecimal getApplicableAmt() {
        return applicableAmt;
    }
    
    /** Setter for property applicableAmt.
     * @param applicableAmt New value of property applicableAmt.
     *
     */
    public void setApplicableAmt(ScaleTwoDecimal applicableAmt) {
        this.applicableAmt = applicableAmt;
    }
    
    /** Getter for property applicableAmtCostSharing.
     * @return Value of property applicableAmtCostSharing.
     *
     */
    public ScaleTwoDecimal getApplicableAmtCostSharing() {
        return applicableAmtCostSharing;
    }
    
    /** Setter for property applicableAmtCostSharing.
     * @param applicableAmtCostSharing New value of property applicableAmtCostSharing.
     *
     */
    public void setApplicableAmtCostSharing(ScaleTwoDecimal applicableAmtCostSharing) {
        this.applicableAmtCostSharing = applicableAmtCostSharing;
    }
    
    /** Getter for property uRRatesBean.
     * @return Value of property uRRatesBean.
     *
     */
    public BudgetRate getURRatesBean() {
        return uRRatesBean;
    }
    
    /** Setter for property uRRatesBean.
     * @param uRRatesBean New value of property uRRatesBean.
     *
     */
    public void setURRatesBean(BudgetRate uRRatesBean) {
        this.uRRatesBean = uRRatesBean;
    }
    
    /**
     * Getter for property budgetPeriod.
     * @return Value of property budgetPeriod.
     */
    public int getBudgetPeriod() {
        return budgetPeriod;
    }
    
    /**
     * Setter for property budgetPeriod.
     * @param budgetPeriod New value of property budgetPeriod.
     */
    public void setBudgetPeriod(int budgetPeriod) {
        this.budgetPeriod = budgetPeriod;
    }
    
    /**
     * Getter for property lineItemNumber.
     * @return Value of property lineItemNumber.
     */
    public int getLineItemNumber() {
        return lineItemNumber;
    }
    
    /**
     * Setter for property lineItemNumber.
     * @param lineItemNumber New value of property lineItemNumber.
     */
    public void setLineItemNumber(int lineItemNumber) {
        this.lineItemNumber = lineItemNumber;
    }
    
    /**
     * Getter for property rateNumber.
     * @return Value of property rateNumber.
     */
    public int getRateNumber() {
        return rateNumber;
    }
    
    /**
     * Setter for property rateNumber.
     * @param rateNumber New value of property rateNumber.
     */
    public void setRateNumber(int rateNumber) {
        this.rateNumber = rateNumber;
    }


    /**
     * Gets the budget attribute. 
     * @return Returns the budget.
     */
    public Long getBudgetId() {
        return budgetId;
    }

    /**
     * Sets the budget attribute value.
     * @param budgetId to set.
     */
    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

}

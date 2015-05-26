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
package org.kuali.kra.award.budget;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.query.QueryList;
import org.kuali.coeus.common.budget.api.rate.RateClassType;
import org.kuali.coeus.common.budget.framework.query.operator.Equals;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is for AWARD_BUDGET_PERIOD_EXT table
 */
public class AwardBudgetPeriodExt extends BudgetPeriod {

    private static final long serialVersionUID = -4306012301567173292L;
    private ScaleTwoDecimal obligatedAmount;
    private ScaleTwoDecimal totalFringeAmount;
    private ScaleTwoDecimal prevTotalFringeAmount;

	private List<AwardBudgetPeriodSummaryCalculatedAmount> awardBudgetPeriodFringeAmounts;
    private List<AwardBudgetPeriodSummaryCalculatedAmount> awardBudgetPeriodFnAAmounts;
    private Map<String, ScaleTwoDecimal> fringeForCostElements;
    private boolean rateOverrideFlag;
    public AwardBudgetPeriodExt(){
        super();
        awardBudgetPeriodFringeAmounts = new ArrayList<AwardBudgetPeriodSummaryCalculatedAmount>();
        awardBudgetPeriodFnAAmounts = new ArrayList<AwardBudgetPeriodSummaryCalculatedAmount>();
        fringeForCostElements = new HashMap<String, ScaleTwoDecimal>();
    }
    /**
     * Gets the obligatedAmount attribute. 
     * @return Returns the obligatedAmount.
     */
    public ScaleTwoDecimal getObligatedAmount() {
        return obligatedAmount==null? ScaleTwoDecimal.ZERO:obligatedAmount;
    }
    /**
     * Sets the obligatedAmount attribute value.
     * @param obligatedAmount The obligatedAmount to set.
     */
    public void setObligatedAmount(ScaleTwoDecimal obligatedAmount) {
        this.obligatedAmount = obligatedAmount;
    }
    @Override
    public AwardBudgetLineItemExt getNewBudgetLineItem() {
        return new AwardBudgetLineItemExt();
    }
    /**
     * This method returns the query list after filtering all eb rates
     */
    private QueryList<AwardBudgetPeriodSummaryCalculatedAmount> filterEBRates() {
        QueryList<AwardBudgetPeriodSummaryCalculatedAmount> qlAwardBudgetPeriodSummaryCalculatedAmounts = 
                                                        new QueryList<AwardBudgetPeriodSummaryCalculatedAmount>(awardBudgetPeriodFringeAmounts);
        Equals ebClassType = new Equals("rateClassType",RateClassType.EMPLOYEE_BENEFITS.getRateClassType());
        QueryList<AwardBudgetPeriodSummaryCalculatedAmount> ebCalculatedAmounts = qlAwardBudgetPeriodSummaryCalculatedAmounts.filter(ebClassType);
        return ebCalculatedAmounts;
    }
    public Map<String,ScaleTwoDecimal> getFringeForCostElements() {
        QueryList<AwardBudgetPeriodSummaryCalculatedAmount> ebCalculatedAmounts = filterEBRates();
        for (AwardBudgetPeriodSummaryCalculatedAmount awardBudgetPeriodSummaryCalculatedAmount : ebCalculatedAmounts) {
            fringeForCostElements.put(awardBudgetPeriodSummaryCalculatedAmount.getCostElement(), awardBudgetPeriodSummaryCalculatedAmount.getCalculatedCost());
        }
        return fringeForCostElements;
    }
    public ScaleTwoDecimal getPrevTotalFringeAmount() {
		return ScaleTwoDecimal.returnZeroIfNull(prevTotalFringeAmount);
	}
	public void setPrevTotalFringeAmount(ScaleTwoDecimal prevTotalFringeAmount) {
		this.prevTotalFringeAmount=prevTotalFringeAmount;
	}

    /**
     * Gets the totalFringeAmount attribute. 
     * @return Returns the totalFringeAmount.
     */
    public ScaleTwoDecimal getTotalFringeAmount() {
        return totalFringeAmount==null? ScaleTwoDecimal.ZERO:totalFringeAmount;
    }
    /**
     * Sets the totalFringeAmount attribute value.
     * @param totalFringeAmount The totalFringeAmount to set.
     */
    public void setTotalFringeAmount(ScaleTwoDecimal totalFringeAmount) {
        this.totalFringeAmount = totalFringeAmount;
    }
    /**
     * Gets the awardBudgetPeriodFringeAmounts attribute. 
     * @return Returns the awardBudgetPeriodFringeAmounts.
     */
    public List<AwardBudgetPeriodSummaryCalculatedAmount> getAwardBudgetPeriodFringeAmounts() {
        return awardBudgetPeriodFringeAmounts;
    }
    /**
     * Sets the awardBudgetPeriodFringeAmounts attribute value.
     * @param awardBudgetPeriodFringeAmounts The awardBudgetPeriodFringeAmounts to set.
     */
    public void setAwardBudgetPeriodFringeAmounts(List<AwardBudgetPeriodSummaryCalculatedAmount> awardBudgetPeriodFringeAmounts) {
        this.awardBudgetPeriodFringeAmounts = awardBudgetPeriodFringeAmounts;
    }
    /**
     * Gets the awardBudgetPeriodFnAAmounts attribute. 
     * @return Returns the awardBudgetPeriodFnAAmounts.
     */
    public List<AwardBudgetPeriodSummaryCalculatedAmount> getAwardBudgetPeriodFnAAmounts() {
        return awardBudgetPeriodFnAAmounts;
    }
    /**
     * Sets the awardBudgetPeriodFnAAmounts attribute value.
     * @param awardBudgetPeriodFnAAmounts The awardBudgetPeriodFnAAmounts to set.
     */
    public void setAwardBudgetPeriodFnAAmounts(List<AwardBudgetPeriodSummaryCalculatedAmount> awardBudgetPeriodFnAAmounts) {
        this.awardBudgetPeriodFnAAmounts = awardBudgetPeriodFnAAmounts;
    }
    /**
     * Sets the rateOverrideFlag attribute value.
     * @param rateOverrideFlag The rateOverrideFlag to set.
     */
    public void setRateOverrideFlag(boolean rateOverrideFlag) {
        this.rateOverrideFlag = rateOverrideFlag;
    }
    /**
     * Gets the isSummaryCalcAmountsChanged attribute. 
     * @return Returns the isSummaryCalcAmountsChanged.
     */
    public boolean getRateOverrideFlag() {
        return rateOverrideFlag;
    }
    public boolean isRateOverrideFlag() {
        return rateOverrideFlag;
    }
    
    @Override
    public List<BudgetLineItem> getBudgetLineItems() {
        List<BudgetLineItem> budgetLineItems = super.getBudgetLineItems();
        calculateLineItemTotals(budgetLineItems);
        return budgetLineItems;
    }

    protected void calculateLineItemTotals(List<BudgetLineItem> budgetLineItems) {
        ScaleTwoDecimal runningTotal = ScaleTwoDecimal.ZERO;
        int i = 0;
        int budgetLineItemsLength = budgetLineItems.size();
        for (BudgetLineItem item : budgetLineItems) {
            AwardBudgetLineItemExt ext = (AwardBudgetLineItemExt) item;
            runningTotal = runningTotal.add(ext.getLineItemCost());
            runningTotal = runningTotal.add(ext.getObligatedAmount());
            ext.setObjectTotal(runningTotal);
            if (i + 1 < budgetLineItems.size()) {
                AwardBudgetLineItemExt nextExt = (AwardBudgetLineItemExt) budgetLineItems.get(i + 1);
                if (!StringUtils.equals(ext.getCostElementName(), nextExt.getCostElementName())) {
                    ext.setDisplayTotalDetail(true);
                    runningTotal = ScaleTwoDecimal.ZERO;
                } else {
                    ext.setDisplayTotalDetail(false);
                }
            } else {
                ext.setDisplayTotalDetail(true);
            }
            i++;
        }
    }

}

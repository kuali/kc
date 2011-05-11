/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.award.budget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.calculator.QueryList;
import org.kuali.kra.budget.calculator.RateClassType;
import org.kuali.kra.budget.calculator.query.Equals;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.parameters.BudgetPeriod;

/**
 * This class is for AWARD_BUDGET_PERIOD_EXT table
 */
public class AwardBudgetPeriodExt extends BudgetPeriod {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -4306012301567173292L;
    private BudgetDecimal obligatedAmount;
    private BudgetDecimal totalFringeAmount;
    private List<AwardBudgetPeriodSummaryCalculatedAmount> awardBudgetPeriodFringeAmounts;
    private List<AwardBudgetPeriodSummaryCalculatedAmount> awardBudgetPeriodFnAAmounts;
    private Map<String, BudgetDecimal> fringeForCostElements;
    public AwardBudgetPeriodExt(){
        super();
        awardBudgetPeriodFringeAmounts = new ArrayList<AwardBudgetPeriodSummaryCalculatedAmount>();
        awardBudgetPeriodFnAAmounts = new ArrayList<AwardBudgetPeriodSummaryCalculatedAmount>();
        fringeForCostElements = new HashMap<String, BudgetDecimal>();
    }
    /**
     * Gets the obligatedAmount attribute. 
     * @return Returns the obligatedAmount.
     */
    public BudgetDecimal getObligatedAmount() {
        return obligatedAmount==null?BudgetDecimal.ZERO:obligatedAmount;
    }
    /**
     * Sets the obligatedAmount attribute value.
     * @param obligatedAmount The obligatedAmount to set.
     */
    public void setObligatedAmount(BudgetDecimal obligatedAmount) {
        this.obligatedAmount = obligatedAmount;
    }
    /**
     * 
     * @see org.kuali.kra.budget.parameters.BudgetPeriod#getNewBudgetLineItem()
     */
    public AwardBudgetLineItemExt getNewBudgetLineItem() {
        return new AwardBudgetLineItemExt();
    }
    /**
     * This method returns the query list after filtering all eb rates
     * @param AwardBudgetPeriodSummaryCalculatedAmounts
     * @return
     */
    private QueryList<AwardBudgetPeriodSummaryCalculatedAmount> filterEBRates() {
        QueryList<AwardBudgetPeriodSummaryCalculatedAmount> qlAwardBudgetPeriodSummaryCalculatedAmounts = 
                                                        new QueryList<AwardBudgetPeriodSummaryCalculatedAmount>(awardBudgetPeriodFringeAmounts);
        Equals ebClassType = new Equals("rateClassType",RateClassType.EMPLOYEE_BENEFITS.getRateClassType());
        QueryList<AwardBudgetPeriodSummaryCalculatedAmount> ebCalculatedAmounts = qlAwardBudgetPeriodSummaryCalculatedAmounts.filter(ebClassType);
        return ebCalculatedAmounts;
    }
    public Map<String,BudgetDecimal> getFringeForCostElements() {
        QueryList<AwardBudgetPeriodSummaryCalculatedAmount> ebCalculatedAmounts = filterEBRates();
        for (AwardBudgetPeriodSummaryCalculatedAmount awardBudgetPeriodSummaryCalculatedAmount : ebCalculatedAmounts) {
            fringeForCostElements.put(awardBudgetPeriodSummaryCalculatedAmount.getCostElement(), awardBudgetPeriodSummaryCalculatedAmount.getCalculatedCost());
        }
        return fringeForCostElements;
    }
    public void populateSummaryCalcAmounts() {
        awardBudgetPeriodFringeAmounts.clear();
        if(awardBudgetPeriodFringeAmounts.isEmpty()){
            Budget budget = getBudget();
            Map<String,List<BudgetDecimal>> objectCodePersonnelFringe = budget.getObjectCodePersonnelFringeTotals();
            if(objectCodePersonnelFringe!=null){
                Iterator<String> objectCodes = objectCodePersonnelFringe.keySet().iterator();
                while (objectCodes.hasNext()) {
                    String costElement = (String) objectCodes.next();
                    List<BudgetDecimal> fringeTotals = objectCodePersonnelFringe.get(costElement);;
                    AwardBudgetPeriodSummaryCalculatedAmount awardBudgetPeriodSummaryCalculatedAmount = 
                        createNewAwardBudgetPeriodSummaryCalculatedAmount(costElement,RateClassType.EMPLOYEE_BENEFITS.getRateClassType(),
                                            fringeTotals.get(getBudgetPeriod()-1));
                    awardBudgetPeriodFringeAmounts.add(awardBudgetPeriodSummaryCalculatedAmount);
                }
            }
            QueryList<AwardBudgetPeriodSummaryCalculatedAmount> ebCalculatedAmounts = filterEBRates();
            setTotalFringeAmount(ebCalculatedAmounts.sumObjects("calculatedCost"));
        }
    }
    private AwardBudgetPeriodSummaryCalculatedAmount createNewAwardBudgetPeriodSummaryCalculatedAmount(String costElement,String rateClassType,
            BudgetDecimal calculatedCost) {
        String[] costElementAndPersonId = costElement.split(",");
        AwardBudgetPeriodSummaryCalculatedAmount awardBudgetPeriodSummaryCalculatedAmount = new AwardBudgetPeriodSummaryCalculatedAmount();
        awardBudgetPeriodSummaryCalculatedAmount.setBudgetPeriodId(getBudgetPeriodId());
        awardBudgetPeriodSummaryCalculatedAmount.setCalculatedCost(calculatedCost);
        awardBudgetPeriodSummaryCalculatedAmount.setCostElement(costElementAndPersonId[0]);
        awardBudgetPeriodSummaryCalculatedAmount.setRateClassType(rateClassType);
        return awardBudgetPeriodSummaryCalculatedAmount;
    }
    /**
     * Gets the totalFringeAmount attribute. 
     * @return Returns the totalFringeAmount.
     */
    public BudgetDecimal getTotalFringeAmount() {
        return totalFringeAmount;
    }
    /**
     * Sets the totalFringeAmount attribute value.
     * @param totalFringeAmount The totalFringeAmount to set.
     */
    public void setTotalFringeAmount(BudgetDecimal totalFringeAmount) {
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

}

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
package org.kuali.kra.budget.calculator;

import static org.kuali.kra.logging.BufferedLogger.debug;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.nonpersonnel.AbstractBudgetCalculatedAmount;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItemBase;
import org.kuali.kra.budget.personnel.BudgetPersonnelCalculatedAmount;
import org.kuali.kra.budget.personnel.BudgetPersonnelDetails;
import org.kuali.kra.budget.personnel.BudgetPersonnelRateAndBase;

/**
 * This class is for calculating personnel line items.
 */
public class PersonnelLineItemCalculator extends AbstractBudgetCalculator {

    private BudgetPersonnelDetails budgetPersonnelLineItem;
    private Budget budget;
    private SalaryCalculator salaryCalculator; 

    public PersonnelLineItemCalculator(Budget budget, BudgetLineItemBase bli) {
        super(budget, bli);
        this.budgetPersonnelLineItem = (BudgetPersonnelDetails)bli;
        this.budget = budget;
        salaryCalculator = new SalaryCalculator(budget,budgetPersonnelLineItem);
    }

    /**
     * @see org.kuali.kra.budget.calculator.AbstractBudgetCalculator#populateApplicableCosts(Boundary)
     */
    @Override
    public void populateApplicableCosts(Boundary boundary) {
        salaryCalculator.setInflationRates(getInflationRates());
        salaryCalculator.calculate(boundary);
    }

    private boolean isDocumentOhRateSameAsFormOhRate() {
        if(budget.getOhRateClassCode()!= null && getBudgetFormFromGlobalVariables()!= null && StringUtils.equalsIgnoreCase(budget.getOhRateClassCode(), getBudgetFormFromGlobalVariables().getOhRateClassCodePrevValue())){
            return true;
        }
        
        return false;
    }
    
    private Map<String, Boolean> saveApplyRateFlagsForReset() { 
        Map<String, Boolean> applyRateFlags = new HashMap<String, Boolean>();
        if(budgetPersonnelLineItem != null && CollectionUtils.isNotEmpty(budgetPersonnelLineItem.getBudgetPersonnelCalculatedAmounts())) {
            for(BudgetPersonnelCalculatedAmount budgetPersonnelCalculatedAmount : budgetPersonnelLineItem.getBudgetPersonnelCalculatedAmounts()) {
                applyRateFlags.put(budgetPersonnelCalculatedAmount.getRateClassCode() + budgetPersonnelCalculatedAmount.getRateTypeCode(), budgetPersonnelCalculatedAmount.getApplyRateFlag());
            }
        }
        
        return applyRateFlags;
    }
    
    @Override
    public void populateCalculatedAmountLineItems() {
       if (CollectionUtils.isEmpty(budgetPersonnelLineItem.getBudgetPersonnelCalculatedAmounts())) { 
            budgetPersonnelLineItem.refreshReferenceObject("budgetPersonnelCalculatedAmounts");
       }
       Long versionNumber = -1L;
       Map<String, Boolean> applyRateFlags = null;
       
       if (!isDocumentOhRateSameAsFormOhRate() || getBudgetRateService().performSyncFlag(budget.getBudgetDocument())){
           if (budgetPersonnelLineItem.getBudgetPersonnelCalculatedAmounts().size() > 0) {
               versionNumber = budgetPersonnelLineItem.getBudgetPersonnelCalculatedAmounts().get(0).getVersionNumber();
           }
           
           //Save applyRateFlag to set it back on the new Calculated Amounts
           applyRateFlags = saveApplyRateFlagsForReset();
           
           budgetPersonnelLineItem.setBudgetPersonnelCalculatedAmounts(new ArrayList<BudgetPersonnelCalculatedAmount>());
           setCalculatedAmounts(budget,budgetPersonnelLineItem);
           
           for (BudgetPersonnelCalculatedAmount budgetPersonnelCalculatedAmount : budgetPersonnelLineItem.getBudgetPersonnelCalculatedAmounts()) {
               if (versionNumber != null && versionNumber.longValue() > -1) {
                   budgetPersonnelCalculatedAmount.setVersionNumber(versionNumber);
               }
           }
       }

       if (budgetPersonnelLineItem.getBudgetPersonnelCalculatedAmounts().size() <= 0) {
           setCalculatedAmounts(budget,budgetPersonnelLineItem);
       }

       for (BudgetPersonnelCalculatedAmount budgetPersonnelCalculatedAmount : budgetPersonnelLineItem.getBudgetPersonnelCalculatedAmounts()) {
           budgetPersonnelCalculatedAmount.refreshReferenceObject("rateClass");
//           budgetPersonnelCalculatedAmount.refreshReferenceObject("rateType");
           
           if(applyRateFlags != null && applyRateFlags.get(budgetPersonnelCalculatedAmount.getRateClassCode()+ budgetPersonnelCalculatedAmount.getRateTypeCode()) != null) {
               budgetPersonnelCalculatedAmount.setApplyRateFlag(applyRateFlags.get(budgetPersonnelCalculatedAmount.getRateClassCode()+budgetPersonnelCalculatedAmount.getRateTypeCode()));
           }
           debug(budgetPersonnelCalculatedAmount);
           debug(budgetPersonnelCalculatedAmount.getRateClass());
       }        
    }

    @Override
    protected void updateBudgetLineItemCalculatedAmounts() {
        Boundary liBoundary = new Boundary(budgetPersonnelLineItem.getStartDate(),budgetPersonnelLineItem.getEndDate());
        salaryCalculator.calculate();
        budgetPersonnelLineItem.setLineItemCost(budgetPersonnelLineItem.getSalaryRequested());
        super.updateBudgetLineItemCalculatedAmounts();
    }
    
    @Override
    protected void addCalculatedAmount(AbstractBudgetCalculatedAmount budgetCalculatedAmount) {
        BudgetPersonnelCalculatedAmount budgetPersonnelCalculatedAmt = (BudgetPersonnelCalculatedAmount)budgetCalculatedAmount;
        budgetPersonnelCalculatedAmt.setPersonNumber(budgetPersonnelLineItem.getPersonNumber());
        budgetPersonnelLineItem.getBudgetPersonnelCalculatedAmounts().add(budgetPersonnelCalculatedAmt);
    }

    @Override
    protected AbstractBudgetCalculatedAmount getNewCalculatedAmountInstance() {
        return budgetPersonnelLineItem.getNewBudgetPersonnelCalculatedAmount();
    }

    @Override
    protected void populateBudgetRateBaseList() {
        List<BudgetPersonnelRateAndBase> budgetRateAndBaseList = budgetPersonnelLineItem.getBudgetPersonnelRateAndBaseList();
        List<BreakUpInterval> breakupIntervals = getBreakupIntervals();
        if(!budgetRateAndBaseList.isEmpty()){
            budgetRateAndBaseList.clear();
        }        
        Integer rateNumber = 0;
        for (BreakUpInterval breakUpInterval : breakupIntervals) {
            List<RateAndCost> vecAmountBean = breakUpInterval.getRateAndCosts();
            for (RateAndCost rateAndCost : vecAmountBean) {
                BudgetPersonnelRateAndBase budgetRateBase = new BudgetPersonnelRateAndBase();
                BudgetDecimal appliedRate = rateAndCost.getAppliedRate();
                budgetRateBase.setAppliedRate(BudgetDecimal.returnZeroIfNull(appliedRate));
                BudgetDecimal calculatedCost = rateAndCost.getCalculatedCost();
                BudgetDecimal calculatedCostSharing = rateAndCost.getCalculatedCostSharing();
                
                budgetRateBase.setSalaryRequested(rateAndCost.getBaseAmount());
                budgetRateBase.setBaseCostSharing(rateAndCost.getBaseCostSharingAmount());
                
                budgetRateBase.setBudgetPeriodId(budgetPersonnelLineItem.getBudgetPeriodId());
                budgetRateBase.setBudgetPeriod(budgetPersonnelLineItem.getBudgetPeriod());
                budgetRateBase.setCalculatedCost(calculatedCost);
                budgetRateBase.setCalculatedCostSharing(calculatedCostSharing);
                
                java.util.Date endDate = breakUpInterval.getBoundary().getEndDate();
                budgetRateBase.setEndDate(new java.sql.Date(endDate.getTime()));
                
                budgetRateBase.setLineItemNumber(budgetPersonnelLineItem.getLineItemNumber());
                budgetRateBase.setOnOffCampusFlag(budgetPersonnelLineItem.getOnOffCampusFlag());
                budgetRateBase.setBudgetId(budgetPersonnelLineItem.getBudgetId());
                budgetRateBase.setPersonNumber(budgetPersonnelLineItem.getPersonNumber());
                budgetRateBase.setPersonId(budgetPersonnelLineItem.getPersonId());
                
                budgetRateBase.setRateClassCode(rateAndCost.getRateClassCode());
                budgetRateBase.setRateNumber(++rateNumber);
                budgetRateBase.setRateTypeCode(rateAndCost.getRateTypeCode());
                java.util.Date startDate = breakUpInterval.getBoundary().getStartDate();
                budgetRateBase.setStartDate(new java.sql.Date(startDate.getTime()));
                budgetRateAndBaseList.add(budgetRateBase);
            }   
        }
    }

}

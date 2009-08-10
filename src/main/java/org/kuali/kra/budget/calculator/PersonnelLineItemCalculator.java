/*
 * Copyright 2006-2009 The Kuali Foundation
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
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItemBase;
import org.kuali.kra.budget.personnel.BudgetPersonnelCalculatedAmount;
import org.kuali.kra.budget.personnel.BudgetPersonnelDetails;
import org.kuali.kra.budget.personnel.BudgetPersonnelRateAndBase;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.rice.kns.util.GlobalVariables;

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
        if(budget.getOhRateClassCode()!= null && ((BudgetForm)GlobalVariables.getKualiForm())!= null && StringUtils.equalsIgnoreCase(budget.getOhRateClassCode(), ((BudgetForm)GlobalVariables.getKualiForm()).getOhRateClassCodePrevValue())){
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
       
       if (!isDocumentOhRateSameAsFormOhRate()){
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
           budgetPersonnelCalculatedAmount.refreshReferenceObject("rateType");
           
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
    protected void addBudgetLineItemCalculatedAmount(String rateClassCode, String rateTypeCode,
            String rateClassType) {
        BudgetPersonnelCalculatedAmount budgetPersonnelCalculatedAmt = new BudgetPersonnelCalculatedAmount();
//            budgetPersonnelCalculatedAmt.setProposalNumber(budgetPersonnelLineItem.getProposalNumber());
//            budgetPersonnelCalculatedAmt.setBudgetVersionNumber(budgetPersonnelLineItem.getBudgetVersionNumber());
            budgetPersonnelCalculatedAmt.setBudgetId(budgetPersonnelLineItem.getBudgetId());
            budgetPersonnelCalculatedAmt.setBudgetPeriodId(budgetPersonnelLineItem.getBudgetPeriodId());
            budgetPersonnelCalculatedAmt.setBudgetPeriod(budgetPersonnelLineItem.getBudgetPeriod());
            budgetPersonnelCalculatedAmt.setLineItemNumber(budgetPersonnelLineItem.getLineItemNumber());
            budgetPersonnelCalculatedAmt.setPersonNumber(budgetPersonnelLineItem.getPersonNumber());
            budgetPersonnelCalculatedAmt.setRateClassType(rateClassType);
            budgetPersonnelCalculatedAmt.setRateClassCode(rateClassCode);
            budgetPersonnelCalculatedAmt.setRateTypeCode(rateTypeCode);
            // budgetLineItemCalculatedAmt.setRateClassDescription(validCeRateType.getRateClass().getDescription());
            // budgetLineItemCalculatedAmt.setRateTypeDescription(validCERateTypesBean.getRateTypeDescription());
            budgetPersonnelCalculatedAmt.setApplyRateFlag(true);
            budgetPersonnelCalculatedAmt.refreshReferenceObject("rateType");
            budgetPersonnelCalculatedAmt.refreshReferenceObject("rateClass");
            budgetPersonnelLineItem.getBudgetPersonnelCalculatedAmounts().add(budgetPersonnelCalculatedAmt);
    }

    @Override
    protected void populateBudgetRateBaseList() {
        List<BudgetPersonnelRateAndBase> budgetRateAndBaseList = budgetPersonnelLineItem.getBudgetPersonnelRateAndBaseList();
        List<BreakUpInterval> breakupIntervals = getBreakupIntervals();
        Long prevVersionNumber = null;
        if(!budgetRateAndBaseList.isEmpty()){
            prevVersionNumber = budgetRateAndBaseList.get(0).getVersionNumber();
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
                
                budgetRateBase.setSalaryRequested(breakUpInterval.getApplicableAmt());
                budgetRateBase.setBaseCostSharing(breakUpInterval.getApplicableAmtCostSharing());
                
                budgetRateBase.setBudgetPeriodId(budgetPersonnelLineItem.getBudgetPeriodId());
                budgetRateBase.setBudgetPeriod(budgetPersonnelLineItem.getBudgetPeriod());
                budgetRateBase.setCalculatedCost(calculatedCost);
                budgetRateBase.setCalculatedCostSharing(calculatedCostSharing);
                
                java.util.Date endDate = breakUpInterval.getBoundary().getEndDate();
                budgetRateBase.setEndDate(new java.sql.Date(endDate.getTime()));
                
                budgetRateBase.setLineItemNumber(budgetPersonnelLineItem.getLineItemNumber());
                budgetRateBase.setOnOffCampusFlag(budgetPersonnelLineItem.getOnOffCampusFlag());
//                budgetRateBase.setProposalNumber(budgetPersonnelLineItem.getProposalNumber());
//                budgetRateBase.setBudgetVersionNumber(budgetPersonnelLineItem.getBudgetVersionNumber());
                budgetRateBase.setBudgetId(budgetPersonnelLineItem.getBudgetId());
                budgetRateBase.setPersonNumber(budgetPersonnelLineItem.getPersonNumber());
                budgetRateBase.setPersonId(budgetPersonnelLineItem.getPersonId());
                
                budgetRateBase.setRateClassCode(rateAndCost.getRateClassCode());
                budgetRateBase.setRateNumber(++rateNumber);
                budgetRateBase.setRateTypeCode(rateAndCost.getRateTypeCode());
                java.util.Date startDate = breakUpInterval.getBoundary().getStartDate();
                budgetRateBase.setStartDate(new java.sql.Date(startDate.getTime()));
//                if(prevVersionNumber!=null) budgetRateBase.setVersionNumber(prevVersionNumber);
                budgetRateAndBaseList.add(budgetRateBase);
            }   
        }
    }

}

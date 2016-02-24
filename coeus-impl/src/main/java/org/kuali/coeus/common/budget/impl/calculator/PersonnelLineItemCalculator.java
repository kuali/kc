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

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.budget.framework.rate.BudgetLaRate;
import org.kuali.coeus.common.budget.framework.rate.BudgetRate;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.nonpersonnel.AbstractBudgetCalculatedAmount;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItemBase;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelCalculatedAmount;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelRateAndBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is for calculating personnel line items.
 */
public class PersonnelLineItemCalculator extends AbstractBudgetCalculator {

    private static final Log LOG = LogFactory.getLog(PersonnelLineItemCalculator.class);

    private BudgetPersonnelDetails budgetPersonnelLineItem;
    private Budget budget;
    private SalaryCalculator salaryCalculator; 

    public PersonnelLineItemCalculator(Budget budget, BudgetLineItemBase bli) {
        super(budget, bli);
        this.budgetPersonnelLineItem = (BudgetPersonnelDetails)bli;
        this.budget = budget;
        salaryCalculator = new SalaryCalculator(budget,budgetPersonnelLineItem);
    }

    @Override
    public void populateApplicableCosts(Boundary boundary) {
        salaryCalculator.setInflationRates(getInflationRates());
        salaryCalculator.calculate(boundary);
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
       Long versionNumber = -1L;
       Map<String, Boolean> applyRateFlags = null;
       
       if (isPerformSync()){
           if (budgetPersonnelLineItem.getBudgetPersonnelCalculatedAmounts().size() > 0) {
               versionNumber = budgetPersonnelLineItem.getBudgetPersonnelCalculatedAmounts().get(0).getVersionNumber();
           }
           
           //Save applyRateFlag to set it back on the new Calculated Amounts
           applyRateFlags = saveApplyRateFlagsForReset();
           
           setCalculatedAmounts(budgetPersonnelLineItem);
           
           for (BudgetPersonnelCalculatedAmount budgetPersonnelCalculatedAmount : budgetPersonnelLineItem.getBudgetPersonnelCalculatedAmounts()) {
               if (versionNumber != null && versionNumber.longValue() > -1) {
                   budgetPersonnelCalculatedAmount.setVersionNumber(versionNumber);
               }
           }
       }

       if (budgetPersonnelLineItem.getBudgetPersonnelCalculatedAmounts().size() <= 0) {
           setCalculatedAmounts(budgetPersonnelLineItem);
       }

       for (BudgetPersonnelCalculatedAmount budgetPersonnelCalculatedAmount : budgetPersonnelLineItem.getBudgetPersonnelCalculatedAmounts()) {
           if(applyRateFlags != null && applyRateFlags.get(budgetPersonnelCalculatedAmount.getRateClassCode()+ budgetPersonnelCalculatedAmount.getRateTypeCode()) != null) {
               budgetPersonnelCalculatedAmount.setApplyRateFlag(applyRateFlags.get(budgetPersonnelCalculatedAmount.getRateClassCode()+budgetPersonnelCalculatedAmount.getRateTypeCode()));
           }
       }        
    }

    protected boolean isPerformSync() {
        return getBudgetRateService().performSyncFlag(budget);
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
        budgetPersonnelCalculatedAmt.setBudgetPersonnelLineItem(budgetPersonnelLineItem);
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
                ScaleTwoDecimal appliedRate = rateAndCost.getAppliedRate();
                budgetRateBase.setAppliedRate(ScaleTwoDecimal.returnZeroIfNull(appliedRate));
                ScaleTwoDecimal calculatedCost = rateAndCost.getCalculatedCost();
                ScaleTwoDecimal calculatedCostSharing = rateAndCost.getCalculatedCostSharing();
                
                budgetRateBase.setSalaryRequested(rateAndCost.getBaseAmount());
                budgetRateBase.setBaseCostSharing(rateAndCost.getBaseCostSharingAmount());
                
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
                budgetRateBase.setBudgetPersonnelLineItem(budgetPersonnelLineItem);
                budgetRateBase.setBudgetPeriodId(budgetPersonnelLineItem.getBudgetPeriodId());
                budgetRateAndBaseList.add(budgetRateBase);
            }   
        }
    }

    @Override
    protected List<BudgetRate> getBudgetRates() {
        if (StringUtils.isNotEmpty(budgetPersonnelLineItem.getBudgetLineItem().getHierarchyProposalNumber())) {
            return budgetPersonnelLineItem.getBudgetLineItem().getHierarchyProposal().getHierarchySummaryBudget().getBudgetRates();
        }
        return budget.getBudgetRates();
    }

    @Override
    protected List<BudgetLaRate> getBudgetLaRates() {
        if (StringUtils.isNotEmpty(budgetPersonnelLineItem.getBudgetLineItem().getHierarchyProposalNumber())) {
            return budgetPersonnelLineItem.getBudgetLineItem().getHierarchyProposal().getHierarchySummaryBudget().getBudgetLaRates();
        }
        return budget.getBudgetLaRates();
    }
}

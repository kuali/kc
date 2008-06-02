/*
 * Copyright 2008 The Kuali Foundation.
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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetLineItemBase;
import org.kuali.kra.budget.bo.BudgetPersonnelCalculatedAmount;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.bo.BudgetPersonnelRateAndBase;
import org.kuali.kra.budget.bo.BudgetRateAndBase;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.web.struts.form.BudgetForm;

/**
 * This class is for calculating personnel line items.
 */
public class PersonnelLineItemCalculator extends AbstractBudgetCalculator {

    private BudgetPersonnelDetails budgetPersonnelLineItem;
    private BudgetDocument budgetDocument;
    private SalaryCalculator salaryCalculator; 

    public PersonnelLineItemCalculator(BudgetDocument bd, BudgetLineItemBase bli) {
        super(bd, bli);
        this.budgetPersonnelLineItem = (BudgetPersonnelDetails)bli;
        this.budgetDocument = bd;
        salaryCalculator = new SalaryCalculator(budgetDocument,budgetPersonnelLineItem);
    }

    /**
     * @see org.kuali.kra.budget.calculator.AbstractBudgetCalculator#populateApplicableCosts(Boundary)
     */
    @Override
    public void populateApplicableCosts(Boundary boundary) {
        salaryCalculator.setInflationRates(getInflationRates());
        salaryCalculator.calculate(boundary);
    }
    @Override
    public void populateCalculatedAmountLineItems() {
        if (budgetPersonnelLineItem.getBudgetPersonnelCalculatedAmounts().size() <= 0) {
            budgetPersonnelLineItem.refreshReferenceObject("budgetPersonnelCalculatedAmounts");
        }
        if (budgetPersonnelLineItem.getBudgetPersonnelCalculatedAmounts().size() <= 0) {
            setCalculatedAmounts(budgetDocument,budgetPersonnelLineItem);
            List<BudgetPersonnelCalculatedAmount> budgetPerosnnelCalcAmts = budgetPersonnelLineItem.getBudgetPersonnelCalculatedAmounts();
            for (BudgetPersonnelCalculatedAmount budgetPersonnelCalculatedAmount : budgetPerosnnelCalcAmts) {
                budgetPersonnelCalculatedAmount.setPersonNumber(budgetPersonnelLineItem.getPersonNumber());
            }
        }
        
        if(budgetDocument.getOhRateClassCode()!=null && ((BudgetForm)GlobalVariables.getKualiForm())!=null && !StringUtils.equalsIgnoreCase(budgetDocument.getOhRateClassCode(),((BudgetForm)GlobalVariables.getKualiForm()).getOhRateClassCodePrevValue())){
            Long versionNumber = budgetPersonnelLineItem.getBudgetPersonnelCalculatedAmounts().get(0).getVersionNumber();
            budgetPersonnelLineItem.setBudgetPersonnelCalculatedAmounts(new ArrayList<BudgetPersonnelCalculatedAmount>());
            
            setCalculatedAmounts(budgetDocument,budgetPersonnelLineItem);
            List<BudgetPersonnelCalculatedAmount> budgetPerosnnelCalcAmts = budgetPersonnelLineItem.getBudgetPersonnelCalculatedAmounts();
            for (BudgetPersonnelCalculatedAmount budgetPersonnelCalculatedAmount : budgetPerosnnelCalcAmts) {
                budgetPersonnelCalculatedAmount.setPersonNumber(budgetPersonnelLineItem.getPersonNumber());
            }
            for(BudgetPersonnelCalculatedAmount budgetPersonnelCalculatedAmount : budgetPerosnnelCalcAmts){
                budgetPersonnelCalculatedAmount.setVersionNumber(versionNumber);
            }
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
            budgetPersonnelCalculatedAmt.setProposalNumber(budgetPersonnelLineItem.getProposalNumber());
            budgetPersonnelCalculatedAmt.setBudgetVersionNumber(budgetPersonnelLineItem.getBudgetVersionNumber());
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
        budgetRateAndBaseList.clear();
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
                budgetRateBase.setProposalNumber(budgetPersonnelLineItem.getProposalNumber());
                budgetRateBase.setPersonNumber(budgetPersonnelLineItem.getPersonNumber());
                budgetRateBase.setPersonId(budgetPersonnelLineItem.getPersonId());
                
                budgetRateBase.setRateClassCode(rateAndCost.getRateClassCode());
                budgetRateBase.setRateNumber(++rateNumber);
                budgetRateBase.setRateTypeCode(rateAndCost.getRateTypeCode());
                java.util.Date startDate = breakUpInterval.getBoundary().getStartDate();
                budgetRateBase.setStartDate(new java.sql.Date(startDate.getTime()));
                budgetRateBase.setBudgetVersionNumber(budgetPersonnelLineItem.getBudgetVersionNumber());
                budgetRateAndBaseList.add(budgetRateBase);
            }   
        }
    }

}

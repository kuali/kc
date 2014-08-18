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

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.calculator.BudgetCalculationService;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.nonpersonnel.AbstractBudgetCalculatedAmount;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItemCalculatedAmount;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetRateAndBase;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.rice.core.api.datetime.DateTimeService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * This class for calculating non personnel line item
 */
public class LineItemCalculator extends AbstractBudgetCalculator {
    private Budget budget;
    private BudgetLineItem bli;
    private DateTimeService dateTimeService;
    private BudgetCalculationService budgetCalculationService;
    public LineItemCalculator(Budget budget,BudgetLineItem bli){
        super(budget,bli);
        this.bli = bli;
        this.budget = budget;
        dateTimeService = getDateTimeService();
        budgetCalculationService = KcServiceLocator.getService(BudgetCalculationService.class);
    }

    private Map<String, Boolean> saveApplyRateFlagsForReset() {
        Map<String, Boolean> applyRateFlags = new HashMap<String, Boolean>();
        if(bli != null && CollectionUtils.isNotEmpty(bli.getBudgetLineItemCalculatedAmounts())) {
            for(BudgetLineItemCalculatedAmount budgetLineItemCalculatedAmount : bli.getBudgetLineItemCalculatedAmounts()) {
                applyRateFlags.put(budgetLineItemCalculatedAmount.getRateClassCode()+budgetLineItemCalculatedAmount.getRateTypeCode(), budgetLineItemCalculatedAmount.getApplyRateFlag());
            }
        }
        
        return applyRateFlags;
    }

    public void populateCalculatedAmountLineItems() {
        if (bli.getBudgetLineItemCalculatedAmounts().size() <= 0) {
            bli.refreshReferenceObject("budgetLineItemCalculatedAmounts");
        }
        if (bli.getBudgetLineItemCalculatedAmounts().size() <= 0) {
            setCalculatedAmounts(budget,bli);
        }

        if(getBudgetRateService().performSyncFlag(budget)){
            Long versionNumber = null;
            if (CollectionUtils.isNotEmpty(bli.getBudgetLineItemCalculatedAmounts())) {
                versionNumber = bli.getBudgetLineItemCalculatedAmounts().get(0).getVersionNumber();
            }
            //Save applyRateFlag to set it back on the new Calculated Amounts
            Map<String, Boolean> applyRateFlags = saveApplyRateFlagsForReset();

            bli.setBudgetLineItemCalculatedAmounts(new ArrayList<BudgetLineItemCalculatedAmount>());
            
            setCalculatedAmounts(budget,bli);
            
            for(BudgetLineItemCalculatedAmount budgetLineItemCalculatedAmount : bli.getBudgetLineItemCalculatedAmounts()){
                if (versionNumber != null) {
                        budgetLineItemCalculatedAmount.setVersionNumber(versionNumber);
                }
                
                if(applyRateFlags != null && applyRateFlags.get(budgetLineItemCalculatedAmount.getRateClassCode() + budgetLineItemCalculatedAmount.getRateTypeCode()) != null) {
                    budgetLineItemCalculatedAmount.setApplyRateFlag(applyRateFlags.get(budgetLineItemCalculatedAmount.getRateClassCode() + budgetLineItemCalculatedAmount.getRateTypeCode()));
                }
            }
        }
         
    }    

    /*
     * unitcost = (totalcost/totalnumofdays)*actualnumofdays
     * 
     */
    @Override
    public void populateApplicableCosts(Boundary boundary) {
        int totalNumOfDays = dateTimeService.dateDiff(this.bli.getStartDate(), this.bli.getEndDate(), true);
        int boundaryNumOfDays = boundary.getNumberOfDays();
        
        List<BudgetPersonnelDetails> personnelDetailsList = bli.getBudgetPersonnelDetailsList();
        ScaleTwoDecimal salaryRequested = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal costSharingRequested = ScaleTwoDecimal.ZERO;
        if(!personnelDetailsList.isEmpty()){
            for (BudgetPersonnelDetails budgetPersonnelDetails : personnelDetailsList) {
                budgetCalculationService.calculateBudgetLineItem(budget, budgetPersonnelDetails);
                salaryRequested = salaryRequested.add(budgetPersonnelDetails.getSalaryRequested());
                costSharingRequested = costSharingRequested.add(budgetPersonnelDetails.getCostSharingAmount());
            }
            bli.setLineItemCost(salaryRequested);
            bli.setCostSharingAmount(costSharingRequested);
        }else{
            ScaleTwoDecimal lineItemCost = bli.getLineItemCost();
            ScaleTwoDecimal lineItemCostSharing = bli.getCostSharingAmount();
            boundary.setApplicableCost(lineItemCost==null? ScaleTwoDecimal.ZERO:new ScaleTwoDecimal(lineItemCost.bigDecimalValue().multiply(((new BigDecimal(boundaryNumOfDays)))).divide(new BigDecimal(totalNumOfDays),2, RoundingMode.HALF_UP)));
            boundary.setApplicableCostSharing(lineItemCostSharing==null? ScaleTwoDecimal.ZERO:new ScaleTwoDecimal(lineItemCostSharing.bigDecimalValue().multiply(((new BigDecimal(boundaryNumOfDays)))).divide(new BigDecimal(totalNumOfDays),2, RoundingMode.HALF_UP)));
        }
    }
    @Override
    protected void addCalculatedAmount(AbstractBudgetCalculatedAmount budgetCalculatedAmount) {
        bli.getBudgetLineItemCalculatedAmounts().add((BudgetLineItemCalculatedAmount)budgetCalculatedAmount);
    }
    @Override
    protected void populateBudgetRateBaseList() {
        List<BudgetRateAndBase> budgetRateAndBaseList = bli.getBudgetRateAndBaseList();
        List<BreakUpInterval> breakupIntervals = getBreakupIntervals();
        if(!budgetRateAndBaseList.isEmpty()){
            budgetRateAndBaseList.clear();
        }
        
        Integer rateNumber = 0;
        for (BreakUpInterval breakUpInterval : breakupIntervals) {
            List<RateAndCost> vecAmountBean = breakUpInterval.getRateAndCosts();
            for (RateAndCost rateAndCost : vecAmountBean) {
                BudgetRateAndBase budgetRateBase = new BudgetRateAndBase();
                ScaleTwoDecimal appliedRate = rateAndCost.getAppliedRate();
                budgetRateBase.setAppliedRate(ScaleTwoDecimal.returnZeroIfNull(appliedRate));
                ScaleTwoDecimal calculatedCost = rateAndCost.getCalculatedCost();
                ScaleTwoDecimal calculatedCostSharing = rateAndCost.getCalculatedCostSharing();
//                budgetRateBase.setBaseCost(breakUpInterval.getApplicableAmt());
//                budgetRateBase.setBaseCostSharing(breakUpInterval.getApplicableAmtCostSharing());
                budgetRateBase.setBaseCostSharing(rateAndCost.getBaseCostSharingAmount());
                budgetRateBase.setBaseCost(rateAndCost.getBaseAmount());
                
                budgetRateBase.setBudgetPeriod(bli.getBudgetPeriod());
                budgetRateBase.setCalculatedCost(calculatedCost);
                budgetRateBase.setCalculatedCostSharing(calculatedCostSharing);
                
                java.util.Date endDate = breakUpInterval.getBoundary().getEndDate();
                budgetRateBase.setEndDate(new java.sql.Date(endDate.getTime()));
                
                budgetRateBase.setLineItemNumber(bli.getLineItemNumber());
                budgetRateBase.setOnOffCampusFlag(bli.getOnOffCampusFlag());
                budgetRateBase.setBudgetId(bli.getBudgetId());
                budgetRateBase.setRateClassCode(rateAndCost.getRateClassCode());
                budgetRateBase.setRateNumber(++rateNumber);
                budgetRateBase.setRateTypeCode(rateAndCost.getRateTypeCode());
                java.util.Date startDate = breakUpInterval.getBoundary().getStartDate();
                budgetRateBase.setStartDate(new java.sql.Date(startDate.getTime()));
                budgetRateAndBaseList.add(budgetRateBase);
            }   
        }
    }

    @Override
    protected AbstractBudgetCalculatedAmount getNewCalculatedAmountInstance() {
        return bli.getNewBudgetLineItemCalculatedAmount();
    }
}

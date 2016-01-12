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

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.calculator.BudgetCalculationService;
import org.kuali.coeus.common.budget.framework.rate.BudgetLaRate;
import org.kuali.coeus.common.budget.framework.rate.BudgetRate;
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
            setCalculatedAmounts(bli);
        }

        if(performSync()){
            Long versionNumber = null;
            if (CollectionUtils.isNotEmpty(bli.getBudgetLineItemCalculatedAmounts())) {
                versionNumber = bli.getBudgetLineItemCalculatedAmounts().get(0).getVersionNumber();
            }
            //Save applyRateFlag to set it back on the new Calculated Amounts
            Map<String, Boolean> applyRateFlags = saveApplyRateFlagsForReset();
            
            setCalculatedAmounts(bli);
            
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

    protected boolean performSync() {
        return getBudgetRateService().performSyncFlag(budget);
    }

    protected BudgetCalculationService getBudgetCalculationService() {
        return budgetCalculationService;
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
                getBudgetCalculationService().calculateBudgetLineItem(budget, budgetPersonnelDetails);
                salaryRequested = salaryRequested.add(budgetPersonnelDetails.getSalaryRequested());
                costSharingRequested = costSharingRequested.add(budgetPersonnelDetails.getCostSharingAmount());
            }
            bli.setLineItemCost(salaryRequested);
            bli.setCostSharingAmount(costSharingRequested);
        }else{
            ScaleTwoDecimal lineItemCost = bli.getLineItemCost();
            ScaleTwoDecimal lineItemCostSharing = bli.getCostSharingAmount();
            boundary.setApplicableCost(lineItemCost == null ? ScaleTwoDecimal.ZERO : new ScaleTwoDecimal(lineItemCost.bigDecimalValue().multiply(((new BigDecimal(boundaryNumOfDays)))).divide(new BigDecimal(totalNumOfDays), 2, RoundingMode.HALF_UP)));
            boundary.setApplicableCostSharing(lineItemCostSharing == null ? ScaleTwoDecimal.ZERO : new ScaleTwoDecimal(lineItemCostSharing.bigDecimalValue().multiply(((new BigDecimal(boundaryNumOfDays)))).divide(new BigDecimal(totalNumOfDays), 2, RoundingMode.HALF_UP)));
        }
    }
    @Override
    protected void addCalculatedAmount(AbstractBudgetCalculatedAmount budgetCalculatedAmount) {
    	BudgetLineItemCalculatedAmount budgetLineItemCalculatedAmount = (BudgetLineItemCalculatedAmount)budgetCalculatedAmount;
    	budgetLineItemCalculatedAmount.setBudgetLineItem(bli);
        bli.getBudgetLineItemCalculatedAmounts().add(budgetLineItemCalculatedAmount);
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
                budgetRateBase.setBudgetPeriodId(bli.getBudgetPeriodId());
                budgetRateBase.setBudgetLineItem(bli);
                budgetRateAndBaseList.add(budgetRateBase);
            }   
        }
    }

    @Override
    protected AbstractBudgetCalculatedAmount getNewCalculatedAmountInstance() {
        return bli.getNewBudgetLineItemCalculatedAmount();
    }

    @Override
    protected List<BudgetRate> getBudgetRates() {
        if (StringUtils.isNotEmpty(bli.getHierarchyProposalNumber())) {
            return bli.getHierarchyProposal().getHierarchySummaryBudget().getBudgetRates();
        }
        return budget.getBudgetRates();
    }

    @Override
    protected List<BudgetLaRate> getBudgetLaRates() {
        if (StringUtils.isNotEmpty(bli.getHierarchyProposalNumber())) {
            return bli.getHierarchyProposal().getHierarchySummaryBudget().getBudgetLaRates();
        }
        return budget.getBudgetLaRates();
    }
}

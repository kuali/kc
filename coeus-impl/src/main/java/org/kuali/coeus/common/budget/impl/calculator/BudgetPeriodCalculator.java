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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.calculator.BudgetCalculationService;
import org.kuali.coeus.common.budget.framework.query.QueryList;
import org.kuali.coeus.common.budget.api.rate.RateClassType;
import org.kuali.coeus.common.budget.framework.query.operator.*;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.CostElement;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItemCalculatedAmount;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetRateAndBase;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelCalculatedAmount;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelRateAndBase;
import org.kuali.coeus.common.budget.framework.rate.BudgetRate;
import org.kuali.coeus.common.budget.framework.rate.ValidCeRateType;
import org.kuali.coeus.common.budget.framework.summary.BudgetSummaryService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.util.ObjectUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;

public class BudgetPeriodCalculator {
	private DataObjectService dataObjectService;
    private BudgetCalculationService budgetCalculationService;
    private List<String> errorMessages;

    public BudgetPeriodCalculator() {
        budgetCalculationService = getService(BudgetCalculationService.class);
        errorMessages = new ArrayList<String>();
    }

    /**
     * 
     * This method calculates and sync the budget period
     * 
     * @param budget
     * @param budgetPeriod
     */
    public void calculate(Budget budget, BudgetPeriod budgetPeriod) {
        List<BudgetLineItem> cvLineItemDetails = budgetPeriod.getBudgetLineItems();
        budgetPeriod.setTotalDirectCost(ScaleTwoDecimal.ZERO);
        budgetPeriod.setTotalIndirectCost(ScaleTwoDecimal.ZERO);
        budgetPeriod.setCostSharingAmount(ScaleTwoDecimal.ZERO);
        budgetPeriod.setTotalCost(ScaleTwoDecimal.ZERO);
        budgetPeriod.setUnderrecoveryAmount(ScaleTwoDecimal.ZERO);
        for (BudgetLineItem budgetLineItem : cvLineItemDetails) {
            budgetCalculationService.calculateBudgetLineItem(budget, budgetLineItem);
            budgetPeriod.setTotalDirectCost(budgetPeriod.getTotalDirectCost().add(budgetLineItem.getDirectCost()));
            budgetPeriod.setTotalIndirectCost(budgetPeriod.getTotalIndirectCost().add(budgetLineItem.getIndirectCost()));
            budgetPeriod.setTotalCost(budgetPeriod.getTotalCost().add(budgetLineItem.getDirectCost().add(budgetLineItem.getIndirectCost())));
            budgetPeriod.setUnderrecoveryAmount(budgetPeriod.getUnderrecoveryAmount().add(budgetLineItem.getUnderrecoveryAmount()));
            budgetPeriod.setCostSharingAmount(budgetPeriod.getCostSharingAmount().add(budgetLineItem.getTotalCostSharingAmount()));
        }
    }

    public void applyToLaterPeriods(Budget budget, BudgetPeriod currentBudgetPeriod, BudgetLineItem currentBudgetLineItem) {

        //put all lineitems in one bucket
        List<BudgetPeriod> budgetPeriods = budget.getBudgetPeriods();
        BudgetLineItem prevBudgetLineItem = currentBudgetLineItem;
        int periodDuration = KcServiceLocator.getService(DateTimeService.class).dateDiff(currentBudgetPeriod.getStartDate(), currentBudgetPeriod.getEndDate(), false);
        // calculate for the apply-from item in case there is any change, so it will be updated properly after apply-to
        budgetCalculationService.calculateBudgetLineItem(budget, currentBudgetLineItem);
        if(currentBudgetLineItem.getBudgetCategory() == null) {
        	getDataObjectService().wrap(currentBudgetLineItem).fetchRelationship("budgetCategory");
        }
        for (BudgetPeriod budgetPeriod : budgetPeriods) {
            if(budgetPeriod.getBudgetPeriod()<=currentBudgetPeriod.getBudgetPeriod()) continue;
            QueryList<BudgetLineItem> currentBudgetPeriodLineItems = new QueryList<BudgetLineItem>(budgetPeriod.getBudgetLineItems());
            for (BudgetLineItem budgetLineItemToBeApplied : currentBudgetPeriodLineItems) {
            	if(budgetLineItemToBeApplied.getBudgetCategory() == null) {
                	getDataObjectService().wrap(budgetLineItemToBeApplied).fetchRelationship("budgetCategory");
                }            	
                if(prevBudgetLineItem.getLineItemNumber().equals(budgetLineItemToBeApplied.getBasedOnLineItem())) {
                    budgetLineItemToBeApplied.setApplyInRateFlag(prevBudgetLineItem.getApplyInRateFlag());
                    if (prevBudgetLineItem.getApplyInRateFlag()){
                    // calculate no matter what because applyinrateflag maybe changed ??
                    
                        if (budgetLineItemToBeApplied.getBudgetCategory().getBudgetCategoryTypeCode() == KeyConstants.PERSONNEL_CATEGORY
                                && (!budgetLineItemToBeApplied.getBudgetPersonnelDetailsList().isEmpty())) {
                            errorMessages.add("This line item contains personnel budget details"
                                    + " and there is already a line item on period " + budgetPeriod + " based on this line item."
                                    + "Cannot apply the changes to later periods.");
                            return;
                        }
                        
                        ScaleTwoDecimal lineItemCost = calculateInflation(budget, prevBudgetLineItem, budgetLineItemToBeApplied
                                .getStartDate());
                        if(!budgetLineItemToBeApplied.getCostElement().equals(prevBudgetLineItem.getCostElement())){
                            budgetLineItemToBeApplied.setBudgetPeriod(budgetPeriod.getBudgetPeriod());
                            budgetLineItemToBeApplied.setBudgetCategory(prevBudgetLineItem.getBudgetCategory());
                            budgetLineItemToBeApplied.setStartDate(budgetPeriod.getStartDate());
                            budgetLineItemToBeApplied.setEndDate(budgetPeriod.getEndDate());
                            budgetLineItemToBeApplied.setCostElement(prevBudgetLineItem.getCostElement());
                            budgetLineItemToBeApplied.refreshReferenceObject("costElementBO");
                            budgetLineItemToBeApplied.setBudgetCategoryCode(budgetLineItemToBeApplied.getCostElementBO().getBudgetCategoryCode());
                        }
                        budgetLineItemToBeApplied.setLineItemCost(lineItemCost);
                    } else {
                        budgetLineItemToBeApplied.setLineItemCost(prevBudgetLineItem.getLineItemCost());
                    }
                    budgetLineItemToBeApplied.setCostSharingAmount(prevBudgetLineItem.getCostSharingAmount());
                    budgetLineItemToBeApplied.setLineItemDescription(prevBudgetLineItem.getLineItemDescription());
                    budgetLineItemToBeApplied.setQuantity(prevBudgetLineItem.getQuantity());
                    budgetLineItemToBeApplied.setUnderrecoveryAmount(prevBudgetLineItem.getUnderrecoveryAmount());
                    budgetLineItemToBeApplied.setOnOffCampusFlag(prevBudgetLineItem.getOnOffCampusFlag());

                    // apply all periods : generate calamts , then update apply rate flag
                    budgetCalculationService.populateCalculatedAmount(budget, budgetLineItemToBeApplied);
                    for (BudgetLineItemCalculatedAmount prevCalAmts : prevBudgetLineItem.getBudgetLineItemCalculatedAmounts()) {
                        for (BudgetLineItemCalculatedAmount CalAmts : budgetLineItemToBeApplied.getBudgetLineItemCalculatedAmounts()) {
                            if (prevCalAmts.getRateClassCode().equals(CalAmts.getRateClassCode()) && prevCalAmts.getRateTypeCode().equals(CalAmts.getRateTypeCode())) {
                                CalAmts.setApplyRateFlag(prevCalAmts.getApplyRateFlag());
                            }
                        }
                    }

                    budgetCalculationService.calculateBudgetLineItem(budget, budgetLineItemToBeApplied);
                    prevBudgetLineItem = budgetLineItemToBeApplied;
               } else if(StringUtils.equals(currentBudgetLineItem.getBudgetCategory().getBudgetCategoryTypeCode(), KeyConstants.PERSONNEL_CATEGORY)){
                    //Additional Check for Personnel Source Line Item
                    if(StringUtils.equals(currentBudgetLineItem.getCostElement(), budgetLineItemToBeApplied.getCostElement()) && 
                            StringUtils.equals(currentBudgetLineItem.getGroupName(), budgetLineItemToBeApplied.getGroupName())) {
                        errorMessages.add("This line item contains personnel budget details"
                                + " and there is already a line item on period " + budgetPeriod + " for this Object Code \\ Group combination. \n"
                                + "Cannot apply the changes to later periods.");
                        return;
                    }
                   
                }

            }
            // new line item check
            int gap;
            int lineDuration;
            int currentPeriodDuration;
            if (prevBudgetLineItem.getBudgetPeriod() < (budgetPeriod.getBudgetPeriod())) {
                BudgetLineItem budgetLineItem = (BudgetLineItem)ObjectUtils.deepCopy(prevBudgetLineItem);
                budgetLineItem.setBudgetLineItemId(null);
                budgetLineItem.getBudgetCalculatedAmounts().clear();
                budgetLineItem.setBudgetPeriod(budgetPeriod.getBudgetPeriod());
                budgetLineItem.setBudgetPeriodId(budgetPeriod.getBudgetPeriodId());
                budgetLineItem.setBudgetPeriodBO(budgetPeriod);
                boolean isLeapDateInPeriod = KcServiceLocator.getService(BudgetSummaryService.class).isLeapDaysInPeriod(prevBudgetLineItem.getStartDate(), prevBudgetLineItem.getEndDate()) ;
                gap= KcServiceLocator.getService(DateTimeService.class).dateDiff(currentBudgetPeriod.getStartDate(), currentBudgetLineItem.getStartDate(), false);
                boolean isLeapDayInGap = KcServiceLocator.getService(BudgetSummaryService.class).isLeapDaysInPeriod(currentBudgetPeriod.getStartDate(), currentBudgetLineItem.getStartDate());
                lineDuration= KcServiceLocator.getService(DateTimeService.class).dateDiff(budgetLineItem.getStartDate(), budgetLineItem.getEndDate(), false);
                currentPeriodDuration = KcServiceLocator.getService(DateTimeService.class).dateDiff(budgetPeriod.getStartDate(), budgetPeriod.getEndDate(), false);
                List <java.sql.Date> startEndDates = new ArrayList<java.sql.Date> ();
                if (periodDuration == lineDuration || lineDuration > currentPeriodDuration) {
                    budgetLineItem.setStartDate(budgetPeriod.getStartDate());
                    budgetLineItem.setEndDate(budgetPeriod.getEndDate());
                } else {
                    startEndDates.add(0, budgetPeriod.getStartDate());
                    startEndDates.add(1, budgetPeriod.getEndDate());
                    List <java.sql.Date> dates = KcServiceLocator.getService(BudgetSummaryService.class).getNewStartEndDates(startEndDates, gap, lineDuration, budgetLineItem.getStartDate(), isLeapDateInPeriod,isLeapDayInGap);
                    budgetLineItem.setStartDate(dates.get(0));
                    budgetLineItem.setEndDate(dates.get(1));
                }

                
                budgetLineItem.setBasedOnLineItem(prevBudgetLineItem.getLineItemNumber());
                budgetLineItem.setVersionNumber(null);
                
                if (prevBudgetLineItem.getApplyInRateFlag()){
                    ScaleTwoDecimal lineItemCost = calculateInflation(budget, prevBudgetLineItem, budgetLineItem.getStartDate());
                    budgetLineItem.setLineItemCost(lineItemCost);
                }
                
                lineDuration= KcServiceLocator.getService(DateTimeService.class).dateDiff(budgetLineItem.getStartDate(), budgetLineItem.getEndDate(), false);
                int personnelDuration = 0;

                /* add personnel line items */
                List<BudgetPersonnelDetails> budgetPersonnelDetails = budgetLineItem.getBudgetPersonnelDetailsList();
                for(BudgetPersonnelDetails budgetPersonnelDetail: budgetPersonnelDetails) {
                    isLeapDateInPeriod = KcServiceLocator.getService(BudgetSummaryService.class).isLeapDaysInPeriod(budgetPersonnelDetail.getStartDate(), budgetPersonnelDetail.getEndDate()) ;
                    budgetPersonnelDetail.setBudgetPersonnelLineItemId(null);
                    budgetPersonnelDetail.getBudgetCalculatedAmounts().clear();
                    budgetPersonnelDetail.setBudgetPeriod(budgetPeriod.getBudgetPeriod());
                    budgetPersonnelDetail.setBudgetPeriodId(budgetPeriod.getBudgetPeriodId());
                    budgetPersonnelDetail.setLineItemSequence(budget.getNextValue(Constants.BUDGET_PERSON_LINE_SEQUENCE_NUMBER));
                    
                    personnelDuration= KcServiceLocator.getService(DateTimeService.class).dateDiff(budgetPersonnelDetail.getStartDate(), budgetPersonnelDetail.getEndDate(), false);
                    gap= KcServiceLocator.getService(DateTimeService.class).dateDiff(prevBudgetLineItem.getStartDate(), budgetPersonnelDetail.getStartDate(), false);
                    isLeapDayInGap = KcServiceLocator.getService(BudgetSummaryService.class).isLeapDaysInPeriod(prevBudgetLineItem.getStartDate(), budgetPersonnelDetail.getStartDate());
                    if (periodDuration == personnelDuration || personnelDuration >= lineDuration) {
                        budgetPersonnelDetail.setStartDate(budgetLineItem.getStartDate());
                        budgetPersonnelDetail.setEndDate(budgetLineItem.getEndDate());
                    } else {
                        startEndDates.add(0, budgetLineItem.getStartDate());
                        startEndDates.add(1, budgetLineItem.getEndDate());
                        List <java.sql.Date> dates = KcServiceLocator.getService(BudgetSummaryService.class).getNewStartEndDates(startEndDates, gap, personnelDuration, budgetPersonnelDetail.getStartDate(), isLeapDateInPeriod, isLeapDayInGap);
                        budgetPersonnelDetail.setStartDate(dates.get(0));
                        budgetPersonnelDetail.setEndDate(dates.get(1));
                    }

                    
                    budgetPersonnelDetail.setVersionNumber(null);
                    budgetCalculationService.populateCalculatedAmount(budget, budgetPersonnelDetail);
                    List<BudgetPersonnelCalculatedAmount> persCalAmounts = budgetPersonnelDetail.getBudgetPersonnelCalculatedAmounts();
                    for (BudgetPersonnelCalculatedAmount budgetPersonnelCalculatedAmount : persCalAmounts) {
                        budgetPersonnelCalculatedAmount.setBudgetPersonnelCalculatedAmountId(null);
                        budgetPersonnelCalculatedAmount.setVersionNumber(null);
                    }
                    List<BudgetPersonnelRateAndBase> perBudgetRateAndBases = budgetPersonnelDetail.getBudgetPersonnelRateAndBaseList();
                    for (BudgetPersonnelRateAndBase budgetPersonnelRateAndBase : perBudgetRateAndBases) {
                        budgetPersonnelRateAndBase.setBudgetPersonnelRateAndBaseId(null);
                        budgetPersonnelRateAndBase.setVersionNumber(null);
                    }
                }
                List<BudgetRateAndBase> budgetRateAndBases = budgetLineItem.getBudgetRateAndBaseList();
                for (BudgetRateAndBase budgetRateAndBase : budgetRateAndBases) {
                    budgetRateAndBase.setBudgetRateAndBaseId(null);
                    budgetRateAndBase.setVersionNumber(null);
                }
                
                budgetPeriod.getBudgetLineItems().add(budgetLineItem);
                budgetCalculationService.populateCalculatedAmount(budget, budgetLineItem);
                for (BudgetLineItemCalculatedAmount prevCalAmts : prevBudgetLineItem.getBudgetLineItemCalculatedAmounts()) {
                    for (BudgetLineItemCalculatedAmount calAmts : budgetLineItem.getBudgetLineItemCalculatedAmounts()) {
                        if (prevCalAmts.getRateClassCode().equals(calAmts.getRateClassCode()) && prevCalAmts.getRateTypeCode().equals(calAmts.getRateTypeCode())) {
                            calAmts.setBudgetLineItemCalculatedAmountId(null);
                            calAmts.setApplyRateFlag(prevCalAmts.getApplyRateFlag());
                            calAmts.setVersionNumber(null);
                        }
                    }
                }
                // calculate again after reset apply rate flag
                budgetCalculationService.calculateBudgetLineItem(budget, budgetLineItem);

                prevBudgetLineItem=budgetLineItem;
            }

        }
    }
    
    private ScaleTwoDecimal calculateInflation(Budget budget, BudgetLineItem budgetLineItem, Date endDate) {
        CostElement costElement = budgetLineItem.getCostElementBO();
        ScaleTwoDecimal lineItemCost = budgetLineItem.getLineItemCost();
        Date startDate = budgetLineItem.getStartDate();
        // Date endDate = budgetDetailBean.getLineItemEndDate();

        // Cost Calculation
        Equals eqInflation = new Equals("rateClassType", RateClassType.INFLATION.getRateClassType());
        // Check for inflation for the Cost Element.
        // Get ValidCERateTypesBean From Server Side.
        if(costElement.getValidCeRateTypes().isEmpty()) costElement.refreshReferenceObject("validCeRateTypes");
        QueryList<ValidCeRateType> vecValidCERateTypes = new QueryList<ValidCeRateType>(costElement.getValidCeRateTypes());
        QueryList<ValidCeRateType> vecCE = vecValidCERateTypes.filter(eqInflation);

        if (vecCE != null && vecCE.size() > 0) {
            ValidCeRateType validCERateTypesBean = vecCE.get(0);
            Equals eqRC = new Equals("rateClassCode", validCERateTypesBean.getRateClassCode());
            Equals eqRT = new Equals("rateTypeCode", validCERateTypesBean.getRateTypeCode());
            GreaterThan gtSD = new GreaterThan("startDate", startDate);
            LesserThan ltED = new LesserThan("startDate", endDate);
            Equals eqED = new Equals("startDate", endDate);
            Or ltEDOrEqED = new Or(ltED, eqED);

            And ltOrEqEDAndGtSD = new And(ltEDOrEqED, gtSD);

            And rcAndRt = new And(eqRC, eqRT);

            And rcAndRtAndLtOrEqEDAndGtSD = new And(rcAndRt, ltOrEqEDAndGtSD);

            QueryList<BudgetRate> vecPropInflationRates = new QueryList<BudgetRate>(budget
                    .getBudgetRates()).filter(rcAndRtAndLtOrEqEDAndGtSD);

            if (!vecPropInflationRates.isEmpty()) {
                // Sort so that the recent date comes first
                vecPropInflationRates.sort("startDate", false);

                //BudgetRate proposalRatesBean = vecPropInflationRates.get(0);
                BudgetRate proposalRatesBean = getCampusMatchedRateBean(budgetLineItem.getOnOffCampusFlag(), vecPropInflationRates);
                if (proposalRatesBean != null) {
                    ScaleTwoDecimal applicableRate = proposalRatesBean.getApplicableRate();
                    // lineItemCost = lineItemCost * (100 + applicableRate) / 100;
                    lineItemCost = lineItemCost.add(lineItemCost.percentage(applicableRate));
                }
            }// End For vecPropInflationRates != null ...
        }// End If vecCE != null ...
        return lineItemCost;
    }

    private BudgetRate getCampusMatchedRateBean(boolean onOffCampus, QueryList<BudgetRate> vecPropInflationRates) {
        BudgetRate proposalRatesBean = null;
        for (BudgetRate budgetRate : vecPropInflationRates) {
            if (budgetRate.getOnOffCampusFlag().booleanValue() == onOffCampus) {
                proposalRatesBean = budgetRate;
                break;
            }
        }
        return proposalRatesBean;
    }
    
    private void resetRateClassTypeIfNeeded(List<BudgetLineItemCalculatedAmount> vecCalAmts) {
        for(BudgetLineItemCalculatedAmount calcAmt: vecCalAmts) {
            if(StringUtils.isEmpty(calcAmt.getRateClassType())) {
                calcAmt.refreshReferenceObject("rateClass");
                if(calcAmt.getRateClass() != null) {
                    calcAmt.setRateClassType(calcAmt.getRateClass().getRateClassTypeCode());
                }
            }
        }
    }

    public boolean syncToPeriodDirectCostLimit(Budget budget, BudgetPeriod budgetPeriodBean, BudgetLineItem budgetDetailBean) {
        // If total_cost equals total_cost_limit, disp msg "Cost limit and total cost for this period is already in sync."
        ScaleTwoDecimal directCostLimit = budgetPeriodBean.getDirectCostLimit();
        ScaleTwoDecimal periodDirectTotal = budgetPeriodBean.getTotalDirectCost();
        directCostLimit = budgetPeriodBean.getDirectCostLimit();

        // Set the Difference as TotalCostLimit minus TotalCost.
        BigDecimal difference = directCostLimit.subtract(periodDirectTotal).bigDecimalValue();
        ScaleTwoDecimal lineItemCost = budgetDetailBean.getLineItemCost();
        BigDecimal multifactor;

        // If line_item_cost is 0 then set the value of line_item_cost in line_items to 10000.
        if (lineItemCost.equals(ScaleTwoDecimal.ZERO)) {
            budgetDetailBean.setLineItemCost(new ScaleTwoDecimal(10000));
        }

        calculate(budget, budgetPeriodBean);

        QueryList<BudgetLineItemCalculatedAmount> vecCalAmts = new QueryList<BudgetLineItemCalculatedAmount>(budgetDetailBean
                .getBudgetLineItemCalculatedAmounts());

        resetRateClassTypeIfNeeded(vecCalAmts);

        ScaleTwoDecimal totalNOHCalcAmount = vecCalAmts.sumObjects("calculatedCost", new NotEquals("rateClassType",
            RateClassType.OVERHEAD.getRateClassType()));
        BigDecimal totalCost = budgetDetailBean.getLineItemCost().add(totalNOHCalcAmount).bigDecimalValue();
        // If the lineItemCost <> 0, set multifactor to TotalCost divided by lineItemCost otherwise multifactor is TotalCost divided
        // by 10000
        if (!lineItemCost.equals(ScaleTwoDecimal.ZERO)) {
            multifactor = totalCost.divide(lineItemCost.bigDecimalValue(), RoundingMode.HALF_UP);
        }else {
            multifactor = totalCost.divide(new ScaleTwoDecimal(10000).bigDecimalValue(), RoundingMode.HALF_UP);
            budgetDetailBean.setLineItemCost(ScaleTwoDecimal.ZERO);
            calculate(budget, budgetPeriodBean);
            totalCost = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }

        if (isProposalBudget(budget) && new ScaleTwoDecimal(totalCost.add(difference)).isLessEqual(ScaleTwoDecimal.ZERO)) {
            return false;
        }

        // Set New Cost
        ScaleTwoDecimal newCost = lineItemCost.add(new ScaleTwoDecimal(difference.divide(multifactor, RoundingMode.HALF_UP)));
        budgetDetailBean.setLineItemCost(newCost);
        calculate(budget, budgetPeriodBean);
        return true;


    }
    public boolean syncToPeriodCostLimit(Budget budget, BudgetPeriod budgetPeriodBean, BudgetLineItem budgetDetailBean) {

        // If total_cost equals total_cost_limit, disp msg "Cost limit and total cost for this period is already in sync."
        ScaleTwoDecimal costLimit = budgetPeriodBean.getTotalCostLimit();
        ScaleTwoDecimal periodTotal = budgetPeriodBean.getTotalCost();

        // Set the Difference as TotalCostLimit minus TotalCost.
        BigDecimal difference = costLimit.subtract(periodTotal).bigDecimalValue();
        ScaleTwoDecimal lineItemCost = budgetDetailBean.getLineItemCost();
        BigDecimal multifactor;

        // If line_item_cost is 0 then set the value of line_item_cost in line_items to 10000.
        if (lineItemCost.equals(ScaleTwoDecimal.ZERO)) {
            budgetDetailBean.setLineItemCost(new ScaleTwoDecimal(10000));
        }

        calculate(budget, budgetPeriodBean);

        QueryList<BudgetLineItemCalculatedAmount> vecCalAmts = new QueryList<BudgetLineItemCalculatedAmount>(budgetDetailBean
                .getBudgetLineItemCalculatedAmounts());

        resetRateClassTypeIfNeeded(vecCalAmts);

        ScaleTwoDecimal totalNOHCalcAmount = vecCalAmts.sumObjects("calculatedCost", new NotEquals("rateClassType",
            RateClassType.OVERHEAD.getRateClassType()));
        ScaleTwoDecimal totalOHCalcAmount = vecCalAmts.sumObjects("calculatedCost", new Equals("rateClassType",
            RateClassType.OVERHEAD.getRateClassType()));

        BigDecimal totalCost = budgetDetailBean.getLineItemCost().add(totalNOHCalcAmount).add(totalOHCalcAmount).bigDecimalValue();

        // If the lineItemCost <> 0, set multifactor to TotalCost divided by lineItemCost otherwise multifactor is TotalCost divided
        // by 10000
        if (!lineItemCost.equals(ScaleTwoDecimal.ZERO)) {
            multifactor = totalCost.divide(lineItemCost.bigDecimalValue(), RoundingMode.HALF_UP);
        }else {
            multifactor = totalCost.divide(new ScaleTwoDecimal(10000).bigDecimalValue(), RoundingMode.HALF_UP);
            budgetDetailBean.setLineItemCost(ScaleTwoDecimal.ZERO);
            calculate(budget, budgetPeriodBean);
            totalCost = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }

        if (isProposalBudget(budget) && new ScaleTwoDecimal(totalCost.add(difference)).isLessEqual(ScaleTwoDecimal.ZERO)) {
            return false;
        }

        // Set New Cost
        ScaleTwoDecimal newCost = lineItemCost.add(new ScaleTwoDecimal(difference.divide(multifactor, RoundingMode.HALF_UP)));
        budgetDetailBean.setLineItemCost(newCost);
        calculate(budget, budgetPeriodBean);
        return true;
    }

    private boolean checkSyncToLimitErrors(Budget budget,BudgetLineItem budgetDetailBean,ScaleTwoDecimal costLimit) {
        if (budgetDetailBean.getBudgetCategory().getBudgetCategoryTypeCode().equals(KeyConstants.PERSONNEL_CATEGORY) && 
              !budgetDetailBean.getBudgetPersonnelDetailsList().isEmpty()) {
            errorMessages.add(KeyConstants.PERSONNEL_LINE_ITEM_EXISTS);
            return false;
        }

        // if cost_limit is 0 disp msg "Cost limit for this period is set to 0. Cannot sync a line item cost to zero limit."
        if (isProposalBudget(budget) && costLimit.equals(ScaleTwoDecimal.ZERO)) {
            errorMessages.add(KeyConstants.CANNOT_SYNC_TO_ZERO_LIMIT);
            return false;
        }
        return true;
    }
    private boolean isProposalBudget(Budget budget){
        return budget.isProposalBudget();
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }
    
	public DataObjectService getDataObjectService() {
		if (dataObjectService == null) {
			dataObjectService = getService(DataObjectService.class);
		}
		return dataObjectService;
	}
}

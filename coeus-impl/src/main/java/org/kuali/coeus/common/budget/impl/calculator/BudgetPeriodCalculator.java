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

public class BudgetPeriodCalculator {
	private DataObjectService dataObjectService;
    private BudgetCalculationService budgetCalculationService;
    private DateTimeService dateTimeService;
    private BudgetSummaryService budgetSummaryService;

    private List<String> errorMessages = new ArrayList<>();

    /**
     * 
     * This method calculates and sync the budget period.
     */
    public void calculate(Budget budget, BudgetPeriod budgetPeriod) {
        budgetPeriod.setTotalDirectCost(ScaleTwoDecimal.ZERO);
        budgetPeriod.setTotalIndirectCost(ScaleTwoDecimal.ZERO);
        budgetPeriod.setCostSharingAmount(ScaleTwoDecimal.ZERO);
        budgetPeriod.setTotalCost(ScaleTwoDecimal.ZERO);
        budgetPeriod.setUnderrecoveryAmount(ScaleTwoDecimal.ZERO);

        //avoiding java.util.ConcurrentModificationException by doing a defensive copy
        new ArrayList<>(budgetPeriod.getBudgetLineItems()).forEach(budgetLineItem -> {
            getBudgetCalculationService().calculateBudgetLineItem(budget, budgetLineItem);
            budgetPeriod.setTotalDirectCost(budgetPeriod.getTotalDirectCost().add(budgetLineItem.getDirectCost()));
            budgetPeriod.setTotalIndirectCost(budgetPeriod.getTotalIndirectCost().add(budgetLineItem.getIndirectCost()));
            budgetPeriod.setTotalCost(budgetPeriod.getTotalCost().add(budgetLineItem.getDirectCost().add(budgetLineItem.getIndirectCost())));
            budgetPeriod.setUnderrecoveryAmount(budgetPeriod.getUnderrecoveryAmount().add(budgetLineItem.getUnderrecoveryAmount()));
            budgetPeriod.setCostSharingAmount(budgetPeriod.getCostSharingAmount().add(budgetLineItem.getTotalCostSharingAmount()));
        });
    }

    public void applyToLaterPeriods(Budget budget, BudgetPeriod currentBudgetPeriod, BudgetLineItem currentBudgetLineItem) {

        //put all lineitems in one bucket
        List<BudgetPeriod> budgetPeriods = budget.getBudgetPeriods();
        BudgetLineItem prevBudgetLineItem = currentBudgetLineItem;
        int periodDuration = getDateTimeService().dateDiff(currentBudgetPeriod.getStartDate(), currentBudgetPeriod.getEndDate(), false);
        // calculate for the apply-from item in case there is any change, so it will be updated properly after apply-to
        getBudgetCalculationService().calculateBudgetLineItem(budget, currentBudgetLineItem);
        if(currentBudgetLineItem.getBudgetCategory() == null) {
        	getDataObjectService().wrap(currentBudgetLineItem).fetchRelationship("budgetCategory");
        }
        for (BudgetPeriod budgetPeriod : budgetPeriods) {
            if(budgetPeriod.getBudgetPeriod()<=currentBudgetPeriod.getBudgetPeriod()) continue;
            QueryList<BudgetLineItem> currentBudgetPeriodLineItems = new QueryList<>(budgetPeriod.getBudgetLineItems());
            for (BudgetLineItem budgetLineItemToBeApplied : currentBudgetPeriodLineItems) {
            	if(budgetLineItemToBeApplied.getBudgetCategory() == null) {
                	getDataObjectService().wrap(budgetLineItemToBeApplied).fetchRelationship("budgetCategory");
                }            	
                if(prevBudgetLineItem.getLineItemNumber().equals(budgetLineItemToBeApplied.getBasedOnLineItem())) {
                    budgetLineItemToBeApplied.setApplyInRateFlag(prevBudgetLineItem.getApplyInRateFlag());
                    if (prevBudgetLineItem.getApplyInRateFlag()){
                    // calculate no matter what because applyinrateflag maybe changed ??
                    
                        if (KeyConstants.PERSONNEL_CATEGORY.equals(budgetLineItemToBeApplied.getBudgetCategory().getBudgetCategoryTypeCode())
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
                    getBudgetCalculationService().populateCalculatedAmount(budget, budgetLineItemToBeApplied);
                    for (BudgetLineItemCalculatedAmount prevCalAmts : prevBudgetLineItem.getBudgetLineItemCalculatedAmounts()) {
                        for (BudgetLineItemCalculatedAmount CalAmts : budgetLineItemToBeApplied.getBudgetLineItemCalculatedAmounts()) {
                            if (prevCalAmts.getRateClassCode().equals(CalAmts.getRateClassCode()) && prevCalAmts.getRateTypeCode().equals(CalAmts.getRateTypeCode())) {
                                CalAmts.setApplyRateFlag(prevCalAmts.getApplyRateFlag());
                            }
                        }
                    }

                    getBudgetCalculationService().calculateBudgetLineItem(budget, budgetLineItemToBeApplied);
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
                boolean isLeapDateInPeriod = getBudgetSummaryService().isLeapDaysInPeriod(prevBudgetLineItem.getStartDate(), prevBudgetLineItem.getEndDate()) ;
                gap= getDateTimeService().dateDiff(currentBudgetPeriod.getStartDate(), currentBudgetLineItem.getStartDate(), false);
                boolean isLeapDayInGap = getBudgetSummaryService().isLeapDaysInPeriod(currentBudgetPeriod.getStartDate(), currentBudgetLineItem.getStartDate());
                lineDuration= getDateTimeService().dateDiff(budgetLineItem.getStartDate(), budgetLineItem.getEndDate(), false);
                currentPeriodDuration = getDateTimeService().dateDiff(budgetPeriod.getStartDate(), budgetPeriod.getEndDate(), false);
                List <java.sql.Date> startEndDates = new ArrayList<> ();
                if (periodDuration == lineDuration || lineDuration > currentPeriodDuration) {
                    budgetLineItem.setStartDate(budgetPeriod.getStartDate());
                    budgetLineItem.setEndDate(budgetPeriod.getEndDate());
                } else {
                    startEndDates.add(0, budgetPeriod.getStartDate());
                    startEndDates.add(1, budgetPeriod.getEndDate());
                    List <java.sql.Date> dates = getBudgetSummaryService().getNewStartEndDates(startEndDates, gap, lineDuration, budgetLineItem.getStartDate(), isLeapDateInPeriod,isLeapDayInGap);
                    budgetLineItem.setStartDate(dates.get(0));
                    budgetLineItem.setEndDate(dates.get(1));
                }

                
                budgetLineItem.setBasedOnLineItem(prevBudgetLineItem.getLineItemNumber());
                budgetLineItem.setVersionNumber(null);
                
                if (prevBudgetLineItem.getApplyInRateFlag()){
                    ScaleTwoDecimal lineItemCost = calculateInflation(budget, prevBudgetLineItem, budgetLineItem.getStartDate());
                    budgetLineItem.setLineItemCost(lineItemCost);
                }
                
                lineDuration= getDateTimeService().dateDiff(budgetLineItem.getStartDate(), budgetLineItem.getEndDate(), false);
                int personnelDuration;

                /* add personnel line items */
                List<BudgetPersonnelDetails> budgetPersonnelDetails = budgetLineItem.getBudgetPersonnelDetailsList();
                for(BudgetPersonnelDetails budgetPersonnelDetail: budgetPersonnelDetails) {
                    isLeapDateInPeriod = getBudgetSummaryService().isLeapDaysInPeriod(budgetPersonnelDetail.getStartDate(), budgetPersonnelDetail.getEndDate()) ;
                    budgetPersonnelDetail.setBudgetPersonnelLineItemId(null);
                    budgetPersonnelDetail.getBudgetCalculatedAmounts().clear();
                    budgetPersonnelDetail.setBudgetPeriod(budgetPeriod.getBudgetPeriod());
                    budgetPersonnelDetail.setBudgetPeriodId(budgetPeriod.getBudgetPeriodId());
                    budgetPersonnelDetail.setLineItemSequence(budget.getNextValue(Constants.BUDGET_PERSON_LINE_SEQUENCE_NUMBER));
                    
                    personnelDuration= getDateTimeService().dateDiff(budgetPersonnelDetail.getStartDate(), budgetPersonnelDetail.getEndDate(), false);
                    gap= getDateTimeService().dateDiff(prevBudgetLineItem.getStartDate(), budgetPersonnelDetail.getStartDate(), false);
                    isLeapDayInGap = getBudgetSummaryService().isLeapDaysInPeriod(prevBudgetLineItem.getStartDate(), budgetPersonnelDetail.getStartDate());
                    if (periodDuration == personnelDuration || personnelDuration >= lineDuration) {
                        budgetPersonnelDetail.setStartDate(budgetLineItem.getStartDate());
                        budgetPersonnelDetail.setEndDate(budgetLineItem.getEndDate());
                    } else {
                        startEndDates.add(0, budgetLineItem.getStartDate());
                        startEndDates.add(1, budgetLineItem.getEndDate());
                        List <java.sql.Date> dates = getBudgetSummaryService().getNewStartEndDates(startEndDates, gap, personnelDuration, budgetPersonnelDetail.getStartDate(), isLeapDateInPeriod, isLeapDayInGap);
                        budgetPersonnelDetail.setStartDate(dates.get(0));
                        budgetPersonnelDetail.setEndDate(dates.get(1));
                    }

                    
                    budgetPersonnelDetail.setVersionNumber(null);
                    getBudgetCalculationService().populateCalculatedAmount(budget, budgetPersonnelDetail);
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
                getBudgetCalculationService().populateCalculatedAmount(budget, budgetLineItem);
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
                getBudgetCalculationService().calculateBudgetLineItem(budget, budgetLineItem);

                prevBudgetLineItem=budgetLineItem;
            }

        }
    }
    
    private ScaleTwoDecimal calculateInflation(Budget budget, BudgetLineItem budgetLineItem, Date endDate) {
        CostElement costElement = budgetLineItem.getCostElementBO();
        ScaleTwoDecimal lineItemCost = budgetLineItem.getLineItemCost();
        Date startDate = budgetLineItem.getStartDate();
        Equals eqInflation = new Equals("rateClassType", RateClassType.INFLATION.getRateClassType());

        if(costElement.getValidCeRateTypes().isEmpty()) costElement.refreshReferenceObject("validCeRateTypes");
        QueryList<ValidCeRateType> vecValidCERateTypes = new QueryList<>(costElement.getValidCeRateTypes());
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
            QueryList<BudgetRate> vecPropInflationRates = new QueryList<>(budget
                    .getBudgetRates()).filter(rcAndRtAndLtOrEqEDAndGtSD);

            if (!vecPropInflationRates.isEmpty()) {
                // Sort so that the recent date comes first
                vecPropInflationRates.sort("startDate", false);
                BudgetRate proposalRatesBean = getCampusMatchedRateBean(budgetLineItem.getOnOffCampusFlag(), vecPropInflationRates);
                if (proposalRatesBean != null) {
                    ScaleTwoDecimal applicableRate = proposalRatesBean.getApplicableRate();
                    lineItemCost = lineItemCost.add(lineItemCost.percentage(applicableRate));
                }
            }
        }
        return lineItemCost;
    }

    private BudgetRate getCampusMatchedRateBean(boolean onOffCampus, QueryList<BudgetRate> vecPropInflationRates) {
        BudgetRate proposalRatesBean = null;
        for (BudgetRate budgetRate : vecPropInflationRates) {
            if (budgetRate.getOnOffCampusFlag().equals(onOffCampus)) {
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
        ScaleTwoDecimal periodDirectTotal = budgetPeriodBean.getTotalDirectCost();
        ScaleTwoDecimal directCostLimit = budgetPeriodBean.getDirectCostLimit();

        // Set the Difference as TotalCostLimit minus TotalCost.
        BigDecimal difference = directCostLimit.subtract(periodDirectTotal).bigDecimalValue();
        ScaleTwoDecimal lineItemCost = budgetDetailBean.getLineItemCost();
        BigDecimal multifactor;

        // If line_item_cost is 0 then set the value of line_item_cost in line_items to 10000.
        if (lineItemCost.equals(ScaleTwoDecimal.ZERO)) {
            budgetDetailBean.setLineItemCost(new ScaleTwoDecimal(10000));
        }

        calculate(budget, budgetPeriodBean);

        QueryList<BudgetLineItemCalculatedAmount> vecCalAmts = new QueryList<>(budgetDetailBean
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

        QueryList<BudgetLineItemCalculatedAmount> vecCalAmts = new QueryList<>(budgetDetailBean
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
			dataObjectService = KcServiceLocator.getService(DataObjectService.class);
		}
		return dataObjectService;
	}

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }

    public BudgetCalculationService getBudgetCalculationService() {
        if (budgetCalculationService == null) {
            budgetCalculationService = KcServiceLocator.getService(BudgetCalculationService.class);
        }
        return budgetCalculationService;
    }

    public void setBudgetCalculationService(BudgetCalculationService budgetCalculationService) {
        this.budgetCalculationService = budgetCalculationService;
    }

    public DateTimeService getDateTimeService() {
        if (dateTimeService == null) {
            dateTimeService = KcServiceLocator.getService(DateTimeService.class);
        }
        return dateTimeService;
    }

    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    public BudgetSummaryService getBudgetSummaryService() {
        if (budgetSummaryService == null) {
            budgetSummaryService = KcServiceLocator.getService(BudgetSummaryService.class);
        }
        return budgetSummaryService;
    }

    public void setBudgetSummaryService(BudgetSummaryService budgetSummaryService) {
        this.budgetSummaryService = budgetSummaryService;
    }
}

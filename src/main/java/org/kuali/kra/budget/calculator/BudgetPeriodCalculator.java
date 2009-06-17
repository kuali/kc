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

import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetLineItemCalculatedAmount;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.bo.BudgetProposalRate;
import org.kuali.kra.budget.bo.CostElement;
import org.kuali.kra.budget.bo.ValidCeRateType;
import org.kuali.kra.budget.calculator.query.And;
import org.kuali.kra.budget.calculator.query.Equals;
import org.kuali.kra.budget.calculator.query.GreaterThan;
import org.kuali.kra.budget.calculator.query.LesserThan;
import org.kuali.kra.budget.calculator.query.NotEquals;
import org.kuali.kra.budget.calculator.query.Or;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.service.BudgetCalculationService;
import org.kuali.kra.budget.service.BudgetSummaryService;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.service.DateTimeService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.ObjectUtils;

public class BudgetPeriodCalculator {
    private BudgetCalculationService budgetCalculationService;
    private DateTimeService dateTimeService;
    private List<String> errorMessages;
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(AbstractBudgetCalculator.class);

    public BudgetPeriodCalculator() {
        budgetCalculationService = getService(BudgetCalculationService.class);
        errorMessages = new ArrayList<String>();
        dateTimeService = KNSServiceLocator.getDateTimeService();
    }

    /**
     * 
     * This method calculates and sync the budget period
     * 
     * @param budgetDocument
     * @param budgetPeriod
     */
    public void calculate(BudgetDocument budgetDocument, BudgetPeriod budgetPeriod) {
        List<BudgetLineItem> cvLineItemDetails = budgetPeriod.getBudgetLineItems();
//        if(cvLineItemDetails.isEmpty())
//            return;
        budgetPeriod.setTotalDirectCost(BudgetDecimal.ZERO);
        budgetPeriod.setTotalIndirectCost(BudgetDecimal.ZERO);
        budgetPeriod.setCostSharingAmount(BudgetDecimal.ZERO);
        budgetPeriod.setTotalCost(BudgetDecimal.ZERO);
        budgetPeriod.setUnderrecoveryAmount(BudgetDecimal.ZERO);
        for (BudgetLineItem budgetLineItem : cvLineItemDetails) {
            budgetCalculationService.calculateBudgetLineItem(budgetDocument, budgetLineItem);
            budgetPeriod.setTotalDirectCost(budgetPeriod.getTotalDirectCost().add(budgetLineItem.getDirectCost()));
            budgetPeriod.setTotalIndirectCost(budgetPeriod.getTotalIndirectCost().add(budgetLineItem.getIndirectCost()));
            budgetPeriod.setTotalCost(budgetPeriod.getTotalCost().add(budgetLineItem.getDirectCost().add(budgetLineItem.getIndirectCost())));
            budgetPeriod.setUnderrecoveryAmount(budgetPeriod.getUnderrecoveryAmount().add(budgetLineItem.getUnderrecoveryAmount()));
            budgetPeriod.setCostSharingAmount(budgetPeriod.getCostSharingAmount().add(budgetLineItem.getTotalCostSharingAmount()));
        }
        if(budgetDocument.getOhRateClassCode()!=null && ((BudgetForm)GlobalVariables.getKualiForm())!=null){
        //if(budgetDocument.getOhRateClassCode()!=null && ((BudgetForm)GlobalVariables.getKualiForm())!=null && budgetDocument.getBudgetPeriods().size() == budgetPeriod.getBudgetPeriod()){
            // this should be set at the last period, otherwise, only the first period will be updated properly because lots of places check prevohrateclass
            // This approach affect other area, so rolled it back.
            ((BudgetForm)GlobalVariables.getKualiForm()).setOhRateClassCodePrevValue(budgetDocument.getOhRateClassCode());
        }        
        // syncBudgetTotals(budgetDocument);
    }

    public void applyToLaterPeriods(BudgetDocument budgetDocument, BudgetPeriod currentBudgetPeriod, BudgetLineItem currentBudgetLineItem) {

        //put all lineitems in one bucket
        List<BudgetPeriod> budgetPeriods = budgetDocument.getBudgetPeriods();
        BudgetLineItem prevBudgetLineItem = currentBudgetLineItem;
        int periodDuration = KraServiceLocator.getService(DateTimeService.class).dateDiff(currentBudgetPeriod.getStartDate(), currentBudgetPeriod.getEndDate(), false);
        // calculate for the apply-from item in case there is any change, so it will be updated properly after apply-to
        budgetCalculationService.calculateBudgetLineItem(budgetDocument, currentBudgetLineItem);
        
        for (BudgetPeriod budgetPeriod : budgetPeriods) {
            if(budgetPeriod.getBudgetPeriod()<=currentBudgetPeriod.getBudgetPeriod()) continue;
//            allLineItems.addAll(budgetPeriod.getBudgetLineItems());
            QueryList<BudgetLineItem> currentBudgetPeriodLineItems = new QueryList<BudgetLineItem>(budgetPeriod.getBudgetLineItems());
            for (BudgetLineItem budgetLineItemToBeApplied : currentBudgetPeriodLineItems) {
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
                        
                        BudgetDecimal lineItemCost = calculateInflation(budgetDocument, prevBudgetLineItem, budgetLineItemToBeApplied
                                .getStartDate());
                        if(!budgetLineItemToBeApplied.getCostElement().equals(prevBudgetLineItem.getCostElement())){
                            budgetLineItemToBeApplied.setBudgetPeriod(budgetPeriod.getBudgetPeriod());
                            budgetLineItemToBeApplied.setBudgetCategory(prevBudgetLineItem.getBudgetCategory());
                            budgetLineItemToBeApplied.setStartDate(budgetPeriod.getStartDate());
                            budgetLineItemToBeApplied.setEndDate(budgetPeriod.getEndDate());
                            budgetLineItemToBeApplied.setCostElement(prevBudgetLineItem.getCostElement());
                            budgetLineItemToBeApplied.refreshReferenceObject("costElementBO");
                            budgetLineItemToBeApplied.setBudgetCategoryCode(budgetLineItemToBeApplied.getCostElementBO().getBudgetCategoryCode());
    //                        budgetLineItemToBeApplied.setCostElement(prevBudgetLineItem.getCostElement());
    //                        budgetLineItemToBeApplied.setCostElementBO(prevBudgetLineItem.getCostElementBO());
    //                        budgetCalculationService.rePopulateCalculatedAmount(budgetDocument, budgetLineItemToBeApplied);
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
                    budgetCalculationService.calculateBudgetLineItem(budgetDocument, budgetLineItemToBeApplied);
                    for (BudgetLineItemCalculatedAmount prevCalAmts : prevBudgetLineItem.getBudgetLineItemCalculatedAmounts()) {
                        for (BudgetLineItemCalculatedAmount CalAmts : budgetLineItemToBeApplied.getBudgetLineItemCalculatedAmounts()) {
                            if (prevCalAmts.getRateClassCode().equals(CalAmts.getRateClassCode()) && prevCalAmts.getRateTypeCode().equals(CalAmts.getRateTypeCode())) {
                                CalAmts.setApplyRateFlag(prevCalAmts.getApplyRateFlag());
                            }
                        }
                    }

                    budgetCalculationService.calculateBudgetLineItem(budgetDocument, budgetLineItemToBeApplied);
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
                budgetLineItem.getBudgetCalculatedAmounts().clear();
                budgetLineItem.setBudgetPeriod(budgetPeriod.getBudgetPeriod());
                budgetLineItem.setBudgetPeriodId(budgetPeriod.getBudgetPeriodId());
                //budgetLineItem.setStartDate(budgetPeriod.getStartDate());
                //budgetLineItem.setEndDate(budgetPeriod.getEndDate());
                
                boolean isLeapDateInPeriod = KraServiceLocator.getService(BudgetSummaryService.class).isLeapDaysInPeriod(prevBudgetLineItem.getStartDate(), prevBudgetLineItem.getEndDate()) ;
                gap=KraServiceLocator.getService(DateTimeService.class).dateDiff(currentBudgetPeriod.getStartDate(), currentBudgetLineItem.getStartDate(), false);
                boolean isLeapDayInGap = KraServiceLocator.getService(BudgetSummaryService.class).isLeapDaysInPeriod(currentBudgetPeriod.getStartDate(), currentBudgetLineItem.getStartDate());
                lineDuration=KraServiceLocator.getService(DateTimeService.class).dateDiff(budgetLineItem.getStartDate(), budgetLineItem.getEndDate(), false);
                currentPeriodDuration = KraServiceLocator.getService(DateTimeService.class).dateDiff(budgetPeriod.getStartDate(), budgetPeriod.getEndDate(), false);
                List <java.sql.Date> startEndDates = new ArrayList<java.sql.Date> ();
                if (periodDuration == lineDuration || lineDuration > currentPeriodDuration) {
                    budgetLineItem.setStartDate(budgetPeriod.getStartDate());
                    budgetLineItem.setEndDate(budgetPeriod.getEndDate());
                } else {
                    startEndDates.add(0, budgetPeriod.getStartDate());
                    startEndDates.add(1, budgetPeriod.getEndDate());
                    List <java.sql.Date> dates = KraServiceLocator.getService(BudgetSummaryService.class).getNewStartEndDates(startEndDates, gap, lineDuration, budgetLineItem.getStartDate(), isLeapDateInPeriod,isLeapDayInGap);
                    budgetLineItem.setStartDate(dates.get(0));
                    budgetLineItem.setEndDate(dates.get(1));
                }

                
                budgetLineItem.setBasedOnLineItem(prevBudgetLineItem.getLineItemNumber());
                //budgetLineItem.setLineItemNumber(budgetDocument.getHackedDocumentNextValue(Constants.BUDGET_LINEITEM_NUMBER));                    
                //budgetLineItem.setLineItemSequence(budgetLineItem.getLineItemNumber());
                budgetLineItem.setVersionNumber(null);
                
                if (prevBudgetLineItem.getApplyInRateFlag()){
                    BudgetDecimal lineItemCost = calculateInflation(budgetDocument, prevBudgetLineItem, budgetLineItem.getStartDate());
                    budgetLineItem.setLineItemCost(lineItemCost);
                }
                
                lineDuration=KraServiceLocator.getService(DateTimeService.class).dateDiff(budgetLineItem.getStartDate(), budgetLineItem.getEndDate(), false);
                int personnelDuration = 0;

                /* add personnel line items */
                List<BudgetPersonnelDetails> budgetPersonnelDetails = budgetLineItem.getBudgetPersonnelDetailsList();
                for(BudgetPersonnelDetails budgetPersonnelDetail: budgetPersonnelDetails) {
                    isLeapDateInPeriod = KraServiceLocator.getService(BudgetSummaryService.class).isLeapDaysInPeriod(budgetPersonnelDetail.getStartDate(), budgetPersonnelDetail.getEndDate()) ;
                    budgetPersonnelDetail.getBudgetCalculatedAmounts().clear();
                    budgetPersonnelDetail.setBudgetPeriod(budgetPeriod.getBudgetPeriod());
                    budgetPersonnelDetail.setBudgetPeriodId(budgetPeriod.getBudgetPeriodId());
                    //budgetPersonnelDetail.setLineItemNumber(budgetLineItem.getLineItemNumber());
                    budgetPersonnelDetail.setLineItemSequence(budgetDocument.getHackedDocumentNextValue(Constants.BUDGET_PERSON_LINE_SEQUENCE_NUMBER));
                    //budgetPersonnelDetail.setStartDate(budgetPeriod.getStartDate());
                    //budgetPersonnelDetail.setEndDate(budgetPeriod.getEndDate());
                    
                    personnelDuration=KraServiceLocator.getService(DateTimeService.class).dateDiff(budgetPersonnelDetail.getStartDate(), budgetPersonnelDetail.getEndDate(), false);
                    gap=KraServiceLocator.getService(DateTimeService.class).dateDiff(prevBudgetLineItem.getStartDate(), budgetPersonnelDetail.getStartDate(), false);
                    isLeapDayInGap = KraServiceLocator.getService(BudgetSummaryService.class).isLeapDaysInPeriod(prevBudgetLineItem.getStartDate(), budgetPersonnelDetail.getStartDate());
                    if (periodDuration == personnelDuration || personnelDuration >= lineDuration) {
                        budgetPersonnelDetail.setStartDate(budgetLineItem.getStartDate());
                        budgetPersonnelDetail.setEndDate(budgetLineItem.getEndDate());
                    } else {
                        startEndDates.add(0, budgetLineItem.getStartDate());
                        startEndDates.add(1, budgetLineItem.getEndDate());
                        List <java.sql.Date> dates = KraServiceLocator.getService(BudgetSummaryService.class).getNewStartEndDates(startEndDates, gap, personnelDuration, budgetPersonnelDetail.getStartDate(), isLeapDateInPeriod, isLeapDayInGap);
                        budgetPersonnelDetail.setStartDate(dates.get(0));
                        budgetPersonnelDetail.setEndDate(dates.get(1));
                    }

                    
                    budgetPersonnelDetail.setVersionNumber(null);
                }
                budgetPeriod.getBudgetLineItems().add(budgetLineItem);
                budgetCalculationService.calculateBudgetLineItem(budgetDocument, budgetLineItem);
                for (BudgetLineItemCalculatedAmount prevCalAmts : prevBudgetLineItem.getBudgetLineItemCalculatedAmounts()) {
                    for (BudgetLineItemCalculatedAmount CalAmts : budgetLineItem.getBudgetLineItemCalculatedAmounts()) {
                        if (prevCalAmts.getRateClassCode().equals(CalAmts.getRateClassCode()) && prevCalAmts.getRateTypeCode().equals(CalAmts.getRateTypeCode())) {
                            CalAmts.setApplyRateFlag(prevCalAmts.getApplyRateFlag());
                        }
                    }
                }
                // calculate again after reset apply rate flag
                budgetCalculationService.calculateBudgetLineItem(budgetDocument, budgetLineItem);

                prevBudgetLineItem=budgetLineItem;
            }

        }
    }
    private BudgetDecimal calculateInflation(BudgetDocument budgetDocument, BudgetLineItem budgetLineItem, Date endDate) {
        CostElement costElement = budgetLineItem.getCostElementBO();
        BudgetDecimal lineItemCost = budgetLineItem.getLineItemCost();
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

            QueryList<BudgetProposalRate> vecPropInflationRates = new QueryList<BudgetProposalRate>(budgetDocument
                    .getBudgetProposalRates()).filter(rcAndRtAndLtOrEqEDAndGtSD);

            if (!vecPropInflationRates.isEmpty()) {
                // Sort so that the recent date comes first
                vecPropInflationRates.sort("startDate", false);

                BudgetProposalRate proposalRatesBean = vecPropInflationRates.get(0);
                BudgetDecimal applicableRate = proposalRatesBean.getApplicableRate();
                // lineItemCost = lineItemCost * (100 + applicableRate) / 100;
                lineItemCost = lineItemCost.add(lineItemCost.percentage(applicableRate));
            }// End For vecPropInflationRates != null ...
        }// End If vecCE != null ...
        return lineItemCost;
    }

    public void syncToPeriodCostLimit(BudgetDocument budgetDocument, BudgetPeriod budgetPeriodBean, BudgetLineItem budgetDetailBean) {

        Equals eqBudgetPeriod = new Equals("budgetPeriod", new Integer(budgetDetailBean.getBudgetPeriod()));
        Equals eqLINumber = new Equals("lineItemNumber", new Integer(budgetDetailBean.getLineItemNumber()));
        And eqBudgetPeriodAndEqLINumber = new And(eqBudgetPeriod, eqLINumber);

//        if (budgetDetailBean.getBudgetCategory().getBudgetCategoryTypeCode().equals(KeyConstants.PERSONNEL_CATEGORY)) {
      if (budgetDetailBean.getBudgetCategory().getBudgetCategoryTypeCode().equals(KeyConstants.PERSONNEL_CATEGORY) && 
              !budgetDetailBean.getBudgetPersonnelDetailsList().isEmpty()) {
            errorMessages.add(KeyConstants.PERSONNEL_LINE_ITEM_EXISTS);
            return;
        }

        // if cost_limit is 0 disp msg "Cost limit for this period is set to 0. Cannot sync a line item cost to zero limit."
        if (budgetPeriodBean.getTotalCostLimit().equals(BudgetDecimal.ZERO)) {
            errorMessages.add(KeyConstants.CANNOT_SYNC_TO_ZERO_LIMIT);
            return;
        }
        calculate(budgetDocument, budgetPeriodBean);

        // If total_cost equals total_cost_limit, disp msg "Cost limit and total cost for this period is already in sync."
        BudgetDecimal periodTotal = budgetPeriodBean.getTotalCost();
        BudgetDecimal costLimit = budgetPeriodBean.getTotalCostLimit();

        if (periodTotal == costLimit) {
            errorMessages.add(KeyConstants.TOTAL_COST_ALREADY_IN_SYNC);
            return;
        }

        // if(periodTotal.isGreaterThan(costLimit)) {
        // //TODO: display confirmation
        // //"Period total is greater than the cost limit for this period.Do you want to reduce this line item cost to make the
        // total cost same as cost limit"
        // }//End IF total_cost > cost_limit

        // Set the Difference as TotalCostLimit minus TotalCost.
        BudgetDecimal difference = costLimit.subtract(periodTotal);
        BudgetDecimal lineItemCost = budgetDetailBean.getLineItemCost();
        BudgetDecimal multifactor;

        // If line_item_cost is 0 then set the value of line_item_cost in line_items to 10000.
        if (lineItemCost.equals(BudgetDecimal.ZERO)) {
            budgetDetailBean.setLineItemCost(new BudgetDecimal(10000));
        }

        calculate(budgetDocument, budgetPeriodBean);

        QueryList<BudgetLineItemCalculatedAmount> vecCalAmts = new QueryList<BudgetLineItemCalculatedAmount>(budgetDetailBean
                .getBudgetLineItemCalculatedAmounts());

        BudgetDecimal totalNOHCalcAmount = vecCalAmts.sumObjects("calculatedCost", new NotEquals("rateClassType",
            RateClassType.OVERHEAD.getRateClassType()));
        BudgetDecimal totalOHCalcAmount = vecCalAmts.sumObjects("calculatedCost", new Equals("rateClassType",
            RateClassType.OVERHEAD.getRateClassType()));

        BudgetDecimal totalCost = budgetDetailBean.getLineItemCost().add(totalNOHCalcAmount).add(totalOHCalcAmount);

        // If the lineItemCost <> 0, set multifactor to TotalCost divided by lineItemCost otherwise multifactor is TotalCost divided
        // by 10000
        if (!lineItemCost.equals(BudgetDecimal.ZERO)) {
            multifactor = totalCost.divide(lineItemCost);
        }else {
            multifactor = totalCost.divide(new BudgetDecimal(10000));
            budgetDetailBean.setLineItemCost(BudgetDecimal.ZERO);
            calculate(budgetDocument, budgetPeriodBean);
            totalCost = BudgetDecimal.ZERO;
        }

        if ((totalCost.add(difference)).isLessEqual(BudgetDecimal.ZERO)) {
            errorMessages.add(KeyConstants.INSUFFICIENT_AMOUNT_TO_SYNC);
            return;
        }

        // Set New Cost
        BudgetDecimal newCost = lineItemCost.add((difference.divide(multifactor)));
        budgetDetailBean.setLineItemCost(newCost);
        calculate(budgetDocument, budgetPeriodBean);
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

}

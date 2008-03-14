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

import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.core.service.DateTimeService;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetLineItemCalculatedAmount;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetPersonnelCalculatedAmount;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.bo.BudgetProposalRate;
import org.kuali.kra.budget.bo.CostElement;
import org.kuali.kra.budget.bo.ValidCeRateType;
import org.kuali.kra.budget.calculator.query.And;
import org.kuali.kra.budget.calculator.query.Equals;
import org.kuali.kra.budget.calculator.query.GreaterThan;
import org.kuali.kra.budget.calculator.query.LesserThan;
import org.kuali.kra.budget.calculator.query.NotEquals;
import org.kuali.kra.budget.calculator.query.ObjectCloner;
import org.kuali.kra.budget.calculator.query.Or;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.service.BudgetCalculationService;
import org.kuali.rice.KNSServiceLocator;

public class BudgetPeriodCalculator {
    private static final String PERSONNEL_CATEGORY = "P";
    /**
     * Cannot perform this operation on a line item with personel budget details.
     */
    private static final String CANNOT_PERFORM_THIS_OPERATION_ON_PERSONNEL_LINE_ITEM = "Cannot perform this operation on a line item with personel budget details.";
    private BudgetCalculationService budgetCalculationService;
    private DateTimeService dateTimeService;
    private List<String> errorMessages;
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(CalculatorBase.class);

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
        budgetPeriod.setTotalDirectCost(BudgetDecimal.ZERO);
        budgetPeriod.setTotalIndirectCost(BudgetDecimal.ZERO);
        budgetPeriod.setCostSharingAmount(BudgetDecimal.ZERO);
        budgetPeriod.setTotalCost(BudgetDecimal.ZERO);
        budgetPeriod.setUnderrecoveryAmount(BudgetDecimal.ZERO);
        List<BudgetLineItem> cvLineItemDetails = budgetPeriod.getBudgetLineItems();
        for (BudgetLineItem budgetLineItem : cvLineItemDetails) {
            budgetCalculationService.calculateBudgetLineItem(budgetDocument, budgetLineItem);
            budgetPeriod.setTotalDirectCost(budgetPeriod.getTotalDirectCost().add(budgetLineItem.getDirectCost()));
            budgetPeriod.setTotalIndirectCost(budgetPeriod.getTotalIndirectCost().add(budgetLineItem.getIndirectCost()));
            budgetPeriod.setTotalCost(budgetPeriod.getTotalCost().add(budgetLineItem.getDirectCost().add(budgetLineItem.getIndirectCost())));
            budgetPeriod.setUnderrecoveryAmount(budgetPeriod.getUnderrecoveryAmount().add(budgetLineItem.getUnderrecoveryAmount()));
            budgetPeriod.setCostSharingAmount(budgetPeriod.getCostSharingAmount().add(budgetLineItem.getCostSharingAmount()));
        }
        // syncBudgetTotals(budgetDocument);
    }

    /** apply to later periods. */
    public void applyToLaterPeriods(BudgetDocument budgetDocument, BudgetPeriod budgetPeriodBean, BudgetLineItem budgetDetailBean) {
        try {
            if (StringUtils.isNotBlank(budgetDetailBean.getCostElement())) {
                Integer currentPeriod = budgetPeriodBean.getBudgetPeriod();
                Integer currentLineItemNumber = budgetDetailBean.getLineItemNumber();
                int totalPeriods = budgetDocument.getBudgetPeriods().size();

                QueryList<BudgetPersonnelDetails> vecBudgetPersonnelDetails;
                BudgetLineItem currentBudgetDetailBean = budgetDetailBean, nextBudgetDetailBean, newBudgetDetailBean;
                BudgetPeriod currentBudgetPeriod = budgetPeriodBean, nextBudgetPeriodBean;
                boolean displayMessage = true;
                int maxLINum = 0;
                int maxSeqNum = 0;
                // get the max line item number > current period
                GreaterThan gtPeriod = new GreaterThan("budgetPeriod", currentPeriod);
                QueryList<BudgetLineItem> qlRemainingLineItems = new QueryList<BudgetLineItem>(currentBudgetPeriod
                        .getBudgetLineItems());
                if (qlRemainingLineItems != null && qlRemainingLineItems.size() > 0) {
                    qlRemainingLineItems.sort("lineItemNumber", false);
                    maxLINum = qlRemainingLineItems.get(0).getLineItemNumber();
                }
                maxLINum = maxLINum + 1;
                List<BudgetPeriod> budgetPeriods = budgetDocument.getBudgetPeriods();
                for (BudgetPeriod budgetPeriod : budgetPeriods) {
                    if (currentPeriod <= budgetPeriod.getBudgetPeriod()) {
                        continue;
                    }
                    BudgetDecimal lineItemCost = BudgetDecimal.ZERO;
                    Integer period = budgetPeriod.getBudgetPeriod();
                    // check if selected line item is present for this period
                    Equals eqBasedOnLINumber = new Equals("basedOnLineItem", currentLineItemNumber);
                    QueryList<BudgetLineItem> qlBudgetLineItems = new QueryList<BudgetLineItem>(budgetPeriod.getBudgetLineItems());
                    QueryList<BudgetLineItem> filteredBudgetLineItems = qlBudgetLineItems.filter(eqBasedOnLINumber);

                    Equals eqLINumber = new Equals("lineItemNumber", currentLineItemNumber);
                    QueryList<BudgetLineItem> vecCurrentPeriodDetail = new QueryList<BudgetLineItem>(currentBudgetPeriod
                            .getBudgetLineItems());
                    if (vecCurrentPeriodDetail != null || vecCurrentPeriodDetail.size() > 0) {
                        currentBudgetDetailBean = vecCurrentPeriodDetail.get(0);
                    }
                    else {
                        currentBudgetDetailBean = null;
                    }

                    // set the line item cost as current line item cost
                    lineItemCost = currentBudgetDetailBean.getLineItemCost();

                    // if next period line item is based on same line item then continue with next period.
                    if (!filteredBudgetLineItems.isEmpty()) {
                        nextBudgetDetailBean = filteredBudgetLineItems.get(0);
                        vecBudgetPersonnelDetails = new QueryList<BudgetPersonnelDetails>(nextBudgetDetailBean
                                .getBudgetPersonnelDetailsList());
                        if (nextBudgetDetailBean.getBudgetCategory().getBudgetCategoryTypeCode() == PERSONNEL_CATEGORY
                                || (vecBudgetPersonnelDetails != null && vecBudgetPersonnelDetails.size() > 0)) {
                            errorMessages.add("This line item contains personnel budget details"
                                    + " and there is already a line item on period " + period + " based on this line item. \n"
                                    + "Cannot apply the changes to later periods.");
                            return;
                        }
                        // Display this message only Once.
                        // if(displayMessage) {
                        // displayMessage = false;
                        // int selection = CoeusOptionPane.showQuestionDialog("There is already a line item on period " +
                        // period + " based on this line item. \n" +
                        // "Do you want to apply changes to existing line items on later periods.",
                        // CoeusOptionPane.OPTION_YES_NO, CoeusOptionPane.DEFAULT_YES);
                        // if(selection == CoeusOptionPane.SELECTION_NO) {
                        // return false;
                        // }
                        // }//End if - display Message

                        // Update line item cost after applying inflation.
                        // Only if the inflation falg is true, then only perform calculateInflation.
                        // otherwise don't do anything.
                        if (currentBudgetDetailBean.getApplyInRateFlag()) {
                            lineItemCost = calculateInflation(budgetDocument, currentBudgetDetailBean, nextBudgetDetailBean
                                    .getStartDate());
                        }

                        BudgetLineItem copyBudgetLineItem = (BudgetLineItem) BeanUtils.cloneBean(currentBudgetDetailBean);
                        // (BudgetLineItem)ObjectCloner.deepCopy(currentBudgetDetailBean);
                        copyBudgetLineItem.setBudgetPeriod(nextBudgetDetailBean.getBudgetPeriod());
                        copyBudgetLineItem.setLineItemNumber(nextBudgetDetailBean.getLineItemNumber());
                        copyBudgetLineItem.setBasedOnLineItem(currentBudgetDetailBean.getLineItemNumber());
                        copyBudgetLineItem.setLineItemSequence(nextBudgetDetailBean.getLineItemSequence());
                        copyBudgetLineItem.setStartDate(nextBudgetDetailBean.getStartDate());
                        copyBudgetLineItem.setEndDate(nextBudgetDetailBean.getEndDate());

                        // Check for Cal Amts
                        if (!copyBudgetLineItem.getCostElement().equals(nextBudgetDetailBean.getCostElement())) {
                            copyBudgetLineItem.getBudgetLineItemCalculatedAmounts().clear();
                            budgetCalculationService.populateCalculatedAmount(budgetDocument, copyBudgetLineItem);
                        }

                        nextBudgetDetailBean = copyBudgetLineItem;

                        nextBudgetDetailBean.setLineItemCost(lineItemCost);
                        currentLineItemNumber = nextBudgetDetailBean.getLineItemNumber();
                        continue;
                    }// End if - check for next period line item is based on same line item

                    // Get Max Squence Number if Adding
                    Equals eqperiod = new Equals("budgetPeriod", period);
                    if (!qlBudgetLineItems.isEmpty()) {
                        qlBudgetLineItems.sort("lineItemSequence", false);
                        maxSeqNum = qlBudgetLineItems.get(0).getLineItemSequence() + 1;
                    }
                    else {
                        maxSeqNum = 1; // First Entry
                    }

                    // nextBudgetPeriodBean = budgetPeriods.get(period - 1);
                    nextBudgetPeriodBean = budgetPeriod;

                    newBudgetDetailBean = (BudgetLineItem) BeanUtils.cloneBean(currentBudgetDetailBean);
                    newBudgetDetailBean.setBudgetPeriod(period);
                    newBudgetDetailBean.setLineItemSequence(maxSeqNum);
                    newBudgetDetailBean.setLineItemNumber(maxLINum);

                    currentLineItemNumber = maxLINum;

                    newBudgetDetailBean.setBasedOnLineItem(currentBudgetDetailBean.getLineItemNumber());
                    newBudgetDetailBean.setStartDate(nextBudgetPeriodBean.getStartDate());
                    newBudgetDetailBean.setEndDate(nextBudgetPeriodBean.getEndDate());


                    // Equals eqBudgetPeriod = new Equals("budgetPeriod", new Integer(period - 1));
                    // Equals eqLineItemNo = new Equals("lineItemNumber", new Integer(currentBudgetDetailBean.getLineItemNumber()));
                    // And eqBudgetPeriodAndEqLineItemNo = new And(eqBudgetPeriod, eqLineItemNo);
                    // CoeusVector vecBudgetPersonnelDetailsBean = queryEngine.getActiveData(queryKey,
                    // BudgetPersonnelDetailsBean.class,
                    // eqBudgetPeriodAndEqLineItemNo);
                    //                
                    QueryList<BudgetPersonnelDetails> budgetPersonnelDetailList = new QueryList<BudgetPersonnelDetails>(
                        currentBudgetDetailBean.getBudgetPersonnelDetailsList());

                    /**
                     * if line item contains personnel line items. then line item cost will be set to 0. correct cost will be set
                     * during calculation.
                     */
                    if (!budgetPersonnelDetailList.isEmpty()) {
                        lineItemCost = BudgetDecimal.ZERO;
                    }

                    // Apply inflation only if line item does not contain personnel line item
                    if (newBudgetDetailBean.getApplyInRateFlag()
                            && (budgetPersonnelDetailList == null || budgetPersonnelDetailList.size() == 0)) {
                        lineItemCost = calculateInflation(budgetDocument, currentBudgetDetailBean, newBudgetDetailBean
                                .getStartDate());
                    }// Apply Inflation check ends here

                    newBudgetDetailBean.setLineItemCost(lineItemCost);

                    // Copy Budget Detail Cal Amts Beans
                    List<BudgetLineItemCalculatedAmount> budgetLineItemCalcAmounts = (List<BudgetLineItemCalculatedAmount>) currentBudgetDetailBean
                            .getBudgetLineItemCalculatedAmounts();
                    newBudgetDetailBean.getBudgetLineItemCalculatedAmounts().clear();
                    for (BudgetLineItemCalculatedAmount budgetLineItemCalculatedAmount : budgetLineItemCalcAmounts) {
                        BudgetLineItemCalculatedAmount newBudgetLineItemCalculatedAmount = (BudgetLineItemCalculatedAmount) BeanUtils
                                .cloneBean(budgetLineItemCalculatedAmount);
                        newBudgetLineItemCalculatedAmount.setBudgetPeriod(nextBudgetPeriodBean.getBudgetPeriod());
                        newBudgetLineItemCalculatedAmount.setLineItemNumber(newBudgetDetailBean.getLineItemNumber());
                        newBudgetDetailBean.getBudgetLineItemCalculatedAmounts().add(newBudgetLineItemCalculatedAmount);
                    }

                    // Copy Personnel Detail Beans
                    newBudgetDetailBean.getBudgetPersonnelDetailsList().clear();
                    for (BudgetPersonnelDetails budgetPersonnelDetails : budgetPersonnelDetailList) {
                        BudgetPersonnelDetails newBudgetPersonnelDetailsBean = (BudgetPersonnelDetails) BeanUtils
                                .cloneBean(budgetPersonnelDetails);
                        newBudgetPersonnelDetailsBean.setBudgetPeriod(nextBudgetPeriodBean.getBudgetPeriod());
                        newBudgetPersonnelDetailsBean.setLineItemNumber(newBudgetDetailBean.getLineItemNumber());
                        newBudgetDetailBean.getBudgetPersonnelDetailsList().add(newBudgetPersonnelDetailsBean);
                        Date pliStartDate = newBudgetPersonnelDetailsBean.getStartDate();
                        Calendar calendar = dateTimeService.getCalendar(pliStartDate);
                        calendar.add(Calendar.YEAR, 1);
                        pliStartDate = calendar.getTime();

                        // since start date for line item will be same as start date of period.
                        // while generating periods we can take period start date.
                        if (nextBudgetPeriodBean.getStartDate().compareTo(pliStartDate) <= 0) {
                            newBudgetPersonnelDetailsBean.setStartDate(new java.sql.Date(pliStartDate.getTime()));
                            // set End Date
                            Date pliEndDate = newBudgetPersonnelDetailsBean.getEndDate();
                            Calendar endCalendar = dateTimeService.getCalendar(pliEndDate);
                            calendar.add(Calendar.YEAR, 1);
                            pliEndDate = calendar.getTime();
                            if (nextBudgetPeriodBean.getEndDate().compareTo(pliEndDate) < 0) {
                                pliEndDate = nextBudgetPeriodBean.getEndDate();
                            }
                            newBudgetPersonnelDetailsBean.setEndDate(new java.sql.Date(pliEndDate.getTime()));
                            List<BudgetPersonnelCalculatedAmount> budgetPersnnelCalAmounts = budgetPersonnelDetails
                                    .getBudgetLineItemCalculatedAmounts();
                            newBudgetPersonnelDetailsBean.getBudgetLineItemCalculatedAmounts().clear();
                            for (BudgetPersonnelCalculatedAmount budgetPersonnelCalculatedAmount : budgetPersnnelCalAmounts) {
                                BudgetPersonnelCalculatedAmount newBudgetPersonnelCalAmountsBean = (BudgetPersonnelCalculatedAmount) BeanUtils
                                        .cloneBean(budgetPersonnelCalculatedAmount);
                                newBudgetPersonnelCalAmountsBean.setBudgetPeriod(nextBudgetPeriodBean.getBudgetPeriod());
                                newBudgetPersonnelCalAmountsBean.setLineItemNumber(newBudgetDetailBean.getLineItemNumber());
                                newBudgetPersonnelDetailsBean.getBudgetLineItemCalculatedAmounts().add(
                                        newBudgetPersonnelCalAmountsBean);
                            }
                        }
                    }// End For Copy Personnel Detail Beans
                }// End For - Periods
            }
            return;
        }
        catch (Exception ex) {
            LOG.error(ex);
            errorMessages.add(ex.getMessage());
            return;
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

    /**
     * Cost limit for this period is set to 0. Cannot sync a line item cost to zero limit.
     */
    private static final String CANNOT_SYNC_TO_ZERO_LIMIT = "Cost limit for this period is set to 0. Cannot sync a line item cost to zero limit.";
    /**
     * Cost limit and total cost for this period is already in sync.
     */
    private static final String TOTAL_COST_ALREADY_IN_SYNC = "Cost limit and total cost for this period is already in sync.";

    /**
     * Insufficient amount on the line item to sync with cost limit.
     */
    private static final String INSUFFICIENT_AMOUNT_TO_SYNC = "Insufficient amount on the line item to sync with cost limit.";

    public void syncToPeriodCostLimit(BudgetDocument budgetDocument, BudgetPeriod budgetPeriodBean, BudgetLineItem budgetDetailBean) {
        // //If cost element is null disp msg "Cannot perform this operation if cost element is not present."
        // if(budgetPeriodTableModel.getValueAt(selectedRow, COST_ELEMENT_COLUMN) == null ||
        // budgetPeriodTableModel.getValueAt(selectedRow, COST_ELEMENT_COLUMN).equals(EMPTY_STRING)) {
        // CoeusOptionPane.showInfoDialog(coeusMessageResources.parseMessageKey(
        // COST_ELEMENT_NOT_PRESENT));
        // return ;
        // }
        //        

        Equals eqBudgetPeriod = new Equals("budgetPeriod", new Integer(budgetDetailBean.getBudgetPeriod()));
        Equals eqLINumber = new Equals("lineItemNumber", new Integer(budgetDetailBean.getLineItemNumber()));
        And eqBudgetPeriodAndEqLINumber = new And(eqBudgetPeriod, eqLINumber);

        if (budgetDetailBean.getBudgetCategory().getBudgetCategoryTypeCode().equals(PERSONNEL_CATEGORY)) {
            errorMessages.add(CANNOT_PERFORM_THIS_OPERATION_ON_PERSONNEL_LINE_ITEM);
            return;
        }

        // if cost_limit is 0 disp msg "Cost limit for this period is set to 0. Cannot sync a line item cost to zero limit."
        if (budgetPeriodBean.getTotalCostLimit().equals(BudgetDecimal.ZERO)) {
            errorMessages.add(CANNOT_SYNC_TO_ZERO_LIMIT);
            return;
        }
        calculate(budgetDocument, budgetPeriodBean);

        // If total_cost equals total_cost_limit, disp msg "Cost limit and total cost for this period is already in sync."
        BudgetDecimal periodTotal = budgetPeriodBean.getTotalCost();
        BudgetDecimal costLimit = budgetPeriodBean.getTotalCostLimit();

        if (periodTotal == costLimit) {
            errorMessages.add(TOTAL_COST_ALREADY_IN_SYNC);
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
            errorMessages.add(INSUFFICIENT_AMOUNT_TO_SYNC);
            // CoeusOptionPane.showErrorDialog(INSUFFICIENT_AMOUNT_TO_SYNC);
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

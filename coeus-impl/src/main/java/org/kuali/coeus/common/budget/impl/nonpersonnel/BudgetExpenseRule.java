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
package org.kuali.coeus.common.budget.impl.nonpersonnel;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.query.QueryList;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetDocument;
import org.kuali.coeus.common.budget.framework.core.BudgetSaveEvent;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetFormulatedCostDetail;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.impl.core.SaveBudgetEvent;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRule;
import org.kuali.coeus.common.framework.ruleengine.KcEventMethod;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.math.BigDecimal;
import java.util.List;

@KcBusinessRule("budgetExpenseRule")
public class BudgetExpenseRule {
    private static final double MAX_BUDGET_DECIMAL_VALUE = 9999999999.00;
    private static final String PERSONNEL_CATEGORY = "P";

    public BudgetExpenseRule() {
    }

    @KcEventMethod
    public boolean processCheckExistBudgetPersonnelDetailsBusinessRules(DeleteBudgetLineItemEvent event) {
        boolean valid = true;

        MessageMap errorMap = GlobalVariables.getMessageMap();
        if (CollectionUtils.isNotEmpty(event.getBudgetLineItem().getBudgetPersonnelDetailsList())) {
            // just try to make sure key is on budget personnel tab
            errorMap.putError(event.getErrorPath() + ".costElement", KeyConstants.ERROR_DELETE_LINE_ITEM);
            valid = false;
        }

        return valid;
    }

    @KcEventMethod
    public boolean processApplyToLaterPeriodsWithPersonnelDetails(ApplyToPeriodsBudgetEvent event) {
        MessageMap errorMap = GlobalVariables.getMessageMap();
        Budget budget = event.getBudget();
        BudgetLineItem budgetLineItem = event.getBudgetLineItem();
       
        List<BudgetPeriod> budgetPeriods = budget.getBudgetPeriods();
        BudgetLineItem prevBudgetLineItem = budgetLineItem;
        for (BudgetPeriod budgetPeriod : budgetPeriods) {
            if (budgetPeriod.getBudgetPeriod() <= event.getBudgetPeriod().getBudgetPeriod())
                continue;
            QueryList<BudgetLineItem> currentBudgetPeriodLineItems = new QueryList<BudgetLineItem>(budgetPeriod
                    .getBudgetLineItems());
            for (BudgetLineItem budgetLineItemToBeApplied : currentBudgetPeriodLineItems) {
                if (prevBudgetLineItem.getLineItemNumber().equals(budgetLineItemToBeApplied.getBasedOnLineItem())) {
                    if (budgetLineItemToBeApplied.getBudgetCategory().getBudgetCategoryTypeCode().equals(PERSONNEL_CATEGORY)
                            && (!budgetLineItemToBeApplied.getBudgetPersonnelDetailsList().isEmpty() || !prevBudgetLineItem
                                    .getBudgetPersonnelDetailsList().isEmpty())) {
                        errorMap.putError(event.getErrorPath() + ".costElement",
                                KeyConstants.ERROR_APPLY_TO_LATER_PERIODS, budgetLineItemToBeApplied.getBudgetPeriod().toString());
                        return false;
                    }
                }
                else if (StringUtils.equals(budgetLineItem.getBudgetCategory().getBudgetCategoryTypeCode(),
                        PERSONNEL_CATEGORY)) {
                    // Additional Check for Personnel Source Line Item
                    if (StringUtils.equals(budgetLineItem.getCostElement(), budgetLineItemToBeApplied.getCostElement())
                            && StringUtils.equals(budgetLineItem.getGroupName(), budgetLineItemToBeApplied.getGroupName())) {
                        errorMap.putError(event.getErrorPath() + ".costElement",
                                KeyConstants.ERROR_PERSONNELLINEITEM_APPLY_TO_LATER_PERIODS, budgetLineItemToBeApplied
                                        .getBudgetPeriod().toString());
                        return false;
                    }

                }

            }
        }


        return true;
    }

    /**
     * This method is checks line item start and end dates for validity and proper ordering in the entire budget document
     * 
     * @param budgetDocument the BudgetDocument
     * @return true if the dates are valid, false otherwise
     */
    @KcEventMethod
    public boolean processCheckLineItemDates(SaveBudgetEvent event) {
        boolean valid = true;
        List<BudgetPeriod> budgetPeriods = event.getBudget().getBudgetPeriods();
        int numLineItems = 0;
        for (BudgetPeriod budgetPeriod : budgetPeriods) {
            numLineItems = budgetPeriod.getBudgetLineItems().size();
            for (int i = 0; i < numLineItems; i++) {
                valid &= processCheckLineItemDates(budgetPeriod, budgetPeriod.getBudgetLineItem(i), 
                		"document.budgetPeriod[" + (budgetPeriod.getBudgetPeriod() - 1) + "].budgetLineItem[" + i + "]");
            }
        }
        return valid;
    }

    @KcEventMethod
    public boolean processCheckLineItemDates(ApplyToPeriodsBudgetEvent event) {
    	return processCheckLineItemDates(event.getBudgetPeriod(), event.getBudgetLineItem(), event.getErrorPath());
    }

    /**
     * This method is checks individual line item start and end dates for validity and proper ordering.
     * 
     * @param currentBudgetPeriod the period containing the line item
     * @param selectedLineItem the number of the line item
     * @return true if the dates are valid, false otherwise
     */
    protected boolean processCheckLineItemDates(BudgetPeriod currentBudgetPeriod, BudgetLineItem budgetLineItem, String errorPath) {
        boolean valid = true;
        MessageMap errorMap = GlobalVariables.getMessageMap();

        if (budgetLineItem.getEndDate() == null) {
            errorMap.putError(errorPath + ".endDate", KeyConstants.ERROR_REQUIRED, "End Date");
            valid = false;
        }

        if (budgetLineItem.getStartDate() == null) {
            errorMap.putError(errorPath + ".startDate", KeyConstants.ERROR_REQUIRED, "Start Date");
            valid = false;
        }

        if (!valid)
            return valid;

        if (budgetLineItem.getEndDate().compareTo(budgetLineItem.getStartDate()) < 0) {
            errorMap.putError(errorPath + ".endDate", KeyConstants.ERROR_LINE_ITEM_DATES);
            valid = false;
        }

        if (currentBudgetPeriod.getEndDate().compareTo(budgetLineItem.getEndDate()) < 0) {
            errorMap.putError(errorPath = ".endDate", KeyConstants.ERROR_LINE_ITEM_END_DATE, new String[] { "can not be after",
                    "end date" });
            valid = false;
        }
        if (currentBudgetPeriod.getStartDate().compareTo(budgetLineItem.getEndDate()) > 0) {
            errorMap.putError(errorPath + ".endDate", KeyConstants.ERROR_LINE_ITEM_END_DATE, new String[] { "can not be before",
                    "start date" });
            valid = false;
        }
        if (currentBudgetPeriod.getStartDate().compareTo(budgetLineItem.getStartDate()) > 0) {
            errorMap.putError(errorPath + ".startDate", KeyConstants.ERROR_LINE_ITEM_START_DATE, new String[] {
                    "can not be before", "start date" });
            valid = false;
        }
        if (currentBudgetPeriod.getEndDate().compareTo(budgetLineItem.getStartDate()) < 0) {
            errorMap.putError(errorPath = ".startDate", KeyConstants.ERROR_LINE_ITEM_START_DATE, new String[] { "can not be after",
                    "end date" });
            valid = false;
        }

        return valid;
    }

    @KcEventMethod
    public boolean processBudgetFormulatedCostValidations(AddFormulatedCostBudgetEvent event) {
    	return processBudgetFormulatedCostValidations(event.getFormulatedCostDetail(), event.getErrorPath());
    }
    
    @KcEventMethod
    public boolean processBudgetFormulatedCostValidations(BudgetSaveEvent event) {
    	boolean result = true;
    	int budgetPeriodIdx = 0;
    	for (BudgetPeriod budgetPeriod : event.getBudget().getBudgetPeriods()) {
    		int lineItemIdx = 0;
	        for(BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
	        	String errorPath = "document.budget.budgetPeriod[" + budgetPeriodIdx + "].budgetLineItem["+ lineItemIdx + "].";
	        	int formulatedCostIdx = 0;
	        	for (BudgetFormulatedCostDetail budgetFormulatedCost : budgetLineItem.getBudgetFormulatedCosts()) {
		            result &= processBudgetFormulatedCostValidations(budgetFormulatedCost, 
		            		errorPath + "budgetFormulatedCosts["+ formulatedCostIdx++ +"]");
	        	}
	            lineItemIdx++;
	        }
	        budgetPeriodIdx++;
    	}
    	return result;
    }
    
    protected boolean processBudgetFormulatedCostValidations(BudgetFormulatedCostDetail budgetFormulatedCost, String errorKey) {
        boolean valid = true;
        MessageMap errorMap = GlobalVariables.getMessageMap();

        BigDecimal unitCost = budgetFormulatedCost.getUnitCost().bigDecimalValue();
        BigDecimal count = new ScaleTwoDecimal(budgetFormulatedCost.getCount()).bigDecimalValue();
        BigDecimal frequency = new ScaleTwoDecimal(budgetFormulatedCost.getFrequency()).bigDecimalValue();
        BigDecimal calculatedExpense = unitCost.multiply(count).multiply(frequency);
        if(new ScaleTwoDecimal(unitCost).isGreaterThan(new ScaleTwoDecimal(MAX_BUDGET_DECIMAL_VALUE))){
            valid = false;
            errorMap.putError(errorKey+".unitCost", KeyConstants.ERROR_FORMULATED_UNIT_COST);
            
        }
        if(new ScaleTwoDecimal(calculatedExpense).isGreaterThan(new ScaleTwoDecimal(MAX_BUDGET_DECIMAL_VALUE))){
            valid = false;
            errorMap.putError(errorKey+".calculatedExpenses", KeyConstants.ERROR_FORMULATED_CALCULATED_EXPENSES);
            
        }
        return valid;
    }
}

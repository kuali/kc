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
package org.kuali.coeus.common.budget.impl.nonpersonnel;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.common.budget.framework.query.QueryList;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetConstants;
import org.kuali.coeus.common.budget.framework.core.BudgetSaveEvent;
import org.kuali.coeus.common.budget.framework.core.SaveBudgetEvent;
import org.kuali.coeus.common.budget.framework.nonpersonnel.ApplyToPeriodsBudgetEvent;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetFormulatedCostDetail;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRule;
import org.kuali.coeus.common.framework.ruleengine.KcEventMethod;
import org.kuali.coeus.common.framework.ruleengine.KcEventResult;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.MessageMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.math.BigDecimal;
import java.util.List;

@KcBusinessRule("budgetExpenseRule")
public class BudgetExpenseRule {
    private static final double MAX_BUDGET_DECIMAL_VALUE = 9999999999.00;
    private static final String PERSONNEL_CATEGORY = "P";
    protected static final String BUDGET_NON_PERSONNEL_COST_DETAILS_ID = "PropBudget-NonPersonnelCosts-LineItemDetails";
    protected static final String BUDGET_PERSONNEL_COST_DETAILS_ID = "PropBudget-AssignPersonnelToPeriodsPage-PersonnelDetails";

    
    @Autowired
	@Qualifier("globalVariableService")
	private GlobalVariableService globalVariableService;

    public BudgetExpenseRule() {
    }

    @KcEventMethod
    public boolean processCheckExistBudgetPersonnelDetailsBusinessRules(DeleteBudgetLineItemEvent event) {
        boolean valid = true;

        MessageMap errorMap = getGlobalVariableService().getMessageMap();
        if (CollectionUtils.isNotEmpty(event.getBudgetLineItem().getBudgetPersonnelDetailsList())) {
            // just try to make sure key is on budget personnel tab
            errorMap.putError(event.getErrorPath() + ".costElement", KeyConstants.ERROR_DELETE_LINE_ITEM);
            valid = false;
        }

        return valid;
    }

    @KcEventMethod
    public boolean processApplyToLaterPeriodsWithPersonnelDetails(ApplyToPeriodsBudgetEvent event) {
        MessageMap errorMap = getGlobalVariableService().getMessageMap();
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
        MessageMap errorMap = getGlobalVariableService().getMessageMap();

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
        MessageMap errorMap = getGlobalVariableService().getMessageMap();

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
    
    @KcEventMethod
    public KcEventResult processBudgetLineItemExpenseRules(BudgetExpensesRuleEvent event) {
    	KcEventResult result = new KcEventResult();
    	Budget budget = event.getBudget();
    	
        if ( budget.getTotalCostLimit().isGreaterThan(new ScaleTwoDecimal(0)) &&
        		budget.getTotalCost().isGreaterThan(budget.getTotalCostLimit()) ) {
            result.getMessageMap().putWarning(event.getErrorPath(), KeyConstants.WARNING_TOTAL_COST_LIMIT_EXCEEDED); 
        }
        
        if ( budget.getTotalDirectCostLimit().isGreaterThan(new ScaleTwoDecimal(0)) &&
        		budget.getTotalDirectCost().isGreaterThan(budget.getTotalDirectCostLimit()) ) {
            result.getMessageMap().putWarning(event.getErrorPath(), KeyConstants.WARNING_TOTAL_DIRECT_COST_LIMIT_EXCEEDED); 
        }
        
        String errorPath = BUDGET_PERSONNEL_COST_DETAILS_ID;
        if(event.getErrorPath().equalsIgnoreCase(BudgetConstants.BudgetAuditRules.NON_PERSONNEL_COSTS.getPageId())) {
        	errorPath = BUDGET_NON_PERSONNEL_COST_DETAILS_ID;
        }
        
        for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
            if(budgetPeriod.getTotalCostLimit().isGreaterThan(new ScaleTwoDecimal(0)) && budgetPeriod.getTotalCost().isGreaterThan(budgetPeriod.getTotalCostLimit())){
                result.getMessageMap().putWarning(errorPath + "_" + budgetPeriod.getBudgetPeriod(), KeyConstants.WARNING_PERIOD_COST_LIMIT_EXCEEDED, new String[]{budgetPeriod.getBudgetPeriod().toString()}); 
            }
            if(budgetPeriod.getDirectCostLimit().isGreaterThan(new ScaleTwoDecimal(0)) && budgetPeriod.getTotalDirectCost().isGreaterThan(budgetPeriod.getDirectCostLimit())){
                result.getMessageMap().putWarning(errorPath + "_" + budgetPeriod.getBudgetPeriod(), KeyConstants.WARNING_PERIOD_DIRECT_COST_LIMIT_EXCEEDED, new String[]{budgetPeriod.getBudgetPeriod().toString()}); 
            }
        }
        return result;
    }

	public GlobalVariableService getGlobalVariableService() {
		return globalVariableService;
	}

	public void setGlobalVariableService(GlobalVariableService globalVariableService) {
		this.globalVariableService = globalVariableService;
	}
    
}

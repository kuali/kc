package org.kuali.coeus.common.budget.impl.nonpersonnel;

import org.kuali.coeus.common.budget.framework.calculator.BudgetCalculationService;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetDirectCostLimitEvent;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetPeriodCostLimitEvent;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRule;
import org.kuali.coeus.common.framework.ruleengine.KcEventMethod;
import org.kuali.coeus.common.framework.ruleengine.KcEventResult;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.kra.infrastructure.KeyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@KcBusinessRule("budgetCostLimitRule")
public class BudgetCostLimitRule {

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    @Autowired
    @Qualifier("budgetCalculationService")
    private BudgetCalculationService budgetCalculationService;
    
    @KcEventMethod
    public KcEventResult processBudgetPeriodCostLimit(BudgetPeriodCostLimitEvent event) {
    	KcEventResult result = new KcEventResult();
    	result.getMessageMap().addToErrorPath(event.getErrorPath());
    	checkPeriodSyncToLimitErrors(event, result);
    	checkPeriodTotalCost(event, result);
    	result.getMessageMap().removeFromErrorPath(event.getErrorPath());
        return result;
    }
    
    @KcEventMethod
    public KcEventResult processBudgetDriectCostLimit(BudgetDirectCostLimitEvent event) {
    	KcEventResult result = new KcEventResult();
    	result.getMessageMap().addToErrorPath(event.getErrorPath());
    	checkDirectSyncToLimitErrors(event, result);
    	checkTotalDirectCost(event, result);
    	result.getMessageMap().removeFromErrorPath(event.getErrorPath());
        return result;
    }
    
    protected void checkPeriodSyncToLimitErrors(BudgetPeriodCostLimitEvent event, KcEventResult result) {
        ScaleTwoDecimal costLimit = event.getBudgetPeriod().getTotalCostLimit();
    	checkSyncToLimitErrors(event.getBudget(), event.getBudgetPeriod(), event.getBudgetLineItem(), result, costLimit);
    }
    
    protected void checkDirectSyncToLimitErrors(BudgetDirectCostLimitEvent event, KcEventResult result) {
        ScaleTwoDecimal directCostLimit = event.getBudgetPeriod().getDirectCostLimit();
    	checkSyncToLimitErrors(event.getBudget(), event.getBudgetPeriod(), event.getBudgetLineItem(), result, directCostLimit);
    }
    
    protected void checkSyncToLimitErrors(Budget budget, BudgetPeriod budgetPeriod, BudgetLineItem budgetLineItem, KcEventResult result, ScaleTwoDecimal costLimit) {
        String personnelCategoryTypeCode = getBudgetCalculationService().getPersonnelBudgetCategoryTypeCode();
        if (budgetLineItem.getBudgetCategory().getBudgetCategoryTypeCode().equals(personnelCategoryTypeCode) && 
              !budgetLineItem.getBudgetPersonnelDetailsList().isEmpty()) {
        	result.getMessageMap().putError("lineItemCost", KeyConstants.PERSONNEL_LINE_ITEM_EXISTS);
            result.setSuccess(false);
        }

        if (isProposalBudget(budget) && costLimit.equals(ScaleTwoDecimal.ZERO)) {
        	result.getMessageMap().putError("lineItemCost", KeyConstants.CANNOT_SYNC_TO_ZERO_LIMIT);
            result.setSuccess(false);
        }
    }

    protected void checkPeriodTotalCost(BudgetPeriodCostLimitEvent event, KcEventResult result) {
    	Budget budget = event.getBudget();
    	BudgetPeriod budgetPeriod = event.getBudgetPeriod();
    	getBudgetCalculationService().calculateBudgetPeriod(budget, budgetPeriod);
        ScaleTwoDecimal periodTotal = budgetPeriod.getTotalCost();
        ScaleTwoDecimal costLimit = budgetPeriod.getTotalCostLimit();
        if (periodTotal.equals(costLimit)) {
        	result.getMessageMap().putError("lineItemCost", KeyConstants.TOTAL_COST_ALREADY_IN_SYNC);
            result.setSuccess(false);
        }
    }
    

    protected void checkTotalDirectCost(BudgetDirectCostLimitEvent event, KcEventResult result) {
    	Budget budget = event.getBudget();
    	BudgetPeriod budgetPeriod = event.getBudgetPeriod();
        ScaleTwoDecimal directCostLimit = budgetPeriod.getDirectCostLimit();
    	getBudgetCalculationService().calculateBudgetPeriod(budget, budgetPeriod);
        ScaleTwoDecimal periodDirectTotal = budgetPeriod.getTotalDirectCost();
        directCostLimit = budgetPeriod.getDirectCostLimit();
        if (periodDirectTotal.equals(directCostLimit)) {
        	result.getMessageMap().putError("lineItemCost", KeyConstants.TOTAL_DIRECT_COST_ALREADY_IN_SYNC);
            result.setSuccess(false);
        }
    	
    }
    
    protected boolean isProposalBudget(Budget budget){
        return budget.isProposalBudget();
    }
    
	public GlobalVariableService getGlobalVariableService() {
		return globalVariableService;
	}

	public void setGlobalVariableService(GlobalVariableService globalVariableService) {
		this.globalVariableService = globalVariableService;
	}

	public BudgetCalculationService getBudgetCalculationService() {
		return budgetCalculationService;
	}

	public void setBudgetCalculationService(
			BudgetCalculationService budgetCalculationService) {
		this.budgetCalculationService = budgetCalculationService;
	}
	
 }

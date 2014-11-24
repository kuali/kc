package org.kuali.coeus.common.budget.framework.nonpersonnel;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;

public class BudgetPeriodCostLimitEvent extends BudgetCostLimitEvent {
	
	public BudgetPeriodCostLimitEvent(Budget budget, BudgetPeriod budgetPeriod, BudgetLineItem budgetLineItem, String errorPath) {
		super(budget, budgetPeriod, budgetLineItem, errorPath);
	}
}

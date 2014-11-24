package org.kuali.coeus.common.budget.framework.nonpersonnel;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;

public class BudgetDirectCostLimitEvent extends BudgetCostLimitEvent {
	
	public BudgetDirectCostLimitEvent(Budget budget, BudgetPeriod budgetPeriod, BudgetLineItem budgetLineItem, String errorPath) {
		super(budget, budgetPeriod, budgetLineItem, errorPath);
	}
}

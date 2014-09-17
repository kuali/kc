package org.kuali.coeus.common.budget.framework.nonpersonnel;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;

public class ApplyToPeriodsBudgetEvent extends BudgetLineItemEventBase {

	private BudgetPeriod budgetPeriod;
	
	public ApplyToPeriodsBudgetEvent(Budget budget, String errorPath, BudgetLineItem budgetLineItem, BudgetPeriod budgetPeriod) {
		super(budget, errorPath, budgetLineItem);
		this.budgetPeriod = budgetPeriod;
	}

	public BudgetPeriod getBudgetPeriod() {
		return budgetPeriod;
	}

	public void setBudgetPeriod(BudgetPeriod budgetPeriod) {
		this.budgetPeriod = budgetPeriod;
	}
}

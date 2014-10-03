package org.kuali.coeus.propdev.impl.budget.subaward;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetEventBase;

public class BudgetSubAwardsEvent extends BudgetEventBase {

	private BudgetSubAwards budgetSubAwards;
	
	public BudgetSubAwardsEvent(BudgetSubAwards budgetSubAwards, Budget budget, String errorPath) {
		super(budget, errorPath);
		this.budgetSubAwards = budgetSubAwards;
	}

	public BudgetSubAwards getBudgetSubAwards() {
		return budgetSubAwards;
	}

	public void setBudgetSubAwards(BudgetSubAwards budgetSubAwards) {
		this.budgetSubAwards = budgetSubAwards;
	}
}

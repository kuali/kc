package org.kuali.coeus.propdev.impl.budget.subaward;

public class BudgetSubAwardsEvent {

	private BudgetSubAwards budgetSubAwards;
	
	public BudgetSubAwardsEvent(BudgetSubAwards budgetSubAwards) {
		this.budgetSubAwards = budgetSubAwards;
	}

	public BudgetSubAwards getBudgetSubAwards() {
		return budgetSubAwards;
	}

	public void setBudgetSubAwards(BudgetSubAwards budgetSubAwards) {
		this.budgetSubAwards = budgetSubAwards;
	}
}

package org.kuali.kra.budget.printing.util;

import org.kuali.kra.budget.BudgetDecimal;

public class BudgetDataPeriodVO {
	private int budgetPeriodId;
	private BudgetDecimal periodCost;

	public int getBudgetPeriodId() {
		return budgetPeriodId;
	}

	public void setBudgetPeriodId(int budgetPeriofId) {
		this.budgetPeriodId = budgetPeriofId;
	}

	public BudgetDecimal getPeriodCost() {
		return periodCost;
	}

	public void setPeriodCost(BudgetDecimal periodCost) {
		this.periodCost = periodCost;
	}
}

package org.kuali.coeus.common.budget.impl.print;

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

public class BudgetDataPeriodVO {
	private int budgetPeriodId;
	private ScaleTwoDecimal periodCost;

	public int getBudgetPeriodId() {
		return budgetPeriodId;
	}

	public void setBudgetPeriodId(int budgetPeriofId) {
		this.budgetPeriodId = budgetPeriofId;
	}

	public ScaleTwoDecimal getPeriodCost() {
		return periodCost;
	}

	public void setPeriodCost(ScaleTwoDecimal periodCost) {
		this.periodCost = periodCost;
	}
}

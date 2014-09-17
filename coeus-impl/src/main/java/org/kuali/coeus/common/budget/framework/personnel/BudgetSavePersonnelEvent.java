package org.kuali.coeus.common.budget.framework.personnel;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetSaveEvent;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;

public class BudgetSavePersonnelEvent extends BudgetSaveEvent {

	private BudgetPeriod currentBudgetPeriod;
	
	public BudgetSavePersonnelEvent(Budget budget, BudgetPeriod currentBudgetPeriod) {
		super(budget);
		this.currentBudgetPeriod = currentBudgetPeriod;
	}

	public BudgetPeriod getCurrentBudgetPeriod() {
		return currentBudgetPeriod;
	}

	public void setCurrentBudgetPeriod(BudgetPeriod currentBudgetPeriod) {
		this.currentBudgetPeriod = currentBudgetPeriod;
	}

}

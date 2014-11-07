package org.kuali.coeus.common.budget.framework.personnel;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItemEventBase;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;

public class BudgetPersonnelDetailsLineItemEvent extends BudgetLineItemEventBase {
	private BudgetPeriod budgetPeriod;

	public BudgetPersonnelDetailsLineItemEvent(Budget budget, String errorPath,
			BudgetLineItem budgetLineItem) {
		super(budget, errorPath, budgetLineItem);
	}

	public BudgetPersonnelDetailsLineItemEvent(Budget budget, BudgetPeriod budgetPeriod, String errorPath, BudgetLineItem budgetLineItem) {
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

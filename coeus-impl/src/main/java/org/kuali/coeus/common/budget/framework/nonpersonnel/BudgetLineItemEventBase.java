package org.kuali.coeus.common.budget.framework.nonpersonnel;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetEventBase;

public class BudgetLineItemEventBase extends BudgetEventBase {

	private BudgetLineItem budgetLineItem;
	
	public BudgetLineItemEventBase(Budget budget, String errorPath, BudgetLineItem budgetLineItem) {
		super(budget, errorPath);
		this.budgetLineItem = budgetLineItem;
	}

	public BudgetLineItem getBudgetLineItem() {
		return budgetLineItem;
	}

	public void setBudgetLineItem(BudgetLineItem budgetLineItem) {
		this.budgetLineItem = budgetLineItem;
	}

}

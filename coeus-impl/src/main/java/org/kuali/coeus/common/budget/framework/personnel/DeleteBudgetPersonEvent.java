package org.kuali.coeus.common.budget.framework.personnel;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetEventBase;

public class DeleteBudgetPersonEvent extends BudgetEventBase {

	private BudgetPerson budgetPerson;
	
	public DeleteBudgetPersonEvent(Budget budget, BudgetPerson budgetPerson, String errorPath) {
		super(budget, errorPath);
		this.budgetPerson = budgetPerson;
	}

	public BudgetPerson getBudgetPerson() {
		return budgetPerson;
	}

	public void setBudgetPerson(BudgetPerson budgetPerson) {
		this.budgetPerson = budgetPerson;
	}
}

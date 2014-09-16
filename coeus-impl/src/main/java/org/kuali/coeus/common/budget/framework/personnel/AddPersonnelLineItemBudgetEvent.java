package org.kuali.coeus.common.budget.framework.personnel;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.impl.nonpersonnel.BudgetLineItemEventBase;

public class AddPersonnelLineItemBudgetEvent extends BudgetLineItemEventBase {

	public AddPersonnelLineItemBudgetEvent(Budget budget, String errorPath,
			BudgetLineItem budgetLineItem) {
		super(budget, errorPath, budgetLineItem);
	}

}

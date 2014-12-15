package org.kuali.coeus.common.budget.framework.personnel;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItemEventBase;

public class AddSummaryPersonnelLineItemBudgetEvent extends BudgetLineItemEventBase {

	public AddSummaryPersonnelLineItemBudgetEvent(Budget budget, String errorPath,
												  BudgetLineItem budgetLineItem) {
		super(budget, errorPath, budgetLineItem);
	}

}

package org.kuali.coeus.common.budget.impl.nonpersonnel;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetEventBase;

public class BudgetExpensesRuleEvent extends BudgetEventBase {

	public BudgetExpensesRuleEvent(Budget budget, String errorPath) {
		super(budget, errorPath);
	}

}

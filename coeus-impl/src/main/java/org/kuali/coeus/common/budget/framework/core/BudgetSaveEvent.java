package org.kuali.coeus.common.budget.framework.core;

import org.kuali.coeus.common.budget.impl.core.BudgetEventBase;

public class BudgetSaveEvent extends BudgetEventBase {

	public BudgetSaveEvent(Budget budget) {
		super(budget);
	}
}

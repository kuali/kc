package org.kuali.coeus.common.budget.impl.core;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.framework.ruleengine.KcEventBase;

public class BudgetEventBase extends KcEventBase {

	private Budget budget;
	
	public BudgetEventBase(Budget budget, String eventName) {
		super(eventName);
		this.budget = budget;
	}

	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}
}

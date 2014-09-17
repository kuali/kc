package org.kuali.coeus.common.budget.framework.core;

import org.kuali.coeus.common.framework.ruleengine.KcEventBase;

public class BudgetAuditEvent extends KcEventBase {

	public static final String EVENT_NAME = "KC-B:budgetAuditEvent";
	
	private Budget budget;
	
	public BudgetAuditEvent(Budget budget) {
		super(EVENT_NAME);
		this.budget = budget;
	}

	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}
}

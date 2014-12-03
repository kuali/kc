package org.kuali.coeus.common.budget.framework.core;

import org.kuali.coeus.common.framework.ruleengine.KcEventBase;

public class BudgetAuditRuleEvent extends KcEventBase {

	public static final String EVENT_NAME = "KC-B:budgetAuditRuleEvent";
	
	private Budget budget;
	
	public BudgetAuditRuleEvent(Budget budget) {
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

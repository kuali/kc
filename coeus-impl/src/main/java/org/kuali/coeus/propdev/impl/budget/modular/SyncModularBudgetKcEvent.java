package org.kuali.coeus.propdev.impl.budget.modular;

import org.kuali.coeus.common.framework.ruleengine.KcEvent;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;

public class SyncModularBudgetKcEvent extends KcEvent {

	public static final String RULE_NAME = "syncModularBudget";
	private ProposalDevelopmentBudgetExt budget;
	
	public SyncModularBudgetKcEvent(ProposalDevelopmentBudgetExt budget) {
		super(RULE_NAME);
		this.budget = budget;
	}

	public ProposalDevelopmentBudgetExt getBudget() {
		return budget;
	}

	public void setBudget(ProposalDevelopmentBudgetExt budget) {
		this.budget = budget;
	}

}

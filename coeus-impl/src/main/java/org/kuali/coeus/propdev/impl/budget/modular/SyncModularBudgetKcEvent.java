package org.kuali.coeus.propdev.impl.budget.modular;

import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;

public class SyncModularBudgetKcEvent {

	private ProposalDevelopmentBudgetExt budget;
	
	public SyncModularBudgetKcEvent(ProposalDevelopmentBudgetExt budget) {
		this.budget = budget;
	}

	public ProposalDevelopmentBudgetExt getBudget() {
		return budget;
	}

	public void setBudget(ProposalDevelopmentBudgetExt budget) {
		this.budget = budget;
	}

}

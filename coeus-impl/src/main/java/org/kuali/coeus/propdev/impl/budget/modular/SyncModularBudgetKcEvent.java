package org.kuali.coeus.propdev.impl.budget.modular;

import org.kuali.coeus.common.framework.ruleengine.KcEventBase;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;

public class SyncModularBudgetKcEvent extends KcEventBase {

	public static final String RULE_NAME = "KC-B:syncModularBudget";
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

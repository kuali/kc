package org.kuali.coeus.propdev.impl.budget.subaward;

import java.util.List;

import org.kuali.coeus.common.framework.ruleengine.KcEventBase;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;

public class BudgetSubAwardsEvent extends KcEventBase {

	public static final String EVENT_NAME = "KC-B:addBudgetSubAward";

	private BudgetSubAwards budgetSubAwards;
	
	public BudgetSubAwardsEvent(BudgetSubAwards budgetSubAwards) {
		super(EVENT_NAME);
		this.budgetSubAwards = budgetSubAwards;
	}

	public BudgetSubAwards getBudgetSubAwards() {
		return budgetSubAwards;
	}

	public void setBudgetSubAwards(BudgetSubAwards budgetSubAwards) {
		this.budgetSubAwards = budgetSubAwards;
	}
}

package org.kuali.coeus.propdev.impl.budget.core;

import org.kuali.coeus.common.budget.framework.core.BudgetParent;
import org.kuali.coeus.common.budget.framework.version.AddBudgetVersionEvent;

public class ProposalAddBudgetVersionEvent extends AddBudgetVersionEvent {

	public ProposalAddBudgetVersionEvent(String errorPath,
			BudgetParent budgetParent, String versionName) {
		super(errorPath, budgetParent, versionName);
	}


}

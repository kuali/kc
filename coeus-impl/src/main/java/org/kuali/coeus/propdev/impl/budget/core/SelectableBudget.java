package org.kuali.coeus.propdev.impl.budget.core;

import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;

public interface SelectableBudget {
	
	public void setSelectedBudget(ProposalDevelopmentBudgetExt budget);
	public ProposalDevelopmentBudgetExt getSelectedBudget();

}

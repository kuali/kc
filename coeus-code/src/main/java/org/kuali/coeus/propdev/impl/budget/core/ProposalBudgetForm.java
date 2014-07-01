package org.kuali.coeus.propdev.impl.budget.core;

import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.rice.krad.web.form.UifFormBase;

public class ProposalBudgetForm extends UifFormBase {

	private ProposalDevelopmentBudgetExt budget;

	public ProposalDevelopmentBudgetExt getBudget() {
		return budget;
	}
	public void setBudget(ProposalDevelopmentBudgetExt budget) {
		this.budget = budget;
	}
}

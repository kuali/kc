package org.kuali.coeus.common.budget.impl.nonpersonnel;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetEventBase;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetFormulatedCostDetail;

public class AddFormulatedCostBudgetEvent extends BudgetEventBase {

	private BudgetFormulatedCostDetail formulatedCostDetail;
	
	public AddFormulatedCostBudgetEvent(Budget budget, String errorPath, BudgetFormulatedCostDetail formulatedCostDetail) {
		super(budget, errorPath);
		this.formulatedCostDetail = formulatedCostDetail;
	}

	public BudgetFormulatedCostDetail getFormulatedCostDetail() {
		return formulatedCostDetail;
	}

	public void setFormulatedCostDetail(
			BudgetFormulatedCostDetail formulatedCostDetail) {
		this.formulatedCostDetail = formulatedCostDetail;
	}



}

package org.kuali.coeus.common.budget.framework.personnel;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetSaveLineItemPeriodEvent;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;

public class BudgetSavePersonnelPeriodEvent extends BudgetSaveLineItemPeriodEvent {

	private BudgetPersonnelDetails budgetPersonnelDetails;
	private int editLineIndex;
	
	public BudgetSavePersonnelPeriodEvent(Budget budget, BudgetPeriod budgetPeriod, BudgetLineItem budgetLineItem, 
			BudgetPersonnelDetails budgetPersonnelDetails, int editLineIndex, String errorPath) {
		super(budget, budgetPeriod, budgetLineItem, errorPath); 
		this.budgetPersonnelDetails = budgetPersonnelDetails;
		this.editLineIndex = editLineIndex;
	}

	public BudgetPersonnelDetails getBudgetPersonnelDetails() {
		return budgetPersonnelDetails;
	}

	public void setBudgetPersonnelDetails(BudgetPersonnelDetails budgetPersonnelDetails) {
		this.budgetPersonnelDetails = budgetPersonnelDetails;
	}

	public int getEditLineIndex() {
		return editLineIndex;
	}

	public void setEditLineIndex(int editLineIndex) {
		this.editLineIndex = editLineIndex;
	}
}

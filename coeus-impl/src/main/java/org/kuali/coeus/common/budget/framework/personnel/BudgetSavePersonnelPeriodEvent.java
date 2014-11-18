package org.kuali.coeus.common.budget.framework.personnel;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetSaveLineItemPeriodEvent;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;

public class BudgetSavePersonnelPeriodEvent extends BudgetSaveLineItemPeriodEvent {

	private BudgetPersonnelDetails budgetPersonnelDetails;
	
	public BudgetSavePersonnelPeriodEvent(Budget budget, BudgetPeriod budgetPeriod, BudgetLineItem budgetLineItem, 
			BudgetPersonnelDetails budgetPersonnelDetails, String errorPath) {
		super(budget, budgetPeriod, budgetLineItem, errorPath); 
		this.budgetPersonnelDetails = budgetPersonnelDetails;
	}

	public BudgetPersonnelDetails getBudgetPersonnelDetails() {
		return budgetPersonnelDetails;
	}

	public void setBudgetPersonnelDetails(BudgetPersonnelDetails budgetPersonnelDetails) {
		this.budgetPersonnelDetails = budgetPersonnelDetails;
	}

}

package org.kuali.coeus.common.budget.framework.personnel;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.nonpersonnel.ApplyToPeriodsBudgetEvent;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;

public class PersonnelApplyToPeriodsBudgetEvent extends ApplyToPeriodsBudgetEvent {

	public PersonnelApplyToPeriodsBudgetEvent(Budget budget, String errorPath,
			BudgetLineItem budgetLineItem, BudgetPeriod budgetPeriod) {
		super(budget, errorPath, budgetLineItem, budgetPeriod);
	}

}

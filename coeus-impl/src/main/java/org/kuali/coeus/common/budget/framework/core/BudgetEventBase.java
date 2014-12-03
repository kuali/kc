package org.kuali.coeus.common.budget.framework.core;


public class BudgetEventBase {

	private Budget budget;
	private String errorPath;
	
	public BudgetEventBase(Budget budget) {
		this.budget = budget;
	}
	public BudgetEventBase(Budget budget, String errorPath) {
		this.budget = budget;
		this.errorPath = errorPath;
	}

	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}
	public String getErrorPath() {
		return errorPath;
	}
	public void setErrorPath(String errorPath) {
		this.errorPath = errorPath;
	}

}

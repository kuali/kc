package org.kuali.coeus.propdev.impl.budget.core;

public class AddBudgetDto {

	private String budgetName;
	private Boolean summaryBudget;
	private Boolean modularBudget;
	private Long originalBudgetId;
	private Boolean allPeriods;

	public String getBudgetName() {
		return budgetName;
	}

	public void setBudgetName(String budgetName) {
		this.budgetName = budgetName;
	}

	public Boolean getSummaryBudget() {
		return summaryBudget;
	}

	public void setSummaryBudget(Boolean summaryBudget) {
		this.summaryBudget = summaryBudget;
	}

	public Boolean getModularBudget() {
		return modularBudget;
	}

	public void setModularBudget(Boolean modularBudget) {
		this.modularBudget = modularBudget;
	}

	public Long getOriginalBudgetId() {
		return originalBudgetId;
	}

	public void setOriginalBudgetId(Long originalBudgetId) {
		this.originalBudgetId = originalBudgetId;
	}

	public Boolean getAllPeriods() {
		return allPeriods;
	}

	public void setAllPeriods(Boolean allPeriods) {
		this.allPeriods = allPeriods;
	}
	
}

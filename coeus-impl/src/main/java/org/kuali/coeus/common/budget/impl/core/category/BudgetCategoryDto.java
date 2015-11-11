package org.kuali.coeus.common.budget.impl.core.category;

public class BudgetCategoryDto {

	private String code;
	private String budgetCategoryTypeCode;
	private String description;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getBudgetCategoryTypeCode() {
		return budgetCategoryTypeCode;
	}
	public void setBudgetCategoryTypeCode(String budgetCategoryTypeCode) {
		this.budgetCategoryTypeCode = budgetCategoryTypeCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}

package org.kuali.kra.budget.printing.util;

import org.kuali.kra.budget.BudgetDecimal;

import java.util.List;

/**
 * This class having the budget salary calculated values.
 */
public class SalaryTypeVO {
	private String costElement;
	private String costElementCode;
	private String name;
	private BudgetDecimal total;
	private List<BudgetDataPeriodVO> budgetPeriodVOs;

	public String getCostElement() {
		return costElement;
	}

	public void setCostElement(String costElement) {
		this.costElement = costElement;
	}

	public String getCostElementCode() {
		return costElementCode;
	}

	public void setCostElementCode(String costElementCode) {
		this.costElementCode = costElementCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BudgetDecimal getTotal() {
		return total;
	}

	public void setTotal(BudgetDecimal total) {
		this.total = total;
	}

	public List<BudgetDataPeriodVO> getBudgetPeriodVOs() {
		return budgetPeriodVOs;
	}

	public void setBudgetPeriodVOs(List<BudgetDataPeriodVO> budgetPeriodVOs) {
		this.budgetPeriodVOs = budgetPeriodVOs;
	}
}

/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.common.budget.impl.print;

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.util.List;

/**
 * This class having the budget salary calculated values.
 */
public class SalaryTypeVO {
	private String costElement;
	private String costElementCode;
	private String name;
	private ScaleTwoDecimal total;
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

	public ScaleTwoDecimal getTotal() {
		return total;
	}

	public void setTotal(ScaleTwoDecimal total) {
		this.total = total;
	}

	public List<BudgetDataPeriodVO> getBudgetPeriodVOs() {
		return budgetPeriodVOs;
	}

	public void setBudgetPeriodVOs(List<BudgetDataPeriodVO> budgetPeriodVOs) {
		this.budgetPeriodVOs = budgetPeriodVOs;
	}
}

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

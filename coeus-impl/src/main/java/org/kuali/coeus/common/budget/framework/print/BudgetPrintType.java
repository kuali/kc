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

package org.kuali.coeus.common.budget.framework.print;

/**
 * This class represents different types of reports for Budget Printing
 */
public enum BudgetPrintType {
	BUDGET_SUMMARY_REPORT("Budget Summary Report"), BUDGET_COST_SHARE_SUMMARY_REPORT(
			"Budget Costshare Summary Report"), INDUSTRIAL_CUMULATIVE_BUDGET_REPORT(
			"Industrial Cumulative Budget Report"), BUDGET_SALARY_REPORT(
			"Budget Salary Report"), BUDGET_TOTAL_REPORT("Budget Total Report"), BUDGET_SUMMARY_TOTAL_REPORT(
			"Budget Summary Total Report"), BUDGET_CUMULATIVE_REPORT("Budget Cumulative Report"), INDUSTRIAL_BUDGET_REPORT(
			"Industrial Budget Report");

	private final String budgetPrintType;

	BudgetPrintType(String budgetPrintType) {
		this.budgetPrintType = budgetPrintType;
	}

	public String getBudgetPrintType() {
		return budgetPrintType;
	}
}

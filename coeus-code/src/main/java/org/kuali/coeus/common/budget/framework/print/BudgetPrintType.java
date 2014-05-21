/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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

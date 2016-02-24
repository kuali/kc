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
package org.kuali.coeus.common.budget.framework.personnel;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetEventBase;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;

public class AddPersonnelBudgetEvent extends BudgetEventBase {

	private BudgetPeriod budgetPeriod;
	private BudgetLineItem budgetLineItem;
	private BudgetPersonnelDetails budgetPersonnelDetails;
	private String errorKey;
	
	public AddPersonnelBudgetEvent(Budget budget, BudgetPeriod budgetPeriod, BudgetLineItem budgetLineItem, BudgetPersonnelDetails budgetPersonnelDetails,
			String errorKey) {
		super(budget);
		this.budgetPeriod = budgetPeriod;
		this.budgetLineItem = budgetLineItem;
		this.budgetPersonnelDetails = budgetPersonnelDetails;
		this.errorKey = errorKey;
	}

	public BudgetLineItem getBudgetLineItem() {
		return budgetLineItem;
	}

	public void setBudgetLineItem(BudgetLineItem budgetLineItem) {
		this.budgetLineItem = budgetLineItem;
	}

	public BudgetPersonnelDetails getBudgetPersonnelDetails() {
		return budgetPersonnelDetails;
	}

	public void setBudgetPersonnelDetails(
			BudgetPersonnelDetails budgetPersonnelDetails) {
		this.budgetPersonnelDetails = budgetPersonnelDetails;
	}

	public BudgetPeriod getBudgetPeriod() {
		return budgetPeriod;
	}

	public void setBudgetPeriod(BudgetPeriod budgetPeriod) {
		this.budgetPeriod = budgetPeriod;
	}

	public String getErrorKey() {
		return errorKey;
	}

	public void setErrorKey(String errorKey) {
		this.errorKey = errorKey;
	}

}

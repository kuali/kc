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
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetSaveLineItemPeriodEvent;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;

public class BudgetSavePersonnelPeriodEvent extends BudgetSaveLineItemPeriodEvent {

	private BudgetPersonnelDetails budgetPersonnelDetails;
	private int editLineIndex;
	
	public BudgetSavePersonnelPeriodEvent(Budget budget, BudgetPeriod budgetPeriod, BudgetLineItem budgetLineItem, 
			BudgetPersonnelDetails budgetPersonnelDetails, int editLineIndex, String errorPath) {
		super(budget, budgetPeriod, budgetLineItem, errorPath); 
		this.budgetPersonnelDetails = budgetPersonnelDetails;
		this.editLineIndex = editLineIndex;
	}

	public BudgetPersonnelDetails getBudgetPersonnelDetails() {
		return budgetPersonnelDetails;
	}

	public void setBudgetPersonnelDetails(BudgetPersonnelDetails budgetPersonnelDetails) {
		this.budgetPersonnelDetails = budgetPersonnelDetails;
	}

	public int getEditLineIndex() {
		return editLineIndex;
	}

	public void setEditLineIndex(int editLineIndex) {
		this.editLineIndex = editLineIndex;
	}
}

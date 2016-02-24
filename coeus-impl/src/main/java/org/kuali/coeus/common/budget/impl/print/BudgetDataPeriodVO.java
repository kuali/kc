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

public class BudgetDataPeriodVO {
	private int budgetPeriodId;
	private ScaleTwoDecimal periodCost;

	public int getBudgetPeriodId() {
		return budgetPeriodId;
	}

	public void setBudgetPeriodId(int budgetPeriofId) {
		this.budgetPeriodId = budgetPeriofId;
	}

	public ScaleTwoDecimal getPeriodCost() {
		return periodCost;
	}

	public void setPeriodCost(ScaleTwoDecimal periodCost) {
		this.periodCost = periodCost;
	}
}

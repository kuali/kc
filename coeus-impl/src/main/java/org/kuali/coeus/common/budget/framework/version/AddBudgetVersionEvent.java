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
package org.kuali.coeus.common.budget.framework.version;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.budget.framework.core.BudgetParent;

public class AddBudgetVersionEvent {

    private static final Log LOG = LogFactory.getLog(AddBudgetVersionEvent.class);

    private String versionName;
    private BudgetParent budgetParent;
    private String errorPath;

    public AddBudgetVersionEvent(String errorPath, BudgetParent budgetParent, String versionName) {
    	this.budgetParent = budgetParent;
    	this.versionName = versionName;
    	this.errorPath = errorPath;
    }

	public BudgetParent getBudgetParent() {
		return budgetParent;
	}

	public void setBudgetParent(BudgetParent budgetParent) {
		this.budgetParent = budgetParent;
	}

	public String getErrorPath() {
		return errorPath;
	}

	public void setErrorPath(String errorPath) {
		this.errorPath = errorPath;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
}


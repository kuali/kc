/*
 * Copyright 2005-2014 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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


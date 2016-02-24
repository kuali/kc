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

import static org.kuali.kra.infrastructure.KeyConstants.BUDGET_VERSION_EXISTS;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_BUDGET_NAME_MISSING;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.version.BudgetVersionRule;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRule;
import org.kuali.coeus.common.framework.ruleengine.KcEventMethod;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@KcBusinessRule("proposalBudgetVersionRule")
public class ProposalBudgetVersionRule extends BudgetVersionRule {
	
	@Autowired
	@Qualifier("globalVariableService")
	private GlobalVariableService globalVariableService;
	
	protected boolean processAddBudgetVersionName(ProposalAddBudgetVersionEvent event) {
		boolean retval = true;
        if (!isNameValid(event.getVersionName())) {
            retval = false;
            getGlobalVariableService().getMessageMap().putError(event.getErrorPath() + ".budgetName", 
                    ERROR_BUDGET_NAME_MISSING, "Name");
        }
        
        for (Budget budget : event.getBudgetParent().getBudgets()) {
        	if (StringUtils.equals(budget.getName(), event.getVersionName())) {
	            retval = false;
	            getGlobalVariableService().getMessageMap().putError(event.getErrorPath() + ".budgetName", BUDGET_VERSION_EXISTS);
	            break;
        	}
        }
        return retval;
	}
	
	@KcEventMethod
	public boolean processAddBudgetVersion(ProposalAddBudgetVersionEvent event) throws WorkflowException {
		boolean retval = true;
		retval &= processAddBudgetVersionName(event);
        retval &= super.processAddBudgetVersion(event);
        return retval;
	}

	public GlobalVariableService getGlobalVariableService() {
		return globalVariableService;
	}

	public void setGlobalVariableService(GlobalVariableService globalVariableService) {
		this.globalVariableService = globalVariableService;
	}
}

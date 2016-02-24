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
package org.kuali.coeus.propdev.impl.budget.modular;

import java.util.List;

import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRule;
import org.kuali.coeus.common.framework.ruleengine.KcEventMethod;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@KcBusinessRule("syncModularBudgetRule")
public class SyncModularBudgetKcRule {

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;
	
	@KcEventMethod
	public Boolean processSyncModularBusinessRules(SyncModularBudgetKcEvent event) {
        Boolean valid = true;
        List budgetPeriods = event.getBudget().getBudgetPeriods();
        if (ObjectUtils.isNotNull(budgetPeriods) && !budgetPeriods.isEmpty()) {
            BudgetPeriod period1 = (BudgetPeriod) budgetPeriods.get(0);
            if (ObjectUtils.isNull(period1.getBudgetLineItems()) || period1.getBudgetLineItems().isEmpty()) {
                valid = false;
            }
        } else {
            valid = false;
        }
        
        if (!valid) {
            globalVariableService.getMessageMap().putError("modularBudget", KeyConstants.ERROR_NO_DETAILED_BUDGET);
        }
        
        return valid;
	}

	public GlobalVariableService getGlobalVariableService() {
		return globalVariableService;
	}

	public void setGlobalVariableService(GlobalVariableService globalVariableService) {
		this.globalVariableService = globalVariableService;
	}
}

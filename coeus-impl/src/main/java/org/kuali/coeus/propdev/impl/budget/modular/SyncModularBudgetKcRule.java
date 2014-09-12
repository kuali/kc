package org.kuali.coeus.propdev.impl.budget.modular;

import java.util.List;

import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRule;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRuleBase;
import org.kuali.coeus.common.framework.ruleengine.KcEventMethod;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@KcBusinessRule("syncModularBudgetRule")
public class SyncModularBudgetKcRule extends KcBusinessRuleBase {

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;
	
	@KcEventMethod(events = {SyncModularBudgetKcEvent.RULE_NAME})
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

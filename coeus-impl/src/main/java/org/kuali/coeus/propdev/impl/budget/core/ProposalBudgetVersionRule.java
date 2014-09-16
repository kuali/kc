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

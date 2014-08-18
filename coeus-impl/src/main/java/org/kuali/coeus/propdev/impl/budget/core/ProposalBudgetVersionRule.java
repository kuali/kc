package org.kuali.coeus.propdev.impl.budget.core;

import static org.kuali.kra.infrastructure.KeyConstants.BUDGET_VERSION_EXISTS;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_BUDGET_NAME_MISSING;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.version.AddBudgetVersionEvent;
import org.kuali.coeus.common.budget.impl.version.BudgetVersionRule;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("proposalBudgetVersionRule")
public class ProposalBudgetVersionRule extends BudgetVersionRule {
	
	@Autowired
	@Qualifier("globalVariableService")
	private GlobalVariableService globalVariableService;
	
	public boolean processAddBudgetVersionName(AddBudgetVersionEvent event) {
		boolean retval = true;
        if (!isNameValid(event.getVersionName())) {
            retval = false;
            getGlobalVariableService().getMessageMap().putError(event.getErrorPathPrefix() + ".budgetName", 
                    ERROR_BUDGET_NAME_MISSING, "Name");
        }
        
        for (Budget budget : event.getBudgetParent().getBudgetVersionOverviews()) {
        	if (StringUtils.equals(budget.getName(), event.getVersionName())) {
	            retval = false;
	            getGlobalVariableService().getMessageMap().putError(event.getErrorPathPrefix() + ".budgetName", BUDGET_VERSION_EXISTS);
	            break;
        	}
        }
        return retval;
	}
	
	public boolean processAddBudgetVersion(AddBudgetVersionEvent event) throws WorkflowException {
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
